package com.mercury1089.scoutingapp2019;

import android.app.AlertDialog;
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

    private BootstrapButton GenerateQRButton;

    private HashMap<String, String> setupHashMap;
    private HashMap<String, String> scoreHashMap;

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

        BootstrapButton SetupButton = findViewById(R.id.ClimbSetupButton);
        BootstrapButton SandstormButton = findViewById(R.id.ClimbSandstormButton);
        BootstrapButton TeleopButton = findViewById(R.id.ClimbTeleopButton);
        BootstrapButton ClimbButton = findViewById(R.id.ClimbClimbButton);

        BootstrapButton OnHABButton = findViewById(R.id.OnHabButton);
        BootstrapButton OffHABButton = findViewById(R.id.OffHabButton);

        BootstrapButton Level1Button = findViewById(R.id.Level1Button);
        BootstrapButton Level2Button = findViewById(R.id.Level2Button);
        BootstrapButton Level3Button = findViewById(R.id.Level3Button);

        BootstrapButton OnYourOwnButton = findViewById(R.id.IsSoloButton);
        BootstrapButton WithHelpButton = findViewById(R.id.WithHelpButton);

        BootstrapButton HasLiftedButton = findViewById(R.id.HasLiftedButton);;
        BootstrapButton HasNotLiftedButton = findViewById(R.id.HasNotLiftedButton);;

        BootstrapButton OnePartnerButton = findViewById(R.id.OnePartnerButton);
        BootstrapButton TwoPartnerButton = findViewById(R.id.TwoPartnersButton);

        Button GenerateQRButton = findViewById(R.id.ClimbGenerateQRButton);

        defaultButtonState(SetupButton);
        defaultButtonState(SandstormButton);
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

        GenerateQRButton.setBackgroundColor(getResources().getColor(R.color.light));
        GenerateQRButton.setTextColor(getResources().getColor(R.color.grey));

        Serializable setupData = getIntent().getSerializableExtra("setupHashMap");

        setupHashMap = (HashMap<String, String>) setupData;

        Serializable scoreData = getIntent().getSerializableExtra("scoreHashMap");
        scoreHashMap = (HashMap<String, String>) scoreData;
    }

    private void defaultButtonState (BootstrapButton button) {
        button.setBackgroundColor(getResources().getColor(R.color.light));
        button.setTextColor(getResources().getColor(R.color.grey));
    }
    public void selectedButtonColors(BootstrapButton button) {
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
        selectedButtonColors(OnHABButton);
        defaultButtonState(OffHABButton);
        Level1Button.setEnabled(true);
        Level2Button.setEnabled(true);
        Level3Button.setEnabled(true);
        OnHAB = 1;
    }
    public void OffHABClick (View view) {
        selectedButtonColors(OffHABButton);
        defaultButtonState(OnHABButton);
        Level1Button.setEnabled(true);
        Level2Button.setEnabled(true);
        Level3Button.setEnabled(true);
        OnHAB = 0;
    }

    public void Level1Click (View view) {
        selectedButtonColors(Level1Button);
        defaultButtonState(Level2Button);
        defaultButtonState(Level3Button);

        OnYourOwnButton.setEnabled(true);
        WithHelpButton.setEnabled(true);

        level = 1;
    }

    public void Level2Click (View view) {
        defaultButtonState(Level1Button);
        selectedButtonColors(Level2Button);
        defaultButtonState(Level3Button);

        GenerateQRButton.setEnabled(true);

        level = 2;
    }

    public void Level3Click (View view) {
        defaultButtonState(Level1Button);
        defaultButtonState(Level2Button);
        selectedButtonColors(Level3Button);

        GenerateQRButton.setEnabled(true);

        level = 3;
    }

    public void OnTheirOwnClick (View view) {
        selectedButtonColors(OnYourOwnButton);
        defaultButtonState(WithHelpButton);

        HasLiftedButton.setEnabled(true);
        HasNotLiftedButton.setEnabled(true);

        OnTheirOwn = 1;
    }

    public void WithHelpClick (View view) {
        selectedButtonColors(OnYourOwnButton);
        defaultButtonState(WithHelpButton);

        GenerateQRButton.setEnabled(true);

        OnTheirOwn = 0;
    }

    public void HasLiftedClick (View view) {
        selectedButtonColors(HasLiftedButton);
        defaultButtonState(HasNotLiftedButton);

        OnePartnerButton.setEnabled(true);
        TwoPartnerButton.setEnabled(true);

        HasLifted = 1;
    }

    public void HasNotLiftedClick (View view) {
        defaultButtonState(HasLiftedButton);
        selectedButtonColors(HasNotLiftedButton);

        OnePartnerButton.setEnabled(true);
        TwoPartnerButton.setEnabled(true);

        HasLifted = 0;
    }

    public void OnePartnerClick (View view) {
        HowManyPartners = 1;
        selectedButtonColors(OnePartnerButton);
        defaultButtonState(TwoPartnerButton);

        GenerateQRButton.setEnabled(true);
    }

    public void TwoPartnerClick (View view) {
        HowManyPartners = 2;
        defaultButtonState(OnePartnerButton);
        selectedButtonColors(TwoPartnerButton);

        GenerateQRButton.setEnabled(true);
    }

    public void GenerateQRClick (View view) {
        String key;
        StringBuilder QRString = new StringBuilder();
            try {
                Object keyset[] = setupHashMap.keySet().toArray();
                for (int i = 0; i < keyset.length; i++) {
                    key = "" + keyset[i];
                    QRString.append(key).append(",").append(setupHashMap.get(key)).append(",");
                }
                    Object keySet[] = scoreHashMap.keySet().toArray();
                for (int i = 0; i < keySet.length; i++) {
                    key = "" + keySet[i];
                    QRString.append(key).append(",").append(scoreHashMap.get(key)).append(",");
                }
                QRValue = QRString.toString();
                Bitmap bitmap = TextToImageEncode(QRValue);
                final AlertDialog.Builder qrDialog = new AlertDialog.Builder(Climb.this);
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
