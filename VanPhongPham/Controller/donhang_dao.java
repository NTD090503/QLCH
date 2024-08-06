/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package VanPhongPham.Controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

//import com.orsoncharts.util.json.JSONArray;
//import com.orsoncharts.util.json.JSONObject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListModel;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import VanPhongPham.Model.donhang_model;
import VanPhongPham.View.donhang_frm;

/**
 *
 * @author ntd
 */
public class donhang_dao {
    private static String DB_URL = "jdbc:mysql://localhost:3306/vanphongpham";
    private static String USER_NAME = "root";
    private static String PASSWORD = "";
    private Connection conn;
    private List<sp> arrsp = new ArrayList<sp>();
    public  List<donhang_model> arrdh = new ArrayList<donhang_model>();
    private JSONArray cacsp = new JSONArray();;
    private String sql;
   // private List<donhang_model> arrhd = new ArrayList<donhang_model>();
    public donhang_dao(){
      
     try {
          Class.forName("com.mysql.jdbc.Driver");
          conn = (Connection) DriverManager.getConnection(DB_URL,USER_NAME,PASSWORD);
      } catch (Exception e) {
          e.printStackTrace();
      }
 }
    public List<String> laynguonlistsp(){
          sql = "SELECT maSP FROM vppproduct";
           List<String> arrmasp = new ArrayList<String>();
        try
     {
        PreparedStatement ps = conn.prepareStatement(sql);  
        ResultSet rs = ps.executeQuery();
        
        while(rs.next())
        {
            String masp = rs.getString("maSP");
            arrmasp.add(masp);
        }
      }
     catch(Exception e){  e.printStackTrace();}
           
            for(String str : arrmasp)
        {
            System.out.println(" "+str);
        }
        return arrmasp;
    }
        public int layslmax(String ma){
          sql = "SELECT soLuong FROM vppproduct where maSP = ?";
          int slmax = 0;
     try
     {
        PreparedStatement ps = conn.prepareStatement(sql); 
        ps.setString(1, ma);
        ResultSet rs = ps.executeQuery();
        
        while(rs.next())
        {
            slmax = rs.getInt("soLuong");
        }
      }
     catch(Exception e){  e.printStackTrace();}
        return slmax;
    }
    class sp{
        public String msp , tensp;
        public int sl;

