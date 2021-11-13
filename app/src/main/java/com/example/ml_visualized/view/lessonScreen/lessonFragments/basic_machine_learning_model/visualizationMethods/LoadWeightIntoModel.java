package com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.visualizationMethods;

import android.widget.TextView;

import com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.LessonVisualizationFragment;

public class LoadWeightIntoModel extends VisualizationMethods{
    @Override
    public int step(LessonVisualizationFragment lessonVisualizationFragment, String textDescription) {
        double weight1 = lessonVisualizationFragment.getModelValueDouble("weight1");
        double weight2 = lessonVisualizationFragment.getModelValueDouble("weight2");

        TextView input1WeightTextView = lessonVisualizationFragment.getTextView("input1WeightTextView");
        TextView input2WeightTextView = lessonVisualizationFragment.getTextView("input2WeightTextView");

        input1WeightTextView.setText(("w = " + weight1));
        input2WeightTextView.setText(("w = " + weight2));

        return getNO_ACTION();
    }
}
