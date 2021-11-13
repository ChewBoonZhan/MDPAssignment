package com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.ml_visualized.R;
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

public class LessonVisualizationFragment extends Fragment {



    private View view;

    private final ChangeOutputText changeOutputText = new ChangeOutputText();
    private final VisualizationMethods visualizationMethods = new VisualizationMethods();
    private final ResetVisualization resetVisualization = new ResetVisualization();
    private final ResetVisualizationIteration resetVisualizationIteration = new ResetVisualizationIteration();


    private HashMap<String,TextView> textViewCollection = new HashMap<String, TextView>();


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

    private HashMap<String,Integer> integerModelParameter = new HashMap<String,Integer>();
    private HashMap<String,Double> doubleModelParameter = new HashMap<String,Double>();


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



    public LessonVisualizationFragment(String datasetType,Button autoStepButton,Button nextStepButton,TextView outputTextView){
        super();

        // set the dataset type to visualize
        this.datasetType = datasetType;

        // set the button to be saved privately
        this.nextStepButton = nextStepButton;

        this.autoStepButton = autoStepButton;
        textViewCollection.put("outputDescriptionTextView",outputTextView);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.basic_machine_learning_visualization_fragment, container, false);

        getScreenComponents();

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

            loadInputOutputIntoHashmap(inputCounter);

            inputCounter++;
            // reset visualization for next iteration

        }
        if(numberOfCorrectDataset == input1Collection.size()){
            if(afterStepCounter < afterCompletedIterations.size()-1){
                changeOutputText.step(this,afterCompletedIterations.get(afterStepCounter));
                afterStepCounter++;
            }
            else{

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
        integerModelParameter.put("input1",input1Collection.get(index));
        integerModelParameter.put("input2",input2Collection.get(index));
        integerModelParameter.put("expectedOutput",outputCollection.get(index));
    }

    private void initAfterCompletedIterations(){
        afterCompletedIterations.add("Now, the model is considered trained.");
        afterCompletedIterations.add("Let's try the model out with some Inputs");
    }

    private void getScreenComponents(){
        textViewCollection.put("input1TextView",view.findViewById(R.id.input1_textview));
        textViewCollection.put("input2TextView",view.findViewById(R.id.input2_textview));
        textViewCollection.put("input1WeightedSum",view.findViewById(R.id.input1_weighted_sum_textview));
        textViewCollection.put("input2WeightedSum",view.findViewById(R.id.input2_weighted_sum_textview));
        textViewCollection.put("outputTextView",view.findViewById(R.id.output_textview));
        textViewCollection.put("input1WeightTextView",view.findViewById(R.id.input_1_weight_textview));
        textViewCollection.put("input2WeightTextView",view.findViewById(R.id.input_2_weight_textview));
        textViewCollection.put("weightedSumTextView",view.findViewById(R.id.weighted_sum_textview));
        textViewCollection.put("thresholdTextView",view.findViewById(R.id.threshold_textview));
        textViewCollection.put("thresholdComparisionTextView",view.findViewById(R.id.threshold_constraint_comparision_textview));
        textViewCollection.put("actualOutputTextView",view.findViewById(R.id.output_comparision_textview));
        textViewCollection.put("numberOfIterationsTextView",view.findViewById(R.id.number_of_iteration_textview));
        textViewCollection.put("learningRateTextView",view.findViewById(R.id.learning_rate_textview));



    }

    private void loadModelParameter(){
        integerModelParameter.put("numberOfIterations",0);
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

    }

    public TextView getTextView(String textViewKey){
        return textViewCollection.get(textViewKey);
    }

    public int getModelValueInt(String valueKey){
        return integerModelParameter.get(valueKey);
    }

    public void setModelValueInt(String valueKey, int integerValue){
        integerModelParameter.put(valueKey,integerValue);
    }

    public double getModelValueDouble(String valueKey){
        return doubleModelParameter.get(valueKey);
    }

    public void setModelValueDouble(String valueKey, double doubleValue){
        doubleModelParameter.put(valueKey,doubleValue);
    }

    public void changeOutputTextViewContent(String content){
        changeOutputText.step(this,content);
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
                    if(afterStepCounter < afterCompletedIterations.size()-1){
                        autoStep();
                        Log.d("thanos","Check if I'm still called at the end");
                    }
                    else{
                        // change the textview to stop playing.
                        // change it to ""
                        autoStepButton.setText(R.string.restart_textview_string);

                        autoStepButton.setBackgroundColor(getResources().getColor(R.color.foreground_light));
                    }

                }
            }
        },DELAY_BEFORE_NEXT_SCROLL);
    }

    private void autoStepButtonOnClick(){
        // sets a timer before auto clicking (calling function)
        if(stepPlaying){
            // pause step playing
            stepPlaying = false;


            // set the text to play
            autoStepButton.setText(R.string.play_textview_string);

            autoStepButton.setBackgroundColor(getResources().getColor(R.color.foreground_light));

            // reset visualization
            resetVisualizationIteration.step(this, "");


        }
        else{
            // play step playing
            stepPlaying = true;

            // set the text to pause
            autoStepButton.setText(R.string.stop_textview_string);

            autoStepButton.setBackgroundColor(getResources().getColor(R.color.foreground_warn));

            autoStep();
        }
    }

    public String getDatasetType(){
        return datasetType;
    }

}
