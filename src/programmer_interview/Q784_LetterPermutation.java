package programmer_interview;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * created by Ethan-Walker on 2019/2/4
 */
public class Q784_LetterPermutation {
    public List<String> letterCasePermutation(String s) {
        List<String> res = new ArrayList<>();
        if (s == null)
            return res;
        permute(s.toCharArray(), 0, res);
        return res;

    }

    public void permute(char[] chs, int index, List<String> res) {
        if (index == chs.length) {
            res.add(String.valueOf(chs));
            return;
        }
        if (isLetter(chs[index])) {
            permute(chs, index + 1, res);

            // 字母切换
            if (chs[index] <= 'Z') { // 大写字母
                chs[index] += 32;
            } else {             // 小写字母
                chs[index] -= 32;
            }
            permute(chs, index + 1, res);
        } else {
            permute(chs, index + 1, res);
        }
    }

    public boolean isLetter(char c) {
        return c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z';
    }

    // 非递归实现
    public List<String> letterCasePermutation2(String s) {
        // TODO: 2019/2/4
        return null;
    }

    @Test
    public void test() {
        System.out.println(letterCasePermutation("a1b2"));
        System.out.println(letterCasePermutation("3z4"));
        System.out.println(letterCasePermutation("12345"));
        System.out.println(letterCasePermutation(""));
    }
}
