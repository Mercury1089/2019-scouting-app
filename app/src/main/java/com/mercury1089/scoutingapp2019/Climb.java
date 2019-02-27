package com.mercury1089.scoutingapp2019;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.beardedhen.androidbootstrap.BootstrapButton;

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
    }

    private void defaultButtonState (BootstrapButton button) {
        button.setBackgroundColor(getResources().getColor(R.color.light));
        button.setTextColor(getResources().getColor(R.color.grey));
    }
    public void selectedButtonColors(BootstrapButton button) {
        button.setBackgroundColor(getResources().getColor(R.color.orange));
        button.setTextColor(getResources().getColor(R.color.light));
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
        //qr code
    }
}
