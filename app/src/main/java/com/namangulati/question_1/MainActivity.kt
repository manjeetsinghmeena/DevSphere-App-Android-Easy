package com.namangulati.question_1

import android.os.Bundle
import android.widget.Button      
import android.widget.EditText    
import android.widget.TextView    
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    companion object {
        fun calculateTax(income: Double, taxRate: Double): Pair<Double, Double> {
            // TODO: Implement tax calculation
             val tax = (taxRate/100.0)*income
            val finalIncome = income - tax

            return Pair(tax, finalIncome)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

         val etIncome = findViewById<EditText>(R.id.etIncome)
         val etTaxRate = findViewById<EditText>(R.id.etTaxRate)
         val btnCalculate = findViewById<Button>(R.id.btnCalculate)
         val tvTaxAmount = findViewById<TextView>(R.id.tvTaxAmount)
         val tvFinalIncome = findViewById<TextView>(R.id.tvFinalIncome)

        btnCalculate.setOnClickListener {
           val income = etIncome.text.toString().trim().toDoubleOrNull() ?: 0.0
           val rate = etTaxRate.text.toString().trim().toDoubleOrNull() ?: 0.0

            val result = calculateTax(income,rate)
            // TODO: Call calculateTax and update tvTaxAmount, tvFinalIncome
            tvTaxAmount.text = "Tax Amount: ₹" + result.first.toString()
            tvFinalIncome.text = "Final Income: ₹"+ result.second.toString()
        }
    }
}