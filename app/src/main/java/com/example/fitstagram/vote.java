package com.example.fitstagram;

public class vote extends user{
    private int user_id;
    private long post_id;
    private boolean first_choice;
    private boolean second_choice;
    private boolean third_choice;

    public vote(int user_id, long post_id) {
        this.user_id = user_id;
        this.post_id = post_id;
        this.first_choice = false;
        this.second_choice = false;
        this.third_choice = false;
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

    public boolean isFirst_choice() {
        return first_choice;
    }

    public void setFirst_choice(boolean first_choice) {
        this.first_choice = first_choice;
    }

    public boolean isSecond_choice() {
        return second_choice;
    }

    public void setSecond_choice(boolean second_choice) {
        this.second_choice = second_choice;
    }

    public boolean isThird_choice() {
        return third_choice;
    }

    public void setThird_choice(boolean third_choice) {
        this.third_choice = third_choice;
    }
}
