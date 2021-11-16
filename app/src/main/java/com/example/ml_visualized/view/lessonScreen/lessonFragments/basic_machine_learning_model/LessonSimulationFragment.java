package com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.ml_visualized.R;
import com.example.ml_visualized.controller.simulateButtonTouch.SimulateButtonTouch;
import com.example.ml_visualized.view.homeScreen.HomeScreen;
import com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.data.LessonSimulationData;
import com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.visualizationMethods.CalculateTotalWeightedSum;
import com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.visualizationMethods.CalculateWeightedSumInput1;
import com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.visualizationMethods.CalculateWeightedSumInput2;
import com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.visualizationMethods.CompareWithThreshold;
import com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.visualizationMethods.ComputeOutput;
import com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.visualizationMethods.VisualizationMethods;

import java.util.ArrayList;
import java.util.HashMap;

public class LessonSimulationFragment extends LessonSimulationVisualizationParentFragment {
    private View view;

    private Button bottomButton, goButton;

    private String datasetType;

    private Spinner input1Spinner, input2Spinner;

    private final int INITIAL_INPUT1_VALUE = 0,INITIAL_INPUT2_VALUE = 0, ANIM_DELAY = 1000;

    private int actualOutputValue = 0, stepIndex = 0;

    private TextView actualOutputTextView, datasetTypeTextView, outputDescriptionTextView;


    private boolean stopIteration = false;

    // to allow spinner to simulate a button touch
    private SimulateButtonTouch simulateButtonTouch = new SimulateButtonTouch();

    // to allow simulation steps
    private ArrayList<VisualizationMethods> simulationSteps = new ArrayList<VisualizationMethods>();

    private LessonSimulationData lessonSimulationData;

    private ScrollView scrollView;

    private void initSimulationSteps(){
        simulationSteps.add(new CalculateWeightedSumInput1());
        simulationSteps.add(new CalculateWeightedSumInput2());
        simulationSteps.add(new CalculateTotalWeightedSum());
        simulationSteps.add(new CompareWithThreshold());
        simulationSteps.add(new ComputeOutput());

    }

    private void initModelForSimulation(){
        hideUnrequiredComponents();

        loadInitialValueIntoUI();
    }

    private void hideUnrequiredComponents(){

        getTextView("learningRateTextView").setVisibility(View.INVISIBLE);
    }

    // load values into model UI
    private void loadInitialValueIntoUI(){

        setModelValueInt("input1",INITIAL_INPUT1_VALUE);
        setModelValueInt("input2",INITIAL_INPUT2_VALUE);

        // change actual output value on the textview
        changeActualOutputValue();

        double weight1 = getModelValueDouble("weight1");
        double weight2 = getModelValueDouble("weight2");
        double THRESHOLD = getModelValueDouble("THRESHOLD");
        double LEARNING_RATE = getModelValueDouble("LEARNING_RATE");

        TextView input1TextView = getTextView("input1TextView");
        input1TextView.setText(("" + INITIAL_INPUT1_VALUE));

        TextView input2TextView = getTextView("input2TextView");
        input2TextView.setText(("" + INITIAL_INPUT2_VALUE));

        TextView input1WeightTextView = getTextView("input1WeightTextView");
        input1WeightTextView.setText(("" + weight1));

        TextView input2WeightTextView = getTextView("input2WeightTextView");
        input2WeightTextView.setText(("" + weight2));

        TextView thresholdTextView = getTextView("thresholdTextView");
        thresholdTextView.setText(("" + THRESHOLD));

        TextView learningRateTextView = getTextView("learningRateTextView");
        learningRateTextView.setText(("" + LEARNING_RATE));

    }

    public LessonSimulationFragment(HashMap<String,Integer> integerModelParameter, HashMap<String,Double> doubleModelParameter, Button bottomButton,String datasetType, ScrollView scrollView){

        setIntegerModelParameter(integerModelParameter);

        setDoubleModelParameter(doubleModelParameter);

        this.bottomButton = bottomButton;

        this.datasetType = datasetType;

        this.scrollView = scrollView;
    }


