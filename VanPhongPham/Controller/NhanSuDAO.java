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
import VanPhongPham.View.NhanSuFrm;
import VanPhongPham.Model.NhanSu;
/**
 *
 * @author hoan3
 */
public class NhanSuDAO {
    private Connection conn;
    public NhanSuDAO(){
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
            ResultSet resultSet = statement.executeQuery("SELECT * FROM vppemployee");
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
    
    
    public void add_NhanVien(NhanSu s){
        String sql = "INSERT INTO vppemployee(maNV,tenNV,diaChi,sDT,ngaySinh,noiSinh,email,cMND,gioiTinh,trangThai) VALUES(?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, s.getMaNV());
            ps.setString(2, s.getTenNV());
            ps.setString(3, s.getDiaChi());
            ps.setString(4, s.getSDT());
            ps.setString(5, s.getNgaySinh());
            ps.setString(6, s.getNoiSinh());
            ps.setString(7, s.getEmail());
            ps.setString(8, s.getCMND());
            ps.setString(9, s.getGioiTinh());
            ps.setString(10, s.getTrangThai());
            
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Thêm nhân viên thành công!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Thêm nhân viên không thành công!");
            e.printStackTrace();
        }
    }
    
    public void sortMaNV(JTable tbl, DefaultTableModel model){   
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM vppemployee ORDER BY maNV ASC");
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
            
            JOptionPane.showMessageDialog(null, "Bảng đã được sắp xếp tăng dần theo thứ tự MÃ NHÂN VIÊN");
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void sortName(JTable tbl, DefaultTableModel model){   
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM vppemployee ORDER BY tenNV ASC");
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
    
    public void delete_NV(Object j){
        String sql = "DELETE FROM vppemployee WHERE maNV = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, j.toString());
            
            
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Xóa nhân viên thành công!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Xóa nhân viên không thành công!");
            e.printStackTrace();
        }
    }
    
    public void edit_NV(NhanSu s,Object IDold){
        String sql = "UPDATE vppemployee SET maNV=?,tenNV=?,diaChi=?,sDT=?,ngaySinh=?,noiSinh=?,email=?,cMND=?,gioiTinh=?,trangThai=? WHERE maNV = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, s.getMaNV());
            ps.setString(2, s.getTenNV());
            ps.setString(3, s.getDiaChi());
            ps.setString(4, s.getSDT());
            ps.setString(5, s.getNgaySinh());
            ps.setString(6, s.getNoiSinh());
            ps.setString(7, s.getEmail());
            ps.setString(8, s.getCMND());
            ps.setString(9, s.getGioiTinh());
            ps.setString(10, s.getTrangThai());
            
            ps.setString(11, IDold.toString());
            
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Sửa nhân viên thành công!");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Sửa nhân viên không thành công!");
        }
    }
    
    public  void searchValue(JTable table,DefaultTableModel model, NhanSuFrm frm) {    
        String sql = "SELECT * FROM vppemployee WHERE maNV LIKE ? OR tenNV LIKE ?";
        try {
            Statement statement = conn.createStatement();
            PreparedStatement ps = conn.prepareStatement(sql);
            String keySearch = frm.searchTxt.getText().trim();
            ps.setString(1, "%" + keySearch + "%");
            ps.setString(2, "%" + keySearch + "%");
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
