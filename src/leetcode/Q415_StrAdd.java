package leetcode;

/**
 * created by Ethan-Walker on 2019/2/1
 */
public class Q415_StrAdd {

    public String addStrings(String num1, String num2) {
        return add(num1, num2);
    }

    // 两个字符串相加
    public String add(String num1, String num2) {
        int len1 = num1.length();
        int len2 = num2.length();
        int maxLen = Math.max(len1, len2);

        char[] res = new char[maxLen + 1]; // 结果最多溢出一位

        int i = len1 - 1, j = len2 - 1;
        int jinwei = 0;
        int resIndex = maxLen;
        int sum = 0;
        while (i >= 0 && j >= 0) {
            sum = add(num1.charAt(i--), num2.charAt(j--));
            sum += jinwei;
            res[resIndex--] = (char) (sum % 10 + '0');

            if (sum >= 10) {
                jinwei = 1;
            } else {
                jinwei = 0;
            }
        }

        while (i >= 0) {
            sum = num1.charAt(i--) - '0' + jinwei;
            res[resIndex--] = (char) (sum % 10 + '0');

            if (sum >= 10) {
                jinwei = 1;
            } else {
                jinwei = 0;
            }
        }
        while (j >= 0) {
            sum = num2.charAt(j--) - '0' + jinwei;
            res[resIndex--] = (char) (sum % 10 + '0');

            if (sum >= 10) {
                jinwei = 1;
            } else {
                jinwei = 0;
            }
        }
        if (jinwei == 1) {
            res[resIndex--] = '1';
        }

        if (resIndex == -1) {
            return String.valueOf(res);
        } else {
            return String.valueOf(res, 1, res.length - 1);
        }
    }

    public int add(char a, char b) {
        return a - '0' + b - '0';
    }
}
