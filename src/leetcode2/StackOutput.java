package leetcode2;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * created by Ethan-Walker on 2019/3/12
 */
public class StackOutput {

    int[] input;

    public List<List<Integer>> outputSeq(int[] input) {
        int[] help = new int[2 * input.length];
        List<List<Integer>> res = new ArrayList<>();
        this.input = input;


        return res;
    }

    /**
     * 进栈 1, 出栈 0
     * 保证任意一个位置，前面 1 的个数 大于等于 0 的个数
     *
     * @param res
     * @param index
     */
    public void recur(List<List<Integer>> res, int index, int[] seq, int left1, int left0) {
        if (index == seq.length) {
            List<Integer> output = new ArrayList<>();
            ArrayDeque<Integer> stack = new ArrayDeque<>();
            int inputIndex = 0;
            for (int i = 0; i < seq.length; i++) {
                if (seq[i] == 1) {
                    stack.push(input[inputIndex++]);
                } else {
                    output.add(stack.pop());
                }
            }
            return;
        }
        if (left1 > 0) {
            seq[index] = 1;
            recur(res, index + 1, seq, left1 - 1, left0);
        }
        if (left1 < left0) {
            seq[index] = 0;
            recur(res, index + 1, seq, left1, left0 - 1);
        }
    }

}
