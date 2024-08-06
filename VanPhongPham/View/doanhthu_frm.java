
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package VanPhongPham.View;

/**
 *
 * @author ntd
 */
import VanPhongPham.View.menu;
import java.beans.PropertyChangeEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import com.toedter.calendar.JDateChooser;

import com.toedter.calendar.JMonthChooser;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import VanPhongPham.Controller.doanhthu_dao;

public class doanhthu_frm extends JFrame implements ActionListener{
    public  JTable table;
    public JButton btnXuatExcel,btnDoanhThu,btnBieuDo;
    public DefaultTableModel tableModel;
    public JComboBox comboBox;
    public JDateChooser dateChooser1, dateChooser2;
    public String datestring1,datestring2;
    public JFreeChart chart; public ChartPanel chartPanel ;
    
   // public int 

    public doanhthu_frm() {
        setTitle("Thống kê doanh thu");
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 600));

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel leftPanel = new JPanel(new BorderLayout());
        tableModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableModel.addColumn("Doanh thu");
        tableModel.addColumn("Thời gian");
        tableModel.addColumn("Số hóa đơn");
        table = new JTable(tableModel);
        leftPanel.add(new JScrollPane(table), BorderLayout.CENTER);
        table.setFillsViewportHeight(true);
        JPanel rightPanel = new JPanel(new BorderLayout());
        doanhthu_dao c = new doanhthu_dao();
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        chart  = ChartFactory.createBarChart("Biểu đồ doanh thu", "Category", "Value",dataset );
        chartPanel = new ChartPanel(chart);
        JScrollPane scrollPane = new JScrollPane(chartPanel);
        rightPanel.add(chartPanel, BorderLayout.CENTER);
        JPanel bottomPanel = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // JComboBox
        comboBox = new JComboBox(new String[]{"Ngày", "Tháng", "Năm"});
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3; // Span across 3 columns
        gbc.fill = GridBagConstraints.HORIZONTAL; // Fill horizontally
        bottomPanel.add(comboBox, gbc);
        JPanel choosertime = new JPanel(new GridLayout(2, 2, 5,5));
        choosertime.add(new Label("Start:"));
        choosertime.add(new Label("End:"));
        dateChooser1 = new JDateChooser();
        choosertime.add(dateChooser1);      
        dateChooser2 = new JDateChooser(); 
        choosertime.add(dateChooser2);        
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        bottomPanel.add(choosertime, gbc);

        // JButton 1
        btnDoanhThu = new JButton("Doanh Thu");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        bottomPanel.add(btnDoanhThu, gbc);

        // JButton 2
        btnBieuDo = new JButton("Biểu đồ");
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        bottomPanel.add(btnBieuDo, gbc);

        // JButton 3
        btnXuatExcel = new JButton("Xuất Excel");
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        bottomPanel.add(btnXuatExcel, gbc);

        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(rightPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        btnDoanhThu.addActionListener(this);
        btnBieuDo.addActionListener(this);
        btnXuatExcel.addActionListener(this);
         comboBox.addItemListener(new ItemListener() {
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            // Lấy mục được chọn
            String selectedItem = comboBox.getSelectedItem().toString();
            
            if(selectedItem.equalsIgnoreCase("ngày"))
            {
               dateChooser1.setDate(null);dateChooser2.setDate(null);
                 editjdate(dateChooser1,0);
                 editjdate(dateChooser2,0);
            }
            if(selectedItem.equalsIgnoreCase("tháng"))
            {
                dateChooser1.setDate(null);dateChooser2.setDate(null);
                 editjdate(dateChooser1,1);
                 editjdate(dateChooser2,1);
            }
            if(selectedItem.equalsIgnoreCase("năm"))
            {
                dateChooser1.setDate(null);dateChooser2.setDate(null);
                 editjdate(dateChooser1,2);
                 editjdate(dateChooser2,2);
            }
        }
    }
});

            addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Perform actions when the window is closing
                System.out.println("Window is closing...");
                // Tạo một cửa sổ mới của lớp menu
                menu d = new menu();
                d.prepare();
                d.setVisible(true);

            }
        });
        
    }
    public void actionPerformed(ActionEvent e) {
        JButton btn = (JButton) e.getSource();
        if(btn.equals(btnDoanhThu))
        {
            btndoanhthu_action();
        }
        if(btn.equals(btnBieuDo)){
            btnbieudo_action();
        }
        if(btn.equals(btnXuatExcel)){
            btnxuatexcel_action();
        }
    }
    
    public void editjdate(final JDateChooser monthYearChooser, int kt ){
         SimpleDateFormat sdf = new SimpleDateFormat() ;
        if(kt != 0)
        {
            monthYearChooser.setMinSelectableDate(Calendar.getInstance().getTime());
            monthYearChooser.setMaxSelectableDate(Calendar.getInstance().getTime());
        }
        else{
            monthYearChooser.setMinSelectableDate(null);
            monthYearChooser.setMaxSelectableDate(null);
            sdf = new SimpleDateFormat("dd-MM-yyyy");
        }

        // Đặt định dạng hiển thị cho ô text

        if(kt == 1)
        {
            sdf = new SimpleDateFormat("MM-yyyy");
            
        }
        if(kt == 2) sdf = new SimpleDateFormat("yyyy");
        monthYearChooser.setDateFormatString(sdf.toPattern());
        //if(kt == 1){
            monthYearChooser.getJCalendar().getMonthChooser().addPropertyChangeListener(new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent evt) {
                if ("month".equals(evt.getPropertyName())) {
                    int newMonth = (Integer) evt.getNewValue();
                    int currentYear = monthYearChooser.getJCalendar().getYearChooser().getYear();
                    Calendar newDate = Calendar.getInstance();
                    newDate.set(currentYear, newMonth, 1);
                    monthYearChooser.setDate(newDate.getTime());
                }
            }
        });
      //  } 
        //if(kt == 2){
        monthYearChooser.getJCalendar().getYearChooser().addPropertyChangeListener(new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent evt) {
                if ("year".equals(evt.getPropertyName())) {
                    int newYear = (Integer) evt.getNewValue();
                    int currentMonth = monthYearChooser.getJCalendar().getMonthChooser().getMonth();
                    Calendar newDate = Calendar.getInstance();
                    newDate.set(newYear, currentMonth, 1);
                    monthYearChooser.setDate(newDate.getTime());
                }
            }
        }); }
   // }
    public void btndoanhthu_action(){
         Date Date1 = dateChooser1.getDate();
         Date Date2 = dateChooser1.getDate();
            if (Date1 != null && Date2 != null && !comboBox.getSelectedItem().toString().isEmpty() ) 
                {
                    doanhthu_dao c = new doanhthu_dao();
                    c.doanhthu(this);
                }
            else JOptionPane.showMessageDialog(null,"bạn đã nhập thiếu dữ liệu");
                 
    }
  public void updateChart() {
    doanhthu_dao c = new doanhthu_dao();
    chart = c.createChart(this);
    chartPanel.setChart(chart);
    chartPanel.repaint(); 
}

public void btnbieudo_action(){
    if(table.getRowCount()>0)
    updateChart(); 
    else JOptionPane.showMessageDialog(null,"mời bạn chọn doanh thu trước ");
}
    public void btnxuatexcel_action(){
     doanhthu_dao c = new doanhthu_dao();
     c.xuatexcel_action(this);
    }
    public static void main(String[] args) {
       doanhthu_frm r = new doanhthu_frm();
        //Controller c = new Controller();
    }
}

