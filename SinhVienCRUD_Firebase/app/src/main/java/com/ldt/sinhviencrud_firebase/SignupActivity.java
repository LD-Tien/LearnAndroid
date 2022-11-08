package com.ldt.sinhviencrud_firebase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignupActivity extends AppCompatActivity {
    TextView tvLogin;
    Button btnSignup;
    EditText edtFirstName, edtLastName, edtUserName, edtPassword, edtConfirmPassword;
    User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        anhXa();

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtFirstName.getText().toString().isEmpty()) {
                    edtFirstName.requestFocus();
                    return;
                }
                if(edtLastName.getText().toString().isEmpty()) {
                    edtLastName.requestFocus();
                    return;
                }
                if(edtUserName.getText().toString().isEmpty()) {
                    edtUserName.requestFocus();
                    return;
                }
                if(edtPassword.getText().toString().isEmpty()) {
                    edtPassword.requestFocus();
                    return;
                }
                if(edtConfirmPassword.getText().toString().isEmpty()) {
                    edtConfirmPassword.requestFocus();
                    return;
                }

                if(!edtPassword.getText().toString().equals(edtConfirmPassword.getText().toString())) {
                    edtConfirmPassword.requestFocus();
                    Toast.makeText(SignupActivity.this, "Confirm password không chính xác!", Toast.LENGTH_SHORT).show();
                    return;
                }
                user.setUserFullName(edtFirstName.getText().toString() + " " + edtLastName.getText().toString());
                user.setUserName(edtUserName.getText().toString());
                user.setUserPassword(edtPassword.getText().toString());
                user.setUserImg(R.mipmap.ic_launcher_round);

                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                intent.putExtra("user", user);
                Toast.makeText(getApplicationContext(), "Đăng ký tài khoản thành công!", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });

    }

    private void anhXa() {
        tvLogin = findViewById(R.id.tvLogin);
        btnSignup = findViewById(R.id.btnSignup);
        edtFirstName = findViewById(R.id.edtFirstName);
        edtLastName = findViewById(R.id.edtLastName);
        edtUserName = findViewById(R.id.edtUsernameSignup);
        edtPassword = findViewById(R.id.edtPasswordSignup);
        edtConfirmPassword = findViewById(R.id.edtConfirmPasswordSignup);
    }
}