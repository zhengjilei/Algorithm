
import org.junit.Test;

import java.math.BigInteger;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Ethan-Walker on 2018/4/1.
 */
public class Q001TwoSumTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long k = sc.nextInt();
        long a = sc.nextInt();
        long aCount = sc.nextInt();
        long b = sc.nextInt();
        long bCount = sc.nextInt();

        BigInteger onlyA = BigInteger.valueOf(0);

        if (a != 0 && aCount != 0 && k % a == 0) {
            long shang = k / a;
            if (shang <= aCount) {
                onlyA = select(shang, aCount);
            }
        }

        BigInteger onlyB = BigInteger.valueOf(0);
        if (b != 0 && bCount != 0 && k % b == 0) {
            long shang = k / b;
            if (shang < bCount) {
                onlyB = select(shang, bCount);
            }
        }

        BigInteger aAndb = BigInteger.valueOf(0);
        if(aCount!=0&&bCount!=0&&a!=0&&b!=0){
            for (int i = 1; k - i * a > 0; i++) {
                // a 的个数
                long remain = k - i * a;
                if (remain % b == 0) {
                    long shang = remain / b;
                    if (shang <= bCount) {
                        BigInteger justB = select(shang, bCount);
                        BigInteger justA = select(i, aCount);
                        aAndb = aAndb.add(justA.multiply(justB));
                    }
                }
            }
        }

        BigInteger sum = (aAndb.add(onlyA).add(onlyB)).mod(BigInteger.valueOf(1000000007));
        System.out.println(sum.longValue());

    }

    /**
     * 求 C(m,n)
     * 计算 从 n 个数当中选出 m 个
     *
     * @param m
     * @param n
     * @return
     */
    public static BigInteger select(long m, long n) {
        if (m > n / 2) m = n - m;
        BigInteger top = BigInteger.valueOf(n);
        for (long i = n - 1; i >= (n - m + 1); i--) {
            top = top.multiply(BigInteger.valueOf(i));
        }
        BigInteger down = BigInteger.valueOf(m);
        for (long i = m - 1; i >= 1; i--) {
            down = down.multiply(BigInteger.valueOf(i));
        }
        return top.divide(down);
    }

    @Test
    public void testAtomicInteger(){
    }

}
