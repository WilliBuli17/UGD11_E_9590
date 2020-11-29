package com.pbp.ugd11_e_9590.ui.pdf;

import java.io.Serializable;

public class Buku implements Serializable {

    private final int idBuku;
    private final String namaBuku;
    private final String pengarang;
    private final String gambar;
    private final Double harga;

    public Buku (int idBuku, String namaBuku, String pengarang, Double harga, String gambar) {
        this.idBuku = idBuku;
        this.namaBuku = namaBuku;
        this.pengarang = pengarang;
        this.harga = harga;
        this.gambar = gambar;
    }

    public int getIdBuku() {
        return idBuku;
    }

    public String getNamaBuku() {
        return namaBuku;
    }

    public String getPengarang() {
        return pengarang;
    }

    public String getGambar() {
        return gambar;
    }

    public Double getHarga() {
        return harga;
    }
}
