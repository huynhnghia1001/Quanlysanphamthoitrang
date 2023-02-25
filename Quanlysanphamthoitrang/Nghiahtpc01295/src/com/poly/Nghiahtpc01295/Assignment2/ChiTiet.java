/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.Nghiahtpc01295.Assignment2;

/**
 *
 * @author Nghiahtpc01295
 */
public class ChiTiet {
   
    String MaSP;
    String TenSP;
    String Mau;
    String KichThuoc;
    String ThongTin;

    public ChiTiet(String MaSP, String TenSP, String Mau, String KichThuoc, String ThongTin) {
        this.MaSP = MaSP;
        this.TenSP = TenSP;
        this.Mau = Mau;
        this.KichThuoc = KichThuoc;
        this.ThongTin = ThongTin;
    }

    public ChiTiet(String MaSP, String TenSP) {
        this.MaSP = MaSP;
        this.TenSP = TenSP;
    }

}
