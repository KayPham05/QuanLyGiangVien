package Modle.Repository;

import Modle.GiangVien;
import Modle.GiangVienCoHuu;
import Modle.GiangVienThinhGiang;
import Modle.MonHoc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GiangVienDAO {

    // Thêm giảng viên vào cơ sở dữ liệu
    public void themGiangVien(GiangVien gv) {
        try (Connection conn = DBConection.getConnection()) {
            String sql = "INSERT INTO GiangViens (MaGV, HoTen, NamSinh, TrinhDo, SoNamCongTac, LoaiGV) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, gv.getMaGV());
            stmt.setString(2, gv.getHoTen());
            stmt.setInt(3, gv.getNamSinh());
            stmt.setString(4, gv.getTrinhDo());
            stmt.setInt(5, gv.getSoNamCongTac());
            stmt.setString(6, gv instanceof GiangVienCoHuu ? "CoHuu" : "ThinhGiang");
            stmt.executeUpdate();

            if (gv instanceof GiangVienCoHuu) {
                GiangVienCoHuu gch = (GiangVienCoHuu) gv;
                String sql2 = "INSERT INTO GiangVienCoHuu (MaGV, HeSoLuong, NamBatDau) VALUES (?, ?, ?)";
                PreparedStatement stmt2 = conn.prepareStatement(sql2);
                stmt2.setString(1, gch.getMaGV());
                stmt2.setDouble(2, gch.getHeSoLuong());
                stmt2.setInt(3, gch.getNamBatDau());
                stmt2.executeUpdate();
            } else if (gv instanceof GiangVienThinhGiang) {
                GiangVienThinhGiang gtg = (GiangVienThinhGiang) gv;
                String sql2 = "INSERT INTO GiangVienThinhGiang (MaGV, NoiCongTac) VALUES (?, ?)";
                PreparedStatement stmt2 = conn.prepareStatement(sql2);
                stmt2.setString(1, gtg.getMaGV());
                stmt2.setString(2, gtg.getNoiCongTac());
                stmt2.executeUpdate();
            }

            System.out.println("✔ Thêm giảng viên thành công.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Lấy toàn bộ danh sách giảng viên
    public List<GiangVien> layTatCaGiangVien() {
        List<GiangVien> danhSach = new ArrayList<>();
        try (Connection conn = DBConection.getConnection()) {
            String sql = """
                    SELECT gv.*, gch.HeSoLuong, gch.NamBatDau, gtg.NoiCongTac
                    FROM GiangViens gv
                    LEFT JOIN GiangVienCoHuu gch ON gv.MaGV = gch.MaGV
                    LEFT JOIN GiangVienThinhGiang gtg ON gv.MaGV = gtg.MaGV
                    """;

            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String maGV = rs.getString("MaGV");
                String hoTen = rs.getString("HoTen");
                int namSinh = rs.getInt("NamSinh");
                String trinhDo = rs.getString("TrinhDo");
                int soNamCongTac = rs.getInt("SoNamCongTac");
                String loai = rs.getString("LoaiGV");

                if ("CoHuu".equalsIgnoreCase(loai)) {
                    double heSoLuong = rs.getDouble("HeSoLuong");
                    int namBatDau = rs.getInt("NamBatDau");
                    GiangVienCoHuu gch = new GiangVienCoHuu(maGV, hoTen, namSinh, trinhDo, soNamCongTac, heSoLuong, namBatDau);
                    danhSach.add(gch);
                } else if ("ThinhGiang".equalsIgnoreCase(loai)) {
                    String noiCongTac = rs.getString("NoiCongTac");
                    GiangVienThinhGiang gtg = new GiangVienThinhGiang(maGV, hoTen, namSinh, trinhDo, soNamCongTac, noiCongTac);
                    danhSach.add(gtg);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return danhSach;
    }

    // Xóa giảng viên theo mã
    public void xoaGiangVien(String maGV) {
        try (Connection conn = DBConection.getConnection()) {
            // Xóa chi tiết trước
            PreparedStatement stmt1 = conn.prepareStatement("DELETE FROM GiangVienCoHuu WHERE MaGV = ?");
            stmt1.setString(1, maGV);
            stmt1.executeUpdate();

            PreparedStatement stmt2 = conn.prepareStatement("DELETE FROM GiangVienThinhGiang WHERE MaGV = ?");
            stmt2.setString(1, maGV);
            stmt2.executeUpdate();

            // Xóa chính giảng viên
            PreparedStatement stmt3 = conn.prepareStatement("DELETE FROM GiangViens WHERE MaGV = ?");
            stmt3.setString(1, maGV);
            stmt3.executeUpdate();

            System.out.println("✔ Đã xóa giảng viên mã: " + maGV);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Tìm giảng viên theo mã
    public GiangVien timGiangVienTheoMa(String maGV) {
        try (Connection conn = DBConection.getConnection()) {
            String sql = """
                    SELECT gv.*, gch.HeSoLuong, gch.NamBatDau, gtg.NoiCongTac
                    FROM GiangViens gv
                    LEFT JOIN GiangVienCoHuu gch ON gv.MaGV = gch.MaGV
                    LEFT JOIN GiangVienThinhGiang gtg ON gv.MaGV = gtg.MaGV
                    WHERE gv.MaGV = ?
                    """;

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, maGV);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String hoTen = rs.getString("HoTen");
                int namSinh = rs.getInt("NamSinh");
                String trinhDo = rs.getString("TrinhDo");
                int soNamCongTac = rs.getInt("SoNamCongTac");
                String loai = rs.getString("LoaiGV");

                if ("CoHuu".equalsIgnoreCase(loai)) {
                    double heSoLuong = rs.getDouble("HeSoLuong");
                    int namBatDau = rs.getInt("NamBatDau");
                    return new GiangVienCoHuu(maGV, hoTen, namSinh, trinhDo, soNamCongTac, heSoLuong, namBatDau);
                } else if ("ThinhGiang".equalsIgnoreCase(loai)) {
                    String noiCongTac = rs.getString("NoiCongTac");
                    return new GiangVienThinhGiang(maGV, hoTen, namSinh, trinhDo, soNamCongTac, noiCongTac);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void capNhatMonHoc(String maGV, List<MonHoc> dsMon) {
        String xoaCu = "DELETE FROM GiangVien_MonHoc WHERE maGV = ?";
        String chenMoi = "INSERT INTO GiangVien_MonHoc(maGV, maMon) VALUES(?, ?)";

        try (Connection conn = DBConection.getConnection()) {
            conn.setAutoCommit(false); // Bắt đầu transaction

            try (PreparedStatement stmtXoa = conn.prepareStatement(xoaCu)) {
                stmtXoa.setString(1, maGV);
                stmtXoa.executeUpdate();
            }

            try (PreparedStatement stmtChen = conn.prepareStatement(chenMoi)) {
                for (MonHoc mon : dsMon) {
                    stmtChen.setString(1, maGV);
                    stmtChen.setString(2, mon.getTenMon()); // đúng rồi!
                    stmtChen.executeUpdate();
                }
            }

            conn.commit();
            System.out.println("✔ Đã cập nhật môn học cho giảng viên " + maGV);
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (DBConection.getConnection() != null) {
                    DBConection.getConnection().rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

}
