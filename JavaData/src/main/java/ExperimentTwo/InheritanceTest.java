package ExperimentTwo;

import javax.swing.*;
import java.text.DecimalFormat;

public class InheritanceTest {
        public static void main(String args[]){
            Point pointRef = null,p;//声明两点对象
            Circle1 circleRef; //声明两圆对象
            Circle1 c;
            String output;//定义一个字符串变量
            p=new Point(30,50);//给点对象赋值
            c= new Circle1(2.7,120,89);//给圆对象赋值

            //把点对象和圆对象转换成字符串后给字符串output赋值

            output="Pointp:"+p.toString()+"\nCirclec:"+c.toString();

            output=output+"\n\nCircle c(via poineRef):"+pointRef.toString();

            circleRef=(Circle1)pointRef;

            output=output+"\n\nCircle c(via poineRef):"+circleRef.toString();

            DecimalFormat precision2=new DecimalFormat("0.00");

            output+= "\nAreaofc(via circleRef):"+precision2.format(circleRef.area());

            //将圆定义成点对象输出
            if( p instanceof Circle1 ){  // 用于判断对象的运行类型是否为XX类型的子类型的实例
                circleRef=(Circle1) p;
                output+="\n\n cast successful";
            }else   output+="\n\n p dose not refer to a Circle";

            //利用对话框输出相关信息
            JOptionPane.showMessageDialog(null,output,
                    "InheritanceTset",JOptionPane.INFORMATION_MESSAGE);

            //退出
            System.exit(0);
        }
}
    class Point{                           //父类点的的定义
        protected int x,y ;                // 定义点的坐标
        public Point(){                    // 确定构造函数
            setPoint(0,0);
        }
        public Point(int a,int b){          //构造函数重载 带参数的构造函数
            setPoint(a,b);
        }
        public void setPoint(int a,int b){  // 构造函数，设置点的坐标
            x=a;
            y=b;
        }
        public int getX(){	return x;}  //获得X的坐标
        public int getY(){ return y;}  // 获得Y的坐标
        public String toString(){
            return "["+x+","+"y"+"]";
        }
    }
    /*子类圆的定义*/
    class Circle1 extends Point {     //圆类继承父类点类
        protected double radius ;//定义圆的半径
        public Circle1(){ setRadius(0);}// 定义子类的构造函数，隐含调用了父类的构造函数
        public Circle1(double r,int a,int b){
            super(a,b);// 调用父类的构造方法，将点的坐标赋值
            setRadius(r);
        }
        public void setRadius(double r){
            radius = (r>=0.0?r:0.0); // r大于等于0则半径等于r，否则半径等于0
        }
        public double getRadius(){ 	// 获得圆半径
            return  radius;
        }
        public double area(){  //  构造函数，求圆的面积
            return Math.PI*radius*radius;
        }
        public String toString(){//圆的半径。以及圆心坐标转换成字符串输出
            return "Center="+"["+x+","+y+"]"+";Radius="+radius;
        }
    }
