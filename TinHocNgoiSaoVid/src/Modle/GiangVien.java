package Modle;

import java.util.List;

public class GiangVien {
    private String maGV,hoTen,trinhDo;
    private int namSinh, soNamCongTac;
    private List<MonHoc> danhSachMonHoc;

    public GiangVien(List<MonHoc> danhSachMonHoc, String hoTen, String maGV, int namSinh, int soNamCongTac, String trinhDo) {
        this.danhSachMonHoc = danhSachMonHoc;
        this.hoTen = hoTen;
        this.maGV = maGV;
        this.namSinh = namSinh;
        this.soNamCongTac = soNamCongTac;
        this.trinhDo = trinhDo;
    }

    public List<MonHoc> getDanhSachMonHoc() {
        return danhSachMonHoc;
    }

    public void setDanhSachMonHoc(List<MonHoc> danhSachMonHoc) {
        this.danhSachMonHoc = danhSachMonHoc;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getMaGV() {
        return maGV;
    }

    public void setMaGV(String maGV) {
        this.maGV = maGV;
    }

    public int getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(int namSinh) {
        this.namSinh = namSinh;
    }

    public int getSoNamCongTac() {
        return soNamCongTac;
    }

    public void setSoNamCongTac(int soNamCongTac) {
        this.soNamCongTac = soNamCongTac;
    }

    public String getTrinhDo() {
        return trinhDo;
    }

    public void setTrinhDo(String trinhDo) {
        this.trinhDo = trinhDo;
    }
}
