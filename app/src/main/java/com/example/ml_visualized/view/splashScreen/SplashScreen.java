package com.example.ml_visualized.view.splashScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ml_visualized.R;
import com.example.ml_visualized.controller.fileConnection.SharedPrefFileConnection;
import com.example.ml_visualized.controller.fileConnection.SharedPrefFileConnectionKeys;
import com.example.ml_visualized.controller.newScreen.GoToNewActivityController;

public class SplashScreen extends AppCompatActivity {

    // animation for logo at top of screen, and text at bottom of screen
    private Animation splashTopAnimation, splashBottomAnimation;

    // imageview for logo on splash screen
    private ImageView splashLogo;

    // textview for text on splash screen
    private TextView splashTitle, splashSlogan;

    // controller for handling going to a new activity
    private GoToNewActivityController goToNewActivityController;

    // duration before ending splash screen
    private final int SPLASH_SCREEN_DURATION = 3000;

    private SharedPrefFileConnection sharedPrefFileConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // set to full screen for loading
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // hide the action bar
        getSupportActionBar().hide();

        setContentView(R.layout.splash_screen);

        // create new instance of going to new activity controller
        // handles activity of going to a new screen
        goToNewActivityController = new GoToNewActivityController(this, this);

        // creates new instance for file connection to store user's data
        sharedPrefFileConnection = new SharedPrefFileConnection(this);

        loadAnimation();

        getSplashScreenComponents();

        setAnimToComponents();

        gotoStartScreen();
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

        splashLogo.setAnimation(splashTopAnimation);
        splashTitle.setAnimation(splashBottomAnimation);
        splashSlogan.setAnimation(splashBottomAnimation);
    }

    private void gotoStartScreen(){

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // check if user has logged in

                String stringClassToGo = "";

                if(sharedPrefFileConnection.getUserName().equals("")){
                    // user has not logged in, therefore username is empty
                    stringClassToGo = "view.loginScreen.LoginScreen";
                }
                else{
                    // user has logged in
                    stringClassToGo = "view.loginScreen.LoginScreen";

                }

                goToNewActivityController.goToNewActivity(getResources().getString(R.string.default_dir) + stringClassToGo,true);

            }
        }, SPLASH_SCREEN_DURATION); //setting that the new screen will be entered after a timeout.





    }

}