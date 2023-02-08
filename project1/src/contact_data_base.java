//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.sql.*;
//import static java.lang.System.out;
//
//public class contact_data_base {
//
//  try {
//        // 加载数据库驱动，注册到驱动管理器
//        Class.forName("com.mysql.jdbc.Driver");
//        // 数据库连接字符串
//        String url = "jdbc:mysql://localhost:3306/db_database10?useUnicod=true&amp;characterEncoding=utf-8";
//        // 数据库用户名
//        String username1 = "zzq";
//        // 数据库密码
//        String password1 = "123456";
//        // 创建Connection连接
//        Connection conn = DriverManager.getConnection(url,username,password);
//        // 判断 数据库连接是否为空
//        if(conn != null){
//            // 输出连接信息
//            out.println("数据库连接成功！");
//            // 关闭数据库连接
//            conn.close();
//        }else{
//            // 输出连接信息
//            out.println("数据库连接失败！");
//        }
//    } catch (ClassNotFoundException e) {
//        e.printStackTrace();
//    } catch (SQLException e) {
//        e.printStackTrace();
//    }
//}
