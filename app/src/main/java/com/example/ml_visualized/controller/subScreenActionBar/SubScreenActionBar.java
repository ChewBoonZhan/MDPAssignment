package com.example.ml_visualized.controller.subScreenActionBar;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.ml_visualized.R;

public class SubScreenActionBar {
    // activity for back
    private Activity activity;
    public SubScreenActionBar(Activity activity){
        this.activity = activity;
    }

    private void setActionBarBackButtonOnClick(){
        // get back button
        Button actionBarBackButton = activity.findViewById(R.id.fragment_action_bar_back_button);

        // set the onClick for back button
        actionBarBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //calls the back function to go back to previous screen
                actionBarBackButton();
            }
        });

    }

    private void actionBarBackButton(){
        this.activity.finish();
    }

    private void setActionBarTitleText(int titleTextResources){
        // get title text
        TextView actionBarTitleText = activity.findViewById(R.id.fragment_action_bar_title);

        actionBarTitleText.setText(titleTextResources);
    }

    public void setupActionBar(int titleTextResources){
        setActionBarBackButtonOnClick();

        setActionBarTitleText(titleTextResources);

    }
}


