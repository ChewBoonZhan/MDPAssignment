package com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.visualizationMethods;

import android.widget.TextView;

import com.example.ml_visualized.R;
import com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.LessonVisualizationFragment;

public class LoadInputOutputIntoModel extends VisualizationMethods{
    private ResetTextViewBackgroundColor resetTextViewBackgroundColor = new ResetTextViewBackgroundColor();
    @Override
    public int step(LessonVisualizationFragment lessonVisualizationFragment, String textDescription) {
        resetTextViewBackgroundColor.step(lessonVisualizationFragment, "");


        TextView input1TextView = lessonVisualizationFragment.getTextView("input1TextView");

        int input1 = lessonVisualizationFragment.getModelValueInt("input1");
        input1TextView.setText(("" + input1));
        input1TextView.setBackgroundColor(lessonVisualizationFragment.getResources().getColor(R.color.foreground_warn));


        TextView input2TextView = lessonVisualizationFragment.getTextView("input2TextView");

        int input2 = lessonVisualizationFragment.getModelValueInt("input2");
        input2TextView.setText(("" + input2));
        input2TextView.setBackgroundColor(lessonVisualizationFragment.getResources().getColor(R.color.foreground_warn));

        TextView actualOutputTextView =lessonVisualizationFragment.getTextView("actualOutputTextView");
        actualOutputTextView.setText((lessonVisualizationFragment.getModelValueInt("expectedOutput") + ""));
        actualOutputTextView.setBackgroundColor(lessonVisualizationFragment.getResources().getColor(R.color.foreground_warn));



        lessonVisualizationFragment.changeOutputTextViewContent(textDescription);

        return getNO_ACTION();
    }

}
