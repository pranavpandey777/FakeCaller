package com.example.work;


import android.annotation.SuppressLint;

import android.content.Context;
import android.content.Intent;

import android.database.Cursor;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;


import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

@SuppressLint("ValidFragment")
public class FragmentWork extends android.support.v4.app.Fragment {
    String val;

    @SuppressLint("ValidFragment")
    public FragmentWork(String val) {
        this.val = val;
    }

    String toast;
    RelativeLayout layout;
    Data data;
    ArrayList<Data> arrayList;
    ArrayList<Search> arrayListsearch;
    RecyclerView recyclerView;
    AutoCompleteTextView search;
    Search search1;
    String item;
    CardView card;
    TextView name, number;
    String iname;
    String na, no;
    String num;
    TextView user;
    FloatingActionButton floatingActionButton;
    private BottomSheetBehavior mBottomSheetBehavior;
    private TextView mTextViewState;
    TextWatcher watcher = null;
    private BottomSheetBehavior mBottomSheetBehaviour;
    Button one, two, three, four, five, six, seven, eight, nine, zero, back, call,star,hash;
    EditText area;
    String lastno;
    MyAdapter adapterfav;


    @SuppressLint("RestrictedApi")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment, container, false);

       // final MediaPlayer mediaPlayer = (MediaPlayer) MediaPlayer.create(getActivity(), R.raw.clk);

        lastno = CallLog.Calls.getLastOutgoingCall(getActivity().getApplicationContext());

        area = view.findViewById(R.id.area);
        star = view.findViewById(R.id.star);
        hash = view.findViewById(R.id.hash);
        one = view.findViewById(R.id.one);
        two = view.findViewById(R.id.two);
        three = view.findViewById(R.id.three);
        four = view.findViewById(R.id.four);
        five = view.findViewById(R.id.five);
        six = view.findViewById(R.id.six);
        seven = view.findViewById(R.id.seven);
        eight = view.findViewById(R.id.eight);
        nine = view.findViewById(R.id.nine);
        zero = view.findViewById(R.id.ten);
        back = view.findViewById(R.id.back);
        call = view.findViewById(R.id.call);


        watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String chk = area.getText().toString();

                if (!area.getText().toString().isEmpty()) {
                    back.setVisibility(View.VISIBLE);
                } else {

                    back.setVisibility(View.INVISIBLE);
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        area.addTextChangedListener(watcher);


        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                area.append("1");
                // Toast.makeText(getContext(), ""+lastno, Toast.LENGTH_SHORT).show();

            }
        });
       /* two.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {

                switch ( event.getAction() ) {
                    case MotionEvent.ACTION_DOWN:
                        area.append("2");

                        break;
                    case MotionEvent.ACTION_UP: break;
                }
                return true;
            }
        });*/
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                area.append("2");
            }
        });
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                area.append("3");
            }
        });
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                area.append("4");
            }
        });
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                area.append("5");
            }
        });
        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                area.append("6");
            }
        });
        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                area.append("7");
            }
        });
        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                area.append("8");
            }
        });
        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                area.append("9");
            }
        });
        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                area.append("0");

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int length = area.getText().length();
                if (length > 0) {
                    area.getText().delete(length - 1, length);
                }
            }
        });
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (area.getText().toString().isEmpty()) {

                    area.setText(lastno);

                } else {
                    String no = area.getText().toString();
                    Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + no));
                    startActivity(i);
                }
            }
        });
        back.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                String no = area.getText().toString();
                area.setText("");

                return false;
            }
        });
        star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                area.append("*");

            }
        });
        hash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                area.append("#");

            }
        });
        zero.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                area.append("+");

                return true;
            }

        });


        View nestedScrollView = view.findViewById(R.id.nestedScrollView);
        mBottomSheetBehaviour = BottomSheetBehavior.from(nestedScrollView);
        //  mBottomSheetBehaviour.setPeekHeight(200);    //Set the peek height


        layout = view.findViewById(R.id.latout);
        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        arrayList = new ArrayList<>();
        arrayListsearch = new ArrayList<>();
        search = view.findViewById(R.id.search);
        floatingActionButton = view.findViewById(R.id.fab);
        name = view.findViewById(R.id.name);
        number = view.findViewById(R.id.number);
        card = view.findViewById(R.id.card);


        mBottomSheetBehaviour.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {

                switch (newState) {
                    case BottomSheetBehavior.STATE_COLLAPSED:

                        break;
                    case BottomSheetBehavior.STATE_DRAGGING:

                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:

                        break;
                    case BottomSheetBehavior.STATE_HIDDEN:

                        break;
                    case BottomSheetBehavior.STATE_SETTLING:

                        break;
                }
            }

            // Toast.makeText(getActivity(), "Bottom Sheet State Changed to: " + state, Toast.LENGTH_SHORT).show();


            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  mBottomSheetBehaviour.setState(BottomSheetBehavior.STATE_EXPANDED);    // Will show the bottom sheet
                // mBottomSheetBehaviour.setState(BottomSheetBehavior.STATE_COLLAPSED);
                mBottomSheetBehaviour.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });


        if (val.equals("favorites")) {
             recyclerView.setVisibility(View.GONE);
            Cursor cursor = getContext().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);

            while (cursor.moveToNext()) {

                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                data = new Data(name, number);
                arrayList.add(data);
            }

             adapterfav = new MyAdapter(getActivity(), arrayList, "dial");
            recyclerView.setAdapter(adapterfav);

