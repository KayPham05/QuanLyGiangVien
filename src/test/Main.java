package test;

import Controller.GiangVienController;
import Modle.Repository.GiangVienDAO;
import Modle.Repository.GiangVienReposit;
import View.GiangVienView;

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            GiangVienView view = new GiangVienView();         // Khởi tạo View
            GiangVienController controller = new GiangVienController(); // Gắn Controller với View
            view.setVisible(true);                            // Hiển thị GUI
        });
    }
}
