/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package VanPhongPham.View;

import VanPhongPham.View.menu;
//import com.orsoncharts.util.json.parser.ParseException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
//import java.util.ArrayList;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.border.EmptyBorder;
import VanPhongPham.Controller.hoadon_dao;

public class hoadon_frm extends JFrame  implements ActionListener , ItemListener{
    public  JTextField txtMaHoaDon, txtTongTien, txtDiaChi, txttimkiem,txtmadh;
    public  JButton  btnSua, btnXoa, btnXoaTrang, btnxuatexcel, btntimkiem, btnsapxep, btnNhap;
    public  DefaultTableModel model;
    public  JTable table;
    public JRadioButton tt_true, tt_false;
    public  JComboBox cbmakh,cbsapxep,phanloaitimkiem,cbthutu;
    public  JDateChooser txtNgayTao;
    public String col ="" , thutu = "", coltk =""; 
    public hoadon_frm() {
        setTitle("Quản lý hóa đơn");
        setSize(800, 600);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        hoadon_dao c = new hoadon_dao();
        JPanel panel = new JPanel(new BorderLayout());
        btnNhap = new JButton("Nhập excel");
        JPanel inputPanel = new JPanel(new GridLayout(9, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        inputPanel.add(new JLabel("Mã hóa đơn:"));
        txtMaHoaDon = new JTextField();
        txtMaHoaDon.setEditable(false);
        inputPanel.add(txtMaHoaDon);

        inputPanel.add(new JLabel("Mã khách hàng:"));
        cbmakh = new JComboBox();
        List<String> nguon= new ArrayList<String>(); 
        nguon = c.laynguoncb();
        for(String str : nguon)
        {
            cbmakh.addItem(str);
        }
        inputPanel.add(cbmakh);
       
         inputPanel.add(new JLabel("Mã Đơn hàng:"));
        txtmadh = new JTextField();
        txtmadh.setEditable(false);
        inputPanel.add(txtmadh);

        inputPanel.add(new JLabel("Ngày tạo:"));
        txtNgayTao = new JDateChooser();
        inputPanel.add(txtNgayTao);

        inputPanel.add(new JLabel("Tổng tiền:"));
        txtTongTien = new JTextField();
        inputPanel.add(txtTongTien);

        inputPanel.add(new JLabel("Trạng thái:"));
        JPanel trangthaipanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        tt_true = new JRadioButton("đã thanh toán");
        trangthaipanel.add(tt_true);
        tt_false = new JRadioButton("chưa thanh toán");
        trangthaipanel.add(tt_false);
        inputPanel.add(trangthaipanel);

        inputPanel.add(new JLabel("Địa chỉ giao hàng:"));
        txtDiaChi = new JTextField();
        inputPanel.add(txtDiaChi);

        inputPanel.add(new JLabel("Tìm kiếm:"));
        JPanel searchPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        phanloaitimkiem = new JComboBox(new String[]{"mã hóa đơn", " mã khach hàng" ,
                "thời gian","địa chỉ giao hàng"});
        gbc.gridx = 1;
        gbc.weightx = 0.5;
        searchPanel.add(phanloaitimkiem, gbc);
        gbc.insets = new Insets(0, 15, 0, 0);
        txttimkiem = new JTextField();
        gbc.gridx = 2;
        gbc.weightx = 1.0;
        searchPanel.add(txttimkiem, gbc);
        inputPanel.add(searchPanel);
        
        inputPanel.add(new JLabel("Sắp xếp"));
        Panel thutusapxep = new Panel(new GridLayout(1, 2 ,10,10));

         cbsapxep = new JComboBox(new String[]{"mã hóa đơn", "mã khách hàng", "tổng tiền","địa chỉ","thời gian"});
        thutusapxep.add(cbsapxep);
        cbthutu = new JComboBox(new String[]{"Tăng", "Giảm"});
        thutusapxep.add(cbthutu);
        inputPanel.add(thutusapxep);
       
        panel.add(inputPanel, BorderLayout.NORTH);
        model = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        model.addColumn("Mã hóa đơn");
        model.addColumn("Mã khách hàng");
        model.addColumn("Mã đơn hàng");
        model.addColumn("Ngày tạo");
        model.addColumn("Tổng tiền");
        model.addColumn("Trạng thái");
        model.addColumn("Địa chỉ giao hàng");

        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);
        c.laynguonchotable(this);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,10, 10));
        buttonPanel.setBorder(new EmptyBorder(20, 20, 20, 20)); 
        btnSua = new JButton("Sửa");
        btnXoa = new JButton("Xóa");
        btnXoaTrang = new JButton("Xóa trắng");
        btnxuatexcel = new JButton("Xuất Excel");
        btntimkiem = new JButton("Tìm kiếm");
        btnsapxep = new JButton("Sắp xếp");

