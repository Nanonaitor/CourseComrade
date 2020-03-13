package com.ics45j.coursecomrade;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/* Made by Armando Contreras
* Sets up Startup activity functionality
* so application initializes properly*/
public class StartupActivity extends AppCompatActivity {
    //Variables
    private Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);

        getSupportActionBar().setTitle("Welcome");

        //Declarations
        buttonLogin = (Button) findViewById(R.id.buttonLogin);

        //Buttons
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLoginActivity();
            }
        });
    }
    public void openLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);           //UPDATE
        startActivity(intent);
    }
}