    private void setupBottomButton(){
        this.bottomButton.setText("End Simulation");
        bottomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HomeScreen.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                getActivity().startActivity(intent);
            }
        });
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.basic_machine_learning_simulation_fragment, container, false);

        getScreenComponents();

        initGoButtonOnClick();

        initSimulationSteps();

        setupDatasetTypeOnTextView();

        setupInputSelectorSpinner();

        getTextViewCollection(view);

        setupBottomButton();

        initModelForSimulation();

        lessonSimulationData = new LessonSimulationData(this);

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();

        // stop the async iteration call
        stopIteration = true;
    }

    private void setupInputSelectorSpinner(){
        //simulate spinner to a button touch
        simulateButtonTouch.simulateButtonOnTouch(input1Spinner);
        simulateButtonTouch.simulateButtonOnTouch(input2Spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.machine_learning_basic_dataset_data, android.R.layout.simple_spinner_item);

        // Set the layout of the dropdown
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Set adapter values to the input 1 spinner
        input1Spinner.setAdapter(adapter);

        //set what happens when a input 1 category is clicked by user
        input1Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            // set what happens when an item is selected by user
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                setModelValueInt("input1",position);

                // set the string in textview for input 1
                TextView input1TextView = getTextView("input1TextView");
                input1TextView.setText(("" + position));

                // change the actual output value
                changeActualOutputValue();

            }

            // set what happens when nothing is selected by user
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // does nothing
            }
        });

        // Set adapter values to the input 2 spinner
        input2Spinner.setAdapter(adapter);

        //set what happens when a input 2 category is clicked by user
        input2Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            // set what happens when an item is selected by user
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                setModelValueInt("input2",position);

                // set the string in textview for input 2
                TextView input2TextView = getTextView("input2TextView");
                input2TextView.setText(("" + position));

                changeActualOutputValue();

            }

            // set what happens when nothing is selected by user
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // does nothing
            }
        });

    }

    private void enableButtonAndSpinner(boolean enable){
        if(enable){
            // set them to clickable
            goButton.setClickable(true);
            input1Spinner.setClickable(true);
            input2Spinner.setClickable(true);

            // set their background color to normal background color
            goButton.setBackgroundColor(getResources().getColor(R.color.background_light));
            input1Spinner.setBackgroundColor(getResources().getColor(R.color.background_light));
            input2Spinner.setBackgroundColor(getResources().getColor(R.color.background_light));
        }
        else{
            // set them to not clickable
            goButton.setClickable(false);
            input1Spinner.setClickable(false);
            input2Spinner.setClickable(false);

            // set their background color to lighter
            goButton.setBackgroundColor(getResources().getColor(R.color.background_light_not_clickable));
            input1Spinner.setBackgroundColor(getResources().getColor(R.color.background_light_not_clickable));
            input2Spinner.setBackgroundColor(getResources().getColor(R.color.background_light_not_clickable));
        }
    }

    public void initGoButtonOnClick(){
        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                stopIteration = false;

                // scroll to the bottom of screen for simulation
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        scrollView.smoothScrollBy(0, 1000);
                    }
                },50);

                // disable click on go button and spinner
                enableButtonAndSpinner(false);

                //calls the back function to go back to previous screen
                startStep();


            }
        });
    }

    private void step(){
        String textDescription = lessonSimulationData.getOutputStringForIndex(stepIndex);
        simulationSteps.get(stepIndex).step(this,textDescription);
        if(stepIndex == simulationSteps.size()-1){
            // reset index
            stepIndex = 0;

            // stop the iteration
            stopIteration = true;

            // allow click on go button and spinner
            enableButtonAndSpinner(true);
        }
        else{
            stepIndex++;
        }

    }

    private void startStep(){
        if(!stopIteration){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(!stopIteration){
                        step();
                        startStep();
                    }

                }
            },ANIM_DELAY);
        }



    }

    private void getScreenComponents(){
        actualOutputTextView = view.findViewById(R.id.simulation_actual_output_textview);

        input1Spinner = view.findViewById(R.id.simulation_input1_spinner);
        input2Spinner = view.findViewById(R.id.simulation_input2_spinner);

        datasetTypeTextView = view.findViewById(R.id.simulation_datasetType_textView);

        goButton = view.findViewById(R.id.simulatorGoButton);

        outputDescriptionTextView = view.findViewById(R.id.output_text_view);
        addTextViewCollection("outputDescriptionTextView",outputDescriptionTextView);

    }

    private void setupDatasetTypeOnTextView(){
        datasetTypeTextView.setText(this.datasetType);
    }

    private void changeActualOutputValue(){
        boolean userSelectedInput1ValueBoolean = getModelValueInt("input1") == 1;
        boolean userSelectedInput2ValueBoolean = getModelValueInt("input2") == 1;

        switch(this.datasetType){
            case "AND":
                actualOutputValue = (userSelectedInput1ValueBoolean && userSelectedInput2ValueBoolean)?
                        1: 0;
                break;
            case "NAND":
                actualOutputValue = (!(userSelectedInput1ValueBoolean && userSelectedInput2ValueBoolean))?
                        1: 0;
                break;
            case "OR":
                actualOutputValue = (userSelectedInput1ValueBoolean || userSelectedInput2ValueBoolean)?
                        1: 0;
                break;
            case "NOR":
                actualOutputValue = (!(userSelectedInput1ValueBoolean || userSelectedInput2ValueBoolean))?
                        1: 0;
                break;
        }

        // set the text for actual output
        actualOutputTextView.setText(("" + actualOutputValue));

        // set the value of input1, input2 and output into parent for processing
        setModelValueInt("expectedOutput",actualOutputValue);


    }

    public String getDatasetType(){
        return this.datasetType;
    }


}
