import java.util.ArrayList;
import java.util.List;

public class Replacement {
    // 生成置换数组
    public static int[][] generatePermutations() {
        int[][] permutations = new int[10][5];
        int cnt = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = i + 1; j < 5; j++) {
                permutations[cnt][i] = j;
                permutations[cnt][j] = i;
                cnt++;
            }
        }
        for (int i = 0; i < 4; i++) {
            int a=0;
            permutations[a][i] = i;
            a++;
        }
        return permutations;
    }

    // 转换为置换字符串
    public static String permutationToString(int[] permutation) {
        StringBuilder sb = new StringBuilder("(");
        for (int i = 0; i < permutation.length - 1; i++) {
            sb.append(permutation[i] + 1).append(", ");
        }
        sb.append(permutation[permutation.length - 1] + 1).append(")");
        return sb.toString();
    }

    // 打印所有置换
    public static void printAllPermutations() {
        int[][] permutations = generatePermutations();
        List<String> permutationList = new ArrayList<>();
        for (int i = 0; i < permutations.length; i++) {
            permutationList.add(permutationToString(permutations[i]));
        }
        System.out.println("总共："+permutationList.size()+"个");
        System.out.println(permutationList);
    }

    public static void main(String[] args) {
        printAllPermutations();
    }
}
