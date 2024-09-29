/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package API;

import Controller.TaiKhoanCtrl;
import Model.TaiKhoan;
import com.google.gson.Gson;
import static spark.Spark.get;
import static spark.Spark.post;

/**
 *
 * @author ad
 */
public class QL_TaiKhoan_API {

    public QL_TaiKhoan_API() {
        get("/tai_khoan", (rqst,rspns) -> {
            TaiKhoanCtrl tk = new TaiKhoanCtrl();
            rspns.type("application/json");
            return new Gson().toJson(tk.createArr());
        });
        post("/tai_khoan/login", (rqst,rspns) -> {
            TaiKhoanCtrl nv = new TaiKhoanCtrl();
            String tk = rqst.queryParams("MaNhanVien");
            String mk = rqst.queryParams("Password");
            rspns.type("application/json");
            return nv.dangNhap(tk, mk);
        });
        post("/tai_khoan/search", (rqst,rspns) -> {
            TaiKhoanCtrl nv = new TaiKhoanCtrl();
            String s = rqst.queryParams("search");
            rspns.type("application/json");
            return new Gson().toJson(nv.searchArr(s));
        });
        post("/tai_khoan/them", (rqst,rspns) -> {
            TaiKhoanCtrl tkCtrl = new TaiKhoanCtrl();
            TaiKhoan tk = new TaiKhoan();
            
            tk.setMaNV(rqst.queryParams("MaNhanVien"));
            
            rspns.type("application/json");
            return tkCtrl.InsertTaiKhoan(tk);
        });
        post("/tai_khoan/xoa", (rqst,rspns) -> {
            TaiKhoanCtrl tkCtrl = new TaiKhoanCtrl();
            String manv = rqst.queryParams("MaNhanVien");
            return tkCtrl.DeleteTaiKhoan(manv);
        });
        post("/tai_khoan/sua", (rqst,rspns) -> {
            TaiKhoanCtrl tkCtrl = new TaiKhoanCtrl();
            TaiKhoan tk = new TaiKhoan();
            
            tk.setMaNV(rqst.queryParams("MaNhanVien"));
            tk.setPassword(rqst.queryParams("Password"));
            tk.setPhanQuyen(rqst.queryParams("PhanQuyen"));
            
            rspns.type("application/json");
            return tkCtrl.UpdateNhanVien(tk);
        });
        post("/tai_khoan/doi_mat_khau", (rqst,rspns) -> {
            TaiKhoanCtrl tkCtrl = new TaiKhoanCtrl();
            String tk = rqst.queryParams("MaNhanVien");
            String mk = rqst.queryParams("Password");
            String newPass = rqst.queryParams("NewPassword");
            
            rspns.type("application/json");
            return tkCtrl.DoiMatKhau(tk,mk,newPass);
        });
    }
    
}
