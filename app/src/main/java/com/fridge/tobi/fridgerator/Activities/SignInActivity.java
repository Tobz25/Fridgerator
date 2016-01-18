package com.fridge.tobi.fridgerator.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.fridge.tobi.fridgerator.R;

public class SignInActivity extends AppCompatActivity {

    /*
    * This method is run when the Activity is created.
    * Refrences the layout we want to use.
    * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
    }

    /*
    * Starts the "MainActivity" activity.
    * This method is called when we click the "LogIn" Button.
    * */
    public void startMainActivity (View v){
        Intent intent = new Intent();
        intent.setClass(this, MainActivity.class);
        startActivity(intent);
    }
}
