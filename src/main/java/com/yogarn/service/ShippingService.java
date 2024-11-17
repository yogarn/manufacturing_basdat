package com.yogarn.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.yogarn.config.DatabaseConfig;
import com.yogarn.model.AverageShippingCarriers;

public class ShippingService {
    public ArrayList<AverageShippingCarriers> getFastestCarriers() {
        String sql = "SELECT s.shipping_carriers, AVG(s.shipping_costs) AS average_costs, AVG(s.shipping_times) AS average_shipping_times " +
                        "FROM shipping s " + 
                        "GROUP BY s.shipping_carriers " +
                        "ORDER BY average_shipping_times ASC";

        ArrayList<AverageShippingCarriers> averageShippingCarriers = new ArrayList<>();

        try (Connection conn = DatabaseConfig.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                AverageShippingCarriers averageShippingCarrier = new AverageShippingCarriers();
                averageShippingCarrier.setCarrier(rs.getString("shipping_carriers"));
                averageShippingCarrier.setAverageShippingCost(rs.getDouble("average_costs"));
                averageShippingCarrier.setAverageShippingTime(rs.getDouble("average_shipping_times"));
                averageShippingCarriers.add(averageShippingCarrier);
            }

        } catch (SQLException e) {
            System.err.println("Database operation error: " + e.getMessage());
        }

        return averageShippingCarriers;
    }
}
