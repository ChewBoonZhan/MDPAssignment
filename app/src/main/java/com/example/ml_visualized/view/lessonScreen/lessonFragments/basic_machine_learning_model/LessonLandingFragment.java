package com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.ml_visualized.R;
import com.example.ml_visualized.view.lessonScreen.lessonFragmentParent.LessonScreen1Parent;

import java.util.ArrayList;

public class LessonLandingFragment extends Fragment implements LessonScreen1Parent {
    // get view of lesson landihg fragment for basic machine learning
    private View view;

    // array of components that are displayed to user
    private ArrayList<View> displayComponentsCollection = new ArrayList<View>();

    // current index elements are displayed
    private int displayedIndex = 1;

    // number of components to be displayed on screen to user
    private int numberOfDisplayComponents;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.basic_machine_learning_landing_fragment, container, false);

        // get all textview displaying elements to user
        getAllDisplayComponents();

        // hide all textview to user
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
        displayComponentsCollection.add(view.findViewById(R.id.basic_ml_landing_9));
        displayComponentsCollection.add(view.findViewById(R.id.basic_ml_landing_10));

    }

    private void hideAllDisplayComponents(){
        int length = displayComponentsCollection.size();

        // show the first element, hide the rest
        for(int counter = 1;counter < length;counter++){
            displayComponentsCollection.get(counter).setVisibility(View.GONE);
        }

        numberOfDisplayComponents = length;
    }

    /**
     * Called when user clicks on Next
     * Shows next element of textview to user
     * @return boolean - true or false based on if all components has been displayed to user
     */
    @Override
    public boolean showNextElement(){

        if(displayedIndex == numberOfDisplayComponents-1){
            // all elements have been displayed
            return true;
        }
        else{
            // show the element
            displayedIndex++;
            displayComponentsCollection.get(displayedIndex).setVisibility(View.VISIBLE);

            // there are still elements to be displayed.
            return false;

        }


    }


}
