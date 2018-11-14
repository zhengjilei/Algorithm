package catalan;

import edu.princeton.cs.algs4.In;
import jdk.nashorn.internal.ir.IdentNode;

import java.util.ArrayDeque;

public class Catalan {

    /**
     * 计算第 n个卡特兰数
     * n       0   1   2   3   4   5
     * h(n)    1   1   2   5  14   42
     * h(n) = h(0)*h(n-1)+h(1)*h(n-2)+...+h(n-1)*h(0)
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
     * 入栈序列为 1,2,...,n
     * 判断是否是合法的出栈顺序
     */
    public static boolean isValidSeq(int[] a) {
        int n = a.length; // 1,2,...,n
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int top = 0;
        int i = 0;
        while (i < n) {
            if (a[i] > top) {
                for (int j = top + 1; j <= a[i]; j++) {
                    stack.push(j);
                }
                top = stack.pop();// top 为刚刚弹出的元素,也是目前压入/弹出的最大元素
                i++;
            } else if (a[i] < top) {
                if (stack.pop() != a[i]) return false;
                else i++;
            } else {
                return false;
            }
        }
        return true;
    }

    // 方法2
    public static boolean isValidOutputSeq(int n, int[] output) {
        int[] input = new int[n];
        for (int i = 0; i < n; i++) {
            input[i] = i + 1;
        }
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int i = 0, j = 0; // i 指向 input ,j 指向output
        // input[i] 待压入元素 output[i] 待匹配元素
        while (i < n) {
            // 将n个数全部入栈
            if (!stack.isEmpty() && stack.peekFirst() == output[j]) {
                // 栈不为空 ，先比较当前 output 元素和栈顶元素是否相等
                stack.pop();
                j++; // 匹配
                continue;
            }
            if (input[i] == output[j]) {
                i++;
                j++;// 下一个
            } else {
                stack.push(input[i]);
                i++;
            }
        }
        while (!stack.isEmpty()) {
            if (stack.peekFirst() == output[j]) {
                stack.pop();
                j++;
            } else {
                break;
            }
        }
        if (stack.isEmpty()) {
            return true;
        } else {
            return false;
        }

    }


    /**
     * 判定给定的出栈序列是否合法
     *
     * @param ori    入栈序列                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    入栈序列
     * @param output 出栈序列
     * @return
     */
    public static boolean isValidSeq(int[] ori, int[] output) {
        int i = 0, j = 0;
        int n = ori.length;
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        while (j < n) {
            if (!stack.isEmpty()) {
                //栈不为空，优先对比栈顶
                if (stack.peekFirst() == output[j]) {
                    // 如果当前栈顶元素恰好等于 出栈元素，则栈顶元素出栈即可，匹配下一个
                    j++;
                    continue;  // 很重要，不能省略，保证不是往后进行比较，而是 先比较栈顶
                }
            }
            //往后进行比较，序列压栈直到遇到 和出栈元素相同的数
            while (i < n && ori[i] != output[j]) {
                stack.push(ori[i]);
                i++;
            }
            if (i == n) return false;
            else {
                i++;
                j++;// 相当于 ori[i] 和 output[j] 匹配了
            }
        }
        return true;

    }

    public static boolean isValidSeq(int[] input, int[] output, int n) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int i = 0, j = 0;
        while (i < n) {
            // 让n个数全部压过栈
            if (!stack.isEmpty() && stack.peekFirst() == output[j]) {
                j++;
                stack.pop();
                continue;
            }
            // 栈为空 或者 栈顶元素不等于输出序列元素
            if (input[i] == output[j]) {
                i++;
                j++;
            } else {
                stack.push(input[i]);
                i++;
            }
        }
        while(!stack.isEmpty()){
            if(stack.peekFirst()==output[j]){
                stack.pop();
                j++;
            }else{
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
