package com.thexsolution.propertyprojectf11.Adapters;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.thexsolution.propertyprojectf11.Activities.InternalClickFullDataActivity;
import com.thexsolution.propertyprojectf11.Model.DoubleNumber;
import com.thexsolution.propertyprojectf11.Model.Survey;
import com.thexsolution.propertyprojectf11.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.thexsolution.propertyprojectf11.Activities.RentDataViewActivity.db;
import static com.thexsolution.propertyprojectf11.Activities.RentDataViewActivity.your_array_list;
import static com.thexsolution.propertyprojectf11.Activities.RentSurveySearchRecyclerViewActivity.your_array_list_rent_survey_10PLUS_10MINUS;

/**
 * Created by ArslanNasr on 5/26/2018.
 */

public class SurveyRentRecyclerViewAdapter extends RecyclerView.Adapter<SurveyRentRecyclerViewAdapter.ViewHolder> {

    private EditText sector_nameID,street_numberID,house_numberID,owner_pd_numberID,bedroomID,bathID,tvLaunchID,drying_roomID,dinning_roomID;
    private EditText servant_roomID,advanceID,securityID,rentID,areaUnitID,adTitleID,descriptionID,mobileSecondNumberID;
    private CheckBox car_parkingCheckBoxID,seperate_gasCheckBoxId,sep_elecCheckBoxId,motorBorCheckBoxID,fullHouseCheckBoxId,gateSeperateCheckBoxId;
    private Button submitbuttonId;
    private Spinner furnishId,areaSpinnerId,floorSpinnerId,rentorsaleId,ownerorpropertyID;
    private ImageView adbtnID;
    public boolean isClickedFirstTime = true;
    String furnishListSpinner;
    String areaListSpinner;
    String floorListSpinner;
    String rentorsaleListSpinner;
    String ownerorpropertyListSpinner;
    String carCheckBoxChecked = null;
    String seperategasChecked = null;
    String sepElecChecked = null;
    String motorBorChecked = null;
    String fullHouseChecked = null;
    String gateSeperateChecked = null;
    String nodeID = null;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    FirebaseDatabase mDatabase;
    DatabaseReference mDatabaseReferrence;
    private ProgressDialog mProgressDialog;

    private AlertDialog.Builder dialogBuilder;
    private AlertDialog alertDialog;
    private AlertDialog.Builder alertDialogBuilder;
    private AlertDialog dialogdelete;
    private LayoutInflater inflater;
    private Context context;
    private List<Survey> surveyList;
    private List<DoubleNumber> numberList;
    public static final String ACTION_SECTOR_NAME = "action_sector_name";
    public static final String ACTION_STREET_NUMBER = "action_street_number";
    public static final String ACTION_ONWER_OR_PROPERTY_NUMBER = "action_owner_or_property_number";
    public static final String ACTION_HOUSE_NUMBER = "action_house_number";
    public static final String ACTION_OWNER_PD_NUMBER = "action_owner_pd_number";
    public static final String ACTION_BEDROOM = "action_bedroom";
    public static final String ACTION_BATH = "action_bath";
    public static final String ACTION_TV_LAUNCH = "action_tv_launch";
    public static final String ACTION_DRYING_ROOM = "action_drting_room";
    public static final String ACTION_DINNING_ROOM = "action_dinning_room";
    public static final String ACTION_SERVANT_ROOM = "action_servant_room";
    public static final String ACTION_ADVANCE = "action_advance";
    public static final String ACTION_SECURITY = "action_security";
    public static final String ACTION_OWNER_SECOND_NUMBER = "action_owner_second_number";
    public static final String ACTION_RENT = "action_rent";
    public static final String ACTION_RENT_OR_SALE = "action_rent_or_sale";
    public static final String ACTION_FURNISH = "action_furnish";
    public static final String ACTION_CAR_PARK = "action_car_park";
    public static final String ACTION_SEP_GAS = "action_sep_gas";
    public static final String ACTION_SEP_ELEC = "action_sep_elec";
    public static final String ACTION_MOTOR_BOR = "action_motor_bor";
    public static final String ACTION_FULL_HOUSE = "action_full_house";
    public static final String ACTION_GATE_SEP = "action_gate_sep";
    public static final String ACTION_AREA = "action_area";
    public static final String ACTION_AREA_UNIT = "action_area_unit";
    public static final String ACTION_FLOOR_LEVELS = "action_floor_levels";
    public static final String ACTION_AD_TITLE = "action_ad_title";
    public static final String ACTION_DESCRIPTION = "action_description";
    public static final String ACTION_DATE = "action_date";
    public static final String ACTION_NODE_ID = "action_node_id";
    DoubleNumber doubleNumber;
    int rentVale = 0;
    int rentlenght = 0;
    private ShowDualNumberRecyclerViewAdapter showDualNumberRecyclerViewAdapter;

