package com.example.fitstagram;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class VotePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote_page);
        VoteButton1();
        VoteButton2();
        VoteButton3();


    }
    private void VoteButton1() {
        ImageButton voteButton1 = (ImageButton) findViewById(R.id.vote_1);
        voteButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               finish();
            }
        });
    }
    private void VoteButton2() {
        ImageButton voteButton2 = (ImageButton) findViewById(R.id.vote_2);
        voteButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void VoteButton3() {
        ImageButton voteButton3 = (ImageButton) findViewById(R.id.vote_3);
        voteButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}