package com.ldt.sinhviencrud_firebase;

public class SinhVien {
    private String msv, hoTen, lhp;
    private Double dtb;

    public SinhVien() {
    }

    public SinhVien(String msv, String hoTen, String lhp, Double dtb) {
        this.msv = msv;
        this.hoTen = hoTen;
        this.lhp = lhp;
        this.dtb = dtb;
    }

    public String getMsv() {
        return msv;
    }

    public void setMsv(String msv) {
        this.msv = msv;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getLhp() {
        return lhp;
    }

    public void setLhp(String lhp) {
        this.lhp = lhp;
    }

    public Double getDtb() {
        return dtb;
    }

    public void setDtb(Double dtb) {
        this.dtb = dtb;
    }
}
