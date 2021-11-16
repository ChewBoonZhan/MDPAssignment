package com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.visualizationMethods;

import android.view.View;
import android.widget.TextView;

import com.example.ml_visualized.R;
import com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.LessonSimulationVisualizationParentFragment;

public class CompareWithThreshold extends VisualizationMethods{
    private ResetTextViewBackgroundColor resetTextViewBackgroundColor = new ResetTextViewBackgroundColor();

    private ChangeOutputText changeOutputText = new ChangeOutputText();
    @Override
    public int step(LessonSimulationVisualizationParentFragment lessonSimulationVisualizationParentFragment, String textDescription) {
        resetTextViewBackgroundColor.step(lessonSimulationVisualizationParentFragment, "");

        double threshold = lessonSimulationVisualizationParentFragment.getModelValueDouble("THRESHOLD");

        double weightedSum = lessonSimulationVisualizationParentFragment.getModelValueDouble("weightedSum");

        String compareType = (weightedSum >= threshold)? ">=":"<";


        TextView thresholdComparisionTextView = lessonSimulationVisualizationParentFragment.getTextView("thresholdComparisionTextView");
        thresholdComparisionTextView.setText(("" + weightedSum +" " +compareType + " " + threshold));

        thresholdComparisionTextView.setBackgroundColor(lessonSimulationVisualizationParentFragment.getResources().getColor(R.color.foreground_warn));

        TextView thresholdTextView = lessonSimulationVisualizationParentFragment.getTextView("thresholdTextView");
        thresholdTextView.setBackgroundColor(lessonSimulationVisualizationParentFragment.getResources().getColor(R.color.foreground_warn));

        TextView weightedSumTextView = lessonSimulationVisualizationParentFragment.getTextView("weightedSumTextView");
        weightedSumTextView.setBackgroundColor(lessonSimulationVisualizationParentFragment.getResources().getColor(R.color.foreground_warn));


        thresholdComparisionTextView.setVisibility(View.VISIBLE);

        changeOutputText.step(lessonSimulationVisualizationParentFragment,textDescription);

        return getNO_ACTION();
    }
}
