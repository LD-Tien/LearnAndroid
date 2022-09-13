package com.ldt.signin_signup_profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignupActivity extends AppCompatActivity {

    Button btnSignup, btnSignin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        anhXa();

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openProfileActivity();
            }
        });

        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSigninActivity();
            }
        });
    }

    private void anhXa() {
        btnSignup = (Button) findViewById(R.id.buttonSignup);
        btnSignin = (Button) findViewById(R.id.buttonSignin);
    }

    private void openProfileActivity() {
        Intent intent = new Intent(SignupActivity.this, ProfileActivity.class);
        startActivity(intent);
    }

    private void openSigninActivity() {
        Intent intent = new Intent(SignupActivity.this, SigninActivity.class);
        startActivity(intent);
    }
}