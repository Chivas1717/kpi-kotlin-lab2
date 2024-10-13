package com.example.kpi_kotlin_lab2

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var resultView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val coalInput = findViewById<EditText>(R.id.coalAmountInput)
        val oilInput = findViewById<EditText>(R.id.oilAmountInput)
        val gasInput = findViewById<EditText>(R.id.gasAmountInput)

        val calculateButton = findViewById<Button>(R.id.calculateButton)
        resultView = findViewById(R.id.resultView)

        calculateButton.setOnClickListener {
            val coal = coalInput.text.toString().toDoubleOrNull() ?: 0.0
            val oil = oilInput.text.toString().toDoubleOrNull() ?: 0.0
            val gas = gasInput.text.toString().toDoubleOrNull() ?: 0.0

            val result = EmissionsCalculator.calculateEmissions(coal, oil, gas)
            displayResult(result)
        }
    }

    private fun displayResult(result: String) {
        resultView.text = result
    }
}
