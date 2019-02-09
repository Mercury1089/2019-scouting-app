package com.mercury1089.scoutingapp2019;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Settings extends AppCompatActivity {
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
    boolean isLocalStorageClicked = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        fieldSideLeftButton = findViewById(R.id.blueButton);
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
            fieldSideLeftButton.setBackgroundColor(getResources().getColor(R.color.light));
            fieldSideLeftButton.setTextColor(getResources().getColor(R.color.grey));
            fieldSideRightButton.setBackgroundColor(getResources().getColor(R.color.orange));
            fieldSideRightButton.setTextColor(getResources().getColor(R.color.light));
        }
        else {
            isRight = false;
            fieldSideRightButton.setBackgroundColor(getResources().getColor(R.color.light));
            fieldSideRightButton.setTextColor(getResources().getColor(R.color.grey));
        }
    }

    public void leftClick (View view) {
        if (isLeft == false) {
            isLeft = true;
            fieldSideLeftButton.setBackgroundColor(getResources().getColor(R.color.light));
            fieldSideLeftButton.setTextColor(getResources().getColor(R.color.grey));
            fieldSideRightButton.setBackgroundColor(getResources().getColor(R.color.orange));
            fieldSideRightButton.setTextColor(getResources().getColor(R.color.light));
        }
        else {
            isLeft = false;
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
    }
}
