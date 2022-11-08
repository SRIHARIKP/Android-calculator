package com.trenser.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private var resultField: TextView? = null
    private var operatorView: TextView? = null
    private var pendingOperator: String = ""
    private var result: Double = 0.0

    var firstNumber:Double=0.0
    var secondNumber:Double=0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var button0: Button = findViewById(R.id.button42)
        var button1: Button = findViewById(R.id.button38)
        var button2: Button = findViewById(R.id.button39)
        var button3: Button = findViewById(R.id.button37)
        var button4: Button = findViewById(R.id.button35)
        var button5: Button = findViewById(R.id.button36)
        var button6: Button = findViewById(R.id.button34)
        var button7: Button = findViewById(R.id.button23)
        var button8: Button = findViewById(R.id.button32)
        var button9: Button = findViewById(R.id.button33)
        var buttonDot: Button = findViewById(R.id.button41)

        resultField = findViewById(R.id.editTextNumber2)
        operatorView = findViewById(R.id.textView2)

        var buttonEquals: Button = findViewById(R.id.button47)
        var buttonPlus: Button = findViewById(R.id.buttonAdd)
        var buttonMinus: Button = findViewById(R.id.buttonMinus)
        var buttonMultiply: Button = findViewById(R.id.buttonMultiply)
        var buttonDivide: Button = findViewById(R.id.buttonDivide)
        var buttonClear:Button = findViewById(R.id.buttonClear)

        var numberField = findViewById<EditText>(R.id.editTextNumber)

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

//        val operatorlistener = View.OnClickListener { v ->
//            val b = v as Button
//            Log.d("OPERATOR", pendingOperator.equals("").toString())
//            if(numberField?.text.toString().equals("")){
//                numberField.setText(result.toString())
//            }
//
//            if (pendingOperator.equals("") ) {
//                result = numberField.text.toString().toDouble()
//                operatorView?.setText(b.text.toString())
//
//                numberField.setText("")
//            } else {
//                var num1 = numberField.text.toString().toDouble()
//                performFunc(num1, result,pendingOperator)
//                operatorView?.setText(b.text.toString())
//
//                numberField.setText("")
//
//            }
//            if(!b.text.equals("=")){
//                pendingOperator = b.text.toString()
//            }else{
//                pendingOperator = ""
//            }
//            operatorView?.setText(b.text.toString())
//
//            numberField.setText("")
//        }
//
//        buttonEquals.setOnClickListener(operatorlistener)
//        buttonPlus.setOnClickListener(operatorlistener)
//        buttonMinus.setOnClickListener(operatorlistener)
//        buttonMultiply.setOnClickListener(operatorlistener)
//        buttonDivide.setOnClickListener(operatorlistener)
//
//    }
//
//    fun performFunc(num1:Double, num2: Double, optr: String) {
//        Log.d("OPERATOR", optr)
//        Log.d(
//            "OPERATOR",
//            "numbfield: ${num1}\nresult: ${num2}"
//        )
//        when (optr) {
//
//            "+" -> result = num1 + num2
//            "-" -> result = num2 - num1
//            "*" -> result = num1 * num2
//            "/" -> result = num2 / num1
//
//
//        }
//        resultField?.setText(result.toString())
//    }

        val operatorlistener = View.OnClickListener { v ->
            val b = v as Button
            Log.d("number", "${numberField.text}")
            try {
                    if (pendingOperator.equals("")) {
                        firstNumber = numberField.text!!.toString().toDouble()
                    } else {
                        Log.d("here", "hello")
                        if(!pendingOperator.equals("=")) {
                            secondNumber = numberField.text.toString().toDouble()
                            performFunc(firstNumber, secondNumber, pendingOperator)
                        }
                        firstNumber = result
                    }

                    operatorView?.setText(b.text.toString())
                    pendingOperator = b.text.toString()
                    numberField.setText("")
            }catch(e: NumberFormatException){
                Log.d("Exception","Provide operands")
            }
        }
        buttonEquals.setOnClickListener(operatorlistener)
        buttonPlus.setOnClickListener(operatorlistener)
        buttonMinus.setOnClickListener(operatorlistener)
        buttonMultiply.setOnClickListener(operatorlistener)
        buttonDivide.setOnClickListener(operatorlistener)



        val clearClickListener=View.OnClickListener {v->
            numberField.setText("")
            operatorView?.setText("")
            pendingOperator=""
            firstNumber=0.0
            secondNumber=0.0
            resultField?.setText("")
            result=0.0

        }
        buttonClear.setOnClickListener(clearClickListener)
    }

    fun performFunc(num1:Double, num2: Double, optr: String) {
        Log.d("OPERATOR", optr)
        when (pendingOperator) {

            "+" -> result = num1 + num2
            "-" -> result = num1 - num2
            "*" -> result = num1 * num2
            "/" ->  if(num2==0.0){
                        return
                    }else{
                        result = num1 / num2
                    }
        }
        resultField?.setText(result.toString())
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if(firstNumber !=null){
            outState?.putDouble("Firstnumber",firstNumber)
        }
        outState?.putString("Pendingoperator",pendingOperator)
        outState?.putBoolean("ContentSaved",true)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        if(savedInstanceState.getBoolean("ContentSaved",false)) {
            firstNumber = savedInstanceState.getDouble("Firstnumber")
        }else{
            firstNumber=0.0
        }
        pendingOperator= savedInstanceState.getString("Pendingoperator")!!
    }


}