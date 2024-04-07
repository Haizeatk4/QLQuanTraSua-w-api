/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Menu;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author ad
 */
public class MenuCtrl {
    public static PreparedStatement ps;
    public static ResultSet rs;
    public static Statement st;
    private ArrayList<Menu> arr = new ArrayList();

    public MenuCtrl() {}
    public void updateSoLuongM(String MaDV,int SoLuong) throws SQLException{
        ps = connectDatabase.TaoKetNoi().prepareStatement("SELECT * FROM dichvu WHERE MaDV=?");
        ps.setString(1, MaDV);
        rs = ps.executeQuery();
        rs.next();
        int s = rs.getInt("SoLuong");
        int r = s-SoLuong;
        
        ps = connectDatabase.TaoKetNoi().prepareStatement("UPDATE dichvu SET SoLuong=? where MaDV = ?");
        ps.setInt(1, r);
        ps.setString(2, MaDV);
        ps.executeUpdate();
        ps.close();
    }
    public ArrayList<Menu> createArr() throws SQLException{
        arr = new ArrayList<>();
        ps = connectDatabase.TaoKetNoi().prepareStatement("SELECT * FROM dichvu");
        rs = ps.executeQuery();
        while(rs.next()){
           Menu tmp = new Menu();
           
           tmp.setMaMon(rs.getString("MaDV"));
           tmp.setTenMon(rs.getString("TenDV"));
           tmp.setSoLuong(rs.getString("SoLuong"));
           tmp.setGia(rs.getInt("Gia"));
           tmp.setAnh(rs.getString("Anh"));
           
           arr.add(tmp);
        }
        ps.close();
        return arr;
    }
    public ArrayList<Menu> searchArr(String s) throws SQLException{
        
        arr = new ArrayList<>();
        String sql = "SELECT * FROM dichvu where MaDV like '%"+s+"%'"
                + " or TenDV like '%"+s+"%'"
                + " or Gia like '%"+s+"%'";
        ps = connectDatabase.TaoKetNoi().prepareStatement(sql);
        rs = ps.executeQuery();
        while(rs.next()){
           Menu tmp = new Menu();
           
           tmp.setMaMon(rs.getString("MaDV"));
           tmp.setTenMon(rs.getString("TenDV"));
           tmp.setSoLuong(rs.getString("SoLuong"));
           tmp.setGia(rs.getInt("Gia"));
           tmp.setAnh(rs.getString("Anh"));
           
           arr.add(tmp);
        }
        ps.close();
        return arr;
    }
}
