
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.*;


public class doctor  extends  JFrame {
    Font f1 = new Font("隶书", 1, 28);
    Font f2 = new Font("华文仿宋", 1, 16);
    ImageIcon bg = new ImageIcon("3.jpg");
    JLabel bgJLabel = new JLabel(bg);

    private int flag = 0;
    JPanel panel = new JPanel();
    public  JButton save = new JButton("查询"),cancel = new JButton("取消");
    JLabel label1 = new JLabel("请输入科室：");
    JLabel label2 = new JLabel("医生姓名");
    JLabel label3 = new JLabel("医生职称");
    JLabel label4 = new JLabel("联系方式");
    JLabel label5 = new JLabel("所在科室");

    public  JTextArea tx1 = new JTextArea(),
            tx2 = new JTextArea(),
            tx3 = new JTextArea(),
            tx4 = new JTextArea(),
            tx5 = new JTextArea();




    public doctor() {

    }
    public void getdoctor(String s) {
        this.setVisible(true);
        this.setLayout(null);
        this.setTitle(s);
        this.setLocation(450,270);
        this.setSize(1045,515);

        panel.setLayout(null);
        panel.setBounds(0,0,1045,515);


        label1.setBounds(50,50,180,50);
        label1.setFont(f1);
        label2.setBounds(300,180,80,50);
        label3.setBounds(300,240,80,50);
        label4.setBounds(300,300,80,50);
        label5.setBounds(300,360,80,50);


        tx1.setBounds(250,50,300,50);
        tx2.setBounds(380,180,300,50);
        tx3.setBounds(380,240,300,50);
        tx4.setBounds(380,300,300,50);
        tx5.setBounds(380,360,300,50);

        tx2.setEditable(false);
        tx3.setEditable(false);
        tx4.setEditable(false);
        tx5.setEditable(false);
        tx2.setLineWrap(true);        //激活自动换行功能
        tx2.setWrapStyleWord(true);
        tx3.setLineWrap(true);        //激活自动换行功能
        tx3.setWrapStyleWord(true);
        tx1.setFont(f2);


        panel.add(label1);
        panel.add(label2);
        panel.add(label3);
        panel.add(label4);
        panel.add(label5);
        panel.add(tx1);
        panel.add(tx2);
        panel.add(tx3);
        panel.add(tx4);
        panel.add(tx5);


        save.setBounds(560,50,60,30);
        cancel.setBounds(620,50,60,30);
        panel.add(save);
        panel.add(cancel);
        ImageIcon bg = new ImageIcon("3.jpg");
        JLabel bgJLabel = new JLabel(bg);
        bgJLabel.setBounds(0, 0, 1045, 515);
        panel.add(bgJLabel);
        this.add(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tx1.setText("");
                tx2.setText("");
                tx3.setText("");
                tx4.setText("");
                tx5.setText("");
            }
        });

        Toolkit tk = getToolkit();
        Image icon = tk.getImage("4.png");
        setIconImage(icon);
    }





}

