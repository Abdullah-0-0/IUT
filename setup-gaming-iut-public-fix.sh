#!/usr/bin/env bash
# Kit public Chrome + Sober + WireGuard -> VM OpenNebula -> Cloudflare WARP.
# Partageable : AUCUNE cle privee SSH ni identifiant administrateur inclus.
# Usage proprio : bash setup-gaming-iut-public.sh ajouter pseudo
# Usage ami     : bash setup-gaming-iut-public.sh installer /chemin/pseudo.peer
# Option proprio: bash setup-gaming-iut-public.sh retirer pseudo
# Cible : PC Linux x86_64, VM Debian OpenNebula existante.
set -Eeuo pipefail
umask 077

VM_HOST="${VM_HOST:-172.21.45.125}"
VM_USER="${VM_USER:-debian}"
VM_PORT="${VM_PORT:-49561}"
VM_FP="SHA256:fwUgO8Z0M1/Q8nvJFS9XoaAZdpuUFRpr1szcjX5MTGs"
OWNER_BASE="${OWNER_BASE:-$HOME/reseau/Perso/Téléchargements/INFO1/tkt}"
OWNER_SSH_KEY="${OWNER_SSH_KEY:-$OWNER_BASE/mindustry/ssh/opennebula}"
if [[ -d "$HOME/reseau/Perso" ]]; then
  DEFAULT_INSTALL="$HOME/reseau/Perso/gaming-iut-amis"
else
  DEFAULT_INSTALL="$HOME/gaming-iut-amis"
fi
INSTALL_DIR="${INSTALL_DIR:-$DEFAULT_INSTALL}"

say() { printf '\n===== %s =====\n' "$*"; }
fail() { printf 'ERREUR : %s\n' "$*" >&2; exit 1; }
need() { command -v "$1" >/dev/null 2>&1 || fail "Commande manquante : $1"; }

