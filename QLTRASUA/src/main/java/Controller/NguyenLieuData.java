/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.QLNguyenLieu;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import frmView.frmQuanLyNguyenLieu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
 * @author Administrator
 */
public class NguyenLieuData {
    //<editor-fold defaultstate="collapsed" desc="Var">
    private frmQuanLyNguyenLieu frm = new frmQuanLyNguyenLieu();
    private ArrayList<QLNguyenLieu> arr = new ArrayList();
    private QLNguyenLieu nl;
    //</editor-fold>
    public NguyenLieuData() throws IOException, ParseException {
        createArr();
        frm.loadTable(arr);
        frm.addListener(new AddListener());
        frm.delListener(new DelListener());
        frm.editListener(new EditListener());
        frm.searchListener(new SearchListener());
        frm.setVisible(true);
    }
    //<editor-fold defaultstate="collapsed" desc="Method">
    public void thongBao(CloseableHttpResponse response) throws IOException, ParseException{
        if(response.toString().contains("200")){
            HttpEntity entity = response.getEntity();
            String r = EntityUtils.toString(entity, Charset.defaultCharset());
            switch(r){
                case "0" -> {
                    JOptionPane.showMessageDialog(null, "Ðã xóa thành công!", "Thông báo", 1);
                    break;
                }
                case "1" -> {
                    JOptionPane.showMessageDialog(null, "Ðã thêm nguyên liệu thành công!", "Thông báo", 1);
                    break;
                }
                case "2" -> {
                    JOptionPane.showMessageDialog(null, "Ðã sửa thành công!", "Thông báo", 1);
                    break;
                }
                default -> {
                    JOptionPane.showMessageDialog(null, r, "Thông báo", 1);
                    break;
                }
            }
            createArr();
            frm.loadTable(arr);
        } else {
            JOptionPane.showMessageDialog(null, response.toString(), "Thông báo", 1);
        }
        
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Event">
    class AddListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                nl = frm.getInfo();
                if(nl != null){
                    CloseableHttpClient client = HttpClients.createDefault();
                    HttpPost httpP = new HttpPost("http://localhost:4567/nguyen_lieu/them");
                    ArrayList<NameValuePair> params = new ArrayList<>();
                    params.add(new BasicNameValuePair("TenNL", nl.getTenNL()));
                    String strDate = nl.getNgayNhap().toString();
                    params.add(new BasicNameValuePair("NgayNhap", strDate));
                    params.add(new BasicNameValuePair("SoLuong", nl.getSoLuong()));
                    params.add(new BasicNameValuePair("DonVi", nl.getDvTinh()));
                    params.add(new BasicNameValuePair("DonGia", nl.getDonGia()));
                    httpP.setEntity(new UrlEncodedFormEntity(params, Charset.defaultCharset()));
                    CloseableHttpResponse response = client.execute(httpP);
                    thongBao(response);
                }
            } catch (IOException | ParseException ex) {
                JOptionPane.showMessageDialog(null, ex, "Thông báo", 1);
            }
        }
    }
    class DelListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if(JOptionPane.showConfirmDialog(null, "Bạn chắc chắn muốn xóa bản ghi này?")==0){
                    int id = frm.getItem_id();
                    CloseableHttpClient client = HttpClients.createDefault();
                    HttpPost httpP = new HttpPost("http://localhost:4567/nguyen_lieu/xoa");
                    ArrayList<NameValuePair> params = new ArrayList<>();
                    params.add(new BasicNameValuePair("MaNL", arr.get(id).getMaNL()));
                    httpP.setEntity(new UrlEncodedFormEntity(params, Charset.defaultCharset()));
                    CloseableHttpResponse response = client.execute(httpP);
                    thongBao(response);
                }
            } catch (IOException | ParseException ex) {
                JOptionPane.showMessageDialog(null, ex, "Thông báo", 1);
            }
        }
    }
    class EditListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if(frm.editMode()){
                    nl = frm.getInfo();
                    int id = frm.getItem_id();
                    if(nl != null){
                        CloseableHttpClient client = HttpClients.createDefault();
                        HttpPost httpP = new HttpPost("http://localhost:4567/nguyen_lieu/sua");
                        ArrayList<NameValuePair> params = new ArrayList<>();
                        params.add(new BasicNameValuePair("MaNL", nl.getMaNL()));
                        params.add(new BasicNameValuePair("TenNL", nl.getTenNL()));
                        String strDate = nl.getNgayNhap().toString();
                        params.add(new BasicNameValuePair("NgayNhap", strDate));
                        params.add(new BasicNameValuePair("SoLuong", nl.getSoLuong()));
                        params.add(new BasicNameValuePair("DonVi", nl.getDvTinh()));
                        params.add(new BasicNameValuePair("DonGia", nl.getDonGia()));
                        httpP.setEntity(new UrlEncodedFormEntity(params, Charset.defaultCharset()));
                        CloseableHttpResponse response = client.execute(httpP);
                        thongBao(response);
                    }
                }
            } catch (IOException | ParseException ex) {
                JOptionPane.showMessageDialog(null, ex, "Thông báo", 1);
            }
        }
    }
    class SearchListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                CloseableHttpClient client = HttpClients.createDefault();
                HttpPost httpG = new HttpPost("http://localhost:4567/nguyen_lieu/search");
                ArrayList<NameValuePair> params = new ArrayList<>();
                params.add(new BasicNameValuePair("search", frm.getSearch()));
                httpG.setEntity(new UrlEncodedFormEntity(params, Charset.defaultCharset()));
                CloseableHttpResponse response = client.execute(httpG);
                HttpEntity entity = response.getEntity();
                String responseString = EntityUtils.toString(entity, Charset.defaultCharset());
                Gson gson = new Gson();
                Type type = new TypeToken<ArrayList<QLNguyenLieu>>(){}.getType();
                arr = gson.fromJson(responseString, type);
                frm.loadTable(arr);
            } catch (JsonSyntaxException | IOException | ParseException ex) {
                JOptionPane.showMessageDialog(null, ex, "Thông báo", 1);
            }
        }
    }
    //</editor-fold>
    private void createArr() throws IOException, ParseException{
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpG = new HttpGet("http://localhost:4567/nguyen_lieu");
        CloseableHttpResponse response = client.execute(httpG);
        HttpEntity entity = response.getEntity();
        String responseString = EntityUtils.toString(entity, Charset.defaultCharset());
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<QLNguyenLieu>>(){}.getType();
        arr = gson.fromJson(responseString, type);
    }
}
