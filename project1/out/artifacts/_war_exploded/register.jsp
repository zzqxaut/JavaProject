<%--
  Created by IntelliJ IDEA.
  com.User: user
  Date: 2022/9/26
  Time: 8:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.net.URLDecoder" %>
<html>
<head>
  <meta charset="utf-8">
  <title>注册</title>
  <link rel="stylesheet" type="text/css" href="http://jwgl.xaut.edu.cn/jsxsd/assets_sddx/css/xalgLogin.css">
  <link rel="stylesheet" type="text/css" href="css.css"/>

  <script src="http://code.jquery.com/jquery-1.4.1.min.js"></script>
  <script>
    function check_email(email) {
      var reg = /^\w+@[a-zA-Z0-9]{2,10}(?:\.[a-z]{2,3}){1,2}$/;
      return reg.test(email);
    }

    function check_phonenumber(phonenumber) {
      var reg = /^((0\d{2,3}-\d{7,8})|(1[345678]\d{9}))$/;
      return reg.test(phonenumber)
    }

    function check_password(password1, password) {
      return password1 == password;
    }

    function check() {
      var un = $("#username").val();
      var tn = $("#truename").val();
      var wp = document.getElementById("workplace").value;
      var yzm = document.getElementById("yzm").value;
      var pn = document.getElementById("phonenumber").value;
      var em = document.getElementById("email").value;
      var pw = document.getElementById("password").value;
      var pw1 = document.getElementById("password1").value;
      if (un == "") {
        alert("用户名不能为空！")
        return false;
      }
      if (tn == "") {
        alert("姓名不能为空！")
        return false;
      }
      if (pw1 == "") {
        alert("密码不能为空！")
        return false;
      }
      if (pn == "") {
        alert("手机号不能为空！")
        return false;
      }
      if (em == "") {
        alert("邮箱不能为空！")
        return false;
      }
      if (wp == "") {
        alert("所在单位不能为空！")
        return false;
      }
      if (yzm == "") {
        alert("验证码不能为空！")
        return false;
      }
      if (check_email(em) != true) {
        alert("邮箱格式错误");
        return false;
      }
      if (check_phonenumber(pn) != true) {
        alert("手机号格式错误");
        return false;
      }
      if (check_password(pw1, pw) != true) {
        alert("两次输入密码不相同！");
        return false;
      }
      if (yzm != "xd52") {
        alert("验证码错误！")
        return false;
      }
      postlist.submit();
      return true;
    }
  </script>
</head>

<body>
<div>
  <!-- <label align="" class="label" style="font-size: 24px;">欢迎注册</label> -->
  <form action="regsuccess.jsp" method="post" name="postlist" id="postlist">
    <table border="2" align="center">
      <caption style="font-size:24px;">
        <!-- 标题 -->
        <b>欢迎注册</b>
      </caption>
      <tr>
        <td>用户类型：</td>
        <td>
          <input type="radio" name="usertype" id="student" value="学生" checked="true"/>学生
          <input type="radio" name="usertype" id="teacher" value="教师"/>教师
          <input type="radio" name="usertype" id="admin" value="管理员"/>管理员
        </td>
      </tr>
      <tr>
        <td>用户名：</td>
        <td><input type="text" name="username" id="username" placeholder="请输入用户名"/></td>
      </tr>
      <tr>
        <td>姓名：</td>
        <td><input type="text" name="truename" id="truename" placeholder="请输入姓名"/></td>
      </tr>
      <tr>
        <td>设置密码：</td>
        <td><input type="password" name="password" id="password" placeholder="请输入密码"/></td>
      </tr>
      <tr>
        <td>确认密码：</td>
        <td><input type="password" name="password1" id="password1" placeholder="请再次输入密码"/></td>
      </tr>
      <tr>
        <td>手机号：</td>
        <td><input type="text" name="phonenumber" id="phonenumber" placeholder="请输入手机号"/></td>
      </tr>
      <tr>
        <td>邮箱：</td>
        <td><input type="email" name="email" id="email" placeholder="请输入邮箱"/></td>
      </tr>
      <tr>
        <td>所在单位：</td>
        <td><input type="text" name="workplace" id="workplace" placeholder="请输入所在单位"/></td>
      </tr>
      <tr>
        <td> 验证码：</td>
        <td><input type="text" name="yzm" id="yzm" value="" placeholder="请输入验证码"/><img src="yzm.png" alt="验证码"/></td>
      </tr>
      <tr>
        <td><input type="reset" name="reset" id="reset" value="重置"/></td>
        <td><input type="button" name="sub" id="sub" value="注册" onclick="return check();"></input>
        </td>
      </tr>
    </table>

  </form>
</div>
</body>
</html>
