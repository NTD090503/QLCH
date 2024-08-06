package VanPhongPham.View;

import VanPhongPham.View.menu;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import org.json.JSONArray;
import org.json.JSONException;
//import javax.swing.JTable;
import org.json.JSONObject;
import VanPhongPham.Controller.donhang_dao;

public class donhang_frm extends JFrame implements ActionListener {
    public  JTextField txtmadh;
    public JSpinner txtsoluong, txtgiamgia;
    public JList chuoimasp;
    public  JComboBox cbmasp;
    public DefaultListModel listModel;
    public  JButton btnAdd,btnClearsp, btnEdit, btnDelete, btnClear, btnSearch, btnExport,btnAddsp,btnEditsp,btnDeletesp,btnxacnhan,btntinhtien;
    public  JTable tabledh , tablesp;
    public DefaultTableModel modeldh , modelsp;

    public donhang_frm() throws JSONException {
        initComponents();
    }

    private void initComponents() throws JSONException {
       // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Order Management");
        setSize(800, 600);
        JPanel mainPanel = new JPanel();
        SpringLayout layout = new SpringLayout();
        mainPanel.setLayout(layout);

        JLabel lblOrderID = new JLabel("Mã đơn hàng:");
        txtmadh = new JTextField(10);
        txtmadh.setEditable(false);
        mainPanel.add(lblOrderID);
        mainPanel.add(txtmadh);

        JLabel lblProductID = new JLabel("Mã sản phẩm:");
        cbmasp = new JComboBox();
        cbmasp.setPreferredSize(new Dimension(100, 25));
         List<String> nguon= new ArrayList<String>();
         final donhang_dao c = new donhang_dao();
        nguon = c.laynguonlistsp();
        for(String str : nguon)
        {
            cbmasp.addItem(str);
        }
        mainPanel.add(lblProductID);
        mainPanel.add(cbmasp);
        
        JLabel lblStringProductID = new JLabel("<html>Sản phầm<br>đã chọn</html>");
        chuoimasp = new JList();
        listModel = new DefaultListModel();
        chuoimasp.setModel(listModel);
        chuoimasp.setEnabled(false);
        chuoimasp.setFixedCellWidth(75); 
        chuoimasp.setFixedCellHeight(20);
        JScrollPane chuoimasppane = new JScrollPane(chuoimasp);
        chuoimasppane.setPreferredSize(new Dimension(175, 40));
        mainPanel.add(lblStringProductID);
        mainPanel.add(chuoimasppane);
        
        JLabel lblQuality = new JLabel("Số Lượng:");
        txtsoluong = new JSpinner();
        txtsoluong.setPreferredSize(new Dimension(100, 25));
        txtsoluong.addChangeListener(new ChangeListener() {
        public void stateChanged(ChangeEvent e) {
                int value = (Integer) txtsoluong.getValue();
                donhang_dao c = new donhang_dao();
                    int slm = c.layslmax(cbmasp.getSelectedItem().toString());
                if (value > slm ) {
                    JOptionPane.showMessageDialog(null,"bạn đã nhập qua số lượng, hiện tại chỉ có "+slm+" sản phẩm");
                    txtsoluong.setValue(0);
                }
                      if (value <0 ) {
                    JOptionPane.showMessageDialog(null,"bạn đã nhập sai giá trị");
                    txtsoluong.setValue(0);
                }
            }
        });
        
        mainPanel.add(lblQuality);
        mainPanel.add(txtsoluong);
        
        JLabel lblDiscount = new JLabel("Giảm giá:");
        txtgiamgia = new JSpinner();
        txtgiamgia.setPreferredSize(new Dimension(100, 25));
        txtgiamgia.addChangeListener(new ChangeListener() {
        public void stateChanged(ChangeEvent e) {
                int value = (Integer) txtgiamgia.getValue();
                if (value > 100 ) {
                    JOptionPane.showMessageDialog(null,"bạn đã nhập qúa giá trị");
                    txtsoluong.setValue(0);
                }
                      if (value <0 ) {
                    JOptionPane.showMessageDialog(null,"bạn đã nhập sai giá trị");
                    txtsoluong.setValue(0);
                }
            }
        });
        mainPanel.add(lblDiscount);
        mainPanel.add(txtgiamgia);

        JPanel tablePanelsp = new JPanel( new BorderLayout());
        //tablePanelsp.setSize(20, 15);
        modelsp = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablesp = new JTable(modelsp);
        modelsp.addColumn("mã sản phẩm");
        modelsp.addColumn("Tên sản phẩm");
        modelsp.addColumn("số lượng");
       // tablesp.setPreferredScrollableViewportSize(new Dimension(400, 300));
        tablesp.addMouseListener(new MouseListener() {

            public void mouseClicked(MouseEvent e) {
                cbmasp.setSelectedItem(modelsp.getValueAt(tablesp.getSelectedRow(), 0));
                txtsoluong.setValue(Integer.parseInt(modelsp.getValueAt(tablesp.getSelectedRow(), 2).toString()));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet.");
            }
        });
        JScrollPane ScrollPanetablesp = new JScrollPane(tablesp);
        ScrollPanetablesp.setPreferredSize(new Dimension(450, 150));
        //
        btnAddsp = new JButton("Thêm sản phẩm");
        btnEditsp = new JButton("sửa sản phẩm");
        btnDeletesp = new JButton("Xóa sản phẩm");
        btnClearsp = new JButton("Clear");
        btnxacnhan = new JButton("Xác nhận");
        JPanel btnsppane = new JPanel(new FlowLayout(1, 10, 10));
        btnsppane.add(btnAddsp);
        btnsppane.add(btnDeletesp);
        btnsppane.add(btnClearsp);
        btnsppane.add(btnxacnhan);
        tablePanelsp.add(btnsppane , BorderLayout.SOUTH);
        tablePanelsp.add(ScrollPanetablesp, BorderLayout.CENTER);
        
        JPanel tabledhPanel = new JPanel(new BorderLayout());
        modeldh = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabledh = new JTable(modeldh);
        modeldh.addColumn("mã đơn hàng");
        modeldh.addColumn("sản phẩm");
        //modeldh.addColumn("số lượng");
        modeldh.addColumn("mã giảm giá");
         c.laynguonchotabledh(this);
         tabledh.addMouseListener(new MouseListener() {

            public void mouseClicked(MouseEvent e) {
                int i = tabledh.getSelectedRow();
                //System.out.println("chi so dong trong table:"+ i);
                txtmadh.setText(modeldh.getValueAt(i, 0).toString());
                txtgiamgia.setValue(Integer.parseInt(modeldh.getValueAt(i, 2).toString()));
                try {
                    testls();;
                } catch (JSONException ex) {
                    Logger.getLogger(donhang_frm.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

            public void mousePressed(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet.");
            }

            public void mouseReleased(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet.");
            }

            public void mouseEntered(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet.");
            }

            public void mouseExited(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet.");
            }
        });
        JScrollPane tabledhscr = new JScrollPane(tabledh);
        tabledhscr.setPreferredSize(new Dimension(760, 250));
        tabledhPanel.add(tabledhscr, BorderLayout.CENTER);
        
        btnAdd = new JButton("Add");
        btnEdit = new JButton("Edit");
        btnDelete = new JButton("Delete");
        btnClear = new JButton("Clear");
        btnSearch = new JButton("Search");
        btnExport = new JButton("Export");
        btntinhtien = new JButton("Tính tiền");
        

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 7, 15 , 15));
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnEdit);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnClear);
        //buttonPanel.add(btnSearch);
        buttonPanel.add(btnExport);
        buttonPanel.add(btntinhtien);
        mainPanel.add(tablePanelsp);
        mainPanel.add(tabledhPanel);
        mainPanel.add(buttonPanel);

