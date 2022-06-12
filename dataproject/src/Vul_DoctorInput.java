
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Vul_DoctorInput extends JFrame implements ActionListener {

    JPanel Vul_InputPanel = new JPanel();
    private JLabel inputTitle = new JLabel("        系统已有智慧诊疗方案");
    private JLabel la1, la2, la3;
    private JTextField tx1,tx2,tx3;
    private JPasswordField tx11;
    public JButton save, clean;

    private final String columnNames[];

    private Font laFont = new Font("宋体", Font.BOLD, 15);
    private JScrollPane JScrollPane1 = new JScrollPane();
    private static JTable table;
    private static DefaultTableModel dtm;
    private java.sql.Connection con = null;
    private Pattern pattern = Pattern.compile("[0-9]*");

    public Vul_DoctorInput() {
        // 智慧诊疗方案录入界面设置
        Vul_InputPanel.setLayout(null);
        ImageIcon background = new ImageIcon("3.jpg"); // 背景图片
        JLabel label = new JLabel(background);
        inputTitle.setFont(new Font("宋体", Font.BOLD, 50));
        inputTitle.setBounds(60, 10, 1000, 50);
        Vul_InputPanel.add(inputTitle);

        // 录入操作面板设置
        final JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBorder(new TitledBorder(null, "录入操作", TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION, null, Color.CYAN));
        panel.setBounds(60, 310, 950, 200);
        panel.setOpaque(false);

        la1 = new JLabel("口腔不健康情况:"); // 标签设置
        la2 = new JLabel("解决方案:");
        la3 = new JLabel("预防方案:");



        la1.setBounds(100, 20, 140, 40);
        la1.setFont(laFont);
        la2.setBounds(100, 80, 140, 40);
        la2.setFont(laFont);
        la3.setBounds(100, 140, 140, 40);
        la3.setFont(laFont);


        tx1 = new JTextField();
        tx1.setBounds(240, 20, 450, 30);
        tx1.setFont(laFont);
        tx2 = new JTextField();
        tx2.setBounds(240, 80, 450, 30);
        tx2.setFont(laFont);
        tx3 = new JTextField();
        tx3.setBounds(240, 140, 450, 35);
        tx3.setFont(laFont);

        save = new JButton("保存");
        clean = new JButton("删除");
        save.setBounds(800, 150, 60, 30);
        save.setBackground(Color.ORANGE);
        clean.setBounds(880, 150, 60, 30);
        clean.setBackground(Color.orange);



        // 表格设置
        columnNames = new String[] {"口腔不健康情况","解决方案","预防方案" };
        defaultTableModel();
        setTableColumnCenter();
        setbgcolor();
        JScrollPane1.setBounds(new Rectangle(60, 70, 935, 230));
        Vul_InputPanel.add(JScrollPane1);
        JScrollPane1.setViewportView(table); // 创建一个视口（如果有必要）并设置其视图
        table.getTableHeader().setReorderingAllowed(false); // 列不可拖动
        table.getTableHeader().setResizingAllowed(false); // 列宽不能改变
        table.setOpaque(false);//设置为透明

        String sql = "select * from 智慧诊疗";
        databaseSearch(sql);

        // 组件添加
        panel.add(la1);
        panel.add(la2);
        panel.add(la3);

        panel.add(tx1);
        panel.add(tx2);
        panel.add(tx3);

        panel.add(save);
        panel.add(clean);

        // 添加监听器
        save.addActionListener(this);
        clean.addActionListener(this);

        Vul_InputPanel.add(panel);

        Vul_InputPanel.add(label); // 添加背景
        label.setBounds(0, 0, 1100, 700);
    }

    // 数据库数据录入到表格中
    private void databaseSearch(String sql) {
        // TODO Auto-generated method stub
        DbConnect dbUtil = new DbConnect();
        Connection con = dbUtil.getConnection();
        ResultSet rs;
        try {
            int rowcount = dtm.getRowCount() - 1;
            if (rowcount != -1) {
                for (int i1 = rowcount; i1 >= 0; i1--) {
                    dtm.removeRow(i1);
                }
                dtm.setRowCount(0);
            }
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            String[] data = new String[3];
            while (rs.next()) {
                for (int j = 1; j <= 3; j++) {
                    data[j - 1] = rs.getString(j);
                }
                dtm.addRow(data);
            }
            con.close();
        } catch (Exception err) {
            String error = err.getMessage();
            JOptionPane.showMessageDialog(null, error);
            err.printStackTrace();
        } finally {
            dbUtil.close();
        }

    }

    // 设置表格不可编辑
    public void defaultTableModel() {
        dtm = new DefaultTableModel(columnNames, 0);
        table = new JTable(dtm) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
    }

    // 设置表格内容居中显示
    public void setTableColumnCenter() {
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, r);
    }

    // 设置表格隔行背景颜色不同
    public static void setbgcolor() {
        try {
            DefaultTableCellRenderer tcr = new DefaultTableCellRenderer() {
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                               boolean hasFocus, int row, int column) {
                    if (row % 2 == 0)
                        setBackground(new Color(223, 220, 239)); // 设置奇数行底色
                    else if (row % 2 == 1)
                        setBackground(Color.white); // 设置偶数行底色
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

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == clean) {
            tx1.setText("");
            tx2.setText("");
            tx3.setText("");

        }
        if (e.getSource() == save) {
            if (tx1.getText().equals("") || tx2.getText().equals("") ||tx3.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "请输完整的智慧诊疗方案！", "错误", JOptionPane.ERROR_MESSAGE);
            } else {
                String bid = tx1.getText();
                try {
                    DbConnect dbUtil = new DbConnect();
                    Connection con = dbUtil.getConnection();
                    Statement stmt = con.createStatement(); // 创建一个数据库会话对象

                        int ok = JOptionPane.showConfirmDialog(null, "是否保存该智慧诊疗方案？", "确定", JOptionPane.YES_NO_OPTION,
                                JOptionPane.INFORMATION_MESSAGE);
                        if (ok == JOptionPane.YES_OPTION) {
                            // 添加信息到数据库

                            String sql1 = "INSERT INTO 智慧诊疗(口腔不健康情况,解决方案,预防方案)VALUES(?,?,?)";
                            PreparedStatement parepare1 = con.prepareStatement(sql1);

                            parepare1.setString(1, tx1.getText());
                            parepare1.setString(2, tx2.getText());
                            parepare1.setString(3, tx3.getText());
                            parepare1.executeUpdate();

                            String data[] = new String[] { tx1.getText(), tx2.getText(),
                                    tx3.getText()};
                            dtm.addRow(data); // 在表格添加一行刚添加的数据
                            JOptionPane.showMessageDialog(null, "录入成功");
                            tx1.setText("");
                            tx2.setText("");
                            tx3.setText("");




                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }finally {

                }
            }
        }
    }

}

