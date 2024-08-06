/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package VanPhongPham.Model;

/**
 *
 * @author hoan3
 */
public class NhaCungCap {
    //thuoc tinh
    private String maNcc;
    private String tenNcc;
    private String soDienThoai;
    private String diaChi;
    private String khuVuc;
    private String phuongXa;
    private String email;
    private String congTy;
    private String nhomNcc;
    private float noCanTra;
    private String ghiChu;

    //constructor
    public NhaCungCap(){
        
    }

    public NhaCungCap(String maNcc, String tenNcc, String soDienThoai, String diaChi, String khuVuc, String phuongXa, String email, String congTy, String nhomNcc, float noCanTra, String ghiChu) {
        this.maNcc = maNcc;
        this.tenNcc = tenNcc;
        this.soDienThoai = soDienThoai;
        this.diaChi = diaChi;
        this.khuVuc = khuVuc;
        this.phuongXa = phuongXa;
        this.email = email;
        this.congTy = congTy;
        this.nhomNcc = nhomNcc;
        this.noCanTra = noCanTra;
        this.ghiChu = ghiChu;
    }

    public float getNoCanTra() {
        return noCanTra;
    }

    public void setNoCanTra(float noCanTra) {
        this.noCanTra = noCanTra;
    }
    
   

    public String getCongTy() {
        return congTy;
    }

    public void setCongTy(String congTy) {
        this.congTy = congTy;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public String getKhuVuc() {
        return khuVuc;
    }

    public void setKhuVuc(String khuVuc) {
        this.khuVuc = khuVuc;
    }

    public String getMaNcc() {
        return maNcc;
    }

    public void setMaNcc(String maNcc) {
        this.maNcc = maNcc;
    }

    public String getNhomNcc() {
        return nhomNcc;
    }

    public void setNhomNcc(String nhomNcc) {
        this.nhomNcc = nhomNcc;
    }

    public String getPhuongXa() {
        return phuongXa;
    }

    public void setPhuongXa(String phuongXa) {
        this.phuongXa = phuongXa;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getTenNcc() {
        return tenNcc;
    }

    public void setTenNcc(String tenNcc) {
        this.tenNcc = tenNcc;
    }
    
           
}
