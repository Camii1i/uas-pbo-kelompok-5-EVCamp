package view;

import controller.PesertaController;
import database.Koneksi;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class PesertaForm extends JFrame {

    JTextField txtNama;
    JTextField txtJurusan;

    JTable tabel;
    DefaultTableModel model;

    PesertaController controller =
            new PesertaController();

    public PesertaForm() {

        setTitle("Data Peserta");
        setSize(700,500);
        setLayout(null);

        JLabel lblNama =
                new JLabel("Nama Peserta");
        lblNama.setBounds(20,20,100,25);

        txtNama =
                new JTextField();
        txtNama.setBounds(130,20,200,25);

        JLabel lblJurusan =
                new JLabel("Jurusan");
        lblJurusan.setBounds(20,60,100,25);

        txtJurusan =
                new JTextField();
        txtJurusan.setBounds(130,60,200,25);

        JButton btnTambah =
                new JButton("Tambah");
        btnTambah.setBounds(20,110,100,30);

        JButton btnHapus =
                new JButton("Hapus");
        btnHapus.setBounds(130,110,100,30);

        String[] kolom = {
                "ID",
                "Nama Peserta",
                "Jurusan"
        };

        model =
                new DefaultTableModel(kolom,0);

        tabel =
                new JTable(model);

        JScrollPane scroll =
                new JScrollPane(tabel);

        scroll.setBounds(
                20,
                170,
                640,
                250
        );

        btnTambah.addActionListener(e -> {

            controller.tambahPeserta(
                    txtNama.getText(),
                    txtJurusan.getText()
            );

            loadData();

            txtNama.setText("");
            txtJurusan.setText("");

        });

        btnHapus.addActionListener(e -> {

            int baris = tabel.getSelectedRow();

            if(baris != -1) {
                model.removeRow(baris);
            }

        });

        add(lblNama);
        add(txtNama);

        add(lblJurusan);
        add(txtJurusan);

        add(btnTambah);
        add(btnHapus);

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
                            "SELECT * FROM peserta"
                    );

            while(rs.next()) {

                model.addRow(
                        new Object[]{
                                rs.getInt("id_peserta"),
                                rs.getString("nama"),
                                rs.getString("jurusan")
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