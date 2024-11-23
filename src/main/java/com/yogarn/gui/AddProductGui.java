package com.yogarn.gui;

import javax.swing.*;
import java.awt.*;
import com.yogarn.model.Products;
import com.yogarn.service.ProductsService;

public class AddProductGui extends JFrame {
    private JTextField skuField, productTypeField, priceField;

    public AddProductGui(JFrame parentFrame) {
        setTitle("Tambah Produk");
        setSize(400, 300);
        setLayout(new GridLayout(5, 2));

        add(new JLabel("SKU:"));
        skuField = new JTextField();
        add(skuField);

        add(new JLabel("Tipe Produk:"));
        productTypeField = new JTextField();
        add(productTypeField);

        add(new JLabel("Harga:"));
        priceField = new JTextField();
        add(priceField);

        JButton saveButton = new JButton("Simpan");
        add(saveButton);
        
        JButton cancelButton = new JButton("Batal");
        add(cancelButton);

        saveButton.addActionListener(e -> {
            String sku = skuField.getText();
            String productType = productTypeField.getText();
            double price = Double.parseDouble(priceField.getText());

            Products product = new Products(sku, productType, price);
            ProductsService service = new ProductsService();
            int result = service.addProduct(product);

            if (result > 0) {
                JOptionPane.showMessageDialog(this, "Wes tak simpen di ssms");
                ((Dashboard) parentFrame).ambilData("Urutkan Berdasarkan SKU");  
                parentFrame.setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "error cuy kok gak bisa ya?");
            }
        });

        cancelButton.addActionListener(e -> {
            parentFrame.setVisible(true);
            dispose();
        });

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}
