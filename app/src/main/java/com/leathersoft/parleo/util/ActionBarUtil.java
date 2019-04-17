package com.leathersoft.parleo.util;

import android.app.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class ActionBarUtil {
    private ActionBarUtil() {
    }

    public static void setDisplayHomeAsUpState(Activity activity,boolean state){

        if(activity == null){
            return;
        }

        if (activity instanceof AppCompatActivity) {
            ActionBar actionBar = ((AppCompatActivity) activity).getSupportActionBar();
            if(actionBar != null){
                actionBar.setHomeButtonEnabled(state); // disable the button
                actionBar.setDisplayHomeAsUpEnabled(state); // remove the left caret
                actionBar.setDisplayShowHomeEnabled(state); // remove the icon
            }
        }
    }



}
