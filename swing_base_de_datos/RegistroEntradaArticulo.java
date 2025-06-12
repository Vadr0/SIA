package swing_base_de_datos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class RegistroEntradaArticulo extends JFrame {
    private JTextField articleNameField;
    private JTextArea descriptionArea;
    private JTextField quantityField;
    private JComboBox<String> unitComboBox;
    private JTextField supplierField;
    private JTextField dateField;
    private JLabel feedbackLabel;

    private List<String> existingArticles = Arrays.asList("Clavos", "Martillo", "Tornillos", "Pintura", "Taladro");

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public RegistroEntradaArticulo() {
        setTitle("Registrar Entrada de Artículo");
        setSize(480, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        dateFormat.setLenient(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Title
        JLabel titleLabel = new JLabel("Registrar Entrada de Artículo");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(new Color(55, 90, 152));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(titleLabel, gbc);

        // Article Name
        gbc.gridwidth = 1;
        gbc.gridy++;
        gbc.gridx = 0;
        add(new JLabel("Nombre del artículo*"), gbc);
        articleNameField = new JTextField();
        gbc.gridx = 1;
        add(articleNameField, gbc);

        // Description
        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Descripción"), gbc);
        descriptionArea = new JTextArea(3, 20);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        gbc.gridx = 1;
        add(new JScrollPane(descriptionArea), gbc);

        // Quantity
        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Cantidad*"), gbc);
        quantityField = new JTextField();
        gbc.gridx = 1;
        add(quantityField, gbc);

        // Unit
        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Unidad de medida*"), gbc);
        unitComboBox = new JComboBox<>(new String[]{"Seleccione unidad", "Pieza", "Kilogramo (kg)", "Litro (l)", "Metro (m)", "Paquete"});
        gbc.gridx = 1;
        add(unitComboBox, gbc);

        // Supplier
        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Proveedor*"), gbc);
        supplierField = new JTextField();
        gbc.gridx = 1;
        add(supplierField, gbc);

        // Date
        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Fecha* (YYYY-MM-DD)"), gbc);
        dateField = new JTextField();
        gbc.gridx = 1;
        add(dateField, gbc);

        // Set today's date in the date field
        dateField.setText(dateFormat.format(new Date())); // Set today's date

        // Feedback Label
        feedbackLabel = new JLabel(" ");
        feedbackLabel.setForeground(Color.RED);
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        add(feedbackLabel, gbc);

        // Submit Button
        JButton submitButton = new JButton("Registrar Entrada");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSubmit();
            }
        });
        gbc.gridy++;
        add(submitButton, gbc);
    }

    private void handleSubmit() {
        String articleName = articleNameField.getText().trim();
        String description = descriptionArea.getText().trim();
        String quantityStr = quantityField.getText().trim();
        String unit = (String) unitComboBox.getSelectedItem();
        String supplier = supplierField.getText().trim();
        String dateStr = dateField.getText().trim();

        // Validations
        if (articleName.isEmpty()) {
            feedbackLabel.setText("El nombre del artículo es obligatorio.");
            articleNameField.requestFocus();
            return;
        }
        if (existingArticles.stream().anyMatch(a -> a.equalsIgnoreCase(articleName))) {
            feedbackLabel.setText("Este nombre ya existe. Debe ser único.");
            articleNameField.requestFocus();
            return;
        }
        int quantity;
        try {
            quantity = Integer.parseInt(quantityStr);
            if (quantity <= 0) {
                feedbackLabel.setText("Ingrese una cantidad válida (mayor que 0).");
                quantityField.requestFocus();
                return;
            }
        } catch (NumberFormatException ex) {
            feedbackLabel.setText("Ingrese una cantidad válida (mayor que 0).");
            quantityField.requestFocus();
            return;
        }
        if (unit == null || unit.equals("Seleccione unidad")) {
            feedbackLabel.setText("Seleccione una unidad de medida.");
            unitComboBox.requestFocus();
            return;
        }
        if (supplier.isEmpty()) {
            feedbackLabel.setText("Ingrese el nombre del proveedor.");
            supplierField.requestFocus();
            return;
        }
        Date date;
        try {
            date = dateFormat.parse(dateStr);
            Date today = dateFormat.parse(dateFormat.format(new Date()));
            if (date.after(today)) {
                feedbackLabel.setText("La fecha no puede ser futura.");
                dateField.requestFocus();
                return;
            }
        } catch (ParseException e) {
            feedbackLabel.setText("Formato de fecha inválido. Use YYYY-MM-DD.");
            dateField.requestFocus();
            return;
        }

        // If valid, simulate saving data
        feedbackLabel.setForeground(new Color(58, 157, 50)); // Green
        feedbackLabel.setText("Artículo registrado con éxito.");

        JOptionPane.showMessageDialog(this,
                String.format("Artículo registrado:%n- Nombre: %s%n- Descripción: %s%n- Cantidad: %d%n- Unidad: %s%n- Proveedor: %s%n- Fecha: %s",
                        articleName, description, quantity, unit, supplier, dateStr));

        // Reset form and feedback
        articleNameField.setText("");
        descriptionArea.setText("");
        quantityField.setText("");
        unitComboBox.setSelectedIndex(0);
        supplierField.setText("");
        dateField.setText(dateFormat.format(new Date())); // Reset to today's date
        feedbackLabel.setForeground(Color.RED);
        feedbackLabel.setText(" ");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RegistroEntradaArticulo frame = new RegistroEntradaArticulo();
            frame.setVisible(true);
        });
    }
}
