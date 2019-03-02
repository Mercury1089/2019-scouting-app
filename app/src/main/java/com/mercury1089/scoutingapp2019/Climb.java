package com.mercury1089.scoutingapp2019;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import java.io.Serializable;
import java.util.HashMap;

public class Climb extends AppCompatActivity {

    //bootstrap buttons
    private BootstrapButton btnSetup;
    private BootstrapButton btnSandstorm;
    private BootstrapButton btnTeleop;
    private BootstrapButton btnClimb;

    private BootstrapButton btnEndOnHab;
    private BootstrapButton btnEndOffHab;

    private BootstrapButton btnClimbLevel1;
    private BootstrapButton btnClimbLevel2;
    private BootstrapButton btnClimbLevel3;

    private BootstrapButton btnClimbIndependent;
    private BootstrapButton btnClimbDependent;

    private BootstrapButton btnClimbHelper;
    private BootstrapButton btnNotClimbHelper;

    private BootstrapButton btnLiftOneTeammate;
    private BootstrapButton btnLiftTwoTeammate;

    private Button btnGenerateQrCode;

    private HashMap<String, String> setupHashMap;
    private HashMap<String, String> scoreHashMap;

    private ProgressDialog progressDialog;

    public final static int QRCodeSize = 500;

    public String QRValue = "";

