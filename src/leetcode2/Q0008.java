package leetcode2;

/**
 * created by Ethan-Walker on 2019/3/15
 */
public class Q0008 {
    public int myAtoi(String str) {
        if (str == null) return 0;
        str = str.trim();
        if (str.length() == 0) return 0;

        boolean positive = true;
        int index = 0;
        if (str.charAt(0) == '-') {
            positive = false;
            index++;
        } else if (str.charAt(0) == '+') {
            index++;
        }

        int minDiv = Integer.MIN_VALUE / 10;
        int minRem = Integer.MIN_VALUE % 10;

        int cur;
        int sum = 0;
        while (index < str.length()) {
            if (!isDigit(str.charAt(index))) break; // 不是数字,前面的结果扔保存

            cur = '0' - str.charAt(index);
            if (sum < minDiv || (sum == minDiv && cur < minRem))
                return positive ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            sum = sum * 10 + cur;
            index++;
        }

        if (positive) {
            if (sum == Integer.MIN_VALUE) return Integer.MAX_VALUE;
            return -sum;
        }
        return sum;
    }

    public boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }
}
