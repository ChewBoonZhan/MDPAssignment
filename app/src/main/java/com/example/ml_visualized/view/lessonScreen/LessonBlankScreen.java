package com.example.ml_visualized.view.lessonScreen;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ScrollView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.ml_visualized.R;
import com.example.ml_visualized.controller.subScreenActionBar.SubScreenActionBar;
import com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.LessonSimulationFragment;

import java.util.HashMap;

public class LessonBlankScreen extends AppCompatActivity {
    // to handle action bar
    private SubScreenActionBar subScreenActionBar;

    private Button bottomButton;

    private String lessonClass;

    private ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lesson_blank_screen);

        // to handle action bar
        subScreenActionBar = new SubScreenActionBar(this);

        getScreenComponents();

        setLessonFragment();


    }
    private void getScreenComponents(){
        bottomButton = findViewById(R.id.blank_screen_bottom_button);
        scrollView = findViewById(R.id.lesson_blank_screen_scrollView);
    }
    private void setLessonFragment(){
        Bundle extras = getIntent().getExtras();

        lessonClass = extras.getString("lessonClass");

        Fragment fragment = null;


        switch(lessonClass){
            case "basic_machine_learning_simulation":

                HashMap<String,Integer> integerModelParameter = new HashMap<String,Integer>();

                HashMap<String,Double> doubleModelParameter = new HashMap<String,Double>();

                integerModelParameter = (HashMap<String,Integer>) extras.getSerializable("integerModelParameter");

                doubleModelParameter = (HashMap<String,Double>) extras.getSerializable("doubleModelParameter");

                String datasetType = extras.getString("datasetType");

                // setup the action bar for basic machine learning
                subScreenActionBar.setupActionBar(R.string.basic_model_title);

                // create new fragment for basic machine learning model
                fragment = new LessonSimulationFragment(integerModelParameter,doubleModelParameter,bottomButton,datasetType,scrollView);

                break;

        }
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.lesson_simulation_framelayout,fragment).commit();
    }

}
