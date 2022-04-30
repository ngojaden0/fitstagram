package com.example.fitstagram;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fitstagram.databinding.ActivityPostInformationBinding;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.io.InputStream;

public class PostInformation extends AppCompatActivity {
    private Object NullPointerException;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseStorage storage = FirebaseStorage.getInstance();
    ActivityPostInformationBinding binding;

    ActivityResultLauncher<String> mTakePhoto1;
    ActivityResultLauncher<String> mTakePhoto2;
    ActivityResultLauncher<String> mTakePhoto3;

    Bitmap photo1;
    Bitmap photo2;
    Bitmap photo3;

    Uri file1;
    Uri file2;
    Uri file3;

    StorageReference storageRef = storage.getReference(); //created a reference
    StorageReference pictures1, pictures2, pictures3;
    UploadTask uploadTask;
    InputStream stream;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPostInformationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        PostBackButton();
        UploadButton();
        mTakePhoto1 = registerForActivityResult(
                new ActivityResultContracts.GetContent(),
                new ActivityResultCallback<Uri>() {
                    @RequiresApi(api = Build.VERSION_CODES.P)
                    @Override
                    public void onActivityResult(Uri result) {
                        binding.firstImage.setImageURI(result);
                        Bitmap bitmap = null;
                        ContentResolver contentResolver = getContentResolver();
                        ImageDecoder.Source source = ImageDecoder.createSource(contentResolver, result);
                        try {
                            bitmap = ImageDecoder.decodeBitmap(source);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        photo1 = bitmap;
                        file1 = result;
                    }
                });
        mTakePhoto2 = registerForActivityResult(
                new ActivityResultContracts.GetContent(),
                new ActivityResultCallback<Uri>() {
                    @RequiresApi(api = Build.VERSION_CODES.P)
                    @Override
                    public void onActivityResult(Uri result) {
                        binding.secondImage.setImageURI(result);
                        Bitmap bitmap = null;
                        ContentResolver contentResolver = getContentResolver();
                        ImageDecoder.Source source = ImageDecoder.createSource(contentResolver, result);
                        try {
                            bitmap = ImageDecoder.decodeBitmap(source);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        photo2 = bitmap;
                        file2 = result;
                    }
                });
        mTakePhoto3 = registerForActivityResult(
                new ActivityResultContracts.GetContent(),
                new ActivityResultCallback<Uri>() {
                    @RequiresApi(api = Build.VERSION_CODES.P)
                    @Override
                    public void onActivityResult(Uri result) {
                        binding.thirdImage.setImageURI(result);
                        Bitmap bitmap = null;
                        ContentResolver contentResolver = getContentResolver();
                        ImageDecoder.Source source = ImageDecoder.createSource(contentResolver, result);
                        try {
                            bitmap = ImageDecoder.decodeBitmap(source);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        photo3 = bitmap;
                        file3 = result;
                    }
                });
        PostSubmitButton(photo1, photo2, photo3);
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
    private void PostSubmitButton(Bitmap picture1, Bitmap picture2, Bitmap picture3){
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
                long post_id = generatePostId();
                int user_id = 789; //this is an example, it should be generated from user login

                if((!TextUtils.isEmpty(description.getText().toString()) || !TextUtils.isEmpty(time.getText().toString())) && (!(file1==null) || !(file2==null) || !(file3==null))) {
                    pictures1 = storageRef.child(Long.toString(post_id)+"/"+file1.getLastPathSegment());
                    pictures2 = storageRef.child(Long.toString(post_id)+"/"+file2.getLastPathSegment());
                    pictures3 = storageRef.child(Long.toString(post_id)+"/"+file3.getLastPathSegment());
                    DocumentReference general_feed = db.collection("general feed").document(Long.toString(post_id));
                    if(choice)
                        db.collection("feed2").document(Long.toString(post_id)).set(new post(user_id,post_id,description.getText().toString(), false, Integer.parseInt(time.getText().toString()), null));
                    else
                        db.collection("feed2").document(Long.toString(post_id)).set(new voting_post(user_id,post_id,description.getText().toString(), true, Integer.parseInt(time.getText().toString()), null));
                    uploadTask = pictures1.putFile(file1);
                    uploadTask = pictures2.putFile(file2);
                    uploadTask = pictures3.putFile(file3);
                    startActivity(intent);
                }
                else {
                    builder.setMessage("Please fill in post information");
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
            }
        });
    }
    private void UploadButton() {
        ImageView picture1 = (ImageView) findViewById(R.id.first_image);
        ImageView picture2 = (ImageView) findViewById(R.id.second_image);
        ImageView picture3 = (ImageView) findViewById(R.id.third_image);
        ImageButton upload1 = (ImageButton) findViewById(R.id.upload_button1);
        ImageButton upload2 = (ImageButton) findViewById(R.id.upload_button2);
        ImageButton upload3 = (ImageButton) findViewById(R.id.upload_button3);
        upload1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTakePhoto1.launch("image/*");
            }
        });
        upload2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTakePhoto2.launch("image/*");
            }
        });
        upload3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTakePhoto3.launch("image/*");
            }
        });
    }
    public long generatePostId(){
        return System.currentTimeMillis();
    }
}