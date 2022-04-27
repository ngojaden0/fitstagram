package com.example.fitstagram;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.common.internal.AccountAccessor;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class user {
    private String username;
    private String password;
    private String UID;
    private int total_points;
    private String bio;

    public user() {
        username = "";
        password = " ";
        UID = "";
        total_points = 0;
        bio = " ";
    }

    public user(String username, String password, String UID, int total_points, String bio) {
        this.username = username;
        this.password = password;
        this.UID = UID;
        this.total_points = total_points;
        this.bio = bio;
    }

    public String getUsername() {
        return username;
    }

    public void setUser_id(String user_id) {
        this.username = user_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUID() { return UID; }

    public int getTotal_points() {
        return total_points;
    }

    public void setTotal_points(int total_points) {
        this.total_points = total_points;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) { this.bio = bio; }

    public static user connectToDatabase(String UID, Context context)
    {
        final user[] output = {null};
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("users").document(UID);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                output[0] = documentSnapshot.toObject(user.class);
                Toast.makeText(context, "user is set to FirebaseUser", Toast.LENGTH_SHORT).show();
            }
        });

        return output[0];
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

}