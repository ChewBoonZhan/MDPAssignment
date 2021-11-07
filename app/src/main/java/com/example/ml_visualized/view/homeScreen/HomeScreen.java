package com.example.ml_visualized.view.homeScreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.ml_visualized.R;
import com.example.ml_visualized.view.homeScreen.fragments.HomeDescFragment;
import com.example.ml_visualized.view.homeScreen.fragments.HomeLessonFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeScreen extends AppCompatActivity {

    // for the menu at the top of screen
    private BottomNavigationView bottomNavigationView;

    private Fragment homeScreenSelectedFragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);

        setFragment();

        getHomeScreenComponents();


    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    private void setFragment(){
        homeScreenSelectedFragment = new HomeLessonFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.home_frame_layout,homeScreenSelectedFragment).commit();
    }

    private void getHomeScreenComponents(){
        // get navigation to set onClick
        bottomNavigationView = findViewById(R.id.home_action_bar_navigation_bar);

        // set the item selected listener for the menu navigation
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationListener);

    }


    private BottomNavigationView.OnNavigationItemSelectedListener navigationListener =
            new BottomNavigationView.OnNavigationItemSelectedListener(){

                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    // switch depending on which fragment is selected
                    switch(item.getItemId()){
                        case(R.id.nav_home_lesson_button):
                            // user chose lesson fragment
                            homeScreenSelectedFragment = new HomeLessonFragment();
                            break;
                        case(R.id.nav_home_desc_button):
                            // user choose home fragment
                            homeScreenSelectedFragment = new HomeDescFragment();
                            break;
                    }
                    // change to the new fragment in home screen activity
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.home_frame_layout,homeScreenSelectedFragment).commit();
                    return true;
                }
            };

    public void lessonOnClick(View view){



        // get default directory to lesson screen
        String activityClassName = getResources().getString(R.string.default_dir) + "view.lessonScreen.LessonScreen";

        // creating new intent
        Intent intent = null;
        try{

            // creating new intent based on default directory to lesson screen
            intent = new Intent(this,Class.forName(activityClassName));

            // add in intent information for type of screen to go to
            intent.putExtra("lesson_type",view.getContentDescription());
        }
        catch(Exception e){
            e.printStackTrace();
        }

        // start intent
        startActivity(intent);


    }

    public void unDevelopedLessonOnClick(View view){
        Toast toast = Toast.makeText(this, "This lesson is not developed in Demo.",Toast.LENGTH_SHORT);
        toast.show();
    }



}