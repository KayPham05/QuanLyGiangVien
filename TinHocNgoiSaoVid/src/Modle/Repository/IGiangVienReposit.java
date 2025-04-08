package Modle.Repository;

import Modle.GiangVien;
import Modle.GiangVienCoHuu;
import Modle.MonHoc;

import java.util.List;
import java.util.Map;

public interface IGiangVienReposit {
    //1 thêm giảng viên
    void themGiangVien(GiangVien gv);

    //2 thêm môn học
    void themMonHocChoGiangVien(GiangVien gv,MonHoc mh);

    //3 Kiểm tra giảng viên là cơ hửu và trên 50 tủi hay không, theo mốc 2021

    boolean kiemTraGiangVienCoHuuTren50(GiangVien gv, int namHienTai);

    //4 Kiểm tra 2 giảng viên có cùng loại và số năm công tác

    boolean kiemTra2GVCungLoai(GiangVien gv1, GiangVien gv2);

    //5 Tính tổng số giảng viên cơ hữu của bộ môn có năm sinh trước 1990

    int tongGVCoHuuSinhNamNhoHon1990();

    //6 Tìm giảng viên cơ hữu lớn tuổi nhất trong bộ môn
    GiangVien timGVCohuLonTuoiNhat();

    //7 Tìm xem giảng viên có tên cho trước có công tác ở bộ môn hay không?
    List<GiangVien> timKiemGiangVienTheoTen(String tenGiaoVien);

    //8 Lấy ra danh sách các giảng viên thỉnh giảng có năm sinh là một năm cho trước (ví dụ năm 1990, hay 1989)
    List<GiangVien> layDanhSachGianVienTheoNam(int nam);

    //9 Sắp xếp các giảng viên trong bộ môn tăng dần theo họ tên và giảm dần theo năm sinh (sử dụng interface Comparable hoặc Comparator)

    List<GiangVien> sapXepTheoTenVaNamSinh();

    //10 Thống kê số lượng giảng viên của bộ môn theo năm sinh. Ví dụ 1990: 5, 1989: 10, … năm 1990 có 5 giảng viên, năm 1989 có 10 giảng viên.
    Map<Integer, Long> thongKeTheoNamSinh();

    // 11. Lấy tất cả giảng viên
    List<GiangVien> getAll();


    GiangVien timGiangVienTheoMa(String maGV);







}
