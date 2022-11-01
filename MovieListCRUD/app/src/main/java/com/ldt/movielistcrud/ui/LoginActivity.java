package com.ldt.movielistcrud.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.ldt.movielistcrud.R;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private Button btnLogin;
    private TextView tvSignup;
    private EditText edtUserName, edtUserPassword;
    private CheckBox cbRememberPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        anhXa();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, NavigationActivity.class );
                startActivity(intent);
                finish();
            }
        });

        tvSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class );
                startActivity(intent);
            }
        });
    }

    private void anhXa() {
        btnLogin = findViewById(R.id.btnLogin);
        tvSignup = findViewById(R.id.tvSignup);
        edtUserName = findViewById(R.id.edtUsernameLogin);
        edtUserPassword = findViewById(R.id.edtPasswordLogin);
        cbRememberPassword = findViewById(R.id.cbRememberPassword);
    }
}