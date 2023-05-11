/**import java.util.Scanner;
public class test1 {
    public static int gcd(int a,int b) {
        int gcd = 0,c;
        if(a > b)
        {
            while(b != 0)
            {
                a = a % b;
                if(a < b)
                {
                    c = a;
                    a = b;
                    b = c;
                }
                gcd = a;
            }
        }
        if(a == b)
            gcd = a;
        else
        {
            c = a;a = b;b = c;
            while(b != 0)
            {
                a = a % b;
                if(a < b){c = a;a = b;b = c;}
                gcd = a;
            }
        }
        return gcd;
    }
    public static void main(String[] args) {
        Scanner zs = new Scanner(System.in);
        System.out.print("请输入一个整数：");
        int a = zs.nextInt();

        System.out.print("请再输入一个整数：");
        int b = zs.nextInt();

        int c = gcd(a,b);
        System.out.println("最大公因数是："+c);
    }
}
 */
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.LinkedList;

public class test1 {

    public void Circle_arrangement(List list, String prefix, Integer length) {
        //应用递归解决圆排列
        //当prefix长度等于数组长度且开头为数字1时输出
        if(prefix.length() == length&& '1'==prefix.charAt(0) ) {
            System.out.println(prefix);
        }
        //循环调用
        for(int i=0; i<list.size(); i++) {
            //将list暂时存放在temp中
            List temp = new LinkedList<Integer>(list);
            //System.out.println("循环："+i);
            //System.out.println(temp);
            //System.out.println(prefix);
            //循环一次，去掉temp中的i个值，放在prefix中
            Circle_arrangement(temp, prefix+temp.remove(i), length);
        }
    }

    public static void main(String[] args) {
        //创建列表存贮数值
        ArrayList<Integer> list=new ArrayList<Integer>(Arrays.asList(1,2,3,4,5));
        //创建对象以便调用函数
        test1 test = new test1();
        test.Circle_arrangement(list, "", list.size());
    }
}
