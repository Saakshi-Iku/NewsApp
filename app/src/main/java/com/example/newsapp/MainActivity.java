package com.example.newsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNav=findViewById(R.id.nav);
        bottomNav.setOnNavigationItemSelectedListener(list);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame,
                    new frag1()).commit();
        }
    }
    private BottomNavigationView.OnNavigationItemSelectedListener  list =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item)
                {
                    Fragment selectedFrag=null;

                    switch(item.getItemId())
                    {
                        case R.id.page_1:
                            selectedFrag=new frag1();
                            break;
                        case R.id.page_2:
                            selectedFrag=new frag2();
                            break;
                        case R.id.page_3:
                            selectedFrag=new frag3();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame,selectedFrag).commit();
                    return true;

                }
            };

}