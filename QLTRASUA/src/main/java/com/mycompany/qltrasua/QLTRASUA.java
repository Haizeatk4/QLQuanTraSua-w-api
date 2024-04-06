/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.qltrasua;

import Controller.ChiTietHoaDonData;
import Controller.HoaDonData;
import Controller.NhanVienData;
import frmView.frmChiTietHoaDon;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import org.apache.hc.core5.http.ParseException;

/**
 *
 * @author ADMIN
 */
public class QLTRASUA {

    public static void main(String[] args) throws SQLException, IOException, URISyntaxException, InterruptedException, ParseException {
//        NhanVienData frm = new NhanVienData("login");
//        ChiTietHoaDonData frm = new ChiTietHoaDonData("admin","HD001","Phong");    
        HoaDonData hd = new HoaDonData("admin");
    }
}
