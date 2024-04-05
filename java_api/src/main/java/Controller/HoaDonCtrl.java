/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import Model.HoaDon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class HoaDonCtrl {

    public static PreparedStatement ps;
    public ResultSet rs;
    public static Statement st;
    private static connectDatabase DBCtrl = new connectDatabase();
    private static Connection conn = DBCtrl.TaoKetNoi();
    private ArrayList<HoaDon> arr = new ArrayList();

    public HoaDonCtrl() {
    }
    public ArrayList<HoaDon> createArr() throws SQLException{
        arr = new ArrayList<>();
        ps = connectDatabase.TaoKetNoi().prepareStatement("SELECT MaHD,TenNhanVien,Ngay,MaBan,ThanhTien,TinhTrang FROM hoadon,qlnhan_vien WHERE qlnhan_vien.MaNhanVien=hoadon.MaNhanVien");
        rs = ps.executeQuery();
        while(rs.next()){
           HoaDon tmp = new HoaDon();
           
           tmp.setMaHD(rs.getString("MaHD"));
           tmp.setTenNhanVien(rs.getString("TenNhanVien"));
           tmp.setNgayLap(rs.getDate("Ngay"));
           tmp.setMaBan(rs.getString("MaBan"));
           tmp.setThanhTien(rs.getString("ThanhTien"));
           tmp.setTinhTrang(rs.getString("TinhTrang"));
           
           arr.add(tmp);
        }
        return arr;
    }
    public ArrayList<HoaDon> searchArr(String s) throws SQLException{
        
        arr = new ArrayList<>();
        String sql = "SELECT MaHD,TenNhanVien,Ngay,MaBan,ThanhTien FROM hoadon,qlnhan_vien WHERE qlnhan_vien.MaNhanVien=hoadon.MaNhanVien "
                + "and (MaHD like '%"+s+"%'"
                + " or TenNhanVien like '%"+s+"%'"
                + " or Ngay like '%"+s+"%'"
                + " or MaBan like '%"+s+"%'"
                + " or ThanhTien like '%"+s+"%')";
        ps = connectDatabase.TaoKetNoi().prepareStatement(sql);
        rs = ps.executeQuery();
        while(rs.next()){
           HoaDon tmp = new HoaDon();
           tmp.setMaHD(rs.getString("MaHD"));
           tmp.setTenNhanVien(rs.getString("TenNhanVien"));
           tmp.setNgayLap(rs.getDate("Ngay"));
           tmp.setMaBan(rs.getString("MaBan"));
           tmp.setThanhTien(rs.getString("ThanhTien"));
           arr.add(tmp);
        }
        return arr;
    }
    public static boolean UpdateHoaDon(HoaDon nv) {
        String sql = "UPDATE HoaDon SET ThanhTien = ? where MaHD = ?";
        try {
            ps = conn.prepareStatement(sql);;
            ps.setString(1, nv.getThanhTien());
            ps.setString(2, nv.getMaHD());
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (SQLException e) {
            return false;
        }

    }
//    public static void InsertHoaDon(QLHoaDon nl) {
//        String sql = "INSERT INTO HoaDon VALUES(?,?,?,?,?)";
//        try {
//            ps = conn.prepareStatement(sql);
//            ps.setString(1, nl.getMaHD());
//            ps.setString(2, nl.getMaNhanVien());
//            ps.setString(3, nl.getNgayLap());
//            ps.setString(4, nl.getMaBan());
//            ps.setString(5, nl.getThanhTien());
//            ps.execute();
//            JOptionPane.showMessageDialog(null, "Ðã thêm nguyên liệu thành công!", "Thông báo", 1);
//            ps.close();
//        } catch (HeadlessException | SQLException e) {
//        }
//
//    }
    

    public static boolean DeleteHoaDon(String MaHD) {
        try {
            ps = conn.prepareStatement("DELETE FROM HoaDon WHERE MaHD = ?");
            ps.setString(1, MaHD);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    public String taomaHoaDon() throws SQLException, ClassNotFoundException {
        Connection Nconn;
        Statement stmt;
        Nconn = connectDatabase.TaoKetNoi();
        String sql = "SELECT MaHD FROM HoaDon order by MaHD Desc";
        stmt = Nconn.createStatement();
        String manv;
        rs = stmt.executeQuery(sql);
        rs.next();
        manv = rs.getString("MaHD").trim();
        stmt.close();
        Nconn.close();

        if ((Integer.parseInt(manv.substring(3)) + 1) < 10) {
            manv = "HD00" + (Integer.parseInt(manv.substring(3)) + 1);
        } else if (((Integer.parseInt(manv.substring(3)) + 1) >= 10) && ((Integer.parseInt(manv.substring(3)) + 1) < 100)) {
            manv = "HD0" + (Integer.parseInt(manv.substring(3)) + 1);
        } else {
            manv = "HD" + (Integer.parseInt(manv.substring(3)) + 1);
        }
        return manv;
    }

}
