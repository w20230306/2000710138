public class test2 {
    public static void main(String[] args) {
        int count = 0;
        for (int i = 1001; i < 10000; i += 2) {
            char[] digits = String.valueOf(i).toCharArray();
            if (digits[0] != digits[1] && digits[0] != digits[2] && digits[0] != digits[3]
                    && digits[1] != digits[2] && digits[1] != digits[3] && digits[2] != digits[3]) {
                count++;
                System.out.println(digits);
            }
        }
        System.out.println("总数："+count);

    }
}
