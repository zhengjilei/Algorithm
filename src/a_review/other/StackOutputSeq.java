package a_review.other;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * created by Ethan-Walker on 2019/2/23
 */
public class StackOutputSeq {
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> seq(int[] input) {
        int len = input.length;
        int[] out = new int[2 * len];

        recur(input, out, 0, len, len);
        // 1 表示入栈 , 0 表示出栈 ，保证 out 任意前 k 位 1 的个数 >= 0 的个数
        return res;
    }


    public void recur(int[] input, int[] out, int index, int left1, int left0) {
        if (index == out.length) {
            add(input, out);
            return;
        }
        if (left0 == left1) {
            out[index] = 1;
            recur(input, out, index + 1, left1 - 1, left0);
            return;
        }

        // 放 1 或 0
        if (left1 > 0) {
            out[index] = 1;
            recur(input, out, index + 1, left1 - 1, left0);
        }

        if (left1 < left0) {
            out[index] = 0;
            recur(input, out, index + 1, left1, left0 - 1);
        }

    }

    public void add(int[] input, int[] output) {
        List<Integer> list = new ArrayList<>();
        int in = 0;
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for (int out = 0; out < output.length; out++) {
            if (output[out] == 1) {
                stack.push(input[in++]);
            } else {
                list.add(stack.pop());
            }
        }
        res.add(list);

    }

    @Test
    public void test() {
        int[] s = new int[]{1, 2, 3, 4, 5, 6};
        List<List<Integer>> seq = seq(s);
        System.out.println(seq.size());
    }
}
