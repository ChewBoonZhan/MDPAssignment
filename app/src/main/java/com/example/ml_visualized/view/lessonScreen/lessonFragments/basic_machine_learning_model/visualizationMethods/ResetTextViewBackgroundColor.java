package com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.visualizationMethods;

import android.widget.TextView;

import com.example.ml_visualized.R;
import com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.LessonVisualizationFragment;

public class ResetTextViewBackgroundColor extends VisualizationMethods{
    @Override
    public int step(LessonVisualizationFragment lessonVisualizationFragment, String textDescription) {
        TextView input1TextView = lessonVisualizationFragment.getTextView("input1TextView");
        input1TextView.setBackgroundColor(lessonVisualizationFragment.getResources().getColor(R.color.background_dark_light));

        TextView input2TextView = lessonVisualizationFragment.getTextView("input2TextView");
        input2TextView.setBackgroundColor(lessonVisualizationFragment.getResources().getColor(R.color.background_dark_light));

        TextView outputTextView = lessonVisualizationFragment.getTextView("outputTextView");
        outputTextView.setBackgroundColor(lessonVisualizationFragment.getResources().getColor(R.color.background_dark_light));

        TextView input1WeightTextView = lessonVisualizationFragment.getTextView("input1WeightTextView");
        input1WeightTextView.setBackgroundColor(lessonVisualizationFragment.getResources().getColor(R.color.background_light));

        TextView input2WeightTextView = lessonVisualizationFragment.getTextView("input2WeightTextView");
        input2WeightTextView.setBackgroundColor(lessonVisualizationFragment.getResources().getColor(R.color.background_light));

        TextView weightedSumTextView = lessonVisualizationFragment.getTextView("weightedSumTextView");
        weightedSumTextView.setBackgroundColor(lessonVisualizationFragment.getResources().getColor(R.color.background_light));

        TextView thresholdTextView = lessonVisualizationFragment.getTextView("thresholdTextView");
        thresholdTextView.setBackgroundColor(lessonVisualizationFragment.getResources().getColor(R.color.background_light));

        TextView thresholdComparisionTextView = lessonVisualizationFragment.getTextView("thresholdComparisionTextView");
        thresholdComparisionTextView.setBackgroundColor(lessonVisualizationFragment.getResources().getColor(R.color.background_light));

        TextView actualOutputTextView = lessonVisualizationFragment.getTextView("actualOutputTextView");
        actualOutputTextView.setBackgroundColor(lessonVisualizationFragment.getResources().getColor(R.color.background_light));

        TextView input1WeightedSum = lessonVisualizationFragment.getTextView("input1WeightedSum");
        input1WeightedSum.setBackgroundColor(lessonVisualizationFragment.getResources().getColor(R.color.background_light));

        TextView input2WeightedSum = lessonVisualizationFragment.getTextView("input2WeightedSum");
        input2WeightedSum.setBackgroundColor(lessonVisualizationFragment.getResources().getColor(R.color.background_light));

        TextView numberOfIterationsTextView = lessonVisualizationFragment.getTextView("numberOfIterationsTextView");
        numberOfIterationsTextView.setBackgroundColor(lessonVisualizationFragment.getResources().getColor(R.color.background_light));

        TextView learningRateTextView = lessonVisualizationFragment.getTextView("learningRateTextView");
        learningRateTextView.setBackgroundColor(lessonVisualizationFragment.getResources().getColor(R.color.background_light));

        return getNO_ACTION();

    }
}
