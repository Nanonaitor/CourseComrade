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

    private CourseManager cm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_courses);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String username = bundle.getString("username");

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
        //mCourseList.remove(position);
        System.out.println(cm.getUserId());
        System.out.println(cm.getUserCourses());
        ArrayList<Integer> courseList = new ArrayList<Integer>(cm.getUserCourses());
        System.out.println("yeet");
        System.out.println(courseList);
        cm.getUserCoursesMap();
        System.out.println("reet");

        courseList.toArray();

        for(int i = 0; i < courseList.size(); ++i){
            System.out.println("skeet");
            System.out.println(courseList.get(i));
            //code = (Integer)courseList.get(i);
            //Integer code = new Integer(courseList.get(i));
            System.out.println("code");
            Map<String, String> course = cm.getCourse(27602);

            mCourseList.add(new ExampleCourse(R.drawable.ic_class, course.get("code"), course.get("status")));
        }
        mCourseAdapter.notifyDataSetChanged();
    }

    public void createCourseList() {


        System.out.println(cm.getUserId());
        System.out.println(cm.getUserCourses());
        for(Integer code: cm.getUserCourses()){
            System.out.println(code);
            //Map<String, String> course = cm.getCourse(code);


            //mCourseList.add(new ExampleCourse(R.drawable.ic_class, course.get("code"), course.get("status")));
        }

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
