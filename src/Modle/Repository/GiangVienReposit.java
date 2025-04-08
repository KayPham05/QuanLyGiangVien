package Modle.Repository;

import Modle.GiangVien;
import Modle.GiangVienCoHuu;
import Modle.GiangVienThinhGiang;
import Modle.MonHoc;

import java.util.*;
import java.util.stream.Collectors;

public class GiangVienReposit implements IGiangVien {

    // Danh sách giảng viên hiện có trong bộ nhớ
    private List<GiangVien> danhSachGiangVien = new ArrayList<>();

    // Thêm giảng viên vào danh sách
    @Override
    public void themGiangVien(GiangVien gv) {
        danhSachGiangVien.add(gv);
    }

    // Thêm môn học vào danh sách môn giảng dạy của giảng viên
    @Override
    public void themMonChoGiangVien(GiangVien gv, MonHoc mon) {
        gv.getDanhSachMonHoc().add(mon);
    }

    // Kiểm tra giảng viên có phải là cơ hữu và trên 50 tuổi (tính đến năm hiện tại)
    @Override
    public boolean laCoHuuVaTren50Tuoi(GiangVien gv, int namHienTai) {
        return gv instanceof GiangVienCoHuu && (namHienTai - gv.getNamSinh()) > 50;
    }

    // Kiểm tra hai giảng viên có cùng loại và cùng số năm công tác
    @Override
    public boolean cungLoaiVaKinhNghiem(GiangVien gv1, GiangVien gv2) {
        return gv1.getClass() == gv2.getClass() &&
                gv1.getSoNamCongTac() == gv2.getSoNamCongTac();
    }

    // Đếm số giảng viên cơ hữu có năm sinh trước 1990
    @Override
    public long demCoHuuTruoc1990() {
        return danhSachGiangVien.stream()
                .filter(gv -> gv instanceof GiangVienCoHuu && gv.getNamSinh() < 1990)
                .count();
    }

    // Tìm giảng viên cơ hữu lớn tuổi nhất (năm sinh nhỏ nhất)
    @Override
    public GiangVienCoHuu timCoHuuLonTuoiNhat() {
        return danhSachGiangVien.stream()
                .filter(gv -> gv instanceof GiangVienCoHuu)
                .map(gv -> (GiangVienCoHuu) gv)
                .min(Comparator.comparingInt(GiangVien::getNamSinh))
                .orElse(null);
    }

    // Kiểm tra trong danh sách có giảng viên nào có họ tên trùng với tên cho trước không
    @Override
    public boolean coGiangVienTen(String ten) {
        return danhSachGiangVien.stream()
                .anyMatch(gv -> gv.getHoTen().equalsIgnoreCase(ten));
    }

    // Lấy danh sách giảng viên thỉnh giảng có năm sinh bằng năm cho trước
    @Override
    public List<GiangVienThinhGiang> layThinhGiangTheoNamSinh(int nam) {
        return danhSachGiangVien.stream()
                .filter(gv -> gv instanceof GiangVienThinhGiang && gv.getNamSinh() == nam)
                .map(gv -> (GiangVienThinhGiang) gv)
                .collect(Collectors.toList());
    }

    // Sắp xếp danh sách giảng viên theo thứ tự tăng dần tên, giảm dần năm sinh
    @Override
    public void sapXepTheoTenVaNamSinh() {
        danhSachGiangVien.sort(Comparator
                .comparing(GiangVien::getHoTen)
                .thenComparing(Comparator.comparingInt(GiangVien::getNamSinh).reversed()));
    }

    // Thống kê số lượng giảng viên theo từng năm sinh
    @Override
    public Map<Integer, Long> thongKeTheoNamSinh() {
        return danhSachGiangVien.stream()
                .collect(Collectors.groupingBy(GiangVien::getNamSinh, Collectors.counting()));
    }

    // Lấy danh sách giảng viên hiện tại
    @Override
    public List<GiangVien> getDanhSachGiangVien() {
        return danhSachGiangVien;
    }
}
