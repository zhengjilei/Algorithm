package recursion;

import java.util.ArrayDeque;
import java.util.Scanner;

/**
 * Created by Ethan-Walker on 2018/7/22.
 * 斐波那契数列：1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, ...
 */
public class Fib {
    /**
     * 递归 时间复杂度 O(2^n) 空间复杂度 O(n)
     *
     * @param n
     * @return
     */
    public static long recursion(int n) {
        if (n == 0) return 0;
        if (n == 1 || n == 2) return 1;
        return recursion(n - 1) + recursion(n - 2);
    }

    /**
     * 迭代，数组备忘录，时间复杂度 O(n)  空间复杂度 O(n)
     *
     * @param n
     * @return
     */
    public static long iteration(int n) {
        long[] a = new long[n + 1];
        a[1] = 1;
        a[2] = 1;
        for (int i = 3; i <= n; i++) {
            a[i] = a[i - 1] + a[i - 2];
        }
        return a[n];
    }

    /**
     * 只保存 2 个数，时间复杂度 O(n) 空间复杂度 O(1)
     *
     * @param n
     * @return
     */
    public static long save2(int n) {
        long a = 1, b = 1, c = 0;
        for (int i = 3; i <= n; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return c;
    }

    /**
     * 栈 时间复杂度O(n) 空间复杂度 O(1)
     *
     * @param n
     * @return
     */
    public static long stackImpl(int n) {
        ArrayDeque<Long> stack = new ArrayDeque<>();
        stack.push(0L);
        stack.push(1L);

        for (int i = 2; i <= n; i++) {
            long pop1 = stack.pop();
            long pop2 = stack.pop();
            stack.push(pop1);
            stack.push(pop1 + pop2);
        }
        return stack.pop();
    }

    static class Obj {
        public Obj(long n0, long n1) {
            this.n1 = n1;
            this.n0 = n0;
        }

        public Obj() {

        }

        long n1;
        long n0;
    }

    /**
     * 矩阵快速幂  时间复杂度 O(log n ) 空间复杂度
     * @param n
     * @return
     */
    public static Obj fib(int n) {
        if (n == 0) {
            return new Obj(0, 1);
        }
        int m = n / 2;
        Obj oldObj = fib(m);
        Obj newObj = new Obj();

        if (n % 2 == 1) {
            newObj.n0 = oldObj.n1 * oldObj.n1 + oldObj.n0 * oldObj.n0;
            newObj.n1 = (2 * oldObj.n0 + oldObj.n1) * oldObj.n1;
        }else{
            newObj.n0 = (2* oldObj.n1-oldObj.n0)*oldObj.n0;
            newObj.n1 = oldObj.n1 * oldObj.n1 + oldObj.n0 * oldObj.n0;
        }

        return newObj;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long bStart = System.currentTimeMillis();

        for(int i=0;i<9999;i++){
            stackImpl(n);
        }
        System.out.println(stackImpl(n));
        long bEnd = System.currentTimeMillis();
        System.out.println("栈消耗的时间: " + (bEnd - bStart) + "\n");

        long cStart = System.currentTimeMillis();
        for(int i=0;i<9999;i++){
             iteration(n);
        }
        System.out.println(iteration(n));
        long cEnd = System.currentTimeMillis();
        System.out.println("数组迭代消耗的时间:" + (cEnd - cStart) + "\n");

        long dStart = System.currentTimeMillis();
        for(int i=0;i<9999;i++){
             save2(n);
        }
        System.out.println(save2(n));
        long dEnd = System.currentTimeMillis();
        System.out.println("保留前两项消耗的时间: " + (dEnd - dStart) + "\n");

        long eStart  =System.currentTimeMillis();
        for(int i=0;i<9999;i++){
             fib(n);
        }
        System.out.println(fib(n).n0);
        long eEnd = System.currentTimeMillis();
        System.out.println("矩阵消耗的时间: " + (eEnd - eStart) + "\n");

/*        long aStart = System.currentTimeMillis();
        System.out.println(recursion(n));
        System.out.println("递归消耗的时间: " + (System.currentTimeMillis() - aStart));*/



    }

}
