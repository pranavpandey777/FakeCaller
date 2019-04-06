package com.example.work;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.support.v7.widget.CardView;
import android.telephony.SmsManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("ValidFragment")
public class Dialog extends AppCompatDialogFragment {
    String na, no;


    @SuppressLint("ValidFragment")
    public Dialog(String name, String number) {
        this.na = name;
        this.no = number;
    }

    Button call, message,send;
    TextView senderno;
    EditText sendetmessage;
    CardView card;
    TextWatcher watcher=null;
    @Override
    public android.app.Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog, null);

        call = view.findViewById(R.id.call);
        message = view.findViewById(R.id.message);
        send=view.findViewById(R.id.send);
        senderno=view.findViewById(R.id.senderno);
        sendetmessage=view.findViewById(R.id.sendermessage);
        card=view.findViewById(R.id.card);
        senderno.setText(no);
        send.setEnabled(false);

        watcher=new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
if(sendetmessage.getText().toString().isEmpty()){

    send.setEnabled(false);
}else{
    send.setEnabled(true);
}
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        sendetmessage.addTextChangedListener(watcher);

        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                card.setVisibility(View.VISIBLE);
                sendetmessage.requestFocus();
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String message=sendetmessage.getText().toString();
               // startActivity(new Intent(Intent.ACTION_VIEW,Uri.fromParts("sms",no,null)));
              /*  Intent i = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + no));
                i.putExtra("sms_body",message);
                startActivity(i);*/


                  SmsManager.getDefault().sendTextMessage(no, null, message, null, null);
                  Toast.makeText(getContext(), "Message Sent", Toast.LENGTH_SHORT).show();
                  sendetmessage.setText("");
                  getDialog().dismiss();
              }

        });
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + no));
                startActivity(i);
            }
        });



        builder.setView(view);

        return builder.create();


    }
}
