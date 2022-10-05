package com.ldt.sharedprefenrences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText edtUsername, edtPassword;
    private CheckBox cbRememberPassword;
    private Button btnDangNhap;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Lê Đức Tiên - 2050531200309");
        anhXa();

        sharedPreferences = getSharedPreferences("dataLogin", MODE_PRIVATE);

        // lấy giá trị sharedPreferences
        edtUsername.setText(sharedPreferences.getString("username",""));
        edtPassword.setText(sharedPreferences.getString("password", ""));
        cbRememberPassword.setChecked(sharedPreferences.getBoolean("checked", false));

        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edtUsername.getText().toString();
                String password = edtPassword.getText().toString();

                if(username.equals("ldtien") && password.equals("abc")) {
                    Toast.makeText(MainActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    if(cbRememberPassword.isChecked()) {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("username", username);
                        editor.putString("password",  password);
                        editor.putBoolean("checked", true);
                        editor.commit();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Lỗi đăng nhập", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void anhXa() {
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        cbRememberPassword = findViewById(R.id.cbRememberPassword);
        btnDangNhap = findViewById(R.id.btnDangNhap);
    }
}