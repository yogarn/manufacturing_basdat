package com.yogarn.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import com.yogarn.model.AverageSuppliers;
import com.yogarn.service.SuppliersService;

public class ViewHighestGui extends JFrame {
    private DefaultTableModel tableModel;

    public ViewHighestGui(JFrame parentFrame) {
        setTitle("Supplier dengan Volume Produksi Tertinggi");
        setSize(600, 500);
        setLayout(new BorderLayout());

        JLabel header = new JLabel("Supplier dengan Volume Produksi Tertinggi", JLabel.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 18));
        add(header, BorderLayout.NORTH);

        String[] columnNames = {
            "Nama Supplier", "Rata-Rata Waktu Pengiriman", "Rata-Rata Biaya Produksi", 
            "Rata-Rata Waktu Produksi", "Rata-Rata Volume Produksi"
        };
        tableModel = new DefaultTableModel(columnNames, 0);

        JTable supplierTable = new JTable(tableModel);
        supplierTable.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(supplierTable);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton backButton = new JButton("Kembali");
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.SOUTH);

        backButton.addActionListener(e -> {
            parentFrame.setVisible(true);
            dispose();
        });

        fetchData();

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void fetchData() {
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
    }
}
