/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package VanPhongPham.View;

/**
 *
 * @author quynh
 */
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.sql.*;
import java.sql.ResultSet;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
/**
 *
 * @author hoan3
 */
public class ForgotPassword extends JFrame{
private JPanel contentPane;
	private JPasswordField textNewPassword;
	private JPasswordField textConfirm;
	private JTextField textUsername;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ForgotPassword frame = new ForgotPassword();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection con;
	Statement pst;
	private JTextField textEmail;
	private JTextField textAnswer;

	
	/**
	 * Create the frame.
	 */
	public ForgotPassword() {
		setTitle("Quên Mật Khẩu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 617, 553);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New Password System");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(185, 26, 223, 49);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_4 = new JLabel("Mật Khẩu Mới:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_4.setBounds(71, 289, 116, 20);
		contentPane.add(lblNewLabel_4);
		
		textNewPassword = new JPasswordField();
		textNewPassword.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textNewPassword.setBounds(213, 290, 195, 20);
		contentPane.add(textNewPassword);
		
		JLabel lblNewLabel_5 = new JLabel("Xác Nhận Lại:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_5.setBounds(71, 354, 131, 19);
		contentPane.add(lblNewLabel_5);
		
		textConfirm = new JPasswordField();
		textConfirm.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textConfirm.setBounds(213, 353, 195, 20);
		contentPane.add(textConfirm);
		
		JButton btnNewButton = new JButton("Lưu");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = textUsername.getText();
				String newpassword = new String(textNewPassword.getPassword());
				String confirm = new String(textConfirm.getPassword());
				String email = textEmail.getText();
				String answer = textAnswer.getText();
				
				StringBuilder cd = new StringBuilder();
				
				if(newpassword.equals("")) {
					cd.append("Bạn chưa nhập mật khẩu mới!\n");
				}
				
				if(confirm.equals("")) {
					cd.append("Bạn chưa xác nhận mật khẩu mới của mình!\n");
				}
				if(email.equals("")) {
					cd.append("Vui lòng nhập email của bạn!\n");
				}
				
				if(answer.equals("")) {
					cd.append("Bạn chưa trả lời!\n");
				}
				
				JFrame frame = new JFrame("JOptionPane showMessageDialog example");
				if(cd.length()>0) {
					JOptionPane.showMessageDialog(frame, cd.toString(), "Invalidation", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				try {
					Class.forName("com.mysql.jdbc.Driver");
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vanphongpham","root","");
					Statement pst = con.createStatement();
					ResultSet rs = pst.executeQuery("select * from vppaccount where iD ='"+ username + "'and email = '"+ answer+"'");
						
					if(rs.next()) {
						
						if (newpassword.equals(confirm)) {
							pst.executeUpdate("update vppaccount set passWord = '"+ newpassword+ "' where iD = '"+ username + "'and email ='"+ answer+"'");
							JOptionPane.showMessageDialog(null, "Mật khẩu của bạn đã được cập nhật thành công");
							setVisible(false);
							LogIn_Frm lif = new LogIn_Frm();
                                                        lif.setVisible(true);
						} else {
							JOptionPane.showMessageDialog(frame, "Mật khẩu đã nhập không khớp. Hãy thử lại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
						}
					}
					else {
						JOptionPane.showMessageDialog(frame,"Vui lòng viết đúng Tên người dùng hoặc Câu trả lời", "Thất bại", JOptionPane.ERROR_MESSAGE);
					}
				
				
				}catch (SQLException e2) {
					e2.printStackTrace();
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Error in connection");
					e2.printStackTrace();
				}
			}
			
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(93, 443, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
                                LogIn_Frm lif = new LogIn_Frm();
                                lif.setVisible(true);
				//new LoginForm().setVisible(true);
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1.setBounds(396, 443, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Clear");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textUsername.setText("");
				textAnswer.setText("");
				textNewPassword.setText("");
				textConfirm.setText("");
			}
		});
                
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_2.setBounds(246, 445, 89, 23);
		contentPane.add(btnNewButton_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("ID:");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_1.setBounds(74, 98, 116, 14);
		contentPane.add(lblNewLabel_2_1);
		
		textUsername = new JTextField();
		textUsername.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textUsername.setColumns(10);
		textUsername.setBounds(216, 97, 195, 20);
		contentPane.add(textUsername);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Câu hỏi bảo mật?");
		lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_1_1.setBounds(74, 159, 116, 20);
		contentPane.add(lblNewLabel_2_1_1);
		
		textEmail = new JTextField();
		textEmail.setText("Mail của bạn là gì?");
		textEmail.setFont(new Font("Tahoma", Font.BOLD, 13));
		textEmail.setColumns(10);
		textEmail.setBounds(216, 158, 195, 20);
		contentPane.add(textEmail);
		
		JLabel lblNewLabel_2_1_2 = new JLabel("Câu hỏi:");
		lblNewLabel_2_1_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2_1_2.setBounds(74, 225, 116, 14);
		contentPane.add(lblNewLabel_2_1_2);
		
		textAnswer = new JTextField();
		textAnswer.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textAnswer.setColumns(10);
		textAnswer.setBounds(216, 224, 195, 20);
		contentPane.add(textAnswer);
                setLocationRelativeTo(null);
                setResizable(false);

		
		
	}
}
