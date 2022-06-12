import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class HomeWindow extends JFrame {
    JPanel homePage = new JPanel();
    private Font laFont = new Font("华文行楷", Font.BOLD, 80);
    private Font laFont1 = new Font("楷书", Font.BOLD, 20);
    private JButton button1;
    private JButton button2 ;
    private JButton button3 ;
    baby_Doctor baby_doctor = new baby_Doctor();
    doctor doc = new doctor();
    oraldata oral = new oraldata();

    public HomeWindow() {

        homePage.setLayout(null);
        this.setTitle("智慧志愿诊疗服务系统");
        this.setLocation(450,200);
        this.setSize(1000,600);
        // 窗口图标设置
        Toolkit tk = getToolkit();
        Image icon = tk.getImage("4.png");
        setIconImage(icon);
        // 获取项目路径
        ImageIcon background = new ImageIcon("2.jpg");
        JLabel label = new JLabel(background);


        button1 = new JButton("智慧诊疗");
        button2 = new JButton("统计情况");
        button3 = new JButton("联系医生");
        button1.setBounds(400, 270, 150, 50);
        button1.setFont(laFont1);
        button2.setBounds(400, 350, 150, 50);
        button2.setFont(laFont1);
        button3.setBounds(400, 420, 150, 50);
        button3.setFont(laFont1);

        homePage.add(button1);
        homePage.add(button2);
        homePage.add(button3);


        homePage.add(label);
        label.setBounds(0, 0, 1000, 600);
        this.add(homePage);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        button1.addActionListener(new ActionListener() {
                   public void actionPerformed(ActionEvent e) {
                       baby_doctor.getbaby_Doctor("智慧诊疗查询");
                     HomeWindow.this.setVisible(false);
                   }
               });
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                oral.getoraldata("口腔数据统计");
                HomeWindow.this.setVisible(false);
            }
        });
        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                doc.getdoctor("联系医生");
                HomeWindow.this.setVisible(false);
            }
        });



    }

}

