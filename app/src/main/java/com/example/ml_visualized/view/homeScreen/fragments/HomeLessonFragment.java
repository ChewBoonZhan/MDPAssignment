package com.example.ml_visualized.view.homeScreen.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.ml_visualized.R;

import java.util.ArrayList;

public class HomeLessonFragment extends Fragment {

    // view for home lesson fragment
    private View view;

    // collection of button to access lessons
    private ArrayList<View> buttonLessonCollection;

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
            buttonLessonCollection.get(counter).setOnTouchListener(new View.OnTouchListener() {
                // override the onTouch method to change alpha
                @Override
                public boolean onTouch(View view, MotionEvent event) {
                    switch (event.getAction()) {

                        // if user is touching on the button
                        case MotionEvent.ACTION_DOWN: {

                            // set alpha to half
                            view.setAlpha(0.5f);
                            break;
                        }

                        // if user cancelled touching action or ahs lifted touch
                        case MotionEvent.ACTION_CANCEL:
                        case MotionEvent.ACTION_UP: {

                            // set alpha back to default value
                            view.setAlpha(1f);
                            break;
                        }
                    }

                    return false;
                }
            });

            counter ++;
        }
    }


}
