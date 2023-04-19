package com.po;

public class User {
    private Integer uid;// 主键
    private String uname;
    private String usex;

    public User() {
    }

    public User(Integer uid, String uname, String usex) {
        this.uid = uid;
        this.uname = uname;
        this.usex = usex;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUsex() {
        return usex;
    }

    public void setUsex(String usex) {
        this.usex = usex;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", uname='" + uname + '\'' +
                ", usex='" + usex + '\'' +
                '}';
    }
}
