package com.example.meteo.entity;

import java.util.Date;

public class StationDetails {
    private Date firstMeasurement;
    private Double avgTYmax;
    private Double avgTMmax;
    private Double avgTYmin;
    private Double avgTMmin;
    private Double avgTYavg;
    private Double avgTMavg;
    private Double sumRainfallY;
    private Double sumRainfallM;
    private Double avgHumY;
    private Double avgHumM;
    private Double avgWindSpeedY;
    private Double avgWindSpeedM;
    private Double MaxTValue;
    private Date MaxTDay;
    private Double MinTValue;
    private Date MinTDay;
    private Double MaxWindSpeedValue;
    private Date MaxWindSpeedDay;
    private Double MaxRainfallValue;
    private Date MaxRainfallDay;
    private DailyMeasurement LastMeasurement;

    public StationDetails() {
    }

    public Date getFirstMeasurement() {
        return firstMeasurement;
    }

    public void setFirstMeasurement(Date firstMeasurement) {
        this.firstMeasurement = firstMeasurement;
    }

    public Double getAvgTYmax() {
        return avgTYmax;
    }

    public void setAvgTYmax(Double avgTYmax) {
        this.avgTYmax = avgTYmax;
    }

    public Double getAvgTMmax() {
        return avgTMmax;
    }

    public void setAvgTMmax(Double avgTMmax) {
        this.avgTMmax = avgTMmax;
    }

    public Double getAvgTYmin() {
        return avgTYmin;
    }

    public void setAvgTYmin(Double avgTYmin) {
        this.avgTYmin = avgTYmin;
    }

    public Double getAvgTMmin() {
        return avgTMmin;
    }

    public void setAvgTMmin(Double avgTMmin) {
        this.avgTMmin = avgTMmin;
    }

    public Double getAvgTYavg() {
        return avgTYavg;
    }

    public void setAvgTYavg(Double avgTYavg) {
        this.avgTYavg = avgTYavg;
    }

    public Double getAvgTMavg() {
        return avgTMavg;
    }

    public void setAvgTMavg(Double avgTMavg) {
        this.avgTMavg = avgTMavg;
    }

    public Double getSumRainfallY() {
        return sumRainfallY;
    }

    public void setSumRainfallY(Double sumRainfallY) {
        this.sumRainfallY = sumRainfallY;
    }

    public Double getSumRainfallM() {
        return sumRainfallM;
    }

    public void setSumRainfallM(Double sumRainfallM) {
        this.sumRainfallM = sumRainfallM;
    }

    public Double getAvgHumY() {
        return avgHumY;
    }

    public void setAvgHumY(Double avgHumY) {
        this.avgHumY = avgHumY;
    }

    public Double getAvgHumM() {
        return avgHumM;
    }

    public void setAvgHumM(Double avgHumM) {
        this.avgHumM = avgHumM;
    }

    public Double getAvgWindSpeedY() {
        return avgWindSpeedY;
    }

    public void setAvgWindSpeedY(Double avgWindSpeedY) {
        this.avgWindSpeedY = avgWindSpeedY;
    }

    public Double getAvgWindSpeedM() {
        return avgWindSpeedM;
    }

    public void setAvgWindSpeedM(Double avgWindSpeedM) {
        this.avgWindSpeedM = avgWindSpeedM;
    }

    public Double getMaxTValue() {
        return MaxTValue;
    }

    public void setMaxTValue(Double maxTValue) {
        MaxTValue = maxTValue;
    }

    public Date getMaxTDay() {
        return MaxTDay;
    }

    public void setMaxTDay(Date maxTDay) {
        MaxTDay = maxTDay;
    }

    public Double getMinTValue() {
        return MinTValue;
    }

    public void setMinTValue(Double minTValue) {
        MinTValue = minTValue;
    }

    public Date getMinTDay() {
        return MinTDay;
    }

    public void setMinTDay(Date minTDay) {
        MinTDay = minTDay;
    }

    public Double getMaxWindSpeedValue() {
        return MaxWindSpeedValue;
    }

    public void setMaxWindSpeedValue(Double maxWindSpeedValue) {
        MaxWindSpeedValue = maxWindSpeedValue;
    }

    public Date getMaxWindSpeedDay() {
        return MaxWindSpeedDay;
    }

    public void setMaxWindSpeedDay(Date maxWindSpeedDay) {
        MaxWindSpeedDay = maxWindSpeedDay;
    }

    public Double getMaxRainfallValue() {
        return MaxRainfallValue;
    }

    public void setMaxRainfallValue(Double maxRainfallValue) {
        MaxRainfallValue = maxRainfallValue;
    }

    public Date getMaxRainfallDay() {
        return MaxRainfallDay;
    }

    public void setMaxRainfallDay(Date maxRainfallDay) {
        MaxRainfallDay = maxRainfallDay;
    }

    public DailyMeasurement getLastMeasurement() {
        return LastMeasurement;
    }

    public void setLastMeasurement(DailyMeasurement lastMeasurement) {
        LastMeasurement = lastMeasurement;
    }
}
