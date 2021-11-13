package com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.visualizationMethods;

import android.view.View;
import android.widget.TextView;

import com.example.ml_visualized.R;
import com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.LessonVisualizationFragment;

public class ShowWeightForInput extends VisualizationMethods{
    private ResetTextViewBackgroundColor resetTextViewBackgroundColor = new ResetTextViewBackgroundColor();
    @Override
    public int step(LessonVisualizationFragment lessonVisualizationFragment, String textDescription) {
        resetTextViewBackgroundColor.step(lessonVisualizationFragment, "");

        // show weight for input 1 and 2
        TextView input1WeightTextView = lessonVisualizationFragment.getTextView("input1WeightTextView");
        input1WeightTextView.setVisibility(View.VISIBLE);

        input1WeightTextView.setBackgroundColor(lessonVisualizationFragment.getResources().getColor(R.color.foreground_warn));

        TextView input2WeightTextView = lessonVisualizationFragment.getTextView("input2WeightTextView");
        input2WeightTextView.setVisibility(View.VISIBLE);

        input2WeightTextView.setBackgroundColor(lessonVisualizationFragment.getResources().getColor(R.color.foreground_warn));

        // change output text
        lessonVisualizationFragment.changeOutputTextViewContent(textDescription);

        return getNO_ACTION();

    }
}
