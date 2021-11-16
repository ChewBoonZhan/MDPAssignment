package com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.visualizationMethods;

import android.widget.TextView;

import com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.LessonSimulationVisualizationParentFragment;

public class LoadWeightIntoModel extends VisualizationMethods{
    @Override
    public int step(LessonSimulationVisualizationParentFragment lessonSimulationVisualizationParentFragment, String textDescription) {
        double weight1 = lessonSimulationVisualizationParentFragment.getModelValueDouble("weight1");
        double weight2 = lessonSimulationVisualizationParentFragment.getModelValueDouble("weight2");


        TextView input1WeightTextView = lessonSimulationVisualizationParentFragment.getTextView("input1WeightTextView");
        TextView input2WeightTextView = lessonSimulationVisualizationParentFragment.getTextView("input2WeightTextView");

        input1WeightTextView.setText(("w = " + weight1));
        input2WeightTextView.setText(("w = " + weight2));

        return getNO_ACTION();
    }
}