# Le serveur doit etre accessible depuis le reseau de l'IUT. Un .peer n'est
# pas un compte SSH : chaque ami a uniquement sa propre identite WireGuard.
owner_connect() {
  local mode="$1" name="$2" pub="${3:-}"
  need ssh; need ssh-keyscan; need ssh-keygen; need wg; need mktemp
  [[ -f "$OWNER_SSH_KEY" ]] || fail "Cle SSH du proprietaire introuvable : $OWNER_SSH_KEY"
  [[ "$name" =~ ^[a-zA-Z0-9_-]{2,32}$ ]] || fail 'Pseudo : 2 a 32 caracteres (lettres, chiffres, _ ou -).'
  local hostfile
  hostfile="$(mktemp)"
  trap 'rm -f "$hostfile"' EXIT
  ssh-keyscan -T 6 -t ed25519 "$VM_HOST" >"$hostfile" 2>/dev/null || true
  [[ -s "$hostfile" ]] || fail "La VM $VM_HOST est injoignable en SSH."
  local fpr
  fpr="$(ssh-keygen -lf "$hostfile" -E sha256 | awk 'NR==1 {print $2}')"
  [[ "$fpr" == "$VM_FP" ]] || fail "Empreinte SSH inattendue ($fpr). Refus de continuer."
  local opts=(-o BatchMode=yes -o IdentitiesOnly=yes -o ConnectTimeout=8
              -o StrictHostKeyChecking=yes -o "UserKnownHostsFile=$hostfile" -i "$OWNER_SSH_KEY")
  if [[ "$mode" == add ]]; then
    ssh "${opts[@]}" "$VM_USER@$VM_HOST" \
      "sudo -n bash -s -- '$name' '$pub'" <<'VM_ADD'
set -Eeuo pipefail
umask 077
NAME="$1"; PUB="$2"
[[ "$NAME" =~ ^[a-zA-Z0-9_-]{2,32}$ ]] || exit 2
[[ "$PUB" =~ ^[A-Za-z0-9+/]{43}=$ ]] || exit 2
[[ "$(id -u)" == 0 ]] || { echo 'sudo root necessaire' >&2; exit 1; }
[[ -f /etc/wireguard/wg-xbox.conf ]] || { echo 'wg-xbox.conf absent : configurer d abord ta VM avec ton installation actuelle.' >&2; exit 1; }
command -v wg >/dev/null || { echo 'wireguard-tools manquant' >&2; exit 1; }
command -v nft >/dev/null || { echo 'nft manquant' >&2; exit 1; }
command -v warp-cli >/dev/null || { echo 'Cloudflare WARP manquant sur la VM' >&2; exit 1; }
systemctl is-active --quiet wg-quick@wg-xbox || systemctl start wg-quick@wg-xbox
systemctl enable wg-quick@wg-xbox >/dev/null
systemctl enable --now warp-svc >/dev/null

# Etat stocke par nom pour eviter les doublons et faciliter la revocation.
mkdir -p /etc/wireguard/iut-friends
chmod 700 /etc/wireguard/iut-friends
[[ ! -e "/etc/wireguard/iut-friends/$NAME" ]] || { echo "PSEUDO_DEJA_UTILISE : $NAME" >&2; exit 1; }
# Bloquer les conflits de cle publique.
if wg show wg-xbox peers | grep -Fx "$PUB" >/dev/null; then
  echo 'CLE_DEJA_AUTORISEE' >&2; exit 1
fi
CLIENT_IP=''
for n in $(seq 3 254); do
  ip="10.77.77.$n"
  if ! wg show wg-xbox allowed-ips | grep -F "/32" | grep -F "${ip}/32" >/dev/null && \
     ! grep -RhF "AllowedIPs = ${ip}/32" /etc/wireguard/wg-xbox.conf /etc/wireguard/iut-friends 2>/dev/null | grep -F "$ip" >/dev/null; then
    CLIENT_IP="$ip"
    break
  fi
done
[[ -n "$CLIENT_IP" ]] || { echo 'Aucune IP WireGuard disponible' >&2; exit 1; }
# Ne jamais remplacer le client original 10.77.77.2.
cat >>/etc/wireguard/wg-xbox.conf <<EOF_PEER

# ami:$NAME
[Peer]
PublicKey = $PUB
AllowedIPs = $CLIENT_IP/32
EOF_PEER
chmod 600 /etc/wireguard/wg-xbox.conf
wg set wg-xbox peer "$PUB" allowed-ips "$CLIENT_IP/32"
printf 'PUB=%s\nIP=%s\n' "$PUB" "$CLIENT_IP" > "/etc/wireguard/iut-friends/$NAME"
chmod 600 "/etc/wireguard/iut-friends/$NAME"

# Protection persistante avant toute connexion WARP. Ne jamais laisser les
# clients amis sortir directement sur le LAN de la VM si le tunnel tombe.
cat >/usr/local/sbin/iut-friends-guard <<'GUARD'
#!/usr/bin/env bash
set -Eeuo pipefail
if ! ip -4 rule show | grep -F 'from 10.77.77.0/24 prohibit' >/dev/null; then
  ip -4 rule add pref 116 from 10.77.77.0/24 prohibit
fi
if ! nft list table inet iut_friends_forward >/dev/null 2>&1; then
  nft -f - <<'NFT'
table inet iut_friends_forward {
  chain forward {
    type filter hook forward priority -10; policy accept;
    iifname "wg-xbox" ip saddr 10.77.77.0/24 oifname != "CloudflareWARP" drop
  }
  chain input {
    type filter hook input priority -10; policy accept;
    iifname "wg-xbox" ip saddr 10.77.77.3-10.77.77.254 ip daddr 10.77.77.1 icmp type echo-request accept
    iifname "wg-xbox" ip saddr 10.77.77.3-10.77.77.254 drop
  }
}
NFT
fi
GUARD
chmod 700 /usr/local/sbin/iut-friends-guard
mkdir -p /etc/systemd/system/wg-quick@wg-xbox.service.d
cat >/etc/systemd/system/wg-quick@wg-xbox.service.d/20-iut-friends-guard.conf <<'DROPIN'
[Service]
ExecStartPost=/usr/local/sbin/iut-friends-guard
DROPIN
/usr/local/sbin/iut-friends-guard

# WARP reste gere par la VM : aucun client ami ne recoit une cle SSH.
# Une regle prohibit empeche la fuite via ens3 si WARP est arrete.
cat >/usr/local/sbin/iut-friends-warp <<'GW'
#!/usr/bin/env bash
set -Eeuo pipefail
exec 8>/run/iut-friends-warp.lock
flock -n 8 || exit 0
systemctl is-active --quiet wg-quick@wg-xbox || systemctl start wg-quick@wg-xbox
systemctl is-active --quiet warp-svc || systemctl start warp-svc
if ! warp-cli --accept-tos status 2>/dev/null | grep -F 'Connected' >/dev/null; then
  timeout 35 warp-cli --accept-tos connect >/dev/null 2>&1 || true
fi
# Rester en mode WARP, pas DNS only. Config deja testee sur la VM.
for _ in $(seq 1 10); do
  if ip -4 route get 1.1.1.1 2>/dev/null | grep -F 'dev CloudflareWARP' >/dev/null; then break; fi
  sleep 2
done
if ! ip -4 route get 1.1.1.1 | grep -F 'dev CloudflareWARP' >/dev/null; then
  echo 'WARP pas encore route : les amis restent bloques (kill-switch)' >&2
  exit 1
fi
if ! ip -4 rule show | grep -F 'from 10.77.77.0/24 lookup 65743' >/dev/null; then
  ip -4 rule add pref 115 from 10.77.77.0/24 lookup 65743
fi
/usr/local/sbin/iut-friends-guard
if ! nft list table ip iut_friends_nat >/dev/null 2>&1; then
  nft -f - <<'NFT'
table ip iut_friends_nat {
  chain postrouting {
    type nat hook postrouting priority srcnat; policy accept;
    ip saddr 10.77.77.0/24 oifname "CloudflareWARP" masquerade
  }
}
NFT
fi
ip -4 route get 1.1.1.1 from 10.77.77.3 iif wg-xbox | grep -F 'dev CloudflareWARP' >/dev/null || {
  echo 'Echec routage ami vers WARP' >&2; exit 1;
}
echo 'WARP_AMIS=OK'
GW
chmod 700 /usr/local/sbin/iut-friends-warp
cat >/etc/systemd/system/iut-friends-warp.service <<'SERVICE'
[Unit]
Description=Maintien permanent de WARP pour clients WireGuard amis
After=network-online.target warp-svc.service wg-quick@wg-xbox.service
Wants=network-online.target
[Service]
Type=oneshot
ExecStart=/usr/local/sbin/iut-friends-warp
SERVICE
cat >/etc/systemd/system/iut-friends-warp.timer <<'TIMER'
[Unit]
Description=Surveillance WARP pour clients WireGuard amis
[Timer]
OnBootSec=15s
OnUnitActiveSec=30s
OnCalendar=*-*-* *:*:00
AccuracySec=5s
Persistent=true
Unit=iut-friends-warp.service
[Install]
WantedBy=timers.target
TIMER
systemctl daemon-reload
systemctl enable --now iut-friends-warp.timer >/dev/null
sysctl -w net.ipv4.ip_forward=1 >/dev/null
printf 'net.ipv4.ip_forward = 1\n' >/etc/sysctl.d/92-iut-friends-forward.conf
# Ne pas interrompre un stream si notre propre WARP est deja actif.
/usr/local/sbin/iut-friends-warp || echo 'WARP non pret : le timer reessaiera automatiquement.' >&2
SERVER_PUB="$(wg show wg-xbox public-key)"
printf 'CLIENT_IP=%s\nSERVER_PUB=%s\n' "$CLIENT_IP" "$SERVER_PUB"
VM_ADD
  else
    ssh "${opts[@]}" "$VM_USER@$VM_HOST" "sudo -n bash -s -- '$name'" <<'VM_REMOVE'
set -Eeuo pipefail
NAME="$1"; DB="/etc/wireguard/iut-friends/$NAME"
[[ -f "$DB" ]] || { echo 'Pseudo introuvable' >&2; exit 1; }
PUB="$(sed -n 's/^PUB=//p' "$DB")"
IP="$(sed -n 's/^IP=//p' "$DB")"
# Supprime seulement le bloc avec la cle publique de cet ami.
python3 - "$PUB" <<'PY'
import pathlib,sys,re
p=pathlib.Path('/etc/wireguard/wg-xbox.conf')
s=p.read_text()
chunks=re.split(r'(?m)(?=^\[Peer\]\s*$)',s)
keep=[chunks[0]]
for part in chunks[1:]:
    if re.search(r'(?m)^PublicKey\s*=\s*'+re.escape(sys.argv[1])+r'\s*$',part):
        continue
    keep.append(part)
p.write_text(''.join(keep))
PY
wg set wg-xbox peer "$PUB" remove
rm -f "$DB"
printf 'REVOQUE=%s\n' "$NAME"
VM_REMOVE
  fi
  trap - EXIT
  rm -f "$hostfile"
}

