package com.example.fitstagram;

import java.util.ArrayList;

public class voting_post extends post{
    private ArrayList<Integer> total_votes;

    public voting_post(int user_id, long post_id, String description, boolean featured, int time, String[] pictures) {
        super(user_id, post_id, description, featured, time, pictures);
    }

    public ArrayList<Integer> getTotalVotes() {
        return total_votes;
    }

    public void setTotalVotes(ArrayList<Integer> total_votes) {
        this.total_votes = total_votes;
    }
}