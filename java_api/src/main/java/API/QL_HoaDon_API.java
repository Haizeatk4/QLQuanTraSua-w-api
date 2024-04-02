/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package API;

import Controller.HoaDonCtrl;
import com.google.gson.Gson;
import static spark.Spark.get;
import static spark.Spark.post;

/**
 *
 * @author ADMIN
 */
public class QL_HoaDon_API {
    public QL_HoaDon_API() {
        get("/hoa_don", (rqst,rspns) -> {
                HoaDonCtrl hd = new HoaDonCtrl();
                rspns.type("application/json");
                return new Gson().toJson(hd.createArr());
        });
        post("/hoa_don/search", (rqst,rspns) -> {
            HoaDonCtrl hd = new HoaDonCtrl();
            String s = rqst.queryParams("search");
            rspns.type("application/json");
            return new Gson().toJson(hd.searchArr(s));
        });
    }
}
