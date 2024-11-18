package com.yogarn.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import com.yogarn.service.ProductsService;
import com.yogarn.model.Products;

public class ViewBySkuGui extends JFrame {
    public ViewBySkuGui(JFrame parentFrame) {
        setTitle("View Product by SKU");
        setSize(600, 500);
        setLayout(new BorderLayout());

        JLabel header = new JLabel("View Product by SKU", JLabel.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 18));
        add(header, BorderLayout.NORTH);

        String[] columnNames = {"SKU", "Product Type", "Price"};

        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        JTable productTable = new JTable(tableModel);
        productTable.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(productTable);
        add(scrollPane, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JTextField skuField = new JTextField(20);
        JButton searchButton = new JButton("Search");
        inputPanel.add(new JLabel("SKU:"));
        inputPanel.add(skuField);
        inputPanel.add(searchButton);
        add(inputPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton backButton = new JButton("Back");
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.SOUTH);

        searchButton.addActionListener(e -> {
            String sku = skuField.getText().trim();
            if (!sku.isEmpty()) {
                ProductsService productService = new ProductsService();
                Products product = productService.getProductBySku(sku);

                tableModel.setRowCount(0);

                if (product.getSku() == null) {
                    JOptionPane.showMessageDialog(this, "No product found for SKU: " + sku, "No Results", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    Object[] row = {
                        product.getSku(),
                        product.getProductType(),
                        String.format("%.2f", product.getPrice())
                    };
                    tableModel.addRow(row);
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
