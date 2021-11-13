package com.example.ml_visualized.controller.simulateButtonTouch;

import android.view.MotionEvent;
import android.view.View;

public class SimulateButtonTouch {
    public void simulateButtonOnTouch(View view){
        view.setOnTouchListener(new View.OnTouchListener() {
            // override the onTouch method to change alpha
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getAction()) {

                    // if user is touching on the button
                    case MotionEvent.ACTION_DOWN: {

                        // set alpha to half
                        view.setAlpha(0.5f);
                        break;
                    }

                    // if user cancelled touching action or ahs lifted touch
                    case MotionEvent.ACTION_CANCEL:
                    case MotionEvent.ACTION_UP: {

                        // set alpha back to default value
                        view.setAlpha(1f);
                        break;
                    }
                }

                return false;
            }
        });
    }
}
