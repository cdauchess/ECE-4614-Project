package com.example.charlie.a4614app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Splash_Screen extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash__screen);

        int secondsDelayed = 2;
        new Handler().postDelayed(new Runnable() {
            public void run() {
                Intent main = new Intent(Splash_Screen.this, MainActivity.class);
                main.putExtra("activity", "Splash");
                startActivity(main);
                finish();
            }
        }, secondsDelayed * 1000);
    }
}



