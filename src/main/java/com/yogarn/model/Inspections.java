package com.yogarn.model;

public class Inspections {
    private String sku;
    private String inspectionResults;
    private double defectRates;

    public Inspections() {
    }

    public Inspections(String sku, String inspectionResults, double defectRates) {
        this.sku = sku;
        this.inspectionResults = inspectionResults;
        this.defectRates = defectRates;
    }

    public String getSku() {
        return sku;
    }
    public void setSku(String sku) {
        this.sku = sku;
    }
    public String getInspectionResults() {
        return inspectionResults;
    }
    public void setInspectionResults(String inspectionResults) {
        this.inspectionResults = inspectionResults;
    }
    public double getDefectRates() {
        return defectRates;
    }
    public void setDefectRates(double defectRates) {
        this.defectRates = defectRates;
    }

}
