package com.yogarn.gui;

import java.awt.GridLayout;

import javax.swing.*;
import com.yogarn.service.ProductsService;

public class DeleteProductGui extends JFrame {
    private JTextField skuField;

    public DeleteProductGui(JFrame parentFrame) {
        setTitle("Delete Product");
        setSize(400, 200);
        setLayout(new GridLayout(3, 2));

        add(new JLabel("Enter SKU of Product to Delete:"));
        skuField = new JTextField();
        add(skuField);

        JButton deleteButton = new JButton("Delete");
        add(deleteButton);
        
        JButton cancelButton = new JButton("Cancel");
        add(cancelButton);

        deleteButton.addActionListener(e -> {
            String sku = skuField.getText().trim();

            if (sku.isEmpty()) {
                JOptionPane.showMessageDialog(this, "SKU cannot be empty.");
                return;
            }

            ProductsService service = new ProductsService();
            int result = service.deleteProduct(sku);

            if (result > 0) {
                JOptionPane.showMessageDialog(this, "Udah dihapus mbek sql ya.");
                parentFrame.setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "gak ada datanya coy, aku gak nemu");
            }
        });

        cancelButton.addActionListener(e -> {
            parentFrame.setVisible(true);
            dispose();
        });

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}
