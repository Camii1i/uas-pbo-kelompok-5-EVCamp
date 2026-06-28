package view;

import database.Koneksi;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginForm extends JFrame {

    public LoginForm() {

        setTitle("Login EVCamp");
        setSize(400,300);
        setLayout(null);

        JLabel lblUser = new JLabel("Username");
        lblUser.setBounds(50,50,100,25);

        JTextField txtUser = new JTextField();
        txtUser.setBounds(150,50,150,25);

        JLabel lblPass = new JLabel("Password");
        lblPass.setBounds(50,90,100,25);

        JPasswordField txtPass = new JPasswordField();
        txtPass.setBounds(150,90,150,25);

        JButton btnLogin = new JButton("Login");
        btnLogin.setBounds(150,140,100,30);

        btnLogin.addActionListener(e -> {

            String username = txtUser.getText();
            String password = String.valueOf(
                    txtPass.getPassword()
            );

            try {

                Connection con =
                        Koneksi.getConnection();

                String sql =
                        "SELECT * FROM user WHERE username=? AND password=?";

                PreparedStatement ps =
                        con.prepareStatement(sql);

                ps.setString(1, username);
                ps.setString(2, password);

                ResultSet rs =
                        ps.executeQuery();

                if(rs.next()) {

                    JOptionPane.showMessageDialog(
                            null,
                            "Login Berhasil!"
                    );

                    DashboardForm dashboard =
                            new DashboardForm();

                    dashboard.setVisible(true);

                    dispose();

                } else {

                    JOptionPane.showMessageDialog(
                            null,
                            "Username atau Password Salah!"
                    );

                }

            } catch(Exception ex) {

                JOptionPane.showMessageDialog(
                        null,
                        ex.getMessage()
                );

            }

        });

        add(lblUser);
        add(txtUser);

        add(lblPass);
        add(txtPass);

        add(btnLogin);

        setDefaultCloseOperation(
                EXIT_ON_CLOSE
        );

        setLocationRelativeTo(null);
    }
}