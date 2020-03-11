package com.ics45j.coursecomrade;

public class ExampleCourse {
    private int imageResource;
    private String mText1, mText2;

    public ExampleCourse(int imageResource, String text1, String text2){
        imageResource = imageResource;
        mText1 = text1;
        mText2 = text2;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getText1() {
        return mText1;
    }

    public String getText2() {
        return mText2;
    }
}
