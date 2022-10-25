package com.ldt.contentprovider;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ContactDetailActivity extends AppCompatActivity {
    TextView tvContactName, tvPhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail);
        setTitle("Contact detail");
        anhXa();

        tvContactName.setText(getIntent().getStringExtra("name").toString());
        tvPhoneNumber.setText(getIntent().getStringExtra("number").toString());

    }

    private void anhXa() {
        tvContactName = findViewById(R.id.tvContactName);
        tvPhoneNumber = findViewById(R.id.tvPhoneNumber);
    }
}