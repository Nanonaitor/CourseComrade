package com.ics45j.coursecomrade;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button buttonSearch, buttonUserCourses;
    private String username = "dathan";

    // everything that needs Firebase goes through courseManager
    // actually it's not needed here
    private CourseManager courseManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize courseManager
        // This will grab all information needed from Firebase
        courseManager = new CourseManager(username);

        //Action Bar Declarations
        getSupportActionBar().setTitle("Home");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        buttonSearch = (Button) findViewById(R.id.buttonSearch);
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openSearchActivity();
            }
        });

        buttonUserCourses = (Button) findViewById(R.id.buttonUserCourses);
        buttonUserCourses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUserCoursesActivity();
            }
        });
    }

    public void openSearchActivity() {
        Bundle bundle = new Bundle();
        bundle.putString("username", username);

        Intent intent = new Intent(this, SearchActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void openUserCoursesActivity() {
        Bundle bundle = new Bundle();
        bundle.putString("username", username);
        bundle.putStringArrayList("userCourses", courseManager.getUserCourses());

        Intent intent = new Intent(this, UserCoursesActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
