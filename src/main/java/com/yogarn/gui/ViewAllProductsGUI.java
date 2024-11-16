package com.yogarn.gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import com.yogarn.model.Products;
import com.yogarn.service.ProductsService;
import javax.swing.text.*;

public class ViewAllProductsGUI extends JFrame {
    public ViewAllProductsGUI(JFrame parentFrame) {
        setTitle("View All Products");
        setSize(600, 500);
        setLayout(new BorderLayout());

        JLabel header = new JLabel("All Products", JLabel.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 18));
        add(header, BorderLayout.NORTH);

        JTextPane outputArea = new JTextPane();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
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

        ProductsService productService = new ProductsService();
        ArrayList<Products> products = productService.getAllProducts();

        StyledDocument doc = outputArea.getStyledDocument();

        Style headerStyle = outputArea.addStyle("HeaderStyle", null);
        StyleConstants.setBackground(headerStyle, Color.GRAY);
        StyleConstants.setForeground(headerStyle, Color.WHITE);
        StyleConstants.setBold(headerStyle, true);

        Style normalStyle = outputArea.addStyle("NormalStyle", null);
        StyleConstants.setForeground(normalStyle, Color.BLACK);

        try {
            String headerText = String.format("%-15s | %-20s | %-15s", "SKU", "Product Type", "Price");
            doc.insertString(doc.getLength(), headerText + "\n", headerStyle);
            doc.insertString(doc.getLength(), "--------------------------------------------------------------\n", normalStyle);

            for (Products product : products) {
                String formattedProduct = String.format("%-15s | %-20s | %-15.2f", 
                                                        product.getSku(), 
                                                        product.getProductType(), 
                                                        product.getPrice());
                doc.insertString(doc.getLength(), formattedProduct + "\n", normalStyle);
            }
        } catch (BadLocationException e) {
            e.printStackTrace();
        }

        backButton.addActionListener(e -> {
            parentFrame.setVisible(true); 
            dispose();
        });

        addButton.addActionListener(e -> {
            new AddProductGui(this).setVisible(true);
            setVisible(false);
        });

        updateButton.addActionListener(e -> {
            String sku = JOptionPane.showInputDialog(this, "Mau SKU yang mana coy?:");
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
