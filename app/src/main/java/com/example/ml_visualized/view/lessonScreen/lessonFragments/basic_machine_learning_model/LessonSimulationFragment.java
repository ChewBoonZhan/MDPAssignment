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
import com.example.ml_visualized.view.lessonScreen.lessonFragmentParent.LessonScreen1Parent;
import com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.data.LessonSimulationData;
import com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.visualizationMethods.CalculateTotalWeightedSum;
import com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.visualizationMethods.CalculateWeightedSumInput1;
import com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.visualizationMethods.CalculateWeightedSumInput2;
import com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.visualizationMethods.CompareWithThreshold;
import com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.visualizationMethods.ComputeOutput;
import com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.visualizationMethods.VisualizationMethods;

import java.util.ArrayList;
import java.util.HashMap;

public class LessonSimulationFragment extends LessonSimulationVisualizationParentFragment implements LessonScreen1Parent {
    // view for lesson simulation
    private View view;

    // bottom button for blank lesson screen, go button to start simulation
    private Button bottomButton, goButton;

    // type of dataset to be used
    private String datasetType;

    // spinner selector for input 1 and input 2 for simulation
    private Spinner input1Spinner, input2Spinner;


    private final int INITIAL_INPUT1_VALUE = 0, // initial input 1 is 0
            INITIAL_INPUT2_VALUE = 0, // initial input 2 is 0
            ANIM_DELAY = 1000;        // delay before simulation animation is 1000 millisecond

    private int actualOutputValue = 0,  // output value for selected input
            stepIndex = 0;              // step index for simulation


    private TextView actualOutputTextView,  // actual output textview
                                // so user can compare simulation result
            datasetTypeTextView, // type of dataset textview to show to user
            outputDescriptionTextView;  // output text description,
                                        // to show user steps done

    // boolean for if the iteration has been stopped or not.
    // changed to true so simulation stops when user leaves the screen
    private boolean stopIteration = false;

    // to allow spinner to simulate a button touch
    private SimulateButtonTouch simulateButtonTouch = new SimulateButtonTouch();

    // to allow simulation steps
    private ArrayList<VisualizationMethods> simulationSteps = new ArrayList<VisualizationMethods>();

    // string data for lesson simulation to be shown on textiew
    private LessonSimulationData lessonSimulationData;

    // scrollview to scroll to control scroll when user starts simulation
    private ScrollView scrollView;

    /**
     * Initialize arraylist of simulation steps to be carried
     * out when a simulation is runned
     */
    private void initSimulationSteps(){
        simulationSteps.add(new CalculateWeightedSumInput1());
        simulationSteps.add(new CalculateWeightedSumInput2());
        simulationSteps.add(new CalculateTotalWeightedSum());
        simulationSteps.add(new CompareWithThreshold());
        simulationSteps.add(new ComputeOutput());

    }

    /**
     * Initialize Ui model for simulation
     */
    private void initModelForSimulation(){
        // hides unrequired components for simulation
        hideUnrequiredComponents();

        // loads initial values of model for simulation
        // into the UI
        loadInitialValueIntoUI();
    }

    /**
     * hides unrequired components for simulation
     */
    private void hideUnrequiredComponents(){

        getTextView("learningRateTextView").setVisibility(View.INVISIBLE);
    }

