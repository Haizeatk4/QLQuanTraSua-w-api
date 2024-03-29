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
public class orderDV {
    private String MaHD;
    private String MaMon;
    private String MaNhanVien;
    private String TenMon;
    private String SoLuong;
    private String DonGia;

    public String getMaHD() {
        return MaHD;
    }

    public String getMaMon() {
        return MaMon;
    }

    public String getMaNhanVien() {
        return MaNhanVien;
    }

    public String getTenMon() {
        return TenMon;
    }

    public String getSoLuong() {
        return SoLuong;
    }

    public String getDonGia() {
        return DonGia;
    }

    public void setMaHD(String MaHD) {
        this.MaHD = MaHD;
    }

    public void setMaMon(String MaMon) {
        this.MaMon = MaMon;
    }

    public void setMaNhanVien(String MaNhanVien) {
        this.MaNhanVien = MaNhanVien;
    }

    public void setTenMon(String TenMon) {
        this.TenMon = TenMon;
    }

    public void setSoLuong(String SoLuong) {
        this.SoLuong = SoLuong;
    }

    public void setDonGia(String DonGia) {
        this.DonGia = DonGia;
    }

    public orderDV(String MaHD, String MaMon, String MaNhanVien, String TenMon, String SoLuong, String DonGia) {
        this.MaHD = MaHD;
        this.MaMon = MaMon;
        this.MaNhanVien = MaNhanVien;
        this.TenMon = TenMon;
        this.SoLuong = SoLuong;
        this.DonGia = DonGia;
    }

    public orderDV() {
    }
    
    
    
}
