package com.example.work;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    String val;
    Toolbar toolbar;
    AutoCompleteTextView search;
    Search search1;
    ArrayList<Search> arrayList;
    String names;
    Button sec;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        sec = findViewById(R.id.sec);
        StringBuffer buffer = new StringBuffer();
        arrayList = new ArrayList<>();
        setSupportActionBar(toolbar);
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_CONTACTS)
                == PackageManager.PERMISSION_DENIED || ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE)
                == PackageManager.PERMISSION_DENIED || ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_CALL_LOG)
                == PackageManager.PERMISSION_DENIED || ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.SEND_SMS)
                == PackageManager.PERMISSION_DENIED) {


            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_CONTACTS, Manifest.permission.CALL_PHONE, Manifest.permission.READ_CALL_LOG, Manifest.permission.SEND_SMS}, 10000);


        }


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_CONTACTS)
                    == PackageManager.PERMISSION_DENIED || ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE)
                    == PackageManager.PERMISSION_DENIED || ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_CALL_LOG)
                    == PackageManager.PERMISSION_DENIED || ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.SEND_SMS)
                    == PackageManager.PERMISSION_DENIED) {

                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_CONTACTS, Manifest.permission.CALL_PHONE, Manifest.permission.READ_CALL_LOG, Manifest.permission.SEND_SMS}, 10000);


            }

        }


        sec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                val = "search";
                Fragment fragment;
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragment = new FragmentWork(val);
                fragmentTransaction.replace(R.id.framelayout, fragment);
                fragmentTransaction.commit();
                sec.setVisibility(View.GONE);


            }
        });

        mTextMessage = findViewById(R.id.message);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        val = "favorites";
        Fragment fragment;
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragment = new FragmentWork(val);
        fragmentTransaction.replace(R.id.framelayout, fragment);
        fragmentTransaction.commit();
        sec.setVisibility(View.GONE);

        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment fragment;
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();


                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        sec.setVisibility(View.GONE);
                        val = "favorites";
                        fragment = new FragmentWork(val);
                        fragmentTransaction.replace(R.id.framelayout, fragment);
                        fragmentTransaction.commit();
                        return true;
                    case R.id.navigation_dashboard:
                        sec.setVisibility(View.GONE);
                        val = "log";
                        fragment = new FragmentWork(val);
                        fragmentTransaction.replace(R.id.framelayout, fragment);
                        fragmentTransaction.commit();
                        return true;
                    case R.id.navigation_notifications:
                        sec.setVisibility(View.VISIBLE);
                        val = "contacts";
                        fragment = new FragmentWork(val);
                        fragmentTransaction.replace(R.id.framelayout, fragment);
                        fragmentTransaction.commit();
                        return true;
                }
                return false;
            }
        });


    }

    boolean singleBack = false;

    @Override
    public void onBackPressed() {
        if (singleBack) {
            super.onBackPressed();
            return;
        }

        this.singleBack = true;
        Toast.makeText(this, "Double Back to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                singleBack=false;
            }
        }, 2000);
    }
}
