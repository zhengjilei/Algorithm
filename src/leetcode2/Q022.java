package leetcode2;

import java.util.ArrayList;
import java.util.List;

/**
 * created by Ethan-Walker on 2019/3/13
 */
public class Q022 {

    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        char[] seq = new char[2 * n];
        generate(res, seq, 0, n, n);
        return res;
    }

    public void generate(List<String> res, char[] seq, int index, int rLeft, int rRight) {
        if (index == seq.length) {
            if (rLeft == 0 && rRight == 0) {
                res.add(new String(seq));
            }
            return;
        }

        if (rLeft > 0) {
            seq[index] = '(';
            generate(res, seq, index + 1, rLeft - 1, rRight);
        }
        if (rLeft < rRight) {
            seq[index] = ')';
            generate(res, seq, index + 1, rLeft, rRight - 1);
        }
    }


}
