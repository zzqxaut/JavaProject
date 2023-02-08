package com.example.db_course_design;


import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


public class jdbc {
    static String DBMS = "database";               // 数据库名
    static String tb_book = "book";                 // 书表
    static String tb_bookno = "book_no";            // 编号表
    static String tb_user = "user";                 // 用户表
    static String tb_borrow_book = "borrow_book";   // 借书表
    static String tb_return_book = "return_book";   // 还书表
    static long no = 20000;

    static String url = "jdbc:mysql://127.0.0.1:3306"+"/"+DBMS+"?useUnicode=true&characterEncoding=utf8";
    //?serverTimezone=UTC表示设置时区
    static String root = "root";                // 管理员账户
    static String pass = "111111";              // 管理员密码

    // 连接数据库
    public static Connection getConnection() throws Exception {

        Class.forName("com.mysql.cj.jdbc.Driver");//mysql8的jdbc8驱动，去掉cj为mysql5
        // 连接数据库
        Connection conn = DriverManager.getConnection(url, root, pass);
        // System.out.println("连接成功");
        return conn;
    }

    // 释放资源
    public static void closeResource(Connection conn, PreparedStatement ps) throws SQLException {
        ps.close();
        conn.close();
//        System.out.println("断开连接");
    }

    // 通过键值返回该用户信息
    public static userinfo search_user(String Key) throws Exception {
        Connection con = jdbc.getConnection();
        String sql = "select * from user where username=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1,Key);
        ResultSet rs = ps.executeQuery();
        userinfo user = new userinfo();
        if (rs.next()) {
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setTruename(rs.getString("truename"));
            user.setUsertype(rs.getString("usertype"));
            user.setPhonenumber(rs.getString("phonenumber"));
            user.setEmail(rs.getString("email"));
            user.setBalance(rs.getDouble("balance"));
            user.setBorrownum(rs.getInt("borrownum"));
        }
        rs.close();
        closeResource(con,ps);
        return user;
    }

