package com.ldt.cuocduakythu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView txtDiem;
    ImageButton ibtnPlay;
    RadioButton cbOne, cbTwo, cbThree;
    RadioGroup radioGroup;
    SeekBar sbOne, sbTwo, sbThree;
    int soDiem = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anhXa();

        txtDiem.setText(soDiem + "");

        CountDownTimer countDownTimer = new CountDownTimer(60000, 1) {
            @Override
            public void onTick(long l) {
                int number = 2;
                Random random = new Random();
                int one = random.nextInt(number);
                int two = random.nextInt(number);
                int three = random.nextInt(number);



                // Kiểm tra ai về trước
                if(sbOne.getProgress() >= sbOne.getMax()) {
                    this.cancel();
                    ibtnPlay.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this, "ONE WIN", Toast.LENGTH_SHORT).show();
                    // Kiểm tra đặt cược
                    if(cbOne.isChecked()) {
                        soDiem += 10;
                        Toast.makeText(MainActivity.this, "Bạn đoán chính xác", Toast.LENGTH_SHORT).show();
                    } else {
                        soDiem -= 5;
                        Toast.makeText(MainActivity.this, "Bạn đoán sai rồi", Toast.LENGTH_SHORT).show();
                    }
                    txtDiem.setText(soDiem + "");
                }

                if(sbTwo.getProgress() >= sbTwo.getMax()) {
                    this.cancel();
                    ibtnPlay.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this, "TWO WIN", Toast.LENGTH_SHORT).show();
                    // Kiểm tra đặt cược
                    if(cbTwo.isChecked()) {
                        soDiem += 10;
                        Toast.makeText(MainActivity.this, "Bạn đoán chính xác", Toast.LENGTH_SHORT).show();
                    } else {
                        soDiem -= 5;
                        Toast.makeText(MainActivity.this, "Bạn đoán sai rồi", Toast.LENGTH_SHORT).show();
                    }
                    txtDiem.setText(soDiem + "");
                }

                if(sbThree.getProgress() >= sbThree.getMax()) {
                    this.cancel();
                    ibtnPlay.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this, "THREE WIN", Toast.LENGTH_SHORT).show();
                    // Kiểm tra đặt cược
                    if(cbThree.isChecked()) {
                        soDiem += 10;
                        Toast.makeText(MainActivity.this, "Bạn đoán chính xác", Toast.LENGTH_SHORT).show();
                    } else {
                        soDiem -= 5;
                        Toast.makeText(MainActivity.this, "Bạn đoán  sai rồi", Toast.LENGTH_SHORT).show();
                    }
                    txtDiem.setText(soDiem + "");
                }
                sbOne.setProgress(sbOne.getProgress() + one);
                sbTwo.setProgress(sbTwo.getProgress() + two);
                sbThree.setProgress(sbThree.getProgress() + three);
            }

            @Override
            public void onFinish() {

            }
        };

        ibtnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cbOne.isChecked() || cbTwo.isChecked() || cbThree.isChecked()) {
                    ibtnPlay.setVisibility(View.INVISIBLE);
                    sbOne.setProgress(0);
                    sbTwo.setProgress(0);
                    sbThree.setProgress(0);
                    countDownTimer.start();
                } else {
                    Toast.makeText(MainActivity.this, "Vui lòng đặt cược trước khi chơi", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void anhXa() {
        txtDiem     = (TextView) findViewById(R.id.textviewDiem);
        ibtnPlay    = (ImageButton) findViewById(R.id.imagePlay);
        radioGroup  = (RadioGroup) findViewById(R.id.radioGroup);
        cbOne       = (RadioButton) findViewById(R.id.checkBoxCharacter1);
        cbTwo       = (RadioButton) findViewById(R.id.checkBoxCharacter2);
        cbThree     = (RadioButton) findViewById(R.id.checkBoxCharacter3);
        sbOne       = (SeekBar) findViewById(R.id.seekBar1);
        sbTwo       = (SeekBar) findViewById(R.id.seekBar2);
        sbThree     = (SeekBar) findViewById(R.id.seekBar3);
    }
}