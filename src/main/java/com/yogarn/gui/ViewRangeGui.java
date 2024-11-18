package com.yogarn.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import com.yogarn.service.ProductsPriceRangeService;
import com.yogarn.model.ProductsPriceRange;

public class ViewRangeGui extends JFrame {
    private DefaultTableModel tableModel;

    public ViewRangeGui(JFrame parentFrame) {
        setTitle("Produk Berdasarkan Rentang Harga");
        setSize(600, 400);
        setLayout(new BorderLayout());

        JLabel header = new JLabel("Produk Berdasarkan Rentang Harga", JLabel.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 18));
        add(header, BorderLayout.NORTH);

        String[] columnNames = {"Rentang Harga", "Total Produk Terjual", "Total Produk", "Harga Rata-Rata"};
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

        fetchData();

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void fetchData() {
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
    }
}
