package com.example.db_course_design;


public class ReturnRecordInfo {
    private String bookno;
    private String uesrname;
    private String bookname;
    private String returntime;
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
        return returntime;
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

    public void setReturntime(String borrowtime) {
        this.returntime = borrowtime;
    }
}
