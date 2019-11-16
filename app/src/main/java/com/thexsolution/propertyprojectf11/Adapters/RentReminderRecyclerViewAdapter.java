package com.thexsolution.propertyprojectf11.Adapters;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.thexsolution.propertyprojectf11.Model.DoubleNumber;
import com.thexsolution.propertyprojectf11.Model.Reminder;
import com.thexsolution.propertyprojectf11.Model.Survey;
import com.thexsolution.propertyprojectf11.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.thexsolution.propertyprojectf11.Activities.RentReminderActivity.db;
import static com.thexsolution.propertyprojectf11.Activities.RentReminderActivity.your_array_list_rent_reminder;

/**
 * Created by ArslanNasr on 5/26/2018.
 */

public class RentReminderRecyclerViewAdapter extends RecyclerView.Adapter<RentReminderRecyclerViewAdapter.ViewHolder> {

    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private AlertDialog.Builder alertDialogBuilder;
    private AlertDialog dialogdelete;
    private LayoutInflater inflater;
    private Spinner rentorsaleId;
    private Context context;
    private EditText person_nameID,owner_pd_numberID,mobileSecondNumberID,rentID,adTitleID,descriptionID;
    private TextView main_textID;
    private Button submitbuttonId;
    private ImageView adbtnID;
    private List<Reminder> reminderList;
    private List<DoubleNumber> numberList;
    private ShowDualNumberRecyclerViewAdapter showDualNumberRecyclerViewAdapter;
    private String rentListSpinner = null;
    FirebaseDatabase mDatabase;
    DatabaseReference mDatabaseReferrence,mDatabaseReference;
    private ProgressDialog mProgressDialog,progressDialog;

    public RentReminderRecyclerViewAdapter(Context context, List<Reminder> surveyList) {
        this.context = context;
        this.reminderList = surveyList;
        this.numberList = new ArrayList<>();
    }

