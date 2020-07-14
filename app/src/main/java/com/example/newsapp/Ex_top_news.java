package com.example.newsapp;

public class Ex_top_news {
    String mTitle;
    String mContent;
    String mImageUrl;
    public Ex_top_news(String mTitle,String mContent,String mImageUrl)
    {
        this.mContent=mContent;
        this.mImageUrl=mImageUrl;
        this.mTitle=mTitle;
    }

    public String getmImageUrl()
    {
        return mImageUrl;
    }
    public String getmContent()
    {
        return mContent;
    }
    public String getmTitle()
    {
        return mTitle;
    }
}
