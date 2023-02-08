<%--
  Created by IntelliJ IDEA.
  User: 寇佳威
  Date: 2022/12/5
  Time: 12:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>找回密码</title>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="css/layui.css"    media="all">
    <link rel="stylesheet" href="css/login_css.css" media="all">
    <script src="http://code.jquery.com/jquery-2.0.0.min.js"></script>
    <style>
        .Form{
            height: 350px;
        }
    </style>
</head>
<body>
<%
    String username = "";
    String phonenumber = "";
    if (session.getAttribute("username") != null && session.getAttribute("phonenumber") != null) {
        username = (String) session.getAttribute("username");
        phonenumber = (String) session.getAttribute("phonenumber");
    }
%>
<form class="layui-form layui-form-pane" action="" id="loginform" name="loginform">
    <div class="wrap">
        <div class="Form">
            <br>
            <div class="layui-form-item layui-row header">
                <h1 class="layui-col-xs6 layui-col-xs-offset3" align="center">找回密码</h1>
            </div>
            <br>
            <div style="position: absolute; left: 25px">
                <div class="layui-form-item">
                    <label class="layui-form-label">用户名</label>
                    <div class="layui-input-inline">
                        <input type="text" id="username" autocomplete="off" placeholder="请输入用户名" class="layui-input" value="<%=username%>">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">手机号码</label>
                    <div class="layui-input-inline">
                        <input type="text" id="phonenumber" autocomplete="off" placeholder="请输入绑定的手机号码" class="layui-input" value="<%=phonenumber%>">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">验证码</label>
                    <div class="layui-input-inline" style="width: 150px">
                        <input type="text" id="checkCode" lay-verify="pass" placeholder="请输入验证码" autocomplete="off" class="layui-input">
                    </div>
                    <img src="validate.jsp" id="picture" onclick="change()" id="picture"><!--点击验证码可以进行动态刷新  -->
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline" style="position: absolute; height: 19.2px">
                        <span id="inform" class="inform"></span>
                    </div>
                </div>
                <br>
                <div class="layui-form-item login_line8" align="center">
                    <input type="button" class="layui-btn cancel" value="取消" id="cancel">
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <input type="button" class="layui-btn" lay-submit lay-filter="formDemo" value="下一步" id="next" onclick="change();">
                </div>
            </div>
        </div>
    </div>
</form>

<script src="js/checkFindPassword.js" charset="utf-8"></script>
<script src="layui.js" charset="utf-8"></script>
<script src="js/randomNum.js" charset="utf-8"></script>
</body>
</html>
