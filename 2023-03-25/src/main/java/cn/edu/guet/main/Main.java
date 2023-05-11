package cn.edu.guet.main;

import cn.edu.guet.bean.Product;
import cn.edu.guet.pay.WXPay;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Author liwei
 * @Date 2023/3/12 12:21
 * @Version 1.0
 */
public class Main {
    public static void main(String[] args) {
        new Main();

    }

    private JFrame jFrame;
    private JPanel jPanel;
    List<Product> salesList = new ArrayList<>();
    List<Product> productList = new ArrayList<>();
    private int productSize;
    static  int selectedRow,selectedRow1;
    static DefaultTableModel model,model1;
    static JTable table,table1;
    JButton item0 = new JButton("首页"),
            item01 = new JButton("查看商品"),
            item02 = new JButton("销售商品"),
            item03 = new JButton("管理商品");
    JLabel[] product ;
    String[][] str ;
    String[] strM ;
    ImageIcon[] photo;


    JScrollPane jscrollpane = new JScrollPane(),
    jScrollPane1 = new JScrollPane();
    private String columnNames1[] = {"ID", "商品名称", "单价", "类型" , "数量"},
    columnNames2[] = {"商品名称", "单价","类型","已点份数"},
    columnNames3[] = {"ID", "商品名称", "单价", "类型" , "数量", "图片"};
    private Object[][] rowData1 ,
     rowData2={{"","","","",""}};

