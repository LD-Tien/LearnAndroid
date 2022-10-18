package com.ldt.recyclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SelectedUserActivity extends AppCompatActivity {
    private TextView tvUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_user);
        anhXa();

        Intent intent = getIntent();
        if(intent.getExtras() != null) {
            UserModel userModel = (UserModel) intent.getSerializableExtra("data");
            tvUser.setText(userModel.getUserName());
        }
    }

    private void anhXa() {
        tvUser = findViewById(R.id.selectedUser);
    }
}