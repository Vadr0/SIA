package swing_base_de_datos;
import javax.swing.*;
import java.awt.*;

public class ArticleEntryForm extends JFrame {

    public ArticleEntryForm() {
        setTitle("Edición de Entrada de Artículo");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 450);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(30, 64, 111)); // Color de fondo

        // Main panel with padding
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        mainPanel.setBackground(new Color(15, 26, 43)); // Color de fondo del panel

        // Title
        JLabel titleLabel = new JLabel("Edición de Entrada de Artículo");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titleLabel.setForeground(new Color(177, 200, 249));
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Article name field
        JLabel nameLabel = new JLabel("Nombre del artículo*");
        nameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(nameLabel);

        JTextField nameField = new JTextField();
        nameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        nameField.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(nameField);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        // Description field
        JLabel descLabel = new JLabel("Descripción");
        descLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        descLabel.setForeground(Color.WHITE);
        descLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(descLabel);

        JLabel descSubLabel = new JLabel("Descripción opcional del artículo");
        descSubLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        descSubLabel.setForeground(Color.GRAY);
        descSubLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(descSubLabel);

        JTextArea descArea = new JTextArea(3, 20);
        descArea.setLineWrap(true);
        descArea.setWrapStyleWord(true);
        JScrollPane descScrollPane = new JScrollPane(descArea);
        descScrollPane.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
        descScrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(descScrollPane);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        // Quantity field
        JLabel quantityLabel = new JLabel("Cantidad*");
        quantityLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        quantityLabel.setForeground(Color.WHITE);
        quantityLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(quantityLabel);

        JTextField quantityField = new JTextField();
        quantityField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        quantityField.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(quantityField);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        // Unit of measure
        JLabel unitLabel = new JLabel("Unidad de medida*");
        unitLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        unitLabel.setForeground(Color.WHITE);
        unitLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(unitLabel);

        JComboBox<String> unitComboBox = new JComboBox<>();
        unitComboBox.addItem("Pieza");
        unitComboBox.addItem("Kilogramo (kg)");
        unitComboBox.addItem("Litro (l)");
        unitComboBox.addItem("Metro (m)");
        unitComboBox.addItem("Paquete");
        unitComboBox.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        unitComboBox.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(unitComboBox);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        // Supplier field
        JLabel supplierLabel = new JLabel("Proveedor*");
        supplierLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        supplierLabel.setForeground(Color.WHITE);
        supplierLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(supplierLabel);

        JTextField supplierField = new JTextField();
        supplierField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        supplierField.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(supplierField);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        // Date field
        JLabel dateLabel = new JLabel("Fecha*");
        dateLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        dateLabel.setForeground(Color.WHITE);
        dateLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(dateLabel);

        JTextField dateField = new JTextField();
        dateField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        dateField.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(dateField);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        add(mainPanel, BorderLayout.CENTER);

        // Button panel for save button, aligned right
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(new Color(15, 26, 43));
        JButton saveButton = new JButton("Guardar Cambios");
        saveButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        saveButton.setBackground(new Color(63, 101, 186));
        saveButton.setForeground(Color.WHITE);
        saveButton.setFocusPainted(false);
        // No agregado ActionListener para que no haga nada.
        buttonPanel.add(saveButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ArticleEntryForm form = new ArticleEntryForm();
            form.setVisible(true);
        });
    }
}
