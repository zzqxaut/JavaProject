
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update</title>
    <title>管理</title>
    <!-- 外部样式表必须由前两属性 -->
    <link rel="stylesheet" type="text/css" href="http://jwgl.xaut.edu.cn/jsxsd/assets_sddx/css/xalgLogin.css">
    <link rel="stylesheet" type="text/css" href="css.css"/>
</head>
<body>
<div id="wap">
    <form action="UpdateServlet" method="get/post" name="loginform" id="loginform">
        <table align="center">
            <tr align="center">
                <td><h2>修改信息</h2></td>
            </tr>
        </table>
        <table align="center" id="table">
            <tr>
                <td class="input-left" align="center">
                    当前用户名:
                </td>
                <td class="input-right">
                    <%=request.getParameter("username")%>
                    <input type="hidden" name="username"  value="<%=request.getParameter("username")%>">
                </td>
            </tr>
            <tr>
                <td class="input-left" align="center">
                    用户类型:
                </td>
                <td class="input-right">
                    <input type="radio" name="usertype" value="学生" id="student" checked="checked">学生
                    <input type="radio" name="usertype" value="教师" id="teacher">教师
                    <input type="radio" name="usertype" value="管理员" id="admire">管理员
                </td>
            </tr>
            <tr>
                <td class="input-left" align="center">
                    密&nbsp;&nbsp;码:
                </td>
                <td class="input-right">
                    <input type="password" name="password" id="password" minlength="6" maxlength="20">
                </td>
            </tr>
            <tr>
                <td class="input-left" align="center">
                    姓&nbsp;&nbsp;名:
                </td>
                <td class="input-right">
                    <input type="text" name="truename" id="truename">
                </td>
                <td><span id="inform4" class="inform"></span></td>
            </tr>
            <tr>
                <td class="input-left" align="center">
                    手机号码:
                </td>
                <td class="input-right">
                    <input type="text" name="phonenumber" id="phonenumber" minlength="11" maxlength="11">
                </td>
                <td><span id="inform5" class="inform"></span></td>
            </tr>
            <tr>
                <td class="input-left" align="center">
                    邮&nbsp;&nbsp;箱:
                </td>
                <td class="input-right">
                    <input type="email" name="email" id="email">
                </td>
                <td><span id="inform6" class="inform"></span></td>
            </tr>
            <tr>
                <td class="input-left" align="center">
                    所在单位:
                </td>
                <td class="input-right">
                    <input type="text" name="workplace" id="workplace">
                </td>
                <td><span id="inform7" class="inform"></span></td>
            </tr>
            <tr>
                <td>
                </td>
                <td>
                    <input type="submit" name="button" id="submit" value="确认">
                </td>
            </tr>
        </table>
    </form>
</div>
</div>
</body>
</html>
