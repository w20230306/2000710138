package cn.edu.guet.main;

import cn.edu.guet.util.PasswordEncoder;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class Login extends JFrame {
    /*
    声明变量
     */
    private JTextField account;
    private JTextField password;
    private JPanel jPanel;
    private JButton login;

    public Login(String title) {
        super(title);
        setSize(400, 280);
        //获取屏幕大小
        Toolkit kit = Toolkit.getDefaultToolkit();
        //获取屏幕大小（int类型）
        Dimension screenSize = kit.getScreenSize();
        //通过屏幕大小获取宽度和长度
        int screenw = screenSize.width;
        //System.out.println(screenw);
        int screenh = screenSize.height;
        //System.out.println(screenh);
        //获取窗口大小
        int width = this.getWidth();
        //System.out.println(width);
        int height = this.getHeight();
        //设置窗口居中
        this.setLocation((screenw-width)/2, (screenh-height)/2);

        // 禁止窗口缩放
        setResizable(false);

        jPanel = (JPanel) this.getContentPane();
        jPanel.setLayout(null);//布局为空：自己任意安排组件的位置

        //界面标签
        JLabel jLabel= new JLabel("管理系统");
        jLabel.setFont(new Font("宋体", Font.PLAIN,20));
        jLabel.setBounds(156,50,100,30);
        jPanel.add(jLabel);
        //账号标签
        JLabel jLabel1= new JLabel("账号");
        jLabel1.setFont(new Font("宋体", Font.PLAIN,20));
        jLabel1.setBounds(50,120,100,35);
        jPanel.add(jLabel1);
        //密码标签
        JLabel jLabel2= new JLabel("密码");
        jLabel2.setFont(new Font("宋体", Font.PLAIN,20));
        jLabel2.setBounds(50,160,100,35);
        jPanel.add(jLabel2);

        account = new JTextField("zhangsan");
        account.setBounds(105, 120, 190, 35);
        jPanel.add(account);
        //password = new JTextField("zs1234");
        JPasswordField password =new JPasswordField("zs1234");
        password.setBounds(105, 160, 190, 35);
        jPanel.add(password);

        login = new JButton("登录");
        login.setBounds(105, 200, 190, 35);

        //给《登录按钮》添加监听事件，用匿名内部类的方式
        login.addActionListener(e -> {
            String username = account.getText();
            String pass = password.getText();
            /*
            如何把Java程序中的用户名和密码与数据库的进行比对，Java给我们提供了JDBC技术
            让我们可以使用Java程序对数据库进行SELECT、UPDATE、DELETE、INSERT各种操作，JDBC技术是固定的套路
             */
            String sql = "SELECT * " +
                    "FROM users " +
                    "WHERE username=?";
            //?是占位符
            Connection conn = null;
            ResultSet resultSet;
            PreparedStatement pstmt;//Statement：语句，PreparedStatement：预编译语句对象
            ResultSet rs;
            String url = "jdbc:oracle:thin:@101.43.210.111:1521:orcl";
            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                conn = DriverManager.getConnection(url, "wcd", "Grcl1234WcD");
                System.out.println(conn);//打印连接对象，如果不为空，则说明与Oracle数据库连接成功
                System.out.println("连接");
                if (conn != null) {
                    pstmt = conn.prepareStatement(sql);//把pstmt和sql做一个关联，pstmt就代表这个sql语句
                    pstmt.setString(1, username);
                    rs = pstmt.executeQuery();//rs是一个结果集，存储了我们的查询结果，结果集：内存区（保存了查询的数据）
                    //结果集中有一个游标（虚拟的看不到的指针），我们可以通过游标来获取数据
                    //如何判断登录成功还是失败呢？
                    //游标如果能向下移动，说明SQL语句查询到了数据，说明用户名和密码是对的
                    if (rs.next()) {
                        String encPass = rs.getString("PASSWORD");
                        PasswordEncoder passwordEncoder = new PasswordEncoder("这是我的盐");
                        boolean result = passwordEncoder.matches(encPass, pass);

                        // 如果密文比对成功，则隐藏《登录窗口》，显示QQ主界面
                        if (result == true) {
                            setVisible(false);//隐藏登录窗口（已经成功）
                            //显示主界面
                            Main main = new Main();
                            // 调用main对象的jFrame属性的setVisible方法
                            main.getjFrame().setVisible(true);
                        }else {
                            //弹出信息提示（窗口，信息，标题，图标）
                            JOptionPane.showMessageDialog(jPanel,"用户名或密码错误，请重新输入！","提示",JOptionPane.INFORMATION_MESSAGE);
                            //密码置空
                            password.setText("");
                        }
                    } else {
                        JOptionPane.showMessageDialog(jPanel,"用户名或密码错误，请重新输入！","提示",JOptionPane.INFORMATION_MESSAGE);
                        password.setText("");
                    }
                }
            } catch (ClassNotFoundException | SQLException ex) {
                ex.printStackTrace();
            } finally {
                try {
                    conn.close();//关闭数据库连接
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        jPanel.add(login);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Login("登录窗口");
    }
}