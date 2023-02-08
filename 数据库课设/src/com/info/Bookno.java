package com.info;

public class Bookno {
    private String bookno;
    private String bookname;
    private int state;  //0已借出

    public int getState() {
        return state;
    }

    public String getBookname() {
        return bookname;
    }

    public String getBookno() {
        return bookno;
    }

    public void setBookno(String bookno) {
        this.bookno = bookno;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public void setState(int state) {
        this.state = state;
    }
}
