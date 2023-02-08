<%--
  Created by IntelliJ IDEA.
  User: 寇佳威
  Date: 2022/12/4
  Time: 20:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="css/layui.css"    media="all">
    <link rel="stylesheet" href="css/login_css.css" media="all">
    <script src="http://code.jquery.com/jquery-2.0.0.min.js"></script>
    <style>
        .Form{
            height: 600px;
        }
    </style>
</head>
<body>
<form class="layui-form layui-form-pane" action="">
    <div class="wrap">
        <div class="Form">
            <br>
            <div class="layui-form-item layui-row header">
                <h1 class="layui-col-xs6 layui-col-xs-offset3" align="center">注册</h1>
            </div>
            <br>
            <div style="position: absolute; left: 25px">
                <div class="layui-form-item">
                    <label class="layui-form-label">用户名</label>
                    <div class="layui-input-inline">
                        <input type="text" id="username" autocomplete="off" placeholder="请输入用户名" class="layui-input">
                    </div>
                </div>
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
                    <label class="layui-form-label">姓名</label>
                    <div class="layui-input-inline">
                        <input type="text" id="truename" autocomplete="off" placeholder="请输入姓名" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">手机号码</label>
                    <div class="layui-input-inline">
                        <input type="text" id="phonenumber" autocomplete="off" placeholder="请输入手机号码" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">邮箱</label>
                    <div class="layui-input-inline">
                        <input type="email" id="email" autocomplete="off" placeholder="请输入邮箱" class="layui-input" maxlength="30">
                    </div>
                </div>
                <div class="layui-form-item" pane="">
                    <label class="layui-form-label">用户类型</label>
                    <div class="layui-input-block">
                        <input type="radio" name="usertype" value="管理员" title="管理员" id="admin">
                        <input type="radio" name="usertype" value="用户" title="用户" id="user" checked="">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">验证码</label>
                    <div class="layui-input-inline" style="width: 150px">
                        <input type="text" id="checkCode" lay-verify="pass" placeholder="请输入验证码" autocomplete="off" class="layui-input">
                    </div>
                    <img src="validate.jsp"  id="picture" onClick="change()" id="picture"><!--点击验证码可以进行动态刷新  -->
                </div>
                <div class="layui-form-item">
                    <span id="inform" class="inform" style="position: absolute; height: 19.2px"></span>
                </div>
                <br>
                <div class="layui-form-item login_line8" align="center">
                    <input type="button" class="layui-btn cancel" value="取消" id="cancel">
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <input type="button" class="layui-btn" lay-submit lay-filter="formDemo" value="确认" id="submit" onclick="change();">
                </div>
            </div>
        </div>
    </div>
</form>

<script src="js/checkRegister.js" charset="utf-8"></script>
<script src="layui.js" charset="utf-8"></script>
<script src="js/randomNum.js" charset="utf-8"></script>
<script>
    layui.use(['form', 'layedit', 'laydate'], function(){
    });
</script>
</body>
</html>
