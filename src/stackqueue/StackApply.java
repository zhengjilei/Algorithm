package stackqueue;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * created by Ethan-Walker on 2019/1/15
 */
public class StackApply {


    /**
     * 计算中缀表达式：只包含+*-/ 整数, 不包含括号
     * 34+13*9+44-12/3
     *
     * @return
     */
    public int calExprStr(String expr) {
        ArrayDeque<Integer> numStack = new ArrayDeque<>();
        ArrayDeque<Character> opStack = new ArrayDeque<>();
        HashMap<Character, Integer> opPriority = new HashMap<>();
        opPriority.put('+', 0);
        opPriority.put('-', 0);
        opPriority.put('*', 1);
        opPriority.put('/', 1);

        char c = 0, op;
        int i = 0, num, a, b;
        while (i < expr.length()) {
            c = expr.charAt(i);
            if (isOp(c)) {
                while (!opStack.isEmpty() && opPriority.get(c) <= opPriority.get(opStack.peek())) {
                    b = numStack.pop();
                    a = numStack.pop();
                    op = opStack.pop();
                    numStack.push(cal(a, b, op));
                }
                opStack.push(c);
                i++;
            } else if (isDigit(c)) {
                num = c - '0';
                i++;
                while (i < expr.length() && isDigit(expr.charAt(i))) {
                    num = num * 10 + expr.charAt(i) - '0';
                    i++;
                }
                numStack.push(num);
            } else {
                i++;
            }

        }
        while (!opStack.isEmpty()) {
            op = opStack.pop();
            b = numStack.pop();
            a = numStack.pop();
            numStack.push(cal(a, b, op));
        }

        return numStack.pop();
    }

    public boolean isOp(char c) {
        if (c == '+' || c == '*' || c == '-' || c == '/') return true;
        return false;
    }

    public boolean isDigit(char c) {
        if (c >= '0' && c <= '9')
            return true;
        return false;
    }

    public int cal(int a, int b, char c) {
        switch (c) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                return a / b;
        }
        return -1;
    }


    /**
     * 浏览器有三种访问方式：前进、后退、新页面
     * 前进后退：如 从 a->b->c 当前页面处于c, 然后回退两步,当前页面位于a, 此时前进，则前进到b 页面，再前进，则进入到 c 页面
     * 新页面: 如 a->b->c 回退一步，此时位于 b, 再此时进入新页面d， 则 c 页面不再能通过 前进得到
     * <p>
     * 给定一个浏览器浏览的步骤，用栈实现, 输出每一步访问的页面
     * 例，访问步骤为: a b c -> -> <- d e (每一步为一个 String)
     * 如果是字母:例a , 表示从当前页面进入 a , 则现在访问的页面就是 a
     * <- : 表示从当前页面回退一步
     * -> : 表示从当前页面前进一步，前进的是之前从页面回退回来的页面
     */
    public void browser(String[] steps) {
        ArrayDeque<String> base = new ArrayDeque<>(); // 栈顶始终是当前访问的页面
        ArrayDeque<String> save = new ArrayDeque<>(); // 存储从 base 页面回退的 页面，供base 页面前进使用

        List<String> history = new ArrayList<>();
        String curPage;
        for (int i = 0; i < steps.length; i++) {
            if (steps[i].equals("<-")) {
                if (base.isEmpty() || base.size() == 1) {
                    // 当前栈中还没有页面， 或者当前页面是 根页面 没法回退
                    System.out.println("<- back disabled");
                    continue;
                }
                save.push(base.pop());
            } else if (steps[i].equals("->")) {
                if (save.isEmpty()) {
                    System.out.println("-> forward disabled");
                    continue;
                }
                base.push(save.pop());
            } else {
                // 新页面
                save.clear();
                base.push(steps[i]);
            }
            history.add(base.peek());
            System.out.println("access: " + base.peek());
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
    @Test
    public void testBrowser() {
        String[] steps = {"a", "b", "c", "<-", "<-", "->", "d", "->", "e"};
        browser(steps);
        System.out.println("---------------------");
        String[] steps2 = {"a", "<-", "b", "->", "c", "<-", "<-", "<-", "->"};
        browser(steps2);
    }

    @Test
    public void test() {
        System.out.println(calExprStr("34+13*9+44-12/3"));
        System.out.println(calExprStr("34+13*9+44/22*3-12/3"));

    }


}