        public sp(String msp, String tensp, int sl) {
            this.msp = msp;
            this.tensp = tensp;
            this.sl = sl;
        }
        
    }
   public void napdlarrsp(donhang_frm v){
      // int j = v.tablesp.
       for(int i =0; i<v.modelsp.getRowCount();i++)
       {
           sp p = new sp(v.modelsp.getValueAt(i, 0).toString(), v.modelsp.getValueAt(i, 1).toString(),Integer.parseInt(v.modelsp.getValueAt(i, 2).toString()));
           arrsp.add(p);
       }
   }
//      public void napdlarrdh(donhang_frm v) throws JSONException{
//      // int j = v.tablesp.
//       for(int i =0; i<v.modelsp.getRowCount();i++)
//       {
//            String jsonString =  v.modeldh.getValueAt(i, 1).toString();
//            JSONArray msp_sl = new JSONArray(jsonString);
//           donhang_model dh = new donhang_model(Integer.parseInt(v.modeldh.getValueAt(i, 0).toString()),msp_sl,Integer.parseInt(v.modelsp.getValueAt(i, 2).toString()));
//           arrdh.add(dh);
//       }
//   }
   public boolean ktmsp_trung(String m, donhang_frm v){
       for (int i = 0; i < v.modelsp.getRowCount(); i++) {
           if(m.equalsIgnoreCase(v.modelsp.getValueAt(i, 0).toString())==true){
               return false;
           }
           
       }
       return true;
   }
    public void add_sp(donhang_frm v){
        if(!v.cbmasp.getSelectedItem().toString().isEmpty())
        {
          
            String maspchon = v.cbmasp.getSelectedItem().toString().trim();
            if(ktmsp_trung(maspchon,v)==true){  
            sql = "Select maSP,tenSP FROM vppproduct where maSP = ?";
             try
     {
        PreparedStatement ps = conn.prepareStatement(sql); 
        ps.setString(1, maspchon);
        ResultSet rs = ps.executeQuery();
        
        while(rs.next())
        {  
         String msp = rs.getString("maSP");
         String tensp = rs.getString("tenSP");
         int sl =Integer.parseInt(v.txtsoluong.getValue().toString());
         arrsp.add(new sp(msp, tensp, sl));
  
        }
         System.out.println("slarr "+arrsp.size());
      }
     catch(Exception e){  e.printStackTrace();}
        
        for(int i = 0; i< arrsp.size();i++){  
            v.modelsp.addRow(new Object[]{arrsp.get(i).msp,arrsp.get(i).tensp,arrsp.get(i).sl});
        }
            } else JOptionPane.showMessageDialog(null,"bạn đã nhập sản phẩm trùng");      
    }
        
     
    }
    public void xoa_sp(donhang_frm v){
        napdlarrsp(v);
        int i = v.tablesp.getSelectedRow();
        String macu = v.modelsp.getValueAt(i, 0).toString();
        //System.out.println("maspcu "+macu);
        if(!macu.isEmpty() && !arrsp.isEmpty()){
            //System.out.println("sz "+i);
            arrsp.remove(i);
            v.modelsp.setRowCount(0);
            for(sp p : arrsp){
           // v.modelsp.addRow(p.msp,p.tensp,p.sl);
            v.modelsp.addRow(new Object[]{p.msp,p.tensp,p.sl});
        }
            v.modelsp.fireTableDataChanged();
       }
    }
    public void clearsp(donhang_frm v){
        v.modelsp.setRowCount(0);
        v.cbmasp.setSelectedItem(null);
        v.txtsoluong.setValue(0);
        //arrsp.removeAll(arrsp);
    }
    public void xacnhan(donhang_frm v) throws JSONException{
            
            v.listModel.clear();
        for(int i =0; i<v.modelsp.getRowCount();i++)
        {
            taojson(cacsp, v.modelsp.getValueAt(i, 0).toString(), v.modelsp.getValueAt(i, 1).toString(), Integer.parseInt(v.modelsp.getValueAt(i, 2).toString()));
        }
           // System.out.println("dt json"+cacsp.toString());
         for (int i = 0; i < cacsp.length(); i++) {
             JSONObject sp = cacsp.getJSONObject(i);
            String maSP = sp.getString("maSP");
            String tenSP = sp.getString("tenSP");
            int soLuong = sp.getInt("soluong");
        v.listModel.addElement("Mã: " + maSP + ", Tên: " + tenSP + ", SL: " + soLuong);
        }
    }
    public void laynguonchotabledh (donhang_frm v) throws JSONException{
        sql = "SELECT * FROM vppdonhang2";
        laydl(sql);laynguon(v);
    }
    
