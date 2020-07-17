package com.example.newsapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import static android.content.Context.MODE_PRIVATE;

public class frag3 extends Fragment {

    ImageView iv;
    EditText name;
    EditText email;
    EditText phone;
    EditText loc;
    ImageView pic;
    EditText url;
    String img;
    ImageView edit;
    boolean editable = false;
    SharedPreferences sp;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //v=getView();
        View rootView=inflater.inflate(R.layout.fragment_frag3, container, false);
        email = rootView.findViewById(R.id.email);
        name = rootView.findViewById(R.id.name);
        phone = rootView.findViewById(R.id.phone);
        loc =rootView.findViewById(R.id.location);
        pic = rootView.findViewById(R.id.profilepic);
        sp = getActivity().getSharedPreferences("SharedMyOwn", MODE_PRIVATE);
        edit=rootView.findViewById(R.id.edit);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editable)
                {
                    editable=false;
                    name.setClickable(false);
                    loc.setClickable(false);
                    phone.setClickable(false);
                    email.setClickable(false);

                    loc.setEnabled(false);
                    phone.setEnabled(false);
                    email.setEnabled(false);
                    name.setEnabled(false);

                }
                else{
                    editable=true;
                    name.setClickable(true);
                    loc.setClickable(true);
                    phone.setClickable(true);
                    email.setClickable(true);

                    loc.setEnabled(true);
                    phone.setEnabled(true);
                    email.setEnabled(true);
                    name.setEnabled(true);
                }

            }
        });

        pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                // Get the layout inflater
                LayoutInflater inflater = getLayoutInflater();

                final View dialog_view = inflater.inflate(R.layout.imgadd, null);
                // Inflate and set the layout for the dialog
                // Pass null as the parent view because its going in the dialog layout
                builder.setView(dialog_view)
                        // Add action buttons
                        .setPositiveButton("Set", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                //SharedPreferences.Editor se=sp.edit();
                                url = dialog_view.findViewById(R.id.pic_user);
                                img = url.getText().toString();
                                Picasso.with(getActivity()).load(img).fit().centerInside().into(pic);

                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        });
                AlertDialog a = builder.create();
                a.setTitle("Edit profile picture");
                a.show();
            }
        });

        return rootView;
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_profile);





    }

    public void onCreateDialog(final View v) {

    }


    public void onResume() {
        super.onResume();
        String name1 = sp.getString("name", "No name given");
        name.setText(name1);
        String loc1 = sp.getString("loc", "Bangalore");
        loc.setText(loc1);
        long phone1 = sp.getLong("phone", 0);
        //phone.setText(phone1.toString());
        String email1 = sp.getString("email", "example@gmail.com");
        email.setText(email1);
        String url1 = sp.getString("url", "https://images.unsplash.com/photo-1560114928-40f1f1eb26a0?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=750&q=80");
        Picasso.with(getActivity()).load(url1).fit().centerInside().into(pic);

    }




    public void onPause() {
        super.onPause();

        SharedPreferences.Editor se = sp.edit();
        se.putString("name", name.getText().toString());
        se.putString("email", email.getText().toString());
        se.putString("loc", loc.getText().toString());
        se.putLong("phone", Long.parseLong(phone.getText().toString()));
        se.putString("url", img);
        se.apply();//se.commit();
    }

//    public void editPhone(View view) {
//        if (editable) {
//
//            email.setFocusable(false);
//            email.setClickable(false);
//
//            name.setFocusable(false);
//            name.setClickable(false);
//
//            loc.setFocusable(false);
//            loc.setClickable(false);
//
//
//            phone.setFocusable(true);
//            phone.setClickable(true);
//            phone.setCursorVisible(true);
//        }
//    }
//
//    public void editEmail(View view) {
//        if (editable) {
//            name.setFocusable(false);
//            name.setClickable(false);
//
//            loc.setFocusable(false);
//            loc.setClickable(false);
//
//            phone.setFocusable(false);
//            phone.setClickable(false);
//            email.setFocusable(true);
//            email.setClickable(true);
//            email.setCursorVisible(true);
//
//        }
//    }
//
//    public void editLocation(View view) {
//        if (editable) {
//            email.setFocusable(false);
//            email.setClickable(false);
//
//            name.setFocusable(false);
//            name.setClickable(false);
//
//            phone.setFocusable(false);
//            phone.setClickable(false);
//            loc.setFocusable(true);
//            loc.setClickable(true);
//            loc.setCursorVisible(true);
//
//        }
//    }
//
//    public void editName(View view) {
//        if (editable) {
//            email.setFocusable(false);
//            email.setClickable(false);
//
//            loc.setFocusable(false);
//            loc.setClickable(false);
//
//            phone.setFocusable(false);
//            phone.setClickable(false);
//
//            name.setFocusable(true);
//            name.setClickable(true);
//            name.setCursorVisible(true);
//        }
//    }
}