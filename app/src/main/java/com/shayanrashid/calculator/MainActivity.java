package com.shayanrashid.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import org.mariuszgromada.math.mxparser.*;

public class MainActivity extends AppCompatActivity {

    EditText calculatorText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calculatorText = findViewById(R.id.CalculatorText);
        calculatorText.setShowSoftInputOnFocus(false);
    }

    public void equalsClick(View view) {
        String userExpression = calculatorText.getText().toString();

        userExpression = userExpression.replaceAll("×", "*");
        userExpression = userExpression.replaceAll("÷","/");

        Expression exp = new Expression(userExpression);

        String result = String.valueOf(exp.calculate());

        calculatorText.setText(result);
        calculatorText.setSelection(result.length());
    }

    public void onClearClick(View view) {
        calculatorText.setText("");
    }

    public void onPlusMinusClick(View view) {
        addToCalculator("-");
    }

    public void onBackspaceClick(View view) {
        int cursorPosition = calculatorText.getSelectionStart();
        int textLength = calculatorText.getText().length();

        if(cursorPosition != 0 && textLength != 0) {
            SpannableStringBuilder selection = (SpannableStringBuilder) calculatorText.getText();
            selection.replace(cursorPosition - 1, cursorPosition, "");
            calculatorText.setText(selection);
            calculatorText.setSelection(cursorPosition - 1);
        }
    }

    public void onDigitPress(View view) {
        Button pressedButton = (Button) view;
        addToCalculator(pressedButton.getText().toString());
    }

    private void addToCalculator(String stringToAdd) {
        int cursorPos = calculatorText.getSelectionStart();

        String leftEnd = calculatorText.getText().toString().substring(0, cursorPos);
        String rightEnd = calculatorText.getText().toString().substring(cursorPos);

        calculatorText.setText( String.format("%s%s%s", leftEnd, stringToAdd, rightEnd) );
        calculatorText.setSelection(cursorPos + 1);
    }
}