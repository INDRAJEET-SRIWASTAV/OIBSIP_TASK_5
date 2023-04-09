package com.example.oibsip_task_5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;


public class MainActivity extends AppCompatActivity {
    private int second;
    private boolean running;
    private boolean wasrunning;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState != null){
            savedInstanceState.getInt("second");
            savedInstanceState.getBoolean("running");
            savedInstanceState.getBoolean("wasRunning");
        }

        runTimer();
    }
    public void onStart(View view){
        running =true;

    }
    public void onStop(View view){
        running = false;
    }
    public void onReset(View view){
        running = false;
        second =0;
    }

    @Override
    protected void onPause() {
        super.onPause();
        wasrunning = running;
        running = false;

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(wasrunning){
            running= true;

        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("second",second);
        outState.putBoolean("running",running);
        outState.putBoolean("wasRunning",wasrunning);
    }

    private void runTimer() {
        TextView timeView = findViewById(R.id.textview);
       Handler handler = new Handler();

       handler.post(new Runnable() {
           @Override
           public void run() {
               int hours = second/3600;
               int minutes = (second % 3600)/60;
               int secs = second % 60;

               String time = String.format(Locale.getDefault(),
                       "%d:%2d:%2d",hours,minutes,secs);



               timeView.setText(time);
               if(running){
                   second++;
               }
               handler.postDelayed(this,1000);
           }
       });


    }
}