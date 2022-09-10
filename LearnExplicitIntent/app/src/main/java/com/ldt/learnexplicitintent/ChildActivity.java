package com.ldt.learnexplicitintent;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ChildActivity extends Activity {

    Button btnBackToMain;
    TextView tvNoiDung;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child);
        Log.d("LDT", "onCreate Child");

        btnBackToMain = (Button) findViewById(R.id.buttonBackToMainActivity);
        tvNoiDung = (TextView) findViewById(R.id.textViewNoiDung);

        btnBackToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMainActivity();
            }
        });
    }

    void openMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        Intent i = getIntent();
        tvNoiDung.setText(i.getSerializableExtra("NoiDung").toString());
        startActivity(intent);
    }
}