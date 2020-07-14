package com.example.newsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleHolder> {
    Context cxt;
    ArrayList<Ex_top_news>mList;
    public ExampleAdapter(Context cxt,ArrayList<Ex_top_news> mList ){
        this.cxt=cxt;
        this.mList=mList;
    }

    @NonNull
    @Override
    public ExampleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(cxt).inflate(R.layout.top_news_item,parent,false);
        return new ExampleHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleHolder holder, int position) {
        Ex_top_news curr_item=mList.get(position);
        String img=curr_item.getmImageUrl();
        String tit=curr_item.getmTitle();
        String con=curr_item.getmContent();
        holder.title.setText(tit);
        holder.content.setText(con);
        Picasso.with(cxt).load(img).fit().centerInside().into(holder.iv);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ExampleHolder extends RecyclerView.ViewHolder{

        TextView title;
        TextView content;
        ImageView iv;
        public ExampleHolder(@NonNull View itemView) {
            super(itemView);
            iv=itemView.findViewById(R.id.iv);
            title=itemView.findViewById(R.id.title);
            content=itemView.findViewById(R.id.content);
        }
    }
}
