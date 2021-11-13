package com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.visualizationMethods;

import android.widget.TextView;

import com.example.ml_visualized.R;
import com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.LessonVisualizationFragment;

import java.text.DecimalFormat;

public class BackPropagate extends VisualizationMethods{
    private ResetTextViewBackgroundColor resetTextViewBackgroundColor = new ResetTextViewBackgroundColor();
    @Override
    public int step(LessonVisualizationFragment lessonVisualizationFragment, String textDescription) {
        resetTextViewBackgroundColor.step(lessonVisualizationFragment, "");


        int outputFromModel = lessonVisualizationFragment.getModelValueInt("outputFromModel");
        int expectedOutput = lessonVisualizationFragment.getModelValueInt("expectedOutput");

        double weight1 = lessonVisualizationFragment.getModelValueDouble("weight1");
        double weight2 = lessonVisualizationFragment.getModelValueDouble("weight2");

        double LEARNING_RATE = lessonVisualizationFragment.getModelValueDouble("LEARNING_RATE");


        if(outputFromModel == expectedOutput){
            lessonVisualizationFragment.changeOutputTextViewContent(textDescription);
            return getREMAIN_WEIGHT();
        }
        else{
            int itemToReturn = 0;
            String datasetType = lessonVisualizationFragment.getDatasetType();
            if(datasetType.equals("AND") || datasetType.equals("OR")) {
                if(outputFromModel > expectedOutput){
                    weight1-=LEARNING_RATE;
                    weight2-=LEARNING_RATE;
                    itemToReturn = getREDUCE_WEIGHT();
                }
                else if(outputFromModel < expectedOutput){
                    weight1+=LEARNING_RATE;
                    weight2+=LEARNING_RATE;
                    itemToReturn = getINCREASE_WEIGHT();
                }
            }
            else{
                // dataset for NAND and NOR
                if(outputFromModel < expectedOutput){
                    weight1-=LEARNING_RATE;
                    weight2-=LEARNING_RATE;
                    itemToReturn = getREDUCE_WEIGHT();
                }
                else if(outputFromModel > expectedOutput){
                    weight1+=LEARNING_RATE;
                    weight2+=LEARNING_RATE;
                    itemToReturn = getINCREASE_WEIGHT();
                }
            }



            DecimalFormat df = new DecimalFormat("0.0");


            weight1 = Double.valueOf(df.format(weight1));
            weight2 = Double.valueOf(df.format(weight2));


            lessonVisualizationFragment.setModelValueDouble("weight1",weight1);
            lessonVisualizationFragment.setModelValueDouble("weight2",weight2);

            TextView input1WeightTextView = lessonVisualizationFragment.getTextView("input1WeightTextView");

            input1WeightTextView.setText(("w = " + weight1));

            TextView input2WeightTextView = lessonVisualizationFragment.getTextView("input2WeightTextView");

            input2WeightTextView.setText(("w = " + weight2));

            input1WeightTextView.setBackgroundColor(lessonVisualizationFragment.getResources().getColor(R.color.foreground_warn));
            input2WeightTextView.setBackgroundColor(lessonVisualizationFragment.getResources().getColor(R.color.foreground_warn));

            lessonVisualizationFragment.changeOutputTextViewContent(textDescription);

            return itemToReturn;
        }




    }
}
