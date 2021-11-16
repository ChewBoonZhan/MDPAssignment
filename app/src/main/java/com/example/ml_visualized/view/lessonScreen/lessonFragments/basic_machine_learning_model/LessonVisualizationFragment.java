package com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.ml_visualized.R;
import com.example.ml_visualized.view.lessonScreen.LessonVisualizationScreen;
import com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.data.LessonFragmentData;
import com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.data.LessonVisualizationData;
import com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.visualizationMethods.BackPropagate;
import com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.visualizationMethods.CalculateTotalWeightedSum;
import com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.visualizationMethods.CalculateWeightedSumInput1;
import com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.visualizationMethods.CalculateWeightedSumInput2;
import com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.visualizationMethods.ChangeOutputText;
import com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.visualizationMethods.CompareWithThreshold;
import com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.visualizationMethods.ComputeOutput;
import com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.visualizationMethods.LoadInputOutputIntoModel;
import com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.visualizationMethods.ResetVisualization;
import com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.visualizationMethods.ResetVisualizationIteration;
import com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.visualizationMethods.ShowWeightForInput;
import com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.visualizationMethods.VisualizationMethods;

import java.util.ArrayList;
import java.util.HashMap;

public class LessonVisualizationFragment extends LessonSimulationVisualizationParentFragment {

    private View view;

    private final ChangeOutputText changeOutputText = new ChangeOutputText();
    private final VisualizationMethods visualizationMethods = new VisualizationMethods();
    private final ResetVisualization resetVisualization = new ResetVisualization();
    private final ResetVisualizationIteration resetVisualizationIteration = new ResetVisualizationIteration();

    private LessonFragmentData lessonFragmentData = new LessonFragmentData();

    private LessonVisualizationData lessonVisualizationData;

    private String datasetType;

    private Button autoStepButton, nextStepButton;

    private boolean stepPlaying = false;

    private boolean firstTime = true;

    // combine it into a hashmap and send it into the function
    private ArrayList<Integer> input1Collection = new ArrayList<Integer>();
    private ArrayList<Integer> input2Collection = new ArrayList<Integer>();
    private ArrayList<Integer> outputCollection = new ArrayList<Integer>();


    private ArrayList<VisualizationMethods> visualizationSteps = new ArrayList<VisualizationMethods>();
    private int numberOfVisualizationSteps;

    // model parameters
    private int stepCounter = 0, numberOfCorrectDataset = 0, afterStepCounter = 0, inputCounter = 0;

    private ArrayList<String> afterCompletedIterations = new ArrayList<String>();

    // milliseconds
    private final int DELAY_BEFORE_NEXT_SCROLL = 3000;

    private final double
            THRESHOLD = 0.5,
            LEARNING_RATE = 0.1,
            INITIAL_WEIGHT1 = 0.4,
            INITIAL_WEIGHT2 = 0.4;

    private int numberOfIterations = 0;

    private LessonVisualizationScreen lessonVisualizationScreen;

    private boolean visualizationStarted = false;



    public LessonVisualizationFragment(String datasetType, LessonVisualizationScreen lessonVisualizationScreen){
        super();

        // set the dataset type to visualize
        this.datasetType = datasetType;

        this.lessonVisualizationScreen = lessonVisualizationScreen;

        // set the button to be saved privately
        this.nextStepButton = lessonVisualizationScreen.getNextStepButton();

        this.autoStepButton = lessonVisualizationScreen.getAutoStepButton();

        addTextViewCollection("outputDescriptionTextView",lessonVisualizationScreen.getOutputTextView());

    }

    private void resetModelParameter(){
        stepCounter = 0;
        numberOfCorrectDataset = 0;
        afterStepCounter = 0;
        inputCounter = 0;

        setModelValueDouble("weight1",INITIAL_WEIGHT1);
        setModelValueDouble("weight2",INITIAL_WEIGHT2);

        resetNumberOfIterations();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.basic_machine_learning_visualization_fragment, container, false);

