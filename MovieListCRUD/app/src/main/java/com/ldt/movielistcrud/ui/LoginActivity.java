package com.ldt.movielistcrud.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ldt.movielistcrud.R;
import com.ldt.movielistcrud.model.User;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private Button btnLogin;
    private TextView tvSignup;
    private EditText edtUserName, edtUserPassword;
    private CheckBox cbRememberPassword;
    private List<User> userList;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        anhXa();
        sharedPreferences = getSharedPreferences("dataLogin", MODE_PRIVATE);
        edtUserName.setText(sharedPreferences.getString("userName",""));
        edtUserPassword.setText(sharedPreferences.getString("userPassword", ""));
        cbRememberPassword.setChecked(sharedPreferences.getBoolean("checked", false));

        createDefaultUsers();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtUserName.getText().toString().isEmpty()) {
                    edtUserName.requestFocus();
                    return;
                }
                if(edtUserPassword.getText().toString().isEmpty()) {
                    edtUserPassword.requestFocus();
                    return;
                }
                String userName = edtUserName.getText().toString();
                String userPassword = edtUserPassword.getText().toString();
                for (User user: userList) {
                    if (userName.equals(user.getUserName()) && userPassword.equals(user.getUserPassword())) {
                        Intent intent = new Intent(LoginActivity.this, NavigationActivity.class);
                        intent.putExtra("user", user);
                        if(cbRememberPassword.isChecked()) {
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("userName", user.getUserName());
                            editor.putString("userPassword",  user.getUserPassword());
                            editor.putInt("imgAvatar", user.getUserImg());
                            editor.putString("userFullName", user.getUserFullName());
                            editor.putBoolean("checked", true);
                            editor.commit();
                        } else {
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("userName", "");
                            editor.putString("userPassword",  "");
                            editor.putInt("imgAvatar", R.mipmap.ic_launcher_round);
                            editor.putString("userFullName", "");
                            editor.putBoolean("checked", false);
                            editor.commit();
                        }
                        startActivity(intent);
                        return;
                    }
                }
                Toast.makeText(LoginActivity.this, "Đăng nhập không thành công!", Toast.LENGTH_SHORT).show();
            }
        });

        tvSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), SignupActivity.class);
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

    private void createDefaultUsers() {
        userList = new ArrayList<>();
        userList.add(new User(R.drawable.user_avatar, "2050531200309", "1234", "Lê Đức Tiên"));
    }

}