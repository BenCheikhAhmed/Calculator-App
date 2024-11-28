package com.example.calculatorapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etNumber1, etNumber2;
    private TextView tvResult;
    private double memory = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNumber1 = findViewById(R.id.etNumber1);
        etNumber2 = findViewById(R.id.etNumber2);
        tvResult = findViewById(R.id.tvResult);

        Button btnAdd = findViewById(R.id.btnAdd);
        Button btnSubtract = findViewById(R.id.btnSubtract);
        Button btnMultiply = findViewById(R.id.btnMultiply);
        Button btnDivide = findViewById(R.id.btnDivide);
        Button btnMemorize = findViewById(R.id.btnMemorize);
        Button btnUseMemory = findViewById(R.id.btnUseMemory);

        btnAdd.setOnClickListener(v -> performOperation("+"));
        btnSubtract.setOnClickListener(v -> performOperation("-"));
        btnMultiply.setOnClickListener(v -> performOperation("*"));
        btnDivide.setOnClickListener(v -> performOperation("/"));

        btnMemorize.setOnClickListener(v -> {
            String resultText = tvResult.getText().toString();
            if (!resultText.isEmpty()) {
                memory = Double.parseDouble(resultText);
                Toast.makeText(this, "Result memorized!", Toast.LENGTH_SHORT).show();
            }
        });

        btnUseMemory.setOnClickListener(v -> etNumber1.setText(String.valueOf(memory)));
    }

    private void performOperation(String operation) {
        String num1Text = etNumber1.getText().toString();
        String num2Text = etNumber2.getText().toString();

        if (num1Text.isEmpty() || num2Text.isEmpty()) {
            Toast.makeText(this, "Please enter both numbers", Toast.LENGTH_SHORT).show();
            return;
        }

        double num1 = Double.parseDouble(num1Text);
        double num2 = Double.parseDouble(num2Text);
        double result = 0;

        switch (operation) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                if (num2 == 0) {
                    Toast.makeText(this, "Cannot divide by zero", Toast.LENGTH_SHORT).show();
                    return;
                }
                result = num1 / num2;
                break;
        }

        tvResult.setText(String.valueOf("Result: " + result));

        // Pass result to ResultActivity
        Intent intent = new Intent(MainActivity.this, ResultActivity.class);
        intent.putExtra("num1", num1);
        intent.putExtra("num2", num2);
        intent.putExtra("operation", operation);
        intent.putExtra("result", result);
        startActivity(intent);
    }
}
