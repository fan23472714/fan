//Main.java
import java.sql.*;

public class DbConnect{
    public Connection con = null;
    public Connection getConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");//1,加载驱动
            //2.创建连接
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/babyteeth?useSSL=true&characterEncoding=utf-8&user=root&password=123456");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("获取连接失败:" + e.getMessage());
        }
    return con;
        }
    public void close() {//关闭数据库连接甘薯
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