            public void laydl(String sql_org){
     try
     {
        PreparedStatement ps = conn.prepareStatement(sql_org);  
        ResultSet rs = ps.executeQuery(); 
        while(rs.next())
        {
            int madh = rs.getInt("ma_don_hang");
            String jsonString = rs.getString("cac_maSP_SL");
            JSONArray msp_sl = new JSONArray(jsonString);
            int giamgia = rs.getInt("giam_gia");
            arrdh.add(new donhang_model(madh,msp_sl,giamgia));
        }
      }
     catch(Exception e){  e.printStackTrace();}
    }       
        public String donguontutvldh(int macu){
            String jsonString = "";
            sql = "SELECT cac_maSP_SL FROM vppdonhang2 WHERE ma_don_hang =?";
      try
     {
        PreparedStatement ps = conn.prepareStatement(sql); 
        ps.setInt(1, macu);
        ResultSet rs = ps.executeQuery(); 
        while(rs.next())
        {
              jsonString = rs.getString("cac_maSP_SL");
        }
      }
     catch(Exception e){  e.printStackTrace();}
            return jsonString;
        }
        public void laylistkhiclicktbldh(donhang_frm v,int macu) throws JSONException{
            v.listModel.clear();
         JSONArray arrdhclick = new JSONArray(donguontutvldh(macu));
        // System.out.println("aaaâ "+arrdhclick.toString());
         for (int i = 0; i < arrdhclick.length(); i++) {
             JSONObject sp = arrdhclick.getJSONObject(i);
            String maSP = sp.getString("maSP");
            String tenSP = sp.getString("tenSP");
            int soLuong = sp.getInt("soluong");
        v.listModel.addElement("Mã: " + maSP + ", Tên: " + tenSP + ", SL: " + soLuong);
        }
     }
      public void laynguontblsptudh(donhang_frm v,int macu) throws JSONException{
         v.modelsp.setRowCount(0);     
         JSONArray arrdhclick = new JSONArray(donguontutvldh(macu));
         for (int i = 0; i < arrdhclick.length(); i++) {
             JSONObject sp = arrdhclick.getJSONObject(i);
            String maSP = sp.getString("maSP");
            String tenSP = sp.getString("tenSP");
            int soLuong = sp.getInt("soluong");
        v.modelsp.addRow(new Object[]{maSP,tenSP,soLuong});
        }
         v.modelsp.fireTableDataChanged();
     }
     public void taojson(JSONArray jsa,String msp, String tensp, int sl) throws JSONException{
            JSONObject sp = new JSONObject();
            sp.put("maSP", msp);
            sp.put("tenSP",tensp);
            sp.put("soluong",sl);
            jsa.put(sp);
     }
     public void laynguon( donhang_frm v) throws JSONException{
      v.modeldh.setRowCount(0); 
      String cacmsp_slrow="";
     
      for(donhang_model dh : arrdh){
          // System.out.println("key "+dh.cacmmdh_sl.toString());
          for(int i=0;i<dh.cacmmdh_sl.length();i++)
          {
              JSONObject js = dh.cacmmdh_sl.getJSONObject(i);
              String masp = js.getString("maSP");
              String tensp = js.getString("tenSP");
              int sl = js.getInt("soluong");
              if(cacmsp_slrow.isEmpty())
                  cacmsp_slrow = masp+"-"+ tensp +"-"+sl;
              else
              cacmsp_slrow = cacmsp_slrow+" & "+ masp+"-"+ tensp +"-"+sl;
          }
       v.modeldh.addRow(new Object[]{dh.madh,cacmsp_slrow,dh.giamgia});
       cacmsp_slrow="";
          }
  }
     public void layjstujlist(donhang_frm v) throws JSONException{
         for(int i=0;i<v.listModel.getSize();i++){
            String[] mangjson = v.listModel.get(i).toString().split(",|:");
             String ma = mangjson[1].trim();
             String ten = mangjson[3].trim();
             int sl = Integer.parseInt(mangjson[5].trim());
             taojson(cacsp, ma, ten, sl);
         }
     }
     public void add(donhang_frm v) throws JSONException{
        // String cac_sp_sl="";
         layjstujlist(v); 
         if(cacsp.length()>0){
         sql = "INSERT vppdonhang2 (cac_maSP_SL,giam_gia) Values(?,?)";
         try{  
        PreparedStatement ps = conn.prepareStatement(sql);  
        ps.setString(1, cacsp.toString());
        ps.setInt(2, Integer.parseInt(v.txtgiamgia.getValue().toString()));
        
        
        //arrdh.add( );
        int row = ps.executeUpdate();
        if(row>0)
        {
            JOptionPane.showMessageDialog(null,"bạn đã thêm thành công");
            
        }
        else JOptionPane.showMessageDialog(null,"bạn đã thêm thất bại");
        
}
    catch(SQLException ex){  ex.printStackTrace();
}
         }
         clear(v);
         laynguonchotabledh(v); v.modeldh.fireTableDataChanged();
     }
     public void xoa(donhang_frm v) throws JSONException{
               sql = "DELETE FROM vppdonhang2 WHERE ma_don_hang = ?";
               int macu = Integer.parseInt(v.txtmadh.getText().toString());
         try{  
        PreparedStatement ps = conn.prepareStatement(sql);  
        ps.setInt(1, macu);
       
        int row = ps.executeUpdate();
        if(row>0)
        {
            JOptionPane.showMessageDialog(null,"bạn đã xóa thành công");
            
        }
        else JOptionPane.showMessageDialog(null,"bạn đã xóa thất bại");
        
}
    catch(SQLException ex){  ex.printStackTrace();
}
         
         laynguonchotabledh(v); v.modeldh.fireTableDataChanged();  
     }
     
