

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class newcode  extends  JPanel {
    Font f1 = new Font("隶书", 1, 20);
    ImageIcon bg = new ImageIcon("3.jpg");
    JLabel bgJLabel = new JLabel(bg);


    public static DefaultTableModel dtm1;

    JPanel panel = new JPanel();
    JButton save,cancel;
    JLabel label1 = new JLabel("请输入您的旧密码：");
    JLabel label2 = new JLabel("请输入您的新密码：");
    JLabel label3 = new JLabel("请确认您的新密码：");
    JPasswordField old_password = new JPasswordField(),new_password = new JPasswordField(),new_password1 = new JPasswordField();

    DbConnect dbUtil = new DbConnect();
    Connection con = dbUtil.getConnection();


    public newcode() {
        panel.setLayout(null);
        panel.setBounds(0,0,1045,515);


        label1.setBounds(300,100,220,50);
        label1.setFont(f1);
            label2.setBounds(300,160,220,50);
        label2.setFont(f1);
            label3.setBounds(300,230,220,50);
        label3.setFont(f1);

            old_password.setBounds(520,100,150,50);
            new_password.setBounds(520,160,150,50);
            new_password1.setBounds(520,230,150,50);

            panel.add(label1);
            panel.add(label2);
            panel.add(label3);
            panel.add(old_password);
            panel.add(new_password);
            panel.add(new_password1);

            save = new JButton("保存");
            cancel = new JButton("删除");
            save.setBounds(380,300,60,30);
            cancel.setBounds(480,300,60,30);
            panel.add(save);
            panel.add(cancel);
        ImageIcon bg = new ImageIcon("3.jpg");
        JLabel bgJLabel = new JLabel(bg);
        bgJLabel.setBounds(0, 0, 1045, 515);
        panel.add(bgJLabel);

        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int temp ;
                try {
                    Statement stm = con.createStatement();
                    ResultSet rs = stm.executeQuery("select  志愿者系统密码 from 志愿者系统管理 where 志愿者账号 = '"+Login.username+"'");
                   rs.next();
                    temp = rs.getInt(1);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                if (old_password.getText().equals("") || new_password.getText().equals("") || new_password1.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "请重新输入！", "错误", JOptionPane.ERROR_MESSAGE);
                } else if (!new_password.getText().equals(new_password1.getText()) ) {
                    JOptionPane.showMessageDialog(null, "新旧密码不一致，请重新输入!", "错误", JOptionPane.ERROR_MESSAGE);
                }
                else if (  Integer.parseInt(old_password.getText()) != temp) {
                    JOptionPane.showMessageDialog(null, "请输入正确的原始密码!", "错误", JOptionPane.ERROR_MESSAGE);
                } else {
                    int bcode = Integer.parseInt(new_password.getText());
                    try {
                        DbConnect dbUtil = new DbConnect();
                        Connection con = dbUtil.getConnection();
                        Statement stmt = con.createStatement(); // 创建一个数据库会话对象
                            int ok = JOptionPane.showConfirmDialog(null, "是否修改为新密码？", "确定", JOptionPane.YES_NO_OPTION,
                                    JOptionPane.INFORMATION_MESSAGE);
                            if (ok == JOptionPane.YES_OPTION) {
                                // 添加信息到数据库
                                String sql1 = "update 志愿者系统管理 set 志愿者系统密码 ="+bcode+" where 志愿者账号 ='"+Login.username+"'";
                                stmt.executeUpdate(sql1);
                                JOptionPane.showMessageDialog(null, "修改成功");
                                old_password.setText("");
                                new_password.setText("");
                                new_password1.setText("");

                        }
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }finally {
                    }
                }
            }
            });
        }





}

