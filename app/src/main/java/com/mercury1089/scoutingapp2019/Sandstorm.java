package com.mercury1089.scoutingapp2019;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v4.os.ParcelableCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapButton;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import at.markushi.ui.CircleButton;

public class Sandstorm extends AppCompatActivity {
    //LEFT ROCKET
    //panel variables
    CircleButton LeftRocketPanelNearT3;
    CircleButton LeftRocketPanelNearT2;
    CircleButton LeftRocketPanelNearT1;
    CircleButton LeftRocketPanelFarT3;
    CircleButton LeftRocketPanelFarT2;
    CircleButton LeftRocketPanelFarT1;

    //cargo variables
    CircleButton LeftRocketCargoT3;
    CircleButton LeftRocketCargoT2;
    CircleButton LeftRocketCargoT1;


    //CARGO SHIP
    //panel variables
    CircleButton CargoShipPanelFront1;
    CircleButton CargoShipPanelFront2;
    CircleButton CargoShipPanelLeft1;
    CircleButton CargoShipPanelLeft2;
    CircleButton CargoShipPanelLeft3;
    CircleButton CargoShipPanelRight1;
    CircleButton CargoShipPanelRight2;
    CircleButton CargoShipPanelRight3;

    //cargo variables
    CircleButton CargoShipCargoFront1;
    CircleButton CargoShipCargoFront2;
    CircleButton CargoShipCargoLeft1;
    CircleButton CargoShipCargoLeft2;
    CircleButton CargoShipCargoLeft3;
    CircleButton CargoShipCargoRight1;
    CircleButton CargoShipCargoRight2;
    CircleButton CargoShipCargoRight3;

    //RIGHT ROCKET
    //panel variables
    CircleButton RightRocketPanelNearT3;
    CircleButton RightRocketPanelNearT2;
    CircleButton RightRocketPanelNearT1;
    CircleButton RightRocketPanelFarT3;
    CircleButton RightRocketPanelFarT2;
    CircleButton RightRocketPanelFarT1;

    //cargo variables
    CircleButton RightRocketCargoT3;
    CircleButton RightRocketCargoT2;
    CircleButton RightRocketCargoT1;

    //Repeated Variables for each buttonClick:
    private final String MODE = "Sandstorm";
    private String location = "";
    private String side = ""; //left, right, or front
    private String nearOrFar = "";
    private String tier = "";

    //displayed counters
    private int totalPanels = 0;
    private int totalCargo = 0;
    private int droppedPanels = 0;
    private int droppedCargo = 0;
    private int missedCargo = 0;
    private int missedPanels = 0;

    //left rocket counters
    private int LRPNT3Counter = 0;
    private int LRPNT2Counter = 0;
    private int LRPNT1Counter = 0;
    private int LRCT3Counter = 0;
    private int LRCT2Counter = 0;
    private int LRCT1Counter = 0;
    private int LRPFT3Counter = 0;
    private int LRPFT2Counter = 0;
    private int LRPFT1Counter = 0;

    //cargo ship counters
    private int CSPF1Counter = 0;
    private int CSPF2Counter = 0;
    private int CSCF1Counter = 0;
    private int CSCF2Counter = 0;
    private int CSPL1Counter = 0;
    private int CSPL2Counter = 0;
    private int CSPL3Counter = 0;
    private int CSCL1Counter = 0;
    private int CSCL2Counter = 0;
    private int CSCL3Counter = 0;
    private int CSCR1Counter = 0;
    private int CSCR2Counter = 0;
    private int CSCR3Counter = 0;
    private int CSPR1Counter = 0;
    private int CSPR2Counter = 0;
    private int CSPR3Counter = 0;

    //right rocket counters
    private int RRPNT1Counter = 0;
    private int RRPNT2Counter = 0;
    private int RRPNT3Counter = 0;
    private int RRCT1Counter = 0;
    private int RRCT2Counter = 0;
    private int RRCT3Counter = 0;
    private int RRPFT1Counter = 0;
    private int RRPFT2Counter = 0;
    private int RRPFT3Counter = 0;

    //left rocket text
    private TextView LRPNT1;
    private TextView LRPNT2;
    private TextView LRPNT3;
    private TextView LRCT1;
    private TextView LRCT2;
    private TextView LRCT3;
    private TextView LRPFT1;
    private TextView LRPFT2;
    private TextView LRPFT3;

    //cargo ship text
    private TextView CSPF1;
    private TextView CSPF2;
    private TextView CSCF1;
    private TextView CSCF2;
    private TextView CSPL1;
    private TextView CSPL2;
    private TextView CSPL3;
    private TextView CSCL1;
    private TextView CSCL2;
    private TextView CSCL3;
    private TextView CSCR1;
    private TextView CSCR2;
    private TextView CSCR3;
    private TextView CSPR1;
    private TextView CSPR2;
    private TextView CSPR3;

    //right rocket text
    private TextView RRPNT1;
    private TextView RRPNT2;
    private TextView RRPNT3;
    private TextView RRCT1;
    private TextView RRCT2;
    private TextView RRCT3;
    private TextView RRPFT1;
    private TextView RRPFT2;
    private TextView RRPFT3;

    private HashMap<String, String> recievedHashMap;




    //counter TextViews
    TextView PanelCounterText;
    TextView CargoCounterText;
    TextView DroppedCounterText;
    TextView MissedCounterText;

    //bootstrap buttons
    BootstrapButton SetupButton;
    BootstrapButton SandstormButton;
    BootstrapButton TeleopButton;
    BootstrapButton ClimbButton;
    BootstrapButton PanelButton;
    BootstrapButton CargoButton;
    BootstrapButton DroppedButton;
    BootstrapButton MissedButton;

    //array counter
    private int arrCounter = 0; //multiples of 7 outputted.... 2D array --> int arr[][] = new int[arrCounter][7];