     public void clear(donhang_frm v){
         v.txtmadh.setText("");v.txtgiamgia.setValue(0);v.txtsoluong.setValue(0);
         v.listModel.clear();v.modelsp.setRowCount(0);
     }
     public void  edit(donhang_frm v ) throws JSONException{
         
         layjstujlist(v);
         if(v.txtmadh.getText().isEmpty()){
             JOptionPane.showMessageDialog(null, "bạn đã nhập thiếu thông tin");
             return;
         }
         if(cacsp.length()>0){
         sql = "UPDATE vppdonhang2 SET cac_maSP_SL = ?, giam_gia = ? WHERE ma_don_hang = ?";
         try{  
        PreparedStatement ps = conn.prepareStatement(sql);  
        ps.setString(1, cacsp.toString());
        ps.setInt(2, Integer.parseInt(v.txtgiamgia.getValue().toString()));
        ps.setInt(3,  Integer.parseInt(v.txtmadh.getText().toString()));
        int row = ps.executeUpdate();
        if(row>0)
        {
            JOptionPane.showMessageDialog(null,"bạn đã sửa thành công");
            
        }
        else JOptionPane.showMessageDialog(null,"bạn đã sửa thất bại");
        
}
    catch(SQLException ex){  ex.printStackTrace();
}
         }
         laynguonchotabledh(v); v.modeldh.fireTableDataChanged();
     }
     
