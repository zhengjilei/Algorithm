package 剑指offer;

import org.junit.Test;

import java.util.ArrayDeque;

/**
 * created by Ethan-Walker on 2019/2/23
 */
public class Q031_InOutSeq {
    public boolean validateStackSequences(int[] pushed, int[] popped) {

        if (pushed == null || popped == null) return false;
        if (pushed.length != popped.length) return false;

        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int i = 0, j = 0;
        while (i < pushed.length) {
            if (!stack.isEmpty() && stack.peek() == popped[j]) {
                j++;
                stack.pop();
                continue;
            }

            if (pushed[i] == popped[j]) {
                i++;
                j++;
            } else {
                stack.push(pushed[i++]);
            }
        }

        while (!stack.isEmpty()) {
            if (stack.pop() != popped[j++]) return false;
        }
        return true;

    }

    @Test
    public void test() {

        System.out.println(validateStackSequences(new int[]{1, 2, 3, 4, 5}, new int[]{4, 5, 3, 2, 1}));

    }
}
