package com.example.newsapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
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

public class frag2 extends Fragment {

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
    EditText et;
    int changed;
    ImageView searchBtn;
    //String searchEle;
   // View v;

    public void startSearch(View view) {
        String searchEle=et.getText().toString().trim();
        //setContentView(R.layout.activity_your_stories);
        jsonClick(searchEle);
    }

    //final ImageView searchBtn

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //v=getView();
        changed=0;
        View rootView=inflater.inflate(R.layout.fragment_frag2, container, false);
        rv=rootView.findViewById(R.id.rv);
        et=rootView.findViewById(R.id.et1);
        searchBtn=(ImageView) rootView.findViewById(R.id.search);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mList=new ArrayList<>();
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String searchEle=et.getText().toString().trim();
                Log.i(TAG, "onClick: "+searchEle);
                //setContentView(R.layout.activity_your_stories);
                changed=1;
                jsonClick(searchEle);
            }
        });
        mReqQ=Volley.newRequestQueue(getActivity());
        jsonClick("india");
        return rootView;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_your_stories);

//        mTextViewResult=findViewById(R.id.text);
//        iv=findViewById(R.id.iv);
//        Button b = findViewById(R.id.button);
//
//        mReqQ= Volley.newRequestQueue(this);
//        jsonClick();


    }


    private void jsonClick(String searchEle){
        //String url= "https://newsapi.org/v2/top-headlines?country=us&apiKey=afcdb79c38d347b58105f5b408359ce3";
        String url="https://newsapi.org/v2/everything?q="+searchEle+"&apiKey=afcdb79c38d347b58105f5b408359ce3";
        //String url="http://newsapi.org/v2/everything?q=Apple&" + "from=2020-07-14&" + "sortBy=popularity&" + "apiKey=afcdb79c38d347b58105f5b408359ce3";
        Log.i(TAG, url);
        if(changed==1){
            mList.clear();
        }
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
                                Log.i(TAG, "onResponse: "+Title);
                                author.toUpperCase();
                                desc.trim();
                                mList.add(new Ex_top_news(Title,desc,imgUrl));
                                Log.i(TAG, "onResponse:list added");
                            }
                            ea=new ExampleAdapter(getActivity(),mList);
                            rv.setAdapter(ea);
                            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                            rv.setLayoutManager(layoutManager);
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
        if(changed==1)
        {
            ea.notifyDataSetChanged();
        }
    }



}