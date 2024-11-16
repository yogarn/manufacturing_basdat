package com.yogarn.gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
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

        JTextArea outputArea = new JTextArea(15, 50);
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton backButton = new JButton("Back");
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.SOUTH);

        ProductsService productService = new ProductsService();
        ArrayList<Products> products = productService.getAllProducts();
        outputArea.setText("All Products:\n");
        for (Products product : products) {
            outputArea.append("SKU: " + product.getSku() + ", Type: " + product.getProductType() + ", Price: " + product.getPrice() + "\n");
        }

        backButton.addActionListener(e -> {
            parentFrame.setVisible(true); 
            dispose();
        });
        backButton.setVisible(true);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}
