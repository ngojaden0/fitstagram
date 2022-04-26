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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote_page);
        Bundle extras = getIntent().getExtras();
        String user_id = extras.getString("user");
        String post_id = extras.getString("post");
        int image_1 = Integer.parseInt(extras.getString("picture_1"));
        int image_2 = Integer.parseInt(extras.getString("picture_2"));
        int image_3 = Integer.parseInt(extras.getString("picture_3"));
        VoteButton1(user_id, post_id, image_1);
        VoteButton2(user_id, post_id, image_2);
        VoteButton3(user_id, post_id, image_3);
    }
    private void VoteButton1(String user_id, String post_id, int image_1) {
        ImageButton voteButton1 = (ImageButton) findViewById(R.id.vote_1);
        getImage(user_id, post_id, image_1, voteButton1);
        voteButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                finish();
            }
        });
    }
    public void getImage(String user_id, String post_id, int image_num, ImageButton image) {
        storageRef.child(post_id+"/"+"image:"+Integer.toString(image_num)).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
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
