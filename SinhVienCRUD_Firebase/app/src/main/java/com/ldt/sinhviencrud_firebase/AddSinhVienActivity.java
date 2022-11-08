package com.ldt.sinhviencrud_firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AddSinhVienActivity extends AppCompatActivity {

    private Button btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sinh_vien);

        anhXa();
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddSinhVienActivity.this, ListSinhVienActivity.class);
            }
        });
    }

    private void anhXa() {
        btnCancel = findViewById(R.id.btnCancel);
    }
}