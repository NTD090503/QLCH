/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package VanPhongPham.Controller;

//import com.orsoncharts.util.json.parser.ParseException;
//import com.sun.rowset.internal.Row;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.TableXYDataset;
import VanPhongPham.Model.Doanhthu;
import VanPhongPham.View.doanhthu_frm;
/**
 *
 * @author ntd
 */
public class doanhthu_dao {
// View v ;
    private static String DB_URL = "jdbc:mysql://localhost:3306/vanphongpham";
    private static String USER_NAME = "root";
    private static String PASSWORD = "";
    private Connection conn;
    public doanhthu_dao() {
        try {
          Class.forName("com.mysql.jdbc.Driver");
          conn = (Connection) DriverManager.getConnection(DB_URL,USER_NAME,PASSWORD);
          //System.out.println("connect successfully");
      } catch (Exception e) {
          e.printStackTrace();
      }
    }
     public void doanhthu (doanhthu_frm v) {
         String sql = null,d1= null,d2= null;
         SimpleDateFormat dateFormat ,dateFormat2;
         dateFormat = new SimpleDateFormat("yyyy-MM-dd");
         dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
         if(v.comboBox.getSelectedItem().toString().trim().equalsIgnoreCase("ngày"))
         {
             sql = " SELECT DAtE(ngay_tao) as date,SUM(tong_tien)  AS doanhthu , COUNT(ma_hoa_don) AS sohd FROM vpphoadon WHERE trang_thai_thanh_toan = true and  ngay_tao BETWEEN ? AND ? GROUP BY DATE(ngay_tao) ORDER BY DATE DESC";
             
              d1 = dateFormat.format(v.dateChooser1.getDate());
              d2 = dateFormat.format(v.dateChooser2.getDate());
         }
         else if(v.comboBox.getSelectedItem().toString().trim().equalsIgnoreCase("tháng"))
         {
             sql = "SELECT DATE_FORMAT(ngay_tao, '%Y-%m') AS date, SUM(tong_tien) AS doanhthu , COUNT(ma_hoa_don) AS sohd FROM vpphoadon WHERE trang_thai_thanh_toan = true and ngay_tao BETWEEN ? AND ? GROUP BY YEAR(ngay_tao), MONTH(ngay_tao) ORDER BY date DESC";
             dateFormat = new SimpleDateFormat("yyyy-MM");
             dateFormat2 = new SimpleDateFormat("yyyy-MM");
             d1 = dateFormat.format(v.dateChooser1.getDate())+"-01"; 
             d2 = dateFormat.format(v.dateChooser2.getDate());
             int ngay2 = layNgayCuoiCungCuaThang(d2);
             if(ngay2!=-1)
             {
                 d2 = d2 + "-"+ Integer.toString(ngay2);
             } 
         }
         else if(v.comboBox.getSelectedItem().toString().trim().equalsIgnoreCase("năm"))
         {
             sql = "SELECT DATE_FORMAT(ngay_tao, '%Y') AS date, SUM(tong_tien) AS doanhthu, COUNT(ma_hoa_don) AS sohd  FROM vpphoadon WHERE trang_thai_thanh_toan = true and ngay_tao BETWEEN ? AND ? GROUP BY YEAR(ngay_tao) ORDER BY date DESC";
             dateFormat = new SimpleDateFormat("yyyy");
             dateFormat2 = new SimpleDateFormat("yyyy");
             d1 = dateFormat.format(v.dateChooser1.getDate())+"-01-01";
             d2 = dateFormat.format(v.dateChooser2.getDate())+"-12"; 
             int ngay2 = layNgayCuoiCungCuaThang(d2);
             if(ngay2!=-1)
             {
                 d2 = d2 + "-"+ Integer.toString(ngay2);
             } 
         }
         System.out.println("  "+d1);
        System.out.println("  "+d2); 
        laynguon(sql,d1,d2,v);
}
    public List<Doanhthu> laydl(String sql_org, String d1, String d2){
       List<Doanhthu> arr = new ArrayList<Doanhthu>();
     try
     {
        PreparedStatement ps = conn.prepareStatement(sql_org);  
        ps.setString(1, d1);
        ps.setString(2, d2);
        ResultSet rs = ps.executeQuery();
        
        while(rs.next())
        {
            String thoigian =  rs.getString("date");
            double tongtien =  rs.getDouble("doanhthu");
            int sohd =  rs.getInt("sohd");
            System.out.println("b  "+sohd);
            arr.add(new Doanhthu(thoigian, tongtien, sohd));
        }
      }
     catch(Exception e){  e.printStackTrace();}
      return arr;
    }
     public void laynguon(String sql,String d1, String d2, doanhthu_frm v){
      List<Doanhthu> arrdt = new ArrayList<Doanhthu>();
      arrdt = laydl(sql,d1,d2);
      v.tableModel.setRowCount(0);
      for(Doanhthu dt : arrdt){
          v.tableModel.addRow(new Object[]{dt.thoigian,dt.tongtien,dt.sohd});
      }
  }
      public static int layNgayCuoiCungCuaThang(String thoiGian){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Calendar calendar = Calendar.getInstance();
        try {
            Date date = sdf.parse(thoiGian);
            calendar.setTime(date);
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        } catch (Exception e) {
            e.printStackTrace();
            return -1; 
        }
    }
      public JFreeChart createChart(doanhthu_frm v) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
           for (int i = 0; i <+ v.table.getRowCount() ; i++) {
            double tongtien = Double.parseDouble(v.tableModel.getValueAt(i, 1).toString());
            int shd = Integer.parseInt(v.tableModel.getValueAt(i, 2).toString());
            String column = v.tableModel.getValueAt(i, 0).toString();
            dataset.addValue(tongtien, "Tổng tiền", column);
            dataset.addValue(shd, "SL HĐ", column);
            }
       JFreeChart chart = ChartFactory.createBarChart3D("Biểu đồ doanh thu", "Category", "Value", dataset);
        return chart;
    }
      public void xuatexcel_action(doanhthu_frm v){
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Lop Data");

        // Tạo hàng header
        XSSFRow headerRow = sheet.createRow(0);
        for (int i = 0; i < v.tableModel.getColumnCount(); i++) {
            XSSFCell cell = headerRow.createCell(i);
            cell.setCellValue(v.tableModel.getColumnName(i));
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
                for (int i = 0; i < v.tableModel.getRowCount(); i++) {
                    XSSFRow row = sheet.createRow(i + 1);
                    for (int j = 0; j < v.tableModel.getColumnCount(); j++) {
                        XSSFCell cell = row.createCell(j);
                        Object value = v.tableModel.getValueAt(i, j);
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

       public static void main(String[] args) {
       //View r = new View();
//        Controller c = new Controller();
    }
}