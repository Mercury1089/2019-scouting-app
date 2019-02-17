package com.mercury1089.scoutingapp2019;




import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;

import android.support.v4.app.NavUtils;



import android.os.Bundle;


import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;



import android.widget.Button;

import android.widget.CompoundButton;

import android.widget.EditText;

import android.widget.ImageView;

import android.widget.ProgressBar;
import android.widget.Switch;

import android.widget.TextView;
import android.widget.Toast;


import com.beardedhen.androidbootstrap.BootstrapButton;



import com.google.zxing.BarcodeFormat;

import com.google.zxing.MultiFormatWriter;

import com.google.zxing.WriterException;

import com.google.zxing.common.BitMatrix;









public class MainActivity extends Activity {
    //variables that should be outputted

    private String scouterName = "";

    private int matchNumber = 0;

    private int teamNumber = 0;

    private int firstAlliancePartner = 0;

    private int secondAlliancePartner = 0;

    private int noShowStatus = 0; //0 or 1

    private int isBlueAlliance = 0; //0 or 1

    private int isRedAlliance = 0; //0 or 1

    private int isSetupPanel = 0;  //0 or 1

    private int isSetupCargo = 0; //0 or 1

    private int startL1 = 0; //0 or 1

    private int startC1 = 0; //0 or 1

    private int startR1 = 0; //0 or 1

    private int startL2 = 0; //0 or 1

    private int startR2 = 0; //0 or 1



    //variables that store elements of the screen for the output variables

    private EditText ScouterNameInput;

    private EditText matchNumberInput;

    private EditText teamNumberInput;

    private EditText firstAlliancePartnerInput;

    private EditText secondAlliancePartnerInput;

    private BootstrapButton blueButton;

    private BootstrapButton redButton;

    private BootstrapButton panelButton;

    private BootstrapButton cargoButton;

    private View LL1;

    private View LC1;

    private View LR1;

    private View LL2;

    private View LR2;

    private View RL1;

    private View RC1;

    private View RR1;

    private View RL2;

    private View RR2;

    private CustomView LL1Circle;

    private CustomView LC1Circle;

    private CustomView LR1Circle;

    private CustomView LL2Circle;

    private CustomView LR2Circle;

    private CustomView RL1Circle;

    private CustomView RC1Circle;

    private CustomView RR1Circle;

    private CustomView RL2Circle;

    private CustomView RR2Circle;



    //for QR code generator

    String QRValue;

    public final static int QRCodeSize = 500;

    Bitmap bitmap;



    //other variables

    TextView prepopulatedDirections;

    TextView prepopulatedTitle;

    Button clearButton;

    Button startButton;

    boolean isQRButton = false;

    String leftOrRight;

    String sendMessage;




    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        LL1Circle = findViewById(R.id.CircleLL1);

        LC1Circle = findViewById(R.id.CircleLC1);

        LR1Circle = findViewById(R.id.CircleLR1);

        LL2Circle = findViewById(R.id.CircleLL2);

        LR2Circle = findViewById(R.id.CircleLR2);

        RL1Circle = findViewById(R.id.CircleRL1);

        RC1Circle = findViewById(R.id.CircleRC1);

        RR1Circle = findViewById(R.id.CircleRR1);

        RL2Circle = findViewById(R.id.CircleRL2);

        RR2Circle = findViewById(R.id.CircleRR2);

        //send circles to front

        LL1Circle.bringToFront();
        LC1Circle.bringToFront();
        LR1Circle.bringToFront();
        LL2Circle.bringToFront();
        LR2Circle.bringToFront();


        //setting variables equal to elements via their ID

        ScouterNameInput = findViewById(R.id.ScouterNameInput);

        matchNumberInput = findViewById(R.id.MatchNumberInput);

        teamNumberInput = findViewById(R.id.TeamNumberInput);

        firstAlliancePartnerInput = findViewById(R.id.FirstAlliancePartnerInput);

        secondAlliancePartnerInput = findViewById(R.id.SecondAlliancePartnerInput);

        prepopulatedTitle = findViewById(R.id.IDPrepopulated);

        prepopulatedTitle.setTextColor(getResources().getColor(R.color.grey));

        prepopulatedDirections = (findViewById(R.id.IDPrepopulatedDirections));

        prepopulatedDirections.setTextColor(getResources().getColor(R.color.grey));

        clearButton = findViewById(R.id.ClearButton);

        startButton = findViewById(R.id.StartButton);

        blueButton = findViewById(R.id.BlueButton);

        redButton = findViewById(R.id.RedButton);

        panelButton = findViewById(R.id.PanelButton);

        cargoButton = findViewById(R.id.CargoButton);

        Switch NoShowSwitch = findViewById(R.id.NoShowSwitch);

        LL1 = findViewById(R.id.LL1);

        LC1 = findViewById(R.id.LC1);

        LR1 = findViewById(R.id.LR1);

        LL2 = findViewById(R.id.LL2);

        LR2 = findViewById(R.id.LR2);

        RL1 = findViewById(R.id.RL1);

