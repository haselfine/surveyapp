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

    String mOptionNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        final Intent resultIntent = getIntent();
        mOptionNames = resultIntent.getStringExtra(MainActivity.EXTRA_RESULTS);


        mResultsText = findViewById(R.id.results_text);
        mResultsText.setText("");
        mResultsText.setText(mOptionNames);

        mResetButton = findViewById(R.id.reset_button);
        mResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nextMove = "reset";
                Intent returnIntent = new Intent();
                returnIntent.putExtra(EXTRA_NEXT_MOVE, nextMove);
                setResult(RESULT_OK, returnIntent);
                finish();
            }
        });


        mContinueButton = findViewById(R.id.continue_button);
        mContinueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra(EXTRA_NEXT_MOVE, "continue");
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });

    }
}
