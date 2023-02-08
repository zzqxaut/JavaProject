<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder"%>
<%@ page import="java.net.URL" %>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">--%>
<html>
<head>
    <meta charset="utf-8">
    <title>登陆信息</title>
    <link rel="stylesheet" type="text/css" href="http://jwgl.xaut.edu.cn/jsxsd/assets_sddx/css/xalgLogin.css">
    <link rel="stylesheet" type="text/css" href="css.css"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<%
    String username = request.getParameter("username"); //获取用户填写的用户名
    String password = request.getParameter("password"); //获取用户填写的用户名
    session.setAttribute("username", username); //将用户名保存在session对象中

//    java.util.Date date = new java.util.Date();
//    java.text.SimpleDateFormat dtf = new java.text.SimpleDateFormat("yyyy-MM-dd-h:m:s");
//    String user = URLEncoder.encode(request.getParameter("username"), "UTF-8"); //获取用户名
    Cookie cookie = new Cookie("usernameCookie", username + "#" + password);
    cookie.setMaxAge(60 * 60 * 24 * 30); //设置cookie有效期30天
    response.addCookie(cookie); //保存cookie

    //response.setHeader("refresh","2;URL=http://localhost:8080/_war_exploded/manage.jsp");
    response.sendRedirect("LoginServlet");
%>

</body>
</html>