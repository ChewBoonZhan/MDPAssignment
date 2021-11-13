package com.example.ml_visualized.view.lessonScreen;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ml_visualized.R;
import com.example.ml_visualized.controller.subScreenActionBar.SubScreenActionBar;

public class LessonSimulationScreen extends AppCompatActivity {
    // to handle action bar
    private SubScreenActionBar subScreenActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lesson_visualization_screen);

        // to handle action bar
        subScreenActionBar = new SubScreenActionBar(this);




    }
}
