package com.example.surveyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "SurveyActivity";
    private static final String ANSWER_KEY = "answer-key";
    public static final String EXTRA_RESULTS = "com.example.surveyapp.results";

    private static final int RESULTS_REQUEST_CODE = 0;


    Button mYesButton;
    Button mNoButton;
    Button mResetButton;
    Button mResultsButton;

    TextView mYesCountTextView;
    TextView mNoCountTextView;

    StringBuilder mResultsConcat = new StringBuilder();
    String mResultsString;

    String[] mOptionNames = new String[2];
    int[] mAnswerCount = new int[2]; //this array holds two integers [0] representing the # of yes's
                                    // and [1] representing the number of no's
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");
        setContentView(R.layout.activity_main);

        mYesCountTextView = findViewById(R.id.yes_count);
        mNoCountTextView = findViewById(R.id.no_count);

        if(savedInstanceState != null){ //if the instancestate holds info, the key passes that info
                                        //back into the yes vs. no array
            mAnswerCount = savedInstanceState.getIntArray(ANSWER_KEY);
            Log.d(TAG, "savedInstanceState accessed."); //this is a good spot to log so I know
                                                            //when the app is loading up data
        }

        if(mAnswerCount == null){
            mAnswerCount = new int[]{0,0}; //this needs to be initialized to avoid a nullpointer error (I'm pretty sure)
        }

        updateAnswers(mAnswerCount); //the placement of this method makes sure that the yes/no count is always accurate


        mYesButton = findViewById(R.id.yes_button);
        mYesButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mAnswerCount[0] = mAnswerCount[0] + 1; //add one tally to the yes count on click
                updateAnswers(mAnswerCount);
            }
        });
        mNoButton = findViewById(R.id.no_button);
        mNoButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mAnswerCount[1] = mAnswerCount[1]+1; //add one tally to the no count on click
                updateAnswers(mAnswerCount);
            }
        });
        mResetButton = findViewById(R.id.reset_button);
        mResetButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                reset();
            }
        });
        mResultsButton = findViewById(R.id.results_button);
        mResultsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOptionNames = setOptionNames();
                mResultsString = concatText();

                Intent showResultsIntent = new Intent(MainActivity.this, ResultsActivity.class);
                showResultsIntent.putExtra(EXTRA_RESULTS, mResultsString);
                Log.d(TAG, "activity started");
                startActivityForResult(showResultsIntent, RESULTS_REQUEST_CODE);
            }
        });
    }

    private void reset() {
        mAnswerCount = new int[]{0,0}; //set yes/no back to zero
        updateAnswers(mAnswerCount);
    }

    private String concatText() {
        if(mOptionNames[0] != null){
            for(int i = 0; i < mOptionNames.length; i++){
                mResultsConcat.append(mOptionNames[i]);
                mResultsConcat.append(": ");
                mResultsConcat.append(mAnswerCount[i]);
                mResultsConcat.append("\n");
            }
            mResultsConcat.deleteCharAt(mResultsConcat.lastIndexOf("\n"));
        }
        return mResultsConcat.toString();
    }

    private String[] setOptionNames(){
        mOptionNames[0] = mYesButton.getText().toString();
        mOptionNames[1] = mNoButton.getText().toString();
        return mOptionNames;
    }

    private void updateAnswers(int[] mAnswerCount){
        if(mAnswerCount != null){ //this if statement also should prevent nullpointer errors
            mYesCountTextView.setText(String.valueOf(mAnswerCount[0])); //
            mNoCountTextView.setText(String.valueOf(mAnswerCount[1]));
        } else { //I can't see where this would come into play, but I added it just in case
            mYesCountTextView.setText("0");
            mYesCountTextView.setText("0");
        }

    }

    @Override
    public void onActivityResult (int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULTS_REQUEST_CODE && resultCode == RESULT_OK) {
            String nextMove = data.getStringExtra(ResultsActivity.EXTRA_NEXT_MOVE);
            if (nextMove.equals("reset")) {
                reset();
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outBundle) {
        super.onSaveInstanceState(outBundle); //take the super, save into the bundle
        outBundle.putIntArray(ANSWER_KEY, mAnswerCount); //add bundle into int array
    }
}