    //other variables
    private int crossedHABLine = 0; //0 or 1
    private int deadRobot = 0; //0 or 1
    private boolean isCargo = false;
    private boolean isPanel = false;
    private String message = "";
    Random random;
    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sandstorm);

        //initializers
        LeftRocketPanelNearT3 = findViewById(R.id.LeftRocketPanelNearT3);
        LeftRocketPanelNearT2 = findViewById(R.id.LeftRocketPanelNearT2);
        LeftRocketPanelNearT1 = findViewById(R.id.LeftRocketPanelNearT1);
        LeftRocketPanelFarT3 = findViewById(R.id.LeftRocketPanelFarT3);
        LeftRocketPanelFarT2 = findViewById(R.id.LeftRocketPanelFarT2);
        LeftRocketPanelFarT1 = findViewById(R.id.LeftRocketPanelFarT1);
        LeftRocketCargoT3 = findViewById(R.id.LeftRocketCargoT3);
        LeftRocketCargoT2 = findViewById(R.id.LeftRocketCargoT2);
        LeftRocketCargoT1 = findViewById(R.id.LeftRocketCargoT1);
        CargoShipPanelFront1 = findViewById(R.id.CargoShipPanelFront1);
        CargoShipPanelFront2 = findViewById(R.id.CargoShipPanelFront2);
        CargoShipPanelLeft1 = findViewById(R.id.CargoShipPanelLeft1);
        CargoShipPanelLeft2 = findViewById(R.id.CargoShipPanelLeft2);
        CargoShipPanelLeft3 = findViewById(R.id.CargoShipPanelLeft3);
        CargoShipPanelRight1 = findViewById(R.id.CargoShipPanelRight1);
        CargoShipPanelRight2 = findViewById(R.id.CargoShipPanelRight2);
        CargoShipPanelRight3 = findViewById(R.id.CargoShipPanelRight3);
        CargoShipCargoFront1 = findViewById(R.id.CargoShipCargoFront1);
        CargoShipCargoFront2 = findViewById(R.id.CargoShipCargoFront2);
        CargoShipCargoLeft1 = findViewById(R.id.CargoShipCargoLeft1);
        CargoShipCargoLeft2 = findViewById(R.id.CargoShipCargoLeft2);
        CargoShipCargoLeft3 = findViewById(R.id.CargoShipCargoLeft3);
        CargoShipCargoRight1 = findViewById(R.id.CargoShipCargoRight1);
        CargoShipCargoRight2 = findViewById(R.id.CargoShipCargoRight2);
        CargoShipCargoRight3 = findViewById(R.id.CargoShipCargoRight3);
        RightRocketPanelNearT3 = findViewById(R.id.RightRocketPanelNearT3);
        RightRocketPanelNearT2 = findViewById(R.id.RightRocketPanelNearT2);
        RightRocketPanelNearT1 = findViewById(R.id.RightRocketPanelNearT1);
        RightRocketPanelFarT3 = findViewById(R.id.RightRocketPanelFarT3);
        RightRocketPanelFarT2 = findViewById(R.id.RightRocketPanelFarT2);
        RightRocketPanelFarT1 = findViewById(R.id.RightRocketPanelFarT1);
        RightRocketCargoT3 = findViewById(R.id.RightRocketCargoT3);
        RightRocketCargoT2 = findViewById(R.id.RightRocketCargoT2);
        RightRocketCargoT1 = findViewById(R.id.RightRocketCargoT1);
        LRPNT1 = findViewById(R.id.LRPNT1);
        LRPNT2 = findViewById(R.id.LRPNT2);
        LRPNT3 = findViewById(R.id.LRPNT3);
        LRCT1 = findViewById(R.id.LRCT1);
        LRCT2 = findViewById(R.id.LRCT2);
        LRCT3 = findViewById(R.id.LRCT3);
        LRPFT1 = findViewById(R.id.LRPFT1);
        LRPFT2 = findViewById(R.id.LRPFT2);
        LRPFT3 = findViewById(R.id.LRPFT3);
        CSPF1 = findViewById(R.id.CSPF1);
        CSPF2 = findViewById(R.id.CSPF2);
        CSCF1 = findViewById(R.id.CSCF1);
        CSCF2 = findViewById(R.id.CSCF2);
        CSPL1 = findViewById(R.id.CSPL1);
        CSPL2 = findViewById(R.id.CSPL2);
        CSPL3 = findViewById(R.id.CSPL3);
        CSCL1 = findViewById(R.id.CSCL1);
        CSCL2 = findViewById(R.id.CSCL2);
        CSCL3 = findViewById(R.id.CSCL3);
        CSCR1 = findViewById(R.id.CSCR1);
        CSCR2 = findViewById(R.id.CSCR2);
        CSCR3 = findViewById(R.id.CSCR3);
        CSPR1 = findViewById(R.id.CSPR1);
        CSPR2 = findViewById(R.id.CSPR2);
        CSPR3 = findViewById(R.id.CSPR3);
        RRPNT1 = findViewById(R.id.RRPNT1);
        RRPNT2 = findViewById(R.id.RRPNT2);
        RRPNT3 = findViewById(R.id.RRPNT3);
        RRCT1 = findViewById(R.id.RRCT1);
        RRCT2 = findViewById(R.id.RRCT2);
        RRCT3 = findViewById(R.id.RRCT3);
        RRPFT1 = findViewById(R.id.RRPFT1);
        RRPFT2 = findViewById(R.id.RRPFT2);
        RRPFT3 = findViewById(R.id.RRPFT3);
        PanelCounterText = findViewById(R.id.PanelCounterText);
        CargoCounterText = findViewById(R.id.CargoCounterText);
        DroppedCounterText = findViewById(R.id.DroppedCounterText);
        MissedCounterText = findViewById(R.id.MissedCounterText);
        SetupButton = findViewById(R.id.SetupButton);
        SandstormButton = findViewById(R.id.SandstormButton);
        TeleopButton = findViewById(R.id.TeleopButton);
        ClimbButton = findViewById(R.id.ClimbButton);
        PanelButton = findViewById(R.id.SPanelButton);
        CargoButton = findViewById(R.id.SCargoButton);
        DroppedButton = findViewById(R.id.DroppedButton);
        MissedButton = findViewById(R.id.MissedButton);
        recievedHashMap = new HashMap<String, String>();
        timer = new Timer();

        //make Sandstorm button look active
        selectedButtonColors(SandstormButton);

        //make other buttons look default
        defaultButtonState(SetupButton);
        defaultButtonState(TeleopButton);
        defaultButtonState(ClimbButton);
        defaultButtonState(PanelButton);
        defaultButtonState(CargoButton);

        //setting counters to default
        PanelCounterText.setText("0");
        CargoCounterText.setText("0");
        DroppedCounterText.setText("0");
        MissedCounterText.setText("0");

        //disable scoring diagram
        disableScoringDiagram('A');

        //make and initialize hashtable
        HashMap<String, String> hashMap = new HashMap<>();

        //left rocket
        hashMap.put("LRPNT3","Sandstorm,Rocket,Panel,Left,Near,T3");
        hashMap.put("LRCT3","Sandstorm,Rocket,Cargo,Left,,T3");
        hashMap.put("LRPFT3","Sandstorm,Rocket,Panel,Left,Far,T3");

        hashMap.put("LRPNT2","Sandstorm,Rocket,Panel,Left,Near,T2");
        hashMap.put("LRCT2","Sandstorm,Rocket,Cargo,Left,,T2");
        hashMap.put("LRPFT2","Sandstorm,Rocket,Panel,Left,Far,T2");

        hashMap.put("LRPNT1","Sandstorm,Rocket,Panel,Left,Near,T1");
        hashMap.put("LRCT1","Sandstorm,Rocket,Cargo,Left,,T1");
        hashMap.put("LRPFT1","Sandstorm,Rocket,Panel,Left,Far,T1");

        //cargoship
        hashMap.put("PF","Sandstorm,Cargoship,Panel,Front,,,");
        hashMap.put("CF","Sandstorm,Cargoship,Cargo,Front,,,");

        hashMap.put("PL","Sandstorm,Cargoship,Panel,Left,,,");
        hashMap.put("CL","Sandstorm,Cargoship,Cargo,Left,,,");
        hashMap.put("PR","Sandstorm,Cargoship,Panel,Right,,,");
        hashMap.put("CR","Sandstorm,Cargoship,Cargo,Right,,,");

        //right rocket
        hashMap.put("RRPNT3","Sandstorm,Rocket,Panel,Right,Near,T3");
        hashMap.put("RRCT3","Sandstorm,Rocket,Cargo,Right,,T3");
        hashMap.put("RRPFT3","Sandstorm,Rocket,Panel,Right,Far,T3");

        hashMap.put("RRPNT2","Sandstorm,Rocket,Panel,Right,Near,T2");
        hashMap.put("RRCT2","Sandstorm,Rocket,Cargo,Right,,T2");
        hashMap.put("RRPFT2","Sandstorm,Rocket,Panel,Right,Far,T2");

        hashMap.put("RRPNT1","Sandstorm,Rocket,Panel,Right,Near,T1");
        hashMap.put("RRCT1","Sandstorm,Rocket,Cargo,Right,,T1");
        hashMap.put("RRPFT1","Sandstorm,Rocket,Panel,Right,Far,T1");

        Serializable setupData = getIntent().getSerializableExtra("setupHashMap");
        recievedHashMap = (HashMap<String, String>)setupData;

        TimerTask displayCountDownMessage = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(Sandstorm.this, "Teleop is starting in 3 seconds", Toast.LENGTH_LONG).show();
                    }
                });
            }
        };
        TimerTask switchToTeleop = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(Sandstorm.this, Teleop.class);
                        startActivity(intent);
                    }
                });
            }
        };
        timer.schedule(displayCountDownMessage, 12000);
        timer.schedule(switchToTeleop, 15000);
         if (recievedHashMap.get("StartingGameObject").charAt(0) == 'P') {
            selectedButtonColors(PanelButton);
            PanelCounterText.setText("1");
            CargoButton.setEnabled(false);
            totalPanels++;
            //enable panel circles
             enableScoringDiagram('P');
         } else if (recievedHashMap.get("StartingGameObject").charAt(0) == 'C'){
             selectedButtonColors(CargoButton);
             CargoCounterText.setText("1");
             PanelButton.setEnabled(false);
             totalCargo++;
             //enable cargo circles
             enableScoringDiagram('C');
         }

         PanelCounterText.bringToFront();
         CargoCounterText.bringToFront();
         DroppedCounterText.bringToFront();
         MissedCounterText.bringToFront();

        //setting buttons to default state
        defaultButtonState(SetupButton);
        selectedButtonColors(SandstormButton);
        defaultButtonState(TeleopButton);
        defaultButtonState(ClimbButton);

        final Switch HABLineSwitch = findViewById(R.id.CrossedHABLineSwitch);
        HABLineSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    crossedHABLine = 1;
                else
                    crossedHABLine = 0;
            }
        });

        final Switch FellOverSwitch = findViewById(R.id.FellOverSwitch);
        FellOverSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                TextView possessionTitle = findViewById(R.id.IDPossession);
                TextView panelOrCargoDirections = findViewById(R.id.IDPanelOrCargoDirections);
                TextView droppedDirection = findViewById(R.id.IDDroppedDirections);

                TextView scoringTitle = findViewById(R.id.IDScoring);
                TextView pOrCDirections = findViewById(R.id.IDScoringPOrCDirections);
                TextView missedDirections = findViewById(R.id.IDScoringMissedDirections);
                if (isChecked) {
                    deadRobot = 1;
                    PanelButton.setEnabled(false);
                    CargoButton.setEnabled(false);
                    setTextToColor(possessionTitle, "grey");
                    setTextToColor(panelOrCargoDirections, "grey");
                    setTextToColor(droppedDirection, "grey");
                    setTextToColor(scoringTitle, "grey");
                    setTextToColor(pOrCDirections, "grey");
                    setTextToColor(missedDirections, "grey");
                } else {
                    deadRobot = 0;
                    PanelButton.setEnabled(true);
                    CargoButton.setEnabled(true);
                    setTextToColor(possessionTitle, "white");
                    setTextToColor(panelOrCargoDirections, "white");
                    setTextToColor(droppedDirection, "white");
                    setTextToColor(scoringTitle, "white");
                    setTextToColor(pOrCDirections, "white");
                    setTextToColor(missedDirections, "white");
                }

            }
        });
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

    //call methods
    private void updateCounterDisplay (int panels, int cargo, TextView textView) {
        int total = panels + cargo;
        textView.setText(String.valueOf(total));
    }
    private void defaultButtonState (BootstrapButton button) {
        button.setBackgroundColor(getResources().getColor(R.color.light));
        button.setTextColor(getResources().getColor(R.color.grey));
    }
    private void selectedButtonColors(BootstrapButton button) {
        button.setBackgroundColor(getResources().getColor(R.color.orange));
        button.setTextColor(getResources().getColor(R.color.light));
    }
    private void setTextToColor (TextView textView, String color) {
        if (color.equals("grey"))
            textView.setTextColor(getResources().getColor(R.color.grey));
        else
            textView.setTextColor(getResources().getColor(R.color.light));
    }

    /*private HashMap setHashtableValues () {
        HashMap<String, String> hashMap = new HashMap<>();

        //left rocket
        hashMap.put("LRPNT3","Sandstorm,Rocket,Panel,,Near,T3");
        hashMap.put("LRCT3","Sandstorm,Rocket,Cargo,,,T3");
        hashMap.put("LRPFT3","Sandstorm,Rocket,Panel,,Far,T3");

        hashMap.put("LRPNT2","Sandstorm,Rocket,Panel,,Near,T2");
        hashMap.put("LRCT2","Sandstorm,Rocket,Cargo,,,T2");
        hashMap.put("LRPFT2","Sandstorm,Rocket,Panel,,Far,T2");

        hashMap.put("LRPNT1","Sandstorm,Rocket,Panel,,Near,T1");
        hashMap.put("LRCT1","Sandstorm,Rocket,Cargo,,,T1");
        hashMap.put("LRPFT1","Sandstorm,Rocket,Panel,,Far,T1");

        //cargoship
        hashMap.put("PF","Sandstorm,Cargoship,Panel,Front,,,");
        hashMap.put("CF","Sandstorm,Cargoship,Cargo,Front,,,");

        hashMap.put("PL","Sandstorm,Cargoship,Panel,Left,,,");
        hashMap.put("CL","Sandstorm,Cargoship,Cargo,Left,,,");
        hashMap.put("PR","Sandstorm,Cargoship,Panel,Right,,,");
        hashMap.put("CR","Sandstorm,Cargoship,Cargo,Right,,,");

        //right rocket
        hashMap.put("RRPNT3","Sandstorm,Rocket,Panel,,Near,T3");
        hashMap.put("RRCT3","Sandstorm,Rocket,Cargo,,,T3");
        hashMap.put("RRPFT3","Sandstorm,Rocket,Panel,,Far,T3");

        hashMap.put("RRPNT2","Sandstorm,Rocket,Panel,,Near,T2");
        hashMap.put("RRCT2","Sandstorm,Rocket,Cargo,,,T2");
        hashMap.put("RRPFT2","Sandstorm,Rocket,Panel,,Far,T2");

        hashMap.put("RRPNT1","Sandstorm,Rocket,Panel,,Near,T1");
        hashMap.put("RRCT1","Sandstorm,Rocket,Cargo,,,T1");
        hashMap.put("RRPFT1","Sandstorm,Rocket,Panel,,Far,T1");
        return hashMap;
    }*/


    private void disableScoringDiagram (char c) {
        switch (c) {
            case 'A':
            case 'P':
                LeftRocketPanelNearT3.setEnabled(false);
                LeftRocketPanelNearT2.setEnabled(false);
                LeftRocketPanelNearT1.setEnabled(false);
                LeftRocketPanelFarT3.setEnabled(false);
                LeftRocketPanelFarT2.setEnabled(false);
                LeftRocketPanelFarT1.setEnabled(false);

                CargoShipPanelFront1.setEnabled(false);
                CargoShipPanelFront2.setEnabled(false);
                CargoShipPanelLeft1.setEnabled(false);
                CargoShipPanelLeft2.setEnabled(false);
                CargoShipPanelLeft3.setEnabled(false);
                CargoShipPanelRight1.setEnabled(false);
                CargoShipPanelRight2.setEnabled(false);
                CargoShipPanelRight3.setEnabled(false);

                RightRocketPanelNearT3.setEnabled(false);
                RightRocketPanelNearT2.setEnabled(false);
                RightRocketPanelNearT1.setEnabled(false);
                RightRocketPanelFarT3.setEnabled(false);
                RightRocketPanelFarT2.setEnabled(false);
                RightRocketPanelFarT1.setEnabled(false);

                if (LRPNT3Counter > 0) {
                    LeftRocketPanelNearT3.setColor(Color.rgb(248, 231, 28));
                    LRPNT3.setTextColor(getResources().getColor(R.color.textdefault));
                }
                else {
                    LeftRocketPanelNearT3.setColor(Color.rgb(30, 30, 30));
                    LRPNT3.setTextColor(getResources().getColor(R.color.light));
                }

                if (LRPNT2Counter > 0) {
                    LeftRocketPanelNearT2.setColor(Color.rgb(248, 231, 28));
                    LRPNT2.setTextColor(getResources().getColor(R.color.textdefault));
                }
                else {
                    LeftRocketPanelNearT2.setColor(Color.rgb(30, 30, 30));
                    LRPNT2.setTextColor(getResources().getColor(R.color.light));
                }

                if (LRPNT1Counter > 0) {
                    LeftRocketPanelNearT1.setColor(Color.rgb(248, 231, 28));
                    LRPNT1.setTextColor(getResources().getColor(R.color.textdefault));
                }
                else {
                    LeftRocketPanelNearT1.setColor(Color.rgb(30, 30, 30));
                    LRPNT1.setTextColor(getResources().getColor(R.color.light));
                }

                if (LRPFT3Counter > 0) {
                    LeftRocketPanelFarT3.setColor(Color.rgb(248, 231, 28));
                    LRPFT3.setTextColor(getResources().getColor(R.color.textdefault));
                }
                else {
                    LeftRocketPanelFarT3.setColor(Color.rgb(30, 30, 30));
                    LRPFT3.setTextColor(getResources().getColor(R.color.light));
                }
                if (LRPFT2Counter > 0) {
                    LeftRocketPanelFarT2.setColor(Color.rgb(248, 231, 28));
                    LRPFT2.setTextColor(getResources().getColor(R.color.textdefault));
                }
                else {
                    LeftRocketPanelFarT2.setColor(Color.rgb(30, 30, 30));
                    LRPFT2.setTextColor(getResources().getColor(R.color.light));
                }
                if (LRPFT1Counter > 0) {
                    LeftRocketPanelFarT1.setColor(Color.rgb(248, 231, 28));
                    LRPFT1.setTextColor(getResources().getColor(R.color.textdefault));
                }
                else {
                    LeftRocketPanelFarT1.setColor(Color.rgb(30, 30, 30));
                    LRPFT1.setTextColor(getResources().getColor(R.color.light));
                }
                if (CSPF1Counter > 0) {
                    CargoShipPanelFront1.setColor(Color.rgb(248, 231, 28));
                    CSPF1.setTextColor(getResources().getColor(R.color.textdefault));
                }
                else {
                    CargoShipPanelFront1.setColor(Color.rgb(30, 30, 30));
                    CSPF1.setTextColor(getResources().getColor(R.color.light));
                }

                if (CSPF2Counter > 0) {
                    CargoShipPanelFront2.setColor(Color.rgb(248, 231, 28));
                    CSPF2.setTextColor(getResources().getColor(R.color.textdefault));
                }
                else {
                    CargoShipPanelFront2.setColor(Color.rgb(30, 30, 30));
                    CSPF2.setTextColor(getResources().getColor(R.color.light));
                }
                if (CSPL1Counter > 0) {
                    CargoShipPanelLeft1.setColor(Color.rgb(248, 231, 28));
                    CSPL1.setTextColor(getResources().getColor(R.color.textdefault));
                }
                else {
                    CargoShipPanelLeft1.setColor(Color.rgb(30, 30, 30));
                    CSPL1.setTextColor(getResources().getColor(R.color.light));
                }
                if (CSPL2Counter > 0) {
                    CargoShipPanelLeft2.setColor(Color.rgb(248, 231, 28));
                    CSPL2.setTextColor(getResources().getColor(R.color.textdefault));
                }
                else {
                    CargoShipPanelLeft2.setColor(Color.rgb(30, 30, 30));
                    CSPL2.setTextColor(getResources().getColor(R.color.light));
                }
                if (CSPL3Counter > 0) {
                    CargoShipPanelLeft3.setColor(Color.rgb(248, 231, 28));
                    CSPL3.setTextColor(getResources().getColor(R.color.textdefault));
                }
                else {
                    CargoShipPanelLeft3.setColor(Color.rgb(30, 30, 30));
                    CSPL3.setTextColor(getResources().getColor(R.color.textdefault));
                }
                if (CSPR1Counter > 0) {
                    CargoShipPanelRight1.setColor(Color.rgb(248, 231, 28));
                    CSPR1.setTextColor(getResources().getColor(R.color.textdefault));
                }
                else {
                    CargoShipPanelRight1.setColor(Color.rgb(30, 30, 30));
                    CSPR1.setTextColor(getResources().getColor(R.color.light));
                }
                if (CSPR2Counter > 0) {
                    CargoShipPanelRight2.setColor(Color.rgb(248, 231, 28));
                    CSPR2.setTextColor(getResources().getColor(R.color.textdefault));
                }
                else {
                    CargoShipPanelRight2.setColor(Color.rgb(30, 30, 30));
                    CSPR2.setTextColor(getResources().getColor(R.color.light));
                }
                if (CSPR3Counter > 0) {
                    CargoShipPanelRight3.setColor(Color.rgb(248, 231, 28));
                    CSPR3.setTextColor(getResources().getColor(R.color.textdefault));
                }
                else {
                    CargoShipPanelRight3.setColor(Color.rgb(30, 30, 30));
                    CSPR3.setTextColor(getResources().getColor(R.color.light));
                }
                if (RRPNT3Counter > 0) {
                    RightRocketPanelNearT3.setColor(Color.rgb(248, 231, 28));
                    RRPNT3.setTextColor(getResources().getColor(R.color.textdefault));
                }
                else {
                    RightRocketPanelNearT3.setColor(Color.rgb(30, 30, 30));
                    RRPNT3.setTextColor(getResources().getColor(R.color.light));
                }
                if (RRPNT2Counter > 0) {
                    RightRocketPanelNearT2.setColor(Color.rgb(248, 231, 28));
                    RRPNT2.setTextColor(getResources().getColor(R.color.textdefault));
                }
                else {
                    RightRocketPanelNearT2.setColor(Color.rgb(30, 30, 30));
                    RRPNT2.setTextColor(getResources().getColor(R.color.light));
                }
                if (RRPNT1Counter > 0) {
                    RightRocketPanelNearT1.setColor(Color.rgb(248, 231, 28));
                    RRPNT1.setTextColor(getResources().getColor(R.color.textdefault));
                }
                else {
                    RightRocketPanelNearT1.setColor(Color.rgb(30, 30, 30));
                    RRPNT1.setTextColor(getResources().getColor(R.color.light));
                }
                if (RRPFT3Counter > 0) {
                    RightRocketPanelFarT3.setColor(Color.rgb(248, 231, 28));
                    RRPFT3.setTextColor(getResources().getColor(R.color.textdefault));
                }
                else {
                    RightRocketPanelFarT3.setColor(Color.rgb(30, 30, 30));
                    RRPFT3.setTextColor(getResources().getColor(R.color.light));
                }
                if (RRPFT2Counter > 0) {
                    RightRocketPanelFarT2.setColor(Color.rgb(248, 231, 28));
                    RRPFT2.setTextColor(getResources().getColor(R.color.textdefault));
                }
                else {
                    RightRocketPanelFarT2.setColor(Color.rgb(30, 30, 30));
                    RRPFT2.setTextColor(getResources().getColor(R.color.light));
                }
                if (RRPFT1Counter > 0) {
                    RightRocketPanelFarT1.setColor(Color.rgb(248, 231, 28));
                    RRPFT1.setTextColor(getResources().getColor(R.color.textdefault));
                }
                else {
                    RightRocketPanelFarT1.setColor(Color.rgb(30, 30, 30));
                    RRPFT1.setTextColor(getResources().getColor(R.color.light));
                }
                if (c == 'P')
                    break;
            case 'C':
                LeftRocketCargoT3.setEnabled(false);
                LeftRocketCargoT2.setEnabled(false);
                LeftRocketCargoT1.setEnabled(false);

                CargoShipCargoFront1.setEnabled(false);
                CargoShipCargoFront2.setEnabled(false);
                CargoShipCargoLeft1.setEnabled(false);
                CargoShipCargoLeft2.setEnabled(false);
                CargoShipCargoLeft3.setEnabled(false);
                CargoShipCargoRight1.setEnabled(false);
                CargoShipCargoRight2.setEnabled(false);
                CargoShipCargoRight3.setEnabled(false);

                RightRocketCargoT3.setEnabled(false);
                RightRocketCargoT2.setEnabled(false);
                RightRocketCargoT1.setEnabled(false);

                if (LRCT3Counter > 0) {
                    LeftRocketCargoT3.setColor(Color.rgb(45, 192, 103));
                    LRCT3.setTextColor(getResources().getColor(R.color.light));
                }
                else {
                    LeftRocketCargoT3.setColor(Color.rgb(30, 30, 30));
                    LRCT3.setTextColor(getResources().getColor(R.color.light));
                }

                if (LRCT2Counter > 0) {
                    LeftRocketCargoT2.setColor(Color.rgb(45, 192, 103));
                    LRCT2.setTextColor(getResources().getColor(R.color.light));
                }
                else {
                    LeftRocketCargoT2.setColor(Color.rgb(30, 30, 30));
                    LRCT2.setTextColor(getResources().getColor(R.color.light));
                }

                if (LRCT1Counter > 0) {
                    LeftRocketCargoT1.setColor(Color.rgb(45, 192, 103));
                    LRCT1.setTextColor(getResources().getColor(R.color.light));
                }
                else {
                    LeftRocketCargoT1.setColor(Color.rgb(30, 30, 30));
                    LRCT1.setTextColor(getResources().getColor(R.color.light));
                }

                if (CSCF1Counter > 0) {
                    CargoShipCargoFront1.setColor(Color.rgb(45, 192, 103));
                    CSCF1.setTextColor(getResources().getColor(R.color.light));
                }
                else {
                    CargoShipCargoFront1.setColor(Color.rgb(30, 30, 30));
                    CSCF1.setTextColor(getResources().getColor(R.color.light));
                }
                if (CSCF2Counter > 0) {
                    CargoShipCargoFront2.setColor(Color.rgb(45, 192, 103));
                    CSCF2.setTextColor(getResources().getColor(R.color.light));
                }
                else {
                    CargoShipCargoFront2.setColor(Color.rgb(30, 30, 30));
                    CSCF2.setTextColor(getResources().getColor(R.color.light));
                }
                if (CSCL1Counter > 0) {
                    CargoShipCargoLeft1.setColor(Color.rgb(45, 192, 103));
                    CSCL1.setTextColor(getResources().getColor(R.color.light));
                }
                else {
                    CargoShipCargoLeft1.setColor(Color.rgb(30, 30, 30));
                    CSCL1.setTextColor(getResources().getColor(R.color.light));
                }
                if (CSCL2Counter > 0) {
                    CargoShipCargoLeft2.setColor(Color.rgb(45, 192, 103));
                    CSCL2.setTextColor(getResources().getColor(R.color.light));
                }
                else {
                    CargoShipCargoLeft2.setColor(Color.rgb(30, 30, 30));
                    CSCL2.setTextColor(getResources().getColor(R.color.light));
                }
                if (CSCL3Counter > 0) {
                    CargoShipCargoLeft3.setColor(Color.rgb(45, 192, 103));
                    CSCL3.setTextColor(getResources().getColor(R.color.light));
                }
                else {
                    CargoShipCargoLeft3.setColor(Color.rgb(30, 30, 30));
                    CSCL3.setTextColor(getResources().getColor(R.color.light));
                }
                if (CSCR1Counter > 0) {
                    CargoShipCargoRight1.setColor(Color.rgb(45, 192, 103));
                    CSCR1.setTextColor(getResources().getColor(R.color.light));
                }
                else {
                    CargoShipCargoRight1.setColor(Color.rgb(30, 30, 30));
                    CSCR1.setTextColor(getResources().getColor(R.color.light));
                }
                if (CSCR2Counter > 0) {
                    CargoShipCargoRight2.setColor(Color.rgb(45, 192, 103));
                    CSCR2.setTextColor(getResources().getColor(R.color.light));
                }
                else {
                    CargoShipCargoRight2.setColor(Color.rgb(30, 30, 30));
                    CSCR2.setTextColor(getResources().getColor(R.color.light));
                }
                if (CSCR3Counter > 0) {
                    CargoShipCargoRight3.setColor(Color.rgb(45, 192, 103));
                    CSCR3.setTextColor(getResources().getColor(R.color.light));
                }
                else {
                    CargoShipCargoRight3.setColor(Color.rgb(30, 30, 30));
                    CSCR3.setTextColor(getResources().getColor(R.color.light));
                }
                if (RRCT3Counter > 0) {
                    RightRocketCargoT3.setColor(Color.rgb(45, 192, 103));
                    RRCT3.setTextColor(getResources().getColor(R.color.light));
                }
                else {
                    RightRocketCargoT3.setColor(Color.rgb(30, 30, 30));
                    RRCT3.setTextColor(getResources().getColor(R.color.light));
                }
                if (RRCT2Counter > 0) {
                    RightRocketCargoT2.setColor(Color.rgb(45, 192, 103));
                    RRCT2.setTextColor(getResources().getColor(R.color.light));
                }
                else {
                    RightRocketCargoT2.setColor(Color.rgb(30, 30, 30));
                    RRCT2.setTextColor(getResources().getColor(R.color.light));
                }
                if (RRCT1Counter > 0) {
                    RightRocketCargoT1.setColor(Color.rgb(45, 192, 103));
                    RRCT1.setTextColor(getResources().getColor(R.color.light));
                }
                else {
                    RightRocketCargoT1.setColor(Color.rgb(30, 30, 30));
                    RRCT1.setTextColor(getResources().getColor(R.color.light));
                }
        }
    }

    private void enableScoringDiagram (char c) {
        switch (c) {
            case 'A':
            case 'P':
                LeftRocketPanelNearT3.setEnabled(true);
                LeftRocketPanelNearT2.setEnabled(true);
                LeftRocketPanelNearT1.setEnabled(true);
                LeftRocketPanelFarT3.setEnabled(true);
                LeftRocketPanelFarT2.setEnabled(true);
                LeftRocketPanelFarT1.setEnabled(true);

                CargoShipPanelFront1.setEnabled(true);
                CargoShipPanelFront2.setEnabled(true);
                CargoShipPanelLeft1.setEnabled(true);
                CargoShipPanelLeft2.setEnabled(true);
                CargoShipPanelLeft3.setEnabled(true);
                CargoShipPanelRight1.setEnabled(true);
                CargoShipPanelRight2.setEnabled(true);
                CargoShipPanelRight3.setEnabled(true);

                RightRocketPanelNearT3.setEnabled(true);
                RightRocketPanelNearT2.setEnabled(true);
                RightRocketPanelNearT1.setEnabled(true);
                RightRocketPanelFarT3.setEnabled(true);
                RightRocketPanelFarT2.setEnabled(true);
                RightRocketPanelFarT1.setEnabled(true);

                if (LRPNT3Counter > 0) {
                    LeftRocketPanelNearT3.setColor(Color.rgb(248, 231, 28));
                    LRPNT3.setTextColor(getResources().getColor(R.color.textdefault));
                }
                else {
                    LeftRocketPanelNearT3.setColor(Color.rgb(255, 255, 217));
                    LRPNT3.setTextColor(getResources().getColor(R.color.textdefault));
                }
                if (LRPNT2Counter > 0) {
                    LeftRocketPanelNearT2.setColor(Color.rgb(248, 231, 28));
                    LRPNT2.setTextColor(getResources().getColor(R.color.textdefault));
                }
                else {
                    LeftRocketPanelNearT2.setColor(Color.rgb(255, 255, 217));
                    LRPNT2.setTextColor(getResources().getColor(R.color.textdefault));
                }
                if (LRPNT1Counter > 0) {
                    LeftRocketPanelNearT1.setColor(Color.rgb(248, 231, 28));
                    LRPNT1.setTextColor(getResources().getColor(R.color.textdefault));
                }
                else {
                    LeftRocketPanelNearT1.setColor(Color.rgb(255, 255, 217));
                    LRPNT1.setTextColor(getResources().getColor(R.color.textdefault));
                }
                if (LRPFT3Counter > 0) {
                    LeftRocketPanelFarT3.setColor(Color.rgb(248, 231, 28));
                    LRPFT3.setTextColor(getResources().getColor(R.color.textdefault));
                }
                else {
                    LeftRocketPanelFarT3.setColor(Color.rgb(255, 255, 217));
                    LRPFT3.setTextColor(getResources().getColor(R.color.textdefault));
                }
                if (LRPFT2Counter > 0) {
                    LeftRocketPanelFarT2.setColor(Color.rgb(248, 231, 28));
                    LRPFT2.setTextColor(getResources().getColor(R.color.textdefault));
                }
                else {
                    LeftRocketPanelFarT2.setColor(Color.rgb(255, 255, 217));
                    LRPFT2.setTextColor(getResources().getColor(R.color.textdefault));
                }
                if (LRPFT1Counter > 0) {
                    LeftRocketPanelFarT1.setColor(Color.rgb(248, 231, 28));
                    LRPFT1.setTextColor(getResources().getColor(R.color.textdefault));
                }
                else {
                    LeftRocketPanelFarT1.setColor(Color.rgb(255, 255, 217));
                    LRPFT1.setTextColor(getResources().getColor(R.color.textdefault));
                }
                if (CSPF1Counter > 0) {
                    CargoShipPanelFront1.setColor(Color.rgb(248, 231, 28));
                    CSPF1.setTextColor(getResources().getColor(R.color.textdefault));
                }
                else {
                    CargoShipPanelFront1.setColor(Color.rgb(255, 255, 217));
                    CSPF1.setTextColor(getResources().getColor(R.color.textdefault));
                }
                if (CSPF2Counter > 0) {
                    CargoShipPanelFront2.setColor(Color.rgb(248, 231, 28));
                    CSPF2.setTextColor(getResources().getColor(R.color.textdefault));
                }
                else {
                    CargoShipPanelFront2.setColor(Color.rgb(255, 255, 217));
                    CSPF1.setTextColor(getResources().getColor(R.color.textdefault));
                }
                if (CSPL1Counter > 0) {
                    CargoShipPanelLeft1.setColor(Color.rgb(248, 231, 28));
                    CSPL1.setTextColor(getResources().getColor(R.color.textdefault));
                }
                else {
                    CargoShipPanelLeft1.setColor(Color.rgb(255, 255, 217));
                    CSPL1.setTextColor(getResources().getColor(R.color.textdefault));
                }
                if (CSPL2Counter > 0) {
                    CargoShipPanelLeft2.setColor(Color.rgb(248, 231, 28));
                    CSPL2.setTextColor(getResources().getColor(R.color.textdefault));
                }
                else {
                    CargoShipPanelLeft2.setColor(Color.rgb(255, 255, 217));
                    CSPL2.setTextColor(getResources().getColor(R.color.textdefault));
                }
                if (CSPL3Counter > 0) {
                    CargoShipPanelLeft3.setColor(Color.rgb(248, 231, 28));
                    CSPL3.setTextColor(getResources().getColor(R.color.textdefault));
                }
                else {
                    CargoShipPanelLeft3.setColor(Color.rgb(255, 255, 217));
                    CSPL1.setTextColor(getResources().getColor(R.color.textdefault));
                }
                if (CSPR1Counter > 0) {
                    CargoShipPanelRight1.setColor(Color.rgb(248, 231, 28));
                    CSPR1.setTextColor(getResources().getColor(R.color.textdefault));
                }
                else {
                    CargoShipPanelRight1.setColor(Color.rgb(255, 255, 217));
                    CSPR1.setTextColor(getResources().getColor(R.color.textdefault));
                }
                if (CSPR2Counter > 0) {
                    CargoShipPanelRight2.setColor(Color.rgb(248, 231, 28));
                    CSPR2.setTextColor(getResources().getColor(R.color.textdefault));
                }
                else {
                    CargoShipPanelRight2.setColor(Color.rgb(255, 255, 217));
                    CSPR2.setTextColor(getResources().getColor(R.color.textdefault));
                }
                if (CSPR3Counter > 0) {
                    CargoShipPanelRight3.setColor(Color.rgb(248, 231, 28));
                    CSPR3.setTextColor(getResources().getColor(R.color.textdefault));
                }
                else {
                    CargoShipPanelRight3.setColor(Color.rgb(255, 255, 217));
                    CSPR3.setTextColor(getResources().getColor(R.color.textdefault));
                }
                if (RRPNT3Counter > 0) {
                    RightRocketPanelNearT3.setColor(Color.rgb(248, 231, 28));
                    RRPNT3.setTextColor(getResources().getColor(R.color.textdefault));
                }
                else {
                    RightRocketPanelNearT3.setColor(Color.rgb(255, 255, 217));
                    RRPNT3.setTextColor(getResources().getColor(R.color.textdefault));
                }
                if (RRPNT2Counter > 0) {
                    RightRocketPanelNearT2.setColor(Color.rgb(248, 231, 28));
                    RRPNT2.setTextColor(getResources().getColor(R.color.textdefault));
                }
                else {
                    RightRocketPanelNearT2.setColor(Color.rgb(255, 255, 217));
                    RRPNT2.setTextColor(getResources().getColor(R.color.textdefault));
                }
                if (RRPNT1Counter > 0) {
                    RightRocketPanelNearT1.setColor(Color.rgb(248, 231, 28));
                    RRPNT1.setTextColor(getResources().getColor(R.color.textdefault));
                }
                else {
                    RightRocketPanelNearT1.setColor(Color.rgb(255, 255, 217));
                    RRPNT1.setTextColor(getResources().getColor(R.color.textdefault));
                }
                if (RRPFT3Counter > 0) {
                    RightRocketPanelFarT3.setColor(Color.rgb(248, 231, 28));
                    RRPFT3.setTextColor(getResources().getColor(R.color.textdefault));
                }
                else {
                    RightRocketPanelFarT3.setColor(Color.rgb(255, 255, 217));
                    RRPFT3.setTextColor(getResources().getColor(R.color.textdefault));
                }
                if (RRPFT2Counter > 0) {
                    RightRocketPanelFarT2.setColor(Color.rgb(248, 231, 28));
                    RRPFT2.setTextColor(getResources().getColor(R.color.textdefault));
                }
                else {
                    RightRocketPanelFarT2.setColor(Color.rgb(255, 255, 217));
                    RRPFT2.setTextColor(getResources().getColor(R.color.textdefault));
                }
                if (RRPFT1Counter > 0) {
                    RightRocketPanelFarT1.setColor(Color.rgb(248, 231, 28));
                    RRPFT1.setTextColor(getResources().getColor(R.color.textdefault));
                }
                else {
                    RightRocketPanelFarT1.setColor(Color.rgb(255, 255, 217));
                    RRPFT1.setTextColor(getResources().getColor(R.color.textdefault));
                }
                if (c == 'P')
                    break;
            case 'C':
                LeftRocketCargoT3.setEnabled(true);
                LeftRocketCargoT2.setEnabled(true);
                LeftRocketCargoT1.setEnabled(true);

                CargoShipCargoFront1.setEnabled(true);
                CargoShipCargoFront2.setEnabled(true);
                CargoShipCargoLeft1.setEnabled(true);
                CargoShipCargoLeft2.setEnabled(true);
                CargoShipCargoLeft3.setEnabled(true);
                CargoShipCargoRight1.setEnabled(true);
                CargoShipCargoRight2.setEnabled(true);
                CargoShipCargoRight3.setEnabled(true);

                RightRocketCargoT3.setEnabled(true);
                RightRocketCargoT2.setEnabled(true);
                RightRocketCargoT1.setEnabled(true);

                if (LRCT3Counter > 0) {
                    LeftRocketCargoT3.setColor(Color.rgb(255, 152, 0));
                    LRCT3.setTextColor(getResources().getColor(R.color.light));
                }
                else {
                    LeftRocketCargoT3.setColor(Color.rgb(221, 172, 107));
                    LRCT3.setTextColor(getResources().getColor(R.color.textdefault));
                }
                if (LRCT2Counter > 0) {
                    LeftRocketCargoT2.setColor(Color.rgb(255, 152, 0));
                    LRCT2.setTextColor(getResources().getColor(R.color.light));
                }
                else {
                    LeftRocketCargoT2.setColor(Color.rgb(221, 172, 107));
                    LRCT2.setTextColor(getResources().getColor(R.color.textdefault));
                }
                if (LRCT1Counter > 0) {
                    LeftRocketCargoT1.setColor(Color.rgb(255, 152, 0));
                    LRCT1.setTextColor(getResources().getColor(R.color.light));
                }
                else {
                    LeftRocketCargoT1.setColor(Color.rgb(221, 172, 107));
                    LRCT1.setTextColor(getResources().getColor(R.color.textdefault));
                }


                if (CSCF1Counter > 0) {
                    CargoShipCargoFront1.setColor(Color.rgb(255, 152, 0));
                    CSCF1.setTextColor(getResources().getColor(R.color.light));
                }
                else {
                    CargoShipCargoFront1.setColor(Color.rgb(221, 172, 107));
                    CSCF1.setTextColor(getResources().getColor(R.color.textdefault));
                }
                if (CSCF2Counter > 0) {
                    CargoShipCargoFront2.setColor(Color.rgb(255, 152, 0));
                    CSCF2.setTextColor(getResources().getColor(R.color.light));
                }
                else {
                    CargoShipCargoFront2.setColor(Color.rgb(221, 172, 107));
                    CSCF1.setTextColor(getResources().getColor(R.color.textdefault));
                }
                if (CSCL1Counter > 0) {
                    CargoShipCargoLeft1.setColor(Color.rgb(255, 152, 0));
                    CSCL1.setTextColor(getResources().getColor(R.color.light));
                }
                else {
                    CargoShipCargoLeft1.setColor(Color.rgb(221, 172, 107));
                    CSCL1.setTextColor(getResources().getColor(R.color.textdefault));
                }
                if (CSCL2Counter > 0) {
                    CargoShipCargoLeft2.setColor(Color.rgb(255, 152, 0));
                    CSCL2.setTextColor(getResources().getColor(R.color.light));
                }
                else {
                    CargoShipCargoLeft2.setColor(Color.rgb(221, 172, 107));
                    CSCL2.setTextColor(getResources().getColor(R.color.textdefault));
                }
                if (CSCL3Counter > 0) {
                    CargoShipCargoLeft3.setColor(Color.rgb(255, 152, 0));
                    CSCL3.setTextColor(getResources().getColor(R.color.light));
                }
                else {
                    CargoShipCargoLeft3.setColor(Color.rgb(221, 172, 107));
                    CSCL3.setTextColor(getResources().getColor(R.color.textdefault));
                }
                if (CSCR1Counter > 0) {
                    CargoShipCargoRight1.setColor(Color.rgb(255, 152, 0));
                    CSCR1.setTextColor(getResources().getColor(R.color.light));
                }
                else {
                    CargoShipCargoRight1.setColor(Color.rgb(221, 172, 107));
                    CSCR1.setTextColor(getResources().getColor(R.color.textdefault));
                }
                if (CSCR2Counter > 0) {
                    CargoShipCargoRight2.setColor(Color.rgb(255, 152, 0));
                    CSCR2.setTextColor(getResources().getColor(R.color.light));
                }
                else {
                    CargoShipCargoRight2.setColor(Color.rgb(221, 172, 107));
                    CSCR2.setTextColor(getResources().getColor(R.color.textdefault));
                }
                if (CSCR3Counter > 0) {
                    CargoShipCargoRight3.setColor(Color.rgb(255, 152, 0));
                    CSCR3.setTextColor(getResources().getColor(R.color.light));
                }
                else {
                    CargoShipCargoRight3.setColor(Color.rgb(221, 172, 107));
                    CSCR3.setTextColor(getResources().getColor(R.color.textdefault));
                }


                if (RRCT3Counter > 0) {
                    RightRocketCargoT3.setColor(Color.rgb(255, 152, 0));
                    RRCT3.setTextColor(getResources().getColor(R.color.light));
                }
                else {
                    RightRocketCargoT3.setColor(Color.rgb(221, 172, 107));
                    RRCT3.setTextColor(getResources().getColor(R.color.defaultdisabled));
                }
                if (RRCT2Counter > 0) {
                    RightRocketCargoT2.setColor(Color.rgb(255, 152, 0));
                    RRCT2.setTextColor(getResources().getColor(R.color.light));
                }
                else {
                    RightRocketCargoT2.setColor(Color.rgb(221, 172, 107));
                    RRCT2.setTextColor(getResources().getColor(R.color.defaultdisabled));
                }

                if (RRCT1Counter > 0) {
                    RightRocketCargoT1.setColor(Color.rgb(255, 152, 0));
                    RRCT1.setTextColor(getResources().getColor(R.color.light));
                }
                else {
                    RightRocketCargoT1.setColor(Color.rgb(221, 172, 107));
                    RRCT3.setTextColor(getResources().getColor(R.color.light));
                }
        }
    }



    //click methods
    public void setupClick (View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("recievedHashMap", recievedHashMap);
        startActivity(intent);
    }
    public void panelCounterClick (View view) {
        selectedButtonColors(PanelButton);
        defaultButtonState(CargoButton);
        totalPanels++;
        PanelCounterText.setText(String.valueOf(totalPanels));
        CargoButton.setEnabled(false);
        isPanel = true;
        isCargo = false;
        enableScoringDiagram('P');
        disableScoringDiagram('C');
    }
    public void cargoCounterClick (View view) {
        selectedButtonColors(CargoButton);
        defaultButtonState(PanelButton);
        totalCargo++;
        CargoCounterText.setText(String.valueOf(totalCargo));
        PanelButton.setEnabled(false);
        isPanel = false;
        isCargo = true;
        enableScoringDiagram('P');
        disableScoringDiagram('C');
    }
    public void droppedClick (View view) {
        //use handler??? for temporarily active (only 500 ms)
        selectedButtonColors(DroppedButton);
        TimerTask changeToDefault = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        defaultButtonState(DroppedButton);
                    }
                });
            }
        };
        timer.schedule(changeToDefault, 500);

        if (isPanel) {
            droppedPanels++;
            totalPanels++;
            PanelCounterText.setText(String.valueOf(totalPanels));
            defaultButtonState(PanelButton);
        }
        if (isCargo) {
            droppedCargo++;
            totalCargo++;
            CargoCounterText.setText(String.valueOf(totalCargo));
            defaultButtonState(CargoButton);
        }
        DroppedButton.setEnabled(false);
        MissedButton.setEnabled(false);
        disableScoringDiagram('A');
        updateCounterDisplay(droppedPanels,droppedCargo,DroppedCounterText);
    }
    public void missedClick (View view) {
        if (isPanel)
            missedPanels++;
        if (isCargo)
            missedCargo++;
        updateCounterDisplay(missedPanels,missedCargo,DroppedCounterText);
    }

    //left rocket onClicks
    public void LRPNT3CounterClick (View view) {
        LRPNT3Counter++;
        LeftRocketPanelNearT3.setColor(Color.rgb(248, 231, 28));

    }
    public void LRPNT2CounterClick (View view) {
        LRPNT2Counter++;
        LeftRocketPanelNearT2.setColor(Color.rgb(248, 231, 28));
    }
    public void LRPNT1CounterClick (View view) {
        LRPNT1Counter++;
        LeftRocketPanelNearT1.setColor(Color.rgb(248, 231, 28));
    }
    public void LRCT3CounterClick (View view) {
        LRCT3Counter++;
        LeftRocketCargoT3.setColor(Color.argb(100, 255, 152, 0));
        ScoreData LRCT3 = new ScoreData();
        LRCT3.setAll(MODE, "Rocket", "Cargo","Left", "", "T3", LRCT3Counter);
    }
    public void LRCT2CounterClick (View view) {
        LRCT2Counter++;
        LeftRocketCargoT2.setColor(Color.argb(100, 255, 152, 0));
        ScoreData LRCT2 = new ScoreData();
        LRCT2.setAll(MODE, "Rocket", "Cargo", "Left","","T2", LRCT2Counter);
    }
    public void LRCT1CounterClick (View view) {
        LRCT1Counter++;
        LeftRocketCargoT1.setColor(Color.argb(100, 255, 152, 0));
        ScoreData LRCT1 = new ScoreData();
        LRCT1.setAll(MODE, "Rocket", "Cargo", "Left","", "T1", LRCT1Counter);
    }
    public void LRPFT3CounterClick (View view) {
        LRPFT3Counter++;
        LeftRocketPanelFarT3.setColor(Color.rgb(248, 231, 28));
        ScoreData LRPFT3 = new ScoreData();
        LRPFT3.setAll(MODE, "Rocket", "Panel", "Left",  "Far","T3", LRPFT3Counter);
    }
    public void LRPFT2CounterClick (View view) {
        LRPFT2Counter++;
        LeftRocketPanelFarT2.setColor(Color.rgb(248, 231, 28));
        ScoreData LRPFT2 = new ScoreData();
        LRPFT2.setAll(MODE, "Rocket", "Panel", "", "Far", "T2", LRPFT2Counter);
    }
    public void LRPFT1CounterClick (View view) {
        LRPFT1Counter++;
        LeftRocketPanelFarT1.setColor(Color.rgb(248, 231, 28));
        ScoreData LRPFT1 = new ScoreData();
        LRPFT1.setAll(MODE, "Rocket", "Panel", "Far", "", "T1", LRPFT1Counter);
    }

    //cargo ship onClicks
    public void CSPF1CounterClick (View view) {
        CSPF1Counter++;
        CargoShipPanelFront1.setColor(Color.rgb(248, 231, 28));
        ScoreData CSPF1 = new ScoreData();
        CSPF1.setAll(MODE, "Cargoship", "Panel", "Front", "", "", CSPF1Counter);
    }
    public void CSPF2CounterClick (View view) {
        CSPF2Counter++;
        CargoShipPanelFront2.setColor(Color.rgb(248, 231, 28));
        ScoreData CSPF2 = new ScoreData();
        CSPF2.setAll(MODE, "Cargoship", "Panel", "Front", "", "", CSPF2Counter);
    }
    public void CSCF1CounterClick (View view) {
        CSCF1Counter++;
        CargoShipCargoFront1.setColor(Color.argb(100, 255, 152, 0));
        ScoreData CSCF1 = new ScoreData();
        CSCF1.setAll(MODE, "Cargoship", "Cargo", "Front", "",  "", CSCF1Counter);
    }
    public void CSCF2CounterClick (View view) {
        CSCF2Counter++;
        CargoShipCargoFront2.setColor(Color.argb(100, 255, 152, 0));
        ScoreData CSCF2 = new ScoreData();
        CSCF2.setAll(MODE, "Cargoship", "Cargo", "Front", "", "", CSCF2Counter);
    }
    public void CSPL1CounterClick (View view) {
        CSPL1Counter++;
        CargoShipPanelLeft1.setColor(Color.rgb(248, 231, 28));
        ScoreData CSPL1 = new ScoreData();
        CSPL1.setAll(MODE, "Cargoship", "Panel", "Left", "", "", CSPL1Counter);
    }
    public void CSPL2CounterClick (View view) {
        CSPL2Counter++;
        CargoShipPanelLeft2.setColor(Color.rgb(248, 231, 28));
        ScoreData CSPL2 = new ScoreData();
        CSPL2.setAll(MODE, "Cargoship", "Panel", "Left", "", "", CSPL2Counter);
    }
    public void CSPL3CounterClick (View view) {
        CSPL3Counter++;
        CargoShipPanelLeft3.setColor(Color.rgb(248, 231, 28));
        ScoreData CSPL3 = new ScoreData();
        CSPL3.setAll(MODE, "Cargoship", "Panel", "Left", "", "", CSPL3Counter);
    }
    public void CSCL1CounterClick (View view) {
        CSCL1Counter++;
        CargoShipCargoLeft1.setColor(Color.argb(100, 255, 152, 0));
        ScoreData CSCL1 = new ScoreData();
        CSCL1.setAll(MODE, "Cargoship", "Cargo", "Left", "", "", CSCL1Counter);
    }
    public void CSCL2CounterClick (View view) {
        CSCL2Counter++;
        CargoShipCargoLeft2.setColor(Color.argb(100, 255, 152, 0));
        ScoreData CSCL2 = new ScoreData();
        CSCL2.setAll(MODE, "Cargoship", "Cargo", "Left", "", "", CSCL2Counter);
    }
    public void CSCL3CounterClick (View view) {
        CSCL3Counter++;
        CargoShipCargoLeft3.setColor(Color.argb(100, 255, 152, 0));
        ScoreData CSCL3 = new ScoreData();
        CSCL3.setAll(MODE, "Cargoship", "Cargo", "Left", "", "", CSCL3Counter);
    }
    public void CSCR1CounterClick (View view) {
        CSCR1Counter++;
        CargoShipCargoRight1.setColor(Color.argb(100, 255, 152, 0));
        ScoreData CSCR1 = new ScoreData();
        CSCR1.setAll(MODE, "Cargoship", "Cargo", "Right", "", "", CSCR1Counter);
    }
    public void CSCR2CounterClick (View view) {
        CSCR2Counter++;
        CargoShipCargoRight2.setColor(Color.argb(100, 255, 152, 0));
        ScoreData CSCR2 = new ScoreData();
        CSCR2.setAll(MODE, "Cargoship", "Cargo", "Right", "", "", CSCR2Counter);
    }
    public void CSCR3CounterClick (View view) {
        CSCR3Counter++;
        CargoShipCargoRight3.setColor(Color.argb(100, 255, 152, 0));
        ScoreData CSCR3 = new ScoreData();
        CSCR3.setAll(MODE, "Cargoship", "Cargo", "Right", "", "", CSCR3Counter);
    }
    public void CSPR1CounterClick (View view) {
        CSPR1Counter++;
        CargoShipPanelRight1.setColor(Color.rgb(248, 231, 28));
        ScoreData CSPR1 = new ScoreData();
        CSPR1.setAll(MODE, "Cargoship", "Panel", "Right", "", "", CSPR1Counter);
    }
    public void CSPR2CounterClick (View view) {
        CSPR2Counter++;
        CargoShipPanelRight2.setColor(Color.rgb(248, 231, 28));
        ScoreData CSPR2 = new ScoreData();
        CSPR2.setAll(MODE, "Cargoship", "Panel", "Right", "", "", CSPR2Counter);
    }
    public void CSPR3CounterClick (View view) {
        CSPR3Counter++;
        CargoShipPanelRight3.setColor(Color.rgb(248, 231, 28));
        ScoreData CSPR3 = new ScoreData();
        CSPR3.setAll(MODE, "Cargoship", "Panel", "Right", "", "", CSPR3Counter);
    }

    //right rocket onClicks
    public void RRPNT3CounterClick (View view) {
        RRPNT3Counter++;
        RightRocketPanelNearT3.setColor(Color.rgb(248, 231, 28));
        ScoreData RRPNT3 = new ScoreData();
        RRPNT3.setAll(MODE, "Rocket", "Panel", "Right", "Near", "T3", RRPNT3Counter);
    }
    public void RRPNT2CounterClick (View view) {
        RRPNT2Counter++;
        RightRocketPanelNearT2.setColor(Color.rgb(248, 231, 28));
        ScoreData RRPNT2 = new ScoreData();
        RRPNT2.setAll(MODE, "Rocket", "Panel", "Right", "Near", "T2", RRPNT2Counter);
    }
    public void RRPNT1CounterClick (View view) {
        RRPNT1Counter++;
        RightRocketPanelNearT1.setColor(Color.rgb(248, 231, 28));
        ScoreData RRPNT1 = new ScoreData();
        RRPNT1.setAll(MODE, "Rocket", "Panel", "Right", "Near", "T1", RRPNT1Counter);
    }
    public void RRCT3CounterClick (View view) {
        RRCT3Counter++;
        RightRocketCargoT3.setColor(Color.argb(100, 255, 152, 0));
        ScoreData RRCT3 = new ScoreData();
        RRCT3.setAll(MODE, "Rocket", "Cargo", "Right", "", "T3", RRCT3Counter);
    }
    public void RRCT2CounterClick (View view) {
        RRCT2Counter++;
        RightRocketCargoT2.setColor(Color.argb(100, 255, 152, 0));
        ScoreData RRCT2 = new ScoreData();
        RRCT2.setAll(MODE, "Rocket", "Cargo", "Right", "", "T2", RRCT2Counter);
    }
    public void RRCT1CounterClick (View view) {
        RRCT1Counter++;
        RightRocketCargoT1.setColor(Color.argb(100, 255, 152, 0));
        ScoreData RRCT1 = new ScoreData();
        RRCT1.setAll(MODE, "Rocket", "Cargo", "Right", "", "T1", RRCT1Counter);
    }
    public void RRPFT3CounterClick (View view) {
        RRPFT3Counter++;
        RightRocketPanelFarT3.setColor(Color.rgb(248, 231, 28));
        ScoreData RRPFT3 = new ScoreData();
        RRPFT3.setAll(MODE, "Rocket", "Panel", "Right", "Far", "T3", RRPFT3Counter);
    }
    public void RRPFT2CounterClick (View view) {
        RRPFT2Counter++;
        RightRocketPanelFarT2.setColor(Color.rgb(248, 231, 28));
        ScoreData RRPFT2 = new ScoreData();
        RRPFT2.setAll(MODE, "Rocket", "Panel", "Right", "Far", "T2", RRPFT2Counter);
    }
    public void RRPFT1CounterClick (View view) {
        RRPFT1Counter++;
        RightRocketPanelFarT1.setColor(Color.rgb(248, 231, 28));
        ScoreData RRPFT1 = new ScoreData();
        RRPFT1.setAll(MODE, "Rocket", "Panel", "Right", "Far", "T1", RRPFT1Counter);
    }
}
