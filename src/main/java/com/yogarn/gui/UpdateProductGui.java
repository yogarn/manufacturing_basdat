package com.yogarn.gui;

import java.awt.GridLayout;

import javax.swing.*;
import com.yogarn.model.Products;
import com.yogarn.service.ProductsService;

public class UpdateProductGui extends JFrame {
    private JTextField productTypeField, priceField;
    private String sku;

    public UpdateProductGui(JFrame parentFrame, String sku) {
        this.sku = sku;
        setTitle("Update Product");
        setSize(400, 300);
        setLayout(new GridLayout(4, 2));

        ProductsService service = new ProductsService();
        Products product = service.getProductBySku(sku);

        add(new JLabel("Product Type:"));
        productTypeField = new JTextField(product.getProductType());
        add(productTypeField);

        add(new JLabel("Price:"));
        priceField = new JTextField(String.valueOf(product.getPrice()));
        add(priceField);

        JButton saveButton = new JButton("Save");
        add(saveButton);
        
        JButton cancelButton = new JButton("Cancel");
        add(cancelButton);

        saveButton.addActionListener(e -> {
            String productType = productTypeField.getText();
            double price = Double.parseDouble(priceField.getText());

            product.setProductType(productType);
            product.setPrice(price);

            int result = service.updateProduct(product);

            if (result > 0) {
                JOptionPane.showMessageDialog(this, "Product berhasil di update cuyyy");
                parentFrame.setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "error ege yang bener");
            }
        });

        cancelButton.addActionListener(e -> {
            parentFrame.setVisible(true);
            dispose();
        });

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}
