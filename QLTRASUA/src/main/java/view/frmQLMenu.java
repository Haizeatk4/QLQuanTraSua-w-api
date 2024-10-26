/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import Controller.TaiKhoanData;
import Model.QLMenu;
import frmView.frmHome;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class frmQLMenu extends javax.swing.JFrame implements ActionListener, KeyListener {
    File anh;
    String projectPath;
    boolean isSelected = false, imgChange=false;
    int item_id,mode=0;
    private ArrayList<QLMenu> arr = new ArrayList();
    private DefaultTableModel model;
    public frmQLMenu() {
        initComponents();
        l_acc.setText("Tài khoản: "+TaiKhoanData.user);
        this.model = (DefaultTableModel) td.getModel();
        btn_add.addActionListener(this);
        btn_save.addActionListener(this);
        btn_del.addActionListener(this);
        txt_search.addKeyListener(this);
        //<editor-fold defaultstate="collapsed" desc="Event">
        //--------------------------Select--------------------------------------
        btn_anh.addActionListener((e) -> {
            JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    // Get the selected file
                    imgChange = true;
                    anh = fileChooser.getSelectedFile();
                }
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
                    setPic();
                } else {
                    isSelected=false;
                    btn_add.setEnabled(true);
                    btn_edit.setEnabled(false);
                    btn_del.setEnabled(false);
                }
            }
        });
        btn_clear.addActionListener((e) -> {
            clearMode();
        });
        mi_exit.addActionListener((e) -> {
           
        });
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
              
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
    public void setPic(){
        try {
            File file = new File("");
            String currentDirectory = file.getAbsolutePath() + "/src/main/java";
            BufferedImage myPicture = ImageIO.read(new File(currentDirectory + arr.get(item_id).getAnh()));
            l_pic.setIcon(new ImageIcon(myPicture.getScaledInstance(200, 200, Image.SCALE_SMOOTH)));
            l_pic.repaint();
        } catch (IOException ex) {
            Logger.getLogger(frmQLMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void saveAnh(){
        try {
            if(imgChange){
                String img = projectPath + "/Image/Menu/";
                Path sourcePath = Paths.get(anh.getAbsolutePath());
                Path targetPath = Paths.get(img + anh.getName());

                Path movedPath = Files.move(sourcePath, targetPath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    public int getItem_id() {
        return item_id;
    }
    public boolean checkBlank(){
        int sl = Integer.parseInt(spr_soLuong.getValue().toString());
        int dg = Integer.parseInt(spr_donGia.getValue().toString());
        if(txt_tenDV.getText().trim().isEmpty()||dg==0||sl==0||(!imgChange&&!isSelected)){
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!", "Thông báo", 1);
            return true;
        } else {
            return false;
        }
    }
    public QLMenu getInfo(){
        if(checkBlank()){
            return null;
        } else {
            String a = txt_maDV.getText();
            String b = txt_tenDV.getText();
            int c = Integer.parseInt(spr_soLuong.getValue().toString());
            int d = Integer.parseInt(spr_donGia.getValue().toString());
            String e;
            if(imgChange){
                e = "/Image/Menu/" + anh.getName();
            } else {
                e = arr.get(item_id).getAnh();
            }
            
            QLMenu nl = new QLMenu(a,b,c,d,e);
            return nl;
        }
    }
    public void setText(){
        txt_maDV.setText(arr.get(item_id).getMaMon());
        txt_tenDV.setText(arr.get(item_id).getTenMon());
        spr_soLuong.setValue(arr.get(item_id).getSoLuong());
        spr_donGia.setValue(Integer.valueOf(arr.get(item_id).getGia()));
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
        imgChange = false;
        isSelected = false;
    }
    public void clearText(){
        txt_maDV.setText("");
        txt_tenDV.setText("");
        spr_soLuong.setValue(0);
        spr_donGia.setValue(0);
    }
    public void enableInput(boolean a){
        txt_tenDV.setEditable(a);
        spr_soLuong.setEnabled(a);
        spr_donGia.setEnabled(a);
        btn_anh.setEnabled(a);
    }
    public void loadTable(ArrayList<QLMenu> arr){
        int rc = model.getRowCount();
        for(int i=0;i<rc;i++){
            model.removeRow(0);
        }
        Object r[] = new Object[5];
        for(int i=0;i<arr.size();i++){
            r[0] = arr.get(i).getMaMon();
            r[1] = arr.get(i).getTenMon();
            r[2] = arr.get(i).getSoLuong();
            r[3] = arr.get(i).getGia();
            r[4] = arr.get(i).getAnh();
            model.addRow(r);
        }
        this.arr = arr;
        clearMode();
    }
    //</editor-fold>
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txt_search = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        td = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_tenDV = new javax.swing.JTextField();
        txt_maDV = new javax.swing.JTextField();
        spr_soLuong = new javax.swing.JSpinner();
        spr_donGia = new javax.swing.JSpinner();
        l_pic = new javax.swing.JLabel();
        btn_add = new javax.swing.JButton();
        btn_edit = new javax.swing.JButton();
        btn_save = new javax.swing.JButton();
        btn_del = new javax.swing.JButton();
        btn_anh = new javax.swing.JButton();
        btn_clear = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        mi_exit = new javax.swing.JMenuItem();
        l_acc = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Quản lý Menu");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 32)); // NOI18N
        jLabel1.setText("QUẢN LÝ MENU");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Tìm kiếm");

        td.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã Món", "Tên Món", "Số Lượng", "Giá", "Ảnh"
            }
        ));
        jScrollPane1.setViewportView(td);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Mã Món:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Tên Món:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Số Lượng:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Giá:");

        txt_maDV.setEnabled(false);

        spr_soLuong.setModel(new javax.swing.SpinnerNumberModel(0, 0, 500, 1));

        spr_donGia.setModel(new javax.swing.SpinnerNumberModel(0, 0, 2000000000, 1000));

        l_pic.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        l_pic.setBorder(javax.swing.BorderFactory.createEtchedBorder());

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

        btn_del.setBackground(new java.awt.Color(255, 255, 254));
        btn_del.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Button/delete_icon.png"))); // NOI18N
        btn_del.setText("Xóa");

        btn_anh.setBackground(new java.awt.Color(255, 255, 254));
        btn_anh.setText("Chọn Ảnh");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(l_pic, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_maDV)
                            .addComponent(txt_tenDV)
                            .addComponent(spr_soLuong, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(spr_donGia, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btn_add, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                            .addComponent(btn_save, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btn_del, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                            .addComponent(btn_edit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(btn_anh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_maDV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_tenDV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(spr_soLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(spr_donGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(l_pic, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_anh)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_add)
                    .addComponent(btn_edit))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_del)
                    .addComponent(btn_save))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btn_clear.setBackground(new java.awt.Color(255, 255, 254));
        btn_clear.setText("Làm Mới");
        btn_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clearActionPerformed(evt);
            }
        });

        jMenu1.setText("Hệ thống");

        mi_exit.setText("Thoát");
        jMenu1.add(mi_exit);

        jMenuBar1.add(jMenu1);

        l_acc.setText("jMenu3");
        jMenuBar1.add(l_acc);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_search))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 837, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btn_clear)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_clear))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_editActionPerformed

    private void btn_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clearActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_clearActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmQLMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_add;
    private javax.swing.JButton btn_anh;
    private javax.swing.JButton btn_clear;
    private javax.swing.JButton btn_del;
    private javax.swing.JButton btn_edit;
    private javax.swing.JButton btn_save;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenu l_acc;
    private javax.swing.JLabel l_pic;
    private javax.swing.JMenuItem mi_exit;
    private javax.swing.JSpinner spr_donGia;
    private javax.swing.JSpinner spr_soLuong;
    private javax.swing.JTable td;
    private javax.swing.JTextField txt_maDV;
    private javax.swing.JTextField txt_search;
    private javax.swing.JTextField txt_tenDV;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {
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
