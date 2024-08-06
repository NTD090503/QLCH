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
import VanPhongPham.View.Question_Form;
import VanPhongPham.Model.Question_model;

/**
 *
 * @author TranNgoc ^.^
 */
public class Question_DAO {

    private Connection conn;

    public Question_DAO() {
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
            //Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tranngoc", "root", "");
            Statement statement = conn.createStatement();
            String sql = "SELECT * FROM vppquestion";
            ResultSet resultSet = statement.executeQuery(sql);
            addTableModel(model, resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean kiemtratrungmaSP(String newMaSP) throws SQLException {
        String sql = "SELECT * FROM vppquestion WHERE id = ?";
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

    public void addTableModel(DefaultTableModel model, ResultSet resultSet) throws SQLException {
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        while (resultSet.next()) {
            Object[] row = new Object[columnCount];
            for (int i = 0; i < columnCount; i++) {
                row[i] = resultSet.getObject(i + 1);
            }
            // Thêm hàng vào bảng
            model.addRow(row);
        }

    }

    public void delete_category(String maLoai) {
        String sql = "DELETE from vppquestion where id='" + maLoai + "'";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Xóa thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Xoa khong thanh cong!");
            e.printStackTrace();
        }

    }

    public void add_category(Question_model cat_model, Question_Form view) {
        String sql = "INSERT INTO vppquestion(id, cauhoi, traloi) VALUES(?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, cat_model.getId());
            ps.setString(2, cat_model.getCauhoi());
            ps.setString(3, cat_model.getTraloi());

            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Thêm câu hỏi thành công ", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            //return;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Thêm câu hỏi không thành công (DAO)", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            //return;
            e.printStackTrace();
        }

    }

    public void edit_category(Question_model cat_model, Question_Form view) {
        // Gọi phương thức layma() để lấy mã từ tên
        String sql = "UPDATE vppquestion SET cauhoi = ?, traloi = ? WHERE id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, cat_model.getCauhoi());
            ps.setString(2, cat_model.getTraloi());
            ps.setString(3, cat_model.getId());

            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cập nhật thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Cập nhật không thành công.");

        }
    }

    public void sortByID(JTable tbl, DefaultTableModel model) {
        try {
            //Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tranngoc", "root", "");
            Statement statement = conn.createStatement();
            String sql = "SELECT * FROM vppquestion order by id DESC";
            // DESC: Giam dan
            // ASC: Tang dan

            ResultSet resultSet = statement.executeQuery(sql);
            addTableModel(model, resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void search_thuoc(DefaultTableModel tableModel, String searchOption, String keyword) throws ParseException {
        String query = " SELECT * FROM vppquestion "
                + "where ";
        try {
            if (searchOption.equals("Id")) {
                query += "id= ?";
            } else if (searchOption.equals("Câu hỏi")) {
                query += "cauhoi LIKE ?";
                keyword = "%" + keyword + "%";
            }
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, keyword);
            ResultSet resultSet = statement.executeQuery();

            addTableModel(tableModel, resultSet);  // gọi hàm thêm vào bảng
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Không tìm thấy kết quả");
            e.printStackTrace();
        }
    }

}
