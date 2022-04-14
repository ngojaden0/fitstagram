package com.example.fitstagram;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class Master extends post{

    public Master(int user_id, String description, boolean featured, int time) {
        super(user_id, description, featured, time);
    }

    List<post> GeneralFeed = new ArrayList<>();
}