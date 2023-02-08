package com.test;

import javax.swing.*;

public class MyJTextArea extends JTextArea {
    private int sign;

    public MyJTextArea() {
    }

    public MyJTextArea(int sign) {
        this.sign = sign;
    }

    public int getSign() {
        return sign;
    }

    public void setSign(int sign) {
        this.sign = sign;
    }
}