case "${1:-}" in
  ajouter)
    [[ $# -eq 2 ]] || fail 'Usage : bash setup-gaming-iut-public.sh ajouter pseudo'
    NAME="$2"
    need wg; need python3
    mkdir -p "$OWNER_BASE/clients"
    OUT="$OWNER_BASE/clients/$NAME.peer"
    [[ ! -e "$OUT" ]] || fail "Fichier deja present : $OUT"
    TMPKEY="$(mktemp)"; trap 'rm -f "$TMPKEY"' EXIT
    wg genkey > "$TMPKEY"; chmod 600 "$TMPKEY"
    PUB="$(wg pubkey <"$TMPKEY")"
    say "Ajout de $NAME sur ta VM (sans partager SSH)"
    RESPONSE="$(owner_connect add "$NAME" "$PUB")"
    IP="$(printf '%s\n' "$RESPONSE" | sed -n 's/^CLIENT_IP=//p' | tail -n 1)"
    SERVER_PUB="$(printf '%s\n' "$RESPONSE" | sed -n 's/^SERVER_PUB=//p' | tail -n 1)"
    [[ "$IP" =~ ^10\.77\.77\.([3-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-4])$ ]] || fail "IP serveur invalide. Diagnostic : $RESPONSE"
    [[ "$SERVER_PUB" =~ ^[A-Za-z0-9+/]{43}=$ ]] || fail 'Cle publique du serveur invalide.'
    cat >"$OUT" <<EOF_CLIENT
# Acces personnel au VPN de la VM - ne pas publier ce fichier.
# Donne-le uniquement a $NAME, en prive.
CLIENT_KEY=$(cat "$TMPKEY")
CLIENT_IP=$IP
SERVER_PUB=$SERVER_PUB
VM_HOST=$VM_HOST
VM_PORT=$VM_PORT
EOF_CLIENT
    chmod 600 "$OUT"
    say "Profil de $NAME cree : $OUT"
    echo "Partage a ton pote 1) ce script PUBLIC 2) uniquement son fichier $NAME.peer"
    echo "Il lance : bash setup-gaming-iut-public.sh installer $NAME.peer"
    ;;
  retirer)
    [[ $# -eq 2 ]] || fail 'Usage : bash setup-gaming-iut-public.sh retirer pseudo'
    say "Revocation du VPN pour $2"
    owner_connect remove "$2"
    echo 'Le profil ami correspondant ne pourra plus se connecter. Pense a supprimer sa copie locale si besoin.'
    ;;
  installer)
    [[ $# -eq 2 ]] || fail 'Usage : bash setup-gaming-iut-public.sh installer pseudo.peer'
    PROFILE_FILE="$2"
    [[ -f "$PROFILE_FILE" ]] || fail "Profil .peer absent : $PROFILE_FILE"
    [[ "$(uname -s)" == Linux && "$(uname -m)" == x86_64 ]] || fail 'PC Linux x86_64 requis.'
    [[ "$EUID" != 0 ]] || fail 'Lance le kit sans sudo.'
    [[ ! -e "$INSTALL_DIR/start-chrome.sh" ]] || fail "Installation deja presente dans $INSTALL_DIR. Aucun ecrasement."
    REQUIRED=(bash wg ip unshare slirp4netns setpriv flatpak curl ping flock python3 xdg-mime)
    MISSING=()
    for prog in "${REQUIRED[@]}"; do
      command -v "$prog" >/dev/null 2>&1 || MISSING+=("$prog")
    done
    if ((${#MISSING[@]})); then
      echo "Dependances absentes : ${MISSING[*]}"
      if command -v sudo >/dev/null 2>&1; then
        if command -v dnf >/dev/null 2>&1; then
          sudo dnf install -y wireguard-tools iproute iputils util-linux slirp4netns flatpak curl python3 xdg-utils desktop-file-utils
        elif command -v apt-get >/dev/null 2>&1; then
          sudo apt-get update
          sudo apt-get install -y wireguard-tools iproute2 iputils-ping util-linux slirp4netns flatpak curl python3 xdg-utils desktop-file-utils
        fi
      fi
    fi
    for prog in "${REQUIRED[@]}"; do
      command -v "$prog" >/dev/null || fail "Outil manquant : $prog. Sur un PC gere sans sudo, l'administrateur doit l'installer."
    done
    # Lire le fichier ami comme des DONNEES, ne jamais l'executer avec source.
    CLIENT_KEY=''; CLIENT_IP=''; SERVER_PUB=''; CLIENT_VM=''; CLIENT_PORT=''
    while IFS= read -r LINE || [[ -n "$LINE" ]]; do
      LINE="${LINE%$'\r'}"
      [[ "$LINE" == *=* ]] || continue
      K="${LINE%%=*}"
      V="${LINE#*=}"
      case "$K" in
        CLIENT_KEY) CLIENT_KEY="$V" ;;
        CLIENT_IP) CLIENT_IP="$V" ;;
        SERVER_PUB) SERVER_PUB="$V" ;;
        VM_HOST) CLIENT_VM="$V" ;;
        VM_PORT) CLIENT_PORT="$V" ;;
      esac
    done < "$PROFILE_FILE"
    [[ "$CLIENT_KEY" =~ ^[A-Za-z0-9+/]{43}=$ && "$SERVER_PUB" =~ ^[A-Za-z0-9+/]{43}=$ ]] || fail 'Cles WireGuard invalides dans le fichier ami.'
    [[ "$CLIENT_IP" =~ ^10\.77\.77\.([3-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-4])$ ]] || fail 'IP WireGuard invalide.'
    [[ "$CLIENT_VM" == "$VM_HOST" && "$CLIENT_PORT" == "$VM_PORT" ]] || fail 'Profil configure pour une autre VM. Ne pas modifier manuellement.'
    # Verifier l'accessibilite de la VM avant d'installer quoi que ce soit.
    if ! timeout 7 bash -c 'echo >/dev/tcp/"$1"/22' _ "$CLIENT_VM" 2>/dev/null; then
      echo "ATTENTION : la VM $CLIENT_VM ne repond pas au TCP/22." >&2
      echo "Si le PC n'est pas sur un reseau autorise a joindre OpenNebula, le VPN ne pourra pas fonctionner." >&2
      echo 'L installation est possible, mais le VPN exigera que le port UDP WireGuard de la VM soit joignable.' >&2
    fi
    say 'Installation Flatpak (Chrome et Sober)'
    mkdir -p "$INSTALL_DIR/wireguard" "$INSTALL_DIR/chrome-profile"
    chmod 700 "$INSTALL_DIR" "$INSTALL_DIR/wireguard" "$INSTALL_DIR/chrome-profile"
    # Flatpak utilisateur : on ne deplace JAMAIS un repertoire deja existant.
    STD_FLATPAK="$HOME/.local/share/flatpak"
    if [[ ! -e "$STD_FLATPAK" && ! -L "$STD_FLATPAK" ]]; then
      mkdir -p "$INSTALL_DIR/flatpak-user" "$(dirname "$STD_FLATPAK")"
      ln -s "$INSTALL_DIR/flatpak-user" "$STD_FLATPAK"
    fi
    unset FLATPAK_USER_DIR
    flatpak --user remote-add --if-not-exists flathub https://dl.flathub.org/repo/flathub.flatpakrepo
    flatpak --user install -y flathub com.google.Chrome org.vinegarhq.Sober
    # Assurer la persistance de Sober si le HOME de l'IUT est efface entre sessions.
    SOBER_VAR="$HOME/.var/app/org.vinegarhq.Sober"
    if [[ ! -e "$SOBER_VAR" && ! -L "$SOBER_VAR" ]]; then
      mkdir -p "$HOME/.var/app" "$INSTALL_DIR/sober-app"
      chmod 700 "$INSTALL_DIR/sober-app"
      ln -s "$INSTALL_DIR/sober-app" "$SOBER_VAR"
    fi
    printf '%s\n' "$CLIENT_KEY" > "$INSTALL_DIR/wireguard/client.key"
    chmod 600 "$INSTALL_DIR/wireguard/client.key"
    cat >"$INSTALL_DIR/gaming.conf" <<EOF_CONF
CLIENT_IP='$CLIENT_IP'
SERVER_PUB='$SERVER_PUB'
VM_HOST='$CLIENT_VM'
VM_PORT='$CLIENT_PORT'
EOF_CONF
    chmod 600 "$INSTALL_DIR/gaming.conf"

    # Le lanceur ne contient AUCUNE cle SSH et ne configure jamais la VM.
    cat >"$INSTALL_DIR/start-chrome.sh" <<'LAUNCH'
#!/usr/bin/env bash
set -Eeuo pipefail
BASE="$(cd -- "$(dirname -- "${BASH_SOURCE[0]}")" && pwd)"
source "$BASE/gaming.conf"
MODE="${1:-chrome}"
[[ "$MODE" == chrome || "$MODE" == sober ]] || { echo 'Usage: start-chrome.sh [chrome|sober]' >&2; exit 2; }
PROFILE="$BASE/chrome-profile"
CLIENT_KEY="$BASE/wireguard/client.key"
mkdir -p "$PROFILE"; chmod 700 "$PROFILE"
exec 9>"$BASE/wireguard/launch.lock"
flock -n 9 || { echo 'Le navigateur VPN est deja en cours.' >&2; exit 1; }
export BASE PROFILE CLIENT_KEY CLIENT_IP SERVER_PUB VM_HOST VM_PORT MODE
unshare --user --map-current-user --keep-caps bash 9>&- <<'PARENT'
set -Eeuo pipefail
TMP="$(mktemp -d)"
CHILD=''; SLIRP=''
cleanup_net() {
  [[ -z "$SLIRP" ]] || kill "$SLIRP" 2>/dev/null || true
  [[ -z "$CHILD" ]] || kill "$CHILD" 2>/dev/null || true
  rm -rf "$TMP"
}
trap cleanup_net EXIT
unshare --net --keep-caps bash -c '
  set -Eeuo pipefail
  ip link set lo up
  echo "$$" > "$1"
  for i in $(seq 1 60); do
    ip -4 route show default | grep -F "default" >/dev/null && break
    sleep 0.25
  done
  ip -4 route show default | grep -F "default" >/dev/null || { echo "slirp4netns sans route" >&2; exit 1; }
  ip link add wg-friend type wireguard
  wg set wg-friend private-key "$CLIENT_KEY" peer "$SERVER_PUB" \
    endpoint "$VM_HOST:$VM_PORT" allowed-ips 0.0.0.0/0 persistent-keepalive 15
  ip addr add "$CLIENT_IP/24" dev wg-friend
  ip link set wg-friend mtu 1280 up
  ip route replace "$VM_HOST/32" via 10.0.2.2 dev tap0
  ip route replace default dev wg-friend
  ping -c 1 -W 4 10.77.77.1 >/dev/null || { echo "WireGuard : VM injoignable" >&2; exit 1; }
  echo "===== VERIFICATION WARP ====="
  TRACE=$(env -u http_proxy -u https_proxy -u HTTP_PROXY -u HTTPS_PROXY -u ALL_PROXY -u all_proxy \
    curl -4fsS --noproxy "*" --connect-timeout 8 --max-time 20 \
    https://www.cloudflare.com/cdn-cgi/trace) || { echo "Internet indisponible" >&2; exit 1; }
  printf "%s\n" "$TRACE" | grep -E "^(ip|warp)="
  printf "%s\n" "$TRACE" | grep -Fx "warp=on" >/dev/null || { echo "WARP inactif, lancement refuse" >&2; exit 1; }
  FIFO="$BASE/wireguard/roblox-link.fifo"
  if [[ -e "$FIFO" && ! -p "$FIFO" ]]; then echo "Fichier Roblox inattendu" >&2; exit 1; fi
  [[ -p "$FIFO" ]] || (umask 077; mkfifo -m 600 "$FIFO")
  (
    while :; do
      if IFS= read -r LINK < "$FIFO"; then
        case "$LINK" in
          roblox:*|roblox-player:*)
            echo "===== ROBLOX VIA VPN ====="
            env -u http_proxy -u https_proxy -u HTTP_PROXY -u HTTPS_PROXY -u ALL_PROXY -u all_proxy \
              setpriv --bounding-set=-all --inh-caps=-all --ambient-caps=-all \
              flatpak --user run --share=network org.vinegarhq.Sober "$LINK" &
            ;;
        esac
      fi
    done
  ) &
  RELAY=$!
  trap "kill $RELAY 2>/dev/null || true; rm -f \"$FIFO\"" EXIT
  if [[ "$MODE" == sober ]]; then
    echo "===== LANCEMENT SOBER ====="
    env -u http_proxy -u https_proxy -u HTTP_PROXY -u HTTPS_PROXY -u ALL_PROXY -u all_proxy \
      setpriv --bounding-set=-all --inh-caps=-all --ambient-caps=-all \
      flatpak --user run --share=network org.vinegarhq.Sober
  else
    echo "===== LANCEMENT CHROME ====="
    env -u http_proxy -u https_proxy -u HTTP_PROXY -u HTTPS_PROXY -u ALL_PROXY -u all_proxy \
      setpriv --bounding-set=-all --inh-caps=-all --ambient-caps=-all \
      flatpak --user run --share=network --filesystem="$PROFILE" com.google.Chrome \
      --user-data-dir="$PROFILE" --no-first-run --no-proxy-server \
      --new-window https://www.xbox.com/play
  fi
' _ "$TMP/pid" &
CHILD=$!
for i in $(seq 1 50); do
  [[ -s "$TMP/pid" ]] && break
  kill -0 "$CHILD" 2>/dev/null || break
  sleep 0.1
done
[[ -s "$TMP/pid" ]] || { echo 'Namespace non disponible' >&2; exit 1; }
slirp4netns --configure --mtu=1420 "$(cat "$TMP/pid")" tap0 >"$TMP/slirp.log" 2>&1 &
SLIRP=$!
set +e
wait "$CHILD"; STATUS=$?
set -e
if [[ "$STATUS" -ne 0 ]]; then cat "$TMP/slirp.log" >&2; fi
exit "$STATUS"
PARENT
LAUNCH
    chmod 700 "$INSTALL_DIR/start-chrome.sh"
    bash -n "$INSTALL_DIR/start-chrome.sh"
    # Le lien Roblox provenant de Chrome/xdg-open arrive dans le namespace.
    cat >"$INSTALL_DIR/roblox-link.sh" <<'LINK'
#!/usr/bin/env bash
set -Eeuo pipefail
BASE="$(cd -- "$(dirname -- "${BASH_SOURCE[0]}")" && pwd)"
URL="${1:-}"
case "$URL" in roblox:*|roblox-player:*) ;; *) exit 2 ;; esac
[[ "$URL" != *$'\n'* && "$URL" != *$'\r'* ]] || exit 2
FIFO="$BASE/wireguard/roblox-link.fifo"
[[ -p "$FIFO" ]] || { echo 'Lancer Chrome avec start-chrome.sh avant de cliquer Jouer.' >&2; exit 1; }
timeout 7 bash -c 'printf "%s\n" "$1" > "$2"' _ "$URL" "$FIFO"
LINK
    chmod 700 "$INSTALL_DIR/roblox-link.sh"
    bash -n "$INSTALL_DIR/roblox-link.sh"
    # .desktop genere sans interpolation de shell par les donnees de l'ami.
    DESKTOP_DIR="$HOME/.local/share/applications"
    mkdir -p "$DESKTOP_DIR"
    # Chemin desktop correctement quote ; le script de lien est dans INSTALL_DIR.
    EXEC_PATH="$(python3 - "$INSTALL_DIR/roblox-link.sh" <<'PY_Q'
