package com.example.fitstagram;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;

public class GeneralFeed extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance(); //instantiate firestore
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PostButton(); //post button
        UserProfileButton(); // Justine
        RankingButton(); // Christian
        //VoteButton();
        TextView ExamplePost = (TextView) findViewById(R.id.ExamplePost);
        ImageView ExampleImage = (ImageView) findViewById(R.id.example_image);
        db.collection("feed").orderBy("post_id", Query.Direction.DESCENDING)
        .get()
        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
        @SuppressLint("SetTextI18n")
        @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        document.get("post_id");
                    }
                    //one instance from query, do everything in the for each loop for accessing all
                    //.get(0) = most recent post in the query
                    DocumentSnapshot single = task.getResult().getDocuments().get(0);
                    Log.d("feed","hey: "+single.get("user_id"));
                    Object user_id = single.get("user_id");
                    Object post_id = single.get("post_id");
                    Object description = single.get("description");
                    String i = user_id.toString();
                    String j = post_id.toString();
                    String k = description.toString();
                    //ExamplePost.setText(i+"\n"+k); //display example post id and description
                    StorageReference listRef = storage.getReference().child(j);
                    listRef.listAll().addOnSuccessListener(new OnSuccessListener<ListResult>() {
                        @Override
                            public void onSuccess(ListResult listResult) {
                                for (StorageReference item : listResult.getItems()) {
                                    // All the items under listRef.
                                    String sub = item.toString().substring(56);
                                    // sub = image:## -> ##
                                    storageRef.child(j+"/"+"image:"+sub).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @RequiresApi(api = Build.VERSION_CODES.P)
                                        @Override
                                        public void onSuccess(Uri uri) {
                                            //Glide.with(getApplicationContext()).load(uri).override(500,500).into(ExampleImage);
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception exception) {
                                            // Handle any errors
                                        }
                                    });
                                }
                            }
                        })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d("images", "dun goofed");
                        }
                    });
                }
        }});
    }

    private void UserProfileButton() {
        Button profileButton = (Button) findViewById(R.id.user_button);
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GeneralFeed.this, profile.class));
            }
        });
    }

    private void RankingButton() {
        Button rankingButton = (Button) findViewById(R.id.ranking_button);
        rankingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GeneralFeed.this, ranking.class));
            }
        });
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
    /*
    private void VoteButton() {
        Button voteButton = (Button) findViewById(R.id.vote_button);
        voteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GeneralFeed.this, VotePage.class));
            }
        });
    }
*/
}