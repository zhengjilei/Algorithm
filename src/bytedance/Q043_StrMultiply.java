package bytedance;

import org.junit.Test;

/**
 * created by Ethan-Walker on 2019/2/1
 */
public class Q043_StrMultiply {
    /**
     * num1[i] * num2[j] => res[i+j+1]（溢出也暂时存在 i+j+1）
     * <p>
     * maxLen= num1.len + num2.len
     * 结果位数为 maxLen-1 或 maxLen
     * 这样我们就可以单独都对每一位进行相乘计算把结果存入相应的index中
     * 最后统一处理溢出
     **/
    public String multiply(String num1, String num2) {
        int len1 = num1.length(), len2 = num2.length();
        int maxResLen = len1 + len2;
        int[] res = new int[maxResLen];
        int tmp = 0;

        for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len2; j++) {
                tmp = (num2.charAt(j) - '0') * (num1.charAt(i) - '0');
                res[i + j + 1] += tmp;
            }
        }

        int carry = 0; // 进位
        for (int i = maxResLen - 1; i >= 0; i--) {
            res[i] += carry;
            carry = res[i] / 10;
            res[i] %= 10;
        }

        int index = 0;
        // 去除开头0, 如果结果为0，保留个位0
        while (index < maxResLen - 1 && res[index] == 0) {
            index++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = index; i < maxResLen; i++) {
            sb.append(res[i]);
        }
        return sb.toString();
    }


    public String multiply2(String num1, String num2) {

        int len1 = num1.length(), len2 = num2.length();
        int maxResLen = len1 + len2;
        int[] res = new int[maxResLen];
        int tmp = 0;

        for (int j = len2 - 1; j >= 0; j--) {
            // num2[j] 和 num1 相乘
            for (int i = len1 - 1; i >= 0; i--) {
                // num2[j] 和 num1[i] 相乘
                tmp = (num2.charAt(j) - '0') * (num1.charAt(i) - '0');
                res[i + j + 1] += tmp;
            }
        }
        int carry = 0; // 进位

        for (int i = maxResLen - 1; i >= 0; i--) {
            res[i] += carry;
            carry = res[i] / 10;
            res[i] %= 10;
        }

        int index = 0;
        // 保留个位0
        while (index < maxResLen - 1 && res[index] == 0) {
            index++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = index; i < maxResLen; i++) {
            sb.append(res[i]);
        }
        return sb.toString();
    }

    @Test
    public void test2() {
        System.out.println(multiply2("1234", "5678"));
        System.out.println(multiply2("11", "5678"));
        System.out.println(multiply2("1", "999"));
        System.out.println(multiply2("1111", "8889"));
        System.out.println(multiply2("123", "456"));
        System.out.println(multiply2("0", "0"));
    }
}
