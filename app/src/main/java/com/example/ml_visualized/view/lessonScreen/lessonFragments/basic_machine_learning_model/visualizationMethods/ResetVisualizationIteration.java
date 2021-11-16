package com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.visualizationMethods;

import com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.LessonSimulationVisualizationParentFragment;

public class ResetVisualizationIteration extends VisualizationMethods{

    private ResetTextViewBackgroundColor resetTextViewBackgroundColor = new ResetTextViewBackgroundColor();

    private ResetComputeTextViewText resetComputeTextViewText = new ResetComputeTextViewText();

    private HideCalculationTextView hideCalculationTextView = new HideCalculationTextView();

    @Override
    public int step(LessonSimulationVisualizationParentFragment lessonSimulationVisualizationParentFragment, String textDescription) {

        resetTextViewBackgroundColor.step(lessonSimulationVisualizationParentFragment,"");

        resetComputeTextViewText.step(lessonSimulationVisualizationParentFragment,"");

        hideCalculationTextView.step(lessonSimulationVisualizationParentFragment,"");

        return getNO_ACTION();
    }
}
