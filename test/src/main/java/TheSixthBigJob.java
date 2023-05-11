import java.math.BigInteger;

public class TheSixthBigJob {
    public static void main(String[] args) {
        int n = 100;
        BigInteger[] f = new BigInteger[n + 1];
        f[0] = BigInteger.ZERO;
        f[1] = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            f[i] = f[i - 2].add(BigInteger.valueOf(3).multiply(BigInteger.valueOf((long)Math.pow(2, i-2))))
                    .add(BigInteger.valueOf(4).multiply(BigInteger.valueOf((long)Math.pow(-1, i-2))));
        }
        System.out.println(f[n]);
    }
}
