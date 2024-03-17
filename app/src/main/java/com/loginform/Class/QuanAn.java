package com.loginform.Class;

public class QuanAn {
    public int getMaQA() {
        return MaQA;
    }

    public void setMaQA(int maQA) {
        MaQA = maQA;
    }

    public String getTenQuan() {
        return TenQuan;
    }

    public void setTenQuan(String tenQuan) {
        TenQuan = tenQuan;
    }

    public byte[] getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(byte[] hinhAnh) {
        HinhAnh = hinhAnh;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public int getSaoDanhGia() {
        return SaoDanhGia;
    }

    public void setSaoDanhGia(int saoDanhGia) {
        SaoDanhGia = saoDanhGia;
    }

    public String getTTGioiThieu() {
        return TTGioiThieu;
    }

    public void setTTGioiThieu(String TTGioiThieu) {
        this.TTGioiThieu = TTGioiThieu;
    }

    public QuanAn(int maQA, String tenQuan, byte[] hinhAnh, String diaChi, String SDT, int saoDanhGia, String TTGioiThieu) {
        MaQA = maQA;
        TenQuan = tenQuan;
        HinhAnh = hinhAnh;
        DiaChi = diaChi;
        this.SDT = SDT;
        SaoDanhGia = saoDanhGia;
        this.TTGioiThieu = TTGioiThieu;
    }

    public int MaQA;
   public String TenQuan;
    public byte [] HinhAnh;


 public String DiaChi;
    public String SDT;
    public int SaoDanhGia;
    public String TTGioiThieu;




    public QuanAn() {
    }
}
