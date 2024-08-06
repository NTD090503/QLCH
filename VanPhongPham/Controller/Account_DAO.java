/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package VanPhongPham.Controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import VanPhongPham.View.Account_Frm;
import VanPhongPham.Model.Account;
import VanPhongPham.View.hoadon_frm;
import java.io.File;
import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author quynh
 */
public class Account_DAO {
      private Connection con;
     
      public Account_DAO(){
          String dbUrl = "jdbc:mysql://localhost:3306/vanphongpham";
          String dbClass = "com.mysql.jdbc.Driver";
          try{
              Class.forName(dbClass);
                con =  DriverManager.getConnection(dbUrl, "root", "");
              //  con = DriverManager.getConnection(dbUrl,"root","");
                System.out.print("Hello");
          }catch(Exception e){
              e.printStackTrace();
          }
          
      }
      public void rsTableModel (JTable AcconutTable, DefaultTableModel tableModel){
         // arr = new ArrayList<Student>();
          try{
              Statement statement =  con.createStatement();
              ResultSet resultSet = statement.executeQuery("SELECT * FROM vppaccount");
              ResultSetMetaData metaData = resultSet.getMetaData();
              
              int columnCount =metaData.getColumnCount();
              for (int columnIndex = 1; columnIndex<= columnCount ; columnIndex ++){
                  tableModel.addColumn(metaData.getColumnLabel(columnIndex));
              }
              while(resultSet.next()){
                  Object[] row = new Object[columnCount];
                  for(int i=0; i<columnCount; i++){
                      row[i] = resultSet.getObject(i+1);
                  }
                  //resultSet.getString("")
                  //arr.add()
                  tableModel.addRow(row);
                  
              }
          }catch(Exception e){
              e.printStackTrace();
          }
      }
       public void add_account(Account ac){
           System.out.println(" mnv "+ac.getTxtmaNV());
          String sql = "INSERT INTO vppaccount(iD, passWord, maNV, tenNV, Tuoi, gioiTinh, sDT, diaChi, email) value(?,?,?,?,?,?,?,?,?)";
          try{
              PreparedStatement ps =  con.prepareStatement(sql);
              ps.setString(1, ac.getTxtID());
              ps.setString(2, ac.getTxtPassWord());
              ps.setString(3, ac.getTxtmaNV());
              ps.setString(4, ac.getTxttenNV());
              ps.setInt(5, ac.getTxtTuoi());
              ps.setString(6, ac.getTxtGioiTinh());
              ps.setString(7, ac.getTxtSDT());
              ps.setString(8, ac.getTxtDiaChi());
              ps.setString(9, ac.getTxtEmail());
              
              ps.executeUpdate();
              JOptionPane.showMessageDialog(null, "Thêm khách hàng thành công!");
          }catch(Exception e){
              JOptionPane.showMessageDialog(null, "Thêm khách hàng thất bại!");
              e.printStackTrace();
          }
      }
       public void loadDataToComboBox(Account_Frm acf) {
          try {
            // Tạo statement và thực hiện truy vấn để lấy dữ liệu từ CSDL
                Statement statement = con.createStatement(); 
                ResultSet resultSet = statement.executeQuery("SELECT maNV FROM vppemployee ");
                // Xóa toàn bộ mục trong JComboBox trước khi thêm mới
                acf.maNVcb.removeAllItems();
              
                
                // Thêm dữ liệu từ kết quả truy vấn vào JComboBox
                while (resultSet.next()) {
                    String item = resultSet.getString("maNV");
                    acf.maNVcb.addItem(item);
                    
                }
                // Đóng các tài nguyên
                resultSet.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
       public void delete_account(Object j){
        String sql = "DELETE FROM vppaccount WHERE iD = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, j.toString());
            int row = ps.executeUpdate();
            if(row>0 )JOptionPane.showMessageDialog(null, "Xóa tài khoản thành công!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Xóa tài khoản thất bại!");
            e.printStackTrace();
        }
    
      } 
        public void edit_account(Account ac, Object obj){
            String sql = "UPDATE vppaccount SET iD = ?, passWord = ? ,maNV =? , tenNV =?, Tuoi =?, gioiTinh =? , sDT = ? , diaChi = ? , email = ? WHERE iD = ?";
          try{
              PreparedStatement ps =  con.prepareStatement(sql);
               ps.setString(1, ac.getTxtID());
              ps.setString(2, ac.getTxtPassWord());
              ps.setString(3, ac.getTxtmaNV());
              ps.setString(4, ac.getTxttenNV());
              ps.setInt(5, ac.getTxtTuoi());
              ps.setString(6, ac.getTxtGioiTinh());
              ps.setString(7, ac.getTxtSDT());
              ps.setString(8, ac.getTxtDiaChi());
              ps.setString(9, ac.getTxtEmail());
              ps.setString(10, obj.toString());
              ps.executeUpdate();
              JOptionPane.showMessageDialog(null, "Sửa tài khoản thành công!");
          }catch(Exception e){
              JOptionPane.showMessageDialog(null, "Sửa tài khoản thất bại!");
              e.printStackTrace();
          }
      }
         public void sortaccount (JTable AccountTable, DefaultTableModel tableModel){
          try{
              Statement sta = con.createStatement();
              ResultSet res = sta.executeQuery("SELECT * FROM vppaccount ORDER BY iD DESC");
              ResultSetMetaData resm = res.getMetaData();
              
              int columnCount = resm.getColumnCount();
              for(int columnIndex = 1; columnIndex<= columnCount; columnIndex++){
                  tableModel.addColumn(resm.getColumnLabel(columnIndex));
              }
              //them du lieu tu ket qua truy van vao model
              while(res.next()){
                  Object[] row = new Object[columnCount];
                  for(int i =0; i<columnCount; i++){
                      row[i] = res.getObject(i+1);
                  }
                  tableModel.addRow(row);
              }
              JOptionPane.showMessageDialog(null, "Tài khoản đã được sắp xếp thành công");
          }catch(Exception e){
              e.printStackTrace();
          }
      }
          public  void searchaccount(JTable AccountTable,DefaultTableModel tableModel, Account_Frm acf) {    
        String sql = "SELECT * FROM vppaccount WHERE iD LIKE ?";
        try {
            Statement statement = con.createStatement();
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
            String id = acf.txtSearch.getText().trim();
            ps.setString(1, "%" + id + "%");
            ResultSet resultSet = ps.executeQuery();
            
            ResultSetMetaData metaData = resultSet.getMetaData();
            
            
            int columnCount = metaData.getColumnCount();
            for(int columnIndex = 1; columnIndex <= columnCount; columnIndex++){
                tableModel.addColumn(metaData.getColumnLabel(columnIndex));
            }
            
            // Them du lieu tu ket qua truy van vao model
            while(resultSet.next()){
                Object[] row = new Object[columnCount];
                for(int i = 0; i < columnCount; i++){
                    row[i] = resultSet.getObject(i+1);
                }
                tableModel.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
          }

       // public void xuatexcel6(JTable AccountTable,DefaultTableModel tableModel, Account_Frm  acf ){
        public void xuatexcel(JTable customerTable,DefaultTableModel tableModel, Account_Frm csf){
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Lop Data");

        // Tạo hàng header
        XSSFRow headerRow = sheet.createRow(0);
        for (int i = 0; i < tableModel.getColumnCount(); i++) {
            XSSFCell cell = headerRow.createCell(i);
            cell.setCellValue(tableModel.getColumnName(i));
        }

        // Dữ liệu
        FileOutputStream fileOut = null;
        try {
            // Hiển thị hộp thoại lưu tệp
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Save Excel File");
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel Files (*.xlsx)", "xlsx");
            fileChooser.setFileFilter(filter);
            int userSelection = fileChooser.showSaveDialog(null);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                String filePath = fileChooser.getSelectedFile().getAbsolutePath();
                if (!filePath.endsWith(".xlsx")) {
                    filePath += ".xlsx"; // Đảm bảo rằng tên file có phần mở rộng .xlsx
                }
                fileOut = new FileOutputStream(filePath);

                // Viết dữ liệu vào file Excel
                for (int i = 0; i < tableModel.getRowCount(); i++) {
                    XSSFRow row = sheet.createRow(i + 1);
                    for (int j = 0; j < tableModel.getColumnCount(); j++) {
                        XSSFCell cell = row.createCell(j);
                        //dặt điều kiện tại if
                        if(tableModel.getValueAt(i, 5).toString().equalsIgnoreCase("nam"))
                        {
                           Object value = tableModel.getValueAt(i, j);
                        if (value != null) {
                            cell.setCellValue(value.toString());
                        } 
                        }
                        
                    }
                }

                // Lưu file Excel
                workbook.write(fileOut);
                System.out.println("Excel file exported successfully.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileOut != null) {
                try {
                    fileOut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
         }

          
        public void nhapexcel(Account_Frm v){
           JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    System.out.println("file "+selectedFile);
                    displayExcelData(selectedFile,v);
                }
   }
    public  void displayExcelData(File file,Account_Frm v) {
         // Clear old data in the table before adding new data
        v.tableModel.setRowCount(0);
        try  {
            FileInputStream fis = new FileInputStream(file);
            Workbook workbook = new XSSFWorkbook(fis); 
            Sheet sheet = workbook.getSheetAt(0);

            // Loop through each row in the sheet and add data to the table
            //for (Row row : sheet) {
              for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                Row row = sheet.getRow(rowIndex);
                Object[] rowData = new Object[row.getLastCellNum()];
                for (int i = 0; i < row.getLastCellNum(); i++) {
                    Cell cell = row.getCell(i);
                    if (cell == null) {
                        rowData[i] = "";
                    } else {
                         rowData[i] = cell.getStringCellValue();
//                        switch (cell.getCellType()) {
//                            case STRING:
//                                rowData[i] = cell.getStringCellValue();
//                                //System.out.println("row1 "+rowData[i]);
//                                break;
//                            case NUMERIC:
//                                rowData[i] = cell.getNumericCellValue();
//                                //System.out.println("row2 "+rowData[i]);
//                                break;
//                            case BOOLEAN:
//                                rowData[i] = cell.getBooleanCellValue();
//                                //System.out.println("row3 "+rowData[i]);
//                                break;
//                            default:
//                                rowData[i] = "";
//                        }
                       // model.addRow(rowData);
                        //System.out.println("row "+rowData[i]);
                    }
                }
                v.tableModel.addRow(rowData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        v.tableModel.fireTableDataChanged();
    }
        public static  void main(String [] args){
            Account_DAO acd = new Account_DAO();
        }
}
