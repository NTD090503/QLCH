/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package VanPhongPham.Model;
/**
 *
 * @author ntd
 */
public class Doanhthu {
public String thoigian;
public double tongtien;
public int sohd;

    public Doanhthu() {
    }


    public Doanhthu(String thoigian, double tongtien, int sohd) {
        this.thoigian = thoigian;
        this.tongtien = tongtien;
        this.sohd = sohd;
    }

    public int getSohd() {
        return sohd;
    }

    public void setSohd(int sohd) {
        this.sohd = sohd;
    }

    public String getThoigian() {
        return thoigian;
    }

    public void setThoigian(String thoigian) {
        this.thoigian = thoigian;
    }

    public double getTongtien() {
        return tongtien;
    }

    public void setTongtien(double tongtien) {
        this.tongtien = tongtien;
    }

        
}
