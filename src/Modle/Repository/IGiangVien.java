package Modle.Repository;

import Modle.GiangVien;
import Modle.GiangVienCoHuu;
import Modle.GiangVienThinhGiang;
import Modle.MonHoc;

import java.util.List;
import java.util.Map;

public interface IGiangVien {
    void themGiangVien(GiangVien gv);

    void themMonChoGiangVien(GiangVien gv, MonHoc mon);

    boolean laCoHuuVaTren50Tuoi(GiangVien gv, int namHienTai);

    boolean cungLoaiVaKinhNghiem(GiangVien gv1, GiangVien gv2);

    long demCoHuuTruoc1990();

    GiangVienCoHuu timCoHuuLonTuoiNhat();

    boolean coGiangVienTen(String ten);

    List<GiangVienThinhGiang> layThinhGiangTheoNamSinh(int nam);

    void sapXepTheoTenVaNamSinh();

    Map<Integer, Long> thongKeTheoNamSinh();

    List<GiangVien> getDanhSachGiangVien();
}
