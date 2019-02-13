package com.mercury1089.scoutingapp2019;



import android.content.Intent;



import android.graphics.Bitmap;



import android.support.v7.app.AppCompatActivity;



import android.os.Bundle;


import android.text.Editable;
import android.text.TextWatcher;
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









public class MainActivity extends AppCompatActivity {
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



    //for QR code generator

    String QRValue;

    public final static int QRCodeSize = 500;

    ImageView imageView;

    Bitmap bitmap;



    //other variables

    TextView prepopulatedDirections;

    TextView prepopulatedTitle;

    Button clearButton;

    Button startButton;

    boolean isResetLocalStorageClicked;

    boolean hasBeenSaved = false;

    boolean isQRButton = false;

    int i = 1;

    String leftOrRight;




    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);



        //going to Settings screen at startup (only once from onCreate)

        if (i < 1) {

            Intent intent = new Intent(this, Settings.class);

            startActivity(intent);

            startActivityForResult(intent, 1);

        }

        i++;



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



        //starting listener to check the status of the switch

        NoShowSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {

                    noShowStatus = 1;



                    panelButton.setEnabled(false);

                    cargoButton.setEnabled(false);

                    startButton.setEnabled(false);



                    startButton.setText(R.string.GenerateQRCode);
                    isQRButton = true;




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
                scouterName = ScouterNameInput.getText().toString();
                if (!scouterName.isEmpty()) {
                    if (teamNumber!= 0 && matchNumber != 0 && firstAlliancePartner != 0 && secondAlliancePartner != 0)
                        startButton.setEnabled(true);
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
                matchNumber = Integer.parseInt(matchNumberInput.getText().toString());
                if (matchNumber != 0) {
                    if (!scouterName.isEmpty() && teamNumber!= 0 && firstAlliancePartner != 0 && secondAlliancePartner != 0)
                        startButton.setEnabled(true);
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
                teamNumber = Integer.parseInt(teamNumberInput.getText().toString());
                if (teamNumber != 0) {
                    if (!scouterName.isEmpty() && matchNumber != 0 && firstAlliancePartner != 0 && secondAlliancePartner != 0)
                        startButton.setEnabled(true);
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
                firstAlliancePartner = Integer.parseInt(firstAlliancePartnerInput.getText().toString());
                if (firstAlliancePartner != 0) {
                    if (!scouterName.isEmpty() && matchNumber != 0 && teamNumber != 0 && secondAlliancePartner != 0)
                        startButton.setEnabled(true);
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
                secondAlliancePartner = Integer.parseInt(secondAlliancePartnerInput.getText().toString());
                if (secondAlliancePartner != 0) {
                    if (!scouterName.isEmpty() && matchNumber != 0 && teamNumber != 0 && firstAlliancePartner != 0)
                        startButton.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });

        //get intent from settings screen and the variable that says whether it is left or right

        Intent intent = getIntent();

        onActivityResult(1,RESULT_OK,intent);



        //set listener for QR Code generator

        startButton.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {
                if (isQRButton) {
                    String RedOrBlue = "";
                    if (isBlueAlliance == 1)

                        RedOrBlue = "Blue";

                    else if (isRedAlliance == 1)

                        RedOrBlue = "Red";

                    QRValue = ScouterNameInput.getText().toString() + "+" + teamNumberInput.getText().toString()

                            + "+" + matchNumberInput.getText().toString() + "+"

                            + firstAlliancePartnerInput.getText().toString() + "+"

                            + secondAlliancePartnerInput.getText().toString() + "+" + RedOrBlue;

                    try {

                        bitmap = TextToImageEncode(QRValue);

                        imageView.setImageBitmap(bitmap);

                    } catch (WriterException e) {

                        e.printStackTrace();

                    }

                }
            }

        });

    }



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



    //for receiving data from settings screen

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {

            if(resultCode == RESULT_OK) {

                leftOrRight = data.getStringExtra("editTextValue");

            }

        }

    }



    //click methods



    public void SettingsClick (View view) {

        Intent intent = new Intent(this, Settings.class);

        startActivityForResult(intent, 1);

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

            }



            else if (leftOrRight.equals("Right")) {

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

            }

        }



        else {

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

        }

    }


    public void redClick (View view) {

        scouterName = ScouterNameInput.getText().toString();

        matchNumber = Integer.parseInt(matchNumberInput.getText().toString());

        teamNumber = Integer.parseInt(teamNumberInput.getText().toString());

        firstAlliancePartner = Integer.parseInt(firstAlliancePartnerInput.getText().toString());

        secondAlliancePartner = Integer.parseInt(secondAlliancePartnerInput.getText().toString());



        if (isRedAlliance == 0) {

            blueDefault();

            redButton.setBackgroundColor(getResources().getColor(R.color.red));

            redButton.setTextColor(getResources().getColor(R.color.light));

            isRedAlliance = 1;

            if (teamNumber != 0 && matchNumber != 0) {

                startButton.setEnabled(true);

            }



            if (leftOrRight.equals("Left")) {

                //set right boxes to red

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

            }

            else {

                //set left boxes to red

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

            }

        }

        else {

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

        } catch (IllegalArgumentException Illegalargumentexception) {

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