package ExperimentTwo;

public class Test {
    public static void main(String args[]){
        Square sq1=new Square(2.0);
        sq1.Show();
        System.out.println("正方形的边长为"+sq1.calcPerimeter());
    }
}
class Retangle {
    public Retangle(double l, double w)
    {  length = l;   width = w;}
    public double calcPerimeter() //返回周长
    {  return 2 * (length + width); }
    public double calcArea() //返回面积
    {  return length * width;   }
    public void Show() //显示矩形的长和宽
    {
        System.out.println("矩形的长为：" + this.length);
        System.out.println("矩形的宽为：" + this.width);
    }
    protected double length;
    protected double width;
}
class Square extends Retangle {
    public Square(double side) //调用父类的构造方法
    {   super(side, side);   }
    public double calcPerimeter()
    {    return width * 4;    }
    public void Show() {
        System.out.println( "边长为" + width +"的正方形");
    }
}
