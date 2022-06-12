

import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Vul_Doctorpanel  extends  JPanel {
    Font f1 = new Font("隶书", Font.BOLD, 50);
    JLabel label = new JLabel("         系统已有智慧诊疗方案");
    public static JTable table1;
    ImageIcon bg = new ImageIcon("3.jpg");
    JLabel bgJLabel = new JLabel(bg);


    public static DefaultTableModel dtm1;

    JPanel panel = new JPanel();

    DbConnect dbUtil = new DbConnect();
    Connection con = dbUtil.getConnection();
    private String columnNames[] = {"口腔不健康情况","解决方案","预防方案" };

    public Vul_Doctorpanel() {
        panel.setLayout(null);
        panel.setBounds(0, 0, 1045, 510);;



        // 设置默认表格面板
        panel.add(bgJLabel);
        label.setBounds(0,0,1045,80);
        label.setFont(f1);
        panel.add(label);
        dtm1 = new DefaultTableModel(8,3 );
        table1 = new JTable(dtm1) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }// 表格不允许被编辑 }
        };
        table1.setOpaque(false);
        table1.setBounds(10, 80, 1000, 510);
        String sql = "select * from 智慧诊疗";
        databaseSearch1(sql, 3);

        JScrollPane jScrollPane=new JScrollPane();
        jScrollPane.setViewportView(table1);// 给表格添加滚动条
        panel.add(jScrollPane);
        jScrollPane.setBounds(30, 480, 1000, 30);
        panel.add(table1);
        setbgcolor(table1);

        ImageIcon bg = new ImageIcon("3.jpg");
        JLabel bgJLabel = new JLabel(bg);
        bgJLabel.setBounds(0, 0, 1045, 515);
        panel.add(bgJLabel);

    }



    private void databaseSearch1(String sql, int i) {
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

