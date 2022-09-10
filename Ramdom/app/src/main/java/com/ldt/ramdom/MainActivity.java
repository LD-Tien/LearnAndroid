package com.ldt.ramdom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView txtNumber;
    Button btnRandom;
    EditText edtMin, edtMax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anhXa();


        btnRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Lấy dữ liệu nhập vào
                String chuoiMin = edtMin.getText().toString();
                String chuoiMax = edtMax.getText().toString();

                // Kiểm dữ liệu đã được nhập hay chưa
                // Chuoi.length() = 0
                // Chuoi.isEmpty()
                if(chuoiMin.isEmpty()) {
                    edtMin.requestFocus();
                    return;
                }
                if(chuoiMax.isEmpty()) {
                    edtMax.requestFocus();
                    return;
                }

                // Ép kiểu
                int min = Integer.parseInt(chuoiMin);
                int max = Integer.parseInt(chuoiMax);

                if(min >= max) {
                    Toast.makeText(MainActivity.this, "số min phải nhỏ hơn số max", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Tạo số ngẫu nhiên
                Random random = new Random();
                int randomNumber = random.nextInt(max - min + 1 )+min;
                txtNumber.setText(String.valueOf(randomNumber)); // randomNumber + ""
            }
        });
    }

    private void anhXa() {
        txtNumber = (TextView) findViewById(R.id.textViewNumber);
        btnRandom = (Button) findViewById(R.id.buttonRamdom);
        edtMin = (EditText) findViewById(R.id.editTextMin);
        edtMax = (EditText) findViewById(R.id.editTextMax);
    }

}