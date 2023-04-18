package com.mybatis.poji;

/**
 * 属性并不等于成员变量，getset方法中的才是属性，未设置成员变量的属性也能通过getset访问
 */


public class User {
    private Integer id;
    private String username;
    private String password;
    private String truename;
    private String usertype;
    private String email;
    private String phonenumber;
    private double balance;
    private Integer borrownum;

    public User(){}

    public User(Integer id, String username, String password, String truename, String usertype,
                String email, String phonenumber, double balance, Integer borrownum ) {
        this.username = username;
        this.password = password;
        this.truename = truename;
        this.usertype = usertype;
        this.email = email;
        this.phonenumber = phonenumber;
        this.balance = balance;
        this.id = id;
        this.borrownum = borrownum;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTruename() {
        return truename;
    }

    public void setTruename(String truename) {
        this.truename = truename;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getBorrownum() {
        return borrownum;
    }

    public void setBorrownum(Integer borrownum) {
        this.borrownum = borrownum;
    }
    public int getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +" id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", truename='" + truename + '\'' +
                ", usertype='" + usertype + '\'' +
                ", email='" + email + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                ", balance=" + balance +
                ", borrownum=" + borrownum +
                '}';
    }
}
