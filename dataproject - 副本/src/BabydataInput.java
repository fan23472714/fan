
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
import javax.swing.JComboBox;
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

public class BabydataInput extends JFrame implements ActionListener {

    JPanel babydataInput = new JPanel();
    private JLabel inputTitle = new JLabel("儿童口腔健康基本信息录入");
    private JLabel la1, la2, la3, la4, la5, la6, la7, la8, la9, la10;
    private JTextField tx1,tx3,tx4,tx5,tx6,tx7, tx2, tx8, tx9,tx10;

    public JButton save, clean;

    private final String columnNames[];

    private Font laFont = new Font("宋体", Font.BOLD, 15);
    private JScrollPane JScrollPane1 = new JScrollPane();
    private static JTable table;
    private static DefaultTableModel dtm;
    private java.sql.Connection con = null;
    private Pattern pattern = Pattern.compile("[0-9]*");

    public BabydataInput() {
        // 医生信息录入界面设置
        babydataInput.setLayout(null);
        ImageIcon background = new ImageIcon("3.jpg"); // 背景图片
        JLabel label = new JLabel(background);
        inputTitle.setFont(new Font("宋体", Font.BOLD, 50));
        inputTitle.setBounds(60, 10, 1000, 50);
        babydataInput.add(inputTitle);

        // 录入操作面板设置
        final JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBorder(new TitledBorder(null, "录入操作", TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION, null, Color.red));
        panel.setBounds(60, 310, 950, 200);
        panel.setOpaque(false);

        la1 = new JLabel("儿童编号:"); // 标签设置
        la2 = new JLabel("儿童姓名:");
        la3 = new JLabel("儿童性别:");
        la4 = new JLabel("儿童年龄:");
        la5 = new JLabel("家庭住址:");
        la6 = new JLabel("监护人:");
        la7 = new JLabel("监护人联系方式:");
        la8 = new JLabel("口腔健康情况:");
        la9 = new JLabel("是否有龋坏:");
        la10 = new JLabel("是否有唇腭裂情况:");


        la1.setBounds(30, 20, 100, 40);
        la1.setFont(laFont);
        la2.setBounds(300, 20, 100, 40);
        la2.setFont(laFont);
        la3.setBounds(570, 20, 100, 40);
        la3.setFont(laFont);
        la4.setBounds(750, 20, 100, 40);
        la4.setFont(laFont);
        la5.setBounds(30, 70, 100, 40);
        la5.setFont(laFont);
        la6.setBounds(210, 70, 100, 40);
        la6.setFont(laFont);
        la7.setBounds(370, 70, 140, 40);
        la7.setFont(laFont);
        la8.setBounds(640, 70, 120, 40);
        la8.setFont(laFont);
        la9.setBounds(30, 120, 120, 40);
        la9.setFont(laFont);
        la10.setBounds(300, 120, 140, 40);
        la10.setFont(laFont);

        tx1 = new JTextField();
        tx1.setBounds(110, 25, 150, 30);
        tx1.setFont(laFont);
        tx2 = new JTextField();
        tx2.setBounds(380, 25, 150, 30);
        tx2.setFont(laFont);
        tx3 = new JTextField();
        tx3.setBounds(650, 25, 100, 25);
        tx3.setFont(laFont);
        tx4 = new JTextField();
        tx4.setBounds(830, 25, 100, 25);
        tx4.setFont(laFont);
        tx5 = new JTextField();
        tx5.setBounds(110, 75, 100, 25);
        tx5.setFont(laFont);
        tx6 = new JTextField();
        tx6.setBounds(270, 75, 100, 25);
        tx6.setFont(laFont);
        tx7 = new JTextField();
        tx7.setBounds(490, 70, 150, 30);
        tx7.setFont(laFont);
        tx8 = new JTextField();
        tx8.setBounds(750, 75, 150, 30);
        tx8.setFont(laFont);
        tx9 = new JTextField();
        tx9.setBounds(120, 125, 150, 30);
        tx9.setFont(laFont);
        tx10 = new JTextField();
        tx10.setBounds(440, 125, 150, 30);
        tx10.setFont(laFont);

        save = new JButton("保存");
        clean = new JButton("清空");
        save.setBounds(630, 150, 150, 30);
        clean.setBounds(780, 150, 150, 30);



        // 表格设置
        columnNames = new String[] { "儿童姓名","儿童编号","儿童性别","儿童年龄","家庭住址","监护人","监护人联系方式","口腔健康情况","是否有龋坏","是否有唇腭裂情况" };
        defaultTableModel();
        setTableColumnCenter();
        setbgcolor();
        JScrollPane1.setBounds(new Rectangle(60, 70, 935, 230));
        babydataInput.add(JScrollPane1);
        JScrollPane1.setViewportView(table); // 创建一个视口（如果有必要）并设置其视图
        table.getTableHeader().setReorderingAllowed(false); // 列不可拖动
        table.getTableHeader().setResizingAllowed(false); // 列宽不能改变
        table.setOpaque(false);//设置表格为透明
        String sql = "select * from 儿童口腔健康基本信息";
        databaseSearch(sql);

        // 组件添加
        panel.add(la1);
        panel.add(la2);
        panel.add(la3);
        panel.add(la4);
        panel.add(la5);
        panel.add(la6);
        panel.add(la7);
        panel.add(la8);
        panel.add(la9);
        panel.add(la10);
        panel.add(tx1);
        panel.add(tx2);
        panel.add(tx3);
        panel.add(tx4);
        panel.add(tx5);
        panel.add(tx6);
        panel.add(tx7);
        panel.add(tx8);
        panel.add(tx9);
        panel.add(tx10);
        panel.add(save);
        panel.add(clean);

        // 添加监听器
        save.addActionListener(this);
        clean.addActionListener(this);

        babydataInput.add(panel);

        babydataInput.add(label); // 添加背景
        label.setBounds(0, 0, 1100, 515);
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
            String[] data = new String[10];
            while (rs.next()) {
                for (int j = 1; j <= 10; j++) {
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
            tx4.setText("");
            tx5.setText("");
            tx6.setText("");
            tx7.setText("");
            tx8.setText("");
            tx10.setText("");

        }
        if (e.getSource() == save) {
            if (tx1.getText().equals("") || tx2.getText().equals("") || tx8.getText().equals("")
                    || tx10.getText().equals("") || tx9.getText().equals("")
                    ||tx3.getText().equals("")  ||tx4.getText().equals("")   ||tx5.getText().equals("")  ||tx6.getText().equals("")
                    ||tx7.getText().equals("")  ) {
                JOptionPane.showMessageDialog(null, "请输完整的医生信息！", "错误", JOptionPane.ERROR_MESSAGE);
            } else if (tx7.getText().length() != 11 || pattern.matcher(tx7.getText()).matches() == false) {
                JOptionPane.showMessageDialog(null, "请输入11位数手机号码！", "错误", JOptionPane.ERROR_MESSAGE);
            } else if (!tx3.getText().equals("男")&&!tx3.getText().equals("女")) {
                JOptionPane.showMessageDialog(null, "请输入正确的性别!", "错误", JOptionPane.ERROR_MESSAGE);
            }
            else if (!tx8.getText().equals("健康")&&!tx8.getText().equals("一般")&&!tx8.getText().equals("不健康") ) {
                JOptionPane.showMessageDialog(null, "请输入正确的健康情况（健康/一般/不健康）!", "错误", JOptionPane.ERROR_MESSAGE);
            }
            else if (!tx9.getText().equals("是")&&!tx9.getText().equals("否") ) {
                JOptionPane.showMessageDialog(null, "请输入正确的龋坏情况（是/否）!", "错误", JOptionPane.ERROR_MESSAGE);
            }
            else if (!tx10.getText().equals("是")&&!tx10.getText().equals("否") ) {
                JOptionPane.showMessageDialog(null, "请输入正确的龋坏情况（是/否）!", "错误", JOptionPane.ERROR_MESSAGE);
            } else {
                String bid = tx1.getText();
                try {
                    DbConnect dbUtil = new DbConnect();
                    Connection con = dbUtil.getConnection();
                    Statement stmt = con.createStatement(); // 创建一个数据库会话对象
                    ResultSet rs = stmt.executeQuery("select * from 儿童口腔健康基本信息 where 儿童编号='" + bid + "'"); // SQL语句
                    if (rs.next()) {
                        JOptionPane.showMessageDialog(null, "该儿童编号已存在，请重新输入!", "错误", JOptionPane.ERROR_MESSAGE);
                        rs.close();
                    } else {
                        int ok = JOptionPane.showConfirmDialog(null, "是否保存该儿童的口腔数据信息？", "确定", JOptionPane.YES_NO_OPTION,
                                JOptionPane.INFORMATION_MESSAGE);
                        if (ok == JOptionPane.YES_OPTION) {
                            // 添加信息到数据库

                            String sql1 = "INSERT INTO 儿童口腔健康基本信息(儿童编号,儿童姓名,儿童性别,儿童年龄,家庭住址,监护人,监护人联系方式,口腔健康情况,是否有龋坏,是否有唇腭裂情况)VALUES(?,?,?,?,?,?,?,?,?,?)";
                            PreparedStatement parepare1 = con.prepareStatement(sql1);

                            parepare1.setString(1, tx1.getText());
                            parepare1.setString(2, tx2.getText());
                            parepare1.setString(3, tx3.getText());
                            parepare1.setString(4,  tx4.getText());
                            parepare1.setString(5, tx5.getText());
                            parepare1.setString(6, tx6.getText());
                            parepare1.setString(7,  tx7.getText());
                            parepare1.setString(8,  tx8.getText());
                            parepare1.setString(9, tx9.getText());
                            parepare1.setString(10, tx10.getText());
                            parepare1.executeUpdate();

                            String data[] = new String[] { tx1.getText(), tx2.getText(),
                                    tx3.getText().toString(), tx4.getText(),
                                    tx5.getText(), tx6.getText(),
                                    tx7.getText(), tx8.getText(), tx9.getText(),
                                    tx10.getText() };
                            dtm.addRow(data); // 在表格添加一行刚添加的数据
                            JOptionPane.showMessageDialog(null, "录入成功");
                            tx1.setText("");
                            tx2.setText("");
                            tx3.setText("");
                            tx4.setText("");
                            tx5.setText("");
                            tx6.setText("");
                            tx7.setText("");
                            tx8.setText("");
                            tx9.setText("");
                            tx10.setText("");


                        }
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }finally {
                }
            }
        }
    }

}

