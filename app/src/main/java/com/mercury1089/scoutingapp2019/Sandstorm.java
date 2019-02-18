package com.mercury1089.scoutingapp2019;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.BootstrapButton;

public class Sandstorm extends AppCompatActivity {
    //LEFT ROCKET
    //panel variables
    CustomView LeftRocketPanelNearT3 = findViewById(R.id.LeftRocketPanelNearT3);
    CustomView LeftRocketPanelNearT2 = findViewById(R.id.LeftRocketPanelNearT2);
    CustomView LeftRocketPanelNearT1 = findViewById(R.id.LeftRocketPanelNearT1);
    CustomView LeftRocketPanelFarT3 = findViewById(R.id.LeftRocketPanelFarT1);
    CustomView LeftRocketPanelFarT2 = findViewById(R.id.LeftRocketPanelFarT2);
    CustomView LeftRocketPanelFarT1 = findViewById(R.id.LeftRocketPanelFarT1);

    //cargo variables
    CustomView LeftRocketCargoT3 = findViewById(R.id.LeftRocketCargoT3);
    CustomView LeftRocketCargoT2 = findViewById(R.id.LeftRocketCargoT2);
    CustomView LeftRocketCargoT1 = findViewById(R.id.LeftRocketCargoT1);


    //CARGO SHIP
    //panel variables
    CustomView CargoShipPanelFront1 = findViewById(R.id.CargoShipPanelFront1);
    CustomView CargoShipPanelFront2 = findViewById(R.id.CargoShipPanelFront2);
    CustomView CargoShipPanelLeft1 = findViewById(R.id.CargoShipPanelLeft1);
    CustomView CargoShipPanelLeft2 = findViewById(R.id.CargoShipPanelLeft2);
    CustomView CargoShipPanelLeft3 = findViewById(R.id.CargoShipPanelLeft3);
    CustomView CargoShipPanelRight1 = findViewById(R.id.CargoShipPanelRight1);
    CustomView CargoShipPanelRight2 = findViewById(R.id.CargoShipPanelRight2);
    CustomView CargoShipPanelRight3 = findViewById(R.id.CargoShipPanelRight2);

    //cargo variables
    CustomView CargoShipCargoFront1 = findViewById(R.id.CargoShipCargoFront1);
    CustomView CargoShipCargoFront2 = findViewById(R.id.CargoShipCargoFront2);
    CustomView CargoShipCargoLeft1 = findViewById(R.id.CargoShipCargoLeft1);
    CustomView CargoShipCargoLeft2 = findViewById(R.id.CargoShipCargoLeft2);
    CustomView CargoShipCargoLeft3 = findViewById(R.id.CargoShipCargoLeft3);
    CustomView CargoShipCargoRight1 = findViewById(R.id.CargoShipCargoRight1);
    CustomView CargoShipCargoRight2 = findViewById(R.id.CargoShipCargoRight2);
    CustomView CargoShipCargoRight3 = findViewById(R.id.CargoShipCargoRight3);

    //RIGHT ROCKET
    //panel variables
    CustomView RightRocketPanelNearT3 = findViewById(R.id.RightRocketPanelNearT3);
    CustomView RightRocketPanelNearT2 = findViewById(R.id.RightRocketPanelNearT2);
    CustomView RightRocketPanelNearT1 = findViewById(R.id.RightRocketPanelNearT1);
    CustomView RightRocketPanelFarT3 = findViewById(R.id.RightRocketPanelFarT3);
    CustomView RightRocketPanelFarT2 = findViewById(R.id.RightRocketPanelFarT2);
    CustomView RightRocketPanelFarT1 = findViewById(R.id.RightRocketPanelFarT1);

    //cargo variables
    CustomView RightRocketCargoT3 = findViewById(R.id.RightRocketCargoT3);
    CustomView RightRocketCargoT2 = findViewById(R.id.RightRocketCargoT2);
    CustomView RightRocketCargoT1 = findViewById(R.id.RightRocketCargoT1);

