package com.example.newsapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import static android.content.Context.MODE_APPEND;
import static android.content.Context.MODE_PRIVATE;


public class frag3 extends Fragment{

    ImageView iv;
    EditText name;
    EditText email;
    EditText phone;
    EditText loc;
    SharedPreferences sp;
    View v;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_profile);


        email=v.findViewById(R.id.email);
        name=v.findViewById(R.id.name);
        phone=v.findViewById(R.id.phone);
        loc=v.findViewById(R.id.location);
        sp=getActivity().getSharedPreferences("SharedMyOwn",MODE_PRIVATE);

        email.setFocusable(false);
        email.setClickable(false);

        name.setFocusable(false);
        name.setClickable(false);

        loc.setFocusable(false);
        loc.setClickable(false);

        phone.setFocusable(false);
        phone.setClickable(false);
    }


    public void onResume() {
        super.onResume();
        String name1=sp.getString("name","No name given");
        String loc1=sp.getString("loc","Bangalore");
        long phone=sp.getLong("phone",0);
        String email=sp.getString("email","example@gmail.com");

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v=getView();
        return inflater.inflate(R.layout.fragment_frag3, container, false);
    }


    public void EditNow(View view) {

        email.setFocusable(true);
        email.setClickable(true);
        email.setCursorVisible(true);

        name.setFocusable(true);
        name.setClickable(true);
        name.setCursorVisible(true);

        loc.setFocusable(true);
        loc.setClickable(true);
        loc.setCursorVisible(true);

        phone.setFocusable(true);
        phone.setClickable(true);
        phone.setCursorVisible(true);


    }

    public void onPause() {
        super.onPause();

        SharedPreferences.Editor se=sp.edit();
        se.putString("name",name.getText().toString());
        se.putString("email",email.getText().toString());
        se.putString("loc",loc.getText().toString());
        se.putLong("phone",Long.parseLong(phone.getText().toString()));
        se.apply();//se.commit();
    }
}