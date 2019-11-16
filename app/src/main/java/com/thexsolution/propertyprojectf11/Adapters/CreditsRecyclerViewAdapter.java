package com.thexsolution.propertyprojectf11.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.thexsolution.propertyprojectf11.Model.Credit;
import com.thexsolution.propertyprojectf11.R;

import java.util.List;


public class CreditsRecyclerViewAdapter extends RecyclerView.Adapter<CreditsRecyclerViewAdapter.ViewHolder> {

    Context context1;
    List<Credit> stringList;

    public CreditsRecyclerViewAdapter(Context context, List<Credit> list) {
        context1 = context;
        this.stringList = list;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public CardView cardView;
        public TextView iconNameID;
        public TextView iconmadebyID;
        public TextView creativeCommonID;

        public ViewHolder(View view) {

            super(view);

            cardView = (CardView) view.findViewById(R.id.card_view);
            iconNameID = (TextView) view.findViewById(R.id.iconNameID);
            iconmadebyID = (TextView) view.findViewById(R.id.iconmadebyID);
            creativeCommonID = (TextView) view.findViewById(R.id.creativeCommonID);
        }
    }

    @Override
    public CreditsRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view2 = LayoutInflater.from(context1).inflate(R.layout.credits_author_cardview_layout_property, parent, false);

        ViewHolder viewHolder = new ViewHolder(view2);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        final Credit credit = stringList.get(position);
        viewHolder.iconNameID.setText(credit.getNameIcon());
        viewHolder.iconmadebyID.setText(credit.getIconMadeBy());
        viewHolder.creativeCommonID.setText(credit.getCreativeCommon());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(credit.getIconURL()));
                context1.startActivity(i);
            }
        });

        viewHolder.creativeCommonID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(credit.getAttributionURL()));
                context1.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }
}
