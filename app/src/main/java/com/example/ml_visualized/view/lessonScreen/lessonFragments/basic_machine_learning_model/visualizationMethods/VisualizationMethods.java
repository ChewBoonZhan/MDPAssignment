package com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.visualizationMethods;

import com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model.LessonVisualizationFragment;

public class VisualizationMethods {



    private final int NO_ACTION = 0;
    private final int REDUCE_WEIGHT = 1;
    private final int INCREASE_WEIGHT = 2;
    private final int REMAIN_WEIGHT = 3;

    public int getREMAIN_WEIGHT(){
        return REMAIN_WEIGHT;
    }

    public int getNO_ACTION(){
        return NO_ACTION;
    }

    public int getREDUCE_WEIGHT(){

        return REDUCE_WEIGHT;
    }

    public int getINCREASE_WEIGHT(){

        return INCREASE_WEIGHT;
    }


    public int step(LessonVisualizationFragment lessonVisualizationFragment, String textDescription) {

        return getNO_ACTION();
    }

}
