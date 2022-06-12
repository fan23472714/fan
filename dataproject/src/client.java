import java.io.*;
import java.net.Socket;

//客户端
public class client {
    public static void main(String[] args) {
        try {
            //1、创建客户端Socket对象，指定要连接的服务器和端口
            Socket socket = new Socket("localhost", 6666);

            //2、通过输出流向服务器发送信息
            OutputStream outputStream = socket.getOutputStream();     //获取输出流
            PrintWriter printWriter = new PrintWriter(outputStream);  //将输出流包装为打印流
            printWriter.write("select * from 智慧诊疗");
            printWriter.flush();
            HomeWindow.createHomeWindow();
            socket.shutdownOutput();

            //3、通过输入流接收服务器的返回信息
            InputStream inputStream = socket.getInputStream();        //获取字节输入流
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));//包装为缓冲字符流
            String info;
            while ((info = bufferedReader.readLine()) != null)
                System.out.println("收到服务器端相应：" + info);






            socket.shutdownInput();

            //4、关闭流资源
            bufferedReader.close();
            inputStream.close();
            printWriter.close();
            outputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}