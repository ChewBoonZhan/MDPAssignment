package com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.data;

import com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.LessonSimulationFragment;

public class LessonSimulationData {
    private LessonSimulationFragment lessonSimulationFragment;

    /**
     * Contructor
     * @param lessonSimulationFragment - to access different model parameter to return different string for explanation
     */
    public LessonSimulationData(LessonSimulationFragment lessonSimulationFragment){
        this.lessonSimulationFragment = lessonSimulationFragment;
    }

    /**
     *
     * @param index - index to get string to show as explanation for simulation
     * @return String - output string explanation
     */
    public String getOutputStringForIndex(int index){
        // output string to return
        String outputString = "";

        // switch based on index
        switch(index){
            case 0:
            {
                double weight1 = lessonSimulationFragment.getModelValueDouble("weight1");
                int input1 = lessonSimulationFragment.getModelValueInt("input1");
                // find the weighted sum
                double weightedSumInput1 =weight1 * input1;
                // create string for explanation of how weighted sum is derived
                outputString = "" + input1 + " * " + weight1 + " = " + weightedSumInput1;
                break;
            }
            case 1:
            {
                double weight2 = lessonSimulationFragment.getModelValueDouble("weight2");
                int input2 = lessonSimulationFragment.getModelValueInt("input2");
                // find the weighted sum
                double weightedSumInput2 =weight2 * input2;
                // create string for explanation of how weighted sum is derived
                outputString = "" + input2 + " * " + weight2 + " = " + weightedSumInput2;
                break;
            }
            case 2:
            {
                double weightedSumInput1 = lessonSimulationFragment.getModelValueDouble("weightedSumInput1");
                double weightedSumInput2 = lessonSimulationFragment.getModelValueDouble("weightedSumInput2");
                // find weighted sum of both inputs
                double weightedSum = weightedSumInput1 + weightedSumInput2;
                // create string for explanation of weighted sum of both input is derived
                outputString = "" + weightedSumInput1 + " + " + weightedSumInput2 + " = " + weightedSum;
                break;
            }
            case 3:
            {
                // get dataset type simulated
                String datasetType = lessonSimulationFragment.getDatasetType();
                // get threshold and weighted sum
                double THRESHOLD = lessonSimulationFragment.getModelValueDouble("THRESHOLD");
                double weightedSum = lessonSimulationFragment.getModelValueDouble("weightedSum");

                // compares threshold and weighted sum
                boolean weightedSumMoreThanThreshold = weightedSum >= THRESHOLD;

                // return string based on comparision of threshold and weighted sum
                String compareSymbol = weightedSumMoreThanThreshold? ">=": "<";
                outputString = "" + weightedSum  + " " + compareSymbol + " " + THRESHOLD + ".";
                break;
            }
            case 4:
            {
                // get dataset type
                String datasetType = lessonSimulationFragment.getDatasetType();

                // get the threshold and weighted sum
                double THRESHOLD = lessonSimulationFragment.getModelValueDouble("THRESHOLD");
                double weightedSum = lessonSimulationFragment.getModelValueDouble("weightedSum");

                // compare threshold and weighted sum
                boolean weightedSumMoreThanThreshold = weightedSum >= THRESHOLD;

                // create symbol based on comparision for threshold and weighted sum
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
