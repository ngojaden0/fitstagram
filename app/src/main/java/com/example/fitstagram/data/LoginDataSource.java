package com.example.fitstagram.data;

import static android.content.ContentValues.TAG;
import static android.content.Context.ACCOUNT_SERVICE;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.fitstagram.data.model.LoggedInUser;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.IOException;
import java.util.concurrent.Executor;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    Context context;
    FirebaseAuth mAuth;
    FirebaseUser user = null;
    public Result<LoggedInUser> login(String username, String password) {

        try {
            mAuth.signInWithEmailAndPassword(username, password)
                    .addOnCompleteListener((Executor) this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "signInWithEmail:success");
                                user = mAuth.getCurrentUser();
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "signInWithEmail:failure", task.getException());
                                Toast.makeText(context, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
            return new Result.Success<>(user);
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {

        // TODO: revoke authentication
    }
}