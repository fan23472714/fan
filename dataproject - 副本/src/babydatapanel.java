

import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class babydatapanel  extends  JPanel{
    Font f1 = new Font("楷书", Font.BOLD, 18);
    Font f2 = new Font("楷书", Font.BOLD, 15);
    public static JTable table1;

    public static DefaultTableModel dtm1;
    private JScrollPane JScrollPane = new JScrollPane();

    JPanel panel = new JPanel();
    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();

    JLabel lab1 = new JLabel("健康情况:");
    JLabel lab2 = new JLabel("龋坏情况:");
    JLabel lab3 = new JLabel("唇腭裂:");

    private JTextArea tx1 = new JTextArea(), tx2 = new JTextArea(),tx3 = new JTextArea();

    DbConnect dbUtil = new DbConnect();
    Connection con = dbUtil.getConnection();
    private String columnNames[] = {"儿童编号","儿童姓名","儿童性别","儿童年龄","家庭住址","监护人联系方式","口腔健康情况","是否有龋坏","是否有唇腭裂情况" };

    public babydatapanel() throws SQLException {

        panel.setLayout(null);
        panel.setBounds(0, 0, 1045, 510);;
        panel1.setLayout(null);
        panel1.setBounds(0, 0, 1045, 100);
        panel2.setLayout(null);
        panel2.setBounds(0, 100, 1000, 410);
        panel.add(panel1);
        panel.add(panel2);


        //统计查询
        lab1.setBackground(Color.PINK);
        lab1.setBounds(20,0,80,80);
        lab1.setFont(f1);
        panel1.add(lab1);
        lab2.setBackground(Color.PINK);
        lab2.setBounds(360,0,80,80);
        lab2.setFont(f1);
        panel1.add(lab2);
        lab3.setBackground(Color.PINK);
        lab3.setBounds(700,0,80,80);
        lab3.setFont(f1);
        panel1.add(lab3);

        tx1.setBounds(120,0,240,80);
        tx1.setFont(f2);
        panel1.add(tx1);

        tx2.setBounds(450,0,240,80);
        tx2.setFont(f2);
        panel1.add(tx2);

        tx3.setBounds(780,0,240,80);
        tx3.setFont(f2);
        panel1.add(tx3);
        try {
            Statement stm = con.createStatement();
            String sql1 = "select avg(儿童口腔健康基本信息.儿童年龄) from 儿童口腔健康基本信息 where 是否有龋坏 ='是'";
            String sql2 = "select count(*) from 儿童口腔健康基本信息 where 是否有龋坏 ='是' and 儿童性别 = '女'";
            String sql3 = "select count(*) from 儿童口腔健康基本信息 where 是否有龋坏 ='是' and 儿童性别 = '男'";
            String sql4 = "select count(*) from 儿童口腔健康基本信息 ";
            String sql5 = "select count(*) from 儿童口腔健康基本信息 where 是否有龋坏 ='是'";
            ResultSet rs1 = stm.executeQuery(sql1);
            rs1.next();
            int avgage = rs1.getInt(1);
            rs1 = stm.executeQuery(sql2);
            rs1.next();
            int wonum = rs1.getInt(1);
            rs1 = stm.executeQuery(sql3);
            rs1.next();
            int mannum = rs1.getInt(1);
            rs1 = stm.executeQuery(sql4);
            rs1.next();
            int sum = rs1.getInt(1);
            rs1 = stm.executeQuery(sql5);
            rs1.next();
            int isqu = rs1.getInt(1);
            tx2.setText("平均患龋年龄："+avgage+"\n女生患龋:"+wonum+"\n男生患龋:"+mannum+"\n患龋率为:"+isqu/sum);

            String sql6 = "select count(*) from 儿童口腔健康基本信息 where 口腔健康情况 ='健康'";
            rs1 = stm.executeQuery(sql6);
            rs1.next();
            int ishealthy = rs1.getInt(1);
            tx1.setText("口腔健康人数为:"+ishealthy+"\n占比:"+ishealthy/sum);


            String sql8 = "select count(*) from 儿童口腔健康基本信息 where 是否有唇腭裂情况 ='是' and 儿童性别 = '女'";
            String sql9 = "select count(*) from 儿童口腔健康基本信息 where 是否有唇腭裂情况 ='是' and 儿童性别 = '男'";
            String sql10 = "select count(*) from 儿童口腔健康基本信息 ";
            String sql11 = "select count(*) from 儿童口腔健康基本信息 where 是否有唇腭裂情况 ='是'";
            rs1 = stm.executeQuery(sql8);
            rs1.next();
            int wonum1 = rs1.getInt(1);
            rs1 = stm.executeQuery(sql9);
            rs1.next();
            int mannum1 = rs1.getInt(1);
            rs1 = stm.executeQuery(sql10);
            rs1.next();
            int sum1 = rs1.getInt(1);
            rs1 = stm.executeQuery(sql11);
            rs1.next();
            int isqu1 = rs1.getInt(1);
            tx3.setText("女生患唇腭裂人数："+wonum1+"\n男生患唇腭裂:"+mannum1+"\n患唇腭裂率为:"+isqu1/sum);


        }catch (Exception e){
            e.printStackTrace();
        }



        panel.add(panel1);
        // 设置默认表格面板
        dtm1 = new DefaultTableModel(12, 10);
        table1 = new JTable(dtm1) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }// 表格不允许被编辑 }
        };
        table1.setBounds(0, 0, 1000, 510);
        table1.setOpaque(false);
        String sql = "select * from 儿童口腔健康基本信息";
        databaseSearch(sql, 10);

        JScrollPane jScrollPane=new JScrollPane();
        jScrollPane.setViewportView(table1);// 给表格添加滚动条
        panel2.add(jScrollPane);
        jScrollPane.setBounds(30, 480, 1000, 30);
        panel2.add(table1);
        setbgcolor(table1);


        ImageIcon bg = new ImageIcon("3.jpg");
        JLabel bgJLabel = new JLabel(bg);
        bgJLabel.setBounds(0, 0, 1045, 515);
        panel2.add(bgJLabel);
        panel.add(panel2);






    }


    private void databaseSearch(String sql, int i) {
        // TODO Auto-generated method stub

        ResultSet rs;
        try {
            int rowcount = dtm1.getRowCount() - 1;
            if (rowcount != -1) {
                for (int i1 = rowcount; i1 >= 0; i1--) {
                    dtm1.removeRow(i1); // 删除Jtable中的所有行
                }
                dtm1.setRowCount(0); // 将Jtable中的行数设为零
            }
            dtm1.addRow(columnNames);
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            String[] data = new String[i];
            while (rs.next()) {
                for (int j = 1; j <=i; j++) {

                    data[j - 1] = rs.getString(j); // 取出数据库中的数组装载到数组中
                }
                dtm1.addRow(data); // 在Jtable中添加数据行
            }

            con.close();
            // 设置表格隔行背景色（隔行背景色不同）
        } catch (Exception err) {
        }
    }



    private void setbgcolor(JTable table) {
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

