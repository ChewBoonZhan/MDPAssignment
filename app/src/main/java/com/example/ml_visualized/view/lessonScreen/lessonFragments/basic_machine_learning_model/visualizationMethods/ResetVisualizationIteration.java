package com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.visualizationMethods;

import com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.LessonVisualizationFragment;

public class ResetVisualizationIteration extends VisualizationMethods{

    private ResetTextViewBackgroundColor resetTextViewBackgroundColor = new ResetTextViewBackgroundColor();

    private ResetComputeTextViewText resetComputeTextViewText = new ResetComputeTextViewText();

    private HideCalculationTextView hideCalculationTextView = new HideCalculationTextView();

    @Override
    public int step(LessonVisualizationFragment lessonVisualizationFragment, String textDescription) {

        resetTextViewBackgroundColor.step(lessonVisualizationFragment,"");

        resetComputeTextViewText.step(lessonVisualizationFragment,"");

        hideCalculationTextView.step(lessonVisualizationFragment,"");

        return getNO_ACTION();
    }
}
