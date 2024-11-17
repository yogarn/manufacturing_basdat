package com.yogarn.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.yogarn.config.DatabaseConfig;
import com.yogarn.model.ProductsPriceRange;

public class ProductsPriceRangeService {
    public ArrayList<ProductsPriceRange> getProductsPriceRange() {
        String sql = "SELECT " +
                            "CASE " + 
                                "WHEN price <= 10 THEN 'Under $10' " +
                                "WHEN price > 10 AND price <= 20 THEN '$10 - $20' " +
                                "WHEN price > 20 AND price <= 30 THEN '$20 - $30' " +
                                "WHEN price > 30 AND price <= 40 THEN '$30 - $40' " +
                                "WHEN price > 40 AND price <= 50 THEN '$40 - $50' " +
                                "ELSE 'Over $50' " +
                            "END AS price_range, " +
                            "SUM(s.num_products_sold) AS total_products_sold, " +
                            "COUNT(p.sku) AS total_products, " +
                            "AVG(price) AS average_price " +
                        "FROM products p " +
                        "JOIN sales s ON p.sku = s.sku " +
                        "GROUP BY " +
                            "CASE " + 
                                "WHEN price <= 10 THEN 'Under $10' " +
                                "WHEN price > 10 AND price <= 20 THEN '$10 - $20' " +
                                "WHEN price > 20 AND price <= 30 THEN '$20 - $30' " +
                                "WHEN price > 30 AND price <= 40 THEN '$30 - $40' " +
                                "WHEN price > 40 AND price <= 50 THEN '$40 - $50' " +
                                "ELSE 'Over $50' " +
                            "END " +
                        "ORDER BY price_range ASC";
        
        ArrayList<ProductsPriceRange> productsPriceRanges = new ArrayList<ProductsPriceRange>();

        try (Connection conn = DatabaseConfig.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ProductsPriceRange productsPriceRange = new ProductsPriceRange();
                productsPriceRange.setPriceRange(rs.getString("price_range"));
                productsPriceRange.setTotalProductsSold(rs.getInt("total_products_sold"));
                productsPriceRange.setTotalProducts(rs.getInt("total_products"));
                productsPriceRange.setAveragePrice(rs.getDouble("average_price"));
                productsPriceRanges.add(productsPriceRange);
            }
        } catch (SQLException e) {
            System.err.println("Database operation error: " + e.getMessage());
        }

        return productsPriceRanges;
    }
}
