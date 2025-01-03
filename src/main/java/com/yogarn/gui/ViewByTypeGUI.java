package com.yogarn.gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import com.yogarn.model.Products;
import com.yogarn.service.ProductsService;

public class ViewByTypeGUI extends JFrame {
    public ViewByTypeGUI(JFrame parentFrame) {
        setTitle("View Products by Type");
        setSize(600, 500);
        setLayout(new BorderLayout());

        JLabel header = new JLabel("View Products by Type", JLabel.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 18));
        add(header, BorderLayout.NORTH);

        String[] columnNames = {"SKU", "Product Type", "Price"};

        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        JTable productTable = new JTable(tableModel);
        productTable.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(productTable);
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
                tableModel.setRowCount(0);

                if (products.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "No products found for type: " + type, "No Results", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    for (Products product : products) {
                        Object[] row = {
                            product.getSku(),
                            product.getProductType(),
                            String.format("%.2f", product.getPrice())
                        };
                        tableModel.addRow(row);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Masukkin typenya dulu kocak emang aku bisa ramal po?", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        backButton.addActionListener(e -> {
            parentFrame.setVisible(true); 
            dispose();
        });

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}
