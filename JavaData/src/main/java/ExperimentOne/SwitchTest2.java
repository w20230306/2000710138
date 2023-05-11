package ExperimentOne;

public class SwitchTest2 {
    public static void main(String[] args) {
        char a=8;
        switch(a)
        {
            case 1:System.out.println("进行加法运算");break;
            case 2:System.out.println("进行减法运算");break;
            case 3:System.out.println("进行乘法运算");break;
            case 4:System.out.println("进行除法运算");break;
            default:System.out.println("a的值不合法");
        }
    }
}
