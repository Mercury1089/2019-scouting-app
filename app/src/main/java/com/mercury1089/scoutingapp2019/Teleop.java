package com.mercury1089.scoutingapp2019;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.Serializable;
import java.util.HashMap;

public class Teleop extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teleop);
        Serializable data = getIntent().getSerializableExtra("map");
        HashMap<String,String> teleopData = new HashMap<>();
        teleopData = (HashMap<String, String>)data;
        String hi;
    }
}
