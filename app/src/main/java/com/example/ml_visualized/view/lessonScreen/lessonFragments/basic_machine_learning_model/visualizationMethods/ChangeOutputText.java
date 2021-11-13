package com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.visualizationMethods;

import android.widget.TextView;

import com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.LessonVisualizationFragment;

public class ChangeOutputText extends VisualizationMethods{

    @Override
    public int step(LessonVisualizationFragment lessonVisualizationFragment, String textDescription) {

        TextView detailsTextView = lessonVisualizationFragment.getTextView("outputDescriptionTextView");
        detailsTextView.setText(textDescription);

        return getNO_ACTION();
    }

}
