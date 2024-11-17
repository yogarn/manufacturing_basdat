package com.yogarn.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.yogarn.config.DatabaseConfig;
import com.yogarn.model.AverageSuppliers;

public class SuppliersService {
    public ArrayList<AverageSuppliers> getFastestSupplierLeadTime() {
        String sql = "SELECT s.supplier_name, AVG(s.lead_time) AS average_lead_time, AVG(s.manufacturing_costs) AS average_manufacturing_costs, AVG(s.manufacturing_lead_time) AS average_manufacturing_lead_time, AVG(s.production_volumes) AS average_production_volumes " +
                        "FROM suppliers s " +
                        "GROUP BY s.supplier_name " +
                        "ORDER BY average_lead_time ASC";

        ArrayList<AverageSuppliers> suppliers = new ArrayList<AverageSuppliers>();

        try (Connection conn = DatabaseConfig.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                AverageSuppliers supplier = new AverageSuppliers();
                supplier.setSupplierName(rs.getString("supplier_name"));
                supplier.setAverageLeadTime(rs.getDouble("average_lead_time"));
                supplier.setAverageManufacturingCosts(rs.getDouble("average_manufacturing_costs"));
                supplier.setAverageManufacturingLeadTime(rs.getDouble("average_manufacturing_lead_time"));
                supplier.setAverageProductionVolumes(rs.getDouble("average_production_volumes"));
                suppliers.add(supplier);
            }
        } catch (SQLException e) {
            System.err.println("Database operation error: " + e.getMessage());
        }

        return suppliers;
    }

    public ArrayList<AverageSuppliers> getHighestProductionVolumeSupplier() {
        String sql = "SELECT s.supplier_name, AVG(s.lead_time) AS average_lead_time, AVG(s.manufacturing_costs) AS average_manufacturing_costs, AVG(s.manufacturing_lead_time) AS average_manufacturing_lead_time, AVG(s.production_volumes) AS average_production_volumes "
                +
                "FROM suppliers s " +
                "GROUP BY s.supplier_name " +
                "ORDER BY average_production_volumes DESC";

        ArrayList<AverageSuppliers> suppliers = new ArrayList<AverageSuppliers>();

        try (Connection conn = DatabaseConfig.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                AverageSuppliers supplier = new AverageSuppliers();
                supplier.setSupplierName(rs.getString("supplier_name"));
                supplier.setAverageLeadTime(rs.getDouble("average_lead_time"));
                supplier.setAverageManufacturingCosts(rs.getDouble("average_manufacturing_costs"));
                supplier.setAverageManufacturingLeadTime(rs.getDouble("average_manufacturing_lead_time"));
                supplier.setAverageProductionVolumes(rs.getDouble("average_production_volumes"));
                suppliers.add(supplier);
            }
        } catch (SQLException e) {
            System.err.println("Database operation error: " + e.getMessage());
        }

        return suppliers;
    }
}
