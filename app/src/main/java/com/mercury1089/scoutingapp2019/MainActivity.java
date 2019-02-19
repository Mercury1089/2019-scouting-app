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
    private EditText ScouterNameInput = findViewById(R.id.ScouterNameInput);
    private EditText matchNumberInput = findViewById(R.id.MatchNumberInput);
    private EditText teamNumberInput = findViewById(R.id.TeamNumberInput);
    private EditText firstAlliancePartnerInput = findViewById(R.id.FirstAlliancePartnerInput);
    private EditText secondAlliancePartnerInput = findViewById(R.id.SecondAlliancePartnerInput);
    private BootstrapButton blueButton = findViewById(R.id.BlueButton);
    private BootstrapButton redButton = findViewById(R.id.RedButton);
    private BootstrapButton panelButton = findViewById(R.id.PanelButton);
    private BootstrapButton cargoButton = findViewById(R.id.CargoButton);

    //rectangles
    private View LL1 = findViewById(R.id.LL1);
    private View LC1 = findViewById(R.id.LC1);
    private View LR1 = findViewById(R.id.LR1);
    private View LL2 = findViewById(R.id.LL2);
    private View LR2 = findViewById(R.id.LR2);
    private View RL1 = findViewById(R.id.RL1);
    private View RC1 = findViewById(R.id.RC1);
    private View RR1 = findViewById(R.id.RR1);
    private View RL2 = findViewById(R.id.RL2);
    private View RR2 = findViewById(R.id.RR2);

    //circles
    private CustomView LL1Circle = findViewById(R.id.CircleLL1);
    private CustomView LC1Circle = findViewById(R.id.CircleLC1);
    private CustomView LR1Circle = findViewById(R.id.CircleLR1);
    private CustomView LL2Circle = findViewById(R.id.CircleLL2);
    private CustomView LR2Circle = findViewById(R.id.CircleLR2);
    private CustomView RL1Circle = findViewById(R.id.CircleRL1);
    private CustomView RC1Circle = findViewById(R.id.CircleRC1);
    private CustomView RR1Circle = findViewById(R.id.CircleRR1);
    private CustomView RL2Circle = findViewById(R.id.CircleRL2);
    private CustomView RR2Circle = findViewById(R.id.CircleRR2);

    //for QR code generator
    public final static int QRCodeSize = 500;
    Bitmap bitmap;
    String QRValue;

    //other variables
    Button clearButton = findViewById(R.id.ClearButton);
    Button startButton = findViewById(R.id.StartButton);
    TextView prepopulatedTitle = findViewById(R.id.IDPrepopulated);
    TextView prepopulatedDirections = findViewById(R.id.IDPrepopulatedDirections);

    boolean isQRButton = false;
    String leftOrRight;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                //to enable/disable start and click button
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
                //to enable/disable start and click button
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
                //to enable/disable start and click button
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
                //to enable/disable start and click button
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
                //to enable/disable start and click button
                checkIfAnythingIsTyped(secondAlliancePartnerInput);
            }
            @Override
            public void afterTextChanged(Editable s) { }
        });

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
        if (side.equals("Left")) {
            LL1.setVisibility(View.INVISIBLE);
            LC1.setVisibility(View.INVISIBLE);
            LR1.setVisibility(View.INVISIBLE);
            LL2.setVisibility(View.INVISIBLE);
            LR2.setVisibility(View.INVISIBLE);
        }
        else {
            RL1.setVisibility(View.INVISIBLE);
            RC1.setVisibility(View.INVISIBLE);
            RR1.setVisibility(View.INVISIBLE);
            RL2.setVisibility(View.INVISIBLE);
            RR2.setVisibility(View.INVISIBLE);
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
            if (ScouterNameInput.getText().length() > 0 && teamNumberInput.getText().length() > 0 && matchNumberInput.getText().length() > 0
                    && firstAlliancePartnerInput.getText().length() > 0
                    && secondAlliancePartnerInput.getText().length() > 0)
                startButton.setEnabled(true);
        }
        else
            startButton.setEnabled(false);
    }

    public void switchCheck(boolean isChecked) {

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
        RC1Circle.setVisibility(View.VISIBLE);
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
                    makeCirclesInvisible();
                } else if (leftOrRight.equals("Right")) {
                    makeBoxesBlue("Right");
                    makeBoxesVisible("Right");
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
                    makeBoxesVisible("Right");
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


    public void startClick (View view) {
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
            QRValue = ScouterNameInput.getText().toString() + "+" + teamNumberInput.getText().toString()
                    + "+" + matchNumberInput.getText().toString() + "+"
                    + firstAlliancePartnerInput.getText().toString() + "+"
                    + secondAlliancePartnerInput.getText().toString() + "+" + RedOrBlue;
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
                    public void onClick(View view) { dialog.cancel(); }
                });

            } catch (WriterException e) { e.printStackTrace(); }
        } else {
            String sendMessage = scouterName + matchNumber + teamNumber + firstAlliancePartner + secondAlliancePartner;
            char initPanelOrCargo = ' ';
            if (isSetupPanel == 1)
                initPanelOrCargo = 'P';
            else if (isSetupCargo == 1)
                initPanelOrCargo = 'C';
            Intent intent = new Intent(MainActivity.this, Sandstorm.class);
            intent.putExtra("message", initPanelOrCargo + sendMessage);
            startActivity(intent);
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
}