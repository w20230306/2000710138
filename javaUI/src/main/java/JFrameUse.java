import javax.swing.*;
import java.awt.*;
/*
容器（Container）是组件（Component）的子类，一个容器可以容纳多个组件，并使他们成为一个整体。容器可以简化图形化界面的设计，
以整体结构来布置界面，所有的组件都可以通过add()方法加入容器中。容器共有四种类型，分别是窗口（JFrame）、弹窗（JDialog）、
面板（JPanel）、滚动面板（JScrollPanel）。
*/
public class JFrameUse {
    /*Frame或JFrame类用于创建一个具有标题栏的框架窗口作为程序的主要界面，它不依赖其他容器可以单独存在。*/
    public static void main(String[] args) {
        // 初始化窗口
        JFrame jFrame = new JFrame("这个是窗口的标题");
        // 设置窗口的位置和大小
        jFrame.setBounds(400, 300, 500, 500);
        // 设置窗口的背景颜色
        jFrame.setBackground(new Color(175, 114, 114));
        // 设置窗口是否可见
        jFrame.setVisible(true);
        // 设置窗口是否可以缩放
        jFrame.setResizable(false);
        /**
         * 设置窗口的相对位置。
         * 如果 comp 整个显示区域在屏幕内, 则将窗口放置到 comp 的中心;
         * 如果 comp 显示区域有部分不在屏幕内, 则将该窗口放置在最接近 comp 中心的一侧;
         * comp 为 null, 表示将窗口放置到屏幕中心。
         */
        jFrame.setLocationRelativeTo(null);
        /**
         * 设置窗口关闭按钮点击后的默认操作, 参考值:
         *     WindowConstants.DO_NOTHING_ON_CLOSE: 不执行任何操作。
         *     WindowConstants.HIDE_ON_CLOSE: 隐藏窗口（不会结束进程）, 再次调用 setVisible(true) 将再次显示。
         *     WindowConstants.DISPOSE_ON_CLOSE: 销毁窗口, 如果所有可显示的窗口都被 DISPOSE, 则可能会自动结束进程。
         *     WindowConstants.EXIT_ON_CLOSE: 退出进程。
         */
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
