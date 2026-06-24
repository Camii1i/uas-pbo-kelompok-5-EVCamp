package view;

import javax.swing.*;

public class LoginForm extends JFrame {

    public LoginForm() {

        setTitle("Login EVCamp");
        setSize(400,300);
        setLayout(null);

        // Label Username
        JLabel lblUser = new JLabel("Username");
        lblUser.setBounds(50,50,100,25);

        // TextField Username
        JTextField txtUser = new JTextField();
        txtUser.setBounds(150,50,150,25);

        // Label Password
        JLabel lblPass = new JLabel("Password");
        lblPass.setBounds(50,90,100,25);

        // Password Field
        JPasswordField txtPass = new JPasswordField();
        txtPass.setBounds(150,90,150,25);

        // Tombol Login
        JButton btnLogin = new JButton("Login");
        btnLogin.setBounds(150,140,100,30);

        btnLogin.addActionListener(e -> {

            String username = txtUser.getText();
            String password = String.valueOf(txtPass.getPassword());

            if(username.equals("admin") && password.equals("123")) {

                JOptionPane.showMessageDialog(
                        null,
                        "Login Berhasil!"
                );

                DashboardForm dashboard = new DashboardForm();
                dashboard.setVisible(true);

                dispose();

            } else {

                JOptionPane.showMessageDialog(
                        null,
                        "Username atau Password Salah!"
                );
            }

        });

        add(lblUser);
        add(txtUser);
        add(lblPass);
        add(txtPass);
        add(btnLogin);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}