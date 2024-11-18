package com.yogarn.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import com.yogarn.service.ShippingService;
import com.yogarn.model.AverageShippingCarriers;

public class ViewShippingGui extends JFrame {
    private DefaultTableModel tableModel;

    public ViewShippingGui(JFrame parentFrame) {
        setTitle("Pengiriman Paling Cepat");
        setSize(600, 400);
        setLayout(new BorderLayout());

        JLabel header = new JLabel("Pengiriman Paling Cepat", JLabel.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 18));
        add(header, BorderLayout.NORTH);

        String[] columnNames = {"Carrier Pengiriman", "Rata-Rata Biaya Pengiriman", "Rata-Rata Waktu Pengiriman"};
        tableModel = new DefaultTableModel(columnNames, 0);

        JTable table = new JTable(tableModel);
        table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton backButton = new JButton("Kembali");
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.SOUTH);

        backButton.addActionListener(e -> {
            parentFrame.setVisible(true);
            dispose();
        });

        fetchShippingData();

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void fetchShippingData() {
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
}
