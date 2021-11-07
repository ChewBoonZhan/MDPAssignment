package com.example.ml_visualized.view.lessonScreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ScrollView;

import com.example.ml_visualized.R;
import com.example.ml_visualized.controller.subScreenActionBar.SubScreenActionBar;
import com.example.ml_visualized.view.lessonScreen.lessonFragmentParent.LessonScreen1Parent;
import com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.LessonLandingFragment;

public class LessonScreen extends AppCompatActivity {

    // to handle action bar
    private SubScreenActionBar subScreenActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lesson_screen);

        // to handle action bar
        subScreenActionBar = new SubScreenActionBar(this);

        setLessonFragment();

    }


    private void setLessonFragment(){
        Bundle extras = getIntent().getExtras();

        String lessonClass = extras.getString("lesson_type");


        switch(lessonClass){
            case "basic_machine_learning_model":

                // setup the ation bar for basic machine learning
                subScreenActionBar.setupActionBar(R.string.basic_model_title);

                // create new fragment for basic machine learning model
                Fragment fragment = new LessonLandingFragment();

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.lesson_screen_frame_layout,fragment).commit();
                break;
        }


    }


    public void lessonNextOnClick(View view){
        // get fragment manager
        FragmentManager fm = getSupportFragmentManager();

        // get fragment from framelayout
        LessonScreen1Parent fragment = (LessonScreen1Parent) fm.findFragmentById(R.id.lesson_screen_frame_layout);

        // call to show next element
        fragment.showNextElement();

    }

}