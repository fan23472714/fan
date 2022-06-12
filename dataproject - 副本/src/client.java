import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

//客户端
public class client {
    public static String sql = "";
    public static void main(String[] args) throws IOException {
        final HomeWindow homeWindow = new HomeWindow();
        homeWindow.baby_doctor.save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String searchText =homeWindow.baby_doctor.tx1.getText().toString(); // 获取查询文本框框内容
                if(searchText.equals("")){
                    JOptionPane.showMessageDialog(null, "输入为空，请重新输入!", "错误", JOptionPane.ERROR_MESSAGE);
                    sql = "";
                }else{
                sql = "select * from 智慧诊疗 where 口腔不健康情况 = '"+searchText+"'";
                try{

                DatagramSocket socket = new DatagramSocket();
                if(!sql.equals("")) {
                    byte[] data = sql.getBytes();
                    InetAddress address = InetAddress.getByName("localhost");
                    DatagramPacket packet = new DatagramPacket(data, data.length, address, 6666);
                    //3、发送数据包
                    socket.send(packet);
                    sql = "";
                }
                //客户端接收服务器响应数据报
                //1、创建接收数据报的packet
                byte[] resData = new byte[1024];
                DatagramPacket resPacket = new DatagramPacket(resData, resData.length);
                //2、接收响应数据
                socket.receive(resPacket);
                //3、读取响应数据
                String res = new String(resData, 0, resPacket.getLength());
                System.out.println("客户端收到响应：" + res);
                //4、关闭资源

                if(res.equals("")){
                    JOptionPane.showMessageDialog(null, "未查找到，请联系医生!", "错误", JOptionPane.ERROR_MESSAGE);
                }else {
                    String[] r = res.split(" ");
                    homeWindow.baby_doctor.tx2.setText(r[0]);
                    homeWindow.baby_doctor.tx3.setText(r[1]);
                }
                    socket.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }}}
        });

        homeWindow.doc.save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String searchText =homeWindow.doc.tx1.getText().toString(); // 获取查询文本框框内容
                if(searchText.equals("")){
                    JOptionPane.showMessageDialog(null, "输入为空，请重新输入!", "错误", JOptionPane.ERROR_MESSAGE);
                    sql = "";
                }else{
                    sql = "select * from 医生信息 where 所在科室 = '"+searchText+"'";
                    try{

                        DatagramSocket socket = new DatagramSocket();
                        if(!sql.equals("")) {
                            byte[] data = sql.getBytes();
                            InetAddress address = InetAddress.getByName("localhost");
                            DatagramPacket packet = new DatagramPacket(data, data.length, address, 6666);
                            //3、发送数据包
                            socket.send(packet);
                            sql = "";
                        }
                        //客户端接收服务器响应数据报
                        //1、创建接收数据报的packet
                        byte[] resData = new byte[1024];
                        DatagramPacket resPacket = new DatagramPacket(resData, resData.length);
                        //2、接收响应数据
                        socket.receive(resPacket);
                        //3、读取响应数据
                        String res = new String(resData, 0, resPacket.getLength());
                        System.out.println("客户端收到响应：" + res);
                        //4、关闭资源

                        if(res.equals("")){
                            JOptionPane.showMessageDialog(null, "未查找到，请联系医生!", "错误", JOptionPane.ERROR_MESSAGE);
                        }else {
                            String[] r = res.split(" ");
                            homeWindow.doc.tx2.setText(r[0]);
                            homeWindow.doc.tx3.setText(r[1]);
                            homeWindow.doc.tx4.setText(r[2]);
                            homeWindow.doc.tx5.setText(r[3]);
                        }
                        socket.close();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }}}
        });
        homeWindow.baby_doctor.doc.save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String searchText =homeWindow.baby_doctor.doc.tx1.getText().toString(); // 获取查询文本框框内容
                if(searchText.equals("")){
                    JOptionPane.showMessageDialog(null, "输入为空，请重新输入!", "错误", JOptionPane.ERROR_MESSAGE);
                    sql = "";
                }else{
                    sql = "select * from 医生信息 where 所在科室 = '"+searchText+"'";
                    try{

                        DatagramSocket socket = new DatagramSocket();
                        if(!sql.equals("")) {
                            byte[] data = sql.getBytes();
                            InetAddress address = InetAddress.getByName("localhost");
                            DatagramPacket packet = new DatagramPacket(data, data.length, address, 6666);
                            //3、发送数据包
                            socket.send(packet);
                            sql = "";
                        }
                        //客户端接收服务器响应数据报
                        //1、创建接收数据报的packet
                        byte[] resData = new byte[1024];
                        DatagramPacket resPacket = new DatagramPacket(resData, resData.length);
                        //2、接收响应数据
                        socket.receive(resPacket);
                        //3、读取响应数据
                        String res = new String(resData, 0, resPacket.getLength());
                        System.out.println("客户端收到响应：" + res);
                        //4、关闭资源

                        if(res.equals("")){
                            JOptionPane.showMessageDialog(null, "未查找到，请在华西口腔医院官网查找!", "错误", JOptionPane.ERROR_MESSAGE);
                        }else {
                            String[] r = res.split(" ");
                            homeWindow.baby_doctor.doc.tx2.setText(r[0]);
                            homeWindow.baby_doctor.doc.tx3.setText(r[1]);
                            homeWindow.baby_doctor.doc.tx4.setText(r[2]);
                            homeWindow.baby_doctor.doc.tx5.setText(r[3]);
                        }
                        socket.close();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }}}
        });
        homeWindow.oral.button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                    sql = "select * from 儿童口腔健康基本信息";

                    try{
                        DatagramSocket socket = new DatagramSocket();
                        if(!sql.equals("")) {
                            byte[] data = sql.getBytes();
                            InetAddress address = InetAddress.getByName("localhost");
                            DatagramPacket packet = new DatagramPacket(data, data.length, address, 6666);
                            //3、发送数据包
                            socket.send(packet);
                            sql = "";
                        }
                        //客户端接收服务器响应数据报
                        //1、创建接收数据报的packet
                        byte[] resData = new byte[1024];
                        DatagramPacket resPacket = new DatagramPacket(resData, resData.length);
                        //2、接收响应数据
                        socket.receive(resPacket);
                        //3、读取响应数据
                        String res = new String(resData, 0, resPacket.getLength());
                        System.out.println("客户端收到响应：" + res);
                        //4、关闭资源

                            String[] r = res.split(" ");
                            homeWindow.oral.tx1.setText(r[0]);
                            homeWindow.oral.tx2.setText(r[1]);
                            homeWindow.oral.tx3.setText(r[2]);


                        socket.close();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }}
        });

    }
}