package com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.visualizationMethods;

import android.widget.TextView;

import com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.LessonSimulationVisualizationParentFragment;

public class ChangeOutputText extends VisualizationMethods{

    @Override
    public int step(LessonSimulationVisualizationParentFragment lessonSimulationVisualizationParentFragment, String textDescription) {

        TextView detailsTextView = lessonSimulationVisualizationParentFragment.getTextView("outputDescriptionTextView");
        detailsTextView.setText(textDescription);

        return getNO_ACTION();
    }

}
