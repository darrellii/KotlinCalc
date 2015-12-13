package com.darrellii.kodlin.kotlincalc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class JavaMainActivity extends AppCompatActivity {


    private boolean isCurrentlyTypingNumber = false;
    private int currentNumber = 0;
    private Operation op = Operation.NONE;
    private int total = 0;

    enum Operation {
        MULTIPLY,
        DIVIDE,
        ADD,
        SUBTRACT,
        NONE
    }

    TextView display, displayHistory;
    Button javaKotlinSwitch;
    Button b0, b1, b2, b3, b4, b5, b6, b7, b8, b9,
            plus, minus, times, divide, equal, delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display = (TextView) findViewById(R.id.display);
        displayHistory = (TextView) findViewById(R.id.displayHistory);
        javaKotlinSwitch = (Button) findViewById(R.id.javaKotlinSwitch);
        b0 = (Button) findViewById(R.id.b0);
        b1 = (Button) findViewById(R.id.b1);
        b2 = (Button) findViewById(R.id.b2);
        b3 = (Button) findViewById(R.id.b3);
        b4 = (Button) findViewById(R.id.b4);
        b5 = (Button) findViewById(R.id.b5);
        b6 = (Button) findViewById(R.id.b6);
        b7 = (Button) findViewById(R.id.b7);
        b8 = (Button) findViewById(R.id.b8);
        b9 = (Button) findViewById(R.id.b9);
        plus = (Button) findViewById(R.id.plus);
        minus = (Button) findViewById(R.id.minus);
        times = (Button) findViewById(R.id.times);
        divide = (Button) findViewById(R.id.divide);
        equal = (Button) findViewById(R.id.equal);
        delete = (Button) findViewById(R.id.delete);

        display.setText("0");
        displayHistory.setText("");
        javaKotlinSwitch.setText("Switch to Kotlin");


        b0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendNumber(0);
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendNumber(1);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendNumber(2);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendNumber(3);
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendNumber(4);
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendNumber(5);
            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendNumber(6);
            }
        });
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendNumber(7);
            }
        });
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendNumber(8);
            }
        });
        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendNumber(9);
            }
        });
        javaKotlinSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(JavaMainActivity.this, MainActivity.class));
            }
        });
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setOperation(Operation.ADD);
            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setOperation(Operation.SUBTRACT);
            }
        });
        times.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setOperation(Operation.MULTIPLY);
            }
        });
        divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setOperation(Operation.DIVIDE);
            }
        });
        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                evaluateProgram();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteOrClear();
            }
        });
    }

    private void deleteOrClear() {
        if(isCurrentlyTypingNumber){
            currentNumber /= 10;
            display.setText(display.getText().length() >1?
                            display.getText().subSequence(0,display.getText().length() -1): "0");
        }else{
            total = 0;
            display.setText("0");
            displayHistory.setText("");
            currentNumber = total;
            op = Operation.NONE;
        }

    }

    private void evaluateProgram() {
        setOperation(Operation.NONE);
        currentNumber = total;
    }

    private void setOperation(Operation newOp) {
        switch (op) {
            case ADD:
                total += currentNumber;
                break;
            case DIVIDE:
                total = total / currentNumber;
                break;
            case SUBTRACT:
                total = total - currentNumber;
                break;
            case MULTIPLY:
                total *= currentNumber;
                break;
            case NONE:
                total = currentNumber;
                break;
        }
        switch (newOp) {
            case ADD:
                displayHistory.setText(total+"+");
                break;
            case DIVIDE:
                displayHistory.setText(total+"÷");
                break;
            case SUBTRACT:
                displayHistory.setText((total + "-"));
                break;
            case MULTIPLY:
                displayHistory.setText(total+"x");
                break;
            case NONE:
                displayHistory.setText(total);
                break;
        }
        op = newOp;
        isCurrentlyTypingNumber = false;
        currentNumber = 0;
        display.setText("0");
    }

    private void appendNumber(int i) {
        currentNumber = currentNumber*10 + i;
        display.setText(isCurrentlyTypingNumber? display.getText() + String.valueOf(i) :
                String.valueOf(i));
        isCurrentlyTypingNumber = true;
    }

}
