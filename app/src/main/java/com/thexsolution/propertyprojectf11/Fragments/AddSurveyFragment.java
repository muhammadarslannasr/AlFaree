package com.thexsolution.propertyprojectf11.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.thexsolution.propertyprojectf11.Activities.DashbaordActivity;
import com.thexsolution.propertyprojectf11.Activities.RentDataViewActivity;
import com.thexsolution.propertyprojectf11.Activities.SaleActivity;
import com.thexsolution.propertyprojectf11.Activities.SaleDataViewActivity;
import com.thexsolution.propertyprojectf11.Activities.SurveyRentActivity;
import com.thexsolution.propertyprojectf11.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddSurveyFragment extends Fragment {

    private LinearLayout surveyID,rentID,surveysaleID,saledataViewID,rentreminderID,salereminderID,commercialID,quitID;

    public AddSurveyFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View  view = inflater.inflate(R.layout.fragment_add_survey, container, false);
        surveyID = view.findViewById(R.id.surveyID);
        rentID = view.findViewById(R.id.rentID);
        surveysaleID = view.findViewById(R.id.surveysaleID);
        saledataViewID = view.findViewById(R.id.saledataViewID);

        surveysaleID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getActivity(),SaleActivity.class));
            }
        });

        saledataViewID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getActivity(),SaleDataViewActivity.class));
            }
        });

        surveyID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),SurveyRentActivity.class));
            }
        });

        rentID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),RentDataViewActivity.class));
            }
        });

        return view;
    }

}
