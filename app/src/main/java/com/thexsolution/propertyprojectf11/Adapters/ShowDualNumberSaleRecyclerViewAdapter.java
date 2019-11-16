package com.thexsolution.propertyprojectf11.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.thexsolution.propertyprojectf11.Model.DoubleNumber;
import com.thexsolution.propertyprojectf11.R;

import java.util.List;

/**
 * Created by ArslanNasr on 2/28/2019.
 */

public class ShowDualNumberSaleRecyclerViewAdapter extends RecyclerView.Adapter<ShowDualNumberSaleRecyclerViewAdapter.ViewHolder>  {

    private List<DoubleNumber> doubleNumberList;
    private Context context;

    public ShowDualNumberSaleRecyclerViewAdapter(List<DoubleNumber> doubleNumberList, Context context) {
        this.doubleNumberList = doubleNumberList;
        this.context = context;
    }

    @NonNull
    @Override
    public ShowDualNumberSaleRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dualnumber_row_updated,parent,false);
        return new ShowDualNumberSaleRecyclerViewAdapter.ViewHolder(view,context);
    }

    @Override
    public void onBindViewHolder(@NonNull final ShowDualNumberSaleRecyclerViewAdapter.ViewHolder holder, final int position) {
        final DoubleNumber doubleNumber = doubleNumberList.get(position);
        if (doubleNumber.getNumber1().isEmpty()){
            holder.mobileNumberID.setText("Owner have only one Number");
        }else {
            holder.mobileNumberID.setText(doubleNumber.getNumber1());
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (holder.mobileNumberID.getText().toString().equals("Owner have only one Number")){
                    Toast.makeText(context, "Sorry this is not a number!", Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + doubleNumber.getNumber1()));
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return doubleNumberList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mobileNumberID;
        public ViewHolder(View itemView, Context ctx) {
            super(itemView);
            context = ctx;

            mobileNumberID = (TextView) itemView.findViewById(R.id.mobileNumberID);

        }
    }
}
