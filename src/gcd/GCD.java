package gcd;

/**
 * 求两个正整数的最大公约数
 */
public class GCD {
    public static void main(String[] args) {
        int a = 98, b = 63;
        System.out.println(gcd(a, b));
    }

    /**
     * 辗转相除法
     * gcd(a,b) = gcd(b%a, a) 假设  a< b
     * 最大公约数等于 较小的那个数 和两数相除余数的 的最大公约数
     *
     * @param a
     * @param b
     * @return
     */
    static int gcd(int a, int b) {
        int t;
        while (a > 0) {
            t = b % a;
            b = a;
            a = t;
        }
        return b;
    }

    // 假设 a < b
    static int gcdRecurn(int a, int b) {
        if (a == 0) return b;
        return gcdRecurn(b % a, a);
    }

    // 最小公倍数
    int method(int a, int b) {
        int gcd = gcd(a, b);
        return a * b / gcd;
    }

    int reviewGcd(int a, int b) {
        int t;
        while (a > 0) {
            t = b % a;
            b = a;
            a = t;
        }
        return b;
    }

}
