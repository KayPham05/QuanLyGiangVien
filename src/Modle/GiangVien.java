package Modle;

import java.util.ArrayList;
import java.util.List;

public class GiangVien {
    private String maGV;
    private String hoTen;
    private int namSinh;
    private String trinhDo;
    private int soNamCongTac;
    private List<MonHoc> danhSachMonHoc;

    public GiangVien(String maGV, String hoTen, int namSinh, String trinhDo, int soNamCongTac) {
        this.maGV = maGV;
        this.hoTen = hoTen;
        this.namSinh = namSinh;
        this.trinhDo = trinhDo;
        this.soNamCongTac = soNamCongTac;
        this.danhSachMonHoc = new ArrayList<>();
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
