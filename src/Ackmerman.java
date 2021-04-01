import java.util.ArrayDeque;

public class Ackmerman {
    public static int akm(int m, int n) {
        if (m == 0) return n + 1;
        if (n == 0) return akm(m - 1, 1);
        return akm(m - 1, akm(m, n - 1));
    }

    /**
     * 栈处理 akm 函数
     *
     * @param m
     * @param n
     * @return
     */
    public static int akmStack(int m, int n) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        stack.push(m);
        while (!stack.isEmpty()) {
            m = stack.pop();
            if (m == 0) {
                n = n + 1;
            } else if (n == 0) {
                m = m - 1;
                n = 1;
                stack.push(m);
            } else {
                n = n - 1;
                stack.push(m - 1);
                stack.push(m);
            }
        }
        return n;
    }

    public static void main(String[] args) {
        System.out.println(akm(2, 1));
        System.out.println(akmStack(2, 1));
//        System.out.println(akm(4, 3));
        System.out.println(akmStack(1, 11));
    }
}
