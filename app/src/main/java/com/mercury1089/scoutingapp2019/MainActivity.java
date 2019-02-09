package com.mercury1089.scoutingapp2019;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    //variables that should be outputted
    String scouterName = "";
    String matchNumber = "";
    int teamNumber = 0;
    int firstAlliancePartner = 0;
    int secondAlliancePartner = 0;
    int noShowStatus = 0; //0 or 1
    int isBlueAlliance = 0; //0 or 1
    int isRedAlliance = 0; //0 or 1
    int isSetupPanel = 0;  //0 or 1
    int isSetupCargo = 0; //0 or 1
    int startL1 = 0; //0 or 1
    int startC1 = 0; //0 or 1
    int startR1 = 0; //0 or 1
    int startL2 = 0; //0 or 1
    int startR2 = 0; //0 or 1

    //variables that store elements of the screen for the output variables
    EditText scouterNameInput;
    EditText matchNumberInput;
    EditText teamNumberInput;
    EditText firstAlliancePartnerInput;
    EditText secondAlliancePartnerInput;
    Switch NoShowSwitch;
    Button blueButton;
    Button redButton;
    Button panelButton;
    Button cargoButton;


    //other variables
    TextView prepopulatedDirections;
    TextView prepopulatedTitle;
    Button startButton;
    Button generateQRButton;
    boolean isResetLocalStorageClicked;




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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setting variables equal to elements
        scouterNameInput = findViewById(R.id.editText);
        matchNumberInput = findViewById(R.id.editText2);
        teamNumberInput = findViewById(R.id.numeditText);
        firstAlliancePartnerInput = findViewById(R.id.numeditText2);
        secondAlliancePartnerInput = findViewById(R.id.numeditText3);
        NoShowSwitch = findViewById(R.id.Switch);
        //starting listener to check the status of the switch
        NoShowSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                        noShowStatus = 1;
                        panelButton.setEnabled(false);
                        cargoButton.setEnabled(false);
                        prepopulatedDirections = (findViewById(R.id.IDPrepopulatedDirections));
                        prepopulatedDirections.setTextColor(getResources().getColor(R.color.grey));
                        prepopulatedTitle = findViewById(R.id.IDPrepopulatedTitle);
                        prepopulatedTitle.setTextColor(getResources().getColor(R.color.grey));
                        startButton = findViewById(R.id.startButton);
                        generateQRButton = findViewById(R.id.generateQRButton);
                        startButton.setVisibility(View.GONE);
                        generateQRButton.setVisibility(View.VISIBLE);
                } else {
                       noShowStatus = 0;
                }
            }
        });
        blueButton = findViewById(R.id.blueButton);
        blueButton.setBackgroundColor(getResources().getColor(R.color.light));
        blueButton.setTextColor(getResources().getColor(R.color.grey));
        redButton = findViewById(R.id.redButton);
        redButton.setBackgroundColor(getResources().getColor(R.color.light));
        redButton.setTextColor(getResources().getColor(R.color.grey));
        panelButton = findViewById(R.id.PanelButton);
        cargoButton = findViewById(R.id.CargoButton);
        //get intent from settings screen and the variable that says whether it is left or right
        Intent intent = getIntent();
        String leftOrRight = intent.getStringExtra(Settings.);

    }
    //setters
    public void setScouterName(String newString) {
        this.scouterName = newString;
    }
    public void setMatchNumber(String newString) {
        this.matchNumber = newString;
    }
    public void setTeamNumber(int newInt) {
        this.teamNumber = newInt;
    }
    public void setFirstAlliancePartner(int newInt) {
        this.firstAlliancePartner = newInt;
    }
    public void setSecondAlliancePartner(int newInt) {
        this.secondAlliancePartner = newInt;
    }
    public void setNoShowStatus(int newInt) {
        this.noShowStatus = newInt;
    }
    public void setIsBlueAlliance(int newInt) {
        this.isBlueAlliance = newInt;
    }
    public void setIsRedAlliance(int newInt) {
        this.isRedAlliance = newInt;
    }
    public void setIsSetupPanel(int newInt) {
        this.isSetupPanel = newInt;
    }
    public void setIsSetupCargo(int newInt) {
        this.isSetupCargo = newInt;
    }
    public void setisResetLocalStorageClicked(boolean newBool) {
        this.isResetLocalStorageClicked = newBool;
    }
    public void setStartL1 (int newInt) {
        this.startL1 = newInt;
    }
    public void setStartC1 (int newInt) {
        this.startC1 = newInt;
    }
    public void setStartR1 (int newInt) {
        this.startR1 = newInt;
    }
    public void setStartL2 (int newInt) {
        this.startL2 = newInt;
    }
    public void setStartR2 (int newInt) {
        this.startR2 = newInt;
    }

    //call methods
    public void blueDefault () {
        isBlueAlliance = 0;
        blueButton.setBackgroundColor(getResources().getColor(R.color.light));
        blueButton.setTextColor(getResources().getColor(R.color.grey));
    }
    public void redDefault () {
        isRedAlliance = 0;
        redButton.setBackgroundColor(getResources().getColor(R.color.light));
        redButton.setTextColor(getResources().getColor(R.color.grey));
    }
    public void panelDefault () {
        panelButton.setBackgroundColor(getResources().getColor(R.color.light));
        panelButton.setTextColor(getResources().getColor(R.color.grey));
        isSetupPanel = 0;
    }
    public void cargoDefault () {
        cargoButton.setBackgroundColor(getResources().getColor(R.color.light));
        cargoButton.setTextColor(getResources().getColor(R.color.grey));
        isSetupCargo = 0;
    }
    //click methods
    public void SettingClick (View view) {
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }
    public void ClearClick (View view) {
        scouterNameInput.setText("");
        matchNumberInput.setText("");
        teamNumberInput.setText("");
        firstAlliancePartnerInput.setText("");
        secondAlliancePartnerInput.setText("");
        blueDefault();
        redDefault();
        panelDefault();
        cargoDefault();
    }
    //do color stuff for this
    public void L1Click (View view) {
        startL1 = 1;
        startC1 = 0;
        startR1 = 0;
        startL2 = 0;
        startR2 = 0;
    }

    public void C1Click (View view) {
        startL1 = 0;
        startC1 = 1;
        startR1 = 0;
        startL2 = 0;
        startR2 = 0;
    }

    public void R1Click (View view) {
        startL1 = 0;
        startC1 = 0;
        startR1 = 1;
        startL2 = 0;
        startR2 = 0;
    }

    public void L2Click (View view) {
        startL1 = 0;
        startC1 = 0;
        startR1 = 0;
        startL2 = 1;
        startR2 = 0;
    }

    public void R2Click (View view) {
        startL1 = 0;
        startC1 = 0;
        startR1 = 0;
        startL2 = 0;
        startR2 = 1;
    }

    public void blueClick (View view) {
        if (isBlueAlliance == 0) {
            blueButton.setBackgroundColor(getResources().getColor(R.color.blue));
            blueButton.setTextColor(getResources().getColor(R.color.light));
            redDefault();
            isBlueAlliance = 1;
        }
        else {
            blueDefault();
        }
    }

    public void redClick (View view) {
        if (isRedAlliance == 0) {
            blueDefault();
            redButton.setBackgroundColor(getResources().getColor(R.color.red));
            redButton.setTextColor(getResources().getColor(R.color.light));
            isRedAlliance = 1;
        }
        else {
            redDefault();
        }

    }

    public void panelClick (View view) {
        if (isSetupPanel == 0) {
            isSetupPanel = 1;
            panelButton.setBackgroundColor(getResources().getColor(R.color.orange));
            panelButton.setTextColor(getResources().getColor(R.color.light));
            cargoDefault();
        }
        else {
            panelDefault();
        }
    }

    public void cargoClick (View view) {
        if (isSetupCargo == 0) {
            panelDefault();
            isSetupCargo = 1;
            cargoButton.setBackgroundColor(getResources().getColor(R.color.orange));
            cargoButton.setTextColor(getResources().getColor(R.color.light));
        }
        else {
            cargoDefault();
        }
    }

    public void startClick (View view) {
        scouterName = scouterNameInput.getText().toString();
        matchNumber = matchNumberInput.getText().toString();
        teamNumber = Integer.parseInt(teamNumberInput.getText().toString());
        firstAlliancePartner = Integer.parseInt(firstAlliancePartnerInput.getText().toString());
        secondAlliancePartner = Integer.parseInt(secondAlliancePartnerInput.getText().toString());
    }


}
