package com.namangulati.question_1

import org.junit.Assert.*
import org.junit.Test

/**
 * Tests the tax calculation logic.
 * Participants must implement a companion object in MainActivity with:
 *
 *   companion object {
 *       fun calculateTax(income: Double, taxRate: Double): Pair<Double, Double> {
 *           val tax = income * taxRate / 100
 *           return Pair(tax, income - tax)
 *       }
 *   }
 */
class TaxCalculatorTest {

    private fun calc(income: Double, rate: Double) =
        MainActivity.calculateTax(income, rate)

    // ── Basic cases ───────────────────────────────────────────────────
    @Test
    fun basicTenPercent() {
        val (tax, final) = calc(100000.0, 10.0)
        assertEquals("Tax wrong",   10000.0, tax,   0.01)
        assertEquals("Final wrong", 90000.0, final, 0.01)
    }

    @Test
    fun basicTwentyPercent() {
        val (tax, final) = calc(50000.0, 20.0)
        assertEquals("Tax wrong",   10000.0, tax,   0.01)
        assertEquals("Final wrong", 40000.0, final, 0.01)
    }

    @Test
    fun basicFifteenPercent() {
        val (tax, final) = calc(200000.0, 15.0)
        assertEquals("Tax wrong",   30000.0, tax,   0.01)
        assertEquals("Final wrong", 170000.0, final, 0.01)
    }

    // ── Edge cases ────────────────────────────────────────────────────
    @Test
    fun zeroIncome() {
        val (tax, final) = calc(0.0, 30.0)
        assertEquals("Tax should be 0",   0.0, tax,   0.001)
        assertEquals("Final should be 0", 0.0, final, 0.001)
    }

    @Test
    fun zeroTaxRate() {
        val (tax, final) = calc(100000.0, 0.0)
        assertEquals("Tax should be 0",          0.0,      tax,   0.001)
        assertEquals("Final should equal income", 100000.0, final, 0.001)
    }

    @Test
    fun hundredPercentTax() {
        val (tax, final) = calc(50000.0, 100.0)
        assertEquals("Tax should equal income", 50000.0, tax,   0.01)
        assertEquals("Final should be 0",       0.0,     final, 0.01)
    }

    @Test
    fun decimalIncome() {
        val (tax, final) = calc(75000.50, 10.0)
        assertEquals("Tax wrong",   7500.05,  tax,   0.01)
        assertEquals("Final wrong", 67500.45, final, 0.01)
    }

    @Test
    fun decimalTaxRate() {
        val (tax, final) = calc(100000.0, 12.5)
        assertEquals("Tax wrong",   12500.0, tax,   0.01)
        assertEquals("Final wrong", 87500.0, final, 0.01)
    }

    // ── Formula integrity check ───────────────────────────────────────
    @Test
    fun taxPlusFinalEqualsIncome() {
        val income = 123456.78
        val rate   = 18.0
        val (tax, final) = calc(income, rate)
        assertEquals("tax + final must equal income", income, tax + final, 0.001)
    }
}