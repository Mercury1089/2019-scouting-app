package com.mercury1089.scoutingapp2019;



import android.content.Intent;



import android.os.Bundle;



import android.support.v4.app.NavUtils;

import android.view.View;



import android.widget.Button;

import com.beardedhen.androidbootstrap.BootstrapButton;


public class Settings extends MainActivity {

    //for intent (going to Settings Activity)

    //variables to be used in the Settings Activity

    BootstrapButton fieldSideLeftButton;

    BootstrapButton   fieldSideRightButton;

    Button localStorageResetButton;

    Button saveButton;

    Button cancelButton;

    boolean isLeft = false;

    boolean isRight = true;

    boolean isLocalStorageClicked = false;

    String leftOrRight = "";









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

            localStorageResetButton.setTextColor(getResources().getColor(R.color.grey));

        }

    }



    public void saveClick (View view) {

        hasBeenSaved = true;



        //only needed if everything resets when you go back to the main activity...

        //must use for loop that separates the string using '+'

        /*

        String ScouterNameLocal = getScouterName();

        String MatchNumberLocal = "" + getMatchNumber();

        String TeamNumberLocal = "" + getTeamNumber();

        String FirstAlliancePartnerLocal = "" + getFirstAlliancePartner();

        String SecondAlliancePartnerLocal = "" + getSecondAlliancePartner();

        String NoShowStatusLocal = "" + getNoShowStatus();

        String IsBlueAllianceLocal = "" + getIsBlueAlliance();

        String IsRedAllianceLocal = "" + getIsRedAlliance();

        String IsSetupPanelLocal = "" + getIsSetupPanel();

        String IsCargoPanelLocal = "" + getIsSetupCargo();

        String StartL1Local = "" + getStartL1();

        String StartC1Local = "" + getStartC1();

        String StartR1Local = "" + getStartR1();

        String StartL2Local = "" + getStartL2();

        String StartR2Local = "" + getStartR2();



        String SendMessage = leftOrRight + ScouterNameLocal + "+" + MatchNumberLocal + TeamNumberLocal + FirstAlliancePartnerLocal + SecondAlliancePartnerLocal + NoShowStatusLocal + IsBlueAllianceLocal + IsRedAllianceLocal + IsSetupPanelLocal + IsCargoPanelLocal + StartL1Local + StartC1Local + StartR1Local + StartL2Local + StartR2Local;

        */

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

        else {

            setisResetLocalStorageClicked(false);

        }

        Intent intent = new Intent(this, MainActivity.class);

        // intent.putExtra(EXTRA_MESSAGE, SendMessage);

        startActivity(intent);

    }



    public void cancelClick (View view) {

        leftOrRight = "";

        //put the parent thing here

        NavUtils.navigateUpFromSameTask(this);

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