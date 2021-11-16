package com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.visualizationMethods;

import android.view.View;
import android.widget.TextView;

import com.example.ml_visualized.R;
import com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.LessonSimulationVisualizationParentFragment;

public class CalculateWeightedSumInput1 extends VisualizationMethods{
    private ResetTextViewBackgroundColor resetTextViewBackgroundColor = new ResetTextViewBackgroundColor();
    private ChangeOutputText changeOutputText = new ChangeOutputText();
    @Override
    public int step(LessonSimulationVisualizationParentFragment lessonSimulationVisualizationParentFragment, String textDescription) {
        resetTextViewBackgroundColor.step(lessonSimulationVisualizationParentFragment, "");

        // get the value of input 1
        int input1 = lessonSimulationVisualizationParentFragment.getModelValueInt("input1");

        // get the weight of input 1
        double weight1 = lessonSimulationVisualizationParentFragment.getModelValueDouble("weight1");

        // get the weightedInput 1
        double weightedInput1 = input1 * weight1;

        // set the weighted input 1
        lessonSimulationVisualizationParentFragment.setModelValueDouble("weightedSumInput1",weightedInput1);

        // get the textview for the weighted input 1
        TextView weightedInput1TextView = lessonSimulationVisualizationParentFragment.getTextView("input1WeightedSum");

        // set the textview to the weighted input 1
        weightedInput1TextView.setText((""+input1 + "*" + weight1  + " = " + weightedInput1));

        weightedInput1TextView.setVisibility(View.VISIBLE);


        // change output text
        changeOutputText.step(lessonSimulationVisualizationParentFragment,textDescription);

        // change affected textview to red color
        weightedInput1TextView.setBackgroundColor(lessonSimulationVisualizationParentFragment.getResources().getColor(R.color.foreground_warn));

        TextView input1TextView = lessonSimulationVisualizationParentFragment.getTextView("input1TextView");
        input1TextView.setBackgroundColor(lessonSimulationVisualizationParentFragment.getResources().getColor(R.color.foreground_warn));

        TextView input1WeightTextView = lessonSimulationVisualizationParentFragment.getTextView("input1WeightTextView");
        input1WeightTextView.setBackgroundColor(lessonSimulationVisualizationParentFragment.getResources().getColor(R.color.foreground_warn));

        return getNO_ACTION();
    }

}