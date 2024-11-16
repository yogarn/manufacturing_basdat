package com.yogarn.model;

public class Customers {
    private String sku;
    private String customerDemographics;

    public Customers() {
    }

    public Customers(String sku, String customerDemographics) {
        this.sku = sku;
        this.customerDemographics = customerDemographics;
    }

    public String getSku() {
        return sku;
    }
    public void setSku(String sku) {
        this.sku = sku;
    }
    public String getCustomerDemographics() {
        return customerDemographics;
    }
    public void setCustomerDemographics(String customerDemographics) {
        this.customerDemographics = customerDemographics;
    }
}
