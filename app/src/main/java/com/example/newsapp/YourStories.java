package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class YourStories extends AppCompatActivity {

//    private TextView mTextViewResult;
//    private ImageView iv;
//    private RequestQueue mReqQ;

    private  static final String TAG="Main Activity";
    RecyclerView rv;
    ExampleAdapter ea;
    ArrayList<Ex_top_news> mList;
    RequestQueue mReqQ;
    ImageView fIV;
    TextView fTv;
    String searchEle;
    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_stories);

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
        searchEle="india";
        mReqQ=Volley.newRequestQueue(this);
        jsonClick();
    }


    private void jsonClick(){
       // String url= "https://newsapi.org/v2/top-headlines?country=us&apiKey=afcdb79c38d347b58105f5b408359ce3";
        //searchEle="bitcoin";
        String url="https://newsapi.org/v2/everything?q="+searchEle+"&apiKey=afcdb79c38d347b58105f5b408359ce3";
        Log.i(TAG, url);
        JsonObjectRequest jor=new JsonObjectRequest(Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>(){
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            JSONArray jarr=response.getJSONArray("articles");
                            JSONObject jobj1=jarr.getJSONObject(0);
                            String author1=jobj1.getString("author");
                            String Title1=jobj1.getString("title");
                            String desc1=jobj1.getString("description");
                            String imgUrl1=jobj1.getString("urlToImage");
                            Title1.toUpperCase();
                            author1.toUpperCase();
                            // Picasso.with(topStories.this).load(imgUrl1).fit().centerInside().into(fIV);

                            for(int i=1;i<jarr.length();i++)
                            {
                                JSONObject jobj=jarr.getJSONObject(i);
                                String author=jobj.getString("author");
                                String Title=jobj.getString("title");
                                String desc=jobj.getString("description");
                                String imgUrl=jobj.getString("urlToImage");

                                Title.toUpperCase();
                                author.toUpperCase();
                                desc.trim();
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


    public void searchStart(View view) {
        searchEle=et.getText().toString().trim();
        setContentView(R.layout.activity_your_stories);
        jsonClick();

    }
}