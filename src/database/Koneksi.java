package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class Koneksi {

    private static Connection koneksi;

    public static Connection getConnection() {

        try {

            String url = "jdbc:mysql://localhost:3306/evcamp";
            String user = "root";
            String password = "";

            koneksi = DriverManager.getConnection(
                    url,
                    user,
                    password
            );

            System.out.println("Koneksi Berhasil");

        } catch (Exception e) {

            System.out.println("Koneksi Gagal");
            System.out.println(e.getMessage());

        }

        return koneksi;
    }
}