/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package VanPhongPham.View;

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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import VanPhongPham.Model.NhanSu;
import VanPhongPham.Controller.NhanSuDAO;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
/**
 *
 * @author hoan3
 */
public class NhanSuFrm implements ActionListener{
 static int row = 0;
    public Object o;
    JLabel id, name, address, soDT,ngaySinh, noiSinh, email,cMND, gioiTinh,trangThai;
    JTextField idTxt, nameTxt,addressTxt,numberTxt, ngaySinhTxt, noiSinhTxt, emailTxt,cMNDTxt;
    
    String[] mangTrangThai = {"Đang làm việc","Không làm việc"};
    JComboBox trangThaiTxt; 
    
    String[] mangGioiTinh = {"Nam","Nữ"};
    JComboBox gioiTinhTxt;
    
    public JTextField searchTxt;
    JButton add, edit, delete, clear, sortByGpa, sortByName;
    JButton btnsearch, btnexportExcel;
    JTable nhanSuTable;
    JScrollPane jScrollPaneNhanSuTable;
     
     // Column Names
        String[] columnNames = { "Mã NV", "Tên NV", "Địa chỉ","Số điện thoại","Khu Vực","Phường Xã","Email","Công Ty","Nhóm NCC","Ghi Chú"};
        DefaultTableModel tableModel;
    public NhanSuFrm(){
         JFrame j = new JFrame();
        j.setTitle("Thông tin nhân viên");
        //j.setSize(500,500);
        j.setExtendedState(JFrame.MAXIMIZED_BOTH);        
        j.setVisible(true);
        
        id = new JLabel("Mã nhân viên");
        name = new JLabel("Tên nhân viên");        
        address = new JLabel("Địa chỉ");
        soDT = new JLabel("Số điện thoại");
        ngaySinh = new JLabel("Ngày sinh");
        noiSinh = new JLabel("Nơi sinh");
        email = new JLabel("Email");
        cMND = new JLabel("Chứng minh nhân dân");
        gioiTinh = new JLabel("Giới tính");
        trangThai = new JLabel("Trạng thái");       
        
        idTxt = new JTextField();       
        nameTxt = new JTextField();
        addressTxt = new JTextField();   
        numberTxt = new JTextField();           
        ngaySinhTxt = new JTextField();
        noiSinhTxt = new JTextField();
        emailTxt = new JTextField();
        cMNDTxt = new JTextField();
        gioiTinhTxt = new JComboBox(mangGioiTinh);
        gioiTinhTxt.setEditable(true);
        trangThaiTxt = new JComboBox(mangTrangThai);
        trangThaiTxt.setEditable(true);
        
        
        searchTxt = new JTextField(20);
        
        add = new JButton("ADD");
        edit = new JButton("EDIT");
        delete = new JButton("DELETE");
        clear = new JButton("CLEAR");
        sortByGpa = new JButton("SORT BY ID");
        sortByName = new JButton("SORT BY NAME");
        btnsearch = new JButton("Search");
        btnexportExcel = new JButton("Export Excel");
        
        j.setLayout(new BorderLayout());
        
        
        JPanel panelBtnWest = new JPanel(new FlowLayout(0,30,0));
        JPanel panelBtnEast = new JPanel(new FlowLayout(0,30,0));
        JPanel panelSearch = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        
        JPanel p1 = new JPanel(new GridBagLayout());
        JPanel p2 = new JPanel(new GridBagLayout());
        
        JLabel frmName = new JLabel("<html><div style='text-align: center;font-size: 24pt;'>Thông tin nhân viên " + "</div></html>");
        frmName.setHorizontalAlignment(SwingConstants.CENTER);

        // Tạo JScrollPane để cho phép cuộn nếu cần thiết
        JScrollPane LabelScroll = new JScrollPane(frmName);
        LabelScroll.setPreferredSize(new Dimension(300, 300)); // Đặt kích thước cho JScrollPane
        
        j.add(LabelScroll, BorderLayout.NORTH);
        j.add(p1, BorderLayout.WEST);
        j.add(p2, BorderLayout.CENTER);
        
        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 50, 20, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        p1.add(id, gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 100;
        gbc.insets = new Insets(0, 50, 20, 200);
        gbc.gridx = 1;
        gbc.gridy = 0;
        p1.add(idTxt, gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 1;
        p1.add(name, gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 50;
        gbc.insets = new Insets(0, 50, 20, 10);
        gbc.gridx = 1;
        gbc.gridy = 1;
        p1.add(nameTxt, gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 2;
        p1.add(address, gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
//        gbc.ipadx = 50;
        gbc.ipady = 50;
        gbc.insets = new Insets(0, 50, 20, 200);
        gbc.gridx = 1;
        gbc.gridy = 2;
        p1.add(addressTxt, gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 3;
        p1.add(soDT, gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady = 0;
        gbc.insets = new Insets(0, 50, 20, 10);
        gbc.gridx = 1;
        gbc.gridy = 3;
        p1.add(numberTxt, gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 4;        
        p1.add(ngaySinh, gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 50;
        gbc.insets = new Insets(0, 50, 20, 0);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.ipady = 5;        
        p1.add(ngaySinhTxt , gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 5;        
        p1.add(noiSinh, gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 50, 20, 0);
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.ipady = 5;
        p1.add(noiSinhTxt , gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 6;        
        p1.add(email, gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 50, 20, 0);
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.ipady = 5;
        p1.add(emailTxt , gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 7;        
        p1.add(cMND, gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 50, 20, 0);
        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.ipady = 5;
        p1.add(cMNDTxt , gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 8;        
        p1.add(gioiTinh, gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 50, 20, 0);
        gbc.gridx = 1;
        gbc.gridy = 8;
        gbc.ipady = 5;
        p1.add(gioiTinhTxt , gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 9;        
        p1.add(trangThai, gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 50, 20, 0);
        gbc.gridx = 1;
        gbc.gridy = 9;
        gbc.ipady = 5;
        p1.add(trangThaiTxt , gbc);     
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 10;
        
        panelBtnWest.add(add);
        panelBtnWest.add(edit);
        panelBtnWest.add(delete);
        panelBtnWest.add(clear);
        p1.add(panelBtnWest,gbc);
        
        // Initializing the JTable
       // JTable jTable = new JTable(data, columnNames);
        
        //jTable.setBounds(30, 40, 200, 300);
        tableModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        nhanSuTable = new JTable(tableModel);
        nhanSuTable.setAutoCreateRowSorter(true);             
        NhanSuDAO std = new NhanSuDAO();
        hienthi_tbl(std);
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelSearch.add(searchTxt);
        panelSearch.add(btnsearch);
        p2.add(panelSearch, gbc);
        
        // adding it to JScrollPane
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.ipadx = 450;
        JScrollPane sp = new JScrollPane(nhanSuTable);    
        p2.add(sp, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        panelBtnEast.add(sortByGpa);
        panelBtnEast.add(sortByName); 
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        panelBtnEast.add(btnexportExcel);
        p2.add(panelBtnEast, gbc);
        
        
        // PLACEHOLDER searchTXT
        searchTxt.setForeground(Color.GRAY);
        searchTxt.setText("Nhập ID or Name");
        searchTxt.addFocusListener(new FocusListener() {

            public void focusGained(FocusEvent e) {
                if (searchTxt.getText().equals("Nhập ID or Name")) {
                    searchTxt.setText("");
                    searchTxt.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent e) {
                if (searchTxt.getText().isEmpty()) {
                    searchTxt.setForeground(Color.GRAY);
                    searchTxt.setText("Nhập ID or Name");
                }
            }

        });
        
        // PLACEHOLDER emailTxt
        emailTxt.setForeground(Color.GRAY);
        emailTxt.setText("abcd@gmail.com");
        emailTxt.addFocusListener(new FocusListener() {

            public void focusGained(FocusEvent e) {
                if (emailTxt.getText().equals("abcd@gmail.com")) {
                    emailTxt.setText("");
                    emailTxt.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent e) {
                if (emailTxt.getText().isEmpty()) {
                    emailTxt.setForeground(Color.GRAY);
                    emailTxt.setText("abcd@gmail.com");
                }
            } 
        });
        
        // PLACEHOLDER ngaysinhTxt
        ngaySinhTxt.setForeground(Color.GRAY);
        ngaySinhTxt.setText("yyyy/mm/dd");
        ngaySinhTxt.addFocusListener(new FocusListener() {

            public void focusGained(FocusEvent e) {
                if (ngaySinhTxt.getText().equals("yyyy/mm/dd")) {
                    ngaySinhTxt.setText("");
                    ngaySinhTxt.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent e) {
                if (ngaySinhTxt.getText().isEmpty()) {
                    ngaySinhTxt.setForeground(Color.GRAY);
                    ngaySinhTxt.setText("yyyy/mm/dd");
                }
            } 
        });
        
        // PLACEHOLDER idTxt
        idTxt.setForeground(Color.GRAY);
        idTxt.setText("NV001");
        idTxt.addFocusListener(new FocusListener() {

            public void focusGained(FocusEvent e) {
                if (idTxt.getText().equals("NV001")) {
                    idTxt.setText("");
                    idTxt.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent e) {
                if (idTxt.getText().isEmpty()) {
                    idTxt.setForeground(Color.GRAY);
                    idTxt.setText("NV001");
                }
            } 
        });
        
        
        
        add.addActionListener(this);
        delete.addActionListener(this);
        edit.addActionListener(this);
        clear.addActionListener(this);
        sortByGpa.addActionListener(this);
        sortByName.addActionListener(this);
        btnsearch.addActionListener(this);
        btnexportExcel.addActionListener(this);
        
        j.addWindowListener(new WindowAdapter() {
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
        
        nhanSuTable.addMouseListener(new MouseListener() {

            public void mouseClicked(MouseEvent e) {           
                row = nhanSuTable.getSelectedRow();
                // lay du lieu tu cot 0
                o = tableModel.getValueAt(row, 0);
                
                // hien thi du lieu len JTextFiled
               Object dataID =  tableModel.getValueAt(row, 0); // Lấy dữ liệu từ cột 0
               Object dataName = tableModel.getValueAt(row, 1); // Lấy dữ liệu từ cột 1
               Object dataAddress = tableModel.getValueAt(row, 2);// Lấy dữ liệu từ cột 2
               Object dataSoDT =  tableModel.getValueAt(row, 3); // Lấy dữ liệu từ cột 3
               Object dataNgaySinh = tableModel.getValueAt(row, 4); // Lấy dữ liệu từ cột 4
               Object dataNoiSinh =  tableModel.getValueAt(row, 5); 
               Object dataEmail = tableModel.getValueAt(row, 6); 
               Object dataCMND =  tableModel.getValueAt(row, 7); 
               Object dataGioiTinh = tableModel.getValueAt(row, 8); 
               Object dataTrangThai = tableModel.getValueAt(row, 9);       
               
                idTxt.setText(dataID.toString());
                nameTxt.setText(dataName.toString());
                addressTxt.setText(dataAddress.toString());
                numberTxt.setText(dataSoDT.toString());               
                ngaySinhTxt.setText(dataNgaySinh.toString());
                noiSinhTxt.setText(dataNoiSinh.toString());
                emailTxt.setText(dataEmail.toString());
                cMNDTxt.setText(dataCMND.toString());
                gioiTinhTxt.setSelectedItem(dataGioiTinh.toString());
                trangThaiTxt.setSelectedItem(dataTrangThai.toString());
                
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
    }
    
    public void hienthi_tbl(NhanSuDAO std){
        std.rsTableModel(nhanSuTable, tableModel);
    }

    public void actionPerformed(ActionEvent e) {
        JButton btn = (JButton) e.getSource();
        if(btn.equals(add)){
            int choice = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn thêm không?","Lựa chọn",JOptionPane.YES_NO_OPTION);
            if(choice == JOptionPane.YES_OPTION){
                btnAdd_actionperformed();
            // Xóa tất cả các hàng và cột
                tableModel.setRowCount(0);
                tableModel.setColumnCount(0);
                NhanSuDAO std = new NhanSuDAO();
                hienthi_tbl(std);
            }else{
                System.out.println("Nguoi dung da chon No");
            }      
        }else if(btn.equals(delete)){
            int choice = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa không?", "Lựa chọn", JOptionPane.YES_NO_OPTION);

            // Kiểm tra lựa chọn của người dùng
            if (choice == JOptionPane.YES_OPTION) {
                btnDelete_actionperformed();
            } else {
                System.out.println("Người dùng đã chọn No");
            }                  
        }else if(btn.equals(edit)){
            int choice = JOptionPane.showConfirmDialog(null, "Bạn có muốn chắc sửa không?", "Lựa chọn", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                btnEdit_actionperformed();
            } else {
                System.out.println("Người dùng đã chọn No");
            } 
        }else if(btn.equals(clear)){
            btnClear_actionperformed();
        }else if(btn.equals(sortByGpa)){           
           tableModel.setRowCount(0);
           tableModel.setColumnCount(0);
            btnSortMaNV_actionperformed();
        }else if(btn.equals(sortByName)){
            tableModel.setRowCount(0);
            tableModel.setColumnCount(0);
            btnSortName_actionperformed();
        }else if(btn.equals(btnsearch)){
            tableModel.setRowCount(0);
            tableModel.setColumnCount(0);
            btnSearch_actionperformed();
            if(tableModel.getRowCount()==0){
                JOptionPane.showMessageDialog(null, "Không tìm thấy kết quả!");
                searchTxt.setText("");
            }
        }else if(btn.equals(btnexportExcel)){
            btnExportExcel_actionperformed();
        }
    }
    
     private void btnAdd_actionperformed() {
        if(idTxt.getText().isEmpty() || nameTxt.getText().isEmpty() || addressTxt.getText().isEmpty()
                || numberTxt.getText().isEmpty() || ngaySinhTxt.getText().isEmpty() || noiSinhTxt.getText().isEmpty()
                || emailTxt.getText().isEmpty() || cMNDTxt.getText().isEmpty()) {
             JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!", "Error", JOptionPane.ERROR_MESSAGE);
        }else if(!isValidEmail(emailTxt.getText().toString())){
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng định dạng email!", "Error", JOptionPane.ERROR_MESSAGE);
        }else if(!isValidDate(ngaySinhTxt.getText().toString())){
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng định dạng ngày tháng(yyy/mm/dd)", "Error", JOptionPane.ERROR_MESSAGE);
        }else if(cMNDTxt.getText().length() != 12){
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng độ dài căn cước (12 số)", "Error", JOptionPane.ERROR_MESSAGE);
        }else if(numberTxt.getText().length() != 10){
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng độ dài số điện thoại(10 số)", "Error", JOptionPane.ERROR_MESSAGE);
        }else if(!isValidPhoneNumber(numberTxt.getText().toString())){
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng định dạng số điện thoại", "Error", JOptionPane.ERROR_MESSAGE);
        }else{
            NhanSu s = new NhanSu();
            s.setMaNV(idTxt.getText());
            s.setTenNV(nameTxt.getText());
            s.setDiaChi(addressTxt.getText());
            s.setSDT(numberTxt.getText());
            s.setNgaySinh(ngaySinhTxt.getText());
            s.setNoiSinh(noiSinhTxt.getText());
            s.setEmail(emailTxt.getText());
            s.setCMND(cMNDTxt.getText());
            s.setGioiTinh(gioiTinhTxt.getSelectedItem().toString());
            s.setTrangThai(trangThaiTxt.getSelectedItem().toString());

            NhanSuDAO std = new NhanSuDAO();
            std.add_NhanVien(s); 
        }    
    }

    private void btnClear_actionperformed() {
        idTxt.setText("");
        nameTxt.setText("");
        numberTxt.setText("");
        addressTxt.setText("");
        ngaySinhTxt.setText("");
        emailTxt.setText("");
        noiSinhTxt.setText("");
        cMNDTxt.setText("");
             
    }
    // Kiểm tra định dạng email
    public static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    
     // Kiểm tra định dạng ngày tháng
    public static boolean isValidDate(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        sdf.setLenient(false);
        try {
            sdf.parse(dateStr);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
    
    // Kiểm tra định dạng số điện thoại
    public static boolean isValidPhoneNumber(String phoneNumber) {
        // Regular expression to validate phone number
        String phoneRegex = "^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$";
        Pattern pattern = Pattern.compile(phoneRegex);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }
    private void btnDelete_actionperformed() {          
        NhanSuDAO std = new NhanSuDAO();
        std.delete_NV(o);
        tableModel.removeRow(row);
        tableModel.fireTableDataChanged();
        
        btnClear_actionperformed();      
    }

    private void btnEdit_actionperformed() {
         if(idTxt.getText().isEmpty() || nameTxt.getText().isEmpty() || addressTxt.getText().isEmpty()
                || numberTxt.getText().isEmpty() || ngaySinhTxt.getText().isEmpty() || noiSinhTxt.getText().isEmpty()
                || emailTxt.getText().isEmpty() || cMNDTxt.getText().isEmpty()) {
             JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!", "Error", JOptionPane.ERROR_MESSAGE);
        }else if(!isValidEmail(emailTxt.getText().toString())){
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng định dạng email!", "Error", JOptionPane.ERROR_MESSAGE);
        }else if(!isValidDate(ngaySinhTxt.getText().toString())){
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng định dạng ngày tháng(yyyy/mm/dd)", "Error", JOptionPane.ERROR_MESSAGE);
        }else if(cMNDTxt.getText().length() != 12){
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng độ dài căn cước (12 số)", "Error", JOptionPane.ERROR_MESSAGE);
        }else if(numberTxt.getText().length() != 10){
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng độ dài số điện thoại(10 số) ", "Error", JOptionPane.ERROR_MESSAGE);
        }else if(!isValidPhoneNumber(numberTxt.getText().toString())){
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng định dạng số điện thoại", "Error", JOptionPane.ERROR_MESSAGE);
        }else{
            NhanSu s = new NhanSu();
            s.setMaNV(idTxt.getText());
            s.setTenNV(nameTxt.getText());
            s.setDiaChi(addressTxt.getText());
            s.setSDT(numberTxt.getText());
            s.setNgaySinh(ngaySinhTxt.getText());
            s.setNoiSinh(noiSinhTxt.getText());
            s.setEmail(emailTxt.getText());
            s.setCMND(cMNDTxt.getText());
            s.setGioiTinh(gioiTinhTxt.getSelectedItem().toString());
            s.setTrangThai(trangThaiTxt.getSelectedItem().toString());

            NhanSuDAO std = new NhanSuDAO();
            std.edit_NV(s, o);
        
        // Cập nhật dữ liệu trong bảng
            tableModel.setValueAt(idTxt.getText(), row, 0);
            tableModel.setValueAt(nameTxt.getText(), row, 1);
            tableModel.setValueAt(addressTxt.getText(), row, 2);
            tableModel.setValueAt(numberTxt.getText(), row, 3);        
            tableModel.setValueAt(ngaySinhTxt.getText(), row, 4);
            tableModel.setValueAt(noiSinhTxt.getText(), row, 5);
            tableModel.setValueAt(emailTxt.getText(), row, 6);
            tableModel.setValueAt(cMNDTxt.getText(), row, 7);
            tableModel.setValueAt(gioiTinhTxt.getSelectedItem().toString(), row, 8);
            tableModel.setValueAt(trangThaiTxt.getSelectedItem().toString(), row, 9);
        }
    }

    private void btnExportExcel_actionperformed() {
        NhanSuDAO std = new NhanSuDAO();
        std.excel_print(tableModel);
    }

    private void btnSearch_actionperformed() {
        NhanSuDAO std = new NhanSuDAO();
        std.searchValue(nhanSuTable, tableModel, this);
    }

    private void btnSortMaNV_actionperformed() {
        NhanSuDAO std = new NhanSuDAO();
        std.sortMaNV(nhanSuTable,tableModel);
        
        
    }

    private void btnSortName_actionperformed() {
        NhanSuDAO std = new NhanSuDAO();
        std.sortName(nhanSuTable, tableModel);
    }
    public static void main(String[] args){
        //NhanSuFrm f = new NhanSuFrm();
    }
}
