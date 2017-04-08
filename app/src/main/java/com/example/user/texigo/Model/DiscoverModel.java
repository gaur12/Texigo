package com.example.user.texigo.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by USER on 08-Apr-17.
 */

public class DiscoverModel {
    List<FlightModel> flight;
    @SerializedName("budget_flight")
    List<FlightModel> budgetFlight;

    public List<FlightModel> getFlight() {
        return flight;
    }

    public void setFlight(List<FlightModel> flight) {
        this.flight = flight;
    }

    public List<FlightModel> getBudgetFlight() {
        return budgetFlight;
    }

    public void setBudgetFlight(List<FlightModel> budgetFlight) {
        this.budgetFlight = budgetFlight;
    }
}
