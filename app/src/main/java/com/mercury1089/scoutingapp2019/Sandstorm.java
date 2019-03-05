package com.mercury1089.scoutingapp2019;

import android.content.Intent;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.BootstrapButton;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import at.markushi.ui.CircleButton;

import static java.lang.Math.abs;

public class Sandstorm extends MainActivity {
    private int CSPR2Counter = 0;
    private int CSPR1Counter = 0;
    private int CSPR3Counter = 0;

    private int RRPNT1Counter = 0;
    private int RRPNT2Counter = 0;
    private int RRPNT3Counter = 0;
    private int RRCT1Counter = 0;
    private int RRCT2Counter = 0;
    private int RRCT3Counter = 0;
    private int RRPFT1Counter = 0;
    private int RRPFT2Counter = 0;
    private int RRPFT3Counter = 0;

    private TextView LRPNT1;
    private TextView LRPNT2;
    private TextView LRPNT3;
    private TextView LRCT1;
    private TextView LRCT2;
    private TextView LRCT3;
    private TextView LRPFT1;
    private TextView LRPFT2;
    private TextView LRPFT3;

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

    private TextView RRPNT1;
    private TextView RRPNT2;
    private TextView RRPNT3;
    private TextView RRCT1;
    private TextView RRCT2;
    private TextView RRCT3;
    private TextView RRPFT1;
    private TextView RRPFT2;
    private TextView RRPFT3;

    //right rocket text
    //cargo ship text
    //left rocket text
    //right rocket counters
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
    CircleButton LeftRocketCargoT1;
    CircleButton LeftRocketCargoT2;


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
    private HashMap<String, String> setupHashMap;
    private HashMap<String, String> scoreHashMap;

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

    //text views
    TextView possessionTitle;
    TextView panelOrCargoDirections;
    TextView droppedDirection;

    TextView scoringTitle;
    TextView pOrCDirections;
    TextView missedDirections;

    //other variables
    private boolean isCargo = false;
    private boolean isPanel = false;
    private Timer timer;
    Button UndoButton;
    ConstraintLayout constraintLayout;
    String UNDO;
    Switch FellOverSwitch;
    Switch HABLineSwitch;
    int YELLOW = Color.rgb(248, 231, 28);
    int ORANGE = Color.rgb(255, 152, 0);


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
        setupHashMap = new HashMap<>();
        scoreHashMap = new HashMap<>();
        timer = new Timer();
        UndoButton = findViewById(R.id.UndoButton);
        possessionTitle = findViewById(R.id.IDPossession);
        panelOrCargoDirections = findViewById(R.id.IDPanelOrCargoDirections);
        droppedDirection = findViewById(R.id.IDDroppedDirections);

        scoringTitle = findViewById(R.id.IDScoring);
        pOrCDirections = findViewById(R.id.IDScoringPOrCDirections);
        missedDirections = findViewById(R.id.IDScoringMissedDirections);

        constraintLayout = findViewById(R.id.layout);

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

        UndoButton.setEnabled(false);

        final Serializable setupData = getIntent().getSerializableExtra("setupHashMap");
        setupHashMap = (HashMap<String, String>)setupData;



