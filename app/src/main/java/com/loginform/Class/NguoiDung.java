package com.loginform.Class;

public class NguoiDung {
    public int maND;
    public String tenTK;

    public int getMaND() {
        return maND;
    }

    public void setMaND(int maND) {
        this.maND = maND;
    }

    public String getTenTK() {
        return tenTK;
    }

    public void setTenTK(String tenTK) {
        this.tenTK = tenTK;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public NguoiDung(int maND, String tenTK, String matKhau, String hoTen, String sdt) {
        this.maND = maND;
        this.tenTK = tenTK;
        this.matKhau = matKhau;
        this.hoTen = hoTen;
        this.sdt = sdt;
    }

    public String matKhau;
    public String hoTen;
    public String sdt;
}
