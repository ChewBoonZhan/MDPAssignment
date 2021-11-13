package com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.visualizationMethods;

import android.widget.TextView;

import com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.LessonVisualizationFragment;

public class ResetComputeTextViewText extends VisualizationMethods{
    @Override
    public int step(LessonVisualizationFragment lessonVisualizationFragment, String textDescription) {
        TextView weightedSumTextView = lessonVisualizationFragment.getTextView("weightedSumTextView");
        weightedSumTextView.setText("");

        TextView outputTextView = lessonVisualizationFragment.getTextView("outputTextView");
        outputTextView.setText("");

        // change input 1 and 2 to empty string
        TextView input1TextView = lessonVisualizationFragment.getTextView("input1TextView");
        input1TextView.setText("");

        TextView input2TextView = lessonVisualizationFragment.getTextView("input2TextView");
        input2TextView.setText("");

        // change output text to empty string
        lessonVisualizationFragment.changeOutputTextViewContent("");

        return getNO_ACTION();
    }
}
