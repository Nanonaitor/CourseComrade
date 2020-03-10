package com.ics45j.coursecomrade;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SearchActivity extends AppCompatActivity {
    EditText textCourseNumber;
    Button buttonReset, buttonHelp, buttonSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        getSupportActionBar().setTitle("Search");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        textCourseNumber = findViewById(R.id.textCourseNumber);
        buttonReset = findViewById(R.id.buttonReset);
        buttonHelp = findViewById(R.id.buttonHelp);
        buttonSubmit = findViewById(R.id.buttonSubmit);

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textCourseNumber.setText("");
            }
        });
        buttonHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Implement this...
                Toast.makeText(getApplicationContext(),"Hi Nathan, Fix Me! I don't work lol",Toast.LENGTH_SHORT).show();
            }
        });
    }
        public void openDialog() {//Gets stuff from Warning Message
            WarningDialog warningDialog = new WarningDialog();
            warningDialog.show(getSupportFragmentManager(), "example dialog");
        }

        //Constraints on Android Studio Input: https://www.youtube.com/watch?v=5maLtpNsOkc
    }
