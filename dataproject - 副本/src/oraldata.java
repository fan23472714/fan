import javax.swing.*;
import java.awt.*;


public class oraldata extends JFrame {
    JPanel Page = new JPanel();
    private Font laFont = new Font("华文行楷", Font.BOLD, 28);
    private Font laFont1 = new Font("楷书", Font.BOLD, 20);
    private JLabel l1 = new JLabel("健康情况");
    private JLabel l2= new JLabel("龋坏情况");
    private JLabel l3= new JLabel("唇腭裂");
    public JTextArea tx1 = new JTextArea();
    public JTextArea tx2 = new JTextArea();
    public JTextArea tx3 = new JTextArea();
    public JButton button1 = new JButton("查看统计结果"), button2 = new JButton("返回首页");


    public oraldata() {

    }

        public void getoraldata(String s) {
        Page.setLayout(null);
        this.setTitle(s);
        this.setLocation(450, 200);
        this.setSize(1015, 550);
        // 窗口图标设置
        Toolkit tk = getToolkit();
        Image icon = tk.getImage("4.png");
        setIconImage(icon);
        // 获取项目路径
        ImageIcon background = new ImageIcon("3.jpg");
        JLabel label = new JLabel(background);


        button1.setBounds(300, 100, 160, 50);
        button1.setFont(laFont1);
        button2.setBounds(600, 100, 120, 50);
        button2.setFont(laFont1);
        Page.add(button1);
        Page.add(button2);

        l1.setBounds(0, 250, 120, 200);
        tx1.setBounds(130, 250, 200, 200);
        l1.setFont(laFont);
        tx1.setEditable(false);
        l2.setBounds(350, 250, 120, 200);
        l2.setFont(laFont);
        tx2.setBounds(480, 250, 200, 200);
        tx2.setEditable(false);
        l3.setBounds(700, 250, 100, 200);
        l3.setFont(laFont);
        tx3.setBounds(815, 250, 200, 200);
            tx3.setEditable(false);

            Page.add(l1);
            Page.add(l2);
            Page.add(l3);
            Page.add(tx1);
            Page.add(tx2);
            Page.add(tx3);




        Page.add(label);
        label.setBounds(0, 0, 1000, 600);
        this.add(Page);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);


    }
}
 
 