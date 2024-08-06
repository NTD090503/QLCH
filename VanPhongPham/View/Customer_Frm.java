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
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import VanPhongPham.Controller.Customer_DAO;
import VanPhongPham.Model.Customer;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
public class Customer_Frm extends JFrame implements ActionListener{
    public JButton addCustomer, btnexit;
    public JButton editCustomer;
    public JButton deleteCustomer;
    public JButton clearCustomer;
    public JButton sortCustomerGPABtn;
    public JButton xuatexcelCustomer;
    public JButton searchCustomer;
    public JScrollPane jScrollPaneCostumerTable;
    public JTable customerTable;
    public String[][] data;
    public DefaultTableModel tableModel;
    public JLabel makhLabel;
    public JLabel tenkhLabel;
    public JLabel ngaysinhLabel;
    public JLabel gioitinhLabel;
    public JLabel sdtLabel;
    public JLabel diachiLabel;
    public JLabel emailLabel;

    public JTextField makhField;
    public JTextField tenkhField;
    public JTextField ngaysinhField;
    public JTextArea diachiTA;
    public JTextField sdtField;
    public JTextField gioitinhFiel;
    public JTextField emailField;
    public JTextField txtSearch;
    public int row;
    public Object o;
   

    
    //private ActionEvent e;

    public Customer_Frm() {
        initComponents();
        Customer_DAO csd = new Customer_DAO();
        hienthi_tbl(csd);
        
    }

   

