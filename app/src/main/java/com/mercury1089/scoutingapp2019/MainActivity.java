package com.mercury1089.scoutingapp2019;



import android.content.Intent;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;

import android.widget.Button;

import android.widget.CompoundButton;

import android.widget.EditText;

import android.widget.ImageView;
import android.widget.Switch;

import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;


public class MainActivity extends AppCompatActivity {

    //variables that should be outputted

    String scouterName = "";

    int matchNumber = 0;

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

    View LL1;

    View LC1;

    View LR1;

    View LL2;

    View LR2;

    View RL1;

    View RC1;

    View RR1;

    View RL2;

    View RR2;



    //for QR code generator
    String QRValue;

    public final static int QRCodeSize = 500;

    ImageView imageView;

    Bitmap bitmap;

    //other variables

    TextView prepopulatedDirections;

    TextView prepopulatedTitle;

    Button startButton;

    Button generateQRButton;

    boolean isResetLocalStorageClicked;

    boolean hasBeenSaved = false;

    int i;

    String leftOrRight;











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

        i++;

        if (i < 1) {

            Intent intent = new Intent(this, Settings.class);

            startActivity(intent);

        }

        scouterNameInput = findViewById(R.id.ScouterNameInput);

        matchNumberInput = findViewById(R.id.MatchNumberInput);

        teamNumberInput = findViewById(R.id.TeamNumberInput);

        firstAlliancePartnerInput = findViewById(R.id.FirstAlliancePartnerInput);

        secondAlliancePartnerInput = findViewById(R.id.SecondAlliancePartnerInput);

        NoShowSwitch = findViewById(R.id.NoShowSwitch);

        //starting listener to check the status of the switch

        NoShowSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {

                    noShowStatus = 1;

                    panelButton.setEnabled(false);

                    cargoButton.setEnabled(false);

                    prepopulatedTitle = findViewById(R.id.IDPrepopulated);

                    prepopulatedTitle.setTextColor(getResources().getColor(R.color.grey));

                    prepopulatedDirections = (findViewById(R.id.IDPrepopulatedDirections));

                    prepopulatedDirections.setTextColor(getResources().getColor(R.color.grey));

                    startButton = findViewById(R.id.StartButton);

                    generateQRButton = findViewById(R.id.GenerateQRCodeButton);

                    startButton.setVisibility(View.GONE);

                    generateQRButton.setVisibility(View.VISIBLE);

                    if (isBlueAlliance == 1 || isRedAlliance == 1) {
                        generateQRButton.setEnabled(true);
                    }

                } else {

                    noShowStatus = 0;
                }

            }

        });

        blueButton = findViewById(R.id.BlueButton);

        blueButton.setBackgroundColor(getResources().getColor(R.color.light));

        blueButton.setTextColor(getResources().getColor(R.color.grey));

        redButton = findViewById(R.id.RedButton);

        redButton.setBackgroundColor(getResources().getColor(R.color.light));

        redButton.setTextColor(getResources().getColor(R.color.grey));

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

        //get intent from settings screen and the variable that says whether it is left or right

        Intent intent = getIntent();

        leftOrRight = intent.getStringExtra(Settings.EXTRA_MESSAGE);

        //set listener for QR Code generator
        generateQRButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String RedOrBlue = "";
                if (isBlueAlliance == 1) {
                    RedOrBlue = "Blue";
                }
                else if (isRedAlliance == 1) {
                    RedOrBlue = "Red";
                }
                QRValue = scouterNameInput.getText().toString() + "+" + teamNumberInput.getText().toString()
                        + "+" + matchNumberInput.getText().toString() + "+" + firstAlliancePartnerInput.getText().toString()
                        + "+" + secondAlliancePartnerInput.getText().toString() + "+" + RedOrBlue;
                try {
                    bitmap = TextToImageEncode(QRValue);
                    imageView.setImageBitmap(bitmap);

                } catch (WriterException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    //setters

    public void setScouterName(String newString) {

        this.scouterName = newString;

    }

    public void setMatchNumber(int newInt) {

        this.matchNumber = newInt;

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

        scouterName = scouterNameInput.getText().toString();

        matchNumber = Integer.parseInt(matchNumberInput.getText().toString());

        teamNumber = Integer.parseInt(teamNumberInput.getText().toString());

        firstAlliancePartner = Integer.parseInt(firstAlliancePartnerInput.getText().toString());

        secondAlliancePartner = Integer.parseInt(secondAlliancePartnerInput.getText().toString());

        if (isBlueAlliance == 0) {

            blueButton.setBackgroundColor(getResources().getColor(R.color.blue));

            blueButton.setTextColor(getResources().getColor(R.color.light));

            redDefault();





            isBlueAlliance = 1;

            if (!scouterName.equals("") && teamNumber!= 0 && matchNumber != 0 && firstAlliancePartner != 0 && secondAlliancePartner != 0)
                startButton.setEnabled(true);

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

        }

    }



    public void redClick (View view) {

        scouterName = scouterNameInput.getText().toString();

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

            if (teamNumber != 0 && matchNumber != 0) {

                startButton.setEnabled(true);

            }

            if (leftOrRight.equals("Left")) {
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
            else {
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

        }

        else {

            blueDefault();

            startButton.setEnabled(false);

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
                        getResources().getColor(R.color.colorPrimaryDark):getResources().getColor(R.color.bootstrap_dropdown_divider);
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(bitMatrixWidth, bitMatrixHeight, Bitmap.Config.ARGB_4444);

        bitmap.setPixels(pixels, 0, 500, 0, 0, bitMatrixWidth, bitMatrixHeight);
        return bitmap;
    }

}