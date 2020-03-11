package com.ics45j.coursecomrade;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class courseAdapter extends RecyclerView.Adapter<courseAdapter.courseViewHolder> {
    private ArrayList<ExampleCourse> mCourseList;

    public static class courseViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView text1, text2;

        public courseViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imageView);
            text1 = itemView.findViewById(R.id.textView1);
            text2 = itemView.findViewById(R.id.textView2);
        }
    }

    public courseAdapter(ArrayList<ExampleCourse> courseList) {
        mCourseList = courseList;
    }

    @NonNull
    @Override
    public courseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_item, parent, false);
        courseViewHolder cvh = new courseViewHolder(v);
        return cvh;
    }

    @Override
    public void onBindViewHolder(@NonNull courseViewHolder holder, int position) {
        ExampleCourse currentCourse = mCourseList.get(position);
        holder.image.setImageResource(currentCourse.getImageResource());
        holder.text1.setText(currentCourse.getText1());
        holder.text2.setText(currentCourse.getText2());
    }

    @Override
    public int getItemCount() {
        return mCourseList.size();
    }
}
