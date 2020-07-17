//package com.example.newsapp;
//
//import android.os.Bundle;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.JsonObjectRequest;
//import com.android.volley.toolbox.Volley;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.util.ArrayList;
//
//public class Frag4 extends Fragment {
//
//
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    public Frag4() {
//
//    }
//
//    public static Frag4 newInstance(String param1, String param2) {
//        Frag4 fragment = new Frag4();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//
//        }
//
//    }
//
//
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//
//        return inflater.inflate(R.layout.fragment_frag4, container, false);
//    }
//
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        final RecyclerView r1=(RecyclerView)view.findViewById(R.id.recyclerView);
//        RequestQueue mQueue= Volley.newRequestQueue(getContext());
//
//
//
//        String url = "https://newsapi.org/v2/top-headlines?country=us&apiKey=27e64574c3c64573afbea511eb0e65d2";
//        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        try {
//
//                            ArrayList<NewsData> items = new ArrayList <>();
//                            JSONArray jsonArray = response.getJSONArray("articles");
//                            for(int i=0;i<jsonArray.length();i++){
//                                JSONObject cases = jsonArray.getJSONObject(i);
//                                String title = cases.getString("title");
//                                String url = cases.getString("urlToImage");
//                                String info = cases.getString("url");
//
//                                items.add(new NewsData(title,url,info));
//
//                            }
//                            MyOwnAdapter adapter = new MyOwnAdapter(getContext(), items);
//                            r1.setAdapter(adapter);
//                            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
//                            r1.setLayoutManager(layoutManager);
//
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                error.printStackTrace();
//            }
//        });
//        mQueue.add(request);
//    }
//
//
//}