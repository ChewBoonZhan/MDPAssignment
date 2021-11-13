package com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.visualizationMethods;

import android.widget.TextView;

import com.example.ml_visualized.R;
import com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.LessonVisualizationFragment;

public class CalculateTotalWeightedSum  extends VisualizationMethods{
    private ResetTextViewBackgroundColor resetTextViewBackgroundColor = new ResetTextViewBackgroundColor();
    @Override
    public int step(LessonVisualizationFragment lessonVisualizationFragment, String textDescription) {
        resetTextViewBackgroundColor.step(lessonVisualizationFragment, "");
        super.step(lessonVisualizationFragment,"");
        double weightedSumInput1 = lessonVisualizationFragment.getModelValueDouble("weightedSumInput1");
        double weightedSumInput2= lessonVisualizationFragment.getModelValueDouble("weightedSumInput2");

        double weightedSum = weightedSumInput1 + weightedSumInput2;

        lessonVisualizationFragment.setModelValueDouble("weightedSum",weightedSum);

        TextView weightedSumTextView = lessonVisualizationFragment.getTextView("weightedSumTextView");
        weightedSumTextView.setText((weightedSum + ""));

        weightedSumTextView.setBackgroundColor(lessonVisualizationFragment.getResources().getColor(R.color.foreground_warn));

        TextView input1WeightedSum = lessonVisualizationFragment.getTextView("input1WeightedSum");
        input1WeightedSum.setBackgroundColor(lessonVisualizationFragment.getResources().getColor(R.color.foreground_warn));

        TextView input2WeightedSum = lessonVisualizationFragment.getTextView("input2WeightedSum");
        input2WeightedSum.setBackgroundColor(lessonVisualizationFragment.getResources().getColor(R.color.foreground_warn));

        lessonVisualizationFragment.changeOutputTextViewContent(textDescription);

        // change output text
        lessonVisualizationFragment.changeOutputTextViewContent(textDescription);

        return getNO_ACTION();
    }
}
