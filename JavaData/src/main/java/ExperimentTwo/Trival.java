package ExperimentTwo;

import java.util.Scanner;

public class Trival {
    double di;
    double gao;
    Trival(){
        di=1.0;
        gao=1.0;
    }
    Trival(double a,double b){
        this.di=a;
        this.gao=b;
    }
     public  double findArea(){
        return (di*gao)/2;
    }

    public static void main(String[] args) {
        Trival trival=new Trival();
        System.out.println("默认值时的三角形面积："+trival.findArea());
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入三角形的底:");
        double a=scanner.nextDouble();
        System.out.print("请输入三角形的高:");
        double b= scanner.nextDouble();
        Trival trival1=new Trival(a,b);
        System.out.println("指定值时的三角形面积："+trival1.findArea());
    }
}
