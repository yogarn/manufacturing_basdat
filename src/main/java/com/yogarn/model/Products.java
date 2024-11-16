package com.yogarn.model;

public class Products {
    private String sku;
    private String productType;
    private double price;

    public Products() {
    }

    public Products(String sku, String productType, double price) {
        this.sku = sku;
        this.productType = productType;
        this.price = price;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
