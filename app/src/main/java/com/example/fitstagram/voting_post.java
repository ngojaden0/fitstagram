package com.example.fitstagram;

import java.util.ArrayList;

public class voting_post extends post{
    private ArrayList<Integer> total_votes;
    private long final_time;
    private int choice_1;
    private int choice_2;
    private int choice_3;
    private int most_voted;

    public voting_post(int user_id, long post_id, String description, boolean voting, int time, String[] pictures) {
        super(user_id, post_id, description, voting, time, pictures);
        this.final_time = getPost_id()+(long)getTime()*60000L;
        this.choice_1 = 0;
        this.choice_2 = 0;
        this.choice_3 = 0;
        this.most_voted = tally_votes();
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

    public int getChoice_1() {
        return choice_1;
    }

    public void setChoice_1(int choice_1) {
        this.choice_1 = choice_1;
    }

    public int getChoice_2() {
        return choice_2;
    }

    public void setChoice_2(int choice_2) {
        this.choice_2 = choice_2;
    }

    public int getChoice_3() {
        return choice_3;
    }

    public void setChoice_3(int choice_3) {
        this.choice_3 = choice_3;
    }

    public int getMost_voted() {
        return most_voted;
    }

    public void setMost_voted(int most_voted) {
        this.most_voted = most_voted;
    }

    public int tally_votes()
    {
        if((this.choice_1 > this.choice_2) && (this.choice_1 > this.choice_3))
            return 1;
        else if((this.choice_2 > this.choice_1) && (this.choice_2 > this.choice_3))
            return 2;
        else
            return 3;
    }

    public ArrayList<Integer> getTotal_votes() {
        return total_votes;
    }

    public void setTotal_votes(ArrayList<Integer> total_votes) {
        this.total_votes = total_votes;
    }
}