import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//服务器端，先启动
public class server {
    public static void main(String[] args) {
        DbConnect dbConnect = new DbConnect();
        Connection con =dbConnect.getConnection();




        try {
            //1、创建服务器端ServerSocket对象，绑定监听的端口号6666
            ServerSocket serverSocket = new ServerSocket(6666);
            //2、调用accept()方法监听开始，等待客户端连接
            System.out.println("服务器端等待客户端连接。。。");
            Socket socket = serverSocket.accept();

            //3、通过输入流读取客户端传来的信息
            InputStream inputStream = socket.getInputStream();        //获取字节输入流
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));//包装为缓冲字符流
            OutputStream outputStream = socket.getOutputStream();     //获取输出流
            PrintWriter printWriter = new PrintWriter(outputStream);  //将输出流包装为打印流
            String info;
            while ((info = bufferedReader.readLine()) != null){
                System.out.println("收到客户端信息：" + info);
                if(info.contains("select")){
                    Statement stm = con.createStatement();
                    ResultSet rs1 = stm.executeQuery(info);
                    while(rs1.next()){
                        printWriter.write(rs1.getString(2));
                    }

                }
            }

            InetAddress address = socket.getInetAddress();    // 获取客户端的InetAddress信息
            System.out.println("当前客户端的IP：" + address.getHostAddress());


            printWriter.write("欢迎登录！");
            printWriter.flush();
                                         //关闭socket输出流
            socket.shutdownInput();
            socket.shutdownOutput();
            //5、关闭资源流
            printWriter.close();
            outputStream.close();
            bufferedReader.close();
            inputStream.close();
            socket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