     public void xuatexecl(donhang_frm v){
         XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Lop Data");

        // Tạo hàng header
        XSSFRow headerRow = sheet.createRow(0);
        for (int i = 0; i < v.modeldh.getColumnCount(); i++) {
            XSSFCell cell = headerRow.createCell(i);
            cell.setCellValue(v.modelsp.getColumnName(i));
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
                for (int i = 0; i < v.modeldh.getRowCount(); i++) {
                    XSSFRow row = sheet.createRow(i + 1);
                    for (int j = 0; j < v.modeldh.getColumnCount(); j++) {
                        XSSFCell cell = row.createCell(j);
                        Object value = v.modeldh.getValueAt(i, j);
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
     public void tinhtien(donhang_frm v) throws JSONException{
         if(v.txtmadh.getText().isEmpty()){
             JOptionPane.showMessageDialog(null, "bạn cần chọn đơn hàng");
             return;
         }
         Date today = new Date();
         Float tinht =0.0f;
//         layjstujlist(v);
//         String[] mangjson=null;
//         List<String> str = new ArrayList<String>();
//         List<String> strmsp = new ArrayList<String>();
//         List<Integer> strsl = new ArrayList<Integer>();
//        for(int i=0;i<v.listModel.getSize();i++){
//            String[] cacCap = v.listModel.get(i).toString().split(",\\s*");
//            StringBuilder ketQua = new StringBuilder();
//            for (String cap : cacCap) {
//            String[] phanTu = cap.split(":\\s*");
//            if (phanTu[0].contains("Mã") || phanTu[0].contains("Tên") || phanTu[0].contains("SL")) {
//            ketQua.append(phanTu[1]).append(", ");
//            }
//         }
//        String ketQuaCuoiCung = ketQua.toString().replaceAll(", $", "");
//        System.out.println("str "+ketQuaCuoiCung);
//        mangjson = ketQuaCuoiCung.split(",");int h=0;
//            for(String tr : mangjson){
//             str.add(tr);
//              h++;
//             System.out.println("mã 1"+h+" "+tr);
//         }  
//         }
//         
//         System.out.println("test str "+str.size());
//         for(int i=0;i<str.size();i=i+3){
//             strmsp.add(str.get(i));
//         }
//         for(int i=2;i<str.size();i=i+3){
//             int gt =Integer.parseInt(str.get(i).trim());
//             strsl.add(gt);
//         }      
         
         
         JSONArray arrdhclick = new JSONArray(donguontutvldh(Integer.parseInt(v.txtmadh.getText())));
         List<sp> arrsp1 = new ArrayList<sp>();
         for (int i = 0; i < arrdhclick.length(); i++) {
             JSONObject sp = arrdhclick.getJSONObject(i);
            String maSP = sp.getString("maSP");
            String tenSP = sp.getString("tenSP");
            int soLuong = sp.getInt("soluong");
            arrsp1.add(new sp(maSP, tenSP, soLuong));
        }
         if(arrsp1.size()<0){
             JOptionPane.showMessageDialog(null, "không có sản phẩm"); return;
         }
         if(v.tabledh.getSelectedRow()!=-1)    
         {
          String sqlcheckdhtrung =   "SELECT * from vpphoadon where ma_don_hang =?";
        try
     {
        PreparedStatement ps1 = conn.prepareStatement(sqlcheckdhtrung);
        ps1.setInt(1, Integer.parseInt(v.txtmadh.getText()));
        ResultSet rscheck = ps1.executeQuery();
        if(rscheck.next())
        {JOptionPane.showMessageDialog(null, "đơn hàng này đã được tính tiền");
        return;}
        }
        catch(SQLException ex){  ex.printStackTrace();  }   
             
    
       
            java.util.Date utilDate = today;
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                  sql = "INSERT vpphoadon (maKH,ma_don_hang,ngay_tao,tong_tien,dia_chi_giao_hang,trang_thai_thanh_toan) Values(0,?,?,?,0,0 )";
        // System.out.println("danvkj");
                  try{  
            // System.out.println("danvkj");
        PreparedStatement ps = conn.prepareStatement(sql);  
        ps.setInt(1,Integer.parseInt(v.txtmadh.getText()));
        ps.setDate(2, sqlDate);
        float thanhtien=0,tongtien=0;int k =0,f=0;
       // for(String s: strmsp){
        for(sp s: arrsp1){
            
             int slsp=0;    
        String sqlchecksoluongthaydoi =   "SELECT soLuong from vppproduct where maSP =?";
        try
     {
        PreparedStatement pschecksl = conn.prepareStatement(sqlchecksoluongthaydoi);
        pschecksl.setString(1, s.msp);
        ResultSet rschecksl = pschecksl.executeQuery();
        while(rschecksl.next()){
            slsp = rschecksl.getInt("soLuong");
           // System.out.println("slsp "+slsp);
        }
        }
        catch(SQLException ex){  ex.printStackTrace();  }
        
        if(slsp<s.sl){
        JOptionPane.showMessageDialog(null, "Số lượng đã thay đổi mời bạn chọn lại");
        return; 
        }
            
            
        String sql1 =   "SELECT donGia from vppproduct where maSP =?";
       // String sql2 = "UPDATE vppproduct SET soLuong= soLuong ? WHERE mmaSP = ?";
        try
     {
        PreparedStatement ps1 = conn.prepareStatement(sql1);
        //ps1.setString(1,s);
        ps1.setString(1, s.msp);
        System.out.println("ma sp "+s.msp);
        ResultSet rs1 = ps1.executeQuery();
        
        while(rs1.next())
        {
            float dongia = rs1.getFloat("donGia"); 
           // int sl = strsl.get(k);k++;
            int sl = s.sl;
            thanhtien = sl*dongia;
            tongtien = tongtien+thanhtien;
            System.out.println("tt "+thanhtien);
           
        }
        }
        catch(SQLException ex){  ex.printStackTrace();
}

        }
        int gg = Integer.parseInt(v.txtgiamgia.getValue().toString());
        double pt = gg/100.0; //System.out.println("pt "+pt);
        double tt = 0;
        if(pt>0.0) tt = tongtien - tongtien*pt;
        else tt = tongtien;
        System.out.println("tt2 "+tongtien);
        ps.setDouble(3, tt);
      
        int row = ps.executeUpdate();
        if(row>0)
        {
            JOptionPane.showMessageDialog(null,"bạn đã tính tiền thành công");
            
        }
        else JOptionPane.showMessageDialog(null,"bạn đã tính tiền thất bại");
        
}
    catch(SQLException ex){  ex.printStackTrace();
}
         
       for (sp s : arrsp1) {
                 
                      
        String sql2 = "UPDATE vppproduct SET soLuong= soLuong-? WHERE maSP = ?";
           try
     {
        PreparedStatement ps2 = conn.prepareStatement(sql2);
        int sl = s.sl;
        ps2.setInt(1, sl);
        ps2.setString(2,s.msp);
        int rs2 = ps2.executeUpdate();
        if(rs2>0)
        {
            System.out.println(" dã sửa sl");
        }
          
        }catch(SQLException ex){  ex.printStackTrace();
}
         
         
       }
         }
               
         
     }
    
     public static void main(String[] args) {
           
    }
}

