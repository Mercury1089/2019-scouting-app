package com.mercury1089.scoutingapp2019;



import android.content.Intent;

import android.os.Bundle;

import android.view.View;

import android.widget.Button;







public class Settings extends MainActivity {

    //for intent (going to Settings Activity)

    public static final String EXTRA_MESSAGE = "com.mercury1089.scoutingapp2019.MESSAGE";



    //variables to be used in the Settings Activity

    Button fieldSideLeftButton;

    Button fieldSideRightButton;

    Button localStorageResetButton;

    Button saveButton;

    Button cancelButton;

    boolean isLeft = false;

    boolean isRight = true;

    String leftOrRight = "";







    //variables to be passed on to the MainActivity Activity

    boolean isLocalStorageClicked = false;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_settings);



        fieldSideLeftButton = findViewById(R.id.FieldSideLeft);

        fieldSideLeftButton.setBackgroundColor(getResources().getColor(R.color.light));

        fieldSideLeftButton.setTextColor(getResources().getColor(R.color.grey));

        fieldSideRightButton = findViewById(R.id.FieldSideRight);

        fieldSideRightButton.setBackgroundColor(getResources().getColor(R.color.light));

        fieldSideRightButton.setTextColor(getResources().getColor(R.color.grey));

        localStorageResetButton = findViewById(R.id.LocalStorageResetButton);

        saveButton = findViewById(R.id.SaveButton);

        cancelButton = findViewById(R.id.CancelButton);



        if (hasBeenSaved) {

            cancelButton.setEnabled(false);

        }

    }



    public void rightClick (View view) {

        if (!isRight) {

            isRight = true;

            leftOrRight = "Right";

            leftDefault();

            fieldSideRightButton.setBackgroundColor(getResources().getColor(R.color.orange));

            fieldSideRightButton.setTextColor(getResources().getColor(R.color.light));

            saveButton.setEnabled(true);

        }

        else {

            rightDefault();

            saveButton.setEnabled(false);

        }

    }



    public void leftClick (View view) {

        if (!isLeft) {

            isLeft = true;

            leftOrRight = "Left";

            fieldSideLeftButton.setBackgroundColor(getResources().getColor(R.color.light));

            fieldSideLeftButton.setTextColor(getResources().getColor(R.color.grey));

            fieldSideRightButton.setBackgroundColor(getResources().getColor(R.color.orange));

            fieldSideRightButton.setTextColor(getResources().getColor(R.color.light));

            saveButton.setEnabled(true);

        }

        else {

            isLeft = false;

            leftOrRight = "";

            leftDefault();

            saveButton.setEnabled(false);

        }

    }



    public void localStorageResetClick (View view) {

        if (!isLocalStorageClicked) {

            isLocalStorageClicked = true;

            localStorageResetButton.setBackgroundColor(getResources().getColor(R.color.orange));

            localStorageResetButton.setTextColor(getResources().getColor(R.color.light));
        }
        else {

            isLocalStorageClicked = false;

            localStorageResetButton.setBackgroundColor(getResources().getColor(R.color.light));

            localStorageResetButton.setTextColor(getResources().getColor(R.color.dark));
        }

    }



    public void saveClick (View view) {

        hasBeenSaved = true;

        if (isLocalStorageClicked) {

            setScouterName("");

            setMatchNumber(0);

            setTeamNumber(0);

            setFirstAlliancePartner(0);

            setSecondAlliancePartner(0);

            setNoShowStatus(0);

            setIsBlueAlliance(0);

            setIsRedAlliance(0);

            setIsSetupPanel(0);

            setIsSetupCargo(0);

            setisResetLocalStorageClicked(false);

            setStartL1(0);

            setStartC1(0);

            setStartR1(0);

            setStartL2(0);

            setStartR2(0);
        }

        Intent intent = new Intent(this, MainActivity.class);

        intent.putExtra(EXTRA_MESSAGE, leftOrRight);

        startActivity(intent);

    }





    public void cancelClick (View view) {

        leftOrRight = "";

        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);

    }



    public void rightDefault () {

        fieldSideRightButton.setBackgroundColor(getResources().getColor(R.color.light));

        fieldSideRightButton.setTextColor(getResources().getColor(R.color.grey));

        isRight = false;

        leftOrRight = "";

    }



    public void leftDefault () {

        fieldSideLeftButton.setBackgroundColor(getResources().getColor(R.color.light));

        fieldSideLeftButton.setTextColor(getResources().getColor(R.color.grey));

        isLeft = false;

        leftOrRight = "";

    }

}