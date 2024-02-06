package com.bignerdranch.android.simplecalculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textInputNumber1: EditText = findViewById(R.id.textInput_number1)
        val textInputNumber2: EditText = findViewById(R.id.textInput_number2)
        val spinnerOperation: Spinner = findViewById(R.id.spinner_operation)
        val btnCalculate: Button = findViewById(R.id.btn_calculate)
        val textViewResult: TextView = findViewById(R.id.textView_result)

        ArrayAdapter.createFromResource(
            this,
            R.array.operations_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerOperation.adapter = adapter
        }

        btnCalculate.setOnClickListener {
            val num1 = textInputNumber1.text.toString().toDoubleOrNull()
            val num2 = textInputNumber2.text.toString().toDoubleOrNull()
            if (num1 == null || num2 == null) {
                textViewResult.text = getString(R.string.please_enter_valid_numbers)
                return@setOnClickListener
            }
            val operation = spinnerOperation.selectedItem.toString()
            var result: Any? = null

            if (operation == "Add") {
                result = num1 + num2
            } else if (operation == "Subtract") {
                result =  - num2
            } else if (operation == "Multiply") {
                result = num1 * num2
            } else if (operation == "Divide") {
                if (num2 != 0.0){
                    result = num1 / num2
                } else {
                    result = "Cannot divide by zero."
                }
            } else if (operation == "Modulus"){
                num1 % num2
            } else {
                result = "Invalid operation"
            }

            if (result == null) {
                textViewResult.text =
                    getString(R.string.an_error_occured_please_retry_the_number_values)
            } else {
                textViewResult.text = "Result: $result"
            }
        }
    }
}