    public Main() {
        Toolkit kit = Toolkit.getDefaultToolkit();
        //获取屏幕大小（int类型）
        Dimension screenSize = kit.getScreenSize();
        //通过屏幕大小获取宽度和长度
        int screenw = screenSize.width;
        //System.out.println(screenw);
        int screenh = screenSize.height;
        int width = (int)(screenw*0.7);
        //System.out.println(width);
        int height = (int)(screenh*0.7);


        //设置窗口居中
        jFrame = new JFrame("主界面");
        jFrame.setBounds((screenw-width)/2, (screenh-height)/2, width, height);
        jPanel = (JPanel) jFrame.getContentPane();
        jPanel.setLayout(null);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container container=jFrame.getContentPane();
        container.setLayout(null);


        //面板1(查看)
        JPanel jPanel1=new JPanel();
        jPanel1.setLayout(null);
        jPanel1.setBounds(0,30,width,height);
        jPanel1.setVisible(false);
        container.add(jPanel1);

        //面板2（销售）
        JPanel jPanel2=new JPanel();
        jPanel2.setLayout(null);
        jPanel2.setBounds(0,30,width,height);
        jPanel2.setVisible(false);
        container.add(jPanel2);

        //面板3（商品按钮）
        JPanel jPanel3=new JPanel();
        jPanel3.setLayout(new GridLayout(0,4,0,0));
        jPanel3.setBounds(width/2,0,width/2,height/2);
        jPanel3.setVisible(false);

        //面板4（首页）
        JPanel jPanel4=new JPanel();
        jPanel4.setLayout(null);
        jPanel4.setBounds(0,30,width,height);
        jPanel4.setBackground(Color.green);
        jPanel4.setVisible(false);
        container.add(jPanel4);

        //面板5（管理）
        JPanel jPanel5=new JPanel();
        jPanel5.setLayout(null);
        jPanel5.setBounds(0,30,width,height);
        jPanel5.setVisible(false);
        container.add(jPanel5);


        JButton delete = new JButton("删除");
        JButton payButton = new JButton("结账");
        JButton addRow = new JButton("添加商品");
        JButton update = new JButton("修改商品");
        JButton deleteRow = new JButton("删除商品");
        addRow.setBounds(width/2-200,height/2,100,30);
        update.setBounds(width/2-50,height/2,100,30);
        deleteRow.setBounds(width/2+100,height/2,100,30);
        delete.setBounds(width/2-200,height/2 , 100, 30);
        payButton.setBounds(100,height/2, 100, 30);
        item0.setBounds(0,0,100,30);
        item01.setBounds(100,0,100,30);
        item03.setBounds(200,0,100,30);
        item02.setBounds(300,0,100,30);
        jPanel.add(item0);
        jPanel.add(item01);
        jPanel.add(item02);
        jPanel.add(item03);
        jFrame.setVisible(true);
        selectProduct();

        model = new DefaultTableModel(rowData1, columnNames3);
        model1 = new DefaultTableModel(rowData2, columnNames2);
        table = new JTable(model);
        table1 = new JTable(model1);




        payButton.addActionListener(e -> {
            /*
            开始结账
             */
            float price = 0;
            int number0 = 0,number = 0;
            String name="";

            try{

            for (int i = 0; i < rowData2.length; i++) {
                String str[] = (String[]) rowData2[i];
                name+=str[0]+";";
                number = Integer.parseInt((str[3]));
                price +=  Float.parseFloat(str[1]) * number;

                for(int j=0;j<productList.size();j++) {
                    if(str[0].equals(productList.get(j).getName())) {
                        number0=productList.get(j).getNumber();
                        if(number0>=number) {
                            productList.get(j).setNumber(number0 - number);
                            updateProduct(productList.get(j));
                        }
                    }
                }

            }
               WXPay.unifiedOrder(name, (int) (price*100));

            /*
            显示二维码
             */
            JFrame jFrame=new JFrame("结账页面");
            jFrame.setBounds(screenw/2-152, screenh/2-165,500, 500);

            JLabel pay=new JLabel();
            ImageIcon icon=new ImageIcon("pay.jpg");
            icon.setImage(icon.getImage().getScaledInstance(300,300, Image.SCALE_DEFAULT));
            pay.setVerticalTextPosition(JLabel.TOP);
            pay.setHorizontalTextPosition(JLabel.CENTER);
            pay.setBounds(80,30,400,400);
            String strMsg1 = name;
            String strMsg = "<html><body bgcolor='#EAEAE0' color='black'><font size='20'>&nbsp;" +strMsg1+ "<br>&nbsp;&nbsp;&nbsp;¥" +price+ "</font><body></html>";
            pay.setText(strMsg);
            pay.setIcon(icon);

            JPanel jPanel= (JPanel) jFrame.getContentPane();
            jPanel.setLayout(null);
            jPanel.add(pay);

            jFrame.setVisible(true);

            }catch (Exception ex){
                JOptionPane.showMessageDialog(jPanel,"请选择商品！","提示",JOptionPane.INFORMATION_MESSAGE);
            }






        });

        item0.addActionListener(e -> {
            JLabel text = new JLabel("<html><body>&nbsp;&nbsp;XXXX商店<br>&nbsp;&nbsp;欢迎光临!<body></html>");
            text.setBounds(width/2-400,height/2-200,800,300);
            text.setFont(new Font("宋体",Font.PLAIN,100));
            jPanel4.add(text);

            jPanel1.setVisible(false);
            jPanel2.setVisible(false);
            jPanel4.setVisible(true);
        });

        item01.addActionListener(e -> {
            productList = new ArrayList<>();
            rowData1 = selectProduct();
            JTable table1 = new JTable(rowData1, columnNames1);

            //设置表头高度
            table1.getTableHeader().setPreferredSize(new Dimension(table1.getColumn("ID").getPreferredWidth(), 35));
            //表头居中
            ((DefaultTableCellRenderer) table1.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
            table1.setRowHeight(35);

            /**
             * 表数据居中显示
             */
            DefaultTableCellRenderer r = new DefaultTableCellRenderer();
            r.setHorizontalAlignment(r.CENTER);
            table1.setDefaultRenderer(Object.class, r);
            jscrollpane.setBounds(0, 0, width, height/2);
            jscrollpane.setViewportView(table1);
            jPanel1.add(jscrollpane);

            jPanel1.repaint();
            jPanel1.setVisible(true);
            jPanel2.setVisible(false);
            jPanel4.setVisible(false);
            jPanel5.setVisible(false);

        });



        item03.addActionListener(e -> {

            productList = new ArrayList<>();
            rowData1 = selectProduct();
            model = new DefaultTableModel(rowData1, columnNames3);
            table = new JTable(model);

            //设置表头高度
            table.getTableHeader().setPreferredSize(new Dimension(table.getColumn("ID").getPreferredWidth(), 35));
            //表头居中
            ((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
            table.setRowHeight(35);

            /**
             * 表数据居中显示
             */
            DefaultTableCellRenderer r = new DefaultTableCellRenderer();
            r.setHorizontalAlignment(r.CENTER);
            table.setDefaultRenderer(Object.class, r);
            jscrollpane.setBounds(0, 0, width, height/2);
            jscrollpane.setViewportView(table);
            jPanel5.add(jscrollpane);


            jPanel5.add(addRow);
            jPanel5.add(update);
            jPanel5.add(deleteRow);
            jPanel5.repaint();
            jPanel5.setVisible(true);
            jPanel1.setVisible(false);
            jPanel2.setVisible(false);
            jPanel4.setVisible(false);



        });

        deleteRow.addActionListener(e1 -> {
            try {
                selectedRow = table.getSelectedRow();
                model.removeRow(selectedRow);
                deleteProduct(selectedRow);
            }catch (IndexOutOfBoundsException e2){
                JOptionPane.showMessageDialog(jPanel,"请选择要删除的商品！","提示",JOptionPane.INFORMATION_MESSAGE);
            }
        });

        update.addActionListener(e -> {
            try {

                selectedRow = table.getSelectedRow();
                Product product = productList.get(selectedRow);

            String[] productUpdate = {String.valueOf(product.getId()),product.getName(), String.valueOf(product.getPrice()),product.getType(), String.valueOf(product.getNumber()),product.getPath()};
            JFrame update_jFrame=new JFrame("修改商品");
            update_jFrame.setBounds(screenw/2-152, screenh/2-165,300, 300);
            JPanel update_jPanel = new JPanel();
            update_jPanel.setLayout(null);
            JTextField[] textFields = new JTextField[columnNames3.length];
            JLabel[] jLabels = new JLabel[columnNames3.length];


            for(int i=0;i< columnNames3.length;i++){
                jLabels[i] = new JLabel(columnNames3[i]+":");
                textFields[i] = new JTextField(productUpdate[i]);
                jLabels[i].setFont(new Font("宋体", Font.PLAIN,13));
                jLabels[i].setBounds(20,i*35+10,80,30);
                textFields[i].setBounds(80,i*35+10,200,30);
                update_jPanel.add(jLabels[i]);
                update_jPanel.add(textFields[i]);

            }

            JButton update_jButton =new JButton("确认修改");
            update_jButton.setBounds(100,220,100,30);
            update_jPanel.add(update_jButton);

            update_jButton.addActionListener(e1 -> {
                product.setId(Integer.parseInt(textFields[0].getText()));
                product.setName(textFields[1].getText());
                product.setPrice(Float.valueOf(textFields[2].getText()));
                product.setType(textFields[3].getText());
                product.setNumber(Integer.parseInt(textFields[4].getText()));
                product.setPath(textFields[5].getText());
                updateProduct(product);
                //弹出信息提示（窗口，信息，标题，图标）
                JOptionPane.showMessageDialog(update_jPanel,"成功修改","提示",JOptionPane.INFORMATION_MESSAGE);
                selectProduct();

                update_jFrame.setVisible(false);
                jscrollpane.repaint();

            });

            update_jFrame.add(update_jPanel);
            update_jFrame.setVisible(true);
            update_jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            }catch (IndexOutOfBoundsException e1){
                JOptionPane.showMessageDialog(jPanel,"请选择要修改的商品！","提示",JOptionPane.INFORMATION_MESSAGE);
            }
        });

        addRow.addActionListener(e -> {
            Product product = new Product();
            JFrame add_jFrame=new JFrame("增加商品");
            add_jFrame.setBounds(screenw/2-152, screenh/2-165,300, 300);
            JPanel add_jPanel = new JPanel();
            add_jPanel.setLayout(null);
            JTextField[] textFields = new JTextField[columnNames3.length];
            JLabel[] jLabels = new JLabel[columnNames3.length];


            for(int i=0;i< columnNames3.length;i++){
                jLabels[i] = new JLabel(columnNames3[i]+":");
                textFields[i] = new JTextField("");
                jLabels[i].setFont(new Font("宋体", Font.PLAIN,13));
                jLabels[i].setBounds(20,i*35+10,80,30);
                textFields[i].setBounds(80,i*35+10,200,30);
                add_jPanel.add(jLabels[i]);
                add_jPanel.add(textFields[i]);

            }

            //添加按钮
            JButton add_jButton =new JButton("确认添加");
            add_jButton.setBounds(100,220,100,30);
            add_jPanel.add(add_jButton);

            add_jButton.addActionListener(ex -> {
                product.setId(Integer.parseInt(textFields[0].getText()));
                product.setName(textFields[1].getText());
                product.setPrice(Float.valueOf(textFields[2].getText()));
                product.setType(textFields[3].getText());
                product.setNumber(Integer.parseInt(textFields[4].getText()));
                product.setPath(textFields[5].getText());

                addProduct(product);

                //置空
                for(int i=0;i< columnNames3.length;i++){
                    textFields[i].setText("");
                }

                //弹出信息提示（窗口，信息，标题，图标）
                JOptionPane.showMessageDialog(add_jPanel,"成功添加","提示",JOptionPane.INFORMATION_MESSAGE);
                add_jFrame.setVisible(false);
                selectProduct();
                jscrollpane.repaint();

            });

            add_jFrame.add(add_jPanel);
            add_jFrame.setVisible(true);
            add_jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        });




        item02.addActionListener(e -> {

            model1=new DefaultTableModel(rowData2,columnNames2);
            table1 = new JTable(model1);

            //JTable table = new JTable(rowData2,columnNames2);
            //设置表头高度
           table1.getTableHeader().setPreferredSize(new Dimension(table1.getColumn("商品名称").getPreferredWidth(), 35));
            //表头居中
           ((DefaultTableCellRenderer) table1.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
            table1.setRowHeight(35);
            /**
             * 表数据居中显示
             */
            DefaultTableCellRenderer r = new DefaultTableCellRenderer();
            r.setHorizontalAlignment(r.CENTER);
            table1.setDefaultRenderer(Object.class, r);
            jscrollpane.setBounds(0, 0, width/2, height/2);
            jscrollpane.setViewportView(table1);
            jPanel2.add(jscrollpane);


            jPanel2.add(payButton);
            jPanel2.add(delete);
            jPanel2.repaint();
            jPanel2.setVisible(true);
            jPanel1.setVisible(false);
            jPanel4.setVisible(false);
            jPanel5.setVisible(false);


            product =new JLabel[productSize];
            str =new  String[productSize][2];
            strM =new String[productSize];
            photo=new ImageIcon[productSize];

            jPanel3.removeAll();
            for (int i = 0; i < productSize; i++) {
                str[i][0] = productList.get(i).getName();
                str[i][1] = String.valueOf(productList.get(i).getPrice());
                strM[i] = "<html><body bgcolor='#EEEEE4' color='black'>&nbsp;&nbsp;&nbsp;&nbsp"
                        + str[i][0] + "&nbsp;&nbsp;&nbsp;&nbsp<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;¥"
                        + str[i][1] + "<body></html>";
                photo[i] = new ImageIcon(productList.get(i).getPath());
                photo[i].setImage(photo[i].getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
                product[i] = new JLabel(strM[i], photo[i], SwingConstants.CENTER);
                product[i].setHorizontalTextPosition(JLabel.CENTER);
                product[i].setVerticalTextPosition(JLabel.BOTTOM);
                product[i].setSize(150,150);
                jPanel3.add(product[i]);
            }

            jPanel3.revalidate();

            jScrollPane1.setBounds(width/2, 0, width/2, height/2);
            jScrollPane1.setViewportView(jPanel3);
            jPanel2.add(jScrollPane1);
            jPanel3.setVisible(true);
            jPanel2.repaint();

            for (int i = 0; i < productSize; i++) {
                int finalI = i;
                product[i].addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        //把所选的菜品添加到左侧
                        Product sale = productList.get(finalI);
                        Product sale1 = new Product(sale.getId(), sale.getName(), sale.getPrice(), sale.getType(), sale.getNumber(), sale.getPath());
                        addSale(sale1);
                        jscrollpane.repaint();
                    }
                    @Override
                    public void mousePressed(MouseEvent e) {
                    }
                    @Override
                    public void mouseReleased(MouseEvent e) {
                    }
                    @Override
                    public void mouseEntered(MouseEvent e) {
                    }
                    @Override
                    public void mouseExited(MouseEvent e) {
                    }
                });

            }

        });


        delete.addActionListener(e -> {
            try {
                selectedRow1 = table1.getSelectedRow();
                model1.removeRow(selectedRow1);
                delectSale(selectedRow1);

            }catch (IndexOutOfBoundsException ex){

            }
        });






       /* String strMsg1 = "宫保鸡丁";
        String strMsg2 = "58.00";
        String url = "'http://rs209p3t9.hn-bkt.clouddn.com/gbjd.jpeg?e=1679710812&token=Vh5IcHOQV-uDk9R8WEpCegKEw7CLP-CrCaJC5opk:YG_tQ9YTed1-f7YDA5j23jVeLlc='";
        String strMsg = "<html><body bgcolor='#EAEAE0' color='black'>" +
                "<img width='100' height='100' align='left' " +
                "src=" + url + "/>"
                + "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
                + strMsg1 + "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ¥"
                + strMsg2 + "<body></html>";
        product01.setHorizontalTextPosition(JLabel.RIGHT);
        product01.setSize(150,150);
        product01.setText(strMsg);


        */


    }

    public void delectSale(int index) {

            if (salesList.size() > 0) {
                salesList.remove(index);
                rowData2 = new Object[salesList.size()][columnNames2.length];
                for (int i = 0; i <= salesList.size(); i++) {
                    Product sales1 = salesList.get(i);
                    rowData2[i] = new String[]{sales1.getName(), String.valueOf(sales1.getPrice()), sales1.getType(), String.valueOf(sales1.getNumber())};
                }
            }

    }


    public void addSale(Product sale){

        boolean q = true;
        int k=0;
        int number = 0;

        for (int i = 0; i < rowData2.length; i++) {
            if(!salesList.isEmpty()) {
                Product product = salesList.get(i);
                if (product.getId()==sale.getId()) {
                    q = false;
                    number = product.getNumber()+1;
                    product.setNumber(number);
                    System.out.println(number);
                    rowData2[i][columnNames2.length - 1] = String.valueOf(number);
                } else {
                    k = i+1;
                }
            }

        }

        if(q) {
            Product sales = new Product();
            sales.setId(sale.getId());
            sales.setName(sale.getName());
            sales.setPrice(sale.getPrice());
            sales.setType(sale.getType());
            sales.setNumber(1);
            sales.setPath(sale.getPath());
            salesList.add(sales);
            rowData2 = new Object[salesList.size()][columnNames2.length];
            for(int i=0;i<=k;i++) {
                Product sales1 = salesList.get(i);
                rowData2[i] = new String[]{sales1.getName(), String.valueOf(sales1.getPrice()),sales1.getType(), String.valueOf(sales1.getNumber())};
            }
        }

    }

    public Object[][] selectProduct(){
        Connection conn = null;
        String url = "jdbc:oracle:thin:@43.138.230.44:1521:orcl";
        String sql = "SELECT * FROM product";
        PreparedStatement pstmt;
        ResultSet rs;

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(url, "wzz", "Grc1234WzZ");
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("ID"));
                product.setName(rs.getString("NAME"));
                product.setPrice(rs.getFloat("PRICE"));
                product.setType(rs.getString("TYPE"));
                product.setNumber(rs.getInt("NUMBERS"));
                product.setPath(rs.getString("PHOTOPATH"));

                productList.add(product);//每循环一次把拿到的商品的数据存入集合，好比每摘一个芒果，把芒果放入桶里
            }
            productSize = productList.size();
            rowData1 = new Object[productSize][columnNames3.length];

                /*
                把集合的数据转成二维数组
                 */
            for (int i = 0; i < rowData1.length; i++) {
                Product product = productList.get(i);
                rowData1[i] = new Object[]{product.getId(), product.getName(), product.getPrice(), product.getType(), product.getNumber(),product.getPath()};
            }

        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("错误！");
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return rowData1;
    }

