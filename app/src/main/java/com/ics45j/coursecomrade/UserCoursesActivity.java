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
    private ArrayList<ExampleCourse> mCourseList;
    private RecyclerView mCourseRecyclerView;
    private RecyclerView.Adapter mCourseAdapter;
    private RecyclerView.LayoutManager mCourseLayout;
    private Button buttonRemove;
    private EditText removeCourse;
    ArrayList<String> savedUserCourses;
    private CourseManager cm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_courses);

        //Action Bar Declarations
        getSupportActionBar().setTitle("My Courses");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Declarations
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String username = bundle.getString("username");
        savedUserCourses = new ArrayList<String>(bundle.getStringArrayList("userCourses"));
        cm = new CourseManager(username);
        mCourseList = new ArrayList<>();
        buttonRemove = findViewById(R.id.buttonRemove);
        removeCourse = findViewById(R.id.editTextDelete);

        //Execute Methods (before course manager!)
        createCourseList();
        buildRecyclerView();

        //Button methods
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

    public void removeCourse(int position) {
        ArrayList<String> courseList = new ArrayList<String>(cm.getUserCourses());
        for(int i = 0; i < courseList.size(); ++i){
            System.out.println("Deleted a course.");
        }
        mCourseAdapter.notifyDataSetChanged();
    }

    public void createCourseList() {
        System.out.println("Creating courses!");
        System.out.println(savedUserCourses);
        for (String element: savedUserCourses){
            System.out.println("Course Displayed!");
            System.out.println(cm.getCourse(element));//TODO: fix the getCourse method in CourseManager.
            System.out.println("code");
            Map<String, String> course = cm.getCourse(element);

            mCourseList.add(new ExampleCourse(R.drawable.ic_class, course.get("code"), course.get("status")));//Crashes here???
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
