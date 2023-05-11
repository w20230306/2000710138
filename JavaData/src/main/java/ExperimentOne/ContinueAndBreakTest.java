package ExperimentOne;

public class ContinueAndBreakTest {
    public static void main(String[] args) {
        // 保存和
        int sum = 0;
        // 循环变量
        int i=1;
        for(;i<1000;i++)
        {
            if(sum>5000)       //（1）
                break;         //（2）
            if(i%3!=0)          //（3）
                continue;       //（4）

            sum+=i;
        }
        // 输出最后的结果
        System.out.println(sum);
    }
}