    // 查找用户类型
    public static String search_usertype(String Key) throws Exception {
        String type = null;
        Connection con = jdbc.getConnection();
        String sql = "select usertype from user where username = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1,Key);
        ResultSet rs = ps.executeQuery();
        if(rs.next())
        {
            type = rs.getString("usertype");
        }
        closeResource(con,ps);
        return type;        // 返回数据库存储的密码
    }
    //删除用户
    public static int delete_user(String username)throws Exception {
        Connection con = jdbc.getConnection();
        String sql1 = "delete from user where username=?";
        String sql2 = "delete from borrow_record where username=?";
        String sql3 = "delete from return_record where username=?";
        PreparedStatement ps1 = con.prepareStatement(sql1);
        PreparedStatement ps2 = con.prepareStatement(sql2);
        PreparedStatement ps3 = con.prepareStatement(sql3);
        ps1.setString(1,username);
        ps2.setString(1,username);
        ps3.setString(1,username);
        int rs1 = ps1.executeUpdate();
        int rs2 = ps1.executeUpdate();
        int rs3 = ps1.executeUpdate();
        return rs1;
    }
    //删除书籍
    public static int delete_book(String bookname)throws Exception {
        Connection con = jdbc.getConnection();
        String sql1 = "update book set is_borrow=0 where bookname=?";
        String sql2 = "select count(bookname) sum from borrow_book where bookname=?;";
        String sql3 = "select count(bookname) sum from return_book where bookname=?;";
        PreparedStatement ps1 = con.prepareStatement(sql1);
        PreparedStatement ps2 = con.prepareStatement(sql2);
        PreparedStatement ps3 = con.prepareStatement(sql3);
        ps1.setString(1,bookname);
        ps2.setString(1,bookname);
        ps3.setString(1,bookname);
        int rs1 = ps1.executeUpdate();
        ResultSet rs2 = ps2.executeQuery();
        ResultSet rs3 = ps3.executeQuery();
        int num1=0,num2=0;
        if(rs2.next()){
            num1 = rs2.getInt("sum");
        }
        if(rs3.next()){
            num2 = rs3.getInt("sum");
        }
        if(num1 == num2){ //可以删除
            String s1 = "delete from book where bookname=?";
            String s2 = "delete from borrow_book where bookname=?";
            String s3 = "delete from return_book where bookname=?";
            String s4 = "delete from book_no where bookname=?";
            PreparedStatement p1 = con.prepareStatement(s1);
            PreparedStatement p2 = con.prepareStatement(s2);
            PreparedStatement p3 = con.prepareStatement(s3);
            PreparedStatement p4 = con.prepareStatement(s4);
            p1.setString(1,bookname);
            p2.setString(1,bookname);
            p3.setString(1,bookname);
            p4.setString(1,bookname);
            p1.executeUpdate();
            p2.executeUpdate();
            p3.executeUpdate();
            p4.executeUpdate();
            return 1;
        }
        else return 0;
    }
    //按书籍编号删除
    public static int delete_bookno(String bookno,String bookname)throws Exception {
        Connection con = jdbc.getConnection();
        String sql1 = "delete from book_no where bookno=? and state=1";
        PreparedStatement ps1 = con.prepareStatement(sql1);
        ps1.setString(1,bookno);
        int rs1 = ps1.executeUpdate();
        ps1.close();
        if(rs1 == 1) {
            String sql2 = "delete from borrow_book where bookno=?";
            String sql3 = "delete from return_book where bookno=?";
            String sql4 = "update book set booknum=booknum-1 where bookname=?";
            PreparedStatement p2 = con.prepareStatement(sql2);
            PreparedStatement p3 = con.prepareStatement(sql3);
            PreparedStatement p4 = con.prepareStatement(sql4);
            p2.setString(1, bookno);
            p3.setString(1, bookno);
            p4.setString(1, bookname);
            p2.executeUpdate();
            p3.executeUpdate();
            p4.executeUpdate();
            p2.close();
            p3.close();
            p4.close();
            con.close();
            return 1;
        }
        else {
            con.close();
            return 0;
        }
    }
    //查询书籍信息
    public static ArrayList<bookinfo> search_book(String Key) throws Exception {
        Connection con = jdbc.getConnection();
        String sql = "select * from book where bookname like ? and is_borrow=1;";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1,"%"+Key+"%");
        ResultSet rs = ps.executeQuery();
        ArrayList<bookinfo> bookl = new ArrayList<>();
        while (rs.next()) {
            bookinfo book = new bookinfo();
            book.setBookname(rs.getString("bookname"));
            book.setBooknum(rs.getInt("booknum"));
            book.setBooktype(rs.getString("booktype"));
            book.setPublisher(rs.getString("publisher"));
            book.setPrice(rs.getDouble("price"));
            book.setAuther(rs.getString("auther"));
            book.setIntroduction(rs.getString("introduction"));
            bookl.add(book);
        }
        rs.close();
        closeResource(con,ps);
        return bookl;
    }
    //查询剩余可借阅书籍
    public static ArrayList<bookno> search_bookno(String Key) throws Exception {
        Connection con = jdbc.getConnection();
        String sql = "select * from book_no where bookname=? and state=1";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1,Key);
        ResultSet rs = ps.executeQuery();
        ArrayList<bookno> booklist = new ArrayList<>();
        while (rs.next()){
            bookno book = new bookno();
            book.setBookno(rs.getString("bookno"));
            book.setBookname(rs.getString("bookname"));
            booklist.add(book);
        }
        rs.close();
        closeResource(con,ps);
        return booklist;
    }
    //查询用户的借书记录
    public static ArrayList<borrowBooks> search_record(String username) throws Exception {
        Connection con = jdbc.getConnection();
        String sql = "select * from borrow_book where username=?;";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1,username);
        ResultSet rs = ps.executeQuery();
        ArrayList<borrowBooks> borrowBookslist = new ArrayList<>();
        while (rs.next()) {
            borrowBooks book = new borrowBooks();
            book.setBookno(rs.getString("bookno"));
            book.setBookname(rs.getString("bookname"));
            book.setBorrowtime(rs.getString("borrowtime"));
            borrowBookslist.add(book);
        }
        rs.close();
        for(borrowBooks i:borrowBookslist){
            System.out.println(i.getBookno());
            String sql1 = "select * from book where bookname=?";
            PreparedStatement ps1 = con.prepareStatement(sql1);
            ps1.setString(1,i.getBookname());
            ResultSet rs1 = ps1.executeQuery();
            if(rs1.next()){
                i.setAuther(rs1.getString("auther"));
                i.setPublisher(rs1.getString("publisher"));
                i.setPrice(rs1.getDouble("price"));
                i.setBooktype(rs1.getString("booktype"));
            }
            System.out.println(i.getBookno());
        }
        con.close();
        return borrowBookslist;
    }
    //查询所有借书
    public static ArrayList<BorrowRecordInfo> searchBorrowRecord() throws Exception {
        Connection con = jdbc.getConnection();
        String sql = "select * from borrow_book;";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        ArrayList<BorrowRecordInfo> recordlist = new ArrayList<>();
        while (rs.next()) {
            BorrowRecordInfo record = new BorrowRecordInfo();
            record.setBookno(rs.getString("bookno"));
            record.setBookname(rs.getString("bookname"));
            record.setBorrowtime(rs.getString("borrowtime"));
            recordlist.add(record);
        }
        rs.close();
        con.close();
        return recordlist;
    }
    //查询所有还书
    public static ArrayList<ReturnRecordInfo> searchReturnRecord() throws Exception {
        Connection con = jdbc.getConnection();
        String sql = "select * from borrow_book;";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        ArrayList<ReturnRecordInfo> recordlist = new ArrayList<>();
        while (rs.next()) {
            ReturnRecordInfo record = new ReturnRecordInfo();
            record.setBookno(rs.getString("bookno"));
            record.setBookname(rs.getString("bookname"));
            record.setReturntime(rs.getString("returntime"));
            recordlist.add(record);
        }
        rs.close();
        con.close();
        return recordlist;
    }

    //检查书的数量
    public static int check_booknum(String bookname)throws Exception {
        Connection con = jdbc.getConnection();

        String sql = "select booknum from book where bookname=?";

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1,bookname);
        ResultSet rs = ps.executeQuery();
        int booknum = 0;
        if (rs.next()) {
            booknum = rs.getInt("booknum");
        }
        return booknum;
    }
    //检查用户余额
    public static double check_balance(String username)throws Exception {
        Connection con = jdbc.getConnection();
        String sql = "select balance from book where username=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1,username);
        ResultSet rs = ps.executeQuery();
        double b = 0;
        if (rs.next()) {
            b = rs.getInt("balance");
        }
        return b;
    }
    //用户注册
    public static int insert_user(String username,String password,String truename,String usertype,String email,String phonenumber) throws Exception {
        Connection con = jdbc.getConnection();
        String sql = "insert user values(?,?,?,?,?,?,?,?);";
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1,username);
        ps.setString(2,password);
        ps.setString(3,truename);
        ps.setString(4,usertype);
        ps.setString(5,email);
        ps.setString(6,phonenumber);
        ps.setString(7, String.valueOf(0));
        ps.setString(8, String.valueOf(0));
        int rs = ps.executeUpdate();
        closeResource(con,ps);
        return rs;
    }
    //检查用户是否已存在
    public static int check_user(String username) throws Exception {
        String un = null;
        Connection con = jdbc.getConnection();
        String sql = "select username from user where username=?;";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1,username);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            un = rs.getString("username");
        }
        closeResource(con,ps);
        if(un == null)
            return 0;
        else return 1;
    }

    //管理员添加书籍
    public static int insert_book(bookinfo Book) throws Exception {
        Connection con = jdbc.getConnection();
        String sql1 = "select * from book where bookname=?;";
        PreparedStatement ps1 = con.prepareStatement(sql1);
        ps1.setString(1,Book.getBookname());
        ResultSet rs1 = ps1.executeQuery();

        if(!rs1.next()){
            String sql = "insert book values(?,?,?,?,?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1,Book.getBookname());
            ps.setString(2,Book.getAuther());
            ps.setString(3,Book.getBooktype());
            ps.setString(4,Book.getPublisher());
            ps.setInt(5,Book.getBooknum());
            ps.setDouble(6,Book.getPrice());
            ps.setString(7,Book.getIntroduction());
            ps.setInt(8,1);

            int rs = ps.executeUpdate();
            String l = "";
            for(int i=0;i<Book.getBooknum();i++){
                l += String.valueOf(no);
                no++;
                if(i < Book.getBooknum()-1)
                    l+="//";
            }
            jdbc.insert_bookno(l,Book.getBookname(),Book.getBooknum());
            closeResource(con,ps);
            return 1;

        }else{
            bookinfo book = new bookinfo();
            if(!Book.getBooktype().equals(rs1.getString("booktype")))
                return 3;
            else if(!Book.getPublisher().equals(rs1.getString("publisher")))
                return 4;
            else if(Book.getPrice() != rs1.getDouble("price"))
                return 6;
            else{
                String sql2 = "update book set booknum=booknum+? where bookname=?;";
                PreparedStatement ps2 = con.prepareStatement(sql2);
                ps2.setInt(1,Book.getBooknum());
                ps2.setString(2,Book.getBookname());
                int rs2 = ps2.executeUpdate();
                ps2.close();
                String l = "";
                for(int i=0;i<Book.getBooknum();i++){
                    l += String.valueOf(no);
                    no++;
                    if(i < Book.getBooknum()-1)
                        l+="//";
                }
                jdbc.insert_bookno(l,Book.getBookname(),Book.getBooknum());
                ps1.close();
                return 1;
            }
        }
    }
    //管理员添加每本书的编号
    public static int insert_bookno(String bookno,String bookname,int booknum) throws Exception {
        Connection con = jdbc.getConnection();
        int i=0,rs=0;
        String[] no = bookno.split("//");
        String sql = "insert book_no values(?,?,?);";
        while(i<booknum){

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1,no[i]);
            ps.setString(2,bookname);
            ps.setString(3, String.valueOf(1));
            i++;
            rs = ps.executeUpdate();
            ps.close();
        }
        con.close();
        return rs;
    }
    //添加借书记录
    public static int insert_borrow_record(String bookno,String username,String bookname) throws Exception {
        Connection con = jdbc.getConnection();
        //获取当前时间
        Calendar calendar= Calendar.getInstance();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss");
        String time = dateFormat.format(calendar.getTime());

        String sql2 = "update book_no set state=0 where bookno=? and state=1;";
        PreparedStatement ps2 = con.prepareStatement(sql2);
        ps2.setString(1,bookno);
        int rs2 = ps2.executeUpdate();
        ps2.close();
        if(rs2 == 1){
            String sql1 = "insert borrow_book values(?,?,?,?);";
            PreparedStatement ps1 = con.prepareStatement(sql1);
            ps1.setString(1,bookno);
            ps1.setString(2,username);
            ps1.setString(3,bookname);
            ps1.setString(4,time);

            String sql3 = "update book set booknum=booknum-1 where bookname=?;";
            String sql4 = "update user set borrownum=borrownum+1 where username=?;";
            PreparedStatement ps3 = con.prepareStatement(sql3);
            PreparedStatement ps4 = con.prepareStatement(sql4);
            ps3.setString(1,bookname);
            ps4.setString(1,username);
            int rs1 = ps1.executeUpdate();
            int rs3 = ps3.executeUpdate();
            int rs4 = ps4.executeUpdate();
            ps1.close();
            ps3.close();
            ps4.close();
            return 1;
        }
        con.close();
        return 0;
    }
    //添加还书记录
    public static int insert_return_record(String bookno,String username,String bookname) throws Exception {
        Connection con = jdbc.getConnection();

        Calendar calendar= Calendar.getInstance();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss");

        String time = dateFormat.format(calendar.getTime());

        String sql1 = "insert return_book values(?,?,?,?);";
        String sql2 = "update book set booknum=booknum+1 where bookname=?;";
        String sql3 = "update book_no set state=1 where bookno=?;";
        String sql4 = "update user set borrownum=borrownum-1 where username=?;";
        String sql5 = "select borrowtime from borrow_book where bookno=?;";
        String sql6 = "delete from borrow_book where bookno=?;";
        PreparedStatement ps1 = con.prepareStatement(sql1);
        PreparedStatement ps2 = con.prepareStatement(sql2);
        PreparedStatement ps3 = con.prepareStatement(sql3);
        PreparedStatement ps4 = con.prepareStatement(sql4);
        PreparedStatement ps5 = con.prepareStatement(sql5);
        PreparedStatement ps6 = con.prepareStatement(sql6);
        ps1.setString(1,bookno);
        ps1.setString(2,username);
        ps1.setString(3,bookname);
        ps1.setString(4,time);
        ps2.setString(1,bookname);
        ps3.setString(1,bookno);
        ps4.setString(1,username);
        ps5.setString(1,bookno);
        ps6.setString(1,bookno);
        int rs1 = ps1.executeUpdate();
        int rs2 = ps2.executeUpdate();
        int rs3 = ps3.executeUpdate();
        int rs4 = ps4.executeUpdate();
        ResultSet rs5 = ps5.executeQuery();
        String bowtime="";
        if(rs5.next()){
            bowtime = rs5.getString("borrowtime");
        }
        int rs6 = ps6.executeUpdate();
        String[] time1 = bowtime.split("-|:");
        calendar.add(calendar.YEAR, 1970-Integer.parseInt(time1[0]));
        calendar.add(calendar.MONTH, 1-Integer.parseInt(time1[1]));
        calendar.add(calendar.DAY_OF_MONTH, 1-Integer.parseInt(time1[2]));
        long t = calendar.getTimeInMillis()/(1000*60*60*24);
        double cost = t*0.1;
        String sql7 = "update user set balance=balance-? where username=?;";
        PreparedStatement ps7 = con.prepareStatement(sql7);
        ps7.setDouble(1, cost);
        ps7.setString(2,username);
        int rs7 = ps7.executeUpdate();
        ps1.close();
        ps2.close();
        ps3.close();
        ps4.close();
        ps5.close();
        ps6.close();
        ps7.close();
        con.close();
        return rs1;
    }
    //忘记密码
    public static int find_pasword(String username,String phonenumber)throws Exception {
        Connection con = jdbc.getConnection();
        String sql = "select username,phonenumber from user where username=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1,username);
        ResultSet rs = ps.executeQuery();
        String un = "";
        String pn = "";
        if (rs.next()) {
            un = rs.getString("username");
            pn = rs.getString("phonenumber");
        }
        closeResource(con,ps);
        if(un.equals(username) && pn.equals(phonenumber))
            return 1;
        else return 0;
    }
    //更新密码
    public static int update_pasword(String username,String password)throws Exception {
        Connection con = jdbc.getConnection();
        String sql = "update user set password=? where username=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1,password);
        ps.setString(2,username);
        int rs = ps.executeUpdate();
        return rs;
    }

    public static void main(String[] args) {


        try {
            ArrayList<BorrowRecordInfo> rel = jdbc.searchBorrowRecord();
            for(BorrowRecordInfo r:rel){
                System.out.println(r.getBookno());
            }
//            int t = jdbc.insert_book(new bookinfo("hhh","李勇平","","新华社",5,39.8,"",1));
//            System.out.println(t);
//            jdbc.delete_bookno("10001","ASP.NET 2.0（C#）基础教程");
//            jdbc.search_record("aaa");
//            int m = jdbc.delete_book("C程序设计教程");
//            System.out.println("m="+m);
//            ArrayList<bookno> bookl = jdbc.search_bookno("ASP.NET 2.0+SQL Server 2005数据库开发与实例");
//            for(int i=0;i<bookl.size();i++){
//                System.out.println(bookl.get(i).getBookno());
//            }

//            if(check_booknum("Authorware多媒体课件制作实用教")>0)
//                jdbc.insert_borrow_record("10015","aaa","Authorware多媒体课件制作实用教");
//            jdbc.insert_borrow_record("10016","aaa","Authorware多媒体课件制作实用教");
//            jdbc.insert_return_record("1","aaa","C程序设计教程");
//            jdbc.insert_return_record("10016","aaa","Authorware多媒体课件制作实用教");
//            ArrayList<bookinfo> bookl = jdbc.search_book("");
//            int no = 10000;
//            for(int i=0;i<bookl.size();i++){
//                int t = bookl.get(i).getBooknum();
//                String na = bookl.get(i).getBookname();
////                System.out.println(t);
//                String l = "";
//                for(int j=0;j<t;j++){
//                     l += String.valueOf(no);
//                     no++;
//                    if(j < t-1)
//                        l+="//";
//                }
//                jdbc.insert_bookno(l,na,t);
//            }

//            jdbc.insert_book("aaa","ccc","瞎几把写","XUT",3,22.5,"哈哈哈");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
