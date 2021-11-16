package com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.visualizationMethods;

import android.view.View;
import android.widget.TextView;

import com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.LessonSimulationVisualizationParentFragment;

public class HideCalculationTextView extends VisualizationMethods{
    private ChangeOutputText changeOutputText = new ChangeOutputText();
    @Override
    public int step(LessonSimulationVisualizationParentFragment lessonSimulationVisualizationParentFragment, String textDescription) {

        TextView input1WeightedSum= lessonSimulationVisualizationParentFragment.getTextView("input1WeightedSum");
        input1WeightedSum.setVisibility(View.INVISIBLE);

        TextView input2WeightedSum = lessonSimulationVisualizationParentFragment.getTextView("input2WeightedSum");
        input2WeightedSum.setVisibility(View.INVISIBLE);

        TextView thresholdComparisionTextView = lessonSimulationVisualizationParentFragment.getTextView("thresholdComparisionTextView");
        thresholdComparisionTextView.setVisibility(View.INVISIBLE);

        // change output text to empty string
        changeOutputText.step(lessonSimulationVisualizationParentFragment,"");

        return getNO_ACTION();

    }
}
