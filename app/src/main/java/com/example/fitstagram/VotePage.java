package com.example.fitstagram;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class VotePage extends AppCompatActivity {

    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReference();
    FirebaseFirestore db = FirebaseFirestore.getInstance(); //instantiate firestore
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote_page);
        Bundle extras = getIntent().getExtras();
        String user_id = extras.getString("user");
        String post_id = extras.getString("post");
        String description = extras.getString("description");
        int image_1 = Integer.parseInt(extras.getString("picture_1"));
        int image_2 = Integer.parseInt(extras.getString("picture_2"));
        int image_3 = Integer.parseInt(extras.getString("picture_3"));
        VoteButton1(user_id, post_id, image_1);
        VoteButton2(user_id, post_id, image_2);
        VoteButton3(user_id, post_id, image_3);
        TextView text = (TextView) findViewById(R.id.about_post);
        text.setText("User: "+user_id+"\n"+"Description: "+description);
    }
    private void VoteButton1(String user_id, String post_id, int image_1) {
        ImageButton voteButton1 = (ImageButton) findViewById(R.id.vote_1);
        getImage(user_id, post_id, image_1, voteButton1);
        voteButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.collection("users").document(user_id).collection("votes").document(post_id).set(new vote(Integer.parseInt(user_id),Long.parseLong(post_id), 1));
                DocumentReference total = db.collection("feed").document(post_id);
                total.update("choice_1", FieldValue.increment(1));
                finish();
            }
        });
    }
    private void VoteButton2(String user_id, String post_id, int image_2) {
        ImageButton voteButton2 = (ImageButton) findViewById(R.id.vote_2);
        getImage(user_id, post_id, image_2, voteButton2);
        voteButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.collection("users").document(user_id).collection("votes").document(post_id).set(new vote(Integer.parseInt(user_id),Long.parseLong(post_id), 2));
                DocumentReference total = db.collection("feed").document(post_id);
                total.update("choice_2", FieldValue.increment(1));
                finish();
            }
        });
    }
    private void VoteButton3(String user_id, String post_id, int image_3) {
        ImageButton voteButton3 = (ImageButton) findViewById(R.id.vote_3);
        getImage(user_id, post_id, image_3, voteButton3);
        voteButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.collection("users").document(user_id).collection("votes").document(post_id).set(new vote(Integer.parseInt(user_id),Long.parseLong(post_id), 3));
                DocumentReference total = db.collection("feed").document(post_id);
                total.update("choice_3", FieldValue.increment(1));
                finish();
            }
        });
    }
    public void getImage(String user_id, String post_id, int image_num, ImageButton image) {
        storageRef.child(post_id+"/"+"image:"+Integer.toString(image_num)).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @RequiresApi(api = Build.VERSION_CODES.P)
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(getApplicationContext()).load(uri).override(500,500).into(image);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });
    }
}
