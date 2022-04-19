package com.example.fitstagram;

import android.graphics.Bitmap;

import java.util.ArrayList;

public class voting_post extends post{

    // A vote object will be stored in this arraylist, Integer as placeholder for now
    // The vote object will contain:
    // (int) user_id: user who casted the vote
    // (int) vote: user's vote, integer between 1-3;
    //      create castVote() function
    // (boolean) correct: if the user chooses the most voted outfit
    // (int) point_distribution(user_id): access and modify user's point count

    private ArrayList<Integer> total_votes;

    public voting_post(int user_id, String description, boolean featured, int time, ArrayList<Bitmap> pictures) {
        super(user_id, description, featured, time, pictures);
        this.total_votes = total_votes;
    }

    public ArrayList<Integer> getTotalVotes() {
        return total_votes;
    }

    public void setTotalVotes(ArrayList<Integer> total_votes) {
        this.total_votes = total_votes;
    }
}