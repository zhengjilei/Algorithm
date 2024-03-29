package catalan;

import java.util.ArrayDeque;

public class Catalan {

    /**
     * 计算第 n个卡特兰数
     * n       0   1   2   3   4   5
     * h(n)    1   1   2   5  14   42
     * h(n) = h(0)*h(n-1)+h(1)*h(n-2)+...+h(n-1)*h(0)
     * <p>
     * C(n,2n) - C(n-1,2n)
     *
     * @param n
     * @return
     */
    public static long catalan(int n) {
        int[] cata = new int[20];
        cata[0] = cata[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                cata[i] += cata[j] * cata[i - 1 - j];
            }
        }
        return cata[n];
    }

    static int count;

    /**
     * 序列 1,2,..,n 依次进栈，打印所有可能的出栈次序
     * seq 表示序列 : 1 2 3 4
     * stack 表示栈
     * output 表示输出
     *
     * @return
     */
    public static void seqKind(ArrayDeque<Integer> seq, ArrayDeque<Integer> stack, ArrayDeque<Integer> output, int n) {
        if (output.size() == n) {
            count++;
            ArrayDeque<Integer> help = new ArrayDeque<>();
            while (!output.isEmpty()) {
                help.push(output.pop());
            }
            while (!help.isEmpty()) {
                System.out.printf("%d ", help.poll());
            }
            System.out.println();
        }
        // 任意元素分为入栈、出栈两种情况

        // 入栈
        if (!seq.isEmpty()) {
            int a = seq.pop();
            stack.push(a);
            seqKind(new ArrayDeque<>(seq), new ArrayDeque<>(stack), new ArrayDeque<>(output), n);
            //恢复
            seq.push(a);
            stack.pop();
        }

        //出栈
        if (!stack.isEmpty()) {
            int b = stack.pop();
            output.push(b);
            seqKind(new ArrayDeque<>(seq), new ArrayDeque<>(stack), new ArrayDeque<>(output), n);
            //恢复
            stack.push(b);
            output.pop();
        }
    }

    /**
     * 判定给定的出栈序列是否合法
     * 当前输出元素
     * 1. 等于栈顶元素，匹配出栈，继续步骤1；不匹配，进入步骤2
     * 2. 等于输入元素，（省略进栈出栈，输入输出往后+1）；不等，压栈
     * <p>
     * 每次优先比较输出元素和栈顶元素是否匹配
     *
     * @param input  入栈序列                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    入栈序列
     * @param output 出栈序列
     * @return
     */
    public static boolean isValidSeq(int[] input, int[] output) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int i = 0, j = 0;

        // 让n个数全部压栈

        while (i < input.length) {
            // 如果当前栈顶元素恰好等于 出栈元素，则栈顶元素出栈即可，匹配下一个
            if (!stack.isEmpty() && stack.peek() == output[j]) {
                j++;
                stack.pop();
                continue; // 很重要，不能省略，保证不是往后进行比较（导致压入元素），而是 先比较栈顶
            }
            // 栈为空 或者 栈顶元素不等于输出序列元素
            if (input[i] == output[j]) { // 压栈出栈直接匹配，不必再执行进栈出栈操作了
                i++;
                j++;
            } else {
                stack.push(input[i]);
                i++;
            }
        }
        while (!stack.isEmpty()) {
            if (stack.pop() == output[j]) {
                j++;
            } else {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {

/*
        System.out.println(isValidSeq(new int[]{7, 8, 5, 6, 3}, new int[]{5, 6, 7, 8, 3}));
        System.out.println(isValidSeq(new int[]{7, 8, 5, 6, 3}, new int[]{5, 6, 8, 7, 3}));
        System.out.println(isValidSeq(new int[]{7, 8, 5, 6, 3}, new int[]{5, 8, 7, 6, 3}));
*/

        ArrayDeque<Integer> seq = new ArrayDeque<>();
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        ArrayDeque<Integer> output = new ArrayDeque<>();

        int n = 4;
        // 逆序加入
        for (int i = n; i > 0; i--) {
            seq.push(i);
        }
        seqKind(seq, stack, output, n);
        System.out.println(count);
        System.out.println("-------------");
        System.out.println(isValidSeq(new int[]{1, 2, 3, 4}, new int[]{1, 2, 3, 4}));
        System.out.println(isValidSeq(new int[]{1, 2, 3, 4}, new int[]{1, 2, 4, 3}));
        System.out.println(isValidSeq(new int[]{1, 2, 3, 4}, new int[]{1, 3, 2, 4}));
        System.out.println(isValidSeq(new int[]{1, 2, 3, 4}, new int[]{1, 3, 4, 2}));
        System.out.println(isValidSeq(new int[]{1, 2, 3, 4}, new int[]{1, 4, 3, 2}));
        System.out.println(isValidSeq(new int[]{1, 2, 3, 4}, new int[]{1, 4, 2, 3}));  //false
        System.out.println("---");
        System.out.println(isValidSeq(new int[]{1, 2, 3, 4}, new int[]{2, 1, 3, 4}));
        System.out.println(isValidSeq(new int[]{1, 2, 3, 4}, new int[]{2, 1, 4, 3}));
        System.out.println(isValidSeq(new int[]{1, 2, 3, 4}, new int[]{2, 3, 1, 4}));
        System.out.println(isValidSeq(new int[]{1, 2, 3, 4}, new int[]{2, 3, 4, 1}));
        System.out.println(isValidSeq(new int[]{1, 2, 3, 4}, new int[]{2, 4, 1, 3})); // false
        System.out.println(isValidSeq(new int[]{1, 2, 3, 4}, new int[]{2, 4, 3, 1}));
        System.out.println("---");

        System.out.println(isValidSeq(new int[]{1, 2, 3, 4}, new int[]{3, 1, 2, 4}));  //false
        System.out.println(isValidSeq(new int[]{1, 2, 3, 4}, new int[]{3, 1, 4, 2}));  //false
        System.out.println(isValidSeq(new int[]{1, 2, 3, 4}, new int[]{3, 2, 1, 4}));
        System.out.println(isValidSeq(new int[]{1, 2, 3, 4}, new int[]{3, 2, 4, 1}));
        System.out.println(isValidSeq(new int[]{1, 2, 3, 4}, new int[]{3, 4, 1, 2}));  //false
        System.out.println(isValidSeq(new int[]{1, 2, 3, 4}, new int[]{3, 4, 2, 1}));
        System.out.println("---");

        System.out.println(isValidSeq(new int[]{1, 2, 3, 4}, new int[]{4, 1, 2, 3}));  //false
        System.out.println(isValidSeq(new int[]{1, 2, 3, 4}, new int[]{4, 1, 3, 2}));  //false
        System.out.println(isValidSeq(new int[]{1, 2, 3, 4}, new int[]{4, 2, 1, 3}));  //false
        System.out.println(isValidSeq(new int[]{1, 2, 3, 4}, new int[]{4, 2, 3, 1}));  //false
        System.out.println(isValidSeq(new int[]{1, 2, 3, 4}, new int[]{4, 3, 1, 2}));  //false
        System.out.println(isValidSeq(new int[]{1, 2, 3, 4}, new int[]{4, 3, 2, 1}));

    }
}
