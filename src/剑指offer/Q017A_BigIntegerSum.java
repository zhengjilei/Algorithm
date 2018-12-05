package 剑指offer;

import org.junit.Test;

/**
 * 任意给定两个整数(以字符串形式给定)，求两数之和
 * created by Ethan-Walker on 2018/12/4
 */
public class Q017A_BigIntegerSum {
    public String sum(String a, String b) {

        boolean positiveA = isPositive(a);
        boolean positiveB = isPositive(b);

        if (positiveA && positiveB || (!positiveA && !positiveB)) {
            // 两个都是正数 或者 都是负数 直接从最低位各位相加，加到最高位（注意最高位可能是符号）
            int i = a.length() - 1, j = b.length() - 1;
            int aStart = 0, bStart = 0;
            if (a.charAt(0) == '+' || a.charAt(0) == '-') {
                aStart = 1;
            }
            if (b.charAt(0) == '+' || b.charAt(0) == '-') {
                bStart = 1;
            }
            String result = sumWithNoSign(a.substring(aStart), b.substring(bStart));
            if (positiveA) {
                return result;
            } else {
                // 两个负数相加
                return new StringBuilder("-").append(result).toString();
            }
        } else {
            // 一正 一负
            if (!positiveA) {
                // a 为负数
                // b-a
                return subWithNoSign(b, a.substring(1));
            } else {
                return subWithNoSign(a, b.substring(1));
            }
        }
    }

    /**
     * 无符号的两个整数序列 相加
     *
     * @param a
     * @param b
     * @return
     */
    public String sumWithNoSign(String x, String y) {
        char[] a = x.toCharArray();
        char[] b = y.toCharArray();

        int len = b.length > a.length ? b.length : a.length;
        char[] result = new char[len + 1]; // 多余的用于存最高位溢出的 1

        int resultIndex = result.length - 1;
        int aIndex = a.length - 1;
        int bIndex = b.length - 1;
        int t = 0;
        boolean upper = false; //判断是否有进位
        while (aIndex >= 0 && bIndex >= 0) {
            t = a[aIndex--] - '0' + b[bIndex--] - '0';
            if (upper) {
                t = t + 1;
            }
            if (t >= 10) {
                upper = true;
                t = t % 10;
            } else {
                upper = false;
            }
            result[resultIndex--] = (char) (t + '0');
        }

        while (aIndex >= 0) {
            t = a[aIndex--] - '0';
            if (upper) {
                t++;
            }
            if (t >= 10) {
                upper = true;
                t = t % 10;
            } else {
                upper = false;
            }
            result[resultIndex--] = (char) (t + '0');
        }
        while (bIndex >= 0) {
            t = b[bIndex--] - '0';
            if (upper) {
                t++;
            }
            if (t >= 10) {
                upper = true;
                t = t % 10;
            } else {
                upper = false;
            }
            result[resultIndex--] = (char) (t + '0');
        }


        if (upper) {
            result[0] = '1';
            return new String(result);
        } else {
            return new String(result, 1, result.length - 1);
        }

    }

    /**
     * 无符号 a-b ,结果带符号
     *
     * @param a
     * @param b
     * @return
     */
    public String subWithNoSign(String a, String b) {
        int com = compare(a, b);
        String temp = null;
        if (com == 0) {
            return "0";//
        } else if (com < 0) {
            temp = a;
            a = b;
            b = temp;
        }
        // 使得 a>b
        char[] result = new char[a.length()];

        int aIndex = a.length() - 1;
        int bIndex = b.length() - 1;
        int resultIndex = a.length() - 1;

        int t = 0;
        boolean borrow = false; // 是否借用上一位
        while (aIndex >= 0 && bIndex >= 0) {
            t = a.charAt(aIndex--) - b.charAt(bIndex--);
            if (borrow) {
                t--;
            }
            if (t < 0) {
                borrow = true;
                t = t + 10;
            } else {
                borrow = false;
            }
            result[resultIndex--] = (char) (t + '0');
        }

        while (aIndex >= 0) {
            t = a.charAt(aIndex--)-'0';
            if (borrow) {
                t--;
            }
            if (t < 0) {
                borrow = true;
                t = t + 10;
            } else {
                borrow = false;
            }
            result[resultIndex--] = (char) (t + '0');
        }

        // chs 最高位若为 0 ，删除最高位
        int i = 0;
        while (result[i] == '0') {
            i++;
        }
        String s = new String(result, i, result.length - i);

        if (com < 0) {
            return new StringBuilder("-").append(s).toString();
        }
        return s;

    }

    /**
     * 无符号 比较 a/b 大小
     *
     * @param a
     * @param b
     * @return 返回1 表示 a>b  -1 表示 a<b  0 表示 a=b
     */
    public int compare(String a, String b) {
        if (a.length() > b.length()) {
            return 1;
        } else if (a.length() < b.length()) {
            return -1;
        } else {
            int i = 0;
            while (i < a.length()) {
                if (a.charAt(i) > b.charAt(i)) {
                    return 1;
                } else if (a.charAt(i) < b.charAt(i)) {
                    return -1;
                }
                i++;
            }
            return 0;
        }
    }

    public boolean isPositive(String s) {
        if (s.charAt(0) == '-') {
            return false;
        }
        return true;
    }

    @Test
    public void test() {
        System.out.println(sum("12", "34"));
        System.out.println(sum("121", "34"));
        System.out.println(sum("89", "34"));
        System.out.println(sum("99", "1"));
        System.out.println(sum("99999999", "1"));
        System.out.println(sum("1111", "123456"));

        System.out.println();
        System.out.println(sum("12", "-11"));
        System.out.println(sum("112", "-21"));
        System.out.println(sum("-112", "21"));
        System.out.println(sum("11", "-12"));
        System.out.println(sum("111", "-11"));
        System.out.println(sum("111", "-111"));

        System.out.println();

        System.out.println(sum("-112", "-21"));
        System.out.println(sum("-0", "-21"));
        System.out.println(sum("-99999999", "-1"));


    }
}
