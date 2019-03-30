package com.mercury1089.scoutingapp2019;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Climb extends AppCompatActivity {

    //bootstrap buttons
    private BootstrapButton SetupButton;
    private BootstrapButton SandstormButton;
    private BootstrapButton TeleopButton;
    private BootstrapButton ClimbButton;

    private BootstrapButton OnHABButton;
    private BootstrapButton OffHABButton;

    private BootstrapButton Level1Button;
    private BootstrapButton Level2Button;
    private BootstrapButton Level3Button;

    private BootstrapButton OnYourOwnButton;
    private BootstrapButton WithHelpButton;

    private BootstrapButton HasLiftedButton;
    private BootstrapButton HasNotLiftedButton;

    private BootstrapButton OnePartnerButton;
    private BootstrapButton TwoPartnerButton;

    private Button GenerateQRButton;

    //boolean variables
    private boolean isOnHAB = false;
    private boolean isOffHAB = false;
    private boolean isLevel1HAB = false;
    private boolean isLevel2HAB = false;
    private boolean isLevel3HAB = false;
    private boolean isOnYourOwn = false;
    private boolean isWithHelp = false;
    private boolean isLifting = false;
    private boolean isNotLifting = false;
    private boolean isOnePartner = false;
    private boolean isTwoPartner = false;



    private HashMap<String, String> setupHashMap;
    private HashMap<String, String> scoreHashMap;

    private ProgressDialog progressDialog;

    public final static int QRCodeSize = 500;

    public String QRValue = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_climb);

        SetupButton = findViewById(R.id.ClimbSetupButton);
        SetupButton.setEnabled(false);

        SandstormButton = findViewById(R.id.ClimbSandstormButton);
        SandstormButton.setEnabled(false);

        TeleopButton = findViewById(R.id.ClimbTeleopButton);
        TeleopButton.setEnabled(false);

        ClimbButton = findViewById(R.id.ClimbClimbButton);

        OnHABButton = findViewById(R.id.OnHabButton);
        OffHABButton = findViewById(R.id.OffHabButton);

        Level1Button = findViewById(R.id.Level1Button);
        Level2Button = findViewById(R.id.Level2Button);
        Level3Button = findViewById(R.id.Level3Button);

        OnYourOwnButton = findViewById(R.id.IsSoloButton);
        WithHelpButton = findViewById(R.id.WithHelpButton);

        HasLiftedButton = findViewById(R.id.HasLiftedButton);
        HasNotLiftedButton = findViewById(R.id.HasNotLiftedButton);

        OnePartnerButton = findViewById(R.id.OnePartnerButton);
        TwoPartnerButton = findViewById(R.id.TwoPartnersButton);

        GenerateQRButton = findViewById(R.id.ClimbGenerateQRButton);



        defaultButtonState(TeleopButton);
        selectedButtonColors(ClimbButton);

        defaultButtonState(OnHABButton);
        defaultButtonState(OffHABButton);

        defaultButtonState(Level1Button);
        defaultButtonState(Level2Button);
        defaultButtonState(Level3Button);

        defaultButtonState(OnYourOwnButton);
        defaultButtonState(WithHelpButton);

        defaultButtonState(HasLiftedButton);
        defaultButtonState(HasNotLiftedButton);

        defaultButtonState(OnePartnerButton);
        defaultButtonState(TwoPartnerButton);

        Level1Button.setEnabled(true);
        Level2Button.setEnabled(true);
        Level3Button.setEnabled(true);

        OnYourOwnButton.setEnabled(true);
        WithHelpButton.setEnabled(true);

        OnePartnerButton.setEnabled(true);
        TwoPartnerButton.setEnabled(true);

        GenerateQRButton.setEnabled(true);

        GenerateQRButton.setBackgroundColor(getResources().getColor(R.color.light));
        GenerateQRButton.setTextColor(getResources().getColor(R.color.grey));

        Serializable setupData = getIntent().getSerializableExtra("setupHashMap");
        setupHashMap = (HashMap<String, String>) setupData;
        if (Integer.parseInt(setupHashMap.get("FellOver")) == 1) {
            disableAllButtons();
            generateQR('E');
        }
        else {
            disableAllButtons();
            yesOrNOButtons('E');
            generateQR('D');
        }


        Serializable scoreData = getIntent().getSerializableExtra("scoreHashMap");
        scoreHashMap = (HashMap<String, String>) scoreData;
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

    private void defaultButtonState (BootstrapButton button) {
        button.setBackgroundColor(getResources().getColor(R.color.light));
        button.setTextColor(getResources().getColor(R.color.grey));
    }
    public void selectedButtonColors(BootstrapButton button) {
        button.setBackgroundColor(getResources().getColor(R.color.orange));
        button.setTextColor(getResources().getColor(R.color.light));
    }
    public void disabledButtonColors(BootstrapButton button) {
        button.setBackgroundColor(getResources().getColor(R.color.savedefault));
        button.setTextColor(getResources().getColor(R.color.textdefault));
    }
    public void activeButtonColors(Button button) {
        button.setBackgroundColor(getResources().getColor(R.color.saveactive));
        button.setTextColor(getResources().getColor(R.color.light));
    }

    /*public void enableAllButtons() {
        OnHABButton.setEnabled(true);
        OffHABButton.setEnabled(true);
        Level1Button.setEnabled(true);
        Level2Button.setEnabled(true);
        Level3Button.setEnabled(true);
        OnYourOwnButton.setEnabled(true);
        WithHelpButton.setEnabled(true);
        HasLiftedButton.setEnabled(true);
        HasNotLiftedButton.setEnabled(true);
        OnePartnerButton.setEnabled(true);
        TwoPartnerButton.setEnabled(true);

        defaultButtonState(OnHABButton);
        defaultButtonState(OffHABButton);
        defaultButtonState(Level1Button);
        defaultButtonState(Level2Button);
        defaultButtonState(Level3Button);
        defaultButtonState(OnYourOwnButton);
        defaultButtonState(WithHelpButton);
        defaultButtonState(HasLiftedButton);
        defaultButtonState(HasNotLiftedButton);
        defaultButtonState(OnePartnerButton);
        defaultButtonState(TwoPartnerButton);
    }*/

    public void disableAllButtons() {
        OnHABButton.setEnabled(false);
        OffHABButton.setEnabled(false);
        Level1Button.setEnabled(false);
        Level2Button.setEnabled(false);
        Level3Button.setEnabled(false);
        OnYourOwnButton.setEnabled(false);
        WithHelpButton.setEnabled(false);
        HasLiftedButton.setEnabled(false);
        HasNotLiftedButton.setEnabled(false);
        OnePartnerButton.setEnabled(false);
        TwoPartnerButton.setEnabled(false);

        disabledButtonColors(OnHABButton);
        disabledButtonColors(OffHABButton);
        disabledButtonColors(Level1Button);
        disabledButtonColors(Level2Button);
        disabledButtonColors(Level3Button);
        disabledButtonColors(OnYourOwnButton);
        disabledButtonColors(WithHelpButton);
        disabledButtonColors(HasLiftedButton);
        disabledButtonColors(HasNotLiftedButton);
        disabledButtonColors(OnePartnerButton);
        disabledButtonColors(TwoPartnerButton);
    }

    public void yesOrNOButtons(char enabledOrDisabled) {
        if (enabledOrDisabled == 'E') {
            OnHABButton.setEnabled(true);
            OffHABButton.setEnabled(true);
            defaultButtonState(OnHABButton);
            defaultButtonState(OffHABButton);
        }
        else {
            OnHABButton.setEnabled(false);
            OffHABButton.setEnabled(false);
            disabledButtonColors(OnHABButton);
            disabledButtonColors(OffHABButton);

        }
        isOnHAB = false;
        isOffHAB = false;
    }

    public void levelButtons(char enabledOrDisabled) {
        if (enabledOrDisabled == 'E') {
            Level1Button.setEnabled(true);
            Level2Button.setEnabled(true);
            Level3Button.setEnabled(true);
            defaultButtonState(Level1Button);
            defaultButtonState(Level2Button);
            defaultButtonState(Level3Button);
        }
        else {
            Level1Button.setEnabled(false);
            Level2Button.setEnabled(false);
            Level3Button.setEnabled(false);
            disabledButtonColors(Level1Button);
            disabledButtonColors(Level2Button);
            disabledButtonColors(Level3Button);

        }
        isLevel1HAB = false;
        isLevel2HAB = false;
        isLevel3HAB = false;
    }

    public void onYourOwnOrWithHelp (char enabledOrDisabled) {
        if (enabledOrDisabled == 'E') {
            OnYourOwnButton.setEnabled(true);
            WithHelpButton.setEnabled(true);
            defaultButtonState(OnYourOwnButton);
            defaultButtonState(WithHelpButton);
        }
        else {
            OnYourOwnButton.setEnabled(false);
            WithHelpButton.setEnabled(false);
            disabledButtonColors(OnYourOwnButton);
            disabledButtonColors(WithHelpButton);

        }
        isOnYourOwn = false;
        isWithHelp  = false;
    }

    public void liftingPartners (char enabledOrDisabled) {
        if (enabledOrDisabled == 'E') {
            HasLiftedButton.setEnabled(true);
            HasNotLiftedButton.setEnabled(true);
            defaultButtonState(HasLiftedButton);
            defaultButtonState(HasNotLiftedButton);
        }
        else {
            HasLiftedButton.setEnabled(false);
            HasNotLiftedButton.setEnabled(false);
            disabledButtonColors(HasLiftedButton);
            disabledButtonColors(HasNotLiftedButton);

        }
        isLifting = false;
        isNotLifting = false;
    }

    public void howManyPartners (char enabledOrDisabled) {
        if (enabledOrDisabled == 'E') {
            OnePartnerButton.setEnabled(true);
            TwoPartnerButton.setEnabled(true);
            defaultButtonState(OnePartnerButton);
            defaultButtonState(TwoPartnerButton);
        }
        else {
            OnePartnerButton.setEnabled(false);
            TwoPartnerButton.setEnabled(false);
            disabledButtonColors(OnePartnerButton);
            disabledButtonColors(TwoPartnerButton);

        }
        isOnePartner = false;
        isTwoPartner = false;
    }

    public void generateQR (char enabledOrDisabled) {
        if (enabledOrDisabled == 'E') {
            GenerateQRButton.setEnabled(true);
            activeButtonColors(GenerateQRButton);
        }
        else {
            GenerateQRButton.setEnabled(false);
            GenerateQRButton.setBackgroundColor(getResources().getColor(R.color.savedefault));
            GenerateQRButton.setTextColor(getResources().getColor(R.color.textdefault));
        }
    }


    public void SetupClick (View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("setupHashMap", setupHashMap);
        intent.putExtra("setupHashMap", setupHashMap);
        startActivity(intent);
    }
    public void SandstormClick (View view) {
        Intent intent = new Intent(this, Sandstorm.class);
        intent.putExtra("setupHashMap", setupHashMap);
        intent.putExtra("scoreHashMap", scoreHashMap);
        startActivity(intent);
    }
    public void TeleopClick (View view) {
        Intent intent = new Intent(this, Teleop.class);
        intent.putExtra("setupHashMap", setupHashMap);
        intent.putExtra("scoreHashMap", scoreHashMap);
        startActivity(intent);
    }



    public void OnHABClick (View view) {
        isOnHAB = !isOnHAB;
        if (!isOnHAB) {
            defaultButtonState(OnHABButton);
            yesOrNOButtons('E');
            levelButtons('D');
            isOnHAB = false;
        }
        else {
            defaultButtonState(OffHABButton);
            selectedButtonColors(OnHABButton);
            isOnHAB = true;
            isOffHAB = false;
            levelButtons('E');
        }


        onYourOwnOrWithHelp('D');
        liftingPartners('D');
        howManyPartners('D');
        generateQR('D');

        setupHashMap.put("ClimbLevel", String.valueOf(0));
        setupHashMap.put("ClimbPartners", String.valueOf(0));
        setupHashMap.put("SelfOrWithHelp", "");
    }



    public void OffHABClick (View view) {
        isOffHAB = !isOffHAB;
        if (!isOffHAB) {
            defaultButtonState(OnHABButton);
            isOffHAB = false;
            yesOrNOButtons('E');
            liftingPartners('D');
        }
        else {
            defaultButtonState(OnHABButton);
            selectedButtonColors(OffHABButton);
            isOnHAB = false;
            isOffHAB = true;
            liftingPartners('E');
        }

        levelButtons('D');
        onYourOwnOrWithHelp('D');

        howManyPartners('D');
        generateQR('D');

        setupHashMap.put("ClimbLevel", String.valueOf(0));
        setupHashMap.put("ClimbPartners", String.valueOf(0));
        setupHashMap.put("SelfOrWithHelp", "");
    }



    public void Level1Click (View view) {
        isLevel1HAB = !isLevel1HAB;
        if (!isLevel1HAB) {
            defaultButtonState(Level1Button);
            isLevel1HAB = false;
            levelButtons('E');
            liftingPartners('D');
        }
        else {
            selectedButtonColors(Level1Button);
            defaultButtonState(Level2Button);
            defaultButtonState(Level3Button);
            isLevel1HAB = true;
            isLevel2HAB = false;
            isLevel3HAB = false;
            liftingPartners('E');
        }

        onYourOwnOrWithHelp('D');

        howManyPartners('D');
        generateQR('D');

        setupHashMap.put("ClimbLevel", String.valueOf(1));
        setupHashMap.put("ClimbPartners", String.valueOf(0));
        setupHashMap.put("SelfOrWithHelp", "");
    }

    public void Level2Click (View view) {
        isLevel2HAB = !isLevel2HAB;

        if (!isLevel2HAB) {
            defaultButtonState(Level2Button);
            isLevel2HAB = false;
            levelButtons('E');
            onYourOwnOrWithHelp('D');
        }
        else {
            defaultButtonState(Level1Button);
            selectedButtonColors(Level2Button);
            defaultButtonState(Level3Button);

            isLevel1HAB = false;
            isLevel2HAB = true;
            isLevel3HAB = false;

            onYourOwnOrWithHelp('E');
        }

        liftingPartners('D');
        howManyPartners('D');
        generateQR('D');

        setupHashMap.put("ClimbLevel", String.valueOf(2));
    }



    public void Level3Click (View view) {
        isLevel3HAB = !isLevel3HAB;
        if (!isLevel3HAB) {
            defaultButtonState(Level3Button);
            isLevel3HAB = false;

            levelButtons('E');
            onYourOwnOrWithHelp('D');
        }
        else {
            defaultButtonState(Level1Button);
            defaultButtonState(Level2Button);
            selectedButtonColors(Level3Button);

            isLevel1HAB = false;
            isLevel2HAB = false;
            isLevel3HAB = true;

            onYourOwnOrWithHelp('E');
        }


        liftingPartners('D');
        howManyPartners('D');
        generateQR('D');

        setupHashMap.put("ClimbLevel", String.valueOf(3));
    }



    public void OnTheirOwnClick (View view) {
        isOnYourOwn = !isOnYourOwn;
        if (!isOnYourOwn) {
            onYourOwnOrWithHelp('E');
            liftingPartners('D');
        }
        else {
            defaultButtonState(WithHelpButton);
            selectedButtonColors(OnYourOwnButton);
            isWithHelp = false;
            isOnYourOwn = true;
            liftingPartners('E');
        }


        howManyPartners('D');
        generateQR('D');

        setupHashMap.put("SelfOrWithHelp", "S");
    }



    public void WithHelpClick (View view) {
        isWithHelp = !isWithHelp;

        if (!isWithHelp) {
            onYourOwnOrWithHelp('E');
            generateQR('D');
        }
        else {
            selectedButtonColors(WithHelpButton);
            defaultButtonState(OnYourOwnButton);
            isWithHelp = true;
            isOnYourOwn = false;
            generateQR('E');
        }

        liftingPartners('D');
        howManyPartners('D');


        setupHashMap.put("SelfOrWithHelp", "H");
        setupHashMap.put("ClimbPartners", String.valueOf(0));
    }


    public void HasLiftedClick (View view) {
        isLifting = !isLifting;

        if (!isLifting) {
            liftingPartners('E');
            howManyPartners('D');
        }
        else {
            selectedButtonColors(HasLiftedButton);
            defaultButtonState(HasNotLiftedButton);
            isLifting = true;
            isNotLifting = false;
            howManyPartners('E');
        }


        generateQR('D');

    }


    public void HasNotLiftedClick (View view) {
        isNotLifting = !isNotLifting;

        if (!isNotLifting) {
            liftingPartners('E');
            generateQR('D');
        }
        else {
            defaultButtonState(HasLiftedButton);
            selectedButtonColors(HasNotLiftedButton);
            isLifting = false;
            isNotLifting = true;
            generateQR('E');
        }

        howManyPartners('D');
    }



    public void OnePartnerClick (View view) {
        isOnePartner = !isOnePartner;
        if (!isOnePartner) {
            howManyPartners('E');
            generateQR('D');
        }
        else {
            selectedButtonColors(OnePartnerButton);
            defaultButtonState(TwoPartnerButton);
            isOnePartner = true;
            isTwoPartner = false;
            generateQR('E');
        }


        setupHashMap.put("ClimbPartners", String.valueOf(1));
    }



    public void TwoPartnerClick (View view) {
        isTwoPartner = !isTwoPartner;
        if (!isTwoPartner) {
            howManyPartners('E');
            generateQR('D');
        }
        else {
            defaultButtonState(OnePartnerButton);
            selectedButtonColors(TwoPartnerButton);
            isOnePartner = false;
            isTwoPartner = true;
            generateQR('E');
        }



        setupHashMap.put("ClimbPartners", String.valueOf(2));
    }

    public void GenerateQRClick (View view) {
        progressDialog = new ProgressDialog(Climb.this, R.style.LoadingDialogStyle);
        progressDialog.setMessage("Please Wait");
        progressDialog.setCancelable(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        QRRunnable qrRunnable = new QRRunnable();
        new Thread(qrRunnable).start();
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
            String key;
            StringBuilder QRString = new StringBuilder();
            try {
                QRString.append(setupHashMap.get("ScouterName")).append(",");
                QRString.append(setupHashMap.get("MatchNumber")).append(",");
                QRString.append(setupHashMap.get("TeamNumber")).append(",");
                QRString.append(setupHashMap.get("FirstAlliancePartner")).append(",");
                QRString.append(setupHashMap.get("SecondAlliancePartner")).append(",");
                QRString.append(setupHashMap.get("AllianceColor")).append(",");
                QRString.append(setupHashMap.get("LeftOrRight")).append(",");
                QRString.append(setupHashMap.get("StartingPosition")).append(",");
                QRString.append(setupHashMap.get("HABLine")).append(",");
                QRString.append(setupHashMap.get("StartingGameObject")).append(",");
                QRString.append(setupHashMap.get("ClimbLevel")).append(",");
                QRString.append(setupHashMap.get("ClimbPartners")).append(",");
                QRString.append(setupHashMap.get("SelfOrWithHelp")).append(",");
                QRString.append(setupHashMap.get("FellOver")).append(",");
                QRString.append(setupHashMap.get("NoShow")).append(",");
                QRString.append(setupHashMap.get("TeleopPrepop"));

                Object keySet[] = scoreHashMap.keySet().toArray();
                for (int i = 0; i < keySet.length; i++) {
                    key = "" + keySet[i];
                    QRString.append(",").append(key).append(",");
                    if (scoreHashMap.get(key) != null) {
                        QRString.append(String.valueOf(scoreHashMap.get(key)));
                    }
                    else
                        continue;
                }
                QRValue = QRString.toString();

                Log.d("QRString",QRValue);

                final Bitmap bitmap = TextToImageEncode(QRValue);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        final AlertDialog.Builder qrDialog = new AlertDialog.Builder(Climb.this);

                        View view1 = getLayoutInflater().inflate(R.layout.qr_popup, null);
                        ImageView imageView = view1.findViewById(R.id.imageView);
                        Switch CheckSwitch = view1.findViewById(R.id.checkSwitch);
                        TextView teamNumber = view1.findViewById(R.id.TeamNumberQR);
                        TextView matchNumber = view1.findViewById(R.id.MatchNumberQR);
                        final Button goBackToMain = view1.findViewById(R.id.GoBackButton);
                        imageView.setImageBitmap(bitmap);
                        qrDialog.setView(view1);
                        final AlertDialog dialog = qrDialog.create();

                        progressDialog.dismiss();
                        teamNumber.setText("Team Number: " + setupHashMap.get("TeamNumber"));
                        matchNumber.setText("Match Number: " + setupHashMap.get("MatchNumber"));

                        dialog.show();

                        CheckSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                if (isChecked) {
                                    goBackToMain.setEnabled(true);
                                    goBackToMain.setBackgroundColor(getResources().getColor((R.color.blue)));
                                    goBackToMain.setTextColor(getResources().getColor(R.color.light));
                                }

                            }

                        });
                        goBackToMain.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.cancel();
                                Intent intent = new Intent (Climb.this, MainActivity.class);
                                intent.putExtra("LEFTORRIGHT",setupHashMap.get("LeftOrRight"));
                                startActivity(intent);
                            }
                        });
                    }
                });
            }
            catch (WriterException e) {
                e.printStackTrace();
            }
        }
    }
}
