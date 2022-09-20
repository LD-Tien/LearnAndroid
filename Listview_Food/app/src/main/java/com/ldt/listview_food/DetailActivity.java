package com.ldt.listview_food;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private TextView tvName, tvDescription, tvPrice;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        setTitle("Detail");

        anhXa();
        image.setImageResource(getIntent().getIntExtra("image", R.mipmap.ic_launcher_round));
        tvName.setText(getIntent().getStringExtra("name"));
        tvDescription.setText(getIntent().getStringExtra("description"));
        tvPrice.setText(getIntent().getStringExtra("price"));
    }

    private void anhXa() {
        image = findViewById(R.id.image);
        tvName = findViewById(R.id.tvName);
        tvDescription = findViewById(R.id.tvDescription);
        tvPrice = findViewById(R.id.tvPrice);
    }
}