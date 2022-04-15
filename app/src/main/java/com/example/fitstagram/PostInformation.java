package com.example.fitstagram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PostInformation extends AppCompatActivity {
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
        postSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
            //transfer data between activities
            //in posting branch
        });
    }
}