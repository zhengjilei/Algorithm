package bytedance;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * created by Ethan-Walker on 2019/2/2
 */
public class Q093_RestoreIP {
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        if (s.length() < 4 || s.length() > 12) return res;
        restore(s.toCharArray(), 0, "", res, 0);
        return res;
    }

    // s 为已拼接的前缀
    public void restore(char[] chs, int index, String s, List<String> res, int pos) {
        if (index == chs.length && pos < 4) { // 所有数字都用上了，但是没有拼出 4个数字
            return;
        }
        if (pos == 4) { // pos=0,1,2,3 都已经确定
            if (index == chs.length) { //所有的字符恰好都使用上了
                res.add(s);
            }

            return;
        }
        if (pos != 0) {
            s += '.';
        }
        // 从 Index 开始拼接下一个数字

        if (chs[index] == '0') {
            // 数字0单独作为一个数字
            s += '0';
            restore(chs, index + 1, s, res, pos + 1);
        } else {
            // 数字长度可以是[1,3]
            restore(chs, index + 1, s + chs[index], res, pos + 1);
            if (index + 1 < chs.length) {
                restore(chs, index + 2, s + chs[index] + chs[index + 1], res, pos + 1);
                if (index + 2 < chs.length) {
                    // 三位数，要求<=255
                    if (chs[index] < '2'
                            || (chs[index] == '2' && chs[index + 1] < '5')
                            || (chs[index] == '2' && chs[index + 1] == '5' && chs[index + 2] <= '5'))
                        restore(chs, index + 3, s + chs[index] + chs[index + 1] + chs[index + 2], res, pos + 1);
                }
            }

        }

    }

    @Test
    public void test() {
        System.out.println(restoreIpAddresses("25525511135"));
        System.out.println(restoreIpAddresses("1231231122"));
        System.out.println(restoreIpAddresses("1111"));
    }
}
