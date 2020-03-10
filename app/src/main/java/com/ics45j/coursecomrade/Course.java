package com.ics45j.coursecomrade;

import java.util.ArrayList;

public class Course {
    private int code;
    private String name;
    private String status;

    public Course(int code, String name, String status){
        this.code = code;
    }

    public boolean isOpen(){
        return (status == "OPEN");
    }
}
