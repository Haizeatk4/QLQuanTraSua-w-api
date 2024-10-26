/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import Controller.ExcelFileExporter;
import Controller.TaiKhoanData;
import Model.QLNhanVien;
import frmView.frmHome;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import org.apache.hc.core5.http.ParseException;

/**
 *
 * @author ad
 */
public class frmQLNhanVien extends javax.swing.JFrame implements ActionListener, KeyListener{

    /**
     * Creates new form frmQLNhanVien
     */
    boolean isSelected = false;
    int item_id,mode=0;
    private ArrayList<QLNhanVien> arr = new ArrayList();
    private DefaultTableModel model;
    
    public frmQLNhanVien() {
        initComponents();
        l_acc.setText("Tài khoản: "+TaiKhoanData.user);
        this.model = (DefaultTableModel) td.getModel();
        btn_add.addActionListener(this);
        btn_del.addActionListener(this);
        txt_search.addKeyListener(this);
        btn_save.addActionListener(this);
        //<editor-fold defaultstate="collapsed" desc="Event">
        btn_excel.addActionListener((e) -> {
            String[] headers = new String[] {"Mã nhân viên","Tên nhân viên","SĐT","Email","CCCD","Ngày bắt đầu"};
            String fileName = "Quản lí nhân viên.xlsx";
            ExcelFileExporter excelFileExporter = new ExcelFileExporter();
            excelFileExporter.exportNhanVienExcelFile(arr, headers, fileName);
        });
        final ListSelectionModel sel = td.getSelectionModel();
        sel.addListSelectionListener((ListSelectionEvent e) -> {
            if(!sel.isSelectionEmpty()){
                if(!sel.isSelectionEmpty()){
                    btn_add.setEnabled(false);
                    btn_edit.setEnabled(true);
                    btn_del.setEnabled(true);
                    item_id=sel.getMinSelectionIndex();
                    enableInput(false);
                    isSelected=true;
                    setText();
                } else {
                    isSelected=false;
                }
            }
        });
        mi_exit.addActionListener((e) -> {
            try {
                frmHome home = new frmHome();
                home.setVisible(true);
                dispose();
            } catch (IOException ex) {}
        });
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    frmHome home = new frmHome();
                    home.setVisible(true);
                } catch (IOException ex) {}
            }
        });
        //</editor-fold>
    }
    //<editor-fold defaultstate="collapsed" desc="Event2">
    
    public void addListener (ActionListener log){
        btn_add.addActionListener(log);
    }
    public void saveListener (ActionListener log){
        btn_save.addActionListener(log);
    }
    
    public void delListener (ActionListener log){
        btn_del.addActionListener(log);
    }
    public void searchListener (KeyListener log){
        txt_search.addKeyListener(log);
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Method">
    public String decode(String pass){
        return new String(Base64.getDecoder().decode(pass));
    }
    public String getSearch(){
        String s = txt_search.getText().trim().replace(" ", "_");
        return s;
    }
    public boolean editMode(){
        if(mode == 1){
            mode = 0;
            btn_del.setEnabled(true);
            td.setEnabled(true);
            enableInput(false);
            btn_save.setEnabled(false);
            return true;
        } else {
            mode = 1;
            btn_del.setEnabled(false);
            td.setEnabled(false);
            btn_save.setEnabled(true);
            enableInput(true);
            return false;
        }
    }
    public void enableInput(boolean a){
        txt_tenNV.setEditable(a);
        txt_sdt.setEditable(a);
        txt_email.setEditable(a);
        txt_cccd.setEditable(a);
        dc_workDate.setEnabled(a);
    }
    public void clearMode(){
        clearText();
        mode = 0;
        enableInput(true);
        td.setEnabled(true);
        btn_add.setEnabled(true);
        btn_edit.setEnabled(false);
        btn_del.setEnabled(false);
        td.clearSelection();
    }
    public void clearText(){
        txt_maNV.setText("");
        txt_tenNV.setText("");
        txt_sdt.setText("");
        txt_email.setText("");
        txt_cccd.setText("");
        dc_workDate.setDate(new java.util.Date("01/01/2000"));
    }
    public void setText(){
        txt_maNV.setText(arr.get(item_id).getMaNhanVien());
        txt_tenNV.setText(arr.get(item_id).getTenNhanVien());
        txt_sdt.setText(arr.get(item_id).getPhone());
        txt_email.setText(arr.get(item_id).getEmail());
        txt_cccd.setText(arr.get(item_id).getCMND());
        java.util.Date  utilDate = new java.util.Date(arr.get(item_id).getNgayLamViec().getTime());
        dc_workDate.setDate(utilDate);
    }
    public void loadTable(ArrayList<QLNhanVien> arr){
        int rc = model.getRowCount();
        for(int i=0;i<rc;i++){
            model.removeRow(0);
        }
        Object r[] = new Object[6];
        for(int i=0;i<arr.size();i++){
            r[0] = arr.get(i).getMaNhanVien();
            r[1] = arr.get(i).getTenNhanVien();
            r[2] = arr.get(i).getPhone();
            r[3] = arr.get(i).getEmail();
            r[4] = arr.get(i).getCMND();
            r[5] = arr.get(i).getNgayLamViec();
            model.addRow(r);
        }
        this.arr = arr;
        clearMode();
    }
    public boolean checkBlank(){
        if(txt_tenNV.getText().trim().isEmpty()||txt_sdt.getText().trim().isEmpty()||txt_cccd.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!", "Thông báo", 1);
            return true;
        } else {
            if(txt_sdt.getText().matches("[0-9]+") && txt_cccd.getText().matches("[0-9]+")){
                return false;
            } else {
                JOptionPane.showMessageDialog(null, "Số điện thoại hoặc CCCD không hợp lệ!", "Thông báo", 1);
                return true;
            }
        }
    }

    public int getItem_id() {
        return item_id;
    }
    
    public QLNhanVien getInfo(){
        if(checkBlank()){
            return null;
        } else {
            String a = txt_maNV.getText().trim();
            String b = txt_tenNV.getText().trim();
            String d = txt_sdt.getText().trim();
            String e = txt_email.getText().trim();
            String f = txt_cccd.getText().trim();
            Date g1 = dc_workDate.getDate();
            java.sql.Date g = new java.sql.Date(g1.getTime());
            QLNhanVien nv = new QLNhanVien(a,b,d,e,f,g);
            return nv;
        }
    }
    //</editor-fold>
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        txt_sdt = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txt_maNV = new javax.swing.JTextField();
        txt_cccd = new javax.swing.JTextField();
        txt_tenNV = new javax.swing.JTextField();
        txt_email = new javax.swing.JTextField();
        dc_workDate = new com.toedter.calendar.JDateChooser();
        jPanel2 = new javax.swing.JPanel();
        txt_search = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        td = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btn_add = new javax.swing.JButton();
        btn_edit = new javax.swing.JButton();
        btn_save = new javax.swing.JButton();
        btn_del = new javax.swing.JButton();
        btn_excel = new javax.swing.JButton();
        btn_clear = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        m_hethong = new javax.swing.JMenu();
        mi_qltk = new javax.swing.JMenuItem();
        mi_exit = new javax.swing.JMenuItem();
        l_acc = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Quản Lý Nhân Viên");

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel1.setText("QUẢN LÝ NHÂN VIÊN");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Mã nhân viên:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Tên nhân viên:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Số điện thoại:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Email:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("CCCD:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Ngày vào làm:");

        txt_maNV.setEnabled(false);

        dc_workDate.setBackground(new java.awt.Color(255, 255, 254));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel4))
                        .addGap(22, 22, 22))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_maNV, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
                    .addComponent(txt_sdt)
                    .addComponent(txt_cccd))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txt_tenNV)
                    .addComponent(txt_email, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dc_workDate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_maNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txt_tenNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_sdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_cccd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)
                        .addComponent(jLabel7))
                    .addComponent(dc_workDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        td.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã nhân viên", "Tên nhân viên", "SDT", "Email", "CCCD", "Ngày bắt đầu"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(td);
        if (td.getColumnModel().getColumnCount() > 0) {
            td.getColumnModel().getColumn(0).setPreferredWidth(50);
            td.getColumnModel().getColumn(1).setPreferredWidth(150);
            td.getColumnModel().getColumn(3).setPreferredWidth(150);
        }

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Tìm kiếm:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1029, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_search)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 356, Short.MAX_VALUE)
                .addContainerGap())
        );

        btn_add.setBackground(new java.awt.Color(255, 255, 254));
        btn_add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Button/add_icon.png"))); // NOI18N
        btn_add.setText("Thêm");

        btn_edit.setBackground(new java.awt.Color(255, 255, 254));
        btn_edit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Button/edit_icon.png"))); // NOI18N
        btn_edit.setText("Sửa");
        btn_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editActionPerformed(evt);
            }
        });

        btn_save.setBackground(new java.awt.Color(255, 255, 254));
        btn_save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Button/save_icon.png"))); // NOI18N
        btn_save.setText("Lưu");
        btn_save.setEnabled(false);

        btn_del.setBackground(new java.awt.Color(255, 255, 254));
        btn_del.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Button/delete_icon.png"))); // NOI18N
        btn_del.setText("Xóa");

        btn_excel.setBackground(new java.awt.Color(255, 255, 254));
        btn_excel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Button/excel_icon.png"))); // NOI18N
        btn_excel.setText("Xuất Excel");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_add, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_edit, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_del, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_save, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_excel, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_add)
                .addGap(18, 18, 18)
                .addComponent(btn_edit)
                .addGap(18, 18, 18)
                .addComponent(btn_save)
                .addGap(18, 18, 18)
                .addComponent(btn_del)
                .addGap(18, 18, 18)
                .addComponent(btn_excel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btn_clear.setBackground(new java.awt.Color(255, 255, 254));
        btn_clear.setText("Làm mới");
        btn_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clearActionPerformed(evt);
            }
        });

        jMenuBar1.setBackground(new java.awt.Color(255, 255, 254));

        m_hethong.setText("Hệ thống");

        mi_qltk.setText("Quản lý tài khoản");
        mi_qltk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_qltkActionPerformed(evt);
            }
        });
        m_hethong.add(mi_qltk);

        mi_exit.setText("Thoát");
        m_hethong.add(mi_exit);

        jMenuBar1.add(m_hethong);

        l_acc.setText("Tài khoản:");
        jMenuBar1.add(l_acc);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_clear, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_clear)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editActionPerformed
        editMode();
    }//GEN-LAST:event_btn_editActionPerformed

    private void btn_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clearActionPerformed
        clearMode();
    }//GEN-LAST:event_btn_clearActionPerformed

    private void mi_qltkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mi_qltkActionPerformed
        try {
            TaiKhoanData frm = new TaiKhoanData("qlnv");
        } catch (IOException | ParseException ex) {
            Logger.getLogger(frmQLNhanVien.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_mi_qltkActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmQLNhanVien().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_add;
    private javax.swing.JButton btn_clear;
    private javax.swing.JButton btn_del;
    private javax.swing.JButton btn_edit;
    private javax.swing.JButton btn_excel;
    private javax.swing.JButton btn_save;
    private com.toedter.calendar.JDateChooser dc_workDate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenu l_acc;
    private javax.swing.JMenu m_hethong;
    private javax.swing.JMenuItem mi_exit;
    private javax.swing.JMenuItem mi_qltk;
    private javax.swing.JTable td;
    private javax.swing.JTextField txt_cccd;
    private javax.swing.JTextField txt_email;
    private javax.swing.JTextField txt_maNV;
    private javax.swing.JTextField txt_sdt;
    private javax.swing.JTextField txt_search;
    private javax.swing.JTextField txt_tenNV;
    // End of variables declaration//GEN-END:variables
    
    @Override
    public void actionPerformed(ActionEvent e) {
    }

    /**
     * @return the model
     */
    public DefaultTableModel getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(DefaultTableModel model) {
        this.model = model;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
