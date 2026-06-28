package view;

import database.Koneksi;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DashboardForm extends JFrame {

    JLabel lblTotalEvent;
    JLabel lblTotalPeserta;
    JLabel lblTotalPendaftaran;

    public DashboardForm() {

        setTitle("Dashboard EVCamp");
        setSize(600,500);
        setLayout(null);

        JLabel lblTitle =
                new JLabel("Selamat Datang di EVCamp");

        lblTitle.setBounds(
                190,
                20,
                250,
                30
        );

        lblTotalEvent =
                new JLabel("Total Event : 0");

        lblTotalEvent.setBounds(
                200,
                60,
                250,
                30
        );

        lblTotalPeserta =
                new JLabel("Total Peserta : 0");

        lblTotalPeserta.setBounds(
                200,
                90,
                250,
                30
        );

        lblTotalPendaftaran =
                new JLabel("Total Pendaftaran : 0");

        lblTotalPendaftaran.setBounds(
                200,
                120,
                250,
                30
        );

        JButton btnEvent =
                new JButton("Data Event");

        btnEvent.setBounds(
                200,
                170,
                150,
                40
        );

        JButton btnPeserta =
                new JButton("Data Peserta");

        btnPeserta.setBounds(
                200,
                220,
                150,
                40
        );

        JButton btnPendaftaran =
                new JButton("Pendaftaran");

        btnPendaftaran.setBounds(
                200,
                270,
                150,
                40
        );

        JButton btnLogout =
                new JButton("Logout");

        btnLogout.setBounds(
                200,
                320,
                150,
                40
        );

        // EVENT
        btnEvent.addActionListener(e -> {

            EventForm eventForm =
                    new EventForm();

            eventForm.setVisible(true);

        });

        // PESERTA
        btnPeserta.addActionListener(e -> {

            PesertaForm pesertaForm =
                    new PesertaForm();

            pesertaForm.setVisible(true);

        });

        // PENDAFTARAN
        btnPendaftaran.addActionListener(e -> {

            PendaftaranForm form =
                    new PendaftaranForm();

            form.setVisible(true);

        });

        // LOGOUT
        btnLogout.addActionListener(e -> {

            LoginForm login =
                    new LoginForm();

            login.setVisible(true);

            dispose();

        });

        add(lblTitle);

        add(lblTotalEvent);
        add(lblTotalPeserta);
        add(lblTotalPendaftaran);

        add(btnEvent);
        add(btnPeserta);
        add(btnPendaftaran);
        add(btnLogout);

        loadStatistik();

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                EXIT_ON_CLOSE
        );
    }

    private void loadStatistik() {

        try {

            Connection con =
                    Koneksi.getConnection();

            Statement st =
                    con.createStatement();

            // TOTAL EVENT
            ResultSet rs =
                    st.executeQuery(
                            "SELECT COUNT(*) AS total FROM event"
                    );

            if(rs.next()) {

                lblTotalEvent.setText(
                        "Total Event : "
                                + rs.getInt("total")
                );

            }

            // TOTAL PESERTA
            rs =
                    st.executeQuery(
                            "SELECT COUNT(*) AS total FROM peserta"
                    );

            if(rs.next()) {

                lblTotalPeserta.setText(
                        "Total Peserta : "
                                + rs.getInt("total")
                );

            }

            // TOTAL PENDAFTARAN
            rs =
                    st.executeQuery(
                            "SELECT COUNT(*) AS total FROM pendaftaran"
                    );

            if(rs.next()) {

                lblTotalPendaftaran.setText(
                        "Total Pendaftaran : "
                                + rs.getInt("total")
                );

            }

        } catch(Exception e) {

            JOptionPane.showMessageDialog(
                    null,
                    e.getMessage()
            );

        }

    }

}