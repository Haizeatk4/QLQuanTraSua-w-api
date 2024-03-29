/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.QLNguyenLieu;
import Model.QLThongKe;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrator
 */
public class NguyenLieuData {

    public static PreparedStatement ps;
    public static ResultSet rs;
    public static Statement st;
    public static char[] nb = {'+', '&', '*', '#', '!', '.', '@', '$', '^', '(', ')', '/', ';', ':', '|', '='};

    public static void InsertNguyenLieu(QLNguyenLieu nl) {
        String sql = "INSERT INTO NguyenLieu VALUES(?,?,?,?,?,?)";
        try {
            ps = connectDatabase.TaoKetNoi().prepareStatement(sql);
            ps.setString(1, nl.getMaNL());
            ps.setString(2, nl.getTenNL());
            ps.setDate(3, new java.sql.Date(nl.getNgayNhap().getYear(), nl.getNgayNhap().getMonth(), nl.getNgayNhap().getDate()));
            ps.setString(4, nl.getSoLuong());
            ps.setString(5, nl.getDvTinh());
            ps.setString(6, nl.getDonGia());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Ðã thêm nguyên liệu thành công!", "Thông báo", 1);
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
            //JOptionPane.showMessageDialog(null, "Khách hàng không du?c thêm", "Thông báo", 1);
        }

    }
    public static void InsertTienNL(QLThongKe nl){
        String sql = "INSERT INTO ThongKe VALUES (?)";
        
        try {
            ps = connectDatabase.TaoKetNoi().prepareStatement(sql);
            ps.setString(1, nl.getTongTienNL());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Đã thanh toán xong tiền nguyên liệu cho NCC");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static boolean UpdateNguyenLieu(QLNguyenLieu nl) {
        try {
            ps = connectDatabase.TaoKetNoi().prepareStatement("UPDATE NguyenLieu SET TenNL = ?,"
                    + "NgayNhap = ?,SoLuong=?,DvTinh=?,DonGia=? where MaNL = ?");
            ps.setString(1, nl.getTenNL());
            ps.setDate(2, nl.getNgayNhap());
            ps.setString(3, nl.getSoLuong());
            ps.setString(4, nl.getDvTinh());
            ps.setString(5, nl.getDonGia());
            ps.setString(6, nl.getMaNL());
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public static boolean DeleteNguyenLieu(String MaNL) {
        try {
            ps = connectDatabase.TaoKetNoi().prepareStatement("DELETE FROM NguyenLieu WHERE MaNL = ?");
            ps.setString(1, MaNL);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String taomaNguyenLieu() throws SQLException, ClassNotFoundException {
        Connection conn;
        Statement stmt;
        conn = connectDatabase.TaoKetNoi();
        String sql = "SELECT MaNL FROM NguyenLieu order by MaNL Desc";
        stmt = conn.createStatement();
        String manv;
        rs = stmt.executeQuery(sql);
        rs.next();
        manv = rs.getString("MaNL").trim();
        stmt.close();
        conn.close();

        if ((Integer.valueOf(manv.substring(3)) + 1) < 10) {
            manv = "NL00" + (Integer.valueOf(manv.substring(3)) + 1);
        } else if (((Integer.valueOf(manv.substring(3)) + 1) >= 10) && ((Integer.valueOf(manv.substring(3)) + 1) < 100)) {
            manv = "NL0" + (Integer.valueOf(manv.substring(3)) + 1);
        } else {
            manv = "NL" + (Integer.valueOf(manv.substring(3)) + 1);
        }
        return manv;
    }
    
//    public static String convertStringtoDate(Date indate){
//        String dateString = null;
//        SimpleDateFormat datefm = new SimpleDateFormat("dd-MM-yyyy");
//        dateString = datefm.format(indate);
//    }
    
//    public static float TinhTongTien() throws SQLException, ClassNotFoundException{
//        Connection conn;
//        Statement stmt;
//        conn = connectDatabase.TaoKetNoi();
//        String sqltt = "SELECT DonGia, SoLuong FROM NguyenLieu";
//        stmt = conn.createStatement();
//        int tt;
//        rs = stmt.executeQuery(sqltt);
//        rs.next();
//        tt = rs.getString(sqltt)
//        return tt;
//    }

//    public static Boolean checkNumber(String nb){
//        boolean b = false;
//        char a[] = nb.toCharArray();
//        for (int i = 0 ; i<a.length ; i++){
//            for (int j = 0; j< nb.length() ; j++){
//                if(a[i] == nb[j])
//                b = true;
//            }
//        }
//        return b;
//    }
}
