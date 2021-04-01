package catalan;

import java.util.ArrayDeque;
import java.util.Scanner;

public class poj1363 {
    public static void main(String[] args) {
        int n;
        Scanner sc = new Scanner(System.in);
        while ((n = sc.nextInt()) != 0) {
            int t;
            while ((t = sc.nextInt()) != 0) {
                int[] seq = new int[n];
                seq[0] = t;
                for (int i = 1; i < n; i++) {
                    seq[i] = sc.nextInt();
                }
                if (isValidOutputSeq(n, seq)) {
                    System.out.println("Yes");
                } else {
                    System.out.println("No");
                }
            }
            System.out.println();
        }
    }

    /**
     * 入栈序列为 1,2,...,n
     * 判断是否是合法的出栈顺序
     */
    public static boolean isValidSeq(int n, int[] a) {
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
                if (stack.isEmpty() || stack.pop() != a[i]) return false;
                else i++;
            } else {
                return false;
            }
        }
        return true;
    }

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
                return false;
            }
        }
        return true;

    }
}
