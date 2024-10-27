/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import Controller.ChiTietHoaDonData;
import Controller.ExcelFileExporter;
import Controller.TaiKhoanData;
import Model.QLHoaDon;
import frmView.frmHome;
import frmView.frmQuanLyHoaDon;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import org.apache.hc.core5.http.ParseException;

/**
 *
 * @author ADMIN
 */
public class frmQLHoaDon extends javax.swing.JFrame implements ActionListener, KeyListener{

    /**
     * Creates new form frmQLHoaDon
     */
    boolean isSelected = false;
    int item_id,mode=0;
    private ArrayList<QLHoaDon> arr = new ArrayList();
    private DefaultTableModel model;
    public frmQLHoaDon() {
        initComponents();
        l_acc.setText("Tài khoản: "+TaiKhoanData.user);
        this.model = (DefaultTableModel) td.getModel();
        txt_search.addKeyListener(this);
        btn_newOrder.addActionListener(this);
        btn_edit.addActionListener(this);
        btn_del.addActionListener(this);
        //<editor-fold defaultstate="collapsed" desc="Event">
        btn_excel.addActionListener((e) -> {
            String[] headers = new String[] {"Mã hóa đơn","Tên nhân viên","Ngày lập","Bàn","Thành tiền","Tình trạng"};
            String fileName = "Quản lí hóa đơn.xlsx";
            ExcelFileExporter excelFileExporter = new ExcelFileExporter();
            excelFileExporter.exportHoaDonExcelFile(arr, headers, fileName);
        });
        btn_detail.addActionListener((e) -> {
            try {
                ChiTietHoaDonData cthd = new ChiTietHoaDonData(arr.get(item_id));
                dispose();
            } catch (IOException | ParseException ex) {
                Logger.getLogger(frmQuanLyHoaDon.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        final ListSelectionModel sel = td.getSelectionModel();
        sel.addListSelectionListener((ListSelectionEvent e) -> {
                if(!sel.isSelectionEmpty()){
                    item_id=sel.getMinSelectionIndex();
                    cb_ban.setSelectedItem(arr.get(item_id).getMaBan());
                    btn_detail.setEnabled(true);
                    if(arr.get(item_id).getTinhTrang().equals("Chưa thanh toán")){
                        btn_del.setEnabled(true);
                        btn_edit.setEnabled(true);
                        check_thanhToan.setSelected(false);
                    } else {
                        if(TaiKhoanData.phanQuyen.equals("Quản lý")){   
                            btn_del.setEnabled(true);
                            btn_edit.setEnabled(false);
                        }else{
                            btn_del.setEnabled(false);
                            btn_edit.setEnabled(false);
                        }
                        check_thanhToan.setSelected(true);
                    }
                    isSelected=true;
                } else {
                    btn_detail.setEnabled(false);
                    btn_edit.setEnabled(false);
                    btn_del.setEnabled(false);
                    isSelected=false;
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
    public void searchListener (KeyListener log){
        txt_search.addKeyListener(log);
    }
    public void newOrderListener (ActionListener log){
        btn_newOrder.addActionListener(log);
    }
    public void editListener (ActionListener log){
        btn_edit.addActionListener(log);
    }
    public void delListener (ActionListener log){
        btn_del.addActionListener(log);
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Method">
    public QLHoaDon getInfo(){
        arr.get(item_id).setMaBan(getBan());
        if(check_thanhToan.isSelected()){
            arr.get(item_id).setTinhTrang("Đã thanh toán");
        } else {
            arr.get(item_id).setTinhTrang("Chưa thanh toán");
        }
        return arr.get(item_id);
    }
    public boolean editMode(){
        if(mode == 1){
            mode = 0;
            check_thanhToan.setEnabled(false);
            btn_edit.setBackground(Color.WHITE);
            btn_del.setEnabled(true);
            td.setEnabled(true);
            return true;
        } else {
            mode = 1;
            if(arr.get(item_id).getTinhTrang().equals("Chưa thanh toán")){
                check_thanhToan.setEnabled(true);
            }
            btn_edit.setBackground(Color.YELLOW);
            btn_del.setEnabled(false);
            td.setEnabled(false);
            return false;
        }
    }
    public int getItem_id() {
        return item_id;
    }
    public String getBan(){
        return cb_ban.getSelectedItem().toString();
    }
    public String getSearch(){
        String s = txt_search.getText().trim().replace(" ", "_");
        return s;
    }
    public void loadTable(ArrayList<QLHoaDon> arr){
        int rc = model.getRowCount();
        for(int i=0;i<rc;i++){
            model.removeRow(0);
        }
        Object r[] = new Object[6];
        for(int i=0;i<arr.size();i++){
            r[0] = arr.get(i).getMaHD();
            r[1] = arr.get(i).getTenNhanVien();
            r[2] = arr.get(i).getNgayLap();
            r[3] = arr.get(i).getMaBan();
            r[4] = arr.get(i).getThanhTien();
            r[5] = arr.get(i).getTinhTrang();
            model.addRow(r);
        }
        this.arr = arr;
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
        btn_detail = new javax.swing.JButton();
        btn_newOrder = new javax.swing.JButton();
        btn_del = new javax.swing.JButton();
        btn_excel = new javax.swing.JButton();
        cb_ban = new javax.swing.JComboBox<>();
        btn_edit = new javax.swing.JButton();
        check_thanhToan = new javax.swing.JCheckBox();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        mi_exit = new javax.swing.JMenuItem();
        l_acc = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Quản Lý Hóa Đơn");

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel1.setText("QUẢN LÝ HÓA ĐƠN");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Tìm kiếm:");

        td.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã hóa đơn", "Tên nhân viên", "Ngày lập", "Bàn", "Thành tiền", "Tình trạng"
            }
        ));
        jScrollPane1.setViewportView(td);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btn_detail.setBackground(new java.awt.Color(255, 255, 254));
        btn_detail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Button/detail_icon.png"))); // NOI18N
        btn_detail.setText("Chi Tiết");
        btn_detail.setEnabled(false);
        btn_detail.setPreferredSize(new java.awt.Dimension(106, 39));

        btn_newOrder.setBackground(new java.awt.Color(255, 255, 254));
        btn_newOrder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Button/add_icon.png"))); // NOI18N
        btn_newOrder.setText("Hóa đơn mới");

        btn_del.setBackground(new java.awt.Color(255, 255, 254));
        btn_del.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Button/delete_icon.png"))); // NOI18N
        btn_del.setText("Xóa");

        btn_excel.setBackground(new java.awt.Color(255, 255, 254));
        btn_excel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Button/excel_icon.png"))); // NOI18N
        btn_excel.setText("Excel");

        cb_ban.setBackground(new java.awt.Color(255, 255, 254));
        cb_ban.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đem về", "Bàn 001", "Bàn 002", "Bàn 003", "Bàn 004", "Bàn 005", "Bàn 006", "Bàn 007", "Bàn 008", "Bàn 009", "Bàn 010" }));

        btn_edit.setBackground(new java.awt.Color(255, 255, 254));
        btn_edit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Button/edit_icon.png"))); // NOI18N
        btn_edit.setText("Sửa");

        check_thanhToan.setText("Đã thanh toán");
        check_thanhToan.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_detail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btn_newOrder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_del, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_excel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(check_thanhToan)
                            .addComponent(cb_ban, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_edit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_detail, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_newOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_ban, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(check_thanhToan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_edit, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_del, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_excel, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jMenu1.setText("Hệ thống");

        mi_exit.setText("Thoát");
        jMenu1.add(mi_exit);

        jMenuBar1.add(jMenu1);

        l_acc.setText("Edit");
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
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, 977, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1041, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line argumen
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmQLHoaDon().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_del;
    private javax.swing.JButton btn_detail;
    private javax.swing.JButton btn_edit;
    private javax.swing.JButton btn_excel;
    private javax.swing.JButton btn_newOrder;
    private javax.swing.JComboBox<String> cb_ban;
    private javax.swing.JCheckBox check_thanhToan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenu l_acc;
    private javax.swing.JMenuItem mi_exit;
    private javax.swing.JTable td;
    private javax.swing.JTextField txt_search;
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
