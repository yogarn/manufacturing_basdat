package com.yogarn.gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import com.yogarn.model.Products;
import com.yogarn.service.ProductsService;
import javax.swing.text.*;

public class ViewByTypeGUI extends JFrame {
    public ViewByTypeGUI(JFrame parentFrame) {
        setTitle("View by Type");
        setSize(600, 500);
        setLayout(new BorderLayout());

        JLabel header = new JLabel("View Products by Type", JLabel.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 18));
        add(header, BorderLayout.NORTH);

        JTextPane outputArea = new JTextPane();
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
                StyledDocument doc = outputArea.getStyledDocument();

                Style headerStyle = outputArea.addStyle("HeaderStyle", null);
                StyleConstants.setBackground(headerStyle, Color.GRAY);
                StyleConstants.setForeground(headerStyle, Color.WHITE);
                StyleConstants.setBold(headerStyle, true);

                Style normalStyle = outputArea.addStyle("NormalStyle", null);
                StyleConstants.setForeground(normalStyle, Color.BLACK);

                try {
                    if (products.isEmpty()) {
                        outputArea.setText("No products found for type: " + type + "\n");
                    } else {
                        String headerText = String.format("%-10s | %-20s | %-10s", "SKU", "Product Type", "Price");
                        doc.insertString(doc.getLength(), headerText + "\n", headerStyle);
                        doc.insertString(doc.getLength(), "--------------------------------------------------------------\n", normalStyle);

                        for (Products product : products) {
                            String formattedProduct = String.format("%-10s | %-20s | %-10.2f", 
                                                                    product.getSku(), 
                                                                    product.getProductType(), 
                                                                    product.getPrice());
                            doc.insertString(doc.getLength(), formattedProduct + "\n", normalStyle);
                        }
                    }
                } catch (BadLocationException ex) {
                    ex.printStackTrace();
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
