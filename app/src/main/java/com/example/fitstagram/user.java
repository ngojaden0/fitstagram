package com.example.fitstagram;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.annotations.NotNull;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

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

    private static ArrayList<user> output = new ArrayList<>();
    private static boolean databaseConnected = false;
    public static user connectToDatabase(String UID, Context context)
    {
        output.add(0, new user());
        readData(UID, new MyCallback() {
            @Override
            public void onCallback(user u) {
                output.set(0, u);
                databaseConnected = true;
                Intent i = new Intent(context, GeneralFeed.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });
        return output.get(0);
    }

    private static void readData(String UID, MyCallback myCallback) {
        DocumentReference mSettings = FirebaseFirestore.getInstance().collection("users").document(UID);
        mSettings.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                user u = documentSnapshot.toObject(user.class);
                myCallback.onCallback(u);
            }
        });
    }

    private interface MyCallback {
        void onCallback(user u);
    }

    public static boolean isDatabaseConnected()
    {
        return databaseConnected;
    }

    public static user databaseGetUser()
    {
        return output.get(0);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private void setUID(String UID) {
        this.UID = UID;
    }   //User ID's can't change. Only update if UID is incorrect

}