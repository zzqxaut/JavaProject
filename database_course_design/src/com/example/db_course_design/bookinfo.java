package com.example.db_course_design;

public class bookinfo {
    private String bookname;
    private String auther;
    private String booktype;
    private String publisher;
    private int booknum;
    private double price;
    private String introduction;
    private int is_borrow;

    public bookinfo() {

    }

    public bookinfo(String bookname, String auther, String booktype, String publisher, int booknum, double price, String introduction, int is_borrow) {
        this.bookname = bookname;
        this.auther = auther;
        this.booktype = booktype;
        this.publisher = publisher;
        this.booknum = booknum;
        this.price = price;
        this.introduction = introduction;
        this.is_borrow = is_borrow;
    }

    public String getBookname() {
        return bookname;
    }
    public String getAuther(){return auther; }

    public String getBooktype() {
        return booktype;
    }

    public String getPublisher() {
        return publisher;
    }

    public int getBooknum() {
        return booknum;
    }

    public double getPrice() {
        return price;
    }
    public String getIntroduction() {
        return introduction;
    }
    public int getIs_borrow(){
        return is_borrow;
    }
    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public void setAuther(String auther) {
        this.auther = auther;
    }

    public void setBooktype(String booktype) {
        this.booktype = booktype;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setBooknum(int booknum) {
        this.booknum = booknum;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public void setIs_borrow(int is_borrow) {
        this.is_borrow = is_borrow;
    }
}
