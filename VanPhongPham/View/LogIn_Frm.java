
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package VanPhongPham.View;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.WindowConstants;
import VanPhongPham.Model.Account;
import VanPhongPham.Controller.LogIn_DAO;
/**
 *
 * @author quynh
 */
public class LogIn_Frm extends JFrame implements ActionListener{
      private static Runnable LogIn;
    private JButton btnLogIn;
    private JButton btnRegister;
    private JButton btnForget;
    private JLabel lbTaiKhoan;
    private JLabel lbMatKhau;
    private JTextField txtTaiKhoan;
    private JPasswordField txtMatKhau;
    
    public  LogIn_Frm(){
        prapreGUI();
    }
    
    public void prapreGUI(){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        btnLogIn = new JButton("Đăng Nhập");
        btnRegister= new JButton("Đăng Kí");
        btnForget = new JButton("Quên mật khẩu");
        
        lbTaiKhoan = new JLabel("Tên tài khoản: ");
        lbMatKhau = new JLabel("Mật Khẩu: ");
        
        txtTaiKhoan = new JTextField(25);
        txtMatKhau = new JPasswordField(25);
        txtTaiKhoan.setForeground(Color.GRAY);
        txtTaiKhoan.setText("Vui lòng nhập tài khoản");
        txtTaiKhoan.addFocusListener(new FocusListener() {

            public void focusGained(FocusEvent e) {
                if (txtTaiKhoan.getText().equals("Vui lòng nhập tài khoản")) {
                    txtTaiKhoan.setText("");
                    txtTaiKhoan.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent e) {
                if (txtTaiKhoan.getText().isEmpty()) {
                    txtTaiKhoan.setForeground(Color.GRAY);
                    txtTaiKhoan.setText("Vui lòng nhập tài khoản");
                }
            } 
        });
        
        SpringLayout layout = new SpringLayout();
        JPanel panel = new JPanel();
        panel.setSize(800, 420);
        panel.setLayout(layout);
        
        panel.add(lbTaiKhoan);
        panel.add(lbMatKhau);
        panel.add(btnLogIn);
        panel.add(btnRegister);
        panel.add(btnForget);
        panel.add(txtTaiKhoan);
        panel.add(txtMatKhau);
        
        layout.putConstraint(SpringLayout.WEST, lbTaiKhoan, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.WEST, txtTaiKhoan, 10, SpringLayout.EAST, lbTaiKhoan);
        layout.putConstraint(SpringLayout.NORTH, lbTaiKhoan, 50, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.NORTH, txtTaiKhoan, 50, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, lbMatKhau, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.WEST, txtMatKhau, 30, SpringLayout.EAST, lbMatKhau);
        layout.putConstraint(SpringLayout.NORTH, lbMatKhau, 20, SpringLayout.SOUTH, lbTaiKhoan);
        layout.putConstraint(SpringLayout.NORTH, txtMatKhau, 20, SpringLayout.SOUTH, txtTaiKhoan);

        layout.putConstraint(SpringLayout.WEST, btnLogIn, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.WEST, btnRegister, 10, SpringLayout.EAST, btnLogIn);
        layout.putConstraint(SpringLayout.NORTH, btnLogIn, 30, SpringLayout.SOUTH, lbMatKhau);
        layout.putConstraint(SpringLayout.NORTH, btnRegister, 22, SpringLayout.SOUTH, txtMatKhau);

        layout.putConstraint(SpringLayout.WEST, btnForget, 295, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, btnForget, -25, SpringLayout.SOUTH, btnLogIn);
        
        
        add(panel);
        setTitle("LogIn-Register");
        setSize(600,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);

        btnLogIn.addActionListener(this);
        btnForget.addActionListener(this);
        btnRegister.addActionListener(this);
    }

    
    public  static  void main(String[] args){
        LogIn_Frm lif = new LogIn_Frm();
    }

    public void actionPerformed(ActionEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
        JButton btn = (JButton) e.getSource();
        if (btn.equals(btnLogIn)) {
            btnLogIn_actionperformed();
        }
        if(btn.equals(btnForget)){
           btnForget_actionperformed();
        }
        if(btn.equals(btnRegister)){
            btnRegister_actionperformed();
    
}
    }

    private void btnForget_actionperformed() {
        ForgotPassword fw = new ForgotPassword();
            
            setVisible(false);
            fw.setVisible(true);
    }

    private void btnLogIn_actionperformed() {
          if(txtTaiKhoan.getText().isEmpty()||txtMatKhau.getText().isEmpty()){
           JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin", "Thông báo", JOptionPane.WARNING_MESSAGE);
       }else{
        Account ac = new Account();
        ac.setTxtID(txtTaiKhoan.getText());
        ac.setTxtPassWord(txtMatKhau.getText());

        LogIn_DAO ld = new LogIn_DAO();
        //ld.conn();
        //ld.login(ac);
        if (ld.login(ac) == true) {
            menu f = new menu();
            setVisible(false);
            f.setVisible(true);

        }
    }
    }

    private void btnRegister_actionperformed() {
         Register rs = new Register();
            
            setVisible(false);
            rs.setVisible(true);
    }
}