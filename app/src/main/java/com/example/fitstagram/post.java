package com.example.fitstagram;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class post extends BitmapFactory{
    private int user_id;
    private int post_id;
    private String description;
    private boolean featured;
    private int time;
    private String[] pictures;

    public post(int user_id, int post_id, String description, boolean featured, int time, String[] pictures){
        this.user_id = user_id;
        this.post_id = post_id;
        this.description = description;
        this.featured = featured;
        this.time = time;
        this.pictures = pictures;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
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
}
