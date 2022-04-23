package com.example.fitstagram;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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

import com.example.fitstagram.databinding.ActivityPostInformationBinding;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicMarkableReference;

public class PostInformation extends AppCompatActivity {
    private Object NullPointerException;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseStorage storage = FirebaseStorage.getInstance();
    ActivityPostInformationBinding binding;
    ActivityResultLauncher<String> mTakePhoto;
    Bitmap photo;
    Uri file;
    StorageReference storageRef = storage.getReference(); //created a reference
    StorageReference pictures;
    UploadTask uploadTask;
    InputStream stream;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPostInformationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        PostBackButton();
        UploadButton();
        mTakePhoto = registerForActivityResult(
                new ActivityResultContracts.GetContent(),
                new ActivityResultCallback<Uri>() {
                    @RequiresApi(api = Build.VERSION_CODES.P)
                    @Override
                    public void onActivityResult(Uri result) {
                        binding.imageView.setImageURI(result);
                        Bitmap bitmap = null;
                        ContentResolver contentResolver = getContentResolver();
                        ImageDecoder.Source source = ImageDecoder.createSource(contentResolver, result);
                        try {
                            bitmap = ImageDecoder.decodeBitmap(source);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        photo = bitmap;
                        file = result;
                    }
                });
        PostSubmitButton(photo);
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
    private void PostSubmitButton(Bitmap picture){
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
                int random_id = generateRandomId();
                int user_id = 123; //this is an example, it should be generated from user login
                pictures = storageRef.child(Integer.toString(user_id)+"/"+Integer.toString(random_id)+"/"+file.getLastPathSegment());
                if(!TextUtils.isEmpty(description.getText().toString()) || !TextUtils.isEmpty(time.getText().toString())) {
                    if(choice)
                        db.collection("feed").add(new post(user_id,random_id,description.getText().toString(), false, Integer.parseInt(time.getText().toString()), null));
                    else
                        db.collection("feed").add(new voting_post(user_id,random_id,description.getText().toString(), false, Integer.parseInt(time.getText().toString()), null));
                    uploadTask = pictures.putFile(file);
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
    private void UploadButton() {
        ImageView picture = (ImageView) findViewById(R.id.imageView2);
        ImageButton upload = (ImageButton) findViewById(R.id.upload_button);
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTakePhoto.launch("image/*");
            }
        });
    }
    public int generateRandomId(){
        return (int) (Math.random()*10000); //comment
    }
}//comment