        buttonPanel.add(btnSua);
        buttonPanel.add(btnXoa);
        buttonPanel.add(btnXoaTrang);
        buttonPanel.add(btnxuatexcel);
        buttonPanel.add(btntimkiem);
        buttonPanel.add(btnsapxep);
        buttonPanel.add(btnNhap);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        add(panel);
        setVisible(true);
           table.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
               int i = table.getSelectedRow();
                txtMaHoaDon.setText(model.getValueAt(i, 0).toString().trim());
                cbmakh.setSelectedItem(model.getValueAt(i, 1).toString().trim());
                txtmadh.setText(model.getValueAt(i, 2).toString().trim());
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Định dạng của chuỗi ngày tháng

                try {
                    Date date = dateFormat.parse(model.getValueAt(i, 3).toString().trim()); // Chuyển đổi chuỗi thành đối tượng Date
                    txtNgayTao.setDate(date);
                } catch (java.text.ParseException g) {g.printStackTrace();}
                txtTongTien.setText(model.getValueAt(i, 4).toString());
                if(model.getValueAt(i, 5).toString().trim().equalsIgnoreCase("đã thanh toán"))
                    tt_true.setSelected(true);
                else tt_false.setSelected(true);
                txtDiaChi.setText(model.getValueAt(i, 6).toString().trim());
            }
                  public void mousePressed(MouseEvent e) {
                
            }

            public void mouseReleased(MouseEvent e) {
                
            }

            public void mouseEntered(MouseEvent e) {
                
            }

            public void mouseExited(MouseEvent e) {
                
            }
        });

        cbsapxep.addItemListener(this);
        cbthutu.addItemListener(this);
        phanloaitimkiem.addItemListener(this);
        btnSua.addActionListener(this);
        btnXoaTrang.addActionListener(this);
        btnXoa.addActionListener(this);
        btntimkiem.addActionListener(this);
        btnsapxep.addActionListener(this);
        btnxuatexcel.addActionListener(this);
        btnNhap.addActionListener(this);
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
    
    
    public void actionItemListener(ItemEvent e){
        if (e.getStateChange() == ItemEvent.SELECTED){
        JComboBox cb = (JComboBox) e.getSource();
        if(cb.equals(cbsapxep))
        {
            if(cbsapxep.getSelectedIndex()==0)
            {
                col = "ma_hoa_don";
            }
             if(cbsapxep.getSelectedIndex()==1)
            {
                col = "maKH";
            }
             if(cbsapxep.getSelectedIndex()==2)
            {
                col = "tong_tien";
            }
             if(cbsapxep.getSelectedIndex()==3)
            {
                col = "dia_chi_giao_hang";
            }
            if(cbsapxep.getSelectedIndex()==4)
            {
                col = "ngay_tao";
            }
        }
        if(cb.equals(cbthutu)){
             if(cbthutu.getSelectedIndex()==0)
            {
                thutu = "ASC"; 
            }
             else thutu = "DESC";
        }
        if(cb.equals(phanloaitimkiem)){
//             coltk = "";
             if(phanloaitimkiem.getSelectedIndex()==0)
            {
                coltk = "ma_hoa_don";
            }
            if(phanloaitimkiem.getSelectedIndex()==1)
            {
                coltk = "maKH";
            }
            if(phanloaitimkiem.getSelectedIndex()==2)
            {
                coltk = "ngay_tao";
            }
             if(phanloaitimkiem.getSelectedIndex()==3)
            {
                coltk = "dia_chi_giao_hang";
            }
        }
    }}
    public void actionPerformed(ActionEvent e) {
        JButton btn = (JButton) e.getSource();
        if(btn.equals(btnSua))
        {
            btnsua_action();
        }
        if(btn.equals(btnXoa)){
            btnxoa_action();
        }
        if(btn.equals(btnXoaTrang)){
            btnxoatrang_action();
        }
        if(btn.equals(btnsapxep)){
            btnsapxep_action();
        }
        if(btn.equals(btntimkiem)){
            btntimkiem_action();
        }
        if(btn.equals(btnxuatexcel)){
            btnxuatexcel_action();
        }
        if(btn.equals(btnNhap)){
            btnNhap_action();
        }

    }
    public void btnNhap_action(){
        hoadon_dao c = new hoadon_dao();
      c.nhapexcel(this);
    }
        public void btnsua_action(){
      hoadon_dao c = new hoadon_dao();
      c.sua_action(this);
    }
        public void btnxoa_action(){
     hoadon_dao c = new hoadon_dao();
     c.xoa_action(this);
    }
        public void btnxoatrang_action(){
        hoadon_dao c = new hoadon_dao();
        c.xoa_trang_action(this);
    }
       public void btnsapxep_action(){
     hoadon_dao c = new hoadon_dao();
     c.sapxep_action(col, thutu, this);
    }
       public void btntimkiem_action(){
      hoadon_dao c = new hoadon_dao();
      //System.out.println(" a "+coltk);
      c.timkiem_action(coltk, txttimkiem.getText(), this);
    }
       public void btnxuatexcel_action(){
     hoadon_dao c = new hoadon_dao();
     c.xuatexcel_action(this);
    }
    public static void main(String[] args) {
        hoadon_frm bf = new hoadon_frm();
    }

    public void itemStateChanged(ItemEvent e) {
       actionItemListener(e);
    }
}
