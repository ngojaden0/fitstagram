package com.example.fitstagram;

public class user {
    private int user_id;
    private String password;
    private int total_points;
    private String bio;
    private int badges;

    public user() {
        user_id = 0;
        password = " ";
        total_points = 0;
        bio = " ";
        badges = 0;
    }

    public user(int user_id, String password, int total_points, String bio, int badges) {
        this.user_id = user_id;
        this.password = password;
        this.total_points = total_points;
        this.bio = bio;
        this.badges = badges;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
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

    public int getBadges() {
        return badges;
    }

    public void setBadges(int badges) {
        this.badges = badges;
    }
}
