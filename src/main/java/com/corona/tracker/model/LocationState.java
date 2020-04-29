package com.corona.tracker.model;

public class LocationState {
    private String state;
    private String country;
    private int currentTotalCases;
    private int yesterdayTotalCases;
    private int difference;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getCurrentTotalCases() {
        return currentTotalCases;
    }

    public void setCurrentTotalCases(int currentTotalCases) {
        this.currentTotalCases = currentTotalCases;
    }

    public int getYesterdayTotalCases() {
        return yesterdayTotalCases;
    }

    public void setYesterdayTotalCases(int yesterdayTotalCases) {
        this.yesterdayTotalCases = yesterdayTotalCases;
    }

    public int getDifference() {
        return difference;
    }

    public void setDifference(int difference) {
        this.difference = difference;
    }

    @Override
    public String toString() {
        return "LocationState{" +
                "state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", currentTotalCases=" + currentTotalCases +
                ", yesterdayTotalCases=" + yesterdayTotalCases +
                ", difference=" + difference +
                '}';
    }
}

