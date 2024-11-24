package com.yogarn.gui;

import javax.swing.*;
import java.awt.*;
import com.yogarn.service.ProductsService;

public class DeleteProductGui extends JFrame {
    private JTextField skuField;

    public DeleteProductGui(JFrame parentFrame) {
        setTitle("Hapus Produk");
        setSize(400, 200);
        setLayout(new GridLayout(3, 2));

        add(new JLabel("Masukkan SKU Produk yang Mau Dihapus:"));
        skuField = new JTextField();
        add(skuField);

        JButton deleteButton = new JButton("Hapus");
        add(deleteButton);
        
        JButton cancelButton = new JButton("Batal");
        add(cancelButton);

        deleteButton.addActionListener(e -> {
            String sku = skuField.getText().trim();

            if (sku.isEmpty()) {
                JOptionPane.showMessageDialog(this, "SKU gak boleh kosong.");
                return;
            }

            ProductsService service = new ProductsService();
            int result = service.deleteProduct(sku);

            if (result > 0) {
                JOptionPane.showMessageDialog(this, "Data berhasil dihapus.");
                ((Dashboard) parentFrame).ambilData("Urutkan Berdasarkan SKU");
                parentFrame.setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Data tidak ditemukan");
            }
        });

        cancelButton.addActionListener(e -> {
            parentFrame.setVisible(true);
            dispose();
        });

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}
