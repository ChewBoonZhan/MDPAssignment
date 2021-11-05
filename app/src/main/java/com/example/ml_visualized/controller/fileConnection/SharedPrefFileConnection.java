package com.example.ml_visualized.controller.fileConnection;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefFileConnection {

    private SharedPrefFileConnectionKeys sharedPrefFileConnectionKeys = new SharedPrefFileConnectionKeys();

    // key to access shared preferences for storing small data on app
    private String SHARED_PREF_FILE_KEY = "defaultFile";


    // context for shared preferences for storing datas
    private Context context;

    // shared preferences is used to store small data for the app
    private SharedPreferences sharedPrefFileConnection;

    // editor to store and edit data inside the shared preferences
    private SharedPreferences.Editor editor;

    // default user has an empty name
    private String defaultUserName = "";

    public SharedPrefFileConnection(Context context){
        this.context = context;

        initFile();

    }

    private void initFile(){
        // get the shared pref of the file name
        sharedPrefFileConnection = context.getSharedPreferences(SHARED_PREF_FILE_KEY, Context.MODE_PRIVATE);

        // editor to edit the data in the file
        editor = sharedPrefFileConnection.edit();
    }

    public String getUserName(){
        return sharedPrefFileConnection.getString(sharedPrefFileConnectionKeys.getUserNameKeys(),defaultUserName);


    }

    public void storeUserName(String userName){

        editor = sharedPrefFileConnection.edit();

        // store username into the sharedpref file storage
        editor.putString(sharedPrefFileConnectionKeys.getUserNameKeys(),userName);
        editor.apply();
    }



}
