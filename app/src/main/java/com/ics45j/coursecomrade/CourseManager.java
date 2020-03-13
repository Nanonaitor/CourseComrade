package com.ics45j.coursecomrade;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

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

    private RecyclerView.Adapter adapter;



    public CourseManager(String userId) {
        depts = new ArrayList<String>();
        courses = new HashMap<String, Map<String, String>>();
        userCourses = new ArrayList<String>();

        adapter = null;

        this.userId = userId;

        // Get course reference and grab all course data from firebase
        DatabaseReference coursesRef= database.getReference("courses");
        coursesRef.keepSynced(true); // helped fix a bug
        coursesListeners(coursesRef);

        // Get reference to all users
        DatabaseReference AllUserCoursesRef = database.getReference("userCourses");

        // Get reference to this specific user
        userRef = AllUserCoursesRef.child(this.userId);

        // Grab data for this specific user from firebase
        userCoursesListener(userRef);



        // Used for testing purposes
        DatabaseReference deptsRef= database.getReference("depts");
        deptListeners(deptsRef);

        //Remove these if u need to Nathan, just testing stuff...
        System.out.println();
    }





    // Constructor to make a new user. Not really needed
    public CourseManager(String userId, boolean newUser){
        this.userId = userId;
        DatabaseReference AllUserCoursesRef = database.getReference("userCourses");
        userRef = FirebaseDatabase.getInstance().getReference().child("userCourses");
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
        // changes code from int to str then adds it
        String codeStr = Integer.toString(code);
        addCourse(codeStr);
    }


    public void addCourse(String code){
        // check for duplicates before adding
        if(!userCourses.contains(code)){
            userCourses.add(code);
            userRef.setValue(userCourses);
        }
    }

    public void removeCourse(int code){
        removeCourse(Integer.toString(code));
    }

    public void removeCourse(String code){
        userCourses.remove(code);
        userRef.setValue((userCourses));
    }

    // for testing
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

                    // clear userCourses ArrayList
                    userCourses.clear();

                    // For course code in a userCourse list (Array<String>)
                    for(DataSnapshot data: dataSnapshot.getChildren()){
                        System.out.println("adding code");
                        System.out.println(data.getValue());

                        // extract code
                        String code = (String)data.getValue();

                        // add code to our list
                        userCourses.add(code);
                    }

                    if(adapter != null){
                        // updates recyclerView if it exists
                        adapter.notifyDataSetChanged();
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

    // for testing
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

    public Map<String, Map<String, String>> getCourses() {
        return courses;
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        this.adapter = adapter;
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



    // not needed
    public boolean makeNewUser(String checkID) { //ONLY USE THIS METHOD FOR SIGN-UP ACTIVITY!
        if (userRef.child(userId).getRoot() != null){
            userRef.child(userId).setValue("");
            return true;
        }
        return false;
    }
}
