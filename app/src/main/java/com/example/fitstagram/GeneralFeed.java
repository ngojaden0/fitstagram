package com.example.fitstagram;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class GeneralFeed extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance(); //instantiate firestore
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_feed);

        PostButton(); //post button
        UserProfileButton(); // Justine
        RankingButton(); // Christian
        VoteButton();
        TextView ExamplePost = (TextView) findViewById(R.id.ExamplePost);
        ImageView ExampleImage = (ImageView) findViewById(R.id.example_image);
        db.collection("feed").orderBy("current_time", Query.Direction.DESCENDING).limit(1);
        DocumentReference general_feed = db.collection("feed").document();
        general_feed.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                post example = documentSnapshot.toObject(post.class);
                assert example != null;
                ExamplePost.setText(Integer.toString(example.getUser_id())+"\n"+example.getDescription());
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                System.out.println("didn't work");
            }
        });

        storageRef.child(Integer.toString(123)+"/"+Integer.toString(5485)+"/"+"image:37").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @RequiresApi(api = Build.VERSION_CODES.P)
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(getApplicationContext()).load(uri).override(500,500).into(ExampleImage);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });
    }

    private void UserProfileButton() {
        /*
              Initialize a button that will take the user to their profile
              Create another activity
              Use Intent Class
         */
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
    private void VoteButton() {
        Button voteButton = (Button) findViewById(R.id.vote_button);
        voteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GeneralFeed.this, VotePage.class));
            }
        });
    }
}