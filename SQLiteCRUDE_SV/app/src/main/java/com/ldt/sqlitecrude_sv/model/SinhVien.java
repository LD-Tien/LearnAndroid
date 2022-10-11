package com.ldt.sqlitecrude_sv.model;

public class SinhVien {
    private String id, ten, lop;
    private Double dtb;

    public SinhVien() {
    }

    public SinhVien(String id, String ten, String lop, Double dtb) {
        this.id = id;
        this.ten = ten;
        this.lop = lop;
        this.dtb = dtb;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getLop() {
        return lop;
    }

    public void setLop(String lop) {
        this.lop = lop;
    }

    public Double getDtb() {
        return dtb;
    }

    public void setDtb(Double dtb) {
        this.dtb = dtb;
    }
}
