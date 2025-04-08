package Modle;

public class GiangVienCoHuu extends GiangVien {
    private double heSoLuong;
    private int namBatDau;

    // Constructor cơ bản (dùng khi thêm mới)
    public GiangVienCoHuu(String maGV, String hoTen, int namSinh, String trinhDo, int soNamCongTac) {
        super(maGV, hoTen, namSinh, trinhDo, soNamCongTac);
        this.heSoLuong = 2.34; // giá trị mặc định
        this.namBatDau = 0;     // giá trị mặc định
    }

    // Constructor đầy đủ (dùng khi lấy từ CSDL)
    public GiangVienCoHuu(String maGV, String hoTen, int namSinh, String trinhDo, int soNamCongTac,
                          double heSoLuong, int namBatDau) {
        super(maGV, hoTen, namSinh, trinhDo, soNamCongTac);
        this.heSoLuong = heSoLuong;
        this.namBatDau = namBatDau;
    }

    public double getHeSoLuong() {
        return heSoLuong;
    }

    public void setHeSoLuong(double heSoLuong) {
        this.heSoLuong = heSoLuong;
    }

    public int getNamBatDau() {
        return namBatDau;
    }

    public void setNamBatDau(int namBatDau) {
        this.namBatDau = namBatDau;
    }
}
