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
import VanPhongPham.Model.NhaCungCap;
import VanPhongPham.Controller.NhaCungCapDAO;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
/**
 *
 * @author hoan3
 */
public class NhaCungCapFrm implements ActionListener{
    static int row = 0;
    public Object o;
    JLabel id, name, soDT, address, khuVuc, phuongXa, email, congTy, nhomNCC,noCanTra ,ghiChu;
    JTextField idTxt, nameTxt,numberTxt, addressTxt, khuVucTxt, emailTxt, congTyTxt, nhomNCCTxt,noCanTraTxt,ghiChuTxt;
    
    String[] mangPhuongXa = {"Cầu giấy", "Đông Anh", "Ba Đình", "Gia Lâm", "Hoài Đức","Hoàn Kiếm","Hoàng Mai"};
    JComboBox phuongXaTxt; 
    
    public JTextField searchTxt;
    JButton add, edit, delete, clear, sortByGpa, sortByName;
    JButton btnsearch, btnexportExcel, nhapExcel;
    JTable nhaCungCapTable;
     
     // Column Names
        String[] columnNames = { "Mã NCC", "Tên NCC", "Số điện thoại","Địa chỉ","Khu Vực","Phường Xã","Email","Công Ty","Nhóm NCC","Ghi Chú"};
        public DefaultTableModel tableModel;
    public NhaCungCapFrm(){
         JFrame j = new JFrame();
        j.setTitle("Thông tin nhà cung cấp");
        //j.setSize(500,500);
        j.setExtendedState(JFrame.MAXIMIZED_BOTH);
        j.setVisible(true);
        
        id = new JLabel("Mã nhà cung cấp");
        name = new JLabel("Tên nhà cung cấp");
        soDT = new JLabel("Số điện thoại");
        address = new JLabel("Địa chỉ");
        khuVuc = new JLabel("Khu Vực");
        phuongXa = new JLabel("Phường Xã");
        email = new JLabel("Email");
        congTy = new JLabel("Công Ty");
        nhomNCC = new JLabel("Nhóm nhà cung cấp");
        noCanTra = new JLabel("Nợ cần trả");
        ghiChu = new JLabel("Ghi Chú");
        nhapExcel = new JButton("Nhập");
        
        idTxt = new JTextField();       
        nameTxt = new JTextField();
        numberTxt = new JTextField();
        addressTxt = new JTextField();      
        khuVucTxt = new JTextField();
        phuongXaTxt = new JComboBox(mangPhuongXa);
        phuongXaTxt.setEditable(true);
        emailTxt = new JTextField();
        congTyTxt = new JTextField();
        nhomNCCTxt = new JTextField();
        noCanTraTxt = new JTextField();
        ghiChuTxt = new JTextField();
        
        searchTxt = new JTextField(20);
        
        add = new JButton("ADD");
        edit = new JButton("EDIT");
        delete = new JButton("DELETE");
        clear = new JButton("CLEAR");
        sortByGpa = new JButton("SORT BY DEBT");
        sortByName = new JButton("SORT BY NAME");
        btnsearch = new JButton("Search");
        btnexportExcel = new JButton("Export Excel");
        
        j.setLayout(new BorderLayout());
        
        
        JPanel panelBtnWest = new JPanel(new FlowLayout(0,30,0));
        JPanel panelBtnEast = new JPanel(new FlowLayout(0,30,0));
        JPanel panelSearch = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        
        JPanel p1 = new JPanel(new GridBagLayout());
        JPanel p2 = new JPanel(new GridBagLayout());
        
        JLabel frmName = new JLabel("<html><div style='text-align: center;font-size: 24pt;'>Thông tin nhà cung cấp " + "</div></html>");
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
        p1.add(soDT, gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 50;
        gbc.insets = new Insets(0, 50, 20, 200);
        gbc.gridx = 1;
        gbc.gridy = 2;
        p1.add(numberTxt, gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 3;
        p1.add(address, gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 50;
        gbc.ipady = 50;
        gbc.insets = new Insets(0, 50, 20, 10);
        gbc.gridx = 1;
        gbc.gridy = 3;
        p1.add(addressTxt, gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 4;        
        p1.add(khuVuc, gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 50;
        gbc.insets = new Insets(0, 50, 20, 0);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.ipady = 5;        
        p1.add(khuVucTxt , gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 5;        
        p1.add(phuongXa, gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 50, 20, 0);
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.ipady = 5;
        p1.add(phuongXaTxt , gbc);
        
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
        p1.add(congTy, gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 50, 20, 0);
        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.ipady = 5;
        p1.add(congTyTxt , gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 8;        
        p1.add(nhomNCC, gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 50, 20, 0);
        gbc.gridx = 1;
        gbc.gridy = 8;
        gbc.ipady = 5;
        p1.add(nhomNCCTxt , gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 9;        
        p1.add(noCanTra, gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 50, 20, 0);
        gbc.gridx = 1;
        gbc.gridy = 9;
        gbc.ipady = 5;
        p1.add(noCanTraTxt , gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 10;        
        p1.add(ghiChu, gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 50, 20, 0);
        gbc.gridx = 1;
        gbc.gridy = 10;
        gbc.ipady = 5;
        p1.add(ghiChuTxt , gbc);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 11;
        
        panelBtnWest.add(add);
        panelBtnWest.add(edit);
        panelBtnWest.add(delete);
        panelBtnWest.add(clear);
//        panelBtnWest.add(nhapExcel);
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
        nhaCungCapTable = new JTable(tableModel);
        nhaCungCapTable.setAutoCreateRowSorter(true);
        NhaCungCapDAO std = new NhaCungCapDAO();
        hienthi_tbl(std);
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelSearch.add(searchTxt);
        panelSearch.add(btnsearch);
        panelSearch.add(nhapExcel);
        
        p2.add(panelSearch, gbc);
        
        // adding it to JScrollPane
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.ipadx = 400;
        JScrollPane sp = new JScrollPane(nhaCungCapTable);    
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
        searchTxt.setText("Nhập ID or NhomNCC");
        searchTxt.addFocusListener(new FocusListener() {

            public void focusGained(FocusEvent e) {
                if (searchTxt.getText().equals("Nhập ID or NhomNCC")) {
                    searchTxt.setText("");
                    searchTxt.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent e) {
                if (searchTxt.getText().isEmpty()) {
                    searchTxt.setForeground(Color.GRAY);
                    searchTxt.setText("Nhập ID or NhomNCC");
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
        
        // PLACEHOLDER idtxt
        idTxt.setForeground(Color.GRAY);
        idTxt.setText("NCC001");
        idTxt.addFocusListener(new FocusListener() {

            public void focusGained(FocusEvent e) {
                if (idTxt.getText().equals("NCC001")) {
                    idTxt.setText("");
                    idTxt.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent e) {
                if (idTxt.getText().isEmpty()) {
                    idTxt.setForeground(Color.GRAY);
                    idTxt.setText("NCC001");
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
        nhapExcel.addActionListener(this);
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
        
           
        nhaCungCapTable.addMouseListener(new MouseListener() {

            public void mouseClicked(MouseEvent e) {           
                row = nhaCungCapTable.getSelectedRow();
                // lay du lieu tu cot 0
                o = tableModel.getValueAt(row, 0);
                
                // hien thi du lieu len JTextFiled
               Object dataID =  tableModel.getValueAt(row, 0); // Lấy dữ liệu từ cột 0
               Object dataName = tableModel.getValueAt(row, 1); // Lấy dữ liệu từ cột 1
               Object dataSoDT =  tableModel.getValueAt(row, 2); // Lấy dữ liệu từ cột 2
               Object dataAddress = tableModel.getValueAt(row, 3); // Lấy dữ liệu từ cột 3
               Object dataKhuVuc = tableModel.getValueAt(row, 4); // Lấy dữ liệu từ cột 4
               Object dataPhuongXa =  tableModel.getValueAt(row, 5); 
               Object dataEmail = tableModel.getValueAt(row, 6); 
               Object dataCongTy =  tableModel.getValueAt(row, 7); 
               Object dataNhomNCC = tableModel.getValueAt(row, 8); 
               Object dataNoCanTra = tableModel.getValueAt(row, 9);
               Object dataGhiChu= tableModel.getValueAt(row, 10);
               
               
                idTxt.setText(dataID.toString());
                nameTxt.setText(dataName.toString());
                numberTxt.setText(dataSoDT.toString());
                addressTxt.setText(dataAddress.toString());
                khuVucTxt.setText(dataKhuVuc.toString());
                phuongXaTxt.setSelectedItem(dataPhuongXa);
                emailTxt.setText(dataEmail.toString());
                congTyTxt.setText(dataCongTy.toString());
                nhomNCCTxt.setText(dataNhomNCC.toString());
                noCanTraTxt.setText(dataNoCanTra.toString());
                ghiChuTxt.setText(dataGhiChu.toString());
                
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

    public void hienthi_tbl(NhaCungCapDAO std){
        std.rsTableModel(nhaCungCapTable, tableModel);
    }
    
    public void actionPerformed(ActionEvent e) {
        JButton btn = (JButton) e.getSource();
        if(btn.equals(add)){
             int choice = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn thêm không?", "Lựa chọn", JOptionPane.YES_NO_OPTION);
            if(choice == JOptionPane.YES_OPTION){
                if(addressTxt.getText().toString().equals("43")){
                btnAdd_actionperformed();
            // Xóa tất cả các hàng và cột
                tableModel.setRowCount(0);
                tableModel.setColumnCount(0);
                NhaCungCapDAO std = new NhaCungCapDAO();
                hienthi_tbl(std);
                }else{
                JOptionPane.showMessageDialog(null, "Bạn nhập địa chỉ khác 43" + addressTxt.getText().toString());
                }
                
                
            }else{
                 System.out.println("Nguoi dung chon No");
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
            btnSortDebt_actionperformed();
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
        else if(btn.equals(nhapExcel)){
            btn_nhap_actionerformed();
        }
    }
    
     // Kiểm tra định dạng email
    public static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    
     // Kiểm tra định dạng số điện thoại
    public static boolean isValidPhoneNumber(String phoneNumber) {
        // Regular expression to validate phone number
        String phoneRegex = "^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$";
        Pattern pattern = Pattern.compile(phoneRegex);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }
    
    // Kiểm tra định dạng số nợ
    public static boolean isValidDebtAmount(String debtAmount) {
        // Regular expression to validate debt amount (positive number with up to 2 decimal places)
        String debtRegex = "^[0-9]+(\\.[0-9]{1,2})?$";
        Pattern pattern = Pattern.compile(debtRegex);
        Matcher matcher = pattern.matcher(debtAmount);
        return matcher.matches();
    }
    public void btn_nhap_actionerformed() {
        NhaCungCapDAO pd = new NhaCungCapDAO();
        pd.nhapexcel(this);
    }
    
     private void btnAdd_actionperformed() {
         if(idTxt.getText().isEmpty() || nameTxt.getText().isEmpty() || addressTxt.getText().isEmpty()
                 || numberTxt.getText().isEmpty() || khuVucTxt.getText().isEmpty() || emailTxt.getText().isEmpty()
                 || congTyTxt.getText().isEmpty() || nhomNCCTxt.getText().isEmpty()){
             JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ dữ liệu", "Error", JOptionPane.ERROR_MESSAGE);
         }else if(!isValidEmail(emailTxt.getText().toString())){
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng định dạng email", "Error", JOptionPane.ERROR_MESSAGE);
         }else if(numberTxt.getText().length() != 10){
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng độ dài số điện thoại(10 số) ", "Error", JOptionPane.ERROR_MESSAGE);
         }else if(!isValidPhoneNumber(numberTxt.getText().toString())){
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng định dạng số điện thoại", "Error", JOptionPane.ERROR_MESSAGE);
         }else if(!isValidDebtAmount(noCanTraTxt.getText().toString())){
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng định dạng số nợ", "Error", JOptionPane.ERROR_MESSAGE);
         }
         
         else{
            NhaCungCap s = new NhaCungCap();
            s.setMaNcc(idTxt.getText());
            s.setTenNcc(nameTxt.getText());
            s.setSoDienThoai(numberTxt.getText());
            s.setDiaChi(addressTxt.getText());
            s.setKhuVuc(khuVucTxt.getText());
            s.setPhuongXa(phuongXaTxt.getSelectedItem().toString());
            s.setEmail(emailTxt.getText());
            s.setCongTy(congTyTxt.getText());
            s.setNhomNcc(nhomNCCTxt.getText());
            s.setNoCanTra(Float.valueOf(noCanTraTxt.getText()));
            s.setGhiChu(ghiChuTxt.getText());

            NhaCungCapDAO std = new NhaCungCapDAO();
            std.add_NCC(s); 
         }       
    }

    private void btnClear_actionperformed() {
        idTxt.setText("");
        nameTxt.setText("");
        numberTxt.setText("");
        addressTxt.setText("");
        khuVucTxt.setText("");
        emailTxt.setText("");
        congTyTxt.setText("");
        nhomNCCTxt.setText("");
        noCanTraTxt.setText("");
        ghiChuTxt.setText("");
        
    }

    private void btnDelete_actionperformed() {          
        NhaCungCapDAO std = new NhaCungCapDAO();
        std.delete_NCC(o);
        tableModel.removeRow(row);
        tableModel.fireTableDataChanged();
        
        btnClear_actionperformed();      
    }

    private void btnEdit_actionperformed() {
         if(idTxt.getText().isEmpty() || nameTxt.getText().isEmpty() || addressTxt.getText().isEmpty()
                 || numberTxt.getText().isEmpty() || khuVucTxt.getText().isEmpty() || emailTxt.getText().isEmpty()
                 || congTyTxt.getText().isEmpty() || nhomNCCTxt.getText().isEmpty()){
             JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ dữ liệu", "Error", JOptionPane.ERROR_MESSAGE);
         }else if(!isValidEmail(emailTxt.getText().toString())){
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng định dạng email", "Error", JOptionPane.ERROR_MESSAGE);
         }else if(numberTxt.getText().length() != 10){
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng độ dài số điện thoại(10 số) ", "Error", JOptionPane.ERROR_MESSAGE);
         }else if(!isValidPhoneNumber(numberTxt.getText().toString())){
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng định dạng số điện thoại", "Error", JOptionPane.ERROR_MESSAGE);
         }else if(!isValidDebtAmount(noCanTraTxt.getText().toString())){
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng định dạng số nợ", "Error", JOptionPane.ERROR_MESSAGE);
         }else{
            NhaCungCap s = new NhaCungCap();
            s.setMaNcc(idTxt.getText());
            s.setTenNcc(nameTxt.getText());
            s.setSoDienThoai(numberTxt.getText());
            s.setDiaChi(addressTxt.getText());
            s.setKhuVuc(khuVucTxt.getText());
            s.setPhuongXa(phuongXaTxt.getSelectedItem().toString());
            s.setEmail(emailTxt.getText());
            s.setCongTy(congTyTxt.getText());
            s.setNhomNcc(nhomNCCTxt.getText());
            s.setNoCanTra(Float.valueOf(noCanTraTxt.getText()));
            s.setGhiChu(ghiChuTxt.getText());
        
            NhaCungCapDAO std = new NhaCungCapDAO();
            std.edit_NCC(s, o);
        
        // Cập nhật dữ liệu trong bảng
            tableModel.setValueAt(idTxt.getText(), row, 0);
            tableModel.setValueAt(nameTxt.getText(), row, 1);
            tableModel.setValueAt(numberTxt.getText(), row, 2);
            tableModel.setValueAt(addressTxt.getText(), row, 3);
            tableModel.setValueAt(khuVucTxt.getText(), row, 4);
            tableModel.setValueAt(phuongXaTxt.getSelectedItem().toString(), row, 5);
            tableModel.setValueAt(emailTxt.getText(), row, 6);
            tableModel.setValueAt(congTyTxt.getText(), row, 7);
            tableModel.setValueAt(nhomNCCTxt.getText(), row, 8);
            tableModel.setValueAt(noCanTraTxt.getText(), row, 9);
            tableModel.setValueAt(ghiChuTxt.getText(), row, 10);
         }
        
        
    }

    private void btnExportExcel_actionperformed() {
        NhaCungCapDAO std = new NhaCungCapDAO();
        std.excel_print(tableModel);
    }

    private void btnSearch_actionperformed() {
        NhaCungCapDAO std = new NhaCungCapDAO();
        std.searchValue(nhaCungCapTable, tableModel, this);
    }

    private void btnSortDebt_actionperformed() {
        NhaCungCapDAO std = new NhaCungCapDAO();
        std.sortDebt(nhaCungCapTable,tableModel);
        
        
    }

    private void btnSortName_actionperformed() {
        NhaCungCapDAO std = new NhaCungCapDAO();
        std.sortName(nhaCungCapTable, tableModel);
    }
    
    
    public static void main(String[] args){
        //NhanSuFrm f = new NhanSuFrm();
    }
    }

