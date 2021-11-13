package com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.visualizationMethods;

import android.widget.TextView;

import com.example.ml_visualized.R;
import com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.LessonVisualizationFragment;

public class NextIteration extends VisualizationMethods{
    @Override
    public int step(LessonVisualizationFragment lessonVisualizationFragment, String textDescription) {
        super.step(lessonVisualizationFragment,"");
        int numberOfIterations = lessonVisualizationFragment.getModelValueInt("numberOfIterations");

        numberOfIterations++;

        TextView numberOfIterationsTextView = lessonVisualizationFragment.getTextView("numberOfIterationsTextView");

        numberOfIterationsTextView.setText(("" + numberOfIterations));

        numberOfIterationsTextView.setBackgroundColor(lessonVisualizationFragment.getResources().getColor(R.color.foreground_warn));

        return getNO_ACTION();
    }
}
