<%@ page import="com.example.db_course_design.bookno" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.db_course_design.bookinfo" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>Layui</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <link rel="stylesheet" href="css/layui.css"    media="all">
  <!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
  <style>
    body{margin: 10px;}
    .demo-carousel{height: 200px; line-height: 200px; text-align: center;}
  </style>
</head>
<body>

<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
  <legend>欢迎您，用户***</legend>
</fieldset>

<div>
  <div class="layui-tab layui-tab-brief" lay-filter="demo">
    <ul class="layui-tab-title">
      <li class="layui-this">演示说明</li>
      <li>日期</li>
    </ul>
    <div class="layui-tab-content">
      <div class="layui-tab-item layui-show">
        <div class="layui-carousel" id="test1">
          <div carousel-item>
            <div><p class="layui-bg-green demo-carousel">静心吸吮知识源泉，更显真才实学本色</p></div>
            <div><p class="layui-bg-red demo-carousel">书中自有黄金屋，书中自有颜如玉</p></div>
          </div>
        </div>
      </div>
      <div class="layui-tab-item">
        <div id="laydateDemo"></div>
      </div>
    </div>
  </div>

  <ul class="layui-nav layui-nav-tree layui-inline" lay-filter="demo" style="margin-right: 10px;height:500px;float:left;" data-anim="layui-anim-down" >
    <li class="layui-nav-item">
      <a href="">通知<span class="layui-badge-dot"></span></a>
    </li>
    <li class="layui-nav-item layui-nav-itemed">
      <a href="javascript:;">个人信息</a>
    </li>
    <li class="layui-nav-item  layui-nav-itemed">
      <a href="javascript:;">借阅书籍</a>
    </li>
    <li class="layui-nav-item"><a href="">已借书籍</a></li>
    <li class="layui-nav-item"><a href="">联系客服</a></li>
  </ul>

  <form class="layui-form" action="com/example/db_course_design/findbook" style="margin-left: 50px;margin-top: 300px;">
    <div class="layui-form-item">
      <label class="layui-form-label">查询书籍</label>
      <div class="layui-input-block">
        <input type="text" name="bookname" lay-verify="title" autocomplete="off" placeholder="请输入书名" class="layui-input" style="width:200px;float:left">
        <button type="submit" class="layui-btn" lay-submit="" lay-filter="demo1">查询</button>
      </div>
    </div>
  </form>

</div>

<%
  bookinfo book = (bookinfo)request.getAttribute("book");
%>
<form action="" method="get" name="" style="width: 1000px;">
  <table border="6" >
    <thead>
    <th>书籍名称</th>
    <th>书籍作者</th>
    <th>书籍类型</th>
    <th>书籍出版商</th>
    <th>数量</th>
    <th>价格</th>
    <th>书籍简介</th>
    </thead>
    <tbody>
    <tr>
      <td><%=book.getBookname()%></td>
      <td><%=book.getAuther()%></td>
      <td><%=book.getBooktype()%></td>
      <td><%=book.getPublisher()%></td>
      <td><%=book.getBooknum()%></td>
      <td><%=book.getPrice()%></td>
      <td><%=book.getIntroduction()%></td>
    </tr>
    </tbody>
  </table>
</form>
<form action="" method="get" name="" id="getlist" style="width: 1000px;">
  <table border="6" id="">
    <thead>
    <th>书籍编号</th>
    <th>书籍名称</th>
    <th>操作</th>
    </thead>
    <% //新增部分
      // 获取图书信息集合
      ArrayList<bookno> booklist = (ArrayList<bookno>) request.getAttribute("booklist");
      // 判断集合是否有效
      if(booklist == null || booklist.size() < 1){
        System.out.println("集合无效！");
      }else{
        // 遍历图书集合中的数据
        for(bookno book1 : booklist){
    %>
    <tbody>
    <tr>
      <td><%=book1.getBookno()%></td>
      <td><%=book1.getBookname()%></td>
      <td><td><a href="borrowedServlet?bookno=<%=book1.getBookno()%>">借阅</a></td>
<%--      <td><a href="DeleteServlet?username=<%=book.getUsername()%>">删除</a>--%>
<%--        <a href="Update.jsp?username=<%=book.getUsername()%>">修改</a> </td>--%>
    </tr>
    <%
        }
      }
    %>
    </tbody>
  </table>
</form>


<blockquote class="layui-elem-quote layui-hide layui-text" id="footer">西安理工大学图书馆</blockquote>
<script src="layui.js" charset="utf-8"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述 JS 路径需要改成你本地的 -->

<script>
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

    //将日期直接嵌套在指定容器中
    var dateIns = laydate.render({
      elem: '#laydateDemo'
      ,position: 'static'
      ,calendar: true //是否开启公历重要节日
      ,mark: { //标记重要日子
        '0-10-14': '生日'
        ,'2020-01-18': '小年'
        ,'2020-01-24': '除夕'
        ,'2020-01-25': '春节'
        ,'2020-02-01': '上班'
      }
      ,done: function(value, date, endDate){
        if(date.year == 2017 && date.month == 11 && date.date == 30){
          dateIns.hint('一不小心就月底了呢');
        }
      }
      ,change: function(value, date, endDate){
        layer.msg(value)
      }
    });


    //底部信息
    var footerTpl = lay('#footer')[0].innerHTML;
    lay('#footer').html(layui.laytpl(footerTpl).render({}))
            .removeClass('layui-hide');
  });
</script>
</body>
</html>