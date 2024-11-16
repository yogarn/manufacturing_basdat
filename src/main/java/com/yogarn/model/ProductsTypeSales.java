package com.yogarn.model;

public class ProductsTypeSales {
    private String productType;
    private double totalPrice;
    private int totalSales;
    private double totalRevenue;

    public ProductsTypeSales() {
    }

    public ProductsTypeSales(String productType, double totalPrice, int totalSales, double totalRevenue) {
        this.productType = productType;
        this.totalPrice = totalPrice;
        this.totalSales = totalSales;
        this.totalRevenue = totalRevenue;
    }

    public String getProductType() {
        return productType;
    }
    public void setProductType(String productType) {
        this.productType = productType;
    }
    public double getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
    public int getTotalSales() {
        return totalSales;
    }
    public void setTotalSales(int totalSales) {
        this.totalSales = totalSales;
    }
    public double getTotalRevenue() {
        return totalRevenue;
    }
    public void setTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
}
