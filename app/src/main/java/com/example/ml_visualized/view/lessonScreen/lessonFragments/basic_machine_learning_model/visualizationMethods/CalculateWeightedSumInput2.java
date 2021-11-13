package com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.visualizationMethods;

import android.view.View;
import android.widget.TextView;

import com.example.ml_visualized.R;
import com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.LessonVisualizationFragment;

public class CalculateWeightedSumInput2 extends VisualizationMethods{
    private ResetTextViewBackgroundColor resetTextViewBackgroundColor = new ResetTextViewBackgroundColor();
    @Override
    public int step(LessonVisualizationFragment lessonVisualizationFragment, String textDescription) {
        resetTextViewBackgroundColor.step(lessonVisualizationFragment, "");

        super.step(lessonVisualizationFragment,"");
        // get the value of input 1
        int input2 = lessonVisualizationFragment.getModelValueInt("input2");

        // get the weight of input 1
        double weight2 = lessonVisualizationFragment.getModelValueDouble("weight2");

        // get the weightedInput 1
        double weightedInput2 = input2 * weight2;

        // set the weighted input 1
        lessonVisualizationFragment.setModelValueDouble("weightedSumInput2",weightedInput2);

        // get the textview for the weighted input 1
        TextView weightedInput2TextView = lessonVisualizationFragment.getTextView("input2WeightedSum");

        // set the textview to the weighted input 1
        weightedInput2TextView.setText(("" +input2 + "*" + weight2 + " = "+ weightedInput2));

        weightedInput2TextView.setVisibility(View.VISIBLE);

        // change output text
        lessonVisualizationFragment.changeOutputTextViewContent(textDescription);

        // change affected textview to red color
        weightedInput2TextView.setBackgroundColor(lessonVisualizationFragment.getResources().getColor(R.color.foreground_warn));

        TextView input2TextView = lessonVisualizationFragment.getTextView("input2TextView");
        input2TextView.setBackgroundColor(lessonVisualizationFragment.getResources().getColor(R.color.foreground_warn));

        TextView input2WeightTextView = lessonVisualizationFragment.getTextView("input2WeightTextView");
        input2WeightTextView.setBackgroundColor(lessonVisualizationFragment.getResources().getColor(R.color.foreground_warn));

        return getNO_ACTION();
    }
}
