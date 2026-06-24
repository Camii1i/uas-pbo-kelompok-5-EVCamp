package model;

public class Peserta extends User {

    private int idPeserta;
    private String jurusan;

    public Peserta(int idPeserta, String nama, String jurusan) {
        super(nama);
        this.idPeserta = idPeserta;
        this.jurusan = jurusan;
    }

    public int getIdPeserta() {
        return idPeserta;
    }

    public void setIdPeserta(int idPeserta) {
        this.idPeserta = idPeserta;
    }

    public String getJurusan() {
        return jurusan;
    }

    public void setJurusan(String jurusan) {
        this.jurusan = jurusan;
    }
}