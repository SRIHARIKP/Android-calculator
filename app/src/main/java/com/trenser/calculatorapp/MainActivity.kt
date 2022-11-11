package com.trenser.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var resultField: TextView? = null
    private var operatorView: TextView? = null
    private var pendingOperator: String = ""
    private var result: Double = 0.0

    private var firstNumber: Double = 0.0
    private var secondNumber: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultField = findViewById(R.id.editTextNumber2)
        operatorView = findViewById(R.id.textView2)

        val numberField = findViewById<EditText>(R.id.editTextNumber)

        val listener = View.OnClickListener { v ->
            val b = v as Button
            numberField.append(b.text.toString())
        }

        button0.setOnClickListener(listener)
        button1.setOnClickListener(listener)
        button2.setOnClickListener(listener)
        button3.setOnClickListener(listener)
        button4.setOnClickListener(listener)
        button5.setOnClickListener(listener)
        button6.setOnClickListener(listener)
        button7.setOnClickListener(listener)
        button8.setOnClickListener(listener)
        button9.setOnClickListener(listener)
        buttonDot.setOnClickListener(listener)


        val operatorlistener = View.OnClickListener { v ->
            val b = v as Button
            Log.d("number", "${numberField.text}")
            try {
                if (pendingOperator == "") {
                    firstNumber = numberField.text!!.toString().toDouble()
                } else {
                    Log.d("here", "hello")
                    if (pendingOperator != "=") {
                        secondNumber = numberField.text.toString().toDouble()
                        performFunc(firstNumber, secondNumber, pendingOperator)
                    }
                    firstNumber = result
                }

                operatorView?.text = b.text.toString()
                pendingOperator = b.text.toString()
                numberField.setText("")
            } catch (e: NumberFormatException) {
                Log.d("Exception", "Provide operands")
            }
        }
        buttonEquals.setOnClickListener(operatorlistener)
        buttonAdd.setOnClickListener(operatorlistener)
        buttonMinus.setOnClickListener(operatorlistener)
        buttonMultiply.setOnClickListener(operatorlistener)
        buttonDivide.setOnClickListener(operatorlistener)


        val clearClickListener = View.OnClickListener { v ->
            numberField.setText("")
            operatorView?.text = ""
            pendingOperator = ""
            firstNumber = 0.0
            secondNumber = 0.0
            resultField?.text = ""
            result = 0.0

        }
        buttonClear.setOnClickListener(clearClickListener)
    }

    private fun performFunc(num1: Double, num2: Double, optr: String) {
        Log.d("OPERATOR", optr)
        when (pendingOperator) {

            "+" -> result = num1 + num2
            "-" -> result = num1 - num2
            "*" -> result = num1 * num2
            "/" -> if (num2 == 0.0) {
                return
            } else {
                result = num1 / num2
            }
        }
        resultField?.text = result.toString()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putDouble("Firstnumber", firstNumber)
        outState.putString("Pendingoperator", pendingOperator)
        outState.putBoolean("ContentSaved", true)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        firstNumber = if (savedInstanceState.getBoolean("ContentSaved", false)) {
            savedInstanceState.getDouble("Firstnumber")
        } else {
            0.0
        }
        pendingOperator = savedInstanceState.getString("Pendingoperator")!!
    }
}