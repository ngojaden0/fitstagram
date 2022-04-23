package com.example.fitstagram;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class VotePage extends AppCompatActivity {

    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReference();

    int user_id = 123; //hardcoded to test voting
    int post_id = 2380; // hardcoded to test voting
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
        getImage(user_id, post_id, 31, voteButton1);
        voteButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void VoteButton2() {
        ImageButton voteButton2 = (ImageButton) findViewById(R.id.vote_2);
        getImage(user_id, post_id, 33, voteButton2);
        voteButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void VoteButton3() {
        ImageButton voteButton3 = (ImageButton) findViewById(R.id.vote_3);
        getImage(user_id, post_id, 35, voteButton3);
        voteButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    public void getImage(int user_id, int post_id, int image_num, ImageButton image) {
        storageRef.child(Integer.toString(user_id)+"/"+Integer.toString(post_id)+"/"+"image:"+Integer.toString(image_num)).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @RequiresApi(api = Build.VERSION_CODES.P)
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(getApplicationContext()).load(uri).override(250,250).into(image);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });
    }
}
