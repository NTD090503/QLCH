/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package VanPhongPham.Controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.print.DocFlavor.STRING;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import VanPhongPham.Model.HoaDon;
import VanPhongPham.View.hoadon_frm;
import java.io.File;
import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
/**
 *
 * @author ntd
 */
public class hoadon_dao {
 //public  View v = new View();
 public  HoaDon hd;
    private static String DB_URL = "jdbc:mysql://localhost:3306/vanphongpham";
    private static String USER_NAME = "root";
    private static String PASSWORD = "";
    private Connection conn;
    private String sql;
    private List<HoaDon> arrhd = new ArrayList<HoaDon>();
    public hoadon_dao(){
      
     try {
          Class.forName("com.mysql.jdbc.Driver");
          conn = (Connection) DriverManager.getConnection(DB_URL,USER_NAME,PASSWORD);
      } catch (Exception e) {
          e.printStackTrace();
      }
 }
//    public List<Integer> laymahdrong(){
//        List<Integer> arrmahdnull = new ArrayList<Integer>();
//         String sqlnull =   "SELECT ma_hoa_don FROM vpphoadon where tong_tien = null or tong_tien= 0";
//     try
//     {
//        PreparedStatement ps = conn.prepareStatement(sqlnull);  
//        ResultSet rs = ps.executeQuery();
//        
//        while(rs.next())
//        {
//            int mahd = rs.getInt("ma_hoa_don");
//            arrmahdnull.add(mahd);
//        }
//      }
//     catch(Exception e){  e.printStackTrace();}
//        return arrmahdnull;
//    }
//   public void capnhathoadonrong(){
//        List<Integer> arrmahdnull = new ArrayList<Integer>();
//        arrmahdnull = laymahdrong();
//         Set<Integer> uniqueElements = new HashSet(arrmahdnull);
//
//        // Tạo lại arrmahdnull từ các phần tử duy nhất trong Set
//        arrmahdnull.clear();
//        arrmahdnull.addAll(uniqueElements);
//        float thanhtien = 0, tongtien = 0;
//        if(arrmahdnull.size()>0)
//        for(int i : arrmahdnull)
//        {
//            thanhtien = 0; tongtien = 0;
//            System.out.println(" abc "+i);
//            String sqlnull =   "SELECT vppdonhang.soLuong, vppproduct.donGia FROM vppdonhang JOIN vppproduct ON vppdonhang.maSP = vppproduct.maSP where ma_hoa_don = "+i;
//        try
//     {
//        PreparedStatement ps = conn.prepareStatement(sqlnull);  
//        ResultSet rs = ps.executeQuery();
//        
//        while(rs.next())
//        {
//            int sl = rs.getInt("soLuong");
//            float dongia = rs.getFloat("donGia"); 
//            
//            thanhtien = sl*dongia;
//            tongtien = tongtien+thanhtien;
//            System.out.println("tt "+thanhtien);
//           
//        }
//        String sqlupdate = " UPDATE vpphoadon set tong_tien = "+tongtien+" where ma_hoa_don ="+i; 
//        try{
//                ps = conn.prepareStatement(sqlupdate);
//                  int row = ps.executeUpdate();
//}
//    catch(Exception e){  e.printStackTrace();}
//        System.out.println("tft "+tongtien);
//      }
//     catch(Exception e){  e.printStackTrace();}
//         
//        }
//        else return;
//       
//    }
    
    public void laynguonchotable(hoadon_frm v){
        sql = "SELECT* FROM vpphoadon";
        laydl(sql, "", false); laynguon(v);
    }
    
