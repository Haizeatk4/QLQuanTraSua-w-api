/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package API;

import Controller.MenuCtrl;
import com.google.gson.Gson;
import static spark.Spark.get;

/**
 *
 * @author ad
 */
public class QL_Menu_API {
    public QL_Menu_API() {
        get("/menu", (rqst,rspns) -> {
                MenuCtrl nl = new MenuCtrl();
                rspns.type("application/json");
                return new Gson().toJson(nl.createArr());
        });
    }
}