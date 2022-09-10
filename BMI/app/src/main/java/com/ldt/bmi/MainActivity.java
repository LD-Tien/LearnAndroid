package com.ldt.bmi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Tinh();
            }
        });
    }

    public void Tinh() {
//         Do something in response to button click
        EditText et_ChieuCao = (EditText) findViewById(R.id.editTextChieuCao);
        EditText et_CanNang = (EditText) findViewById(R.id.editTextCanNang);
        TextView tv_KetQua = (TextView) findViewById(R.id.KetQua);
        TextView tv_bmi = (TextView) findViewById(R.id.BMI);
        if(String.valueOf(et_ChieuCao.getText()).equals("") || String.valueOf(et_CanNang.getText()).equals("")) {
            return;
        }
        double chieu_cao = Double.parseDouble(String.valueOf(et_ChieuCao.getText()));
        double can_nang = Double.parseDouble(String.valueOf(et_CanNang.getText()));
        double bmi = can_nang/(chieu_cao*chieu_cao);

        if(bmi >= 35)
            tv_KetQua.setText("Người béo phì độ III");
        else if (bmi >= 30)
            tv_KetQua.setText("Người béo phì độ II");
        else if (bmi >= 25)
            tv_KetQua.setText("Người béo phì độ I");
        else if (bmi >= 18)
            tv_KetQua.setText("Người bình thường");
        else
            tv_KetQua.setText("Người gầy");

        tv_bmi.setText("Chỉ số BMI là " + String.valueOf(bmi));
    }
}