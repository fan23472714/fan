import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class HomeWindow extends JFrame {
    JPanel homePage = new JPanel();
    private final JLabel la1;
    private final JLabel la2;
    private Font laFont = new Font("华文行楷", Font.BOLD, 80);
    private Font laFont1 = new Font("楷书", Font.BOLD, 20);
    private JButton button1;
    private JButton button2 ;

    private HomeWindow() {

        homePage.setLayout(null);
        this.setTitle("智慧志愿诊疗服务系统");
        this.setLocation(450,200);
        this.setSize(1000,600);
        // 窗口图标设置
        Toolkit tk = getToolkit();
        Image icon = tk.getImage("3.jpg");
        setIconImage(icon);
        // 获取项目路径
        ImageIcon background = new ImageIcon("1.jpg");
        JLabel label = new JLabel(background);

        la1 = new JLabel("欢迎使用");
        la2 = new JLabel("智慧志愿诊疗服务系统");

        la1.setBounds(330, 0, 800, 100);
        la1.setFont(laFont);
        la2.setBounds(120, 110, 1200, 100);
        la2.setFont(laFont);

        homePage.add(la1);
        homePage.add(la2);

        button1 = new JButton("登陆");
        button2 = new JButton("注册");
        button1.setBounds(400, 300, 150, 50);
        button1.setFont(laFont1);
        button2.setBounds(400, 380, 150, 50);
        button2.setFont(laFont1);

        homePage.add(button1);
        homePage.add(button2);


        homePage.add(label);
        label.setBounds(0, 0, 1000, 600);
        this.add(homePage);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        button1.addActionListener(new ActionListener() {
                   public void actionPerformed(ActionEvent e) {
                     Login login = new Login("登陆界面");
                     HomeWindow.this.setVisible(false);
                   }
               });
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Register register = new Register("注册界面");
            }
        });



    }

    public static HomeWindow createHomeWindow() {
        return new HomeWindow();
    }
}

