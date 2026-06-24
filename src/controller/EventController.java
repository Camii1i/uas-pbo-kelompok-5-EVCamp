package controller;

import database.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class EventController {

    public void tambahEvent(String nama,
            String tanggal,
            String lokasi) {

        try {

            Connection con = Koneksi.getConnection();

            String sql =
                    "INSERT INTO event(nama_event,tanggal,lokasi)"
                    + " VALUES(?,?,?)";

            PreparedStatement pst =
                    con.prepareStatement(sql);

            pst.setString(1, nama);
            pst.setString(2, tanggal);
            pst.setString(3, lokasi);

            pst.executeUpdate();

            System.out.println("Data Event Berhasil Ditambahkan");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}