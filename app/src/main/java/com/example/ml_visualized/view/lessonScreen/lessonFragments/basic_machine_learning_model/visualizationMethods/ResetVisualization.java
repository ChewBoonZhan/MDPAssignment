package com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.visualizationMethods;

import android.view.View;
import android.widget.TextView;

import com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.LessonVisualizationFragment;

public class ResetVisualization extends VisualizationMethods{
    private ResetTextViewBackgroundColor resetTextViewBackgroundColor = new ResetTextViewBackgroundColor();

    private HideCalculationTextView hideCalculationTextView = new HideCalculationTextView();

    private ResetComputeTextViewText resetComputeTextViewText = new ResetComputeTextViewText();

    private LoadWeightIntoModel loadWeightIntoModel = new LoadWeightIntoModel();
    @Override
    public int step(LessonVisualizationFragment lessonVisualizationFragment, String textDescription) {
        // reset background color of textview
        resetTextViewBackgroundColor.step(lessonVisualizationFragment,"");

        // hide textview used for calculations
        hideCalculationTextView.step(lessonVisualizationFragment,"");

        resetComputeTextViewText.step(lessonVisualizationFragment,"");

        loadWeightIntoModel.step(lessonVisualizationFragment,"");

        // hide weight for input 1 and 2
        TextView input1WeightTextView = lessonVisualizationFragment.getTextView("input1WeightTextView");
        input1WeightTextView.setVisibility(View.INVISIBLE);

        TextView input2WeightTextView = lessonVisualizationFragment.getTextView("input2WeightTextView");
        input2WeightTextView.setVisibility(View.INVISIBLE);


        // change output text to empty string
        lessonVisualizationFragment.changeOutputTextViewContent("");

        return getNO_ACTION();
    }
}
