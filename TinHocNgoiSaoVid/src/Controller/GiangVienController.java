package Controller;

import Modle.GiangVien;
import Modle.GiangVienCoHuu;
import Modle.GiangVienThiGiang;
import Modle.MonHoc;
import Modle.Repository.IGiangVienReposit;
import View.GiangVienView;

import javax.swing.*;
import java.util.List;
import java.util.Map;

public class GiangVienController {
    private final IGiangVienReposit repo;
    private final GiangVienView view;

    public GiangVienController(IGiangVienReposit repo, GiangVienView view) {
        this.repo = repo;
        this.view = view;
        initListeners();
        loadDataToTable();
    }

    private void initListeners() {
        view.getBtnThemGV().addActionListener(e -> themGiangVien());
        view.getBtnThemMon().addActionListener(e -> themMonHocChoGV());
        view.getBtnTimTheoTen().addActionListener(e -> timTheoTen());
        view.getBtnSapXep().addActionListener(e -> sapXep());
        view.getBtnThongKe().addActionListener(e -> thongKe());
        view.getBtnKiemTra50().addActionListener(e -> kiemTraTren50());
        view.getBtnSoSanhGV().addActionListener(e -> kiemTraCungLoai());
        view.getBtnDemTruoc1990().addActionListener(e -> demGVTruoc1990());
        view.getBtnTimLonTuoi().addActionListener(e -> timLonTuoiNhat());
        view.getBtnLocThinhGiang().addActionListener(e -> locTheoNamSinh());
        view.getBtnLamMoi().addActionListener(e -> loadDataToTable());
    }

    private void themGiangVien() {
        GiangVien gv = view.layThongTinTuForm();
        if (gv != null) {
            repo.themGiangVien(gv);
            for (MonHoc mh : gv.getDanhSachMonHoc()) {
                repo.themMonHocChoGiangVien(gv, mh);
            }
            view.showMessage("Thêm giảng viên thành công!");
            loadDataToTable();
            view.clearForm();
        } else {
            view.showMessage("Dữ liệu không hợp lệ!");
        }
    }

    private void themMonHocChoGV() {
        MonHoc mh = view.layMonHocTuForm();
        GiangVien gv = view.layGiangVienDangChon();
        if (mh != null && gv != null) {
            repo.themMonHocChoGiangVien(gv, mh);
            view.showMessage("Thêm môn học thành công!");
            loadDataToTable();
        } else {
            view.showMessage("Chưa chọn giảng viên hoặc dữ liệu không hợp lệ!");
        }
    }

    private void timTheoTen() {
        String ten = view.getTxtTimKiem().trim();
        List<GiangVien> ketQua = repo.timKiemGiangVienTheoTen(ten);
        view.hienThiDanhSachGiangVien(ketQua);
    }

    private void sapXep() {
        List<GiangVien> ds = repo.sapXepTheoTenVaNamSinh();
        view.hienThiDanhSachGiangVien(ds);
    }

    private void thongKe() {
        Map<Integer, Long> tk = repo.thongKeTheoNamSinh();
        view.hienThiThongKe(tk);
    }

    private void kiemTraTren50() {
        GiangVien gv = view.layGiangVienDangChon();
        if (gv != null) {
            boolean kq = repo.kiemTraGiangVienCoHuuTren50(gv, 2021);
            view.showMessage(kq ? "Giảng viên cơ hữu trên 50 tuổi." : "Không trên 50 tuổi hoặc không phải giảng viên cơ hữu.");
        } else {
            view.showMessage("Vui lòng chọn một giảng viên.");
        }
    }

    public void kiemTraCungLoai() {
        List<GiangVien> list = view.layHaiGiangVienDuocChon();
        if (list.size() != 2) return;

        GiangVien gv1 = list.get(0);
        GiangVien gv2 = list.get(1);

        if ((gv1 instanceof GiangVienCoHuu && gv2 instanceof GiangVienCoHuu) ||
                (gv1 instanceof GiangVienThiGiang && gv2 instanceof GiangVienThiGiang)) {
            JOptionPane.showMessageDialog(null, "Hai giảng viên cùng loại.");
        } else {
            JOptionPane.showMessageDialog(null, "Hai giảng viên khác loại.");
        }
    }

    private void demGVTruoc1990() {
        int soLuong = repo.tongGVCoHuuSinhNamNhoHon1990();
        view.showMessage("Số giảng viên cơ hữu sinh trước 1990: " + soLuong);
    }

    private void timLonTuoiNhat() {
        GiangVien gv = repo.timGVCohuLonTuoiNhat();
        if (gv != null) {
            view.showMessage("Giảng viên cơ hữu lớn tuổi nhất: " + gv.getHoTen() + " (" + gv.getNamSinh() + ")");
        } else {
            view.showMessage("Không tìm thấy giảng viên cơ hữu nào.");
        }
    }

    private void locTheoNamSinh() {
        try {
            int nam = view.layNamSinhLoc();
            List<GiangVien> ds = repo.layDanhSachGianVienTheoNam(nam);
            view.hienThiDanhSachGiangVien(ds);
        } catch (NumberFormatException e) {
            view.showMessage("Vui lòng nhập năm hợp lệ!");
        }
    }

    private void loadDataToTable() {
        List<GiangVien> danhSach = repo.getAll();
        view.hienThiDanhSachGiangVien(danhSach);
    }
}
