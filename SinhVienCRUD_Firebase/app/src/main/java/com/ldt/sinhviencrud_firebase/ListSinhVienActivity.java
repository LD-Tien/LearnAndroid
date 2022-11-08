package com.ldt.sinhviencrud_firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ListSinhVienActivity extends AppCompatActivity {
    private FloatingActionButton btnAddSV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_sinh_vien);

        anhXa();

        btnAddSV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListSinhVienActivity.this, AddSinhVienActivity.class);
                startActivity(intent);
            }
        });
    }

    private void anhXa() {
        btnAddSV = findViewById(R.id.floatingActionButtonAddSV);
    }
}