    public SurveyRentRecyclerViewAdapter(Context context, List<Survey> surveyList,int rentVale) {
        this.context = context;
        this.surveyList = surveyList;
        this.numberList = new ArrayList<>();
        this.rentVale = rentVale;
        rentlenght = rentVale-10000;
    }

    @NonNull
    @Override
    public SurveyRentRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.survey_row,parent,false);
        return new SurveyRentRecyclerViewAdapter.ViewHolder(view,context);
    }

    @Override
    public void onBindViewHolder(@NonNull SurveyRentRecyclerViewAdapter.ViewHolder holder, int position) {

        Survey survey = surveyList.get(position);

        if (Integer.parseInt(survey.getRentID()) >= rentlenght && Integer.parseInt(survey.getRentID()) < rentVale+10000){

            if (survey.getMobileSecondNumberID().isEmpty()){
                holder.mobileNumberID2.setText("Number 2 not given!");
            }else {
                holder.mobileNumberID2.setText("Number 2:"+ "\t" + survey.getMobileSecondNumberID());
            }
            holder.addressID.setText(survey.getSector_nameID() + "\t" + "\t" + "Street:" + "\t" + survey.getStreet_numberID() + "\t" + "House:" + "\t" + survey.getHouse_numberID());
            holder.rentID.setText("Rent:" + "\t" + "\t" + survey.getRentID() + "\t" + "Rs/-");
            holder.mobileNumberID.setText("Mobile No 1:"+ "\t" + survey.getOwner_pd_numberID());
            holder.ownerOrpropertyID.setText("Own/Prop:"+"\t"+survey.getOwnerorpropertyID());
            holder.date.setText(survey.getTimestamp());

            if(your_array_list_rent_survey_10PLUS_10MINUS.contains(survey.getSurvey_nodeID())){
                holder.favourites_images.setImageResource(R.drawable.ic_like);
            }else {
                holder.favourites_images.setImageResource(R.drawable.ic_thumb_up);
            }

        }else {
            holder.itemView.setVisibility(View.GONE);
            holder.itemView.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
            //Toast.makeText(context, "Sorry Data Not Found!", Toast.LENGTH_SHORT).show();
        }


//        if (survey.getMobileSecondNumberID().isEmpty()){
//            holder.mobileNumberID2.setVisibility(View.GONE);
//        }else {
//            holder.mobileNumberID2.setText("Mobile No 2:"+ "\t" + survey.getMobileSecondNumberID());
//        }
//        holder.addressID.setText(survey.getSector_nameID() + "\t" + "\t" + "Stree:" + "\t" + survey.getStreet_numberID() + "\t" + "House:" + "\t" + survey.getHouse_numberID());
//        holder.rentID.setText("Rent:" + "\t" + "\t" + survey.getRentID() + "\t" + "Rs/-");
//        holder.mobileNumberID.setText("Mobile No 1:"+ "\t" + survey.getOwner_pd_numberID());
//        holder.ownerOrpropertyID.setText("Own/Prop:"+"\t"+survey.getOwnerorpropertyID());
//        holder.date.setText(survey.getTimestamp());
//
//        if(your_array_list.contains(survey.getSurvey_nodeID())){
//            holder.favourites_images.setImageResource(R.drawable.ic_like);
//        }else {
//            holder.favourites_images.setImageResource(R.drawable.ic_thumb_up);
//        }
    }

    public void setfilter(List<Survey> itemList){
        surveyList = new ArrayList<>();
        surveyList.addAll(itemList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return surveyList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView addressID, rentID, date, mobileNumberID, mobileNumberID2,ownerOrpropertyID;
        public Button deleteButton, editBtn, shareBtn, callBtn;
        public DatabaseReference databaseReference;
        public FirebaseAuth mAuth;
        public FirebaseUser mUser;
        public ImageView favourites_images;
        public ViewHolder(View itemView, final Context ctx) {
            super(itemView);

            context = ctx;
            mAuth = FirebaseAuth.getInstance();
            mUser = mAuth.getCurrentUser();
            addressID = itemView.findViewById(R.id.addressID);
            rentID = itemView.findViewById(R.id.rentID);
            mobileNumberID = itemView.findViewById(R.id.mobileNumberID);
            date = itemView.findViewById(R.id.date);
            mobileNumberID2 = itemView.findViewById(R.id.mobileNumberID2);
            deleteButton = itemView.findViewById(R.id.deleteButton);
            editBtn = itemView.findViewById(R.id.editBtn);
            shareBtn = itemView.findViewById(R.id.shareBtn);
            callBtn = itemView.findViewById(R.id.callBtn);
            ownerOrpropertyID = itemView.findViewById(R.id.ownerOrpropertyID);
            favourites_images = itemView.findViewById(R.id.favourites_images);

            favourites_images.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int position = getAdapterPosition();
                    Survey survey = surveyList.get(position);

                    if (your_array_list_rent_survey_10PLUS_10MINUS.contains(survey.getSurvey_nodeID())) {
                        favourites_images.setImageResource(R.drawable.ic_thumb_up);
                        db.execSQL("DELETE FROM TableFavourites WHERE image='" + survey.getSurvey_nodeID() + "'");
                        your_array_list_rent_survey_10PLUS_10MINUS.remove(survey.getSurvey_nodeID());
                        Toast.makeText(ctx, "UnDone!", Toast.LENGTH_SHORT).show();
                    } else if (!your_array_list_rent_survey_10PLUS_10MINUS.contains(survey.getSurvey_nodeID())) {
                        favourites_images.setImageResource(R.drawable.ic_like);
                        db.execSQL("INSERT INTO TableFavourites VALUES(NULL,'" + survey.getSurvey_nodeID() + "');");
                        your_array_list_rent_survey_10PLUS_10MINUS.add(survey.getSurvey_nodeID());
                        Toast.makeText(ctx, "Done!", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            editBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int pos = getAdapterPosition();
                    final Survey survey = surveyList.get(pos);

                    dialogBuilder = new AlertDialog.Builder(context);
                    inflater = LayoutInflater.from(context);
                    View view1 = inflater.inflate(R.layout.activity_rent_update, null);
                    submitbuttonId = view1.findViewById(R.id.submitbuttonId);
                    sector_nameID = view1.findViewById(R.id.sector_nameID);
                    sector_nameID.setText(survey.getSector_nameID());
                    sector_nameID.setSelection(sector_nameID.getText().length());
                    street_numberID = view1.findViewById(R.id.street_numberID);
                    street_numberID.setText(survey.getStreet_numberID());
                    street_numberID.setSelection(street_numberID.getText().length());
                    house_numberID = view1.findViewById(R.id.house_numberID);
                    house_numberID.setText(survey.getHouse_numberID());
                    house_numberID.setSelection(house_numberID.getText().length());
                    ownerorpropertyID = view1.findViewById(R.id.ownerorpropertyID);
                    owner_pd_numberID = view1.findViewById(R.id.owner_pd_numberID);
                    owner_pd_numberID.setText(survey.getOwner_pd_numberID());
                    owner_pd_numberID.setSelection(owner_pd_numberID.getText().length());
                    adbtnID = view1.findViewById(R.id.adbtnID);
                    mobileSecondNumberID = view1.findViewById(R.id.mobileSecondNumberID);
                    mobileSecondNumberID.setText(survey.getMobileSecondNumberID());
                    mobileSecondNumberID.setSelection(mobileSecondNumberID.getText().length());
                    bedroomID = view1.findViewById(R.id.bedroomID);
                    bedroomID.setText(survey.getBedroomID());
                    bedroomID.setSelection(bedroomID.getText().length());
                    bathID = view1.findViewById(R.id.bathID);
                    bathID.setText(survey.getBathID());
                    tvLaunchID = view1.findViewById(R.id.tvLaunchID);
                    tvLaunchID.setText(survey.getTvLaunchID());
                    tvLaunchID.setSelection(tvLaunchID.getText().length());
                    drying_roomID = view1.findViewById(R.id.drying_roomID);
                    drying_roomID.setText(survey.getDrying_roomID());
                    drying_roomID.setSelection(drying_roomID.getText().length());
                    dinning_roomID = view1.findViewById(R.id.dinning_roomID);
                    dinning_roomID.setText(survey.getDinning_roomID());
                    dinning_roomID.setSelection(dinning_roomID.getText().length());
                    servant_roomID = view1.findViewById(R.id.servant_roomID);
                    servant_roomID.setText(survey.getServant_roomID());
                    servant_roomID.setSelection(servant_roomID.getText().length());
                    advanceID = view1.findViewById(R.id.advanceID);
                    advanceID.setText(survey.getAdvanceID());
                    advanceID.setSelection(advanceID.getText().length());
                    securityID = view1.findViewById(R.id.securityID);
                    securityID.setText(survey.getSecurityID());
                    securityID.setSelection(securityID.getText().length());
                    rentID = view1.findViewById(R.id.rentID);
                    rentID.setText(survey.getRentID());
                    rentorsaleId = view1.findViewById(R.id.rentorsaleId);
                    furnishId = view1.findViewById(R.id.furnishId);

                    adbtnID.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //change boolean value
                            if (isClickedFirstTime) {
                                mobileSecondNumberID.setVisibility(View.VISIBLE);
                                isClickedFirstTime = false;
                            } else {
                                mobileSecondNumberID.setVisibility(View.GONE);
                                isClickedFirstTime = true;
                            }

                        }
                    });

                    car_parkingCheckBoxID = view1.findViewById(R.id.car_parkingCheckBoxID);
                    car_parkingCheckBoxID.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            if (car_parkingCheckBoxID.isChecked()){
                                carCheckBoxChecked = "Yes";
                            }else {
                                carCheckBoxChecked = "No";
                            }
                        }
                    });
                    if(survey.getCar_parkingCheckBoxID().equals("No")){
                        car_parkingCheckBoxID.setChecked(false);
                        carCheckBoxChecked = "No";
                    }else {
                        car_parkingCheckBoxID.setChecked(true);
                        carCheckBoxChecked = "Yes";
                    }
                    seperate_gasCheckBoxId = view1.findViewById(R.id.seperate_gasCheckBoxId);
                    seperate_gasCheckBoxId.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            if (seperate_gasCheckBoxId.isChecked()){
                                seperategasChecked = "Yes";
                            }else {
                                seperategasChecked = "No";
                            }
                        }
                    });
                    if(survey.getSeperate_gasCheckBoxId().equals("No")){
                        seperate_gasCheckBoxId.setChecked(false);
                        seperategasChecked = "No";
                    }else {
                        seperate_gasCheckBoxId.setChecked(true);
                        seperategasChecked = "Yes";
                    }
                    sep_elecCheckBoxId = view1.findViewById(R.id.sep_elecCheckBoxId);
                    sep_elecCheckBoxId.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            if (sep_elecCheckBoxId.isChecked()){
                                sepElecChecked = "Yes";
                            }else {
                                sepElecChecked = "No";
                            }
                        }
                    });
                    if(survey.getSep_elecCheckBoxId().equals("No")){
                        sep_elecCheckBoxId.setChecked(false);
                        sepElecChecked = "No";
                    }else {
                        sep_elecCheckBoxId.setChecked(true);
                        sepElecChecked = "Yes";
                    }
                    motorBorCheckBoxID = view1.findViewById(R.id.motorBorCheckBoxID);
                    motorBorCheckBoxID.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            if (motorBorCheckBoxID.isChecked()){
                                motorBorChecked = "Yes";
                            }else {
                                motorBorChecked = "No";
                            }
                        }
                    });
                    if(survey.getMotorBorCheckBoxID().equals("No")){
                        motorBorCheckBoxID.setChecked(false);
                        motorBorChecked = "No";
                    }else {
                        motorBorCheckBoxID.setChecked(true);
                        motorBorChecked = "Yes";
                    }
                    fullHouseCheckBoxId = view1.findViewById(R.id.fullHouseCheckBoxId);
                    fullHouseCheckBoxId.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            if (fullHouseCheckBoxId.isChecked()){

                                fullHouseChecked = "Yes";
                            }else {
                                fullHouseChecked = "No";
                            }
                        }
                    });
                    if(survey.getFullHouseCheckBoxId().equals("No")){
                        fullHouseCheckBoxId.setChecked(false);
                        fullHouseChecked = "No";
                    }else {
                        fullHouseCheckBoxId.setChecked(true);
                        fullHouseChecked = "Yes";
                    }
                    gateSeperateCheckBoxId = view1.findViewById(R.id.gateSeperateCheckBoxId);
                    gateSeperateCheckBoxId.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            if (gateSeperateCheckBoxId.isChecked()){
                                gateSeperateChecked = "Yes";
                            }else {
                                gateSeperateChecked = "No";
                            }
                        }
                    });
                    if(survey.getGateSeperateCheckBoxId().equals("No")){
                        gateSeperateCheckBoxId.setChecked(false);
                        gateSeperateChecked = "No";
                    }else {
                        gateSeperateCheckBoxId.setChecked(true);
                        gateSeperateChecked = "Yes";
                    }

                    areaSpinnerId = view1.findViewById(R.id.areaSpinnerId);
                    areaUnitID = view1.findViewById(R.id.areaUnitID);
                    areaUnitID.setText(survey.getAreaUnitID());
                    floorSpinnerId = view1.findViewById(R.id.floorSpinnerId);
                    adTitleID = view1.findViewById(R.id.adTitleID);
                    adTitleID.setText(survey.getAdTitleID());
                    descriptionID = view1.findViewById(R.id.descriptionID);
                    descriptionID.setText(survey.getDescriptionID());




                    furnishSpinner();
                    areaSpinner();
                    floorSpinner();
                    rentOrSaleSpinner();
                    OwnerOrPropertySpinner();


                    mProgressDialog = new ProgressDialog(context);
                    mAuth = FirebaseAuth.getInstance();
                    mDatabase = FirebaseDatabase.getInstance();
                    mUser = mAuth.getCurrentUser();
                    mDatabaseReferrence = FirebaseDatabase.getInstance().getReference().child("SurveyData").child(mUser.getUid()).child(survey.getSurvey_nodeID());

                    submitbuttonId.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            mProgressDialog.setMessage("Updated Data....");
                            mProgressDialog.setCanceledOnTouchOutside(false);
                            mProgressDialog.show();

                            //DatabaseReference newPost = mDatabaseReferrence.push();
                            String ID = mDatabaseReferrence.getKey();
                            Map<String,String> data = new HashMap<>();
                            data.put("sector_nameID",sector_nameID.getText().toString().trim());
                            survey.setSector_nameID(sector_nameID.getText().toString().trim());
                            data.put("street_numberID",street_numberID.getText().toString().trim());
                            survey.setStreet_numberID(street_numberID.getText().toString().trim());
                            data.put("timestamp",String.valueOf(java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime())));
                            survey.setTimestamp(String.valueOf(java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime())));
                            data.put("survey_nodeID",ID);
                            survey.setSurvey_nodeID(survey.getSurvey_nodeID());
                            data.put("house_numberID",house_numberID.getText().toString().trim());
                            survey.setHouse_numberID(house_numberID.getText().toString().trim());
                            data.put("ownerorpropertyID",ownerorpropertyListSpinner);
                            survey.setOwnerorpropertyID(ownerorpropertyListSpinner);
                            data.put("owner_pd_numberID",owner_pd_numberID.getText().toString().trim());
                            survey.setOwner_pd_numberID(owner_pd_numberID.getText().toString().trim());
                            data.put("bedroomID",bedroomID.getText().toString().trim());
                            survey.setBedroomID(bedroomID.getText().toString().trim());
                            data.put("bathID",bathID.getText().toString().trim());
                            survey.setBathID(bathID.getText().toString().trim());
                            data.put("tvLaunchID",tvLaunchID.getText().toString().trim());
                            survey.setTvLaunchID(tvLaunchID.getText().toString().trim());
                            data.put("drying_roomID",drying_roomID.getText().toString().trim());
                            survey.setDrying_roomID(drying_roomID.getText().toString().trim());
                            data.put("dinning_roomID",dinning_roomID.getText().toString().trim());
                            survey.setDinning_roomID(dinning_roomID.getText().toString().trim());
                            data.put("servant_roomID",servant_roomID.getText().toString().trim());
                            survey.setServant_roomID(servant_roomID.getText().toString().trim());
                            data.put("advanceID",advanceID.getText().toString().trim());
                            survey.setAdvanceID(advanceID.getText().toString().trim());
                            data.put("securityID",securityID.getText().toString().trim());
                            survey.setSecurityID(securityID.getText().toString().trim());
                            data.put("mobileSecondNumberID",mobileSecondNumberID.getText().toString().trim());
                            survey.setMobileSecondNumberID(mobileSecondNumberID.getText().toString().trim());
                            data.put("rentID",rentID.getText().toString().trim());
                            survey.setRentID(rentID.getText().toString().trim());
                            data.put("rentorsaleId","Rent");
                            survey.setRentorsaleId("Rent");
                            data.put("furnishId","null");
                            survey.setFurnishId("null");
                            data.put("car_parkingCheckBoxID",carCheckBoxChecked);
                            survey.setCar_parkingCheckBoxID(carCheckBoxChecked);
                            data.put("seperate_gasCheckBoxId",seperategasChecked);
                            survey.setSeperate_gasCheckBoxId(seperategasChecked);
                            data.put("sep_elecCheckBoxId",sepElecChecked);
                            survey.setSep_elecCheckBoxId(sepElecChecked);
                            data.put("motorBorCheckBoxID",motorBorChecked);
                            survey.setMotorBorCheckBoxID(motorBorChecked);
                            data.put("gateSeperateCheckBoxId",gateSeperateChecked);
                            survey.setGateSeperateCheckBoxId(gateSeperateChecked);
                            data.put("fullHouseCheckBoxId",fullHouseChecked);
                            survey.setFullHouseCheckBoxId(fullHouseChecked);
                            data.put("areaSpinnerId",areaListSpinner);
                            survey.setAreaSpinnerId(areaListSpinner);
                            data.put("areaUnitID",areaUnitID.getText().toString().trim());
                            survey.setAreaUnitID(areaUnitID.getText().toString().trim());
                            data.put("floorSpinnerId",floorListSpinner);
                            survey.setFloorSpinnerId(floorListSpinner);
                            data.put("adTitleID",adTitleID.getText().toString().trim());
                            survey.setAdTitleID(adTitleID.getText().toString().trim());
                            data.put("descriptionID",descriptionID.getText().toString().trim());
                            survey.setDescriptionID(descriptionID.getText().toString().trim());
                            mDatabaseReferrence.setValue(data);
                            notifyItemChanged(getAdapterPosition(),survey);
                            Toast.makeText(context, "Rent Updated!", Toast.LENGTH_SHORT).show();
                            mProgressDialog.dismiss();
                            alertDialog.dismiss();
                        }
                    });

                    dialogBuilder.setView(view1);
                    alertDialog = dialogBuilder.create();
                    alertDialog.show();

                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int pos = getAdapterPosition();
                    Survey survey = surveyList.get(pos);

                    Intent intent = new Intent(context, InternalClickFullDataActivity.class);
                    intent.putExtra(ACTION_SECTOR_NAME,survey.getSector_nameID());
                    intent.putExtra(ACTION_STREET_NUMBER,survey.getStreet_numberID());
                    intent.putExtra(ACTION_HOUSE_NUMBER,survey.getHouse_numberID());
                    intent.putExtra(ACTION_OWNER_PD_NUMBER,survey.getOwner_pd_numberID());
                    intent.putExtra(ACTION_BEDROOM,survey.getBedroomID());
                    intent.putExtra(ACTION_BATH,survey.getBathID());
                    intent.putExtra(ACTION_ONWER_OR_PROPERTY_NUMBER,survey.getOwnerorpropertyID());
                    intent.putExtra(ACTION_TV_LAUNCH,survey.getTvLaunchID());
                    intent.putExtra(ACTION_DRYING_ROOM,survey.getDrying_roomID());
                    intent.putExtra(ACTION_DINNING_ROOM,survey.getDinning_roomID());
                    intent.putExtra(ACTION_SERVANT_ROOM,survey.getServant_roomID());
                    intent.putExtra(ACTION_ADVANCE,survey.getAdvanceID());
                    intent.putExtra(ACTION_SECURITY,survey.getSecurityID());
                    intent.putExtra(ACTION_OWNER_SECOND_NUMBER,survey.getMobileSecondNumberID());
                    intent.putExtra(ACTION_RENT,survey.getRentID());
                    intent.putExtra(ACTION_RENT_OR_SALE,survey.getRentorsaleId());
                    intent.putExtra(ACTION_FURNISH,survey.getFurnishId());
                    intent.putExtra(ACTION_CAR_PARK,survey.getCar_parkingCheckBoxID());
                    intent.putExtra(ACTION_SEP_GAS,survey.getSeperate_gasCheckBoxId());
                    intent.putExtra(ACTION_SEP_ELEC,survey.getSep_elecCheckBoxId());
                    intent.putExtra(ACTION_MOTOR_BOR,survey.getMotorBorCheckBoxID());
                    intent.putExtra(ACTION_FULL_HOUSE,survey.getFullHouseCheckBoxId());
                    intent.putExtra(ACTION_GATE_SEP,survey.getGateSeperateCheckBoxId());
                    intent.putExtra(ACTION_AREA,survey.getAreaSpinnerId());
                    intent.putExtra(ACTION_AREA_UNIT,survey.getAreaUnitID());
                    intent.putExtra(ACTION_FLOOR_LEVELS,survey.getFloorSpinnerId());
                    intent.putExtra(ACTION_AD_TITLE,survey.getAdTitleID());
                    intent.putExtra(ACTION_DESCRIPTION,survey.getDescriptionID());
                    intent.putExtra(ACTION_DATE,survey.getTimestamp());

                    context.startActivity(intent);
                }
            });


            shareBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int pos = getAdapterPosition();
                    Survey survey = surveyList.get(pos);

                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_SUBJECT,"Property Detail");
                    sendIntent.putExtra(Intent.EXTRA_TEXT,
                            "Address:\n"+survey.getSector_nameID()
                                    + "\t" + "Street No:" + "\t"
                                    + survey.getStreet_numberID() + "\t"
                                    + "House No :" + "\t" + survey.getHouse_numberID()
                                    + "\nMobile Number 1 :" + survey.getOwner_pd_numberID()
                                    + "\nMobile Number 2 :" + survey.getMobileSecondNumberID()
                                    + "\nBedroom :" + survey.getBedroomID()
                            +"\nBath:" + "\t" + survey.getBathID()
                            +"\nTv Launch:"+ "\t" + survey.getTvLaunchID()
                            +"\nDrying Room:"+ "\t" + survey.getDrying_roomID()
                            +"\nDinning Room:"+ "\t" + survey.getDinning_roomID()
                            +"\nServant Room:"+ "\t" + survey.getServant_roomID()
                            +"\nAdvance:"+ "\t" + survey.getAdvanceID()
                            +"\nSecurity:"+ "\t" + survey.getTvLaunchID()
                            +"\nRent:"+ "\t" + survey.getRentID()+"\t"+"Rs/-"
                            +"\nRent/Sale:"+ "\t" + survey.getRentorsaleId()
                            +"\nCar Parking:"+ "\t" + survey.getCar_parkingCheckBoxID()
                            +"\nSeperate Gas Meter:"+ "\t" + survey.getSeperate_gasCheckBoxId()
                            +"\nSeperate Electricity Meter:"+ "\t" + survey.getSep_elecCheckBoxId()
                            +"\nSeperate Motor Bor:"+ "\t" + survey.getMotorBorCheckBoxID()
                            +"\nFurnish House:"+ "\t" + survey.getFullHouseCheckBoxId()
                            +"\nSeperate Gate:"+ "\t" + survey.getGateSeperateCheckBoxId()
                            +"\nArea:"+ "\t" + survey.getAreaSpinnerId()
                            +"\nArea Unit:"+ "\t" + survey.getAreaUnitID()
                            +"\nFloor Levels:"+ "\t" + survey.getFloorSpinnerId()
                            +"\nAd Title:"+ "\t" + survey.getAdTitleID()
                            +"\nDescription:"+ "\t" + survey.getDescriptionID()
                    );
                    sendIntent.setType("text/plain");
                    context.startActivity(sendIntent);
                }
            });

            callBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    numberList.clear();
                    int pos = getAdapterPosition();
                    Survey survey = surveyList.get(pos);
                    displayDualNumbers(survey.getOwner_pd_numberID(), survey.getMobileSecondNumberID(), context);
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
                            Survey s = surveyList.get(position);
                            databaseReference = FirebaseDatabase.getInstance().getReference().child("SurveyData").child(mUser.getUid()).child(s.getSurvey_nodeID());
                            databaseReference.removeValue();
                            surveyList.remove(getAdapterPosition());
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

        }

    }

    private void addSurveyData() {


    }

    public void furnishSpinner(){
        List<String> furnishList = new ArrayList<>();
        furnishList.add("Yes");
        furnishList.add("No");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item,furnishList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        furnishId.setAdapter(arrayAdapter);
        furnishId.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((TextView) adapterView.getChildAt(0)).setTextColor(Color.BLACK);
                furnishListSpinner = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void OwnerOrPropertySpinner(){
        List<String> furnishList = new ArrayList<>();
        furnishList.add("Owner");
        furnishList.add("Property Dealer");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item,furnishList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ownerorpropertyID.setAdapter(arrayAdapter);
        ownerorpropertyID.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((TextView) adapterView.getChildAt(0)).setTextColor(Color.BLACK);
                ownerorpropertyListSpinner = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void areaSpinner(){

        List<String> areaList = new ArrayList<>();
        areaList.add("Marla");
        areaList.add("Kanal");
        areaList.add("Square Yards");
        areaList.add("Square Feet");
        areaList.add("Square Meter");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item,areaList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        areaSpinnerId.setAdapter(arrayAdapter);
        areaSpinnerId.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((TextView) adapterView.getChildAt(0)).setTextColor(Color.BLACK);
                areaListSpinner = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void floorSpinner(){

        List<String> floorList = new ArrayList<>();
        floorList.add("Full House");
        floorList.add("Ground + Upper");
        floorList.add("Ground + Basement");
        floorList.add("Ground");
        floorList.add("Upper");
        floorList.add("Basement");
        floorList.add("Floor Level 1");
        floorList.add("Floor Level 2");
        floorList.add("Floor Level 3");
        floorList.add("Floor Level 4");
        floorList.add("Floor Level 5");
        floorList.add("Floor Level 6+");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item,floorList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        floorSpinnerId.setAdapter(arrayAdapter);
        floorSpinnerId.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((TextView) adapterView.getChildAt(0)).setTextColor(Color.BLACK);
                floorListSpinner = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
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
                rentorsaleListSpinner = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

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
}
