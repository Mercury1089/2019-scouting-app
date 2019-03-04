package com.mercury1089.scoutingapp2019;



import android.content.Intent;



import android.os.Bundle;



import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;



import android.widget.Button;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapButton;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Set;

public class Settings extends AppCompatActivity {

    private BootstrapButton fieldSideLeftButton;
    private BootstrapButton fieldSideRightButton;
    private Button localStorageResetButton;
    private Button saveButton;
    private boolean isLeft;
    private boolean isRight;
    private boolean isLocalStorageClicked;
    private boolean HasBeenCleared;
    private boolean isFirstTime;
    private String leftOrRight;
    private Button CancelButton;
    private Serializable setupData;

    private HashMap<String, String> setupHashMap;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        fieldSideLeftButton = findViewById(R.id.FieldSideLeft);
        fieldSideRightButton = findViewById(R.id.FieldSideRight);
        localStorageResetButton = findViewById(R.id.LocalStorageResetButton);
        saveButton = findViewById(R.id.SaveButton);
        CancelButton = findViewById(R.id.CancelButton);

        isLeft = false;
        isRight = false;
        isLocalStorageClicked = false;
        HasBeenCleared = false;
        isFirstTime = false;
        leftOrRight = "";
        leftDefault();
        rightDefault();

        String mainLeftOrRight = "";

