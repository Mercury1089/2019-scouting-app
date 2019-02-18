package com.mercury1089.scoutingapp2019;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Sandstorm extends AppCompatActivity {



    //LEFT ROCKET
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
    CustomView CargoShipCargolLeft2;
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
    private String panelOrCargo = "";
    private String side = ""; //left, right, or front
    private String nearOrFar = "";
    private String tier = "";

    //counters
    private int totalPanels = 0;
    private int totalCargo = 0;
    private int droppedPanels = 0;
    private int droppedCargo = 0;
    private int missedCargo = 0;
    private int missedPanels = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sandstorm);
        //multiples of 7 outputted.... 2D array


    }

    //call methods
}
