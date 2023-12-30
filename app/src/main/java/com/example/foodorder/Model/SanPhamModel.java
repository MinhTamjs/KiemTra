package com.example.foodorder.Model;

public class SanPhamModel {
    private int masp;
    private String tesp;
    private String giasp;
    private byte[] image;

    public SanPhamModel(int masp, String tesp, String giasp, byte[] image) {
        this.masp = masp;
        this.tesp = tesp;
        this.giasp = giasp;
        this.image = image;
    }

    public int getMasp() {
        return masp;
    }

    public void setMasp(int masp) {
        this.masp = masp;
    }

    public String getTesp() {
        return tesp;
    }

    public void setTesp(String tesp) {
        this.tesp = tesp;
    }

    public String getGiasp() {
        return giasp;
    }

    public void setGiasp(String giasp) {
        this.giasp = giasp;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
