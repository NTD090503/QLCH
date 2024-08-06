/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package VanPhongPham.Model;

import java.util.Date;

/**
 *
 * @author ntd
 */
public class HoaDon {
        private  String MaHoaDon , MaKhachHang,DiaChi,MaDonHang;
        private Date NgayTao;
        private double TongTien;
        private boolean TrangThai;

    public HoaDon(String MaHoaDon, String MaKhachHang, String MaDonHang, String DiaChi, Date NgayTao, double TongTien, boolean TrangThai) {
        this.MaHoaDon = MaHoaDon;
        this.MaKhachHang = MaKhachHang;
        this.DiaChi = DiaChi;
        this.MaDonHang = MaDonHang;
        this.NgayTao = NgayTao;
        this.TongTien = TongTien;
        this.TrangThai = TrangThai;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public String getMaDonHang() {
        return MaDonHang;
    }

    public void setMaDonHang(String MaDonHang) {
        this.MaDonHang = MaDonHang;
    }

    public String getMaHoaDon() {
        return MaHoaDon;
    }

    public void setMaHoaDon(String MaHoaDon) {
        this.MaHoaDon = MaHoaDon;
    }

    public String getMaKhachHang() {
        return MaKhachHang;
    }

    public void setMaKhachHang(String MaKhachHang) {
        this.MaKhachHang = MaKhachHang;
    }

    public Date getNgayTao() {
        return NgayTao;
    }

    public void setNgayTao(Date NgayTao) {
        this.NgayTao = NgayTao;
    }

    public double getTongTien() {
        return TongTien;
    }

    public void setTongTien(double TongTien) {
        this.TongTien = TongTien;
    }

    public boolean isTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(boolean TrangThai) {
        this.TrangThai = TrangThai;
    }
    

   
}
