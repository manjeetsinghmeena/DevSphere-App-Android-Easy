#!/usr/bin/env bash
set -euo pipefail

ROOT="$(cd "$(dirname "$0")/.." && pwd)"
MAIN="$ROOT/app/src/main/java/com/namangulati/question_1/MainActivity.kt"

# ── 1. MainActivity.kt must exist ─────────────────────────────────────
if [[ ! -f "$MAIN" ]]; then
  echo "FAIL: MainActivity.kt not found"
  exit 1
fi
echo "  [OK] MainActivity.kt exists"

# ── 2. Must reference all required view IDs ───────────────────────────
for id in etIncome etTaxRate btnCalculate tvTaxAmount tvFinalIncome; do
  if ! grep -q "$id" "$MAIN"; then
    echo "FAIL: MainActivity.kt does not reference view id '$id'"
    exit 1
  fi
done
echo "  [OK] All required view IDs are referenced"

# ── 3. Must have a click listener on btnCalculate ─────────────────────
if ! grep -qE "btnCalculate|setOnClickListener" "$MAIN"; then
  echo "FAIL: No click listener found for btnCalculate"
  exit 1
fi
echo "  [OK] Click listener present"

# ── 4. Must expose calculateTax companion function for unit tests ──────
if ! grep -q "calculateTax" "$MAIN"; then
  echo "FAIL: Missing 'calculateTax' companion object function"
  echo "      Add this to MainActivity:"
  echo "        companion object {"
  echo "          fun calculateTax(income: Double, taxRate: Double): Pair<Double, Double>"
  echo "        }"
  exit 1
fi
echo "  [OK] calculateTax function found"

# ── 4b. Verify calculateTax is not just returning the stub (0.0, 0.0) ──
if grep -q "return Pair(0.0, 0.0)" "$MAIN"; then
  echo "FAIL: calculateTax() is not implemented (still returning stub values)"
  exit 1
fi
echo "  [OK] calculateTax appears implemented"

# ── 5. Build the project ──────────────────────────────────────────────
echo "  Building project (this may take a minute)..."
cd "$ROOT"
chmod +x gradlew

BUILD_LOG=$(./gradlew assembleDebug --no-daemon 2>&1) || {
  # Extract only the error lines for a clean failure message
  echo "FAIL: Build failed. Compiler errors:"
  echo "$BUILD_LOG" | grep -E "^e:|error:" | head -20
  exit 1
}
echo "  [OK] Build successful"

# ── 6. Run unit tests ─────────────────────────────────────────────────
echo "  Running unit tests..."
if ! ./gradlew testDebugUnitTest --no-daemon -q 2>&1; then
  echo "FAIL: Unit tests failed"
  exit 1
fi
echo "  [OK] All unit tests passed"

echo "PASS"
exit 0