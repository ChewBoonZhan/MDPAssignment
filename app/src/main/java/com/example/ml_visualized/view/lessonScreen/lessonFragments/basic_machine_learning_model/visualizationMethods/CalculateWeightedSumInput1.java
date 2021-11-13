package com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.visualizationMethods;

import android.view.View;
import android.widget.TextView;

import com.example.ml_visualized.R;
import com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.LessonVisualizationFragment;

public class CalculateWeightedSumInput1 extends VisualizationMethods{
    private ResetTextViewBackgroundColor resetTextViewBackgroundColor = new ResetTextViewBackgroundColor();
    @Override
    public int step(LessonVisualizationFragment lessonVisualizationFragment, String textDescription) {
        resetTextViewBackgroundColor.step(lessonVisualizationFragment, "");

        // get the value of input 1
        int input1 = lessonVisualizationFragment.getModelValueInt("input1");

        // get the weight of input 1
        double weight1 = lessonVisualizationFragment.getModelValueDouble("weight1");

        // get the weightedInput 1
        double weightedInput1 = input1 * weight1;

        // set the weighted input 1
        lessonVisualizationFragment.setModelValueDouble("weightedSumInput1",weightedInput1);

        // get the textview for the weighted input 1
        TextView weightedInput1TextView = lessonVisualizationFragment.getTextView("input1WeightedSum");

        // set the textview to the weighted input 1
        weightedInput1TextView.setText((""+input1 + "*" + weight1  + " = " + weightedInput1));

        weightedInput1TextView.setVisibility(View.VISIBLE);


        // change output text
        lessonVisualizationFragment.changeOutputTextViewContent(textDescription);

        // change affected textview to red color
        weightedInput1TextView.setBackgroundColor(lessonVisualizationFragment.getResources().getColor(R.color.foreground_warn));

        TextView input1TextView = lessonVisualizationFragment.getTextView("input1TextView");
        input1TextView.setBackgroundColor(lessonVisualizationFragment.getResources().getColor(R.color.foreground_warn));

        TextView input1WeightTextView = lessonVisualizationFragment.getTextView("input1WeightTextView");
        input1WeightTextView.setBackgroundColor(lessonVisualizationFragment.getResources().getColor(R.color.foreground_warn));

        return getNO_ACTION();
    }

}