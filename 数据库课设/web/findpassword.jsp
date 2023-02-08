<%--
  Created by IntelliJ IDEA.
  User: 寇佳威
  Date: 2022/12/5
  Time: 13:42
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
            height: 300px;
        }
    </style>
</head>
<body>
<%
    session.setAttribute("username", request.getParameter("username"));
    session.setAttribute("phonenumber", request.getParameter("phonenumber"));
%>

<form class="layui-form layui-form-pane" action="" id="findpasswordform" name="findpasswordform">
    <div class="wrap">
        <div class="Form">
            <br>
            <div class="layui-form-item layui-row header">
                <h1 class="layui-col-xs6 layui-col-xs-offset3" align="center">找回密码</h1>
            </div>
            <br>
            <div style="position: absolute; left: 50px">
                <div class="layui-form-item">
                    <label class="layui-form-label">密码</label>
                    <div class="layui-input-inline">
                        <input type="password" id="password1" lay-verify="pass" placeholder="请输入密码" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">确认密码</label>
                    <div class="layui-input-inline">
                        <input type="password" id="password2" lay-verify="pass" placeholder="请重复输入密码" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline" style="position: absolute; height: 19.2px">
                        <span id="inform" class="inform"></span>
                    </div>
                </div>
                <br>
                <div class="layui-form-item login_line8" align="center">
                    <input type="button" class="layui-btn cancel" value="上一步" id="last">
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <input type="button" class="layui-btn" lay-submit lay-filter="formDemo" value="确认" id="submit">
                </div>
            </div>
        </div>
    </div>
</form>

<script src="js/checkFindPassword.js" charset="utf-8"></script>
<script src="layui.js" charset="utf-8"></script>
</body>
</html>
