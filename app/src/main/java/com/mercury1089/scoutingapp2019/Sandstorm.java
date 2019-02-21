package com.mercury1089.scoutingapp2019;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.BootstrapButton;

import java.util.Random;

import at.markushi.ui.CircleButton;

public class Sandstorm extends AppCompatActivity {
    //LEFT ROCKET
    //panel variables
    CustomView LeftRocketPanelNearT3;
    CustomView LeftRocketPanelNearT2;
    CustomView LeftRocketPanelNearT1;
    CustomView LeftRocketPanelFarT3;
    CustomView LeftRocketPanelFarT2;
    CustomView LeftRocketPanelFarT1;

    //cargo variables
    CustomView LeftRocketCargoT3;
    CustomView LeftRocketCargoT2;
    CustomView LeftRocketCargoT1;


    //CARGO SHIP
    //panel variables
    CustomView CargoShipPanelFront1;
    CustomView CargoShipPanelFront2;
    CustomView CargoShipPanelLeft1;
    CustomView CargoShipPanelLeft2;
    CustomView CargoShipPanelLeft3;
    CustomView CargoShipPanelRight1;
    CustomView CargoShipPanelRight2;
    CustomView CargoShipPanelRight3;

    //cargo variables
    CustomView CargoShipCargoFront1;
    CustomView CargoShipCargoFront2;
    CustomView CargoShipCargoLeft1;
    CustomView CargoShipCargoLeft2;
    CustomView CargoShipCargoLeft3;
    CustomView CargoShipCargoRight1;
    CustomView CargoShipCargoRight2;
    CustomView CargoShipCargoRight3;

    //RIGHT ROCKET
    //panel variables
    CustomView RightRocketPanelNearT3;
    CustomView RightRocketPanelNearT2;
    CustomView RightRocketPanelNearT1;
    CustomView RightRocketPanelFarT3;
    CustomView RightRocketPanelFarT2;
    CustomView RightRocketPanelFarT1;

    //cargo variables
    CustomView RightRocketCargoT3;
    CustomView RightRocketCargoT2;
    CustomView RightRocketCargoT1;

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
    CircleButton btn;
    Random random = new Random();

    public Sandstorm() {

    }


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
        btn = findViewById(R.id.btn_home);

        btn.setEnabled(false);

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

