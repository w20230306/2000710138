package ExperimentOne;
import java.util.Scanner;

public class Conversion {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        System.out.print("输入华氏温度：");
        double f=scanner.nextInt();
        double degree=(5/9.0)*(f-32);
        System.out.print("输出摄氏温度：");
        System.out.println(degree);
    }
}
