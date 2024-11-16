package com.yogarn.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.yogarn.config.DatabaseConfig;
import com.yogarn.model.ProductsTypeSales;

public class ProductsTypeSalesService {
    public ArrayList<ProductsTypeSales> getTopProductSales() {
        String sql = "SELECT p.product_type, SUM(s.num_products_sold) AS total_sales, SUM(p.price) AS total_price, SUM(s.revenue_generated) AS total_revenue " +
                        "FROM products p " + 
                        "JOIN sales s ON s.sku = p.sku " + 
                        "GROUP BY p.product_type " + 
                        "ORDER BY total_sales DESC";

        ArrayList<ProductsTypeSales> productsTypeSales = new ArrayList<>();

        try (Connection conn = DatabaseConfig.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ProductsTypeSales productTypeSales = new ProductsTypeSales();
                productTypeSales.setProductType(rs.getString("product_type"));
                productTypeSales.setTotalSales(rs.getInt("total_sales"));
                productTypeSales.setTotalPrice(rs.getDouble("total_price"));
                productTypeSales.setTotalRevenue(rs.getDouble("total_revenue"));
                productsTypeSales.add(productTypeSales);
            }

        } catch (SQLException e) {
            System.err.println("Database operation error: " + e.getMessage());
        }

        return productsTypeSales;
    }
}
