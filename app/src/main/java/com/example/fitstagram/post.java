package com.example.fitstagram;

public class post {
    private int user_id;
    private long post_id;
    private String description;
    private boolean voting;
    private int time;
    private String[] pictures;

    public post(){
        this.user_id = 0;
        this.post_id = 0;
        this.description = " ";
        this.voting = false;
        this.time = 0;
        this.pictures = null;
    }
    public post(int user_id, long post_id, String description, boolean voting, int time, String[] pictures){
        this.user_id = user_id;
        this.post_id = post_id;
        this.description = description;
        this.voting = voting;
        this.time = time;
        this.pictures = pictures;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public long getPost_id() {
        return post_id;
    }

    public void setPost_id(long post_id) {
        this.post_id = post_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isVoting() {
        return voting;
    }

    public void setVoting(boolean voting) {
        this.voting = voting;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String[] getPictures() {
        return pictures;
    }

    public void setPictures(String[] pictures) {
        this.pictures = pictures;
    }
}
