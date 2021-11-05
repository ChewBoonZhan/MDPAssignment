package com.example.ml_visualized.view.loginScreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ml_visualized.R;
import com.example.ml_visualized.controller.fileConnection.SharedPrefFileConnection;
import com.example.ml_visualized.customToast.CustomToast;
import com.example.ml_visualized.view.homeScreen.HomeScreen;

public class LoginScreen extends AppCompatActivity{

    private SharedPrefFileConnection sharedPrefFileConnection;

    private final String userName = "Guest";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);

        // hide the action bar
        getSupportActionBar().hide();

        // to save user's details in the file
        sharedPrefFileConnection = new SharedPrefFileConnection(this);


    }

    public void socialMediaButtonClicked(View view){

        Toast toast = Toast.makeText(this, "Social Media Linking is not available in demo.", Toast.LENGTH_LONG);
        toast.show();
    }

    public void skipSocialMediaClicked(View view){

        // store user's name as Guest to enter home screen
        /**
         * TODO: Remove this, and add it into the intro fragment in home_screen when it is added
         */
        sharedPrefFileConnection.storeUserName(userName);

        Intent intent = new Intent(this, HomeScreen.class);
        startActivity(intent);
    }
}