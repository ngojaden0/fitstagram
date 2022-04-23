package com.example.profile;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    // Using ArrayList to store images data
    ArrayList images = new ArrayList<>(Arrays.asList(R.drawable.mfit1, R.drawable.mfit2, R.drawable.mfit3,R.drawable.mfit4));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Getting reference of recyclerView
        recyclerView = findViewById(R.id.recyclerView);

        // Setting the layout as Staggered Grid for vertical orientation
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);

        // Sending reference and data to Adapter
        Adapter adapter = new Adapter(MainActivity.this, images);

        // Setting Adapter to RecyclerView
        recyclerView.setAdapter(adapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.profile_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        Toast.makeText(this, "Selected: " +item.getTitle(), Toast.LENGTH_SHORT).show();
        Button submit = (Button) findViewById(R.id.submitButton);

        switch(item.getItemId()){
            case R.id.changeUserName:
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
}