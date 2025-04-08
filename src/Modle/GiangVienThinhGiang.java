package Modle;

public class GiangVienThinhGiang extends GiangVien {
    private String noiCongTac;

    public GiangVienThinhGiang(String maGV, String hoTen, int namSinh, String trinhDo, int soNamCongTac,
                               String noiCongTac) {
        super(maGV, hoTen, namSinh, trinhDo, soNamCongTac);
        this.noiCongTac = noiCongTac;
    }

    public String getNoiCongTac() {
        return noiCongTac;
    }

    public void setNoiCongTac(String noiCongTac) {
        this.noiCongTac = noiCongTac;
    }
}
