package com.ldt.cuocduakythu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView txtDiem;
    ImageButton ibtnPlay;
    RadioButton cbOne, cbTwo, cbThree;
    SeekBar sbOne, sbTwo, sbThree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anhXa();

        CountDownTimer countDownTimer = new CountDownTimer(60000, 1) {
            @Override
            public void onTick(long l) {
                int number = 2;
                Random random = new Random();
                int one = random.nextInt(number);
                int two = random.nextInt(number);
                int three = random.nextInt(number);
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
                ibtnPlay.setVisibility(View.INVISIBLE);
                countDownTimer.start();
            }
        });
    }

    private void anhXa() {
        txtDiem     = (TextView) findViewById(R.id.textviewDiem);
        ibtnPlay    = (ImageButton) findViewById(R.id.imagePlay);
        cbOne       = (RadioButton) findViewById(R.id.checkBoxCharacter1);
        cbTwo       = (RadioButton) findViewById(R.id.checkBoxCharacter2);
        cbThree     = (RadioButton) findViewById(R.id.checkBoxCharacter3);
        sbOne       = (SeekBar) findViewById(R.id.seekBar1);
        sbTwo       = (SeekBar) findViewById(R.id.seekBar2);
        sbThree     = (SeekBar) findViewById(R.id.seekBar3);
    }
}