package com.yogarn.gui;

import javax.swing.*;
import java.awt.*;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Dashboard::new);
        //  SwingUtilities.invokeLater(Dashboard::new);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Sistem Manajemen Produk");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        frame.setLayout(new BorderLayout());

        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.WHITE);
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        backgroundPanel.setLayout(new BorderLayout());
        frame.add(backgroundPanel);

        JLabel header = new JLabel("Menu Manajemen Produk", JLabel.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(header, BorderLayout.NORTH);

        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JButton viewAllButton = new JButton("Lihat Semua Produk");
        JButton viewByTypeButton = new JButton("Lihat Berdasarkan Tipe");
        JButton viewBySKUButton = new JButton("Lihat Berdasarkan SKU");
        JButton updateButton = new JButton("Update Produk");
        JButton deleteButton = new JButton("Hapus Produk");
        JButton addButton = new JButton("Tambah Produk");
        JButton exitButton = new JButton("Keluar");
        
        JButton productPriceRangeButton = new JButton("Lihat Produk Berdasarkan Rentang Harga");
        JButton fastestShippingCarriersButton = new JButton("Pengiriman Cepat");
        JButton highestProductionVolumeSupplierButton = new JButton("Pemasok dengan Volume Produksi Terbanyak");

        menuPanel.add(viewAllButton);
        menuPanel.add(viewByTypeButton);
        menuPanel.add(viewBySKUButton);
        menuPanel.add(updateButton);
        menuPanel.add(deleteButton);
        menuPanel.add(addButton);
        menuPanel.add(exitButton);
        menuPanel.add(productPriceRangeButton);
        menuPanel.add(fastestShippingCarriersButton);
        menuPanel.add(highestProductionVolumeSupplierButton);

        frame.add(menuPanel, BorderLayout.CENTER);

        viewAllButton.addActionListener(e -> new ViewAllProductsGUI(frame).setVisible(true));
        viewByTypeButton.addActionListener(e -> new ViewByTypeGUI(frame).setVisible(true));
        viewBySKUButton.addActionListener(e ->new ViewBySkuGui(frame).setVisible(true));
        updateButton.addActionListener(e -> showMessage(frame, "Update Product belum ada nanti dulu ya."));
        deleteButton.addActionListener(e -> showMessage(frame, "Delete Product belum ada nanti dulu ya."));
        addButton.addActionListener(e -> showMessage(frame, "Add Product belum ada nanti dulu ya."));

        productPriceRangeButton.addActionListener(e -> new ViewRangeGui(frame).setVisible(true));
        fastestShippingCarriersButton.addActionListener(e -> new ViewShippingGui(frame).setVisible(true));
        highestProductionVolumeSupplierButton.addActionListener(e -> new ViewHighestGui(frame).setVisible(true));
        exitButton.addActionListener(e -> System.exit(0));
        frame.setVisible(true);
    }

    private static void showMessage(JFrame frame, String message) {
        JOptionPane.showMessageDialog(frame, message, "Info", JOptionPane.INFORMATION_MESSAGE);
    }
}
