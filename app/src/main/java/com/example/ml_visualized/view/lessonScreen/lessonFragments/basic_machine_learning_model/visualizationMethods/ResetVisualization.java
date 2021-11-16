package com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.visualizationMethods;

import android.view.View;
import android.widget.TextView;

import com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.LessonSimulationVisualizationParentFragment;

public class ResetVisualization extends VisualizationMethods{
    private ResetTextViewBackgroundColor resetTextViewBackgroundColor = new ResetTextViewBackgroundColor();

    private HideCalculationTextView hideCalculationTextView = new HideCalculationTextView();

    private ResetComputeTextViewText resetComputeTextViewText = new ResetComputeTextViewText();

    private LoadWeightIntoModel loadWeightIntoModel = new LoadWeightIntoModel();

    private ChangeOutputText changeOutputText = new ChangeOutputText();
    @Override
    public int step(LessonSimulationVisualizationParentFragment lessonSimulationVisualizationParentFragment, String textDescription) {

        // reset background color of textview
        resetTextViewBackgroundColor.step(lessonSimulationVisualizationParentFragment,"");

        // hide textview used for calculations
        hideCalculationTextView.step(lessonSimulationVisualizationParentFragment,"");

        resetComputeTextViewText.step(lessonSimulationVisualizationParentFragment,"");

        loadWeightIntoModel.step(lessonSimulationVisualizationParentFragment,"");

        // hide weight for input 1 and 2
        TextView input1WeightTextView = lessonSimulationVisualizationParentFragment.getTextView("input1WeightTextView");
        input1WeightTextView.setVisibility(View.INVISIBLE);

        TextView input2WeightTextView = lessonSimulationVisualizationParentFragment.getTextView("input2WeightTextView");
        input2WeightTextView.setVisibility(View.INVISIBLE);

        // change output text to empty string
        changeOutputText.step(lessonSimulationVisualizationParentFragment,"");

        return getNO_ACTION();
    }
}
