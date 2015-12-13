package com.darrellii.kodlin.kotlincalc

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.content_main.*

class MainActivity : AppCompatActivity() {

    private var isCurrentlyTypingNumber = false
    private var currentNumber = 0
    private var op = Operation.NONE
    private var total = 0

    private enum class Operation {
        MULTIPLY,
        DIVIDE,
        ADD,
        SUBTRACT,
        NONE
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*Set up is so much easier*/
        display.text = "0"
        displayHistory.text = ""
        b0.setOnClickListener { appendNumber(0) }
        b1.setOnClickListener { appendNumber(1) }
        b2.setOnClickListener { appendNumber(2) }
        b3.setOnClickListener { appendNumber(3) }
        b4.setOnClickListener { appendNumber(4) }
        b5.setOnClickListener { appendNumber(5) }
        b6.setOnClickListener { appendNumber(6) }
        b7.setOnClickListener { appendNumber(7) }
        b8.setOnClickListener { appendNumber(8) }
        b9.setOnClickListener { appendNumber(9) }
        plus.setOnClickListener { setOperation(Operation.ADD) }
        minus.setOnClickListener { setOperation(Operation.SUBTRACT) }
        divide.setOnClickListener { setOperation(Operation.DIVIDE) }
        times.setOnClickListener { setOperation(Operation.MULTIPLY) }
        equal.setOnClickListener { evaluateProgram() }
        delete.setOnClickListener { deleteOrClear() }
        javaKotlinSwitch.setOnClickListener { Intent(applicationContext as Context, JavaMainActivity::class.java) }
        javaKotlinSwitch.text = "Switch to Java"
    }


    private fun setOperation(newOp: Operation) {

        total = when (op) {
            Operation.ADD -> total + currentNumber
            Operation.DIVIDE -> total / currentNumber
            Operation.SUBTRACT -> total - currentNumber
            Operation.MULTIPLY -> total * currentNumber
            Operation.NONE -> currentNumber
        }
        displayHistory.text = total.toString() + when (newOp) {
            Operation.ADD -> "+"
            Operation.DIVIDE -> "÷"
            Operation.SUBTRACT -> "-"
            Operation.MULTIPLY -> "x"
            Operation.NONE -> ""
        }
        op = newOp
        isCurrentlyTypingNumber = false
        currentNumber = 0
        display.text = 0.toString()
    }

    private fun appendNumber(number: Int) {
        currentNumber =  currentNumber * 10 + number
        display.text = if(isCurrentlyTypingNumber) display.text.toString() + number else number.toString()
        isCurrentlyTypingNumber = true
    }

    private fun evaluateProgram() {
        setOperation(Operation.NONE)
        currentNumber = total
    }

    private fun deleteOrClear() {
        if (isCurrentlyTypingNumber) {
            currentNumber /= 10
            display.text =
                if(display.text.length>1)
                    display.text.subSequence(0,display.text.length-1)
                else
                    0.toString()
        } else {
            total = 0
            display.text = total.toString()
            displayHistory.text = ""
            currentNumber = total
            op = Operation.NONE
        }
        isCurrentlyTypingNumber = currentNumber != 0
    }

}
