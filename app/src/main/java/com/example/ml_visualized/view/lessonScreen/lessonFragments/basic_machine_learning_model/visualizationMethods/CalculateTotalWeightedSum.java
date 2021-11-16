package com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.visualizationMethods;

import android.widget.TextView;

import com.example.ml_visualized.R;
import com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.LessonSimulationVisualizationParentFragment;

public class CalculateTotalWeightedSum  extends VisualizationMethods{
    private ResetTextViewBackgroundColor resetTextViewBackgroundColor = new ResetTextViewBackgroundColor();
    private ChangeOutputText changeOutputText = new ChangeOutputText();
    @Override
    public int step(LessonSimulationVisualizationParentFragment lessonSimulationVisualizationParentFragment, String textDescription) {
        resetTextViewBackgroundColor.step(lessonSimulationVisualizationParentFragment, "");

        double weightedSumInput1 = lessonSimulationVisualizationParentFragment.getModelValueDouble("weightedSumInput1");
        double weightedSumInput2= lessonSimulationVisualizationParentFragment.getModelValueDouble("weightedSumInput2");

        double weightedSum = weightedSumInput1 + weightedSumInput2;

        lessonSimulationVisualizationParentFragment.setModelValueDouble("weightedSum",weightedSum);

        TextView weightedSumTextView = lessonSimulationVisualizationParentFragment.getTextView("weightedSumTextView");
        weightedSumTextView.setText((weightedSum + ""));

        weightedSumTextView.setBackgroundColor(lessonSimulationVisualizationParentFragment.getResources().getColor(R.color.foreground_warn));

        TextView input1WeightedSum = lessonSimulationVisualizationParentFragment.getTextView("input1WeightedSum");
        input1WeightedSum.setBackgroundColor(lessonSimulationVisualizationParentFragment.getResources().getColor(R.color.foreground_warn));

        TextView input2WeightedSum = lessonSimulationVisualizationParentFragment.getTextView("input2WeightedSum");
        input2WeightedSum.setBackgroundColor(lessonSimulationVisualizationParentFragment.getResources().getColor(R.color.foreground_warn));

        changeOutputText.step(lessonSimulationVisualizationParentFragment,textDescription);


        return getNO_ACTION();
    }
}