        RC1 = findViewById(R.id.RC1);

        RR1 = findViewById(R.id.RR1);

        RL2 = findViewById(R.id.RL2);

        RR2 = findViewById(R.id.RR2);


        //setting group buttons to default state

        blueDefault();

        redDefault();

        panelDefault();

        cargoDefault();

        setButtonsToFalse();

        leftOrRight = getIntent().getStringExtra("LEFTORRIGHT"); //get data from the settings class to be used in the mainactivity
        Log.d("leftorright", "Getting Left Or Right: " + leftOrRight);

        //starting listener to check the status of the switch

        NoShowSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {

                    noShowStatus = 1;

                    startButton.setText(R.string.GenerateQRCode);
                    isQRButton = true;

                    LL1Circle.setVisibility(View.INVISIBLE);
                    LC1Circle.setVisibility(View.INVISIBLE);
                    LR1Circle.setVisibility(View.INVISIBLE);
                    LL2Circle.setVisibility(View.INVISIBLE);
                    LR2Circle.setVisibility(View.INVISIBLE);

                    RL1Circle.setVisibility(View.INVISIBLE);
                    RC1Circle.setVisibility(View.INVISIBLE);
                    RR1Circle.setVisibility(View.INVISIBLE);
                    RL2Circle.setVisibility(View.INVISIBLE);
                    RR2Circle.setVisibility(View.INVISIBLE);

                    LL1.setVisibility(View.INVISIBLE);
                    LC1.setVisibility(View.INVISIBLE);
                    LR1.setVisibility(View.INVISIBLE);
                    LL2.setVisibility(View.INVISIBLE);
                    LR2.setVisibility(View.INVISIBLE);

                    RL1.setVisibility(View.INVISIBLE);
                    RC1.setVisibility(View.INVISIBLE);
                    RR1.setVisibility(View.INVISIBLE);
                    RL2.setVisibility(View.INVISIBLE);
                    RR2.setVisibility(View.INVISIBLE);

                    panelButton.setEnabled(false);
                    panelButton.setBackgroundColor(getResources().getColor(R.color.light));
                    panelButton.setTextColor(getResources().getColor(R.color.grey));

                    cargoButton.setEnabled(false);
                    cargoButton.setBackgroundColor(getResources().getColor(R.color.light));
                    cargoButton.setTextColor(getResources().getColor(R.color.grey));


                    if (isBlueAlliance == 1 || isRedAlliance == 1 && matchNumber != 0 &&
                            teamNumber != 0 && firstAlliancePartner != 0 && secondAlliancePartner !=0)
                        startButton.setEnabled(true);
                } else {

                    noShowStatus = 0;

                    panelButton.setEnabled(true);

                    cargoButton.setEnabled(true);

                    startButton.setText(R.string.Start);
                    startButton.setEnabled(false);

                }

            }

        });


        ScouterNameInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (ScouterNameInput.getText().length() > 0) {
                    clearButton.setEnabled(true);
                    if (teamNumberInput.getText().length() > 0 && matchNumberInput.getText().length() > 0 && firstAlliancePartnerInput.getText().length() > 0
                    && secondAlliancePartnerInput.getText().length() > 0)
                        startButton.setEnabled(true);
                }
                else {
                    startButton.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });

        matchNumberInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (matchNumberInput.getText().length() > 0) {
                    clearButton.setEnabled(true);
                    if (ScouterNameInput.getText().length() > 0 && teamNumberInput.getText().length() > 0 && firstAlliancePartnerInput.getText().length() > 0
                            && secondAlliancePartnerInput.getText().length() > 0)
                        startButton.setEnabled(true);
                }
                else {
                    startButton.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });

        teamNumberInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (teamNumberInput.getText().length() > 0) {
                    clearButton.setEnabled(true);
                    if (ScouterNameInput.getText().length() > 0 && matchNumberInput.getText().length() > 0 && firstAlliancePartnerInput.getText().length() > 0
                            && secondAlliancePartnerInput.getText().length() > 0)
                        startButton.setEnabled(true);
                }
                else {
                    startButton.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });

        firstAlliancePartnerInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (firstAlliancePartnerInput.getText().length() > 0) {
                    clearButton.setEnabled(true);
                    if (ScouterNameInput.getText().length() > 0 && matchNumberInput.getText().length() > 0 && teamNumberInput.getText().length() > 0
                            && secondAlliancePartnerInput.getText().length() > 0)
                        startButton.setEnabled(true);
                }
                else {
                    startButton.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });

        secondAlliancePartnerInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (secondAlliancePartnerInput.getText().length() > 0) {
                    clearButton.setEnabled(true);
                    if (ScouterNameInput.getText().length() > 0 && matchNumberInput.getText().length() > 0 && teamNumberInput.getText().length() > 0
                            && firstAlliancePartnerInput.getText().length() > 0)
                        startButton.setEnabled(true);
                }
                else {
                    startButton.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });



        //set listener for QR Code generator

        startButton.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {
                //loading popup
                Log.d("statement","detected start");
                final AlertDialog.Builder loadingDialog = new AlertDialog.Builder(MainActivity.this);
                View view2 = getLayoutInflater().inflate(R.layout.loadingpopup, null);
                loadingDialog.setView(view2);
                final AlertDialog loadingdialog = loadingDialog.create();
                Log.d("statement","loading dialog created");
                loadingdialog.show();
                if (isQRButton) {

                    QRValue = ScouterNameInput.getText().toString() + "," + matchNumberInput.getText().toString()

                            + "," + teamNumberInput.getText().toString() + ","

                            + firstAlliancePartnerInput.getText().toString() + ","

                            + secondAlliancePartnerInput.getText().toString() + ","
                            + isRedAlliance + "," + isBlueAlliance + "," + noShowStatus + "," + ' ' + ","
                            + 0 + "," +  0 + "," + 0 + "," + 0 + "," + 0 + ",";

                    try {
                        bitmap = TextToImageEncode(QRValue);
                        final AlertDialog.Builder qrDialog = new AlertDialog.Builder(MainActivity.this);
                        View view1 = getLayoutInflater().inflate(R.layout.qr_popup, null);
                        ImageView imageView = view1.findViewById(R.id.imageView);
                        Button goBackToMain = view1.findViewById(R.id.GoBackButton);
                        imageView.setImageBitmap(bitmap);
                        qrDialog.setView(view1);
                        final AlertDialog dialog = qrDialog.create();
                        loadingdialog.cancel();
                        dialog.show();

                        goBackToMain.setOnClickListener(new View.OnClickListener() {
                            @Override

                            public void onClick(View view) {
                                dialog.cancel();
                            }
                        });


                    } catch (WriterException e) {
                        e.printStackTrace();

                    }

                }
            }

        });

    }



    /*
    //setters

    public void setScouterName(String newString) { this.scouterName = newString; }



    public void setMatchNumber(int newInt) { this.matchNumber = newInt; }



    public void setTeamNumber(int newInt) { this.teamNumber = newInt; }



    public void setFirstAlliancePartner(int newInt) { this.firstAlliancePartner = newInt; }



    public void setSecondAlliancePartner(int newInt) { this.secondAlliancePartner = newInt; }



    public void setNoShowStatus(int newInt) { this.noShowStatus = newInt; }



    public void setIsBlueAlliance(int newInt) { this.isBlueAlliance = newInt; }



    public void setIsRedAlliance(int newInt) { this.isRedAlliance = newInt; }



    public void setIsSetupPanel(int newInt) { this.isSetupPanel = newInt; }



    public void setIsSetupCargo(int newInt) { this.isSetupCargo = newInt; }



    public void setisResetLocalStorageClicked(boolean newBool) { this.isResetLocalStorageClicked = newBool; }



    public void setStartL1 (int newInt) { this.startL1 = newInt; }



    public void setStartC1 (int newInt) { this.startC1 = newInt; }



    public void setStartR1 (int newInt) { this.startR1 = newInt; }



    public void setStartL2 (int newInt) { this.startL2 = newInt; }



    public void setStartR2 (int newInt) { this.startR2 = newInt; }



    //getters

    public String getScouterName() { return this.scouterName; }



    public int getMatchNumber() { return this.matchNumber; }



    public int getTeamNumber() { return this.teamNumber; }



    public int getFirstAlliancePartner() { return this.firstAlliancePartner; }



    public int getSecondAlliancePartner() { return this.secondAlliancePartner; }



    public int getNoShowStatus() { return this.noShowStatus; }



    public int getIsBlueAlliance() { return this.isBlueAlliance; }



    public int getIsRedAlliance() { return this.isRedAlliance; }



    public int getIsSetupPanel() { return this.isSetupPanel; }



    public int getIsSetupCargo() { return this.isSetupCargo; }



    public int getStartL1 () { return this.startL1; }



    public int getStartC1 () { return this.startC1; }



    public int getStartR1 () { return this.startR1; }



    public int getStartL2 () { return this.startL2; }



    public int getStartR2 () { return this.startR2; }
    */


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



    public void SettingsClick (View view) {

        NavUtils.navigateUpFromSameTask(this);

    }



    public void ClearClick (View view) {

        ScouterNameInput.setText("");

        matchNumberInput.setText("");

        teamNumberInput.setText("");

        firstAlliancePartnerInput.setText("");

        secondAlliancePartnerInput.setText("");



        blueDefault();

        redDefault();

        panelDefault();

        cargoDefault();

    }




    //do circle stuff for this

    public void LL1Click (View view) {

        startL1 = 1;
        startC1 = 0;
        startR1 = 0;
        startL2 = 0;
        startR2 = 0;

        //make circle visible
        LL1Circle.setVisibility(View.VISIBLE);
        LC1Circle.setVisibility(View.INVISIBLE);
        LR1Circle.setVisibility(View.INVISIBLE);
        LL2Circle.setVisibility(View.INVISIBLE);
        LR2Circle.setVisibility(View.INVISIBLE);

        RL1Circle.setVisibility(View.INVISIBLE);
        RC1Circle.setVisibility(View.INVISIBLE);
        RR1Circle.setVisibility(View.INVISIBLE);
        RL2Circle.setVisibility(View.INVISIBLE);
        RR2Circle.setVisibility(View.INVISIBLE);
    }



    public void LC1Click (View view) {

        startL1 = 0;
        startC1 = 1;
        startR1 = 0;
        startL2 = 0;
        startR2 = 0;

        //make circle visible
        LL1Circle.setVisibility(View.INVISIBLE);
        LC1Circle.setVisibility(View.VISIBLE);
        LR1Circle.setVisibility(View.INVISIBLE);
        LL2Circle.setVisibility(View.INVISIBLE);
        LR2Circle.setVisibility(View.INVISIBLE);

        RL1Circle.setVisibility(View.INVISIBLE);
        RC1Circle.setVisibility(View.INVISIBLE);
        RR1Circle.setVisibility(View.INVISIBLE);
        RL2Circle.setVisibility(View.INVISIBLE);
        RR2Circle.setVisibility(View.INVISIBLE);
    }



    public void LR1Click (View view) {

        startL1 = 0;
        startC1 = 0;
        startR1 = 1;
        startL2 = 0;
        startR2 = 0;

        //make circle visible
        LL1Circle.setVisibility(View.INVISIBLE);
        LC1Circle.setVisibility(View.INVISIBLE);
        LR1Circle.setVisibility(View.VISIBLE);
        LL2Circle.setVisibility(View.INVISIBLE);
        LR2Circle.setVisibility(View.INVISIBLE);

        RL1Circle.setVisibility(View.INVISIBLE);
        RC1Circle.setVisibility(View.INVISIBLE);
        RR1Circle.setVisibility(View.INVISIBLE);
        RL2Circle.setVisibility(View.INVISIBLE);
        RR2Circle.setVisibility(View.INVISIBLE);
    }



    public void LL2Click (View view) {

        startL1 = 0;
        startC1 = 0;
        startR1 = 0;
        startL2 = 1;
        startR2 = 0;

        //make circle visible
        LL1Circle.setVisibility(View.INVISIBLE);
        LC1Circle.setVisibility(View.INVISIBLE);
        LR1Circle.setVisibility(View.INVISIBLE);
        LL2Circle.setVisibility(View.VISIBLE);
        LR2Circle.setVisibility(View.INVISIBLE);

        RL1Circle.setVisibility(View.INVISIBLE);
        RC1Circle.setVisibility(View.INVISIBLE);
        RR1Circle.setVisibility(View.INVISIBLE);
        RL2Circle.setVisibility(View.INVISIBLE);
        RR2Circle.setVisibility(View.INVISIBLE);
    }



    public void LR2Click (View view) {

        startL1 = 0;
        startC1 = 0;
        startR1 = 0;
        startL2 = 0;
        startR2 = 1;

        //make circle visible
        LL1Circle.setVisibility(View.INVISIBLE);
        LC1Circle.setVisibility(View.INVISIBLE);
        LR1Circle.setVisibility(View.INVISIBLE);
        LL2Circle.setVisibility(View.INVISIBLE);
        LR2Circle.setVisibility(View.VISIBLE);

        RL1Circle.setVisibility(View.INVISIBLE);
        RC1Circle.setVisibility(View.INVISIBLE);
        RR1Circle.setVisibility(View.INVISIBLE);
        RL2Circle.setVisibility(View.INVISIBLE);
        RR2Circle.setVisibility(View.INVISIBLE);
    }

    public void RL1Click (View view) {

        startL1 = 1;
        startC1 = 0;
        startR1 = 0;
        startL2 = 0;
        startR2 = 0;

        //make circle visible
        LL1Circle.setVisibility(View.INVISIBLE);
        LC1Circle.setVisibility(View.INVISIBLE);
        LR1Circle.setVisibility(View.INVISIBLE);
        LL2Circle.setVisibility(View.INVISIBLE);
        LR2Circle.setVisibility(View.INVISIBLE);

        RL1Circle.setVisibility(View.VISIBLE);
        RC1Circle.setVisibility(View.INVISIBLE);
        RR1Circle.setVisibility(View.INVISIBLE);
        RL2Circle.setVisibility(View.INVISIBLE);
        RR2Circle.setVisibility(View.INVISIBLE);
    }



    public void RC1Click (View view) {

        startL1 = 0;
        startC1 = 1;
        startR1 = 0;
        startL2 = 0;
        startR2 = 0;

        //make circle visible
        LL1Circle.setVisibility(View.INVISIBLE);
        LC1Circle.setVisibility(View.INVISIBLE);
        LR1Circle.setVisibility(View.INVISIBLE);
        LL2Circle.setVisibility(View.INVISIBLE);
        LR2Circle.setVisibility(View.INVISIBLE);

        RL1Circle.setVisibility(View.INVISIBLE);
        RC1Circle.setVisibility(View.VISIBLE);
        RR1Circle.setVisibility(View.INVISIBLE);
        RL2Circle.setVisibility(View.INVISIBLE);
        RR2Circle.setVisibility(View.INVISIBLE);
    }



    public void RR1Click (View view) {

        startL1 = 0;
        startC1 = 0;
        startR1 = 1;
        startL2 = 0;
        startR2 = 0;

        //make circle visible
        LL1Circle.setVisibility(View.INVISIBLE);
        LC1Circle.setVisibility(View.INVISIBLE);
        LR1Circle.setVisibility(View.INVISIBLE);
        LL2Circle.setVisibility(View.INVISIBLE);
        LR2Circle.setVisibility(View.INVISIBLE);

        RL1Circle.setVisibility(View.INVISIBLE);
        RC1Circle.setVisibility(View.INVISIBLE);
        RR1Circle.setVisibility(View.VISIBLE);
        RL2Circle.setVisibility(View.INVISIBLE);
        RR2Circle.setVisibility(View.INVISIBLE);
    }



    public void RL2Click (View view) {

        startL1 = 0;
        startC1 = 0;
        startR1 = 0;
        startL2 = 1;
        startR2 = 0;

        //make circle visible
        LL1Circle.setVisibility(View.INVISIBLE);
        LC1Circle.setVisibility(View.INVISIBLE);
        LR1Circle.setVisibility(View.INVISIBLE);
        LL2Circle.setVisibility(View.INVISIBLE);
        LR2Circle.setVisibility(View.INVISIBLE);

        RL1Circle.setVisibility(View.INVISIBLE);
        RC1Circle.setVisibility(View.INVISIBLE);
        RR1Circle.setVisibility(View.INVISIBLE);
        RL2Circle.setVisibility(View.VISIBLE);
        RR2Circle.setVisibility(View.INVISIBLE);
    }



    public void RR2Click (View view) {

        startL1 = 0;
        startC1 = 0;
        startR1 = 0;
        startL2 = 0;
        startR2 = 1;

        //make circle visible
        LL1Circle.setVisibility(View.INVISIBLE);
        LC1Circle.setVisibility(View.INVISIBLE);
        LR1Circle.setVisibility(View.INVISIBLE);
        LL2Circle.setVisibility(View.INVISIBLE);
        LR2Circle.setVisibility(View.INVISIBLE);

        RL1Circle.setVisibility(View.INVISIBLE);
        RC1Circle.setVisibility(View.INVISIBLE);
        RR1Circle.setVisibility(View.INVISIBLE);
        RL2Circle.setVisibility(View.INVISIBLE);
        RR2Circle.setVisibility(View.VISIBLE);
    }


    public void blueClick (View view) {
        Log.d("leftorright", "LeftOrRight: " + leftOrRight);
        if (noShowStatus == 0) {
            if (leftOrRight.equals("Left") || leftOrRight.equals("Right")) {
                if (isBlueAlliance == 0) {
                    blueButton.setBackgroundColor(getResources().getColor(R.color.blue));
                    blueButton.setTextColor(getResources().getColor(R.color.light));
                    isBlueAlliance = 1;
                    redDefault();

                    if (leftOrRight.equals("Left")) {
                        //set left boxes to blue
                        LL1.setBackgroundColor(getResources().getColor(R.color.blue));
                        LC1.setBackgroundColor(getResources().getColor(R.color.blue));
                        LR1.setBackgroundColor(getResources().getColor(R.color.blue));
                        LL2.setBackgroundColor(getResources().getColor(R.color.blue));
                        LR2.setBackgroundColor(getResources().getColor(R.color.blue));


                        //make the boxes visible
                        LL1.setVisibility(View.VISIBLE);
                        LC1.setVisibility(View.VISIBLE);
                        LR1.setVisibility(View.VISIBLE);
                        LL2.setVisibility(View.VISIBLE);
                        LR2.setVisibility(View.VISIBLE);

                        RL1.setVisibility(View.INVISIBLE);
                        RC1.setVisibility(View.INVISIBLE);
                        RR1.setVisibility(View.INVISIBLE);
                        RL2.setVisibility(View.INVISIBLE);
                        RR2.setVisibility(View.INVISIBLE);

                        LL1Circle.setVisibility(View.INVISIBLE);
                        LC1Circle.setVisibility(View.INVISIBLE);
                        LR1Circle.setVisibility(View.INVISIBLE);
                        LL2Circle.setVisibility(View.INVISIBLE);
                        LR2Circle.setVisibility(View.INVISIBLE);

                        RL1Circle.setVisibility(View.INVISIBLE);
                        RC1Circle.setVisibility(View.INVISIBLE);
                        RR1Circle.setVisibility(View.INVISIBLE);
                        RL2Circle.setVisibility(View.INVISIBLE);
                        RR2Circle.setVisibility(View.INVISIBLE);
                    } else if (leftOrRight.equals("Right")) {
                        //set right boxes to blue
                        RL1.setBackgroundColor(getResources().getColor(R.color.blue));
                        RC1.setBackgroundColor(getResources().getColor(R.color.blue));
                        RR1.setBackgroundColor(getResources().getColor(R.color.blue));
                        RL2.setBackgroundColor(getResources().getColor(R.color.blue));
                        RR2.setBackgroundColor(getResources().getColor(R.color.blue));

                        //make the boxes visible
                        RL1.setVisibility(View.VISIBLE);
                        RC1.setVisibility(View.VISIBLE);
                        RR1.setVisibility(View.VISIBLE);
                        RL2.setVisibility(View.VISIBLE);
                        RR2.setVisibility(View.VISIBLE);
                        LL1.setVisibility(View.INVISIBLE);
                        LC1.setVisibility(View.INVISIBLE);
                        LR1.setVisibility(View.INVISIBLE);
                        LL2.setVisibility(View.INVISIBLE);
                        LR2.setVisibility(View.INVISIBLE);

                        LL1Circle.setVisibility(View.INVISIBLE);
                        LC1Circle.setVisibility(View.INVISIBLE);
                        LR1Circle.setVisibility(View.INVISIBLE);
                        LL2Circle.setVisibility(View.INVISIBLE);
                        LR2Circle.setVisibility(View.INVISIBLE);

                        RL1Circle.setVisibility(View.INVISIBLE);
                        RC1Circle.setVisibility(View.INVISIBLE);
                        RR1Circle.setVisibility(View.INVISIBLE);
                        RL2Circle.setVisibility(View.INVISIBLE);
                        RR2Circle.setVisibility(View.INVISIBLE);

                    } else {

                        blueDefault();
                        startButton.setEnabled(false);
                        //make boxes invisible
                        RL1.setVisibility(View.INVISIBLE);
                        RC1.setVisibility(View.INVISIBLE);
                        RR1.setVisibility(View.INVISIBLE);
                        RL2.setVisibility(View.INVISIBLE);
                        RR2.setVisibility(View.INVISIBLE);
                        LL1.setVisibility(View.INVISIBLE);
                        LC1.setVisibility(View.INVISIBLE);
                        LR1.setVisibility(View.INVISIBLE);
                        LL2.setVisibility(View.INVISIBLE);
                        LR2.setVisibility(View.INVISIBLE);

                        LL1Circle.setVisibility(View.INVISIBLE);
                        LC1Circle.setVisibility(View.INVISIBLE);
                        LR1Circle.setVisibility(View.INVISIBLE);
                        LL2Circle.setVisibility(View.INVISIBLE);
                        LR2Circle.setVisibility(View.INVISIBLE);

                        RL1Circle.setVisibility(View.INVISIBLE);
                        RC1Circle.setVisibility(View.INVISIBLE);
                        RR1Circle.setVisibility(View.INVISIBLE);
                        RL2Circle.setVisibility(View.INVISIBLE);
                        RR2Circle.setVisibility(View.INVISIBLE);
                    }
                } else {
                    RL1.setVisibility(View.INVISIBLE);
                    RC1.setVisibility(View.INVISIBLE);
                    RR1.setVisibility(View.INVISIBLE);
                    RL2.setVisibility(View.INVISIBLE);
                    RR2.setVisibility(View.INVISIBLE);
                    LL1.setVisibility(View.INVISIBLE);
                    LC1.setVisibility(View.INVISIBLE);
                    LR1.setVisibility(View.INVISIBLE);
                    LL2.setVisibility(View.INVISIBLE);
                    LR2.setVisibility(View.INVISIBLE);

                    LL1Circle.setVisibility(View.INVISIBLE);
                    LC1Circle.setVisibility(View.INVISIBLE);
                    LR1Circle.setVisibility(View.INVISIBLE);
                    LL2Circle.setVisibility(View.INVISIBLE);
                    LR2Circle.setVisibility(View.INVISIBLE);

                    RL1Circle.setVisibility(View.INVISIBLE);
                    RC1Circle.setVisibility(View.INVISIBLE);
                    RR1Circle.setVisibility(View.INVISIBLE);
                    RL2Circle.setVisibility(View.INVISIBLE);
                    RR2Circle.setVisibility(View.INVISIBLE);
                }
            }
            else {
                //leftOrRight is ""
                Toast.makeText(MainActivity.this, "You haven't selected \"Left\" or \"Right\" in the Settings screen", Toast.LENGTH_SHORT).show();

                LL1Circle.setVisibility(View.INVISIBLE);
                LC1Circle.setVisibility(View.INVISIBLE);
                LR1Circle.setVisibility(View.INVISIBLE);
                LL2Circle.setVisibility(View.INVISIBLE);
                LR2Circle.setVisibility(View.INVISIBLE);

                RL1Circle.setVisibility(View.INVISIBLE);
                RC1Circle.setVisibility(View.INVISIBLE);
                RR1Circle.setVisibility(View.INVISIBLE);
                RL2Circle.setVisibility(View.INVISIBLE);
                RR2Circle.setVisibility(View.INVISIBLE);

                LL1.setVisibility(View.INVISIBLE);
                LC1.setVisibility(View.INVISIBLE);
                LR1.setVisibility(View.INVISIBLE);
                LL2.setVisibility(View.INVISIBLE);
                LR2.setVisibility(View.INVISIBLE);

                RL1.setVisibility(View.INVISIBLE);
                RC1.setVisibility(View.INVISIBLE);
                RR1.setVisibility(View.INVISIBLE);
                RL2.setVisibility(View.INVISIBLE);
                RR2.setVisibility(View.INVISIBLE);
            }
        }
    }


    public void redClick (View view) {
        if (noShowStatus == 0) {
                if (leftOrRight.equals("Left") || leftOrRight.equals("Right")) {
                    if (isRedAlliance == 0) {

                        redButton.setBackgroundColor(getResources().getColor(R.color.red));

                        redButton.setTextColor(getResources().getColor(R.color.light));

                        isRedAlliance = 1;

                        blueDefault();

                        if (leftOrRight.equals("Right")) {
                            //set left boxes to blue
                            LL1.setBackgroundColor(getResources().getColor(R.color.red));
                            LC1.setBackgroundColor(getResources().getColor(R.color.red));
                            LR1.setBackgroundColor(getResources().getColor(R.color.red));
                            LL2.setBackgroundColor(getResources().getColor(R.color.red));
                            LR2.setBackgroundColor(getResources().getColor(R.color.red));


                            //make the boxes visible
                            LL1.setVisibility(View.VISIBLE);
                            LC1.setVisibility(View.VISIBLE);
                            LR1.setVisibility(View.VISIBLE);
                            LL2.setVisibility(View.VISIBLE);
                            LR2.setVisibility(View.VISIBLE);

                            RL1.setVisibility(View.INVISIBLE);
                            RC1.setVisibility(View.INVISIBLE);
                            RR1.setVisibility(View.INVISIBLE);
                            RL2.setVisibility(View.INVISIBLE);
                            RR2.setVisibility(View.INVISIBLE);

                            LL1Circle.setVisibility(View.INVISIBLE);
                            LC1Circle.setVisibility(View.INVISIBLE);
                            LR1Circle.setVisibility(View.INVISIBLE);
                            LL2Circle.setVisibility(View.INVISIBLE);
                            LR2Circle.setVisibility(View.INVISIBLE);

                            RL1Circle.setVisibility(View.INVISIBLE);
                            RC1Circle.setVisibility(View.INVISIBLE);
                            RR1Circle.setVisibility(View.INVISIBLE);
                            RL2Circle.setVisibility(View.INVISIBLE);
                            RR2Circle.setVisibility(View.INVISIBLE);
                        } else if (leftOrRight.equals("Left")) {
                            //set right boxes to blue
                            RL1.setBackgroundColor(getResources().getColor(R.color.red));
                            RC1.setBackgroundColor(getResources().getColor(R.color.red));
                            RR1.setBackgroundColor(getResources().getColor(R.color.red));
                            RL2.setBackgroundColor(getResources().getColor(R.color.red));
                            RR2.setBackgroundColor(getResources().getColor(R.color.red));

                            //make the boxes visible
                            RL1.setVisibility(View.VISIBLE);
                            RC1.setVisibility(View.VISIBLE);
                            RR1.setVisibility(View.VISIBLE);
                            RL2.setVisibility(View.VISIBLE);
                            RR2.setVisibility(View.VISIBLE);

                            LL1.setVisibility(View.INVISIBLE);
                            LC1.setVisibility(View.INVISIBLE);
                            LR1.setVisibility(View.INVISIBLE);
                            LL2.setVisibility(View.INVISIBLE);
                            LR2.setVisibility(View.INVISIBLE);

                            LL1Circle.setVisibility(View.INVISIBLE);
                            LC1Circle.setVisibility(View.INVISIBLE);
                            LR1Circle.setVisibility(View.INVISIBLE);
                            LL2Circle.setVisibility(View.INVISIBLE);
                            LR2Circle.setVisibility(View.INVISIBLE);

                            RL1Circle.setVisibility(View.INVISIBLE);
                            RC1Circle.setVisibility(View.INVISIBLE);
                            RR1Circle.setVisibility(View.INVISIBLE);
                            RL2Circle.setVisibility(View.INVISIBLE);
                            RR2Circle.setVisibility(View.INVISIBLE);

                    } else {

                        redDefault();

                        startButton.setEnabled(false);

                        //make boxes invisible

                        RL1.setVisibility(View.INVISIBLE);

                        RC1.setVisibility(View.INVISIBLE);

                        RR1.setVisibility(View.INVISIBLE);

                        RL2.setVisibility(View.INVISIBLE);

                        RR2.setVisibility(View.INVISIBLE);

                        LL1.setVisibility(View.INVISIBLE);

                        LC1.setVisibility(View.INVISIBLE);

                        LR1.setVisibility(View.INVISIBLE);

                        LL2.setVisibility(View.INVISIBLE);

                        LR2.setVisibility(View.INVISIBLE);

                        LL1Circle.setVisibility(View.INVISIBLE);
                        LC1Circle.setVisibility(View.INVISIBLE);
                        LR1Circle.setVisibility(View.INVISIBLE);
                        LL2Circle.setVisibility(View.INVISIBLE);
                        LR2Circle.setVisibility(View.INVISIBLE);

                        RL1Circle.setVisibility(View.INVISIBLE);
                        RC1Circle.setVisibility(View.INVISIBLE);
                        RR1Circle.setVisibility(View.INVISIBLE);
                        RL2Circle.setVisibility(View.INVISIBLE);
                        RR2Circle.setVisibility(View.INVISIBLE);
                    }
                } else if (isRedAlliance == 1){
                    RL1.setVisibility(View.INVISIBLE);

                    RC1.setVisibility(View.INVISIBLE);

                    RR1.setVisibility(View.INVISIBLE);

                    RL2.setVisibility(View.INVISIBLE);

                    RR2.setVisibility(View.INVISIBLE);

                    LL1.setVisibility(View.INVISIBLE);

                    LC1.setVisibility(View.INVISIBLE);

                    LR1.setVisibility(View.INVISIBLE);

                    LL2.setVisibility(View.INVISIBLE);

                    LR2.setVisibility(View.INVISIBLE);

                    LL1Circle.setVisibility(View.INVISIBLE);
                    LC1Circle.setVisibility(View.INVISIBLE);
                    LR1Circle.setVisibility(View.INVISIBLE);
                    LL2Circle.setVisibility(View.INVISIBLE);
                    LR2Circle.setVisibility(View.INVISIBLE);

                    RL1Circle.setVisibility(View.INVISIBLE);
                    RC1Circle.setVisibility(View.INVISIBLE);
                    RR1Circle.setVisibility(View.INVISIBLE);
                    RL2Circle.setVisibility(View.INVISIBLE);
                    RR2Circle.setVisibility(View.INVISIBLE);
                }
            }
            else {
                    //leftOrRight is ""
                    Toast.makeText(MainActivity.this, "You haven't selected \"Left\" or \"Right\" in the Settings screen", Toast.LENGTH_SHORT).show();

                    LL1Circle.setVisibility(View.INVISIBLE);
                    LC1Circle.setVisibility(View.INVISIBLE);
                    LR1Circle.setVisibility(View.INVISIBLE);
                    LL2Circle.setVisibility(View.INVISIBLE);
                    LR2Circle.setVisibility(View.INVISIBLE);

                    RL1Circle.setVisibility(View.INVISIBLE);
                    RC1Circle.setVisibility(View.INVISIBLE);
                    RR1Circle.setVisibility(View.INVISIBLE);
                    RL2Circle.setVisibility(View.INVISIBLE);
                    RR2Circle.setVisibility(View.INVISIBLE);

                    LL1.setVisibility(View.INVISIBLE);
                    LC1.setVisibility(View.INVISIBLE);
                    LR1.setVisibility(View.INVISIBLE);
                    LL2.setVisibility(View.INVISIBLE);
                    LR2.setVisibility(View.INVISIBLE);

                    RL1.setVisibility(View.INVISIBLE);
                    RC1.setVisibility(View.INVISIBLE);
                    RR1.setVisibility(View.INVISIBLE);
                    RL2.setVisibility(View.INVISIBLE);
                    RR2.setVisibility(View.INVISIBLE);
                }

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

        public void setButtonsToFalse() {

            panelButton.setEnabled(true);

            cargoButton.setEnabled(true);

            startButton.setEnabled(false);
        }


    public void startClick (View view) {

        scouterName = ScouterNameInput.getText().toString();

        matchNumber = Integer.parseInt(matchNumberInput.getText().toString());

        teamNumber = Integer.parseInt(teamNumberInput.getText().toString());

        firstAlliancePartner = Integer.parseInt(firstAlliancePartnerInput.getText().toString());

        secondAlliancePartner = Integer.parseInt(secondAlliancePartnerInput.getText().toString());



    }



    Bitmap TextToImageEncode(String Value) throws WriterException {

        BitMatrix bitMatrix;

        try {

            bitMatrix = new MultiFormatWriter().encode(

                    Value,

                    BarcodeFormat.DATA_MATRIX.QR_CODE,

                    QRCodeSize, QRCodeSize, null

            );

        } catch (IllegalArgumentException illegalArgumentException) {

            return null;

        }

        int bitMatrixWidth = bitMatrix.getWidth();

        int bitMatrixHeight = bitMatrix.getHeight();

        int[] pixels = new int[bitMatrixWidth * bitMatrixHeight];

        for (int y = 0; y < bitMatrixHeight; y++) {
            int offset = y * bitMatrixWidth;

            for (int x = 0; x < bitMatrixWidth; x++) {

                pixels[offset + x] = bitMatrix.get(x, y) ?

                        getResources().getColor(R.color.colorPrimaryDark) : getResources().getColor(R.color.bootstrap_dropdown_divider);

            }

        }

        Bitmap bitmap = Bitmap.createBitmap(bitMatrixWidth, bitMatrixHeight, Bitmap.Config.ARGB_4444);

        bitmap.setPixels(pixels, 0, 500, 0, 0, bitMatrixWidth, bitMatrixHeight);

        return bitmap;

    }

}