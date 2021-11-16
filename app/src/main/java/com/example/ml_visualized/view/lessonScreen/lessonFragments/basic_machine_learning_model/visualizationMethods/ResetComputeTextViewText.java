package com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.visualizationMethods;

import android.widget.TextView;

import com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.LessonSimulationVisualizationParentFragment;

public class ResetComputeTextViewText extends VisualizationMethods{
    private ChangeOutputText changeOutputText = new ChangeOutputText();
    @Override
    public int step(LessonSimulationVisualizationParentFragment lessonSimulationVisualizationParentFragment, String textDescription) {
        TextView weightedSumTextView = lessonSimulationVisualizationParentFragment.getTextView("weightedSumTextView");
        weightedSumTextView.setText("");

        TextView outputTextView = lessonSimulationVisualizationParentFragment.getTextView("outputTextView");
        outputTextView.setText("");

        // change input 1 and 2 to empty string
        TextView input1TextView = lessonSimulationVisualizationParentFragment.getTextView("input1TextView");
        input1TextView.setText("");

        TextView input2TextView = lessonSimulationVisualizationParentFragment.getTextView("input2TextView");
        input2TextView.setText("");

        TextView actualOutputTextView = lessonSimulationVisualizationParentFragment.getTextView("actualOutputTextView");
        actualOutputTextView.setText("");

        // change output text to empty string
        changeOutputText.step(lessonSimulationVisualizationParentFragment,"");

        return getNO_ACTION();
    }
}
