package com.ics45j.coursecomrade;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/* Made by Armando Contreras
 * Sets up Login activity functionality
 * so users may log into their accounts*/
public class LoginActivity extends AppCompatActivity {
    //Variables
    EditText textUsernameInput;
    Button buttonLogin;
    String submittedUsername;
    private String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Action Bar Declarations
        getSupportActionBar().setTitle("Login");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Variables set
        textUsernameInput = (EditText)findViewById(R.id.textUsernameLogin);
        buttonLogin = findViewById(R.id.buttonSignIn);
        submittedUsername = "";

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = textUsernameInput.getText().toString();
                if (username.length() < 1)
                    Toast.makeText(getApplicationContext(), "Please input a username", Toast.LENGTH_SHORT).show();
                else {
                    Toast.makeText(getApplicationContext(), "Logging in...", Toast.LENGTH_SHORT).show();
                    openMainActivity();
                }
            }
        });
    }

    public void openMainActivity() {
        Bundle bundle = new Bundle();
        bundle.putString("username", username);
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtras(bundle);

        startActivity(intent);
    }
}
