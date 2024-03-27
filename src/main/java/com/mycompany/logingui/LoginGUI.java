/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.logingui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class LoginGUI {
    private JFrame frame;
    private JTextField idField;
    private JTextField matriculaField;
    private JPasswordField passField;
    private JButton loginButton;
    private JButton registerButton;
    private UserService userService;

    public LoginGUI() {
        frame = new JFrame("Inicio de Sesión");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        JLabel idLabel = new JLabel("ID:");
        idField = new JTextField();

        JLabel matriculaLabel = new JLabel("Matrícula:");
        matriculaField = new JTextField();

        JLabel passLabel = new JLabel("Contraseña:");
        passField = new JPasswordField();

        loginButton = new JButton("Iniciar Sesión");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                char[] password = passField.getPassword();

                boolean credencialesValidas = userService.validarCredenciales(id, new String(password));

                if (credencialesValidas) {
                    JOptionPane.showMessageDialog(frame, "Inicio de sesión exitoso.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(frame, "Usuario o contraseña incorrectos.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        registerButton = new JButton("Registrarse");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                String matricula = matriculaField.getText();
                char[] password = passField.getPassword();

                if (!id.isEmpty() && !matricula.isEmpty() && password.length > 0) {
                    boolean registroExitoso = userService.registrarUsuario(id, matricula, new String(password));
                    if (registroExitoso) {
                        JOptionPane.showMessageDialog(frame, "Usuario registrado exitosamente.", "Registro", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Error al registrar el usuario.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        panel.add(idLabel);
        panel.add(idField);
        panel.add(matriculaLabel);
        panel.add(matriculaField);
        panel.add(passLabel);
        panel.add(passField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);

        frame.add(panel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);

        userService = new UserService();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginGUI();
            }
        });
    }
}
