package com.example.ml_visualized.view.homeScreen.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.ml_visualized.R;
import com.example.ml_visualized.controller.simulateButtonTouch.SimulateButtonTouch;

import java.util.ArrayList;

public class HomeLessonFragment extends Fragment {

    // view for home lesson fragment
    private View view;

    // collection of button to access lessons
    private ArrayList<View> buttonLessonCollection;

    // to allow layout to simulate button touch animation
    private SimulateButtonTouch simulateButtonTouch = new SimulateButtonTouch();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.home_lesson_fragment, container, false);

        setLessonOnTouchEffect();

        return view;
    }

    private void setupButtonLessonCollection(){
        buttonLessonCollection= new ArrayList<View>();
        buttonLessonCollection.add(view.findViewById(R.id.button_lesson_1));
        buttonLessonCollection.add(view.findViewById(R.id.button_lesson_2));
        buttonLessonCollection.add(view.findViewById(R.id.button_lesson_3));
        buttonLessonCollection.add(view.findViewById(R.id.button_lesson_4));
    }

    private void setLessonOnTouchEffect(){
        setupButtonLessonCollection();

        int numberOfButtonLessonCollection = buttonLessonCollection.size();
        int counter = 0;
        // for each button in arraylist of button lesson collection
        while (counter < numberOfButtonLessonCollection){
            // add touch listener to change alpha of view
            simulateButtonTouch.simulateButtonOnTouch(buttonLessonCollection.get(counter));

            counter ++;
        }
    }


}
