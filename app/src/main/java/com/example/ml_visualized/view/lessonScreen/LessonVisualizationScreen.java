package com.example.ml_visualized.view.lessonScreen;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.ml_visualized.R;
import com.example.ml_visualized.controller.subScreenActionBar.SubScreenActionBar;
import com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.LessonVisualizationFragment;

public class LessonVisualizationScreen extends AppCompatActivity {

    // to handle action bar
    private SubScreenActionBar subScreenActionBar;

    // button to control auto visualization, or user clicks next to bring next visualization
    private Button autoStepButton, nextStepButton;

    // textview for showing output and explanation
    private TextView outputTextView;

    private String lessonClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lesson_visualization_screen);

        // to handle action bar
        subScreenActionBar = new SubScreenActionBar(this);

        // get components from the screen
        getScreenComponents();

        // sets the fragment for visualizing components
        setLessonFragment();


    }

    // gets button and textview from screen
    private void getScreenComponents(){
        autoStepButton = findViewById(R.id.auto_step_button);
        nextStepButton = findViewById(R.id.next_step_button);
        outputTextView = findViewById(R.id.output_text_view);
    }

    /**
     * sets the lesson fragment on framelayout for visualization of components
     */
    private void setLessonFragment(){
        Bundle extras = getIntent().getExtras();

        // gets the lessonClass
        lessonClass = extras.getString("lesson_type");

        Fragment fragment = null;

        // switch on lessonClass to set fragment on framelayout
        switch(lessonClass){
            case "basic_machine_learning_visualization":

                // get dataset type from string
                String datasetType = extras.getString("datasetType");

                // setup the action bar for basic machine learning
                subScreenActionBar.setupActionBar(R.string.basic_model_title);

                // create new fragment for basic machine learning model
                fragment = new LessonVisualizationFragment(datasetType,this);

                break;

        }
        // set framelayout to visualization fragment
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.lesson_visualization_frame_layout,fragment).commit();
    }

    /**
     * getter for autostep Button
     * @return button which states auto-step
     */
    public Button getAutoStepButton(){
        return autoStepButton;
    }

    /**
     * getter for next step button
     * @return button - button which states Next step
     */
    public Button getNextStepButton(){
        return nextStepButton;
    }

    /**
     *
     * @return textview - a textview to show description of visualization
     */
    public TextView getOutputTextView(){
        return outputTextView;
    }

    /**
     *
     * @return intent - intent to go to new activity after visualization is completed
     */
    public Intent goToNewActivity(){
        Bundle extras = getIntent().getExtras();

        Intent intent = null;

        // name of class to go to lesson screen
        String activityClassName = getResources().getString(R.string.default_dir) + "view.lessonScreen";

        // string for intent lesson type
        String intentLessonType = "";

        // switch on lesson class to determine which actiity to go to once visualization is complete
        switch (lessonClass){
            case "basic_machine_learning_visualization":
                intentLessonType = "basic_machine_learning_simulation";
                activityClassName +=".LessonBlankScreen";
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
        intent.putExtra("lessonClass",intentLessonType);

        // get dataset type from string
        String datasetType = extras.getString("datasetType");

        // set datatype as extra into intent
        intent.putExtra("datasetType",datasetType);


        return intent;
    }

    /**
     * start intent to go to new activity
     * @param intent
     */
    public void startIntentActivity(Intent intent){
        this.startActivity(intent);
    }
}
