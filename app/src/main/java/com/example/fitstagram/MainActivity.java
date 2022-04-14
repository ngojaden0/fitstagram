package com.example.fitstagram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.StringPrepParseException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PostButton();
        TextView ExamplePost = (TextView) findViewById(R.id.ExamplePost);
        TextView FeedSize = (TextView) findViewById(R.id.feed_size);

        post examplePost = new post(123,"hi guys which outfit is best", false, 10);
        List<post> GeneralFeed = new ArrayList<>();

        GeneralFeed.add(examplePost);
        ExamplePost.setText(MessageFormat.format("User: {0}\nDescription: {1}\nTime: {2}", examplePost.getUser_id(), examplePost.getDescription(), examplePost.getTime()));
        FeedSize.setText(MessageFormat.format("Feed Size: {0}", GeneralFeed.size()));
    }
    private void PostButton(){
        Button postButton = (Button) findViewById(R.id.post_button);

        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, PostInformation.class));
            }
        });
    }
}