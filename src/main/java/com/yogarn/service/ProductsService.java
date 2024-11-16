package com.yogarn.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.yogarn.config.DatabaseConfig;
import com.yogarn.model.Products;

public class ProductsService {
    public ArrayList<Products> getAllProducts() {
        String sql = "SELECT * FROM products";

        ArrayList<Products> products = new ArrayList<>();

        try (Connection conn = DatabaseConfig.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Products product = new Products();
                product.setSku(rs.getString("sku"));
                product.setProductType(rs.getString("product_type"));
                product.setPrice(rs.getDouble("price"));
                products.add(product);
            }

        } catch (SQLException e) {
            System.err.println("Database operation error: " + e.getMessage());
        }

        return products;
    }

    public ArrayList<Products> getProductsByType(String productType) {
        String sql = "SELECT * FROM products WHERE product_type = ?";

        ArrayList<Products> products = new ArrayList<>();

        try (Connection conn = DatabaseConfig.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, productType);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Products product = new Products();
                product.setSku(rs.getString("sku"));
                product.setProductType(rs.getString("product_type"));
                product.setPrice(rs.getDouble("price"));
                products.add(product);
            }

        } catch (SQLException e) {
            System.err.println("Database operation error: " + e.getMessage());
        }

        return products;
    }

    public Products getProductBySku(String sku) {
        String sql = "SELECT * FROM products WHERE sku = ?";

        Products product = new Products();

        try (Connection conn = DatabaseConfig.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, sku);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                product.setSku(rs.getString("sku"));
                product.setProductType(rs.getString("product_type"));
                product.setPrice(rs.getDouble("price"));
            }

        } catch (SQLException e) {
            System.err.println("Database operation error: " + e.getMessage());
        }

        return product;
    }

    public int addProduct(Products product) {
        String sql = "INSERT INTO products (sku, product_type, price) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConfig.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, product.getSku());
            stmt.setString(2, product.getProductType());
            stmt.setDouble(3, product.getPrice());
            stmt.executeUpdate();
            return stmt.getUpdateCount();
        } catch (SQLException e) {
            System.err.println("Database operation error: " + e.getMessage());
        }

        return 0;
    }

    public int updateProduct(Products product) {
        String sql = "UPDATE products SET product_type = ?, price = ? WHERE sku = ?";

        try (Connection conn = DatabaseConfig.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, product.getProductType());
            stmt.setDouble(2, product.getPrice());
            stmt.setString(3, product.getSku());
            stmt.executeUpdate();

            return stmt.getUpdateCount();
        } catch (SQLException e) {
            System.err.println("Database operation error: " + e.getMessage());
        }

        return 0;
    }

    public int deleteProduct(String sku) {
        String sql = "DELETE FROM products WHERE sku = ?";

        try (Connection conn = DatabaseConfig.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, sku);
            stmt.executeUpdate();
            return stmt.getUpdateCount();
        } catch (SQLException e) {
            System.err.println("Database operation error: " + e.getMessage());
        }

        return 0;
    }
}
