package com.fridge.tobi.fridgerator.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fridge.tobi.fridgerator.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link IngredientsAddedFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link IngredientsAddedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class IngredientsAddedFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Bundle args = getArguments();
        String message = args.getString("index");

       View view = inflater.inflate(R.layout.fragment_ingredients_added, container, false);
        LinearLayout ingredientsConatainer = (LinearLayout) view.findViewById(R.id.ingredients_container);
        TextView text = new TextView(getActivity());
        text.setTextSize(20);
        text.setText(message);
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        p.weight = 3;
        text.setLayoutParams(p);
        ingredientsConatainer.addView(text);




        return view;
    }
}
