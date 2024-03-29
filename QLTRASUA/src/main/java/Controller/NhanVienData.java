/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.QLNhanVien;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import frmView.frmQuanLyNV;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
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
import java.lang.reflect.Type;
import org.apache.hc.client5.http.classic.methods.HttpGet;

/**
 *
 * @author Administrator
 */
public class NhanVienData {

    //<editor-fold defaultstate="collapsed" desc="Var">
    public static PreparedStatement ps;
    frmQuanLyNV frm = new frmQuanLyNV();
    public static ResultSet rs;
    public static Statement st;
    private ArrayList<QLNhanVien> arr = new ArrayList();
    private QLNhanVien nv;
    //</editor-fold>
    public NhanVienData() throws SQLException, IOException, ParseException, URISyntaxException {
        createArr();
        frm.loadTable(arr);
        frm.addListener(new AddListener());
        frm.delListener(new DelListener());
        frm.editListener(new EditListener());
        frm.calListener(new CalListener());
        frm.searchListener(new SearchListener());
        frm.setVisible(true);
    }

    //<editor-fold defaultstate="collapsed" desc="Event">
    class AddListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                nv = frm.getInfo();
                if(nv != null){
                    CloseableHttpClient client = HttpClients.createDefault();
                    HttpPost httpP = new HttpPost("http://localhost:4567/nhan_vien/them");
                    ArrayList<NameValuePair> params = new ArrayList<>();
                    params.add(new BasicNameValuePair("MaNhanVien", nv.getMaNhanVien()));
                    params.add(new BasicNameValuePair("TenNhanVien", nv.getTenNhanVien()));
                    params.add(new BasicNameValuePair("Password", nv.getPassword()));
                    params.add(new BasicNameValuePair("Phone", nv.getPhone()));
                    params.add(new BasicNameValuePair("Email", nv.getEmail()));
                    params.add(new BasicNameValuePair("CMND", nv.getCMND()));
                    String strDate = nv.getNgayLamViec().toString();
                    params.add(new BasicNameValuePair("NgayLamViec", strDate));
                    params.add(new BasicNameValuePair("CaLamViec", nv.getCaLamViec()));
                    params.add(new BasicNameValuePair("LuongCoBan", nv.getLuongCoBan()));
                    params.add(new BasicNameValuePair("HeSoLuong", nv.getHeSoLuong()));
                    params.add(new BasicNameValuePair("TienLuong", nv.getTienLuong()));
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
                    HttpPost httpP = new HttpPost("http://localhost:4567/nhan_vien/xoa");
                    ArrayList<NameValuePair> params = new ArrayList<>();
                    params.add(new BasicNameValuePair("MaNhanVien", arr.get(id).getMaNhanVien()));
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
                    nv = frm.getInfo();
                    int id = frm.getItem_id();
                    if(nv != null){
                        CloseableHttpClient client = HttpClients.createDefault();
                        HttpPost httpP = new HttpPost("http://localhost:4567/nhan_vien/sua");
                        ArrayList<NameValuePair> params = new ArrayList<>();
                        params.add(new BasicNameValuePair("MaNhanVien", nv.getMaNhanVien()));
                        params.add(new BasicNameValuePair("TenNhanVien", nv.getTenNhanVien()));
                        params.add(new BasicNameValuePair("Password", nv.getPassword()));
                        params.add(new BasicNameValuePair("Phone", nv.getPhone()));
                        params.add(new BasicNameValuePair("Email", nv.getEmail()));
                        params.add(new BasicNameValuePair("CMND", nv.getCMND()));
                        String strDate = nv.getNgayLamViec().toString();
                        params.add(new BasicNameValuePair("NgayLamViec", strDate));
                        params.add(new BasicNameValuePair("CaLamViec", nv.getCaLamViec()));
                        params.add(new BasicNameValuePair("LuongCoBan", nv.getLuongCoBan()));
                        params.add(new BasicNameValuePair("HeSoLuong", nv.getHeSoLuong()));
                        if(!nv.getLuongCoBan().equals(arr.get(id).getLuongCoBan()) || !nv.getHeSoLuong().equals(arr.get(id).getHeSoLuong())){
                            params.add(new BasicNameValuePair("TienLuong", "0"));
                        } else {
                            params.add(new BasicNameValuePair("TienLuong", nv.getTienLuong()));
                        }
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
    class CalListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                    nv = frm.getInfo();
                    if(nv != null){
                        CloseableHttpClient client = HttpClients.createDefault();
                        HttpPost httpP = new HttpPost("http://localhost:4567/nhan_vien/sua");
                        ArrayList<NameValuePair> params = new ArrayList<>();
                        params.add(new BasicNameValuePair("MaNhanVien", nv.getMaNhanVien()));
                        params.add(new BasicNameValuePair("TenNhanVien", nv.getTenNhanVien()));
                        params.add(new BasicNameValuePair("Password", nv.getPassword()));
                        params.add(new BasicNameValuePair("Phone", nv.getPhone()));
                        params.add(new BasicNameValuePair("Email", nv.getEmail()));
                        params.add(new BasicNameValuePair("CMND", nv.getCMND()));
                        String strDate = nv.getNgayLamViec().toString();
                        params.add(new BasicNameValuePair("NgayLamViec", strDate));
                        params.add(new BasicNameValuePair("CaLamViec", nv.getCaLamViec()));
                        params.add(new BasicNameValuePair("LuongCoBan", nv.getLuongCoBan()));
                        params.add(new BasicNameValuePair("HeSoLuong", nv.getHeSoLuong()));
                        int lcb = Integer.parseInt(nv.getLuongCoBan());
                        double hsl = Double.parseDouble(nv.getHeSoLuong());
                        params.add(new BasicNameValuePair("TienLuong", Integer.toString((int) (hsl*lcb))));
                        httpP.setEntity(new UrlEncodedFormEntity(params, Charset.defaultCharset()));
                        CloseableHttpResponse response = client.execute(httpP);
                        thongBao(response);
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
                HttpPost httpG = new HttpPost("http://localhost:4567/nhan_vien/search");
                ArrayList<NameValuePair> params = new ArrayList<>();
                params.add(new BasicNameValuePair("search", frm.getSearch()));
                httpG.setEntity(new UrlEncodedFormEntity(params, Charset.defaultCharset()));
                CloseableHttpResponse response = client.execute(httpG);
                HttpEntity entity = response.getEntity();
                String responseString = EntityUtils.toString(entity, Charset.defaultCharset());
                Gson gson = new Gson();
                Type type = new TypeToken<ArrayList<QLNhanVien>>(){}.getType();
                arr = gson.fromJson(responseString, type);
                frm.loadTable(arr);
            } catch (JsonSyntaxException | IOException | ParseException ex) {
                JOptionPane.showMessageDialog(null, ex, "Thông báo", 1);
            }
        }
    }
    //</editor-fold>
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
                    JOptionPane.showMessageDialog(null, "Ðã thêm khách hàng thành công!", "Thông báo", 1);
                    break;
                }
                case "10" -> {
                    JOptionPane.showMessageDialog(null, "Ðã sửa thành công!", "Thông báo", 1);
                    break;
                }
                case "2" -> {
                    JOptionPane.showMessageDialog(null, "Căn cước công dân này đã tồn tại trong hệ thống! Vui lòng nhập lại hoặc xóa bản ghi trước đó.", "Thông báo", 1);
                    break;
                }
                case "3" -> {
                    JOptionPane.showMessageDialog(null, "Email đã tồn tại trong hệ thống! Vui lòng nhập lại hoặc xóa bản ghi trước đó.", "Thông báo", 1);
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
//        CloseableHttpResponse response = client.execute(httpPost);
//        HttpEntity entity = response.getEntity();
//        String responseString = EntityUtils.toString(entity, Charset.defaultCharset());
//        System.out.println(responseString);
    private void createArr() throws IOException, ParseException{
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpG = new HttpGet("http://localhost:4567/nhan_vien");
        CloseableHttpResponse response = client.execute(httpG);
        HttpEntity entity = response.getEntity();
        String responseString = EntityUtils.toString(entity, Charset.defaultCharset());
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<QLNhanVien>>(){}.getType();
        arr = gson.fromJson(responseString, type);
    }
}
