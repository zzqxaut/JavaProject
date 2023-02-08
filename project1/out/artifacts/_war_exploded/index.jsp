<%@ page import="com.test.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.net.URLDecoder" %>
<%@ page pageEncoding="UTF-8" import = "java.sql.*"%>
<html>
<head>
  <meta charset="utf-8">
  <title>登录</title>
  <link rel="stylesheet" type="text/css" href="css.css"/>

  <script>

    function check()
    {
      var un = document.getElementById("username").value;
      var yzm = document.getElementById("yzm").value;
      var pw = document.getElementById("password");
      if(un==""){
        alert("用户名不能为空！")
        return false;
      }
      // if(un!="zzq"){
      //   alert("用户名错误！")
      //   return false;
      // }
      if(pw.value == ""){
        alert("密码不能为空！")
        pw.focus();
        return false;
      }
      // if(pw1!="12345"){
      //   alert("密码错误！")
      //   return false;
      // }
      if(yzm==""){
        alert("验证码不能为空！")
        return false;
      }
      if(yzm!="xd52"){
        alert("验证码错误！")
        return false;
      }
      //alert("登陆成功！");
      postlist.submit();  //提交
      return true;
    }
  </script>

</head>

<body>
<%
  String username="";
  String password="";
  Cookie[] cookies = request.getCookies();//从request中获得Cookie对象的集合
  if(cookies!=null)
  {
    for (int i = 0; i < cookies.length; i++) { //遍历cookie对象的集合
      if (cookies[i].getName().equals("usernameCookie")) {//如果cookie对象的名称为mrCookie
        username = URLDecoder.decode(cookies[i].getValue().split("#")[0], "UTF-8");//获取用户名
        password = cookies[i].getValue().split("#")[1];//获取密码
      }
    }
  }
%>
<div class="login">
  <form action="LoginServlet" method="post" name="postlist" id="postlist">
    <table border="6" align="center">
      <caption style="font-size:24px;">
        <!-- 标题 -->
        <b>欢迎登陆</b>
      </caption>
      <tr>
        <td>用户名：</td>
        <td><input type="text" name="username" id="username" placeholder="请输入用户名" value=<%=username %>/></td>
      </tr>
      <tr>
        <td>密码：</td>
        <td><input type="password" name="password" id="password" placeholder="请输入密码" value=<%=password %>/></td>
      </tr>
      <tr>
        <td>用户类型：</td>
        <td>
          <input type="radio" name="usertype" id="student" value="学生" checked="true" />学生
          <input type="radio" name="usertype" id="teacher" value="教师" />教师
          <input type="radio" name="usertype" id="admin" value="管理员" />管理员
        </td>
      </tr>

      <tr>
        <td> 验证码：</td>
        <td><input type="text" name="yzm" id="yzm" value="" placeholder="请输入验证码" /><img src="yzm.png" alt="验证码"  /></td>
      </tr>
      <tr>
        <td><input type="reset" name="reset" id="reset" value="重置" /></td>
        <td><input type="submit" name="sub" id="sub" value="登录" onclick="check();"/>
          <a href="register.jsp"><input type="button" value="注册" /></a></td>
      </tr>
    </table>
  </form>
</div>

</body>

</html>
