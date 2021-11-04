package com.example.ml_visualized;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {

    // animation for logo at top of screen, and text at bottom of screen
    private Animation splashTopAnimation, splashBottomAnimation;

    // imageview for logo on splash screen
    private ImageView splashLogo;

    // textview for text on splash screen
    private TextView splashTitle, splashSlogan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("THANOS","snapped");

        // set to full screen for loading
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        getSupportActionBar().hide();

        setContentView(R.layout.splash_screen);

        loadAnimation();

        getSplashScreenComponents();

        setAnimToComponents();
    }

    private void loadAnimation(){
        splashTopAnimation  =AnimationUtils.loadAnimation(this, R.anim.splash_top_animation);
        splashBottomAnimation = AnimationUtils.loadAnimation(this, R.anim.splash_bottom_animation);

    }

    private void getSplashScreenComponents(){
        splashLogo = findViewById(R.id.splash_icon);
        splashTitle = findViewById(R.id.splash_title);
        splashSlogan = findViewById(R.id.splash_desc);

    }

    private void setAnimToComponents(){
        Log.d("thanos","here");

        splashLogo.setAnimation(splashTopAnimation);
        splashTitle.setAnimation(splashBottomAnimation);
        splashSlogan.setAnimation(splashBottomAnimation);
    }
}