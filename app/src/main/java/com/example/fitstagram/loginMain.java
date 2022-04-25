package com.example.fitstagram;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fitstagram.databinding.ActivityLoginMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class loginMain extends AppCompatActivity {

    private ActivityLoginMainBinding binding;

    private FirebaseAuth mAuth;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);

        binding = ActivityLoginMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();

        //Instantiates I/O machines on screen
        final Button signIn = binding.signIn;
        final Button register = binding.register;
        final EditText email = binding.emailAddress;
        final EditText password = binding.password;

        //Sign in attempts
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(attemptSignIn(email.getText().toString(), password.getText().toString()))
                {
                    //SIGN IN COMPLETE: user = signedInUser
                    finish();
                }
                else
                    Toast.makeText(loginMain.this, "Incorrect Email or Password", Toast.LENGTH_SHORT).show();
            }
        });

        //Register attempts
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(register(email.getText().toString(), password.getText().toString()))
                {
                    //Takes you to ProfileCreation
                    startActivity(new Intent(loginMain.this, ProfileCreation.class));
                    finish();
                }
            }
        });
    }

    private boolean attemptSignIn(String email, String password)    //attempts sign in
    {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update user
                            Log.d(TAG, "signInWithEmail:success");
                            Toast.makeText(loginMain.this, "Authentication complete.",
                                    Toast.LENGTH_SHORT).show();
                            user = mAuth.getCurrentUser();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(loginMain.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            user = null;
                        }
                    }
                });
        return user != null;
    }

    private boolean register(String email, String password)
    {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            Toast.makeText(loginMain.this, "Authentication complete.",
                                    Toast.LENGTH_SHORT).show();
                            user = mAuth.getCurrentUser();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(loginMain.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        return user != null;
    }
}