area.addTextChangedListener(new TextWatcher() {
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {


    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if(area.getText().toString().isEmpty()){
            recyclerView.setVisibility(View.INVISIBLE);

        }else{

            recyclerView.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void afterTextChanged(Editable s) {
        filter(s.toString());
    }
});



        } else if (val.equals("log")) {

            Cursor cursor = getContext().getContentResolver().query(CallLog.Calls.CONTENT_URI, null, null, null, CallLog.Calls.DATE + " DESC");

            while (cursor.moveToNext()) {

                String name = cursor.getString(cursor.getColumnIndex(CallLog.Calls.CACHED_NAME));
                String number = cursor.getString(cursor.getColumnIndex(CallLog.Calls.NUMBER));
                long time = cursor.getLong(cursor.getColumnIndex(CallLog.Calls.DATE));
                long duration = cursor.getLong(cursor.getColumnIndex(CallLog.Calls.DURATION));

                data = new Data(name, number, time, duration);
                arrayList.add(data);
            }

            MyAdapter adapter = new MyAdapter(getActivity(), arrayList, "log");
            recyclerView.setAdapter(adapter);


        } else if (val.equals("contacts")) {

            Cursor cursor = getContext().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC");

            while (cursor.moveToNext()) {

                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                data = new Data(name, number,0,0);
                arrayList.add(data);
            }

            MyAdapter adapter = new MyAdapter(getActivity(), arrayList, "contacts");
            recyclerView.setAdapter(adapter);

            // Toast.makeText(getContext(), "" + arrayList.size(), Toast.LENGTH_SHORT).show();


        } else if (val.equals("search")) {
            search.setVisibility(View.VISIBLE);
            search.requestFocus();
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);
            floatingActionButton.setVisibility(View.GONE);
            Cursor cursor = getContext().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC");

            while (cursor.moveToNext()) {

                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                search1 = new Search(name, number);
                arrayListsearch.add(search1);
            }
            SearchAdapter adapter = new SearchAdapter(getActivity(), R.layout.searchlayout, arrayListsearch, "search");
            search.setThreshold(1);
            search.setAdapter(adapter);

            search.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Search fruit = (Search) adapterView.getItemAtPosition(i);

                    iname = fruit.getName();
                    num = fruit.getNumber();
                    name.setText(iname);
                    number.setText(num);
                    card.setVisibility(View.VISIBLE);
                    search.setText("");
                    card.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {


                            dialog(iname, num);

                        }
                    });

                }
            });


        }
        return view;
    }

    public void dialog(String name, String number) {
        this.na = name;
        this.no = number;
        Dialog log = new Dialog(name, number);
        log.show(getActivity().getSupportFragmentManager(), "login");


    }
    private void filter(String text) {
        //new array list that will hold the filtered data
        ArrayList<Data> filterdNames = new ArrayList<>();

        //looping through existing elements
        for (Data s : arrayList) {
            //if the existing elements contains the search input
            if (s.getNumber1().contains(text)) {
                //adding the element to filtered list
                filterdNames.add(s);
            }
        }

        //calling a method of the adapter class and passing the filtered list
        adapterfav.filterList(filterdNames);
    }


}
