package com.example.fitstagram;

public class postModel {
    private String description;
    private long user_id;
    private long post_id;

    private postModel(){}

    private postModel(String description, long user_id, long post_id){
        this.description = description;
        this.user_id = user_id;
        this.post_id = post_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getPost_id() {
        return post_id;
    }

    public void setPost_id(long post_id) {
        this.post_id = post_id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }
}
