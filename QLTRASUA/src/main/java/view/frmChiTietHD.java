/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import Controller.ExcelFileExporter;
import Controller.HoaDonData;
import Controller.MenuData;
import Controller.NguyenLieuData;
import Controller.TaiKhoanData;
import Model.ChiTietHoaDon;
import Model.QLHoaDon;
import Model.QLMenu;
import Model.QLNguyenLieu;
import frmView.frmChiTietHoaDon;
import java.awt.BorderLayout;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import org.apache.hc.core5.http.ParseException;

/**
 *
 * @author ADMIN
 */
public class frmChiTietHD extends javax.swing.JFrame implements ActionListener {
    boolean tddv_isSelected = false, tdhd_isSelected = false, tdnl_isSelected = false;
    int tddv_item_id, tdhd_item_id, tdnl_item_id;
    private ArrayList<QLNguyenLieu> arr_nl = new ArrayList();
    private ArrayList<ChiTietHoaDon> arr_hd = new ArrayList();
    private ArrayList<QLMenu> arr_mn = new ArrayList();
    MenuData mn = new MenuData("");
    NguyenLieuData nl = new NguyenLieuData("");
    QLHoaDon chd;   
    private DefaultTableModel modeldv,modelnl,modelhd;
    public frmChiTietHD(QLHoaDon hd) {
        initComponents();
        chd=hd;
        if(chd.getTinhTrang().equals("Đã thanh toán")){
            tddv.setEnabled(false);
            tdhd.setEnabled(false);
            btn_excel.setEnabled(true);
        }
        l_maHD.setText("Mã hóa đơn: " + hd.getMaHD());
        l_tenNV.setText("Nhân viên: " + hd.getTenNhanVien());
        
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String strDate = dateFormat.format(hd.getNgayLap());
        l_ngayLap.setText("Ngày: " + strDate);
        l_ban.setText("Bàn: "+hd.getMaBan());
        l_acc.setText("Tài khoản: "+TaiKhoanData.user);
        this.modeldv = (DefaultTableModel) tddv.getModel();
        this.modelnl = (DefaultTableModel) tdnl.getModel();
        this.modelhd = (DefaultTableModel) tdhd.getModel();
        btn_add.addActionListener(this);
        btn_del.addActionListener(this);
        //<editor-fold defaultstate="collapsed" desc="Event">
        btn_excel.addActionListener((e) -> {
            String[] headers = new String[] {"Tên sản phẩm","Số lượng","Đơn giá","Thành tiền"};
            String fileName = "Hóa đơn.xlsx";
            ExcelFileExporter excelFileExporter = new ExcelFileExporter();
            excelFileExporter.exportChiTietHoaDonExcelFile(chd, arr_hd, headers, fileName);
        });
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    HoaDonData hdfrm = new HoaDonData();
                } catch (IOException | ParseException ex) {
                    Logger.getLogger(frmChiTietHoaDon.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        mi_exit.addActionListener((e) -> {
            try {
                HoaDonData hdfrm = new HoaDonData();
                dispose();
            } catch (IOException | ParseException ex) {
                Logger.getLogger(frmChiTietHoaDon.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        final ListSelectionModel sel_dv = tddv.getSelectionModel();
        sel_dv.addListSelectionListener((ListSelectionEvent e) -> {
            if(!sel_dv.isSelectionEmpty()){
                spr_soLuong.setValue(0);
                tddv_item_id = sel_dv.getMinSelectionIndex();
                btn_add.setEnabled(true);
                tddv_isSelected=true;
                setPic();
            } else {
                btn_add.setEnabled(false);
                tddv_isSelected=false;
            }
        });
        final ListSelectionModel sel_nl = tdnl.getSelectionModel();
        sel_nl.addListSelectionListener((ListSelectionEvent e) -> {
            if(!sel_nl.isSelectionEmpty()){
                spr_soLuong.setValue(0);
                tdnl_item_id = sel_nl.getMinSelectionIndex();
                btn_add.setEnabled(true);
                tdnl_isSelected=true;
            } else {
                btn_add.setEnabled(false);
                tdnl_isSelected=false;
            }
        });
        final ListSelectionModel sel_hd = tdhd.getSelectionModel();
        sel_hd.addListSelectionListener((ListSelectionEvent e) -> {
            if(!sel_hd.isSelectionEmpty()){
                tdhd_item_id = sel_hd.getMinSelectionIndex();
                btn_del.setEnabled(true);
                tdhd_isSelected=true;
            } else {
                btn_del.setEnabled(false);
                tdhd_isSelected=false;
            }
        });
        //</editor-fold>
    }
    //<editor-fold defaultstate="collapsed" desc="Event2">
    public void delListener (ActionListener log){
        btn_del.addActionListener(log);
    }
    public void addListener (ActionListener log){
        btn_add.addActionListener(log);
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Method">
    public QLHoaDon getChd() {
        return chd;
    }
    
    public String getMaHD(){
        return chd.getMaHD();
    }
    public String getMaDV(){
        if(tab_menu.getSelectedIndex()==0){
            return arr_mn.get(tddv_item_id).getMaMon();
        } else {
            return arr_nl.get(tdnl_item_id).getMaNL();
        }
    }
    public String getSoLuong(){
        if(Integer.parseInt(spr_soLuong.getValue().toString())==0){
            JOptionPane.showMessageDialog(null, "Chọn số lượng muốn thêm", "Thông báo", 1);
            return "";
        } else {
            return spr_soLuong.getValue().toString();
        }
    }
    public String getThanhTien(){
        int r,n=Integer.parseInt(spr_soLuong.getValue().toString());
        if(tab_menu.getSelectedIndex()==0){
            r = arr_mn.get(tddv_item_id).getGia() * n;
        } else {
            r = arr_nl.get(tdnl_item_id).getDonGia() * n;
        }
        return Integer.toString(r);
    }
    public int getTbhd_item_id() {
        return tdhd_item_id;
    }
    
    public void loadTable(ArrayList<ChiTietHoaDon> arr) throws ParseException, IOException{
        int rc = modelhd.getRowCount();
        for(int i=0;i<rc;i++){
            modelhd.removeRow(0);
        }
        Object r[] = new Object[4];
        int tong=0;
        for(int i=0;i<arr.size();i++){
            r[0] = arr.get(i).getTenDV();
            r[1] = arr.get(i).getSoLuong();
            r[2] = arr.get(i).getDonGia();
            r[3] = arr.get(i).getThanhTien();
            tong = tong + arr.get(i).getThanhTien();
            modelhd.addRow(r);
        }
        this.arr_hd = arr;
        chd.setThanhTien(tong);
        loadTableMN("");
        loadTableNL("");
        txt_search1.setText("");
        txt_search2.setText("");
        l_thanhTien.setText("Tổng: "+chd.getThanhTien());
        l_tinhTrang.setText("Tình Trạng: "+chd.getTinhTrang());
    }
    public void setPic(){
        if(tab_menu.getSelectedIndex()==0){
            try {
                File file = new File("");
                String currentDirectory = file.getAbsolutePath() + "/src/main/java";
                BufferedImage myPicture = ImageIO.read(new File(currentDirectory + arr_mn.get(tddv_item_id).getAnh()));
                l_pic.setIcon(new ImageIcon(myPicture.getScaledInstance(250, 250, Image.SCALE_SMOOTH)));
                l_pic.repaint();
            } catch (IOException ex) {
                Logger.getLogger(frmChiTietHoaDon.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public void loadTableNL(String s) throws ParseException, IOException{
        this.arr_nl = nl.getNL(s);
        int rc = modelnl.getRowCount();
        for(int i=0;i<rc;i++){
            modelnl.removeRow(0);
        }
        Object r[] = new Object[3];
        for(int i=0;i<arr_nl.size();i++){
            r[0] = arr_nl.get(i).getTenNL();
            r[1] = arr_nl.get(i).getSoLuong();
            r[2] = arr_nl.get(i).getDonGia();
            modelnl.addRow(r);
        }
    }
    public void loadTableMN(String s) throws ParseException, IOException{
        this.arr_mn = mn.getDV(s);
        int rc = modeldv.getRowCount();
        for(int i=0;i<rc;i++){
            modeldv.removeRow(0);
        }
        Object r[] = new Object[3];
        for(int i=0;i<arr_mn.size();i++){
            r[0] = arr_mn.get(i).getTenMon();
            r[1] = arr_mn.get(i).getSoLuong();
            r[2] = arr_mn.get(i).getGia();
            modeldv.addRow(r);
        }
    }
    //</editor-fold>
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tab_menu = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tddv = new javax.swing.JTable();
        txt_search1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        l_pic = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txt_search2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tdnl = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        spr_soLuong = new javax.swing.JSpinner();
        btn_add = new javax.swing.JButton();
        btn_del = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        l_maHD = new javax.swing.JLabel();
        l_ngayLap = new javax.swing.JLabel();
        l_ban = new javax.swing.JLabel();
        l_tenNV = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tdhd = new javax.swing.JTable();
        l_thanhTien = new javax.swing.JLabel();
        l_tinhTrang = new javax.swing.JLabel();
        btn_excel = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        mi_exit = new javax.swing.JMenuItem();
        l_acc = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Chi Tiết Hóa Đơn");

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tddv.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Món", "Số lượng", "Giá"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tddv);

        txt_search1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_search1KeyReleased(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Tìm kiếm:");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel2.setText("MENU");

        l_pic.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        l_pic.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_search1))
                    .addComponent(l_pic, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 161, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addGap(0, 161, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_search1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(l_pic, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tab_menu.addTab("Menu", jPanel1);

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel3.setText("NGUYÊN LIỆU");

        txt_search2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_search2KeyReleased(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Tìm kiếm:");
        jLabel4.setToolTipText("");

        tdnl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Món", "Số lượng", "Giá"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tdnl);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_search2))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 115, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addGap(0, 114, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txt_search2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 590, Short.MAX_VALUE)
                .addContainerGap())
        );

        tab_menu.addTab("Nguyên liệu", jPanel2);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Số lượng");

        spr_soLuong.setModel(new javax.swing.SpinnerNumberModel(0, 0, 100, 1));

        btn_add.setBackground(new java.awt.Color(255, 255, 254));
        btn_add.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Button/plus.png"))); // NOI18N
        btn_add.setText(">>");
        btn_add.setEnabled(false);

        btn_del.setBackground(new java.awt.Color(255, 255, 254));
        btn_del.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_del.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Button/minus.png"))); // NOI18N
        btn_del.setText("<<");
        btn_del.setEnabled(false);

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel6.setText("HÓA ĐƠN");

        l_maHD.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        l_maHD.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        l_maHD.setText("hóa đơn");

        l_ngayLap.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        l_ngayLap.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        l_ngayLap.setText("ngày");

        l_ban.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        l_ban.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        l_ban.setText("Bàn");

        l_tenNV.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        l_tenNV.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        l_tenNV.setText("nhân viên");

        tdhd.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Món", "Số lượng", "Đơn giá", "Thành tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tdhd);

        l_thanhTien.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        l_thanhTien.setText("Tổng: 1000000000");

        l_tinhTrang.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        l_tinhTrang.setText("Tình trạng: Chưa thanh toán");

        btn_excel.setBackground(new java.awt.Color(255, 255, 254));
        btn_excel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Button/excel_icon.png"))); // NOI18N
        btn_excel.setText("Excel");
        btn_excel.setEnabled(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 206, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addGap(0, 206, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(l_maHD)
                                .addGap(183, 183, 183))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(l_ngayLap)
                                .addGap(203, 203, 203)))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(l_ban)
                            .addComponent(l_tenNV))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane3)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btn_excel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(l_thanhTien)
                        .addGap(18, 18, 18)
                        .addComponent(l_tinhTrang)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(l_maHD, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                    .addComponent(l_tenNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(l_ban, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(l_ngayLap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 534, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(l_thanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(l_tinhTrang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_excel)))
                .addContainerGap())
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
                .addComponent(tab_menu, javax.swing.GroupLayout.PREFERRED_SIZE, 409, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(spr_soLuong)
                    .addComponent(btn_del, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_add, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(257, 257, 257)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spr_soLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_add)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_del)
                .addGap(248, 248, 248))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tab_menu, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(0, 9, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_search1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_search1KeyReleased
        try {
            tddv.clearSelection();
            loadTableMN(txt_search1.getText());
        } catch (ParseException | IOException ex) {}
    }//GEN-LAST:event_txt_search1KeyReleased

    private void txt_search2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_search2KeyReleased
        try {
            tdnl.clearSelection();
            loadTableNL(txt_search2.getText());
        } catch (ParseException | IOException ex) {}
    }//GEN-LAST:event_txt_search2KeyReleased

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_add;
    private javax.swing.JButton btn_del;
    private javax.swing.JButton btn_excel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JMenu l_acc;
    private javax.swing.JLabel l_ban;
    private javax.swing.JLabel l_maHD;
    private javax.swing.JLabel l_ngayLap;
    private javax.swing.JLabel l_pic;
    private javax.swing.JLabel l_tenNV;
    private javax.swing.JLabel l_thanhTien;
    private javax.swing.JLabel l_tinhTrang;
    private javax.swing.JMenuItem mi_exit;
    private javax.swing.JSpinner spr_soLuong;
    private javax.swing.JTabbedPane tab_menu;
    private javax.swing.JTable tddv;
    private javax.swing.JTable tdhd;
    private javax.swing.JTable tdnl;
    private javax.swing.JTextField txt_search1;
    private javax.swing.JTextField txt_search2;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
