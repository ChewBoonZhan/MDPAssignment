package com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.visualizationMethods;

import android.widget.TextView;

import com.example.ml_visualized.R;
import com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.LessonVisualizationFragment;

public class ComputeOutput extends VisualizationMethods{
    private ResetTextViewBackgroundColor resetTextViewBackgroundColor = new ResetTextViewBackgroundColor();
    @Override
    public int step(LessonVisualizationFragment lessonVisualizationFragment, String textDescription) {
        resetTextViewBackgroundColor.step(lessonVisualizationFragment, "");

        TextView thresholdComparisionTextView = lessonVisualizationFragment.getTextView("thresholdComparisionTextView");
        thresholdComparisionTextView.setBackgroundColor(lessonVisualizationFragment.getResources().getColor(R.color.foreground_warn));

        double threshold = lessonVisualizationFragment.getModelValueDouble("THRESHOLD");

        double weightedSum = lessonVisualizationFragment.getModelValueDouble("weightedSum");


        TextView outputTextView = lessonVisualizationFragment.getTextView("outputTextView");

        int output;
        String datasetType = lessonVisualizationFragment.getDatasetType();
        if(datasetType.equals("AND") || datasetType.equals("OR")) {
            output = (weightedSum >= threshold)? 1: 0;
        }
        else{
            // dataset for NAND and NOR
            output = (weightedSum >= threshold)? 0: 1;
        }

        outputTextView.setText(("" + output));

        outputTextView.setBackgroundColor(lessonVisualizationFragment.getResources().getColor(R.color.foreground_warn));

        lessonVisualizationFragment.setModelValueInt("outputFromModel",output);

        // change output text to empty string
        lessonVisualizationFragment.changeOutputTextViewContent(textDescription);

        return getNO_ACTION();
    }
}
