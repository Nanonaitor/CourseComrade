package com.ics45j.coursecomrade;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SearchActivity extends AppCompatActivity {
    //Variables
    EditText textCourseNumberInput;
    Button buttonReset, buttonHelp, buttonSubmit;
    int submittedCourse;
    CourseManager cm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        //Action Bar Declarations
        getSupportActionBar().setTitle("Search");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Variables set
        textCourseNumberInput = (EditText)findViewById(R.id.textCourseNumber);
        buttonReset = findViewById(R.id.buttonReset);
        buttonHelp = findViewById(R.id.buttonHelp);
        buttonSubmit = findViewById(R.id.buttonSubmit);
        submittedCourse = 0;
        cm = new CourseManager();

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textCourseNumberInput.setText("");
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
                try {
                    submittedCourse = Integer.valueOf(textCourseNumberInput.getText().toString());
                    if(submittedCourse < 10000)
                        Toast.makeText(getApplicationContext(), "Please input a 5 digit number", Toast.LENGTH_SHORT).show();
                    else {
                        //TODO: ADD THE COURSE TO FIREBASE
                        Toast.makeText(getApplicationContext(), "Added course #" + submittedCourse + " to your course list", Toast.LENGTH_SHORT).show();
                    }
                }
                catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Please input a course number.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void openDialog() {//Gets stuff from Warning Message
        WarningDialog warningDialog = new WarningDialog();
        warningDialog.show(getSupportFragmentManager(), "example dialog");
    }
}
