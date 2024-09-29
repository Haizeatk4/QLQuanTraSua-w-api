/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author ad
 */
public class TaiKhoan {
    String MaNV;
    String HoTen;
    String Password;
    String PhanQuyen;

    public TaiKhoan() {
    }

    public TaiKhoan(String MaNV, String HoTen, String Password, String PhanQuyen) {
        this.MaNV = MaNV;
        this.HoTen = HoTen;
        this.Password = Password;
        this.PhanQuyen = PhanQuyen;
    }
    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String HoTen) {
        this.HoTen = HoTen;
    }

    public String getPhanQuyen() {
        return PhanQuyen;
    }

    public void setPhanQuyen(String PhanQuyen) {
        this.PhanQuyen = PhanQuyen;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }
    
}
