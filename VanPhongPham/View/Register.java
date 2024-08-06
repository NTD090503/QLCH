/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package VanPhongPham.View;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.WindowConstants;
import VanPhongPham.Controller.Register_DAO;
import VanPhongPham.Model.Account;



/**
 *
 * @author quynh
 */
public class Register extends JFrame implements ActionListener{
    public JLabel IDLabel, head;
    public JLabel PasswordLabel;
    public JLabel maNVLabel;
    public JLabel tenNVLabel;
    public JLabel ngaysinhLabel;
    public JLabel gioitinhLabel;
    public JLabel sdtLabel;
    public JLabel diachiLabel;
    public JLabel emailLabel;
    
    public JTextField IDField, PasswordField;
    public JComboBox jcbMaNV;
    public JTextField tenNVField;
    public JTextField ngaysinhField;
    public JTextArea diachiTA;
    public JTextField sdtField;
    public JTextField gioitinhFiel;
    public JTextField emailField;
    //public JTextField txtSearch;
    public JButton btnSave;
    public JButton btnClear;
    public JButton btnBack;
     public Register() {
        initComponents();
        hien_cb();
        
    }
    
    private void initComponents() {
        //throw new UnsupportedOperationException("Not yet implemented");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        IDLabel = new JLabel("ID");
        PasswordLabel = new JLabel("PassWord");
        maNVLabel = new JLabel("Mã NV");
        tenNVLabel = new JLabel("Tên NV");
        ngaysinhLabel = new JLabel("Tuoi");
        gioitinhLabel = new JLabel("Giới tính");
        diachiLabel = new JLabel("Địa Chỉ");
        sdtLabel = new JLabel("SĐT");
        emailLabel = new JLabel("Email");
        head = new JLabel("THÔNG TIN NHÂN VIÊN");
        
        IDField = new JTextField(20);
        PasswordField = new JTextField(20);
        jcbMaNV = new JComboBox();
        jcbMaNV.setPreferredSize(new Dimension(190, 20));
        tenNVField = new JTextField(20);
        ngaysinhField = new JTextField(20);
        diachiTA = new JTextArea();
        diachiTA.setColumns(20);
        diachiTA.setRows(5);
        JScrollPane jScrollPaneAddress = new JScrollPane(diachiTA);
        gioitinhFiel = new JTextField(20);
        sdtField = new JTextField(20);
        emailField = new JTextField(20);
        
         btnSave = new JButton("Lưu");
         btnClear = new JButton("Xóa Trắng");
         btnBack = new JButton("Trở Về");
            

        SpringLayout layout = new SpringLayout();
        JPanel panel = new JPanel();
        panel.setSize(800,400);
        panel.setLayout(layout);
        
        panel.add(btnBack);
        panel.add(btnClear);
        panel.add(btnSave);
        
        panel.add(head);
        panel.add(IDLabel);
        panel.add(PasswordLabel);
        panel.add(maNVLabel);
        panel.add(tenNVLabel);
        panel.add(ngaysinhLabel);
        panel.add(diachiLabel);
        panel.add(sdtLabel);
        panel.add(gioitinhLabel);
        panel.add(emailLabel);

        panel.add(IDField);
        panel.add(PasswordField);
        panel.add(jcbMaNV);
        panel.add(tenNVField);
        panel.add(ngaysinhField);
        panel.add(gioitinhFiel);
        panel.add(sdtField);
        panel.add(emailField);
        panel.add(jScrollPaneAddress);

         layout.putConstraint(SpringLayout.WEST, head, 300, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, head, 50, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, IDLabel, 200, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, IDLabel, 100, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, PasswordLabel, 200, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, PasswordLabel, 130, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, maNVLabel, 200, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, maNVLabel, 160, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, tenNVLabel, 200, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, tenNVLabel, 190, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, ngaysinhLabel, 200, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, ngaysinhLabel, 220, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, diachiLabel, 200, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, diachiLabel, 250, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, gioitinhLabel, 200, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, gioitinhLabel, 350, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, sdtLabel, 200, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, sdtLabel, 380, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, emailLabel, 200, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, emailLabel, 410, SpringLayout.NORTH, panel);
        
        layout.putConstraint(SpringLayout.WEST, IDField, 350, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, IDField, 100, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, PasswordField, 350, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, PasswordField, 130, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, jcbMaNV, 350, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, jcbMaNV, 160, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, tenNVField, 350, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, tenNVField, 190, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, ngaysinhField, 350, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, ngaysinhField, 220, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, jScrollPaneAddress, 350, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, jScrollPaneAddress, 250, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, gioitinhFiel, 350, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, gioitinhFiel, 350, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, sdtField, 350, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, sdtField, 380, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, emailField, 350, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, emailField, 410, SpringLayout.NORTH, panel);
        
  
        
        layout.putConstraint(SpringLayout.WEST, btnBack, 200, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, btnBack, 450, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, btnClear, 150, SpringLayout.WEST, btnBack);
        layout.putConstraint(SpringLayout.NORTH, btnClear, 450, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, btnSave, 300, SpringLayout.WEST, btnBack);
        layout.putConstraint(SpringLayout.NORTH, btnSave, 450, SpringLayout.NORTH, panel);
        
        add(panel);
        setTitle("Register Form");
        setSize(800,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);

        
        btnSave.addActionListener(this);
        btnClear.addActionListener(this);
        btnBack.addActionListener(this);
        
    }
    
    public void actionPerformed(ActionEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
        JButton btn = (JButton) e.getSource();
        if (btn.equals(btnSave)) {
          save_actionperformed();
        } 
         if(btn.equals(btnClear)){
             clear_actionperformed();
         }
        if(btn.equals(btnBack)){
            back_actionperformed();
        }
    }
    public void hien_cb(){
        Register_DAO red = new Register_DAO();
        red.loadDataToComboBox(this);
    }
    public static void main(String [] args){
        Register rg = new Register();
    }

   public void save_actionperformed(){
       Account ac = new Account();
        ac.setTxtID(IDField.getText());
        ac.setTxtPassWord(PasswordField.getText());
        ac.setTxtmaNV(jcbMaNV.getSelectedItem().toString());
        ac.setTxttenNV(tenNVField.getText());
        ac.setTxtGioiTinh(gioitinhFiel.getText());
        ac.setTxtTuoi(Integer.parseInt(ngaysinhField.getText()));
        ac.setTxtGioiTinh(gioitinhFiel.getText());
        ac.setTxtSDT(sdtField.getText());
        ac.setTxtDiaChi(diachiTA.getText());
        ac.setTxtEmail(emailField.getText());

        Register_DAO red = new Register_DAO();
        red.add_account(ac);
   }
   
   public void clear_actionperformed(){
       IDField.setText(" ");
       PasswordField.setText(" ");
       jcbMaNV.setSelectedItem(null);
       tenNVField.setText(" ");
       ngaysinhField.setText(" ");
       gioitinhFiel.setText(" ");
       sdtField.setText(" ");
       diachiTA.setText(" ");
       emailField.setText(" ");
   }
   public void back_actionperformed(){
       setVisible(false);
       LogIn_Frm lif = new LogIn_Frm();
       lif.setVisible(true);
   }
}
