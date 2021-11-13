package com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.visualizationMethods;

import android.view.View;
import android.widget.TextView;

import com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.LessonVisualizationFragment;

public class HideCalculationTextView extends VisualizationMethods{
    @Override
    public int step(LessonVisualizationFragment lessonVisualizationFragment, String textDescription) {
        TextView input1WeightedSum= lessonVisualizationFragment.getTextView("input1WeightedSum");
        input1WeightedSum.setVisibility(View.INVISIBLE);

        TextView input2WeightedSum = lessonVisualizationFragment.getTextView("input2WeightedSum");
        input2WeightedSum.setVisibility(View.INVISIBLE);

        TextView thresholdComparisionTextView = lessonVisualizationFragment.getTextView("thresholdComparisionTextView");
        thresholdComparisionTextView.setVisibility(View.INVISIBLE);

        // change output text to empty string
        lessonVisualizationFragment.changeOutputTextViewContent("");

        return getNO_ACTION();

    }
}
