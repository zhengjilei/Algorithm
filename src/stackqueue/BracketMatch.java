package stackqueue;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * 给定 n 对括号，要求左右括号必须得匹配，输出所有可能的情况
 * <p>
 * Created by Ethan-Walker on 2018/4/25.
 */
public class BracketMatch {


    /**
     * 数组 chs 中前 k 个括号中 ,左括号的数目必须大于等于右括号
     *
     * @param chs   存放2n个括号
     * @param index 当前要设置第 index 个位置存放 的括号种类
     * @param leftL 剩下左括号的数目
     * @param leftR 剩下右括号的数目
     */
    public void solveMatch(char[] chs, int index, int leftL, int leftR) {
        if (index == chs.length) {
            print(chs);
            return;
        }
        if (leftL > 0) { // 左括号剩余
            chs[index] = '(';
            solveMatch(chs, index + 1, leftL - 1, leftR);
        }
        if (leftL < leftR) {
            chs[index] = ')';
            solveMatch(chs, index + 1, leftL, leftR - 1);
        }
    }

    void print(char[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]);
        }
        System.out.println();
    }

    @Test
    public void test() {
        int n = 5;
        char[] chs = new char[n * 2];
        solveMatch(chs, 0, n, n);
    }

}
