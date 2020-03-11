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

    Map<String, Map<String, String>> courses;
    ArrayList<String> depts;

    public CourseManager() {
        depts = new ArrayList<String>();
        courses = new HashMap<String, Map<String, String>>();

        DatabaseReference deptsRef= database.getReference("depts");
        DatabaseReference coursesRef= database.getReference("courses");

        deptListeners(deptsRef);
        coursesListeners(coursesRef);
    }

    private void deptListeners(DatabaseReference ref){
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                depts.clear();
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
                courses.clear();
                courses = (Map<String, Map<String, String>>)dataSnapshot.getValue();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    public void printDepts(){
        for(String dept: depts){
            System.out.println(dept);
        }
    }
}
