/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package VanPhongPham.Model;

import org.json.JSONArray;
import org.json.JSONArray;
import org.json.JSONArray;

/**
 *
 * @author ntd
 */
public class donhang_model {
public int madh,giamgia;
public JSONArray cacmmdh_sl;

    public donhang_model(int madh, JSONArray cacmmdh_sl, int giamgia) {
        this.madh = madh;
        this.giamgia = giamgia;
        this.cacmmdh_sl = cacmmdh_sl;
    }

    public JSONArray getcacmmdh_sl() {
        return cacmmdh_sl;
    }

    public void setcacmmdh_sl(JSONArray cacmmdh_sl) {
        this.cacmmdh_sl = cacmmdh_sl;
    }

    public int getGiamgia() {
        return giamgia;
    }

    public void setGiamgia(int giamgia) {
        this.giamgia = giamgia;
    }

    public int getmadh() {
        return madh;
    }

    public void setmadh(int madh) {
        this.madh = madh;
    }
}
