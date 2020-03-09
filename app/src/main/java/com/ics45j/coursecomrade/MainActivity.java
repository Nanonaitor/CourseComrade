package com.ics45j.coursecomrade;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void test(View view){
        WebSocScraper scraper = new WebSocScraper();
        scraper.initDatabase();
        Button buttonTest = (Button)findViewById(R.id.buttonTest);
        buttonTest.setText("Done!");
        //buttonTest.invalidate();
        //buttonTest.requestLayout();
    }
}
