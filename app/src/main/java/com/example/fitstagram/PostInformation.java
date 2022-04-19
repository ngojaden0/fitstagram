package com.example.fitstagram;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.firestore.FirebaseFirestore;

public class PostInformation extends AppCompatActivity {
    private Object NullPointerException;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_information);
        PostBackButton();
        PostSubmitButton();
    }
    private void PostBackButton(){
        Button postBackButton = (Button) findViewById(R.id.back_button);
        postBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void PostSubmitButton(){
        Button postSubmitButton = (Button) findViewById(R.id.submit_post_button);
        EditText description = (EditText) findViewById(R.id.description);
        EditText time = (EditText) findViewById(R.id.time);
        AlertDialog.Builder builder = new AlertDialog.Builder(PostInformation.this);
        postSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // error handle no user input
                boolean choice = false;
                Bundle extras = getIntent().getExtras();
                if (extras != null) {
                    choice = extras.getBoolean("key");
                }
                Intent intent = new Intent(PostInformation.this,GeneralFeed.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                if(!TextUtils.isEmpty(description.getText().toString()) || !TextUtils.isEmpty(time.getText().toString())) {
                    if(choice)
                        db.collection("feed").add(new post(123, description.getText().toString(), false, Integer.parseInt(time.getText().toString()), null));
                    else
                        db.collection("feed").add(new voting_post(123, description.getText().toString(), false, Integer.parseInt(time.getText().toString()), null));
                    startActivity(intent);
                }
                else {
                    builder.setMessage("Please fill in information");
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
            }
        });
    }
}