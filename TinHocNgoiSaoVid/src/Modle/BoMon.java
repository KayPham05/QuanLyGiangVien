package Modle;

import java.util.List;

public class BoMon {
    private String tenBoMon;
    private List<GiangVien> danhSachGiangVien;

    public BoMon(List<GiangVien> danhSachGiangVien, String tenBoMon) {
        this.danhSachGiangVien = danhSachGiangVien;
        this.tenBoMon = tenBoMon;
    }

    public List<GiangVien> getDanhSachGiangVien() {
        return danhSachGiangVien;
    }

    public void setDanhSachGiangVien(List<GiangVien> danhSachGiangVien) {
        this.danhSachGiangVien = danhSachGiangVien;
    }

    public String getTenBoMon() {
        return tenBoMon;
    }

    public void setTenBoMon(String tenBoMon) {
        this.tenBoMon = tenBoMon;
    }
}
