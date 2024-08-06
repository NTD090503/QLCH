/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package VanPhongPham.View;

//import QLThuoc.ControlThuoc;
//import QLThuoc.FormThuoc;
//import QLThuoc.ModelThuoc;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
//import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import VanPhongPham.Controller.product_DAO;
import VanPhongPham.Model.product_model;
import javax.swing.BorderFactory;

/**
 *
 * @author TranNgoc ^.^
 */
public final class product_form implements ActionListener {

    // Thuoc tinh
    JFrame mainFrame;
    JPanel controlPanel;
    JTextField txtMaSP, txtTenSP, txtMaNCC, txtMaLoai, txtDonGia,
            txtSoLuong, txtMoTa, txtTrangThai, txtTimKiem;
    private JLabel lb_MaSP, lb_TenSP, lb_TenNCC, lb_TenLoai, lb_DonGia,
            lb_SoLuong, lb_MoTa, lb_TrangThai, lb_TimKiem;
    public JComboBox cboTenNCC, cboTenDM;
    public JComboBox cboTimKiem;
    JButton btnAdd, btnEdit, btnDelete, btnClear, btnExit, btnSortName, btnXuatExcel;
    JButton btnTimKiem, btnNhap;
    static int row = 0;
    public Object o;
    JTable jtable;
    public DefaultTableModel tableModel;
    JScrollPane scrollPane;
    JLabel lbHien;

    // Phuong thuc
    public product_form() {
        this.prepare();
        this.show();
        this.hienthi_tbl();

    }

