package com.yogarn.model;

public class Suppliers {
    private String sku;
    private String supplierName;
    private String location;
    private int leadTime;
    private int productionVolumes;
    private int manufacturingLeadTime;
    private int manufacturingCosts;

    public Suppliers() {
    }

    public Suppliers(String sku, String supplierName, String location, int leadTime, int productionVolumes, int manufacturingLead, int manufacturingLeadTime, int manufacturingCosts) {
        this.sku = sku;
        this.supplierName = supplierName;
        this.location = location;
        this.leadTime = leadTime;
        this.productionVolumes = productionVolumes;
        this.manufacturingLeadTime = manufacturingLead;
        this.manufacturingCosts = manufacturingCosts;
    }

    public String getSku() {
        return sku;
    }
    public void setSku(String sku) {
        this.sku = sku;
    }
    public String getSupplierName() {
        return supplierName;
    }
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public int getLeadTime() {
        return leadTime;
    }
    public void setLeadTime(int leadTime) {
        this.leadTime = leadTime;
    }
    public int getProductionVolumes() {
        return productionVolumes;
    }
    public void setProductionVolumes(int productionVolumes) {
        this.productionVolumes = productionVolumes;
    }
    public int getManufacturingLeadTime() {
        return manufacturingLeadTime;
    }
    public void setManufacturingLeadTime(int manufacturingLeadTime) {
        this.manufacturingLeadTime = manufacturingLeadTime;
    }
    public int getManufacturingCosts() {
        return manufacturingCosts;
    }
    public void setManufacturingCosts(int manufacturingCosts) {
        this.manufacturingCosts = manufacturingCosts;
    }
}
