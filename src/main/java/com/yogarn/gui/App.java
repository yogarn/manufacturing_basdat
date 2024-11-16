package com.yogarn.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(App::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Product Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        frame.setLayout(new BorderLayout());

        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.WHITE);
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        backgroundPanel.setLayout(new BorderLayout());
        frame.add(backgroundPanel);

        JLabel header = new JLabel("Product Management Menu", JLabel.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(header, BorderLayout.NORTH);

        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JButton viewAllButton = new JButton("View All Products");
        JButton viewByTypeButton = new JButton("View by Type");
        JButton viewBySKUButton = new JButton("View by SKU");
        JButton updateButton = new JButton("Update Product");
        JButton deleteButton = new JButton("Delete Product");
        JButton addButton = new JButton("Add Product");
        JButton exitButton = new JButton("Exit");

        menuPanel.add(viewAllButton);
        menuPanel.add(viewByTypeButton);
        menuPanel.add(viewBySKUButton);
        menuPanel.add(updateButton);
        menuPanel.add(deleteButton);
        menuPanel.add(addButton);
        menuPanel.add(exitButton);

        frame.add(menuPanel, BorderLayout.CENTER);

        viewAllButton.addActionListener(e -> new ViewAllProductsGUI(frame).setVisible(true));
        viewByTypeButton.addActionListener(e -> new ViewByTypeGUI(frame).setVisible(true));
        viewBySKUButton.addActionListener(e -> showMessage(frame, "View by SKU belum ada nanti dulu ya."));
        updateButton.addActionListener(e -> showMessage(frame, "Update Product belum ada nanti dulu ya."));
        deleteButton.addActionListener(e -> showMessage(frame, "Delete Product belum ada nanti dulu ya."));
        addButton.addActionListener(e -> showMessage(frame, "Add Product belum ada nanti dulu ya."));
        exitButton.addActionListener(e -> System.exit(0));

        frame.setVisible(true);
    }

    private static void showMessage(JFrame frame, String message) {
        JOptionPane.showMessageDialog(frame, message, "Info", JOptionPane.INFORMATION_MESSAGE);
    }
}
