package com.example.fitstagram;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.IOException;

public class profile extends AppCompatActivity {
    private static final int GALLERY_REQUEST = 1 ;
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getUserProfile();
        onBtnReturn();
    }

    public void onBtnReturn() {
        Button goBack = (Button) findViewById(R.id.returnBtn);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.profile_menu, menu);
        return true;
    }

    public void getUserProfile() {
        // [START get_user_profile]
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        Toast.makeText(this, "" + user.getUid(), Toast.LENGTH_SHORT).show();

        if (user != null) {
            // Name, email address, and profile photo Url
            String name = user.getDisplayName();
            String email = user.getEmail();
            Uri photoUrl = user.getPhotoUrl();

            // Check if user's email is verified
            boolean emailVerified = user.isEmailVerified();

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getIdToken() instead.
            String uid = user.getUid();
        }
        // [END get_user_profile]
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(this, "Selected: " + item.getTitle(), Toast.LENGTH_SHORT).show();
        Button submit = (Button) findViewById(R.id.submitButton);
        switch (item.getItemId()) {
            case R.id.changeUserName:
                //Get Value from the EditText to the TextView
                TextView username = (TextView) findViewById(R.id.userName);
                EditText editUserName = (EditText) findViewById(R.id.editUserName);

                //Changing Visibility to edit
                editUserName.setVisibility(View.VISIBLE);
                username.setVisibility(View.INVISIBLE);
                submit.setVisibility(View.VISIBLE);

                submit.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        String name = editUserName.getText().toString();
                        username.setText(name);

                        //Changing Visibility
                        editUserName.setVisibility(View.INVISIBLE);
                        username.setVisibility(View.VISIBLE);
                        submit.setVisibility(View.INVISIBLE);
                    }
                });
                return true;
            case R.id.changeAboutMe:
                //Get Value from the EditText to the TextView
                TextView aboutMe = (TextView) findViewById(R.id.userAboutMe);
                EditText editAboutMe = (EditText) findViewById(R.id.editAboutMe);

                //Changing Visibility
                aboutMe.setVisibility(View.INVISIBLE);
                editAboutMe.setVisibility(View.VISIBLE);
                submit.setVisibility(View.VISIBLE);

                submit.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        String name = editAboutMe.getText().toString();
                        aboutMe.setText(name);

                        //Changing Visibility
                        aboutMe.setVisibility(View.VISIBLE);
                        editAboutMe.setVisibility(View.INVISIBLE);
                        submit.setVisibility(View.INVISIBLE);
                    }
                });
                return true;
            case R.id.changePfp:
                img = findViewById(R.id.pfp);
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, GALLERY_REQUEST);
                return true;
            case R.id.logout:
                // do your code
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK)
            switch (requestCode){
                case GALLERY_REQUEST:
                    Uri selectedImage = data.getData();
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(profile.this.getContentResolver(), selectedImage);
                        img.setImageBitmap(bitmap);
                    } catch (IOException e) {
                        Log.i("TAG", "Some exception " + e);
                    }
                    break;
            }
    }
}