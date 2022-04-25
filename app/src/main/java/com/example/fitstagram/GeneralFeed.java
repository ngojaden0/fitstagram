package com.example.fitstagram;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.text.MessageFormat;

public class GeneralFeed extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance(); //instantiate firestore
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_feed);

        PostButton(); //post button
        UserProfileButton(); // Justine
        RankingButton(); // Christian

        TextView ExamplePost = (TextView) findViewById(R.id.ExamplePost);

        //example post
        post examplePost = new post(123,456,"hi guys which outfit is best", false, 10,null);

        //example - add post object to firestore
        //db.collection("feed").add(examplePost);

        ExamplePost.setText(MessageFormat.format("User: {0}\nDescription: {1}\nTime: {2}", examplePost.getUser_id(), examplePost.getDescription(), examplePost.getTime()));
    }

    private void UserProfileButton() {
        Button profileButton = (Button) findViewById(R.id.profileButton);
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GeneralFeed.this, profile.class));
            }
        });
    }

    private void RankingButton() {
        /*
              Initialize a button that will take the user to the current ranking
              Create another activity
              Use Intent Class
         */
    }

    private void PostButton() {
        Button postButton = (Button) findViewById(R.id.post_button);
        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // start next activity
                startActivity(new Intent(GeneralFeed.this, userpost_or_voterpost.class));
            }
        });
    }
}