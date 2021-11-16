package com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model;

import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.ml_visualized.R;

import java.util.HashMap;

public class LessonSimulationVisualizationParentFragment extends Fragment {

    private HashMap<String,Integer> integerModelParameter = new HashMap<String,Integer>();

    public void setIntegerModelParameter(HashMap<String,Integer> integerModelParameter){
        this.integerModelParameter = integerModelParameter;
    }

    public HashMap<String, Integer> getIntegerModelParameter() {
        return integerModelParameter;
    }

    private HashMap<String,Double> doubleModelParameter = new HashMap<String,Double>();

    public void setDoubleModelParameter(HashMap<String,Double> doubleModelParameter){
        this.doubleModelParameter = doubleModelParameter;
    }

    public HashMap<String, Double> getDoubleModelParameter() {
        return doubleModelParameter;
    }

    private HashMap<String,TextView> textViewCollection = new HashMap<String, TextView>();

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


    public TextView getTextView(String textViewKey){
        return textViewCollection.get(textViewKey);
    }


    public String getDatasetType(){
        return "";
    }

    public void addTextViewCollection(String textViewKey, TextView textview){
        textViewCollection.put(textViewKey,textview);
    }

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