        TimerTask switchToTeleop = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < constraintLayout.getChildCount(); i++) {
                            if (constraintLayout.getChildAt(i) instanceof TextView) {
                                TextView textView = (TextView) constraintLayout.getChildAt(i);
                                if (textView.getTag() != null) {
                                    if (!textView.getTag().equals("") && !textView.getTag().equals("label")) {
                                        scoreHashMap.put(textView.getTag().toString(), textView.getText().toString());
                                    }
                                }
                            }
                        }
                        scoreHashMap.put("Sandstorm,Missed,Panel,,,", missedPanels + "");
                        scoreHashMap.put("Sandstorm,Missed,Cargo,,,", missedCargo + "");
                        scoreHashMap.put("Sandstorm,Dropped,Panel,,,", droppedPanels + "");
                        scoreHashMap.put("Sandstorm,Dropped,Cargo,,,", droppedCargo + "");



                        Intent intent = new Intent(Sandstorm.this, Teleop.class);
                        String POrC = " ";
                        if (isPanel)
                            POrC = "P";
                        else if (isCargo)
                            POrC = "C";
                        intent.putExtra("setupHashMap", setupHashMap);
                        intent.putExtra("scoreHashMap", scoreHashMap);
                        intent.putExtra("POrC", POrC);
                        startActivity(intent);
                    }
                });
            }
        };
        timer.schedule(switchToTeleop, 15000);

        Serializable scoreData = getIntent().getSerializableExtra("scoreHashMap");

        if (scoreData != null) {
            constraintLayout.setBackgroundColor(Color.rgb(91,24,24));
            scoreHashMap = (HashMap<String, String>) scoreData;
            Object keySet[] = scoreHashMap.keySet().toArray();
            String tag;
            for (int i = 0; i < keySet.length; i++) {
                tag = String.valueOf(keySet[i]);
                char arr[] = tag.toCharArray();
                String hashVal;

                if (arr[1] == 'R')
                {
                    if (arr[0] == 'L')
                    {
                        if (arr[2] == 'P')
                        {
                            if (arr[3] == 'N')
                            {
                                if (arr[5] == '1')
                                {
                                    hashVal = scoreHashMap.get("LRPNT1");
                                    LRPNT1.setText(hashVal);
                                    if (Integer.parseInt(hashVal) > 0) {
                                        LeftRocketPanelNearT1.setColor(YELLOW);
                                        LRPNT1.setTextColor(getResources().getColor(R.color.textdefault));
                                    }
                                    else {
                                        //panel disabled colors
                                        //panel text set
                                    }
                                }
                                else if (arr[5] == '2')
                                {
                                    hashVal = scoreHashMap.get("LRPNT2");
                                    LRPNT2.setText(hashVal);
                                    if (Integer.parseInt(hashVal) > 0) {
                                        LeftRocketPanelNearT2.setColor(YELLOW);
                                        LRPNT2.setTextColor(getResources().getColor(R.color.textdefault));
                                    }
                                    else {
                                        //panel disabled colors
                                        //panel text set
                                    }
                                }
                                else if (arr[5] == '3')
                                {
                                    hashVal = scoreHashMap.get("LRPNT3");
                                    LRPNT3.setText(hashVal);
                                    if (Integer.parseInt(hashVal) > 0) {
                                        LeftRocketPanelNearT3.setColor(YELLOW);
                                        LRPNT3.setTextColor(getResources().getColor(R.color.textdefault));
                                    }
                                    else {
                                        //panel disabled colors
                                        //panel text set
                                    }
                                }
                            }
                            else if (arr[3] == 'F')
                            {
                                if (arr[5] == '1')
                                {
                                    hashVal = scoreHashMap.get("LRPFT1");
                                    LRPFT1.setText(hashVal);
                                    if (Integer.parseInt(hashVal) > 0) {
                                        LeftRocketPanelFarT1.setColor(YELLOW);
                                        LRPFT1.setTextColor(getResources().getColor(R.color.textdefault));
                                    }
                                    else {
                                        //panel disabled colors
                                        //panel text set
                                    }
                                }
                                else if (arr[5] == '2')
                                {
                                    hashVal = scoreHashMap.get("LRPFT2");
                                    LRPFT2.setText(hashVal);
                                    if (Integer.parseInt(hashVal) > 0) {
                                        LeftRocketPanelFarT2.setColor(YELLOW);
                                        LRPFT2.setTextColor(getResources().getColor(R.color.textdefault));
                                    }
                                    else {
                                        //panel disabled colors
                                        //panel text set
                                    }
                                }
                                else if (arr[5] == '3')
                                {
                                    hashVal = scoreHashMap.get("LRPFT3");
                                    LRPFT3.setText(hashVal);
                                    if (Integer.parseInt(hashVal) > 0) {
                                        LeftRocketPanelFarT3.setColor(YELLOW);
                                        LRPFT3.setTextColor(getResources().getColor(R.color.textdefault));
                                    }
                                    else {
                                        //panel disabled colors
                                        //panel text set
                                    }
                                }
                            }
                        }
                        else if (arr[2] == 'C')
                        {
                            if (arr[4] == '1')
                            {
                                hashVal = scoreHashMap.get("LRCT1");
                                LRCT1.setText(hashVal);
                                if (Integer.parseInt(hashVal) > 0) {
                                    LeftRocketCargoT1.setColor(ORANGE);
                                    //panel text set
                                }
                                else {
                                    //panel disabled colors
                                    //panel text set
                                }
                            }
                            else if (arr[4] == '2')
                            {
                                hashVal = scoreHashMap.get("LRCT2");
                                LRCT2.setText(hashVal);
                                if (Integer.parseInt(hashVal) > 0) {
                                    LeftRocketCargoT2.setColor(ORANGE);
                                    //panel text set
                                }
                                else {
                                    //panel disabled colors
                                    //panel text set
                                }
                            }
                            else if (arr[4] == '3')
                            {
                                hashVal = scoreHashMap.get("LRCT3");
                                LRCT3.setText(hashVal);
                                if (Integer.parseInt(hashVal) > 0) {
                                    LeftRocketCargoT3.setColor(ORANGE);
                                    //panel text set
                                }
                                else {
                                    //panel disabled colors
                                    //panel text set
                                }
                            }
                        }
                    }
                    else if (arr[0] == 'R') {
                        if (arr[2] == 'P')
                        {
                            if (arr[3] == 'N')
                            {
                                if (arr[5] == '1')
                                {
                                    hashVal = scoreHashMap.get("RRPNT1");
                                    RRPNT1.setText(hashVal);
                                    if (Integer.parseInt(hashVal) > 0) {
                                        RightRocketPanelNearT1.setColor(YELLOW);
                                        RRPNT1.setTextColor(getResources().getColor(R.color.textdefault));
                                    }
                                    else {
                                        //panel disabled colors
                                        //panel text set
                                    }
                                }
                                else if (arr[5] == '2')
                                {
                                    hashVal = scoreHashMap.get("RRPNT2");
                                    RRPNT2.setText(hashVal);
                                    if (Integer.parseInt(hashVal) > 0) {
                                        RightRocketPanelNearT2.setColor(YELLOW);
                                        RRPNT2.setTextColor(getResources().getColor(R.color.textdefault));
                                    }
                                    else {
                                        //panel disabled colors
                                        //panel text set
                                    }
                                }
                                else if (arr[5] == '3')
                                {
                                    hashVal = scoreHashMap.get("RRPNT3");
                                    RRPNT3.setText(hashVal);
                                    if (Integer.parseInt(hashVal) > 0) {
                                        RightRocketPanelNearT3.setColor(YELLOW);
                                        RRPNT3.setTextColor(getResources().getColor(R.color.textdefault));
                                    }
                                    else {
                                        //panel disabled colors
                                        //panel text set
                                    }
                                }
                            }
                            else if (arr[3] == 'F')
                            {
                                if (arr[5] == '1')
                                {
                                    hashVal = scoreHashMap.get("RRPFT1");
                                    RRPFT1.setText(hashVal);
                                    if (Integer.parseInt(hashVal) > 0) {
                                        RightRocketPanelFarT1.setColor(YELLOW);
                                        RRPFT1.setTextColor(getResources().getColor(R.color.textdefault));
                                    }
                                    else {
                                        //panel disabled colors
                                        //panel text set
                                    }
                                }
                                else if (arr[5] == '2')
                                {
                                    hashVal = scoreHashMap.get("RRPFT2");
                                    RRPFT2.setText(hashVal);
                                    if (Integer.parseInt(hashVal) > 0) {
                                        RightRocketPanelFarT2.setColor(YELLOW);
                                        RRPFT2.setTextColor(getResources().getColor(R.color.textdefault));
                                    }
                                    else {
                                        //panel disabled colors
                                        //panel text set
                                    }
                                }
                                else if (arr[5] == '3')
                                {
                                    hashVal = scoreHashMap.get("RRPFT3");
                                    RRPFT3.setText(hashVal);
                                    if (Integer.parseInt(hashVal) > 0) {
                                        RightRocketPanelFarT3.setColor(YELLOW);
                                        RRPFT3.setTextColor(getResources().getColor(R.color.textdefault));
                                    }
                                    else {
                                        //panel disabled colors
                                        //panel text set
                                    }
                                }
                            }
                        }
                        else if (arr[2] == 'C')
                        {
                            if (arr[4] == '1')
                            {
                                hashVal = scoreHashMap.get("RRCT1");
                                RRCT1.setText(hashVal);
                                if (Integer.parseInt(hashVal) > 0) {
                                    RightRocketCargoT1.setColor(ORANGE);
                                    //panel text set
                                }
                                else {
                                    //panel disabled colors
                                    //panel text set
                                }
                            }
                            else if (arr[4] == '2')
                            {
                                hashVal = scoreHashMap.get("RRCT2");
                                RRCT2.setText(hashVal);
                                if (Integer.parseInt(hashVal) > 0) {
                                    RightRocketCargoT2.setColor(ORANGE);
                                    //panel text set
                                }
                                else {
                                    //panel disabled colors
                                    //panel text set
                                }
                            }
                            else if (arr[4] == '3')
                            {
                                hashVal = scoreHashMap.get("RRCT3");
                                RRCT3.setText(hashVal);
                                if (Integer.parseInt(hashVal) > 0) {
                                    RightRocketCargoT3.setColor(ORANGE);
                                    //panel text set
                                }
                                else {
                                    //panel disabled colors
                                    //panel text set
                                }
                            }
                        }
                    }
                }
                else if (tag.toCharArray()[1] == 'S') {
                    if (arr[2] == 'P')
                    {
                        if (arr[3] == 'L') {
                            if (arr[4] == '1') {
                                hashVal = scoreHashMap.get("CSPL1");
                                CSPL1.setText(hashVal);
                                if (Integer.parseInt(hashVal) > 0) {
                                    CargoShipPanelLeft1.setColor(YELLOW);
                                    CSPL1.setTextColor(getResources().getColor(R.color.textdefault));
                                }
                                else {
                                    //panel disabled colors
                                    //panel text set
                                }
                            }
                            else if (arr[4] == '2') {
                                hashVal = scoreHashMap.get("CSPL2");
                                CSPL2.setText(hashVal);
                                if (Integer.parseInt(hashVal) > 0) {
                                    CargoShipPanelLeft2.setColor(YELLOW);
                                    CSPL2.setTextColor(getResources().getColor(R.color.textdefault));
                                }
                                else {
                                    //panel disabled colors
                                    //panel text set
                                }
                            }
                            else if (arr[4] == '3') {
                                hashVal = scoreHashMap.get("CSPL3");
                                CSPL3.setText(hashVal);
                                if (Integer.parseInt(hashVal) > 0) {
                                    CargoShipPanelLeft3.setColor(YELLOW);
                                    CSPL3.setTextColor(getResources().getColor(R.color.textdefault));
                                }
                                else {
                                    //panel disabled colors
                                    //panel text set
                                }
                            }
                        }
                        else if (arr[3] == 'R') {
                            if (arr[4] == '1') {
                                hashVal = scoreHashMap.get("CSPR1");
                                CSPR1.setText(hashVal);
                                if (Integer.parseInt(hashVal) > 0) {
                                    CargoShipPanelRight1.setColor(YELLOW);
                                    CSPR1.setTextColor(getResources().getColor(R.color.textdefault));
                                }
                                else {
                                    //panel disabled colors
                                    //panel text set
                                }
                            }
                            else if (arr[4] == '2') {
                                hashVal = scoreHashMap.get("CSPR2");
                                CSPR2.setText(hashVal);
                                if (Integer.parseInt(hashVal) > 0) {
                                    CargoShipPanelRight2.setColor(YELLOW);
                                    CSPR2.setTextColor(getResources().getColor(R.color.textdefault));
                                }
                                else {
                                    //panel disabled colors
                                    //panel text set
                                }
                            }
                            else if (arr[4] == '3') {
                                hashVal = scoreHashMap.get("CSPR3");
                                CSPR3.setText(hashVal);
                                if (Integer.parseInt(hashVal) > 0) {
                                    CargoShipPanelRight3.setColor(YELLOW);
                                    CSPR3.setTextColor(getResources().getColor(R.color.textdefault));
                                }
                                else {
                                    //panel disabled colors
                                    //panel text set
                                }
                            }
                        }
                        else if (arr[3] == 'F') {
                            if (arr[4] == '1') {
                                hashVal = scoreHashMap.get("CSPF1");
                                CSPF1.setText(hashVal);
                                if (Integer.parseInt(hashVal) > 0) {
                                    CargoShipPanelFront1.setColor(YELLOW);
                                    CSPF1.setTextColor(getResources().getColor(R.color.textdefault));
                                }
                                else {
                                    //panel disabled colors
                                    //panel text set
                                }
                            }
                            else if (arr[4] == '2') {
                                hashVal = scoreHashMap.get("CSPF2");
                                CSPF2.setText(hashVal);
                                if (Integer.parseInt(hashVal) > 0) {
                                    CargoShipPanelFront2.setColor(YELLOW);
                                    CSPF2.setTextColor(getResources().getColor(R.color.textdefault));
                                }
                                else {
                                    //panel disabled colors
                                    //panel text set
                                }
                            }
                        }
                    }
                    else if (arr[2] == 'C')
                    {
                        if (arr[3] == 'L') {
                            if (arr[4] == '1') {
                                hashVal = scoreHashMap.get("CSCL1");
                                CSPL1.setText(hashVal);
                                if (Integer.parseInt(hashVal) > 0) {
                                    CargoShipCargoLeft1.setColor(ORANGE);
                                    //panel text set
                                }
                                else {
                                    //panel disabled colors
                                    //panel text set
                                }
                            }
                            else if (arr[4] == '2') {
                                hashVal = scoreHashMap.get("CSCL2");
                                CSPL2.setText(hashVal);
                                if (Integer.parseInt(hashVal) > 0) {
                                    CargoShipCargoLeft2.setColor(ORANGE);
                                    //panel text set
                                }
                                else {
                                    //panel disabled colors
                                    //panel text set
                                }
                            }
                            else if (arr[4] == '3') {
                                hashVal = scoreHashMap.get("CSCL3");
                                CSPL3.setText(hashVal);
                                if (Integer.parseInt(hashVal) > 0) {
                                    CargoShipCargoLeft3.setColor(ORANGE);
                                    //panel text set
                                }
                                else {
                                    //panel disabled colors
                                    //panel text set
                                }
                            }
                        }
                        else if (arr[3] == 'R') {
                            if (arr[4] == '1') {
                                hashVal = scoreHashMap.get("CSCR1");
                                CSPR1.setText(hashVal);
                                if (Integer.parseInt(hashVal) > 0) {
                                    CargoShipCargoRight1.setColor(ORANGE);
                                    //panel text set
                                } else {
                                    //panel disabled colors
                                    //panel text set
                                }
                            } else if (arr[4] == '2') {
                                hashVal = scoreHashMap.get("CSCL2");
                                CSPL2.setText(hashVal);
                                if (Integer.parseInt(hashVal) > 0) {
                                    CargoShipCargoRight2.setColor(ORANGE);
                                    //panel text set
                                } else {
                                    //panel disabled colors
                                    //panel text set
                                }
                            } else if (arr[4] == '3') {
                                hashVal = scoreHashMap.get("CSCL3");
                                CSPL3.setText(hashVal);
                                if (Integer.parseInt(hashVal) > 0) {
                                    CargoShipCargoRight3.setColor(ORANGE);
                                    //panel text set
                                } else {
                                    //panel disabled colors
                                    //panel text set
                                }
                            }
                        }
                    }
                }
            }
        }
        else {
            scoreHashMap = new HashMap<>();

        }


         if (setupHashMap.get("StartingGameObject").equals("Panel")) {
            selectedButtonColors(PanelButton);
            PanelCounterText.setText("1");
            CargoButton.setEnabled(false);
            CargoCounterText.setEnabled(false);
            isPanel = true;
            totalPanels++;
             enableScoringDiagram('P');
         } else if (setupHashMap.get("StartingGameObject").equals("Cargo")){
             selectedButtonColors(CargoButton);
             CargoCounterText.setText("1");
             PanelButton.setEnabled(false);
             PanelCounterText.setEnabled(false);
             isCargo = true;
             totalCargo++;
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

        CircleButton PanelCounterCircle = findViewById(R.id.PanelCounterCircle);
        CircleButton CargoCounterCircle = findViewById(R.id.CargoCounterCircle);
        final CircleButton DroppedCounterCircle = findViewById(R.id.DroppedCounterCircle);
        CircleButton MissedCounterCircle = findViewById(R.id.MissedCounterCircle);

        PanelCounterCircle.setEnabled(false);
        CargoCounterCircle.setEnabled(false);
        DroppedCounterCircle.setEnabled(false);
        MissedCounterCircle.setEnabled(false);

        possessionTitle.setTextColor(getResources().getColor(R.color.light));
        panelOrCargoDirections.setTextColor(getResources().getColor(R.color.light));
        droppedDirection.setTextColor(getResources().getColor(R.color.light));
        scoringTitle.setTextColor(getResources().getColor(R.color.light));
        pOrCDirections.setTextColor(getResources().getColor(R.color.light));
        missedDirections.setTextColor(getResources().getColor(R.color.light));

        MissedButton.setBackgroundColor(getResources().getColor(R.color.light));
        MissedButton.setTextColor(getResources().getColor(R.color.grey));
        MissedCounterText.setTextColor(getResources().getColor(R.color.light));
        MissedButton.setEnabled(true);
        MissedCounterText.setEnabled(true);

        DroppedButton.setBackgroundColor(getResources().getColor(R.color.light));
        DroppedButton.setTextColor(getResources().getColor(R.color.grey));
        DroppedCounterText.setTextColor(getResources().getColor(R.color.light));
        DroppedButton.setEnabled(true);
        DroppedCounterText.setEnabled(true);

        HABLineSwitch = findViewById(R.id.CrossedHABLineSwitch);
        HABLineSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    UNDO = "HAB";
                    UndoButton.setEnabled(true);
                    setupHashMap.put("HABLine",String.valueOf(1));
                }
                else
                    setupHashMap.put("HABLine",String.valueOf(0));
            }
        });



        FellOverSwitch = findViewById(R.id.FellOverSwitch);
        FellOverSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            boolean wasPanel = false;
            boolean wasCargo = false;
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                String color;
                if (isChecked) {
                    if (UNDO != null) {
                        if (UNDO.equals("Panel"))
                            wasPanel = true;
                        else if (UNDO.equals("Cargo"))
                            wasCargo = true;
                    }
                    UNDO = "FellOver";
                    UndoButton.setEnabled(true);
                    setupHashMap.put("FellOver",String.valueOf(1));
                    defaultButtonState(PanelButton);
                    defaultButtonState(CargoButton);
                    defaultButtonState(DroppedButton);
                    defaultButtonState(MissedButton);
                    HABLineSwitch.setEnabled(false);

                    PanelButton.setEnabled(false);
                    PanelCounterText.setEnabled(false);
                    CargoButton.setEnabled(false);
                    CargoCounterText.setEnabled(false);
                    DroppedButton.setEnabled(false);
                    DroppedCounterText.setEnabled(false);
                    MissedButton.setEnabled(false);
                    MissedCounterText.setEnabled(false);

                    disableScoringDiagram('A');


                    color = "grey";

                } else {
                    setupHashMap.put("FellOver",String.valueOf(0));
                    HABLineSwitch.setEnabled(true);
                    PanelButton.setEnabled(true);
                    PanelCounterText.setEnabled(true);
                    CargoButton.setEnabled(true);
                    CargoCounterText.setEnabled(true);
                    DroppedButton.setEnabled(false);
                    DroppedCounterText.setEnabled(false);
                    MissedButton.setEnabled(false);
                    MissedCounterText.setEnabled(false);
                    if (wasPanel) {
                        UNDO = "Panel";
                        UndoButton.setEnabled(true);
                        selectedButtonColors(PanelButton);
                        defaultButtonState(CargoButton);
                        PanelCounterText.setText(String.valueOf(totalPanels));
                        PanelButton.setEnabled(false);
                        PanelCounterText.setEnabled(false);
                        CargoButton.setEnabled(false);
                        CargoCounterText.setEnabled(false);
                        DroppedButton.setEnabled(true);
                        DroppedCounterText.setEnabled(true);
                        MissedButton.setEnabled(true);
                        MissedCounterText.setEnabled(true);
                        enableScoringDiagram('P');
                        disableScoringDiagram('C');
                    }
                    if (wasCargo) {
                        UNDO = "Cargo";
                        UndoButton.setEnabled(true);
                        selectedButtonColors(CargoButton);
                        defaultButtonState(PanelButton);
                        CargoCounterText.setText(String.valueOf(totalCargo));
                        PanelButton.setEnabled(false);
                        PanelCounterText.setEnabled(false);
                        CargoButton.setEnabled(false);
                        CargoCounterText.setEnabled(false);
                        DroppedButton.setEnabled(true);
                        DroppedCounterText.setEnabled(true);
                        MissedButton.setEnabled(true);
                        MissedCounterText.setEnabled(true);
                        enableScoringDiagram('C');
                        disableScoringDiagram('P');
                    }
                    color = "white";




                }

                for (int i = 0; i < constraintLayout.getChildCount(); i++) {
                    if ((constraintLayout.getChildAt(i) instanceof TextView) && (constraintLayout.getChildAt(i).getTag() != null)) {
                            if (constraintLayout.getChildAt(i).getTag().toString().length() > 9) {
                                String tag = constraintLayout.getChildAt(i).getTag().toString();
                                if (tag.equals("label")) {
                                    setTextToColor((TextView) constraintLayout.getChildAt(i), color);
                                }
                            }
                        }
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
    private void defaultButtonState (BootstrapButton button) {
        button.setBackgroundColor(getResources().getColor(R.color.light));
        button.setTextColor(getResources().getColor(R.color.grey));
    }
    public void selectedButtonColors(BootstrapButton button) {
        button.setBackgroundColor(getResources().getColor(R.color.orange));
        button.setTextColor(getResources().getColor(R.color.light));
    }
    private void setTextToColor (TextView textView, String color) {
        if (color.equals("grey"))
            textView.setTextColor(getResources().getColor(R.color.grey));
        else if (color.equals("white"))
            textView.setTextColor(getResources().getColor(R.color.light));
    }


    private void disableScoringDiagram (char c) {
        switch (c) {
            case 'A':
                isCargo = false;
                isPanel = false;
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

                LRPNT1.setEnabled(false);
                LRPNT2.setEnabled(false);
                LRPNT3.setEnabled(false);

                LRPFT1.setEnabled(false);
                LRPFT2.setEnabled(false);
                LRPFT3.setEnabled(false);
                CSPF1.setEnabled(false);
                CSPF2.setEnabled(false);

                CSPL1.setEnabled(false);
                CSPL2.setEnabled(false);
                CSPL3.setEnabled(false);

                CSPR1.setEnabled(false);
                CSPR2.setEnabled(false);
                CSPR3.setEnabled(false);
                RRPNT1.setEnabled(false);
                RRPNT2.setEnabled(false);
                RRPNT3.setEnabled(false);

                RRPFT1.setEnabled(false);
                RRPFT2.setEnabled(false);
                RRPFT3.setEnabled(false);

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

                LRCT1.setEnabled(false);
                LRCT2.setEnabled(false);
                LRCT3.setEnabled(false);

                CSCF1.setEnabled(false);
                CSCF2.setEnabled(false);

                CSCL1.setEnabled(false);
                CSCL2.setEnabled(false);
                CSCL3.setEnabled(false);
                CSCR1.setEnabled(false);
                CSCR2.setEnabled(false);
                CSCR3.setEnabled(false);

                RRCT1.setEnabled(false);
                RRCT2.setEnabled(false);
                RRCT3.setEnabled(false);

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

                LRPNT1.setEnabled(true);
                LRPNT2.setEnabled(true);
                LRPNT3.setEnabled(true);

                LRPFT1.setEnabled(true);
                LRPFT2.setEnabled(true);
                LRPFT3.setEnabled(true);
                CSPF1.setEnabled(true);
                CSPF2.setEnabled(true);

                CSPL1.setEnabled(true);
                CSPL2.setEnabled(true);
                CSPL3.setEnabled(true);

                CSPR1.setEnabled(true);
                CSPR2.setEnabled(true);
                CSPR3.setEnabled(true);
                RRPNT1.setEnabled(true);
                RRPNT2.setEnabled(true);
                RRPNT3.setEnabled(true);

                RRPFT1.setEnabled(true);
                RRPFT2.setEnabled(true);
                RRPFT3.setEnabled(true);



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
                    CSPF2.setTextColor(getResources().getColor(R.color.textdefault));
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
                    CSPL3.setTextColor(getResources().getColor(R.color.textdefault));
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

                LRCT1.setEnabled(true);
                LRCT2.setEnabled(true);
                LRCT3.setEnabled(true);

                CSCF1.setEnabled(true);
                CSCF2.setEnabled(true);

                CSCL1.setEnabled(true);
                CSCL2.setEnabled(true);
                CSCL3.setEnabled(true);
                CSCR1.setEnabled(true);
                CSCR2.setEnabled(true);
                CSCR3.setEnabled(true);

                RRCT1.setEnabled(true);
                RRCT2.setEnabled(true);
                RRCT3.setEnabled(true);

                if (LRCT3Counter > 0) {
                    LeftRocketCargoT3.setColor(Color.rgb(255, 152, 0));
                    LRCT3.setTextColor(getResources().getColor(R.color.light));
                }
                else {
                    LeftRocketCargoT3.setColor(Color.rgb(221, 172, 107));
                    LRCT3.setTextColor(getResources().getColor(R.color.defaultdisabled));
                }
                if (LRCT2Counter > 0) {
                    LeftRocketCargoT2.setColor(Color.rgb(255, 152, 0));
                    LRCT2.setTextColor(getResources().getColor(R.color.light));
                }
                else {
                    LeftRocketCargoT2.setColor(Color.rgb(221, 172, 107));
                    LRCT2.setTextColor(getResources().getColor(R.color.defaultdisabled));
                }
                if (LRCT1Counter > 0) {
                    LeftRocketCargoT1.setColor(Color.rgb(255, 152, 0));
                    LRCT1.setTextColor(getResources().getColor(R.color.light));
                }
                else {
                    LeftRocketCargoT1.setColor(Color.rgb(221, 172, 107));
                    LRCT1.setTextColor(getResources().getColor(R.color.defaultdisabled));
                }


                if (CSCF1Counter > 0) {
                    CargoShipCargoFront1.setColor(Color.rgb(255, 152, 0));
                    CSCF1.setTextColor(getResources().getColor(R.color.light));
                }
                else {
                    CargoShipCargoFront1.setColor(Color.rgb(221, 172, 107));
                    CSCF1.setTextColor(getResources().getColor(R.color.defaultdisabled));
                }
                if (CSCF2Counter > 0) {
                    CargoShipCargoFront2.setColor(Color.rgb(255, 152, 0));
                    CSCF2.setTextColor(getResources().getColor(R.color.light));
                }
                else {
                    CargoShipCargoFront2.setColor(Color.rgb(221, 172, 107));
                    CSCF2.setTextColor(getResources().getColor(R.color.defaultdisabled));
                }
                if (CSCL1Counter > 0) {
                    CargoShipCargoLeft1.setColor(Color.rgb(255, 152, 0));
                    CSCL1.setTextColor(getResources().getColor(R.color.light));
                }
                else {
                    CargoShipCargoLeft1.setColor(Color.rgb(221, 172, 107));
                    CSCL1.setTextColor(getResources().getColor(R.color.defaultdisabled));
                }
                if (CSCL2Counter > 0) {
                    CargoShipCargoLeft2.setColor(Color.rgb(255, 152, 0));
                    CSCL2.setTextColor(getResources().getColor(R.color.light));
                }
                else {
                    CargoShipCargoLeft2.setColor(Color.rgb(221, 172, 107));
                    CSCL2.setTextColor(getResources().getColor(R.color.defaultdisabled));
                }
                if (CSCL3Counter > 0) {
                    CargoShipCargoLeft3.setColor(Color.rgb(255, 152, 0));
                    CSCL3.setTextColor(getResources().getColor(R.color.light));
                }
                else {
                    CargoShipCargoLeft3.setColor(Color.rgb(221, 172, 107));
                    CSCL3.setTextColor(getResources().getColor(R.color.defaultdisabled));
                }
                if (CSCR1Counter > 0) {
                    CargoShipCargoRight1.setColor(Color.rgb(255, 152, 0));
                    CSCR1.setTextColor(getResources().getColor(R.color.light));
                }
                else {
                    CargoShipCargoRight1.setColor(Color.rgb(221, 172, 107));
                    CSCR1.setTextColor(getResources().getColor(R.color.defaultdisabled));
                }
                if (CSCR2Counter > 0) {
                    CargoShipCargoRight2.setColor(Color.rgb(255, 152, 0));
                    CSCR2.setTextColor(getResources().getColor(R.color.light));
                }
                else {
                    CargoShipCargoRight2.setColor(Color.rgb(221, 172, 107));
                    CSCR2.setTextColor(getResources().getColor(R.color.defaultdisabled));
                }
                if (CSCR3Counter > 0) {
                    CargoShipCargoRight3.setColor(Color.rgb(255, 152, 0));
                    CSCR3.setTextColor(getResources().getColor(R.color.light));
                }
                else {
                    CargoShipCargoRight3.setColor(Color.rgb(221, 172, 107));
                    CSCR3.setTextColor(getResources().getColor(R.color.defaultdisabled));
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
                    RRCT1.setTextColor(getResources().getColor(R.color.defaultdisabled));
                }
        }
    }



    //click methods
    public void setupClick (View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("setupHashMap", setupHashMap);
        timer.cancel();
        startActivity(intent);
    }
    public void teleopClick (View view) {
        Intent intent = new Intent(this, Teleop.class);

        if (isPanel)
            intent.putExtra("prepopPOrC", 'P');
        else if (isCargo)
            intent.putExtra("prepopPOrC", 'C');

        intent.putExtra("setupHashMap", setupHashMap);
        intent.putExtra("scoreHashMap", scoreHashMap);
        timer.cancel();
        startActivity(intent);
    }

    public void climbClick (View view) {
        Intent intent = new Intent(this, Climb.class);
        intent.putExtra("setupHashMap", setupHashMap);
        intent.putExtra("scoreHashMap", scoreHashMap);
        timer.cancel();
        startActivity(intent);
    }

    //called when a hatch panel is scored
    public void panelCounterClick (View view) {
        UNDO = "Panel";
        UndoButton.setEnabled(true);
        selectedButtonColors(PanelButton);
        defaultButtonState(CargoButton);
        totalPanels++;
        PanelCounterText.setText(String.valueOf(totalPanels));
        PanelButton.setEnabled(false);
        PanelCounterText.setEnabled(false);
        CargoButton.setEnabled(false);
        CargoCounterText.setEnabled(false);
        DroppedButton.setEnabled(true);
        DroppedCounterText.setEnabled(true);
        MissedButton.setEnabled(true);
        MissedCounterText.setEnabled(true);
        isPanel = true;
        isCargo = false;
        enableScoringDiagram('P');
        disableScoringDiagram('C');
    }

    //called when a cargo is scored
    public void cargoCounterClick (View view) {
        UNDO = "Cargo";
        UndoButton.setEnabled(true);
        selectedButtonColors(CargoButton);
        defaultButtonState(PanelButton);
        totalCargo++;
        CargoCounterText.setText(String.valueOf(totalCargo));
        PanelButton.setEnabled(false);
        PanelCounterText.setEnabled(false);
        CargoButton.setEnabled(false);
        CargoCounterText.setEnabled(false);
        DroppedButton.setEnabled(true);
        DroppedCounterText.setEnabled(true);
        MissedButton.setEnabled(true);
        MissedCounterText.setEnabled(true);
        isPanel = false;
        isCargo = true;
        enableScoringDiagram('C');
        disableScoringDiagram('P');
    }

    public void droppedClick (View view) {
        UNDO = "Dropped";
        UndoButton.setEnabled(true);

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
            defaultButtonState(PanelButton);
        }
        if (isCargo) {
            droppedCargo++;
            defaultButtonState(CargoButton);
        }

        int totalDropped = droppedPanels+droppedCargo;
        DroppedCounterText.setText(String.valueOf(totalDropped));
        DroppedButton.setBackgroundColor(getResources().getColor(R.color.orange));

        PanelButton.setEnabled(true);
        PanelCounterText.setEnabled(true);
        CargoButton.setEnabled(true);
        CargoCounterText.setEnabled(true);
        DroppedButton.setEnabled(false);
        DroppedCounterText.setEnabled(false);
        MissedButton.setEnabled(false);
        MissedCounterText.setEnabled(false);
        disableScoringDiagram('A');
    }
    public void missedClick (View view) {
        UNDO = "Missed";
        UndoButton.setEnabled(true);

        selectedButtonColors(MissedButton);
        TimerTask changeToDefault = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        defaultButtonState(MissedButton);
                    }
                });
            }
        };
        timer.schedule(changeToDefault, 500);

        if (isPanel){
            missedPanels++;
            defaultButtonState(PanelButton);
        }
        if (isCargo){
            missedCargo++;
            defaultButtonState(CargoButton);
        }
        int totalMissed = missedPanels+missedCargo;

        MissedButton.setBackgroundColor(getResources().getColor(R.color.orange));
        MissedCounterText.setText(String.valueOf(totalMissed));

        PanelButton.setEnabled(true);
        PanelCounterText.setEnabled(true);
        CargoButton.setEnabled(true);
        CargoCounterText.setEnabled(true);
        DroppedButton.setEnabled(false);
        DroppedCounterText.setEnabled(false);
        MissedButton.setEnabled(false);
        MissedCounterText.setEnabled(false);
        disableScoringDiagram('A');
    }

    //left rocket onClicks
    public void LRPNT3CounterClick (View view) {
        LRPNT3Counter++;
        scoreHashMap.put(LRPNT3.getTag().toString(), String.valueOf(LRPNT3Counter));
        LeftRocketPanelNearT3.setColor(Color.rgb(248, 231, 28));
        UNDO = "LRPNT3";
        LRPNT3.setText(String.valueOf(LRPNT3Counter));
        LRPNT3.setTextColor(getResources().getColor(R.color.textdefault));
        disableScoringDiagram('A');
        defaultButtonState(PanelButton);
        defaultButtonState(CargoButton);
        PanelButton.setEnabled(true);
        PanelCounterText.setEnabled(true);
        CargoButton.setEnabled(true);
        CargoCounterText.setEnabled(true);
        DroppedButton.setEnabled(false);
        DroppedCounterText.setEnabled(false);
        MissedButton.setEnabled(false);
        MissedCounterText.setEnabled(false);
        UndoButton.setEnabled(true);
        isPanel = false;
        isCargo = false;
    }
    public void LRPNT2CounterClick (View view) {
        LRPNT2Counter++;
        LeftRocketPanelNearT2.setColor(Color.rgb(248, 231, 28));
        scoreHashMap.put(LRPNT2.getTag().toString(), String.valueOf(LRPNT2Counter));
        UNDO = "LRPNT2";
        LRPNT2.setText(String.valueOf(LRPNT2Counter));
        LRPNT2.setTextColor(getResources().getColor(R.color.textdefault));
        disableScoringDiagram('A');
        defaultButtonState(PanelButton);
        defaultButtonState(CargoButton);
        isPanel = false;
        isCargo = false;
        MissedButton.setEnabled(false);
        MissedCounterText.setEnabled(false);
        DroppedButton.setEnabled(false);
        DroppedCounterText.setEnabled(false);
        PanelButton.setEnabled(true);
        PanelCounterText.setEnabled(true);
        CargoButton.setEnabled(true);
        CargoCounterText.setEnabled(true);
        UndoButton.setEnabled(true);
        isPanel = false;
        isCargo = false;
    }
    public void LRPNT1CounterClick (View view) {
        LRPNT1Counter++;
        LeftRocketPanelNearT1.setColor(Color.rgb(248, 231, 28));
        scoreHashMap.put(LRPNT1.getTag().toString(), String.valueOf(LRPNT1Counter));
        UNDO = "LRPNT1";
        LRPNT1.setText(String.valueOf(LRPNT1Counter));
        LRPNT1.setTextColor(getResources().getColor(R.color.textdefault));
        disableScoringDiagram('A');
        defaultButtonState(PanelButton);
        defaultButtonState(CargoButton);
        MissedButton.setEnabled(false);
        MissedCounterText.setEnabled(false);
        DroppedButton.setEnabled(false);
        DroppedCounterText.setEnabled(false);
        PanelButton.setEnabled(true);
        PanelCounterText.setEnabled(true);
        CargoButton.setEnabled(true);
        CargoCounterText.setEnabled(true);
        UndoButton.setEnabled(true);
        isPanel = false;
        isCargo = false;
    }
    public void LRCT3CounterClick (View view) {
        LRCT3Counter++;
        LeftRocketCargoT3.setColor(Color.argb(100, 255, 152, 0));
        scoreHashMap.put(LRCT3.getTag().toString(), String.valueOf(LRCT3Counter));
        UNDO = "LRCT3";
        LRCT3.setText(String.valueOf(LRCT3Counter));
        disableScoringDiagram('A');
        defaultButtonState(PanelButton);
        defaultButtonState(CargoButton);
        MissedButton.setEnabled(false);
        MissedCounterText.setEnabled(false);
        DroppedButton.setEnabled(false);
        DroppedCounterText.setEnabled(false);
        PanelButton.setEnabled(true);
        PanelCounterText.setEnabled(true);
        CargoButton.setEnabled(true);
        CargoCounterText.setEnabled(true);
        UndoButton.setEnabled(true);
        isPanel = false;
        isCargo = false;
    }
    public void LRCT2CounterClick (View view) {
        LRCT2Counter++;
        LeftRocketCargoT2.setColor(Color.argb(100, 255, 152, 0));
        scoreHashMap.put(LRCT2.getTag().toString(), String.valueOf(LRCT2Counter));
        UNDO = "LRCT2";
        LRCT2.setText(String.valueOf(LRCT2Counter));
        disableScoringDiagram('A');
        defaultButtonState(PanelButton);
        defaultButtonState(CargoButton);
        MissedButton.setEnabled(false);
        MissedCounterText.setEnabled(false);
        DroppedButton.setEnabled(false);
        DroppedCounterText.setEnabled(false);
        PanelButton.setEnabled(true);
        PanelCounterText.setEnabled(true);
        CargoButton.setEnabled(true);
        CargoCounterText.setEnabled(true);
        UndoButton.setEnabled(true);
        isPanel = false;
        isCargo = false;
    }
    public void LRCT1CounterClick (View view) {
        LRCT1Counter++;
        LeftRocketCargoT1.setColor(Color.argb(100, 255, 152, 0));
        scoreHashMap.put(LRCT1.getTag().toString(), String.valueOf(LRCT1Counter));
        UNDO = "LRCT1";
        LRCT1.setText(String.valueOf(LRCT1Counter));
        disableScoringDiagram('A');
        defaultButtonState(PanelButton);
        defaultButtonState(CargoButton);
        MissedButton.setEnabled(false);
        MissedCounterText.setEnabled(false);
        DroppedButton.setEnabled(false);
        DroppedCounterText.setEnabled(false);
        PanelButton.setEnabled(true);
        PanelCounterText.setEnabled(true);
        CargoButton.setEnabled(true);
        CargoCounterText.setEnabled(true);
        UndoButton.setEnabled(true);
        isPanel = false;
        isCargo = false;
    }
    public void LRPFT3CounterClick (View view) {
        LRPFT3Counter++;
        LeftRocketPanelFarT3.setColor(Color.rgb(248, 231, 28));
        scoreHashMap.put(LRPFT3.getTag().toString(), String.valueOf(LRPFT3Counter));
        UNDO = "LRPFT3";
        LRPFT3.setText(String.valueOf(LRPFT3Counter));
        LRPFT3.setTextColor(getResources().getColor(R.color.textdefault));
        disableScoringDiagram('A');
        defaultButtonState(PanelButton);
        defaultButtonState(CargoButton);
        MissedButton.setEnabled(false);
        MissedCounterText.setEnabled(false);
        DroppedButton.setEnabled(false);
        DroppedCounterText.setEnabled(false);
        PanelButton.setEnabled(true);
        PanelCounterText.setEnabled(true);
        CargoButton.setEnabled(true);
        CargoCounterText.setEnabled(true);
        UndoButton.setEnabled(true);
        isPanel = false;
        isCargo = false;
    }
    public void LRPFT2CounterClick (View view) {
        LRPFT2Counter++;
        LeftRocketPanelFarT2.setColor(Color.rgb(248, 231, 28));
        scoreHashMap.put(LRPFT2.getTag().toString(), String.valueOf(LRPFT2Counter));
        UNDO = "LRPFT2";
        LRPFT2.setText(String.valueOf(LRPFT2Counter));
        LRPFT2.setTextColor(getResources().getColor(R.color.textdefault));
        disableScoringDiagram('A');
        defaultButtonState(PanelButton);
        defaultButtonState(CargoButton);
        MissedButton.setEnabled(false);
        MissedCounterText.setEnabled(false);
        DroppedButton.setEnabled(false);
        DroppedCounterText.setEnabled(false);
        PanelButton.setEnabled(true);
        PanelCounterText.setEnabled(true);
        CargoButton.setEnabled(true);
        CargoCounterText.setEnabled(true);
        UndoButton.setEnabled(true);
        isPanel = false;
        isCargo = false;
    }
    public void LRPFT1CounterClick (View view) {
        LRPFT1Counter++;
        LeftRocketPanelFarT1.setColor(Color.rgb(248, 231, 28));
        scoreHashMap.put(LRPFT1.getTag().toString(), String.valueOf(LRPFT1Counter));
        UNDO = "LRPFT1";
        LRPFT1.setText(String.valueOf(LRPFT1Counter));
        LRPFT1.setTextColor(getResources().getColor(R.color.textdefault));
        disableScoringDiagram('A');
        defaultButtonState(PanelButton);
        defaultButtonState(CargoButton);
        MissedButton.setEnabled(false);
        MissedCounterText.setEnabled(false);
        DroppedButton.setEnabled(false);
        DroppedCounterText.setEnabled(false);
        PanelButton.setEnabled(true);
        PanelCounterText.setEnabled(true);
        CargoButton.setEnabled(true);
        CargoCounterText.setEnabled(true);
        UndoButton.setEnabled(true);
        isPanel = false;
        isCargo = false;
    }

    //cargo ship onClicks
    public void CSPF1CounterClick (View view) {
        CSPF1Counter++;
        CargoShipPanelFront1.setColor(Color.rgb(248, 231, 28));
        scoreHashMap.put(CSPF1.getTag().toString(), String.valueOf(CSPF1Counter));
        UNDO = "CSPF1";
        CSPF1.setText(String.valueOf(CSPF1Counter));
        CSPF1.setTextColor(getResources().getColor(R.color.textdefault));
        disableScoringDiagram('A');
        defaultButtonState(PanelButton);
        MissedButton.setEnabled(false);
        MissedCounterText.setEnabled(false);
        DroppedButton.setEnabled(false);
        DroppedCounterText.setEnabled(false);
        UndoButton.setEnabled(true);
        defaultButtonState(CargoButton);
        PanelButton.setEnabled(true);
        PanelCounterText.setEnabled(true);
        CargoButton.setEnabled(true);
        CargoCounterText.setEnabled(true);
        isPanel = false;
        isCargo = false;
    }
    public void CSPF2CounterClick (View view) {
        CSPF2Counter++;
        CargoShipPanelFront2.setColor(Color.rgb(248, 231, 28));
        scoreHashMap.put(CSPF2.getTag().toString(), String.valueOf(CSPF2Counter));
        UNDO = "CSPF2";
        CSPF2.setText(String.valueOf(CSPF2Counter));
        CSPF2.setTextColor(getResources().getColor(R.color.textdefault));
        disableScoringDiagram('A');
        defaultButtonState(PanelButton);
        MissedButton.setEnabled(false);
        MissedCounterText.setEnabled(false);
        DroppedButton.setEnabled(false);
        DroppedCounterText.setEnabled(false);
        UndoButton.setEnabled(true);
        defaultButtonState(CargoButton);
        PanelButton.setEnabled(true);
        PanelCounterText.setEnabled(true);
        CargoButton.setEnabled(true);
        CargoCounterText.setEnabled(true);
        isPanel = false;
        isCargo = false;
    }
    public void CSCF1CounterClick (View view) {
        CSCF1Counter++;
        CargoShipCargoFront1.setColor(Color.argb(100, 255, 152, 0));
        scoreHashMap.put(CSCF1.getTag().toString(), String.valueOf(CSCF1Counter));
        UNDO = "CSCF1";
        CSCF1.setText(String.valueOf(CSCF1Counter));
        disableScoringDiagram('A');
        defaultButtonState(PanelButton);
        MissedButton.setEnabled(false);
        MissedCounterText.setEnabled(false);
        DroppedButton.setEnabled(false);
        DroppedCounterText.setEnabled(false);
        UndoButton.setEnabled(true);
        defaultButtonState(CargoButton);
        PanelButton.setEnabled(true);
        PanelCounterText.setEnabled(true);
        CargoButton.setEnabled(true);
        CargoCounterText.setEnabled(true);
        isPanel = false;
        isCargo = false;
    }
    public void CSCF2CounterClick (View view) {
        CSCF2Counter++;
        scoreHashMap.put(CSCF2.getTag().toString(), String.valueOf(CSCF2Counter));
        CargoShipCargoFront2.setColor(Color.argb(100, 255, 152, 0));
        UNDO = "CSCF2";
        CSCF2.setText(String.valueOf(CSCF2Counter));
        disableScoringDiagram('A');
        defaultButtonState(PanelButton);
        MissedButton.setEnabled(false);
        MissedCounterText.setEnabled(false);
        DroppedButton.setEnabled(false);
        DroppedCounterText.setEnabled(false);
        UndoButton.setEnabled(true);
        defaultButtonState(CargoButton);
        PanelButton.setEnabled(true);
        PanelCounterText.setEnabled(true);
        CargoButton.setEnabled(true);
        CargoCounterText.setEnabled(true);
        isPanel = false;
        isCargo = false;
    }
    public void CSPL1CounterClick (View view) {
        CSPL1Counter++;
        scoreHashMap.put(CSPL1.getTag().toString(), String.valueOf(CSPL1Counter));
        CargoShipPanelLeft1.setColor(Color.rgb(248, 231, 28));
        UNDO = "CSPL1";
        CSPL1.setText(String.valueOf(CSPL1Counter));
        CSPL1.setTextColor(getResources().getColor(R.color.textdefault));
        disableScoringDiagram('A');
        defaultButtonState(PanelButton);
        MissedButton.setEnabled(false);
        MissedCounterText.setEnabled(false);
        DroppedButton.setEnabled(false);
        DroppedCounterText.setEnabled(false);
        UndoButton.setEnabled(true);
        defaultButtonState(CargoButton);
        PanelButton.setEnabled(true);
        PanelCounterText.setEnabled(true);
        CargoButton.setEnabled(true);
        CargoCounterText.setEnabled(true);
        isPanel = false;
        isCargo = false;
    }
    public void CSPL2CounterClick (View view) {
        CSPL2Counter++;
        scoreHashMap.put(CSPL2.getTag().toString(), String.valueOf(CSPL2Counter));
        CargoShipPanelLeft2.setColor(Color.rgb(248, 231, 28));
        UNDO = "CSPL2";
        CSPL2.setText(String.valueOf(CSPL2Counter));
        CSPL2.setTextColor(getResources().getColor(R.color.textdefault));
        disableScoringDiagram('A');
        defaultButtonState(PanelButton);
        MissedButton.setEnabled(false);
        MissedCounterText.setEnabled(false);
        DroppedButton.setEnabled(false);
        DroppedCounterText.setEnabled(false);
        UndoButton.setEnabled(true);
        defaultButtonState(CargoButton);
        PanelButton.setEnabled(true);
        PanelCounterText.setEnabled(true);
        CargoButton.setEnabled(true);
        CargoCounterText.setEnabled(true);
        isPanel = false;
        isCargo = false;
    }
    public void CSPL3CounterClick (View view) {
        CSPL3Counter++;
        scoreHashMap.put(CSPL3.getTag().toString(), String.valueOf(CSPL3Counter));
        CargoShipPanelLeft3.setColor(Color.rgb(248, 231, 28));
        UNDO = "CSPL3";
        CSPL3.setText(String.valueOf(CSPL3Counter));
        CSPL3.setTextColor(getResources().getColor(R.color.textdefault));
        disableScoringDiagram('A');
        defaultButtonState(PanelButton);
        MissedButton.setEnabled(false);
        MissedCounterText.setEnabled(false);
        DroppedButton.setEnabled(false);
        DroppedCounterText.setEnabled(false);
        UndoButton.setEnabled(true);
        defaultButtonState(CargoButton);
        PanelButton.setEnabled(true);
        PanelCounterText.setEnabled(true);
        CargoButton.setEnabled(true);
        CargoCounterText.setEnabled(true);
        isPanel = false;
        isCargo = false;
    }
    public void CSCL1CounterClick (View view) {
        CSCL1Counter++;
        scoreHashMap.put(CSCL1.getTag().toString(), String.valueOf(CSCL1Counter));
        CargoShipCargoLeft1.setColor(Color.argb(100, 255, 152, 0));
        UNDO = "CSCL1";
        CSCL1.setText(String.valueOf(CSCL1Counter));
        disableScoringDiagram('A');
        defaultButtonState(PanelButton);
        MissedButton.setEnabled(false);
        MissedCounterText.setEnabled(false);
        DroppedButton.setEnabled(false);
        DroppedCounterText.setEnabled(false);
        UndoButton.setEnabled(true);
        defaultButtonState(CargoButton);
        PanelButton.setEnabled(true);
        PanelCounterText.setEnabled(true);
        CargoButton.setEnabled(true);
        CargoCounterText.setEnabled(true);
        isPanel = false;
        isCargo = false;
    }
    public void CSCL2CounterClick (View view) {
        CSCL2Counter++;
        scoreHashMap.put(CSCL2.getTag().toString(), String.valueOf(CSCL2Counter));
        CargoShipCargoLeft2.setColor(Color.argb(100, 255, 152, 0));
        UNDO = "CSCL2";
        CSCL2.setText(String.valueOf(CSCL2Counter));
        disableScoringDiagram('A');
        defaultButtonState(PanelButton);
        MissedButton.setEnabled(false);
        MissedCounterText.setEnabled(false);
        DroppedButton.setEnabled(false);
        DroppedCounterText.setEnabled(false);
        UndoButton.setEnabled(true);
        defaultButtonState(CargoButton);
        PanelButton.setEnabled(true);
        PanelCounterText.setEnabled(true);
        CargoButton.setEnabled(true);
        CargoCounterText.setEnabled(true);
        isPanel = false;
        isCargo = false;
    }
    public void CSCL3CounterClick (View view) {
        CSCL3Counter++;
        scoreHashMap.put(CSCL3.getTag().toString(), String.valueOf(CSCL3Counter));
        CargoShipCargoLeft3.setColor(Color.argb(100, 255, 152, 0));
        UNDO = "CSCL3";
        CSCL3.setText(String.valueOf(CSCL3Counter));
        disableScoringDiagram('A');
        defaultButtonState(PanelButton);
        MissedButton.setEnabled(false);
        MissedCounterText.setEnabled(false);
        DroppedButton.setEnabled(false);
        DroppedCounterText.setEnabled(false);
        UndoButton.setEnabled(true);
        defaultButtonState(CargoButton);
        PanelButton.setEnabled(true);
        PanelCounterText.setEnabled(true);
        CargoButton.setEnabled(true);
        CargoCounterText.setEnabled(true);
        isPanel = false;
        isCargo = false;
    }
    public void CSCR1CounterClick (View view) {
        CSCR1Counter++;
        scoreHashMap.put(CSCR1.getTag().toString(), String.valueOf(CSCR1Counter));
        CargoShipCargoRight1.setColor(Color.argb(100, 255, 152, 0));
        UNDO = "CSCR1";
        CSCR1.setText(String.valueOf(CSCR1Counter));
        disableScoringDiagram('A');
        defaultButtonState(PanelButton);
        MissedButton.setEnabled(false);
        MissedCounterText.setEnabled(false);
        DroppedButton.setEnabled(false);
        DroppedCounterText.setEnabled(false);
        UndoButton.setEnabled(true);
        defaultButtonState(CargoButton);
        PanelButton.setEnabled(true);
        PanelCounterText.setEnabled(true);
        CargoButton.setEnabled(true);
        CargoCounterText.setEnabled(true);
        isPanel = false;
        isCargo = false;
    }
    public void CSCR2CounterClick (View view) {
        CSCR2Counter++;
        scoreHashMap.put(CSCR2.getTag().toString(), String.valueOf(CSCR2Counter));
        CargoShipCargoRight2.setColor(Color.argb(100, 255, 152, 0));
        UNDO = "CSCR2";
        CSCR2.setText(String.valueOf(CSCR2Counter));
        disableScoringDiagram('A');
        defaultButtonState(PanelButton);
        MissedButton.setEnabled(false);
        MissedCounterText.setEnabled(false);
        DroppedButton.setEnabled(false);
        DroppedCounterText.setEnabled(false);
        UndoButton.setEnabled(true);
        defaultButtonState(CargoButton);
        PanelButton.setEnabled(true);
        PanelCounterText.setEnabled(true);
        CargoButton.setEnabled(true);
        CargoCounterText.setEnabled(true);
        isPanel = false;
        isCargo = false;
    }
    public void CSCR3CounterClick (View view) {
        CSCR3Counter++;
        scoreHashMap.put(CSCR3.getTag().toString(), String.valueOf(CSCR3Counter));
        CargoShipCargoRight3.setColor(Color.argb(100, 255, 152, 0));
        UNDO = "CSCR3";
        CSCR3.setText(String.valueOf(CSCR3Counter));
        disableScoringDiagram('A');
        defaultButtonState(PanelButton);
        MissedButton.setEnabled(false);
        MissedCounterText.setEnabled(false);
        DroppedButton.setEnabled(false);
        DroppedCounterText.setEnabled(false);
        UndoButton.setEnabled(true);
        defaultButtonState(CargoButton);
        PanelButton.setEnabled(true);
        PanelCounterText.setEnabled(true);
        CargoButton.setEnabled(true);
        CargoCounterText.setEnabled(true);
        isPanel = false;
        isCargo = false;
    }
    public void CSPR1CounterClick (View view) {
        CSPR1Counter++;
        scoreHashMap.put(CSPR1.getTag().toString(), String.valueOf(CSPR1Counter));
        CargoShipPanelRight1.setColor(Color.rgb(248, 231, 28));
        UNDO = "CSPR1";
        CSPR1.setText(String.valueOf(CSPR1Counter));
        CSPR1.setTextColor(getResources().getColor(R.color.textdefault));
        disableScoringDiagram('A');
        defaultButtonState(PanelButton);
        MissedButton.setEnabled(false);
        MissedCounterText.setEnabled(false);
        DroppedButton.setEnabled(false);
        DroppedCounterText.setEnabled(false);
        UndoButton.setEnabled(true);
        defaultButtonState(CargoButton);
        PanelButton.setEnabled(true);
        PanelCounterText.setEnabled(true);
        CargoButton.setEnabled(true);
        CargoCounterText.setEnabled(true);
        isPanel = false;
        isCargo = false;
    }
    public void CSPR2CounterClick (View view) {
        CSPR2Counter++;
        scoreHashMap.put(CSPR2.getTag().toString(), String.valueOf(CSPR2Counter));
        CargoShipPanelRight2.setColor(Color.rgb(248, 231, 28));
        UNDO = "CSPR2";
        CSPR2.setText(String.valueOf(CSPR2Counter));
        CSPR2.setTextColor(getResources().getColor(R.color.textdefault));
        disableScoringDiagram('A');
        defaultButtonState(PanelButton);
        MissedButton.setEnabled(false);
        MissedCounterText.setEnabled(false);
        DroppedButton.setEnabled(false);
        DroppedCounterText.setEnabled(false);
        UndoButton.setEnabled(true);
        defaultButtonState(CargoButton);
        PanelButton.setEnabled(true);
        PanelCounterText.setEnabled(true);
        CargoButton.setEnabled(true);
        CargoCounterText.setEnabled(true);
        isPanel = false;
        isCargo = false;
    }
    public void CSPR3CounterClick (View view) {
        CSPR3Counter++;
        scoreHashMap.put(CSPR3.getTag().toString(), String.valueOf(CSPR3Counter));
        CargoShipPanelRight3.setColor(Color.rgb(248, 231, 28));
        UNDO = "CSPR3";
        CSPR3.setText(String.valueOf(CSPR3Counter));
        CSPR3.setTextColor(getResources().getColor(R.color.textdefault));
        disableScoringDiagram('A');
        defaultButtonState(PanelButton);
        MissedButton.setEnabled(false);
        MissedCounterText.setEnabled(false);
        DroppedButton.setEnabled(false);
        DroppedCounterText.setEnabled(false);
        UndoButton.setEnabled(true);
        defaultButtonState(CargoButton);
        PanelButton.setEnabled(true);
        PanelCounterText.setEnabled(true);
        CargoButton.setEnabled(true);
        CargoCounterText.setEnabled(true);
        isPanel = false;
        isCargo = false;
    }

    //right rocket onClicks
    public void RRPNT3CounterClick (View view) {
        RRPNT3Counter++;
        scoreHashMap.put(RRPNT3.getTag().toString(), String.valueOf(RRPNT3Counter));
        RightRocketPanelNearT3.setColor(Color.rgb(248, 231, 28));
        UNDO = "RRPNT3";
        RRPNT3.setText(String.valueOf(RRPNT3Counter));
        RRPNT3.setTextColor(getResources().getColor(R.color.textdefault));
        disableScoringDiagram('A');
        defaultButtonState(PanelButton);
        UndoButton.setEnabled(true);
        defaultButtonState(CargoButton);
        MissedButton.setEnabled(false);
        MissedCounterText.setEnabled(false);
        DroppedButton.setEnabled(false);
        DroppedCounterText.setEnabled(false);
        PanelButton.setEnabled(true);
        PanelCounterText.setEnabled(true);
        CargoButton.setEnabled(true);
        CargoCounterText.setEnabled(true);
        isPanel = false;
        isCargo = false;
    }
    public void RRPNT2CounterClick (View view) {
        RRPNT2Counter++;
        scoreHashMap.put(RRPNT2.getTag().toString(), String.valueOf(RRPNT2Counter));
        RightRocketPanelNearT2.setColor(Color.rgb(248, 231, 28));
        UNDO = "RRPNT2";
        RRPNT2.setText(String.valueOf(RRPNT2Counter));
        RRPNT2.setTextColor(getResources().getColor(R.color.textdefault));
        disableScoringDiagram('A');
        defaultButtonState(PanelButton);
        UndoButton.setEnabled(true);
        defaultButtonState(CargoButton);
        MissedButton.setEnabled(false);
        MissedCounterText.setEnabled(false);
        DroppedButton.setEnabled(false);
        DroppedCounterText.setEnabled(false);
        PanelButton.setEnabled(true);
        PanelCounterText.setEnabled(true);
        CargoButton.setEnabled(true);
        CargoCounterText.setEnabled(true);
        isPanel = false;
        isCargo = false;
    }
    public void RRPNT1CounterClick (View view) {
        RRPNT1Counter++;
        scoreHashMap.put(RRPNT1.getTag().toString(), String.valueOf(RRPNT1Counter));
        RightRocketPanelNearT1.setColor(Color.rgb(248, 231, 28));
        UNDO = "RRPNT1";
        RRPNT1.setText(String.valueOf(RRPNT1Counter));
        RRPNT1.setTextColor(getResources().getColor(R.color.textdefault));
        disableScoringDiagram('A');
        defaultButtonState(PanelButton);
        UndoButton.setEnabled(true);
        defaultButtonState(CargoButton);
        MissedButton.setEnabled(false);
        MissedCounterText.setEnabled(false);
        DroppedButton.setEnabled(false);
        DroppedCounterText.setEnabled(false);
        PanelButton.setEnabled(true);
        PanelCounterText.setEnabled(true);
        CargoButton.setEnabled(true);
        CargoCounterText.setEnabled(true);
        isPanel = false;
        isCargo = false;
    }
    public void RRCT3CounterClick (View view) {
        RRCT3Counter++;
        scoreHashMap.put(RRCT3.getTag().toString(), String.valueOf(RRCT3Counter));
        RightRocketCargoT3.setColor(Color.argb(100, 255, 152, 0));
        UNDO = "RRCT3";
        RRCT3.setText(String.valueOf(RRCT3Counter));
        disableScoringDiagram('A');
        defaultButtonState(PanelButton);
        UndoButton.setEnabled(true);
        defaultButtonState(CargoButton);
        MissedButton.setEnabled(false);
        MissedCounterText.setEnabled(false);
        DroppedButton.setEnabled(false);
        DroppedCounterText.setEnabled(false);
        PanelButton.setEnabled(true);
        PanelCounterText.setEnabled(true);
        CargoButton.setEnabled(true);
        CargoCounterText.setEnabled(true);
        isPanel = false;
        isCargo = false;
    }
    public void RRCT2CounterClick (View view) {
        RRCT2Counter++;
        scoreHashMap.put(RRCT2.getTag().toString(), String.valueOf(RRCT2Counter));
        RightRocketCargoT2.setColor(Color.argb(100, 255, 152, 0));
        UNDO = "RRCT2";
        RRCT2.setText(String.valueOf(RRCT2Counter));
        disableScoringDiagram('A');
        defaultButtonState(PanelButton);
        UndoButton.setEnabled(true);
        defaultButtonState(CargoButton);
        MissedButton.setEnabled(false);
        MissedCounterText.setEnabled(false);
        DroppedButton.setEnabled(false);
        DroppedCounterText.setEnabled(false);
        PanelButton.setEnabled(true);
        PanelCounterText.setEnabled(true);
        CargoButton.setEnabled(true);
        CargoCounterText.setEnabled(true);
        isPanel = false;
        isCargo = false;
    }
    public void RRCT1CounterClick (View view) {
        RRCT1Counter++;
        scoreHashMap.put(RRCT1.getTag().toString(), String.valueOf(RRCT1Counter));
        RightRocketCargoT1.setColor(Color.argb(100, 255, 152, 0));
        UNDO = "RRCT1";
        RRCT1.setText(String.valueOf(RRCT1Counter));
        disableScoringDiagram('A');
        defaultButtonState(PanelButton);
        UndoButton.setEnabled(true);
        defaultButtonState(CargoButton);
        MissedButton.setEnabled(false);
        MissedCounterText.setEnabled(false);
        DroppedButton.setEnabled(false);
        DroppedCounterText.setEnabled(false);
        PanelButton.setEnabled(true);
        PanelCounterText.setEnabled(true);
        CargoButton.setEnabled(true);
        CargoCounterText.setEnabled(true);
        isPanel = false;
        isCargo = false;
    }
    public void RRPFT3CounterClick (View view) {
        RRPFT3Counter++;
        scoreHashMap.put(RRPFT3.getTag().toString(), String.valueOf(RRPFT3Counter));
        RightRocketPanelFarT3.setColor(Color.rgb(248, 231, 28));
        UNDO = "RRPFT3";
        RRPFT3.setText(String.valueOf(RRPFT3Counter));
        RRPFT3.setTextColor(getResources().getColor(R.color.textdefault));
        disableScoringDiagram('A');
        defaultButtonState(PanelButton);
        UndoButton.setEnabled(true);
        defaultButtonState(CargoButton);
        MissedButton.setEnabled(false);
        MissedCounterText.setEnabled(false);
        DroppedButton.setEnabled(false);
        DroppedCounterText.setEnabled(false);
        PanelButton.setEnabled(true);
        PanelCounterText.setEnabled(true);
        CargoButton.setEnabled(true);
        CargoCounterText.setEnabled(true);
        isPanel = false;
        isCargo = false;
    }
    public void RRPFT2CounterClick (View view) {
        RRPFT2Counter++;
        scoreHashMap.put(RRPFT2.getTag().toString(), String.valueOf(RRPFT2Counter));
        RightRocketPanelFarT2.setColor(Color.rgb(248, 231, 28));
        UNDO = "RRPFT2";
        RRPFT2.setText(String.valueOf(RRPFT2Counter));
        RRPFT2.setTextColor(getResources().getColor(R.color.textdefault));
        disableScoringDiagram('A');
        defaultButtonState(PanelButton);
        UndoButton.setEnabled(true);
        defaultButtonState(CargoButton);
        MissedButton.setEnabled(false);
        MissedCounterText.setEnabled(false);
        DroppedButton.setEnabled(false);
        DroppedCounterText.setEnabled(false);
        PanelButton.setEnabled(true);
        PanelCounterText.setEnabled(true);
        CargoButton.setEnabled(true);
        CargoCounterText.setEnabled(true);
        isPanel = false;
        isCargo = false;
    }
    public void RRPFT1CounterClick (View view) {
        RRPFT1Counter++;
        scoreHashMap.put(RRPFT1.getTag().toString(), String.valueOf(RRPFT1Counter));
        RightRocketPanelFarT1.setColor(Color.rgb(248, 231, 28));
        UNDO = "RRPFT1";
        RRPFT1.setText(String.valueOf(RRPFT1Counter));
        RRPFT1.setTextColor(getResources().getColor(R.color.textdefault));
        disableScoringDiagram('A');
        defaultButtonState(PanelButton);
        UndoButton.setEnabled(true);
        defaultButtonState(CargoButton);
        MissedButton.setEnabled(false);
        MissedCounterText.setEnabled(false);
        DroppedButton.setEnabled(false);
        DroppedCounterText.setEnabled(false);
        PanelButton.setEnabled(true);
        PanelCounterText.setEnabled(true);
        CargoButton.setEnabled(true);
        CargoCounterText.setEnabled(true);
        isPanel = false;
        isCargo = false;
    }

    //undo button
    public void UndoClick (View view) {
        UndoButton.setEnabled(false);
        switch (UNDO) {
            case "Panel":
                defaultButtonState(PanelButton);
                defaultButtonState(CargoButton);
                totalPanels--;
                PanelCounterText.setText(String.valueOf(totalPanels));
                PanelButton.setEnabled(true);
                PanelCounterText.setEnabled(true);
                CargoButton.setEnabled(true);
                CargoCounterText.setEnabled(true);
                DroppedButton.setEnabled(false);
                DroppedCounterText.setEnabled(false);
                MissedButton.setEnabled(false);
                MissedCounterText.setEnabled(false);
                isPanel = false;
                isCargo = false;
                disableScoringDiagram('A');
                break;
            case "Cargo":
                defaultButtonState(CargoButton);
                defaultButtonState(PanelButton);
                totalCargo--;
                CargoCounterText.setText(String.valueOf(totalCargo));
                PanelButton.setEnabled(true);
                PanelCounterText.setEnabled(true);
                CargoButton.setEnabled(true);
                CargoCounterText.setEnabled(true);
                DroppedButton.setEnabled(false);
                DroppedCounterText.setEnabled(false);
                MissedButton.setEnabled(false);
                MissedCounterText.setEnabled(false);
                isPanel = false;
                isCargo = false;
                disableScoringDiagram('A');
                break;
            case "Dropped":
                if (isPanel) {
                    droppedPanels--;
                    selectedButtonColors(PanelButton);
                    enableScoringDiagram('P');
                }
                if (isCargo) {
                    droppedCargo--;
                    selectedButtonColors(CargoButton);
                    enableScoringDiagram('C');
                }
                DroppedCounterText.setText(String.valueOf(droppedPanels + droppedCargo));

                PanelButton.setEnabled(false);
                PanelCounterText.setEnabled(false);
                CargoButton.setEnabled(false);
                CargoCounterText.setEnabled(false);
                DroppedButton.setEnabled(true);
                DroppedCounterText.setEnabled(true);
                MissedButton.setEnabled(true);
                MissedCounterText.setEnabled(true);
                break;
            case "Missed":
                if (isPanel) {
                    missedPanels--;
                    selectedButtonColors(PanelButton);
                    enableScoringDiagram('P');
                }
                if (isCargo) {
                    missedCargo--;
                    selectedButtonColors(CargoButton);
                    enableScoringDiagram('C');
                }

                MissedCounterText.setText(String.valueOf(missedPanels + missedCargo));

                PanelButton.setEnabled(false);
                PanelCounterText.setEnabled(false);
                CargoButton.setEnabled(false);
                CargoCounterText.setEnabled(false);
                DroppedButton.setEnabled(true);
                DroppedCounterText.setEnabled(true);
                MissedButton.setEnabled(true);
                MissedCounterText.setEnabled(true);
                break;
            case "FellOver":
                setupHashMap.put("FellOver",String.valueOf(0));
                HABLineSwitch.setEnabled(true);
                PanelButton.setEnabled(true);
                PanelCounterText.setEnabled(true);
                CargoButton.setEnabled(true);
                CargoCounterText.setEnabled(true);
                DroppedButton.setEnabled(false);
                DroppedCounterText.setEnabled(false);
                MissedButton.setEnabled(false);
                MissedCounterText.setEnabled(false);
                setTextToColor(possessionTitle, "white");
                setTextToColor(panelOrCargoDirections, "white");
                setTextToColor(droppedDirection, "white");
                setTextToColor(scoringTitle, "white");
                setTextToColor(pOrCDirections, "white");
                setTextToColor(missedDirections, "white");
                FellOverSwitch.setChecked(!FellOverSwitch.isChecked());
                break;
            case "HAB":
                setupHashMap.put("HABLine",String.valueOf(0));
                HABLineSwitch.setChecked(!HABLineSwitch.isChecked());
                break;
            case "LRPNT3": //undo for circle buttons aka locations
                selectedButtonColors(PanelButton);
                defaultButtonState(CargoButton);
                PanelButton.setEnabled(false);
                PanelCounterText.setEnabled(false);
                CargoButton.setEnabled(false);
                CargoCounterText.setEnabled(false);
                DroppedButton.setEnabled(true);
                DroppedCounterText.setEnabled(true);
                MissedButton.setEnabled(true);
                MissedCounterText.setEnabled(true);
                enableScoringDiagram('P');
                LRPNT3Counter--;
                LRPNT3.setText(String.valueOf(LRPNT3Counter));
                if (LRPNT3Counter == 0) {
                    LeftRocketPanelNearT3.setColor(Color.rgb(255, 255, 217));
                    LRPNT3.setTextColor(getResources().getColor(R.color.textdefault));
                }
                break;
            case "LRPNT2":
                selectedButtonColors(PanelButton);
                defaultButtonState(CargoButton);

                PanelButton.setEnabled(false);
                PanelCounterText.setEnabled(false);
                CargoButton.setEnabled(false);
                CargoCounterText.setEnabled(false);
                DroppedButton.setEnabled(true);
                DroppedCounterText.setEnabled(true);
                MissedButton.setEnabled(true);
                MissedCounterText.setEnabled(true);
                LRPNT2Counter--;
                LRPNT2.setText(String.valueOf(LRPNT2Counter));
                enableScoringDiagram('P');
                if (LRPNT2Counter == 0) {
                    LeftRocketPanelNearT2.setColor(Color.rgb(255, 255, 217));
                    LRPNT2.setTextColor(getResources().getColor(R.color.textdefault));
                }
                break;
            case "LRPNT1":
                selectedButtonColors(PanelButton);
                defaultButtonState(CargoButton);

                PanelButton.setEnabled(false);
                PanelCounterText.setEnabled(false);
                CargoButton.setEnabled(false);
                CargoCounterText.setEnabled(false);
                DroppedButton.setEnabled(true);
                DroppedCounterText.setEnabled(true);
                MissedButton.setEnabled(true);
                MissedCounterText.setEnabled(true);
                LRPNT1Counter--;
                LRPNT1.setText(String.valueOf(LRPNT1Counter));
                enableScoringDiagram('P');

                if (LRPNT1Counter == 0) {
                    LeftRocketPanelNearT1.setColor(Color.rgb(255, 255, 217));
                    LRPNT1.setTextColor(getResources().getColor(R.color.textdefault));
                }
                break;
            case "LRCT3":
                selectedButtonColors(CargoButton);
                defaultButtonState(PanelButton);

                PanelButton.setEnabled(false);
                PanelCounterText.setEnabled(false);
                CargoButton.setEnabled(false);
                CargoCounterText.setEnabled(false);
                DroppedButton.setEnabled(true);
                DroppedCounterText.setEnabled(true);
                MissedButton.setEnabled(true);
                MissedCounterText.setEnabled(true);
                LRCT3Counter--;
                LRCT3.setText(String.valueOf(LRCT3Counter));
                enableScoringDiagram('C');

                if (LRCT3Counter == 0) {
                    LeftRocketCargoT3.setColor(Color.rgb(221, 172, 107));
                    LRCT3.setTextColor(getResources().getColor(R.color.defaultdisabled));
                }
                break;
            case "LRCT2":
                selectedButtonColors(CargoButton);
                defaultButtonState(PanelButton);

                PanelButton.setEnabled(false);
                PanelCounterText.setEnabled(false);
                CargoButton.setEnabled(false);
                CargoCounterText.setEnabled(false);
                DroppedButton.setEnabled(true);
                DroppedCounterText.setEnabled(true);
                MissedButton.setEnabled(true);
                MissedCounterText.setEnabled(true);
                LRCT2Counter--;
                LRCT2.setText(String.valueOf(LRCT2Counter));
                enableScoringDiagram('C');

                if (LRCT2Counter == 0) {
                    LeftRocketCargoT2.setColor(Color.rgb(221, 172, 107));
                    LRCT2.setTextColor(getResources().getColor(R.color.defaultdisabled));
                }
                break;
            case "LRCT1":
                selectedButtonColors(CargoButton);
                defaultButtonState(PanelButton);

                PanelButton.setEnabled(false);
                PanelCounterText.setEnabled(false);
                CargoButton.setEnabled(false);
                CargoCounterText.setEnabled(false);
                DroppedButton.setEnabled(true);
                DroppedCounterText.setEnabled(true);
                MissedButton.setEnabled(true);
                MissedCounterText.setEnabled(true);
                LRCT1Counter--;
                LRCT1.setText(String.valueOf(LRCT1Counter));
                enableScoringDiagram('C');

                if (LRCT1Counter == 0) {
                    LeftRocketCargoT1.setColor(Color.rgb(221, 172, 107));
                    LRCT1.setTextColor(getResources().getColor(R.color.defaultdisabled));
                }
                break;
            case "LRPFT3":
                selectedButtonColors(PanelButton);
                defaultButtonState(CargoButton);

                PanelButton.setEnabled(false);
                PanelCounterText.setEnabled(false);
                CargoButton.setEnabled(false);
                CargoCounterText.setEnabled(false);
                DroppedButton.setEnabled(true);
                DroppedCounterText.setEnabled(true);
                MissedButton.setEnabled(true);
                MissedCounterText.setEnabled(true);
                LRPFT3Counter--;
                LRPFT3.setText(String.valueOf(LRPFT3Counter));
                enableScoringDiagram('P');

                if (LRPFT3Counter == 0) {
                    LeftRocketPanelFarT3.setColor(Color.rgb(255, 255, 217));
                    LRPFT3.setTextColor(getResources().getColor(R.color.textdefault));
                }
                break;
            case "LRPFT2":
                selectedButtonColors(PanelButton);
                defaultButtonState(CargoButton);

                PanelButton.setEnabled(false);
                PanelCounterText.setEnabled(false);
                CargoButton.setEnabled(false);
                CargoCounterText.setEnabled(false);
                DroppedButton.setEnabled(true);
                DroppedCounterText.setEnabled(true);
                MissedButton.setEnabled(true);
                MissedCounterText.setEnabled(true);
                LRPFT2Counter--;
                LRPFT2.setText(String.valueOf(LRPFT2Counter));
                enableScoringDiagram('P');

                if (LRPFT2Counter == 0) {
                    LeftRocketPanelFarT2.setColor(Color.rgb(255, 255, 217));
                    LRPFT2.setTextColor(getResources().getColor(R.color.textdefault));
                }
                break;
            case "LRPFT1":
                selectedButtonColors(PanelButton);
                defaultButtonState(CargoButton);

                PanelButton.setEnabled(false);
                PanelCounterText.setEnabled(false);
                CargoButton.setEnabled(false);
                CargoCounterText.setEnabled(false);
                DroppedButton.setEnabled(true);
                DroppedCounterText.setEnabled(true);
                MissedButton.setEnabled(true);
                MissedCounterText.setEnabled(true);
                LRPFT1Counter--;
                LRPFT1.setText(String.valueOf(LRPFT1Counter));
                enableScoringDiagram('P');

                if (LRPFT1Counter == 0) {
                    LeftRocketPanelFarT1.setColor(Color.rgb(255, 255, 217));
                    LRPFT1.setTextColor(getResources().getColor(R.color.textdefault));
                }
                break;
            case "CSPF1":
                selectedButtonColors(PanelButton);
                defaultButtonState(CargoButton);

                PanelButton.setEnabled(false);
                PanelCounterText.setEnabled(false);
                CargoButton.setEnabled(false);
                CargoCounterText.setEnabled(false);
                DroppedButton.setEnabled(true);
                DroppedCounterText.setEnabled(true);
                MissedButton.setEnabled(true);
                MissedCounterText.setEnabled(true);
                CSPF1Counter--;
                CSPF1.setText(String.valueOf(CSPF1Counter));
                enableScoringDiagram('P');

                if (CSPF1Counter == 0) {
                    CargoShipPanelFront1.setColor(Color.rgb(255, 255, 217));
                    CSPF1.setTextColor(getResources().getColor(R.color.textdefault));
                }
                break;
            case "CSPF2":
                selectedButtonColors(PanelButton);
                defaultButtonState(CargoButton);

                PanelButton.setEnabled(false);
                PanelCounterText.setEnabled(false);
                CargoButton.setEnabled(false);
                CargoCounterText.setEnabled(false);
                DroppedButton.setEnabled(true);
                DroppedCounterText.setEnabled(true);
                MissedButton.setEnabled(true);
                MissedCounterText.setEnabled(true);
                CSPF2Counter--;
                CSPF2.setText(String.valueOf(CSPF2Counter));
                enableScoringDiagram('P');

                if (CSPF2Counter == 0) {
                    CargoShipPanelFront2.setColor(Color.rgb(255, 255, 217));
                    CSPF2.setTextColor(getResources().getColor(R.color.textdefault));
                }
                break;
            case "CSCF1":
                selectedButtonColors(CargoButton);
                defaultButtonState(PanelButton);

                PanelButton.setEnabled(false);
                PanelCounterText.setEnabled(false);
                CargoButton.setEnabled(false);
                CargoCounterText.setEnabled(false);
                DroppedButton.setEnabled(true);
                DroppedCounterText.setEnabled(true);
                MissedButton.setEnabled(true);
                MissedCounterText.setEnabled(true);
                CSCF1Counter--;
                CSCF1.setText(String.valueOf(CSCF1Counter));
                enableScoringDiagram('C');

                if (CSCF1Counter == 0) {
                    CargoShipCargoFront1.setColor(Color.rgb(221, 172, 107));
                    CSCF1.setTextColor(getResources().getColor(R.color.textdefault));
                }
                break;
            case "CSCF2":
                selectedButtonColors(CargoButton);
                defaultButtonState(PanelButton);

                PanelButton.setEnabled(false);
                PanelCounterText.setEnabled(false);
                CargoButton.setEnabled(false);
                CargoCounterText.setEnabled(false);
                DroppedButton.setEnabled(true);
                DroppedCounterText.setEnabled(true);
                MissedButton.setEnabled(true);
                MissedCounterText.setEnabled(true);
                CSCF2Counter--;
                CSCF2.setText(String.valueOf(CSCF2Counter));
                enableScoringDiagram('C');

                if (CSCF2Counter == 0) {
                    CargoShipCargoFront2.setColor(Color.rgb(221, 172, 107));
                    CSCF2.setTextColor(getResources().getColor(R.color.textdefault));
                }
                break;
            case "CSPL1":
                selectedButtonColors(PanelButton);
                defaultButtonState(CargoButton);

                PanelButton.setEnabled(false);
                PanelCounterText.setEnabled(false);
                CargoButton.setEnabled(false);
                CargoCounterText.setEnabled(false);
                DroppedButton.setEnabled(true);
                DroppedCounterText.setEnabled(true);
                MissedButton.setEnabled(true);
                MissedCounterText.setEnabled(true);
                CSPL1Counter--;
                CSPL1.setText(String.valueOf(CSPL1Counter));
                enableScoringDiagram('P');

                if (CSPL1Counter == 0) {
                    CargoShipPanelLeft1.setColor(Color.rgb(255, 255, 217));
                    CSPL1.setTextColor(getResources().getColor(R.color.textdefault));
                }
                break;
            case "CSPL2":
                selectedButtonColors(PanelButton);
                defaultButtonState(CargoButton);

                PanelButton.setEnabled(false);
                PanelCounterText.setEnabled(false);
                CargoButton.setEnabled(false);
                CargoCounterText.setEnabled(false);
                DroppedButton.setEnabled(true);
                DroppedCounterText.setEnabled(true);
                MissedButton.setEnabled(true);
                MissedCounterText.setEnabled(true);
                CSPL2Counter--;
                CSPL2.setText(String.valueOf(CSPL2Counter));
                enableScoringDiagram('P');

                if (CSPL2Counter == 0) {
                    CargoShipPanelLeft2.setColor(Color.rgb(255, 255, 217));
                    CSPL2.setTextColor(getResources().getColor(R.color.textdefault));
                }
                break;
            case "CSPL3":
                selectedButtonColors(PanelButton);
                defaultButtonState(CargoButton);

                PanelButton.setEnabled(false);
                PanelCounterText.setEnabled(false);
                CargoButton.setEnabled(false);
                CargoCounterText.setEnabled(false);
                DroppedButton.setEnabled(true);
                DroppedCounterText.setEnabled(true);
                MissedButton.setEnabled(true);
                MissedCounterText.setEnabled(true);
                CSPL3Counter--;
                CSPL3.setText(String.valueOf(CSPL3Counter));
                enableScoringDiagram('P');

                if (CSPL3Counter == 0) {
                    CargoShipPanelLeft3.setColor(Color.rgb(255, 255, 217));
                    CSPL3.setTextColor(getResources().getColor(R.color.textdefault));
                }
                break;
            case "CSCL1":
                selectedButtonColors(CargoButton);
                defaultButtonState(PanelButton);

                PanelButton.setEnabled(false);
                PanelCounterText.setEnabled(false);
                CargoButton.setEnabled(false);
                CargoCounterText.setEnabled(false);
                DroppedButton.setEnabled(true);
                DroppedCounterText.setEnabled(true);
                MissedButton.setEnabled(true);
                MissedCounterText.setEnabled(true);
                CSCL1Counter--;
                CSCL1.setText(String.valueOf(CSCL1Counter));
                enableScoringDiagram('C');

                if (CSCL1Counter == 0) {
                    CargoShipCargoLeft1.setColor(Color.rgb(221, 172, 107));
                    CSCL1.setTextColor(getResources().getColor(R.color.defaultdisabled));
                }
                break;
            case "CSCL2":
                selectedButtonColors(CargoButton);
                defaultButtonState(PanelButton);

                PanelButton.setEnabled(false);
                PanelCounterText.setEnabled(false);
                CargoButton.setEnabled(false);
                CargoCounterText.setEnabled(false);
                DroppedButton.setEnabled(true);
                DroppedCounterText.setEnabled(true);
                MissedButton.setEnabled(true);
                MissedCounterText.setEnabled(true);
                CSCL2Counter--;
                CSCL2.setText(String.valueOf(CSCL2Counter));
                enableScoringDiagram('C');

                if (CSCL2Counter == 0) {
                    CargoShipCargoLeft2.setColor(Color.rgb(221, 172, 107));
                    CSCL2.setTextColor(getResources().getColor(R.color.defaultdisabled));
                }
                break;
            case "CSCL3":
                selectedButtonColors(CargoButton);
                defaultButtonState(PanelButton);

                PanelButton.setEnabled(false);
                PanelCounterText.setEnabled(false);
                CargoButton.setEnabled(false);
                CargoCounterText.setEnabled(false);
                DroppedButton.setEnabled(true);
                DroppedCounterText.setEnabled(true);
                MissedButton.setEnabled(true);
                MissedCounterText.setEnabled(true);
                CSCL3Counter--;
                CSCL3.setText(String.valueOf(CSCL3Counter));
                enableScoringDiagram('C');

                if (CSCL3Counter == 0) {
                    CargoShipCargoLeft3.setColor(Color.rgb(221, 172, 107));
                    CSCL3.setTextColor(getResources().getColor(R.color.defaultdisabled));
                }
                break;
            case "CSCR1":
                selectedButtonColors(CargoButton);
                defaultButtonState(PanelButton);

                PanelButton.setEnabled(false);
                PanelCounterText.setEnabled(false);
                CargoButton.setEnabled(false);
                CargoCounterText.setEnabled(false);
                DroppedButton.setEnabled(true);
                DroppedCounterText.setEnabled(true);
                MissedButton.setEnabled(true);
                MissedCounterText.setEnabled(true);
                CSCR1Counter--;
                CSCR1.setText(String.valueOf(CSCR1Counter));
                enableScoringDiagram('C');

                if (CSCR1Counter == 0) {
                    CargoShipCargoRight1.setColor(Color.rgb(221, 172, 107));
                    CSCR1.setTextColor(getResources().getColor(R.color.defaultdisabled));
                }
                break;
            case "CSCR2":
                selectedButtonColors(CargoButton);
                defaultButtonState(PanelButton);

                PanelButton.setEnabled(false);
                PanelCounterText.setEnabled(false);
                CargoButton.setEnabled(false);
                CargoCounterText.setEnabled(false);
                DroppedButton.setEnabled(true);
                DroppedCounterText.setEnabled(true);
                MissedButton.setEnabled(true);
                MissedCounterText.setEnabled(true);
                CSCR2Counter--;
                CSCR2.setText(String.valueOf(CSCR2Counter));
                enableScoringDiagram('C');

                if (CSCR2Counter == 0) {
                    CargoShipCargoRight2.setColor(Color.rgb(221, 172, 107));
                    CSCR2.setTextColor(getResources().getColor(R.color.defaultdisabled));
                }
                break;
            case "CSCR3":
                selectedButtonColors(CargoButton);
                defaultButtonState(PanelButton);

                PanelButton.setEnabled(false);
                PanelCounterText.setEnabled(false);
                CargoButton.setEnabled(false);
                CargoCounterText.setEnabled(false);
                DroppedButton.setEnabled(true);
                DroppedCounterText.setEnabled(true);
                MissedButton.setEnabled(true);
                MissedCounterText.setEnabled(true);
                CSCR3Counter--;
                CSCR3.setText(String.valueOf(CSCR3Counter));
                enableScoringDiagram('C');

                if (CSCR3Counter == 0) {
                    CargoShipCargoRight3.setColor(Color.rgb(221, 172, 107));
                    CSCR3.setTextColor(getResources().getColor(R.color.defaultdisabled));
                }
                break;
            case "CSPR1":
                selectedButtonColors(PanelButton);
                defaultButtonState(CargoButton);

                PanelButton.setEnabled(false);
                PanelCounterText.setEnabled(false);
                CargoButton.setEnabled(false);
                CargoCounterText.setEnabled(false);
                DroppedButton.setEnabled(true);
                DroppedCounterText.setEnabled(true);
                MissedButton.setEnabled(true);
                MissedCounterText.setEnabled(true);
                CSCR1Counter--;
                CSPR1.setText(String.valueOf(CSPR1Counter));
                enableScoringDiagram('P');

                if (CSCR1Counter == 0) {
                    CargoShipCargoRight1.setColor(Color.rgb(255, 255, 217));
                    CSCR1.setTextColor(getResources().getColor(R.color.textdefault));
                }
                break;
            case "CSPR2":
                selectedButtonColors(PanelButton);
                defaultButtonState(CargoButton);

                PanelButton.setEnabled(false);
                PanelCounterText.setEnabled(false);
                CargoButton.setEnabled(false);
                CargoCounterText.setEnabled(false);
                DroppedButton.setEnabled(true);
                DroppedCounterText.setEnabled(true);
                MissedButton.setEnabled(true);
                MissedCounterText.setEnabled(true);
                CSPR2Counter--;
                CSPR2.setText(String.valueOf(CSPR2Counter));
                enableScoringDiagram('P');

                if (CSPR2Counter == 0) {
                    CargoShipPanelRight2.setColor(Color.rgb(255, 255, 217));
                    CSPR2.setTextColor(getResources().getColor(R.color.textdefault));
                }
                break;
            case "CSPR3":
                selectedButtonColors(PanelButton);
                defaultButtonState(CargoButton);

                PanelButton.setEnabled(false);
                PanelCounterText.setEnabled(false);
                CargoButton.setEnabled(false);
                CargoCounterText.setEnabled(false);
                DroppedButton.setEnabled(true);
                DroppedCounterText.setEnabled(true);
                MissedButton.setEnabled(true);
                MissedCounterText.setEnabled(true);
                CSPR3Counter--;
                CSPR3.setText(String.valueOf(CSPR3Counter));
                enableScoringDiagram('P');

                if (CSPR3Counter == 0) {
                    CargoShipPanelRight3.setColor(Color.rgb(255, 255, 217));
                    CSPR3.setTextColor(getResources().getColor(R.color.textdefault));
                }
                break;
            case "RRPNT3":
                selectedButtonColors(PanelButton);
                defaultButtonState(CargoButton);

                PanelButton.setEnabled(false);
                PanelCounterText.setEnabled(false);
                CargoButton.setEnabled(false);
                CargoCounterText.setEnabled(false);
                DroppedButton.setEnabled(true);
                DroppedCounterText.setEnabled(true);
                MissedButton.setEnabled(true);
                MissedCounterText.setEnabled(true);
                RRPNT3Counter--;
                RRPNT3.setText(String.valueOf(RRPNT3Counter));
                enableScoringDiagram('P');

                if (RRPNT3Counter == 0) {
                    RightRocketPanelNearT3.setColor(Color.rgb(255, 255, 217));
                    RRPNT3.setTextColor(getResources().getColor(R.color.textdefault));
                }
                break;
            case "RRPNT2":
                selectedButtonColors(PanelButton);
                defaultButtonState(CargoButton);

                PanelButton.setEnabled(false);
                PanelCounterText.setEnabled(false);
                CargoButton.setEnabled(false);
                CargoCounterText.setEnabled(false);
                DroppedButton.setEnabled(true);
                DroppedCounterText.setEnabled(true);
                MissedButton.setEnabled(true);
                MissedCounterText.setEnabled(true);
                RRPNT2Counter--;
                RRPNT2.setText(String.valueOf(RRPNT2Counter));
                enableScoringDiagram('P');

                if (LRPNT2Counter == 0) {
                    RightRocketPanelNearT2.setColor(Color.rgb(255, 255, 217));
                    RRPNT2.setTextColor(getResources().getColor(R.color.textdefault));
                }
                break;
            case "RRPNT1":
                selectedButtonColors(PanelButton);
                defaultButtonState(CargoButton);

                PanelButton.setEnabled(false);
                PanelCounterText.setEnabled(false);
                CargoButton.setEnabled(false);
                CargoCounterText.setEnabled(false);
                DroppedButton.setEnabled(true);
                DroppedCounterText.setEnabled(true);
                MissedButton.setEnabled(true);
                MissedCounterText.setEnabled(true);
                RRPNT1Counter--;
                RRPNT1.setText(String.valueOf(RRPNT1Counter));
                enableScoringDiagram('P');

                if (RRPNT1Counter == 0) {
                    RightRocketPanelNearT1.setColor(Color.rgb(255, 255, 217));
                    RRPNT1.setTextColor(getResources().getColor(R.color.textdefault));
                }
                break;
            case "RRCT3":
                selectedButtonColors(CargoButton);
                defaultButtonState(PanelButton);

                PanelButton.setEnabled(false);
                PanelCounterText.setEnabled(false);
                CargoButton.setEnabled(false);
                CargoCounterText.setEnabled(false);
                DroppedButton.setEnabled(true);
                DroppedCounterText.setEnabled(true);
                MissedButton.setEnabled(true);
                MissedCounterText.setEnabled(true);
                RRCT3Counter--;
                RRCT3.setText(String.valueOf(RRCT3Counter));
                enableScoringDiagram('C');

                if (RRCT3Counter == 0) {
                    RightRocketCargoT3.setColor(Color.rgb(221, 172, 107));
                    RRCT3.setTextColor(getResources().getColor(R.color.defaultdisabled));
                }
                break;
            case "RRCT2":
                selectedButtonColors(CargoButton);
                defaultButtonState(PanelButton);

                PanelButton.setEnabled(false);
                PanelCounterText.setEnabled(false);
                CargoButton.setEnabled(false);
                CargoCounterText.setEnabled(false);
                DroppedButton.setEnabled(true);
                DroppedCounterText.setEnabled(true);
                MissedButton.setEnabled(true);
                MissedCounterText.setEnabled(true);
                RRCT2Counter--;
                RRCT2.setText(String.valueOf(RRCT2Counter));
                enableScoringDiagram('C');

                if (RRCT2Counter == 0) {
                    RightRocketCargoT2.setColor(Color.rgb(221, 172, 107));
                    RRCT2.setTextColor(getResources().getColor(R.color.defaultdisabled));
                }
                break;
            case "RRCT1":
                selectedButtonColors(CargoButton);
                defaultButtonState(PanelButton);

                PanelButton.setEnabled(false);
                PanelCounterText.setEnabled(false);
                CargoButton.setEnabled(false);
                CargoCounterText.setEnabled(false);
                DroppedButton.setEnabled(true);
                DroppedCounterText.setEnabled(true);
                MissedButton.setEnabled(true);
                MissedCounterText.setEnabled(true);
                RRCT1Counter--;
                RRCT1.setText(String.valueOf(RRCT1Counter));
                enableScoringDiagram('C');

                if (LRCT1Counter == 0) {
                    RightRocketCargoT1.setColor(Color.rgb(221, 172, 107));
                    RRCT1.setTextColor(getResources().getColor(R.color.defaultdisabled));
                    break;
                }
            case "RRPFT3":
                selectedButtonColors(PanelButton);
                defaultButtonState(CargoButton);

                PanelButton.setEnabled(false);
                PanelCounterText.setEnabled(false);
                CargoButton.setEnabled(false);
                CargoCounterText.setEnabled(false);
                DroppedButton.setEnabled(true);
                        DroppedCounterText.setEnabled(true);
                        MissedButton.setEnabled(true);
                        MissedCounterText.setEnabled(true);
                        RRPFT3Counter--;
                        RRPFT3.setText(String.valueOf(RRPFT3Counter));
                        enableScoringDiagram('P');

                        if (RRPFT3Counter == 0) {
                            RightRocketPanelFarT3.setColor(Color.rgb(255, 255, 217));
                            RRPFT3.setTextColor(getResources().getColor(R.color.textdefault));

                        }
                        break;
            case "RRPFT2":
                selectedButtonColors(PanelButton);
                defaultButtonState(CargoButton);

                PanelButton.setEnabled(false);
                PanelCounterText.setEnabled(false);
                CargoButton.setEnabled(false);
                CargoCounterText.setEnabled(false);
                DroppedButton.setEnabled(true);
                        DroppedCounterText.setEnabled(true);
                        MissedButton.setEnabled(true);
                        MissedCounterText.setEnabled(true);
                        RRPFT2Counter--;
                        RRPFT2.setText(String.valueOf(RRPFT2Counter));
                        enableScoringDiagram('P');

                        if (RRPFT2Counter == 0) {
                            RightRocketPanelFarT2.setColor(Color.rgb(255, 255, 217));
                            RRPFT2.setTextColor(getResources().getColor(R.color.textdefault));
                        }
                        break;
            case "RRPFT1":
                selectedButtonColors(PanelButton);
                defaultButtonState(CargoButton);

                PanelButton.setEnabled(false);
                PanelCounterText.setEnabled(false);
                CargoButton.setEnabled(false);
                CargoCounterText.setEnabled(false);
                DroppedButton.setEnabled(true);
                        DroppedCounterText.setEnabled(true);
                        MissedButton.setEnabled(true);
                        MissedCounterText.setEnabled(true);
                        RRPFT1Counter--;
                        RRPFT1.setText(String.valueOf(RRPFT1Counter));
                        enableScoringDiagram('P');

                        if (RRPFT1Counter == 0) {
                            RightRocketPanelFarT1.setColor(Color.rgb(255, 255, 217));
                            RRPFT1.setTextColor(getResources().getColor(R.color.textdefault));
                        }
                        break;
                }
        }
}
