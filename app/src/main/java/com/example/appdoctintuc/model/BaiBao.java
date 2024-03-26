package com.example.appdoctintuc.model;

import java.io.Serializable;

public class BaiBao implements Serializable {

    private int idBaiBao;
    private String tenBaiBao;
    private String ndBaiBao;
    private int idLoai;
    private byte[] anhBaiBao;

    public BaiBao(int idBaiBao, String tenBaiBao, String ndBaiBao, int idLoai, byte[] anhBaiBao) {
        this.idBaiBao = idBaiBao;
        this.tenBaiBao = tenBaiBao;
        this.ndBaiBao = ndBaiBao;
        this.idLoai = idLoai;
        this.anhBaiBao = anhBaiBao;
    }

    public int getIdBaiBao() {
        return idBaiBao;
    }

    public void setIdBaiBao(int idBaiBao) {
        this.idBaiBao = idBaiBao;
    }

    public String getTenBaiBao() {
        return tenBaiBao;
    }

    public void setTenBaiBao(String tenBaiBao) {
        this.tenBaiBao = tenBaiBao;
    }

    public String getNdBaiBao() {
        return ndBaiBao;
    }

    public void setNdBaiBao(String ndBaiBao) {
        this.ndBaiBao = ndBaiBao;
    }

    public int getIdLoai() {
        return idLoai;
    }

    public void setIdLoai(int idLoai) {
        this.idLoai = idLoai;
    }

    public byte[] getAnhBaiBao() {
        return anhBaiBao;
    }

    public void setAnhBaiBao(byte[] anhBaiBao) {
        this.anhBaiBao = anhBaiBao;
    }
}