    int OnHAB = 0;
    int level = 0;
    int OnTheirOwn = 0;
    int HasLifted = 1;
    int HowManyPartners = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_climb);

         btnSetup = findViewById(R.id.ClimbSetupButton);
         btnSandstorm = findViewById(R.id.ClimbSandstormButton);
         btnTeleop = findViewById(R.id.ClimbTeleopButton);
         btnClimb = findViewById(R.id.ClimbClimbButton);

         //Did they end the match on HAB buttons
         btnEndOnHab = findViewById(R.id.OnHabButton);
         btnEndOnHab.setEnabled(true);

         btnEndOffHab = findViewById(R.id.OffHabButton);
         btnEndOffHab.setEnabled(true);

         //HAB Climb level buttons are
         //disabled by default
         btnClimbLevel1 = findViewById(R.id.Level1Button);
         btnClimbLevel1.setEnabled(false);

         btnClimbLevel2 = findViewById(R.id.Level2Button);
         btnClimbLevel2.setEnabled(false);

         btnClimbLevel3 = findViewById(R.id.Level3Button);
         btnClimbLevel3.setEnabled(false);

         //Did they climb on their own buttons
         //Disabled by default
         btnClimbIndependent = findViewById(R.id.IsSoloButton);
         btnClimbIndependent.setEnabled(false);

         btnClimbDependent = findViewById(R.id.WithHelpButton);
         btnClimbDependent.setEnabled(false);

         //Did they lift a member of their alliance buttons
         //Disabled by default
         btnClimbHelper = findViewById(R.id.HasLiftedButton);
         btnClimbHelper.setEnabled(false);

         btnNotClimbHelper = findViewById(R.id.HasNotLiftedButton);
         btnNotClimbHelper.setEnabled(false);

         //Buttons to select how many members were lifted
         //Disabled by default
         btnLiftOneTeammate = findViewById(R.id.OnePartnerButton);
         btnLiftOneTeammate.setEnabled(false);

         btnLiftTwoTeammate = findViewById(R.id.TwoPartnersButton);
         btnLiftTwoTeammate.setEnabled(false);

         //Button to generate QR code
         btnGenerateQrCode = findViewById(R.id.ClimbGenerateQRButton);
         btnGenerateQrCode.setEnabled(false);

        setDefaultColorState(btnSetup);
        setDefaultColorState(btnSandstorm);
        setDefaultColorState(btnTeleop);
        setSelectedColorState(btnClimb);

        setDefaultColorState(btnEndOnHab);
        setDefaultColorState(btnEndOffHab);

        setDefaultColorState(btnClimbLevel1);
        setDefaultColorState(btnClimbLevel2);
        setDefaultColorState(btnClimbLevel3);

        setDefaultColorState(btnClimbIndependent);
        setDefaultColorState(btnClimbDependent);

        setDefaultColorState(btnClimbHelper);
        setDefaultColorState(btnNotClimbHelper);

        setDefaultColorState(btnLiftOneTeammate);
        setDefaultColorState(btnLiftTwoTeammate);

        btnClimbLevel1.setEnabled(false);
        btnClimbLevel2.setEnabled(false);
        btnClimbLevel3.setEnabled(false);

        btnClimbIndependent.setEnabled(false);
        btnClimbDependent.setEnabled(false);

        btnClimbHelper.setEnabled(false);
        btnNotClimbHelper.setEnabled(false);

        btnLiftOneTeammate.setEnabled(false);
        btnLiftTwoTeammate.setEnabled(false);

        btnGenerateQrCode.setEnabled(false);

        btnGenerateQrCode.setBackgroundColor(getResources().getColor(R.color.light));
        btnGenerateQrCode.setTextColor(getResources().getColor(R.color.grey));

        Serializable setupData = getIntent().getSerializableExtra("setupHashMap");

        setupHashMap = (HashMap<String, String>) setupData;

        Serializable scoreData = getIntent().getSerializableExtra("scoreHashMap");
        scoreHashMap = (HashMap<String, String>) scoreData;
    }

    private void setDefaultColorState(BootstrapButton button) {
        button.setBackgroundColor(getResources().getColor(R.color.light));
        button.setTextColor(getResources().getColor(R.color.grey));
    }
    public void setSelectedColorState(BootstrapButton button) {
        button.setBackgroundColor(getResources().getColor(R.color.orange));
        button.setTextColor(getResources().getColor(R.color.light));
    }

    public void SetupClick (View view) {
        Intent intent = new Intent(this, MainActivity.class);
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
        setSelectedColorState(btnEndOnHab);
        setDefaultColorState(btnEndOffHab);
        btnClimbLevel1.setEnabled(true);
        btnClimbLevel2.setEnabled(true);
        btnClimbLevel3.setEnabled(true);
        OnHAB = 1;
    }
    public void OffHABClick (View view) {
        setSelectedColorState(btnEndOffHab);
        setDefaultColorState(btnEndOnHab);
        btnGenerateQrCode.setEnabled(true);
        OnHAB = 0;
    }

    public void Level1Click (View view) {
        setSelectedColorState(btnClimbLevel1);
        setDefaultColorState(btnClimbLevel2);
        setDefaultColorState(btnClimbLevel3);

        btnGenerateQrCode.setEnabled(true);

        level = 1;
    }

    public void Level2Click (View view) {
        setDefaultColorState(btnClimbLevel1);
        setSelectedColorState(btnClimbLevel2);
        setDefaultColorState(btnClimbLevel3);

        btnClimbIndependent.setEnabled(true);
        btnClimbDependent.setEnabled(true);

        level = 2;
    }

    public void Level3Click (View view) {
        setDefaultColorState(btnClimbLevel1);
        setDefaultColorState(btnClimbLevel2);
        setSelectedColorState(btnClimbLevel3);

        btnClimbIndependent.setEnabled(true);
        btnClimbDependent.setEnabled(true);

        level = 3;
    }

    public void OnTheirOwnClick (View view) {
        setSelectedColorState(btnClimbIndependent);
        setDefaultColorState(btnClimbDependent);

        btnClimbHelper.setEnabled(true);
        btnNotClimbHelper.setEnabled(true);

        OnTheirOwn = 1;
    }

    public void WithHelpClick (View view) {
        setSelectedColorState(btnClimbIndependent);
        setDefaultColorState(btnClimbDependent);

        btnGenerateQrCode.setEnabled(true);

        OnTheirOwn = 0;
    }

    public void HasLiftedClick (View view) {
        setSelectedColorState(btnClimbHelper);
        setDefaultColorState(btnNotClimbHelper);

        btnLiftOneTeammate.setEnabled(true);
        btnLiftTwoTeammate.setEnabled(true);

        HasLifted = 1;
    }

    public void HasNotLiftedClick (View view) {
        setDefaultColorState(btnClimbHelper);
        setSelectedColorState(btnNotClimbHelper);

        btnGenerateQrCode.setEnabled(true);

        HasLifted = 0;
    }

    public void OnePartnerClick (View view) {
        HowManyPartners = 1;
        setSelectedColorState(btnLiftOneTeammate);
        setDefaultColorState(btnLiftTwoTeammate);

        btnGenerateQrCode.setEnabled(true);
    }

    public void TwoPartnerClick (View view) {
        HowManyPartners = 2;
        setDefaultColorState(btnLiftOneTeammate);
        setSelectedColorState(btnLiftTwoTeammate);

        btnGenerateQrCode.setEnabled(true);
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
                Object keyset[] = setupHashMap.keySet().toArray();
                for (int i = 0; i < keyset.length; i++) {
                    key = "" + keyset[i];
                    QRString.append(setupHashMap.get(key)).append(",");
                }
                Object keySet[] = scoreHashMap.keySet().toArray();
                for (int i = 0; i < keySet.length; i++) {
                    key = "" + keySet[i];
                    QRString.append(key).append(",").append(scoreHashMap.get(key)).append(",");
                }
                QRValue = QRString.toString();
                final Bitmap bitmap = TextToImageEncode(QRValue);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        final AlertDialog.Builder qrDialog = new AlertDialog.Builder(Climb.this);
                        View view1 = getLayoutInflater().inflate(R.layout.qr_popup,null);
                        ImageView imageView = view1.findViewById(R.id.imageView);
                        Button goBackToMain = view1.findViewById(R.id.GoBackButton);

                        imageView.setImageBitmap(bitmap);
                        qrDialog.setView(view1);

                        final AlertDialog dialog = qrDialog.create();

                        progressDialog.cancel();
                        dialog.show();
                        goBackToMain.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.cancel();
                                Intent intent = new Intent (Climb.this, MainActivity.class);
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
