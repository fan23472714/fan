

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class SignUp {
    Font f1 = new Font("隶书", Font.BOLD, 30);
    public static JTable table;

    public static DefaultTableModel dtm;
    private JScrollPane JScrollPane = new JScrollPane();
    JPanel panel = new JPanel();
    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();
    JButton jButton ;
    private JLabel la1;
    private JTextField tx1, tx2;

    private DbConnect dbUtil = new DbConnect();
    private Connection con = dbUtil.getConnection();
    private String columnNames[] = {"志愿活动名称","志愿活动时间","志愿活动所需人数","志愿活动地点","志愿时长" };

    public SignUp() {

        panel.setLayout(null);
        panel.setBounds(0, 0, 1045, 510);
        panel1.setLayout(null);
        panel1.setBounds(0, 0, 1045, 30);
        panel2.setLayout(null);
        panel2.setBounds(0, 30, 1000, 510);

        //报名活动


        jButton = new JButton("报名活动");
        jButton.setBackground(Color.PINK);

        jButton.setBounds(490,0,150,30);
        panel1.add(jButton);
        panel.add(panel1);
        jButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("报名志愿活动");
                frame.setLayout(null);
                frame.setBounds(600,300,600,350);
                frame.setVisible(true);
                la1 = new JLabel("搜索活动名称");
                la1.setBounds(100,10,200,50);
                la1.setFont(new Font("宋体", Font.BOLD, 20));
                frame.add(la1);
                tx1 = new JTextField();
                tx1.setBounds(80,60,450,50);
                frame.add(tx1);
                JButton okButton = new JButton("报名");
                JButton cancelButton = new JButton("取消");
                okButton.setBounds(120,200,80,30);
                cancelButton.setBounds(200,200,80,30);
                frame.add(okButton);
                frame.add(cancelButton);
                ImageIcon bg = new ImageIcon("1.jpg");
                JLabel bgJLabel = new JLabel(bg);
                bgJLabel.setBounds(0, 0, 600, 350);
                frame.add(bgJLabel);

                okButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        String volname = tx1.getText();
                        System.out.println(volname);
                        try {
                            DbConnect dbUtil = new DbConnect();
                            Connection con = dbUtil.getConnection();
                            ResultSet rs,rs1,rs2,rs3;
                            Statement stmt = con.createStatement();
                            rs = stmt.executeQuery("select 志愿活动名称 from 志愿信息 where 志愿活动所需人数 >-1");
                            int sign = 0;
                            while(rs.next()){
                                System.out.println(rs.getString(1));
                                if(volname.equals(rs.getString(1))){
                                    //为志愿者报名活动
                                    rs1 =  stmt.executeQuery("select 志愿活动所需人数 from 志愿信息 where 志愿活动名称='"+volname+"'");
                                    rs1.next();
                                    int num1 = rs1.getInt(1)-1;

                                    rs2 =  stmt.executeQuery("select 志愿时长 from 志愿信息 where 志愿活动名称='"+volname+"'");
                                    rs2.next();
                                    int num2 = rs2.getInt(1);

                                    rs3 = stmt.executeQuery("select count(*) from 志愿者信息");
                                    rs3.next();
                                    int num3 = rs3.getInt(1);
                                    stmt.executeUpdate("update 志愿信息 set 志愿活动所需人数 ="+num1+" where 志愿活动名称 ='"+volname+"'");
                                    stmt.executeUpdate("insert into 志愿者信息 values ('"+(num3+1)+"','"+Login.username+"','"+volname+"',"+num2+")");
                                    tx1.setText(""); // 错误的话则文本框内容设置为空，显示错误标签
                                    JOptionPane.showMessageDialog(null, "报名成功");
                                    sign = 1;
                                    break;
                                }
                            }
                            if(sign == 0){
                                tx1.setText(""); // 错误的话则文本框内容设置为空，显示错误标签
                                JOptionPane.showMessageDialog(null, "报名失败");
                            }


                        }catch (Exception exception){
                            exception.printStackTrace();
                            System.out.println("error");
                        }


                    }
                });
                cancelButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        tx1.setText("");
                    }
                });



                    }
                });




        // 设置默认表格面板
        dtm = new DefaultTableModel(10, 5);
        table = new JTable(dtm) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }// 表格不允许被编辑 }
        };
        table.setBounds(0, 30, 1000, 510);
        table.setOpaque(false);
        String sql = "select * from 志愿信息";
        databaseSearch(sql, 5);

        JScrollPane jScrollPane=new JScrollPane();
        jScrollPane.setViewportView(table);// 给表格添加滚动条
        panel2.add(jScrollPane);
        jScrollPane.setBounds(30, 480, 1000, 30);
        panel2.add(table);
        setbgcolor();

        panel.add(panel2);
        ImageIcon bg = new ImageIcon("3.jpg");
        JLabel bgJLabel = new JLabel(bg);
        bgJLabel.setBounds(0, 0, 1045, 515);
        panel2.add(bgJLabel);


    }

    private void databaseSearch(String sql, int i) {
        // TODO Auto-generated method stub

        ResultSet rs;
        try {
            int rowcount = dtm.getRowCount() - 1;
            if (rowcount != -1) {
                for (int i1 = rowcount; i1 >= 0; i1--) {
                    dtm.removeRow(i1); // 删除Jtable中的所有行
                }
                dtm.setRowCount(0); // 将Jtable中的行数设为零
            }
            dtm.addRow(columnNames);
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            String[] data = new String[i];
            while (rs.next()) {
                for (int j = 1; j <=i; j++) {

                    data[j - 1] = rs.getString(j); // 取出数据库中的数组装载到数组中
                }
                dtm.addRow(data); // 在Jtable中添加数据行
            }

            con.close();
            // 设置表格隔行背景色（隔行背景色不同）
        } catch (Exception err) {
        }
    }
    private void setbgcolor() {
        // TODO Auto-generated method stub
        try {
            DefaultTableCellRenderer tcr = new DefaultTableCellRenderer() {
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                               boolean hasFocus, int row, int column) {
                    if (row % 2 == 0)
                        setBackground(new Color(223, 220, 239)); // 设置奇数行底色
                    else if (row % 2 == 1)
                        setBackground(Color.PINK); // 设置偶数行底色
                    return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                }
            };
            for (int i = 0; i < table.getColumnCount(); i++) {
                table.getColumn(table.getColumnName(i)).setCellRenderer(tcr);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}

