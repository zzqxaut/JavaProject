package com.test;

public class User {
    private String usertype;
    private String username;
    private String truename;
    private String password;
    private String phonenumber;
    private String email;
    private String workplace;

    public void setUsertype(String ut){
        usertype = ut;
    }
    public String getUsertype(){
        return usertype;
    }
    public void setUsername(String un){
        username = un;
    }
    public String getUsername(){
        return username;
    }
    public void setTruename(String tn){
        truename = tn;
    }
    public String getTruename(){
        return truename;
    }

    public void setPassword(String pw){
        password = pw;
    }
    public String getPassword(){
        return password;
    }

    public void setPhonenumber(String pn){
        phonenumber = pn;
    }
    public String getPhonenumber(){
        return phonenumber;
    }
    public void setEmail(String em){
        email = em;
    }
    public String getEmail(){
        return email;
    }
    public void setWorkplace(String wp){
        workplace  = wp;
    }
    public String getWorkplace(){
        return workplace;
    }
}
