package View;

import Modle.GiangVien;
import Modle.GiangVienCoHuu;
import Modle.GiangVienThiGiang;
import Modle.MonHoc;
import Modle.Repository.GiangVienReposit;
import Modle.Repository.IGiangVienReposit;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GiangVienView extends JFrame {
    private JTextField txtHoTen, txtMaGV, txtNamSinh, txtSoNamCT, txtTrinhDo;
    private JTextField txtHeSoLuong, txtNamBDCT, txtNoiCongTac;
    private JTextField txtTenMon, txtSoTiet, txtTimKiem, txtLocNamSinh;
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton btnThemGV, btnThemMon, btnTimTheoTen, btnSapXep, btnThongKe, btnKiemTra50;
    private JButton btnSoSanhGV, btnDemTruoc1990, btnTimLonTuoi, btnLocThinhGiang, btnLamMoi;
    private JComboBox<String> cboLoaiGV;

    public GiangVienView() {
        setTitle("Quản lý Giảng viên");
        setSize(1000, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        add(initFormPanel(), BorderLayout.NORTH);
        add(initTablePanel(), BorderLayout.CENTER);
        add(initFunctionPanel(), BorderLayout.SOUTH);

        setVisible(true);
    }

    private JPanel initFormPanel() {
        JPanel formPanel = new JPanel(new GridLayout(3, 1));
        formPanel.setBorder(BorderFactory.createTitledBorder("Thông tin giảng viên"));

        // Row 1: Thông tin chung
        JPanel row1 = new JPanel(new GridLayout(2, 5, 10, 5));
        txtHoTen = new JTextField(); txtMaGV = new JTextField(); txtNamSinh = new JTextField();
        txtSoNamCT = new JTextField(); txtTrinhDo = new JTextField();
        cboLoaiGV = new JComboBox<>(new String[]{"Cơ Hữu", "Thỉnh Giảng"});

        row1.add(new JLabel("Họ tên:")); row1.add(txtHoTen);
        row1.add(new JLabel("Mã GV:")); row1.add(txtMaGV);
        row1.add(new JLabel("Năm sinh:")); row1.add(txtNamSinh);
        row1.add(new JLabel("Số năm CT:")); row1.add(txtSoNamCT);
        row1.add(new JLabel("Trình độ:")); row1.add(txtTrinhDo);
        row1.add(new JLabel("Loại GV:")); row1.add(cboLoaiGV);

        // Row 2: Thông tin riêng từng loại
        JPanel row2 = new JPanel(new GridLayout(1, 6, 10, 5));
        txtHeSoLuong = new JTextField(); txtNamBDCT = new JTextField(); txtNoiCongTac = new JTextField();

        row2.add(new JLabel("Hệ số lương:")); row2.add(txtHeSoLuong);
        row2.add(new JLabel("Năm bắt đầu CT:")); row2.add(txtNamBDCT);
        row2.add(new JLabel("Nơi công tác:")); row2.add(txtNoiCongTac);

        // Row 3: Thông tin môn học
        JPanel row3 = new JPanel(new GridLayout(1, 4, 10, 5));
        txtTenMon = new JTextField(); txtSoTiet = new JTextField();
        row3.add(new JLabel("Tên môn học:")); row3.add(txtTenMon);
        row3.add(new JLabel("Số tín chỉ:")); row3.add(txtSoTiet);

        formPanel.add(row1);
        formPanel.add(row2);
        formPanel.add(row3);
        return formPanel;
    }

    private JScrollPane initTablePanel() {
        tableModel = new DefaultTableModel(new String[]{
                "Mã GV", "Họ tên", "Năm sinh", "Loại", "Trình độ", "Số năm CT",
                "Hệ số lương", "Năm BĐ", "Nơi CT", "Môn học"
        }, 0);
        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        return new JScrollPane(table);
    }

    private JPanel initFunctionPanel() {
        JPanel funcPanel = new JPanel(new GridLayout(2, 1));

        JPanel row1 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        JPanel row2 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));

        // Dòng 1
        btnThemGV = new JButton("Thêm GV"); btnThemMon = new JButton("Thêm Môn"); btnLamMoi = new JButton("Làm mới");
        btnTimTheoTen = new JButton("Tìm theo tên"); txtTimKiem = new JTextField(10);
        btnSapXep = new JButton("Sắp xếp"); btnThongKe = new JButton("Thống kê NS");

        row1.add(btnThemGV); row1.add(btnThemMon); row1.add(btnLamMoi);
        row1.add(btnTimTheoTen); row1.add(txtTimKiem);
        row1.add(btnSapXep); row1.add(btnThongKe);

        // Dòng 2
        btnKiemTra50 = new JButton("GV > 50 tuổi (Cơ Hữu)");
        btnSoSanhGV = new JButton("So sánh loại GV"); btnDemTruoc1990 = new JButton("Đếm trước 1990");
        btnTimLonTuoi = new JButton("GV lớn tuổi nhất");
        btnLocThinhGiang = new JButton("Lọc Thỉnh Giảng"); txtLocNamSinh = new JTextField(5);

        row2.add(btnKiemTra50); row2.add(btnSoSanhGV);
        row2.add(btnDemTruoc1990); row2.add(btnTimLonTuoi);
        row2.add(btnLocThinhGiang); row2.add(new JLabel("Năm sinh:")); row2.add(txtLocNamSinh);

        funcPanel.add(row1);
        funcPanel.add(row2);
        return funcPanel;
    }

    // ==== Các phương thức chức năng giữ nguyên như bạn yêu cầu ==== //
    public JButton getBtnThemGV() { return btnThemGV; }
    public JButton getBtnThemMon() { return btnThemMon; }
    public JButton getBtnTimTheoTen() { return btnTimTheoTen; }
    public JButton getBtnSapXep() { return btnSapXep; }
    public JButton getBtnThongKe() { return btnThongKe; }
    public JButton getBtnKiemTra50() { return btnKiemTra50; }
    public JButton getBtnSoSanhGV() { return btnSoSanhGV; }
    public JButton getBtnDemTruoc1990() { return btnDemTruoc1990; }
    public JButton getBtnTimLonTuoi() { return btnTimLonTuoi; }
    public JButton getBtnLocThinhGiang() { return btnLocThinhGiang; }
    public JButton getBtnLamMoi() { return btnLamMoi; }

    public String getTxtTimKiem() { return txtTimKiem.getText(); }
    public JTextField getTxtLocNamSinh() { return txtLocNamSinh; }

    public GiangVien layThongTinTuForm() {
        try {
            String ma = txtMaGV.getText().trim();
            String ten = txtHoTen.getText().trim();
            int ns = Integer.parseInt(txtNamSinh.getText().trim());
            int soNam = Integer.parseInt(txtSoNamCT.getText().trim());
            String trinhDo = txtTrinhDo.getText().trim();
            List<MonHoc> dsMon = new ArrayList<>();
            String loai = (String) cboLoaiGV.getSelectedItem();

            if ("Cơ Hữu".equals(loai)) {
                float hsl = Float.parseFloat(txtHeSoLuong.getText().trim());
                int namBD = Integer.parseInt(txtNamBDCT.getText().trim());
                return new GiangVienCoHuu(dsMon, ten, ma, ns, soNam, trinhDo, hsl, namBD);
            } else {
                String noiCT = txtNoiCongTac.getText().trim();
                return new GiangVienThiGiang(dsMon, ten, ma, ns, soNam, trinhDo, noiCT);
            }
        } catch (Exception e) {
            return null;
        }
    }

    public MonHoc layMonHocTuForm() {
        try {
            String ten = txtTenMon.getText().trim();
            int soTiet = Integer.parseInt(txtSoTiet.getText().trim());
            return new MonHoc(soTiet, ten);
        } catch (Exception e) {
            return null;
        }
    }

    public GiangVien layGiangVienDangChon() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            String maGV = (String) tableModel.getValueAt(row, 0); // cột 0 là mã giảng viên
            IGiangVienReposit reposit = new GiangVienReposit();
            return reposit.timGiangVienTheoMa(maGV); // Truy xuất từ repository hoặc danh sách
        }
        return null;
    }

    public List<GiangVien> layHaiGiangVienDuocChon() {
        int[] selectedRows = table.getSelectedRows();
        List<GiangVien> danhSach = new ArrayList<>();

        if (selectedRows.length != 2) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn đúng 2 giảng viên để so sánh.");
            return danhSach;
        }

        IGiangVienReposit repository = new GiangVienReposit();

        for (int row : selectedRows) {
            String maGV = (String) tableModel.getValueAt(row, 0);
            GiangVien gv = repository.timGiangVienTheoMa(maGV);
            if (gv != null) danhSach.add(gv);
        }

        return danhSach;
    }
    public GiangVien layGiangVienThuNhat() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            String maGV = (String) tableModel.getValueAt(row, 0); // Lấy mã giảng viên từ cột 0
            IGiangVienReposit repository = new GiangVienReposit();
            return repository.timGiangVienTheoMa(maGV); // Truy từ danh sách hoặc DB
        }
        return null;
    }
    public GiangVien layGiangVienThuHai() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            String maGV = (String) tableModel.getValueAt(row, 0); // Lấy mã giảng viên từ cột 0
            IGiangVienReposit repository = new GiangVienReposit();
            return repository.timGiangVienTheoMa(maGV); // Truy từ danh sách hoặc DB
        }
        return null;
    }

    public void hienThiDanhSachGiangVien(List<GiangVien> ds) {
        tableModel.setRowCount(0);
        for (GiangVien gv : ds) {
            String loai = gv instanceof GiangVienCoHuu ? "Cơ Hữu" : "Thỉnh Giảng";
            String monHocStr = String.join(", ",
                    gv.getDanhSachMonHoc().stream().map(MonHoc::getTenMonHoc).toList());

            if (gv instanceof GiangVienCoHuu ch) {
                tableModel.addRow(new Object[]{
                        gv.getMaGV(), gv.getHoTen(), gv.getNamSinh(), loai, gv.getTrinhDo(),
                        gv.getSoNamCongTac(), ch.getHeSoLuong(), ch.getNamBatDauCongTac(), "", monHocStr
                });
            } else if (gv instanceof GiangVienThiGiang tg) {
                tableModel.addRow(new Object[]{
                        gv.getMaGV(), gv.getHoTen(), gv.getNamSinh(), loai, gv.getTrinhDo(),
                        gv.getSoNamCongTac(), "", "", tg.getNoiCongTac(), monHocStr
                });
            }
        }
    }

    public void hienThiThongKe(Map<Integer, Long> tk) {
        StringBuilder sb = new StringBuilder("Thống kê giảng viên theo năm sinh:\n");
        for (Map.Entry<Integer, Long> entry : tk.entrySet()) {
            sb.append("Năm: ").append(entry.getKey()).append(" - Số lượng: ").append(entry.getValue()).append("\n");
        }
        JOptionPane.showMessageDialog(this, sb.toString());
    }

    public void clearForm() {
        txtHoTen.setText(""); txtMaGV.setText(""); txtNamSinh.setText(""); txtSoNamCT.setText(""); txtTrinhDo.setText("");
        txtHeSoLuong.setText(""); txtNamBDCT.setText(""); txtNoiCongTac.setText("");
        txtTenMon.setText(""); txtSoTiet.setText("");
    }

    public int layNamSinhLoc() throws NumberFormatException {
        return Integer.parseInt(txtLocNamSinh.getText().trim());
    }

    public void showMessage(String s) {
        JOptionPane.showMessageDialog(this, s);
    }
}