    public void deleteProduct(int index) {
        String driver = "oracle.jdbc.driver.OracleDriver";
        String url = "jdbc:oracle:thin:@43.138.230.44:1521:orcl";
        String user = "wzz";
        String password = "Grc1234WzZ";
        String sql="DELETE FROM PRODUCT WHERE ID=?";
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,String.valueOf(productList.get(index).getId()));
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            JOptionPane.showMessageDialog(jPanel,"成功删除","提示",JOptionPane.INFORMATION_MESSAGE);
        }catch (ClassNotFoundException | SQLException ex) {
            System.out.println("删除失败！");
        }

    }

    void addProduct(Product product){
        String driver = "oracle.jdbc.driver.OracleDriver";
        String url = "jdbc:oracle:thin:@43.138.230.44:1521:orcl";
        String user = "wzz";
        String password = "Grc1234WzZ";
        //String sql="insert into PRODUCT(ID,NAME,PRICE,NUMBER) values(?,?,?,?)";
        String sql = "insert into PRODUCT values (?,?,?,?,?,?)";
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, product.getId());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setFloat(3, product.getPrice());
            preparedStatement.setString(4, product.getType());
            preparedStatement.setInt(5, product.getNumber());
            preparedStatement.setString(6, product.getPath());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            System.out.println("添加成功！");
        }catch (ClassNotFoundException | SQLException e){
            System.out.println("添加失败！");
        }
    }

    public void updateProduct(Product product){
        String driver = "oracle.jdbc.driver.OracleDriver";
        String url = "jdbc:oracle:thin:@43.138.230.44:1521:orcl";
        String user = "wzz";
        String password = "Grc1234WzZ";
        String sql="UPDATE PRODUCT SET ID=?,NAME=?,PRICE=?,TYPE=?,NUMBERS=?,PHOTOPATH=? WHERE ID=?";
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,product.getId());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setFloat(3, product.getPrice());
            preparedStatement.setString(4, product.getType());
            preparedStatement.setInt(5, product.getNumber());
            preparedStatement.setString(6, String.valueOf(product.getPath()));
            preparedStatement.setInt(7,product.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        }catch (ClassNotFoundException | SQLException ex) {
            System.out.println("修改失败！");
        }

    }
    public JFrame getjFrame() {
        return jFrame;
    }
}