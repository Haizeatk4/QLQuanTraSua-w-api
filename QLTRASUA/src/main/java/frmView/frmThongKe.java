/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package frmView;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author ad
 */
public class frmThongKe extends JFrame{
    public frmThongKe(){
        this.setSize(1200,800);
        this.setLocation(50, 70);
        this.setTitle("Thống kê");
        this.setLayout(new BorderLayout());
        
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(100000, "", "2024-02");
        dataset.addValue(108000, "", "2024-03");
        dataset.addValue(222000, "", "2024-04");
        JFreeChart chart = ChartFactory.createLineChart("Doanh thu", "Tháng", "đ", dataset);
        ChartPanel chartPanel = new ChartPanel( chart );
        chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
        setContentPane( chartPanel );
        this.setVisible(true);
    }
    public static void main(String[] args) {
        frmThongKe frm = new frmThongKe();
    }
}