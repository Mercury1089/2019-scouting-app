package com.mercury1089.scoutingapp2019;




import android.app.Activity;
import android.app.AlertDialog;
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

    //rectangles
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

    //circles
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
    public final static int QRCodeSize = 500;
    Bitmap bitmap;
    String QRValue;

    //other variables
    Button clearButton;
    Button startButton;
    TextView prepopulatedTitle;
    TextView prepopulatedDirections;

    boolean isQRButton = false;
    String leftOrRight;
    String message;



    @Override

    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initializers
        ScouterNameInput = findViewById(R.id.ScouterNameInput);
        matchNumberInput = findViewById(R.id.MatchNumberInput);
        teamNumberInput = findViewById(R.id.TeamNumberInput);
        firstAlliancePartnerInput = findViewById(R.id.FirstAlliancePartnerInput);
        secondAlliancePartnerInput = findViewById(R.id.SecondAlliancePartnerInput);
        blueButton = findViewById(R.id.BlueButton);
        redButton = findViewById(R.id.RedButton);
        panelButton = findViewById(R.id.PanelButton);
        cargoButton = findViewById(R.id.CargoButton);
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

        clearButton = findViewById(R.id.ClearButton);
        startButton = findViewById(R.id.StartButton);
        prepopulatedTitle = findViewById(R.id.IDPrepopulated);
        prepopulatedDirections = findViewById(R.id.IDPrepopulatedDirections);

        //send circles to front
        LL1Circle.bringToFront();
        LC1Circle.bringToFront();
        LR1Circle.bringToFront();
        LL2Circle.bringToFront();
        LR2Circle.bringToFront();

        //setting group buttons to default state
        blueDefault();
        redDefault();
        panelDefault();
        cargoDefault();
        enableButtonsDefault();

        //setting prepopulated text to default state
        prepopulatedTitle.setTextColor(getResources().getColor(R.color.grey));
        prepopulatedDirections.setTextColor(getResources().getColor(R.color.grey));

        //getting side from the settings to orientate the HAB
        leftOrRight = getIntent().getStringExtra("LEFTORRIGHT");
        message = "" + getIntent().getStringExtra("message");
        if (message.length() > 0 && (message.charAt(0) == 'P' || message.charAt(0) == 'C')) {
            if (message.charAt(0) == 'P') {
                isSetupPanel = 1;
                selectedButtonColors(panelButton);
                cargoDefault();
            } else if (message.charAt(0) == 'C') {
                isSetupCargo = 1;
                selectedButtonColors(cargoButton);
                panelDefault();
            }
            message = message.substring(message.indexOf(",")+1, message.length());
            for (int i = 1; i < message.length(); i++) {
                switch (i) {
                    case 1:
                        scouterName = message.substring(0, message.indexOf(","));
                        ScouterNameInput.setText(scouterName);
                        message = message.substring(message.indexOf(",")+1, message.length());
                    case 2:
                        matchNumber = Integer.parseInt(message.substring(0, message.indexOf(",")));
                        matchNumberInput.setText(String.valueOf(matchNumber));
                        message = message.substring(message.indexOf(",")+1, message.length());
                    case 3:
                        teamNumber = Integer.parseInt(message.substring(0, message.indexOf(",")));
                        teamNumberInput.setText(String.valueOf(teamNumber));
                        message = message.substring(message.indexOf(",")+1, message.length());
                    case 4:
                        firstAlliancePartner = Integer.parseInt(message.substring(0, message.indexOf(",")));
                        firstAlliancePartnerInput.setText(String.valueOf(firstAlliancePartner));
                        message = message.substring(message.indexOf(",")+1, message.length());
                    case 5:
                        secondAlliancePartner = Integer.parseInt(message.substring(0, message.indexOf(",")));
                        secondAlliancePartnerInput.setText(String.valueOf(secondAlliancePartner));
                        message = message.substring(message.indexOf(",")+1, message.length());
                    case 6:
                        String RedOrBlue = message.substring(0, message.indexOf(","));
                        message = message.substring(message.indexOf(",")+1, message.length());
                        if (RedOrBlue.equals("Red")){
                            isRedAlliance = 1;
                            isBlueAlliance = 0;
                        }
                        else if (RedOrBlue.equals("Blue")){
                            isRedAlliance = 0;
                            isBlueAlliance = 1;
                        }
                    case 7:
                        leftOrRight = message.substring(0, message.indexOf(","));
                        message = message.substring(message.indexOf(",")+1, message.length());
                        if (leftOrRight.equals("Left")) {
                            if (isBlueAlliance == 1) {
                                makeBoxesBlue("Left");
                                makeBoxesVisible("Left");
                            }
                            else if (isRedAlliance == 1) {
                                makeBoxesRed("Right");
                                makeBoxesVisible("Right");
                            }
                        }
                        else if (leftOrRight.equals("Right")){
                            if (isBlueAlliance == 1) {
                                makeBoxesBlue("Right");
                                makeBoxesVisible("Right");
                            }
                            else if (isRedAlliance == 1) {
                                makeBoxesRed("Left");
                                makeBoxesVisible("Left");
                            }
                        }
                    case 8:
                        String startingposition = message;
                        message = "";
                        makeCirclesInvisible();
                        if (isBlueAlliance == 1) {
                            if (leftOrRight.equals("Left"))
                                if (startingposition.equals("L1"))
                                    LL1Circle.setVisibility(View.VISIBLE);
                                else if (startingposition.equals("C1"))
                                    LC1Circle.setVisibility(View.VISIBLE);
                                else if (startingposition.equals("R1"))
                                    LR1Circle.setVisibility(View.VISIBLE);
                                else if (startingposition.equals("L2"))
                                    LL2Circle.setVisibility(View.VISIBLE);
                                else if (startingposition.equals("R2"))
                                    LR2Circle.setVisibility(View.VISIBLE);
                            if (leftOrRight.equals("Right"))
                                if (startingposition.equals("L1"))
                                    RL1Circle.setVisibility(View.VISIBLE);
                                else if (startingposition.equals("C1"))
                                    RC1Circle.setVisibility(View.VISIBLE);
                                else if (startingposition.equals("R1"))
                                    RR1Circle.setVisibility(View.VISIBLE);
                                else if (startingposition.equals("L2"))
                                    RL2Circle.setVisibility(View.VISIBLE);
                                else if (startingposition.equals("R2"))
                                    RR2Circle.setVisibility(View.VISIBLE);
                        }
                        else if (isRedAlliance == 1) {
                            if (leftOrRight.equals("Right"))
                                if (startingposition.equals("L1"))
                                    LL1Circle.setVisibility(View.VISIBLE);
                                else if (startingposition.equals("C1"))
                                    LC1Circle.setVisibility(View.VISIBLE);
                                else if (startingposition.equals("R1"))
                                    LR1Circle.setVisibility(View.VISIBLE);
                                else if (startingposition.equals("L2"))
                                    LL2Circle.setVisibility(View.VISIBLE);
                                else if (startingposition.equals("R2"))
                                    LR2Circle.setVisibility(View.VISIBLE);
                            if (leftOrRight.equals("Left"))
                                if (startingposition.equals("L1"))
                                    RL1Circle.setVisibility(View.VISIBLE);
                                else if (startingposition.equals("C1"))
                                    RC1Circle.setVisibility(View.VISIBLE);
                                else if (startingposition.equals("R1"))
                                    RR1Circle.setVisibility(View.VISIBLE);
                                else if (startingposition.equals("L2"))
                                    RL2Circle.setVisibility(View.VISIBLE);
                                else if (startingposition.equals("R2"))
                                    RR2Circle.setVisibility(View.VISIBLE);
                        }
                }
            }
        }

        //starting listener to check the status of the switch
        Switch NoShowSwitch = findViewById(R.id.NoShowSwitch);
        NoShowSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    noShowStatus = 1;
                    startButton.setText(R.string.GenerateQRCode);
                    isQRButton = true;
                    makeCirclesInvisible();
                    makeBoxesInvisible("Both");
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
                //to enable/disable start and cancel button
                checkIfAnythingIsTyped(ScouterNameInput);
            }
            @Override
            public void afterTextChanged(Editable s) { }
        });

        matchNumberInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //to enable/disable start and cancel button
                checkIfAnythingIsTyped(matchNumberInput);
            }
            @Override
            public void afterTextChanged(Editable s) { }
        });

        teamNumberInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //to enable/disable start and cancel button
                checkIfAnythingIsTyped(teamNumberInput);
            }
            @Override
            public void afterTextChanged(Editable s) { }
        });

        firstAlliancePartnerInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //to enable/disable start and cancel button
                checkIfAnythingIsTyped(firstAlliancePartnerInput);
            }
            @Override
            public void afterTextChanged(Editable s) { }
        });

        secondAlliancePartnerInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //to enable/disable start and cancel button
                checkIfAnythingIsTyped(secondAlliancePartnerInput);
            }
            @Override
            public void afterTextChanged(Editable s) { }
        });

        //set listener for QR Code generator
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scouterName = ScouterNameInput.getText().toString();
                matchNumber = Integer.parseInt(matchNumberInput.getText().toString());
                teamNumber = Integer.parseInt(teamNumberInput.getText().toString());
                firstAlliancePartner = Integer.parseInt(firstAlliancePartnerInput.getText().toString());
                secondAlliancePartner = Integer.parseInt(secondAlliancePartnerInput.getText().toString());

                if (isQRButton) {
                    String RedOrBlue = "";
                    if (isBlueAlliance == 1)
                        RedOrBlue = "Blue";
                    else if (isRedAlliance == 1)
                        RedOrBlue = "Red";
                    QRValue = ScouterNameInput.getText().toString() + "," + teamNumberInput.getText().toString()
                            + "," + matchNumberInput.getText().toString() + ","
                            + firstAlliancePartnerInput.getText().toString() + ","
                            + secondAlliancePartnerInput.getText().toString() + ","
                            + RedOrBlue + "," + leftOrRight + ",,";
                    try {
                        bitmap = TextToImageEncode(QRValue);
                        final AlertDialog.Builder qrDialog = new AlertDialog.Builder(MainActivity.this);
                        View view1 = getLayoutInflater().inflate(R.layout.qr_popup, null);
                        ImageView imageView = view1.findViewById(R.id.imageView);
                        Button goBackToMain = view1.findViewById(R.id.GoBackButton);

                        imageView.setImageBitmap(bitmap);
                        qrDialog.setView(view1);

                        final AlertDialog dialog = qrDialog.create();
                        dialog.show();
                        goBackToMain.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.cancel();
                            }
                        });

                    } catch (WriterException e) { e.printStackTrace(); }
                } else {
                    scouterName = ScouterNameInput.getText().toString();
                    matchNumber = Integer.parseInt(matchNumberInput.getText().toString());
                    teamNumber = Integer.parseInt(teamNumberInput.getText().toString());
                    firstAlliancePartner = Integer.parseInt(firstAlliancePartnerInput.getText().toString());
                    secondAlliancePartner = Integer.parseInt(secondAlliancePartnerInput.getText().toString());
                    String RedOrBlue = "";
                    if (isRedAlliance == 1)
                        RedOrBlue = "Red";
                    else if (isBlueAlliance == 1)
                        RedOrBlue = "Blue";
                    String startingPosition = "";
                    if (startL1 == 1)
                        startingPosition = "L1";
                    else if (startC1 == 1)
                        startingPosition = "C1";
                    else if (startR1 == 1)
                        startingPosition = "R1";
                    else if (startL2 == 1)
                        startingPosition = "L2";
                    else if (startR2 == 1)
                        startingPosition = "R2";
                    String sendMessage = scouterName + "," + matchNumber + "," + teamNumber
                            + "," + firstAlliancePartner + "," + secondAlliancePartner
                            + "," + RedOrBlue + "," + leftOrRight + "," + startingPosition;
                    char initPanelOrCargo = ' ';
                    if (isSetupPanel == 1)
                        initPanelOrCargo = 'P';
                    else if (isSetupCargo == 1)
                        initPanelOrCargo = 'C';
                    sendMessage = initPanelOrCargo + "," + sendMessage;
                    Intent intent = new Intent(MainActivity.this, Sandstorm.class);
                    intent.putExtra("message", sendMessage);
                    startActivity(intent);
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

    public void makeCirclesInvisible() {
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

    public void startPositionDefault() {
        startL1 = 0;
        startC1 = 0;
        startR1 = 0;
        startL2 = 0;
        startR2 = 0;
    }

    public void makeBoxesInvisible (String side) {
        switch (side) {
            case "Both":
            case "Left":
                LL1.setVisibility(View.INVISIBLE);
                LC1.setVisibility(View.INVISIBLE);
                LR1.setVisibility(View.INVISIBLE);
                LL2.setVisibility(View.INVISIBLE);
                LR2.setVisibility(View.INVISIBLE);
                if (side.equals("Left"))
                    break;
            case "Right":
                RL1.setVisibility(View.INVISIBLE);
                RC1.setVisibility(View.INVISIBLE);
                RR1.setVisibility(View.INVISIBLE);
                RL2.setVisibility(View.INVISIBLE);
                RR2.setVisibility(View.INVISIBLE);
        }
    }

    public void makeBoxesVisible (String side) {
        switch (side) {
            case "Both":
            case "Left":
                LL1.setVisibility(View.VISIBLE);
                LC1.setVisibility(View.VISIBLE);
                LR1.setVisibility(View.VISIBLE);
                LL2.setVisibility(View.VISIBLE);
                LR2.setVisibility(View.VISIBLE);
                if (side.equals("Left"))
                    break;
            case "Right":
                RL1.setVisibility(View.VISIBLE);
                RC1.setVisibility(View.VISIBLE);
                RR1.setVisibility(View.VISIBLE);
                RL2.setVisibility(View.VISIBLE);
                RR2.setVisibility(View.VISIBLE);
        }
    }

    public void makeBoxesRed (String side) {
        if (side.equals("Right")){
            RL1.setBackgroundColor(getResources().getColor(R.color.red));
            RC1.setBackgroundColor(getResources().getColor(R.color.red));
            RR1.setBackgroundColor(getResources().getColor(R.color.red));
            RL2.setBackgroundColor(getResources().getColor(R.color.red));
            RR2.setBackgroundColor(getResources().getColor(R.color.red));
        }
        else {
            LL1.setBackgroundColor(getResources().getColor(R.color.red));
            LC1.setBackgroundColor(getResources().getColor(R.color.red));
            LR1.setBackgroundColor(getResources().getColor(R.color.red));
            LL2.setBackgroundColor(getResources().getColor(R.color.red));
            LR2.setBackgroundColor(getResources().getColor(R.color.red));
        }
    }

    public void makeBoxesBlue (String side) {
        if (side.equals("Right")){
            RL1.setBackgroundColor(getResources().getColor(R.color.blue));
            RC1.setBackgroundColor(getResources().getColor(R.color.blue));
            RR1.setBackgroundColor(getResources().getColor(R.color.blue));
            RL2.setBackgroundColor(getResources().getColor(R.color.blue));
            RR2.setBackgroundColor(getResources().getColor(R.color.blue));
        }
        else {
            LL1.setBackgroundColor(getResources().getColor(R.color.blue));
            LC1.setBackgroundColor(getResources().getColor(R.color.blue));
            LR1.setBackgroundColor(getResources().getColor(R.color.blue));
            LL2.setBackgroundColor(getResources().getColor(R.color.blue));
            LR2.setBackgroundColor(getResources().getColor(R.color.blue));
        }
    }

    public void selectedButtonColors(BootstrapButton button) {
        button.setBackgroundColor(getResources().getColor(R.color.orange));
        button.setTextColor(getResources().getColor(R.color.light));
    }

    public void checkIfAnythingIsTyped (EditText editText) {
        if (editText.getText().length() > 0) {
            clearButton.setEnabled(true);
            if (ScouterNameInput.getText().length() > 0
                    && teamNumberInput.getText().length() > 0
                    && matchNumberInput.getText().length() > 0
                    && firstAlliancePartnerInput.getText().length() > 0
                    && secondAlliancePartnerInput.getText().length() > 0)
                startButton.setEnabled(true);
        }
        else
            startButton.setEnabled(false);
    }


    //click methods

    public void SettingsClick (View view) { NavUtils.navigateUpFromSameTask(this); }

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

    public void LL1Click (View view) {
        startPositionDefault();
        startL1 = 1;

        makeCirclesInvisible();
        LL1Circle.setVisibility(View.VISIBLE);
    }



    public void LC1Click (View view) {
        startPositionDefault();
        startC1 = 1;

        makeCirclesInvisible();
        LC1Circle.setVisibility(View.VISIBLE);
    }



    public void LR1Click (View view) {
        startPositionDefault();
        startR1 = 1;

        makeCirclesInvisible();
        LR1Circle.setVisibility(View.VISIBLE);
    }



    public void LL2Click (View view) {
        startPositionDefault();
        startL2 = 1;

        makeCirclesInvisible();
        LL2Circle.setVisibility(View.VISIBLE);
    }



    public void LR2Click (View view) {
        startPositionDefault();
        startR2 = 1;

        makeCirclesInvisible();
        LR2Circle.setVisibility(View.VISIBLE);
    }

    public void RL1Click (View view) {
        startPositionDefault();
        startL1 = 1;

        makeCirclesInvisible();
        RL1Circle.setVisibility(View.VISIBLE);
    }



    public void RC1Click (View view) {
        startPositionDefault();
        startC1 = 1;

        makeCirclesInvisible();
        RC1Circle.setVisibility(View.VISIBLE);
    }



    public void RR1Click (View view) {
        startPositionDefault();
        startR1 = 1;

        makeCirclesInvisible();
        RR1Circle.setVisibility(View.VISIBLE);
    }



    public void RL2Click (View view) {
        startPositionDefault();
        startL2 = 1;

        makeCirclesInvisible();
        RL2Circle.setVisibility(View.VISIBLE);
    }



    public void RR2Click (View view) {
        startPositionDefault();
        startR2 = 1;

        makeCirclesInvisible();
        RR2Circle.setVisibility(View.VISIBLE);
    }


    public void blueClick (View view) {
        if (isBlueAlliance == 0) {
            blueButton.setBackgroundColor(getResources().getColor(R.color.blue));
            blueButton.setTextColor(getResources().getColor(R.color.light));
            isBlueAlliance = 1;
            redDefault();

            if (noShowStatus == 0) {
                if (leftOrRight.equals("Left")) {
                    makeBoxesBlue("Left");
                    makeBoxesVisible("Left");
                    makeBoxesInvisible("Right");
                    makeCirclesInvisible();
                } else if (leftOrRight.equals("Right")) {
                    makeBoxesBlue("Right");
                    makeBoxesVisible("Right");
                    makeBoxesInvisible("Left");
                    makeCirclesInvisible();
                }
            }
            else {
                makeCirclesInvisible();
                makeBoxesInvisible("Both");
            }
        }



        else {
            blueDefault();
            startButton.setEnabled(false);
            //make boxes invisible
            makeBoxesInvisible("Both");
            makeCirclesInvisible();
        }
    }

    public void redClick (View view) {
        if (isRedAlliance == 0) {
            blueDefault();
            redButton.setBackgroundColor(getResources().getColor(R.color.red));
            redButton.setTextColor(getResources().getColor(R.color.light));
            isRedAlliance = 1;

            if (teamNumber != 0 && matchNumber != 0)
                startButton.setEnabled(true);

            if (noShowStatus == 0) {
                if (leftOrRight.equals("Left")) {
                    makeBoxesRed("Right");
                    makeBoxesVisible("Right");
                    makeBoxesInvisible("Left");
                    makeCirclesInvisible();
                } else {
                    makeBoxesRed("Left");
                    makeBoxesVisible("Left");
                    makeBoxesInvisible("Right");
                    makeCirclesInvisible();
                }
            }
            else {
                makeBoxesInvisible("Both");
                makeCirclesInvisible();
            }
        }
        else {
            redDefault();
            startButton.setEnabled(false);
            makeBoxesInvisible("Both");
            makeCirclesInvisible();
        }

    }

    public void panelClick (View view) {
        if (isSetupPanel == 0) {
            isSetupPanel = 1;
            selectedButtonColors(panelButton);
            cargoDefault();
        } else
            panelDefault();

    }



    public void cargoClick (View view) {

        if (isSetupCargo == 0) {
            panelDefault();
            isSetupCargo = 1;
            selectedButtonColors(cargoButton);
        } else
            cargoDefault();

    }

    public void enableButtonsDefault() {

        panelButton.setEnabled(true);
        cargoButton.setEnabled(true);
        startButton.setEnabled(false);
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