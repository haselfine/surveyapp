package com.example.surveyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity {

    public static final String EXTRA_NEXT_MOVE = "com.example.surveyapp.results";

    TextView mResultsText;

    Button mResetButton;
    Button mContinueButton;

    String mResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        final Intent resultIntent = getIntent();
        mResults = resultIntent.getStringExtra(MainActivity.EXTRA_RESULTS); //takes concatenated results string


        mResultsText = findViewById(R.id.results_text);
        mResultsText.setText(""); //clear previous text (not sure this is necessary)
        mResultsText.setText(mResults); //sets the textview text to concatenated results string

        mResetButton = findViewById(R.id.reset_button);
        mResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nextMove = "reset"; //set string variable to reset
                Intent returnIntent = new Intent();
                returnIntent.putExtra(EXTRA_NEXT_MOVE, nextMove); //send string variable back to main
                setResult(RESULT_OK, returnIntent); //ok result, send intent
                finish();
            }
        });


        mContinueButton = findViewById(R.id.continue_button);
        mContinueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra(EXTRA_NEXT_MOVE, "continue"); //this honestly isn't necessary at the moment
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });

    }
}
