package com.ldt.signin_signup_profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SigninActivity extends AppCompatActivity {

    Button btnSignin, btnSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        anhXa();

        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openProfileActivity();
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSignupActivity();
            }
        });

    }

    private void anhXa() {
        btnSignin = (Button) findViewById(R.id.buttonSignin);
        btnSignup = (Button) findViewById(R.id.buttonSignup);
    }

    private void openProfileActivity() {
        Intent intent = new Intent(SigninActivity.this, ProfileActivity.class);
        startActivity(intent);
    }

    private void openSignupActivity() {
        Intent intent = new Intent(SigninActivity.this, SignupActivity.class);
        startActivity(intent);
    }
}