         String message = getIntent().getStringExtra("message");
         if (message.charAt(0) == 'P') {
            selectedButtonColors(PanelButton);
            PanelCounterText.setText("1");
            CargoButton.setEnabled(false);
            totalPanels++;
            //enable panel circles
             enableScoringDiagram('P');
         } else {
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
        defaultButtonState(SandstormButton);
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
                    setTextToColor(possessionTitle, "black");
                    setTextToColor(panelOrCargoDirections, "black");
                    setTextToColor(droppedDirection, "black");
                    setTextToColor(scoringTitle, "black");
                    setTextToColor(pOrCDirections, "black");
                    setTextToColor(missedDirections, "black");
                }

            }
        });
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
            textView.setTextColor(getResources().getColor(R.color.black));
    }


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
        }
    }



    //click methods
    public void setupClick (View view) {
        Intent intent = new Intent(this, MainActivity.class);
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
        String LPRNT3[] = {"Rocket", "Panel", "", "Near","T3", "1"};
    }
    public void LRPNT2CounterClick (View view) {
        LRPNT2Counter++;
        String LPRNT2[] = {"Rocket", "Panel", "", "Near","T2", "1"};
    }
    public void LRPNT1CounterClick (View view) {
        LRPNT1Counter++;
        String LPRNT1[] = {"Rocket", "Panel", "", "Near","T1", "1"};
    }
    public void LRCT3CounterClick (View view) {
        LRCT3Counter++;
        String LPCT3[] = {"Rocket", "Cargo", "","T3", "1"};
    }
    public void LRCT2CounterClick (View view) {
        LRCT2Counter++;
        String LPCT2[] = {"Rocket", "Cargo", "","T2", "1"};
    }
    public void LRCT1CounterClick (View view) {
        LRCT1Counter++;
        String LPCT1[] = {"Rocket", "Cargo", "","T1", "1"};
    }
    public void LRPFT3CounterClick (View view) {
        LRPFT3Counter++;
        String LPRFT3[] = {"Rocket", "Panel", "Far", "", "T3","1"};
    }
    public void LRPFT2CounterClick (View view) {
        LRPFT2Counter++;
        String LPRFT2[] = {"Rocket", "Panel", "Far", "", "T2", "1"};
    }
    public void LRPFT1CounterClick (View view) {
        LRPFT2Counter++;
        String LPRFT1[] = {"Rocket", "Panel", "Far", "", "T1", "1"};
    }

    //cargo ship onClicks
    public void CSPF1CounterClick (View view) {
        CSPF1Counter++;
        String CSPF1[] = {"Cargoship", "Panel", "Front", "", "", "1"};
    }
    public void CSPF2CounterClick (View view) {
        CSPF2Counter++;
        String CSPF2[] = {"Cargoship", "Panel", "Front", "", "", "1"};
    }
    public void CSCF1CounterClick (View view) {
        CSCF1Counter++;
        String CSCF1[] = {"Cargoship", "Cargo", "Front", "", "", "1"};
    }
    public void CSCF2CounterClick (View view) {
        CSCF2Counter++;
        String CSCF2[] = {"Cargoship", "Cargo", "Front", "", "", "1"};
    }
    public void CSPL1CounterClick (View view) {
        CSPL1Counter++;
        String CSPL1[] = {"Cargoship", "Panel", "Left", "", "", "1"};
    }
    public void CSPL2CounterClick (View view) {
        CSPL2Counter++;
        String CSPL2[] = {"Cargoship", "Panel", "Left", "", "", "1"};
    }
    public void CSPL3CounterClick (View view) {
        CSPL3Counter++;
        String CSPL3[] = {"Cargoship", "Panel", "Left", "", "", "1"};
    }
    public void CSCL1CounterClick (View view) {
        CSCL1Counter++;
        String CSCL1[] = {"Cargoship", "Cargo", "Left", "", "", "1"};
    }
    public void CSCL2CounterClick (View view) {
        CSCL2Counter++;
        String CSCL2[] = {"Cargoship", "Cargo", "Left", "", "", "1"};
    }
    public void CSCL3CounterClick (View view) {
        CSCL3Counter++;
        String CSCL3[] = {"Cargoship", "Cargo", "Left", "", "", "1"};
    }
    public void CSCR1CounterClick (View view) {
        CSCR1Counter++;
        String CSCR1[] = {"Cargoship", "Cargo", "Right", "", "", "1"};
    }
    public void CSCR2CounterClick (View view) {
        CSCR2Counter++;
        String CSCR2[] = {"Cargoship", "Cargo", "Right", "", "", "1"};
    }
    public void CSCR3CounterClick (View view) {
        CSCR3Counter++;
        String CSCR3[] = {"Cargoship", "Cargo", "Right", "", "", "1"};
    }
    public void CSPR1CounterClick (View view) {
        CSPR1Counter++;
        String CSPR1[] = {"Cargoship", "Panel", "Right", "", "", "1"};
    }
    public void CSPR2CounterClick (View view) {
        CSPR2Counter++;
        String CSPR2[] = {"Cargoship", "Panel", "Right", "", "", "1"};
    }
    public void CSPR3CounterClick (View view) {
        CSPR3Counter++;
        String CSPR3[] = {"Cargoship", "Panel", "Right", "", "", "1"};
    }

    //right rocket onClicks
    public void RRPNT3CounterClick (View view) {
        RRPNT3Counter++;
        String RRPNT3[] = {MODE, "Rocket", "Panel", "", "Near", "T3", "1"};
    }
    public void RRPNT2CounterClick (View view) {
        RRPNT2Counter++;
        String RRPNT2[] = {MODE, "Rocket", "Panel", "", "Near", "T2", "1"};
    }
    public void RRPNT1CounterClick (View view) {
        RRPNT1Counter++;
        String RRPNT1[] = {MODE, "Rocket", "Panel", "", "Near", "T1", "1"};
    }
    public void RRCT3CounterClick (View view) {
        RRCT3Counter++;
        String RRPCT3[] = {MODE, "Rocket", "Cargo", "", "", "T3", "1"};
    }
    public void RRCT2CounterClick (View view) {
        RRCT2Counter++;
        String RRPCT2[] = {MODE, "Rocket", "Cargo", "", "", "T2", "1"};
    }
    public void RRCT1CounterClick (View view) {
        RRCT1Counter++;
        String RRPCT1[] = {MODE, "Rocket", "Cargo", "", "", "T1", "1"};
    }
    public void RRPFT3CounterClick (View view) {
        RRPFT3Counter++;
        String RRPFT3[] = {MODE, "Rocket", "Panel", "", "Far", "T2", "1"};
    }
    public void RRPFT2CounterClick (View view) {
        RRPFT2Counter++;
        String RRPFT2[] = {MODE, "Rocket", "Panel", "", "Far", "T2", "1"};
    }
    public void RRPFT1CounterClick (View view) {
        RRPFT1Counter++;
        String RRPFT1[] = {MODE, "Rocket", "Panel", "", "Far", "T1", "1"};
    }

    //button test
    public void buttonTest (View view) {
        btn.setColor(Color.rgb(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
    }
}
