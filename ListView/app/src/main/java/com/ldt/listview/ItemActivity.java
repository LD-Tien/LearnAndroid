package com.ldt.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ItemActivity extends AppCompatActivity {

    TextView tvItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        setTitle("Item");
        tvItem = (TextView) findViewById(R.id.textViewItem);
        Intent intent = getIntent();

        tvItem.setText(intent.getStringExtra("item"));

    }
}