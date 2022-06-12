
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Login extends JFrame {

    // 输入的用户Id
    public static String userId;
    // 输入的用户名
    public static String username;
    // 输入的密码
    public static String password;
    // 验证标识
    int loginFlag = 0;
    private static final long serialVersionUID = 1L;
    DbConnect dbUtil = new DbConnect();
    Connection con = dbUtil.getConnection();

    // 账号
    JLabel accountJLabel = new JLabel("账号：");
    // 错误提示标签
    JLabel errorJLabel = new JLabel("用户名或者密码不对，请重新输入");
    // 密码
    JLabel passwordJLabel = new JLabel("密码：");

    // r1：医学志愿者 r2：儿童用户
    public JRadioButton r1, r2;
    ImageIcon bg = new ImageIcon("3.jpg");
    JLabel bgJLabel = new JLabel(bg);

    JButton loginJButton = new JButton("登录");
    JButton cancelJButton = new JButton("取消");

    private boolean flag;
    static JTextField usernameJTextField = new JTextField();
    static JPasswordField passwordJPasswordField = new JPasswordField();

    Login(String sTitle) {
        super(sTitle);
        Toolkit tk = getToolkit();
        Image icon = tk.getImage("4.jpg");
        setIconImage(icon);
        this.setLayout(null);
        this.add(errorJLabel); // 添加控件

        this.add(accountJLabel);
        this.add(passwordJLabel);
        this.add(loginJButton);
        this.add(cancelJButton);

        this.add(usernameJTextField);
        this.add(passwordJPasswordField);

        final JRadioButton r1 = new JRadioButton("医学志愿者");
        final JRadioButton r2 = new JRadioButton("儿童用户");

        ButtonGroup rg = new ButtonGroup();
        this.add(r2);
        rg.add(r1);
        this.add(r1);
        rg.add(r2);
        r1.setBounds(180, 180, 160, 30);
        r2.setBounds(300, 180, 80, 30);
        r1.setFocusPainted(false);
        r2.setFocusPainted(false);
        r1.setContentAreaFilled(false);
        r2.setContentAreaFilled(false);

        errorJLabel.setBounds(100, 130, 200, 50);
        errorJLabel.setForeground(Color.black);
        errorJLabel.setVisible(false);

        bgJLabel.setBounds(0, 0, 592, 350);

        //取消监听
        cancelJButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        // 登录监听
        loginJButton.addActionListener(new ActionListener() {
            public boolean flag = false;

            public void actionPerformed(ActionEvent e) {


                // 医学志愿者
                if (r1.isSelected()) {
                    try {
                        String usernameText = usernameJTextField.getText().toString(); // 获取帐号文本框内容
                        String passwordText = passwordJPasswordField.getText().toString(); // 获取密码文本框内容

                        Statement stmt = con.createStatement();
                        ResultSet rs = stmt.executeQuery("select * from 志愿者系统管理"); // 执行SQL语句，返回结果集
                        while (rs.next()) {

                            userId = rs.getString(1); // 获取登录的用户编号，
                            username = rs.getString(1);// 获取登录的用户姓名
                            password = rs.getString(2); // 获取数据库中的数据项的密码
                            if (username.equals(usernameText) && password.equals(passwordText)) {// 判断数据库的用户编号以及密码是否与文本框的值相同
                                loginFlag = 1;
                                break;
                            }
                        }
                        // 登录成功
                        if (loginFlag == 1) {
                            JOptionPane.showMessageDialog(null, "登录成功");
                            System.out.println("医学志愿者登陆成功");
                            VolunteerFrame mainPanelCashier = new VolunteerFrame("志愿者服务");

                            Login.this.setVisible(false);// 关闭登录按钮
                            // 登录失败
                        } else {
                            usernameJTextField.setText(""); // 错误的话则文本框内容设置为空，显示错误标签
                            passwordJPasswordField.setText("");
                            JOptionPane.showMessageDialog(null, "登陆错误");
                        }
                    } catch (SQLException e3) {
                        System.out.println(e3);
                    }

                }
                // 儿童用户
                else if (r2.isSelected()) {

                    try {
                        String usernameText = usernameJTextField.getText().toString(); // 获取帐号文本框内容
                        String passwordText = passwordJPasswordField.getText().toString(); // 获取密码文本框内容

                        Statement stmt = con.createStatement();
                        ResultSet rs = stmt.executeQuery("select * from 儿童用户系统管理"); // 执行SQL语句，返回结果集
                        while (rs.next()) {

                            userId = rs.getString(1); // 获取登录的用户编号，
                            username = rs.getString(1);// 获取登录的用户姓名
                            password = rs.getString(2); // 获取数据库中的数据项的密码
                            if (username.equals(usernameText) && password.equals(passwordText)) {// 判断数据库的用户编号以及密码是否与文本框的值相同

                                loginFlag = 1;

                                break;
                            }

                        }
                        if (loginFlag == 1) {
                            JOptionPane.showMessageDialog(null, "登录成功");
                            new babyframe("儿童用户页面"); // 显示系统主界面
                            System.out.println("儿童用户登陆成功");
                            Login.this.setVisible(false);// 关闭登录按钮
                        } else {
                            usernameJTextField.setText(""); // 错误的话则文本框内容设置为空，显示错误标签
                            passwordJPasswordField.setText("");

                            JOptionPane.showMessageDialog(null, "登陆错误");
                        }
                    } catch (SQLException e3) {
                        System.out.println(e3);
                    }

                } else if (r1.isSelected() == false && r2.isSelected() == false ) {
                    JOptionPane.showMessageDialog(null, "请选择用户类型");

                }

            }
        });

        // 登录按钮添加功能事件

        // 账号
        accountJLabel.setBounds(150, 50, 100, 50);
        accountJLabel.setFont(new Font("", 1, 20));

        // 密码
        passwordJLabel.setBounds(150, 120, 100, 50);
        passwordJLabel.setFont(new Font("", 1, 20));

        // 登录
        loginJButton.setBounds(160, 220, 100, 40);
        loginJButton.setBackground(Color.PINK);

        // 取消
        cancelJButton.setBounds(300, 220, 100, 40);
        cancelJButton.setBackground(Color.PINK);

        // 账号输入框
        usernameJTextField.setBounds(250, 60, 150, 30);
        // 密码输入框
        passwordJPasswordField.setBounds(250, 120, 150, 30);

        this.add(bgJLabel);
        this.setVisible(true);
        this.setSize(600, 350); // 设置窗口大小
        this.setResizable(false); // 设置不可调整窗口大小
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

}
