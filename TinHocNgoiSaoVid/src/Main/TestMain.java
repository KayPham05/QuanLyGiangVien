package Main;

import Controller.GiangVienController;
import Modle.Repository.GiangVienReposit;
import Modle.Repository.IGiangVienReposit;
import View.GiangVienView;

import javax.swing.*;

public class TestMain {
    public static void main(String[] args) {
        GiangVienView view = new GiangVienView();
        IGiangVienReposit reposit = new GiangVienReposit();
        new GiangVienController(reposit, view);

    }

}
