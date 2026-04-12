package com.namangulati.question_1

import android.os.Bundle
import android.widget.Button      
import android.widget.EditText    
import android.widget.TextView    
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    companion object {
        fun calculateTax(income: Double, taxRate: Double): Pair<Double, Double> {
            // TODO: Implement tax calculation
            return Pair(0.0, 0.0)
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

        // val etIncome      = 
        // val etTaxRate     = 
        // val btnCalculate  = 
        // val tvTaxAmount   = 
        // val tvFinalIncome = 

        btnCalculate.setOnClickListener {
            // val income 
            // val rate   

            // TODO: Call calculateTax and update tvTaxAmount, tvFinalIncome
        }
    }
}