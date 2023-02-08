package com.info;

public class BorrowBook {
    private String bookno;
    private String bookname;
    private String auther;
    private String booktype;
    private String publisher;
    private double price;
    private String borrowtime;

    public String getBookno() {
        return bookno;
    }

    public void setBookno(String bookno) {
        this.bookno = bookno;
    }

    public  String getBookname() {
        return bookname;
    }

    public  void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public  String getAuther() {
        return auther;
    }

    public void setAuther(String auther) {
        this.auther = auther;
    }

    public  String getBooktype() {
        return booktype;
    }

    public  void setBooktype(String booktype) {
        this.booktype = booktype;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getBorrowtime() {
        return borrowtime;
    }

    public void setBorrowtime(String borrowtime) {
        this.borrowtime = borrowtime;
    }
}
