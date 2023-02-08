<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder"%>
<%@ page import="java.net.URL" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="com.test.JDBC" %>
<%@ page import="com.test.User" %>
<%@ page import="java.sql.SQLException" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="utf-8">
  <title>注册信息</title>
  <link rel="stylesheet" type="text/css" href="http://jwgl.xaut.edu.cn/jsxsd/assets_sddx/css/xalgLogin.css">
  <link rel="stylesheet" type="text/css" href="css.css"/>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>

<body>

<% request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean id="user" class="com.test.User" >
  <jsp:setProperty name="user" property="*" />
</jsp:useBean>

<% //将注册信息保存到数据库
  try {
    Connection conn = JDBC.connection();
    // 添加图书信息的SQL语句
    String sql = "insert into user(username, password, truename, usertype, phonenumber, email, workplace) values(?,?,?,?,?,?,?)";
    // 获取PreparedStatement
    PreparedStatement ps = conn.prepareStatement(sql);

    // 对SQL语句中的第1个参数赋值
    ps.setString(1, user.getUsername());
    ps.setString(2, user.getPassword());
    ps.setString(3, user.getTruename());
    ps.setString(4, user.getUsertype());
    ps.setString(5, user.getPhonenumber());
    ps.setString(6, user.getEmail());
    ps.setString(7, user.getWorkplace());
    // 执行更新操作，返回所影响的行数
    int row = ps.executeUpdate();
    // 判断是否更新成功
    if(row > 0){
      // 更新成输出信息
      System.out.println("成功添加了 " + row + "条数据！");
    }

    // 关闭PreparedStatement，释放资源
    ps.close();
    // 关闭Connection，释放资源
    conn.close();
    ps.close();
  } catch (Exception e) {
    System.out.println("信息添加失败！");
    e.printStackTrace();
  }
%>

<table border="2" align="center" id="postlist">
  <caption style="font-size:24px;">
    <b>注册成功</b>
  </caption>
  <tr>
    <td>用户名：</td>
    <td><jsp:getProperty property="username" name="user"/></td>
  </tr>
  <tr>
    <td>姓名：</td>
    <td><jsp:getProperty property="truename" name="user"/></td>
  </tr>
  <tr>
    <td>手机号：</td>
    <td><jsp:getProperty property="phonenumber" name="user"/></td>
  </tr>
  <tr>
    <td>邮箱：</td>
    <td><jsp:getProperty property="email" name="user"/></td>
  </tr>
  <tr>
    <td>所在单位：</td>
    <td><jsp:getProperty property="workplace" name="user"/></td>
  </tr>
</table>
<h1>请核对您的信息，5秒后自动跳转到登陆界面！</h1>
<%
  response.setHeader("refresh","5;URL=http://localhost:8082/_war_exploded/index.jsp");
%>
</body>
</html>