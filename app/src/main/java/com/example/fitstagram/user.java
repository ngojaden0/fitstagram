package com.example.fitstagram;

public class user {
    private String user_id;
    private String password;
    private int total_points;
    private String bio;

    public user() {
        user_id = "";
        password = " ";
        total_points = 0;
        bio = " ";
    }

    public user(String user_id, String password, int total_points, String bio) {
        this.user_id = user_id;
        this.password = password;
        this.total_points = total_points;
        this.bio = bio;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTotal_points() {
        return total_points;
    }

    public void setTotal_points(int total_points) {
        this.total_points = total_points;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}