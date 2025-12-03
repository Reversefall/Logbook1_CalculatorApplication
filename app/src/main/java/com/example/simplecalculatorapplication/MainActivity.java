package com.example.simplecalculatorapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.google.android.material.button.MaterialButton;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final char ADDITION = '+';
    private static final char SUBTRACTION = '-';
    private static final char MULTIPLICATION = '*';
    private static final char DIVISION = '/';

    private char currentSymbol;

    private double firstValue = Double.NaN;
    private double secondValue;

    private TextView inputDisplay, outputDisplay;
    private DecimalFormat decimalFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        decimalFormat = new DecimalFormat("#.##########");
        inputDisplay = findViewById(R.id.Solution_textview);
        outputDisplay = findViewById(R.id.result_textview);

        findViewById(R.id.Button0).setOnClickListener(this);
        findViewById(R.id.Button1).setOnClickListener(this);
        findViewById(R.id.Button2).setOnClickListener(this);
        findViewById(R.id.Button3).setOnClickListener(this);
        findViewById(R.id.Button4).setOnClickListener(this);
        findViewById(R.id.Button5).setOnClickListener(this);
        findViewById(R.id.Button6).setOnClickListener(this);
        findViewById(R.id.Button7).setOnClickListener(this);
        findViewById(R.id.Button8).setOnClickListener(this);
        findViewById(R.id.Button9).setOnClickListener(this);

        findViewById(R.id.ButtonPlus).setOnClickListener(this);
        findViewById(R.id.ButtonMinus).setOnClickListener(this);
        findViewById(R.id.ButtonDivide).setOnClickListener(this);
        findViewById(R.id.ButtonMutiply).setOnClickListener(this);
        findViewById(R.id.ButtonDelete).setOnClickListener(this);
        findViewById(R.id.ButtonEqual).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == R.id.Button0) inputDisplay.setText(inputDisplay.getText() + "0");
        else if (id == R.id.Button1) inputDisplay.setText(inputDisplay.getText() + "1");
        else if (id == R.id.Button2) inputDisplay.setText(inputDisplay.getText() + "2");
        else if (id == R.id.Button3) inputDisplay.setText(inputDisplay.getText() + "3");
        else if (id == R.id.Button4) inputDisplay.setText(inputDisplay.getText() + "4");
        else if (id == R.id.Button5) inputDisplay.setText(inputDisplay.getText() + "5");
        else if (id == R.id.Button6) inputDisplay.setText(inputDisplay.getText() + "6");
        else if (id == R.id.Button7) inputDisplay.setText(inputDisplay.getText() + "7");
        else if (id == R.id.Button8) inputDisplay.setText(inputDisplay.getText() + "8");
        else if (id == R.id.Button9) inputDisplay.setText(inputDisplay.getText() + "9");
        else if (id == R.id.ButtonPlus) handleOperator(ADDITION);
        else if (id == R.id.ButtonMinus) handleOperator(SUBTRACTION);
        else if (id == R.id.ButtonMutiply) handleOperator(MULTIPLICATION);
        else if (id == R.id.ButtonDivide) handleOperator(DIVISION);
        else if (id == R.id.ButtonEqual) handleEquals();
        else if (id == R.id.ButtonDelete) handleClear();
    }

    private void handleOperator(char operator) {
        if (!inputDisplay.getText().toString().isEmpty()) {
            calculate();
            currentSymbol = operator;
            outputDisplay.setText(decimalFormat.format(firstValue) + " " + operator);
            inputDisplay.setText(null);
        }
    }

    private void handleEquals() {
        calculate();
        outputDisplay.setText(decimalFormat.format(firstValue));
        inputDisplay.setText(decimalFormat.format(firstValue));
        firstValue = Double.NaN;
        currentSymbol = '0';
    }

    private void handleClear() {
        String input = inputDisplay.getText().toString();

        if (input.isEmpty()) {
            firstValue = Double.NaN;
            secondValue = Double.NaN;
            outputDisplay.setText("");
        } else {
            String trimmed = input.length() > 1 ? input.substring(0, input.length() - 1) : "";
            if ("-".equals(trimmed)) trimmed = "";
            inputDisplay.setText(trimmed);
        }
    }


    private void calculate() {
        if (inputDisplay.getText().toString().isEmpty()) {
            return;
        }

        if (Double.isNaN(firstValue)) {
            try {
                firstValue = Double.parseDouble(inputDisplay.getText().toString());
            } catch (NumberFormatException e) {
                outputDisplay.setText("Error");
                firstValue = Double.NaN;
            }
        }
        else {
            try {
                secondValue = Double.parseDouble(inputDisplay.getText().toString());
                inputDisplay.setText(null);

                switch (currentSymbol) {
                    case ADDITION:
                        firstValue = this.firstValue + secondValue;
                        break;
                    case SUBTRACTION:
                        firstValue = this.firstValue - secondValue;
                        break;
                    case MULTIPLICATION:
                        firstValue = this.firstValue * secondValue;
                        break;
                    case DIVISION:
                        if (secondValue == 0) {
                            outputDisplay.setText("Cannot divide by zero");
                            firstValue = Double.NaN;
                        } else {
                            firstValue = this.firstValue / secondValue;
                        }
                        break;
                }
            } catch (NumberFormatException e) {
                outputDisplay.setText("Error");
                firstValue = Double.NaN;
            }
        }
    }
}
