package com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.visualizationMethods;

import android.widget.TextView;

import com.example.ml_visualized.R;
import com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.LessonSimulationVisualizationParentFragment;

import java.text.DecimalFormat;



public class BackPropagate extends VisualizationMethods{
    private ResetTextViewBackgroundColor resetTextViewBackgroundColor = new ResetTextViewBackgroundColor();
    private ChangeOutputText changeOutputText = new ChangeOutputText();
    @Override
    public int step(LessonSimulationVisualizationParentFragment lessonSimulationVisualizationParentFragment, String textDescription) {
        resetTextViewBackgroundColor.step(lessonSimulationVisualizationParentFragment, "");


        int outputFromModel = lessonSimulationVisualizationParentFragment.getModelValueInt("outputFromModel");
        int expectedOutput = lessonSimulationVisualizationParentFragment.getModelValueInt("expectedOutput");

        double weight1 = lessonSimulationVisualizationParentFragment.getModelValueDouble("weight1");
        double weight2 = lessonSimulationVisualizationParentFragment.getModelValueDouble("weight2");

        double LEARNING_RATE = lessonSimulationVisualizationParentFragment.getModelValueDouble("LEARNING_RATE");


        if(outputFromModel == expectedOutput){
            changeOutputText.step(lessonSimulationVisualizationParentFragment,textDescription);
            return getREMAIN_WEIGHT();
        }
        else{
            int itemToReturn = 0;
            String datasetType = lessonSimulationVisualizationParentFragment.getDatasetType();
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


            lessonSimulationVisualizationParentFragment.setModelValueDouble("weight1",weight1);
            lessonSimulationVisualizationParentFragment.setModelValueDouble("weight2",weight2);

            TextView input1WeightTextView = lessonSimulationVisualizationParentFragment.getTextView("input1WeightTextView");

            input1WeightTextView.setText(("w = " + weight1));

            TextView input2WeightTextView = lessonSimulationVisualizationParentFragment.getTextView("input2WeightTextView");

            input2WeightTextView.setText(("w = " + weight2));

            input1WeightTextView.setBackgroundColor(lessonSimulationVisualizationParentFragment.getResources().getColor(R.color.foreground_warn));
            input2WeightTextView.setBackgroundColor(lessonSimulationVisualizationParentFragment.getResources().getColor(R.color.foreground_warn));

            changeOutputText.step(lessonSimulationVisualizationParentFragment,textDescription);

            return itemToReturn;
        }




    }
}
