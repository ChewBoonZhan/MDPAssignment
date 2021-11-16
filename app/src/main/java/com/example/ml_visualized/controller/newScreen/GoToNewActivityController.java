package com.example.ml_visualized.controller.newScreen;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

public class GoToNewActivityController {

    // context for opening a new screen in app
    private Context context;

    // activity for opening a new screen in app
    private Activity activity;

    public GoToNewActivityController(Context context, Activity activity){
        this.context = context;
        this.activity = activity;

    }

    public void goToNewActivity(String activityClassName, boolean finishCurrentActivity){
        //creating new intent to go to new screen
        Intent intent = null;
        try {

            // create intent to go to the new activity
            intent = new Intent(context,Class.forName(activityClassName));


        } catch (ClassNotFoundException e) {
            // print stack trace, if class name for new activity
            // passed in here does not exist.
            e.printStackTrace();
        }

        // start intent to go to the new screen
        context.startActivity(intent);

        if(finishCurrentActivity){
            // finish the current activity
            // back button on Phone will not go to previous screen
            activity.finish();
        }

    }


}
