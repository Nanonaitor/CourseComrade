package com.ics45j.coursecomrade;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button buttonSearch, buttonUserCourses;

    // everything that needs Firebase goes through courseManager
    private CourseManager courseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize courseManager
        // This will grab all information needed from Firebase
        courseManager = new CourseManager();

        getSupportActionBar().setTitle("Home");
        buttonSearch = (Button) findViewById(R.id.buttonSearch);
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openSearchActivity();
            }
        });

        getSupportActionBar().setTitle("Home");
        buttonUserCourses = (Button) findViewById(R.id.buttonUserCourses);
        buttonUserCourses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUserCoursesActivity();
            }
        });
    }

    public void openSearchActivity() {
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }

    public void openUserCoursesActivity() {
        Intent intent = new Intent(this, UserCoursesActivity.class);
        startActivity(intent);
    }
}
