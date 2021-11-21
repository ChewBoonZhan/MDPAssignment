package com.example.ml_visualized.controller.subScreenActionBar;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ml_visualized.R;

public class SubScreenActionBar {
    // activity for bringing user back to previois activity
    private Activity activity;

    /**
     * Constructor
     * @param activity - activity to bring user to previous activity
     */
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
        // when back is pressed, finish current activity
        // brings user back to previous activity
        this.activity.finish();
    }

    private void setActionBarTitleText(int titleTextResources){
        // get title text
        TextView actionBarTitleText = activity.findViewById(R.id.fragment_action_bar_title);

        // set text for the action bar title
        actionBarTitleText.setText(titleTextResources);
    }

    public void setupActionBar(int titleTextResources){
        // set the onclick for the back button on action bar
        setActionBarBackButtonOnClick();

        // set the text for the title text on the action bar
        setActionBarTitleText(titleTextResources);

    }
}


