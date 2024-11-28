package com.example.calculatorapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView tvDisplayResult = findViewById(R.id.tvDisplayResult);
        Button btnBack = findViewById(R.id.btnBack);

        Intent intent = getIntent();
        double num1 = intent.getDoubleExtra("num1", 0);
        double num2 = intent.getDoubleExtra("num2", 0);
        String operation = intent.getStringExtra("operation");
        double result = intent.getDoubleExtra("result", 0);
        String message = "First Value: " + num1 + "\n"
                + "Second Value: " + num2 + "\n"
                + "Operation: " + operation + "\n"
                + "Result: " + result;
        tvDisplayResult.setText(message);

        btnBack.setOnClickListener(v -> finish());
    }
}

