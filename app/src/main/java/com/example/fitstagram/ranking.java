package com.example.fitstagram;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ranking extends AppCompatActivity {

    RecyclerView recyclerview;
    FirebaseFirestore db;
    RankingAdapter rAdapter;
    ArrayList<user> list;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking_list);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Fetching data");
        progressDialog.show();

        recyclerview = findViewById(R.id.userlist);
        recyclerview.setHasFixedSize(true);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));

        db = FirebaseFirestore.getInstance();
        list = new ArrayList<user>();
        rAdapter = new RankingAdapter(ranking.this, list);
        recyclerview.setAdapter(rAdapter);

        EventChangeListener();

        returnBtn();
        infoBtn();
    }

    private void EventChangeListener() {

        db.collection("users").orderBy("total_points", Query.Direction.DESCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException e) {

                        if(e != null)
                        {
                            if(progressDialog.isShowing())
                            {progressDialog.dismiss();}
                            Log.e("Firestore error", e.getMessage());
                            return;
                        }

                        for (DocumentChange dc : value.getDocumentChanges()){
                            if(dc.getType() == DocumentChange.Type.ADDED){
                                list.add(dc.getDocument().toObject(user.class));
                            }
                            rAdapter.notifyDataSetChanged();
                            if(progressDialog.isShowing())
                            {progressDialog.dismiss();}
                        }
                    }
                });
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