package com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.visualizationMethods;

import android.widget.TextView;

import com.example.ml_visualized.R;
import com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.LessonSimulationVisualizationParentFragment;

public class ResetTextViewBackgroundColor extends VisualizationMethods{
    @Override
    public int step(LessonSimulationVisualizationParentFragment lessonSimulationVisualizationParentFragment, String textDescription) {
        TextView input1TextView = lessonSimulationVisualizationParentFragment.getTextView("input1TextView");
        input1TextView.setBackgroundColor(lessonSimulationVisualizationParentFragment.getResources().getColor(R.color.background_dark_light));

        TextView input2TextView = lessonSimulationVisualizationParentFragment.getTextView("input2TextView");
        input2TextView.setBackgroundColor(lessonSimulationVisualizationParentFragment.getResources().getColor(R.color.background_dark_light));

        TextView outputTextView = lessonSimulationVisualizationParentFragment.getTextView("outputTextView");
        outputTextView.setBackgroundColor(lessonSimulationVisualizationParentFragment.getResources().getColor(R.color.background_dark_light));

        TextView input1WeightTextView = lessonSimulationVisualizationParentFragment.getTextView("input1WeightTextView");
        input1WeightTextView.setBackgroundColor(lessonSimulationVisualizationParentFragment.getResources().getColor(R.color.background_light));

        TextView input2WeightTextView = lessonSimulationVisualizationParentFragment.getTextView("input2WeightTextView");
        input2WeightTextView.setBackgroundColor(lessonSimulationVisualizationParentFragment.getResources().getColor(R.color.background_light));

        TextView weightedSumTextView = lessonSimulationVisualizationParentFragment.getTextView("weightedSumTextView");
        weightedSumTextView.setBackgroundColor(lessonSimulationVisualizationParentFragment.getResources().getColor(R.color.background_light));

        TextView thresholdTextView = lessonSimulationVisualizationParentFragment.getTextView("thresholdTextView");
        thresholdTextView.setBackgroundColor(lessonSimulationVisualizationParentFragment.getResources().getColor(R.color.background_light));

        TextView thresholdComparisionTextView = lessonSimulationVisualizationParentFragment.getTextView("thresholdComparisionTextView");
        thresholdComparisionTextView.setBackgroundColor(lessonSimulationVisualizationParentFragment.getResources().getColor(R.color.background_light));

        TextView actualOutputTextView = lessonSimulationVisualizationParentFragment.getTextView("actualOutputTextView");
        if(actualOutputTextView!= null){
            actualOutputTextView.setBackgroundColor(lessonSimulationVisualizationParentFragment.getResources().getColor(R.color.background_light));
        }


        TextView input1WeightedSum = lessonSimulationVisualizationParentFragment.getTextView("input1WeightedSum");
        input1WeightedSum.setBackgroundColor(lessonSimulationVisualizationParentFragment.getResources().getColor(R.color.background_light));

        TextView input2WeightedSum = lessonSimulationVisualizationParentFragment.getTextView("input2WeightedSum");
        input2WeightedSum.setBackgroundColor(lessonSimulationVisualizationParentFragment.getResources().getColor(R.color.background_light));

        TextView numberOfIterationsTextView = lessonSimulationVisualizationParentFragment.getTextView("numberOfIterationsTextView");
        if(numberOfIterationsTextView != null){
            numberOfIterationsTextView.setBackgroundColor(lessonSimulationVisualizationParentFragment.getResources().getColor(R.color.background_light));
        }

        TextView learningRateTextView = lessonSimulationVisualizationParentFragment.getTextView("learningRateTextView");
        learningRateTextView.setBackgroundColor(lessonSimulationVisualizationParentFragment.getResources().getColor(R.color.background_light));

        return getNO_ACTION();

    }
}
