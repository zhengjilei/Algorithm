package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * created by Ethan-Walker on 2019/1/26
 */
public class Q0022_GenerateBracket {
    public List<String> generateParenthesis(int n) {
        char[] arr = new char[n * 2];
        List<String> res = new ArrayList<>();
        generate(arr, 0, 0, res);
        return res;
    }

    // sum: arr[0...index-1] 之和
    public void generate(char[] arr, int index, int sum, List<String> res) {
        if (sum < 0) return;
        if (index == arr.length) {
            if (sum == 0)
                res.add(String.valueOf(arr));
            return;
        }

        arr[index] = '(';
        generate(arr, index + 1, sum + 1, res);
        arr[index] = ')';
        generate(arr, index + 1, sum - 1, res);
    }

    @Test
    public void test() {
        System.out.println(generateParenthesis(3));
    }

}
