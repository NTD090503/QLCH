/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package VanPhongPham.Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import VanPhongPham.View.product_form;
import VanPhongPham.Model.product_model;
import VanPhongPham.View.hoadon_frm;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.swing.JFileChooser;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/**
 *
 * @author TranNgoc ^.^
 */
public class product_DAO {

    private Connection conn;

    public product_DAO() {
        conn();
//        rsTableModel(tbl, model);
    }

    public Connection conn() {

        try {
            String JDBC_URL = "jdbc:mysql://localhost:3306/vanphongpham";
            String USER = "root";
            String PASSWORD = "";
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            System.out.println("Kết nối thành công !");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            System.out.println("Kết nối thất bại!");
        }
        return null;
    }

    ;
    public void rsTableModel(JTable tbl, DefaultTableModel model) {
        try {
            Statement statement = conn.createStatement();
            String sql = "SELECT * FROM "
                    + "(vppproduct as sp inner join vppsupplier as ncc on sp.maNCC=ncc.maNCC )"
                    + " inner join vppCategory as dm on sp.maLoai=dm.maLoai";
            ResultSet resultSet = statement.executeQuery(sql);
            addTableModel(model, resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Đưa dữ liệu từ CSDL lên JComboBox
    public void loadDataToComboBox(product_form view) {
        try {
            // Tạo statement và thực hiện truy vấn để lấy dữ liệu từ CSDL
            Statement statement = conn.createStatement();
            Statement statement1 = conn.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT tenNCC FROM vppsupplier ");
            ResultSet resultSet1 = statement1.executeQuery("SELECT tenLoai FROM vppcategory");
            // Xóa toàn bộ mục trong JComboBox trước khi thêm mới
            view.cboTenNCC.removeAllItems();
            view.cboTenDM.removeAllItems();

            // Thêm dữ liệu từ kết quả truy vấn vào JComboBox
            while (resultSet.next()) {
                String item = resultSet.getString("tenNCC");
                view.cboTenNCC.addItem(item);
            }
            while (resultSet1.next()) {
                String item1 = resultSet1.getString("tenLoai");
                view.cboTenDM.addItem(item1);
            }
            // Đóng các tài nguyên
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public String layma(product_form view) {
        // Khởi tạo biến maNCC và maLoai ở đây để tránh lỗi khi truy cập từ bên ngoài khối try
        String maNCC = null;
        String maLoai = null;

        // Lấy tên của nhà cung cấp và danh mục từ combobox
        String tenNCC = view.cboTenNCC.getSelectedItem().toString();
        String tenDM = view.cboTenDM.getSelectedItem().toString();

        String sql1 = "SELECT maNCC FROM vppsupplier WHERE tenNCC = ?";
        String sql2 = "SELECT maLoai FROM vppcategory WHERE tenLoai = ?";
        try {
            PreparedStatement statement1 = conn.prepareStatement(sql1);
            statement1.setString(1, tenNCC);
            ResultSet resultSet1 = statement1.executeQuery();

            PreparedStatement statement2 = conn.prepareStatement(sql2);
            statement2.setString(1, tenDM);
            ResultSet resultSet2 = statement2.executeQuery();

            if (resultSet1.next()) {
                maNCC = resultSet1.getString("maNCC");
            }
            if (resultSet2.next()) {
                maLoai = resultSet2.getString("maLoai");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Trả về các giá trị maNCC và maLoai dưới dạng mảng hoặc đối tượng chứa các giá trị này
        return maNCC + "," + maLoai;

    }

    public boolean kiemtratrungmaSP(String newMaSP) throws SQLException {
        String sql = "SELECT * FROM vppproduct WHERE maSP = ?";
        try {
            PreparedStatement statement1 = conn.prepareStatement(sql);
            statement1.setString(1, newMaSP);
            ResultSet resultSet1 = statement1.executeQuery();
            return resultSet1.next();  // Nếu resultSet có dữ liệu, trả về true. Không có trả về false
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean kiemtratrungTenSP(String newMaSP) throws SQLException {
        String sql = "SELECT * FROM vppproduct WHERE tenSP = ?";
        try {
            PreparedStatement statement1 = conn.prepareStatement(sql);
            statement1.setString(1, newMaSP);
            ResultSet resultSet1 = statement1.executeQuery();
            return resultSet1.next();  // Nếu resultSet có dữ liệu, trả về true. Không có trả về false
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public void delete_product(String maSP){
        String sql= "DELETE from vppproduct where maSP='"+ maSP+ "'";
        try {
            PreparedStatement ps= conn.prepareStatement(sql);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Xóa thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Xoa khong thanh cong!");
            e.printStackTrace();
        }

    }

    public void add_product(product_model pro_model, product_form view) {
        // Gọi phương thức layma() để lấy maNCC, maLoai từ tenNCC, tenLoai
        String[] mas = layma(view).split(",");
        String maNCC = mas[0];
        String maLoai = mas[1];
        System.out.println(maNCC + "," + maLoai);

        String sql = "INSERT INTO vppproduct(maSP, tenSP, maNCC, maLoai, donGia, soLuong, moTa, trangThai) VALUES(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, pro_model.getMaSP());
            ps.setString(2, pro_model.getTenSP());
            ps.setString(3, maNCC);
            ps.setString(4, maLoai);
            ps.setFloat(5, pro_model.getDonGia());
            ps.setInt(6, pro_model.getSoLuong());
            ps.setString(7, pro_model.getMoTa());
            ps.setString(8, pro_model.getTrangThai());

            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Thêm sản phẩm thành công ", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            //return;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Thêm sản phẩm không thành công (DAO)", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            //return;
            e.printStackTrace();
        }

    }
    public void edit_product(product_model pro_model, product_form view){
        // Gọi phương thức layma() để lấy mã từ tên
        String[] mas = layma(view).split(",");
        String maNCC = mas[0];
        String maLoai = mas[1];
        System.out.println(maNCC+","+ maLoai);
        String sql = "UPDATE vppproduct SET tenSP = ?, maNCC = ?, maLoai = ?, donGia = ?,soLuong = ?,moTa=?, trangThai=? WHERE maSP = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,pro_model.getTenSP());
            ps.setString(2, maNCC);
            ps.setString(3, maLoai);
            ps.setFloat(4, pro_model.getDonGia());
            ps.setInt(5, pro_model.getSoLuong());
            ps.setString(6, pro_model.getMoTa());
            ps.setString(7,pro_model.getTrangThai());
            ps.setString(8, pro_model.getMaSP());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cập nhật thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

        } 
        catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Cập nhật không thành công.");

        }      
    }
    public void sortByID(JTable tbl, DefaultTableModel model) {
        try {
            //Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tranngoc", "root", "");
            Statement statement = conn.createStatement();
            String sql = "SELECT * FROM "
                    + "(vppproduct as sp inner join vppsupplier as ncc on sp.maNCC=ncc.maNCC )"
                    + " inner join vppCategory as dm on sp.maLoai=dm.maLoai order by tenSP ASC";
            // DESC: Giam dan
            // ASC: Tang dan
          
            ResultSet resultSet = statement.executeQuery(sql);
            addTableModel(model, resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void nhapexcel(product_form v){
           JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    System.out.println("file "+selectedFile);
                    displayExcelData(selectedFile,v);
                }
   }
    public  void displayExcelData(File file,product_form v) {
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
    public void search_thuoc(DefaultTableModel tableModel, String searchOption, String keyword) throws ParseException{
        String query = " SELECT * "
                + "FROM (vppproduct as sp inner join vppsupplier as ncc on sp.maNCC=ncc.maNCC )"
                + "inner join vppcategory as dm on sp.maLoai=dm.maLoai "
                + "where ";
        try{
            if(searchOption.equals("Mã SP")) {
                query += "maSP= ?";
            }else if(searchOption.equals("Tên SP")) {
                query += "tenSP LIKE ?";    //LIKE 'qtm%' : giá trị bắt đầu = qtm 
                keyword = "%" + keyword + "%";    //WHERE a >= 10 AND b <= 10;
            }else if (searchOption.equals("Tên NCC")) {
                query += "tenNCC LIKE ?";
                keyword = "%" + keyword + "%";
            }
            else if (searchOption.equals("Tên loại")) {
                query += "tenLoai LIKE ?";
                keyword = "%" + keyword + "%";
            }
            PreparedStatement statement= conn.prepareStatement(query);
            statement.setString(1, keyword);
            ResultSet resultSet = statement.executeQuery();

            addTableModel(tableModel, resultSet);  // gọi hàm thêm vào bảng
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Không tìm thấy kết quả");
            e.printStackTrace();
        }
    }
    public void addTableModel(DefaultTableModel model, ResultSet resultSet) throws SQLException {
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
            while (resultSet.next()) {
               Object[] row = new Object[columnCount];
                for (int i = 0; i < columnCount; i++) {                
                    row[i] = resultSet.getObject(i + 1);                   
                 }
                
                // Lấy giá trị Tên NCC từ ResultSet
                String tenNCC = resultSet.getString("tenNCC");
                row[2] = tenNCC;
                String tenLoai = resultSet.getString("tenLoai");
                row[3] = tenLoai;
            // Thêm hàng vào bảng
            model.addRow(row);
             }
            
    }

    public static void main(String[] args) {

    }

}
