package com.example.jivenlanstabien.projecthitch1.Login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.content.Intent;
import android.os.Handler;
import android.view.View;

import com.example.jivenlanstabien.projecthitch1.R;

public class splash_screen extends AppCompatActivity {
    private final int SPLASH_DISPLAY_LENGTH = 3000;
    ProgressBar Loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Loading = (ProgressBar) findViewById(R.id.Loading);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(splash_screen.this,Login.class);
                Loading.setVisibility(View.VISIBLE);
                splash_screen.this.startActivity(mainIntent);
                splash_screen.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
