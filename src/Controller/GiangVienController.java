package Controller;

import Modle.*;
import Modle.Repository.GiangVienDAO;
import Modle.Repository.GiangVienReposit;

import java.util.*;
import java.util.stream.Collectors;

public class GiangVienController {
    private final GiangVienDAO dao =  new GiangVienDAO();
    private final GiangVienReposit reposit = new GiangVienReposit();
    private final List<GiangVien> danhSachGV = new ArrayList<>();

    public GiangVienController() {
        // Khởi tạo danh sách từ cơ sở dữ liệu
        danhSachGV.addAll(dao.layTatCaGiangVien());
    }

    public List<GiangVien> layTatCaGiangVien() {
        return danhSachGV;
    }

    // 1. Thêm giảng viên
    public void themGiangVien(String ma, String ten, int namSinh, String trinhDo, int soNamCT, String loai) {
        GiangVien gv;
        if (loai.equalsIgnoreCase("Cơ Hữu")) {
            gv = new GiangVienCoHuu(ma, ten, namSinh, trinhDo, soNamCT);
        } else {
            gv = new GiangVienThinhGiang(ma, ten, namSinh, trinhDo, soNamCT, loai);
        }
        danhSachGV.add(gv);
        dao.themGiangVien(gv); // lưu vào database
    }

    // 3. Thêm môn học cho một giảng viên
    public void themMonHocChoGiangVien(GiangVien gv, MonHoc monHoc) {
        gv.getDanhSachMonHoc().add(monHoc);
        dao.capNhatMonHoc(gv.getMaGV(), gv.getDanhSachMonHoc());
    }

    // 4. Kiểm tra giảng viên là cơ hữu và > 50 tuổi (tính đến 2021)
    public boolean laCoHuuVaTren50(GiangVien gv) {
        return gv instanceof GiangVienCoHuu && (2021 - gv.getNamSinh()) > 50;
    }

    // 5. Kiểm tra hai giảng viên có cùng loại và cùng số năm công tác không
    public boolean cungLoaiVaKinhNghiem(GiangVien gv1, GiangVien gv2) {
        return gv1.getClass().equals(gv2.getClass()) &&
                gv1.getSoNamCongTac() == gv2.getSoNamCongTac();
    }

    // 6. Tính tổng số giảng viên cơ hữu có năm sinh < 1990
    public long demCoHuuTruoc1990() {
        return danhSachGV.stream()
                .filter(gv -> gv instanceof GiangVienCoHuu && gv.getNamSinh() < 1990)
                .count();
    }

    // 7. Tìm giảng viên cơ hữu lớn tuổi nhất
    public GiangVien timCoHuuLonTuoiNhat() {
        return danhSachGV.stream()
                .filter(gv -> gv instanceof GiangVienCoHuu)
                .min(Comparator.comparingInt(GiangVien::getNamSinh))
                .orElse(null);
    }

    // 8. Tìm giảng viên có tên cho trước
    public boolean coGiangVienTen(String ten) {
        return danhSachGV.stream()
                .anyMatch(gv -> gv.getHoTen().equalsIgnoreCase(ten));
    }

    // 9. Lấy danh sách giảng viên thỉnh giảng theo năm sinh
    public List<GiangVienThinhGiang> layThinhGiangTheoNam(int nam) {
        return danhSachGV.stream()
                .filter(gv -> gv instanceof GiangVienThinhGiang && gv.getNamSinh() == nam)
                .map(gv -> (GiangVienThinhGiang) gv)
                .collect(Collectors.toList());
    }

    // 10. Sắp xếp tăng dần theo họ tên, giảm dần theo năm sinh
    public void sapXepTheoTenVaNamSinh() {
        danhSachGV.sort((gv1, gv2) -> {
            int comp = gv1.getHoTen().compareToIgnoreCase(gv2.getHoTen());
            if (comp == 0) {
                return Integer.compare(gv2.getNamSinh(), gv1.getNamSinh());
            }
            return comp;
        });
    }

    // 11. Thống kê số lượng giảng viên theo năm sinh
    public Map<Integer, Long> thongKeTheoNamSinh() {
        return danhSachGV.stream()
                .collect(Collectors.groupingBy(GiangVien::getNamSinh, Collectors.counting()));
    }
}
