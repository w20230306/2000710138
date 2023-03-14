package cn.edu.guet.main;

import cn.edu.guet.bean.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author liwei
 * @Date 2023/3/12 12:21
 * @Version 1.0
 */
public class Main {

    private JFrame jFrame;
    private JPanel jPanel;

    JMenu jMenu = new JMenu("基础信息管理");
    JMenuBar jMenuBar = new JMenuBar();
    JMenuItem item01 = new JMenuItem("查看商品");

    JTable table;
    JScrollPane jscrollpane = new JScrollPane();
    private String columnNames[] = {"id", "商品名称", "单价", "类型"};
    private Object[][] rowData = null;

    public Main() {

        jFrame = new JFrame("主界面");
        jFrame.setSize(700, 400);
        jPanel = (JPanel) jFrame.getContentPane();
        jPanel.setLayout(null);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        item01.addActionListener(e -> {
            //去数据库把product表的数据取出来

            /*
            1、加载驱动
            2、创建连接
            3、声明PreparedsStatement对象
            4、执行SQL
            5、获取数据
            6、关闭连接
             */
            Connection conn = null;
            String url = "jdbc:oracle:thin:@43.139.94.243:1521:orcl";
            String sql = "SELECT * FROM product";
            PreparedStatement pstmt;
            ResultSet rs;
            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                conn = DriverManager.getConnection(url, "liwei", "Grcl1234LiweiU");
                pstmt = conn.prepareStatement(sql);
                rs = pstmt.executeQuery();
                List<Product> productList=new ArrayList<>();
                while (rs.next()) {
                    //取出的数据，放入一个容器（数据的载体）
                    Product product=new Product();
                    product.setId(rs.getInt("ID"));
                    product.setName(rs.getString("NAME"));
                    product.setPrice(rs.getFloat("PRICE"));
                    product.setType(rs.getString("TYPE"));

                    productList.add(product);//每循环一次把拿到的商品的数据存入集合，好比每摘一个芒果，把芒果放入桶里
                }
                rowData=new Object[productList.size()][columnNames.length];

                /*
                把集合的数据转成二维数组
                 */
                for(int i= 0;i<rowData.length;i++){
                    Product product=productList.get(i);
                    rowData[i]=new Object[]{product.getId(),product.getName(),product.getPrice(),product.getType()};
                }

                for(Product product:productList){
                    System.out.println("集合中的商品数据："+product.getName());
                }

            } catch (ClassNotFoundException | SQLException ex) {
                ex.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            //点击菜单内的代码
            table = new JTable(rowData, columnNames);
            jscrollpane.setBounds(0, 31, 700, 370);
            jscrollpane.setViewportView(table);
            table.setRowHeight(35);
            /**
             * 字居中显示设置
             */
            DefaultTableCellRenderer r = new DefaultTableCellRenderer();
            r.setHorizontalAlignment(JLabel.CENTER);
            table.setDefaultRenderer(Object.class, r);
            jPanel.add(jscrollpane);
        });
        jMenu.add(item01);
        jMenuBar.add(jMenu);
        jMenuBar.setBounds(0, 0, 100, 30);
        jPanel.add(jMenuBar);


        // jFrame.setVisible(true);
    }

    public JFrame getjFrame() {
        return jFrame;
    }
}
