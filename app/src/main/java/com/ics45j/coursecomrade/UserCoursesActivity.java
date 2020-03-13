package com.ics45j.coursecomrade;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;

public class UserCoursesActivity extends AppCompatActivity {
    //Variables

    private RecyclerView mCourseRecyclerView;
    private RecyclerView.Adapter mCourseAdapter;
    private RecyclerView.LayoutManager mCourseLayout;
    private Button buttonRemove, buttonBack;
    private EditText removeCourse;

    private CourseManager cm;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_courses);

        //Action Bar Declarations
        getSupportActionBar().setTitle("My Courses");
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Declarations
        buttonRemove = findViewById(R.id.buttonRemove);
        buttonBack = findViewById(R.id.buttonBackOutUserCourses);
        removeCourse = findViewById(R.id.editTextDelete);
        Intent intent = getIntent();

        Bundle bundle = intent.getExtras();
        username = bundle.getString("username");


        System.out.println("init cm in usercoursesactivity");
        cm = new CourseManager(username);

        //Execute Methods (before course manager!)

        buildRecyclerView();

        //Button methods
        buttonRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    int code = Integer.parseInt(removeCourse.getText().toString());
                    removeCourse(code);
                }
                catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "You cannot remove an item that does not exist!", Toast.LENGTH_SHORT).show();
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

    public void removeCourse(int code) {
        // removes course by code
        cm.removeCourse(code);
        mCourseAdapter.notifyDataSetChanged();
    }

    public void openMainActivity() {
        Bundle bundle = new Bundle();
        bundle.putString("username", username);
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }



    public void buildRecyclerView() {
        mCourseRecyclerView = findViewById(R.id.recyclerView);
        mCourseRecyclerView.setHasFixedSize(true); //Increases performance
        mCourseLayout = new LinearLayoutManager(this);
        mCourseAdapter = new courseAdapter(cm);

        // set adapter in cm so that it updates when Firebase changes
        cm.setAdapter(mCourseAdapter);

        mCourseRecyclerView.setLayoutManager(mCourseLayout);
        mCourseRecyclerView.setAdapter(mCourseAdapter);
    }
}
