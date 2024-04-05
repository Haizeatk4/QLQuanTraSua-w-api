/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package frmView;

import Controller.MenuData;
import Controller.NguyenLieuData;
import Model.ChiTietHoaDon;
import Model.QLMenu;
import Model.QLNguyenLieu;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import org.apache.hc.core5.http.ParseException;

/**
 *
 * @author ad
 */
public class frmChiTietHoaDon extends JFrame implements ActionListener {
    //<editor-fold defaultstate="collapsed" desc="Var">
    boolean tbdv_isSelected = false, tbhd_isSelected = false;
    int tbdv_item_id, tbhd_item_id;
    private ArrayList<QLNguyenLieu> arr_nl = new ArrayList();
    private ArrayList<ChiTietHoaDon> arr_hd = new ArrayList();
    private ArrayList<QLMenu> arr_mn = new ArrayList();
    MenuData mn = new MenuData("");
    NguyenLieuData nl = new NguyenLieuData("");
    //---------------------------------------------------------------------------
    
    JPanel p_left = new JPanel();//_________________
    JLabel tile1 = new JLabel("MENU");
    JButton btn_search = new JButton("TÌM KIẾM");
    JTextField txt_search = new JTextField();
    JComboBox<String> cb_filter = new JComboBox<>();
    JTable tbdv = new JTable();
    DefaultTableModel model_dv = (DefaultTableModel) tbdv.getModel();
    JLabel l_pic = new JLabel();
    
    JLabel l_soLuong = new JLabel(" Số lượng: ");
    SpinnerNumberModel snm_soLuong = new SpinnerNumberModel(0, 0, 500, 0.5);
    JSpinner spr_soLuong = new JSpinner(snm_soLuong);
    JButton btn_add = new JButton("THÊM MÓN");
    JButton btn_del = new JButton("XÓA MÓN");
    
    JPanel p_right = new JPanel();//_________________
    JLabel tile2 = new JLabel("HÓA ĐƠN");
    JLabel l_maHD = new JLabel();
    JLabel l_tenNV = new JLabel();
    JLabel l_ngayLap = new JLabel();
    JComboBox<String> cb_ban = new JComboBox<>();
    JTable tbhd = new JTable();
    DefaultTableModel model_hd = (DefaultTableModel) tbhd.getModel();
    JLabel l_thanhTien = new JLabel();
    
