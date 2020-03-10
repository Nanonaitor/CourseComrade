package com.ics45j.coursecomrade;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CourseManager {
    private final FirebaseDatabase database = FirebaseDatabase.getInstance();

    /*
    Map contains all course information, in strings (including codes)
    Example:
        12345:
            code: 12345
            days: MWF
            instructor: Obama, B.
            labels: WorldDomination 200 RESEARCH COURSE
            location: WH 100
            status: OPEN
            time: 2:00-4:50p
            type: RESEARCH
     */
    Map<String, Map<String, String>> courses;

    // array list of deptartments. Used for testing, not really needed
    ArrayList<String> depts;



    public CourseManager() {
        // initialize data structures
        depts = new ArrayList<String>();
        courses = new HashMap<String, Map<String, String>>();

        // get references
        DatabaseReference deptsRef= database.getReference("depts");
        DatabaseReference coursesRef= database.getReference("courses");

        // add listeners to references to insert data into depts and courses
        deptListeners(deptsRef);
        coursesListeners(coursesRef);
    }

    public Map<String, String> getCourse(String courseCode){
        /* Course code is technically a 6 digit number
        But since it's stored as a string we pass it as
        a String.
         */
        return courses.get(courseCode);
    }



    private void deptListeners(DatabaseReference ref){
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // clear existing values
                depts.clear();

                // cast into appropriate data structure
                depts = (ArrayList<String>)dataSnapshot.getValue();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    private void coursesListeners(DatabaseReference ref){
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // clear existing values
                courses.clear();

                // cast into appropriate data strucutre
                courses = (Map<String, Map<String, String>>)dataSnapshot.getValue();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }



    public void printDepts(){
        // used for debugging
        for(String dept: depts){
            System.out.println(dept);
        }
    }




}
