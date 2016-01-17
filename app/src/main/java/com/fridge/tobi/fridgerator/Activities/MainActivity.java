package com.fridge.tobi.fridgerator.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.fridge.tobi.fridgerator.Database.DBHelper;
import com.fridge.tobi.fridgerator.R;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;

public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "com.fridge.tobi.fridgerator.MESSAGE";

    DBHelper myDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fillDatabase();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void startSearch (View v) {
        Intent intent = new Intent(this, SearchRecipeActivity.class);
        startActivity(intent);

    }

    public void startCreate (View v) {
        Intent intent = new Intent(this, CreateRecipeActivity.class);
        startActivity(intent);
    }

    /**
     * Create the internal database and fill it with data from the file in the assets folder
     */
    public void fillDatabase(){

        myDbHelper = new DBHelper(this);

        try{
            myDbHelper.createDataBase();
        }
        catch (IOException ioe) {
            throw new Error ("Unable to create database");
        }
    }

    public void startImpressum (View v) {
        Intent intent = new Intent();
        intent.setClass(this, ImpressumActivity.class);
        startActivity(intent);
    }

    public void startEinkaufsliste (View v) {
        Intent intent = new Intent();
        intent.setClass(this, EinkaufslisteActivity.class);
        startActivity(intent);
    }


}
