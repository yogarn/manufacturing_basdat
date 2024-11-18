package com.yogarn.gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import com.yogarn.model.Products;
import com.yogarn.service.ProductsService;

public class ViewAllProductsGUI extends JFrame {
    private DefaultTableModel tableModel;
    private JComboBox<String> sortComboBox;

    public ViewAllProductsGUI(JFrame parentFrame) {
        setTitle("Lihat Semua Produk");
        setSize(600, 500);
        setLayout(new BorderLayout());

        JLabel header = new JLabel("Semua Produk", JLabel.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 18));
        add(header, BorderLayout.NORTH);

        String[] columnNames = {"SKU", "Tipe Produk", "Harga"};
        tableModel = new DefaultTableModel(columnNames, 0);

        JTable productTable = new JTable(tableModel);
        productTable.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(productTable);
        add(scrollPane, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel(new BorderLayout());

        sortComboBox = new JComboBox<>(new String[] { "Pilih Opsi Urutkan", "Urutkan Berdasarkan SKU", "Urutkan Berdasarkan Tipe", "Urutkan Berdasarkan Harga" });
        sortComboBox.addActionListener(e -> sortData());
        controlPanel.add(sortComboBox, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton backButton = new JButton("Kembali");
        JButton addButton = new JButton("Tambah Produk");
        JButton updateButton = new JButton("Update Produk");
        JButton deleteButton = new JButton("Hapus Produk");

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(backButton);
        controlPanel.add(buttonPanel, BorderLayout.CENTER);
        
        add(controlPanel, BorderLayout.SOUTH);

        backButton.addActionListener(e -> {
            parentFrame.setVisible(true); 
            dispose();
        });

        addButton.addActionListener(e -> {
            new AddProductGui(this).setVisible(true); 
            setVisible(false);
        });

        updateButton.addActionListener(e -> {
            String sku = JOptionPane.showInputDialog(this, "Masukkan SKU produk yang mau diupdate:");
            if (sku != null) {
                new UpdateProductGui(this, sku).setVisible(true); 
                setVisible(false);
            }
        });

        deleteButton.addActionListener(e -> {
            new DeleteProductGui(this).setVisible(true);
            setVisible(false);
        });

        ambildata(null);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public void ambildata(String sortCriteria) {
        ProductsService productService = new ProductsService();
        ArrayList<Products> products = productService.getAllProducts();

        if (sortCriteria != null) {
            switch (sortCriteria) {
                case "Urutkan Berdasarkan SKU":
                    products.sort((p1, p2) -> p1.getSku().compareTo(p2.getSku()));
                    break;
                case "Urutkan Berdasarkan Tipe":
                    products.sort((p1, p2) -> p1.getProductType().compareTo(p2.getProductType()));
                    break;
                case "Urutkan Berdasarkan Harga":
                    products.sort((p1, p2) -> Double.compare(p1.getPrice(), p2.getPrice()));
                    break;
            }
        }

        tableModel.setRowCount(0);

        for (Products product : products) {
            Object[] row = {
                product.getSku(),
                product.getProductType(),
                String.format("%.2f", product.getPrice())
            };
            tableModel.addRow(row);
        }
    }

    private void sortData() {
        String selectedSortOption = (String) sortComboBox.getSelectedItem();
        if (selectedSortOption != null && !selectedSortOption.equals("Pilih Opsi Urutkan")) {
            ambildata(selectedSortOption);
        }
    }
}
