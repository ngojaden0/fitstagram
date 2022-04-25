package com.example.fitstagram;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fitstagram.databinding.ActivityLoginMainBinding;
import com.example.fitstagram.databinding.ActivityProfileCreationBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileCreation extends AppCompatActivity {

    ActivityProfileCreationBinding binding;
    FirebaseAuth mAuth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_creation);

        mAuth = FirebaseAuth.getInstance();
        binding = ActivityProfileCreationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //creates I/O machines
        final EditText username = binding.Username;
        final EditText emailAddress = binding.Email;
        final EditText password = binding.Password;
        final Button signUp = binding.signUp;

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().length()<1)
                    Toast.makeText(ProfileCreation.this, "Please input Username", Toast.LENGTH_SHORT);
                else if(!emailAddress.getText().toString().contains("@"))
                    Toast.makeText(ProfileCreation.this, "Email must contain @", Toast.LENGTH_SHORT);
                else if(password.getText().toString().length()<5)
                    Toast.makeText(ProfileCreation.this, "Password must be 5 characters or longer", Toast.LENGTH_SHORT);
                else if(!signUpAttempt(emailAddress.getText().toString(), password.getText().toString()))
                    Toast.makeText(ProfileCreation.this, "Email already in use", Toast.LENGTH_SHORT);
                else
                {
                    Toast.makeText(ProfileCreation.this, "Profile Created", Toast.LENGTH_SHORT);
                    finish();
                }
            }
        });
    }

    private boolean signUpAttempt(String email, String password)
    {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            Toast.makeText(ProfileCreation.this, "Authentication complete.",
                                    Toast.LENGTH_SHORT).show();
                            user = mAuth.getCurrentUser();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(ProfileCreation.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        return user!= null;
    }
}