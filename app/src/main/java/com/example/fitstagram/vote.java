package com.example.fitstagram;

public class vote{
    private int user_id;
    private long post_id;
    private int choice;

    public vote() {
        this.user_id = 0;
        this.post_id = 0L;
        this.choice = 0;
    }
    public vote(int user_id, long post_id, int choice) {
        this.user_id = user_id;
        this.post_id = post_id;
        this.choice = choice;
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

    public int getChoice() {
        return choice;
    }

    public void setChoice(int choice) {
        this.choice = choice;
    }
}
