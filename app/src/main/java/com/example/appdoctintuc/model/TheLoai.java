package com.example.appdoctintuc.model;

import java.io.Serializable;

public class TheLoai implements Serializable {
    private int idLoai;
    private String tenTheLoai;

    public TheLoai() {
    }

    public TheLoai(int idLoai, String tenTheLoai) {
        this.idLoai = idLoai;
        this.tenTheLoai = tenTheLoai;
    }

    public int getIdLoai() {
        return idLoai;
    }

    public void setIdLoai(int idLoai) {
        this.idLoai = idLoai;
    }

    public String getTenTheLoai() {
        return tenTheLoai;
    }

    public void setTenTheLoai(String tenTheLoai) {
        this.tenTheLoai = tenTheLoai;
    }
}
