package com.yogarn.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import io.github.cdimascio.dotenv.Dotenv;

public class DatabaseConfig {
    private static Dotenv dotenv = Dotenv.load();
    private static String dbUrl = dotenv.get("DB_URL");
    private static String dbUser = dotenv.get("DB_USER");
    private static String dbPassword = dotenv.get("DB_PASSWORD");

    private DatabaseConfig() {
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }
}
