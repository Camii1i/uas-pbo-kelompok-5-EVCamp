package view;

import database.Koneksi;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class PendaftaranForm extends JFrame {

    JComboBox<String> cmbPeserta;
    JComboBox<String> cmbEvent;

    JTable tabel;
    DefaultTableModel model;

    public PendaftaranForm() {

        setTitle("Pendaftaran Event");
        setSize(800,500);
        setLayout(null);

        JLabel lblPeserta =
                new JLabel("Peserta");
        lblPeserta.setBounds(
                20,20,100,25
        );

        cmbPeserta =
                new JComboBox<>();
        cmbPeserta.setBounds(
                120,20,250,25
        );

        JLabel lblEvent =
                new JLabel("Event");
        lblEvent.setBounds(
                20,60,100,25
        );

        cmbEvent =
                new JComboBox<>();
        cmbEvent.setBounds(
                120,60,250,25
        );

        JButton btnDaftar =
                new JButton("Daftarkan");

        btnDaftar.setBounds(
                120,100,120,30
        );

        String[] kolom = {
                "ID Daftar",
                "Nama Peserta",
                "Nama Event"
        };

        model =
                new DefaultTableModel(
                        kolom,0
                );

        tabel =
                new JTable(model);

        JScrollPane scroll =
                new JScrollPane(tabel);

        scroll.setBounds(
                20,
                160,
                740,
                250
        );

        btnDaftar.addActionListener(e -> {

            try {

                Connection con =
                        Koneksi.getConnection();

                int idPeserta =
                        Integer.parseInt(
                                cmbPeserta.getSelectedItem()
                                        .toString()
                                        .split(" - ")[0]
                        );

                int idEvent =
                        Integer.parseInt(
                                cmbEvent.getSelectedItem()
                                        .toString()
                                        .split(" - ")[0]
                        );

                String sql =
                        "INSERT INTO pendaftaran(id_peserta,id_event) VALUES(?,?)";

                PreparedStatement ps =
                        con.prepareStatement(sql);

                ps.setInt(1,idPeserta);
                ps.setInt(2,idEvent);

                ps.executeUpdate();

                JOptionPane.showMessageDialog(
                        null,
                        "Peserta berhasil didaftarkan!"
                );

                loadData();

            } catch(Exception ex){

                JOptionPane.showMessageDialog(
                        null,
                        ex.getMessage()
                );

            }

        });

        add(lblPeserta);
        add(cmbPeserta);

        add(lblEvent);
        add(cmbEvent);

        add(btnDaftar);

        add(scroll);

        loadPeserta();
        loadEvent();
        loadData();

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );
    }

    private void loadPeserta() {

        cmbPeserta.removeAllItems();

        try {

            Connection con =
                    Koneksi.getConnection();

            Statement st =
                    con.createStatement();

            ResultSet rs =
                    st.executeQuery(
                            "SELECT * FROM peserta"
                    );

            while(rs.next()) {

                cmbPeserta.addItem(
                        rs.getInt("id_peserta")
                        + " - "
                        + rs.getString("nama_peserta")
                );

            }

        } catch(Exception e){

            JOptionPane.showMessageDialog(
                    null,
                    e.getMessage()
            );

        }

    }

    private void loadEvent() {

        cmbEvent.removeAllItems();

        try {

            Connection con =
                    Koneksi.getConnection();

            Statement st =
                    con.createStatement();

            ResultSet rs =
                    st.executeQuery(
                            "SELECT * FROM event"
                    );

            while(rs.next()) {

                cmbEvent.addItem(
                        rs.getInt("id_event")
                        + " - "
                        + rs.getString("nama_event")
                );

            }

        } catch(Exception e){

            JOptionPane.showMessageDialog(
                    null,
                    e.getMessage()
            );

        }

    }

    private void loadData() {

        model.setRowCount(0);

        try {

            Connection con =
                    Koneksi.getConnection();

            String sql =
                    "SELECT p.id_daftar, " +
                    "ps.nama_peserta, " +
                    "e.nama_event " +
                    "FROM pendaftaran p " +
                    "JOIN peserta ps " +
                    "ON p.id_peserta = ps.id_peserta " +
                    "JOIN event e " +
                    "ON p.id_event = e.id_event";

            Statement st =
                    con.createStatement();

            ResultSet rs =
                    st.executeQuery(sql);

            while(rs.next()) {

                model.addRow(
                        new Object[]{
                                rs.getInt("id_daftar"),
                                rs.getString("nama_peserta"),
                                rs.getString("nama_event")
                        }
                );

            }

        } catch(Exception e){

            JOptionPane.showMessageDialog(
                    null,
                    e.getMessage()
            );

        }

    }

}