package com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.data;

import com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.LessonSimulationFragment;

public class LessonSimulationData {
    private LessonSimulationFragment lessonSimulationFragment;

    public LessonSimulationData(LessonSimulationFragment lessonSimulationFragment){
        this.lessonSimulationFragment = lessonSimulationFragment;
    }

    public String getOutputStringForIndex(int index){
        String outputString = "";
        switch(index){
            case 0:
            {
                double weight1 = lessonSimulationFragment.getModelValueDouble("weight1");
                int input1 = lessonSimulationFragment.getModelValueInt("input1");
                double weightedSumInput1 =weight1 * input1;
                outputString = "" + input1 + " * " + weight1 + " = " + weightedSumInput1;
                break;
            }
            case 1:
            {
                double weight2 = lessonSimulationFragment.getModelValueDouble("weight2");
                int input2 = lessonSimulationFragment.getModelValueInt("input2");
                double weightedSumInput2 =weight2 * input2;
                outputString = "" + input2 + " * " + weight2 + " = " + weightedSumInput2;
                break;
            }
            case 2:
            {
                double weightedSumInput1 = lessonSimulationFragment.getModelValueDouble("weightedSumInput1");
                double weightedSumInput2 = lessonSimulationFragment.getModelValueDouble("weightedSumInput2");
                double weightedSum = weightedSumInput1 + weightedSumInput2;
                outputString = "" + weightedSumInput1 + " + " + weightedSumInput2 + " = " + weightedSum;
                break;
            }
            case 3:
            {
                String datasetType = lessonSimulationFragment.getDatasetType();
                double THRESHOLD = lessonSimulationFragment.getModelValueDouble("THRESHOLD");
                double weightedSum = lessonSimulationFragment.getModelValueDouble("weightedSum");
                boolean weightedSumMoreThanThreshold = weightedSum >= THRESHOLD;
                String compareSymbol = weightedSumMoreThanThreshold? ">=": "<";
                outputString = "" + weightedSum  + " " + compareSymbol + " " + THRESHOLD + ".";
                break;
            }
            case 4:
            {
                String datasetType = lessonSimulationFragment.getDatasetType();
                double THRESHOLD = lessonSimulationFragment.getModelValueDouble("THRESHOLD");
                double weightedSum = lessonSimulationFragment.getModelValueDouble("weightedSum");
                boolean weightedSumMoreThanThreshold = weightedSum >= THRESHOLD;
                String compareSymbol = weightedSumMoreThanThreshold? ">=": "<";
                String modelOutputText = "";
                if(datasetType.equals("AND") || datasetType.equals("OR")) {
                    modelOutputText = weightedSumMoreThanThreshold? "1": "0";
                }
                else{
                    // dataset for NAND and NOR
                    modelOutputText = weightedSumMoreThanThreshold? "0": "1";
                }
                outputString = "" + weightedSum  + " " + compareSymbol + " " + THRESHOLD + ", therefore output is " + modelOutputText;
                break;
            }

        }
        return outputString;
    }
}
