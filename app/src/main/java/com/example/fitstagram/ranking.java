package com.example.fitstagram;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ranking extends AppCompatActivity {

    RecyclerView recyclerview;
    DatabaseReference database;
    RankingAdapter rAdapter;
    ArrayList<userrank> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking_list);

        recyclerview = findViewById(R.id.userlist);
        database = FirebaseDatabase.getInstance().getReference("users");
        recyclerview.setHasFixedSize(true);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        rAdapter = new RankingAdapter(this,list);
        recyclerview.setAdapter(rAdapter);
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    userrank user = dataSnapshot.getValue(userrank.class);
                    list.add(user);
                }

                rAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        returnBtn();
        infoBtn();
    }

    private void returnBtn() {
        Button return_button = (Button) findViewById(R.id.returnBtn);
        return_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { finish(); }
        });
    }

    private void infoBtn(){
        Button infoButton = (Button) findViewById(R.id.infoBtn);
        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ranking.this, rankingInfo.class));
            }
        });
    }


}

