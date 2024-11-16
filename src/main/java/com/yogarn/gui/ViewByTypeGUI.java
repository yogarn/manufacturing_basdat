package com.yogarn.gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import com.yogarn.model.Products;
import com.yogarn.service.ProductsService;

public class ViewByTypeGUI extends JFrame {
    public ViewByTypeGUI(JFrame parentFrame) {
        setTitle("View by Type");
        setSize(600, 500);
        setLayout(new BorderLayout());

        JLabel header = new JLabel("View Products by Type", JLabel.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 18));
        add(header, BorderLayout.NORTH);

        JTextArea outputArea = new JTextArea(15, 50);
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        add(scrollPane, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JTextField typeField = new JTextField(20);
        JButton searchButton = new JButton("Search");
        inputPanel.add(new JLabel("Type:"));
        inputPanel.add(typeField);
        inputPanel.add(searchButton);
        add(inputPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton backButton = new JButton("Back");
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.SOUTH);

        searchButton.addActionListener(e -> {
            String type = typeField.getText().trim();
            if (!type.isEmpty()) {
                ProductsService productService = new ProductsService();
                ArrayList<Products> products = productService.getProductsByType(type);
                if (products.isEmpty()) {
                    outputArea.setText("No products found for type: " + type + "\n");
                } else {
                    outputArea.setText("Products of type " + type + ":\n");
                    for (Products product : products) {
                        outputArea.append("SKU: " + product.getSku() + ", Type: " + product.getProductType() + ", Price: " + product.getPrice() + "\n");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please enter a product type.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Back button action
        backButton.addActionListener(e -> {
            parentFrame.setVisible(true); // Show main menu
            dispose(); // Close this window
        });

        backButton.setVisible(true);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}
