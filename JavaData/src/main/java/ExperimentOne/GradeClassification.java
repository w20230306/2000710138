package ExperimentOne;

import java.util.Scanner;
public class GradeClassification {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("输入成绩(0~100)：");
        int score=scanner.nextInt();
        String grade;
        if(score>=0&&score<=100) {
            switch (score / 10) {
                case 10:
                case 9:
                    grade = "优秀";
                    break;
                case 8:
                    grade = "良好";
                    break;
                case 7:
                    grade = "合格";
                    break;
                case 6:
                    grade = "及格";
                    break;
                default:
                    grade = "不及格";
                    break;
            }
            System.out.println("该学生成绩："+grade);
        }else{
            System.out.println("请输入0到100的整数!!!!");
        }
    }
}
