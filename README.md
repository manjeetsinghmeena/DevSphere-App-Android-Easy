# 🟢 Easy: Tax Calculator (Android Basics)

## Challenge Overview
In this challenge, you are tasked with completing a simple Android application that calculates income tax. You are provided with a partially implemented `MainActivity` and must complete both the core tax calculation logic and the user interface wiring. 

Your goal is to implement the missing logic to ensure the calculation works accurately for all income values and tax rates, and that the app correctly responds to user interactions.

## Problem Description
You are given an Android project with a `MainActivity.kt` file containing a shell of the application logic. 

The `calculateTax` function inside the `companion object` accepts the following parameters:
- `income`: `Double` - The total income.
- `taxRate`: `Double` - The tax percentage to be applied (e.g., `10.0` for 10%, `12.5` for 12.5%).

You need to perform the following operations:

1. **Tax Calculation**: Implement the `calculateTax` function. It must return the computed tax amount and the final income (income minus tax).
2. **UI Initialization**: Uncomment and initialize the UI views (`etIncome`, `etTaxRate`, `btnCalculate`, `tvTaxAmount`, `tvFinalIncome`) in the `onCreate` method.
3. **Event Handling**: Set a click listener on the `btnCalculate` button to retrieve the parsed input values, perform the calculation, and update the text labels with the results.

## Expected Output
The `calculateTax` function should return a `Pair<Double, Double>`:

- `first` (Tax): A `Double` representing the calculated tax amount.
- `second` (Final Income): A `Double` representing the remaining income after the tax is deducted.

## How to Run
Run the unit tests:

```bash
./gradlew test
```
The unit tests are designed to fail initially. Your task is to modify `MainActivity.kt` until all tests in `TaxCalculatorTest.kt` pass.

## Important Implementation Notes
**UI Wiring**
Uncomment the variables and carefully assign them using `findViewById`.

**Input Parsing**
Ensure you parse the text from the `EditText` boxes to `Double` correctly before doing any calculations.

**Tax Calculation Logic**
Income can be decimal.
The tax calculation should support `0.0` edge cases or percentages up to `100.0`.

## Hints
- Pay close attention to how the `taxRate` is treated. A `taxRate` of `10.0` means 10% (i.e. `10.0 / 100`).
- Review the logic for calculating the final income. The sum of the `tax` and `final income` must exactly equal the original `income`.
- Think about edge cases: `0.0` income, `0.0` tax rate, or decimal math.
- Remember: The code is intentionally left partially unimplemented. Your structural problem solving and fundamental Android UI skills are key!
