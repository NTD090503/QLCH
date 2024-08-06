/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package VanPhongPham.Model;

/**
 *
 * @author quynh
 */
public class Account {
        private String txtID, txtPassWord, txtSDT, txtDiaChi, txtGioiTinh, txtEmail, txtmaNV, txttenNV;
        private int txtTuoi;
        
        public Account(){
            
        }

    public Account(String txtID, String txtPassWord, String txtSDT, String txtDiaChi, String txtGioiTinh, String txtEmail, String txtmaNV, String txttenNV, int txtTuoi) {
        this.txtID = txtID;
        this.txtPassWord = txtPassWord;
        this.txtSDT = txtSDT;
        this.txtDiaChi = txtDiaChi;
        this.txtGioiTinh = txtGioiTinh;
        this.txtEmail = txtEmail;
        this.txtmaNV = txtmaNV;
        this.txttenNV = txttenNV;
        this.txtTuoi = txtTuoi;
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

    public String getTxtID() {
        return txtID;
    }

    public String getTxtPassWord() {
        return txtPassWord;
    }

    public String getTxtSDT() {
        return txtSDT;
    }

    public int getTxtTuoi() {
        return txtTuoi;
    }

    public String getTxtmaNV() {
        return txtmaNV;
    }

    public String getTxttenNV() {
        return txttenNV;
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

    public void setTxtID(String txtID) {
        this.txtID = txtID;
    }

    public void setTxtPassWord(String txtPassWord) {
        this.txtPassWord = txtPassWord;
    }

    public void setTxtSDT(String txtSDT) {
        this.txtSDT = txtSDT;
    }

    public void setTxtTuoi(int txtTuoi) {
        this.txtTuoi = txtTuoi;
    }

    public void setTxtmaNV(String txtmaNV) {
        this.txtmaNV = txtmaNV;
    }

    public void setTxttenNV(String txttenNV) {
        this.txttenNV = txttenNV;
    }

    void setTxtmaNV(Object selectedItem) {
        //throw new UnsupportedOperationException("Not yet implemented");
    }
        
        
}