    /**
     * load values into model UI
     */
    private void loadInitialValueIntoUI(){
        // set the initial input 1 and 2 in model parameter
        // to the initial value
        setModelValueInt("input1",INITIAL_INPUT1_VALUE);
        setModelValueInt("input2",INITIAL_INPUT2_VALUE);

        // change actual output value on the textview
        changeActualOutputValue();

        // get the weight of the model trained
        // from model parameter
        double weight1 = getModelValueDouble("weight1");
        double weight2 = getModelValueDouble("weight2");

        // get the threshold of the model trained
        // from model parameter
        double THRESHOLD = getModelValueDouble("THRESHOLD");

        // get the learning rate of the model trained
        // from model parameter
        double LEARNING_RATE = getModelValueDouble("LEARNING_RATE");

        // get the textview for input 1
        TextView input1TextView = getTextView("input1TextView");
        // sets the text of input 1 to input 1 initial text
        input1TextView.setText(("" + INITIAL_INPUT1_VALUE));

        // get the textview for input 2
        TextView input2TextView = getTextView("input2TextView");
        // sets the text of input 2 to input 2 initial text
        input2TextView.setText(("" + INITIAL_INPUT2_VALUE));

        // get textview for weight for input 1
        TextView input1WeightTextView = getTextView("input1WeightTextView");

        // sets the text for weight for input 1
        input1WeightTextView.setText(("" + weight1));

        // get textview for weight for input 2
        TextView input2WeightTextView = getTextView("input2WeightTextView");

        // sets the text for weight for input 2
        input2WeightTextView.setText(("" + weight2));

        // get the textview for threshold of the model
        TextView thresholdTextView = getTextView("thresholdTextView");
        // set the text for threshold of the model
        thresholdTextView.setText(("" + THRESHOLD));

        // get the textview for the learning rate of the model
        TextView learningRateTextView = getTextView("learningRateTextView");

        // sets the text for the learning rate of the model
        learningRateTextView.setText(("" + LEARNING_RATE));

    }

    /**
     * Constructor
     * @param integerModelParameter - hashmap that stores model paramter in int
     * @param doubleModelParameter - Hashmap that stores model parameters in double
     * @param bottomButton - Button, the bottom button to allow user to end simulation
     * @param datasetType - String, type of dataset to be used
     * @param scrollView - to control how the scrollview is scrolled
     */
    public LessonSimulationFragment(HashMap<String,Integer> integerModelParameter, HashMap<String,Double> doubleModelParameter, Button bottomButton,String datasetType, ScrollView scrollView){

        // set the model parameter for integer
        setIntegerModelParameter(integerModelParameter);

        // set the model parameter for double
        setDoubleModelParameter(doubleModelParameter);

        // set bottomButton button
        this.bottomButton = bottomButton;

        // set the datatype selected by user
        this.datasetType = datasetType;

        // set the scrollview to allow user to control scrollview
        this.scrollView = scrollView;
    }

