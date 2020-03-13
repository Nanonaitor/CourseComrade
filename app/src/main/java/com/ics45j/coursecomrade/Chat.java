package com.ics45j.coursecomrade;

import java.util.ArrayList;
import java.util.Map;

public class Chat {
    private CourseManager courseManager;
    private ArrayList<Map<String, String>> messages;

    public Chat(CourseManager courseManager){
        this.courseManager = courseManager;
        messages = new ArrayList<>();
    }



}
