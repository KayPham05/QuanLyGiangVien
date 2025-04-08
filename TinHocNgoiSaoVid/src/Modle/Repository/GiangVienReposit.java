package Modle.Repository;

import Modle.GiangVien;
import Modle.GiangVienCoHuu;
import Modle.GiangVienThiGiang;
import Modle.MonHoc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GiangVienReposit implements IGiangVienReposit {
    private static final GiangVienDAO dao = new GiangVienDAO();
    private List<GiangVien> danhSachGiangVien = new ArrayList<>();
    @Override
    public void themGiangVien(GiangVien gv) {
        dao.insertGiangVien(gv);
    }

    @Override
    public void themMonHocChoGiangVien(GiangVien gv, MonHoc mh) {
        dao.insertMonHocChoGiangVien(gv.getMaGV(), mh.getTenMonHoc());
    }

    @Override
    public boolean kiemTraGiangVienCoHuuTren50(GiangVien gv, int namHienTai) {
        return gv instanceof GiangVienCoHuu && (namHienTai - gv.getNamSinh() > 50);
    }

    @Override
    public boolean kiemTra2GVCungLoai(GiangVien gv1, GiangVien gv2) {
        // so sánh 2 giáo viên cùng loại(Cơ hưu or thịnh giảng ,cùng số năm công tác)
        return ( (gv1 instanceof GiangVienCoHuu) && (gv2 instanceof GiangVienCoHuu) )||  ((gv1 instanceof GiangVienThiGiang) && (gv2 instanceof GiangVienThiGiang));
    }

    @Override
    public int tongGVCoHuuSinhNamNhoHon1990() {
        List<GiangVien> ds = dao.getAll();
        int count = 0;
        for (GiangVien gv : ds) {
            if (gv instanceof GiangVienCoHuu && gv.getNamSinh() < 1990) {
                count++;
            }
        }
        return count;
    }

    @Override
    public GiangVien timGVCohuLonTuoiNhat() {
        List<GiangVien> ds = dao.getAll();
        GiangVienCoHuu max = null;
        for (GiangVien gv : ds) {
            if (gv instanceof GiangVienCoHuu) {
                if (max == null || gv.getNamSinh() < max.getNamSinh()) {
                    max = (GiangVienCoHuu) gv;
                }
            }
        }
        return max;
    }
    @Override
    public List<GiangVien> timKiemGiangVienTheoTen(String tenGiaoVien) {
        List<GiangVien> result = new ArrayList<>();
        for (GiangVien gv : dao.getAll()) {
            if (gv.getHoTen().toLowerCase().contains(tenGiaoVien.toLowerCase())) {
                result.add(gv);
            }
        }
        return result;
    }

    @Override
    public List<GiangVien> layDanhSachGianVienTheoNam(int nam) {
        List<GiangVien> result = new ArrayList<>();
        for (GiangVien gv : dao.getAll()) {
            if (gv.getNamSinh() == nam) {
                result.add(gv);
            }
        }
        return result;
    }

    @Override
    public List<GiangVien> sapXepTheoTenVaNamSinh() {
        List<GiangVien> list = new ArrayList<>(dao.getAll());
        list.sort((a, b) -> {
            int cmp = a.getHoTen().compareToIgnoreCase(b.getHoTen());
            if (cmp == 0) return Integer.compare(a.getNamSinh(), b.getNamSinh());
            return cmp;
        });
        return list;
    }

    @Override
    public Map<Integer, Long> thongKeTheoNamSinh() {
        return dao.getAll().stream()
                .collect(java.util.stream.Collectors.groupingBy(
                        GiangVien::getNamSinh,
                        java.util.stream.Collectors.counting()
                ));
    }

    @Override
    public List<GiangVien> getAll() {
        return dao.getAll();
    }

    @Override
    public GiangVien timGiangVienTheoMa(String maGV) {
        danhSachGiangVien = dao.getAll();
        for (GiangVien gv :  danhSachGiangVien ) {
            if (gv.getMaGV().equalsIgnoreCase(maGV)) {
                return gv;
            }
        }
        return null;
    }


}
