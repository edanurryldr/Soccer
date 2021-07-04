package com.edanuryildirim.futbolapi.view;

import android.content.Context;
import android.content.SharedPreferences;

public class savesharePrefences {

    Context context;
    SharedPreferences sharedPreferences;

    public savesharePrefences(Context context){
        this.context=context;
        sharedPreferences=context.getSharedPreferences("apreferences",Context.MODE_PRIVATE);


    }
    public void setState(Boolean bo){
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putBoolean("editor",bo);
        editor.apply();
    }
    public  boolean getState(){
        return sharedPreferences.getBoolean("editor",false);
    }
}
