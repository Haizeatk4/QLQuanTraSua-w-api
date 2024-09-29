/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.TaiKhoan;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import frmView.frmDangNhap;
import frmView.frmDoiMK;
import frmView.frmHome;
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
import view.frmQuanLyTaiKhoan;

/**
 *
 * @author ad
 */
public class TaiKhoanData {
    //<editor-fold defaultstate="collapsed" desc="Var">
    frmQuanLyTaiKhoan frmQL;
    frmDangNhap frm_login;
    frmDoiMK frm_doiMK;
    private ArrayList<TaiKhoan> arr = new ArrayList();
    private TaiKhoan tk;
    public static String user;
    public static String phanQuyen;
    //</editor-fold>

    public TaiKhoanData(String l) throws IOException, ParseException {
        if(l.equals("login")){
            this.frm_login = new frmDangNhap();
            frm_login.loginListener(new LoginListener());
            frm_login.setVisible(true);
        } else if (l.contains("qlnv")){
            this.frmQL = new frmQuanLyTaiKhoan();
            createArr();
            frmQL.loadTable(arr);
    //        frmQL.addListener(new AddListener());
            frmQL.delListener(new DelListener());
            frmQL.saveListener(new EditListener());
            frmQL.searchListener(new SearchListener());
            frmQL.setVisible(true);
        } else {
            this.frm_doiMK = new frmDoiMK();
            frm_doiMK.confirmListener(new ConfirmListener());
            frm_doiMK.setVisible(true);
        }
    }
    //<editor-fold defaultstate="collapsed" desc="Event of QL">
    class DelListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if(JOptionPane.showConfirmDialog(null, "Bạn chắc chắn muốn xóa bản ghi này?")==0){
                    int id = frmQL.getItem_id();
                    CloseableHttpClient client = HttpClients.createDefault();
                    HttpPost httpP = new HttpPost("http://localhost:4567/tai_khoan/xoa");
                    ArrayList<NameValuePair> params = new ArrayList<>();
                    params.add(new BasicNameValuePair("MaNhanVien", arr.get(id).getMaNV()));
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
                if(frmQL.editMode()){
                    tk = frmQL.getInfo();
                    int id = frmQL.getItem_id();
                    if(tk != null){
                        CloseableHttpClient client = HttpClients.createDefault();
                        HttpPost httpP = new HttpPost("http://localhost:4567/tai_khoan/sua");
                        ArrayList<NameValuePair> params = new ArrayList<>();
                        params.add(new BasicNameValuePair("MaNhanVien", tk.getMaNV()));
                        params.add(new BasicNameValuePair("Password", tk.getPassword()));
                        params.add(new BasicNameValuePair("PhanQuyen",tk.getPhanQuyen()));
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
                HttpPost httpG = new HttpPost("http://localhost:4567/tai_khoan/search");
                ArrayList<NameValuePair> params = new ArrayList<>();
                params.add(new BasicNameValuePair("search", frmQL.getSearch()));
                httpG.setEntity(new UrlEncodedFormEntity(params, Charset.defaultCharset()));
                CloseableHttpResponse response = client.execute(httpG);
                HttpEntity entity = response.getEntity();
                String responseString = EntityUtils.toString(entity, Charset.defaultCharset());
                Gson gson = new Gson();
                Type type = new TypeToken<ArrayList<TaiKhoan>>(){}.getType();
                arr = gson.fromJson(responseString, type);
                frmQL.loadTable(arr);
            } catch (JsonSyntaxException | IOException | ParseException ex) {
                JOptionPane.showMessageDialog(null, ex, "Thông báo", 1);
            }
        }
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Event of LOGIN">
    class LoginListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String tk = frm_login.getTK();
            user=tk;
            String mk = frm_login.getMK();
            if(tk!=null && mk!=null){
                try {
                    CloseableHttpClient client = HttpClients.createDefault();
                    HttpPost httpP = new HttpPost("http://localhost:4567/tai_khoan/login");
                    ArrayList<NameValuePair> params = new ArrayList<>();
                    params.add(new BasicNameValuePair("MaNhanVien", tk));
                    params.add(new BasicNameValuePair("Password", mk));
                    httpP.setEntity(new UrlEncodedFormEntity(params, Charset.defaultCharset()));
                    CloseableHttpResponse response = client.execute(httpP);
                    HttpEntity entity = response.getEntity();
                    String kq = EntityUtils.toString(entity, Charset.defaultCharset());
                    phanQuyen=kq;
                    if(kq!=null){
                        JOptionPane.showMessageDialog(null, "Đăng nhập thành công", "Thông báo", 1);
                        frmHome home = new frmHome();
                        home.setVisible(true);
                        frm_login.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Tài khoản hoặc mật khẩu không chính xác", "Thông báo", 1);
                    }
                } catch (IOException | ParseException ex) {
                    JOptionPane.showMessageDialog(null, ex, "Thông báo", 1);
                }
            }
        }
    }class ConfirmListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String tk = TaiKhoanData.user;
            String mk = frm_doiMK.getMatKhauCu();
            String mkMoi = frm_doiMK.getMatKhauMoi();
            if(tk!=null && mk!=null && mkMoi!=null){
                try {
                    CloseableHttpClient client = HttpClients.createDefault();
                    HttpPost httpP = new HttpPost("http://localhost:4567/tai_khoan/doi_mat_khau");
                    ArrayList<NameValuePair> params = new ArrayList<>();
                    params.add(new BasicNameValuePair("MaNhanVien", tk));
                    params.add(new BasicNameValuePair("Password", mk));
                    params.add(new BasicNameValuePair("NewPassword", mkMoi));
                    httpP.setEntity(new UrlEncodedFormEntity(params, Charset.defaultCharset()));
                    CloseableHttpResponse response = client.execute(httpP);
                    HttpEntity entity = response.getEntity();
                    String r = EntityUtils.toString(entity, Charset.defaultCharset());
                    if(Boolean.parseBoolean(r)){
                        JOptionPane.showMessageDialog(null, "Đổi mật khẩu thành công", "Thông báo", 1);
                        frmHome home = new frmHome();
                        home.setVisible(true);
                        frm_doiMK.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Mật khẩu cũ không chính xác", "Thông báo", 1);
                    }
                } catch (IOException | ParseException ex) {
                    JOptionPane.showMessageDialog(null, ex, "Thông báo", 1);
                }
            }
        }
    }
    //</editor-fold>
    private void createArr() throws IOException, ParseException{
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpG = new HttpGet("http://localhost:4567/tai_khoan");
        CloseableHttpResponse response = client.execute(httpG);
        HttpEntity entity = response.getEntity();
        String responseString = EntityUtils.toString(entity, Charset.defaultCharset());
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<TaiKhoan>>(){}.getType();
        arr = gson.fromJson(responseString, type);
    }
    public void thongBao(CloseableHttpResponse response) throws IOException, ParseException{
        if(response.toString().contains("200")){
            HttpEntity entity = response.getEntity();
            String r = EntityUtils.toString(entity, Charset.defaultCharset());
            JOptionPane.showMessageDialog(null, r, "Thông báo", 1);
            createArr();
            frmQL.loadTable(arr);
        } else {
            JOptionPane.showMessageDialog(null, response.toString(), "Thông báo", 1);
        }   
    }
}
