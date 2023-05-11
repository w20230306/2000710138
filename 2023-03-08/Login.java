package cn.edu.guet.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

/**
 * @Author liwei
 * @Date 2023/3/6 19:47
 * @Version 1.0
 */
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
        // 禁止窗口缩放
        setResizable(false);
        jPanel = (JPanel) this.getContentPane();
        jPanel.setLayout(null);//布局为空：自己任意安排组件的位置
        account = new JTextField("zhangsan");

        account.setBounds(105, 120, 190, 35);
        jPanel.add(account);
        password = new JTextField("zs1234");
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
            /*
            1、加载驱动
            2、创建连接（和数据库的连接）
            3、写SQL
            4、创建Statement对象与SQL语句关联
             */
            String sql = "SELECT * " +
                    "FROM users " +
                    "WHERE username=? AND password=?";
            PreparedStatement pstmt;
            //?是占位符
            Connection conn = null;
            ResultSet resultSet;
            String url = "jdbc:oracle:thin:@43.139.94.243:1521:orcl";
            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                conn = DriverManager.getConnection(url, "liwei", "Grcl1234LiweiU");
                System.out.println(conn);//打印连接对象，如果不为空，则说明与Oracle数据库连接成功
                if (conn != null) {
                    System.out.println("连接成功");
                    pstmt = conn.prepareStatement(sql);//把pstmt和sql语句做关联
                    pstmt.setString(1, username);
                    pstmt.setString(2, pass);
                    //执行SQL语句
                    resultSet = pstmt.executeQuery();//执行查询，得到一个结果集：内存区（存储了你查询到的结果），内存区有一个"游标"
                    //如何判断登录时成功还是失败？
                    if (resultSet.next()) {
                        System.out.println("登录成功");
                    } else {
                        System.out.println("用户名或密码错误");
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
