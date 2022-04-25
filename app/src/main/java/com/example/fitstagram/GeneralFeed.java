package com.example.fitstagram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.MessageFormat;
//this is login
public class GeneralFeed extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance(); //instantiate firestore
    FirebaseUser currentUser;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_feed);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        if(currentUser == null)
            startActivity(new Intent(GeneralFeed.this, loginMain.class));
        else
            Toast.makeText(GeneralFeed.this, "Logged in as "+currentUser.getEmail(), Toast.LENGTH_SHORT).show();

        PostButton(); //post signIn
        UserProfileButton(); // Justine
        RankingButton(); // Christian

        TextView ExamplePost = (TextView) findViewById(R.id.ExamplePost);

        //example post
        post examplePost = new post("123","hi guys which outfit is best", false, false,10,null);

        //example - add post object to firestore
        //db.collection("feed").add(examplePost);

        ExamplePost.setText(MessageFormat.format("User: {0}\nDescription: {1}\nTime: {2}", examplePost.getUser_id(), examplePost.getDescription(), examplePost.getTime()));
    }

    private void UserProfileButton() {
        /*
              Initialize a signIn that will take the user to their profile
              Create another activity
              Use Intent Class
         */
    }

    private void RankingButton() {
        /*
              Initialize a signIn that will take the user to the current ranking
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