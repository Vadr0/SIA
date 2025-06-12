package swing_base_de_datos;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.*;
import java.util.List;

public class HistorialEntradas extends JFrame {
    private JTextField filterDateFrom;
    private JTextField filterDateTo;
    private JComboBox<String> filterSupplier;
    private JTextField filterSearch;
    private JTable historyTable;
    private DefaultTableModel tableModel;

    private List<Entry> entries = Arrays.asList(
            new Entry("E1023", "Clavos", "2024-06-01", "Proveedor A", 150),
            new Entry("E1024", "Martillo", "2024-06-05", "Proveedor B", 30),
            new Entry("E1025", "Tornillos", "2024-06-04", "Proveedor A", 75),
            new Entry("E1026", "Pintura", "2024-06-07", "Proveedor C", 45),
            new Entry("E1027", "Taladro", "2024-06-02", "Proveedor B", 20),
            new Entry("E1028", "Pegamento", "2024-06-06", "Proveedor D", 60),
            new Entry("E1029", "Lijas", "2024-06-03", "Proveedor A", 100)
    );

    public HistorialEntradas() {
        setTitle("Historial de Entradas");
        setSize(1100, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(15, 15));
        getContentPane().setBackground(new Color(30, 64, 111)); // Background color

        // Main panel with padding and background
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(20, 20));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(15, 26, 43));
        add(mainPanel, BorderLayout.CENTER);

        // Title Label
        JLabel titleLabel = new JLabel("Historial de Entradas");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 26));
        titleLabel.setForeground(new Color(177, 200, 249));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBorder(new EmptyBorder(10, 0, 20, 0));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Filters panel with modern spacing and flow
        JPanel filtersPanel = new JPanel();
        filtersPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 10));
        filtersPanel.setBackground(new Color(15, 26, 43));
        mainPanel.add(filtersPanel, BorderLayout.PAGE_START);

        filterDateFrom = new JTextField(10);
        filterDateFrom.setToolTipText("Fecha desde (YYYY-MM-DD)");
        filtersPanel.add(createFilterPanel("Fecha desde", filterDateFrom));

        filterDateTo = new JTextField(10);
        filterDateTo.setToolTipText("Fecha hasta (YYYY-MM-DD)");
        filtersPanel.add(createFilterPanel("Fecha hasta", filterDateTo));

        filterSupplier = new JComboBox<>();
        filterSupplier.addItem("Todos");
        populateSupplierOptions();
        filtersPanel.add(createFilterPanel("Proveedor", filterSupplier));

        filterSearch = new JTextField(15);
        filtersPanel.add(createFilterPanel("Buscar", filterSearch));

        // Table setup
        String[] columnNames = {"Código", "Artículo", "Fecha", "Proveedor", "Cantidad"};
        tableModel = new DefaultTableModel(columnNames, 0);
        historyTable = new JTable(tableModel);
        historyTable.setFillsViewportHeight(true);
        historyTable.setRowHeight(32);
        historyTable.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        historyTable.setSelectionBackground(new Color(85, 123, 223));
        historyTable.setSelectionForeground(Color.WHITE);

        // Table header styling
        JTableHeader header = historyTable.getTableHeader();
        header.setBackground(new Color(55, 90, 152));
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Segoe UI", Font.BOLD, 16));
        header.setReorderingAllowed(false);

        // Cell renderer for alternating row colors and padding
        historyTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            Color evenBackground = new Color(46, 63, 108);
            Color oddBackground = new Color(31, 44, 86);
            Color textColor = new Color(198, 213, 243);

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus,
                                                           int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                if (isSelected) {
                    c.setBackground(table.getSelectionBackground());
                    c.setForeground(table.getSelectionForeground());
                } else {
                    c.setBackground(row % 2 == 0 ? evenBackground : oddBackground);
                    c.setForeground(textColor);
                }
                setBorder(BorderFactory.createEmptyBorder(6, 10, 6, 10));
                return c;
            }
        });

        JScrollPane tableScrollPane = new JScrollPane(historyTable);
        tableScrollPane.setBorder(BorderFactory.createLineBorder(new Color(55, 90, 152), 2, true));
        mainPanel.add(tableScrollPane, BorderLayout.CENTER);

        // Listeners for filtering on key press and selection changes
        filterDateFrom.getDocument().addDocumentListener((SimpleDocumentListener) this::applyFilters);
        filterDateTo.getDocument().addDocumentListener((SimpleDocumentListener) this::applyFilters);
        filterSupplier.addActionListener(e -> applyFilters());
        filterSearch.getDocument().addDocumentListener((SimpleDocumentListener) this::applyFilters);

        // Initial table data rendering
        renderTable(entries);
    }

    private JPanel createFilterPanel(String labelText, JComponent component) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(5, 5));
        panel.setPreferredSize(new Dimension(200, 55));
        panel.setBackground(new Color(15, 26, 43));

        JLabel label = new JLabel(labelText);
        label.setForeground(new Color(142, 165, 214));
        label.setFont(new Font("Segoe UI", Font.BOLD, 14));
        panel.add(label, BorderLayout.NORTH);

        component.setBackground(new Color(25, 48, 88));
        component.setForeground(Color.WHITE);
        component.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        if(component instanceof JTextField) {
            ((JTextField) component).setCaretColor(Color.WHITE);
        }
        if(component instanceof JComboBox<?>) {
            ((JComboBox<?>) component).setFocusable(true);
        }
        component.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(71, 106, 178), 2, true),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));

        panel.add(component, BorderLayout.CENTER);
        return panel;
    }

    private void populateSupplierOptions() {
        Set<String> suppliersSet = new TreeSet<>();
        for (Entry entry : entries) {
            suppliersSet.add(entry.getSupplier());
        }
        for (String supplier : suppliersSet) {
            filterSupplier.addItem(supplier);
        }
    }

    private void renderTable(List<Entry> filteredEntries) {
        tableModel.setRowCount(0);
        if (filteredEntries.isEmpty()) {
            // Show no data row spanning all columns, center aligned, with accent color
            tableModel.addRow(new Object[]{"", "No se encontraron entradas que coincidan con los filtros.", "", "", ""});
            return;
        }
        for (Entry entry : filteredEntries) {
            tableModel.addRow(new Object[]{entry.getCode(), entry.getArticle(), entry.getDate(), entry.getSupplier(), entry.getQuantity()});
        }
    }

    private void applyFilters() {
        String fromDateStr = filterDateFrom.getText().trim();
        String toDateStr = filterDateTo.getText().trim();
        String selectedSupplier = (String) filterSupplier.getSelectedItem();
        String searchText = filterSearch.getText().trim().toLowerCase();

        List<Entry> filtered = new ArrayList<>(entries);

        if (!fromDateStr.isEmpty()) {
            filtered.removeIf(entry -> entry.getDate().compareTo(fromDateStr) < 0);
        }
        if (!toDateStr.isEmpty()) {
            filtered.removeIf(entry -> entry.getDate().compareTo(toDateStr) > 0);
        }
        if (selectedSupplier != null && !selectedSupplier.equals("Todos")) {
            filtered.removeIf(entry -> !entry.getSupplier().equals(selectedSupplier));
        }
        if (!searchText.isEmpty()) {
            filtered.removeIf(entry ->
                    !entry.getCode().toLowerCase().contains(searchText) &&
                    !entry.getArticle().toLowerCase().contains(searchText) &&
                    !entry.getDate().toLowerCase().contains(searchText) &&
                    !entry.getSupplier().toLowerCase().contains(searchText) &&
                    !String.valueOf(entry.getQuantity()).contains(searchText)
            );
        }

        renderTable(filtered);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            HistorialEntradas frame = new HistorialEntradas();
            frame.setVisible(true);
        });
    }

    // Data model for entry
    static class Entry {
        private final String code;
        private final String article;
        private final String date;
        private final String supplier;
        private final int quantity;

        public Entry(String code, String article, String date, String supplier, int quantity) {
            this.code = code;
            this.article = article;
            this.date = date;
            this.supplier = supplier;
            this.quantity = quantity;
        }

        public String getCode() { return code; }
        public String getArticle() { return article; }
        public String getDate() { return date; }
        public String getSupplier() { return supplier; }
        public int getQuantity() { return quantity; }
    }

    // Functional interface for document listener simplification
    @FunctionalInterface
    interface SimpleDocumentListener extends javax.swing.event.DocumentListener {
        void update();

        @Override
        default void insertUpdate(javax.swing.event.DocumentEvent e) {
            update();
        }

        @Override
        default void removeUpdate(javax.swing.event.DocumentEvent e) {
            update();
        }

        @Override
        default void changedUpdate(javax.swing.event.DocumentEvent e) {
            update();
        }
    }
}
