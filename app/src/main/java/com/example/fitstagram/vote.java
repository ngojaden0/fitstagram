package com.example.fitstagram;

public class vote {
    private int user_id;
    private int vote;
    private int point_distribution;
    private boolean correct;

    public vote(int user_id, int vote, int point_distribution, boolean correct) {
        this.user_id = user_id;
        this.vote = vote;
        this.point_distribution = point_distribution;
        this.correct = correct;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getVote() {
        return vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }

    public int getPoint_distribution() {
        return point_distribution;
    }

    public void setPoint_distribution(int point_distribution) {
        this.point_distribution = point_distribution;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }
}
