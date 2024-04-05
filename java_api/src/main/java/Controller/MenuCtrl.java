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
        return arr;
    }
}
