<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@page import="java.awt.image.BufferedImage"%>
<%@page import="java.awt.Color"%>
<%@page import="java.awt.Font"%>
<%@page import="javax.imageio.ImageIO"%>
<%@page import="java.util.Random"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>验证码</title>
</head>
<body>
<%
  BufferedImage image = new BufferedImage(80, 37, BufferedImage.TYPE_INT_RGB);
  //获取图片画笔
  java.awt.Graphics g = image.getGraphics();
  Random r = new Random();
  //设置画笔颜色
  g.setColor(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
  //绘制矩形背景
  g.fillRect(0, 0, 80, 37);
  //绘制n条干扰线
  for(int i=0;i<10;i++) {
    g.setColor(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
    g.drawLine(r.nextInt(80),r.nextInt(37),r.nextInt(80), r.nextInt(37));
  }

  String string = "QWERTYUIOPASDFGHJKLZXCVBNM0123456789";
  String number="";
  Random rd=new Random();
  for(int i=0;i<4;i++) {
    char c=string.charAt(rd.nextInt(string.length()));
    number=number+c;
  }

  session.setAttribute("checknumber", number);

  g.setFont(new Font(null,Font.ITALIC+Font.BOLD,24));
  g.drawString(number,5,25);
  response.setContentType("image/jpeg");
  ServletOutputStream sos = response.getOutputStream();
  ImageIO.write(image, "jpeg", sos);
  sos.close();
  out.clear();
  out = pageContext.pushBody();
%>
</body>
</html>