        setupHashMap = new HashMap<>();
        setupData = getIntent().getSerializableExtra("setupHashMap");
        if (setupData != null) {
            setupHashMap = (HashMap<String, String>) setupData;
            mainLeftOrRight = setupHashMap.get("LeftOrRight");
        }
        else if (getIntent().getStringExtra("mainLeftOrRight") != null) {
            mainLeftOrRight = getIntent().getStringExtra("mainLeftOrRight");
        }
        else {
            CancelButton.setEnabled(false);
            isFirstTime = true;
        }
        if (mainLeftOrRight.equals("Left")) {
            isLeft = true;
            leftOrRight = "Left";
            rightDefault();
            fieldSideLeftButton.setBackgroundColor(getResources().getColor(R.color.orange));
            fieldSideLeftButton.setTextColor(getResources().getColor(R.color.light));
            saveButton.setEnabled(true);
            saveButton.setBackgroundColor(getResources().getColor((R.color.saveactive)));
            saveButton.setTextColor(getResources().getColor(R.color.light));
            localStorageResetDefault();
        } else if (mainLeftOrRight.equals("Right")) {
            isRight = true;
            leftOrRight = "Right";
            fieldSideRightButton.setBackgroundColor(getResources().getColor(R.color.orange));
            fieldSideRightButton.setTextColor(getResources().getColor(R.color.light));
            leftDefault();
            localStorageResetDefault();
            saveButton.setEnabled(true);
            saveButton.setBackgroundColor(getResources().getColor((R.color.saveactive)));
            saveButton.setTextColor(getResources().getColor(R.color.light));
        }
    }
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        View decorView = getWindow().getDecorView();
        if (hasFocus) {
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);}
    }

    public void rightClick (View view) {
        if (!isRight) {
            isRight = true;
            leftOrRight = "Right";
            fieldSideRightButton.setBackgroundColor(getResources().getColor(R.color.orange));
            fieldSideRightButton.setTextColor(getResources().getColor(R.color.light));
            leftDefault();
            localStorageResetDefault();
            if (!isFirstTime)
                CancelButton.setEnabled(true);
            saveButton.setEnabled(true);
            saveButton.setBackgroundColor(getResources().getColor((R.color.saveactive)));
            saveButton.setTextColor(getResources().getColor(R.color.light));
        }
        else {
            isRight = false;
            leftOrRight = "";
            rightDefault();
            saveButton.setEnabled(false);
            CancelButton.setEnabled(false);
            saveButton.setBackgroundColor(getResources().getColor((R.color.savedefault)));
            saveButton.setTextColor(getResources().getColor(R.color.savetextdefault));
        }
    }



    public void leftClick (View view) {
        if (!isLeft) {
            isLeft = true;
            leftOrRight = "Left";
            rightDefault();
            fieldSideLeftButton.setBackgroundColor(getResources().getColor(R.color.orange));
            fieldSideLeftButton.setTextColor(getResources().getColor(R.color.light));
            saveButton.setEnabled(true);
            if (!isFirstTime)
               CancelButton.setEnabled(true);
            saveButton.setBackgroundColor(getResources().getColor((R.color.saveactive)));
            saveButton.setTextColor(getResources().getColor(R.color.light));
            localStorageResetDefault();
        } else {
            isLeft = false;
            leftOrRight = "";
            leftDefault();
            saveButton.setEnabled(false);
            CancelButton.setEnabled(false);
            saveButton.setBackgroundColor(getResources().getColor((R.color.savedefault)));
            saveButton.setTextColor(getResources().getColor(R.color.savetextdefault));
        }
    }



    public void localStorageResetClick (View view) {
        if (!isLocalStorageClicked) {
            isLocalStorageClicked = true;
            localStorageResetButton.setBackgroundColor(getResources().getColor(R.color.orange));
            localStorageResetButton.setTextColor(getResources().getColor(R.color.light));
            CancelButton.setEnabled(false);
        } else {
            localStorageResetDefault();
            if (!isFirstTime)
                CancelButton.setEnabled(true);
        }

    }

    private void localStorageResetDefault () {
        isLocalStorageClicked = false;
        localStorageResetButton.setBackgroundColor(getResources().getColor(R.color.light));
        localStorageResetButton.setTextColor(getResources().getColor(R.color.grey));
    }

    private void rightDefault () {
        fieldSideRightButton.setBackgroundColor(getResources().getColor(R.color.light));
        fieldSideRightButton.setTextColor(getResources().getColor(R.color.grey));
        isRight = false;
    }

    private void leftDefault () {
        fieldSideLeftButton.setBackgroundColor(getResources().getColor(R.color.light));
        fieldSideLeftButton.setTextColor(getResources().getColor(R.color.grey));
        isLeft = false;
    }

    public void saveClick (View view) {


        if (isLocalStorageClicked) {
            leftOrRight = "";
            leftDefault();
            rightDefault();
            localStorageResetDefault();
            saveButton.setEnabled(false);
            saveButton.setBackgroundColor(getResources().getColor((R.color.savedefault)));
            saveButton.setTextColor(getResources().getColor(R.color.savetextdefault));

            setupHashMap.clear();
            setupHashMap.put("NoShow","0");
            setupHashMap.put("AllianceColor","");
            HasBeenCleared = true;
            Toast.makeText(Settings.this, "All variables successfully reset.", Toast.LENGTH_SHORT).show();
        }
        else {
            if (isLeft)
                leftOrRight = "Left";
            else if (isRight)
                leftOrRight = "Right";

            if (setupData == null) {
                setupHashMap.put("NoShow","0");
                setupHashMap.put("AllianceColor","");
            }

            setupHashMap.put("LeftOrRight",leftOrRight);

            Intent intent = new Intent(Settings.this, MainActivity.class);
            intent.putExtra("setupHashMap", setupHashMap);
            Serializable scoreData = getIntent().getSerializableExtra("scoreHashMap");
            HashMap<String, String> scoreHashMap;
            if (scoreData != null && !HasBeenCleared) {
                scoreHashMap = (HashMap<String, String>) scoreData;
                intent.putExtra("scoreHashMap", scoreHashMap);
            }
            startActivity(intent);
        }

    }

    public void cancelClick (View view) {
        Intent intent = new Intent(Settings.this,MainActivity.class);
        intent.putExtra("LEFTORRIGHT", getIntent().getStringExtra("mainLeftOrRight"));
        intent.putExtra("setupHashMap", setupHashMap);
        startActivity(intent);
    }
}