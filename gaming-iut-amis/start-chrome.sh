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
