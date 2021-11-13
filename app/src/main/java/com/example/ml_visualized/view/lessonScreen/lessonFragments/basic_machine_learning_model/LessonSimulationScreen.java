package com.example.ml_visualized.view.lessonScreen.lessonFragments.basic_machine_learning_model;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.ml_visualized.R;

public class LessonSimulationScreen extends Fragment {
    private View view;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.basic_machine_learning_visualization_fragment, container, false);

        return view;
    }
}
