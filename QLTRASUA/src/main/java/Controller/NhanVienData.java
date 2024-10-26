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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.sql.SQLException;
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
import view.frmChonNhanVien;
import view.frmQLNhanVien;

/**
 *
 * @author Administrator
 */
public class NhanVienData {

    //<editor-fold defaultstate="collapsed" desc="Var">
    frmQLNhanVien frm;
    private ArrayList<QLNhanVien> arr = new ArrayList();
    private QLNhanVien nv;
    frmChonNhanVien frm_chon;
    //</editor-fold>
    public NhanVienData(String l) throws SQLException, IOException, ParseException, URISyntaxException {
        if(l.equals("QL")){
            frm = new frmQLNhanVien();
            createArr();
            frm.loadTable(arr);
            frm.addListener(new AddListener());
            frm.delListener(new DelListener());
            frm.saveListener(new SaveListener());
            frm.searchListener(new SearchListener());
            frm.setVisible(true); 
        } else if(l.equals("Chọn nv")){
            this.frm_chon = new frmChonNhanVien();
            nhanVienKhongTK();
            frm_chon.loadTable(arr);
            frm_chon.addListener(new ChoseListener());
            frm_chon.setVisible(true);
        }
    }
    //<editor-fold defaultstate="collapsed" desc="Event of QLNV">
    class AddListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                nv = frm.getInfo();
                if(nv != null){
                    CloseableHttpClient client = HttpClients.createDefault();
                    HttpPost httpP = new HttpPost("http://localhost:4567/nhan_vien/them");
                    ArrayList<NameValuePair> params = new ArrayList<>();
                    
                    params.add(new BasicNameValuePair("TenNhanVien", nv.getTenNhanVien()));
                    params.add(new BasicNameValuePair("Phone", nv.getPhone()));
                    params.add(new BasicNameValuePair("Email", nv.getEmail()));
                    params.add(new BasicNameValuePair("CMND", nv.getCMND()));
                    String strDate = nv.getNgayLamViec().toString();
                    params.add(new BasicNameValuePair("NgayLamViec", strDate));
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
    class SaveListener implements ActionListener {
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
                        params.add(new BasicNameValuePair("Phone", nv.getPhone()));
                        params.add(new BasicNameValuePair("Email", nv.getEmail()));
                        params.add(new BasicNameValuePair("CMND", nv.getCMND()));
                        String strDate = nv.getNgayLamViec().toString();
                        params.add(new BasicNameValuePair("NgayLamViec", strDate));
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
    class SearchListener implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
        }

        @Override
        public void keyReleased(KeyEvent e) {
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
    //<editor-fold defaultstate="collapsed" desc="Event of Chọn">
    private void nhanVienKhongTK() throws IOException, ParseException{
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpG = new HttpGet("http://localhost:4567/nhan_vien/chua_tai_khoan");
        CloseableHttpResponse response = client.execute(httpG);
        HttpEntity entity = response.getEntity();
        String responseString = EntityUtils.toString(entity, Charset.defaultCharset());
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<QLNhanVien>>(){}.getType();
        arr = gson.fromJson(responseString, type);
    }
    class ChoseListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                QLNhanVien nv = new QLNhanVien();
                nv.setMaNhanVien(frm_chon.getMa());
                if(nv != null){
                    CloseableHttpClient client = HttpClients.createDefault();
                    HttpPost httpP = new HttpPost("http://localhost:4567/tai_khoan/them");
                    ArrayList<NameValuePair> params = new ArrayList<>();
                    
                    params.add(new BasicNameValuePair("MaNhanVien", nv.getMaNhanVien()));
                    httpP.setEntity(new UrlEncodedFormEntity(params, Charset.defaultCharset()));
                    CloseableHttpResponse response = client.execute(httpP);
                    if(response.toString().contains("200")){
                        HttpEntity entity = response.getEntity();
                        String r = EntityUtils.toString(entity, Charset.defaultCharset());
                        JOptionPane.showMessageDialog(null, r, "Thông báo", 1);
                    } else {
                        JOptionPane.showMessageDialog(null, response.toString(), "Thông báo", 1);
                    }
                    TaiKhoanData frm = new TaiKhoanData("qlnv");
                    frm_chon.dispose();
                }
            } catch (IOException | ParseException ex) {
                JOptionPane.showMessageDialog(null, ex, "Thông báo", 1);
            }
        }
    }
    //</editor-fold>
    public void thongBao(CloseableHttpResponse response) throws IOException, ParseException{
        if(response.toString().contains("200")){
            HttpEntity entity = response.getEntity();
            String r = EntityUtils.toString(entity, Charset.defaultCharset());
            JOptionPane.showMessageDialog(null, r, "Thông báo", 1);
            createArr();
            frm.loadTable(arr);
        } else {
            JOptionPane.showMessageDialog(null, response.toString(), "Thông báo", 1);
        }   
    }
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
