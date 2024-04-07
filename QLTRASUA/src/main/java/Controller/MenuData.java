/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.QLMenu;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.message.BasicNameValuePair;

/**
 *
 * @author ad
 */
public class MenuData {
    //<editor-fold defaultstate="collapsed" desc="Var">
    private ArrayList<QLMenu> arr = new ArrayList();
    private QLMenu nl;
    //</editor-fold>
    public MenuData(){
    }
    public MenuData(String l){
    }
    //<editor-fold defaultstate="collapsed" desc="Method">
    public ArrayList<QLMenu> getDV(String s) throws ParseException, IOException{
        try {
            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost httpG = new HttpPost("http://localhost:4567/menu/search");
            ArrayList<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("search", s));
            httpG.setEntity(new UrlEncodedFormEntity(params, Charset.defaultCharset()));
            CloseableHttpResponse response = client.execute(httpG);
            HttpEntity entity = response.getEntity();
            String responseString = EntityUtils.toString(entity, Charset.defaultCharset());
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<QLMenu>>(){}.getType();
            arr = gson.fromJson(responseString, type);
        } catch (JsonSyntaxException | IOException | ParseException ex) {
            JOptionPane.showMessageDialog(null, ex, "Thông báo", 1);
        }
        return arr;
    }
    //</editor-fold>
    private void createArr() throws IOException, ParseException{
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpG = new HttpGet("http://localhost:4567/menu");
        CloseableHttpResponse response = client.execute(httpG);
        HttpEntity entity = response.getEntity();
        String responseString = EntityUtils.toString(entity, Charset.defaultCharset());
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<QLMenu>>(){}.getType();
        arr = gson.fromJson(responseString, type);
    }
}
