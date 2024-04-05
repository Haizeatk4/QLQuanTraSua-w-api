/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package API;

import Controller.ChiTietHoaDonCtrl;
import com.google.gson.Gson;
import static spark.Spark.get;
import static spark.Spark.post;

/**
 *
 * @author ad
 */
public class ChiTietHoaDon_API {

    public ChiTietHoaDon_API() {
        get("/chi_tiet_hoa_don", (rqst,rspns) -> {
            ChiTietHoaDonCtrl nl = new ChiTietHoaDonCtrl();
            String MaHD = rqst.queryParams("MaHD");
            rspns.type("application/json");
            return new Gson().toJson(nl.createArr(MaHD));
        });
    }
}
