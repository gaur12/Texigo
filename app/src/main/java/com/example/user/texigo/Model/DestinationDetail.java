package com.example.user.texigo.Model;

import java.util.List;

/**
 * Created by USER on 08-Apr-17.
 */

public class DestinationDetail {
    private List<String> categoryNames;
    private String countryName;
    private String description;
    private String howToReach;
    private String keyImageUrl;
    private String whyToVisit;
    private float latitude;
    private float longitude;
    private String name;
    private String url;
    private String stateName;
    private String id;
    private String shortDescription;
    private int xid;

    public int getXid() {
        return xid;
    }

    public void setXid(int xid) {
        this.xid = xid;
    }

    public List<String> getCategoryNames() {
        return categoryNames;
    }

    public void setCategoryNames(List<String> categoryNames) {
        this.categoryNames = categoryNames;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHowToReach() {
        return howToReach;
    }

    public void setHowToReach(String howToReach) {
        this.howToReach = howToReach;
    }

    public String getKeyImageUrl() {
        return keyImageUrl;
    }

    public void setKeyImageUrl(String keyImageUrl) {
        this.keyImageUrl = keyImageUrl;
    }

    public String getWhyToVisit() {
        return whyToVisit;
    }

    public void setWhyToVisit(String whyToVisit) {
        this.whyToVisit = whyToVisit;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }
}
