
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//服务器端
public class server {

            static DbConnect db = new DbConnect();
            static Connection con = db.getConnection();
            
    public static void main(String[] args) throws IOException {
        while (true) {
            //服务器端接收客户端信息
            //1、创建非服务器端的UDPsocket对象并指定端口
            DatagramSocket socket = new DatagramSocket(6666);
            //2、创建用于接受客户端数据的数据报
            byte[] data = new byte[1024];            //用于接收数据的字节数组
            DatagramPacket packet = new DatagramPacket(data, data.length);
            //3、接收客户端的数据
            System.out.println("等待客户端数据中。。。");
            socket.receive(packet);
            //4、读取接收在data中的字节数组转化为字符串
            String s = new String(data, 0, packet.getLength());
            System.out.println("服务器端收到的数据：" + s);

            if (s.contains("select")&&s.contains("智慧诊疗")) {
                try {
                    Statement stm = con.createStatement();
                    ResultSet rs1 = stm.executeQuery(s);
                    String response = "";
                    while (rs1.next()) {
                        response = rs1.getString(2);
                        response =response + " " + rs1.getString(3);
                    }


                    //服务器向客户端发送响应信息
                    //1、定义响应信息与客户端的地址和端口
                    byte[] response1 = response.getBytes();
                    InetAddress clientAddress = packet.getAddress();  //从客户端发来的数据报中得到其IP地址
                    int port = packet.getPort();
                    //2、创建响应数据报
                    DatagramPacket responsePacket = new DatagramPacket(response1, response1.length, clientAddress, port);
                    //3、发送数据报
                    socket.send(responsePacket);
                    //4、关闭资源
                    socket.close();

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } else if (s.contains("select")&&s.contains("医生信息")) {
                try {
                    Statement stm = con.createStatement();
                    ResultSet rs1 = stm.executeQuery(s);
                    String response = "";
                    while (rs1.next()) {
                        response = rs1.getString(1);
                        response =response + " " + rs1.getString(2);
                        response =response + " " + rs1.getString(3);
                        response =response + " " + rs1.getString(4);
                    }


                    //服务器向客户端发送响应信息
                    //1、定义响应信息与客户端的地址和端口
                    byte[] response1 = response.getBytes();
                    InetAddress clientAddress = packet.getAddress();  //从客户端发来的数据报中得到其IP地址
                    int port = packet.getPort();
                    //2、创建响应数据报
                    DatagramPacket responsePacket = new DatagramPacket(response1, response1.length, clientAddress, port);
                    //3、发送数据报
                    socket.send(responsePacket);
                    //4、关闭资源
                    socket.close();

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }else if(s.contains("儿童口腔健康基本信息")){
                String response = "";
                try{
                Statement stm = con.createStatement();
                String sql1 = "select avg(儿童口腔健康基本信息.儿童年龄) from 儿童口腔健康基本信息 where 是否有龋坏 ='是'";
                String sql2 = "select count(*) from 儿童口腔健康基本信息 where 是否有龋坏 ='是' and 儿童性别 = '女'";
                String sql3 = "select count(*) from 儿童口腔健康基本信息 where 是否有龋坏 ='是' and 儿童性别 = '男'";
                String sql4 = "select count(*) from 儿童口腔健康基本信息 ";
                String sql5 = "select count(*) from 儿童口腔健康基本信息 where 是否有龋坏 ='是'";
                ResultSet rs1 = stm.executeQuery(sql1);
                rs1.next();
                int avgage = rs1.getInt(1);
                rs1 = stm.executeQuery(sql2);
                rs1.next();
                int wonum = rs1.getInt(1);
                rs1 = stm.executeQuery(sql3);
                rs1.next();
                int mannum = rs1.getInt(1);
                rs1 = stm.executeQuery(sql4);
                rs1.next();
                int sum = rs1.getInt(1);
                rs1 = stm.executeQuery(sql5);
                rs1.next();
                int isqu = rs1.getInt(1);
                response="平均患龋年龄："+avgage+"\n女生患龋:"+wonum+"\n男生患龋:"+mannum+"\n患龋率为:"+isqu/sum+" ";

                String sql6 = "select count(*) from 儿童口腔健康基本信息 where 口腔健康情况 ='健康'";
                rs1 = stm.executeQuery(sql6);
                rs1.next();
                int ishealthy = rs1.getInt(1);
                response = response+"口腔健康人数为:"+ishealthy+"\n占比:"+ishealthy/sum+" ";


                String sql8 = "select count(*) from 儿童口腔健康基本信息 where 是否有唇腭裂情况 ='是' and 儿童性别 = '女'";
                String sql9 = "select count(*) from 儿童口腔健康基本信息 where 是否有唇腭裂情况 ='是' and 儿童性别 = '男'";
                String sql10 = "select count(*) from 儿童口腔健康基本信息 ";
                String sql11 = "select count(*) from 儿童口腔健康基本信息 where 是否有唇腭裂情况 ='是'";
                rs1 = stm.executeQuery(sql8);
                rs1.next();
                int wonum1 = rs1.getInt(1);
                rs1 = stm.executeQuery(sql9);
                rs1.next();
                int mannum1 = rs1.getInt(1);
                rs1 = stm.executeQuery(sql10);
                rs1.next();
                int sum1 = rs1.getInt(1);
                rs1 = stm.executeQuery(sql11);
                rs1.next();
                int isqu1 = rs1.getInt(1);
               response = response+"女生患唇腭裂人数："+wonum1+"\n男生患唇腭裂:"+mannum1+"\n患唇腭裂率为:"+isqu1/sum;
                    byte[] response1 = response.getBytes();
                    InetAddress clientAddress = packet.getAddress();  //从客户端发来的数据报中得到其IP地址
                    int port = packet.getPort();
                    //2、创建响应数据报
                    DatagramPacket responsePacket = new DatagramPacket(response1, response1.length, clientAddress, port);
                    //3、发送数据报
                    socket.send(responsePacket);
                    //4、关闭资源
                    socket.close();


                }   catch (SQLException e) {
                throw new RuntimeException(e);
            }
            }
        }
        }
    }