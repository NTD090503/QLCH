/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package VanPhongPham.Model;

/**
 *
 * @author TranNgoc ^.^
 */
public class product_model {
    // Thuoc tinh
    public int  soLuong;
    public String maSP, maNCC, maLoai,tenSP, moTa, trangThai;
    public float donGia;
    
    // Phuong thuc

   
    public product_model() {
    }

   
    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getSoLuong() {
        return soLuong;
    }

    

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public void setDonGia(float donGia) {
        this.donGia = donGia;
    }

    

    public String getTenSP() {
        return tenSP;
    }

    public String getMoTa() {
        return moTa;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public float getDonGia() {
        return donGia;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public void setMaNCC(String maNCC) {
        this.maNCC = maNCC;
    }

    public void setMaLoai(String maLoai) {
        this.maLoai = maLoai;
    }

    public String getMaSP() {
        return maSP;
    }

    public String getMaNCC() {
        return maNCC;
    }

    public String getMaLoai() {
        return maLoai;
    }

    public product_model(int soLuong, String maSP, String maNCC, String maLoai, String tenSP, String moTa, String trangThai, float donGia) {
        this.soLuong = soLuong;
        this.maSP = maSP;
        this.maNCC = maNCC;
        this.maLoai = maLoai;
        this.tenSP = tenSP;
        this.moTa = moTa;
        this.trangThai = trangThai;
        this.donGia = donGia;
    }
    
}
