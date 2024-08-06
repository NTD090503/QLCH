/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package VanPhongPham.View;

import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import VanPhongPham.Controller.Account_DAO;
import VanPhongPham.Model.Account;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import VanPhongPham.View.menu;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/**
 *
 * @author quynh
 */
public class Account_Frm extends JFrame implements ActionListener{
    public JButton addAccount;
    public JButton editAccount;
    public JButton deleteAccount;
    public JButton clearAccount, nhapexcel;
    public JButton sortAcount;
    public JButton xuatexcelAcount, btnexit;
    public JButton searchAccont;
    public JScrollPane jScrollPaneCostumerTable;
    public JTable AccountTable;
    public String[][] data;
    public DefaultTableModel tableModel;
    public JLabel IDLabel;
    public JLabel PasswordLabel;
    public JLabel maNVLabel;
    public JLabel tenNVLabel;
    public JLabel ngaysinhLabel;
    public JLabel gioitinhLabel;
    public JLabel sdtLabel;
    public JLabel diachiLabel;
    public JLabel emailLabel;

    public JTextField  IDField, PasswordField;
    public JComboBox maNVcb;
    public JTextField tenNVField;
    public JTextField ngaysinhField;
    public JTextArea diachiTA;
    public JTextField sdtField;
    public JTextField gioitinhFiel;
    public JTextField emailField;
    public JTextField txtSearch;
    public int row;
    public Object o;
   
    
     public Account_Frm() {
        initComponents();
        Account_DAO acd = new Account_DAO();
        hienthi_tbl(acd);
        
    }

