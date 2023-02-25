/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.Nghiahtpc01295.Assignment2;



/**
 *
 * @author Nghiahtpc01295
 */
public class QuanAo {
    String MaSP;
    String TenSP;
    float Gia;
    String Hinh;
    int soLuong;
    

    public QuanAo(String MaSP, String TenSP, float Gia, String Hinh, int soLuong) {
        this.MaSP = MaSP;
        this.TenSP = TenSP;
        this.Gia = Gia;
        this.Hinh = Hinh;
        this.soLuong = soLuong;
        
    }

    public void setMaSP(String MaSP) {
        this.MaSP = MaSP;
    }

    public void setTenSP(String TenSP) {
        this.TenSP = TenSP;
    }

    public void setGia(float Gia) {
        this.Gia = Gia;
    }

    public void setHinh(String Hinh) {
        this.Hinh = Hinh;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getMaSP() {
        return MaSP;
    }

    public String getTenSP() {
        return TenSP;
    }

    public float getGia() {
        return Gia;
    }

    public String getHinh() {
        return Hinh;
    }

    public int getSoLuong() {
        return soLuong;
    }

    
}
