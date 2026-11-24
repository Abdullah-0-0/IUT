#!/usr/bin/env bash
set -Eeuo pipefail
BASE="$(cd -- "$(dirname -- "${BASH_SOURCE[0]}")" && pwd)"
URL="${1:-}"
case "$URL" in roblox:*|roblox-player:*) ;; *) exit 2 ;; esac
[[ "$URL" != *$'\n'* && "$URL" != *$'\r'* ]] || exit 2
FIFO="$BASE/wireguard/roblox-link.fifo"
[[ -p "$FIFO" ]] || { echo 'Lancer Chrome avec start-chrome.sh avant de cliquer Jouer.' >&2; exit 1; }
timeout 7 bash -c 'printf "%s\n" "$1" > "$2"' _ "$URL" "$FIFO"
