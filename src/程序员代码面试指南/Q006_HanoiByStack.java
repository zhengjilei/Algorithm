package 程序员代码面试指南;

import org.junit.Test;

import java.util.ArrayDeque;

/**
 * 用栈解决汉诺塔
 * 要求：盘子只能在相邻汉诺塔之间移动
 * created by Ethan-Walker on 2018/12/16
 */
public class Q006_HanoiByStack {


    private enum Action {
        NO, LToM, MToL, MToR, RToM
    }

    public void hanoi(int n, String A, String B, String C) {
        ArrayDeque<Integer> stackA = new ArrayDeque<>();
        ArrayDeque<Integer> stackB = new ArrayDeque<>();
        ArrayDeque<Integer> stackC = new ArrayDeque<>();

        stackA.push(Integer.MAX_VALUE);
        stackB.push(Integer.MAX_VALUE);
        stackC.push(Integer.MAX_VALUE);

        Action[] prevAction = {Action.NO}; // 记录上一步的行为

        for (int i = n; i >= 1; i--) {
            stackA.push(i);
        }

        long count = 0;

        // 每走一步，下一步只有一种可能

        // 只有四个方向: L->M  M->L M->R R->M
        // 限制条件  上一步 Action: L->M  下一步不允许 L->M 或者 M->L

        while (stackC.size() != n + 1) {
            count += tryMove(prevAction, Action.MToL, Action.LToM, stackA, stackB, A, B);
            count += tryMove(prevAction, Action.LToM, Action.MToL, stackB, stackA, B, A);
            count += tryMove(prevAction, Action.RToM, Action.MToR, stackB, stackC, B, C);
            count += tryMove(prevAction, Action.MToR, Action.RToM, stackC, stackB, C, B);
        }
        System.out.println(count);
    }

    public int tryMove(Action[] record, Action prevCannotBeAction, Action nowAction, ArrayDeque<Integer> fStack,
                       ArrayDeque<Integer> tStack, String from, String to) {
        if (record[0] != prevCannotBeAction && fStack.peek() < tStack.peek()) {
            record[0] = nowAction;
            System.out.println("push " + fStack.peek() + " from " + from + " -> " + to);
            tStack.push(fStack.pop());
            return 1;
        }
        return 0;
    }

    @Test
    public void test() {
        hanoi(3, "A", "B", "C");
    }

}
