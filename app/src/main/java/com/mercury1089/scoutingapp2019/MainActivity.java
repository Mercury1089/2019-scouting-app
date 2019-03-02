package com.mercury1089.scoutingapp2019;









import android.app.Activity;

import android.app.AlertDialog;

import android.app.ProgressDialog;
import android.content.Intent;

import android.graphics.Bitmap;



import android.support.constraint.ConstraintLayout;







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



import java.io.Serializable;

import java.util.HashMap;


public class MainActivity extends Activity {



    //variables that should be outputted

    private String scouterName = "";

    private String matchNumber = "";

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



    private Switch NoShowSwitch;



    private HashMap<String, String> setupHashMap;



    //for QR code generator

    public final static int QRCodeSize = 500;

    Bitmap bitmap;

    String QRValue;



    //other variables

    Button clearButton;

    Button startButton;

    TextView prepopulatedTitle;

    TextView prepopulatedDirections;

    TextView DiagramMessage;



    boolean isQRButton = false;

    String leftOrRight;

    private ProgressDialog progressDialog;








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



        setupHashMap = new HashMap<>();

        setupHashMap.put("StartingGameObject", "Neither");

        setupHashMap.put("NoShow", "False");

        setupHashMap.put("LeftOrRight", getIntent().getStringExtra("LEFTORRIGHT"));

        setupHashMap.put("StartingPosition", "None");

        setupHashMap.put("AllianceColor", "Neither");

        NoShowSwitch = findViewById(R.id.NoShowSwitch);



        clearButton = findViewById(R.id.ClearButton);

        startButton = findViewById(R.id.StartButton);

        prepopulatedTitle = findViewById(R.id.IDPrepopulated);

        prepopulatedDirections = findViewById(R.id.IDPrepopulatedDirections);

        DiagramMessage = findViewById(R.id.DiagramMessage);



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



        //setting prepopulated text to default state

        prepopulatedTitle.setTextColor(getResources().getColor(R.color.grey));

        prepopulatedDirections.setTextColor(getResources().getColor(R.color.grey));

        if (getIntent().getStringExtra("LEFTORRIGHT") != null) {
            leftOrRight = getIntent().getStringExtra("LEFTORRIGHT");

        }
        else {
            //
        }



        if (setupHashMap.size() == 5) {

            Serializable setupData = getIntent().getSerializableExtra("setupHashMap");

            if (setupData != null) {
                setupHashMap = (HashMap<String, String>) setupData;
            }

            if (setupHashMap == null) setupHashMap = new HashMap<>();

        }



        enableButtonsDefault();

        if (setupHashMap.size() > 5) {

            if (setupHashMap.get("StartingPosition") != null) {
                if (setupHashMap.get("StartingPosition").equals("LL1"))
                    LL1Circle.setVisibility(View.VISIBLE);
                else if (setupHashMap.get("StartingPosition").equals("LC1"))
                    LC1Circle.setVisibility(View.VISIBLE);
                else if (setupHashMap.get("StartingPosition").equals("LR1"))
                    LR1Circle.setVisibility(View.VISIBLE);
                else if (setupHashMap.get("StartingPosition").equals("LL2"))
                    LR1Circle.setVisibility(View.VISIBLE);
                else if (setupHashMap.get("StartingPosition").equals("LR2"))
                    LR1Circle.setVisibility(View.VISIBLE);
                else if (setupHashMap.get("StartingPosition").equals("RL1"))
                    LL1Circle.setVisibility(View.VISIBLE);
                else if (setupHashMap.get("StartingPosition").equals("RC1"))
                    LC1Circle.setVisibility(View.VISIBLE);
                else if (setupHashMap.get("StartingPosition").equals("RR1"))
                    LR1Circle.setVisibility(View.VISIBLE);
                else if (setupHashMap.get("StartingPosition").equals("RL2"))
                    LR1Circle.setVisibility(View.VISIBLE);
                else if (setupHashMap.get("StartingPosition").equals("RR2"))
                    LR1Circle.setVisibility(View.VISIBLE);
            }
            ScouterNameInput.setText(setupHashMap.get(ScouterNameInput.getTag().toString()));

            matchNumberInput.setText(setupHashMap.get(matchNumberInput.getTag().toString()));

            teamNumberInput.setText(setupHashMap.get(teamNumberInput.getTag().toString()));

            firstAlliancePartnerInput.setText(setupHashMap.get(firstAlliancePartnerInput.getTag().toString()));

            secondAlliancePartnerInput.setText(setupHashMap.get(secondAlliancePartnerInput.getTag().toString()));

            DiagramMessage.setVisibility(View.INVISIBLE);



            if (setupHashMap.get("LeftOrRight").equals("Left")) {

                if (setupHashMap.get("AllianceColor").equals("Blue")) {

                    makeBoxesBlue("Left");

                    makeBoxesVisible("Left");

                    blueButton.setBackgroundColor(getResources().getColor(R.color.blue));

                    blueButton.setTextColor(getResources().getColor(R.color.light));

                }

                else if (setupHashMap.get("AllianceColor").equals("Red")) {

                    makeBoxesRed("Right");

                    makeBoxesVisible("Right");

                    redButton.setBackgroundColor(getResources().getColor(R.color.red));

                    redButton.setTextColor(getResources().getColor(R.color.light));

                }

            }

            else if (setupHashMap.get("LeftOrRight").equals("Right")){

                if (setupHashMap.get("AllianceColor").equals("Blue")) {

                    makeBoxesBlue("Right");

                    makeBoxesVisible("Right");

                    blueButton.setBackgroundColor(getResources().getColor(R.color.blue));

                    blueButton.setTextColor(getResources().getColor(R.color.light));

                }

                else if (setupHashMap.get("AllianceColor").equals("Red")) {

                    makeBoxesRed("Left");

                    makeBoxesVisible("Left");

                    redButton.setBackgroundColor(getResources().getColor(R.color.red));

                    redButton.setTextColor(getResources().getColor(R.color.light));

                }

            }

            if (!setupHashMap.get("StartingGameObject").equals("Neither")) {

                if (setupHashMap.get("StartingGameObject").equals("Panel")) {

                    panelButton.setBackgroundColor(getResources().getColor(R.color.orange));

                    panelButton.setTextColor(getResources().getColor(R.color.light));

                }

                else if (setupHashMap.get("StartingGameObject").equals("Cargo")) {

                    cargoButton.setBackgroundColor(getResources().getColor(R.color.orange));

                    cargoButton.setTextColor(getResources().getColor(R.color.light));

                }

            }



            ConstraintLayout constraintLayout = findViewById(R.id.mainactivity);

            for (int i = 0; i < constraintLayout.getChildCount(); i++) {

                if (constraintLayout.getChildAt(i) instanceof CustomView) {

                    View circle = constraintLayout.getChildAt(i);

                    if (circle.getTag().toString().equals(setupHashMap.get("StartingPosition"))) {

                        circle.setVisibility(View.VISIBLE);

                        break;

                    }

                }

            }

        }

