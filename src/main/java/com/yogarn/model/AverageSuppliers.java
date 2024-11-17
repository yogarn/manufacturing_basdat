package com.yogarn.model;

public class AverageSuppliers {
    private String supplierName;
    private double averageLeadTime;
    private double averageManufacturingCosts;
    private double averageManufacturingLeadTime;
    private double averageProductionVolumes;

    public AverageSuppliers() {
    }

    public AverageSuppliers(String supplierName, double averageLeadTime, double averageManufacturingCosts, double averageManufacturingLeadTime, double averageProductionVolumes) {
        this.supplierName = supplierName;
        this.averageLeadTime = averageLeadTime;
        this.averageManufacturingCosts = averageManufacturingCosts;
        this.averageManufacturingLeadTime = averageManufacturingLeadTime;
        this.averageProductionVolumes = averageProductionVolumes;
    }

    public String getSupplierName() {
        return supplierName;
    }
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }
    public double getAverageLeadTime() {
        return averageLeadTime;
    }
    public void setAverageLeadTime(double averageLeadTime) {
        this.averageLeadTime = averageLeadTime;
    }
    public double getAverageManufacturingCosts() {
        return averageManufacturingCosts;
    }
    public void setAverageManufacturingCosts(double averageManufacturingCosts) {
        this.averageManufacturingCosts = averageManufacturingCosts;
    }
    public double getAverageManufacturingLeadTime() {
        return averageManufacturingLeadTime;
    }
    public void setAverageManufacturingLeadTime(double averageManufacturingLeadTime) {
        this.averageManufacturingLeadTime = averageManufacturingLeadTime;
    }
    public double getAverageProductionVolumes() {
        return averageProductionVolumes;
    }
    public void setAverageProductionVolumes(double averageProductionVolumes) {
        this.averageProductionVolumes = averageProductionVolumes;
    }
}
