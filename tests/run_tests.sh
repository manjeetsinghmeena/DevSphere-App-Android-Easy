#!/usr/bin/env bash
set -euo pipefail

ROOT="$(cd "$(dirname "$0")/.." && pwd)"
SAMPLES="$ROOT/samples"
CHECKER="$ROOT/tests/checker.sh"
TIMEOUT=300

PASS=0; FAIL=0
chmod +x "$CHECKER"

for IN_FILE in "$SAMPLES"/in_*.txt; do
  NAME=$(basename "$IN_FILE" | sed 's/^in_//' | sed 's/\.txt$//')
  OUT_FILE="$SAMPLES/out_${NAME}.txt"

  echo -n "==> Test [$NAME] ... "
  ACTUAL=$(timeout "$TIMEOUT" bash "$CHECKER" 2>&1) || true
  LAST_LINE=$(echo "$ACTUAL" | tail -n 1)

  if [[ "$LAST_LINE" == "PASS" ]]; then
    echo "PASS"
    PASS=$((PASS + 1))
  else
    echo "FAIL"
    echo "$ACTUAL"
    FAIL=$((FAIL + 1))
  fi
done

echo ""
echo "Results: $PASS passed, $FAIL failed"
[[ $FAIL -eq 0 ]]