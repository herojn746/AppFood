package com.loginform.Class;

public class BinhLuan {
    public int maDG, saoDG, maQA,maND;
    public String ndDG;
    public byte [] hinhAnh;

    public BinhLuan() {

    }

    public int getMaDG() {
        return maDG;
    }

    public void setMaDG(int maDG) {
        this.maDG = maDG;
    }

    public int getSaoDG() {
        return saoDG;
    }

    public void setSaoDG(int saoDG) {
        this.saoDG = saoDG;
    }

    public int getMaQA() {
        return maQA;
    }

    public void setMaQA(int maQA) {
        this.maQA = maQA;
    }

    public int getMaND() {
        return maND;
    }

    public void setMaND(int maND) {
        this.maND = maND;
    }

    public String getNdDG() {
        return ndDG;
    }

    public void setNdDG(String ndDG) {
        this.ndDG = ndDG;
    }

    public byte[] getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(byte[] hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public BinhLuan(int maDG, int saoDG, int maQA, int maND, String ndDG, byte[] hinhAnh) {
        this.maDG = maDG;
        this.saoDG = saoDG;
        this.maQA = maQA;
        this.maND = maND;
        this.ndDG = ndDG;
        this.hinhAnh = hinhAnh;
    }
}
