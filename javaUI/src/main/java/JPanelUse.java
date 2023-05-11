import javax.swing.*;
import java.awt.*;
//普通面板
public class JPanelUse {
    public static void main(String[] args) {
        // 初始化窗口
        JFrame jFrame = new JFrame("面板窗口");
        jFrame.setVisible(true);
        jFrame.setSize(400, 400);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // 初始化面板：采用默认的流式布局或指定布局
        //JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel jPanel = new JPanel();
        jPanel.setLayout(null);
        // 设置面板大小
        jPanel.setSize(100, 100);
        // 设置面板背景颜色
        jPanel.setBackground(new Color(164, 24, 24));
        // 将面板添加到窗口
        JButton pay = new JButton("结账");
        pay.setBounds(0, 0, 10, 30);
        jPanel.add(pay);
        jFrame.add(jPanel);
    }
}
