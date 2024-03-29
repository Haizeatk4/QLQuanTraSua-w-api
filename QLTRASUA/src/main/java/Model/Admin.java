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
public class Admin {
    private String HovaTen;
    private String Password;
    private String NgaySinh;
    private String GioiTinh;
    private String Username;
    private String Email;
    private String DiaChi;
    private String CMND;
    private String SoDienThoai;

    public String getHovaTen() {
        return HovaTen;
    }

    public String getPassword() {
        return Password;
    }

    public String getNgaySinh() {
        return NgaySinh;
    }

    public String getGioiTinh() {
        return GioiTinh;
    }

    public String getUsername() {
        return Username;
    }

    public String getEmail() {
        return Email;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public String getCMND() {
        return CMND;
    }

    public String getSoDienThoai() {
        return SoDienThoai;
    }

    public void setHovaTen(String HovaTen) {
        this.HovaTen = HovaTen;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public void setNgaySinh(String NgaySinh) {
        this.NgaySinh = NgaySinh;
    }

    public void setGioiTinh(String GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public void setCMND(String CMND) {
        this.CMND = CMND;
    }

    public void setSoDienThoai(String SoDienThoai) {
        this.SoDienThoai = SoDienThoai;
    }

    public Admin(String HovaTen, String Password, String NgaySinh, String GioiTinh, String Username, String Email, String DiaChi, String CMND, String SoDienThoai) {
        this.HovaTen = HovaTen;
        this.Password = Password;
        this.NgaySinh = NgaySinh;
        this.GioiTinh = GioiTinh;
        this.Username = Username;
        this.Email = Email;
        this.DiaChi = DiaChi;
        this.CMND = CMND;
        this.SoDienThoai = SoDienThoai;
    }

    public Admin() {
    }
    
}
