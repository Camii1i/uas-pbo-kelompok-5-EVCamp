package controller;

import database.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class PesertaController {

    public void tambahPeserta(String nama,
            String jurusan) {

        try {

            Connection con = Koneksi.getConnection();

            String sql =
                    "INSERT INTO peserta(nama,jurusan)"
                    + " VALUES(?,?)";

            PreparedStatement pst =
                    con.prepareStatement(sql);

            pst.setString(1, nama);
            pst.setString(2, jurusan);

            pst.executeUpdate();

            System.out.println("Peserta Berhasil Ditambahkan");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}