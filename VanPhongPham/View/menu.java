/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package VanPhongPham.View;

/**
 *
 * @author hoan3
 */
// Java program to construct 
// Menu bar to add menu items
//import BTL.Account_Frm;
//import donhang.donhang_frm;
//import BTL.product_form;
//import doanh_thu.doanhthu_frm;
//import hoa_don.hoadon_frm;
//import BTL.Customer;
//import BTL.Customer_Frm;
//import BTL.NhanSuFrm;
import VanPhongPham.View.Account_Frm;
import VanPhongPham.View.donhang_frm;
import VanPhongPham.View.doanhthu_frm;
import VanPhongPham.View.hoadon_frm;
import VanPhongPham.View.Customer_Frm;
import VanPhongPham.View.NhanSuFrm;
import VanPhongPham.View.category_form;
import VanPhongPham.View.product_form;
import VanPhongPham.View.Question_Form;

import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import java.awt.event.*;
import org.json.JSONException;

public class menu extends JFrame implements ActionListener {

    // menubar
    JMenuBar mb;

    // JMenu
    JMenu x, x2, x3, x4, x5;

    // Menu items
    JMenuItem m1, m2, m3, m4, k1, k2, k3, k4, t1, t2, c1, c2, c3;

    // create a frame
    JFrame f;

    JLabel lb_center;

    public menu() {
        prepare();

    }

    public void prepare() {
        // create a frame
        //f = new JFrame("Quản lý văn phòng phẩm");
        this.setTitle("Quản lý cửa hàng văn phòng phẩm");
        // create a menubar
        mb = new JMenuBar();

        // create a menu
        x = new JMenu("Tài khoản");

        // create menuitems
        m1 = new JMenuItem("Quản lý tài khoản");
        m1.addActionListener(this);
//        m2 = new JMenuItem("Đăng nhập");
//        m2.addActionListener(this);
        m3 = new JMenuItem("Đăng xuất");
        m3.addActionListener(this);
        m4 = new JMenuItem("Quản lý khách hàng");
        m4.addActionListener(this);

        // add menu items to menu
        x.add(m1);
        //x.add(m2);
        x.add(m3);
        x.add(m4);

        // add menu to menu bar
        mb.add(x);

        x2 = new JMenu("Sản Phẩm");
        k1 = new JMenuItem("Quản lý sản phẩm");
        k1.addActionListener(this);
        k2 = new JMenuItem("Danh mục sản phẩm");
        k2.addActionListener(this);
        k3 = new JMenuItem("Quản lý nhà cung cấp");
        k3.addActionListener(this);
        k4 = new JMenuItem("Quản lý câu hỏi");
        k4.addActionListener(this);
        x2.add(k1);
        x2.add(k2);
        x2.add(k4);

        mb.add(x2);

        x3 = new JMenu("Nhân sự");
        t1 = new JMenuItem("Thông tin nhân viên");
        t1.addActionListener(this);
        t2 = new JMenuItem("Thông tin nhà cung cấp");
        t2.addActionListener(this);

        x3.add(t1);
        x3.add(t2);

        mb.add(x3);

        x4 = new JMenu("Thống kê");
        c1 = new JMenuItem("Quản lý hóa đơn");
        c1.addActionListener(this);
        c2 = new JMenuItem("Quản lý đơn hàng");
        c2.addActionListener(this);
        c3 = new JMenuItem("Thống kê");
        c3.addActionListener(this);

        x4.add(c1);
        x4.add(c2);
        x4.add(c3);

        mb.add(x4);

        lb_center = new JLabel();
        // Load the image
        ImageIcon icon = new ImageIcon("C:\\Users\\LAPTOP\\Pictures\\GOAT- LEO\\anime.jpg"); // Thay "image.jpg" bằng đường dẫn đến file ảnh của bạn

        // Tạo một JLabel để hiển thị ảnh
        lb_center.setIcon(icon);

        // Thêm JLabel vào JFrame
        getContentPane().add(lb_center, BorderLayout.CENTER);

        // add menubar to frame
        setJMenuBar(mb);// Đặt cửa sổ ở giữa màn hình
        //setLocationRelativeTo(null);
        //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        // set the size of the frame
        //setSize(1120, 800);     
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
        //setLocationRelativeTo(null);
    }

    public static void main(String[] args) {

        menu f = new menu();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        if (e.getActionCommand().equals("Exit")) {
            System.exit(0);
        }

        if (e.getActionCommand().equals("About")) {
            //new About().setVisible(true);
        }
        if (e.getActionCommand().equals("Quản lý tài khoản")) {
            setVisible(false);
            new Account_Frm().setVisible(true);
        }
        if (e.getActionCommand().equals("Quản lý sản phẩm")) {
            setVisible(false);
            new product_form().setVisible(true);
        }
        if (e.getActionCommand().equals("Danh mục sản phẩm")) {
            setVisible(false);
            new category_form().setVisible(true);
        }
        if (e.getActionCommand().equals("Quản lý câu hỏi")) {
            setVisible(false);
            new Question_Form().setVisible(true);
        }
        if (e.getActionCommand().equals("Quản lý đơn hàng")) {
            setVisible(false);
            try {
                new donhang_frm().setVisible(true);
            } catch (JSONException ex) {
                Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (e.getActionCommand().equals("Quản lý khách hàng")) {
            setVisible(false);
            new Customer_Frm().setVisible(true);
        }
        if (e.getActionCommand().equals("Thông tin nhân viên")) {
            setVisible(false);
            new NhanSuFrm();
        }
        if (e.getActionCommand().equals("Thông tin nhà cung cấp")) {
            setVisible(false);
            new NhaCungCapFrm();
        }
        if (e.getActionCommand().equals("Đăng xuất")) {
            setVisible(false);
            new LogIn_Frm().setVisible(true);
        }
        if (e.getActionCommand().equals("Thống kê")) {
            setVisible(false);
            new doanhthu_frm().setVisible(true);
        }
        if (e.getActionCommand().equals("Quản lý hóa đơn")) {
            setVisible(false);
            new hoadon_frm().setVisible(true);
        }

    }
}
