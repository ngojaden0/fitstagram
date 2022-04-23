package com.example.fitstagram;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;

import java.util.ArrayList;

public class post extends BitmapFactory{
    private String user_id;
    private String description;
    private boolean featured;
    private boolean voting;
    private int time;
    private ArrayList<Bitmap> pictures;

    public post(String user_id, String description, boolean featured, boolean voting, int time, ArrayList<Bitmap> pictures){
        this.user_id = user_id;
        this.description = description;
        this.featured = featured;
        this.voting = voting;
        this.time = time;
        this.pictures = pictures;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isFeatured() {
        return featured;
    }

    public void setFeatured(boolean featured) {
        this.featured = featured;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public boolean isVoting() {
        return voting;
    }

    public void setVoting(boolean voting) {
        this.voting = voting;
    }

    public ArrayList<Bitmap> getPictures() {
        return pictures;
    }

    public void setPictures(ArrayList<Bitmap> pictures) {
        this.pictures = pictures;
    }
}
