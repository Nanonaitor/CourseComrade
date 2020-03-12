package com.ics45j.coursecomrade;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class CourseManager {
    private final FirebaseDatabase database = FirebaseDatabase.getInstance();

    private Map<String, Map<String, String>> courses;
    private ArrayList<String> depts;
    private ArrayList<Integer> userCourses;

    private final String userId;
    private DatabaseReference userRef;


    public CourseManager(String userId) {
        depts = new ArrayList<String>();
        courses = new HashMap<String, Map<String, String>>();
        userCourses = new ArrayList<Integer>();

        this.userId = userId;




        DatabaseReference userCoursesRef = database.getReference("userCourses");
        userRef = userCoursesRef.child(this.userId);
        userListener(userRef);
        //userCoursesListeners(userCoursesRef, this.userId);

        DatabaseReference deptsRef= database.getReference("depts");
        deptListeners(deptsRef);

        DatabaseReference coursesRef= database.getReference("courses");
        coursesListeners(coursesRef);



    }


    public Map<String, String> getCourse(String code){
        return courses.get(code);
    }

    public Map<String, String> getCourse(int code){
        // to take care of integer input
        String codeStr = Integer.toString(code);
        return courses.get(codeStr);
    }


    public void addCourse(int code){
        // check for duplicates
        if(!userCourses.contains(code)){
            userCourses.add(code);
            userRef.setValue(userCourses);
        }
    }



    public void addCourse(String code){
        // convert code to int
        Integer codeInt = Integer.parseInt(code);

        if(!userCourses.contains(codeInt)){
            userCourses.add(codeInt);
            userRef.setValue(userCourses);
        }
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


    private void userListener(DatabaseReference ref){
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot != null){
                    userCourses.clear();
                    userCourses = (ArrayList<Integer>)dataSnapshot.getValue();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }


    private void userCoursesListeners(DatabaseReference ref, final String userId){
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                if (dataSnapshot.child(userId).exists()){
                    DataSnapshot userData = dataSnapshot.child(userId);
                    userCourses.clear();
                    userCourses = (ArrayList<Integer>)userData.getValue();


                }
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

    public String getUserId() {
        return userId;
    }



    public ArrayList<Integer> getUserCourses() {

        return (ArrayList<Integer>)userCourses;
    }

    public ArrayList<Map<String, String>> getUserCoursesMap(){

        ArrayList<Map<String,String>> result = new ArrayList<Map<String, String>>();
        System.out.println("in couses mao");
        System.out.println(userCourses);
        for(Integer code: userCourses){
            System.out.println(code);
            result.add(courses.get(code));
        }
        return result;
    }
}
