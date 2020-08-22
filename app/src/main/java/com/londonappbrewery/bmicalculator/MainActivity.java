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
        //linking the textMessage
        final TextView textMessage = findViewById(R.id.textMessage);
        //linking the button
        Button buttonGenerate = findViewById(R.id.buttonGenerate);

        //creating the generator that will generate the data
        final Random randomNumberGenerator = new Random();

        //creating the decimal format for limiting generated data to 2 decimals
        final DecimalFormat numberFormat = new DecimalFormat("#.00");

        //calling the generateValues method on startup
        //so that we'll have a set of data already printed on the screen
        generateValues(textWeight, textHeight, textBMI, textMessage, randomNumberGenerator, numberFormat);

        //creating the onclick listener
        buttonGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //we call the generateValues method each time the button is pressed
                generateValues(textWeight, textHeight, textBMI, textMessage, randomNumberGenerator, numberFormat);
            }
        });
    }

    public static double bmiIndex(double weight, double height) {

        return weight / (height * height);
    }

    public static void generateValues(TextView textWeight, TextView textHeight, TextView textBMI, TextView textMessage, Random randomNumberGenerator, DecimalFormat numberFormat) {
        //this method will be used to generate and fill the data

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

        //setting the Message
        if(bmi > 25) {
            textMessage.setText("You're pretty hefty. You should consider losing some weight");
        }
        else if(bmi > 18.5) {
            textMessage.setText("You're normal weight. Good job!");
        }
        else {
            textMessage.setText("You should consider putting on some more weight");
        }

    }

}