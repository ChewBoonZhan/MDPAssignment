package com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.visualizationMethods;

import android.widget.TextView;

import com.example.ml_visualized.R;
import com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.LessonSimulationVisualizationParentFragment;

public class ComputeOutput extends VisualizationMethods{
    private ChangeOutputText changeOutputText = new ChangeOutputText();
    private ResetTextViewBackgroundColor resetTextViewBackgroundColor = new ResetTextViewBackgroundColor();
    @Override
    public int step(LessonSimulationVisualizationParentFragment lessonSimulationVisualizationParentFragment, String textDescription) {
        resetTextViewBackgroundColor.step(lessonSimulationVisualizationParentFragment, "");

        TextView thresholdComparisionTextView = lessonSimulationVisualizationParentFragment.getTextView("thresholdComparisionTextView");
        thresholdComparisionTextView.setBackgroundColor(lessonSimulationVisualizationParentFragment.getResources().getColor(R.color.foreground_warn));

        double threshold = lessonSimulationVisualizationParentFragment.getModelValueDouble("THRESHOLD");

        double weightedSum = lessonSimulationVisualizationParentFragment.getModelValueDouble("weightedSum");


        TextView outputTextView = lessonSimulationVisualizationParentFragment.getTextView("outputTextView");

        int output;
        String datasetType = lessonSimulationVisualizationParentFragment.getDatasetType();
        if(datasetType.equals("AND") || datasetType.equals("OR")) {
            output = (weightedSum >= threshold)? 1: 0;
        }
        else{
            // dataset for NAND and NOR
            output = (weightedSum >= threshold)? 0: 1;
        }

        outputTextView.setText(("" + output));

        outputTextView.setBackgroundColor(lessonSimulationVisualizationParentFragment.getResources().getColor(R.color.foreground_warn));

        lessonSimulationVisualizationParentFragment.setModelValueInt("outputFromModel",output);

        // change output text to empty string
        changeOutputText.step(lessonSimulationVisualizationParentFragment,textDescription);

        return getNO_ACTION();
    }
}