    private void initComponents() {
        //throw new UnsupportedOperationException("Not yet implemented");
        //setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        nhapexcel = new JButton("Nhập");
        addAccount = new JButton("Add");
        editAccount = new JButton("Edit");
        deleteAccount = new JButton("Delete");
        clearAccount = new JButton("Clear");
        sortAcount = new JButton("Sort By ID");
        searchAccont = new JButton("Search Name");
        xuatexcelAcount = new JButton("Xuat Excel");
        btnexit = new JButton("Đăng Xuất");
        
        jScrollPaneCostumerTable = new JScrollPane(AccountTable);
        AccountTable = new JTable();
        
        IDLabel = new JLabel("ID");
        PasswordLabel = new JLabel("PassWord");
        maNVLabel = new JLabel("Mã NV");
        tenNVLabel = new JLabel("Tên NV");
        ngaysinhLabel = new JLabel("Tuoi");
        gioitinhLabel = new JLabel("Giới tính");
        diachiLabel = new JLabel("Địa Chỉ");
        sdtLabel = new JLabel("SĐT");
        emailLabel = new JLabel("Email");

        IDField = new JTextField(6);
        PasswordField = new JTextField(15);
        maNVcb = new JComboBox();
        maNVcb.setPreferredSize(new Dimension(100, 20));
        tenNVField = new JTextField(15);
        ngaysinhField = new JTextField(6);
        diachiTA = new JTextArea();
        diachiTA.setColumns(15);
        diachiTA.setRows(5);
        JScrollPane jScrollPaneAddress = new JScrollPane(diachiTA);
        gioitinhFiel = new JTextField(6);
        sdtField = new JTextField(15);
        emailField = new JTextField(20);
        txtSearch = new JTextField(10);
        txtSearch.setPreferredSize(new Dimension(120, 28));
         txtSearch.setForeground(Color.GRAY);
        txtSearch.setText("Vui lòng nhập ID");
        txtSearch.addFocusListener(new FocusListener() {

            public void focusGained(FocusEvent e) {
                if (txtSearch.getText().equals("Vui lòng nhập ID")) {
                    txtSearch.setText("");
                    txtSearch.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent e) {
                if (txtSearch.getText().isEmpty()) {
                    txtSearch.setForeground(Color.GRAY);
                    txtSearch.setText("Vui lòng nhập ID");
                }
            } 
        });
        nhapexcel.addActionListener(this);
        tableModel  = new DefaultTableModel();
        AccountTable.setModel(tableModel);
        jScrollPaneCostumerTable.setViewportView(AccountTable);
        jScrollPaneCostumerTable.setPreferredSize(new Dimension(500,300));
         AccountTable.addMouseListener(new MouseListener() {
            
            
             //addCustomer.addActionListener(this);
       

            public void mouseClicked(MouseEvent e) {           
                row = AccountTable.getSelectedRow();
                // lay du lieu tu cot 0
                o = tableModel.getValueAt(row, 0);
                
                // hien thi du lieu len JTextFiled
               Object datamID =  tableModel.getValueAt(row, 0); // Lấy dữ liệu từ cột 0
               PasswordField.setText(tableModel.getValueAt(row, 1).toString()); // Lấy dữ liệu từ cột 1
               Object datamaNV =  tableModel.getValueAt(row, 2); // Lấy dữ liệu từ cột 2
               Object datatenNV = tableModel.getValueAt(row, 3); // Lấy dữ liệu từ cột 3
               Object dataTuoi = tableModel.getValueAt(row, 4); // Lấy dữ liệu từ cột 4
               Object datagioiTinh = tableModel.getValueAt(row, 5);
               Object datasDT = tableModel.getValueAt(row, 6);
               Object datadiaChi = tableModel.getValueAt(row, 7);
               Object dataemail = tableModel.getValueAt(row, 8);
               
               maNVcb.removeAllItems();
               Account_DAO acd = new Account_DAO();
                acd.loadDataToComboBox(Account_Frm.this);
                // Đặt giá trị mặc định cho JComboBox bằng giá trị từ hàng được chọn
                
                
                IDField.setText(datamID.toString());
                //PasswordField.setText(data.toString());
                maNVcb.setSelectedItem(AccountTable.getValueAt(row, 2));
                tenNVField.setText(datatenNV.toString());
                ngaysinhField.setText(dataTuoi.toString());
                gioitinhFiel.setText(datagioiTinh.toString());
                sdtField.setText(datasDT.toString());
                diachiTA.setText(datadiaChi.toString());
                emailField.setText(dataemail.toString());
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
  

        SpringLayout layout = new SpringLayout();
        JPanel panel = new JPanel();
        panel.setSize(1200,600);
        panel.setLayout(layout);
        panel.add(jScrollPaneCostumerTable);

        panel.add(addAccount);
        panel.add(editAccount);
        panel.add(deleteAccount);
        panel.add(clearAccount);
        panel.add(sortAcount);
        panel.add(searchAccont);
        panel.add(xuatexcelAcount);
      
        
        panel.add(IDLabel);
        panel.add(PasswordLabel);
        panel.add(maNVLabel);
        panel.add(tenNVLabel);
        panel.add(ngaysinhLabel);
        panel.add(diachiLabel);
        panel.add(sdtLabel);
        panel.add(gioitinhLabel);
        panel.add(emailLabel);

        panel.add(IDField);
        panel.add(PasswordField);
        panel.add(maNVcb);
        panel.add(tenNVField);
        panel.add(ngaysinhField);
        panel.add(jScrollPaneAddress);
        panel.add(gioitinhFiel);
        panel.add(sdtField);
        panel.add(emailField);
        panel.add(txtSearch);
        panel.add(nhapexcel);
        
        layout.putConstraint(SpringLayout.WEST, IDLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, IDLabel, 10, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, PasswordLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, PasswordLabel, 40, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, maNVLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, maNVLabel, 70, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, tenNVLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, tenNVLabel, 100, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, ngaysinhLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, ngaysinhLabel, 130, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, diachiLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, diachiLabel, 160, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, gioitinhLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, gioitinhLabel, 260, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, sdtLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, sdtLabel, 290, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, emailLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, emailLabel, 320, SpringLayout.NORTH, panel);
        
        layout.putConstraint(SpringLayout.WEST, IDField, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, IDField, 10, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, PasswordField, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, PasswordField, 40, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, maNVcb, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, maNVcb, 70, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, tenNVField, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, tenNVField, 100, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, ngaysinhField, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, ngaysinhField, 130, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, jScrollPaneAddress, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, jScrollPaneAddress, 160, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, gioitinhFiel, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, gioitinhFiel, 260, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, sdtField, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, sdtField, 290, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, emailField, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, emailField, 320, SpringLayout.NORTH, panel);
        
        layout.putConstraint(SpringLayout.WEST, jScrollPaneCostumerTable, 400, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, jScrollPaneCostumerTable, 20, SpringLayout.NORTH, panel);
        
        layout.putConstraint(SpringLayout.WEST, addAccount, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, addAccount, 400, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, editAccount, 60, SpringLayout.WEST, addAccount);
        layout.putConstraint(SpringLayout.NORTH, editAccount, 400, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, clearAccount, 60, SpringLayout.WEST, editAccount);
        layout.putConstraint(SpringLayout.NORTH, clearAccount, 400, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, deleteAccount, 70, SpringLayout.WEST, clearAccount);
        layout.putConstraint(SpringLayout.NORTH, deleteAccount, 400, SpringLayout.NORTH, panel);
        
         layout.putConstraint(SpringLayout.WEST, sortAcount, 400, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, sortAcount, 340, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, searchAccont, 100, SpringLayout.WEST, sortAcount);
        layout.putConstraint(SpringLayout.NORTH, searchAccont, 340, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, xuatexcelAcount, 120, SpringLayout.WEST, searchAccont);
        layout.putConstraint(SpringLayout.NORTH, xuatexcelAcount, 340, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, txtSearch, 120, SpringLayout.WEST, xuatexcelAcount);
        layout.putConstraint(SpringLayout.NORTH, txtSearch, 340, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, nhapexcel, 140, SpringLayout.WEST, txtSearch);
        layout.putConstraint(SpringLayout.NORTH, nhapexcel, 340, SpringLayout.NORTH, panel);
      
        
        
        add(panel);
        setTitle("Account Information");
        setSize(1050,600);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
        
        
        addAccount.addActionListener(this);
        editAccount.addActionListener(this);
        clearAccount.addActionListener(this);
        deleteAccount.addActionListener(this);
        sortAcount.addActionListener(this);
        searchAccont.addActionListener(this);
        xuatexcelAcount.addActionListener(this);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Perform actions when the window is closing
                System.out.println("Window is closing...");
                //menu d = new menu();
                menu d = new menu();
                d.setVisible(true);
                // Optionally, you can dispose or hide the frame
                // frame.dispose();
            }
        });
        ngaysinhField.addKeyListener(new KeyAdapter() {
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
    }
     public void hienthi_tbl(Account_DAO acd) {
         acd.loadDataToComboBox(this);
        acd.rsTableModel(AccountTable, tableModel);
    }
    
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
    
    public void actionPerformed(ActionEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
        JButton btn = (JButton) e.getSource();
        if (btn.equals(addAccount)) {
            addAccount_actionperformed();
            // Xóa hết dữ liệu từ model của JTable
          tableModel.setRowCount(0);
          tableModel.setColumnCount(0);
           Account_DAO acd = new Account_DAO();
            hienthi_tbl(acd);
        } 
         if(btn.equals(clearAccount)){
             clearAccount_actionperformed();
         }
        if(btn.equals(deleteAccount)){
            deleteAccount_actionperformed();
        }
        if(btn.equals(editAccount)){
            editAccount_actionperformed();
        }
        if(btn.equals(sortAcount)){
            tableModel.setRowCount(0);
            tableModel.setColumnCount(0);
          sortAccount_actionperformed();
        }
        if(btn.equals(searchAccont)){
            tableModel.setRowCount(0);
            tableModel.setColumnCount(0);
            searchAccount_actionperformed();
        }
        if(btn.equals(xuatexcelAcount)){
           xuatexcelAccount_actionperformed();
        }
        if(btn.equals(nhapexcel)){
           btn_nhap_actionerformed();
        }
        
    }
    public void btn_nhap_actionerformed() {
        Account_DAO d = new Account_DAO();
        d.nhapexcel(this);
    }
     private void addAccount_actionperformed() {
        //throw new UnsupportedOperationException("Not yet implemented");
         if(IDField.getText().isEmpty()||PasswordField.getText().isEmpty()||tenNVField.getText().isEmpty()||gioitinhFiel.getText().isEmpty()||diachiTA.getText().isEmpty()||ngaysinhField.getText().isEmpty()||emailField.getText().isEmpty()){
           JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin", "Thông báo", JOptionPane.WARNING_MESSAGE);
       }else if(!isValidEmail(emailField.getText().toString())){
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng định dạng email!", "Error", JOptionPane.ERROR_MESSAGE);
       }
       else if(sdtField.getText().length() != 10){
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng độ dài số điện thoại(10 số)", "Error", JOptionPane.ERROR_MESSAGE);
        }else if(!isValidPhoneNumber(sdtField.getText().toString())){
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng định dạng số điện thoại", "Error", JOptionPane.ERROR_MESSAGE);
        }else{
        Account ac = new Account();
        ac.setTxtID(IDField.getText());
        ac.setTxtPassWord(PasswordField.getText());
        ac.setTxtmaNV(maNVcb.getSelectedItem().toString());
        ac.setTxttenNV(tenNVField.getText());
        ac.setTxtGioiTinh(gioitinhFiel.getText());
        ac.setTxtTuoi(Integer.parseInt(ngaysinhField.getText()));
        ac.setTxtSDT(sdtField.getText());
        ac.setTxtDiaChi(diachiTA.getText());
        ac.setTxtEmail(emailField.getText());

        Account_DAO acd = new Account_DAO();
        acd.add_account(ac);
                
    // JOptionPane.showMessageDialog(null, "Add successful!");
    //std.rsTableModel(tbl_ds, model);
    }
     }

    private void clearAccount_actionperformed() {
        //throw new UnsupportedOperationException("Not yet implemented");
       IDField.setText(" ");
       PasswordField.setText(" ");
       maNVcb.setSelectedItem(null);
       tenNVField.setText(" ");
       ngaysinhField.setText(" ");
       gioitinhFiel.setText(" ");
       sdtField.setText(" ");
       diachiTA.setText(" ");
       emailField.setText(" ");
       
    }

    public void deleteAccount_actionperformed(){
        Account_DAO acd = new Account_DAO();
        
         acd.delete_account(o);
         clearAccount_actionperformed();
         tableModel.removeRow(row);
         tableModel.fireTableDataChanged();
       
//       IDField.setText(" ");
//       PasswordField.setText(" ");
//       //maNVField.setText(" ");
//       tenNVField.setText(" ");
//       ngaysinhField.setText(" ");
//       gioitinhFiel.setText(" ");
//       sdtField.setText(" ");
//       diachiTA.setText(" ");
//       emailField.setText(" ");
    }
    
    public void  editAccount_actionperformed(){
          if(IDField.getText().isEmpty()||PasswordField.getText().isEmpty()||tenNVField.getText().isEmpty()||gioitinhFiel.getText().isEmpty()||diachiTA.getText().isEmpty()||ngaysinhField.getText().isEmpty()||emailField.getText().isEmpty()){
           JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin", "Thông báo", JOptionPane.WARNING_MESSAGE);
       }else if(!isValidEmail(emailField.getText().toString())){
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng định dạng email!", "Error", JOptionPane.ERROR_MESSAGE);
       }
       else if(sdtField.getText().length() != 10){
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng độ dài số điện thoại(10 số)", "Error", JOptionPane.ERROR_MESSAGE);
        }else if(!isValidPhoneNumber(sdtField.getText().toString())){
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng định dạng số điện thoại", "Error", JOptionPane.ERROR_MESSAGE);
        }else{
        Account ac = new Account();
        ac.setTxtID(IDField.getText());
        ac.setTxtPassWord(PasswordField.getText());
        ac.setTxtmaNV(maNVcb.getSelectedItem().toString());
        ac.setTxttenNV(tenNVField.getText());
        ac.setTxtGioiTinh(gioitinhFiel.getText());
        ac.setTxtTuoi(Integer.parseInt(ngaysinhField.getText()));
        ac.setTxtGioiTinh(gioitinhFiel.getText());
        ac.setTxtSDT(sdtField.getText());
        ac.setTxtDiaChi(diachiTA.getText());
        ac.setTxtEmail(emailField.getText());

        Account_DAO acd = new Account_DAO();
        acd.edit_account(ac, o);
        
                tableModel.setValueAt(IDField.getText(),row, 0); // Lấy dữ liệu từ cột 0
                tableModel.setValueAt(PasswordField.getText(),row, 1);
                //tableModel.setValueAt(maNVField.getText(),row, 2); // Lấy dữ liệu từ cột 0
                tableModel.setValueAt(tenNVField.getText(),row, 3); // Lấy dữ liệu từ cột 1
                tableModel.setValueAt(ngaysinhField.getText(),row, 4); // Lấy dữ liệu từ cột 2
                tableModel.setValueAt(gioitinhFiel.getText(),row, 5); // Lấy dữ liệu từ cột 3
                tableModel.setValueAt(sdtField.getText(),row, 6); // Lấy dữ liệu từ cột 4
                tableModel.setValueAt(diachiTA.getText(),row, 7);
                tableModel.setValueAt(emailField.getText(), row,8);
    }
    
    }
    public void sortAccount_actionperformed(){
        Account_DAO acd = new Account_DAO();
        acd.sortaccount(AccountTable, tableModel);
    }
    
    public void searchAccount_actionperformed(){
        if(txtSearch.getText().equals("Vui lòng nhập ID")){
              JOptionPane.showMessageDialog(null, "Bạn đang để trống", "Thông báo", JOptionPane.WARNING_MESSAGE);
             Account_DAO acd = new Account_DAO();
              hienthi_tbl(acd);
          }else{
            Account_DAO acd = new Account_DAO();
          acd.searchaccount(AccountTable, tableModel, this);
          }        
    }
    
    public void xuatexcelAccount_actionperformed(){
          Account_DAO acd = new Account_DAO();
          acd.xuatexcel(AccountTable, tableModel, this);
    }   
       
    int fileCount = 1;
     public void btn_ExportDK_actionPerformed() {
    // Tạo workbook mới
    XSSFWorkbook workbook = new XSSFWorkbook();
    // Tạo một trang tính mới
    XSSFSheet sheet = workbook.createSheet("Sản phẩm " + fileCount);
    // Lấy số lượng hàng và cột trong JTable
    int rowCount = AccountTable.getRowCount();

    // Tạo hàng tiêu đề
    XSSFRow headerRow = sheet.createRow(0);
    // Tạo tiêu đề cho cột maSP
    XSSFCell cellMaSP = headerRow.createCell(0);
    cellMaSP.setCellValue("gioiTinh");
    // Tạo tiêu đề cho cột tenSP
   

    // Ghi dữ liệu từ JTable vào các hàng tiếp theo trong trang tính
    for (int i = 0; i < rowCount; i++) {
        XSSFRow row = sheet.createRow(i + 1); // Tạo một hàng mới (bỏ qua hàng tiêu đề)
        // Lấy giá trị của cột maSP từ JTable
        Object gioiTinh = AccountTable.getValueAt(i, 5);
        if (gioiTinh != null&& gioiTinh.toString().equalsIgnoreCase("nam")) {
            row.createCell(0).setCellValue(gioiTinh.toString());
        }
        // Lấy giá trị của cột tenSP từ JTable
       
    }

    try (FileOutputStream fileOut = new FileOutputStream("D:/ExportJava/TypeProduct_11" + fileCount + ".xlsx")) {
        workbook.write(fileOut);
        JOptionPane.showMessageDialog(null, "Tạo tệp Excel thành công: TypeProduct_11" + fileCount + ".xlsx");
    } catch (IOException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi khi tạo tệp Excel.");
    }
    fileCount++;
}
  
    public static void main(String[] args){
        Account_Frm acf = new Account_Frm();
    }

    
}