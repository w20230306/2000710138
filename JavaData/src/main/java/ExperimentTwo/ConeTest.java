package ExperimentTwo;

public class ConeTest {
    public static void main(String args[])
    {    Cone  c1=new Cone();
        System.out.println(c1.V());
        Cone  c2=new Cone(1.0,3.0);
        System.out.println(c2.V());
    }
}
class   Circle
{   double  r ;
    Circle( ) {
        r=1.0;
    } //无参构造方法，默认半径为1.0
    Circle(double  a)  //有参构造方法
    {
        r=a;
    }
    double Area( )    {return Math.PI * r * r;}//返回面积
    double Girth(  )  {return 2 * Math.PI * r;}//返回周长

}
class Cone extends Circle{
    double h;
    Cone()
    {   super();
        h=1.0;
    }
    Cone(double rr,double hh){
        //为半径和高赋值
        r=rr;h = hh;
    }
    double V()  {return super.Area() * h / 3;}//返回圆锥的体积
}

