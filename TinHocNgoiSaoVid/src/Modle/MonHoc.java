package Modle;

public class MonHoc {
    private String tenMonHoc;
    private int soTinChi;

    public MonHoc(int soTinChi, String tenMonHoc) {
        this.soTinChi = soTinChi;
        this.tenMonHoc = tenMonHoc;
    }

    public String getTenMonHoc() {
        return tenMonHoc;
    }

    public void setTenMonHoc(String tenMonHoc) {
        this.tenMonHoc = tenMonHoc;
    }

    public int getSoTinChi() {
        return soTinChi;
    }

    public void setSoTinChi(int soTinChi) {
        this.soTinChi = soTinChi;
    }
}