    JMenuBar mb = new JMenuBar();
    JMenu m_hethong = new JMenu("Hệ thống");
    JMenuItem mi_exit = new JMenuItem("Thoát");
    JLabel l_preAcc = new JLabel("Tài khoản: ");
    JLabel l_acc = new JLabel();
    //</editor-fold>
    public frmChiTietHoaDon(String tk, String maHD, String tenNV) throws ParseException, IOException {
        this.setSize(1200,800);
        this.setLocation(50, 70);
        this.setTitle("Chi tiết hóa đơn");
        this.setLayout(new BorderLayout());
        Border pad = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        Border padB = BorderFactory.createLineBorder(Color.BLACK);
        //<editor-fold defaultstate="collapsed" desc="Menu">
        l_acc.setText(tk);
        m_hethong.add(mi_exit);
        mb.add(m_hethong);
        mb.add(l_preAcc);
        mb.add(l_acc);
        this.add(mb,BorderLayout.NORTH);
        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="Panel_left">
        p_left.setLayout(new BorderLayout());
        //setup tiêu đề
        JPanel pL1 = new JPanel();
        pL1.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc= new GridBagConstraints();
        gbc.weightx = 1;
        gbc.weighty = 1.0;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        
        Font fo_tile = new Font("Serif", Font.BOLD,26);
        tile1.setFont(fo_tile);
        tile1.setHorizontalAlignment(JLabel.CENTER);
        tile1.setBorder(pad);
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 0;
        pL1.add(tile1,gbc);
        //setup thanh tìm kiếm
        btn_search.setBackground(Color.WHITE);
        btn_search.setPreferredSize(new Dimension(75,25));
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        pL1.add(btn_search,gbc);
        btn_search.addActionListener(this);
        
        Font fo_t = new Font("Serif", Font.PLAIN,14);
        txt_search.setFont(fo_t);
        txt_search.setPreferredSize(new Dimension(200,25));
        gbc.gridx=1;
        pL1.add(txt_search,gbc);
        
        p_left.add(pL1,BorderLayout.NORTH);//thêm panel phụ 1
        
        //---------------------------------------------------------------------------------
        JPanel pL2 = new JPanel();
        pL2.setLayout(new BorderLayout());
        //setup combo box
        cb_filter.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Menu", "Nguyên liệu"}));
        cb_filter.setEditable(false);
        pL2.add(cb_filter,BorderLayout.NORTH);
        //setup bảng
        model_dv.addColumn("Món");
        model_dv.addColumn("Số lượng");
        model_dv.addColumn("Giá");
        pL2.add(new JScrollPane(tbdv),BorderLayout.CENTER);
                    this.arr_mn = mn.getAllDV();
                    loadTableMN();
        //setup ảnh
        l_pic.setPreferredSize(new Dimension(250,250));
        l_pic.setBorder(padB);
        pL2.add(l_pic,BorderLayout.SOUTH);
        
        p_left.add(pL2,BorderLayout.CENTER);//thêm panel phụ 2
        //---------------------------------------------------------------------------------
        JPanel pL3 = new JPanel();
        pL3.setBorder(pad);
        pL3.setLayout(new GridLayout(1,3,10,10));
        
        Font fo_l = new Font("Serif", Font.BOLD,14);
        l_soLuong.setFont(fo_l);
        pL3.add(l_soLuong,gbc);
        
        spr_soLuong.setFont(fo_t);
        pL3.add(spr_soLuong,gbc);
        
        btn_add.setBackground(Color.WHITE);
        btn_add.setEnabled(false);
        pL3.add(btn_add);
        
        p_left.add(pL3,BorderLayout.SOUTH);//thêm panel phụ 3

        this.add(p_left,BorderLayout.WEST);
        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="Panel_right">
        p_right.setLayout(new BorderLayout());
        p_right.setBorder(pad);
        //setup tiêu đề
        tile2.setFont(fo_tile);
        tile2.setHorizontalAlignment(JLabel.CENTER);
        tile2.setBorder(pad);
        p_right.add(tile2,BorderLayout.NORTH);
        //----------------------------setup label
        JPanel pR11 = new JPanel();
        pR11.setBorder(pad);
        pR11.setLayout(new GridLayout(2, 2, 10, 10));
        
        //config element
        l_maHD.setFont(fo_l);
        l_tenNV.setFont(fo_l);
        l_ngayLap.setFont(fo_l);
        cb_ban.setFont(fo_t);
        
        if(maHD.equals("")){
            l_maHD.setText("Mã hóa đơn: Tạo mới");
        } else {
            l_maHD.setText("Mã hóa đơn: " + maHD);
        }
        l_tenNV.setText("Nhân viên: " + tenNV);
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String date = localDate.format(formatter);
        l_ngayLap.setText("Ngày: " + date);
        
        cb_ban.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "B001", "B002", "B003", "B004", "B005" }));
        cb_ban.setEditable(false);
        //add
        pR11.add(l_maHD);
        pR11.add(l_tenNV);
        pR11.add(l_ngayLap);
        pR11.add(cb_ban);
        //------------------------table
        model_hd.addColumn("Dịch vụ");
        model_hd.addColumn("Số lượng");
        model_hd.addColumn("Đơn giá");
        
        JPanel pR1 = new JPanel();
        pR1.setLayout(new BorderLayout());
        pR1.add(pR11,BorderLayout.NORTH);
        pR1.add(new JScrollPane(tbhd),BorderLayout.CENTER);
        
        p_right.add(pR1,BorderLayout.CENTER);
        //setup xóa và thành tiền
        JPanel pR2 = new JPanel();
        pR2.setLayout(new BorderLayout());
        
        btn_del.setBackground(Color.WHITE);
        pR2.add(btn_del,BorderLayout.WEST);
        
        JLabel l_bl = new JLabel("  ");
        pR2.add(l_bl,BorderLayout.CENTER);
        
        l_thanhTien.setFont(fo_l);
        l_thanhTien.setHorizontalAlignment(JLabel.RIGHT);
        pR2.add(l_thanhTien,BorderLayout.EAST);
        pR2.setBorder(pad);
        p_right.add(pR2,BorderLayout.SOUTH);
        
        this.add(p_right,BorderLayout.CENTER);
        //</editor-fold>
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //<editor-fold defaultstate="collapsed" desc="Event">
        final ListSelectionModel sel_dv = tbdv.getSelectionModel();
        sel_dv.addListSelectionListener((ListSelectionEvent e) -> {
//            if(!sel_dv.isSelectionEmpty()){
                if(!sel_dv.isSelectionEmpty()){
                    tbdv_item_id = sel_dv.getMinSelectionIndex();
                    btn_add.setEnabled(true);
                    tbdv_isSelected=true;
                    setPic();
                } else {
                    btn_add.setEnabled(false);
                    tbdv_isSelected=false;
                }
//            }
        });
        cb_filter.addActionListener((e) -> {
            if(cb_filter.getSelectedIndex()==0){
                try {
                    tile1.setText("Menu");
                    this.arr_mn = mn.getAllDV();
                    loadTableMN();
                    pL2.add(l_pic,BorderLayout.SOUTH);
                } catch (ParseException | IOException ex) {}
            } else {
                try {
                    tile1.setText("Danh sách Nguyên liệu");
                    this.arr_nl = nl.getAllNL();
                    loadTableNL();
                    pL2.remove(l_pic);
                } catch (IOException | ParseException ex) {}
            }
        });
        //</editor-fold>
    }
    //<editor-fold defaultstate="collapsed" desc="Method">
    public void setPic(){
        if(cb_filter.getSelectedIndex()==0){
            try {
                File file = new File("");
                String currentDirectory = file.getAbsolutePath() + "/src/main/java";
                BufferedImage myPicture = ImageIO.read(new File(currentDirectory + arr_mn.get(tbdv_item_id).getAnh()));
                System.out.println(currentDirectory + arr_mn.get(tbdv_item_id).getAnh());
                l_pic = new JLabel(new ImageIcon(myPicture));
            } catch (IOException ex) {
                Logger.getLogger(frmChiTietHoaDon.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public void loadTableNL(){
        int rc = model_dv.getRowCount();
        for(int i=0;i<rc;i++){
            model_dv.removeRow(0);
        }
        Object r[] = new Object[3];
        for(int i=0;i<arr_nl.size();i++){
            r[0] = arr_nl.get(i).getTenNL();
            r[1] = arr_nl.get(i).getSoLuong();
            r[2] = arr_nl.get(i).getDonGia();
            model_dv.addRow(r);
        }
    }
    public void loadTableMN(){
        int rc = model_dv.getRowCount();
        for(int i=0;i<rc;i++){
            model_dv.removeRow(0);
        }
        Object r[] = new Object[3];
        for(int i=0;i<arr_mn.size();i++){
            r[0] = arr_mn.get(i).getTenMon();
            r[1] = arr_mn.get(i).getSoLuong();
            r[2] = arr_mn.get(i).getGia();
            model_dv.addRow(r);
        }
    }
    //</editor-fold>
    @Override
    public void actionPerformed(ActionEvent e) {} 
}
