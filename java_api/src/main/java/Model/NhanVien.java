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
public class NhanVien {
    private String MaNhanVien;
    private String TenNhanVien;
    private String Phone;
    private String Email;
    private String CMND;
    private Date NgayLamViec;

    public NhanVien(String MaNhanVien, String TenNhanVien, String Phone, String Email, String CMND, Date NgayLamViec) {
        this.MaNhanVien = MaNhanVien;
        this.TenNhanVien = TenNhanVien;
        this.Phone = Phone;
        this.Email = Email;
        this.CMND = CMND;
        this.NgayLamViec = NgayLamViec;
    }
    
    public NhanVien() {
    }

    public String getMaNhanVien() {
        return MaNhanVien;
    }

    public void setMaNhanVien(String MaNhanVien) {
        this.MaNhanVien = MaNhanVien;
    }

    public String getTenNhanVien() {
        return TenNhanVien;
    }

    public void setTenNhanVien(String TenNhanVien) {
        this.TenNhanVien = TenNhanVien;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getCMND() {
        return CMND;
    }

    public void setCMND(String CMND) {
        this.CMND = CMND;
    }

    public Date getNgayLamViec() {
        return NgayLamViec;
    }

    public void setNgayLamViec(Date NgayLamViec) {
        this.NgayLamViec = NgayLamViec;
    }
    
    
}
