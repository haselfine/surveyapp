package com.example.surveyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class NameActivity extends AppCompatActivity {

    public static final String EXTRA_USER_INPUT = "com.example.surveyapp.user_input";

    EditText mUserQuestion;
    EditText mUserAnswerOne;
    EditText mUserAnswerTwo;

    Button mReadyButton;

    String[] mUserArray = new String[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);

        mUserQuestion = findViewById(R.id.user_question);

        mUserAnswerOne = findViewById(R.id.user_answer_one);

        mUserAnswerTwo = findViewById(R.id.user_answer_two);

        mReadyButton = findViewById(R.id.ready_button);
        mReadyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mUserArray[0] = mUserQuestion.getText().toString();
                mUserArray[1] = mUserAnswerOne.getText().toString();
                mUserArray[2] = mUserAnswerTwo.getText().toString();

                Intent userIntent = new Intent();
                userIntent.putExtra(EXTRA_USER_INPUT, mUserArray);
                setResult(RESULT_OK, userIntent);
                finish();
            }
        });

    }


}
