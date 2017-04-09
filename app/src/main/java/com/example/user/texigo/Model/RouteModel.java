package com.example.user.texigo.Model;

/**
 * Created by USER on 09-Apr-17.
 */

public class RouteModel {
    private int price;
    private String timePretty;
    private String timePrettySuffix;
    private String durationPretty;
    private String modeViaString;
    private String type;
    private String firstModeTypesCss;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getTimePretty() {
        return timePretty;
    }

    public void setTimePretty(String timePretty) {
        this.timePretty = timePretty;
    }

    public String getTimePrettySuffix() {
        return timePrettySuffix;
    }

    public void setTimePrettySuffix(String timePrettySuffix) {
        this.timePrettySuffix = timePrettySuffix;
    }

    public String getDurationPretty() {
        return durationPretty;
    }

    public void setDurationPretty(String durationPretty) {
        this.durationPretty = durationPretty;
    }

    public String getModeViaString() {
        return modeViaString;
    }

    public void setModeViaString(String modeViaString) {
        this.modeViaString = modeViaString;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFirstModeTypesCss() {
        return firstModeTypesCss;
    }

    public void setFirstModeTypesCss(String firstModeTypesCss) {
        this.firstModeTypesCss = firstModeTypesCss;
    }
}
