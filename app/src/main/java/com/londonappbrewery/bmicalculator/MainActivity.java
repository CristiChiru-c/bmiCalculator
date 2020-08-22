package com.londonappbrewery.bmicalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // The following three TextView variable names should not be confused with
        // the static TextViews in the layout file (the one without "_variable")
        final TextView textWeight = findViewById(R.id.textWeight_variable);
        final TextView textHeight = findViewById(R.id.textHeight_variable);
        final TextView textBMI = findViewById(R.id.textBMI_variable);
        //linking the button
        Button buttonGenerate = findViewById(R.id.buttonGenerate);

        //creating the generator that will generate the data
        final Random randomNumberGenerator = new Random();

        //creating the decimal format for limiting generated data to 2 decimals
        final DecimalFormat numberFormat = new DecimalFormat("#.00");

        //creating the onclick listener
        buttonGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //creating the variables that hold the data
                //we will use the formula double random = min + r.nextDouble() * (max - min);
                //to generate our values

                //for weight min = 40, max = 150
                double weight = 40 + randomNumberGenerator.nextDouble() * (150 - 40);
                //for height min = 1.40, max = 2
                double height = 1.40 + randomNumberGenerator.nextDouble() * (2 - 1.40);
                //calculating the BMI Index
                double bmi = bmiIndex(weight, height);

                //setting the TextViews
                textWeight.setText(String.valueOf(numberFormat.format(weight)));
                textHeight.setText(String.valueOf(numberFormat.format(height)));
                textBMI.setText(String.valueOf(numberFormat.format(bmi)));
            }
        });
    }

    public static double bmiIndex (double weight, double height) {

        return weight / (height * height);
    }
}