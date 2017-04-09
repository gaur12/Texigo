package com.example.user.texigo.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by USER on 09-Apr-17.
 */

public class AutoCompleteSuggest {
    @SerializedName("text")
    private String destinationName;
    @SerializedName("st")
    private String stateName;
    @SerializedName("co")
    private String countryName;
    private int xid;

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public int getXid() {
        return xid;
    }

    public void setXid(int xid) {
        this.xid = xid;
    }
}
