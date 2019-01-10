package leetcode;

/**
 * 判断整数是否为回文数
 * 方法一: 转换成字符串进行比较
 * <p>
 * 要求不能转换成字符串比较呢？
 * <p>
 *
 * 对整数的右半部分进行逆转
 *
 * created by Ethan-Walker on 2019/1/9
 */
public class Q009_PalindromeInt {
    public boolean isPalindrome(int x) {
        if (x < 0) return false;

        int numLen = (int) Math.log10(x) + 1;

        int t = 0;
        for (int i = 0; i < numLen / 2; i++) {
            t = t * 10 + (x % 10);
            x = x / 10;
        }
        // 12121 ->     x=121 t=12
        // 123321 ->    x=123 t=123
        if ((numLen & 1) == 0) {// 偶数
            return t == x;
        } else {
            return x / 10 == t;
        }
    }
}
