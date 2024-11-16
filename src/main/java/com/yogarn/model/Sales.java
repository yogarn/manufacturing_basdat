package com.yogarn.model;

public class Sales {
    private String sku;
    private int availability;
    private int numProductsSold;
    private double revenueGenerated;

    public Sales() {
    }

    public Sales(String sku, int availability, int numProductsSold, double revenueGenerated) {
        this.sku = sku;
        this.availability = availability;
        this.numProductsSold = numProductsSold;
        this.revenueGenerated = revenueGenerated;
    }

    public String getSku() {
        return sku;
    }
    public void setSku(String sku) {
        this.sku = sku;
    }
    public int getAvailability() {
        return availability;
    }
    public void setAvailability(int availability) {
        this.availability = availability;
    }
    public int getNumProductsSold() {
        return numProductsSold;
    }
    public void setNumProductsSold(int numProductsSold) {
        this.numProductsSold = numProductsSold;
    }
    public double getRevenueGenerated() {
        return revenueGenerated;
    }
    public void setRevenueGenerated(double revenueGenerated) {
        this.revenueGenerated = revenueGenerated;
    }
}
