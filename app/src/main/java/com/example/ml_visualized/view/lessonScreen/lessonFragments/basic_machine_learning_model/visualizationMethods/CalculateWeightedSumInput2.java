package com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.visualizationMethods;

import android.view.View;
import android.widget.TextView;

import com.example.ml_visualized.R;
import com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.LessonSimulationVisualizationParentFragment;

public class CalculateWeightedSumInput2 extends VisualizationMethods{
    private ResetTextViewBackgroundColor resetTextViewBackgroundColor = new ResetTextViewBackgroundColor();
    private ChangeOutputText changeOutputText = new ChangeOutputText();
    @Override
    public int step(LessonSimulationVisualizationParentFragment lessonSimulationVisualizationParentFragment, String textDescription) {
        resetTextViewBackgroundColor.step(lessonSimulationVisualizationParentFragment, "");

        // get the value of input 1
        int input2 = lessonSimulationVisualizationParentFragment.getModelValueInt("input2");

        // get the weight of input 1
        double weight2 = lessonSimulationVisualizationParentFragment.getModelValueDouble("weight2");

        // get the weightedInput 1
        double weightedInput2 = input2 * weight2;

        // set the weighted input 1
        lessonSimulationVisualizationParentFragment.setModelValueDouble("weightedSumInput2",weightedInput2);

        // get the textview for the weighted input 1
        TextView weightedInput2TextView = lessonSimulationVisualizationParentFragment.getTextView("input2WeightedSum");

        // set the textview to the weighted input 1
        weightedInput2TextView.setText(("" +input2 + "*" + weight2 + " = "+ weightedInput2));

        weightedInput2TextView.setVisibility(View.VISIBLE);

        // change output text
        changeOutputText.step(lessonSimulationVisualizationParentFragment,textDescription);

        // change affected textview to red color
        weightedInput2TextView.setBackgroundColor(lessonSimulationVisualizationParentFragment.getResources().getColor(R.color.foreground_warn));

        TextView input2TextView = lessonSimulationVisualizationParentFragment.getTextView("input2TextView");
        input2TextView.setBackgroundColor(lessonSimulationVisualizationParentFragment.getResources().getColor(R.color.foreground_warn));

        TextView input2WeightTextView = lessonSimulationVisualizationParentFragment.getTextView("input2WeightTextView");
        input2WeightTextView.setBackgroundColor(lessonSimulationVisualizationParentFragment.getResources().getColor(R.color.foreground_warn));

        return getNO_ACTION();
    }
}
