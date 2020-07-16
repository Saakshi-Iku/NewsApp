package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class YourStories extends AppCompatActivity {

    RecyclerView rv;
    ExampleAdapter ea;
    ArrayList<Ex_top_news> mList;
    RequestQueue mReqQ;
    ImageButton b;
    EditText et;
    String searchQuery;

    public void Search(View view) {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_stories);

//        mTextViewResult=findViewById(R.id.text);
//        iv=findViewById(R.id.iv);
//        Button b = findViewById(R.id.button);
//
//        mReqQ= Volley.newRequestQueue(this);
//        jsonClick();
        et=findViewById(R.id.et1);
        rv=findViewById(R.id.rv);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        mList=new ArrayList<>();
        mReqQ=Volley.newRequestQueue(this);
        b=findViewById(R.id.search);
//        b.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                jsonClick();
//            }
//        });
        jsonClick();
    }


    private void jsonClick(){
        searchQuery="Apple";
        //searchQuery=et.getText().toString().trim();
        String url = "http://newsapi.org/v2/everything?" +
                "q="+searchQuery+"&" +
                "from=2020-07-14&" +
                "sortBy=popularity&" +
                "apiKey=afcdb79c38d347b58105f5b408359ce3";

        //String url= "https://newsapi.org/v2/top-headlines?country=us&apiKey=afcdb79c38d347b58105f5b408359ce3";
        JsonObjectRequest jor=new JsonObjectRequest(Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>(){
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            //JSONObject result=response.getJSONObject("cases_time_series");
                            JSONArray jarr=response.getJSONArray("articles");
                            for(int i=0;i<jarr.length();i++)
                            {
                                JSONObject jobj=jarr.getJSONObject(i);
                                String author=jobj.getString("author");
                                String Title=jobj.getString("title");
                                String desc=jobj.getString("description");
                                String imgUrl=jobj.getString("urlToImage");

                                Title.toUpperCase();
                                author.toUpperCase();
                                mList.add(new Ex_top_news(Title,desc,imgUrl));
                            }
                            ea=new ExampleAdapter(YourStories.this,mList);
                            rv.setAdapter(ea);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                },
                new Response.ErrorListener(){

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });
        mReqQ.add(jor);
    }

}