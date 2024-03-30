/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package frmView;

import Model.QLNguyenLieu;
import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
public class frmQuanLyNguyenLieu extends JFrame implements ActionListener {
    //<editor-fold defaultstate="collapsed" desc="Var">
    boolean isSelected = false;
    int item_id,mode=0;
    private ArrayList<QLNguyenLieu> arr = new ArrayList();
    //---------------------------------------------------------------------------
    
    JLabel tile = new JLabel("QUẢN LÝ NGUYÊN LIỆU");
    
    JPanel p1 = new JPanel();
    JButton btn_add = new JButton("THÊM");
    JButton btn_edit = new JButton("SỬA");
    JButton btn_del = new JButton("XÓA");
    JButton btn_clear = new JButton("LÀM MỚI");
    JButton btn_search = new JButton("TÌM KIẾM");
    JButton btn_cal = new JButton("TÍNH TIỀN");
    JTextField txt_search = new JTextField();
    
    JLabel l_maNL = new JLabel("Mã Nguyên Liệu:");
    JLabel l_tenNL = new JLabel("Tên Nguyên Liệu:");
    JLabel l_ngayNhap = new JLabel("Ngày Nhập:");
    JLabel l_soLuong = new JLabel("Số Lượng:");
    JLabel l_donVi = new JLabel("Đơn vị tính:");
    JLabel l_donGia = new JLabel("Đơn Giá:");
    JTextField txt_maNL = new JTextField();
    JTextField txt_tenNL = new JTextField();
    JDateChooser dc_ngayNhap = new JDateChooser();
    SpinnerNumberModel snm_soLuong = new SpinnerNumberModel(0, 0, 500, 1); //----------
    JSpinner spr_soLuong = new JSpinner(snm_soLuong);
    JTextField txt_donVi = new JTextField();
    SpinnerNumberModel snm_donGia = new SpinnerNumberModel(0, 0, 2000000000, 100000);//------------
    JSpinner spr_donGia = new JSpinner(snm_donGia);
    
    JTable td = new JTable();
    DefaultTableModel model = (DefaultTableModel) td.getModel();
    //</editor-fold>
    public frmQuanLyNguyenLieu() {
        this.setSize(1200,800);
        this.setLayout(new BorderLayout());
        Border pad = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        
        Font fo_tile = new Font("Serif", Font.BOLD,32);
        tile.setFont(fo_tile);
        tile.setHorizontalAlignment(JLabel.CENTER);
        tile.setBorder(pad);
        //<editor-fold defaultstate="collapsed" desc="Panel01">
        p1.setBorder(pad);
        p1.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc= new GridBagConstraints();
        gbc.weightx = 1;
        gbc.weighty = 1.0;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        
        Font fo_l = new Font("Serif", Font.BOLD,14);
        Font fo_t = new Font("Serif", Font.PLAIN,14);
        //--------------------------add Lable-----------------------------------
        l_maNL.setFont(fo_l);
        gbc.gridx = 0;
        gbc.gridy = 0;
        p1.add(l_maNL,gbc);
        
        l_tenNL.setFont(fo_l);
        gbc.gridy = 1;
        p1.add(l_tenNL,gbc);
        
        l_ngayNhap.setFont(fo_l);
        gbc.gridy = 2;
        p1.add(l_ngayNhap,gbc);
        
        l_soLuong.setFont(fo_l);
        gbc.gridy = 3;
        p1.add(l_soLuong,gbc);
        
        l_donVi.setFont(fo_l);
        gbc.gridy = 4;
        p1.add(l_donVi,gbc);
        
        l_donGia.setFont(fo_l);
        gbc.gridy = 5;
        p1.add(l_donGia,gbc);
        //--------------------------add Text-----------------------------------
        txt_maNL.setEditable(false);
        txt_maNL.setFont(fo_t);
        txt_maNL.setPreferredSize(new Dimension(200,25));
        gbc.gridx=1;
        gbc.gridy=0;
        p1.add(txt_maNL,gbc);
        
        txt_tenNL.setFont(fo_t);
        txt_tenNL.setPreferredSize(new Dimension(200,25));
        gbc.gridy=1;
        p1.add(txt_tenNL,gbc);
        
        dc_ngayNhap.setFont(fo_t);
        dc_ngayNhap.setPreferredSize(new Dimension(200,25));
        gbc.gridy=2;
        p1.add(dc_ngayNhap,gbc);
        
        spr_soLuong.setFont(fo_t);
        spr_soLuong.setPreferredSize(new Dimension(200,25));
        gbc.gridy=3;
        p1.add(spr_soLuong,gbc);
        
        txt_donVi.setFont(fo_t);
        txt_donVi.setPreferredSize(new Dimension(200,25));
        gbc.gridy=4;
        p1.add(txt_donVi,gbc);
        
        spr_donGia.setFont(fo_t);
        spr_donGia.setPreferredSize(new Dimension(200,25));
        gbc.gridy=5;
        p1.add(spr_donGia,gbc);
        //--------------------------add Button-----------------------------------
        JPanel j_b = new JPanel();
        j_b.setPreferredSize(new Dimension(300,200));
        j_b.setLayout(new FlowLayout(100,10,10));
        
        btn_del.setBackground(Color.WHITE);
        btn_edit.setBackground(Color.WHITE);
        btn_add.setBackground(Color.WHITE);
        btn_clear.setBackground(Color.WHITE);
        j_b.add(btn_add);
        j_b.add(btn_edit);
        j_b.add(btn_del);
        j_b.add(btn_clear);
        btn_add.addActionListener(this);
        btn_edit.addActionListener(this);
        btn_del.addActionListener(this);
        
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        p1.add(j_b,gbc);
        //</editor-fold>
        model.addColumn("Mã nguyên liệu");
        model.addColumn("Tên nguyên liệu");
        model.addColumn("Ngày nhập");
        model.addColumn("Số lượng");
        model.addColumn("Đơn vị tính");
        model.addColumn("Đơn giá");
        
        this.add(new JScrollPane(td),BorderLayout.CENTER);
        this.add(tile,BorderLayout.NORTH);
        this.add(p1,BorderLayout.WEST);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
    }
    
}
