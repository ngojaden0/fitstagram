package com.example.fitstagram;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fitstagram.databinding.ActivityProfileCreationBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class ProfileCreation extends AppCompatActivity {

    ActivityProfileCreationBinding binding;

    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    FirebaseFirestore dBase = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_creation);

        mAuth = FirebaseAuth.getInstance();

        binding = ActivityProfileCreationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if(currentUser != null)
            finish();

        //creates I/O machines
        final EditText username = binding.Username;
        final EditText emailAddress = binding.Email;
        final EditText password = binding.Password;
        final Button signUp = (Button) findViewById(R.id.signUp);

        signUp.setOnClickListener(v -> {
            if(username.getText().toString().length()<1)
                Toast.makeText(ProfileCreation.this, "Please input Username", Toast.LENGTH_SHORT).show();
            else if(!emailAddress.getText().toString().contains("@"))
                Toast.makeText(ProfileCreation.this, "Email must contain @", Toast.LENGTH_SHORT).show();
            else if(password.getText().toString().length()<5)
                Toast.makeText(ProfileCreation.this, "Password must be 5 characters or longer", Toast.LENGTH_SHORT).show();
            else
                signUpAttempt(username.getText().toString(), emailAddress.getText().toString(), password.getText().toString());
        });
    }

    private boolean signUpAttempt(String username, String email, String password)
    {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success");
                        Toast.makeText(ProfileCreation.this, "Authentication success.",
                                Toast.LENGTH_SHORT).show();
                        currentUser = mAuth.getCurrentUser();
                        userCreation(username, password);
                        finish();

                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.getException());
                        Toast.makeText(ProfileCreation.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                    }
                });
        return currentUser!= null;
    }
    private void userCreation(String username, String password)
    {
        dBase.collection("users").document(currentUser.getUid()).set(new user(username, password, currentUser.getUid(), 0,""));
    }
}