package view;

import database.Koneksi;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class PesertaForm extends JFrame {

    JTextField txtId;
    JTextField txtNama;
    JTextField txtEmail;
    JTextField txtNoHp;

    JTable tabel;
    DefaultTableModel model;

    public PesertaForm() {

        setTitle("Data Peserta");
        setSize(800,550);
        setLayout(null);

        JLabel lblId = new JLabel("ID");
        lblId.setBounds(20,20,100,25);

        txtId = new JTextField();
        txtId.setBounds(130,20,200,25);
        txtId.setEditable(false);

        JLabel lblNama = new JLabel("Nama Peserta");
        lblNama.setBounds(20,60,100,25);

        txtNama = new JTextField();
        txtNama.setBounds(130,60,200,25);

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setBounds(20,100,100,25);

        txtEmail = new JTextField();
        txtEmail.setBounds(130,100,200,25);

        JLabel lblNoHp = new JLabel("No HP");
        lblNoHp.setBounds(20,140,100,25);

        txtNoHp = new JTextField();
        txtNoHp.setBounds(130,140,200,25);

        JButton btnTambah = new JButton("Tambah");
        JButton btnUpdate = new JButton("Update");
        JButton btnHapus = new JButton("Hapus");
        JButton btnReset = new JButton("Reset");

        btnTambah.setBounds(20,190,100,30);
        btnUpdate.setBounds(130,190,100,30);
        btnHapus.setBounds(240,190,100,30);
        btnReset.setBounds(350,190,100,30);

        String[] kolom = {
                "ID",
                "Nama Peserta",
                "Email",
                "No HP"
        };

        model = new DefaultTableModel(kolom,0);

        tabel = new JTable(model);

        tabel.addMouseListener(
                new java.awt.event.MouseAdapter() {

                    public void mouseClicked(
                            java.awt.event.MouseEvent evt) {

                        int row =
                                tabel.getSelectedRow();

                        txtId.setText(
                                model.getValueAt(row,0)
                                        .toString()
                        );

                        txtNama.setText(
                                model.getValueAt(row,1)
                                        .toString()
                        );

                        txtEmail.setText(
                                model.getValueAt(row,2)
                                        .toString()
                        );

                        txtNoHp.setText(
                                model.getValueAt(row,3)
                                        .toString()
                        );
                    }
                });

        JScrollPane scroll =
                new JScrollPane(tabel);

        scroll.setBounds(
                20,
                250,
                740,
                220
        );

        // TAMBAH
        btnTambah.addActionListener(e -> {

            try {

                Connection con =
                        Koneksi.getConnection();

                String sql =
                        "INSERT INTO peserta(nama_peserta,email,no_hp) VALUES(?,?,?)";

                PreparedStatement ps =
                        con.prepareStatement(sql);

                ps.setString(
                        1,
                        txtNama.getText()
                );

                ps.setString(
                        2,
                        txtEmail.getText()
                );

                ps.setString(
                        3,
                        txtNoHp.getText()
                );

                ps.executeUpdate();

                JOptionPane.showMessageDialog(
                        null,
                        "Data berhasil ditambahkan"
                );

                loadData();
                resetForm();

            } catch(Exception ex) {

                JOptionPane.showMessageDialog(
                        null,
                        ex.getMessage()
                );

            }

        });

        // UPDATE
        btnUpdate.addActionListener(e -> {

            if(txtId.getText().isEmpty()) {

                JOptionPane.showMessageDialog(
                        null,
                        "Pilih data terlebih dahulu!"
                );

                return;
            }

            try {

                Connection con =
                        Koneksi.getConnection();

                String sql =
                        "UPDATE peserta SET nama_peserta=?, email=?, no_hp=? WHERE id_peserta=?";

                PreparedStatement ps =
                        con.prepareStatement(sql);

                ps.setString(
                        1,
                        txtNama.getText()
                );

                ps.setString(
                        2,
                        txtEmail.getText()
                );

                ps.setString(
                        3,
                        txtNoHp.getText()
                );

                ps.setInt(
                        4,
                        Integer.parseInt(
                                txtId.getText()
                        )
                );

                ps.executeUpdate();

                JOptionPane.showMessageDialog(
                        null,
                        "Data berhasil diupdate"
                );

                loadData();
                resetForm();

            } catch(Exception ex) {

                JOptionPane.showMessageDialog(
                        null,
                        ex.getMessage()
                );

            }

        });

        // HAPUS
        btnHapus.addActionListener(e -> {

            if(txtId.getText().isEmpty()) {

                JOptionPane.showMessageDialog(
                        null,
                        "Pilih data terlebih dahulu!"
                );

                return;
            }

            int pilih =
                    JOptionPane.showConfirmDialog(
                            null,
                            "Yakin ingin menghapus?",
                            "Konfirmasi",
                            JOptionPane.YES_NO_OPTION
                    );

            if(pilih == JOptionPane.YES_OPTION) {

                try {

                    Connection con =
                            Koneksi.getConnection();

                    String sql =
                            "DELETE FROM peserta WHERE id_peserta=?";

                    PreparedStatement ps =
                            con.prepareStatement(sql);

                    ps.setInt(
                            1,
                            Integer.parseInt(
                                    txtId.getText()
                            )
                    );

                    ps.executeUpdate();

                    JOptionPane.showMessageDialog(
                            null,
                            "Data berhasil dihapus"
                    );

                    loadData();
                    resetForm();

                } catch(Exception ex) {

                    JOptionPane.showMessageDialog(
                            null,
                            ex.getMessage()
                    );

                }

            }

        });

        btnReset.addActionListener(
                e -> resetForm()
        );

        add(lblId);
        add(txtId);

        add(lblNama);
        add(txtNama);

        add(lblEmail);
        add(txtEmail);

        add(lblNoHp);
        add(txtNoHp);

        add(btnTambah);
        add(btnUpdate);
        add(btnHapus);
        add(btnReset);

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
                        new Object[] {
                                rs.getInt("id_peserta"),
                                rs.getString("nama_peserta"),
                                rs.getString("email"),
                                rs.getString("no_hp")
                        }
                );

            }

        } catch(Exception e) {

            JOptionPane.showMessageDialog(
                    null,
                    e.getMessage()
            );

        }

    }

    private void resetForm() {

        txtId.setText("");
        txtNama.setText("");
        txtEmail.setText("");
        txtNoHp.setText("");

    }

}