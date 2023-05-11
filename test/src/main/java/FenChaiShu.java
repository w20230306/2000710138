public class FenChaiShu {
    //无序分拆B(n,k)可以使用递归式来计算：
    //B(n, k) = B(n-1, k-1) + B(n-k, k)
    //其中，B(n,1) = B(n,n) = 1。
    public static int unorderedPartition(int n, int k) {
        if (k > n) {
            return 0;
        }
        else if (k == 1 || k == n) {
            return 1;
        }
        else {
            return unorderedPartition(n-1, k-1) + unorderedPartition(n-k, k);
        }
    }
    //在上述代码中，unorderedPartition()方法使用递归方法计算将正整数n拆分为k个不同的正整数的方法数，并返回结果。
    // 对于B(n,k)的计算，只需要调用unorderedPartition(n,k)即可得到结果。
    public static void main(String[] args) {
        /*System.out.println(unorderedPartition(5,1));
        System.out.println(unorderedPartition(5,2));
        System.out.println(unorderedPartition(5,3));
        System.out.println(unorderedPartition(5,4));
        System.out.println(unorderedPartition(5,5));
        System.out.println(unorderedPartition(5,6));*/
        for(int j=1;j<=10;j++) {
            System.out.print("  "+j);
        }
        System.out.println();
        for(int i=1;i<=10;i++){
            System.out.print(i + "  ");
            for (int k=1;k<=10;k++){
                System.out.print(unorderedPartition(i,k)+"  ");
            }
            System.out.println();
        }
    }
}
