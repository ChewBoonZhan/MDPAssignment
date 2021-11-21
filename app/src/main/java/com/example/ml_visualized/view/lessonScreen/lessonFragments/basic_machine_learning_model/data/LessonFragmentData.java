package com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.data;

import java.util.ArrayList;
import java.util.HashMap;

/*
    this class is mainly containing data of:
    - Dataset type used for lessons
    - Dataset data for different dataset type
 */
public class LessonFragmentData {
    // array list to store all dataset types
    private ArrayList<String> datasetTypeCollection = new ArrayList<String>();

    // data structure for input1, input2 and output
    private HashMap<String, ArrayList<Integer>> inputOutputValueCollection;

    // create data structure for and, nand, or and nor
    private HashMap<String,HashMap<String, ArrayList<Integer>>> datasetCollection = new HashMap<>();


    public LessonFragmentData(){
        // init arraylist that stores dataset type
        initDatasetType();

        // initialize dataset collection for the model
        initDatasetCollection();
    }

    // get dataset collection index
    public HashMap<String, ArrayList<Integer>> getSpecificDatasetCollection(int datasetIndex){
        String dataset = datasetTypeCollection.get(datasetIndex);
        return datasetCollection.get(dataset);
    }

    public HashMap<String, ArrayList<Integer>> getSpecificDatasetCollection(String dataset){
        return datasetCollection.get(dataset);
    }

    private void initDatasetType(){
        datasetTypeCollection.add("AND");
        datasetTypeCollection.add("NAND");
        datasetTypeCollection.add("OR");
        datasetTypeCollection.add("NOR");
    }

    private void initDatasetCollection(){
        ArrayList<Integer> input1Collection = new ArrayList<Integer>();
        input1Collection.add(1);
        input1Collection.add(1);
        input1Collection.add(0);
        input1Collection.add(0);

        ArrayList<Integer> input2Collection = new ArrayList<Integer>();
        input2Collection.add(1);
        input2Collection.add(0);
        input2Collection.add(1);
        input2Collection.add(0);

        ArrayList<Boolean> input1BooleanCollection = new ArrayList<Boolean>();
        input1BooleanCollection.add(true);
        input1BooleanCollection.add(true);
        input1BooleanCollection.add(false);
        input1BooleanCollection.add(false);

        ArrayList<Boolean> input2BooleanCollection = new ArrayList<Boolean>();
        input2BooleanCollection.add(true);
        input2BooleanCollection.add(false);
        input2BooleanCollection.add(true);
        input2BooleanCollection.add(false);

        ArrayList<Integer> outputCollection;

        int numberOfDatasetType = datasetTypeCollection.size();

        for(int counter = 0;counter<numberOfDatasetType;counter++){
            // reinitialize input Output Collection
            inputOutputValueCollection = new HashMap<>();


            // get dataset type, and, nand, or, nor
            String datasetType = datasetTypeCollection.get(counter);

            // get outputCollection for dataset type
            outputCollection =getOutputBasedOnInput(datasetType,input1BooleanCollection,input2BooleanCollection);

            // store input and output in hashmap
            inputOutputValueCollection.put("input1",input1Collection);
            inputOutputValueCollection.put("input2",input2Collection);
            inputOutputValueCollection.put("output",outputCollection);

            // store hashmap into hashmap for datatype
            datasetCollection.put(datasetType,inputOutputValueCollection);

        }

    }

    /**
     * Initialize output based on dataset type and input values
     * @param datasetType - type of dataset used
     * @param input1BooleanCollection - boolean value for input 1
     * @param input2BooleanCollection - boolean value for input 2
     * @return
     */
    private ArrayList<Integer> getOutputBasedOnInput(String datasetType, ArrayList<Boolean> input1BooleanCollection, ArrayList<Boolean> input2BooleanCollection){
        ArrayList<Integer> outputBooleanCollection = new ArrayList<Integer>();
        for (int counter = 0;counter < 4;counter++){
            int outputValue1 = 0;
            if(datasetType.equals("AND")){
                outputValue1= (input1BooleanCollection.get(counter) && input2BooleanCollection.get(counter))? 1:0;
            }
            else if(datasetType.equals("NAND")){
                outputValue1= (!(input1BooleanCollection.get(counter) && input2BooleanCollection.get(counter)))?1:0;
            }
            else if(datasetType.equals("OR")){
                outputValue1= (input1BooleanCollection.get(counter) || input2BooleanCollection.get(counter))?1:0;
            }
            else if(datasetType.equals("NOR")){
                outputValue1= (!(input1BooleanCollection.get(counter) || input2BooleanCollection.get(counter)))?1:0;
            }
            outputBooleanCollection.add(outputValue1);

        }
        return outputBooleanCollection;
    }



}
