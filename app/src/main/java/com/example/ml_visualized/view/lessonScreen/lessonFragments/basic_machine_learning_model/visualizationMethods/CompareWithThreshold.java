package com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.visualizationMethods;

import android.view.View;
import android.widget.TextView;

import com.example.ml_visualized.R;
import com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.LessonVisualizationFragment;

public class CompareWithThreshold extends VisualizationMethods{
    private ResetTextViewBackgroundColor resetTextViewBackgroundColor = new ResetTextViewBackgroundColor();
    @Override
    public int step(LessonVisualizationFragment lessonVisualizationFragment, String textDescription) {
        resetTextViewBackgroundColor.step(lessonVisualizationFragment, "");

        double threshold = lessonVisualizationFragment.getModelValueDouble("THRESHOLD");

        double weightedSum = lessonVisualizationFragment.getModelValueDouble("weightedSum");

        String compareType = (weightedSum >= threshold)? ">=":"<";


        TextView thresholdComparisionTextView = lessonVisualizationFragment.getTextView("thresholdComparisionTextView");
        thresholdComparisionTextView.setText(("" + weightedSum +" " +compareType + " " + threshold));

        thresholdComparisionTextView.setBackgroundColor(lessonVisualizationFragment.getResources().getColor(R.color.foreground_warn));

        TextView thresholdTextView = lessonVisualizationFragment.getTextView("thresholdTextView");
        thresholdTextView.setBackgroundColor(lessonVisualizationFragment.getResources().getColor(R.color.foreground_warn));

        TextView weightedSumTextView = lessonVisualizationFragment.getTextView("weightedSumTextView");
        weightedSumTextView.setBackgroundColor(lessonVisualizationFragment.getResources().getColor(R.color.foreground_warn));


        thresholdComparisionTextView.setVisibility(View.VISIBLE);

        lessonVisualizationFragment.changeOutputTextViewContent(textDescription);

        return getNO_ACTION();
    }
}
