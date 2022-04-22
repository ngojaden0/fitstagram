package com.example.fitstagram;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.w3c.dom.Text;

import java.io.IOException;
import java.text.MessageFormat;

public class GeneralFeed extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance(); //instantiate firestore
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_feed);
        String name;
        String description;

        PostButton(); //post button
        UserProfileButton(); // Justine
        RankingButton(); // Christian
        TextView ExamplePost = (TextView) findViewById(R.id.ExamplePost);
        TextView ExampleURI = (TextView) findViewById(R.id.uri_string);
        ImageView ExampleImage = (ImageView) findViewById(R.id.example_image) ;
        DocumentReference general_feed = db.collection("feed").document("3991");
        general_feed.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                post example = documentSnapshot.toObject(post.class);
                assert example != null;
                ExamplePost.setText(Integer.toString(example.getUser_id())+"\n"+example.getDescription());
            }
        });
        storageRef.child(Integer.toString(123)+"/"+Integer.toString(3991)+"/"+"image:31").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
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
}