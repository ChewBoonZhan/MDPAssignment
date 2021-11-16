package com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.visualizationMethods;

import android.widget.TextView;

import com.example.ml_visualized.R;
import com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.LessonSimulationVisualizationParentFragment;

public class LoadInputOutputIntoModel extends VisualizationMethods{
    private ResetTextViewBackgroundColor resetTextViewBackgroundColor = new ResetTextViewBackgroundColor();
    private ChangeOutputText changeOutputText = new ChangeOutputText();
    @Override
    public int step(LessonSimulationVisualizationParentFragment lessonSimulationVisualizationParentFragment, String textDescription) {
        resetTextViewBackgroundColor.step(lessonSimulationVisualizationParentFragment, "");


        TextView input1TextView = lessonSimulationVisualizationParentFragment.getTextView("input1TextView");

        int input1 = lessonSimulationVisualizationParentFragment.getModelValueInt("input1");
        input1TextView.setText(("" + input1));
        input1TextView.setBackgroundColor(lessonSimulationVisualizationParentFragment.getResources().getColor(R.color.foreground_warn));


        TextView input2TextView = lessonSimulationVisualizationParentFragment.getTextView("input2TextView");

        int input2 = lessonSimulationVisualizationParentFragment.getModelValueInt("input2");
        input2TextView.setText(("" + input2));
        input2TextView.setBackgroundColor(lessonSimulationVisualizationParentFragment.getResources().getColor(R.color.foreground_warn));

        TextView actualOutputTextView =lessonSimulationVisualizationParentFragment.getTextView("actualOutputTextView");
        actualOutputTextView.setText((lessonSimulationVisualizationParentFragment.getModelValueInt("expectedOutput") + ""));
        actualOutputTextView.setBackgroundColor(lessonSimulationVisualizationParentFragment.getResources().getColor(R.color.foreground_warn));

        changeOutputText.step(lessonSimulationVisualizationParentFragment,textDescription);

        return getNO_ACTION();
    }

}