    @NonNull
    @Override
    public RentReminderRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row,parent,false);
        return new RentReminderRecyclerViewAdapter.ViewHolder(view,context);
    }

    @Override
    public void onBindViewHolder(@NonNull RentReminderRecyclerViewAdapter.ViewHolder holder, int position) {

        Reminder reminder = reminderList.get(position);

        if (reminder.getMobileSecondNumberID().isEmpty()){
            holder.number2.setText("Number 2 not given!");
        }else {
            holder.number2.setText("Number 2:"+ "\t" + reminder.getMobileSecondNumberID());
        }
        holder.title.setText("Title:"+"\t"+reminder.getAdTitleID());
        holder.description.setText("Description:"+"\t"+reminder.getDescriptionID());
        holder.name.setText(reminder.getPerson_nameID());
        holder.rent.setText("Rent:" + "\t" + "\t" + reminder.getRentID() + "\t" + "Rs/-");
        holder.number1.setText("Number 1:"+ "\t" + reminder.getOwner_pd_numberID());

        holder.dateAdded.setText(reminder.getTimestamp());

        if(your_array_list_rent_reminder.contains(reminder.getRentReminder_nodeID())){
            holder.favourites_images.setImageResource(R.drawable.ic_like);
        }else {
            holder.favourites_images.setImageResource(R.drawable.ic_thumb_up);
        }
    }

    public void setfilter(List<Reminder> itemList){
        reminderList = new ArrayList<>();
        reminderList.addAll(itemList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return reminderList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name, number1,number2, rent,title,description,dateAdded;
        public Button callButton, editButton, deleteButton;
        public DatabaseReference databaseReference;
        public FirebaseAuth mAuth;
        public FirebaseUser mUser;
        public ImageView favourites_images;
        public ViewHolder(View itemView, final Context ctx) {
            super(itemView);

            context = ctx;
            mAuth = FirebaseAuth.getInstance();
            mUser = mAuth.getCurrentUser();
            name = itemView.findViewById(R.id.name);
            number1 = itemView.findViewById(R.id.number1);
            number2 = itemView.findViewById(R.id.number2);
            rent = itemView.findViewById(R.id.rent);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            dateAdded = itemView.findViewById(R.id.dateAdded);
            callButton = itemView.findViewById(R.id.callButton);
            editButton = itemView.findViewById(R.id.editButton);
            deleteButton = itemView.findViewById(R.id.deleteButton);
            favourites_images = itemView.findViewById(R.id.favourites_images);

            favourites_images.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int position = getAdapterPosition();
                    Reminder reminder = reminderList.get(position);

                    if (your_array_list_rent_reminder.contains(reminder.getRentReminder_nodeID())) {
                        favourites_images.setImageResource(R.drawable.ic_thumb_up);
                        db.execSQL("DELETE FROM TableFavourites WHERE image='" + reminder.getRentReminder_nodeID() + "'");
                        your_array_list_rent_reminder.remove(reminder.getRentReminder_nodeID());
                        Toast.makeText(ctx, "UnDone!", Toast.LENGTH_SHORT).show();
                    } else if (!your_array_list_rent_reminder.contains(reminder.getRentReminder_nodeID())) {
                        favourites_images.setImageResource(R.drawable.ic_like);
                        db.execSQL("INSERT INTO TableFavourites VALUES(NULL,'" + reminder.getRentReminder_nodeID() + "');");
                        your_array_list_rent_reminder.add(reminder.getRentReminder_nodeID());
                        Toast.makeText(ctx, "Done!", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            callButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    numberList.clear();
                    int pos = getAdapterPosition();
                    Reminder reminder = reminderList.get(pos);
                    displayDualNumbers(reminder.getOwner_pd_numberID(), reminder.getMobileSecondNumberID(), context);
                }
            });

            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    dialogBuilder = new AlertDialog.Builder(context);
                    dialogBuilder.setMessage("Are you sure you want to delete?");

                    dialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            mAuth= FirebaseAuth.getInstance();
                            mUser=mAuth.getCurrentUser();
                            int position = getAdapterPosition();
                            Reminder reminder = reminderList.get(position);
                            databaseReference = FirebaseDatabase.getInstance().getReference().child("RentReminder").child(mUser.getUid()).child(reminder.getRentReminder_nodeID());
                            databaseReference.removeValue();
                            reminderList.remove(getAdapterPosition());
                            notifyItemRemoved(getAdapterPosition());
                            dialog.dismiss();

                        }
                    });

                    dialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            dialog.dismiss();
                        }
                    });

                    dialogdelete = dialogBuilder.create();
                    dialogdelete.show();

                }
            });

            editButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int pos = getAdapterPosition();
                    final Reminder reminder = reminderList.get(pos);


                    dialogBuilder = new AlertDialog.Builder(context);
                    inflater = LayoutInflater.from(context);
                    View view1 = inflater.inflate(R.layout.rent_reminder_dialog_add_layout_update, null);
                    main_textID = view1.findViewById(R.id.main_textID);
                    person_nameID = view1.findViewById(R.id.person_nameID);
                    owner_pd_numberID = view1.findViewById(R.id.owner_pd_numberID);
                    mobileSecondNumberID = view1.findViewById(R.id.mobileSecondNumberID);
                    rentID = view1.findViewById(R.id.rentID);
                    adTitleID = view1.findViewById(R.id.adTitleID);
                    descriptionID = view1.findViewById(R.id.descriptionID);
                    submitbuttonId = view1.findViewById(R.id.submitbuttonId);
                    rentorsaleId = view1.findViewById(R.id.rentorsaleId);

                    rentOrSaleSpinner();

                    main_textID.setText("Reminder Rent Updation Here!");

                    person_nameID.setText(reminder.getPerson_nameID());
                    person_nameID.setSelection(person_nameID.getText().length());
                    if (reminder.getMobileSecondNumberID().isEmpty()){
                        mobileSecondNumberID.setText("Number not Given");
                    }else {
                        mobileSecondNumberID.setText(reminder.getMobileSecondNumberID());
                    }
                    owner_pd_numberID.setText(reminder.getOwner_pd_numberID());
                    rentID.setText(reminder.getRentID());
                    adTitleID.setText(reminder.getAdTitleID());
                    descriptionID.setText(reminder.getDescriptionID());

                    mProgressDialog = new ProgressDialog(context);
                    mAuth = FirebaseAuth.getInstance();
                    mDatabase = FirebaseDatabase.getInstance();
                    mUser = mAuth.getCurrentUser();
                    mDatabaseReferrence = FirebaseDatabase.getInstance().getReference().child("RentReminder").child(mUser.getUid()).child(reminder.getRentReminder_nodeID());

                    submitbuttonId.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            mProgressDialog.setMessage("Updated Data....");
                            mProgressDialog.setCanceledOnTouchOutside(false);
                            mProgressDialog.show();

                            //DatabaseReference newPost = mDatabaseReferrence.push();
                            String ID = mDatabaseReferrence.getKey();
                            Map<String,String> data = new HashMap<>();
                            data.put("person_nameID",person_nameID.getText().toString().trim());
                            reminder.setPerson_nameID(person_nameID.getText().toString().trim());
                            data.put("owner_pd_numberID",owner_pd_numberID.getText().toString().trim());
                            reminder.setOwner_pd_numberID(owner_pd_numberID.getText().toString().trim());
                            data.put("timestamp",String.valueOf(java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime())));
                            reminder.setTimestamp(String.valueOf(java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime())));
                            data.put("rentReminder_nodeID",ID);
                            data.put("mobileSecondNumberID",mobileSecondNumberID.getText().toString().trim());
                            reminder.setMobileSecondNumberID(mobileSecondNumberID.getText().toString().trim());
                            data.put("rentID",rentID.getText().toString().trim());
                            reminder.setRentID(rentID.getText().toString().trim());
                            data.put("rentorsaleId",rentListSpinner);
                            reminder.setRentorsaleId(rentListSpinner);
                            data.put("adTitleID",adTitleID.getText().toString().trim());
                            reminder.setAdTitleID(adTitleID.getText().toString().trim());
                            data.put("descriptionID",descriptionID.getText().toString().trim());
                            reminder.setDescriptionID(descriptionID.getText().toString().trim());
                            mDatabaseReferrence.setValue(data);
                            notifyItemChanged(getAdapterPosition(),reminder);
                            Toast.makeText(context, "Rent Reminder Updated!", Toast.LENGTH_SHORT).show();
                            mProgressDialog.dismiss();
                            dialog.dismiss();
                        }
                    });


                    dialogBuilder.setView(view1);
                    dialog = dialogBuilder.create();
                    dialog.show();
                }
            });

        }

    }

    private void displayDualNumbers(String firstNumber,String secondNumber, final Context context) {
        numberList.add(new DoubleNumber(firstNumber));
        numberList.add(new DoubleNumber(secondNumber));
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.numbers_design);
        RecyclerView recylerViewID = (RecyclerView) dialog.findViewById(R.id.recylerViewID);
        Button cancel_btnID = (Button) dialog.findViewById(R.id.cancel_btnID);
        TextView chooseLanguageID = (TextView) dialog.findViewById(R.id.chooseLanguageID);
        //chooseLanguageID.setText("Call to" + "\t" + "\t" +contactName);
        recylerViewID.setLayoutManager(new LinearLayoutManager(context));
        showDualNumberRecyclerViewAdapter = new ShowDualNumberRecyclerViewAdapter(numberList, context);
        recylerViewID.setAdapter(showDualNumberRecyclerViewAdapter);
        dialog.show();
    }

    public void rentOrSaleSpinner(){
        final List<String> rentOrSaleList = new ArrayList<>();

        rentOrSaleList.add("Rent");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item,rentOrSaleList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        rentorsaleId.setAdapter(arrayAdapter);
        rentorsaleId.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((TextView) adapterView.getChildAt(0)).setTextColor(Color.BLACK);
                rentListSpinner = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
}
