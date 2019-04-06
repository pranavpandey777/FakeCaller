package com.example.work;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context context;
    ArrayList<Data> arrayList;
    String data;
    String na,no;

    public MyAdapter(Context context, ArrayList<Data> arrayList, String data) {
        this.context = context;
        this.arrayList = arrayList;
        this.data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (data.equals("contacts")) {
            View v = LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.layout, parent, false);
            MyViewHolder myViewHolder = new MyViewHolder(v, context, arrayList);

            return myViewHolder;

        } else if (data.equals("log")) {
            View v = LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.layout2, parent, false);
            MyViewHolder myViewHolder = new MyViewHolder(v, context, arrayList);

            return myViewHolder;

        } else if (data.equals("dial")) {
            View v = LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.searchlayout2, parent, false);
            MyViewHolder myViewHolder = new MyViewHolder(v, context, arrayList);

            return myViewHolder;

        }
        return null;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        if (data.equals("contacts")) {
            Data data = arrayList.get(position);
            String name = data.getName();
            String number = data.getNumber();

            holder.name.setText(name);
            holder.number.setText(number);

        } else if (data.equals("log")) {
            Data data = arrayList.get(position);
            String name = data.getName();
            String number = data.getNumber();
            long time = data.getDate();
            long millis = data.getDuration();
            float ff = millis;
            String f = DateFormat.format("dd/MM/yyy hh:mm:ss", new Date(time)).toString();
            BigDecimal roundfinalPrice = new BigDecimal(ff / 60).setScale(2, BigDecimal.ROUND_HALF_UP);


            holder.name.setText(name);
            holder.number.setText(number);
            holder.time.setText(f);
            holder.duration.setText(String.valueOf(roundfinalPrice));

        } else if (data.equals("dial")) {

            Data data = arrayList.get(position);
            String name = data.getName1();
            String number = data.getNumber1();

            holder.a.setText(name);
            holder.b.setText(number);


            holder.card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   // Toast.makeText(context, ""+holder.b.getText().toString(), Toast.LENGTH_SHORT).show();
                    String num=holder.b.getText().toString();
                    String name=holder.a.getText().toString();
                    dialog(name,num);
                }
            });


        }

    }

    public void filterList(ArrayList<Data> filterdNames) {
        this.arrayList = filterdNames;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name, number;
        Button button;
        Context context;
        ArrayList<Data> arrayList;
        TextView time, duration;
        LinearLayout card;
        TextView a,b;


        public MyViewHolder(View itemView, Context context, ArrayList<Data> arrayList) {
            super(itemView);
            if (data.equals("contacts")) {
                name = itemView.findViewById(R.id.name);
                number = itemView.findViewById(R.id.number);
                button = itemView.findViewById(R.id.button);
                this.arrayList = arrayList;
                this.context = context;
                itemView.setOnClickListener(this);
                button.setOnClickListener(this);
            } else if (data.equals("log")) {
                name = itemView.findViewById(R.id.name);
                number = itemView.findViewById(R.id.number);
                time = itemView.findViewById(R.id.time);
                duration = itemView.findViewById(R.id.duration);

                button = itemView.findViewById(R.id.button);
                this.arrayList = arrayList;
                this.context = context;
                itemView.setOnClickListener(this);
                button.setOnClickListener(this);
            } else if (data.equals("dial")) {
                a = itemView.findViewById(R.id.name);
                card = itemView.findViewById(R.id.card);
                b = itemView.findViewById(R.id.number);
                this.arrayList = arrayList;
                this.context = context;
                card.setOnClickListener(this);
                itemView.setOnClickListener(this);


            }


        }

        @Override
        public void onClick(View v) {

            int pos = getAdapterPosition();
            Data d = arrayList.get(pos);
            String mob = d.getNumber();

            if (v == button) {

                Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + mob));
                context.startActivity(i);

            }


        }



        }

    public void dialog(String name, String number) {
        this.na = name;
        this.no = number;
        Dialog log = new Dialog(name, number);
        log.show(((AppCompatActivity) context).getSupportFragmentManager(), "login");


    }


    }



