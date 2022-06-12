
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class VolunteerFrame extends JFrame implements MouseListener {
    // 基本组件
    public CardLayout layout = new CardLayout(); // 卡片布局方式
    JPanel top, left, bottom, right;
    Container container = new Container();
    public static DefaultTableModel dtm;
    public static JTable table;
    JLabel head;
    Font f1 = new Font("楷书", Font.BOLD, 50);
    Font f2 = new Font("楷书", Font.BOLD, 35);
    // 菜单栏组件
    private JPanel pNorth, pSouth, subMenuContainer;
    private JButton item1, item6, item7, item8, htn[], gtn[], btn[], ftn[];
    // 时间组件
    private JPanel timePanel;
    private JLabel displayArea;
    private String DEFAULT_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss"; // 设置时间格式
    private String time;
    private int ONE_SECOND = 1000; // 设置时间间隔
    JLabel label1 = new JLabel("当前账户：");
    CardLayout card = new CardLayout();

    private static final long serialVersionUID = 1L;
    DbConnect dbUtil = new DbConnect();
    Connection con = dbUtil.getConnection();
    public VolunteerFrame(String title) {

        super("智慧志愿诊疗服务系统（医学志愿）)");

        String volunteer_name = Login.username; // 获取登录的用户名，放在标签中，显示在上面板。
        JLabel label8 = new JLabel(volunteer_name);

        // 窗口图标设置
        Toolkit tk = getToolkit();
        Image icon = tk.getImage("3.jpg");
        setIconImage(icon);

        // 时间控件设置
        timePanel = new JPanel();
        displayArea = new JLabel();
        configTimeArea();
        timePanel.add(displayArea);
        timePanel.setBounds(1000, 0, 200, 50);
        timePanel.setOpaque(true); // 时间面板设置为透明

        // 组件基本设置
        left = new JPanel();
        left.setBounds(5, 180, 150, 515);
        this.add(left);

        right = new JPanel();
        right.setBounds(155, 180, 1045, 515);
        right.setLayout(card);
        this.add(right);
        ImageIcon bg = new ImageIcon("2.jpg");
        JLabel bgJLabel = new JLabel(bg);
        right.add(bgJLabel,"bg");
        card.show(right,"bg");


        bottom = new JPanel();
        bottom.setLayout(null);
        bottom.setBounds(0, 666, 1200, 35);
        bottom.setBackground(Color.WHITE);
        bottom.add(timePanel); // 添加时间控件
        this.add(bottom);

        top = new JPanel();
        ImageIcon background_temp = new ImageIcon("4.jpg");
        JLabel label = new JLabel(background_temp);
        top.setLayout(null);
        label.setBounds(0, 0, 300, 180);
        top.setBounds(0, 0, 1200, 180);
        this.add(top);
        top.add(label8);
        label8.setBounds(1010, 110, 150, 50);
        label8.setFont(f2);

        head = new JLabel("智慧志愿诊疗服务系统");
        head.setFont(f1);
        head.setBounds(305, 40, 700, 75);
        top.add(head);

        top.add(label1);
        label1.setBounds(850, 110, 200, 50);
        label1.setFont(f2);
        top.add(label);

        // Left面板折叠式菜单设置,三面板网格式布局
        pNorth = new JPanel();
        pNorth.setLayout(new GridLayout(4, 1));
        pSouth = new JPanel();
        subMenuContainer = new JPanel();

        subMenuContainer.setLayout(new GridLayout(4, 1));

        item1 = new JButton("报名志愿活动"); // 设置按钮

        item6 = new JButton("儿童口腔数据");
        item7 = new JButton("诊疗服务");
        item8 = new JButton("系统操作");

        item1.setPreferredSize(new Dimension(150, 47)); // 优先设置按钮大小

        item6.setPreferredSize(new Dimension(150, 47));
        item7.setPreferredSize(new Dimension(150, 47));
        item7.setPreferredSize(new Dimension(150, 47));
        item8.setPreferredSize(new Dimension(150, 47));

        item1.setContentAreaFilled(false); // 设置为透明
        item6.setContentAreaFilled(false);
        item7.setContentAreaFilled(false);
        item8.setContentAreaFilled(false);

        pNorth.add(item1);
        pNorth.add(item6);
        pNorth.add(item7);
        pNorth.add(item8);

        ftn = new JButton[2];
        ftn[0] = new JButton("查询儿童口腔数据");
        ftn[1] = new JButton("录入儿童口腔数据");
        for (int i = 0; i < ftn.length; i++) {
            ftn[i].setBackground(Color.WHITE);
            ftn[i].setPreferredSize(new Dimension(150, 30));
            ftn[i].addMouseListener(this);
        }
        gtn = new JButton[2];
        gtn[0] = new JButton("查询系统诊疗意见");
        gtn[1] = new JButton("录入系统诊疗意见");
        for (int i = 0; i < gtn.length; i++) {
            gtn[i].setBackground(Color.WHITE);
            gtn[i].setPreferredSize(new Dimension(150, 30));
            gtn[i].addMouseListener(this);
        }
        htn = new JButton[2];
        htn[0] = new JButton("返回首页");
        htn[1] = new JButton("修改密码");

        for (int i = 0; i < htn.length; i++) {
            htn[i].setBackground(Color.WHITE);
            htn[i].setPreferredSize(new Dimension(150, 30));
            htn[i].addMouseListener(this);
        }

        left.add(pNorth, "North"); // 按钮添加到left面板中
        left.add(subMenuContainer, "Center");
        left.add(pSouth, "South");

        // 监听器添加
        item1.addMouseListener(this);

        item6.addMouseListener(this);
        item7.addMouseListener(this);
        item8.addMouseListener(this);

        // 窗体设置
        this.setLayout(null);
        this.setSize(1200, 730);
        this.setLocationRelativeTo(null); // 窗口居中显示
        this.setVisible(true);
        this.setResizable(false); // 窗体不可改变大小
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }



    // 时间控件方法
    private void configTimeArea() {
        Timer tmr = new Timer();
        tmr.scheduleAtFixedRate(new JLabelTimerTask(), new Date(), ONE_SECOND);
    }

    protected class JLabelTimerTask extends TimerTask {
        SimpleDateFormat dateFormatter = new SimpleDateFormat(DEFAULT_TIME_FORMAT);

        public void run() {
            time = dateFormatter.format(Calendar.getInstance().getTime());
            displayArea.setText(time);
        }
    }

    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == item1) {
            SignUp signUp = new SignUp();
           right.add(signUp.panel,"1");
             card.show(right,"1");
           this.repaint();

        }
        // 设置折叠按钮功能，先把南面板清空，让后在给各面板设置布局方式，添加按钮也添加子功能按钮，清楚别的子功能按钮

        if (e.getSource() == item6) {
            pSouth.removeAll();
            pNorth.setLayout(new GridLayout(2, 1));
            pSouth.setLayout(new GridLayout(2, 1));
            pNorth.add(item1);

            pNorth.add(item6);
            pSouth.add(item7);
            pSouth.add(item8);

            for (int i = 0; i < ftn.length; i++)
                subMenuContainer.add(ftn[i]);
            for (int i = 0; i < gtn.length; i++)
                subMenuContainer.remove(gtn[i]);
            for (int i = 0; i < htn.length; i++)
                subMenuContainer.remove(htn[i]);
            validate();
            getContentPane().repaint();
        }
        if (e.getSource() == item7) {
            pSouth.removeAll();
            pNorth.setLayout(new GridLayout(3, 1));
            pSouth.setLayout(new GridLayout(1, 1));
            pNorth.add(item1);

            pNorth.add(item6);
            pNorth.add(item7);
            pSouth.add(item8);

            for (int i = 0; i < ftn.length; i++)
                subMenuContainer.remove(ftn[i]);
            for (int i = 0; i < gtn.length; i++)
                subMenuContainer.add(gtn[i]);
            for (int i = 0; i < htn.length; i++)
                subMenuContainer.remove(htn[i]);
            validate();
            getContentPane().repaint();
        }
        if (e.getSource() == item8) {
            pSouth.removeAll();
            pNorth.setLayout(new GridLayout(4, 1));

            pNorth.add(item1);

            pNorth.add(item6);
            pNorth.add(item7);
            pNorth.add(item8);

            for (int i = 0; i < ftn.length; i++)
                subMenuContainer.remove(ftn[i]);
            for (int i = 0; i < gtn.length; i++)
                subMenuContainer.remove(gtn[i]);
            for (int i = 0; i < htn.length; i++)
                subMenuContainer.add(htn[i]);
            validate();
            getContentPane().repaint();
        }

        else if (e.getSource() == ftn[0]) {
            babydatapanel babydatapanel1 = null;
            try {
                babydatapanel1 = new babydatapanel();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            right.add(babydatapanel1.panel,"2");
            card.show(right,"2");
            this.repaint();

        }else if (e.getSource() == ftn[1]) {
            BabydataInput babydataInput1= new BabydataInput();
            right.add(babydataInput1.babydataInput,"3");
            card.show(right,"3");
            this.repaint();

        } else if (e.getSource() == gtn[0]) {
            Vul_Doctorpanel vul_doctorpanel = new Vul_Doctorpanel();
            right.add(vul_doctorpanel.panel,"4");
            card.show(right,"4");
            this.repaint();

        } else if (e.getSource() == gtn[1]) {

            Vul_DoctorInput vul_doctorInput = new Vul_DoctorInput();
            right.add(vul_doctorInput.Vul_InputPanel,"5");
            card.show(right,"5");
            this.repaint();
        } else if (e.getSource() == htn[0]) {
            card.show(right,"bg");
            this.repaint();

        }
        else if (e.getSource() == htn[1]) {
            newcode c = new newcode();
            right.add(c.panel,"6");
            card.show(right,"6");
            this.repaint();


        }
    }

    public void mouseEntered(MouseEvent arg0) {
    }

    public void mouseExited(MouseEvent arg0) {
    }

    public void mousePressed(MouseEvent arg0) {
    }

    public void mouseReleased(MouseEvent arg0) {
    }

}

