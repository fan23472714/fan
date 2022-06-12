
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.*;


public class baby_Doctor  extends  JFrame {
    Font f1 = new Font("隶书", 1, 28);
    Font f2 = new Font("华文仿宋", 1, 16);
    ImageIcon bg = new ImageIcon("3.jpg");
    JLabel bgJLabel = new JLabel(bg);

    private int flag = 0;
    JPanel panel = new JPanel();
    public  JButton save = new JButton("查询"),cancel = new JButton("取消"),d = new JButton("联系医生");
    JLabel label1 = new JLabel("咨询内容：");
    JLabel label2 = new JLabel("解决方案：");
    JLabel label3 = new JLabel("预防方案：");
    public  JTextArea tx1 = new JTextArea(),tx2 = new JTextArea(),tx3 = new JTextArea();
    doctor doc = new doctor();



    public baby_Doctor() {

    }
    public void getbaby_Doctor(String s) {
        this.setVisible(true);
        this.setLayout(null);
        this.setTitle(s);
        this.setLocation(450,270);
        this.setSize(1045,515);

        panel.setLayout(null);
        panel.setBounds(0,0,1045,515);


        label1.setBounds(100,80,200,50);
        label1.setFont(f1);
        label2.setBounds(100,160,200,50);
        label2.setFont(f1);
        label3.setBounds(100,330,200,50);
        label3.setFont(f1);

        tx1.setBounds(320,80,300,50);
        tx2.setBounds(320,140,300,150);
        tx3.setBounds(320,310,300,150);
        tx2.setEditable(false);
        tx3.setEditable(false);
        tx2.setLineWrap(true);        //激活自动换行功能
        tx2.setWrapStyleWord(true);
        tx3.setLineWrap(true);        //激活自动换行功能
        tx3.setWrapStyleWord(true);
        tx1.setFont(f2);
        tx2.setFont(f2);
        tx3.setFont(f2);

        panel.add(label1);
        panel.add(label2);
        panel.add(label3);
        panel.add(tx1);
        panel.add(tx2);
        panel.add(tx3);


        save.setBounds(690,80,60,30);
        cancel.setBounds(780,80,60,30);
        d.setBounds(870,80,90,30);
        panel.add(save);
        panel.add(cancel);
        panel.add(d);
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
            }
        });
        d.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                doc.getdoctor("联系医生");
                baby_Doctor.this.setVisible(false);
            }
        });

        Toolkit tk = getToolkit();
        Image icon = tk.getImage("4.png");
        setIconImage(icon);
    }





}

