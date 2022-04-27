package com.example.fitstagram;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fitstagram.databinding.ActivityLoginMainBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class loginMain extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);

        com.example.fitstagram.databinding.ActivityLoginMainBinding binding = ActivityLoginMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        //Instantiates I/O machines on screen
        final Button signIn = (Button) findViewById(R.id.signIn);
        final Button register = (Button) findViewById(R.id.register);
        final EditText email = binding.emailAddress;
        final EditText password = binding.password;

        if(currentUser != null)
            finish();

        //Sign in attempts
        signIn.setOnClickListener(v -> {
            if(attemptSignIn(email.getText().toString(), password.getText().toString()))
            {
                //SIGN IN COMPLETE: user = signedInUser
                finish();
            }
            else
                Toast.makeText(loginMain.this, "Incorrect Email or Password", Toast.LENGTH_SHORT).show();
        });
        //Register attempts
        register.setOnClickListener(v -> {
            startActivity(new Intent(loginMain.this, ProfileCreation.class));
            currentUser = mAuth.getCurrentUser();
            finish();
        });
        if(currentUser != null)
            finish();
    }

    private boolean attemptSignIn(String email, String password)    //attempts sign in
    {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update user
                        Log.d(TAG, "signInWithEmail:success");
                        Toast.makeText(loginMain.this, "Authentication complete.",
                                Toast.LENGTH_SHORT).show();
                        currentUser = mAuth.getCurrentUser();
                        finish();
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithEmail:failure", task.getException());
                        Toast.makeText(loginMain.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                    }
                });
        return currentUser != null;
    }
}