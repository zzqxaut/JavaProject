package com.po;

public class Student {
    private int stuid;
    private String stuname;
    private String stusex;
    private String stunum;

    public int getStuid() {
        return stuid;
    }

    public void setStuid(int stuid) {
        this.stuid = stuid;
    }

    public String getStuname() {
        return stuname;
    }

    public void setStuname(String stuname) {
        this.stuname = stuname;
    }

    public String getStusex() {
        return stusex;
    }

    public void setStusex(String stusex) {
        this.stusex = stusex;
    }

    public String getStunum() {
        return stunum;
    }

    public void setStunum(String stunum) {
        this.stunum = stunum;
    }

    public Student() {
    }

    public Student(int stuid, String stuname, String stusex, String stunum) {
        this.stuid = stuid;
        this.stuname = stuname;
        this.stusex = stusex;
        this.stunum = stunum;
    }

    @Override
    public String toString() {
        return "Student{" +
                "stuid=" + stuid +
                ", stuname='" + stuname + '\'' +
                ", stusex='" + stusex + '\'' +
                ", stunum='" + stunum + '\'' +
                '}';
    }
}
