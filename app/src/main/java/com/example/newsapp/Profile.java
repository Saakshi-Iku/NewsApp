package com.example.newsapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class Profile extends AppCompatActivity {

    ImageView iv;
    EditText name;
    EditText email;
    EditText phone;
    EditText loc;
    SharedPreferences sp;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        email=findViewById(R.id.email);
        name=findViewById(R.id.name);
        phone=findViewById(R.id.phone);
        loc=findViewById(R.id.location);
        sp=getSharedPreferences("SharedMyOwn",MODE_PRIVATE);



        email.setFocusable(false);
        email.setClickable(false);

        name.setFocusable(false);
        name.setClickable(false);

        loc.setFocusable(false);
        loc.setClickable(false);

        phone.setFocusable(false);
        phone.setClickable(false);
    }


    protected void onResume() {
        super.onResume();
        String name1=sp.getString("name","No name given");
        name.setText(name1);
        String loc1=sp.getString("loc","Bangalore");
        loc.setText(loc1);
        long phone1=sp.getLong("phone",0);
        //phone.setText(phone1.toString());
        String email1=sp.getString("email","example@gmail.com");
        email.setText(email1);

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

    protected void onPause() {
        super.onPause();

        SharedPreferences.Editor se=sp.edit();
        se.putString("name",name.getText().toString());
        se.putString("email",email.getText().toString());
        se.putString("loc",loc.getText().toString());
        se.putLong("phone",Long.parseLong(phone.getText().toString()));
        se.apply();//se.commit();
    }
}