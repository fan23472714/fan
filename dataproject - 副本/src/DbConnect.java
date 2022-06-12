//Main.java
import java.sql.*;

public class DbConnect{
    /***
     本文使用的数据库名称为user,账号密码均为root,
     数据库含表userinfo,
     该表有三个字段，id(INT),userinfo(VARCHAR),password(VARCHAR)
     读者可根据数据库的实际情况对语句进行修改
     ***/
    public Connection con = null;

    public Connection getConnection(){
        try {
            //1,加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            //2.创建连接
            //此处按照实际的数据库名称和账号密码进行修改
            //格式为jdbc:mysql://127.0.0.1:3306/数据库名称?useSSL=true&characterEncoding=utf-8&user=账号名&password=密码
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/babyteeth?useSSL=true&characterEncoding=utf-8&user=root&password=123456");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("获取连接失败:" + e.getMessage());
        }
    return con;
        }
    public void close() {
        try {
            if (con != null) {
                con.close();
            }
            con = null;
            System.out.println("数据库连接关闭");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

