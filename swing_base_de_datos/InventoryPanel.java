package swing_base_de_datos;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class InventoryPanel extends JFrame {

    public InventoryPanel() {
        setTitle("Panel Principal - Inventario");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1100, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(15, 15));
        getContentPane().setBackground(new Color(30, 64, 111)); // Background similar to html gradient base

        // Header panel
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(15, 26, 43));
        headerPanel.setBorder(new EmptyBorder(15, 20, 15, 20));

        JLabel headerLabel = new JLabel("Panel Principal - Inventario");
        headerLabel.setForeground(new Color(177, 200, 249));
        headerLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        headerLabel.setHorizontalAlignment(SwingConstants.LEFT);
        headerLabel.setBorder(new EmptyBorder(0,0,0,0));

        headerPanel.add(headerLabel, BorderLayout.WEST);

        add(headerPanel, BorderLayout.NORTH);

        // Main content panel
        JPanel mainPanel = new JPanel();
        mainPanel.setOpaque(false);
        mainPanel.setLayout(new BorderLayout(15, 15));
        mainPanel.setBorder(new EmptyBorder(10, 20, 20, 20));

        // Summary cards container with FlowLayout
        JPanel summaryPanel = new JPanel(new GridLayout(1,3, 15, 0));
        summaryPanel.setOpaque(false);

        summaryPanel.add(createCard("Total de productos", "1,432"));
        summaryPanel.add(createCard("Ratio de rotación", "0.74%"));
        summaryPanel.add(createCard("Margen bruto ROI", "$1.6620"));

        mainPanel.add(summaryPanel, BorderLayout.NORTH);

        // Inventory table
        String[] columnNames = {
                "Producto",
                "Vendidos (Hombres)", "Stock actual (Hombres)", "Por llegar (Hombres)",
                "Vendidos (Mujeres)", "Stock actual (Mujeres)", "Por llegar (Mujeres)",
                "Vendidos (Total General)", "Stock actual (Total General)", "Por llegar (Total General)"
        };

        Object[][] data = {
                {"Camisetas", "831 pcs", "425 pcs", "1,860 pcs", "601 pcs", "544 pcs", "90 pcs", "1,432 pcs", "969 pcs", "276 pcs"},
                {"Producto A", "-", "-", "-", "49 pcs", "49 pcs", "10 pcs", "49 pcs", "49 pcs", "10 pcs"},
                {"Producto B", "56 pcs", "34 pcs", "12 pcs", "-", "-", "-", "56 pcs", "34 pcs", "12 pcs"},
                {"Producto C", "88 pcs", "52 pcs", "10 pcs", "-", "-", "-", "88 pcs", "52 pcs", "10 pcs"},
                {"Producto D", "-", "-", "-", "66 pcs", "66 pcs", "10 pcs", "66 pcs", "66 pcs", "10 pcs"},
        };

        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames) {
            // Prevent editing table cells
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable inventoryTable = new JTable(tableModel);
        inventoryTable.setFillsViewportHeight(true);
        inventoryTable.setRowHeight(28);
        inventoryTable.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        inventoryTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        inventoryTable.getTableHeader().setBackground(new Color(63, 101, 186));
        inventoryTable.getTableHeader().setForeground(Color.WHITE);
        inventoryTable.setSelectionBackground(new Color(118, 157, 211));
        inventoryTable.setGridColor(new Color(217, 226, 243));
        inventoryTable.setShowHorizontalLines(true);
        inventoryTable.setShowVerticalLines(false);

        JScrollPane tableScrollPane = new JScrollPane(inventoryTable);
        tableScrollPane.setBorder(BorderFactory.createLineBorder(new Color(63,101,186), 2));
        mainPanel.add(tableScrollPane, BorderLayout.CENTER);

        // Actions panel with buttons
        JPanel actionsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 20));
        actionsPanel.setOpaque(false);

        actionsPanel.add(createActionButton("Registrar nueva entrada", "plus-circle"));
        actionsPanel.add(createActionButton("Consultar historial", "history"));
        actionsPanel.add(createActionButton("Generar reportes", "file-alt"));

        mainPanel.add(actionsPanel, BorderLayout.SOUTH);

        add(mainPanel, BorderLayout.CENTER);

        // Footer
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(new Color(15, 26, 43));
        footerPanel.setPreferredSize(new Dimension(getWidth(), 40));
        footerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JLabel footerLabel = new JLabel("\u00a9 2024 Panel de Inventario. Todos los derechos reservados.");
        footerLabel.setForeground(new Color(177, 200, 249));
        footerLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        footerPanel.add(footerLabel);

        add(footerPanel, BorderLayout.SOUTH);
    }

    private JPanel createCard(String title, String value) {
        JPanel card = new JPanel();
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        card.setLayout(new BorderLayout());
        card.setPreferredSize(new Dimension(300, 130));
        card.setMaximumSize(new Dimension(300, 130));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 18));
        titleLabel.setForeground(new Color(30, 60, 146));
        titleLabel.setBorder(new EmptyBorder(0, 0, 10, 0));

        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(new Font("Segoe UI", Font.BOLD, 36));
        valueLabel.setForeground(new Color(55, 90, 152));
        valueLabel.setHorizontalAlignment(SwingConstants.CENTER);
        valueLabel.setVerticalAlignment(SwingConstants.CENTER);

        card.add(titleLabel, BorderLayout.NORTH);
        card.add(valueLabel, BorderLayout.CENTER);

        card.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        return card;
    }

    private JButton createActionButton(String text, String iconName) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setForeground(new Color(30, 60, 114));
        button.setBackground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(55, 90, 152), 2, true),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Icon from FontAwesome
        Icon icon = getFontAwesomeIcon(iconName);
        if (icon != null) {
            button.setIcon(icon);
            button.setHorizontalTextPosition(SwingConstants.RIGHT);
            button.setIconTextGap(10);
        }

        button.addActionListener((ActionEvent e) -> {
            JOptionPane.showMessageDialog(this,
                    "Función '" + text + "' no implementada.",
                    "Información",
                    JOptionPane.INFORMATION_MESSAGE);
        });

        // Hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(230, 240, 255));
                button.setForeground(new Color(10, 50, 120));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.WHITE);
                button.setForeground(new Color(30, 60, 114));
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(200, 220, 250));
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(230, 240, 255));
            }
        });

        return button;
    }

    private Icon getFontAwesomeIcon(String iconName) {
        // Swing doesn't support FontAwesome icons natively without external libs.
        // Instead, we can use Unicode emoji or omit icon for simplicity.
        // Alternatively, if such libs installed, we would load them.
        // Here, omit icons for simplicity.
        return null;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            InventoryPanel frame = new InventoryPanel();
            frame.setVisible(true);
        });
    }
}
