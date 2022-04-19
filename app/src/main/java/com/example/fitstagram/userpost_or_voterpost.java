package com.example.fitstagram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class userpost_or_voterpost extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userpost_or_voterpost);

        UserPostButton();
        VotePostButton();
        BackButton();
    }

    private void BackButton() {
        Button back_button = (Button) findViewById(R.id.backbutton);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void UserPostButton() {
        Button user_post = (Button) findViewById(R.id.userpost);
        user_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean choice = true;
                Intent i = new Intent(userpost_or_voterpost.this, PostInformation.class);
                i.putExtra("key",choice);
                startActivity(i);
            }
        });
    }

    private void VotePostButton() {
        Button voter_post = (Button) findViewById(R.id.voterpost);
        voter_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean choice = false;
                Intent i = new Intent(userpost_or_voterpost.this, PostInformation.class);
                i.putExtra("key",choice);
                startActivity(i);
            }
        });
    }
}