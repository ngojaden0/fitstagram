package com.example.fitstagram;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.Arrays;

public class profile extends AppCompatActivity {

    // Using ArrayList to store images data
    ArrayList images = new ArrayList<>(Arrays.asList(R.drawable.mfit1, R.drawable.mfit2, R.drawable.mfit3,R.drawable.mfit4));
    RecyclerView recyclerView;
    String name,email,uid;
    Uri photoUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //Setting Up User Profile
        //getUserProfile();
        TextView username = (TextView)findViewById(R.id.userName);
        username.setText(name);

        // Getting reference of recyclerView
        recyclerView = findViewById(R.id.recyclerView);

        // Setting the layout as Staggered Grid for vertical orientation
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);

        // Sending reference and data to Adapter
        Adapter adapter = new Adapter(profile.this, images);

        // Setting Adapter to RecyclerView
        recyclerView.setAdapter(adapter);
    }
    /*
    //Currently signed-in user
    public void getUserProfile(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            name = user.getDisplayName();
            email = user.getEmail();
            photoUrl = user.getPhotoUrl();

            // Check if user's email is verified
            boolean emailVerified = user.isEmailVerified();

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getIdToken() instead.
            uid = user.getUid();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.profile_menu, menu);
        return true;
    }

    private static final String TAG = "MyActivity";
    public boolean onOptionsItemSelected(MenuItem item){
        Toast.makeText(this, "Selected: " +item.getTitle(), Toast.LENGTH_SHORT).show();
        Button submit = (Button) findViewById(R.id.submitButton);
        switch(item.getItemId()){
            case R.id.changeUserName:
                //Accessing User in firebase
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                //Get Value from the EditText to the TextView
                TextView username = (TextView)findViewById(R.id.userName);
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

                        //Updating Firebase with new information
                        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder().setDisplayName(name).build();
                        user.updateProfile(profileUpdates)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Log.d(TAG, "User profile updated.");
                                        }
                                    }
                                });
                    }
                });
                return true;
            case R.id.changeAboutMe:
                //Get Value from the EditText to the TextView
                TextView aboutMe = (TextView)findViewById(R.id.userAboutMe);
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
                ImageView img = (ImageView) findViewById(R.id.pfp);
                img.setImageResource(R.drawable.pfp);
                return true;
            case R.id.logout:
                // do your code
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
     */
}