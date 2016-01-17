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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_einkaufsliste);

        liste = (ListView) findViewById(R.id.listViewEinkauf);
        edit = (EditText) findViewById(R.id.editTextEinkauf);



        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new ArrayList<String>());
        liste.setAdapter(adapter);


    }

    public void addToList(View view){
        adapter.add(edit.getText().toString());
        adapter.notifyDataSetChanged();
        edit.setText("");
    }

    public void clearList (View view){
        adapter.clear();
        adapter.notifyDataSetChanged();
    }



}
