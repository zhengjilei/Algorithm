package programmer_interview;

import org.junit.Test;

/**
 * created by Ethan-Walker on 2019/1/8
 */
public class Q068_Winner {
    /**
     * 时间复杂度: O(2^n)
     * 空间复杂度: O(n)
     *
     * @param arr
     * @return
     */
    public int winnerScore(int[] arr) {
        // a先拿，a 得到的最高分数
        int a = first(arr, 0, arr.length - 1);
        int sum = 0;
        for (int t : arr) {
            sum += t;
        }
        if (sum - a > a) {
            // b 拿到的分数高于 a
            System.out.println("winner is b, score= " + (sum - a));
            return sum - a;
        } else {
            System.out.println("winner is a, score= " + (a));
            return a;
        }

    }


    /**
     * 数组 arr[l..r] 绝顶聪明的人如果先拿，最终该人能拿到的最大分数
     * 1. l==r ,只有一个元素，直接返回 arr[l]
     * 2. 如果拿 arr[l] ，剩余 arr[l+1,r] 中该绝顶聪明的人是后拿的,最大分数为 second(arr,l+1,r)
     * arr[l]+second(arr,l+1,r)
     * 3. 如果拿 arr[l] , 剩余 arr[l,r-1] 中该绝顶聪明的人是后拿的，最大分数为 second(arr,l,r-1)
     * arr[r]+second(arr,l,r-1)
     * <p>
     * 两种方式中选择最大的（因为该人先拿，且该人是绝顶聪明的）
     * Math.max(...,...)
     *
     * @param arr
     * @param l
     * @param r
     * @return
     */
    public int first(int[] arr, int l, int r) {
        if (l == r) {
            return arr[l];
        }
        return Math.max(second(arr, l + 1, r) + arr[l], second(arr, l, r - 1) + arr[r]);
    }


    /**
     * 数组 arr[l..r] 绝顶聪明的人后拿，最终该人能拿到的最大分数
     * <p>
     * 1. l==r ，只剩一个元素，但由于绝顶聪明的人后拿，故拿不到任何元素，返回 0
     * 2. 绝顶聪明的人后拿，由于了另一个人也是绝顶聪明的
     * 对手如果拿了 arr[l], 则该人在 arr[l+1..r] 中处于先拿的位置 即 first(arr,l+1,r)
     * 对手如果拿了 arr[r], 则该人在 arr[l,r-1] 中处于先拿的位置 即 first(arr,l,r-1)
     * <p>
     * 由于对手是绝顶聪明的人，保障自己的总分最高，故会把最差的结果留给该人
     * Math.min(first(arr,l+1,r), first(arr,l,r-1))
     *
     * @param arr
     * @param l
     * @param r
     * @return
     */
    public int second(int[] arr, int l, int r) {
        if (l == r) return 0;
        return Math.min(first(arr, l + 1, r), first(arr, l, r - 1));
    }


    /**
     * 动态规划
     * 二维数组记录暴力递归的结果
     * f[i][j] 表示绝顶聪明的人在 arr[i..j] 先拿，能拿到的最大分数
     * s[i][j] 表示绝顶聪明的人在 arr[i..j] 后拿，能拿到的最大分数
     * <p>
     * f[i][j] = Math.max( arr[i]+s[i+1][j],arr[j]+s[i][j-1])
     * s[i][j] = Math.min( f[i+1][j], f[i][j-1])
     * <p>
     * 计算 f[0][len-1]
     * 先计算所有一个元素的 f/s
     * 再计算所有两个元素的 f/s
     * <p>
     * 示例: f[0][3]
     * 1. 计算 f/s[0][0] f/s[1][1] f/s[2][2] f/s[3][3]
     * 2. 计算 f/s[0][1] f/s[1][2] f/s[2][3]
     * 3. 计算 f/s[0][2] f/s[1][3]
     * 4. 计算 f/s[0][3]
     * <p>
     * 时间复杂度: O(n^2)
     * 空间复杂度: O(n^2)
     *
     * @param arr
     * @return
     */
    public int winnerScore2(int[] arr) {

        int len = arr.length;
        int[][] f = new int[len][len];
        int[][] s = new int[len][len];

        // 先计算所有的只有一个元素 的 f/s
        for (int i = 0; i < len; i++) {
            f[i][i] = arr[i];
            s[i][i] = 0;
        }

        for (int gap = 1; gap < len; gap++) {

            // 计算 f/s[i][i+gap]
            for (int i = 0; i + gap < len; i++) {
                f[i][i + gap] = Math.max(arr[i] + s[i + 1][i + gap], arr[i + gap] + s[i][i + gap - 1]);
                s[i][i + gap] = Math.min(f[i + 1][i + gap], f[i][i + gap - 1]);
            }
        }

        // 先拿+后拿 = sum 因为如果后拿，相当于对手先拿，对手也是决定聪明，等于当前先拿的值

        if (f[0][len - 1] > s[0][len - 1]) {
            System.out.println("winner is a, score= " + f[0][len - 1]);
            return f[0][len - 1];
        } else {
            System.out.println("winner is b, score= " + s[0][len - 1]);
            return s[0][len - 1];
        }

    }

    @Test
    public void test() {
        int[] a = {1, 2, 100, 4};
        int[] b = {1, 100, 2};

        System.out.println(winnerScore(a));
        System.out.println(winnerScore(b));
        System.out.println("-----------------------------");
        System.out.println(winnerScore2(a));
        System.out.println(winnerScore2(b));
    }
}
