package com.au.employeemanagement2;

import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Viewattendence extends AppCompatActivity {
    private ProgressBar progressBar;
    private int progressStatus = 0;
    private TextView textView,mTextView;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewattendence);
        progressBar = (ProgressBar) findViewById(R.id.progressBar1);
        textView = (TextView) findViewById(R.id.textView1);
        mTextView = (TextView) findViewById(R.id.textView);
        mTextView.setTextColor(Color.WHITE);
        mTextView.setMovementMethod(new ScrollingMovementMethod());
       int value= getIntent().getIntExtra("percent",0);
        String Detail=getIntent().getStringExtra("detail");
        // Start long running operation in a background thread
        final int finalValue = value;
        progressBar.getProgressDrawable().setColorFilter(
                Color.BLUE, android.graphics.PorterDuff.Mode.SRC_IN);
        new Thread(new Runnable() {
            public void run() {
                while (progressStatus < finalValue) {
                    progressStatus += 1;
                    // Update the progress bar and display the
                    //current value in the text view
                    handler.post(new Runnable() {
                        public void run() {
                            progressBar.setProgress(progressStatus);
                            textView.setText(progressStatus+"/"+progressBar.getMax());
                        }
                    });
                    try {
                        // Sleep for 200 milliseconds.
                        //Just to display the progress slowly
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        mTextView.setText(Detail);
    }

    }

