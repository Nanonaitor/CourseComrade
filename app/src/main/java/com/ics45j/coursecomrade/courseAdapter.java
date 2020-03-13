package com.ics45j.coursecomrade;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Map;

public class courseAdapter extends RecyclerView.Adapter<courseAdapter.courseViewHolder> {
    private CourseManager courseManager;

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



    public courseAdapter(CourseManager courseManager) { this.courseManager = courseManager;}

    @NonNull
    @Override
    public courseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_item, parent, false);
        courseViewHolder cvh = new courseViewHolder(v);
        return cvh;
    }

    @Override
    public void onBindViewHolder(@NonNull courseViewHolder holder, int position) {
        Map<String,String> currentCourse = courseManager.getCourses().get(courseManager.getUserCourses().get(position));
        holder.image.setImageResource(R.drawable.ic_class);
        holder.text1.setText(currentCourse.get("code") + " " + currentCourse.get("labels") + "\n"
                                + currentCourse.get("instructor"));
        holder.text2.setText(currentCourse.get("status"));
    }

    @Override
    public int getItemCount() {
        return courseManager.getUserCourses().size();
    }
}
