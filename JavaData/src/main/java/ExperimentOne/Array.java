package ExperimentOne;

import java.util.Random;

public class Array {
    public static void main(String[] args) {
        int[][] data=new int[4][4];
        int sum=0;
        Random random = new Random();
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                data[i][j]=random.nextInt(0,10);
            }
        }
        //对角线和
        //int a=data[0][0]+data[1][1]+data[2][2]+data[3][3];
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                if(i==j){
                    sum+=data[i][j];
                }
            }
        }
        System.out.println("对角线元素的和："+sum);
        //输出
        System.out.println("4*4数组为：");
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                System.out.print(data[i][j]+"  ");
            }
            System.out.println();
        }
    }
}
