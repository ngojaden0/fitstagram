package com.example.generalfeedui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import android.view.MenuItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.ArrayList;
import java.util.Arrays;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerViewPhoto;

    // Using ArrayList to store images data
    ArrayList images = new ArrayList<>(Arrays.asList(R.drawable.mfit2, R.drawable.mfit3,
            R.drawable.mfit4));
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Getting reference of recyclerView
        recyclerViewPhoto = (RecyclerView) findViewById(R.id.recyclerViewPictures);

        // Setting the layout as Staggered Grid for vertical orientation
        StaggeredGridLayoutManager staggeredGridLayoutManagerH = new StaggeredGridLayoutManager(1, LinearLayoutManager.HORIZONTAL);
        recyclerViewPhoto.setLayoutManager(staggeredGridLayoutManagerH);


        // Sending reference and data to Adapter
        Adapter adapter = new Adapter(MainActivity.this, images);

        // Setting Adapter to RecyclerView
        recyclerViewPhoto.setAdapter(adapter);
    }
}