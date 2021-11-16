package com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.visualizationMethods;

import android.view.View;
import android.widget.TextView;

import com.example.ml_visualized.R;
import com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.LessonSimulationVisualizationParentFragment;

public class ShowWeightForInput extends VisualizationMethods{
    private ResetTextViewBackgroundColor resetTextViewBackgroundColor = new ResetTextViewBackgroundColor();
    private ChangeOutputText changeOutputText = new ChangeOutputText();
    @Override
    public int step(LessonSimulationVisualizationParentFragment lessonSimulationVisualizationParentFragment, String textDescription) {
        resetTextViewBackgroundColor.step(lessonSimulationVisualizationParentFragment, "");

        // show weight for input 1 and 2
        TextView input1WeightTextView = lessonSimulationVisualizationParentFragment.getTextView("input1WeightTextView");
        input1WeightTextView.setVisibility(View.VISIBLE);

        input1WeightTextView.setBackgroundColor(lessonSimulationVisualizationParentFragment.getResources().getColor(R.color.foreground_warn));

        TextView input2WeightTextView = lessonSimulationVisualizationParentFragment.getTextView("input2WeightTextView");
        input2WeightTextView.setVisibility(View.VISIBLE);

        input2WeightTextView.setBackgroundColor(lessonSimulationVisualizationParentFragment.getResources().getColor(R.color.foreground_warn));

        // change output text
        changeOutputText.step(lessonSimulationVisualizationParentFragment,textDescription);

        double weight1 = lessonSimulationVisualizationParentFragment.getModelValueDouble("weight1");
        double weight2 = lessonSimulationVisualizationParentFragment.getModelValueDouble("weight2");

        return getNO_ACTION();

    }
}