        //starting listener to check the status of the switch

        NoShowSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {

                    if (ScouterNameInput.getText().length() > 0

                            && teamNumberInput.getText().length() > 0

                            && matchNumberInput.getText().length() > 0

                            && firstAlliancePartnerInput.getText().length() > 0

                            && secondAlliancePartnerInput.getText().length() > 0 && (setupHashMap.get("AllianceColor").equals("Blue") ||
                            setupHashMap.get("AllianceColor").equals("Red")))

                        startButton.setEnabled(true);
                    else
                        startButton.setEnabled(false);
                    if (ScouterNameInput.getText().length() > 0

                            || teamNumberInput.getText().length() > 0

                            || matchNumberInput.getText().length() > 0

                            || firstAlliancePartnerInput.getText().length() > 0

                            || secondAlliancePartnerInput.getText().length() > 0 || (setupHashMap.get("AllianceColor").equals("Blue") ||
                            setupHashMap.get("AllianceColor").equals("Red")) || NoShowSwitch.isChecked())

                        clearButton.setEnabled(true);
                    else
                        clearButton.setEnabled(false);

                    setupHashMap.put("NoShow", "True");

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

                } else {

                    setupHashMap.put("NoShow", "False");

                    noShowStatus = 0;

                    isQRButton = false;

                    panelButton.setEnabled(true);

                    cargoButton.setEnabled(true);

                    startButton.setText(R.string.Start);

                    if (setupHashMap.get("AllianceColor").equals("Blue")) {
                        if (setupHashMap.get("LeftOrRight").equals("Right")) {
                            makeBoxesBlue("Right");

                            makeBoxesVisible("Right");

                            makeBoxesInvisible("Left");
                        }
                        else if (setupHashMap.get("LeftOrRight").equals("Left")) {
                            makeBoxesBlue("Left");

                            makeBoxesVisible("Left");

                            makeBoxesInvisible("Right");
                        }

                    }
                    else if (setupHashMap.get("AllianceColor").equals("Red")) {
                        if (setupHashMap.get("LeftOrRight").equals("Right")) {
                            makeBoxesRed("Right");

                            makeBoxesVisible("Right");

                            makeBoxesInvisible("Left");
                        }
                        else if (setupHashMap.get("LeftOrRight").equals("Left")) {
                            makeBoxesRed("Left");

                            makeBoxesVisible("Left");

                            makeBoxesInvisible("Right");
                        }
                    }

                    ConstraintLayout constraintLayout = findViewById(R.id.mainactivity);

                    for (int i = 0; i < constraintLayout.getChildCount(); i++) {

                        if (constraintLayout.getChildAt(i) instanceof CustomView) {

                            View circle = constraintLayout.getChildAt(i);

                            if (circle.getTag().toString().equals(setupHashMap.get("StartingPosition"))) {

                                circle.setVisibility(View.VISIBLE);

                                break;

                            }

                        }

                    }

                }

            }

        });





        ScouterNameInput.addTextChangedListener(new TextWatcher() {

            @Override

            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override

            public void onTextChanged(CharSequence s, int start, int before, int count) {

                //to enable/disable start and cancel button

                if (ScouterNameInput.getText().length() > 0

                        && teamNumberInput.getText().length() > 0

                        && matchNumberInput.getText().length() > 0

                        && firstAlliancePartnerInput.getText().length() > 0

                        && secondAlliancePartnerInput.getText().length() > 0 && (setupHashMap.get("AllianceColor").equals("Blue") ||
                        setupHashMap.get("AllianceColor").equals("Red")) && NoShowSwitch.isChecked())

                    startButton.setEnabled(true);
                else
                    startButton.setEnabled(false);
                if (ScouterNameInput.getText().length() > 0

                        || teamNumberInput.getText().length() > 0

                        || matchNumberInput.getText().length() > 0

                        || firstAlliancePartnerInput.getText().length() > 0

                        || secondAlliancePartnerInput.getText().length() > 0 || (setupHashMap.get("AllianceColor").equals("Blue") ||
                        setupHashMap.get("AllianceColor").equals("Red")) || NoShowSwitch.isChecked())

                    clearButton.setEnabled(true);
                else
                    clearButton.setEnabled(false);

                if (ScouterNameInput.getText().toString().length() > 0)
                    clearButton.setEnabled(true);

                setupHashMap.put(ScouterNameInput.getTag().toString(), ScouterNameInput.getText().toString());

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

                if (ScouterNameInput.getText().length() > 0

                        && teamNumberInput.getText().length() > 0

                        && matchNumberInput.getText().length() > 0

                        && firstAlliancePartnerInput.getText().length() > 0

                        && secondAlliancePartnerInput.getText().length() > 0 && (setupHashMap.get("AllianceColor").equals("Blue") ||
                        setupHashMap.get("AllianceColor").equals("Red")) && NoShowSwitch.isChecked())

                    startButton.setEnabled(true);
                else
                    startButton.setEnabled(false);

                if (ScouterNameInput.getText().length() > 0

                        || teamNumberInput.getText().length() > 0

                        || matchNumberInput.getText().length() > 0

                        || firstAlliancePartnerInput.getText().length() > 0

                        || secondAlliancePartnerInput.getText().length() > 0 || (setupHashMap.get("AllianceColor").equals("Blue") ||
                        setupHashMap.get("AllianceColor").equals("Red")) || NoShowSwitch.isChecked())

                    clearButton.setEnabled(true);
                else
                    clearButton.setEnabled(false);

                setupHashMap.put(matchNumberInput.getTag().toString(), matchNumberInput.getText().toString());

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

                if (ScouterNameInput.getText().length() > 0

                        && teamNumberInput.getText().length() > 0

                        && matchNumberInput.getText().length() > 0

                        && firstAlliancePartnerInput.getText().length() > 0

                        && secondAlliancePartnerInput.getText().length() > 0 && (setupHashMap.get("AllianceColor").equals("Blue") ||
                        setupHashMap.get("AllianceColor").equals("Red")) && NoShowSwitch.isChecked())

                    startButton.setEnabled(true);
                else
                    startButton.setEnabled(false);
                if (ScouterNameInput.getText().length() > 0

                        || teamNumberInput.getText().length() > 0

                        || matchNumberInput.getText().length() > 0

                        || firstAlliancePartnerInput.getText().length() > 0

                        || secondAlliancePartnerInput.getText().length() > 0 || (setupHashMap.get("AllianceColor").equals("Blue") ||
                        setupHashMap.get("AllianceColor").equals("Red")) || NoShowSwitch.isChecked())

                    clearButton.setEnabled(true);
                else
                    clearButton.setEnabled(false);

                setupHashMap.put(teamNumberInput.getTag().toString(), teamNumberInput.getText().toString());

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


                if (ScouterNameInput.getText().length() > 0

                        && teamNumberInput.getText().length() > 0

                        && matchNumberInput.getText().length() > 0

                        && firstAlliancePartnerInput.getText().length() > 0

                        && secondAlliancePartnerInput.getText().length() > 0 && (setupHashMap.get("AllianceColor").equals("Blue") ||
                        setupHashMap.get("AllianceColor").equals("Red")) && NoShowSwitch.isChecked())

                    startButton.setEnabled(true);
                else
                    startButton.setEnabled(false);
                if (ScouterNameInput.getText().length() > 0

                        || teamNumberInput.getText().length() > 0

                        || matchNumberInput.getText().length() > 0

                        || firstAlliancePartnerInput.getText().length() > 0

                        || secondAlliancePartnerInput.getText().length() > 0 || (setupHashMap.get("AllianceColor").equals("Blue") ||
                        setupHashMap.get("AllianceColor").equals("Red")) || NoShowSwitch.isChecked())

                    clearButton.setEnabled(true);
                else
                    clearButton.setEnabled(false);

                setupHashMap.put(firstAlliancePartnerInput.getTag().toString(), firstAlliancePartnerInput.getText().toString());

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

                if (ScouterNameInput.getText().length() > 0

                        && teamNumberInput.getText().length() > 0

                        && matchNumberInput.getText().length() > 0

                        && firstAlliancePartnerInput.getText().length() > 0

                        && secondAlliancePartnerInput.getText().length() > 0 && (setupHashMap.get("AllianceColor").equals("Blue") ||
                        setupHashMap.get("AllianceColor").equals("Red")) && NoShowSwitch.isChecked())

                    startButton.setEnabled(true);
                else
                    startButton.setEnabled(false);
                if (ScouterNameInput.getText().length() > 0

                        || teamNumberInput.getText().length() > 0

                        || matchNumberInput.getText().length() > 0

                        || firstAlliancePartnerInput.getText().length() > 0

                        || secondAlliancePartnerInput.getText().length() > 0 || (setupHashMap.get("AllianceColor").equals("Blue") ||
                        setupHashMap.get("AllianceColor").equals("Red")) || NoShowSwitch.isChecked())

                    clearButton.setEnabled(true);
                else
                    clearButton.setEnabled(false);

                setupHashMap.put(secondAlliancePartnerInput.getTag().toString(), secondAlliancePartnerInput.getText().toString());

            }

            @Override

            public void afterTextChanged(Editable s) { }

        });



        //set listener for QR Code generator

        startButton.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {

                scouterName = ScouterNameInput.getText().toString();

                matchNumber = matchNumberInput.getText().toString();

                teamNumber = Integer.parseInt(teamNumberInput.getText().toString());

                firstAlliancePartner = Integer.parseInt(firstAlliancePartnerInput.getText().toString());

                secondAlliancePartner = Integer.parseInt(secondAlliancePartnerInput.getText().toString());



                if (isQRButton) {

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            progressDialog = new ProgressDialog(MainActivity.this, R.style.LoadingDialogStyle);
                            progressDialog.setMessage("Please Wait");
                            progressDialog.setCancelable(false);
                            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                            progressDialog.show();
                        }
                    });
                    QRRunnable qrRunnable = new QRRunnable();
                    new Thread(qrRunnable).start();

                } else {
                    Intent intent = new Intent(MainActivity.this, Sandstorm.class);

                    intent.putExtra("setupHashMap", setupHashMap);

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
        setupHashMap.put("StartingPosition", null);
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



    //click methods



    public void SettingsClick (View view) {
        Intent intent = new Intent(MainActivity.this, Settings.class);
        intent.putExtra("mainLeftOrRight", leftOrRight);
        startActivity(intent);
    }



    public void ClearClick (View view) {

        ScouterNameInput.setText("");

        matchNumberInput.setText("");

        teamNumberInput.setText("");

        firstAlliancePartnerInput.setText("");

        secondAlliancePartnerInput.setText("");

        NoShowSwitch.setChecked(false);



        blueDefault();

        redDefault();

        panelDefault();

        cargoDefault();

        clearButton.setEnabled(false);

        makeCirclesInvisible();
        makeBoxesInvisible("Both");

    }



    public void LL1Click (View view) {

        startPositionDefault();

        startL1 = 1;

        setupHashMap.put("StartingPosition", LL1Circle.getTag().toString());

        if (ScouterNameInput.getText().length() > 0

                && teamNumberInput.getText().length() > 0

                && matchNumberInput.getText().length() > 0

                && firstAlliancePartnerInput.getText().length() > 0

                && secondAlliancePartnerInput.getText().length() > 0 && (setupHashMap.get("AllianceColor").equals("Blue") ||
                setupHashMap.get("AllianceColor").equals("Red")))

            startButton.setEnabled(true);
        else
            startButton.setEnabled(false);
        if (ScouterNameInput.getText().length() > 0

                || teamNumberInput.getText().length() > 0

                || matchNumberInput.getText().length() > 0

                || firstAlliancePartnerInput.getText().length() > 0

                || secondAlliancePartnerInput.getText().length() > 0 || (setupHashMap.get("AllianceColor").equals("Blue") ||
                setupHashMap.get("AllianceColor").equals("Red")) || NoShowSwitch.isChecked())

            clearButton.setEnabled(true);
        else
            clearButton.setEnabled(false);

        makeCirclesInvisible();

        LL1Circle.setVisibility(View.VISIBLE);

    }







    public void LC1Click (View view) {

        startPositionDefault();

        startC1 = 1;

        setupHashMap.put("StartingPosition", LC1Circle.getTag().toString());

        if (ScouterNameInput.getText().length() > 0

                && teamNumberInput.getText().length() > 0

                && matchNumberInput.getText().length() > 0

                && firstAlliancePartnerInput.getText().length() > 0

                && secondAlliancePartnerInput.getText().length() > 0 && (setupHashMap.get("AllianceColor").equals("Blue") ||
                setupHashMap.get("AllianceColor").equals("Red")) && !NoShowSwitch.isChecked())

            startButton.setEnabled(true);
        else
            startButton.setEnabled(false);
        if (ScouterNameInput.getText().length() > 0

                || teamNumberInput.getText().length() > 0

                || matchNumberInput.getText().length() > 0

                || firstAlliancePartnerInput.getText().length() > 0

                || secondAlliancePartnerInput.getText().length() > 0 || (setupHashMap.get("AllianceColor").equals("Blue") ||
                setupHashMap.get("AllianceColor").equals("Red")) || !NoShowSwitch.isChecked())

            clearButton.setEnabled(true);
        else
            clearButton.setEnabled(false);

        makeCirclesInvisible();

        LC1Circle.setVisibility(View.VISIBLE);

    }







    public void LR1Click (View view) {

        startPositionDefault();

        startR1 = 1;

        setupHashMap.put("StartingPosition", LR1Circle.getTag().toString());

        if (ScouterNameInput.getText().length() > 0

                && teamNumberInput.getText().length() > 0

                && matchNumberInput.getText().length() > 0

                && firstAlliancePartnerInput.getText().length() > 0

                && secondAlliancePartnerInput.getText().length() > 0 && (setupHashMap.get("AllianceColor").equals("Blue") ||
                setupHashMap.get("AllianceColor").equals("Red")) && !NoShowSwitch.isChecked())

            startButton.setEnabled(true);
        else
            startButton.setEnabled(false);
        if (ScouterNameInput.getText().length() > 0

                || teamNumberInput.getText().length() > 0

                || matchNumberInput.getText().length() > 0

                || firstAlliancePartnerInput.getText().length() > 0

                || secondAlliancePartnerInput.getText().length() > 0 || (setupHashMap.get("AllianceColor").equals("Blue") ||
                setupHashMap.get("AllianceColor").equals("Red")) || !NoShowSwitch.isChecked())

            clearButton.setEnabled(true);
        else
            clearButton.setEnabled(false);

        makeCirclesInvisible();

        LR1Circle.setVisibility(View.VISIBLE);

    }







    public void LL2Click (View view) {

        startPositionDefault();

        startL2 = 1;

        setupHashMap.put("StartingPosition", LL2Circle.getTag().toString());

        if (ScouterNameInput.getText().length() > 0

                && teamNumberInput.getText().length() > 0

                && matchNumberInput.getText().length() > 0

                && firstAlliancePartnerInput.getText().length() > 0

                && secondAlliancePartnerInput.getText().length() > 0 && (setupHashMap.get("AllianceColor").equals("Blue") ||
                setupHashMap.get("AllianceColor").equals("Red")) && !NoShowSwitch.isChecked())

            startButton.setEnabled(true);
        else
            startButton.setEnabled(false);
        if (ScouterNameInput.getText().length() > 0

                || teamNumberInput.getText().length() > 0

                || matchNumberInput.getText().length() > 0

                || firstAlliancePartnerInput.getText().length() > 0

                || secondAlliancePartnerInput.getText().length() > 0 || (setupHashMap.get("AllianceColor").equals("Blue") ||
                setupHashMap.get("AllianceColor").equals("Red")) || !NoShowSwitch.isChecked())

            clearButton.setEnabled(true);
        else
            clearButton.setEnabled(false);

        makeCirclesInvisible();

        LL2Circle.setVisibility(View.VISIBLE);

    }







    public void LR2Click (View view) {

        startPositionDefault();

        startR2 = 1;

        setupHashMap.put("StartingPosition", LR2Circle.getTag().toString());

        if (ScouterNameInput.getText().length() > 0

                && teamNumberInput.getText().length() > 0

                && matchNumberInput.getText().length() > 0

                && firstAlliancePartnerInput.getText().length() > 0

                && secondAlliancePartnerInput.getText().length() > 0 && (setupHashMap.get("AllianceColor").equals("Blue") ||
                setupHashMap.get("AllianceColor").equals("Red")) && !NoShowSwitch.isChecked())

            startButton.setEnabled(true);
        else
            startButton.setEnabled(false);
        if (ScouterNameInput.getText().length() > 0

                || teamNumberInput.getText().length() > 0

                || matchNumberInput.getText().length() > 0

                || firstAlliancePartnerInput.getText().length() > 0

                || secondAlliancePartnerInput.getText().length() > 0 || (setupHashMap.get("AllianceColor").equals("Blue") ||
                setupHashMap.get("AllianceColor").equals("Red")) || !NoShowSwitch.isChecked())

            clearButton.setEnabled(true);
        else
            clearButton.setEnabled(false);

        makeCirclesInvisible();

        LR2Circle.setVisibility(View.VISIBLE);

    }



    public void RL1Click (View view) {

        startPositionDefault();

        startL1 = 1;

        setupHashMap.put("StartingPosition", RL1Circle.getTag().toString());

        if (ScouterNameInput.getText().length() > 0

                && teamNumberInput.getText().length() > 0

                && matchNumberInput.getText().length() > 0

                && firstAlliancePartnerInput.getText().length() > 0

                && secondAlliancePartnerInput.getText().length() > 0 && (setupHashMap.get("AllianceColor").equals("Blue") ||
                setupHashMap.get("AllianceColor").equals("Red")) && !NoShowSwitch.isChecked())

            startButton.setEnabled(true);
        else
            startButton.setEnabled(false);
        if (ScouterNameInput.getText().length() > 0

                || teamNumberInput.getText().length() > 0

                || matchNumberInput.getText().length() > 0

                || firstAlliancePartnerInput.getText().length() > 0

                || secondAlliancePartnerInput.getText().length() > 0 || (setupHashMap.get("AllianceColor").equals("Blue") ||
                setupHashMap.get("AllianceColor").equals("Red")) || !NoShowSwitch.isChecked())

            clearButton.setEnabled(true);
        else
            clearButton.setEnabled(false);

        makeCirclesInvisible();

        RL1Circle.setVisibility(View.VISIBLE);

    }







    public void RC1Click (View view) {

        startPositionDefault();

        startC1 = 1;

        setupHashMap.put("StartingPosition", RC1Circle.getTag().toString());

        if (ScouterNameInput.getText().length() > 0

                && teamNumberInput.getText().length() > 0

                && matchNumberInput.getText().length() > 0

                && firstAlliancePartnerInput.getText().length() > 0

                && secondAlliancePartnerInput.getText().length() > 0 && (setupHashMap.get("AllianceColor").equals("Blue") ||
                setupHashMap.get("AllianceColor").equals("Red")) && !NoShowSwitch.isChecked())

            startButton.setEnabled(true);
        else
            startButton.setEnabled(false);
        if (ScouterNameInput.getText().length() > 0

                || teamNumberInput.getText().length() > 0

                || matchNumberInput.getText().length() > 0

                || firstAlliancePartnerInput.getText().length() > 0

                || secondAlliancePartnerInput.getText().length() > 0 || (setupHashMap.get("AllianceColor").equals("Blue") ||
                setupHashMap.get("AllianceColor").equals("Red")) || !NoShowSwitch.isChecked())

            clearButton.setEnabled(true);
        else
            clearButton.setEnabled(false);

        makeCirclesInvisible();

        RC1Circle.setVisibility(View.VISIBLE);

    }







    public void RR1Click (View view) {

        startPositionDefault();

        startR1 = 1;

        setupHashMap.put("StartingPosition", RR1Circle.getTag().toString());

        if (ScouterNameInput.getText().length() > 0

                && teamNumberInput.getText().length() > 0

                && matchNumberInput.getText().length() > 0

                && firstAlliancePartnerInput.getText().length() > 0

                && secondAlliancePartnerInput.getText().length() > 0 && (setupHashMap.get("AllianceColor").equals("Blue") ||
                setupHashMap.get("AllianceColor").equals("Red")) && !NoShowSwitch.isChecked())

            startButton.setEnabled(true);
        else
            startButton.setEnabled(false);
        if (ScouterNameInput.getText().length() > 0

                || teamNumberInput.getText().length() > 0

                || matchNumberInput.getText().length() > 0

                || firstAlliancePartnerInput.getText().length() > 0

                || secondAlliancePartnerInput.getText().length() > 0 || (setupHashMap.get("AllianceColor").equals("Blue") ||
                setupHashMap.get("AllianceColor").equals("Red")) || !NoShowSwitch.isChecked())

            clearButton.setEnabled(true);
        else
            clearButton.setEnabled(false);

        makeCirclesInvisible();

        RR1Circle.setVisibility(View.VISIBLE);

    }







    public void RL2Click (View view) {

        startPositionDefault();

        startL2 = 1;

        setupHashMap.put("StartingPosition", RL2Circle.getTag().toString());

        if (ScouterNameInput.getText().length() > 0

                && teamNumberInput.getText().length() > 0

                && matchNumberInput.getText().length() > 0

                && firstAlliancePartnerInput.getText().length() > 0

                && secondAlliancePartnerInput.getText().length() > 0 && (setupHashMap.get("AllianceColor").equals("Blue") ||
                setupHashMap.get("AllianceColor").equals("Red")) && !NoShowSwitch.isChecked())

            startButton.setEnabled(true);
        else
            startButton.setEnabled(false);
        if (ScouterNameInput.getText().length() > 0

                || teamNumberInput.getText().length() > 0

                || matchNumberInput.getText().length() > 0

                || firstAlliancePartnerInput.getText().length() > 0

                || secondAlliancePartnerInput.getText().length() > 0 || (setupHashMap.get("AllianceColor").equals("Blue") ||
                setupHashMap.get("AllianceColor").equals("Red")) || !NoShowSwitch.isChecked())

            clearButton.setEnabled(true);
        else
            clearButton.setEnabled(false);

        makeCirclesInvisible();

        RL2Circle.setVisibility(View.VISIBLE);

    }







    public void RR2Click (View view) {

        startPositionDefault();

        startR2 = 1;

        setupHashMap.put("StartingPosition", RR2Circle.getTag().toString());

        if (ScouterNameInput.getText().length() > 0

                && teamNumberInput.getText().length() > 0

                && matchNumberInput.getText().length() > 0

                && firstAlliancePartnerInput.getText().length() > 0

                && secondAlliancePartnerInput.getText().length() > 0 && (setupHashMap.get("AllianceColor").equals("Blue") ||
                setupHashMap.get("AllianceColor").equals("Red")) && !NoShowSwitch.isChecked())

            startButton.setEnabled(true);
        else
            startButton.setEnabled(false);
        if (ScouterNameInput.getText().length() > 0

                || teamNumberInput.getText().length() > 0

                || matchNumberInput.getText().length() > 0

                || firstAlliancePartnerInput.getText().length() > 0

                || secondAlliancePartnerInput.getText().length() > 0 || (setupHashMap.get("AllianceColor").equals("Blue") ||
                setupHashMap.get("AllianceColor").equals("Red")) || NoShowSwitch.isChecked())

            clearButton.setEnabled(true);
        else
            clearButton.setEnabled(false);

        makeCirclesInvisible();

        RR2Circle.setVisibility(View.VISIBLE);

    }





    public void blueClick (View view) {
        setupHashMap.put("AllianceColor", "Blue");
        DiagramMessage.setVisibility(View.INVISIBLE);
        if (isBlueAlliance == 0) {
            if (ScouterNameInput.getText().length() > 0

                    && teamNumberInput.getText().length() > 0

                    && matchNumberInput.getText().length() > 0

                    && firstAlliancePartnerInput.getText().length() > 0

                    && secondAlliancePartnerInput.getText().length() > 0 && (setupHashMap.get("AllianceColor").equals("Blue") ||
                    setupHashMap.get("AllianceColor").equals("Red")) && setupHashMap.get("StartingPosition")!= null)
                startButton.setEnabled(true);
            else
                startButton.setEnabled(false);
            if (ScouterNameInput.getText().length() > 0

                    || teamNumberInput.getText().length() > 0

                    || matchNumberInput.getText().length() > 0

                    || firstAlliancePartnerInput.getText().length() > 0

                    || secondAlliancePartnerInput.getText().length() > 0 || (setupHashMap.get("AllianceColor").equals("Blue") ||
                    setupHashMap.get("AllianceColor").equals("Red")) || NoShowSwitch.isChecked())

                clearButton.setEnabled(true);
            else
                clearButton.setEnabled(false);

            redDefault();

            blueButton.setBackgroundColor(getResources().getColor(R.color.blue));

            blueButton.setTextColor(getResources().getColor(R.color.light));

            isBlueAlliance = 1;

            setupHashMap.put("StartingPosition", null);

            if (noShowStatus == 0) {
                isQRButton = false;
                if (setupHashMap.get("LeftOrRight").equals("Left")) {

                    makeBoxesBlue("Left");

                    makeBoxesVisible("Left");

                    makeBoxesInvisible("Right");

                    makeCirclesInvisible();



                } else if (setupHashMap.get("LeftOrRight").equals("Right"))  {

                    makeBoxesBlue("Right");

                    makeBoxesVisible("Right");

                    makeBoxesInvisible("Left");

                    makeCirclesInvisible();
                }

            }

            else {

                makeBoxesInvisible("Both");

                makeCirclesInvisible();

            }

        }

        else {

            blueDefault();

            setupHashMap.put("AllianceColor", "Neither");

            DiagramMessage.setVisibility(View.VISIBLE);

            if (ScouterNameInput.getText().length() > 0

                    && teamNumberInput.getText().length() > 0

                    && matchNumberInput.getText().length() > 0

                    && firstAlliancePartnerInput.getText().length() > 0

                    && secondAlliancePartnerInput.getText().length() > 0 && (setupHashMap.get("AllianceColor").equals("Blue") ||
                    setupHashMap.get("AllianceColor").equals("Red")) || NoShowSwitch.isChecked())

                startButton.setEnabled(true);
            else
                startButton.setEnabled(false);
            if (ScouterNameInput.getText().length() > 0

                    || teamNumberInput.getText().length() > 0

                    || matchNumberInput.getText().length() > 0

                    || firstAlliancePartnerInput.getText().length() > 0

                    || secondAlliancePartnerInput.getText().length() > 0 || (setupHashMap.get("AllianceColor").equals("Blue") ||
                    setupHashMap.get("AllianceColor").equals("Red")) && setupHashMap.get("StartingPosition")!= null)

                clearButton.setEnabled(true);
            else
                clearButton.setEnabled(false);

            makeBoxesInvisible("Both");

            makeCirclesInvisible();



        }




    }



    public void redClick (View view) {

        setupHashMap.put("AllianceColor", "Red");

        DiagramMessage.setVisibility(View.INVISIBLE);

        if (isRedAlliance == 0) {

            if (ScouterNameInput.getText().length() > 0

                    && teamNumberInput.getText().length() > 0

                    && matchNumberInput.getText().length() > 0

                    && firstAlliancePartnerInput.getText().length() > 0

                    && secondAlliancePartnerInput.getText().length() > 0 && (setupHashMap.get("AllianceColor").equals("Blue") ||
                    setupHashMap.get("AllianceColor").equals("Red"))&& setupHashMap.get("StartingPosition")!= null)

                startButton.setEnabled(true);
            else
                startButton.setEnabled(false);

            blueDefault();

            redButton.setBackgroundColor(getResources().getColor(R.color.red));

            redButton.setTextColor(getResources().getColor(R.color.light));

            isRedAlliance = 1;

            setupHashMap.put("StartingPosition", null);

            if (noShowStatus == 0) {

                if (setupHashMap.get("LeftOrRight").equals("Left")) {

                    makeBoxesRed("Left");

                    makeBoxesVisible("Left");

                    makeBoxesInvisible("Right");

                    makeCirclesInvisible();

                } else if (setupHashMap.get("LeftOrRight").equals("Right"))  {

                    makeBoxesRed("Right");

                    makeBoxesVisible("Right");

                    makeBoxesInvisible("Left");

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

            setupHashMap.put("AllianceColor", "Neither");

            DiagramMessage.setVisibility(View.VISIBLE);

            if (ScouterNameInput.getText().length() > 0

                    && teamNumberInput.getText().length() > 0

                    && matchNumberInput.getText().length() > 0

                    && firstAlliancePartnerInput.getText().length() > 0

                    && secondAlliancePartnerInput.getText().length() > 0 && (setupHashMap.get("AllianceColor").equals("Blue") ||
                    setupHashMap.get("AllianceColor").equals("Red")) || NoShowSwitch.isChecked())

                startButton.setEnabled(true);
            else
                startButton.setEnabled(false);

            makeBoxesInvisible("Both");

            makeCirclesInvisible();

        }



    }



    public void panelClick (View view) {

        if (isSetupPanel == 0) {

            setupHashMap.put("StartingGameObject", "Panel");

            isSetupPanel = 1;

            selectedButtonColors(panelButton);

            cargoDefault();

        } else {

            setupHashMap.put("StartingGameObject", "Neither");

            panelDefault();
        }



    }

    public void cargoClick (View view) {

        if (isSetupCargo == 0) {

            setupHashMap.put("StartingGameObject", "Cargo");

            panelDefault();

            isSetupCargo = 1;

            selectedButtonColors(cargoButton);

        } else {

            setupHashMap.put("StartingGameObject", "Neither");

            cargoDefault();
        }



    }

    //This the defualt state for the cargo button

    public void enableButtonsDefault() {
        panelButton.setEnabled(true);

        cargoButton.setEnabled(true);



        if (setupHashMap.size() > 5) {
            startButton.setEnabled(true);
        }
        else {
            startButton.setEnabled(false);
        }
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


    class QRRunnable implements Runnable {

        @Override
        public void run() {
            QRValue = ScouterNameInput.getText().toString() + "," + matchNumberInput.getText().toString()

                    + "," + teamNumberInput.getText().toString() + ","

                    + firstAlliancePartnerInput.getText().toString() + ","

                    + secondAlliancePartnerInput.getText().toString() + ","
                    + isRedAlliance + "," + isBlueAlliance + "," + noShowStatus + "," + ' ' + ","
                    + 0 + "," +  0 + "," + 0 + "," + 0 + "," + 0 + ",";

            try {
                bitmap = TextToImageEncode(QRValue);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        final AlertDialog.Builder qrDialog = new AlertDialog.Builder(MainActivity.this);
                        View view1 = getLayoutInflater().inflate(R.layout.qr_popup, null);
                        ImageView imageView = view1.findViewById(R.id.imageView);
                        Button goBackToMain = view1.findViewById(R.id.GoBackButton);
                        imageView.setImageBitmap(bitmap);
                        qrDialog.setView(view1);
                        final AlertDialog dialog = qrDialog.create();

                        progressDialog.dismiss();

                        dialog.show();

                        goBackToMain.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                            }
                        });
                    }
                });

            } catch (WriterException e) {
                e.printStackTrace();

            }
        }
    }


}