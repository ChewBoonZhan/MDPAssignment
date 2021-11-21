package com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.ml_visualized.R;
import com.example.ml_visualized.controller.simulateButtonTouch.SimulateButtonTouch;
import com.example.ml_visualized.view.lessonScreen.lessonFragmentParent.LessonScreen1Parent;
import com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.data.LessonFragmentData;

import java.util.ArrayList;
import java.util.HashMap;

public class LessonFragment2 extends Fragment implements LessonScreen1Parent {

    private View view;

    // spinner for dataset selection
    private Spinner datasetSelectorSpinner;

    // to allow spinner to simulate a button touch
    private SimulateButtonTouch simulateButtonTouch = new SimulateButtonTouch();

    // get data of lesson fragment dataset
    private LessonFragmentData lessonFragmentData = new LessonFragmentData();

    // arraylist textview collection for output
    private ArrayList<TextView> outputTextViewCollection = new ArrayList<TextView>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.basic_machine_learning_fragment2, container, false);

        // get components from screen to setup
        getScreenComponents();

        // setup spinner for user dataset selection
        setupDatasetSelectorSpinner();

        return view;


    }

    /**
     * Setup spinner for user to select dataset
     */
    private void setupDatasetSelectorSpinner(){
        simulateButtonTouch.simulateButtonOnTouch(datasetSelectorSpinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.machine_learning_basic_dataset_array, android.R.layout.simple_spinner_item);

        // Set the layout of the dropdown
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Set adapter values to the spinner
        datasetSelectorSpinner.setAdapter(adapter);

        //set what happens when a category is clicked by user
        datasetSelectorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            // set what happens when an item is selected by user
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                changeTextViewCollectionForDataset(position);

            }

            // set what happens when nothing is selected by user
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // does nothing
            }
        });

    }

    /**
     * Change the textview in table layout based on dataset selected by user in the dropdown
     * @param spinnerSelectedItemIndex - index of dataset selected by user in spinner
     */
    private void changeTextViewCollectionForDataset(int spinnerSelectedItemIndex){
        // hashmap to getdataset selected input and output
        HashMap<String, ArrayList<Integer>> specificDatasetCollection= lessonFragmentData.getSpecificDatasetCollection(spinnerSelectedItemIndex);

        // change output based on user selected dataset
        ArrayList<Integer> outputList = specificDatasetCollection.get("output");
        for(int counter = 0;counter < 4;counter++){
            outputTextViewCollection.get(counter).setText((outputList.get(counter) + ""));
        }

    }

    /**
     * get components from the view screen
     */
    private void getScreenComponents(){
        datasetSelectorSpinner = view.findViewById(R.id.basic_ml_dataset_spinner);

        // add textview into outputTextViewCollection
        outputTextViewCollection.add(view.findViewById(R.id.output_1));
        outputTextViewCollection.add(view.findViewById(R.id.output_2));
        outputTextViewCollection.add(view.findViewById(R.id.output_3));
        outputTextViewCollection.add(view.findViewById(R.id.output_4));

    }

    @Override
    public boolean showNextElement() {
        // returns true as all elements on this screen has been shown
        return true;
    }


}
