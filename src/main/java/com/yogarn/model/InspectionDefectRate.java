package com.yogarn.model;

public class InspectionDefectRate {
    private String inspectionResult;
    private double averageDefectRate;
    private int totalProduct;

    public InspectionDefectRate() {
    }

    public InspectionDefectRate(String inspectionResult, double averageDefectRate, int totalProduct) {
        this.inspectionResult = inspectionResult;
        this.averageDefectRate = averageDefectRate;
        this.totalProduct = totalProduct;
    }

    public String getInspectionResult() {
        return inspectionResult;
    }

    public void setInspectionResult(String inspectionResult) {
        this.inspectionResult = inspectionResult;
    }

    public double getAverageDefectRate() {
        return averageDefectRate;
    }

    public void setAverageDefectRate(double averageDefectRate) {
        this.averageDefectRate = averageDefectRate;
    }

    public int getTotalProduct() {
        return totalProduct;
    }

    public void setTotalProduct(int totalProduct) {
        this.totalProduct = totalProduct;
    }
}
