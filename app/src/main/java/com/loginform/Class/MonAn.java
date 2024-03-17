package com.loginform.Class;

public class MonAn {
    public int maMA,maQA;
    public String tenMA,gia;

    public int getMaMA() {
        return maMA;
    }

    public void setMaMA(int maMA) {
        this.maMA = maMA;
    }

    public int getMaQA() {
        return maQA;
    }

    public void setMaQA(int maQA) {
        this.maQA = maQA;
    }

    public String getTenMA() {
        return tenMA;
    }

    public void setTenMA(String tenMA) {
        this.tenMA = tenMA;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }

    public byte[] getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(byte[] hinhAnh) {
        HinhAnh = hinhAnh;
    }

    public byte [] HinhAnh;

    public MonAn(int maMA, int maQA, String tenMA, String gia, byte[] hinhAnh) {
        this.maMA = maMA;
        this.maQA = maQA;
        this.tenMA = tenMA;
        this.gia = gia;
        HinhAnh = hinhAnh;
    }
}
