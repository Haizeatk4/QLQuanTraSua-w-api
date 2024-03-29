/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package frmView;

import Controller.NhanVienData;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import org.apache.hc.core5.http.ParseException;

/**
 *
 * @author ADMIN
 */
public class frmHome extends JFrame{
    //<editor-fold defaultstate="collapsed" desc="Var">
    JLabel j_tile = new JLabel("QUÁN TRÀ SỮA");
    JLabel l_pic = new JLabel();
    JPanel p1 = new JPanel();
    JButton btn_qlnv;
    JButton btn_qlnl;
    JButton btn_qlhd;
    JButton btn_tk;
    //</editor-fold>

    public frmHome() throws IOException {
        this.setTitle("Quản lý quán trà sữa");
        this.setSize(1185,560);
        this.setLayout(new BorderLayout());
        Border g_pad = BorderFactory.createEmptyBorder(10,10,10,10);
        
        Font fo_l = new Font("Serif", Font.BOLD,30);
        j_tile.setBorder(g_pad);
        j_tile.setFont(fo_l);
        j_tile.setHorizontalAlignment(JLabel.CENTER);
        this.add(j_tile,BorderLayout.NORTH);
        
        File file = new File("");
        String currentDirectory = file.getAbsolutePath() + "/src/main/java";
        BufferedImage myPicture = ImageIO.read(new File(currentDirectory + "/Image/anhgioithieu.jpg"));
        l_pic = new JLabel(new ImageIcon(myPicture));
        this.add(l_pic,BorderLayout.CENTER);
        
        //<editor-fold defaultstate="collapsed" desc="Panel1">
        p1.setLayout(new GridLayout(4,1,10,10));
        
        ImageIcon nvi = new ImageIcon(currentDirectory + "/Image/iconNhanVien2.png");
        btn_qlnv = new JButton("QUẢN LÝ NHÂN VIÊN",nvi);
        Font fo_b = new Font("Serif", Font.BOLD,20);
        btn_qlnv.setBackground(Color.WHITE);
        btn_qlnv.setFont(fo_b);
        
        ImageIcon nli = new ImageIcon(currentDirectory + "/Image/iconNguyenLieu.png");
        btn_qlnl = new JButton("QUẢN LÝ NGUYÊN LIỆU",nli);
        btn_qlnl.setBackground(Color.WHITE);
        btn_qlnl.setFont(fo_b);
        
        ImageIcon hdi = new ImageIcon(currentDirectory + "/Image/iconHoaDon.png");
        btn_qlhd = new JButton("QUẢN LÝ HÓA ĐƠN",hdi);
        btn_qlhd.setBackground(Color.WHITE);
        btn_qlhd.setFont(fo_b);
        
        ImageIcon tki = new ImageIcon(currentDirectory + "/Image/iconThongKe.png");
        btn_tk = new JButton("THỐNG KÊ",tki);
        btn_tk.setBackground(Color.WHITE);
        btn_tk.setFont(fo_b);
        
        p1.add(btn_qlnv);
        p1.add(btn_qlnl);
        p1.add(btn_qlhd);
        p1.add(btn_tk);
        
        //</editor-fold>
        Border pad = BorderFactory.createLineBorder(Color.BLACK, 2);
        p1.setBorder(pad);
        p1.setPreferredSize(new Dimension(443,448));
        this.add(p1,BorderLayout.EAST);
        
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        //<editor-fold defaultstate="collapsed" desc="Event">
        btn_qlnv.addActionListener(((e) -> {
            try {
                NhanVienData frmQLNV = new NhanVienData();
                dispose();
            } catch (SQLException | IOException | ParseException | URISyntaxException ex) {
                Logger.getLogger(frmHome.class.getName()).log(Level.SEVERE, null, ex);
            }
        }));
        //</editor-fold>
    }
}
