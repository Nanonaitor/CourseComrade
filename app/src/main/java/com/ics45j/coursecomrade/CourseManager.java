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
    private ArrayList<String> userCourses;

    private String userId;
    private DatabaseReference userRef;


    public CourseManager(String userId) {
        depts = new ArrayList<String>();
        courses = new HashMap<String, Map<String, String>>();
        userCourses = new ArrayList<String>();

        this.userId = userId;

        DatabaseReference AllUserCoursesRef = database.getReference("userCourses");
        userRef = AllUserCoursesRef.child(this.userId);
        userCoursesListener(userRef);

        DatabaseReference coursesRef= database.getReference("courses");
        coursesRef.keepSynced(true);
        coursesListeners(coursesRef);

        DatabaseReference deptsRef= database.getReference("depts");
        deptListeners(deptsRef);

        //Remove these if u need to Nathan, just testing stuff...
        System.out.println();
    }

    //Constructor to make a new user.
    public CourseManager(String userId, boolean newUser){
        this.userId = userId;
        DatabaseReference AllUserCoursesRef = database.getReference("userCourses");
        userRef = FirebaseDatabase.getInstance().getReference().child("userCourses");
    }


    public Map<String, String> getCourse(String code){//TODO: Fix this because it returns null for some reason.
        return courses.get(code);
    }

    public Map<String, String> getCourse(int code){
        // to take care of integer input
        String codeStr = Integer.toString(code);
        return courses.get(codeStr);
    }


    public void addCourse(int code){
        // check for duplicates
        String codeStr = Integer.toString(code);
        if(!userCourses.contains(codeStr)){
            userCourses.add(codeStr);
            userRef.setValue(userCourses);
        }
    }


    public void addCourse(String code){

        if(!userCourses.contains(code)){
            userCourses.add(code);
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


    private void userCoursesListener(DatabaseReference ref){
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                System.out.println("in userCoursesListener");
                if (dataSnapshot.getValue() != null){

                    System.out.println(dataSnapshot.getValue());
                    userCourses.clear();

                    for(DataSnapshot data: dataSnapshot.getChildren()){
                        System.out.println("adding code");
                        System.out.println(data.getValue());
                        String code = (String)data.getValue();
                        userCourses.add(code);
                        System.out.println(code);
                    }

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
                System.out.println("in courses listener");
                System.out.println(dataSnapshot.getValue());
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



    public ArrayList<String> getUserCourses() {

        return (ArrayList<String>)userCourses;
    }

    public ArrayList<Map<String, String>> getUserCoursesMap(){

        ArrayList<Map<String,String>> result = new ArrayList<Map<String, String>>();
        System.out.println("in couses mao");
        System.out.println(userCourses);
        for(String code: userCourses){
            System.out.println(code);
            result.add(courses.get(code));
        }
        return result;
    }

    public boolean makeNewUser(String checkID) { //ONLY USE THIS METHOD FOR SIGN-UP ACTIVITY!
        if (userRef.child(userId).getRoot() != null){
            userRef.child(userId).setValue("");
            return true;
        }
        return false;
    }

   
}
