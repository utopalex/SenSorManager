package com.example.m1.test;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class BallActivity extends AppCompatActivity {

    Ball ball;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ball);

        ball = (Ball) findViewById(R.id.ball);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
