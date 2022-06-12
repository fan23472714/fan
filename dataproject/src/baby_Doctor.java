

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class baby_Doctor  extends  JPanel {
    Font f1 = new Font("隶书", 1, 25);
    ImageIcon bg = new ImageIcon("3.jpg");
    JLabel bgJLabel = new JLabel(bg);

    private int flag = 0;
    String search,plan,plan1;
    JPanel panel = new JPanel();
    JButton save,cancel;
    JLabel label1 = new JLabel("咨询内容：");
    JLabel label2 = new JLabel("解决方案：");
    JLabel label3 = new JLabel("预防方案：");
    JTextArea tx1 = new JTextArea(),tx2 = new JTextArea(),tx3 = new JTextArea();

    DbConnect dbUtil = new DbConnect();
    Connection con = dbUtil.getConnection();


    public baby_Doctor() {
        panel.setLayout(null);
        panel.setBounds(0,0,1045,515);


        label1.setBounds(100,100,200,50);
        label1.setFont(f1);
        label2.setBounds(100,160,200,50);
        label2.setFont(f1);
        label3.setBounds(100,330,200,50);
        label3.setFont(f1);

        tx1.setBounds(320,100,300,50);
        tx2.setBounds(320,160,300,150);
        tx3.setBounds(320,330,300,150);
        tx2.setEditable(false);
        tx3.setEditable(false);
        tx2.setLineWrap(true);        //激活自动换行功能
        tx2.setWrapStyleWord(true);
        tx3.setLineWrap(true);        //激活自动换行功能
        tx3.setWrapStyleWord(true);

        panel.add(label1);
        panel.add(label2);
        panel.add(label3);
        panel.add(tx1);
        panel.add(tx2);
        panel.add(tx3);

        save = new JButton("查询");
        cancel = new JButton("取消");
        save.setBounds(690,100,60,30);
        cancel.setBounds(780,100,60,30);
        panel.add(save);
        panel.add(cancel);
        ImageIcon bg = new ImageIcon("3.jpg");
        JLabel bgJLabel = new JLabel(bg);
        bgJLabel.setBounds(0, 0, 1045, 515);
        panel.add(bgJLabel);

        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String searchText = tx1.getText().toString(); // 获取查询文本框框内容
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery("select * from 智慧诊疗"); // 执行SQL语句，返回结果集
                    while (rs.next()) {

                        search = rs.getString(1);// 获取登录的用户姓名
                        plan = rs.getString(2); // 获取数据库中的数据项的解决方案
                        plan1 = rs.getString(3);// 获取数据库中的数据项的预防方案
                        if (search.equals(searchText)) {// 判断数据库情况是否与文本框的值相同

                            flag= 1;

                            break;
                        }

                    }
                    if (flag == 1) {
                        JOptionPane.showMessageDialog(null, "查询成功");
                        tx2.setText(plan);
                        tx3.setText(plan1);
                    } else {
                        tx1.setText(""); // 错误的话则文本框内容设置为空，显示错误标签
                        tx2.setText("");
                        tx3.setText("");

                        JOptionPane.showMessageDialog(null, "查询错误，请咨询医生或者重新输入");
                    }
                } catch (SQLException e3) {
                    System.out.println(e3);
                }

            }
        });
    }





}

