package com.yogarn.model;

public class Shipping {
    private String sku;
    private String shippingCarriers;
    private double shippingCosts;
    private int shippingTimes;
    private String transportationModes;
    private String routes;

    public Shipping() {
    }

    public Shipping(String sku, String shippingCarriers, double shippingCosts, int shippingTimes, String transportationModes, String routes) {
        this.sku = sku;
        this.shippingCarriers = shippingCarriers;
        this.shippingCosts = shippingCosts;
        this.shippingTimes = shippingTimes;
        this.transportationModes = transportationModes;
        this.routes = routes;
    }

    public String getSku() {
        return sku;
    }
    public void setSku(String sku) {
        this.sku = sku;
    }
    public String getShippingCarriers() {
        return shippingCarriers;
    }
    public void setShippingCarriers(String shippingCarriers) {
        this.shippingCarriers = shippingCarriers;
    }
    public double getShippingCosts() {
        return shippingCosts;
    }
    public void setShippingCosts(double shippingCosts) {
        this.shippingCosts = shippingCosts;
    }
    public int getShippingTimes() {
        return shippingTimes;
    }
    public void setShippingTimes(int shippingTimes) {
        this.shippingTimes = shippingTimes;
    }
    public String getTransportationModes() {
        return transportationModes;
    }
    public void setTransportationModes(String transportationModes) {
        this.transportationModes = transportationModes;
    }
    public String getRoutes() {
        return routes;
    }
    public void setRoutes(String routes) {
        this.routes = routes;
    }
}
