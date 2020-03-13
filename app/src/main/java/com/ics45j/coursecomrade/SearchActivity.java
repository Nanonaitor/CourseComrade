package com.ics45j.coursecomrade;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Map;

public class SearchActivity extends AppCompatActivity {
    //Variables
    EditText textCourseNumberInput;
    Button buttonReset, buttonHelp, buttonSubmit, buttonSaveChanges, buttonBack;
    int submittedCourse;
    CourseManager cm;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        //Action Bar Declarations
        getSupportActionBar().setTitle("Search");
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Declarations
        textCourseNumberInput = (EditText)findViewById(R.id.textCourseNumber);
        buttonReset = findViewById(R.id.buttonReset);
        buttonHelp = findViewById(R.id.buttonHelp);
        buttonSubmit = findViewById(R.id.buttonSubmit);
        buttonBack = findViewById(R.id.buttonBackOutSearch);
        submittedCourse = 0;

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        username = bundle.getString("username");
        cm = new CourseManager(username);

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
                        // check if course exists
                        if(cm.getCourse(submittedCourse) != null){
                            cm.addCourse(submittedCourse);
                            Toast.makeText(getApplicationContext(), "Added course #" + submittedCourse + " to your course list", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Please input a course number.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity();
            }
        });
    }

    public void openDialog() {//Gets stuff from Warning Message
        WarningDialog warningDialog = new WarningDialog();
        warningDialog.show(getSupportFragmentManager(), "example dialog");
    }

    public void openMainActivity() {
        Bundle bundle = new Bundle();
        bundle.putString("username", username);

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
