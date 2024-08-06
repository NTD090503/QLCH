/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package VanPhongPham.Controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import VanPhongPham.View.NhaCungCapFrm;
import VanPhongPham.Model.NhaCungCap;
import VanPhongPham.View.hoadon_frm;
import java.io.File;
import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
/**
 *
 * @author hoan3
 */
public class NhaCungCapDAO {
 private Connection conn;
    public NhaCungCapDAO(){
        String dbUrl = "jdbc:mysql://localhost:3306/vanphongpham";
        String dbClass = "com.mysql.jdbc.Driver";
        try {
            Class.forName(dbClass);
            conn = DriverManager.getConnection(dbUrl,"root","");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void rsTableModel(JTable tbl, DefaultTableModel model){
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM vppsupplier");
            ResultSetMetaData metaData = resultSet.getMetaData();
            
            int columnCount = metaData.getColumnCount();
            for(int columnIndex = 1; columnIndex <= columnCount; columnIndex++){
                model.addColumn(metaData.getColumnLabel(columnIndex));
            }
            
            // Them du lieu tu ket qua truy van vao model
            while(resultSet.next()){
                Object[] row = new Object[columnCount];
                for(int i = 0; i < columnCount; i++){
                    row[i] = resultSet.getObject(i+1);
                }
                model.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    public void add_NCC(NhaCungCap s){
        String sql = "INSERT INTO vppsupplier(maNCC,tenNCC,SDT,DiaChi,KhuVuc,PhuongXa,Email,CongTy,NhomNCC,noCanTra,GhiChu) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, s.getMaNcc());
            ps.setString(2, s.getTenNcc());
            ps.setString(3, s.getSoDienThoai());
            ps.setString(4, s.getDiaChi());
            ps.setString(5, s.getKhuVuc());
            ps.setString(6, s.getPhuongXa());
            ps.setString(7, s.getEmail());
            ps.setString(8, s.getCongTy());
            ps.setString(9, s.getNhomNcc());
            ps.setFloat(10, s.getNoCanTra());
            ps.setString(11, s.getGhiChu());
            
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Thêm nhà cung cấp thành công!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Thêm nhà cung cấp không thành công!");
            e.printStackTrace();
        }
    }
    
    public void sortDebt(JTable tbl, DefaultTableModel model){   
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM vppsupplier ORDER BY noCanTra ASC");
            ResultSetMetaData metaData = resultSet.getMetaData();
            
            int columnCount = metaData.getColumnCount();
            for(int columnIndex = 1; columnIndex <= columnCount; columnIndex++){
                model.addColumn(metaData.getColumnLabel(columnIndex));
            }
            
            // Them du lieu tu ket qua truy van vao model
            while(resultSet.next()){
                Object[] row = new Object[columnCount];
                for(int i = 0; i < columnCount; i++){
                    row[i] = resultSet.getObject(i+1);
                }
                model.addRow(row);
            }
            
            JOptionPane.showMessageDialog(null, "Bảng đã được sắp xếp tăng dần theo thứ tự nợ cần trả");
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void sortName(JTable tbl, DefaultTableModel model){   
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM vppsupplier ORDER BY tenNCC ASC");
            ResultSetMetaData metaData = resultSet.getMetaData();
            
            int columnCount = metaData.getColumnCount();
            for(int columnIndex = 1; columnIndex <= columnCount; columnIndex++){
                model.addColumn(metaData.getColumnLabel(columnIndex));
            }
            
            // Them du lieu tu ket qua truy van vao model
            while(resultSet.next()){
                Object[] row = new Object[columnCount];
                for(int i = 0; i < columnCount; i++){
                    row[i] = resultSet.getObject(i+1);
                }
                model.addRow(row);
            }
            
            JOptionPane.showMessageDialog(null, "Bảng đã được sắp xếp theo tên theo thứ tự tăng dần của tên(A-Z)!");
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void delete_NCC(Object j){
        String sql = "DELETE FROM vppsupplier WHERE maNCC = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, j.toString());
            
            
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Xóa nhà cung cấp thành công!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Xóa nhà cung cấp không thành công!");
            e.printStackTrace();
        }
    }
    
    public void edit_NCC(NhaCungCap s,Object IDold){
        String sql = "UPDATE vppsupplier SET maNCC=?,tenNCC=?,SDT=?,DiaChi=?,KhuVuc=?,PhuongXa=?,Email=?,CongTy=?,NhomNCC=?,noCanTra=?,GhiChu=? WHERE maNCC = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, s.getMaNcc());
            ps.setString(2, s.getTenNcc());
            ps.setString(3, s.getSoDienThoai());
            ps.setString(4, s.getDiaChi());
            ps.setString(5, s.getKhuVuc());
            ps.setString(6, s.getPhuongXa());
            ps.setString(7, s.getEmail());
            ps.setString(8, s.getCongTy());
            ps.setString(9, s.getNhomNcc());
            ps.setFloat(10, s.getNoCanTra());
            ps.setString(11, s.getGhiChu());
            
            ps.setString(12, IDold.toString());
            
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Sửa nhà cung cấp thành công!");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Sửa nhà cung cấp không thành công!");
        }
    }
    
    public  void searchValue(JTable table,DefaultTableModel model, NhaCungCapFrm frm) {    
        String sql = "SELECT * FROM vppsupplier WHERE maNCC LIKE ? OR NhomNCC LIKE ?";
        try {
            Statement statement = conn.createStatement();
            PreparedStatement ps = conn.prepareStatement(sql);
            String searchKey = frm.searchTxt.getText().trim();
            ps.setString(1, "%" + searchKey + "%");
            ps.setString(2,"%" + searchKey + "%");
            ResultSet resultSet = ps.executeQuery();
            
            ResultSetMetaData metaData = resultSet.getMetaData();
            
            
            int columnCount = metaData.getColumnCount();
            for(int columnIndex = 1; columnIndex <= columnCount; columnIndex++){
                model.addColumn(metaData.getColumnLabel(columnIndex));
            }
            
            // Them du lieu tu ket qua truy van vao model
            while(resultSet.next()){
                Object[] row = new Object[columnCount];
                for(int i = 0; i < columnCount; i++){
                    row[i] = resultSet.getObject(i+1);
                }
                model.addRow(row);
            }            
        } catch (SQLException e) {
            e.printStackTrace();
        }
}
    public void nhapexcel(NhaCungCapFrm v){
           JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    System.out.println("file "+selectedFile);
                    displayExcelData(selectedFile,v);
                }
   }
    public  void displayExcelData(File file,NhaCungCapFrm v) {
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
    
     public void excel_print(DefaultTableModel model){
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Lop Data");

        // Tạo hàng header
        XSSFRow headerRow = sheet.createRow(0);
        for (int i = 0; i < model.getColumnCount(); i++) {
            XSSFCell cell = headerRow.createCell(i);
            cell.setCellValue(model.getColumnName(i));
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
                for (int i = 0; i < model.getRowCount(); i++) {
                    XSSFRow row = sheet.createRow(i + 1);
                    for (int j = 0; j < model.getColumnCount(); j++) {
                        XSSFCell cell = row.createCell(j);
                        Object value = model.getValueAt(i, j);
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
}
