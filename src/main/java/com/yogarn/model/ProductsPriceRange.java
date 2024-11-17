package com.yogarn.model;

public class ProductsPriceRange {
    private String priceRange;
    private int totalProductsSold;
    private int totalProducts;
    private double averagePrice;

    public ProductsPriceRange() {
    }

    public ProductsPriceRange(String priceRange, int totalProductsSold, int totalProducts, double averagePrice) {
        this.priceRange = priceRange;
        this.totalProductsSold = totalProductsSold;
        this.totalProducts = totalProducts;
        this.averagePrice = averagePrice;
    }

    public String getPriceRange() {
        return priceRange;
    }

    public void setPriceRange(String priceRange) {
        this.priceRange = priceRange;
    }

    public int getTotalProductsSold() {
        return totalProductsSold;
    }

    public void setTotalProductsSold(int totalProductsSold) {
        this.totalProductsSold = totalProductsSold;
    }

    public int getTotalProducts() {
        return totalProducts;
    }

    public void setTotalProducts(int totalProducts) {
        this.totalProducts = totalProducts;
    }

    public double getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(double averagePrice) {
        this.averagePrice = averagePrice;
    }
}
