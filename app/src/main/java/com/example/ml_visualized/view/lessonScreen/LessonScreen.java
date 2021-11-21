package com.example.ml_visualized.view.lessonScreen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ScrollView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.ml_visualized.R;
import com.example.ml_visualized.controller.subScreenActionBar.SubScreenActionBar;
import com.example.ml_visualized.view.lessonScreen.lessonFragmentParent.LessonScreen1Parent;
import com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.LessonFragment2;
import com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.LessonLandingFragment;

import java.util.HashMap;

public class LessonScreen extends AppCompatActivity {

    // to handle action bar
    private SubScreenActionBar subScreenActionBar;

    // checks if need to scroll to bottom of screen if user click next
    private boolean onNextScrollToBottomScreen = false;

    // scrollview to control scroll of the scrollview
    private ScrollView lessonScrollView;

    // lessonClass to determine which activity to goto, as well as
    // which fragment to open in this activity
    private String lessonClass;

    // hashmap of extra to be added to intent
    // before we start the intent
    private HashMap<String,String> intentToBeAdded = new HashMap<String,String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lesson_screen);

        // to handle action bar
        subScreenActionBar = new SubScreenActionBar(this);

        // set the fragment for the lesson
        setLessonFragment();

        // get components from the screen
        getScreenComponents();

    }

    /**
     * get components from the screen
     */
    private void getScreenComponents(){
        lessonScrollView = findViewById(R.id.lesson_screen_scroll);
    }

    // set the fragment for the framelayout of this activity
    private void setLessonFragment(){
        Bundle extras = getIntent().getExtras();

        // get lessonClass from extra
        lessonClass = extras.getString("lesson_type");

        Fragment fragment = null;

        // switch on it to determine which fragment to open
        switch(lessonClass){
            case "basic_machine_learning_model":

                onNextScrollToBottomScreen = true;

                // setup the ation bar for basic machine learning
                subScreenActionBar.setupActionBar(R.string.basic_model_title);

                // create new fragment for basic machine learning model
                fragment = new LessonLandingFragment();

                break;
            case "basic_machine_learning_model2":

                onNextScrollToBottomScreen = false;

                // setup the ation bar for basic machine learning
                subScreenActionBar.setupActionBar(R.string.basic_model_title);

                // create new fragment for basic machine learning model
                fragment = new LessonFragment2();

                break;


        }

        // change the framelayout of this activity to the content of the new fragment
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.lesson_screen_frame_layout,fragment).commit();



    }

    /**
     * Set the onClick of the Next button
     * @param view
     */
    public void lessonNextOnClick(View view){
        // get fragment manager
        FragmentManager fm = getSupportFragmentManager();

        // get fragment from framelayout
        LessonScreen1Parent fragment = (LessonScreen1Parent) fm.findFragmentById(R.id.lesson_screen_frame_layout);

        // call to show next element
        boolean allElementFinished = fragment.showNextElement();

        if(allElementFinished){
            // go to a new fragment
            goToNewActivity();
        }
        else{
            // set a timer before scrolling to the bottom of screen
            if(onNextScrollToBottomScreen){
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        lessonScrollView.smoothScrollBy(0, 1000);
                    }
                },50);

            }
        }



    }

    /**
     * Go to a new activity, with some extra in the intent
     */
    private void goToNewActivity(){
        // intent to go to new lesson screen
        Intent intent = null;

        // name of class to go to lesson screen
        String activityClassName = getResources().getString(R.string.default_dir) + "view.lessonScreen";

        // string for intent lesson type
        String intentLessonType = "";

        switch(lessonClass){
            case "basic_machine_learning_model":
                intentLessonType = "basic_machine_learning_model2";
                activityClassName +=".LessonScreen";
                break;
            case "basic_machine_learning_model2":
                intentToBeAdded = new HashMap<String,String>();

                intentLessonType  = "basic_machine_learning_visualization";
                Spinner datasetType = findViewById(R.id.basic_ml_dataset_spinner);
                String datasetSelected = datasetType.getSelectedItem().toString();

                // add intent to the hashmap to set the intent to be added to the new intent
                // before start activity
                addDataToIntentLater("datasetType",datasetSelected);
                activityClassName +=".LessonVisualizationScreen";

                break;
        }

        try {

            // create intent to go to the new activity
            intent = new Intent(this,Class.forName(activityClassName));

        } catch (ClassNotFoundException e) {
            // print stack trace, if class name for new activity
            // passed in here does not exist.
            e.printStackTrace();
        }
        intent = addDataFromHashmapToIntent(intent);
        intent.putExtra("lesson_type",intentLessonType);

        // start intent to go to the new screen
        startActivity(intent);
    }

    /**
     * add extra content to a hashmap to be added to the intent
     * @param intentKey - key of intent
     * @param intentValue - value of the intent, a string
     */
    private void addDataToIntentLater(String intentKey,String intentValue){
        intentToBeAdded.put(intentKey,intentValue);
    }

    /**
     * add data from hashmap that was not added to intent to intent
     * @param intent
     * @return
     */
    private Intent addDataFromHashmapToIntent(Intent intent){
        for ( String intentKey : intentToBeAdded.keySet()){
            intent.putExtra(intentKey,intentToBeAdded.get(intentKey));
        }
        return intent;
    }

}