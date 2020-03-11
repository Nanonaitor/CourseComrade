package com.ics45j.coursecomrade;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class UserCoursesActivity extends AppCompatActivity {
    private ArrayList<ExampleCourse> mCourseList;

    private RecyclerView mCourseRecyclerView;
    private RecyclerView.Adapter mCourseAdapter;
    private RecyclerView.LayoutManager mCourseLayout;

    private Button buttonSubmit, buttonRemove;
    private EditText submitCourse, removeCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_courses);

        getSupportActionBar().setTitle("My Courses");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        createCourseList();
        buildRecyclerView();

        //buttonSubmit = findViewById(R.id.buttonSubmit);
        buttonRemove = findViewById(R.id.buttonRemove);
        //submitCourse = findViewById(R.id.textCourseNumber);
        removeCourse = findViewById(R.id.editTextDelete);

        /*buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = Integer.parseInt(submitCourse.getText().toString());
                insertCourse(position);
            }
        });*/

        buttonRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    int position = Integer.parseInt(removeCourse.getText().toString());
                    removeCourse(position);
                }
                catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "You cannot remove an item that does not exist!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void insertCourse(int position) {//REMOVE THIS PROBABLY
        mCourseList.add(position, new ExampleCourse(R.drawable.ic_class, "New Course Added at " + position, "OPEN?"));
        mCourseAdapter.notifyDataSetChanged();
    }

    public void removeCourse(int position) {
        mCourseList.remove(position);
        mCourseAdapter.notifyDataSetChanged();
    }

    public void createCourseList() {
        mCourseList = new ArrayList<>();
        mCourseList.add(new ExampleCourse(R.drawable.ic_class, "Class 1", "OPEN"));//TESTING RIGHT NOW
        mCourseList.add(new ExampleCourse(R.drawable.ic_class, "Class 2", "FULL"));
        mCourseList.add(new ExampleCourse(R.drawable.ic_class, "Class 3", "FULL"));
    }

    public void buildRecyclerView() {
        mCourseRecyclerView = findViewById(R.id.recyclerView);
        mCourseRecyclerView.setHasFixedSize(true); //Increases performance
        mCourseLayout = new LinearLayoutManager(this);
        mCourseAdapter = new courseAdapter(mCourseList);

        mCourseRecyclerView.setLayoutManager(mCourseLayout);
        mCourseRecyclerView.setAdapter(mCourseAdapter);
    }
}
