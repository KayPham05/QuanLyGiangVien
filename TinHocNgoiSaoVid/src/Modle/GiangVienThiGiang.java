package Modle;

import java.util.List;

public class GiangVienThiGiang extends GiangVien {
    private String noiCongTac;

    public GiangVienThiGiang(List<MonHoc> danhSachMonHoc, String hoTen, String maGV, int namSinh, int soNamCongTac, String trinhDo, String noiCongTac) {
        super(danhSachMonHoc, hoTen, maGV, namSinh, soNamCongTac, trinhDo);
        this.noiCongTac = noiCongTac;
    }

    public String getNoiCongTac() {
        return noiCongTac;
    }

    public void setNoiCongTac(String noiCongTac) {
        this.noiCongTac = noiCongTac;
    }
}
