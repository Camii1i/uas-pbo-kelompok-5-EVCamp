package view;

import database.Koneksi;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class EventForm extends JFrame {

    JTextField txtId;
    JTextField txtNama;
    JTextField txtTanggal;
    JTextField txtLokasi;

    JComboBox<String> cmbJenis;

    JTable tabel;
    DefaultTableModel model;

    public EventForm() {

        setTitle("Data Event");
        setSize(800, 550);
        setLayout(null);

        JLabel lblId = new JLabel("ID");
        lblId.setBounds(20, 20, 100, 25);

        txtId = new JTextField();
        txtId.setBounds(130, 20, 200, 25);
        txtId.setEditable(false);

        JLabel lblNama = new JLabel("Nama Event");
        lblNama.setBounds(20, 60, 100, 25);

        txtNama = new JTextField();
        txtNama.setBounds(130, 60, 200, 25);

        JLabel lblTanggal = new JLabel("Tanggal");
        lblTanggal.setBounds(20, 100, 100, 25);

        txtTanggal = new JTextField();
        txtTanggal.setBounds(130, 100, 200, 25);

        JLabel lblLokasi = new JLabel("Lokasi");
        lblLokasi.setBounds(20, 140, 100, 25);

        txtLokasi = new JTextField();
        txtLokasi.setBounds(130, 140, 200, 25);

        JLabel lblJenis = new JLabel("Jenis Event");
        lblJenis.setBounds(20, 180, 100, 25);

        cmbJenis = new JComboBox<>();
        cmbJenis.addItem("Seminar");
        cmbJenis.addItem("Workshop");
        cmbJenis.setBounds(130, 180, 200, 25);

        JButton btnTambah = new JButton("Tambah");
        JButton btnUpdate = new JButton("Update");
        JButton btnHapus = new JButton("Hapus");
        JButton btnReset = new JButton("Reset");

        btnTambah.setBounds(20, 230, 100, 30);
        btnUpdate.setBounds(130, 230, 100, 30);
        btnHapus.setBounds(240, 230, 100, 30);
        btnReset.setBounds(350, 230, 100, 30);

        String[] kolom = {
                "ID",
                "Nama Event",
                "Tanggal",
                "Lokasi",
                "Jenis"
        };

        model = new DefaultTableModel(kolom, 0);

        tabel = new JTable(model);

        tabel.addMouseListener(
                new java.awt.event.MouseAdapter() {

                    @Override
                    public void mouseClicked(
                            java.awt.event.MouseEvent evt) {

                        int row = tabel.getSelectedRow();

                        txtId.setText(
                                model.getValueAt(row, 0)
                                        .toString()
                        );

                        txtNama.setText(
                                model.getValueAt(row, 1)
                                        .toString()
                        );

                        txtTanggal.setText(
                                model.getValueAt(row, 2)
                                        .toString()
                        );

                        txtLokasi.setText(
                                model.getValueAt(row, 3)
                                        .toString()
                        );

                        cmbJenis.setSelectedItem(
                                model.getValueAt(row, 4)
                                        .toString()
                        );
                    }
                });

        JScrollPane scroll =
                new JScrollPane(tabel);

        scroll.setBounds(
                20,
                290,
                740,
                180
        );

        // TAMBAH
        btnTambah.addActionListener(e -> {

            if (txtNama.getText().isEmpty()
                    || txtTanggal.getText().isEmpty()
                    || txtLokasi.getText().isEmpty()) {

                JOptionPane.showMessageDialog(
                        null,
                        "Semua data harus diisi!"
                );

                return;
            }

            try {

                Connection con =
                        Koneksi.getConnection();

                String sql =
                        "INSERT INTO event(nama_event,tanggal,lokasi,jenis_event) VALUES(?,?,?,?)";

                PreparedStatement ps =
                        con.prepareStatement(sql);

                ps.setString(
                        1,
                        txtNama.getText()
                );

                ps.setString(
                        2,
                        txtTanggal.getText()
                );

                ps.setString(
                        3,
                        txtLokasi.getText()
                );

                ps.setString(
                        4,
                        cmbJenis.getSelectedItem()
                                .toString()
                );

                ps.executeUpdate();

                JOptionPane.showMessageDialog(
                        null,
                        "Data berhasil ditambahkan"
                );

                loadData();
                resetForm();

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        null,
                        ex.getMessage()
                );

            }

        });

        // UPDATE
        btnUpdate.addActionListener(e -> {

            if (txtId.getText().trim().isEmpty()) {

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
                        "UPDATE event SET nama_event=?, tanggal=?, lokasi=?, jenis_event=? WHERE id_event=?";

                PreparedStatement ps =
                        con.prepareStatement(sql);

                ps.setString(1,
                        txtNama.getText());

                ps.setString(2,
                        txtTanggal.getText());

                ps.setString(3,
                        txtLokasi.getText());

                ps.setString(4,
                        cmbJenis.getSelectedItem()
                                .toString());

                ps.setInt(5,
                        Integer.parseInt(
                                txtId.getText()
                        ));

                ps.executeUpdate();

                JOptionPane.showMessageDialog(
                        null,
                        "Data berhasil diupdate"
                );

                loadData();
                resetForm();

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        null,
                        ex.getMessage()
                );

            }

        });

        // HAPUS
        btnHapus.addActionListener(e -> {

            if (txtId.getText().trim().isEmpty()) {

                JOptionPane.showMessageDialog(
                        null,
                        "Pilih data terlebih dahulu!"
                );

                return;
            }

            int pilih =
                    JOptionPane.showConfirmDialog(
                            null,
                            "Yakin ingin menghapus data?",
                            "Konfirmasi",
                            JOptionPane.YES_NO_OPTION
                    );

            if (pilih == JOptionPane.YES_OPTION) {

                try {

                    Connection con =
                            Koneksi.getConnection();

                    String sql =
                            "DELETE FROM event WHERE id_event=?";

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

                } catch (Exception ex) {

                    JOptionPane.showMessageDialog(
                            null,
                            ex.getMessage()
                    );

                }

            }

        });

        // RESET
        btnReset.addActionListener(
                e -> resetForm()
        );

        add(lblId);
        add(txtId);

        add(lblNama);
        add(txtNama);

        add(lblTanggal);
        add(txtTanggal);

        add(lblLokasi);
        add(txtLokasi);

        add(lblJenis);
        add(cmbJenis);

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
                            "SELECT * FROM event"
                    );

            while (rs.next()) {

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

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    null,
                    e.getMessage()
            );

        }

    }

    private void resetForm() {

        txtId.setText("");
        txtNama.setText("");
        txtTanggal.setText("");
        txtLokasi.setText("");
        cmbJenis.setSelectedIndex(0);

    }
}