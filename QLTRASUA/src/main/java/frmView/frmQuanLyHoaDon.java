/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package frmView;

import Model.QLHoaDon;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import org.apache.hc.core5.http.ParseException;

/**
 *
 * @author ADMIN
 */
public class frmQuanLyHoaDon extends JFrame implements ActionListener{
    //<editor-fold defaultstate="collapsed" desc="Var">
    boolean isSelected = false;
    int item_id,mode=0;
    private ArrayList<QLHoaDon> arr = new ArrayList();
    //---------------------------------------------------------------------------
    JLabel tile = new JLabel("QUẢN LÝ HÓA ĐƠN");
    
    JPanel p1 = new JPanel();
    JTextField txt_search = new JTextField();
    JButton btn_search = new JButton("TÌM KIẾM");
    
    JPanel p2 = new JPanel();
    JButton btn_detail = new JButton("CHI TIẾT");
    JButton btn_newOrder = new JButton("HÓA ĐƠN MỚI");
    JButton btn_thanhToan = new JButton("XÁC NHẬN THANH TOÁN HÓA ĐƠN");
    
    JTable td = new JTable();
    DefaultTableModel model = (DefaultTableModel) td.getModel();
    
    JMenuBar mb = new JMenuBar();
    JMenu m_hethong = new JMenu("Hệ thống");
    JMenuItem mi_exit = new JMenuItem("Thoát");
    JLabel l_preAcc = new JLabel("Tài khoản: ");
    public JLabel l_acc = new JLabel();
    //</editor-fold>
    public frmQuanLyHoaDon(String tk) {
        this.setSize(1200,800);
        this.setLocation(50, 70);
        this.setTitle("Quản lý hóa đơn");
        this.setLayout(new BorderLayout());
        Border pad = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        //<editor-fold defaultstate="collapsed" desc="Menu">
        l_acc.setText(tk);
        m_hethong.add(mi_exit);
        mb.add(m_hethong);
        mb.add(l_preAcc);
        mb.add(l_acc);
        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="Panel01">
        p1.setBorder(pad);
        p1.setLayout(new GridLayout(5, 1, 10, 10));
        
        btn_detail.setBackground(Color.WHITE);
        btn_detail.setEnabled(false);
        btn_newOrder.setBackground(Color.WHITE);
        btn_thanhToan.setBackground(Color.WHITE);
        btn_thanhToan.setEnabled(false);
        p1.add(btn_detail);
        p1.add(btn_newOrder);
        p1.add(btn_thanhToan);
        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="Panel02">
        p2.setLayout(new BorderLayout());
        
        p2.add(mb,BorderLayout.NORTH);
        Font fo_tile = new Font("Serif", Font.BOLD,32);
        tile.setFont(fo_tile);
        tile.setHorizontalAlignment(JLabel.CENTER);
        tile.setBorder(pad);
        //---------------------------------------------------------------------------------
        JPanel p21 = new JPanel();
        p21.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc= new GridBagConstraints();
        gbc.weightx = 1;
        gbc.weighty = 1.0;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        
        btn_search.setBackground(Color.WHITE);
        btn_search.setPreferredSize(new Dimension(75,25));
        gbc.gridx = 0;
        gbc.gridy = 0;
        p21.add(btn_search,gbc);
        btn_search.addActionListener(this);
        
        Font fo_t = new Font("Serif", Font.PLAIN,14);
        txt_search.setFont(fo_t);
        txt_search.setPreferredSize(new Dimension(1000,25));
        gbc.gridx=1;
        p21.add(txt_search,gbc);
        //---------------------------------------------------------------------------------
        p2.add(tile,BorderLayout.CENTER);
        p2.add(p21,BorderLayout.SOUTH);
        //</editor-fold>
        model.addColumn("Mã hóa đơn");
        model.addColumn("Tên nhân viên");
        model.addColumn("Ngày lập");
        model.addColumn("Bàn");
        model.addColumn("Thành tiền");
        model.addColumn("Tình trạng");
        
        this.add(new JScrollPane(td),BorderLayout.CENTER);
        this.add(p1,BorderLayout.WEST);
        this.add(p2,BorderLayout.NORTH);
        //<editor-fold defaultstate="collapsed" desc="Event">
        final ListSelectionModel sel = td.getSelectionModel();
        sel.addListSelectionListener((ListSelectionEvent e) -> {
            if(!sel.isSelectionEmpty()){
                if(!sel.isSelectionEmpty()){
                    item_id=sel.getMinSelectionIndex();
                    if(arr.get(item_id).getTinhTrang().equals("Chưa thanh toán")){
                        btn_detail.setEnabled(true);
                        btn_thanhToan.setEnabled(true);
                    } else {
                        btn_detail.setEnabled(false);
                        btn_thanhToan.setEnabled(false);
                    }
                    isSelected=true;
                } else {
                    isSelected=false;
                }
            }
        });
        mi_exit.addActionListener((e) -> {
            try {
                frmHome home = new frmHome(tk);
                home.setVisible(true);
                dispose();
            } catch (IOException ex) {}
        });
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    frmHome home = new frmHome(tk);
                    home.setVisible(true);
                } catch (IOException ex) {}
            }
        });
        btn_newOrder.addActionListener((e) -> {
            try {
                frmChiTietHoaDon frm = new frmChiTietHoaDon(tk,"", arr.get(item_id).getTenNhanVien());
            } catch (ParseException | IOException ex) {}
        });
        //</editor-fold>
    }
    //<editor-fold defaultstate="collapsed" desc="Event2">
    public void searchListener (ActionListener log){
        btn_search.addActionListener(log);
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Method">
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
    @Override
    public void actionPerformed(ActionEvent e) {}
}
