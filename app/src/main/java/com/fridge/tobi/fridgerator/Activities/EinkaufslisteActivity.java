package com.fridge.tobi.fridgerator.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.fridge.tobi.fridgerator.R;

import java.util.ArrayList;

public class EinkaufslisteActivity extends AppCompatActivity {

    ListView liste;
    EditText edit;
    ArrayAdapter<String> adapter;

    /*
    * This method is run when the Activity is created.
    * Refrences to the layout we want to use.
    * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_einkaufsliste);

        //Add the Layouts to the ListView, EditText
        liste = (ListView) findViewById(R.id.listViewEinkauf);
        edit = (EditText) findViewById(R.id.editTextEinkauf);

        //Create the adapter, that fills our ArrayList
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new ArrayList<String>());
        liste.setAdapter(adapter);
    }

    /*
    * This method adds the ingredients from the EditText box into our ArrayList
    * This method is called by the "Hinzugügen" Button.
    * */
    public void addToList(View view){
        adapter.add(edit.getText().toString());
        adapter.notifyDataSetChanged();
        edit.setText("");
    }

    /*
    * This method clears our ArrayList from all entries.
    * This method is called by the "Einkaufsliste löschen" Button.
    * */
    public void clearList (View view){
        adapter.clear();
        adapter.notifyDataSetChanged();
    }



}
