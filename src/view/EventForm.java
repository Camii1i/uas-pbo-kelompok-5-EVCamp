package view;

import controller.EventController;
import database.Koneksi;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class EventForm extends JFrame {

    JTextField txtNama;
    JTextField txtTanggal;
    JTextField txtLokasi;

    JTable tabel;
    DefaultTableModel model;

    EventController controller =
            new EventController();

    public EventForm() {

        setTitle("Data Event");
        setSize(700,500);
        setLayout(null);

        JLabel lblNama =
                new JLabel("Nama Event");
        lblNama.setBounds(20,20,100,25);

        txtNama =
                new JTextField();
        txtNama.setBounds(130,20,200,25);

        JLabel lblTanggal =
                new JLabel("Tanggal");
        lblTanggal.setBounds(20,60,100,25);

        txtTanggal =
                new JTextField();
        txtTanggal.setBounds(130,60,200,25);

        JLabel lblLokasi =
                new JLabel("Lokasi");
        lblLokasi.setBounds(20,100,100,25);

        txtLokasi =
                new JTextField();
        txtLokasi.setBounds(130,100,200,25);

        JButton btnTambah =
                new JButton("Tambah");
        btnTambah.setBounds(20,150,100,30);

        String[] kolom = {
                "ID",
                "Nama Event",
                "Tanggal",
                "Lokasi"
        };

        model =
                new DefaultTableModel(
                        kolom,
                        0
                );

        tabel =
                new JTable(model);

        JScrollPane scroll =
                new JScrollPane(tabel);

        scroll.setBounds(
                20,
                210,
                640,
                200
        );

        btnTambah.addActionListener(e -> {

            controller.tambahEvent(
                    txtNama.getText(),
                    txtTanggal.getText(),
                    txtLokasi.getText()
            );

            loadData();

            txtNama.setText("");
            txtTanggal.setText("");
            txtLokasi.setText("");

        });

        add(lblNama);
        add(txtNama);

        add(lblTanggal);
        add(txtTanggal);

        add(lblLokasi);
        add(txtLokasi);

        add(btnTambah);
        add(scroll);

        loadData();

        setLocationRelativeTo(null);
        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );
    }

    private void loadData() {

        model.setRowCount(0);

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

                model.addRow(
                        new Object[]{
                                rs.getInt("id_event"),
                                rs.getString("nama_event"),
                                rs.getDate("tanggal"),
                                rs.getString("lokasi")
                        }
                );

            }

        } catch(Exception e) {

            System.out.println(
                    e.getMessage()
            );

        }

    }
}