import sys
p=sys.argv[1].replace('\\','\\\\').replace('"','\\"').replace('$','\\$').replace('`','\\`')
print('"'+p+'"')
PY_Q
)"
    cat >"$DESKTOP_DIR/iut-friends-sober.desktop" <<EOF_DESKTOP
[Desktop Entry]
Type=Application
Name=Roblox via Sober et VPN
Exec=$EXEC_PATH %u
Terminal=false
NoDisplay=true
MimeType=x-scheme-handler/roblox;x-scheme-handler/roblox-player;
Categories=Game;
EOF_DESKTOP
    chmod 644 "$DESKTOP_DIR/iut-friends-sober.desktop"
    if command -v update-desktop-database >/dev/null; then
      update-desktop-database "$DESKTOP_DIR"
    fi
    xdg-mime default iut-friends-sober.desktop x-scheme-handler/roblox
    xdg-mime default iut-friends-sober.desktop x-scheme-handler/roblox-player
    say 'Installation terminee'
    echo "Chrome : $INSTALL_DIR/start-chrome.sh"
    echo "Sober  : $INSTALL_DIR/start-chrome.sh sober"
    echo 'Roblox depuis Chrome : se connecter sur roblox.com puis Jouer.'
    echo 'AUCUNE donnee Chrome preexistante n a ete importee.'
    echo 'Sober reste experimental : une verification Roblox peut encore echouer.'
    echo 'La VM doit etre accessible depuis le reseau du PC.'
    ;;
  *)
    cat <<'HELP'
KIT PUBLIC Chrome + Sober + WireGuard/WARP (sans cle SSH admin)

Pour le proprietaire (sur son PC actuel a l'IUT) :
    bash setup-gaming-iut-public.sh ajouter pseudo
    bash setup-gaming-iut-public.sh retirer pseudo

Pour un ami (Linux x86_64, sur le reseau autorise) :
    bash setup-gaming-iut-public.sh installer pseudo.peer

Le fichier .sh est public. Chaque .peer est PRIVE et unique.
Pas de migration de cookies, comptes ou extensions.
HELP
    ;;
esac
