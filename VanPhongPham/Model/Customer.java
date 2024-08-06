/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package VanPhongPham.Model;

/**
 *
 * @author quynh
 */
public class Customer {
      private String txtMaKh, txtTenKH, txtDiaChi, txtEmail, txtGioiTinh, txtSDT;
      private int NgaySinh;
      
      public Customer(){
          
      }

    public Customer(String txtMaKh, String txtTenKH, String txtDiaChi, String txtEmail, String txtGioiTinh, String SDT, int NgaySinh) {
        this.txtMaKh = txtMaKh;
        this.txtTenKH = txtTenKH;
        this.txtDiaChi = txtDiaChi;
        this.txtEmail = txtEmail;
        this.txtGioiTinh = txtGioiTinh;
        this.txtSDT = SDT;
        this.NgaySinh = NgaySinh;
    }

    public int getNgaySinh() {
        return NgaySinh;
    }

    public String getSDT() {
        return txtSDT;
    }

    public String getTxtDiaChi() {
        return txtDiaChi;
    }

    public String getTxtEmail() {
        return txtEmail;
    }

    public String getTxtGioiTinh() {
        return txtGioiTinh;
    }

    public String getTxtMaKh() {
        return txtMaKh;
    }

    public String getTxtTenKH() {
        return txtTenKH;
    }

    public void setNgaySinh(int NgaySinh) {
        this.NgaySinh = NgaySinh;
    }

    public void setSDT(String SDT) {
        this.txtSDT = SDT;
    }

    public void setTxtDiaChi(String txtDiaChi) {
        this.txtDiaChi = txtDiaChi;
    }

    public void setTxtEmail(String txtEmail) {
        this.txtEmail = txtEmail;
    }

    public void setTxtGioiTinh(String txtGioiTinh) {
        this.txtGioiTinh = txtGioiTinh;
    }

    public void setTxtMaKh(String txtMaKh) {
        this.txtMaKh = txtMaKh;
    }

    public void setTxtTenKH(String txtTenKH) {
        this.txtTenKH = txtTenKH;
    }
      
      
      public static void main (String[] args){
          Customer cs =  new Customer();
      }        
}
