package com.example.db_course_design;

public class BorrowRecordInfo {
    private String bookno;
    private String uesrname;
    private String bookname;
    private String borrowtime;
    public String getBookno() {
        return bookno;
    }
    public String getUesrname() {
        return uesrname;
    }
    public String getBookname() {
        return bookname;
    }
    public String getBorrowtime() {
        return borrowtime;
    }

    public void setBookno(String bookno) {
        this.bookno = bookno;
    }

    public void setUesrname(String uesrname) {
        this.uesrname = uesrname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public void setBorrowtime(String borrowtime) {
        this.borrowtime = borrowtime;
    }
}
