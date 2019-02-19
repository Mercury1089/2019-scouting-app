package com.mercury1089.scoutingapp2019;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
    CustomView LeftRocketPanelFarT3 = findViewById(R.id.LeftRocketPanelFarT3);
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
    CustomView CargoShipPanelRight3 = findViewById(R.id.CargoShipPanelRight3);

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

    //buttons
    Button DroppedButton = findViewById(R.id.DroppedButton);
    Button MissedButton = findViewById(R.id.MissedButton);

    //array counter
    private int arrCounter = 0; //multiples of 7 outputted.... 2D array --> int arr[][] = new int[arrCounter][7];

    //other variables
    private int crossedHABLine = 0; //0 or 1
    private int deadRobot = 0; //0 or 1
    private boolean isCargo = false;
    private boolean isPanel = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sandstorm);

        //setting counters to default
        PanelCounterText.setText('0');
        CargoCounterText.setText('0');
        DroppedCounterText.setText('0');
        MissedCounterText.setText('0');

         String message = getIntent().getStringExtra("message");
         if (message.charAt(0) == 'P') {
            selectedButtonColors(PanelButton);
            PanelCounterText.setText('1');
            CargoButton.setEnabled(false);
            totalPanels++;
            //enable panel circles
         } else {
             selectedButtonColors(CargoButton);
             CargoCounterText.setText('1');
             PanelButton.setEnabled(false);
             totalCargo++;
             //enable cargo circles
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
        textView.setText(total);
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


        //cargo variables



        //CARGO SHIP
        //panel variables


        //cargo variables


        //RIGHT ROCKET
        //panel variables


        //cargo variables

    }


    //click methods
    public void setupClick (View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void panelCounterClick (View view) {
        selectedButtonColors(PanelButton);
        totalPanels++;
        PanelCounterText.setText(totalPanels);
        CargoButton.setEnabled(false);
        isPanel = true;
        isCargo = false;
        //enable panel circles
    }
    public void cargoCounterClick (View view) {
        selectedButtonColors(CargoButton);
        totalCargo++;
        CargoCounterText.setText(totalCargo);
        PanelButton.setEnabled(false);
        isPanel = false;
        isCargo = true;
        //enable cargo circles
    }
    public void droppedClick (View view) {
        //use handler??? for temporarily active (only 500 ms)
        if (isPanel) {
            droppedPanels++;
            totalPanels++;
            PanelCounterText.setText(totalPanels);
            defaultButtonState(PanelButton);
        }
        if (isCargo) {
            droppedCargo++;
            totalCargo++;
            CargoCounterText.setText(totalCargo);
            defaultButtonState(CargoButton);
        }
        DroppedButton.setEnabled(false);
        MissedButton.setEnabled(false);
        disableScoringDiagram('A');
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
