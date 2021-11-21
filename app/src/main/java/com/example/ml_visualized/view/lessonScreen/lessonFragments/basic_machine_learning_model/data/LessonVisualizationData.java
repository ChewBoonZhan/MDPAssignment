package com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.data;

import com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.LessonVisualizationFragment;
import com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.visualizationMethods.VisualizationMethods;

public class LessonVisualizationData {
    private LessonVisualizationFragment lessonVisualizationFragment;

    private VisualizationMethods visualizationMethods = new VisualizationMethods();

    /**
     * Constructor
     * @param lessonVisualizationFragment - fragment to acquire data to produce return string for explanation of visualization
     */
    public LessonVisualizationData(LessonVisualizationFragment lessonVisualizationFragment){
        this.lessonVisualizationFragment = lessonVisualizationFragment;
    }

    /**
     *
     * @param index - index for string to return to user as explanation of visualization
     * @return String - explanation for visualization on the screen for user
     */
    public String getOutputStringForIndex(int index){
        // get the dataset type and show on the screen
        String datasetType= lessonVisualizationFragment.getDatasetType();
        String output = "";

        // switch based on different data of the fragment
        switch(index){
            case 0:
                output = "Now, lets visualize how a model trains";
                break;
            case 1:
                output = "Let's load our Input and Output into the model from the dataset";
                break;
            case 2:
                output = "These are the weight for Input 1 and 2 respectively.";

                break;
            case 3:
                output = "Then, we calculate the weighted input by using weight * input";
                break;
            case 4:
                // return text for how weighted input 1 is calculated
                output = "Weighted input 1 = " +
                        lessonVisualizationFragment.getModelValueInt("input1") +
                        " * " +
                        lessonVisualizationFragment.getModelValueDouble("weight1") + " = "
                        + (lessonVisualizationFragment.getModelValueInt("input1") *
                        lessonVisualizationFragment.getModelValueDouble("weight1"));
                break;
            case 5:
                // return text for how weighted input 2 is calculated
                output = "Weighted input 2 = " +
                        lessonVisualizationFragment.getModelValueInt("input2") +
                        " * " +
                        lessonVisualizationFragment.getModelValueDouble("weight2") + " = "
                        + (lessonVisualizationFragment.getModelValueInt("input2") *
                        lessonVisualizationFragment.getModelValueDouble("weight2"));
                break;
            case 6:
                output = "Now, we sum them up to get the weighted sum";
                break;
            case 7:
                // return text for how weighted input is calculated based on weighted input 1 and weighted input 2
                output = "" + lessonVisualizationFragment.getModelValueDouble("weightedSumInput1")
                        + " + " +
                        lessonVisualizationFragment.getModelValueDouble("weightedSumInput2")
                        + " = " +
                        (lessonVisualizationFragment.getModelValueDouble("weightedSumInput1") +
                                lessonVisualizationFragment.getModelValueDouble("weightedSumInput2"));
                break;
            case 8:
                output = "Now, we compare the weighted sum with the threshold";
                break;
            case 9:
                // return text for explanation based on dataset type, and comparision of
                // weighted su and threshold
                boolean weightedSumMoreThanThreshold =
                        lessonVisualizationFragment.getModelValueDouble("weightedSum")
                                >=
                                lessonVisualizationFragment.getModelValueDouble("THRESHOLD");
                if(datasetType.equals("AND") || datasetType.equals("OR")){
                    output = weightedSumMoreThanThreshold ?
                            "Since Weighted Sum is >= Threshold, therefore the Output is 1" :
                            "Since Weighted Sum is < Threshold, therefore the Output is 0";
                }
                else{
                    // dataset for NAND and NOR
                    output = weightedSumMoreThanThreshold ?
                            "Since Weighted Sum is >= Threshold, therefore the Output is 0":
                            "Since Weighted Sum is < Threshold, therefore the Output is 1";
                }

                break;
            case 10:
                // return text for backpropagation and changes in model parameter based on
                int outputFromModel = lessonVisualizationFragment
                        .getModelValueInt("outputFromModel");
                int actualOutput = lessonVisualizationFragment
                        .getModelValueInt("expectedOutput");
                String changeInWeight = "";
                if(outputFromModel > actualOutput){
                    // output from model is more than actual output
                    if(datasetType.equals("AND") || datasetType.equals("OR")) {
                        changeInWeight = "decrease";
                    }
                    else{
                        // dataset for NAND and NOR
                        changeInWeight = "increase";
                    }
                    output = "Since actual output < output from model, we " + changeInWeight+ " weight by learning rate.";
                }
                else if(actualOutput > outputFromModel){
                    // actual output is more than output from model

                    if(datasetType.equals("AND") || datasetType.equals("OR")) {
                        changeInWeight = "increase";
                    }
                    else{
                        // dataset for NAND and NOR
                        changeInWeight = "decrease";
                    }

                    output = "Since actual output > output from model, we "+changeInWeight+" weight by learning rate";
                }
                else{
                    // actual output is same as output from model
                    output = "Since actual output == output from model, no changes of parameter needs to be made.";
                }
                break;
            case 11:
                // we only reach here if we are changing the weight by learning rate.
                int learningRateChange = lessonVisualizationFragment.getModelValueInt("learningRateChange");

                // set learningRateChange to no action
                lessonVisualizationFragment.setModelValueInt("learningRateChange",visualizationMethods.getNO_ACTION());
                String weightChange = "";
                String weightChangeSymbol = "";
                if(learningRateChange == visualizationMethods.getREDUCE_WEIGHT()){

                    weightChange = "reduce";
                    weightChangeSymbol = "-";

                }
                else if(learningRateChange == visualizationMethods.getINCREASE_WEIGHT()){

                    weightChange = "increase";
                    weightChangeSymbol = "+";

                }
                output ="We "+ weightChange+ " the weight by learning rate. New Weight = Old Weight " +weightChangeSymbol+ " Learning Rate";
                break;
            case 12:
                // end of an iteration
                output = "Now, we move on to the new iteration. ";
                break;
        }
        return output;
    }
}
