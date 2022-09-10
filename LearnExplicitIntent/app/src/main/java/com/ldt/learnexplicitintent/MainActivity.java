package com.ldt.learnexplicitintent;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    Button btnOpenChild;
    EditText edtNoiDung;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("LDT", "onCreate Main");
        btnOpenChild = (Button) findViewById(R.id.buttonOpenChildActivity);
        edtNoiDung = (EditText) findViewById(R.id.editTextNoidung);

        btnOpenChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openChildActivity();
            }
        });
    }

    void openChildActivity() {
        Intent intent = new Intent(this, ChildActivity.class);
        intent.putExtra("NoiDung", edtNoiDung.getText().toString());
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("LDT", "onStart Main");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("LDT", "onRestart Main");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("LDT", "onResume Main");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("LDT", "onPause Main");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("LDT", "onStop Main");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("LDT", "onDestroy Main");
    }
}