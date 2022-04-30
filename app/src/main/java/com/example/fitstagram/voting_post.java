package com.example.fitstagram;

import java.util.ArrayList;

public class voting_post extends post{
    private ArrayList<Integer> total_votes;
    private long final_time;

    public voting_post(int user_id, long post_id, String description, boolean voting, int time, String[] pictures) {
        super(user_id, post_id, description, voting, time, pictures);
        this.final_time = getPost_id()+(long)getTime()*60000L;
    }

    public ArrayList<Integer> getTotalVotes() {
        return total_votes;
    }

    public long getFinal_time() {
        return final_time;
    }

    public void setFinal_time(long final_time) {
        this.final_time = final_time;
    }

    public void setTotalVotes(ArrayList<Integer> total_votes) {
        this.total_votes = total_votes;
    }
}