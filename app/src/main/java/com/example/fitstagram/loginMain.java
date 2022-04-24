package com.example.fitstagram;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class loginMain extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);

        mAuth = FirebaseAuth.getInstance();

        //final Button signIn =
    }

    protected FirebaseUser getUser()    //returns FirebaseUser if authorized, null otherwise
    {
        return null;
    }
}