package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import org.mariuszgromada.math.mxparser.Expression;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView resultTV, solutionTV;
    MaterialButton buttonC,buttonCE;
    MaterialButton buttonRoot, buttonChangeSign;

    MaterialButton buttonDivide,buttonMultiply,buttonSubtract,buttonSum,buttonEqual;

    MaterialButton button0,button1,button2,button3,button4,button5,button6,button7,button8,button9;
    MaterialButton buttonDot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTV = findViewById(R.id.result_tv);
        solutionTV=findViewById(R.id.solution_tv);

        assignId(buttonC, R.id.button_c);
        assignId(buttonCE, R.id.button_ce);
        assignId(buttonRoot, R.id.button_root);
        assignId(buttonChangeSign, R.id.button_changing_sign);
        assignId(buttonDivide, R.id.button_division);
        assignId(buttonMultiply, R.id.button_multiplication);
        assignId(buttonSubtract, R.id.button_subtraction);
        assignId(buttonSum, R.id.button_sum);
        assignId(buttonEqual, R.id.button_equal);
        assignId(button0, R.id.button_zero);
        assignId(button1, R.id.button_one);
        assignId(button2, R.id.button_two);
        assignId(button3, R.id.button_three);
        assignId(button4, R.id.button_four);
        assignId(button5, R.id.button_five);
        assignId(button6, R.id.button_six);
        assignId(button7, R.id.button_seven);
        assignId(button8, R.id.button_eight);
        assignId(button9, R.id.button_nine);
        assignId(buttonDot, R.id.button_dot);
    }

    void assignId(MaterialButton btn,int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    private String evaluateExpression(String expression) {
        Expression e = new Expression(expression);
        double result = e.calculate();

        // Check if the result is an integer
        if (result % 1 == 0) {
            return String.format("%.0f", result);  // Format to 0 decimal places
        } else {
            return String.valueOf(result);
        }
    }


    @Override
    public void onClick(View view) {
    MaterialButton button = (MaterialButton) view;
    String buttonText = button.getText().toString();
    String dataToCalculate = solutionTV.getText().toString();

    if (dataToCalculate.equals("null") || dataToCalculate.equals("0")) {
            if (Character.isDigit(buttonText.charAt(0)) || buttonText.equals(".")) {
                dataToCalculate = "";
            }
    }

    if (buttonText.equals("C")){
        solutionTV.setText("");
        resultTV.setText("0");
        return;
    }
        if (buttonText.equals("=")) {
            // Evaluate the expression and show the result
            String result = evaluateExpression(dataToCalculate);
            resultTV.setText(result);
            return;
        }
        if(buttonText.equals("CE")){
            dataToCalculate = dataToCalculate.substring(0,dataToCalculate.length()-1);
        }
        else if (buttonText.equals("±")) {
            int lastOperatorPos = -1;
            for (int i = dataToCalculate.length() - 1; i >= 0; i--) {
                char c = dataToCalculate.charAt(i);
                if (c == '+' || c == '-') {
                    lastOperatorPos = i;
                    break;
                }
            }

            if (lastOperatorPos == -1) { // No operators, just one number
                if (dataToCalculate.startsWith("-")) {
                    dataToCalculate = dataToCalculate.substring(1);
                } else {
                    dataToCalculate = "-" + dataToCalculate;
                }
            } else {
                if (dataToCalculate.charAt(lastOperatorPos) == '-') {
                    if (lastOperatorPos == 0) { // If '-' is the very first character
                        dataToCalculate = dataToCalculate.substring(1);
                    } else {
                        dataToCalculate = dataToCalculate.substring(0, lastOperatorPos) +
                                "+" + dataToCalculate.substring(lastOperatorPos + 1);
                    }
                } else {
                    dataToCalculate = dataToCalculate.substring(0, lastOperatorPos) +
                            "-" + dataToCalculate.substring(lastOperatorPos + 1);
                }
            }
        }

        else{
            dataToCalculate = dataToCalculate+buttonText;
        }
    solutionTV.setText(dataToCalculate);
    }
}