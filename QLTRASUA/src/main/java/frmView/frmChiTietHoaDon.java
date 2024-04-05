/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package frmView;

import Model.ChiTietHoaDon;
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
import java.util.ArrayList;
import javax.swing.BorderFactory;
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
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ad
 */
public class frmChiTietHoaDon extends JFrame implements ActionListener {
    //<editor-fold defaultstate="collapsed" desc="Var">
    boolean tbdv_isSelected = false, tbhd_isSelected = false;
    int tbdv_item_id, tbhd_item_id;
    private ArrayList<QLNguyenLieu> arr_dv = new ArrayList();
    private ArrayList<ChiTietHoaDon> arr_hd = new ArrayList();
    //---------------------------------------------------------------------------
    
    JPanel p_left = new JPanel();//_________________
    JLabel tile1 = new JLabel("DANH SÁCH DỊCH VỤ");
    JButton btn_search = new JButton("TÌM KIẾM");
    JTextField txt_search = new JTextField();
    JTable tbdv = new JTable();
    DefaultTableModel model_dv = (DefaultTableModel) tbdv.getModel();
    JLabel l_pic = new JLabel();
    
    JPanel p_mid = new JPanel();//_________________
    SpinnerNumberModel snm_soLuong = new SpinnerNumberModel(0, 0, 500, 1);
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
    //</editor-fold>
    public frmChiTietHoaDon(String maHD, String maNV) {
        this.setSize(1200,800);
        this.setLocation(50, 70);
        this.setTitle("Chi tiết hóa đơn");
        this.setLayout(new BorderLayout());
        Border pad = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        Border padB = BorderFactory.createLineBorder(Color.BLACK);
        //<editor-fold defaultstate="collapsed" desc="Menu">
        m_hethong.add(mi_exit);
        mb.add(m_hethong);
        this.add(mb,BorderLayout.NORTH);
        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="Panel_left">
        p_left.setLayout(new BorderLayout());
        //---------------------------------------------------------------------------------
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
        
        btn_search.setBackground(Color.WHITE);
        btn_search.setPreferredSize(new Dimension(75,25));
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        pL1.add(btn_search,gbc);
        btn_search.addActionListener(this);
        
        Font fo_t = new Font("Serif", Font.PLAIN,14);
        txt_search.setFont(fo_t);
        txt_search.setPreferredSize(new Dimension(175,25));
        gbc.gridx=1;
        pL1.add(txt_search,gbc);
        
        p_left.add(pL1,BorderLayout.NORTH);
        //---------------------------------------------------------------------------------
        model_dv.addColumn("Dịch vụ");
        model_dv.addColumn("Số lượng");
        p_left.add(new JScrollPane(tbdv),BorderLayout.CENTER);
        //---------------------------------------------------------------------------------
        l_pic.setPreferredSize(new Dimension(250,250));
        l_pic.setBorder(padB);
        p_left.add(l_pic,BorderLayout.SOUTH);
        
        this.add(p_left,BorderLayout.WEST);
        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="Panel_mid">
        p_mid.setLayout(new GridBagLayout());
        gbc= new GridBagConstraints();
        
        spr_soLuong.setFont(fo_t);
        gbc.gridx=0;
        gbc.gridy=0;
        p_mid.add(spr_soLuong,gbc);
        
        btn_add.setBackground(Color.WHITE);
        gbc.gridx=1;
        p_mid.add(btn_add,gbc);
        
        btn_del.setBackground(Color.WHITE);
        gbc.gridwidth=2;
        gbc.gridx=0;
        gbc.gridy=1;
        p_mid.add(btn_del,gbc);
        this.add(p_mid,BorderLayout.CENTER);
        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="Panel_right">
        p_right.setLayout(new BorderLayout());
        p_right.setBorder(pad);
        //------------------------------------------------
        tile2.setFont(fo_tile);
        tile2.setHorizontalAlignment(JLabel.CENTER);
        tile2.setBorder(pad);
        p_right.add(tile2,BorderLayout.NORTH);
        //------------------------------------------------
        JPanel pR11 = new JPanel();
        pR11.setBorder(pad);
        pR11.setLayout(new GridLayout(2, 2, 10, 10));
        
        //config element
        Font fo_l = new Font("Serif", Font.BOLD,14);
        l_maHD.setFont(fo_l);
        l_tenNV.setFont(fo_l);
        l_ngayLap.setFont(fo_l);
        cb_ban.setFont(fo_t);
        
        l_maHD.setText("Mã hóa đơn: HD001");
        l_tenNV.setText("Nhân viên: Chu Nguyên Phong");
        l_ngayLap.setText("Ngày: 03/04/2024");
        
        cb_ban.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "B001", "B002", "B003", "B004", "B005" }));
        cb_ban.setEditable(false);
        //add
        pR11.add(l_maHD);
        pR11.add(l_tenNV);
        pR11.add(l_ngayLap);
        pR11.add(cb_ban);
        //table
        model_hd.addColumn("Dịch vụ");
        model_hd.addColumn("Số lượng");
        model_hd.addColumn("Đơn giá");
        
        JPanel pR1 = new JPanel();
        pR1.setLayout(new BorderLayout());
        pR1.add(pR11,BorderLayout.NORTH);
        pR1.add(new JScrollPane(tbhd),BorderLayout.CENTER);
        
        p_right.add(pR1,BorderLayout.CENTER);
        //------------------------------------------------
        l_thanhTien.setFont(fo_l);
        l_thanhTien.setHorizontalAlignment(JLabel.RIGHT);
        l_thanhTien.setBorder(pad);
        p_right.add(l_thanhTien,BorderLayout.SOUTH);
        
        this.add(p_right,BorderLayout.EAST);
        //</editor-fold>
        this.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {} 
}
