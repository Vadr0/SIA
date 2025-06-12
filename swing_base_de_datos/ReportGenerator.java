package swing_base_de_datos;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ReportGenerator extends JFrame {
    private JTextField dateFromField;
    private JTextField dateToField;
    private JComboBox<String> supplierSelect;
    private JTextArea messageArea;

    public ReportGenerator() {
        setTitle("Generar Reportes");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(30, 64, 111));

        // Main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(20, 30, 20, 30));
        mainPanel.setBackground(new Color(15, 26, 43));

        // Title
        JLabel titleLabel = new JLabel("Generar Reportes");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titleLabel.setForeground(new Color(177, 200, 249));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Form panel with GridBagLayout for precise control
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(15, 26, 43));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Date From
        JLabel dateFromLabel = new JLabel("Fecha desde:");
        dateFromLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(dateFromLabel, gbc);

        dateFromField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(dateFromField, gbc);

        // Date To
        JLabel dateToLabel = new JLabel("Fecha hasta:");
        dateToLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(dateToLabel, gbc);

        dateToField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(dateToField, gbc);

        // Supplier
        JLabel supplierLabel = new JLabel("Proveedor:");
        supplierLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(supplierLabel, gbc);

        supplierSelect = new JComboBox<>();
        supplierSelect.addItem("Todos");
        supplierSelect.addItem("Proveedor A");
        supplierSelect.addItem("Proveedor B");
        supplierSelect.addItem("Proveedor C");
        supplierSelect.addItem("Proveedor D");
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(supplierSelect, gbc);

        mainPanel.add(formPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Buttons panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(15, 26, 43));
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JButton btnPdf = new JButton("Generar PDF");
        btnPdf.setBackground(new Color(0, 120, 215));
        btnPdf.setForeground(Color.WHITE);
        btnPdf.setFocusPainted(false);
        
        JButton btnExcel = new JButton("Generar Excel");
        btnExcel.setBackground(new Color(34, 139, 34));
        btnExcel.setForeground(Color.WHITE);
        btnExcel.setFocusPainted(false);
        
        buttonPanel.add(btnPdf);
        buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        buttonPanel.add(btnExcel);
        mainPanel.add(buttonPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Message area
        messageArea = new JTextArea(3, 20);
        messageArea.setEditable(false);
        messageArea.setBackground(new Color(15, 26, 43));
        messageArea.setForeground(Color.WHITE);
        messageArea.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.GRAY),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        messageArea.setLineWrap(true);
        messageArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(messageArea);
        scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(scrollPane);

        add(mainPanel, BorderLayout.CENTER);

        // Button actions
        btnPdf.addActionListener(e -> {
            if (validateDates()) {
                messageArea.setText("Generando reporte PDF para el periodo: " + 
                                  dateFromField.getText() + " - " + dateToField.getText() + 
                                  "\nProveedor: " + supplierSelect.getSelectedItem());
            }
        });
        
        btnExcel.addActionListener(e -> {
            if (validateDates()) {
                messageArea.setText("Generando reporte Excel para el periodo: " + 
                                  dateFromField.getText() + " - " + dateToField.getText() + 
                                  "\nProveedor: " + supplierSelect.getSelectedItem());
            }
        });
    }

    private boolean validateDates() {
        if (dateFromField.getText().trim().isEmpty() || dateToField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese ambas fechas", 
                                        "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ReportGenerator frame = new ReportGenerator();
            frame.setVisible(true);
        });
    }
}
