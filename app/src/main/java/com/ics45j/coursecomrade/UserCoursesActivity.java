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
    private ArrayList<ExampleCourse> mCourseList;

    private RecyclerView mCourseRecyclerView;
    private RecyclerView.Adapter mCourseAdapter;
    private RecyclerView.LayoutManager mCourseLayout;

    private Button buttonSubmit, buttonRemove;
    private EditText submitCourse, removeCourse;
    ArrayList<String> savedUserCourses;

    private CourseManager cm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_courses);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String username = bundle.getString("username");
        savedUserCourses = new ArrayList<String>(bundle.getStringArrayList("userCourses"));

        cm = new CourseManager(username);
        mCourseList = new ArrayList<>();


        getSupportActionBar().setTitle("My Courses");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // CourseManager must be initialized before this!
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
        ArrayList<String> courseList = new ArrayList<String>(cm.getUserCourses());
        // i was using this for testing... can u implement this correctly?

        for(int i = 0; i < courseList.size(); ++i){
            System.out.println("skeet");
        }
        mCourseAdapter.notifyDataSetChanged();
    }

    public void createCourseList() {

        System.out.println("creating courses");
        System.out.println(savedUserCourses);
        for(int i = 0; i < savedUserCourses.size(); ++i){
            System.out.println("skeet");
            System.out.println(cm.getCourse(savedUserCourses.get(i)));
            System.out.println("code");
            Map<String, String> course = cm.getCourse(savedUserCourses.get(i));

            mCourseList.add(new ExampleCourse(R.drawable.ic_class, course.get("code"), course.get("status")));
        }
        mCourseList.add(new ExampleCourse(R.drawable.ic_class, "class", "status"));

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
