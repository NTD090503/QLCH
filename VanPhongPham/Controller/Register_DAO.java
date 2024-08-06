/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package VanPhongPham.Controller;


//import baitaplon_buinhuquynh.statement;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import VanPhongPham.View.Register;
import VanPhongPham.Model.Account;
/**
 *
 * @author quynh
 */
public class Register_DAO {
         private Connection con;
     
      public Register_DAO(){
          String dbUrl = "jdbc:mysql://localhost:3306/vanphongpham";
          String dbClass = "com.mysql.jdbc.Driver";
          try{
              Class.forName(dbClass);
                con =   DriverManager.getConnection(dbUrl, "root", "");
              //  con = DriverManager.getConnection(dbUrl,"root","");
                System.out.print("Hello");
          }catch(Exception e){
              e.printStackTrace();
          }
      }
            public void loadDataToComboBox(Register view) {
          try {
            // Tạo statement và thực hiện truy vấn để lấy dữ liệu từ CSDL
                Statement statement = con.createStatement(); 
                ResultSet resultSet = statement.executeQuery("SELECT maNV FROM vppemployee ");
                // Xóa toàn bộ mục trong JComboBox trước khi thêm mới
                view.jcbMaNV.removeAllItems();
              
                
                // Thêm dữ liệu từ kết quả truy vấn vào JComboBox
                while (resultSet.next()) {
                    String item = resultSet.getString("maNV");
                    view.jcbMaNV.addItem(item);
                    
                }
                // Đóng các tài nguyên
                resultSet.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
            public void add_account(Account ac){
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
            public static void main (String[] args){
                Register_DAO red = new Register_DAO();
            }
      }

