package jdbc;

import com.info.*;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class JDBC {
    private static int MAX_INFO_NUM;
    private static int MAX_BOOK_NO;
    private static String DBMS = "database";                // 数据库名
    private static String tb_book = "book";                 // 书表
    private static String tb_user = "user";                 // 用户表
    private static String tb_borrow_book = "borrow_book";   // 借书表
    private static String tb_return_book = "return_book";   // 还书表


    static String url = "jdbc:mysql://127.0.0.1:3306"+"/"+DBMS;
    static String root = "root";                // 管理员账户
    static String pass = "123456";              // 管理员密码

    static {
        try {
            MAX_INFO_NUM = JDBC.showInform().size();
            MAX_INFO_NUM++;

            MAX_BOOK_NO = JDBC.searchBookSize() + 1;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    // 连接数据库
    public static Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        // 连接数据库
        Connection conn = DriverManager.getConnection(url, root, pass);
        // System.out.println("连接成功");
        return conn;
    }

    // 释放资源
    public static void closeResource(Connection conn, PreparedStatement ps) throws SQLException {
        ps.close();
        conn.close();
    }

    public static Userinfo searchUser(String Key) throws Exception {
        Connection con = JDBC.getConnection();
        String sql = "select * from user where username=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1,Key);
        ResultSet rs = ps.executeQuery();
        Userinfo user = new Userinfo();
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

    //用户注册
    public static int insert_user(String username,String password,String truename,String usertype,String email,String phonenumber) throws Exception {
        Connection con = JDBC.getConnection();
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
        Connection con = JDBC.getConnection();
        String sql = "select username from user where username=?;";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1,username);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            un = rs.getString("username");
        }
        closeResource(con,ps);
        if(un == null)
            return 1;
        else return 0;
    }

    //忘记密码
    public static int find_pasword(String username,String phonenumber)throws Exception {
        Connection con = JDBC.getConnection();
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
        return 0;
    }

    public static int update_pasword(String username,String password)throws Exception {
        Connection con = JDBC.getConnection();
        String sql = "update user set password=? where username=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1,password);
        ps.setString(2,username);
        int rs = ps.executeUpdate();
        return rs;
    }

    // --------------------------------------------------------------------消息----------------------------------------------------------------------------
    // 显示所有通知
    public static ArrayList<Informinfo> showInform() throws Exception {
        Connection con = JDBC.getConnection();
        String sql = "select * from inform";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        ArrayList<Informinfo> list = new ArrayList<>();
        while (rs.next()) {
            Informinfo inform =new Informinfo(rs.getInt("infonum"),rs.getString("title"),rs.getString("infotime"),rs.getString("content"));
            list.add(inform);
        }
        rs.close();
        closeResource(con,ps);
        return list;
    }


    // ------------------------------------------------------------------书籍信息---------------------------------------------------------------------------

    //查询书籍信息
    public static ArrayList<Bookinfo> searchBook(String Key) throws Exception {
        Connection con = JDBC.getConnection();
        String sql = "select * from book where bookname like ? and is_borrow=1;";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1,"%"+Key+"%");
        ResultSet rs = ps.executeQuery();
        ArrayList<Bookinfo> bookl = new ArrayList<>();
        while (rs.next()) {
            Bookinfo book = new Bookinfo();
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

    public static int searchBookSize() throws Exception {
        Connection con = JDBC.getConnection();
        String sql = "select max(bookno) from book_no;";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        int max = 0;
        if (rs.next()) {
             max = Integer.parseInt(rs.getString("max(bookno)"));
        }

        rs.close();
        closeResource(con,ps);
        return max;
    }

    //查询剩余可借阅书籍
    public static ArrayList<Bookno> searchBookno(String Key) throws Exception {
        Connection con = JDBC.getConnection();
        String sql = "select * from book_no where bookname=? and state=1";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1,Key);
        ResultSet rs = ps.executeQuery();
        ArrayList<Bookno> booklist = new ArrayList<>();
        while (rs.next()){
            Bookno book = new Bookno();
            book.setBookno(rs.getString("bookno"));
            book.setBookname(rs.getString("bookname"));
            booklist.add(book);
        }
        rs.close();
        closeResource(con,ps);
        return booklist;
    }

    //查询用户的借书记录
    public static ArrayList<BorrowBook> searchRecord(String username) throws Exception {
        Connection con = JDBC.getConnection();
        String sql = "select * from borrow_book where username=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1,username);
        ResultSet rs = ps.executeQuery();
        ArrayList<BorrowBook> borrowBookslist = new ArrayList<>();
        while (rs.next()) {
            BorrowBook book = new BorrowBook();
            book.setBookno(rs.getString("bookno"));
            book.setBookname(rs.getString("bookname"));
            book.setBorrowtime(rs.getString("borrowtime"));
            borrowBookslist.add(book);
        }
        rs.close();
        for(BorrowBook i:borrowBookslist){
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
        }
        return borrowBookslist;
    }

    //查询所有借书
    public static ArrayList<BorrowRecordInfo> displayBorrowRecord() throws Exception {
        Connection con = JDBC.getConnection();
        String sql = "select * from borrow_book;";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        ArrayList<BorrowRecordInfo> recordlist = new ArrayList<>();
        while (rs.next()) {
            BorrowRecordInfo record = new BorrowRecordInfo();
            record.setBookno(rs.getString("bookno"));
            record.setUsername(rs.getString("username"));
            record.setBookname(rs.getString("bookname"));
            record.setBorrowtime(rs.getString("borrowtime"));
            recordlist.add(record);
        }
        rs.close();
        con.close();
        return recordlist;
    }
    //查询所有还书
    public static ArrayList<ReturnRecordInfo> displayReturnRecord() throws Exception {
        Connection con = JDBC.getConnection();
        String sql = "select * from return_book;";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        ArrayList<ReturnRecordInfo> recordlist = new ArrayList<>();
        while (rs.next()) {
            ReturnRecordInfo record = new ReturnRecordInfo();
            record.setBookno(rs.getString("bookno"));
            record.setUsername(rs.getString("username"));
            record.setBookname(rs.getString("bookname"));
            record.setReturntime(rs.getString("returntime"));
            recordlist.add(record);
        }
        rs.close();
        con.close();
        return recordlist;
    }




    //管理员添加每本书的编号
    public static int insertBookno(String bookno,String bookname,int booknum) throws Exception {
        Connection con = JDBC.getConnection();
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
    public static int insertBorrowRecord(String bookno,String username,String bookname) throws Exception {
        Connection con = JDBC.getConnection();
        //获取当前时间
        Calendar calendar= Calendar.getInstance();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
        String time = dateFormat.format(calendar.getTime());

        String sql1 = "insert borrow_book values(?,?,?,?);";
        String sql2 = "update book set booknum=booknum-1 where bookname=?;";
        String sql3 = "update book_no set state=0 where bookno=?;";
        String sql4 = "update user set borrownum=borrownum+1 where username=?;";
        PreparedStatement ps1 = con.prepareStatement(sql1);
        PreparedStatement ps2 = con.prepareStatement(sql2);
        PreparedStatement ps3 = con.prepareStatement(sql3);
        PreparedStatement ps4 = con.prepareStatement(sql4);
        ps1.setString(1,bookno);
        ps1.setString(2,username);
        ps1.setString(3,bookname);
        ps1.setString(4,time);
        ps2.setString(1,bookname);
        ps3.setString(1,bookno);
        ps4.setString(1,username);
        int rs1 = ps1.executeUpdate();
        int rs2 = ps2.executeUpdate();
        int rs3 = ps3.executeUpdate();
        int rs4 = ps4.executeUpdate();
        ps1.close();
        ps2.close();
        ps3.close();
        ps4.close();
        con.close();
        return rs1;
    }

    //添加还书记录
    public static int insertReturnRecord(String bookno,String username,String bookname) throws Exception {
        Connection con = JDBC.getConnection();

        Calendar calendar= Calendar.getInstance();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");

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

    //管理员添加书籍
    public static int insertNewBook(Bookinfo Book) throws Exception {
        Connection con = JDBC.getConnection();
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
            ps.setString(7,"");
            ps.setInt(8,1);

            int rs = ps.executeUpdate();
            String l = "";
            for(int i=0;i<Book.getBooknum();i++){
                l += String.valueOf(MAX_BOOK_NO);
                MAX_BOOK_NO++;
                if(i < Book.getBooknum()-1)
                    l+="//";
            }
            JDBC.insertBookno(l,Book.getBookname(),Book.getBooknum());
            closeResource(con,ps);
            return 1;

        }else{
            Bookinfo book = new Bookinfo();
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
                    l += String.valueOf(MAX_BOOK_NO);
                    MAX_BOOK_NO++;
                    if(i < Book.getBooknum()-1)
                        l+="//";
                }
                JDBC.insertBookno(l,Book.getBookname(),Book.getBooknum());
                ps1.close();
                return 1;
            }
        }
    }


    public static int insertInform(String title, String content) throws Exception {

        Calendar calendar= Calendar.getInstance();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");

        String data = dateFormat.format(calendar.getTime());
        Connection con = getConnection();
        String sql = "insert inform values(?,?,?,?);";

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, String.valueOf(MAX_INFO_NUM));
        ps.setString(2,title);
        ps.setString(3,data);
        ps.setString(4,content);

        int rs = ps.executeUpdate();
        closeResource(con, ps);

        MAX_INFO_NUM++;
        return rs;
    }

    public static int AlertUserInform(String username, String truename, String password, String phonenumber, String email) throws Exception{
        Connection con = getConnection();
        String sql = "update user set truename=?,password=?,phonenumber=?,email=? where username=?";

        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, truename);
        ps.setString(2, password);
        ps.setString(3, phonenumber);
        ps.setString(4, email);
        ps.setString(5, username);

        int row = ps.executeUpdate();
        closeResource(con, ps);
        return row;
    }


    //删除书籍
    public static int deleteAllBook(String bookname)throws Exception {
        Connection con = JDBC.getConnection();

        String sql2 = "select count(bookname) sum from borrow_book where bookname=?;";

        PreparedStatement ps2 = con.prepareStatement(sql2);

        ps2.setString(1,bookname);

        ResultSet rs2 = ps2.executeQuery();
        int num1=0,num2=0;
        if(rs2.next()){
            num1 = rs2.getInt("sum");
        }
        if(num1 == 0){ //可以删除
            String sql1 = "update book set is_borrow=0 where bookname=?";
            PreparedStatement ps1 = con.prepareStatement(sql1);
            ps1.setString(1,bookname);
            int rs1 = ps1.executeUpdate();

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
    public static int deleteBookno(String bookno,String bookname)throws Exception {
        Connection con = JDBC.getConnection();
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

    public static int charge(String key,String money) throws Exception {

        double balance =JDBC.searchUser(key).getBalance();

        balance+=Double.parseDouble(money);

        Connection con = getConnection();
        String sql = "update user set balance=? where username=?;";
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setDouble(1,balance);
        ps.setString(2,key);

        int rs = ps.executeUpdate();

        closeResource(con,ps);

        return rs;
    }




//    public static void main(String[] args) throws Exception{
//        ArrayList<Bookinfo> bookl = JDBC.searchBook("");
//        for(int i=0;i<bookl.size();i++){
//            int t = bookl.get(i).getBooknum();
//            String na = bookl.get(i).getBookname();
//            String l = "";
//            for(int j=0;j<t;j++){
//                l += String.valueOf(MAX_BOOK_NO);
//                MAX_BOOK_NO++;
//                if(j < t-1)
//                    l+="//";
//            }
//            JDBC.insertBookno(l,na,t);
//        }
//    }
}
