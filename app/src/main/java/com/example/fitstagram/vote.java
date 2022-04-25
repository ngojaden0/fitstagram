package com.example.fitstagram;

public class vote extends user{
    private int vote;
    private int point_distribution;
    private boolean correct;

    public vote(int user_id, int vote, int point_distribution, boolean correct) {
        this.vote = vote;
        this.point_distribution = point_distribution;
        this.correct = correct;
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
