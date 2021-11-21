package com.example.ml_visualized.view.loginScreen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ml_visualized.R;
import com.example.ml_visualized.controller.fileConnection.SharedPrefFileConnection;
import com.example.ml_visualized.view.homeScreen.HomeScreen;

public class LoginScreen extends AppCompatActivity{

    // gets shared preference to store data
    private SharedPrefFileConnection sharedPrefFileConnection;

    private final String userName = "Guest";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);


        // to save user's details in the file
        sharedPrefFileConnection = new SharedPrefFileConnection(this);


    }

    /**
     * Show toast that social media linking is now developed yet
     * @param view
     */
    public void socialMediaButtonClicked(View view){

        Toast toast = Toast.makeText(this, "Social Media Linking is not available in demo.", Toast.LENGTH_LONG);
        toast.show();
    }

    /**
     * OnClick that allows user to skip social media linking
     * the usser can proceed to use the app
     * @param view
     */
    public void skipSocialMediaClicked(View view){

        // store user's name as Guest to enter home screen
        sharedPrefFileConnection.storeUserName(userName);

        // start new intent to go to the home screen
        Intent intent = new Intent(this, HomeScreen.class);
        startActivity(intent);
    }
}