    public void initComponents() {
        //setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        addCustomer = new JButton("Add");
        editCustomer = new JButton("Edit");
        deleteCustomer = new JButton("Delete");
        clearCustomer = new JButton("Clear");
        sortCustomerGPABtn = new JButton("Sort By ID");
        searchCustomer = new JButton("Search Name");
        xuatexcelCustomer = new JButton("Xuat Excel");
        btnexit = new JButton("Đăng Xuất");

        
        jScrollPaneCostumerTable = new JScrollPane(customerTable);
        customerTable = new JTable();
        customerTable.addMouseListener(new MouseListener() {
            
            
             //addCustomer.addActionListener(this);
       
            
            public void mouseClicked(MouseEvent e) {           
                row = customerTable.getSelectedRow();
                // lay du lieu tu cot 0
                o = tableModel.getValueAt(row, 0);
                
                // hien thi du lieu len JTextFiled
               Object datamaKH =  tableModel.getValueAt(row, 0); // Lấy dữ liệu từ cột 0
               Object datatenKH = tableModel.getValueAt(row, 1); // Lấy dữ liệu từ cột 1
               Object datangaySinh =  tableModel.getValueAt(row, 2); // Lấy dữ liệu từ cột 2
               Object datagioiTinh = tableModel.getValueAt(row, 3); // Lấy dữ liệu từ cột 3
               Object datasDT = tableModel.getValueAt(row, 4); // Lấy dữ liệu từ cột 4
               Object datadiaChi = tableModel.getValueAt(row, 5);
               Object dataemail = tableModel.getValueAt(row, 6);
               
                makhField.setText(datamaKH.toString());
                tenkhField.setText(datatenKH.toString());
                ngaysinhField.setText(datangaySinh.toString());
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

        makhLabel = new JLabel("Mã KH");
        tenkhLabel = new JLabel("Tên KH");
        ngaysinhLabel = new JLabel("Tuổi");
        gioitinhLabel = new JLabel("Giới tính");
        diachiLabel = new JLabel("Địa Chỉ");
        sdtLabel = new JLabel("SĐT");
        emailLabel = new JLabel("Email");

        makhField = new JTextField(6);
        tenkhField = new JTextField(15);
        ngaysinhField = new JTextField(6);
        diachiTA = new JTextArea();
        diachiTA.setColumns(15);
        diachiTA.setRows(5);
        JScrollPane jScrollPaneAddress = new JScrollPane(diachiTA);
        gioitinhFiel = new JTextField(6);
        sdtField = new JTextField(15);
        emailField = new JTextField(20);
        txtSearch = new JTextField(10);
        txtSearch.setPreferredSize(new Dimension(100,25));
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
        
//        data = new String[][]{
//                { "1", "Bui Nhu Quynh", "21","Ha Noi","9.0" }
//               
//        };
//        String[] columnNames = { "ID", "Name", "Age","Address","GPA"};        
        //tbl_ds = new JTable(data, columnNames);
        tableModel  = new DefaultTableModel();
        customerTable.setModel(tableModel);
        jScrollPaneCostumerTable.setViewportView(customerTable);
        jScrollPaneCostumerTable.setPreferredSize(new Dimension(500,300));
    
  

        SpringLayout layout = new SpringLayout();
        JPanel panel = new JPanel();
        panel.setSize(1200,600);
        panel.setLayout(layout);
        panel.add(jScrollPaneCostumerTable);

        panel.add(addCustomer);
        panel.add(editCustomer);
        panel.add(deleteCustomer);
        panel.add(clearCustomer);
        panel.add(sortCustomerGPABtn);
        panel.add(searchCustomer);
        panel.add(xuatexcelCustomer);
        
        

        panel.add(makhLabel);
        panel.add(tenkhLabel);
        panel.add(ngaysinhLabel);
        panel.add(diachiLabel);
        panel.add(sdtLabel);
        panel.add(gioitinhLabel);
        panel.add(emailLabel);

        panel.add(makhField);
        panel.add(tenkhField);
        panel.add(ngaysinhField);
        panel.add(jScrollPaneAddress);
        panel.add(gioitinhFiel);
        panel.add(sdtField);
        panel.add(emailField);
        panel.add(txtSearch);
         

        layout.putConstraint(SpringLayout.WEST, makhLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, makhLabel, 10, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, tenkhLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, tenkhLabel, 40, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, ngaysinhLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, ngaysinhLabel, 70, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, diachiLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, diachiLabel, 100, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, gioitinhLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, gioitinhLabel, 200, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, sdtLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, sdtLabel, 230, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, emailLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, emailLabel, 260, SpringLayout.NORTH, panel);
        

        layout.putConstraint(SpringLayout.WEST, makhField, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, makhField, 10, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, tenkhField, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, tenkhField, 40, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, ngaysinhField, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, ngaysinhField, 70, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, jScrollPaneAddress, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, jScrollPaneAddress, 100, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, gioitinhFiel, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, gioitinhFiel, 200, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, sdtField, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, sdtField, 230, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, emailField, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, emailField, 260, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, jScrollPaneCostumerTable, 400, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, jScrollPaneCostumerTable, 20, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, addCustomer, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, addCustomer, 300, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, editCustomer, 60, SpringLayout.WEST, addCustomer);
        layout.putConstraint(SpringLayout.NORTH, editCustomer, 300, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, clearCustomer, 60, SpringLayout.WEST, editCustomer);
        layout.putConstraint(SpringLayout.NORTH, clearCustomer, 300, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, deleteCustomer, 70, SpringLayout.WEST, clearCustomer);
        layout.putConstraint(SpringLayout.NORTH, deleteCustomer, 300, SpringLayout.NORTH, panel);
        
        
        layout.putConstraint(SpringLayout.WEST, sortCustomerGPABtn, 400, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, sortCustomerGPABtn, 340, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, searchCustomer, 100, SpringLayout.WEST, sortCustomerGPABtn);
        layout.putConstraint(SpringLayout.NORTH, searchCustomer, 340, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, xuatexcelCustomer, 120, SpringLayout.WEST, searchCustomer);
        layout.putConstraint(SpringLayout.NORTH, xuatexcelCustomer, 340, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, txtSearch, 120, SpringLayout.WEST, xuatexcelCustomer);
        layout.putConstraint(SpringLayout.NORTH, txtSearch, 340, SpringLayout.NORTH, panel);
        
        
        
        addCustomer.addActionListener(this);
        editCustomer.addActionListener(this);
        clearCustomer.addActionListener(this);
        deleteCustomer.addActionListener(this);
        sortCustomerGPABtn.addActionListener(this);
        searchCustomer.addActionListener(this);
        xuatexcelCustomer.addActionListener(this);
  
        
        add(panel);
        setTitle("Customer Information");
        setSize(1000,700);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);

        this.addWindowListener(new WindowAdapter() {
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
    public void hienthi_tbl(Customer_DAO csd) {
        csd.rsTableModel(customerTable, tableModel);
    }

    // kiểm tra đinhj dạng email
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
        if (btn.equals(addCustomer)) {
            addCustomer_actionperformed();
            // Xóa hết dữ liệu từ model của JTable
          tableModel.setRowCount(0);
          tableModel.setColumnCount(0);
            Customer_DAO csd = new Customer_DAO();
            hienthi_tbl(csd);
        } 
         if(btn.equals(clearCustomer)){
             clearCustomer_actionperformed();
         }
        if(btn.equals(deleteCustomer)){
            deleteCustomer_actionperformed();
        }
        if(btn.equals(editCustomer)){
            editCustomer_actionperformed();
        }
        if(btn.equals(sortCustomerGPABtn)){
            tableModel.setRowCount(0);
            tableModel.setColumnCount(0);
          sortCustomerGPABtn_actionperformed();
        }
        if(btn.equals(searchCustomer)){
            tableModel.setRowCount(0);
            tableModel.setColumnCount(0);
            searchCustomer_actionperformed();           
        }
        if(btn.equals(xuatexcelCustomer)){
           xuatexcelCustomer_actionperformed();
        
        }
    }
     private void addCustomer_actionperformed() {
        //throw new UnsupportedOperationException("Not yet implemented");
        
       if(makhField.getText().isEmpty()||tenkhField.getText().isEmpty()||diachiTA.getText().isEmpty()||sdtField.getText().isEmpty()||gioitinhFiel.getText().isEmpty()||ngaysinhField.getText().isEmpty()||emailField.getText().isEmpty()){
           JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin", "Thông báo", JOptionPane.WARNING_MESSAGE);
       }else if(!isValidEmail(emailField.getText().toString())){
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng định dạng email!", "Error", JOptionPane.ERROR_MESSAGE);
       }
       else if(sdtField.getText().length() != 10){
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng độ dài số điện thoại(10 số)", "Error", JOptionPane.ERROR_MESSAGE);
        }else if(!isValidPhoneNumber(sdtField.getText().toString())){
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng định dạng số điện thoại", "Error", JOptionPane.ERROR_MESSAGE);
        }else{
        Customer cs = new Customer();
        cs.setTxtMaKh(makhField.getText());
        cs.setTxtTenKH(tenkhField.getText());
        cs.setTxtDiaChi(diachiTA.getText());
        cs.setSDT(sdtField.getText());
        cs.setTxtGioiTinh(gioitinhFiel.getText());
        cs.setNgaySinh(Integer.parseInt(ngaysinhField.getText()));
        cs.setTxtEmail(emailField.getText());     
        Customer_DAO csd = new Customer_DAO();
        csd.add_customer(cs);
                
    // JOptionPane.showMessageDialog(null, "Add successful!");
    //std.rsTableModel(tbl_ds, model);
    }
     }
     

    private void clearCustomer_actionperformed() {
        //throw new UnsupportedOperationException("Not yet implemented");
       makhField.setText(" ");
       tenkhField.setText(" ");
       ngaysinhField.setText(" ");
       gioitinhFiel.setText(" ");
       sdtField.setText(" ");
       diachiTA.setText(" ");
       emailField.setText(" ");
       
    }

    public void deleteCustomer_actionperformed(){
        Customer_DAO csd = new Customer_DAO();
         csd.delete_customer(o);
         tableModel.removeRow(row);
         tableModel.fireTableDataChanged();
         
       makhField.setText(" ");
       tenkhField.setText(" ");
       ngaysinhField.setText(" ");
       gioitinhFiel.setText(" ");
       sdtField.setText(" ");
       diachiTA.setText(" ");
       emailField.setText(" ");
    }
    
    public void  editCustomer_actionperformed(){
         if(makhField.getText().isEmpty()||tenkhField.getText().isEmpty()||diachiTA.getText().isEmpty()||sdtField.getText().isEmpty()||gioitinhFiel.getText().isEmpty()||ngaysinhField.getText().isEmpty()||emailField.getText().isEmpty()){
           JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin", "Thông báo", JOptionPane.WARNING_MESSAGE);
       }else if(!isValidEmail(emailField.getText().toString())){
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng định dạng email!", "Error", JOptionPane.ERROR_MESSAGE);
       }
       else if(sdtField.getText().length() != 10){
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng độ dài số điện thoại(10 số)", "Error", JOptionPane.ERROR_MESSAGE);
        }else if(!isValidPhoneNumber(sdtField.getText().toString())){
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng định dạng số điện thoại", "Error", JOptionPane.ERROR_MESSAGE);
        }else{
        Customer cs = new Customer();
        cs.setTxtMaKh(makhField.getText());
        cs.setTxtTenKH(tenkhField.getText());
        cs.setNgaySinh(Integer.parseInt(ngaysinhField.getText()));
        cs.setTxtGioiTinh(gioitinhFiel.getText());
        cs.setSDT(sdtField.getText());
        cs.setTxtDiaChi(diachiTA.getText());
        cs.setTxtEmail(emailField.getText());

        Customer_DAO csd = new Customer_DAO();
        csd.edit_customer(cs, o);
        
                tableModel.setValueAt(makhField.getText(),row, 0); // Lấy dữ liệu từ cột 0
                tableModel.setValueAt(tenkhField.getText(),row, 1); // Lấy dữ liệu từ cột 1
                tableModel.setValueAt(ngaysinhField.getText(),row, 2); // Lấy dữ liệu từ cột 2
                tableModel.setValueAt(gioitinhFiel.getText(),row, 3); // Lấy dữ liệu từ cột 3
                tableModel.setValueAt(sdtField.getText(),row, 4); // Lấy dữ liệu từ cột 4
                tableModel.setValueAt(diachiTA.getText(),row, 5);
                tableModel.setValueAt(emailField.getText(), row,6);
    }
    
    } 
    public void sortCustomerGPABtn_actionperformed(){
        Customer_DAO csd = new Customer_DAO();
        csd.sortGPA(customerTable, tableModel);
    }
    
    public void searchCustomer_actionperformed(){     
          if(txtSearch.getText().equals("Vui lòng nhập ID")){
              JOptionPane.showMessageDialog(null, "Bạn đang để trống", "Thông báo", JOptionPane.WARNING_MESSAGE);
             Customer_DAO csd = new Customer_DAO();
              hienthi_tbl(csd);
          }else{
              Customer_DAO csd = new Customer_DAO();
          csd.searchValue(customerTable, tableModel, this);
          }
    }
    
    public void xuatexcelCustomer_actionperformed(){
          Customer_DAO csd = new Customer_DAO();
          csd.xuatexcel(customerTable, tableModel, this);
    }   
        
    public static void main(String[] args) {
       Customer_Frm cfm = new Customer_Frm();
    }
    // Biến đếm cho tên tệp Excel
    int fileCount = 1;
     public void btn_ExportDK_actionPerformed() {
    // Tạo workbook mới
    XSSFWorkbook workbook = new XSSFWorkbook();
    // Tạo một trang tính mới
    XSSFSheet sheet = workbook.createSheet("Sản phẩm " + fileCount);
    // Lấy số lượng hàng và cột trong JTable
    int rowCount = customerTable.getRowCount();

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
        Object maSPValue = customerTable.getValueAt(i, 0);
        if (maSPValue != null) {
            row.createCell(0).setCellValue(maSPValue.toString());
        }
        // Lấy giá trị của cột tenSP từ JTable
        Object tenSPValue = customerTable.getValueAt(i, 1);
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

 
    
    
    
}