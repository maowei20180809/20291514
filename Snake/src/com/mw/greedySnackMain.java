package com.mw;
import javax.swing.JFrame;

public class greedySnackMain extends JFrame {
    snackWin snackwin;
    static final int Width = 800 , Height = 600 , LocX = 200 , LocY = 80;

    public greedySnackMain() {
        super("GreedySncak_SL");
        snackwin = new snackWin();
        //��JFrame��������������������
        add(snackwin);
        //��������Ĵ�С
        this.setSize(Width, Height);
        //��������Ŀɼ���
        this.setVisible(true);
        //���������λ��
        this.setLocation(LocX, LocY);
        //snackwin.requestFocus();
    }

    public static void main(String[] args) {
        new greedySnackMain();
    }
}