    //Repeated Variables for each buttonClick:
    private final String MODE = "Sandstorm";
    private String location = "";
    private String panelOrCargo = "";
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

    //counter TextViews
    TextView PanelCounterText = findViewById(R.id.PanelCounterText);
    TextView CargoCounterText = findViewById(R.id.CargoCounterText);
    TextView DroppedCounterText = findViewById(R.id.DroppedCounterText);
    TextView MissedCounterText = findViewById(R.id.MissedCounterText);

    //bootstrap buttons
    BootstrapButton SetupButton = findViewById(R.id.GoToSetup);
    BootstrapButton SandstormButton = findViewById(R.id.GoToSandstorm);
    BootstrapButton TeleopButton = findViewById(R.id.GoToTeleop);
    BootstrapButton ClimbButton = findViewById(R.id.GoToClimb);
    BootstrapButton PanelButton = findViewById(R.id.SPanelButton);
    BootstrapButton CargoButton = findViewById(R.id.SCargoButton);

    //array counter
    private int arrCounter = 0; //multiples of 7 outputted.... 2D array --> int arr[][] = new int[arrCounter][7];

    //other output variables
    private int crossedHABLine = 0; //0 or 1
    private int deadRobot = 0; //0 or 1


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sandstorm);

         String message = getIntent().getStringExtra("message");
         if (message.charAt(0) == 'P') {

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
                if (isChecked) {
                    deadRobot = 1;
                    PanelButton.setEnabled(false);
                    CargoButton.setEnabled(false);

                    TextView possessionTitle = findViewById(R.id.IDPossession);
                    TextView panelOrCargoDirections = findViewById(R.id.IDPanelOrCargoDirections);
                    TextView droppedDirection = findViewById(R.id.IDDroppedDirections);

                    TextView scoringTitle = findViewById(R.id.IDScoring);
                    TextView pOrCDirections = findViewById(R.id.IDScoringPOrCDirections);
                    TextView missedDirections = findViewById(R.id.IDScoringMissedDirections);

                    possessionTitle.setTextColor(getResources().getColor(R.color.grey));
                    panelOrCargoDirections.setTextColor(getResources().getColor(R.color.grey));
                    droppedDirection.setTextColor(getResources().getColor(R.color.grey));
                    scoringTitle.setTextColor(getResources().getColor(R.color.grey));
                    pOrCDirections.setTextColor(getResources().getColor(R.color.grey));
                    missedDirections.setTextColor(getResources().getColor(R.color.grey));
                }

            }
        });
    }

    //call methods
    public void updateCounterDisplay (int panels, int cargo, TextView textView) {
        int total = panels + cargo;
        textView.setText(total);
    }
    public void defaultButtonState (BootstrapButton button) {
        button.setBackgroundColor(getResources().getColor(R.color.light));
        button.setTextColor(getResources().getColor(R.color.grey));
    }

    //click methods
    public void setupClick (View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    /*public void teleopClick (View view) {
        Intent intent = new Intent(this, Teleop.class);
        startActivity(intent);
    }
    public void climbClick (View view) {
        Intent intent = new Intent(this, Climb.class);
        startActivity(intent);
    }*/
    public void panelCounterClick (View view) {
        panelOrCargo = "Panel";
    }
    public void cargoCounterClick (View view) {
        panelOrCargo = "Cargo";
    }
    public void droppedClick (View view) {
        if (panelOrCargo.equals("Panel"))
            droppedPanels++;
        else if (panelOrCargo.equals("Cargo"))
            droppedCargo++;
        updateCounterDisplay(droppedPanels,droppedCargo,DroppedCounterText);
    }
    public void missedClick (View view) {
        if (panelOrCargo.equals("Panel"))
            missedPanels++;
        else if (panelOrCargo.equals("Cargo"))
            missedCargo++;
        updateCounterDisplay(missedPanels,missedCargo,DroppedCounterText);
    }

}
