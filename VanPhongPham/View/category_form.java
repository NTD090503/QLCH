/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package VanPhongPham.View;

//import Layout.category_form;
import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import VanPhongPham.Controller.category_DAO;
import VanPhongPham.Model.category_model;

/**
 *
 * @author TranNgoc ^.^
 */
public class category_form implements ActionListener {

    // Thuoc tinh
    JFrame mainFrame;
    JPanel controlPanel;
    JTextField txtMaLoai, txtTenLoai, txtTimKiem;
    private JLabel lb_MaLoai, lb_TenLoai, lb_NgayTao;
    JComboBox cboTimKiem;
    JButton btnAdd, btnEdit, btnDelete, btnClear, btnExit, btnSortName, btnXuatExcel;
    JButton btnTimKiem;
    JDateChooser jd_ngayTao;
    static int row = 0;
    public Object o;
    JTable jtable;
    DefaultTableModel tableModel;
    JScrollPane scrollPane;
    JLabel lbHien;

    public category_form() {
        prepare();
        connect();
        hienthi_tbl();
        show();
    }

    public void prepare() {

        mainFrame = new JFrame("Quản lý loại sản phẩm");
        mainFrame.setSize(1220, 760);
        mainFrame.setTitle("Quản lý loại sản phẩm");

        txtMaLoai = new JTextField();
        txtTenLoai = new JTextField();
        txtTimKiem = new JTextField();
        addPlaceholder(txtTimKiem, "Bạn cần tìm gì ?");

//        lb_MaSP.setFont(new Font("Arial", Font.PLAIN, 24)); // Đặt font mới với kích cỡ 24
        //Font font = lb_MaLoai.getFont();
        //lb_MaLoai.setFont(new Font(font.getName(), Font.PLAIN, 18));
        lb_MaLoai = new JLabel("Mã Loại");
        lb_TenLoai = new JLabel("Tên Loại");
        lbHien = new JLabel();
        cboTimKiem = new JComboBox(new String[]{"Mã loại", "Tên loại"});

        btnAdd = new JButton("Add");
        btnEdit = new JButton("Edit");
        btnDelete = new JButton("Delete");
        btnClear = new JButton("Clear");
        btnTimKiem = new JButton("Tìm Kiếm");
        btnExit = new JButton("Exit");
        btnSortName = new JButton("SortName");
        btnXuatExcel = new JButton("Export");

        String column[] = {"Mã loại", "Tên loại"};
        tableModel = new DefaultTableModel(column, 0);

        jtable = new JTable(tableModel);
        jtable.setBounds(30, 40, 200, 300);
        jtable.setPreferredScrollableViewportSize(new Dimension(200, 100));

        scrollPane = new JScrollPane(jtable);

        controlPanel = new JPanel();
        mainFrame.setLayout(new FlowLayout());
        mainFrame.getContentPane().add(controlPanel);
        mainFrame.setLocationRelativeTo(null);  // form chính giữa màn hình
        mainFrame.setVisible(true);
//        mainFrame.setResizable(false); // cố định form

        btnAdd.addActionListener(this);
        btnEdit.addActionListener(this);
        btnDelete.addActionListener(this);
        btnClear.addActionListener(this);
        btnTimKiem.addActionListener(this);
        btnExit.addActionListener(this);
        btnSortName.addActionListener(this);
        btnXuatExcel.addActionListener(this);

        // Khóa nút
        btnDelete.setEnabled(false);
        btnEdit.setEnabled(false);

// hiển thị dữ liệu từ bảng lên ô text
        jtable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Mở nút
                btnDelete.setEnabled(true);
                btnEdit.setEnabled(true);

                row = jtable.getSelectedRow();
                // lay du lieu tu cot 0
                o = jtable.getValueAt(row, 0);
                // lấy dl từ bảng lên textfiled
                txtMaLoai.setText(jtable.getValueAt(row, 0).toString());
                txtTenLoai.setText(jtable.getValueAt(row, 1).toString());

                //txtMaSP không được chỉnh sửa
                txtMaLoai.setEditable(false);

            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Perform actions when the window is closing
                System.out.println("Window is closing...");
                // Tạo một cửa sổ mới của lớp menu
                menu d = new menu();
                d.prepare();
                d.setVisible(true);

            }
        });
    }

    public void show() {
        //Build controlPanel
        int topMg = 10, leftMg = 20, bottomMg = 10, rightMg = 20;
        controlPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 10, 0, 10);
        //// panel tìm kiếm
        JPanel panelTim = new JPanel();
        //panelTim.setBorder(new EmptyBorder(topMg, leftMg, bottomMg, rightMg));

        gbc.gridx = 0;
        gbc.gridy = 0;
