package com.yogarn.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import com.yogarn.model.Products;
import com.yogarn.model.AverageSuppliers;
import com.yogarn.service.ProductsService;
import com.yogarn.service.SuppliersService;
import com.yogarn.service.ShippingService;
import com.yogarn.service.ProductsPriceRangeService;
import com.yogarn.model.AverageShippingCarriers;
import com.yogarn.model.ProductsPriceRange;

public class Dashboard extends JFrame {
    private DefaultTableModel tableModel;
    private JComboBox<String> sortComboBox;

    public Dashboard() {
        setTitle("Dashboard - Sistem Manajemen Produk");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setApplicationIcon("D:\\Kelas Basis Data\\Project\\manufacturing_basdat\\src\\main\\java\\com\\yogarn\\gui\\image.png");
        setLayout(new BorderLayout());

        JLabel header = new JLabel("Dashboard Manajemen Produk", JLabel.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 20));
        add(header, BorderLayout.NORTH);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Semua Produk", createProductsTablePanel());
        tabbedPane.addTab("Berdasarkan Tipe Produk", createProductTypesPanel());
        tabbedPane.addTab("Berdasarkan SKU", createViewBySkuPanel());
        tabbedPane.addTab("Supplier Tertinggi", createHighestSuppliersPanel());
        tabbedPane.addTab("Rentang Harga", createPriceRangePanel());
        tabbedPane.addTab("Pengiriman Cepat", createShippingPanel());

        tabbedPane.addChangeListener(e -> {
            int selectedTabIndex = tabbedPane.getSelectedIndex();
            switch (selectedTabIndex) {
                case 0 -> getContentPane().setBackground(Color.BLUE);
                case 1 -> getContentPane().setBackground(Color.PINK);
                case 2 -> getContentPane().setBackground(Color.CYAN);
                case 3 -> getContentPane().setBackground(Color.YELLOW);
                case 4 -> getContentPane().setBackground(Color.ORANGE);
                case 5 -> getContentPane().setBackground(Color.GREEN);
                default -> getContentPane().setBackground(Color.LIGHT_GRAY);
            }
        });

