package com.example.user.texigo.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by USER on 08-Apr-17.
 */

public class FlightModel implements Parcelable {
    private String image;
    private String name;
    private String cityName;
    private String stateName;
    private int price;
    private String currency;
    private String cityId;
    List<String> destinationCategories;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public List<String> getDestinationCategories() {
        return destinationCategories;
    }

    public void setDestinationCategories(List<String> destinationCategories) {
        this.destinationCategories = destinationCategories;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.image);
        dest.writeString(this.name);
        dest.writeString(this.cityName);
        dest.writeString(this.stateName);
        dest.writeInt(this.price);
        dest.writeString(this.currency);
        dest.writeString(this.cityId);
        dest.writeStringList(this.destinationCategories);
    }

    public FlightModel() {
    }

    protected FlightModel(Parcel in) {
        this.image = in.readString();
        this.name = in.readString();
        this.cityName = in.readString();
        this.stateName = in.readString();
        this.price = in.readInt();
        this.currency = in.readString();
        this.cityId = in.readString();
        this.destinationCategories = in.createStringArrayList();
    }

    public static final Parcelable.Creator<FlightModel> CREATOR = new Parcelable.Creator<FlightModel>() {
        @Override
        public FlightModel createFromParcel(Parcel source) {
            return new FlightModel(source);
        }

        @Override
        public FlightModel[] newArray(int size) {
            return new FlightModel[size];
        }
    };
}
