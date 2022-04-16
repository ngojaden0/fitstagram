package com.example.userprofile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.graphics.Color;
import android.os.Handler;
import android.view.View;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    //Contains all users post
    CardView post_1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        post_1 = (CardView) findViewById(R.id.post_1);
        post_1.setVisibility(View.GONE);
        post_1.setCardBackgroundColor(Color.TRANSPARENT);
        post_1.setCardElevation(0);


    }
}

