package com.example.ml_visualized.customToast;

import android.content.Context;
import android.graphics.PorterDuff;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.example.ml_visualized.R;

public class CustomToast {
    // default text colour of a toast
    private final int TOAST_TEXT_COLOuR = R.color.white;

    private final int TOAST_BACKGROUND_COLOUR = R.color.background_light;

    // default text toast is centered in a toast
    private final int TOAST_TEXT_GRAVITY = Gravity.CENTER;

    // context for showing toast to user
    private Context context;

    public CustomToast(Context context){
        this.context = context;
    }

    public void showToast(String messageToast, boolean longToast){
        int toastDuration;
        if(longToast){
            toastDuration = Toast.LENGTH_LONG;
        }
        else{
            toastDuration = Toast.LENGTH_SHORT;
        }

        Toast toast = Toast.makeText(this.context, messageToast, toastDuration);

        View view = new View(context);



        // get the view of toast to change attribute of the toast
//        TextView text = (TextView) toast.getView().findViewById(android.R.id.message);

        // gets actual background color of toast.
//        int background_color = ContextCompat.getColor(context, TOAST_BACKGROUND_COLOUR);

        // set the background colour to the background colour input into the function
//        view.getBackground().setColorFilter((background_color), PorterDuff.Mode.SRC_IN);

        // get textview from toast to change the gravity
//        TextView text = view.findViewById(android.R.id.message);

        // get integer based on toast_text_color
//        int string_color = ContextCompat.getColor(context, TOAST_TEXT_COLOuR);

        // set the toast text color to default text color
//        text.setTextColor((string_color));

        // set the toast text gravity based on default gravity
//        text.setGravity(TOAST_TEXT_GRAVITY);

//        toast.setView(view);

        toast.show();


        
    }

}