        public void laydl(String sql_org,String dk,boolean ktdk){
     try
     {
        PreparedStatement ps = conn.prepareStatement(sql_org);  
        if(ktdk == true ) ps.setString(1,dk);
        ResultSet rs = ps.executeQuery();
        
        while(rs.next())
        {
            String mahd = rs.getString("ma_hoa_don");
            String makh = rs.getString("maKH");
            String madh = rs.getString("ma_don_hang");
            Date thoigian = rs.getDate("ngay_tao");
            Double tongtien = rs.getDouble("tong_tien");
            boolean tt = rs.getBoolean("trang_thai_thanh_toan"); 
            String dcgh = rs.getString("dia_chi_giao_hang");
            arrhd.add(new HoaDon(mahd, makh, madh, dcgh, thoigian, tongtien, tt));
        }
      }
     catch(Exception e){  e.printStackTrace();}
    }
     public void laynguon( hoadon_frm v){
      v.model.setRowCount(0);String tt ;
      for(HoaDon hd : arrhd){
          if(hd.isTrangThai()==true)
              tt = "đã thanh toán";
          else tt = "chưa thanh toán";
          v.model.addRow(new Object[]{hd.getMaHoaDon(),hd.getMaKhachHang(),hd.getMaDonHang(),hd.getNgayTao(),hd.getTongTien(),tt,hd.getDiaChi()});
      }
  }
     public void sua_action(hoadon_frm v){
         if(v.txtDiaChi.getText().isEmpty()||v.txtNgayTao.getDate()==null||v.txtTongTien.getText().isEmpty()){
             JOptionPane.showMessageDialog(null, "bạn đã nhập thiếu thông tin");
             return;
         }
            int mdh_cu = Integer.parseInt(v.txtMaHoaDon.getText());
            String makh = v.cbmakh.getSelectedItem().toString();
            String madh = v.txtmadh.getText();
            Date thoigian = v.txtNgayTao.getDate();
            java.util.Date utilDate = thoigian;
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            Double tongtien =Double.parseDouble(v.txtTongTien.getText());
            Boolean tt = null;
            if(v.tt_true.isSelected()) 
             tt = true;
            else tt = false;
            String dcgh = v.txtDiaChi.getText();
            if(makh.isEmpty() || tongtien < 0 ||(v.tt_true.isSelected()&&v.tt_false.isSelected())||
                (v.tt_true.isSelected() == false && v.tt_false.isSelected()==false)
                ||dcgh.isEmpty() || tt ==null || thoigian == null)
            {
                JOptionPane.showMessageDialog(null, "bạn chưa nhập đủ dữ liệu");
                return;
            }
            sql = " Update vpphoadon SET maKH = ?,ma_don_hang = ?, ngay_tao = ?, tong_tien = ?, trang_thai_thanh_toan = ?, dia_chi_giao_hang = ? WHERE ma_hoa_don = ?";
               try{
                PreparedStatement ps = conn.prepareStatement(sql);  
                ps.setString(1, makh ); 
                ps.setString(2, madh);
                ps.setDate(3,sqlDate);
                ps.setDouble(4, tongtien);
                ps.setBoolean(5, tt ); 
                ps.setString(6, dcgh);
                ps.setInt(7, mdh_cu);
                int row = ps.executeUpdate();
                if(row>0)
                JOptionPane.showMessageDialog(null,"bạn đã sửa thành công");
                else JOptionPane.showMessageDialog(null,"bạn đã sửa thất bại");
}
    catch(SQLException ex){  ex.printStackTrace();
}
    xoa_trang_action(v);
    laynguonchotable(v);
    v.model.fireTableDataChanged();
     }
    public void xoa_trang_action(hoadon_frm v){
        v.txtMaHoaDon.setText("");v.cbmakh.setSelectedItem(null);v.txtmadh.setText("");
        v.txtNgayTao.setDate(null); v.txtDiaChi.setText("");
        v.tt_false.setSelected(false); v.tt_true.setSelected(false);
        v.txtTongTien.setText("");
    }
         public void xoa_action(hoadon_frm v){ 
            if(v.txtMaHoaDon.getText().isEmpty())
            {
                JOptionPane.showInternalMessageDialog(null, "bạn đã xóa thất bại ");
                return;
            }
            int mdh_cu = Integer.parseInt(v.txtMaHoaDon.getText());
            sql = "DELETE FROM vpphoadon WHERE ma_hoa_don = ?";
               try{
                PreparedStatement ps = conn.prepareStatement(sql);  
                ps.setInt(1, mdh_cu);
                int row = ps.executeUpdate();
                if(row>0)
                JOptionPane.showMessageDialog(null,"bạn đã xóa thành công");
                else JOptionPane.showMessageDialog(null,"bạn đã xóa thất bại");
}
    catch(SQLException ex){  ex.printStackTrace();
}
             xoa_trang_action(v);
             laynguonchotable(v);
             v.model.fireTableDataChanged();
         }
   public void sapxep_action(String col , String thutu,hoadon_frm v){
       if( col.isEmpty()||thutu.isEmpty() )
       {
           JOptionPane.showMessageDialog(null, "bạn đã nhập thiếu thông tin");
           return;
       }
       else
       {
             String sqlsx = "SELECT * FROM vpphoadon ORDER BY "+col+" "+thutu;
             laydl(sqlsx, "", false); laynguon(v);
             v.model.fireTableDataChanged();
       }
         }
   public void timkiem_action(String coltk , String dktk,hoadon_frm v){
       System.out.println(" t "+ coltk + " h "+dktk);
       if( coltk.isEmpty()||dktk.isEmpty() )
           {
           JOptionPane.showMessageDialog(null, "bạn đã nhập thiếu thông tin");
           return;
       }
       else
       {
           String sqltk; 
             sqltk = "SELECT * FROM vpphoadon WHERE "+coltk+" LIKE '%"+dktk+"%'";
             laydl(sqltk,"", false); laynguon(v);
             v.model.fireTableDataChanged();
       }
         }
   public List<String> laynguoncb(){
       sql = "SELECT maKH FROM vppcustomer";
       List<String> arrmkh = new ArrayList<String>();
       try
     {
        PreparedStatement ps = conn.prepareStatement(sql);  
        ResultSet rs = ps.executeQuery();
        
        while(rs.next())
        {
           String mkh = rs.getString("maKH");
            arrmkh.add(mkh);
        }
      }
     catch(Exception e){  e.printStackTrace();}
       return arrmkh;
   }
   public void xuatexcel_action(hoadon_frm v){
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Lop Data");

        // Tạo hàng header
        XSSFRow headerRow = sheet.createRow(0);
        for (int i = 0; i < v.model.getColumnCount(); i++) {
            XSSFCell cell = headerRow.createCell(i);
            cell.setCellValue(v.model.getColumnName(i));
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
                for (int i = 0; i < v.model.getRowCount(); i++) {
                    XSSFRow row = sheet.createRow(i + 1);
                    for (int j = 0; j < v.model.getColumnCount(); j++) {
                        XSSFCell cell = row.createCell(j);
                        Object value = v.model.getValueAt(i, j);
                        if (value != null) {
                            cell.setCellValue(value.toString());
                        }
                    }
                }

                // Lưu file Excel
                workbook.write(fileOut);
                JOptionPane.showMessageDialog(null, "bạn đã xuât thành công");
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
   public void nhapexcel(hoadon_frm v){
           JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    System.out.println("file "+selectedFile);
                    displayExcelData(selectedFile,v);
                }
   }
    public  void displayExcelData(File file,hoadon_frm v) {
         // Clear old data in the table before adding new data
        v.model.setRowCount(0);
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
                v.model.addRow(rowData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        v.model.fireTableDataChanged();
    }
        public static void main(String[] args) {

    }
}