package com.mercury1089.scoutingapp2019;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class Settings extends MainActivity {
    /* for main activity
  scouterName - populated by data entered in Scouter name field
» matchNumber - populated by data entered in Match # field
» teamNumber - populated by data entered in Team # field
» partner1 - populated by data entered in 1st alliance partner field
» partner2 - populated by data entered in 2nd alliance partner field
» noShowStatus - populated by state of no show toggle (0 if off, 1 if on)
» blueAlliance - populated by state of blue alliance color button (0 if off, 1 if on)
» redAlliance - populated by state of red alliance color button (0 if off, 1 if on)
» prepopPanel - populated by state of panel button (0 if off, 1 if on)
» prepopCargo - populated by state of cargo button (0 if off, 1 if on)
» start1L - populated by the state of the 1L square location on the diagram (0 if off, 1 if on)
» start1C - populated by the state of the 1C square location on the diagram (0 if off, 1 if on)
» start1R - populated by the state of the 1R square location on the diagram (0 if off, 1 if on)
» start2L - populated by the state of the 2L square location on the diagram (0 if off, 1 if on)
» start2R - populated by the state of the 2R square location on the diagram (0 if off, 1 if on)
     */
    Button fieldSideLeftButton;
    Button fieldSideRightButton;
    Button localStorageResetButton;
    boolean isLeft = false;
    boolean isRight = true;
    String leftOrRight = "";
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
        localStorageResetButton = findViewById(R.id.localStorageResetButton);
    }

    public void rightClick (View view) {
        if (isRight == false) {
            isRight = true;
            leftOrRight = "Right";
            fieldSideLeftButton.setBackgroundColor(getResources().getColor(R.color.light));
            fieldSideLeftButton.setTextColor(getResources().getColor(R.color.grey));
            fieldSideRightButton.setBackgroundColor(getResources().getColor(R.color.orange));
            fieldSideRightButton.setTextColor(getResources().getColor(R.color.light));
        }
        else {
            isRight = false;
            leftOrRight = "";
            fieldSideRightButton.setBackgroundColor(getResources().getColor(R.color.light));
            fieldSideRightButton.setTextColor(getResources().getColor(R.color.grey));
        }
    }

    public void leftClick (View view) {
        if (isLeft == false) {
            isLeft = true;
            leftOrRight = "Left";
            fieldSideLeftButton.setBackgroundColor(getResources().getColor(R.color.light));
            fieldSideLeftButton.setTextColor(getResources().getColor(R.color.grey));
            fieldSideRightButton.setBackgroundColor(getResources().getColor(R.color.orange));
            fieldSideRightButton.setTextColor(getResources().getColor(R.color.light));
        }
        else {
            isLeft = false;
            leftOrRight = "";
            fieldSideRightButton.setBackgroundColor(getResources().getColor(R.color.light));
            fieldSideRightButton.setTextColor(getResources().getColor(R.color.grey));
        }
    }

    public void localStorageResetClick (View view) {
        if (isLocalStorageClicked == false) {
            isLocalStorageClicked = true;
            localStorageResetButton.setBackgroundColor(getResources().getColor(R.color.orange));
            localStorageResetButton.setTextColor(getResources().getColor(R.color.light));
        }
        else {
            isLocalStorageClicked = false;
            localStorageResetButton.setBackgroundColor(getResources().getColor(R.color.light));
            localStorageResetButton.setTextColor(getResources().getColor(R.color.grey));
        }
        //setters here
    }

    public void startClick (View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(EXTRA_MESSAGE, leftOrRight);
        startActivity(intent);
    }
}
