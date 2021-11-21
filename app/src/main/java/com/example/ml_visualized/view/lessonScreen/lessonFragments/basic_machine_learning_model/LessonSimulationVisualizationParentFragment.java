package com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model;

import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.ml_visualized.R;

import java.util.HashMap;

public class LessonSimulationVisualizationParentFragment extends Fragment {

    // arraylist of model's parameter, in integer
    private HashMap<String,Integer> integerModelParameter = new HashMap<String,Integer>();

    // set arraylist of model's integer paramter
    public void setIntegerModelParameter(HashMap<String,Integer> integerModelParameter){
        this.integerModelParameter = integerModelParameter;
    }

    // get hashmap model parameter integer
    public HashMap<String, Integer> getIntegerModelParameter() {
        return integerModelParameter;
    }

    // arraylist of model's parameter, in double
    private HashMap<String,Double> doubleModelParameter = new HashMap<String,Double>();

    // set arraylist of model's parameter in double
    public void setDoubleModelParameter(HashMap<String,Double> doubleModelParameter){
        this.doubleModelParameter = doubleModelParameter;
    }

    // get hashmap of model's parameter double
    public HashMap<String, Double> getDoubleModelParameter() {
        return doubleModelParameter;
    }

    // arraylist of collection of visualization textviews
    private HashMap<String,TextView> textViewCollection = new HashMap<String, TextView>();

    // getter method for getting model's parameter integer values
    public int getModelValueInt(String valueKey){
        return integerModelParameter.get(valueKey);
    }

    // setter method for getting model's parameter integer values
    public void setModelValueInt(String valueKey, int integerValue){
        integerModelParameter.put(valueKey,integerValue);
    }

    // getter method for getting model's parameter double values
    public double getModelValueDouble(String valueKey){
        return doubleModelParameter.get(valueKey);
    }

    // setter method for getting model's parameter double values
    public void setModelValueDouble(String valueKey, double doubleValue){
        doubleModelParameter.put(valueKey,doubleValue);
    }

    // getter method for getting model's textview for simulation components
    public TextView getTextView(String textViewKey){
        return textViewCollection.get(textViewKey);
    }


    public String getDatasetType(){
        return "";
    }

    // adding new textview to arraylist of textview for model simulation
    public void addTextViewCollection(String textViewKey, TextView textview){
        textViewCollection.put(textViewKey,textview);
    }

    /**
     * Get textview from the view for visualization purposes
     * @param view - view that contains all the textview for simulation
     */
    public void getTextViewCollection(View view){
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

}
