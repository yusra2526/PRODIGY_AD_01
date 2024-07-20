package com.yusra.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView textViewDisplay;
    private StringBuilder input = new StringBuilder();
    private String operator = "";
    private double operand1 = 0;
    private double operand2 = 0;
    private boolean isOperatorPressed = false;
    private boolean isResultDisplayed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewDisplay = findViewById(R.id.textViewDisplay);

        View.OnClickListener numberListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isResultDisplayed) {
                    input.setLength(0);
                    isResultDisplayed = false;
                }
                Button button = (Button) v;
                input.append(button.getText().toString());
                textViewDisplay.setText(input.toString());
            }
        };

        View.OnClickListener operatorListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (input.length() > 0) {
                    operand1 = Double.parseDouble(input.toString());
                    Button button = (Button) v;
                    operator = button.getText().toString();
                    input.setLength(0);
                    isOperatorPressed = true;
                }
            }
        };

        findViewById(R.id.button0).setOnClickListener(numberListener);
        findViewById(R.id.button1).setOnClickListener(numberListener);
        findViewById(R.id.button2).setOnClickListener(numberListener);
        findViewById(R.id.button3).setOnClickListener(numberListener);
        findViewById(R.id.button4).setOnClickListener(numberListener);
        findViewById(R.id.button5).setOnClickListener(numberListener);
        findViewById(R.id.button6).setOnClickListener(numberListener);
        findViewById(R.id.button7).setOnClickListener(numberListener);
        findViewById(R.id.button8).setOnClickListener(numberListener);
        findViewById(R.id.button9).setOnClickListener(numberListener);
        findViewById(R.id.button_point).setOnClickListener(numberListener);

        findViewById(R.id.button_add).setOnClickListener(operatorListener);
        findViewById(R.id.button_sub).setOnClickListener(operatorListener);
        findViewById(R.id.button_mul).setOnClickListener(operatorListener);
        findViewById(R.id.button_div).setOnClickListener(operatorListener);

        findViewById(R.id.button_equal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOperatorPressed && input.length() > 0) {
                    operand2 = Double.parseDouble(input.toString());
                    double result = 0;

                    switch (operator) {
                        case "+":
                            result = operand1 + operand2;
                            break;
                        case "-":
                            result = operand1 - operand2;
                            break;
                        case "*":
                            result = operand1 * operand2;
                            break;
                        case "/":
                            if (operand2 != 0) {
                                result = operand1 / operand2;
                            } else {
                                textViewDisplay.setText("Error");
                                return;
                            }
                            break;
                    }
                    textViewDisplay.setText(String.valueOf(result));
                    input.setLength(0);
                    input.append(result);
                    isOperatorPressed = false;
                    isResultDisplayed = true;
                }
            }
        });
    }
}
