package Modle;

import java.util.List;

public class GiangVienCoHuu extends GiangVien {
    private float heSoLuong;
    private int namBatDauCongTac;

    public GiangVienCoHuu(List<MonHoc> danhSachMonHoc, String hoTen, String maGV, int namSinh, int soNamCongTac, String trinhDo, float heSoLuong, int namBatDauCongTac) {
        super(danhSachMonHoc, hoTen, maGV, namSinh, soNamCongTac, trinhDo);
        this.heSoLuong = heSoLuong;
        this.namBatDauCongTac = namBatDauCongTac;
    }


    public float getHeSoLuong() {
        return heSoLuong;
    }

    public void setHeSoLuong(float heSoLuong) {
        this.heSoLuong = heSoLuong;
    }

    public int getNamBatDauCongTac() {
        return namBatDauCongTac;
    }

    public void setNamBatDauCongTac(int namBatDauCongTac) {
        this.namBatDauCongTac = namBatDauCongTac;
    }
}
