package com.ics45j.coursecomrade;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUpActivity extends AppCompatActivity {
    //Variables
    EditText textUsernameInput;
    Button buttonSignUp;
    String submittedUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Action Bar Declarations
        getSupportActionBar().setTitle("Sign Up");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Variables set
        textUsernameInput = (EditText)findViewById(R.id.textMakeUsername);
        buttonSignUp = findViewById(R.id.buttonRegister);
        submittedUsername = "";

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submittedUsername = textUsernameInput.getText().toString();
                //TODO: Make SignUp work!
                /*if(submittedUsername == Some_Username_In_Firebase)
                    Toast.makeText(getApplicationContext(), "That username is taken. Please try again.", Toast.LENGTH_SHORT).show();
                else {
                    // Make an account!
                }*/

            }
        });
    }
}
