package Modle.Repository;

import Modle.GiangVien;
import Modle.GiangVienCoHuu;
import Modle.GiangVienThiGiang;
import Modle.MonHoc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GiangVienDAO {
    public List<GiangVien> getAll() {
        List<GiangVien> list = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM GiangVien";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String loai = rs.getString("loai");
                String ma = rs.getString("maGV");
                String ten = rs.getString("hoTen");
                int namSinh = rs.getInt("namSinh");
                int soNamCongTac = rs.getInt("soNamCongTac");
                String trinhDo = rs.getString("trinhDo");

                List<MonHoc> danhSachMonHoc = new ArrayList<>(); // chưa load từ DB

                if ("cohuu".equalsIgnoreCase(loai)) {
                    float heSoLuong = rs.getFloat("heSoLuong");
                    int namBatDau = rs.getInt("namBatDauCongTac");

                    list.add(new GiangVienCoHuu(
                            danhSachMonHoc, ten, ma, namSinh, soNamCongTac, trinhDo, heSoLuong, namBatDau
                    ));
                } else if ("thinhgiang".equalsIgnoreCase(loai)) {
                    String noiCongTac = rs.getString("noiCongTac");

                    list.add(new GiangVienThiGiang(
                            danhSachMonHoc, ten, ma, namSinh, soNamCongTac, trinhDo, noiCongTac
                    ));
                }
            }

            rs.close();
            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public boolean insertGiangVien(GiangVien gv) {
        String sql = "INSERT INTO GiangVien(maGV, hoTen, namSinh, loai, soNamCongTac, trinhDo, heSoLuong, namBatDauCongTac, noiCongTac) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, gv.getMaGV());
            stmt.setString(2, gv.getHoTen());
            stmt.setInt(3, gv.getNamSinh());

            stmt.setInt(5, gv.getSoNamCongTac());
            stmt.setString(6, gv.getTrinhDo());

            if (gv instanceof GiangVienCoHuu ch) {
                stmt.setString(4, "cohuu");
                stmt.setFloat(7, ch.getHeSoLuong());
                stmt.setInt(8, ch.getNamBatDauCongTac());
                stmt.setNull(9, java.sql.Types.VARCHAR);
            } else if (gv instanceof GiangVienThiGiang tg) {
                stmt.setString(4, "thinhgiang");
                stmt.setNull(7, java.sql.Types.FLOAT);
                stmt.setNull(8, java.sql.Types.INTEGER);
                stmt.setString(9, tg.getNoiCongTac());
            }

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    public boolean insertMonHocChoGiangVien(String maGV, String tenMon) {
        String layIdMon = "SELECT id FROM MonHoc WHERE tenMon = ?";
        String insert = "INSERT INTO GiangVien_MonHoc(maGV, idMonHoc) VALUES (?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt1 = conn.prepareStatement(layIdMon);
             PreparedStatement stmt2 = conn.prepareStatement(insert)) {

            // Lấy ID môn học từ tên môn
            stmt1.setString(1, tenMon);
            ResultSet rs = stmt1.executeQuery();

            if (rs.next()) {
                int idMon = rs.getInt("id");

                // Chèn vào bảng liên kết
                stmt2.setString(1, maGV);
                stmt2.setInt(2, idMon);

                int rows = stmt2.executeUpdate();
                return rows > 0;
            } else {
                System.out.println("Không tìm thấy môn học: " + tenMon);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    public List<MonHoc> getMonHocTheoGiangVien(String maGV) {
        List<MonHoc> list = new ArrayList<>();
        String sql = "SELECT m.maMon, m.tenMon, m.soTinChi " +
                "FROM MonHoc m JOIN GiangVien_MonHoc gm ON m.maMon = gm.maMon " +
                "WHERE gm.maGV = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, maGV);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String tenMon = rs.getString("tenMon");
                int soTinChi = rs.getInt("soTinChi");
                list.add(new MonHoc(soTinChi, tenMon));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

}