//        gbc.weightx = 1.0;  // Dãn ra theo chiều ngang
//        gbc.weighty = 1.0;  // Dãn ra theo chiều dọc
        gbc.fill = GridBagConstraints.BOTH;  // giãn cả ngang và dọc
        //panelTim.setPreferredSize(new Dimension(400, 200));
        controlPanel.add(panelTim, gbc);

        // Build panelTim
        panelTim.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 10, 5, 10);
        txtTimKiem.setColumns(20);
        gbc.fill = GridBagConstraints.BOTH;  // giãn cả ngang và dọc
        panelTim.add(txtTimKiem, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 10, 5, 10);
        //gbc.anchor = GridBagConstraints.WEST;  //
        //panelTim.add(lb_TimKiem, gbc_Tim);
        gbc.fill = GridBagConstraints.BOTH;  // giãn cả ngang và dọc
        cboTimKiem.setPreferredSize(new Dimension(100, 20));
        panelTim.add(cboTimKiem, gbc);
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 10, 5, 10);
        //gbc.anchor = GridBagConstraints.WEST;  //
        //panelTim.add(lb_TimKiem, gbc_Tim);
        gbc.fill = GridBagConstraints.BOTH;  // giãn cả ngang và dọc
        panelTim.add(btnTimKiem, gbc);

        //---Panel txt, lb
        JPanel panel_center = new JPanel();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        controlPanel.add(panel_center, gbc);

        //panel_center.setBorder(new EmptyBorder(topMg, leftMg, bottomMg, rightMg));
        panel_center.setLayout(new GridBagLayout());
        panel_center.setPreferredSize(new Dimension(1120, 180));
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 30, 10, 20);
        gbc.anchor = GridBagConstraints.WEST;
        //  x tăng, y const;
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel_center.add(lb_MaLoai, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        txtMaLoai.setColumns(18);
        gbc.insets = new Insets(10, 30, 10, 20);
        panel_center.add(txtMaLoai, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 30, 10, 20);
        panel_center.add(lb_TenLoai, gbc);
        gbc.gridx = 3;
        gbc.gridy = 0;
        txtTenLoai.setColumns(18);
        gbc.insets = new Insets(10, 30, 10, 20);
        panel_center.add(txtTenLoai, gbc);
        
        // Panel for Buttons
        JPanel buttonPanel = new JPanel();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 6; // Span four columns
        // đặt thành phần ở giữa ô lưới
        gbc.anchor = GridBagConstraints.CENTER;
//        gbc.insets = new Insets(0, 10, 0, 10);
        panel_center.add(buttonPanel, gbc);
        // đặt khoảng cách hgap: ngang, vgap: dọc
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
//        buttonPanel.setLayout(new GridLayout(1,4,10,10)); 1 hàng 4 cột k/c 10
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnEdit);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnClear);
        buttonPanel.add(btnSortName);
        buttonPanel.add(btnXuatExcel);
        buttonPanel.add(btnExit);

        // label hiện số dòng
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.EAST;
        controlPanel.add(lbHien, gbc);

        JPanel Paneltable = new JPanel();
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridheight = 5;
//        gbc.weightx = 1.0;
//        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        controlPanel.add(Paneltable, gbc);
        Paneltable.setLayout(new BorderLayout());
        Paneltable.add(scrollPane, BorderLayout.CENTER);

        // Thiết lập kích thước ưu tiên cho Paneltable
        Paneltable.setPreferredSize(new Dimension(500, 300));
    }

    public void connect() {
        category_DAO cat_dao = new category_DAO();
        cat_dao.conn();
    }

    public void hienthi_tbl() {
        category_DAO cat_dao = new category_DAO();
        cat_dao.rsTableModel(jtable, tableModel);
        int rowCount = tableModel.getRowCount(); // trả về lượng hàng/ bản ghi
        lbHien.setText("Có " + rowCount + " sản phẩm");

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        JButton btn = (JButton) e.getSource();
        if (btn.equals(btnAdd)) {
            try {
                String newMalOai = txtMaLoai.getText();
                if (newMalOai.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập mã loại!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                // Kiểm tra xem mã thuốc mới đã tồn tại trong bảng hay không
                category_DAO cat_dao = new category_DAO();
                boolean kiemtra = cat_dao.kiemtratrungmaSP(newMalOai);
                if (!kiemtra) { // kiemtra == false
                    btn_add_actionerformed();
                    // Xóa hết dữ liệu từ model của JTable
                    tableModel.setRowCount(0);
                    //tableModel.setColumnCount(0);
                    hienthi_tbl();
                } else {
                    JOptionPane.showMessageDialog(null, "Mã loại đã tồn tại!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                    txtMaLoai.requestFocus();
                }

            } catch (Exception ex) {
                Logger.getLogger(BTL.category_form.class
                        .getName()).log(Level.SEVERE, null, ex);

            }
        } else if (btn.equals(btnEdit)) {
            int choice = JOptionPane.showConfirmDialog(mainFrame, "Bạn có muốn sửa thông tin loại sản phẩm không?", "Sửa loại sản phẩm", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                btn_edit_actionerformed();
                // Xóa hết dữ liệu hàng
//                tableModel.setRowCount(0);
//                // Hiển thị lại dữ liệu hàng
//                hienthi_tbl();
            }

        } else if (btn.equals(btnDelete)) {
            int choice = JOptionPane.showConfirmDialog(mainFrame, "Bạn có muốn xóa danh mục không?", "Xóa danh mục", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                btn_delete_actionerformed();
                // Xóa hết dữ liệu hàng
                tableModel.setRowCount(0);
                // Hiển thị lại dữ liệu hàng
                hienthi_tbl();
            }
        } else if (btn.equals(btnClear)) {
            btn_clear_actionerformed();
        } else if (btn.equals(btnExit)) {
            btn_exit_actionerformed();
        } else if (btn.equals(btnSortName)) {
            btn_sortID_actionerformed();
        } else if (btn.equals(btnXuatExcel)) {
            btn_Export_actionerformed();
        } else if (btn.equals(btnTimKiem)) {
            try {
                btn_tim_actionperformed();
            } catch (ParseException ex) {
                Logger.getLogger(category_form.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void btn_add_actionerformed() {
        category_model cat_model = new category_model();
        cat_model.setMaLoai(txtMaLoai.getText());
        cat_model.setTenLoai(txtTenLoai.getText());
        category_DAO cat_dao = new category_DAO();
        cat_dao.add_category(cat_model, category_form.this);
        this.btn_clear_actionerformed();

        int rowCount = tableModel.getRowCount(); // trả về lượng hàng/ bản ghi
        lbHien.setText("Có " + rowCount + " sản phẩm");
    }

    public void btn_clear_actionerformed() {
        txtMaLoai.setText("");
        txtTenLoai.setText("");
        txtTimKiem.setText("");
        addPlaceholder(txtTimKiem, "Bạn cần tìm gì ?");
        // txtMaSP được quyền chỉnh sửa
        txtMaLoai.setEditable(true);
        tableModel.setRowCount(0);
        hienthi_tbl();

        // Khóa nút
        btnDelete.setEnabled(false);
        btnEdit.setEnabled(false);

    }

    public void btn_delete_actionerformed() {
        String newMaSP = txtMaLoai.getText();
        category_DAO cat_dao = new category_DAO();

        cat_dao.delete_category(newMaSP);
        this.btn_clear_actionerformed();

        int rowCount = tableModel.getRowCount(); // trả về lượng hàng/ bản ghi
        lbHien.setText("Có " + rowCount + " sản phẩm");

    }

    public void btn_edit_actionerformed() {
        category_model cat_model = new category_model();
        cat_model.setMaLoai(txtMaLoai.getText());
        cat_model.setTenLoai(txtTenLoai.getText());

        category_DAO cat_dao = new category_DAO();
        cat_dao.edit_category(cat_model, category_form.this);
        troViTri(cat_model);
    }
    public void troViTri(category_model cate_model) {
        String maUpdate = cate_model.getMaLoai();
        int rowIndex = -1; // khởi tạo rowIndex
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            if (maUpdate.equals(tableModel.getValueAt(i, 0).toString())) {
                rowIndex = i; // tìm row
                break;
            }
        }
        if (rowIndex != -1) {
            // Update row in the table model
            tableModel.setValueAt(cate_model.getMaLoai(), rowIndex, 0);
            tableModel.setValueAt(cate_model.getTenLoai(), rowIndex, 1);

//            // Scroll to the updated row
//            jtable.scrollRectToVisible(jtable.getCellRect(rowIndex, 0, true));
        }
    }


    public void btn_exit_actionerformed() {
        // Xử lý sự kiện khi nút "Exit" được nhấp
        int choice = JOptionPane.showConfirmDialog(mainFrame, "Bạn có muốn thoát khỏi ứng dụng không?", "Thoát ứng dụng", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            System.exit(0); // Thoát khỏi ứng dụng với mã thoát 0
        }
    }

    public void btn_sortID_actionerformed() {
        category_DAO cat_dao = new category_DAO();
        tableModel.setRowCount(0);
        cat_dao.sortByID(jtable, tableModel);
    }
    // Biến đếm cho tên tệp Excel
    int fileCount = 1;

    public void btn_Export_actionerformed() {
         //Tạo workbook mới
        XSSFWorkbook workbook = new XSSFWorkbook();
        // Tạo một trang tính mới
        XSSFSheet sheet = workbook.createSheet("Sản phẩm " + fileCount);
        // Lấy số lượng hàng và cột trong JTable
        int rowCount = jtable.getRowCount();
        int columnCount = jtable.getColumnCount();

        XSSFRow headerRow = sheet.createRow(0);// Tạo một hàng mới để chứa tiêu đề cột
        // Ghi tiêu đề cột vào hàng tiêu đề
        for (int j = 0; j < columnCount; j++) {
            String columnHeader = jtable.getColumnName(j);
            // Tạo một ô mới cho tên cột
            XSSFCell cell = headerRow.createCell(j);
            // Thiết lập định dạng in đậm cho ô
            XSSFFont font = workbook.createFont();
            font.setBold(true);
            XSSFCellStyle style = workbook.createCellStyle();
            style.setFont(font);
            cell.setCellStyle(style);
            // Ghi tên cột vào ô tương ứng trong hàng tiêu đề
            cell.setCellValue(columnHeader);
        }
        int columnWidth = 3000; // Độ rộng mong muốn cho tất cả các cột (tùy chỉnh theo nhu cầu)
        for (int j = 0; j < columnCount; j++) {
            sheet.setColumnWidth(j, columnWidth);
        }
        // Ghi dữ liệu từ JTable vào các hàng tiếp theo trong trang tính
        for (int i = 0; i < rowCount; i++) {
            XSSFRow row = sheet.createRow(i + 1); // Tạo một hàng mới (bỏ qua hàng tiêu đề)
            for (int j = 0; j < columnCount; j++) {
                Object value = jtable.getValueAt(i, j); // Lấy giá trị từ JTable
                if (value != null) {
                    row.createCell(j).setCellValue(value.toString()); // Ghi giá trị vào ô tương ứng trong hàng
                }
            }
        }
        try ( FileOutputStream fileOut = new FileOutputStream("D:/ExportJava/Category_" + fileCount + ".xlsx")) {
            workbook.write(fileOut);
            JOptionPane.showMessageDialog(null, "Tạo tệp Excel thành công: Category_" + fileCount + ".xlsx");
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi khi tạo tệp Excel.");
        }
        fileCount++;
    }

    public void btn_tim_actionperformed() throws ParseException {
        category_DAO cat_dao_dao = new category_DAO();
        // Lấy lựa chọn từ JComboBox
        String selectedOption = cboTimKiem.getSelectedItem().toString();
        String keyword = txtTimKiem.getText();
        //System.out.println("keyword: " + keyword);
        if (keyword.equals("Bạn cần tìm gì ?")) {
            JOptionPane.showMessageDialog(null, "Bạn đang để trống!", "Thông báo", JOptionPane.ERROR_MESSAGE);
            //hienthi_tbl();
        } else {
            tableModel.setRowCount(0);  // xóa dl cũ trong bảng
            cat_dao_dao.search_thuoc(tableModel, selectedOption, keyword);
            int rowCount = tableModel.getRowCount(); // trả về lượng hàng/ bản ghi
            lbHien.setText("Có " + rowCount + " sản phẩm");
        }
    }

    private static void addPlaceholder(final JTextField textField, final String placeholder) {
        textField.setForeground(Color.GRAY);
        textField.setText(placeholder);

        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setForeground(Color.GRAY);
                    textField.setText(placeholder);
                }
            }
        });
    }

    public static void main(String args[]) {
        category_form cf = new category_form();
        //cf.show();

    }

    public void setVisible(boolean b) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
