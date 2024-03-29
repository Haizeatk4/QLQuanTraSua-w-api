/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Date;

/**
 *
 * @author Administrator
 */
public class QLNguyenLieu {
    private String MaNL;
    private String TenNL;
    private Date NgayNhap;
    private String SoLuong;
    private String DvTinh;
    private String DonGia;

    public String getMaNL() {
        return MaNL;
    }

    public String getTenNL() {
        return TenNL;
    }

    public Date getNgayNhap() {
        return NgayNhap;
    }

    public String getSoLuong() {
        return SoLuong;
    }

    public String getDvTinh() {
        return DvTinh;
    }

    public String getDonGia() {
        return DonGia;
    }

    public void setMaNL(String MaNL) {
        this.MaNL = MaNL;
    }

    public void setTenNL(String TenNL) {
        this.TenNL = TenNL;
    }

    public void setNgayNhap(Date NgayNhap) {
        this.NgayNhap = NgayNhap;
    }

    public void setSoLuong(String SoLuong) {
        this.SoLuong = SoLuong;
    }

    public void setDvTinh(String DvTinh) {
        this.DvTinh = DvTinh;
    }

    public void setDonGia(String DonGia) {
        this.DonGia = DonGia;
    }

    public QLNguyenLieu(String MaNL, String TenNL, Date NgayNhap, String SoLuong, String DvTinh, String DonGia) {
        this.MaNL = MaNL;
        this.TenNL = TenNL;
        this.NgayNhap = NgayNhap;
        this.SoLuong = SoLuong;
        this.DvTinh = DvTinh;
        this.DonGia = DonGia;
    }

    public QLNguyenLieu() {
    }
    
    
}
