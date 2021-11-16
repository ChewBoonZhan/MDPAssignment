package com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.visualizationMethods;

import android.widget.TextView;

import com.example.ml_visualized.R;
import com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.LessonSimulationVisualizationParentFragment;

public class NextIteration extends VisualizationMethods{
    @Override
    public int step(LessonSimulationVisualizationParentFragment lessonSimulationVisualizationParentFragment, String textDescription) {

        int numberOfIterations = lessonSimulationVisualizationParentFragment.getModelValueInt("numberOfIterations");

        numberOfIterations++;

        TextView numberOfIterationsTextView = lessonSimulationVisualizationParentFragment.getTextView("numberOfIterationsTextView");

        numberOfIterationsTextView.setText(("" + numberOfIterations));

        numberOfIterationsTextView.setBackgroundColor(lessonSimulationVisualizationParentFragment.getResources().getColor(R.color.foreground_warn));

        return getNO_ACTION();
    }
}
