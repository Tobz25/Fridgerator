package com.fridge.tobi.fridgerator.Fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fridge.tobi.fridgerator.Activities.CreateRecipeActivity;
import com.fridge.tobi.fridgerator.Activities.SearchRecipeActivity;
import com.fridge.tobi.fridgerator.R;

import java.util.List;

/**
 * Fragment to display ingredients added by a uer dynamically
 */
public class IngredientsAddedFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Bundle args = getArguments();

        //message is the text typed in by the user and represents an ingredient
        String message = args.getString("index");

       View view = inflater.inflate(R.layout.fragment_ingredients_added, container, false);
        final LinearLayout ingredientsConatainer = (LinearLayout) view.findViewById(R.id.ingredients_container);


        //Create a new TextView element
        final TextView text = new TextView(getActivity());
        text.setTextSize(20);
        //Displayed test is the ingredient name
        text.setText(message);
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        p.weight = 3;
        text.setLayoutParams(p);

        //onClickListener enables to delete the added ingredient TextView by click on it
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View view = v;
                //Before delete, show a message and ask for confirmation that user wants to delete the entry
                AlertDialog.Builder ingredAlert = new AlertDialog.Builder(getContext());
                ingredAlert.setMessage(getResources().getString(R.string.deleteMessage));
                ingredAlert.setPositiveButton(getResources().getString(R.string.confirmDeleteButton), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //remove the TExtView with the ingredient and remove from the ingredient from the List containing
                        // the current ingredients (e.g. for database operations)
                        ingredientsConatainer.removeView(view);
                        List<String> ingredientsList;
                        // if the fragment is started in the scope of the CreateRecipeActivity
                        if(SearchRecipeActivity.ingredientsList == null)
                            ingredientsList = CreateRecipeActivity.createRecipeIngredientsList;

                        // if the fragment is started in the scope of the SearchRecipeActivity
                        else
                            ingredientsList= SearchRecipeActivity.ingredientsList;
                        for (String entry : ingredientsList) {
                            if (text.getText().equals(entry)) {
                                ingredientsList.remove(entry);
                            }
                        }
                    }
                });
                ingredAlert.create().show();
            }
        });
        ingredientsConatainer.addView(text);


        return view;
    }

    public void deleteItem(View v){

    }
}
