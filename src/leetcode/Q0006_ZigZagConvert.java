package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * created by Ethan-Walker on 2019/3/21
 */
public class Q0006_ZigZagConvert {
    public String convert(String s, int numRows) {
        List<StringBuilder> res = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            res.add(new StringBuilder());
        }

        int row = 0;
        int i = 0, j = 0;
        while (i < s.length()) {
            j = 0;
            while (j < numRows && i < s.length()) {
                res.get(j).append(s.charAt(i));
                j++;
                i++;
            }
            j -= 2; // 注意这里是 j-=2, 不是 -=1
            while (j > 0 && i < s.length()) {
                res.get(j).append(s.charAt(i));
                j--;
                i++;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (StringBuilder t : res) {
            sb.append(t);
        }

        return sb.toString();
    }

    @Test
    public void test() {
        System.out.println(convert("LEETCODEISHIRING", 3));
        System.out.println(convert("LEETCODEISHIRING", 4));
    }
}
