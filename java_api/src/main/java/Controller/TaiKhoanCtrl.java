/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.TaiKhoan;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;

/**
 *
 * @author ad
 */
public class TaiKhoanCtrl {
    //<editor-fold defaultstate="collapsed" desc="Var">
    public static PreparedStatement ps;
    public static ResultSet rs;
    public static Statement st;
    private ArrayList<TaiKhoan> arr = new ArrayList();
    //</editor-fold>

    public TaiKhoanCtrl() {
    }
    public String encode(String pass){
        String encode = Base64.getEncoder().encodeToString(pass.getBytes());
        return encode;
    }
    public boolean DoiMatKhau(String tk,String mk,String mkMoi) throws SQLException{
            if(dangNhap(tk, mk)!=null){
                ps = connectDatabase.TaoKetNoi().prepareStatement("UPDATE taikhoan SET Password = ? where MaNhanVien = ?");
                ps.setString(1, encode(mkMoi));
                ps.setString(2, tk);
                ps.executeUpdate();
                ps.close();
                return true;
            } else {
                return false;
            }
    }
    public String dangNhap(String taiKhoan, String pass) throws SQLException {
        String kt = null;
        try {
            ps = connectDatabase.TaoKetNoi().prepareStatement("SELECT PhanQuyen FROM taikhoan where MaNhanVien = ? and Password=?");
            ps.setString(1, taiKhoan);
            ps.setString(2, encode(pass));
            rs = ps.executeQuery();
            if (rs.next()) {
                kt = rs.getString("PhanQuyen");
                ps.close();
            }
        } catch (SQLException e) {
            kt = e.getMessage();
        }
        return kt;

    }
    public ArrayList<TaiKhoan> createArr() throws SQLException{
        arr = new ArrayList<>();
        ps = connectDatabase.TaoKetNoi().prepareStatement("select TaiKhoan.MaNhanVien,TenNhanVien,Password,PhanQuyen from nhanvien,taikhoan where taikhoan.MaNhanVien=nhanvien.MaNhanVien");
        rs = ps.executeQuery();
        while(rs.next()){
           TaiKhoan tmp = new TaiKhoan();
           
           tmp.setMaNV(rs.getString("MaNhanVien"));
           tmp.setHoTen(rs.getString("TenNhanVien"));
           tmp.setPassword(rs.getString("Password"));
           tmp.setPhanQuyen(rs.getString("PhanQuyen"));
           
           arr.add(tmp);
        }
        ps.close();
        return arr;
    }
    public ArrayList<TaiKhoan> searchArr(String s) throws SQLException{
        
        arr = new ArrayList<>();
        String sql = "select TaiKhoan.MaNhanVien,TenNhanVien,Password,PhanQuyen from nhanvien,taikhoan where taikhoan.MaNhanVien=nhanvien.MaNhanVien"
                + " and (TaiKhoan.MaNhanVien like '%"+s+"%'"
                + " or TenNhanVien like '%"+s+"%'"
                + " or PhanQuyen like '%"+s+"%')";
        ps = connectDatabase.TaoKetNoi().prepareStatement(sql);
        rs = ps.executeQuery();
        while(rs.next()){
           TaiKhoan tmp = new TaiKhoan();
           
           tmp.setMaNV(rs.getString("MaNhanVien"));
           tmp.setHoTen(rs.getString("TenNhanVien"));
           tmp.setPassword(rs.getString("Password"));
           tmp.setPhanQuyen(rs.getString("PhanQuyen"));
           
           arr.add(tmp);
        }
        ps.close();
        return arr;
    }
    public String InsertTaiKhoan(TaiKhoan tk) throws ClassNotFoundException {
        String sql = "INSERT INTO taikhoan VALUES(null,?,?,?)";
        try {
            ps = connectDatabase.TaoKetNoi().prepareStatement(sql);
            ps.setString(1, tk.getMaNV());
            ps.setString(2, encode("123"));
            ps.setString(3, "Nhân viên");
            ps.execute();
            ps.close();
            return "Ðã thêm thành công!";
        } catch (SQLException e) {
            return e.getMessage();
        }

    }
    public String UpdateNhanVien(TaiKhoan tk) throws ClassNotFoundException {
        try {
            ps = connectDatabase.TaoKetNoi().prepareStatement("UPDATE taikhoan SET `Password` = ?,"
                    + "PhanQuyen=? where MaNhanVien = ?");
            ps.setString(1, encode(tk.getPassword()));
            ps.setString(2, tk.getPhanQuyen());
            ps.setString(3, tk.getMaNV());
            ps.executeUpdate();
            ps.close();
            return "Ðã sửa thành công!";
        } catch (SQLException e) {
            return e.getMessage();
        }

    }
    public String DeleteTaiKhoan(String MaNhanVien) {
        try {
            ps = connectDatabase.TaoKetNoi().prepareStatement("DELETE FROM taikhoan WHERE MaNhanVien = ?");
            ps.setString(1, MaNhanVien);
            ps.executeUpdate();
            ps.close();
            return "Ðã xóa thành công!";
        } catch (SQLException e) {
            return e.getMessage();
        }
    }
}
