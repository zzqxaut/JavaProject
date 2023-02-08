package com.test;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBC {
    public static Connection connection() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        // 获取连接
        String url = "jdbc:mysql://127.0.0.1:3306/dbmis"; //数据库名称
        String username = "root";
        String password = "111111";

        Connection conn = DriverManager.getConnection(url, username, password);

        return conn;
    }
}
