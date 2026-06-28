package view;

import database.Koneksi;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class LaporanForm extends JFrame {

    JTable tabel;
    DefaultTableModel model;

    public LaporanForm() {

        setTitle("Laporan Event");
        setSize(700,400);
        setLayout(null);

        String[] kolom = {
                "ID",
                "Nama Event",
                "Tanggal",
                "Lokasi",
                "Jenis"
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
                20,
                640,
                300
        );

        add(scroll);

        loadData();

        setLocationRelativeTo(null);
    }

    private void loadData() {

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
                                rs.getString("lokasi"),
                                rs.getString("jenis_event")
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