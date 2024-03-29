/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import Model.HoaDonQL;
import Model.QLNguyenLieu;
import Model.QLNhanVien;
import Model.QLThongKe;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Administrator
 */
public class HoaDonData {

    public static PreparedStatement ps;
    public static ResultSet rs;
    public static Statement st;
    private static connectDatabase DBCtrl = new connectDatabase();
    private static Connection conn = DBCtrl.TaoKetNoi();

    @SuppressWarnings("empty-statement")
    public static boolean UpdateHoaDon(HoaDonQL nv) {
        String sql = "UPDATE HoaDon SET ThanhTien = ? where MaHD = ?";
        try {
            ps = conn.prepareStatement(sql);;
            ps.setString(1, nv.getMaNL());
            ps.setString(2, nv.getMaNhanVien());
            ps.setString(3, nv.getSoLuong());
            ps.setString(4, nv.getDonGia());
            ps.setString(5, nv.getThanhTien());
            ps.setString(6, nv.getMaHD());
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
    public static void InsertHoaDon(HoaDonQL nl) {
        String sql = "INSERT INTO HoaDon VALUES(?,?,?,?,?,?,?)";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, nl.getMaHD());
            ps.setString(2, nl.getMaNL());
            ps.setString(3, nl.getMaNhanVien());
            ps.setString(4, nl.getSoLuong());
            ps.setString(5, nl.getDonGia());
            ps.setString(6, nl.getThanhTien());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Ðã thêm nguyên liệu thành công!", "Thông báo", 1);
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
            //JOptionPane.showMessageDialog(null, "Khách hàng không du?c thêm", "Thông báo", 1);
        }

    }
    

    public static boolean DeleteHoaDon(String MaHD) {
        try {
            ps = conn.prepareStatement("DELETE FROM HoaDon WHERE MaHD = ?");
            ps.setString(1, MaHD);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String taomaHoaDon() throws SQLException, ClassNotFoundException {
        Connection conn;
        Statement stmt;
        conn = DBCtrl.TaoKetNoi();
        String sql = "SELECT MaHD FROM HoaDon order by MaHD Desc";
        stmt = conn.createStatement();
        String manv;
        rs = stmt.executeQuery(sql);
        rs.next();
        manv = rs.getString("MaHD").trim();
        stmt.close();
        conn.close();

        if ((Integer.valueOf(manv.substring(3)) + 1) < 10) {
            manv = "HD00" + (Integer.valueOf(manv.substring(3)) + 1);
        } else if (((Integer.valueOf(manv.substring(3)) + 1) >= 10) && ((Integer.valueOf(manv.substring(3)) + 1) < 100)) {
            manv = "HD0" + (Integer.valueOf(manv.substring(3)) + 1);
        } else {
            manv = "HD" + (Integer.valueOf(manv.substring(3)) + 1);
        }
        return manv;
    }

}
