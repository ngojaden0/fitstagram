package com.example.fitstagram;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.fitstagram.databinding.ActivityLoginMainBinding;

public class ProfileCreation extends AppCompatActivity {

    ActivityLoginMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_creation);

        binding = ActivityLoginMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //creates I/O machines
        final Button signIn = binding.signIn;
        final Button register = binding.register;
        final EditText email = binding.emailAddress;
        final EditText password = binding.password;
    }
}