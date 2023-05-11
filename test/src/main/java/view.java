import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Vector;

public class view {
    public static void main(String[] args) {
        JPanel jPanel;
        JFrame jFrame = new JFrame("主界面");
        jFrame.setSize(600, 600);
        jPanel = (JPanel) jFrame.getContentPane();
        jPanel.setLayout(null);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //容器
        Container container=jFrame.getContentPane();
        container.setLayout(null);

        //面板1
        JPanel jPanel1=new JPanel();
        //布局为空
        jPanel1.setLayout(null);
        jPanel1.setBounds(0,30,600,570);
        jPanel1.setBackground(Color.cyan);
        jPanel1.setVisible(false);
        container.add(jPanel1);
        JMenuItem delete = new JMenuItem("删除商品");
        delete.setBounds(0, 0, 100, 30);
        jPanel1.add(delete);

        String columnNames[] = {"ID", "商品名称", "单价", "类型"};
        Object[][] rowData = {
                {"", "", "", ""},
                {"", "", "", ""},
                {"", "", "", ""}
        };
        rowData = new Object[3][3];
                /*
                把集合的数据转成二维数组
                 */
        for (int i = 0; i < rowData.length; i++) {
            rowData[i] = new Object[]{"Q", "2", "4","5"};
        }
        JTable jTable=new JTable(rowData,columnNames);
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(r.CENTER);
        jTable.setDefaultRenderer(Object.class, r);
        JScrollPane jscrollpane = new JScrollPane();
        jscrollpane.setBounds(0, 30, 600, 400);
        jscrollpane.setViewportView(jTable);

        jPanel1.add(jscrollpane);
        jPanel1.repaint();

        //面板2
        JPanel jPanel2=new JPanel();
        jPanel2.setLayout(null);
        jPanel2.setBounds(0,30,600,570);
        jPanel2.setBackground(Color.pink);
        jPanel2.setVisible(false);
        container.add(jPanel2);
        JButton pay = new JButton("结账");
        pay.setBounds(300, 300, 100, 30);
        jPanel2.add(pay);
        JScrollPane jScrollPane = new JScrollPane();
        jscrollpane.setBounds(0, 0, 600, 250);
        JLabel jLabel =new JLabel("测试");
        //jLabel.setBounds(0,0,);
        jPanel2.add(jscrollpane);

        /*
        在java中菜单有如下三大组件：JMenuBar，JMenu，JMenuItem。
        JMenuBar是相关的菜单栏，一般一个窗体中有一个就可以了；
        JMenu有两种功能，一是在菜单栏中显示，二是当它被加入到另一个JMenu中时，会产生引出子菜单的效果；
        JMenuItem是JMenu目录下的菜单。
        */

        //JMenuBar jMenuBar = new JMenuBar();
        //JMenu jMenu = new JMenu("基础信息管理");
        /*
        JMenuItem item01 = new JMenuItem("查看商品");
        JMenuItem item02 = new JMenuItem("销售商品");
        JMenuItem item03 = new JMenuItem("销售商品1");
        JMenuItem item04 = new JMenuItem("销售商品2");
        JMenuItem item05 = new JMenuItem("销售商品3");
        JMenuItem item06 = new JMenuItem("销售商品4");

         */

        JButton item01 = new JButton("查看商品"),
                item02 = new JButton("销售商品"),
                item03 = new JButton("销售商品1"),
                item04 = new JButton("销售商品2"),
                item05 = new JButton("销售商品3"),
                item06 = new JButton("销售商品4");

        item01.setBounds(0,0,100,30);
        item02.setBounds(100,0,100,30);
        item03.setBounds(200,0,100,30);
        item04.setBounds(300,0,100,30);
        item05.setBounds(400,0,100,30);
        item06.setBounds(500,0,100,30);

        jPanel.add(item01);
        jPanel.add(item02);
        jPanel.add(item03);
        jPanel.add(item04);
        jPanel.add(item05);
        jPanel.add(item06);



        /*JLabel jLabel =new JLabel("***************************************************");
        jLabel.setBounds(100,0,600,30);
        jPanel.add(jLabel);

         */


        /*
        jMenu.add(item01);
        jMenu.add(item02);
        jMenuBar.add(jMenu);
        jMenuBar.setBounds(0, 0, 100, 30);
        jPanel.add(jMenuBar);

         */



        item01.addActionListener(e -> {
            jPanel1.setVisible(true);
            jPanel2.setVisible(false);
        });
        item02.addActionListener(e -> {
            jPanel2.setVisible(true);
            jPanel1.setVisible(false);
        });

        jFrame.setVisible(true);

    }
}
