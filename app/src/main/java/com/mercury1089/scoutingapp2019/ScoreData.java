package com.mercury1089.scoutingapp2019;

public class ScoreData {
    private String mode;
    private String location;
    private String panelOrCargo;
    private String side;
    private String nearOrFar;
    private String tier;
    private int score;

    //constructor
    public ScoreData() {
        mode = "";
        location = "";
        panelOrCargo = "";
        side = "";
        nearOrFar = "";
        tier = "";
        score = 0;
    }

    //setters
    public void setMode(String mode) { this.mode = mode; }
    public void setLocation(String location) { this.location = location; }
    public void setPanelOrCargo(String panelOrCargo) { this.panelOrCargo = panelOrCargo; }
    public void setSide(String side) { this.side = side; }
    public void setNearOrFar(String nearOrFar) { this.nearOrFar = nearOrFar; }
    public void setTier(String tier) { this.tier = tier; }
    public void setScore(int score) { this.score = score; }
    public void setAll (String mode, String location, String panelOrCargo, String side, String nearOrFar, String tier, int score){
        this.mode = mode;
        this.location = location;
        this.panelOrCargo = panelOrCargo;
        this.side = side;
        this.nearOrFar = nearOrFar;
        this.tier = tier;
        this.score = score;
    }

    //getters
    public String getMode() { return mode; }
    public String getLocation() { return location; }
    public String getPanelOrCargo() { return panelOrCargo; }
    public String getSide() { return side; }
    public String getNearOrFar() { return nearOrFar; }
    public String getTier() { return tier; }
    public int getScore() { return score; }
    public String getAll() {
        return mode + "," + location + "," + panelOrCargo + "," + side + "," + nearOrFar
                + "," + tier + "," + score;
    }
}
