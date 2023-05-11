public class Stirling {
        public static int S(int n, int k) {
            if (k == 0 || k > n) {
                return 0; // 当 k=0 或 k>n 时，返回 0
            }
            if (n == 1 && k == 1) {
                return 1; // 当 n=1,k=1 时，返回 1
            }
            return S(n - 1, k - 1) + k * S(n - 1, k); // 递推式
        }

        public static void main(String[] args) {
            int result = S(7, 4);
            System.out.println(result);
        }
}
