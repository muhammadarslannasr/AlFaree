package com.thexsolution.propertyprojectf11.Adapters;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.thexsolution.propertyprojectf11.Model.FriendlyMessage;
import com.thexsolution.propertyprojectf11.R;

import java.util.List;

import static android.content.Context.CLIPBOARD_SERVICE;

public class MessageAdapter extends ArrayAdapter<FriendlyMessage> {
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private LayoutInflater inflater;
    private Button share_bt,delete_btn;
    public DatabaseReference databaseReference;
    private Context context;
    //FriendlyMessage message;
    private List<FriendlyMessage> objects;
    public MessageAdapter(Context context, int resource, List<FriendlyMessage> objects) {
        super(context, resource, objects);
        this.context = context;
        this.objects = objects;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.item_message, parent, false);
        }
         databaseReference=FirebaseDatabase.getInstance().getReference().child("GroupMessages");
        TextView messageTextView = (TextView) convertView.findViewById(R.id.messageTextView);
        TextView authorTextView = (TextView) convertView.findViewById(R.id.nameTextView);
        TextView timeMessageID = (TextView) convertView.findViewById(R.id.timeMessageID);
        ImageView profileImage = convertView.findViewById(R.id.profile_pic);
        final FriendlyMessage message = getItem(position);
        messageTextView.setText(message.getText());
        authorTextView.setText(message.getName());
        timeMessageID.setText(message.getTimestamp());
        convertView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                final CharSequence[] items = {"Share Text","Copy Text"};

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        if (items[item].toString().equals("Share Text")){

                            Intent sendIntent = new Intent();
                            sendIntent.setAction(Intent.ACTION_SEND);
                            sendIntent.putExtra(Intent.EXTRA_TEXT, message.getText());
                            sendIntent.setType("text/plain");
                            getContext().startActivity(sendIntent);

                            dialog.dismiss();
                        }else if (items[item].toString().equals("Copy Text")){
                            ClipboardManager clipboard = (ClipboardManager) context.getSystemService(CLIPBOARD_SERVICE);
                            ClipData clip = ClipData.newPlainText("label", message.getText());
                            if (clipboard != null) {
                                clipboard.setPrimaryClip(clip);
                                Toast.makeText(context, "Copied", Toast.LENGTH_SHORT).show();
                            }
                            dialog.dismiss();
                        }

                    }
                }).show();

                return true;
            }
        });
        return convertView;
    }
}
