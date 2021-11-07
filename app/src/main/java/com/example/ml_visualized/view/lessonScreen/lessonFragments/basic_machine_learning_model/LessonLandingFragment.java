package com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.ml_visualized.R;
import com.example.ml_visualized.view.lessonScreen.lessonFragmentParent.LessonScreen1Parent;

import java.util.ArrayList;

public class LessonLandingFragment extends Fragment implements LessonScreen1Parent {
    // get view of lesson landihg fragment for basic machine learning
    private View view;

    private ArrayList<View> displayComponentsCollection = new ArrayList<View>();

    private int displayedIndex = 1;

    private int numberOfDisplayComponents;

    private ScrollView lessonScrollView;

    //https://stackoverflow.com/questions/6831671/is-there-a-way-to-programmatically-scroll-a-scroll-view-to-a-specific-edit-text

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.basic_machine_learning_landing_fragment, container, false);

        getAllDisplayComponents();

        hideAllDisplayComponents();

        return view;
    }

    private void getAllDisplayComponents(){
        displayComponentsCollection.add(view.findViewById(R.id.basic_ml_landing_1));
        displayComponentsCollection.add(view.findViewById(R.id.basic_ml_landing_2));
        displayComponentsCollection.add(view.findViewById(R.id.basic_ml_landing_3));
        displayComponentsCollection.add(view.findViewById(R.id.basic_ml_landing_4));
        displayComponentsCollection.add(view.findViewById(R.id.basic_ml_landing_5));
        displayComponentsCollection.add(view.findViewById(R.id.basic_ml_landing_6));
        displayComponentsCollection.add(view.findViewById(R.id.basic_ml_landing_7));
        displayComponentsCollection.add(view.findViewById(R.id.basic_ml_landing_8));

        // get scrollview
        lessonScrollView = view.findViewById(R.id.lesson_screen_scroll);

    }

    private void hideAllDisplayComponents(){
        int length = displayComponentsCollection.size();

        // show the first element, hide the rest
        for(int counter = 1;counter < length;counter++){
            displayComponentsCollection.get(counter).setVisibility(View.GONE);
        }

        numberOfDisplayComponents = length;
    }

    public void showNextElement(){


        if(displayedIndex == numberOfDisplayComponents-1){
            // all elements have been displayed
        }
        else{
            // show the element
            displayedIndex++;
            displayComponentsCollection.get(displayedIndex).setVisibility(View.VISIBLE);
        }


        // add scroll
        // scroll to bottom of scrollview
        lessonScrollView.scrollTo(100,-100);

    }


}
