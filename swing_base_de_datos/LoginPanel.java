package swing_base_de_datos;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class LoginPanel extends JFrame {

    public LoginPanel() {
        setTitle("Pantalla de Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 550);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(30, 64, 111)); // Background similar to html gradient base
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(30, 30, 30, 30));

        // Title
        JLabel titleLabel = new JLabel("Acceso de Usuarios");
        titleLabel.setForeground(new Color(177, 200, 249));
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBorder(new EmptyBorder(0, 0, 30, 0));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Form panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setOpaque(false);

        // Email field
        JPanel emailPanel = createInputField("Correo electrónico");
        JTextField emailField = (JTextField) emailPanel.getComponent(1);
        formPanel.add(emailPanel);

        // Password field
        JPanel passwordPanel = createInputField("Contraseña");
        JPasswordField passwordField = (JPasswordField) passwordPanel.getComponent(1);
        formPanel.add(passwordPanel);

        // Login button
        JButton loginButton = createButton("Acceder");
        formPanel.add(loginButton);

        // Social login buttons
        JPanel socialPanel = new JPanel();
        socialPanel.setLayout(new BoxLayout(socialPanel, BoxLayout.Y_AXIS));
        socialPanel.setOpaque(false);
        socialPanel.setBorder(new EmptyBorder(20, 0, 0, 0));

        JButton googleButton = createSocialButton("Acceder con Google", "C:\\Users\\Usuario\\Downloads\\descarga_google.png");
        JButton facebookButton = createSocialButton("Acceder con Facebook", "C:\\Users\\Usuario\\Downloads\\png-facebook.png");

        socialPanel.add(googleButton);
        socialPanel.add(facebookButton);

        formPanel.add(socialPanel);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        // Error message label
        JLabel errorMessageLabel = new JLabel();
        errorMessageLabel.setForeground(new Color(255, 99, 99));
        errorMessageLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        errorMessageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        errorMessageLabel.setVisible(false);
        mainPanel.add(errorMessageLabel, BorderLayout.SOUTH);

        // Login action
        loginButton.addActionListener(e -> {
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());

            errorMessageLabel.setVisible(false);

            if (email.isEmpty()) {
                errorMessageLabel.setText("Por favor, ingresa un correo electrónico.");
                errorMessageLabel.setVisible(true);
                emailField.requestFocusInWindow();
                return;
            }

            if (!isValidEmail(email)) {
                errorMessageLabel.setText("Formato de correo inválido.");
                errorMessageLabel.setVisible(true);
                emailField.requestFocusInWindow();
                return;
            }

            if (password.isEmpty()) {
                errorMessageLabel.setText("Por favor, ingresa la contraseña.");
                errorMessageLabel.setVisible(true);
                passwordField.requestFocusInWindow();
                return;
            }

            if (password.length() < 6) {
                errorMessageLabel.setText("La contraseña debe tener al menos 6 caracteres.");
                errorMessageLabel.setVisible(true);
                passwordField.requestFocusInWindow();
                return;
            }

            if (email.equals("user@test.com") && password.equals("password123")) {
                JOptionPane.showMessageDialog(this, "¡Inicio de sesión exitoso!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                errorMessageLabel.setText("Credenciales inválidas. Intenta nuevamente.");
                errorMessageLabel.setVisible(true);
            }
        });

        add(mainPanel);
    }

    private JPanel createInputField(String labelText) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);
        panel.setBorder(new EmptyBorder(0, 0, 15, 0));

        JLabel label = new JLabel(labelText);
        label.setForeground(new Color(55, 90, 152));
        label.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
        panel.add(label);

        JTextField textField = (labelText.equals("Contraseña")) ? new JPasswordField() : new JTextField();
        textField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        textField.setBackground(new Color(249, 250, 255));
        textField.setForeground(new Color(34, 34, 34));
        textField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(196, 208, 248)),
                new EmptyBorder(8, 10, 8, 10)));
        panel.add(textField);

        return panel;
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 18));
        button.setForeground(new Color(30, 60, 114));
        button.setBackground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(55, 90, 152), 2, true),
                new EmptyBorder(10, 20, 10, 20)));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, button.getPreferredSize().height));

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

    private JButton createSocialButton(String text, String imagePath) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setForeground(new Color(30, 60, 114));
        button.setBackground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(55, 90, 152), 2, true),
                new EmptyBorder(8, 16, 8, 16)));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, button.getPreferredSize().height));

        try {
            BufferedImage img = ImageIO.read(new File(imagePath));

            if (img != null) { // Check if img is null
                Image scaledImg = img.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
                ImageIcon icon = new ImageIcon(scaledImg);
                button.setIcon(icon);
                button.setHorizontalTextPosition(SwingConstants.RIGHT);
                button.setIconTextGap(8);
            } else {
                System.err.println("Could not read image file: " + imagePath);
                // Optionally, set a default icon or text on the button
                button.setText(text + " (Image not found)");
            }
        } catch (IOException e) {
            System.err.println("Error loading image: " + e.getMessage());
            // Optionally, set a default icon or text on the button
            button.setText(text + " (Error loading image)");
        }

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

        button.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Función no implementada.", "Información", JOptionPane.INFORMATION_MESSAGE);
        });

        return button;
    }
//////////////////////////////////


package swing_base_de_datos;

import javax.swing.SwingUtilities;
public class main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginPanel frame = new LoginPanel();
            frame.setVisible(true);
        });
    }
}

    private boolean isValidEmail(String email) {
        String regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        return email.matches(regex);
    }
}
