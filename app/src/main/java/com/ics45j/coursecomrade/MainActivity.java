package com.ics45j.coursecomrade;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.concurrent.TimeUnit;

/* Made by Armando Contreras and Nathaniel Tisuela
 * Sets up Main activity functionality. Serves
 * as a users home menu.*/
public class MainActivity extends AppCompatActivity {
    private Button buttonSearch, buttonUserCourses;
    private String username;
    private TextView displayedUsername;

    // Everything that needs Firebase goes through courseManager
    // actually it's not needed here


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        System.out.println("grabbing username");
        username = bundle.getString("username");
        System.out.println("opening main");

        displayedUsername = findViewById(R.id.textUsername);
        displayedUsername.setText("Welcome\n" + username.toUpperCase() + "!");

        //Initialize courseManager
        // This will grab all information needed from Firebase


        //Action Bar Declarations
        getSupportActionBar().setTitle("Home");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Buttons
        buttonSearch = (Button) findViewById(R.id.buttonSearch);
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSearchActivity();
            }
        });

        buttonUserCourses = (Button) findViewById(R.id.buttonUserCourses);
        buttonUserCourses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUserCoursesActivity();
            }
        });
    }

    /* Made by Armando Contreras and Nathaniel Tisuela
     * opens the search activity and sends bundled
     * information to it*/
    public void openSearchActivity() {
        Bundle bundle = new Bundle();
        bundle.putString("username", username);

        Intent intent = new Intent(this, SearchActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /* Made by Armando Contreras and Nathaniel Tisuela
     * opens the user courses activity and sends bundled
     * information to it*/
    public void openUserCoursesActivity() {
        Bundle bundle = new Bundle();
        bundle.putString("username", username);

        Intent intent = new Intent(this, UserCoursesActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
