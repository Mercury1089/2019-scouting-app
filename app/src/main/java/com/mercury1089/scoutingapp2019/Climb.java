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
         SandstormButton = findViewById(R.id.ClimbSandstormButton);
         TeleopButton = findViewById(R.id.ClimbTeleopButton);
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

        disabledButtonColors(SetupButton);
        disabledButtonColors(SandstormButton);
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

        Level1Button.setEnabled(false);
        Level2Button.setEnabled(false);
        Level3Button.setEnabled(false);

        OnYourOwnButton.setEnabled(false);
        WithHelpButton.setEnabled(false);

        OnePartnerButton.setEnabled(false);
        TwoPartnerButton.setEnabled(false);

        GenerateQRButton.setEnabled(false);

        GenerateQRButton.setBackgroundColor(getResources().getColor(R.color.light));
        GenerateQRButton.setTextColor(getResources().getColor(R.color.grey));

        Serializable setupData = getIntent().getSerializableExtra("setupHashMap");

        setupHashMap = (HashMap<String, String>) setupData;

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
        OnHABButton.setEnabled(true);
        OffHABButton.setEnabled(true);
        Level1Button.setEnabled(true);
        Level2Button.setEnabled(true);
        Level3Button.setEnabled(true);
        OnYourOwnButton.setEnabled(false);
        WithHelpButton.setEnabled(false);
        HasLiftedButton.setEnabled(false);
        HasNotLiftedButton.setEnabled(false);
        OnePartnerButton.setEnabled(false);
        TwoPartnerButton.setEnabled(false);

        defaultButtonState(OnHABButton);
        defaultButtonState(OffHABButton);
        defaultButtonState(Level1Button);
        defaultButtonState(Level2Button);
        defaultButtonState(Level3Button);
        disabledButtonColors(OnYourOwnButton);
        disabledButtonColors(WithHelpButton);
        disabledButtonColors(HasLiftedButton);
        disabledButtonColors(HasNotLiftedButton);
        disabledButtonColors(OnePartnerButton);
        disabledButtonColors(TwoPartnerButton);

        selectedButtonColors(OnHABButton);
        defaultButtonState(OffHABButton);

        setupHashMap.put("ClimbLevel", String.valueOf(0));
        setupHashMap.put("ClimbPartners", String.valueOf(0));
        setupHashMap.put("SelfOrWithHelp", "");
    }

    public void OffHABClick (View view) {
        OnHABButton.setEnabled(true);
        OffHABButton.setEnabled(true);
        Level1Button.setEnabled(false);
        Level2Button.setEnabled(false);
        Level3Button.setEnabled(false);
        OnYourOwnButton.setEnabled(false);
        WithHelpButton.setEnabled(false);
        HasLiftedButton.setEnabled(true);
        HasNotLiftedButton.setEnabled(true);
        OnePartnerButton.setEnabled(false);
        TwoPartnerButton.setEnabled(false);

        defaultButtonState(OnHABButton);
        defaultButtonState(OffHABButton);
        disabledButtonColors(Level1Button);
        disabledButtonColors(Level2Button);
        disabledButtonColors(Level3Button);
        disabledButtonColors(OnYourOwnButton);
        disabledButtonColors(WithHelpButton);
        defaultButtonState(HasLiftedButton);
        defaultButtonState(HasNotLiftedButton);
        disabledButtonColors(OnePartnerButton);
        disabledButtonColors(TwoPartnerButton);

        selectedButtonColors(OffHABButton);
        defaultButtonState(OnHABButton);

        setupHashMap.put("ClimbLevel", String.valueOf(0));
        setupHashMap.put("ClimbPartners", String.valueOf(0));
        setupHashMap.put("SelfOrWithHelp", "");
    }

    public void Level1Click (View view) {
        OnHABButton.setEnabled(true);
        OffHABButton.setEnabled(true);
        Level1Button.setEnabled(true);
        Level2Button.setEnabled(true);
        Level3Button.setEnabled(true);
        OnYourOwnButton.setEnabled(false);
        WithHelpButton.setEnabled(false);
        HasLiftedButton.setEnabled(true);
        HasNotLiftedButton.setEnabled(true);
        OnePartnerButton.setEnabled(false);
        TwoPartnerButton.setEnabled(false);

        defaultButtonState(OnHABButton);
        defaultButtonState(OffHABButton);
        defaultButtonState(Level1Button);
        defaultButtonState(Level2Button);
        defaultButtonState(Level3Button);
        disabledButtonColors(OnYourOwnButton);
        disabledButtonColors(WithHelpButton);
        defaultButtonState(HasLiftedButton);
        defaultButtonState(HasNotLiftedButton);
        disabledButtonColors(OnePartnerButton);
        disabledButtonColors(TwoPartnerButton);

        selectedButtonColors(Level1Button);
        defaultButtonState(Level2Button);
        defaultButtonState(Level3Button);

        setupHashMap.put("ClimbLevel", String.valueOf(1));
        setupHashMap.put("ClimbPartners", String.valueOf(0));
        setupHashMap.put("SelfOrWithHelp", "");
    }

    public void Level2Click (View view) {
        OnYourOwnButton.setEnabled(true);
        WithHelpButton.setEnabled(true);
        Level1Button.setEnabled(true);
        Level2Button.setEnabled(true);
        Level3Button.setEnabled(true);
        OnYourOwnButton.setEnabled(true);
        WithHelpButton.setEnabled(true);
        HasLiftedButton.setEnabled(false);
        HasNotLiftedButton.setEnabled(false);
        OnePartnerButton.setEnabled(false);
        TwoPartnerButton.setEnabled(false);

        defaultButtonState(OnHABButton);
        defaultButtonState(OffHABButton);
        defaultButtonState(Level1Button);
        defaultButtonState(Level2Button);
        defaultButtonState(Level3Button);
        defaultButtonState(OnYourOwnButton);
        defaultButtonState(WithHelpButton);
        disabledButtonColors(HasLiftedButton);
        disabledButtonColors(HasNotLiftedButton);
        disabledButtonColors(OnePartnerButton);
        disabledButtonColors(TwoPartnerButton);

        defaultButtonState(Level1Button);
        selectedButtonColors(Level2Button);
        defaultButtonState(Level3Button);

        setupHashMap.put("ClimbLevel", String.valueOf(2));
    }

    public void Level3Click (View view) {
        OnYourOwnButton.setEnabled(true);
        WithHelpButton.setEnabled(true);
        Level1Button.setEnabled(true);
        Level2Button.setEnabled(true);
        Level3Button.setEnabled(true);
        OnYourOwnButton.setEnabled(true);
        WithHelpButton.setEnabled(true);
        HasLiftedButton.setEnabled(false);
        HasNotLiftedButton.setEnabled(false);
        OnePartnerButton.setEnabled(false);
        TwoPartnerButton.setEnabled(false);

        defaultButtonState(OnHABButton);
        defaultButtonState(OffHABButton);
        defaultButtonState(Level1Button);
        defaultButtonState(Level2Button);
        defaultButtonState(Level3Button);
        defaultButtonState(OnYourOwnButton);
        defaultButtonState(WithHelpButton);
        disabledButtonColors(HasLiftedButton);
        disabledButtonColors(HasNotLiftedButton);
        disabledButtonColors(OnePartnerButton);
        disabledButtonColors(TwoPartnerButton);

        defaultButtonState(Level1Button);
        defaultButtonState(Level2Button);
        selectedButtonColors(Level3Button);

        setupHashMap.put("ClimbLevel", String.valueOf(3));
    }

    public void OnTheirOwnClick (View view) {
        OnYourOwnButton.setEnabled(true);
        WithHelpButton.setEnabled(true);
        Level1Button.setEnabled(true);
        Level2Button.setEnabled(true);
        Level3Button.setEnabled(true);
        OnYourOwnButton.setEnabled(true);
        WithHelpButton.setEnabled(true);
        HasLiftedButton.setEnabled(true);
        HasNotLiftedButton.setEnabled(true);
        OnePartnerButton.setEnabled(false);
        TwoPartnerButton.setEnabled(false);

        defaultButtonState(OnHABButton);
        defaultButtonState(OffHABButton);
        defaultButtonState(Level1Button);
        defaultButtonState(Level2Button);
        defaultButtonState(Level3Button);
        defaultButtonState(OnYourOwnButton);
        defaultButtonState(WithHelpButton);
        defaultButtonState(HasLiftedButton);
        defaultButtonState(HasNotLiftedButton);
        disabledButtonColors(OnePartnerButton);
        disabledButtonColors(TwoPartnerButton);

        selectedButtonColors(OnYourOwnButton);
        defaultButtonState(WithHelpButton);

        setupHashMap.put("SelfOrWithHelp", "S");
    }

    public void WithHelpClick (View view) {
        OnYourOwnButton.setEnabled(true);
        WithHelpButton.setEnabled(true);
        Level1Button.setEnabled(true);
        Level2Button.setEnabled(true);
        Level3Button.setEnabled(true);
        OnYourOwnButton.setEnabled(true);
        WithHelpButton.setEnabled(true);
        HasLiftedButton.setEnabled(true);
        HasNotLiftedButton.setEnabled(true);
        OnePartnerButton.setEnabled(false);
        TwoPartnerButton.setEnabled(false);

        defaultButtonState(OnHABButton);
        defaultButtonState(OffHABButton);
        defaultButtonState(Level1Button);
        defaultButtonState(Level2Button);
        defaultButtonState(Level3Button);
        defaultButtonState(OnYourOwnButton);
        defaultButtonState(WithHelpButton);
        defaultButtonState(HasLiftedButton);
        defaultButtonState(HasNotLiftedButton);
        disabledButtonColors(OnePartnerButton);
        disabledButtonColors(TwoPartnerButton);

        defaultButtonState(OnYourOwnButton);
        selectedButtonColors(WithHelpButton);

        setupHashMap.put("SelfOrWithHelp", "H");
        setupHashMap.put("ClimbPartners", String.valueOf(0));
    }

    public void HasLiftedClick (View view) {
        OnYourOwnButton.setEnabled(true);
        WithHelpButton.setEnabled(true);
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

        selectedButtonColors(HasLiftedButton);
        defaultButtonState(HasNotLiftedButton);
    }



    public void HasNotLiftedClick (View view) {
        OnYourOwnButton.setEnabled(true);
        WithHelpButton.setEnabled(true);
        Level1Button.setEnabled(true);
        Level2Button.setEnabled(true);
        Level3Button.setEnabled(true);
        OnYourOwnButton.setEnabled(true);
        WithHelpButton.setEnabled(true);
        HasLiftedButton.setEnabled(true);
        HasNotLiftedButton.setEnabled(true);
        OnePartnerButton.setEnabled(false);
        TwoPartnerButton.setEnabled(false);

        defaultButtonState(OnHABButton);
        defaultButtonState(OffHABButton);
        defaultButtonState(Level1Button);
        defaultButtonState(Level2Button);
        defaultButtonState(Level3Button);
        defaultButtonState(OnYourOwnButton);
        defaultButtonState(WithHelpButton);
        defaultButtonState(HasLiftedButton);
        defaultButtonState(HasNotLiftedButton);
        disabledButtonColors(OnePartnerButton);
        disabledButtonColors(TwoPartnerButton);

        defaultButtonState(HasLiftedButton);
        selectedButtonColors(HasNotLiftedButton);

        activeButtonColors(GenerateQRButton);
        GenerateQRButton.setEnabled(true);
    }

    public void OnePartnerClick (View view) {
        OnYourOwnButton.setEnabled(true);
        WithHelpButton.setEnabled(true);
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

        selectedButtonColors(OnePartnerButton);
        defaultButtonState(TwoPartnerButton);

        activeButtonColors(GenerateQRButton);

        GenerateQRButton.setEnabled(true);

        setupHashMap.put("ClimbPartners", String.valueOf(1));
    }

    public void TwoPartnerClick (View view) {
        OnYourOwnButton.setEnabled(true);
        WithHelpButton.setEnabled(true);
        Level1Button.setEnabled(true);
        Level2Button.setEnabled(true);
        Level3Button.setEnabled(true);
        OnYourOwnButton.setEnabled(true);
        WithHelpButton.setEnabled(true);
        HasLiftedButton.setEnabled(true);
        HasNotLiftedButton.setEnabled(true);
        OnePartnerButton.setEnabled(false);
        TwoPartnerButton.setEnabled(false);

        defaultButtonState(OnHABButton);
        defaultButtonState(OffHABButton);
        defaultButtonState(Level1Button);
        defaultButtonState(Level2Button);
        defaultButtonState(Level3Button);
        defaultButtonState(OnYourOwnButton);
        defaultButtonState(WithHelpButton);
        defaultButtonState(HasLiftedButton);
        defaultButtonState(HasNotLiftedButton);
        disabledButtonColors(OnePartnerButton);
        disabledButtonColors(TwoPartnerButton);

        defaultButtonState(OnePartnerButton);
        selectedButtonColors(TwoPartnerButton);

        activeButtonColors(GenerateQRButton);

        GenerateQRButton.setEnabled(true);

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
                QRString.append(setupHashMap.get("NoShow"));

                Object keySet[] = scoreHashMap.keySet().toArray();
                for (int i = 0; i < keySet.length; i++) {
                    key = "" + keySet[i];
                    QRString.append(",").append(key).append(",");
                    if (scoreHashMap.get(key) != null)
                        QRString.append(String.valueOf(scoreHashMap.get(key)));
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
                        goBackToMain.setEnabled(false);
                        goBackToMain.setBackgroundColor(getResources().getColor((R.color.savedefault)));
                        goBackToMain.setTextColor(getResources().getColor(R.color.savetextdefault));
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
                                else{
                                    goBackToMain.setEnabled(false);
                                    goBackToMain.setBackgroundColor(getResources().getColor((R.color.defaultdisabled)));
                                    goBackToMain.setTextColor(getResources().getColor(R.color.textdefault));
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
