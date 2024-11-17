package com.yogarn.model;

public class AverageShippingCarriers {
    private String carrier;
    private double averageShippingTime;
    private double averageShippingCost;

    public AverageShippingCarriers() {
    }
    
    public AverageShippingCarriers(String carrier, double averageShippingTime, double averageShippingCost) {
        this.carrier = carrier;
        this.averageShippingTime = averageShippingTime;
        this.averageShippingCost = averageShippingCost;
    }

    public String getCarrier() {
        return carrier;
    }
    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }
    public double getAverageShippingTime() {
        return averageShippingTime;
    }
    public void setAverageShippingTime(double averageShippingTime) {
        this.averageShippingTime = averageShippingTime;
    }
    public double getAverageShippingCost() {
        return averageShippingCost;
    }
    public void setAverageShippingCost(double averageShippingCost) {
        this.averageShippingCost = averageShippingCost;
    }
}