    /**
     * setup the bottom button's onClick
     * Ends the simulation,and bring user back to the home screen
     */
    private void setupBottomButton(){
        // set the bottom button text to be End Simulation
        this.bottomButton.setText("End Simulation");

        // setup bottom button onclick listener
        bottomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // create new intent to go to home screen
                Intent intent = new Intent(getActivity(), HomeScreen.class);

                // set flags to clear activity stack
                // this allows user to press back
                // user will not return to the current activity anymore as activity stack is empty
                // guide link: https://stackoverflow.com/questions/3473168/clear-the-entire-history-stack-and-start-a-new-activity-on-android
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                // get activity from activity parent of fragment, and start intent
                getActivity().startActivity(intent);
            }
        });
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.basic_machine_learning_simulation_fragment, container, false);

        // get components from the screen
        getScreenComponents();

        // init go button
        initGoButtonOnClick();

        // initialize simulation steps array
        initSimulationSteps();

        // initialize type of dataset on the textview
        // to show user type of dataset being simulated
        setupDatasetTypeOnTextView();

        // setup the spinner for user to select input
        setupInputSelectorSpinner();

        // get textviewcollection to perform operations on it
        getTextViewCollection(view);

        // setup bottom button to end simulation
        setupBottomButton();

        // initialize model for simulation
        initModelForSimulation();

        // create new instance of lesson simulation data to get string
        // string helps to explain to user simulation on the screen
        lessonSimulationData = new LessonSimulationData(this);

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();

        // stop the async iteration call
        // this stops the simulation if it is ongoing
        // called when user leaves the screen
        stopIteration = true;
    }

    /**
     * Setups the input selector spinner
     * Make spinner simulate a button touch
     * Setup spinner onClick
     */
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

    /**
     * enable/disable button and spinner to allow or disallow user interaction with it
     * @param enable - boolean to show if button and spinner should be enabled for user interaction
     */
    private void enableButtonAndSpinner(boolean enable){
        if(enable){
            // set them to clickable
            goButton.setClickable(true);
            input1Spinner.setClickable(true);
            input2Spinner.setClickable(true);

            // set their background color to normal background color
            goButton.setBackgroundColor(getResources().getColor(R.color.foreground_alt));
            input1Spinner.setBackgroundColor(getResources().getColor(R.color.foreground_alt));
            input2Spinner.setBackgroundColor(getResources().getColor(R.color.foreground_alt));
        }
        else{
            // set them to not clickable
            goButton.setClickable(false);
            input1Spinner.setClickable(false);
            input2Spinner.setClickable(false);

            // set their background color to lighter
            goButton.setBackgroundColor(getResources().getColor(R.color.foreground_alt_light));
            input1Spinner.setBackgroundColor(getResources().getColor(R.color.foreground_alt_light));
            input2Spinner.setBackgroundColor(getResources().getColor(R.color.foreground_alt_light));
        }
    }

    /**
     * Initializes go button onClick listener
     */
    public void initGoButtonOnClick(){
        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // set stopIteration to false
                // this stops any async call as simulation has reached and end
                stopIteration = false;

                // scroll to the bottom of screen for simulation
                scrollView.smoothScrollBy(0, 1000);

                // disable click on go button and spinner
                enableButtonAndSpinner(false);

                //calls the back function to go back to previous screen
                startStep();


            }
        });
    }

    /**
     * Moves to the next step of simulation step
     */
    private void step(){
        // get the text description based on lessonSimulationData
        // describes to user simulation steps going on on screen
        String textDescription = lessonSimulationData.getOutputStringForIndex(stepIndex);

        // perform simulation step
        simulationSteps.get(stepIndex).step(this,textDescription);

        // step index has reached the end of all simulation steps
        // simulation has reached an end
        if(stepIndex == simulationSteps.size()-1){
            // reset index
            stepIndex = 0;

            // stop the iteration
            stopIteration = true;

            // allow click on go button and spinner
            enableButtonAndSpinner(true);
        }
        else{
            // increment step index
            stepIndex++;
        }

    }

    /**
     * start the simulation by calling postdelayed
     * next animation is called after a delay of short duration
     */
    private void startStep(){
        if(!stopIteration){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(!stopIteration){
                        // perform the next step
                        step();

                        // call startstep to start the async delay
                        // before the next step is called
                        startStep();
                    }

                }
            },ANIM_DELAY);
        }



    }

    /**
     * get components from the screen
     */
    private void getScreenComponents(){
        // get the actual output textview
        actualOutputTextView = view.findViewById(R.id.simulation_actual_output_textview);

        // get the textview for input1
        input1Spinner = view.findViewById(R.id.simulation_input1_spinner);
        // get the textview for input2
        input2Spinner = view.findViewById(R.id.simulation_input2_spinner);

        // get the textview for datatype
        datasetTypeTextView = view.findViewById(R.id.simulation_datasetType_textView);

        // get the button for user to click Go
        goButton = view.findViewById(R.id.simulatorGoButton);

        // get the textview for output description
        // describes to user what actions are performed in simulation
        outputDescriptionTextView = view.findViewById(R.id.output_text_view);

        // add textview to collection for model simulation to use it
        // used to change its textview
        addTextViewCollection("outputDescriptionTextView",outputDescriptionTextView);

    }

    /**
     * set datatype textview text
     */
    private void setupDatasetTypeOnTextView(){
        datasetTypeTextView.setText(this.datasetType);
    }

    /**
     * change actual output value based on user selected input value
     */
    private void changeActualOutputValue(){
        // get user input 1 in boolean
        boolean userSelectedInput1ValueBoolean = getModelValueInt("input1") == 1;
        // get user input 2 in boolean
        boolean userSelectedInput2ValueBoolean = getModelValueInt("input2") == 1;

        // get actual output based on dataset type
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


    @Override
    public boolean showNextElement() {
        return true;
    }
}
