<%@ page import="java.net.URLDecoder" %>
<%@ page import="com.test.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="utf-8">
  <title>管理</title>
  <!-- 外部样式表必须由前两属性 -->
  <link rel="stylesheet" type="text/css" href="http://jwgl.xaut.edu.cn/jsxsd/assets_sddx/css/xalgLogin.css">
  <link rel="stylesheet" type="text/css" href="css.css"/>
  <script>
    // function del(){
    //   var b = confirm("是否确认删除？")
    //   if(b==true)
    //   {
    //     alert("信息已删除！")
    //   }
    //   else {
    //     return;
    //   }
    // }
    // function modify(){
    //   var b = confirm("是否确认修改？")
    //   if(b==true)
    //   {
    //     alert("信息已修改！")
    //   }
    //   else {
    //     return;
    //   }
    // }
    // function reset(){
    //   var b = confirm("是否确认重置密码？")
    //   if(b==true)
    //   {
    //     alert("密码已重置！")
    //   }
    //   else {
    //     return;
    //   }
    // }
  </script>
</head>

<body>
<%
  String username = (String) session.getAttribute("username"); //获取session内的对象
%>
<div>
  <div>
    <label style="font-size: 24px;">管理</label><br>
    <label style="font-size: 18px;">
      欢迎您！用户<%=username %>
    </label>
  </div>

  <div>
    <form action="Login1Servlet" method="get" name="" id="searchform">
      <input type="text" autocomplete="off" placeholder="用户名" id="sname"
             name="sname"> <input type="submit" name="operation" id="operation" value="查询" />
    </form>
  </div>
  <br>
  <form action="" method="get" name="" id="getlist" style="width: 1000px;">
    <table border="6" id="">
      <thead>
      <th>用户名</th>
      <th>姓名</th>
      <th>密码</th>
      <th>手机号</th>
      <th>邮箱</th>
      <th>所在单位</th>
      <th>用户类型</th>
      <th>操作</th>
      </thead>
      <% //新增部分
        // 获取图书信息集合
        ArrayList<User> list = (ArrayList<User>) request.getAttribute("list");
        // 判断集合是否有效
        if(list == null || list.size() < 1){
          System.out.println("集合无效！");
        }else{
          // 遍历图书集合中的数据
          for(User user : list){
      %>
      <tbody>
      <tr>
        <td><%=user.getUsername()%></td>
        <td><%=user.getTruename()%></td>
        <td><%=user.getPassword()%></td>
        <td><%=user.getPhonenumber()%></td>
        <td><%=user.getEmail()%></td>
        <td><%=user.getWorkplace()%></td>
        <td><%=user.getUsertype()%></td>
        <td><a href="DeleteServlet?username=<%=user.getUsername()%>">删除</a>
          <a href="Update.jsp?username=<%=user.getUsername()%>">修改</a> </td>
      </tr>
      <%
          }
        }
      %>
      </tbody>
    </table>
  </form>

</div>
</body>
</html>