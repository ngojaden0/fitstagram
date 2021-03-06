package com.example.fitstagram;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

//this is login
public class GeneralFeed extends AppCompatActivity {

    private RecyclerView mFirestoreList;
    private FirestoreRecyclerAdapter adapter;

    FirebaseFirestore db = FirebaseFirestore.getInstance(); //instantiate firestore
    FirebaseUser FBUser;
    FirebaseAuth mAuth;
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReference();

    //user currentUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        FBUser = mAuth.getCurrentUser();
        user currentUser = new user();

        if (FBUser == null)
            startActivity(new Intent(GeneralFeed.this, loginMain.class));
        else if(!user.isDatabaseConnected())
            currentUser = user.connectToDatabase(FBUser.getUid(), GeneralFeed.this);
        else{
            currentUser = user.databaseGetUser();
            Toast.makeText(GeneralFeed.this, "Logged in as " + currentUser.getUsername(), Toast.LENGTH_SHORT).show();
        }

        removeVotePosts();
        PostButton(); //post signIn
        UserProfileButton(); // Justine
        RankingButton(); // Christian
        TextView ExampleText1 = (TextView) findViewById(R.id.text1);
        TextView ExampleText2 = (TextView) findViewById(R.id.text2);
        TextView ExampleText3 = (TextView) findViewById(R.id.text3);
        ImageView ExampleImage1 = (ImageView) findViewById(R.id.post1);
        ImageView ExampleImage2 = (ImageView) findViewById(R.id.post2);
        ImageView ExampleImage3 = (ImageView) findViewById(R.id.post3);
        postToFeed(ExampleImage1, ExampleText1, 0);
        postToFeed(ExampleImage2, ExampleText2, 1);
        postToFeed(ExampleImage3, ExampleText3, 2);


        //mFirestoreList = findViewById(R.id.firestore_list);

        Query query = FirebaseFirestore.getInstance().collection("feed");
        FirestoreRecyclerOptions<postModel> option = new FirestoreRecyclerOptions.Builder<postModel>()
                .setQuery(query,postModel.class).build();
            adapter = new FirestoreRecyclerAdapter<postModel,postViewHolder>(option) {
            @NonNull
            @Override
            public postViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_single,parent,false);
                return new postViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull postViewHolder holder, int position, @NonNull postModel model) {
                holder.descriptionID.setText(model.getDescription());
                holder.user_id.setText(model.getUser_id() + "");
                holder.post_id.setText(model.getPost_id() + "");

            }
        };


        //mFirestoreList.setHasFixedSize(true);
        //mFirestoreList.setLayoutManager(new LinearLayoutManager(this));
        //mFirestoreList.setAdapter(adapter);
    }

    private class postViewHolder extends RecyclerView.ViewHolder{

        private TextView descriptionID;
        private TextView user_id;
        private TextView post_id;

        public postViewHolder(@NonNull View itemView) {
            super(itemView);
            descriptionID = itemView.findViewById(R.id.descriptionID);
            user_id = itemView.findViewById(R.id.user_id);
            post_id = itemView.findViewById(R.id.post_id);

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        removeVotePosts();
        adapter.startListening();
    }

    private void UserProfileButton() {
        Button profileButton = (Button) findViewById(R.id.user_button);
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeVotePosts();
                startActivity(new Intent(GeneralFeed.this, profile.class));
            }
        });
    }

    private void RankingButton() {
        Button rankingButton = (Button) findViewById(R.id.ranking_button);
        rankingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeVotePosts();
                startActivity(new Intent(GeneralFeed.this, ranking.class));
            }
        });
    }

    private void PostButton() {
        Button postButton = (Button) findViewById(R.id.post_button);
        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // start next activity
                removeVotePosts();
                startActivity(new Intent(GeneralFeed.this, userpost_or_voterpost.class));
            }
        });
    }
    private void VoteButton(String user_id, String post_id, ArrayList<String> list, String description) {
        Button voteButton = (Button) findViewById(R.id.vote_button);
        voteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeVotePosts();
                Intent i = new Intent(GeneralFeed.this, VotePage.class);
                i.putExtra("user",user_id);
                i.putExtra("post",post_id);
                i.putExtra("description",description);
                i.putExtra("picture_1",list.get(0));
                i.putExtra("picture_2",list.get(1));
                i.putExtra("picture_3",list.get(2));
                startActivity(i);
            }
        });
    }
    private void postToFeed(ImageView post, TextView text, int index){
        removeVotePosts();
        db.collection("feed").orderBy("post_id", Query.Direction.DESCENDING)
        .get()
        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {

                    String i,j,k;
                    ArrayList<String> list = new ArrayList<String>();
                    DocumentSnapshot single = task.getResult().getDocuments().get(index);
                    Log.d("feed","hey: "+single.get("user_id"));
                    Object user_id = single.get("user_id");
                    Object post_id = single.get("post_id");
                    Object description = single.get("description");
                    i = user_id.toString();
                    j = post_id.toString();
                    k = description.toString();
                    text.setText(i+"\n"+k); //display example post id and description


                    StorageReference listRef = storage.getReference().child(j);
                    listRef.listAll().addOnSuccessListener(new OnSuccessListener<ListResult>() {
                        @Override
                        public void onSuccess(ListResult listResult) {
                            for (StorageReference item : listResult.getItems()) {
                                // All the items under listRef.
                                Log.d("string", item.toString());
                                String sub = item.toString().substring(56);
                                // sub = image:## -> ##
                                list.add(sub);
                            }
                                storageRef.child(j+"/"+"image:"+ list.get(0)).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @RequiresApi(api = Build.VERSION_CODES.P)
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        Glide.with(getApplicationContext()).load(uri).override(350,350).into(post);
                                        Log.d("string",list.toString());
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception exception) {
                                        // Handle any errors
                                    }
                                });

                        }
                    });
                    VoteButton(i,j,list,k);
                    Log.d("vote",i+j+list.toString());
                }
            }});
    }

    private void removeVotePosts(){
        CollectionReference itemsRef = db.collection("feed");
        CollectionReference usersRef = db.collection("users");
        Query query = itemsRef.whereEqualTo("voting",true);
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                String id;
                if (task.isSuccessful()) {
                    for(DocumentSnapshot document : task.getResult()) {
                        if(System.currentTimeMillis() > Long.parseLong(document.get("final_time").toString())) {
                            //distributePoints(document.getId());
                            itemsRef.document(document.getId()).delete();
                        }
                    }
                }
            }
        });
    }
/*
    private void distributePoints(String post_id)
    {
        CollectionReference usersRef = db.collection("users");
        usersRef.get()
            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            DocumentSnapshot single_user = usersRef.document(document.getId()).collection("votes").document(post_id).get().getResult();
                            DocumentSnapshot single_post = db.collection("feed").document(post_id).get().getResult();
                            if(single_user.getString("choice") == single_post.getString("most_voted"))
                            {
                                usersRef.document(document.getId()).update("total_points",FieldValue.increment(1));
                            }
                        }
                    }
                }
            });
    }
 */
}