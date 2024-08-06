/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package VanPhongPham.Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import VanPhongPham.View.Account_Frm;
import VanPhongPham.Model.Account;

/**
 *
 * @author quynh
 */
public class LogIn_DAO {
       private Connection con;

    public LogIn_DAO(){
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

  

    public boolean login(Account ac) {
        String sql = "SELECT * FROM vppaccount WHERE iD = ? AND passWord = ?";
        try {
            
            PreparedStatement ps = con.prepareStatement(sql);
            // Đặt tham số cho câu lệnh SQL
            ps.setString(1, ac.getTxtID());
            //System.out.println("tt23");
            ps.setString(2, ac.getTxtPassWord());
            // Thực tlihi truy vấn và lấy kết quả
            ResultSet rs = ps.executeQuery();
            // Kiểm tra xem có kết quả hay không
            if (rs.next()) {
                // Đăng nhập thành công
                JOptionPane.showMessageDialog(null, "Đăng nhập thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                return true;
            } else {
                // Sai tên đăng nhập hoặc mật khẩu
                JOptionPane.showMessageDialog(null, "Sai tên đăng nhập hoặc mật khẩu", "Thông báo", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, "Đăng nhập không thành công(Login)", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            //return;

            e.printStackTrace();

        }
        return false;
    }
    public static void main(String[] args){
        LogIn_DAO lid = new LogIn_DAO();
    }

   

    
}
