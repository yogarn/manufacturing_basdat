package com.yogarn.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.yogarn.config.DatabaseConfig;
import com.yogarn.model.InspectionDefectRate;

public class InspectionDefectRateService {
    public ArrayList<InspectionDefectRate> getInspectionDefectRate() {
        String sql = "SELECT inspection_results, AVG(defect_rates) AS average_defect_rates, COUNT(*) AS total_products " +
                        "FROM inspections " +
                        "GROUP BY inspection_results " +
                        "ORDER BY AVG(defect_rates) DESC";

        ArrayList<InspectionDefectRate> inspectionDefectRates = new ArrayList<InspectionDefectRate>();

        try (Connection conn = DatabaseConfig.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                InspectionDefectRate inspectionDefectRate = new InspectionDefectRate();
                inspectionDefectRate.setInspectionResult(rs.getString("inspection_results"));
                inspectionDefectRate.setAverageDefectRate(rs.getDouble("average_defect_rates"));
                inspectionDefectRate.setTotalProduct(rs.getInt("total_products"));
                inspectionDefectRates.add(inspectionDefectRate);
            }

            return inspectionDefectRates;
        } catch (SQLException e) {
            System.err.println("Database operation error: " + e.getMessage());
        }
        
        return inspectionDefectRates;
    }
}
