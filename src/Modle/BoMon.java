package Modle;

import java.util.ArrayList;
import java.util.List;

public class BoMon {
    private String tenBoMon;
    private List<GiangVien> danhSachGiangVien;

    public BoMon(String tenBoMon) {
        this.tenBoMon = tenBoMon;
        this.danhSachGiangVien = new ArrayList<>();
    }

    public String getTenBoMon() {
        return tenBoMon;
    }

    public void setTenBoMon(String tenBoMon) {
        this.tenBoMon = tenBoMon;
    }

    public List<GiangVien> getDanhSachGiangVien() {
        return danhSachGiangVien;
    }

    public void setDanhSachGiangVien(List<GiangVien> danhSachGiangVien) {
        this.danhSachGiangVien = danhSachGiangVien;
    }

    public void themGiangVien(GiangVien gv) {
        this.danhSachGiangVien.add(gv);
    }
}