        // Set SpringLayout constraints
        layout.putConstraint(SpringLayout.WEST, lblOrderID, 10, SpringLayout.WEST, mainPanel);
        layout.putConstraint(SpringLayout.NORTH, lblOrderID, 10, SpringLayout.NORTH, mainPanel);
        layout.putConstraint(SpringLayout.WEST, txtmadh, 48, SpringLayout.EAST, lblOrderID);
        layout.putConstraint(SpringLayout.NORTH, txtmadh, 10, SpringLayout.NORTH, mainPanel);

        layout.putConstraint(SpringLayout.WEST, lblProductID, 10, SpringLayout.WEST, mainPanel);
        layout.putConstraint(SpringLayout.NORTH, lblProductID, 25, SpringLayout.SOUTH, lblOrderID);
        layout.putConstraint(SpringLayout.WEST, cbmasp, 46 , SpringLayout.EAST, lblProductID);
        layout.putConstraint(SpringLayout.NORTH, cbmasp, 25, SpringLayout.SOUTH, txtmadh);
        
//
        layout.putConstraint(SpringLayout.WEST, lblQuality, 10, SpringLayout.WEST, mainPanel);
        layout.putConstraint(SpringLayout.NORTH, lblQuality, 28, SpringLayout.SOUTH, lblProductID);
        layout.putConstraint(SpringLayout.WEST, txtsoluong, 65, SpringLayout.EAST, lblQuality);
        layout.putConstraint(SpringLayout.NORTH, txtsoluong, 17, SpringLayout.SOUTH, cbmasp);
        
        
        layout.putConstraint(SpringLayout.WEST, lblDiscount, 10, SpringLayout.WEST, mainPanel);
        layout.putConstraint(SpringLayout.NORTH, lblDiscount, 28, SpringLayout.SOUTH, lblQuality);
        layout.putConstraint(SpringLayout.WEST, txtgiamgia, 73, SpringLayout.EAST, lblDiscount);
        layout.putConstraint(SpringLayout.NORTH, txtgiamgia, 17, SpringLayout.SOUTH, txtsoluong);
        
