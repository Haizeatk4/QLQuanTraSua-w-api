/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.ChiTietHoaDon;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author ad
 */
public class ChiTietHoaDonCtrl {
    public static PreparedStatement ps;
    public static ResultSet rs;
    public static Statement st;
    private ArrayList<ChiTietHoaDon> arr = new ArrayList();
    
    public ChiTietHoaDonCtrl() {}
    public ArrayList<ChiTietHoaDon> createArr(String MaHD) throws SQLException{
        arr = new ArrayList<>();
        ps = connectDatabase.TaoKetNoi().prepareStatement("select TenDV,chitiethoadon.SoLuong,Gia,DonGia "
                + "from chitiethoadon,nguyenlieu "
                + "where chitiethoadon.MaDV=nguyenlieu.MaDV and MaHD = ?");
        ps.setString(1, MaHD);
        rs = ps.executeQuery();
        while(rs.next()){
           ChiTietHoaDon tmp = new ChiTietHoaDon();
           
           tmp.setTenDV(rs.getString("TenDV"));
           tmp.setSoLuong(rs.getDouble("SoLuong"));
           tmp.setDonGia(rs.getInt("Gia"));
           tmp.setThanhTien(rs.getInt("DonGia"));
           
           arr.add(tmp);
        }
        ps = connectDatabase.TaoKetNoi().prepareStatement("select TenDV,chitiethoadon.SoLuong,Gia,DonGia "
                + "from chitiethoadon,dichvu "
                + "where chitiethoadon.MaDV=dichvu.MaDV and MaHD = ?");
        ps.setString(1, MaHD);
        rs = ps.executeQuery();
        while(rs.next()){
           ChiTietHoaDon tmp = new ChiTietHoaDon();
           
           tmp.setTenDV(rs.getString("TenDV"));
           tmp.setSoLuong(rs.getDouble("SoLuong"));
           tmp.setDonGia(rs.getInt("DonGia"));
           
           arr.add(tmp);
        }
        return arr;
    }
}
