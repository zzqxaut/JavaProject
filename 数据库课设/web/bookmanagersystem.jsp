

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>图书管理员页面</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="css/layui.css" media="all">
    <link rel="stylesheet" href="css/book_css.css" media="all">
    <link rel="stylesheet" href="css/login_css.css" media="all">
    <script src="http://code.jquery.com/jquery-2.0.0.min.js"></script>
</head>
<body>
<%
    String truename = (String) session.getAttribute("truename");
    String username = (String) session.getAttribute("username");
%>
<div class="container">
    <div class="title">
        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
            <legend>欢迎您，管理员<%=truename%></legend>
        </fieldset>
    </div>
    <div class="nav">
        <div class="layui-tab layui-tab-brief" lay-filter="demo">
            <div class="layui-tab-content">
                <div class="layui-tab-item layui-show">
                    <div class="layui-carousel" id="test1">
                        <div carousel-item>
                            <div><p class="layui-bg-green demo-carousel" style="font-size: 50px; letter-spacing:15px">静心吸吮知识源泉，更显真才实学本色</p></div>
                            <div><p class="layui-bg-red demo-carousel" style="font-size: 50px; letter-spacing:15px">书中自有黄金屋，书中自有颜如玉</p></div>
                        </div>
                    </div>
                </div>
                <div class="layui-tab-item">
                    <div id="laydateDemo"></div>
                </div>
            </div>
        </div>
    </div>
    <div class="content">
        <div class="demo1">
            <ul class="layui-nav layui-nav-tree layui-inline" lay-filter="demo" style="margin-right: 10px;height:700px;float:left;" data-anim="layui-anim-down" >
                <li class="layui-nav-item">
                    <a>通知<span class="layui-badge-dot"></span></a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" onclick="clickInform()">历史</a></dd>
                        <dd><a href="javascript:;" onclick="clickPublishInform()">发布通知</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a>个人信息</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" onclick="clickUserInfo()">查看个人信息</a></dd>
                        <dd><a href="javascript:;" onclick="clickAlertInform()">修改个人信息</a></dd>
                        <dd><a href="javascript:;" onclick="clickCharge()">余额充值</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item layui-nav-itemed">
                    <a>书籍管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" onclick="clickBookList()">借阅书籍</a></dd>
                        <dd><a href="javascript:;" onclick="clickReturnBook()">归还书籍</a></dd>
                        <dd><a href="javascript:;" onclick="clickAddNewBook()">添加书籍</a></dd>
                        <dd><a href="javascript:;" onclick="clickDeleteBook()">删除书籍</a></dd>
                        <dd><a href="javascript:;" onclick="clickCheckBorrowBookRecord()">查看借书记录</a></dd>
                        <dd><a href="javascript:;" onclick="clickCheckReturnBookRecord()">查看还书记录</a></dd>
                    </dl>
                </li>
            </ul>
        </div>

        <div class="demo2" id="demo2">

        </div>
    </div>
</div>


<script src="js/catalog.js" charset="utf-8"></script>
<script src="layui.js" charset="utf-8"></script>
<script>


    var username = <%=username%>;

    layui.config({
        version: '1632428048355' //为了更新 js 缓存，可忽略
    });

    //加载模块
    layui.use(function(){ //亦可加载特定模块：layui.use(['layer', 'laydate', function(){

        //得到各种内置组件
        var layer = layui.layer //弹层
            ,laypage = layui.laypage //分页
            ,laydate = layui.laydate //日期
            ,table = layui.table //表格
            ,carousel = layui.carousel //轮播
            ,element = layui.element //元素操作

        //监听Tab切换
        element.on('tab(demo)', function(data){
            layer.tips('切换了 '+ data.index +'：'+ this.innerHTML, this, {
                tips: 1
            });
        });

        //执行一个轮播实例
        carousel.render({
            elem: '#test1'
            ,width: '100%' //设置容器宽度
            ,height: 200
            ,arrow: 'none' //不显示箭头
            ,anim: 'fade' //切换动画方式
        });


    });


</script>
</body>
</html>
