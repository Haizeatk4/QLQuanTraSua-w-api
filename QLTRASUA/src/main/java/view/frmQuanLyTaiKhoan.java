/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import Controller.TaiKhoanData;
import Model.TaiKhoan;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
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
public class frmQuanLyTaiKhoan extends javax.swing.JFrame implements ActionListener {

    boolean isSelected = false;
    int item_id,mode=0;
    private ArrayList<TaiKhoan> arr = new ArrayList();
    private DefaultTableModel model;
    public frmQuanLyTaiKhoan() {
        initComponents();
        l_acc.setText("Tài khoản: "+TaiKhoanData.user);
        this.model = (DefaultTableModel) td.getModel();
        btn_del.addActionListener(this);
        btn_search.addActionListener(this);
        btn_save.addActionListener(this);
        btn_add.addActionListener(this);
        //<editor-fold defaultstate="collapsed" desc="Event">
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
            dispose();
        });
        //</editor-fold>
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        td = new javax.swing.JTable();
        txt_search = new javax.swing.JTextField();
        btn_search = new javax.swing.JButton();
        btn_add = new javax.swing.JButton();
        btn_edit = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        txt_pass = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cb_phanquyen = new javax.swing.JComboBox<>();
        btn_save = new javax.swing.JButton();
        btn_del = new javax.swing.JButton();
        btn_clear = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        m_hethong = new javax.swing.JMenu();
        mi_exit = new javax.swing.JMenuItem();
        l_acc = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Quản lý tài khoản");

        td.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã Nhân viên", "Họ tên", "Mật khẩu", "Phân quyền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(td);
        if (td.getColumnModel().getColumnCount() > 0) {
            td.getColumnModel().getColumn(0).setMaxWidth(50);
        }

        btn_search.setBackground(new java.awt.Color(255, 255, 254));
        btn_search.setText("Tìm kiếm");

        btn_add.setBackground(new java.awt.Color(255, 255, 254));
        btn_add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Button/add_icon.png"))); // NOI18N
        btn_add.setText("Thêm");
        btn_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addActionPerformed(evt);
            }
        });

        btn_edit.setBackground(new java.awt.Color(255, 255, 254));
        btn_edit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Button/edit_icon.png"))); // NOI18N
        btn_edit.setText("Sửa");
        btn_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txt_pass.setBackground(new java.awt.Color(255, 255, 252));

        jLabel2.setText("Phân quyền");

        jLabel3.setText("Mật khẩu");

        cb_phanquyen.setBackground(new java.awt.Color(255, 255, 254));
        cb_phanquyen.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nhân viên", "Quản lý" }));

        btn_save.setBackground(new java.awt.Color(255, 255, 254));
        btn_save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Button/save_icon.png"))); // NOI18N
        btn_save.setText("Lưu");
        btn_save.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_save, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cb_phanquyen, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_pass, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_pass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_phanquyen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_save)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btn_del.setBackground(new java.awt.Color(255, 255, 254));
        btn_del.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Button/delete_icon.png"))); // NOI18N
        btn_del.setText("Xóa");

        btn_clear.setBackground(new java.awt.Color(255, 255, 254));
        btn_clear.setText("Làm mới");
        btn_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clearActionPerformed(evt);
            }
        });

        jMenuBar1.setBackground(new java.awt.Color(204, 204, 204));

        m_hethong.setBackground(new java.awt.Color(255, 255, 254));
        m_hethong.setText("Hệ thống");

        mi_exit.setText("Thoát");
        m_hethong.add(mi_exit);

        jMenuBar1.add(m_hethong);

        l_acc.setText("tk");
        jMenuBar1.add(l_acc);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_search, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_search))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_edit, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_add, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_del, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btn_clear)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 699, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_search))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_add)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_edit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_del)
                        .addGap(0, 57, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_clear)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editActionPerformed
        editMode();
    }//GEN-LAST:event_btn_editActionPerformed

    private void btn_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addActionPerformed
        try {
            TaiKhoanData frm_chon = new TaiKhoanData("Chọn nv");
            this.dispose();
        } catch (IOException | ParseException ex) {
            Logger.getLogger(frmQuanLyTaiKhoan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_addActionPerformed

    private void btn_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clearActionPerformed
        clearMode();
    }//GEN-LAST:event_btn_clearActionPerformed
    //<editor-fold defaultstate="collapsed" desc="Method">
    public int getItem_id() {
        return item_id;
    }
    public String decode(String pass){
        return new String(Base64.getDecoder().decode(pass));
    }
    public void enableInput(boolean a){
        txt_pass.setEditable(a);
        cb_phanquyen.setEnabled(a);
    }
    public void setText(){
        txt_pass.setText(decode(arr.get(item_id).getPassword()));
        cb_phanquyen.setSelectedItem(arr.get(item_id).getPhanQuyen());
    }
    public void clearText(){
        txt_pass.setText("");
        cb_phanquyen.setSelectedIndex(0);
    }
    public void clearMode(){
        clearText();
        mode = 0;
        btn_edit.setBackground(Color.WHITE);
        enableInput(true);
        td.setEnabled(true);
        btn_add.setEnabled(true);
        btn_edit.setEnabled(false);
        btn_del.setEnabled(false);
        td.clearSelection();
    }
    public void loadTable(ArrayList<TaiKhoan> arr){
        int rc = getModel().getRowCount();
        for(int i=0;i<rc;i++){
            getModel().removeRow(0);
        }
        Object r[] = new Object[5];
        for(int i=0;i<arr.size();i++){
            r[0] = i+1;
            r[1] = arr.get(i).getMaNV();
            r[2] = arr.get(i).getHoTen();
            r[3] = arr.get(i).getPassword();
            r[4] = arr.get(i).getPhanQuyen();
            getModel().addRow(r);
        }
        this.arr = arr;
        clearMode();
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
    public boolean checkBlank(){
        if(txt_pass.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!", "Thông báo", 1);
            return true;
        } else {
            return false;
        }
    }
    public TaiKhoan getInfo(){
        if(checkBlank()){
            return null;
        } else {
            String a = arr.get(item_id).getMaNV();
            String b = arr.get(item_id).getHoTen();
            String c = txt_pass.getText();
            String d = cb_phanquyen.getSelectedItem().toString();
            TaiKhoan nv = new TaiKhoan(a,b,c,d);
            return nv;
        }
    }
    public String getSearch(){
        String s = txt_search.getText().trim().replace(" ", "_");
        return s;
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Event2">
    public void addListener (ActionListener log){
        btn_add.addActionListener(log);
    }
    public void delListener (ActionListener log){
        btn_del.addActionListener(log);
    }
    public void searchListener (ActionListener log){
        btn_search.addActionListener(log);
    }
    public void saveListener (ActionListener log) {
        btn_save.addActionListener(log);
    }
    //</editor-fold>
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmQuanLyTaiKhoan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_add;
    private javax.swing.JButton btn_clear;
    private javax.swing.JButton btn_del;
    private javax.swing.JButton btn_edit;
    private javax.swing.JButton btn_save;
    private javax.swing.JButton btn_search;
    private javax.swing.JComboBox<String> cb_phanquyen;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenu l_acc;
    private javax.swing.JMenu m_hethong;
    private javax.swing.JMenuItem mi_exit;
    private javax.swing.JTable td;
    private javax.swing.JPasswordField txt_pass;
    private javax.swing.JTextField txt_search;
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
}
