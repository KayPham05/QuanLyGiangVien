package View;

import Controller.GiangVienController;
import Modle.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class GiangVienView extends JFrame {
    private final GiangVienController controller = new GiangVienController();

    private JTextField txtMa, txtTen, txtNamSinh, txtTrinhDo, txtSoNamCT, txtHeSoLuong, txtNamBatDau, txtNoiCongTac;
    private JComboBox<String> cboLoaiGV;
    private JButton btnThem;
    private JTable table;
    private DefaultTableModel tableModel;
    private JPanel panelCoHuu, panelThinhGiang;
    private JTextField txtMonHoc;
    public GiangVienView() {
        setTitle("Quản lý Giảng Viên");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // --- PANEL NHẬP LIỆU ---
        JPanel pnlInput = new JPanel(new GridLayout(10, 2, 5, 5));
        txtMa = new JTextField();
        txtTen = new JTextField();
        txtNamSinh = new JTextField();
        txtTrinhDo = new JTextField();
        txtSoNamCT = new JTextField();
        txtMonHoc = new JTextField();
        cboLoaiGV = new JComboBox<>(new String[]{"Cơ Hữu", "Thỉnh Giảng"});
        cboLoaiGV.addActionListener(e -> switchPanel());

        // Panel Cơ Hữu
        panelCoHuu = new JPanel(new GridLayout(2, 2));
        txtHeSoLuong = new JTextField();
        txtNamBatDau = new JTextField();
        panelCoHuu.add(new JLabel("Hệ số lương:"));
        panelCoHuu.add(txtHeSoLuong);
        panelCoHuu.add(new JLabel("Năm bắt đầu:"));
        panelCoHuu.add(txtNamBatDau);

        // Panel Thỉnh Giảng
        panelThinhGiang = new JPanel(new GridLayout(1, 2));
        txtNoiCongTac = new JTextField();
        panelThinhGiang.add(new JLabel("Nơi công tác:"));
        panelThinhGiang.add(txtNoiCongTac);

        pnlInput.add(new JLabel("Mã giảng viên:"));
        pnlInput.add(txtMa);
        pnlInput.add(new JLabel("Họ tên:"));
        pnlInput.add(txtTen);
        pnlInput.add(new JLabel("Năm sinh:"));
        pnlInput.add(txtNamSinh);
        pnlInput.add(new JLabel("Trình độ:"));
        pnlInput.add(txtTrinhDo);
        pnlInput.add(new JLabel("Số năm công tác:"));
        pnlInput.add(txtSoNamCT);
        pnlInput.add(new JLabel("Môn học đảm nhận:"));
        pnlInput.add(txtMonHoc);
        pnlInput.add(new JLabel("Loại giảng viên:"));
        pnlInput.add(cboLoaiGV);
        pnlInput.add(panelCoHuu);
        pnlInput.add(panelThinhGiang);

        btnThem = new JButton("Thêm giảng viên");
        btnThem.addActionListener(e -> themGiangVien());
        pnlInput.add(btnThem);

        add(pnlInput, BorderLayout.NORTH);

        // --- BẢNG HIỂN THỊ ---
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(new String[]{"Mã", "Tên", "Năm sinh", "Trình độ", "Số năm CT", "Loại", "Chi tiết"});
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        loadData();
        switchPanel();
        setVisible(true);
    }

    private void switchPanel() {
        String selected = (String) cboLoaiGV.getSelectedItem();
        panelCoHuu.setVisible("Cơ Hữu".equalsIgnoreCase(selected));
        panelThinhGiang.setVisible("Thỉnh Giảng".equalsIgnoreCase(selected));
    }

    private void themGiangVien() {
        try {
            String ma = txtMa.getText().trim();
            String ten = txtTen.getText().trim();
            int namSinh = Integer.parseInt(txtNamSinh.getText().trim());
            String trinhDo = txtTrinhDo.getText().trim();
            int soNamCT = Integer.parseInt(txtSoNamCT.getText().trim());
            String loai = (String) cboLoaiGV.getSelectedItem();
            String tenMon = txtMonHoc.getText().trim();
            MonHoc mon = new MonHoc(tenMon);
            GiangVien gv = null;
            gv.getDanhSachMonHoc().add(mon);
            if ("Cơ Hữu".equalsIgnoreCase(loai)) {
                double heSoLuong = Double.parseDouble(txtHeSoLuong.getText().trim());
                int namBD = Integer.parseInt(txtNamBatDau.getText().trim());
                GiangVienCoHuu ch = new GiangVienCoHuu(ma, ten, namSinh, trinhDo, soNamCT);
                ch.setHeSoLuong(heSoLuong);
                ch.setNamBatDau(namBD);
                gv = ch;
            } else {
                String noiCongTac = txtNoiCongTac.getText().trim();
                gv = new GiangVienThinhGiang(ma, ten, namSinh, trinhDo, soNamCT, noiCongTac);
            }

            controller.layTatCaGiangVien().add(gv);
            controller.themGiangVien(ma, ten, namSinh, trinhDo, soNamCT, loai);
            loadData();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi nhập liệu: " + ex.getMessage());
        }
    }

    private void loadData() {
        tableModel.setRowCount(0);
        for (GiangVien gv : controller.layTatCaGiangVien()) {
            String loai = gv instanceof GiangVienCoHuu ? "Cơ Hữu" : "Thỉnh Giảng";
            String chiTiet = "";
            if (gv instanceof GiangVienCoHuu ch) {
                chiTiet = "HSL: " + ch.getHeSoLuong() + ", NBD: " + ch.getNamBatDau();
            } else if (gv instanceof GiangVienThinhGiang tg) {
                chiTiet = "Nơi CT: " + tg.getNoiCongTac();
            }

            String tenMonHoc = gv.getDanhSachMonHoc().stream()
                    .map(MonHoc::getTenMon)
                    .reduce("", (s1, s2) -> s1 + (s1.isEmpty() ? "" : ", ") + s2);

            chiTiet += " | Môn: " + tenMonHoc;
            tableModel.addRow(new Object[]{
                    gv.getMaGV(), gv.getHoTen(), gv.getNamSinh(),
                    gv.getTrinhDo(), gv.getSoNamCongTac(), loai, chiTiet
            });
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GiangVienView::new);
    }
}
