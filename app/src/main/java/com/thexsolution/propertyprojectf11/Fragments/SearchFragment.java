package com.thexsolution.propertyprojectf11.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.thexsolution.propertyprojectf11.Activities.DashbaordActivity;
import com.thexsolution.propertyprojectf11.Activities.RentSurveySearchActivity;
import com.thexsolution.propertyprojectf11.Activities.SaleSurveySearchActivity;
import com.thexsolution.propertyprojectf11.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {

    private LinearLayout commercialID,quitID;


    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        commercialID = view.findViewById(R.id.commercialID);
        quitID = view.findViewById(R.id.quitID);

        commercialID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getActivity(),RentSurveySearchActivity.class));
            }
        });

        quitID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getActivity(),SaleSurveySearchActivity.class));
            }
        });

        return view;
    }

}
