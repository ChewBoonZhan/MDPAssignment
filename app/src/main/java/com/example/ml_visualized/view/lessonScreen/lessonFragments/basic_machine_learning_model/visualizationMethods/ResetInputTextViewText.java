package com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.visualizationMethods;

import android.widget.TextView;

import com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.LessonVisualizationFragment;

public class ResetInputTextViewText extends VisualizationMethods{
    @Override
    public int step(LessonVisualizationFragment lessonVisualizationFragment, String textDescription) {

        // removing input text
        TextView input1TextView = lessonVisualizationFragment.getTextView("input1TextView");
        input1TextView.setText("");

        TextView input2TextView = lessonVisualizationFragment.getTextView("input2TextView");
        input2TextView.setText("");

        return getNO_ACTION();
    }
}
