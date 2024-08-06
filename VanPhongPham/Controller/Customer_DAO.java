/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package VanPhongPham.Controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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
import VanPhongPham.View.Customer_Frm;
import VanPhongPham.Model.Customer;
/**
 *
 * @author quynh
 */
public class Customer_DAO {
     private Connection con;
     
      public Customer_DAO(){
          String dbUrl = "jdbc:mysql://localhost:3306/vanphongpham";
          String dbClass = "com.mysql.jdbc.Driver";
          try{
              Class.forName(dbClass);
                con =  DriverManager.getConnection(dbUrl, "root", "");
              //  con = DriverManager.getConnection(dbUrl,"root","");
                System.out.print("Kết nối thành công!");
          }catch(Exception e){
              e.printStackTrace();
          }
          
      }
      public void rsTableModel (JTable customerTable, DefaultTableModel tableModel){
         // arr = new ArrayList<Student>();
          try{
              Statement statement =  con.createStatement();
              ResultSet resultSet = statement.executeQuery("SELECT * FROM vppcustomer");
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
       public void add_customer(Customer cs){
          String sql = "INSERT INTO vppcustomer(maKH, tenKh, Tuoi, gioiTinh, sDT, diaChi, email) value(?,?,?,?,?,?,?)";
          try{
              PreparedStatement ps =  con.prepareStatement(sql);
              ps.setString(1, cs.getTxtMaKh());
              ps.setString(2, cs.getTxtTenKH());
              ps.setInt(3, cs.getNgaySinh());
              ps.setString(4, cs.getTxtGioiTinh());
              ps.setString(6, cs.getTxtDiaChi());
              ps.setString(5, cs.getSDT());
              ps.setString(7, cs.getTxtEmail());
                     Customer_Frm cfm = null;
      
              ps.executeUpdate();
            
              JOptionPane.showMessageDialog(null, "Thêm khách hàng thành công!");
          }catch(Exception e){
              JOptionPane.showMessageDialog(null, "Thêm khách hàng thất bại!");
              e.printStackTrace();
          }
      }
       
       public void delete_customer(Object j){
        
        String sql = "DELETE FROM vppcustomer WHERE maKH = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, j.toString());
            int row = ps.executeUpdate();
            if(row>0 )
                JOptionPane.showMessageDialog(null, "Bạn chắc chắn xóa khách hàng này không?", "Thông báo", JOptionPane.WARNING_MESSAGE);
                JOptionPane.showMessageDialog(null, "Xóa khách hàng thành công!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Xóa khách hàng thất bại!");
            e.printStackTrace();
        }
    
      } 
       
        public void edit_customer(Customer cs, Object obj){
            String sql = "UPDATE vppcustomer SET maKH =? , tenKH =?, Tuoi =?, gioiTinh =? , sDT = ? , diaChi = ? , email = ? WHERE maKH = ?";
          try{
              PreparedStatement ps =  con.prepareStatement(sql);
              ps.setString(1, cs.getTxtMaKh());
              ps.setString(2, cs.getTxtTenKH());
              ps.setInt(3, cs.getNgaySinh());
              ps.setString(4, cs.getTxtGioiTinh());
              ps.setString(5, cs.getSDT());
              ps.setString(6, cs.getTxtDiaChi());
              ps.setString(7, cs.getTxtEmail());
              ps.setString(8, obj.toString());
              ps.executeUpdate();
              JOptionPane.showMessageDialog(null, "Sửa khách hàng thành công!");
          }catch(Exception e){
              JOptionPane.showMessageDialog(null, "Sửa khách hàng thất bại!");
              e.printStackTrace();
          }
      }
        
        public void sortGPA (JTable customerTable, DefaultTableModel tableModel){
          try{
              Statement sta = con.createStatement();
              ResultSet res = sta.executeQuery("SELECT * FROM vppcustomer ORDER BY maKH ASC");
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
              JOptionPane.showMessageDialog(null, "Khách hàng đã được sắp xếp thành công");
          }catch(Exception e){
              e.printStackTrace();
          }
      }
        
        public  void searchValue(JTable customerTable,DefaultTableModel tableModel, Customer_Frm cfm) {    
        String sql = "SELECT * FROM vppcustomer WHERE maKH LIKE ?";
        try {
            Statement statement = con.createStatement();
            PreparedStatement ps = con.prepareStatement(sql);
            String id = cfm.txtSearch.getText().trim();
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
         public void xuatexcel(JTable customerTable,DefaultTableModel tableModel, Customer_Frm csf){
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
                        Object value = tableModel.getValueAt(i, j);
                        if (value != null) {
                            cell.setCellValue(value.toString());
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
        
        public static void main(String[] args){
            Customer_DAO csd = new Customer_DAO();
        }
                
      
}