        getTextViewCollection(view);

        loadDataIntoArrayList();

        loadModelParameter();

        lessonVisualizationData = new LessonVisualizationData(this);

        initAfterCompletedIterations();

        setButtonOnClick();

        initSteps();

        // reset visualization
        resetVisualization.step(this, "");

        loadInputOutputIntoHashmap(0);

        // perform a single step
        step();

        return view;


    }

    @Override
    public void onPause() {
        super.onPause();
        stepPlaying = false;

        resetModelParameter();

        resetVisualization.step(this, "");
    }

    private void initSteps(){
        visualizationSteps.add(new ChangeOutputText());
        visualizationSteps.add(new LoadInputOutputIntoModel());
        visualizationSteps.add(new ShowWeightForInput());
        visualizationSteps.add(new ChangeOutputText());
        visualizationSteps.add(new CalculateWeightedSumInput1());
        visualizationSteps.add(new CalculateWeightedSumInput2());
        visualizationSteps.add(new ChangeOutputText());
        visualizationSteps.add(new CalculateTotalWeightedSum());
        visualizationSteps.add(new CompareWithThreshold());
        visualizationSteps.add(new ComputeOutput());
        visualizationSteps.add(new BackPropagate());
        visualizationSteps.add(new ChangeOutputText());
        visualizationSteps.add(new ChangeOutputText());

        numberOfVisualizationSteps = visualizationSteps.size();

    }


   private void setButtonOnClick(){

       nextStepButton.setOnClickListener(new View.OnClickListener() {
           public void onClick(View v)
           {
               visualizationStarted = true;
               step();
           }
       });

       autoStepButton.setOnClickListener(new View.OnClickListener(){
           public void onClick(View v){

               autoStepButtonOnClick();
           }
       });
   }


    private void step(){
        if(stepCounter == (numberOfVisualizationSteps)){
            firstTime = false;

            // reset counter
            stepCounter = 0;

            resetVisualizationIteration.step(this, "");

            // reset to go from beginning of dataset
            if(inputCounter == input1Collection.size()){
                inputCounter = 0;
            }
            else{
                inputCounter++;
            }


            loadInputOutputIntoHashmap(inputCounter);


            // increase number of iterations
            numberOfIterations ++;

            // update the number of iterations textview
            TextView numberOfIterationsTextView = getTextView("numberOfIterationsTextView");
            numberOfIterationsTextView.setText(("" + numberOfIterations));

            Log.d("Thanos","" + inputCounter);

        }
        if(numberOfCorrectDataset == input1Collection.size()){
            if(afterStepCounter <= afterCompletedIterations.size()-1){
                changeOutputText.step(this,afterCompletedIterations.get(afterStepCounter));
                afterStepCounter++;
            }
            else{
                // go to new activity
                Intent intent = lessonVisualizationScreen.goToNewActivity();
                intent.putExtra("integerModelParameter",getIntegerModelParameter());
                intent.putExtra("doubleModelParameter",getDoubleModelParameter());

                lessonVisualizationScreen.startIntentActivity(intent);
            }

        }
        else{
            String visualizeString = lessonVisualizationData.getOutputStringForIndex(stepCounter,firstTime);
            int changeWeightState = visualizationSteps.get(stepCounter).step(this, visualizeString);

            if(changeWeightState == visualizationMethods.getREMAIN_WEIGHT()){
                // change step to +1
                stepCounter++;
                numberOfCorrectDataset++;
            }
            else if (changeWeightState != visualizationMethods.getNO_ACTION()){
                // reset such that the dataset is not learnt yet
                numberOfCorrectDataset = 0;

                // increase or decrease in weight
                setModelValueInt("learningRateChange",changeWeightState);
            }

            stepCounter++;


        }


    }
    private void loadInputOutputIntoHashmap(int index){
        // load new set of inputs
        setModelValueInt("input1",input1Collection.get(index));
        setModelValueInt("input2",input2Collection.get(index));
        setModelValueInt("expectedOutput",outputCollection.get(index));
    }

    private void initAfterCompletedIterations(){
        afterCompletedIterations.add("Now, the model is considered trained.");
        afterCompletedIterations.add("Let's try the model out with some Inputs");
    }


    private void loadModelParameter(){
        HashMap<String,Integer> integerModelParameter = new HashMap<String,Integer>();
        HashMap<String,Double> doubleModelParameter = new HashMap<String,Double>();

        integerModelParameter.put("input1",0);
        integerModelParameter.put("input2",0);
        integerModelParameter.put("expectedOutput",0);
        integerModelParameter.put("outputFromModel",0);

        doubleModelParameter.put("THRESHOLD",THRESHOLD);
        doubleModelParameter.put("LEARNING_RATE",LEARNING_RATE);
        doubleModelParameter.put("weight1",INITIAL_WEIGHT1);
        doubleModelParameter.put("weight2",INITIAL_WEIGHT2);
        doubleModelParameter.put("weightedSum",0.0);
        doubleModelParameter.put("weightedSumInput1",0.0);
        doubleModelParameter.put("weightedSumInput2",0.0);

        // set the integer and double parameter for its parent
        setIntegerModelParameter(integerModelParameter);

        setDoubleModelParameter(doubleModelParameter);

    }



    private void loadDataIntoArrayList(){
        HashMap<String, ArrayList<Integer>> datasetCollection =  lessonFragmentData.getSpecificDatasetCollection(datasetType);
        input1Collection = datasetCollection.get("input1");
        input2Collection = datasetCollection.get("input2");
        outputCollection = datasetCollection.get("output");
    }

    private void autoStep(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(stepPlaying){
                    step();
                    if(afterStepCounter <= afterCompletedIterations.size()-1){
                        autoStep();
                    }
                    else{
                        visualizationStarted = false;

                        // change the textview to stop playing.
                        // change it to ""
                        autoStepButton.setText(R.string.restart_textview_string);

                        enableNextStepButton(true);

                        autoStepButton.setBackgroundColor(getResources().getColor(R.color.foreground_light));

                    }

                }
            }
        },DELAY_BEFORE_NEXT_SCROLL);
    }

    private void resetNumberOfIterations(){
        numberOfIterations = 0;
        setModelValueInt("numberOfIterations",0);
        TextView numberOfIterationsTextView = getTextView("numberOfIterationsTextView");
        numberOfIterationsTextView.setText(("" + 0));
    }

    private void autoStepButtonOnClick(){
        // sets a timer before auto clicking (calling function)
        if(stepPlaying){
            visualizationStarted = false;

            // pause step playing
            stepPlaying = false;


            // set the text to play
            autoStepButton.setText(R.string.play_textview_string);

            autoStepButton.setBackgroundColor(getResources().getColor(R.color.foreground_light));

            resetModelParameter();

            loadInputOutputIntoHashmap(0);

            // reset visualization
            resetVisualization.step(this, "");

            enableNextStepButton(true);

        }
        else{
            if(!visualizationStarted){
                // visualization has just ended
                resetModelParameter();

                loadInputOutputIntoHashmap(0);
            }
            step();

            visualizationStarted = true;

            // play step playing
            stepPlaying = true;

            // set the text to pause
            autoStepButton.setText(R.string.stop_textview_string);

            autoStepButton.setBackgroundColor(getResources().getColor(R.color.foreground_warn));

            enableNextStepButton(false);

            autoStep();
        }
    }
    private void enableNextStepButton(boolean enableButton){
        if(enableButton){
            //enable next button

            // set to be able to click on next step button
            nextStepButton.setClickable(true);
            nextStepButton.setBackgroundColor(getResources().getColor(R.color.background_light));
        }
        else{
            // disable next button
            nextStepButton.setClickable(false);
            nextStepButton.setBackgroundColor(getResources().getColor(R.color.background_light_not_clickable));
        }
    }

    @Override
    public String getDatasetType(){
        return datasetType;
    }

}