    public void prepare() {

        mainFrame = new JFrame("Quản lý sản phẩm");
        mainFrame.setSize(1220, 760);

        txtMaSP = new JTextField();
        txtTenSP = new JTextField();
        txtDonGia = new JTextField();
        txtMoTa = new JTextField();
        txtSoLuong = new JTextField();
        txtTrangThai = new JTextField();
        txtTimKiem = new JTextField();
        addPlaceholder(txtTimKiem, "Bạn cần tìm gì ?");

//      lb_MaSP.setFont(new Font("Arial", Font.PLAIN, 24)); // Đặt font mới với kích cỡ 24
        lb_MaSP = new JLabel("Mã SP");
        lb_TenSP = new JLabel("Tên SP");
        lb_TenNCC = new JLabel("Tên NCC");
        lb_TenLoai = new JLabel("Tên loại");
        lb_DonGia = new JLabel("Đơn giá");
        lb_SoLuong = new JLabel("Số lượng");
        lb_MoTa = new JLabel("Mô tả");
        lb_TrangThai = new JLabel("Trạng thái");
        lb_TimKiem = new JLabel("Tìm kiếm");
        //controlPanel.setBackground(Color.red);

        cboTenDM = new JComboBox();
        cboTenNCC = new JComboBox();
        cboTimKiem = new JComboBox(new String[]{"Mã SP", "Tên SP", "Tên NCC", "Tên loại"});
        btnAdd = new JButton("Add");
        btnEdit = new JButton("Edit");
        btnDelete = new JButton("Delete");
        btnClear = new JButton("Clear");
        btnTimKiem = new JButton("Tìm Kiếm");
        btnExit = new JButton("Exit");
        btnSortName = new JButton("SortName");
        btnXuatExcel = new JButton("Export");
        btnNhap = new JButton("Nhập");

        String column[] = {"Mã sản phẩm", "Tên sản phẩm", "Tên NCC", "Tên loại", "Đơn giá", "Số lượng", "Mô tả", "Trạng thái"};
        tableModel = new DefaultTableModel(column, 0);

        jtable = new JTable(tableModel);
        //jtable.setBounds(30, 40, 200, 300);
        jtable.setPreferredScrollableViewportSize(new Dimension(200, 100));

        scrollPane = new JScrollPane(jtable);

        controlPanel = new JPanel();

        mainFrame.setLayout(new FlowLayout(FlowLayout.CENTER));
        //mainFrame.getContentPane().add(controlPanel);
        mainFrame.add(controlPanel);

        mainFrame.setLocationRelativeTo(null);  // Đặt JFrame ở trung tâm màn hình
        mainFrame.setVisible(true);             // Hiển thị JFrame
//        mainFrame.setResizable(false); // cố định form

        // Thêm sự kiện ActionListener cho các JButton
        btnAdd.addActionListener(this);
        btnEdit.addActionListener(this);
        btnDelete.addActionListener(this);
        btnClear.addActionListener(this);
        btnTimKiem.addActionListener(this);
        btnExit.addActionListener(this);
        btnSortName.addActionListener(this);
        btnXuatExcel.addActionListener(this);
        btnNhap.addActionListener(this);

        // Khóa nút Delete và Edit khi mới bắt đầu
        btnDelete.setEnabled(false);
        btnEdit.setEnabled(false);
        txtTrangThai.setEditable(false);

// hiển thị dữ liệu từ bảng lên ô text
        jtable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                // Mở nút
                btnDelete.setEnabled(true);
                btnEdit.setEnabled(true);

                row = jtable.getSelectedRow();
                // lay du lieu tu cot 0
                o = jtable.getValueAt(row, 0);
                // lấy dl từ bảng lên textfiled
                txtMaSP.setText(jtable.getValueAt(row, 0).toString());
                txtTenSP.setText(jtable.getValueAt(row, 1).toString());
//                cboTenNCC.removeAllItems();
//                cboTenDM.removeAllItems();
//                Object valuemaNCC = jtable.getValueAt(row, 2); // Lấy giá trị thứ tư của hàng được chọn trong bảng
//                product_DAO p_dao = new product_DAO();
//                p_dao.loadDataToComboBox(product_form.this);
                // Đặt giá trị mặc định cho JComboBox bằng giá trị từ hàng được chọn
                cboTenNCC.setSelectedItem(jtable.getValueAt(row, 2));
                cboTenDM.setSelectedItem(jtable.getValueAt(row, 3));
                txtDonGia.setText(jtable.getValueAt(row, 4).toString());
                txtSoLuong.setText(jtable.getValueAt(row, 5).toString());
                txtMoTa.setText(jtable.getValueAt(row, 6).toString());
                txtTrangThai.setText(jtable.getValueAt(row, 7).toString());

                //txtMaSP không được chỉnh sửa: Khóa txtMaSP
                txtMaSP.setEditable(false);
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        //setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        mainFrame.addWindowListener(new WindowAdapter() {
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
        txtSoLuong.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                // Kiểm tra nếu ký tự không phải là số
                if (!Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE) {
                    e.consume();// ko cho phép nhập ký tự
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        txtDonGia.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE) {
                    e.consume();// ko cho phép nhập ký tự
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

    }

    public void show() {
        //Build controlPanel
        int topMg = 10, leftMg = 20, bottomMg = 10, rightMg = 20;
        controlPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 10, 5, 10);
        //// panel tìm kiếm
        JPanel panelTim = new JPanel();
        panelTim.setPreferredSize(new Dimension(1000, 80));

        //panelTim.setBorder(BorderFactory.createEtchedBorder());
        //panelTim.setBorder(new EmptyBorder(topMg, leftMg, bottomMg, rightMg));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;  // giãn cả ngang và dọc
        //panelTim.setPreferredSize(new Dimension(400, 200));
        controlPanel.add(panelTim, gbc);

        // Build panelTim
        panelTim.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 10, 5, 10);
        txtTimKiem.setColumns(20);
        gbc.fill = GridBagConstraints.BOTH;  // giãn cả ngang và dọc
        panelTim.add(txtTimKiem, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.BOTH;  // giãn cả ngang và dọc
        cboTimKiem.setPreferredSize(new Dimension(100, 20));
        panelTim.add(cboTimKiem, gbc);
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.BOTH;  // giãn cả ngang và dọc
        panelTim.add(btnTimKiem, gbc);

        //---Panel txt, lb
        JPanel panel_center = new JPanel();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        controlPanel.add(panel_center, gbc);

        panel_center.setBorder(new EmptyBorder(topMg, leftMg, bottomMg, rightMg));
        panel_center.setLayout(new GridBagLayout());
        panel_center.setPreferredSize(new Dimension(1000, 250));
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 30, 10, 20);
        gbc.anchor = GridBagConstraints.WEST;
        //  x tăng, y const;
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel_center.add(lb_MaSP, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        txtMaSP.setColumns(17);
        gbc.insets = new Insets(10, 30, 10, 20);
        panel_center.add(txtMaSP, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 30, 10, 20);
        panel_center.add(lb_TenSP, gbc);
        gbc.gridx = 3;
        gbc.gridy = 0;
        txtTenSP.setColumns(17);
        gbc.insets = new Insets(10, 30, 10, 20);
        panel_center.add(txtTenSP, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(10, 30, 10, 20);
        panel_center.add(lb_TenNCC, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        cboTenNCC.setPreferredSize(new Dimension(190, 20));
        //cboTenNCC.setSize(10, 13);
        panel_center.add(cboTenNCC, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.insets = new Insets(10, 30, 10, 20);
        panel_center.add(lb_TenLoai, gbc);
        gbc.gridx = 3;
        gbc.gridy = 1;
        cboTenDM.setPreferredSize(new Dimension(190, 20));
        panel_center.add(cboTenDM, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel_center.add(lb_DonGia, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        txtDonGia.setColumns(17);
        gbc.insets = new Insets(10, 30, 10, 20);
        panel_center.add(txtDonGia, gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.insets = new Insets(10, 30, 10, 20);
        panel_center.add(lb_SoLuong, gbc);
        gbc.gridx = 3;
        gbc.gridy = 2;
        txtSoLuong.setColumns(17);
        gbc.insets = new Insets(10, 30, 10, 20);
        panel_center.add(txtSoLuong, gbc);

        //
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel_center.add(lb_MoTa, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        txtMoTa.setColumns(17);
        gbc.insets = new Insets(10, 30, 10, 20);
        panel_center.add(txtMoTa, gbc);

        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.insets = new Insets(10, 30, 10, 20);
        panel_center.add(lb_TrangThai, gbc);
        gbc.gridx = 3;
        gbc.gridy = 3;
        txtTrangThai.setColumns(17);
        gbc.insets = new Insets(10, 30, 10, 20);
        panel_center.add(txtTrangThai, gbc);

        //
        // Panel for Buttons
        JPanel buttonPanel = new JPanel();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 6; // Span four columns
        // đặt thành phần ở giữa ô lưới
        gbc.anchor = GridBagConstraints.CENTER;
//        gbc.insets = new Insets(0, 10, 0, 10);
        panel_center.add(buttonPanel, gbc);
        // đặt khoảng cách hgap: ngang, vgap: dọc
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
//        buttonPanel.setPreferredSize(new Dimension(1000, 250));

//        buttonPanel.setLayout(new GridLayout(1,4,10,10)); 1 hàng 4 cột k/c 10
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnEdit);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnClear);
        buttonPanel.add(btnSortName);
        buttonPanel.add(btnXuatExcel);
        buttonPanel.add(btnNhap);
        buttonPanel.add(btnExit);

        // label hiện số dòng
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.EAST;
        lbHien = new JLabel();
        controlPanel.add(lbHien, gbc);

        JPanel Paneltable = new JPanel();
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridheight = 6;
        gbc.fill = GridBagConstraints.BOTH;
        controlPanel.add(Paneltable, gbc);
        Paneltable.setLayout(new BorderLayout());
        Paneltable.add(scrollPane, BorderLayout.CENTER);

        // Thiết lập kích thước ưu tiên cho Paneltable
        Paneltable.setPreferredSize(new Dimension(1000, 300));
    }

    public void connect_db() {
        product_DAO pro = new product_DAO();
        pro.conn();
    }

    public void hienthi_tbl() {
        product_DAO pro = new product_DAO();
        pro.rsTableModel(jtable, tableModel);
        pro.loadDataToComboBox(this);
//        for (int i = 0; i < jtable.getColumnCount(); i++) {
//            TableColumn column_tang = jtable.getColumnModel().getColumn(i);
//            column_tang.setPreferredWidth(300); // Đặt độ rộng 300 pixel cho mỗi cột
//        }
        int rowCount = tableModel.getRowCount(); // trả về lượng hàng/ bản ghi
        lbHien.setText("Có " + rowCount + " sản phẩm");

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton btn = (JButton) e.getSource();
        if (btn.equals(btnAdd)) {
            try {
                String newMaSP = txtMaSP.getText();
                String tenSP = txtTenSP.getText();

                if (newMaSP.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập mã sản phẩm!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                    return;
                } else if (txtDonGia.getText().isEmpty() || txtSoLuong.getText().isEmpty() || txtTenSP.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập tên SP hoặc số lượng hoặc đơn giá!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                // Kiểm tra xem mã thuốc mới đã tồn tại trong bảng hay không
                product_DAO pro = new product_DAO();
                boolean kiemtra = pro.kiemtratrungmaSP(newMaSP);
                String tenSP1 = txtTenSP.getText();
                boolean kiemTraTen = pro.kiemtratrungTenSP(tenSP1);
                if (!kiemtra || !kiemTraTen) { // kiemtra == false
                    btn_add_actionerformed();
                    // Xóa hết dữ liệu từ model của JTable
                    tableModel.setRowCount(0);
                    //tableModel.setColumnCount(0);
                    hienthi_tbl();
                } else {
                    JOptionPane.showMessageDialog(null, "Mã sản phẩm và tên sản phẩm đã tồn tại!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                    txtMaSP.requestFocus();
                }
                
                

            } catch (Exception ex) {
                Logger.getLogger(BTL.product_form.class
                        .getName()).log(Level.SEVERE, null, ex);

            }
        } else if (btn.equals(btnEdit)) {
            int choice = JOptionPane.showConfirmDialog(mainFrame, "Bạn có muốn sửa thông tin sản phẩm không?", "Xóa sản phẩm", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                btn_edit_actionerformed();
                // Xóa hết dữ liệu hàng
//                tableModel.setRowCount(0);
//                // Hiển thị lại dữ liệu hàng
//                hienthi_tbl();
            }

        } else if (btn.equals(btnDelete)) {
            int choice = JOptionPane.showConfirmDialog(mainFrame, "Bạn có muốn xóa sản phẩm không?", "Xóa sản phẩm", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                btn_delete_actionerformed();
                // Xóa hết dữ liệu hàng
                tableModel.setRowCount(0);
//                tableModel.removeRow(row);  // xóa 1 hàng row
//                tableModel.fireTableDataChanged();  //thông báo cho JTable biết rằng dữ liệu đã thay đổi
                // Hiển thị lại dữ liệu hàng
                hienthi_tbl();
            }
        } else if (btn.equals(btnClear)) {
            btn_clear_actionerformed();
        } else if (btn.equals(btnExit)) {
            btn_exit_actionerformed();
        } else if (btn.equals(btnSortName)) {
            btn_sortID_actionerformed();
        }
//        else if (btn.equals(btnXuatExcel)) {
//            btn_Export_actionerformed();
//        } 
        else if (btn.equals(btnTimKiem)) {
            try {
                btn_tim_actionperformed();
            } catch (ParseException ex) {
                Logger.getLogger(product_form.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (btn.equals(btnNhap)) {
            btn_nhap_actionerformed();
        }
        else if (btn.equals(btnXuatExcel)) {
            btn_ExportDK_actionPerformed();
        }
    }

    public void btn_add_actionerformed() {
        product_model pro_model = new product_model();
        pro_model.setMaSP(txtMaSP.getText());
        pro_model.setTenSP(txtTenSP.getText());
        pro_model.setMaNCC(cboTenNCC.getSelectedItem().toString());
        pro_model.setMaLoai(cboTenDM.getSelectedItem().toString());
        pro_model.setDonGia(Float.parseFloat(txtDonGia.getText()));
        pro_model.setSoLuong(Integer.parseInt(txtSoLuong.getText()));
        pro_model.setMoTa(txtMoTa.getText());
//        if (String.valueOf(pro_model.getSoLuong()).equals("0")) {
//            pro_model.setTrangThai("Hết hàng");
//        } else {
//            pro_model.setTrangThai("Còn hàng");
//
//        }
        //pro_model.setTrangThai(txtTrangThai.getText());
        setTrangThai(pro_model);
        product_DAO pro_dao = new product_DAO();
        pro_dao.add_product(pro_model, product_form.this);
        // clear
        this.btn_clear_actionerformed();
        int rowCount = tableModel.getRowCount(); // trả về lượng hàng/ bản ghi
        lbHien.setText("Có " + rowCount + " sản phẩm");
    }

    public void setTrangThai(product_model pro_model) {
//        product_model pro_model = new product_model();
        if (String.valueOf(pro_model.getSoLuong()).equals("0")) {
            pro_model.setTrangThai("Hết hàng");
        } else {
            pro_model.setTrangThai("Còn hàng");
        }
        txtTrangThai.setText(pro_model.getTrangThai());
    }

    public void btn_clear_actionerformed() {
        txtMaSP.setText("");
        txtTenSP.setText("");
        //cboTenNCC.setSelectedItem("");cboTenDM.setSelectedItem("");
        txtDonGia.setText("");
        txtSoLuong.setText("");
        txtMoTa.setText("");
        txtTrangThai.setText("");
        txtTimKiem.setText("");
        // txtMaSP được quyền chỉnh sửa
        txtMaSP.setEditable(true);
        tableModel.setRowCount(0);
        hienthi_tbl();
        // Mở nút
        btnDelete.setEnabled(false);
        btnEdit.setEnabled(false);
    }

    public void btn_delete_actionerformed() {
        String newMaSP = txtMaSP.getText();
        product_DAO pro = new product_DAO();
        pro.delete_product(newMaSP);
        //clear
        this.btn_clear_actionerformed();
        int rowCount = tableModel.getRowCount(); // trả về lượng hàng/ bản ghi
        lbHien.setText("Có " + rowCount + " sản phẩm");
    }

    public void btn_nhap_actionerformed() {
        product_DAO pd = new product_DAO();
        pd.nhapexcel(this);
    }

    public void btn_edit_actionerformed() {
        product_model pro_model = new product_model();
        pro_model.setMaSP(txtMaSP.getText());
        pro_model.setTenSP(txtTenSP.getText());
        pro_model.setMaNCC(cboTenNCC.getSelectedItem().toString());
        pro_model.setMaLoai(cboTenDM.getSelectedItem().toString());
        pro_model.setDonGia(Float.parseFloat(txtDonGia.getText()));
        pro_model.setSoLuong(Integer.parseInt(txtSoLuong.getText()));
        pro_model.setMoTa(txtMoTa.getText());
//      pro_model.setTrangThai(txtTrangThai.getText());
        setTrangThai(pro_model);
        product_DAO pro_dao = new product_DAO();
        pro_dao.edit_product(pro_model, product_form.this);
        //clear
        //this.btn_clear_actionerformed();
        // Trỏ lại vị trí sau khi sửa
        troViTri(pro_model);
    }

    public void troViTri(product_model pro_model) {
        String maUpdate = pro_model.getMaSP();
        int rowIndex = -1; // khởi tạo rowIndex
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            if (maUpdate.equals(tableModel.getValueAt(i, 0).toString())) {
                rowIndex = i; // tìm row
                break;
            }
        }
        if (rowIndex != -1) {
            // Update row in the table model
            tableModel.setValueAt(pro_model.getMaSP(), rowIndex, 0);
            tableModel.setValueAt(pro_model.getTenSP(), rowIndex, 1);
            tableModel.setValueAt(cboTenNCC.getSelectedItem(), rowIndex, 2);
            tableModel.setValueAt(cboTenDM.getSelectedItem(), rowIndex, 3);
            tableModel.setValueAt(pro_model.getDonGia(), rowIndex, 4);
            tableModel.setValueAt(pro_model.getSoLuong(), rowIndex, 5);
            tableModel.setValueAt(pro_model.getMoTa(), rowIndex, 6);
            tableModel.setValueAt(pro_model.getTrangThai(), rowIndex, 7);

//            // Scroll to the updated row
//            jtable.scrollRectToVisible(jtable.getCellRect(rowIndex, 0, true));
        }
    }

    public void btn_exit_actionerformed() {
        // Xử lý sự kiện khi nút "Exit" được nhấp
        // Hiển thị hộp thoại xác nhận
        int choice = JOptionPane.showConfirmDialog(mainFrame, "Bạn có muốn thoát khỏi ứng dụng không?", "Thoát ứng dụng", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            System.exit(0); // Thoát khỏi ứng dụng với mã thoát 0
        }
    }

    public void btn_sortID_actionerformed() {
        product_DAO pro = new product_DAO();
        tableModel.setRowCount(0);
        pro.sortByID(jtable, tableModel);
    }
    // Biến đếm cho tên tệp Excel
    int fileCount = 1;

    public void btn_Export_actionerformed() {
        // Tạo workbook mới
        XSSFWorkbook workbook = new XSSFWorkbook();
        // Tạo một trang tính mới
        XSSFSheet sheet = workbook.createSheet("Sản phẩm " + fileCount);
        // Lấy số lượng hàng và cột trong JTable
        int rowCount = jtable.getRowCount();
        int columnCount = jtable.getColumnCount();

        XSSFRow headerRow = sheet.createRow(0);// Tạo một hàng mới để chứa tiêu đề cột
        // Ghi tiêu đề cột vào hàng tiêu đề
        for (int j = 0; j < columnCount; j++) {
            String columnHeader = jtable.getColumnName(j);
            //
            // Tạo một ô mới cho tên cột
            XSSFCell cell = headerRow.createCell(j);
            // Thiết lập định dạng in đậm cho ô
            XSSFFont font = workbook.createFont();
            font.setBold(true);
            XSSFCellStyle style = workbook.createCellStyle();
            style.setFont(font);
            cell.setCellStyle(style);
            // Ghi tên cột vào ô tương ứng trong hàng tiêu đề
            cell.setCellValue(columnHeader);
        }
        int columnWidth = 3000; // Độ rộng mong muốn cho tất cả các cột (tùy chỉnh theo nhu cầu)
        for (int j = 0; j < columnCount; j++) {
            sheet.setColumnWidth(j, columnWidth);
        }
        // Ghi dữ liệu từ JTable vào các hàng tiếp theo trong trang tính
        for (int i = 0; i < rowCount; i++) {
            XSSFRow row = sheet.createRow(i + 1); // Tạo một hàng mới (bỏ qua hàng tiêu đề)
            for (int j = 0; j < columnCount; j++) {
                Object value = jtable.getValueAt(i, j); // Lấy giá trị từ JTable
                if (value != null) {
                    row.createCell(j).setCellValue(value.toString()); // Ghi giá trị vào ô tương ứng trong hàng
                }
            }
        }
        try ( FileOutputStream fileOut = new FileOutputStream("D:/ExportJava/TypeProduct_" + fileCount + ".xlsx")) {
            workbook.write(fileOut);
            JOptionPane.showMessageDialog(null, "Tạo tệp Excel thành công: TypeProduct_" + fileCount + ".xlsx");
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi khi tạo tệp Excel.");
        }
        fileCount++;
    }
    
    public void btn_ExportDK_actionPerformed() {
    // Tạo workbook mới
    XSSFWorkbook workbook = new XSSFWorkbook();
    // Tạo một trang tính mới
    XSSFSheet sheet = workbook.createSheet("Sản phẩm " + fileCount);
    // Lấy số lượng hàng và cột trong JTable
    int rowCount = jtable.getRowCount();

    // Tạo hàng tiêu đề
    XSSFRow headerRow = sheet.createRow(0);
    // Tạo tiêu đề cho cột maSP
    XSSFCell cellMaSP = headerRow.createCell(0);
    cellMaSP.setCellValue("maSP");
    // Tạo tiêu đề cho cột tenSP
    XSSFCell cellTenSP = headerRow.createCell(1);
    cellTenSP.setCellValue("tenSP");

    // Ghi dữ liệu từ JTable vào các hàng tiếp theo trong trang tính
    for (int i = 0; i < rowCount; i++) {
        XSSFRow row = sheet.createRow(i + 1); // Tạo một hàng mới (bỏ qua hàng tiêu đề)
        // Lấy giá trị của cột maSP từ JTable
        Object maSPValue = jtable.getValueAt(i, 0);
        if (maSPValue != null) {
            row.createCell(0).setCellValue(maSPValue.toString());
        }
        // Lấy giá trị của cột tenSP từ JTable
        Object tenSPValue = jtable.getValueAt(i, 1);
        if (tenSPValue != null) {
            row.createCell(1).setCellValue(tenSPValue.toString());
        }
    }

    try (FileOutputStream fileOut = new FileOutputStream("D:/ExportJava/TypeProduct_" + fileCount + ".xlsx")) {
        workbook.write(fileOut);
        JOptionPane.showMessageDialog(null, "Tạo tệp Excel thành công: TypeProduct_" + fileCount + ".xlsx");
    } catch (IOException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi khi tạo tệp Excel.");
    }
    fileCount++;
}

    public void btn_tim_actionperformed() throws ParseException {
        product_DAO pro_dao = new product_DAO();
        // Lấy lựa chọn từ JComboBox
        String selectedOption = cboTimKiem.getSelectedItem().toString();
        String keyword = txtTimKiem.getText();
        //System.out.println("keyword: " + keyword);
        if (keyword.equals("Bạn cần tìm gì ?")) {
            JOptionPane.showMessageDialog(null, "Bạn đang để trống!", "Thông báo", JOptionPane.ERROR_MESSAGE);
            //hienthi_tbl();
        } else {
            tableModel.setRowCount(0);  // xóa dl cũ trong bảng
            pro_dao.search_thuoc(tableModel, selectedOption, keyword);
            int rowCount = tableModel.getRowCount(); // trả về lượng hàng/ bản ghi
            lbHien.setText("Có " + rowCount + " sản phẩm");

        }
    }

    private static void addPlaceholder(final JTextField textField, final String placeholder) {
        textField.setForeground(Color.GRAY);
        textField.setText(placeholder);

        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setForeground(Color.GRAY);
                    textField.setText(placeholder);
                }
            }
        });
    }

    public static void main(String args[]) {
        product_form pf = new product_form();
//        pf.show();
//        //pf.connect_db();
//        pf.hienthi_tbl();
    }

    void addWindowListener(WindowAdapter windowAdapter) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void setVisible(boolean b) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void setDefaultCloseOperation(int DISPOSE_ON_CLOSE) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        System.out.println("Window is closing...");
        // Tạo một cửa sổ mới của lớp menu
        menu d = new menu();
        d.prepare();
        d.setVisible(true);
    }
}
