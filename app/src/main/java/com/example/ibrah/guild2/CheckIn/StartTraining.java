package com.example.ibrah.guild2.CheckIn;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.SystemClock;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ibrah.guild2.R;

import java.util.Timer;

public class StartTraining extends AppCompatActivity {

    TextView txtTimer;
    Button btnStartTimer,btnEndTimer;
    Timer timer;
    long starttime = 0L;
    long timeInMilliseconds = 0L;
    long timeSwapBuff = 0L;
    long updatedtime = 0L;
    int t = 1;
    int secs = 0;
    int mins = 0;
    int milliseconds = 0;
    Handler handler = new Handler();
    Snackbar mySnackbar;
    RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_training);

        txtTimer = (TextView)findViewById(R.id.timer);
        btnStartTimer = (Button)findViewById(R.id.btnstart);
        btnEndTimer = (Button)findViewById(R.id.btnreset);
        relativeLayout = (RelativeLayout)findViewById(R.id.activity_start_training);

    }

    public void TimerStart(View view)
    {
        if (t == 1) {
            btnStartTimer.setText("Pause");
            starttime = SystemClock.uptimeMillis();
            handler.postDelayed(updateTimer, 0);
            t = 0;
            mySnackbar = Snackbar.make(relativeLayout,"Antreman baştıldıldı",Snackbar.LENGTH_LONG);
            mySnackbar.show();
        } else {
            btnStartTimer.setText("Start");
            txtTimer.setTextColor(Color.BLUE);
            timeSwapBuff += timeInMilliseconds;
            handler.removeCallbacks(updateTimer);
            t = 1;
        }
    }

    public void TimerEnd(View view)
    {
        starttime = 0L;
        timeInMilliseconds = 0L;
        timeSwapBuff = 0L;
        updatedtime = 0L;
        t = 1;
        secs = 0;
        mins = 0;
        milliseconds = 0;
        btnStartTimer.setText("Start");
        handler.removeCallbacks(updateTimer);
        txtTimer.setText("00:00:00");

    }

    public Runnable updateTimer = new Runnable() {
        public void run() {
            timeInMilliseconds = SystemClock.uptimeMillis() - starttime;
            updatedtime = timeSwapBuff + timeInMilliseconds;
            secs = (int) (updatedtime / 1000);
            mins = secs / 60;
            secs = secs % 60;
            milliseconds = (int) (updatedtime % 1000);
            txtTimer.setText("" + mins + ":" + String.format("%02d", secs) + ":"
                    + String.format("%03d", milliseconds));
            txtTimer.setTextColor(Color.RED);
            handler.postDelayed(this, 0);
        }};
}

