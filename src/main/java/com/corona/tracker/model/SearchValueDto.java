package com.corona.tracker.model;

public class SearchValueDto {
    private String country;
    private int currentTotalCases;
    private int difference;

    public SearchValueDto() {
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

    public int getDifference() {
        return difference;
    }

    public void setDifference(int difference) {
        this.difference = difference;
    }

    @Override
    public String toString() {
        return "SearchValueDto{" +
                "country='" + country + '\'' +
                ", currentTotalCases=" + currentTotalCases +
                ", difference=" + difference +
                '}';
    }
}
