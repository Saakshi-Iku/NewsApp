package com.example.newsapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class Profile extends AppCompatActivity {

    ImageView iv;
    EditText name;
    EditText email;
    EditText phone;
    EditText loc;
    ImageView pic;
    EditText url;
    String img;
    boolean editable=false;
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
        pic=findViewById(R.id.profilepic);
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

    public void onCreateDialog(final View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Get the layout inflater
        LayoutInflater inflater = getLayoutInflater();

        final View dialog_view=inflater.inflate(R.layout.imgadd, null);
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(dialog_view)
                // Add action buttons
                .setPositiveButton("Set", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //SharedPreferences.Editor se=sp.edit();
                        url=dialog_view.findViewById(R.id.pic_user);
                        img=url.getText().toString();
                        Picasso.with(Profile.this).load(img).fit().centerInside().into(pic);

                        dialog.dismiss();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        AlertDialog a= builder.create();
        a.setTitle("Edit profile picture");
        a.show();
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
        String url1=sp.getString("url","https://www.nbcsports.com/bayarea/sites/csnbayarea/files/2020/07/15/dakprescottusatsi.jpg");
        Picasso.with(Profile.this).load(url1).fit().centerInside().into(pic);

    }


    public void EditNow(View view) {
        editable=!editable;
    }

    protected void onPause() {
        super.onPause();

        SharedPreferences.Editor se=sp.edit();
        se.putString("name",name.getText().toString());
        se.putString("email",email.getText().toString());
        se.putString("loc",loc.getText().toString());
        se.putLong("phone",Long.parseLong(phone.getText().toString()));
        se.putString("url",img);
        se.apply();//se.commit();
    }

    public void editPhone(View view) {
        if(editable)
        {

            email.setFocusable(false);
            email.setClickable(false);

            name.setFocusable(false);
            name.setClickable(false);

            loc.setFocusable(false);
            loc.setClickable(false);


            phone.setFocusable(true);
            phone.setClickable(true);
            phone.setCursorVisible(true);
        }
    }

    public void editEmail(View view) {
        if(editable)
        {
            name.setFocusable(false);
            name.setClickable(false);

            loc.setFocusable(false);
            loc.setClickable(false);

            phone.setFocusable(false);
            phone.setClickable(false);
            email.setFocusable(true);
            email.setClickable(true);
            email.setCursorVisible(true);

        }
    }

    public void editLocation(View view) {
        if(editable)
        {
            email.setFocusable(false);
            email.setClickable(false);

            name.setFocusable(false);
            name.setClickable(false);

            phone.setFocusable(false);
            phone.setClickable(false);
            loc.setFocusable(true);
            loc.setClickable(true);
            loc.setCursorVisible(true);

        }
    }

    public void editName(View view) {
        if(editable)
        {
            email.setFocusable(false);
            email.setClickable(false);

            loc.setFocusable(false);
            loc.setClickable(false);

            phone.setFocusable(false);
            phone.setClickable(false);

            name.setFocusable(true);
            name.setClickable(true);
            name.setCursorVisible(true);
        }
    }
}