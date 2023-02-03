package com.example.calculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Numbers
        btn1.setOnClickListener { appendOnExpresstion("1", true) }
        btn2.setOnClickListener { appendOnExpresstion("2", true) }
        btn3.setOnClickListener { appendOnExpresstion("3", true) }
        btn4.setOnClickListener { appendOnExpresstion("4", true) }
        btn5.setOnClickListener { appendOnExpresstion("5", true) }
        btn6.setOnClickListener { appendOnExpresstion("6", true) }
        btn7.setOnClickListener { appendOnExpresstion("7", true) }
        btn8.setOnClickListener { appendOnExpresstion("8", true) }
        btn9.setOnClickListener { appendOnExpresstion("9", true) }
        btnzero.setOnClickListener { appendOnExpresstion("0", true) }
        btndot.setOnClickListener { appendOnExpresstion(".", true) }

        //Operators
        btnadd.setOnClickListener { appendOnExpresstion("+", false) }
        btnsub.setOnClickListener { appendOnExpresstion("-", false) }
        btnmult.setOnClickListener { appendOnExpresstion("*", false) }
        btndiv.setOnClickListener { appendOnExpresstion("/", false) }


        //clear the workingTextView
        btnac.setOnClickListener {
            workingTV.text = ""
            resultsTV.text = ""
            numbers=0
        }

        //delete the most recent pressed button
        btnbcsp.setOnClickListener {
            val string = workingTV.text.toString()
            if (string.isNotEmpty()) {
                workingTV.text = string.substring(0, string.length - 1)
                if (string.last() in setOf('+', '-', '*', '/')) {
                    //println("print number from inside the if: " + numbers)//for debug purposes
                } else {
                    numbers--
                }
                if(numbers < 0){
                    numbers = 0
                }
            }
            resultsTV.text = ""
        }

        btneq.setOnClickListener {
            var final = 0.0
            val pattern = "((?:\\d+(?:\\.\\d+)?))([+\\-*/])((?:\\d+(?:\\.\\d+)?))".toRegex()
            val str = workingTV.text.toString()
            val result = pattern.find(str)
            if (result != null) {
                val (num1, op, num2) = result.destructured
                val int1 = num1.toDouble()
                val int2 = num2.toDouble()
                when (op) {
                    "+" -> final = int1 + int2
                    "-" -> final = int1 - int2
                    "*" -> final = int1 * int2
                    "/" -> final = int1 / int2
                }
            }

            //if modulo is = 0 then it's an int
            if (final % 1.0 == 0.0) {
                resultsTV.text = final.toInt().toString()
                numbers=resultsTV.length()
            } else {
                resultsTV.text = final.toString()
                numbers=resultsTV.length()
            }
        }



    }

    var numbers : Int = 0

    fun appendOnExpresstion(string: String, canClear: Boolean) {

            if (resultsTV.text.isNotEmpty()) {
                workingTV.text = ""
            }
            //maximum 20 numbers displayed on the screen
            if (numbers < 20) {
                if (canClear) {
                    numbers++
                    resultsTV.text = ""
                    workingTV.append(string)

                } else {
                    workingTV.append(resultsTV.text)
                    workingTV.append(string)
                    resultsTV.text = ""
                }
            } else {
                workingTV.text ="error"
            }
        }
}
