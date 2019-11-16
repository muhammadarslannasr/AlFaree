package com.thexsolution.propertyprojectf11.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.thexsolution.propertyprojectf11.Activities.DashbaordActivity;
import com.thexsolution.propertyprojectf11.Activities.RentReminderActivity;
import com.thexsolution.propertyprojectf11.Activities.SaleReminderActivity;
import com.thexsolution.propertyprojectf11.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReminderFragment extends Fragment {

    private LinearLayout rentreminderID,salereminderID;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reminder, container, false);
        rentreminderID = view.findViewById(R.id.rentreminderID);
        salereminderID = view.findViewById(R.id.salereminderID);

        salereminderID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getActivity(),SaleReminderActivity.class));
            }
        });

        rentreminderID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getActivity(),RentReminderActivity.class));
            }
        });

        return view;
    }

}
