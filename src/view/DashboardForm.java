package view;

import javax.swing.*;

public class DashboardForm extends JFrame {

    public DashboardForm() {

        setTitle("Dashboard EVCamp");
        setSize(600,400);
        setLayout(null);

        JLabel lblTitle = new JLabel("Selamat Datang di EVCamp");
        lblTitle.setBounds(200,50,250,30);

        JButton btnEvent = new JButton("Data Event");
        btnEvent.setBounds(200,120,150,40);

        JButton btnPeserta = new JButton("Data Peserta");
        btnPeserta.setBounds(200,180,150,40);

        JButton btnLogout = new JButton("Logout");
        btnLogout.setBounds(200,240,150,40);

        // Buka Form Event
        btnEvent.addActionListener(e -> {

            EventForm eventForm = new EventForm();
            eventForm.setVisible(true);

        });

        // Buka Form Peserta
        btnPeserta.addActionListener(e -> {

            PesertaForm pesertaForm = new PesertaForm();
            pesertaForm.setVisible(true);

        });

        // Logout
        btnLogout.addActionListener(e -> {

            LoginForm login = new LoginForm();
            login.setVisible(true);

            dispose();

        });

        add(lblTitle);
        add(btnEvent);
        add(btnPeserta);
        add(btnLogout);

        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}