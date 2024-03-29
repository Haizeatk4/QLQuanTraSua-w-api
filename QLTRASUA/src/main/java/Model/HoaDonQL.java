/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Administrator
 */
public class HoaDonQL {
    private String MaHD;
    private String MaNL;
    private String MaNhanVien;
    private String SoLuong;
    private String DonGia;
    private String ThanhTien;

    public String getMaHD() {
        return MaHD;
    }

    public String getMaNL() {
        return MaNL;
    }

    public String getMaNhanVien() {
        return MaNhanVien;
    }

    public String getSoLuong() {
        return SoLuong;
    }

    public String getDonGia() {
        return DonGia;
    }

    public String getThanhTien() {
        return ThanhTien;
    }

    public void setMaHD(String MaHD) {
        this.MaHD = MaHD;
    }

    public void setMaNL(String MaNL) {
        this.MaNL = MaNL;
    }

    public void setMaNhanVien(String MaNhanVien) {
        this.MaNhanVien = MaNhanVien;
    }

    public void setSoLuong(String SoLuong) {
        this.SoLuong = SoLuong;
    }

    public void setDonGia(String DonGia) {
        this.DonGia = DonGia;
    }

    public void setThanhTien(String ThanhTien) {
        this.ThanhTien = ThanhTien;
    }

    public HoaDonQL(String MaHD, String MaNL, String MaNhanVien, String SoLuong, String DonGia, String ThanhTien) {
        this.MaHD = MaHD;
        this.MaNL = MaNL;
        this.MaNhanVien = MaNhanVien;
        this.SoLuong = SoLuong;
        this.DonGia = DonGia;
        this.ThanhTien = ThanhTien;
    }

    public HoaDonQL() {
    }
    
}
