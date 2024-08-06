/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package VanPhongPham.Model;

/**
 *
 * @author hoan3
 */
public class NhanSu {
    private String maNV;
    private String tenNV;
    private String diaChi;
    private String sDT;
    private String ngaySinh;
    private String noiSinh;
    private String email;
    private String cMND;
    private String gioiTinh;
    private String trangThai;

    public NhanSu(){
        
    }
    
    public NhanSu(String maNV, String tenNV, String diaChi, String sDT, String ngaySinh, String noiSinh, String email, String cMND, String gioiTinh, String trangThai) {
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.diaChi = diaChi;
        this.sDT = sDT;
        this.ngaySinh = ngaySinh;
        this.noiSinh = noiSinh;
        this.email = email;
        this.cMND = cMND;
        this.gioiTinh = gioiTinh;
        this.trangThai = trangThai;
    }

    public String getCMND() {
        return cMND;
    }

    public void setCMND(String cMND) {
        this.cMND = cMND;
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

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getNoiSinh() {
        return noiSinh;
    }

    public void setNoiSinh(String noiSinh) {
        this.noiSinh = noiSinh;
    }

    public String getSDT() {
        return sDT;
    }

    public void setSDT(String sDT) {
        this.sDT = sDT;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
    
    
    
}
