package com.yogarn.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.yogarn.config.DatabaseConfig;
import com.yogarn.model.User;

public class UserService {
    public User fetchUserById(int userId) {
        String sql = "SELECT * FROM Users WHERE id = ?";

        User user = new User();

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
            }

        } catch (SQLException e) {
            System.err.println("Database operation error: " + e.getMessage());
        }
        
        return user;
    }
}
