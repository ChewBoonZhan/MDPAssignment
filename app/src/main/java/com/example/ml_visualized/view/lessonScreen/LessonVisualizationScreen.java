package com.example.ml_visualized.view.lessonScreen;

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

    private Button autoStepButton, nextStepButton;

    private TextView outputTextView;

    private String lessonClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lesson_visualization_screen);

        // to handle action bar
        subScreenActionBar = new SubScreenActionBar(this);

        getScreenComponents();

        setLessonFragment();


    }

    private void getScreenComponents(){
        autoStepButton = findViewById(R.id.auto_step_button);
        nextStepButton = findViewById(R.id.next_step_button);
        outputTextView = findViewById(R.id.output_text_view);
    }

    private void setLessonFragment(){
        Bundle extras = getIntent().getExtras();

        lessonClass = extras.getString("lesson_type");

        Fragment fragment = null;


        switch(lessonClass){
            case "basic_machine_learning_visualization":

                // get dataset type from string
                String datasetType = extras.getString("datasetType");

                // setup the action bar for basic machine learning
                subScreenActionBar.setupActionBar(R.string.basic_model_title);

                // create new fragment for basic machine learning model
                fragment = new LessonVisualizationFragment(datasetType,autoStepButton,nextStepButton,outputTextView);

                break;

        }
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.lesson_visualization_frame_layout,fragment).commit();
    }
}