        add(tabbedPane, BorderLayout.CENTER);
        setVisible(true);
    }

    private void setApplicationIcon(String iconPath) {
        try {
            ImageIcon icon = new ImageIcon(iconPath);
            Image img = icon.getImage();
            setIconImage(img);
        } catch (Exception e) {
            System.err.println("Gagal memuat ikon aplikasi: " + e.getMessage());
        }
    }


    private JPanel createProductsTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.LIGHT_GRAY);

        JLabel label = new JLabel("Data Semua Produk", JLabel.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(label, BorderLayout.NORTH);

        String[] columnNames = {"SKU", "Tipe Produk", "Harga"};
        tableModel = new DefaultTableModel(columnNames, 0);

        JTable productTable = new JTable(tableModel);
        productTable.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(productTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel(new BorderLayout());
        sortComboBox = new JComboBox<>(new String[]{
            "Pilih Opsi Urutkan",
            "Urutkan Berdasarkan SKU",
            "Urutkan Berdasarkan Tipe",
            "Urutkan Berdasarkan Harga"
        });
        sortComboBox.addActionListener(e -> sortData());
        controlPanel.add(sortComboBox, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton addButton = new JButton("Tambah Produk");
        JButton updateButton = new JButton("Update Produk");
        JButton deleteButton = new JButton("Hapus Produk");

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        controlPanel.add(buttonPanel, BorderLayout.CENTER);

        panel.add(controlPanel, BorderLayout.SOUTH);

        addButton.addActionListener(e -> new AddProductGui(this).setVisible(true));
        updateButton.addActionListener(e -> {
            String sku = JOptionPane.showInputDialog(this, "Masukkan SKU produk yang mau diupdate:");
            if (sku != null) {
                new UpdateProductGui(this, sku).setVisible(true);
            }
        });
        deleteButton.addActionListener(e -> new DeleteProductGui(this).setVisible(true));

        ambilData(null);
        return panel;
    }

    public void ambilData(String sortCriteria) {
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
        if (selectedSortOption != null && !selectedSortOption.equals("Dipilih maseh urutannya mau opo?")) {
            ambilData(selectedSortOption);
        }
    }

    private JPanel createProductTypesPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.PINK);

        JLabel label = new JLabel("Data Berdasarkan Tipe Produk", JLabel.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(label, BorderLayout.NORTH);

        String[] columnNames = {"SKU", "Tipe Produk", "Harga"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel filterPanel = new JPanel(new FlowLayout());
        JTextField typeField = new JTextField(20);
        JButton searchButton = new JButton("Cari");
        filterPanel.add(new JLabel("Masukkan Tipe Produk:"));
        filterPanel.add(typeField);
        filterPanel.add(searchButton);
        panel.add(filterPanel, BorderLayout.SOUTH);

        searchButton.addActionListener(e -> {
            String type = typeField.getText().trim();
            if (!type.isEmpty()) {
                ProductsService productService = new ProductsService();
                ArrayList<Products> products = productService.getProductsByType(type);
                tableModel.setRowCount(0);

                for (Products product : products) {
                    tableModel.addRow(new Object[]{
                        product.getSku(),
                        product.getProductType(),
                        String.format("%.2f", product.getPrice())
                    });
                }
            } else {
                JOptionPane.showMessageDialog(panel, "Masukkin dulu mas/mba saya tidak bisa menerawang pikiran anda", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        return panel;
    }

    private JPanel createViewBySkuPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.cyan);

        JLabel header = new JLabel("View Product by SKU", JLabel.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(header, BorderLayout.NORTH);

        String[] columnNames = {"SKU", "Product Type", "Price"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        JTable productTable = new JTable(tableModel);
        productTable.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(productTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JTextField skuField = new JTextField(20);
        JButton searchButton = new JButton("Search");
        inputPanel.add(new JLabel("SKU:"));
        inputPanel.add(skuField);
        inputPanel.add(searchButton);
        panel.add(inputPanel, BorderLayout.NORTH);

        searchButton.addActionListener(e -> {
            String sku = skuField.getText().trim();
            if (!sku.isEmpty()) {
                ProductsService productService = new ProductsService();
                Products product = productService.getProductBySku(sku);

                tableModel.setRowCount(0);

                if (product == null || product.getSku() == null) {
                    JOptionPane.showMessageDialog(panel, "di database ra ono rek " + sku, "No Results", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    Object[] row = {
                        product.getSku(),
                        product.getProductType(),
                        String.format("%.2f", product.getPrice())
                    };
                    tableModel.addRow(row);
                }
            } else {
                JOptionPane.showMessageDialog(panel, "Masukkin dulu mas/mba saya tidak bisa menerawang pikiran anda", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        return panel;
    }

    private JPanel createHighestSuppliersPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.YELLOW);

        JLabel header = new JLabel("Supplier dengan Volume Produksi Tertinggi", JLabel.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(header, BorderLayout.NORTH);

        String[] columnNames = {
            "Nama Supplier", "Rata-Rata Waktu Pengiriman", 
            "Rata-Rata Biaya Produksi", "Rata-Rata Waktu Produksi", 
            "Rata-Rata Volume Produksi"
        };
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        JTable supplierTable = new JTable(tableModel);
        supplierTable.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(supplierTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        SuppliersService service = new SuppliersService();
        List<AverageSuppliers> suppliers = service.getHighestProductionVolumeSupplier();

        tableModel.setRowCount(0);
        for (AverageSuppliers supplier : suppliers) {
            Object[] row = {
                supplier.getSupplierName(),
                String.format("%.2f", supplier.getAverageLeadTime()),
                String.format("%.2f", supplier.getAverageManufacturingCosts()),
                String.format("%.2f", supplier.getAverageManufacturingLeadTime()),
                String.format("%.2f", supplier.getAverageProductionVolumes())
            };
            tableModel.addRow(row);
        }

        return panel;
    }

    private JPanel createShippingPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.GREEN);

        JLabel header = new JLabel("Pengiriman Paling Cepat", JLabel.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(header, BorderLayout.NORTH);

        String[] columnNames = {"Carrier Pengiriman", "Rata-Rata Biaya Pengiriman", "Rata-Rata Waktu Pengiriman"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        JTable table = new JTable(tableModel);
        table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        fetchShippingData(tableModel);

        return panel;
    }

    private void fetchShippingData(DefaultTableModel tableModel) {
        ShippingService service = new ShippingService();
        List<AverageShippingCarriers> carriers = service.getFastestCarriers();

        tableModel.setRowCount(0);

        for (AverageShippingCarriers carrier : carriers) {
            Object[] row = {
                carrier.getCarrier(),
                String.format("%.2f", carrier.getAverageShippingCost()),
                String.format("%.2f", carrier.getAverageShippingTime())
            };
            tableModel.addRow(row);
        }
    }

    private JPanel createPriceRangePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.orange);

        JLabel header = new JLabel("Produk Berdasarkan Rentang Harga", JLabel.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(header, BorderLayout.NORTH);

        String[] columnNames = {"Rentang Harga", "Total Produk Terjual", "Total Produk", "Harga Rata-Rata"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        JTable table = new JTable(tableModel);
        table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        ProductsPriceRangeService service = new ProductsPriceRangeService();
        List<ProductsPriceRange> priceRanges = service.getProductsPriceRange();

        tableModel.setRowCount(0);

        for (ProductsPriceRange range : priceRanges) {
            Object[] row = {
                range.getPriceRange(),
                range.getTotalProductsSold(),
                range.getTotalProducts(),
                String.format("%.2f", range.getAveragePrice())
            };
            tableModel.addRow(row);
        }

        return panel;
    }

    // public static void main(String[] args) {
    //     SwingUtilities.invokeLater(Dashboard::new);
    // }
}
