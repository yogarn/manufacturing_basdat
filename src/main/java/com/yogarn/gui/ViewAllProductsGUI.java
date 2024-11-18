package com.yogarn.gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import com.yogarn.model.Products;
import com.yogarn.service.ProductsService;

public class ViewAllProductsGUI extends JFrame {
    public ViewAllProductsGUI(JFrame parentFrame) {
        setTitle("View All Products");
        setSize(600, 500);
        setLayout(new BorderLayout());

        JLabel header = new JLabel("All Products", JLabel.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 18));
        add(header, BorderLayout.NORTH);

        String[] columnNames = {"SKU", "Product Type", "Price"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        ProductsService productService = new ProductsService();
        ArrayList<Products> products = productService.getAllProducts();

        for (Products product : products) {
            Object[] row = {
                product.getSku(),
                product.getProductType(),
                String.format("%.2f", product.getPrice())
            };
            tableModel.addRow(row);
        }

        JTable productTable = new JTable(tableModel);
        productTable.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(productTable);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton backButton = new JButton("Back");
        JButton addButton = new JButton("Add Product");
        JButton updateButton = new JButton("Update Product");
        JButton deleteButton = new JButton("Delete Product");

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.SOUTH);

        backButton.addActionListener(e -> {
            parentFrame.setVisible(true); 
            dispose();
        });

        addButton.addActionListener(e -> {
            new AddProductGui(this).setVisible(true);
            setVisible(false);
        });

        updateButton.addActionListener(e -> {
            String sku = JOptionPane.showInputDialog(this, "Enter SKU of product to update:");
            if (sku != null) {
                new UpdateProductGui(this, sku).setVisible(true);
                setVisible(false);
            }
        });

        deleteButton.addActionListener(e -> {
            new DeleteProductGui(this).setVisible(true);
            setVisible(false);
        });

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}