        layout.putConstraint(SpringLayout.WEST, lblStringProductID, 10, SpringLayout.WEST, mainPanel);
        layout.putConstraint(SpringLayout.NORTH, lblStringProductID, 30, SpringLayout.SOUTH, lblDiscount);
        layout.putConstraint(SpringLayout.WEST, chuoimasppane, 42, SpringLayout.EAST, lblStringProductID);
        layout.putConstraint(SpringLayout.NORTH, chuoimasppane, 17, SpringLayout.SOUTH, txtgiamgia);
//
        layout.putConstraint(SpringLayout.WEST, tablePanelsp, 35, SpringLayout.EAST, txtmadh);
        layout.putConstraint(SpringLayout.NORTH, tablePanelsp, 10, SpringLayout.NORTH, mainPanel);
        
        
        layout.putConstraint(SpringLayout.WEST, tabledhPanel, 10, SpringLayout.WEST, mainPanel);
        layout.putConstraint(SpringLayout.NORTH, tabledhPanel, 29, SpringLayout.SOUTH, tablePanelsp);
        
        layout.putConstraint(SpringLayout.WEST, buttonPanel, 65, SpringLayout.WEST, mainPanel);
        layout.putConstraint(SpringLayout.NORTH, buttonPanel, 20, SpringLayout.SOUTH, tabledhPanel);
        

        add(mainPanel);
        setLocationRelativeTo(null);// Center the frame
        this.setVisible(true);
        btnAdd.addActionListener(this);
        btnAddsp.addActionListener(this);
        btnEdit.addActionListener(this);
        btnDelete.addActionListener(this);
        btnExport.addActionListener(this);
        btnDeletesp.addActionListener(this);
        btnSearch.addActionListener(this);
        btntinhtien.addActionListener(this);
        btnxacnhan.addActionListener(this);
        btnClear.addActionListener(this);
        btnClearsp.addActionListener(this);
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
        if(btn.equals(btnAddsp))
        {
            add_sp_action();
        }
        if(btn.equals(btnDeletesp)){
            xoa_sp_action();
        }
        if(btn.equals(btnxacnhan)){
            try {
                xacnhan_sp_action();
            } catch (JSONException ex) {
                Logger.getLogger(donhang_frm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(btn.equals(btnClearsp)){
            clear_sp_action();
        }
        if(btn.equals(btnAdd)){
            try {
                add_action();
            } catch (JSONException ex) {
                Logger.getLogger(donhang_frm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(btn.equals(btnEdit)){
            try {
                edit_action();
            } catch (JSONException ex) {
                Logger.getLogger(donhang_frm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(btn.equals(btnDelete)){
            try {
                delete_action();
            } catch (JSONException ex) {
                Logger.getLogger(donhang_frm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
         if(btn.equals(btnClear)){
            clear_action();
        }
         if(btn.equals(btnExport)){
            export_action();
        }
         if(btn.equals(btnSearch)){
            
        }
         if(btn.equals(btntinhtien)){
            try {
                tt_action();
            } catch (JSONException ex) {
                Logger.getLogger(donhang_frm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
     public void export_action(){
         donhang_dao c = new donhang_dao();
         c.xuatexecl(this);
     }
     public void tt_action() throws JSONException{
         donhang_dao c = new donhang_dao();
         c.tinhtien(this);
     }
     public void add_sp_action(){
         donhang_dao c = new donhang_dao();
         c.add_sp(this);
     }
       public void xoa_sp_action(){
           if(tablesp.getSelectedRow()<0){
            JOptionPane.showMessageDialog(null, "bạn chưa chọn sp để xóa");
            return;
        }
         donhang_dao c = new donhang_dao();
         c.xoa_sp(this);
     }
       public void  clear_sp_action(){
         donhang_dao c = new donhang_dao();
         c.clearsp(this);
       }
         public void xacnhan_sp_action() throws JSONException{
         donhang_dao c = new donhang_dao();
         c.xacnhan(this);
     }
       public void add_action() throws JSONException{
         donhang_dao c = new donhang_dao();
         c.add(this);
     }
       public void delete_action() throws JSONException{
         donhang_dao c = new donhang_dao();
         c.xoa(this);
     }
         public void edit_action() throws JSONException{
         donhang_dao c = new donhang_dao();
         c.edit(this);
     }
           public void clear_action(){
         donhang_dao c = new donhang_dao();
         c.clear(this);
     }
      public void testls() throws JSONException{
         donhang_dao c = new donhang_dao();
         c.laylistkhiclicktbldh(this,Integer.parseInt(modeldh.getValueAt(tabledh.getSelectedRow(), 0).toString()));
         c.laynguontblsptudh(this,Integer.parseInt(modeldh.getValueAt(tabledh.getSelectedRow(), 0).toString()));
     }
           
         
    public static void main(String[] args) throws JSONException {
            donhang_frm orderForm = new donhang_frm();
           
    }
}
