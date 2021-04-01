package programmer_interview;

import org.junit.Test;

/**
 * created by Ethan-Walker on 2019/1/4
 */
public class Q060_Hanoi {


    /**
     * 将A 上的 n 个盘子移到 C 上
     *
     * @param A
     * @param B
     * @param C
     * @param n
     * @return
     */
    public int hanoi(String A, String B, String C, int n) {
        if (n == 0) return 0;
        if (n == 1) {
            System.out.println(A + " to " + C);
            return 1;
        } else {
            int count = 0;
            count += hanoi(A, C, B, n - 1); // 将A 上的 n-1 个盘子移到 B
            System.out.println(A + " to " + C); // 第 n 个盘子移到 C
            count += 1;
            count += hanoi(B, A, C, n - 1);// B上的 n-1 个盘子移到 C
            return count;
        }
    }


    /**
     * 盘子总数: arr.length
     * <p>
     * arr[i] = 1/2/3 表示第 i+1 个盘子位于左、中、右柱上,盘子大小随着 i 增大而增大
     * 如果arr 是hanoi 最优移动轨迹中的某一歌状态，求该状态是移动了多少步得到的
     * 如果不是最优，返回-1
     *
     * @param arr
     * @return
     */
    public int step1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        return process(arr, arr.length - 1, 1, 2, 3);
    }

    // 将移动盘子的全过程分为 3步            操作步骤数
    // 1. 上面的 n-1 个盘子 from -> mid     2^(n-1) - 1
    // 2. 第 n 个盘子 from -> mid           1
    // 3. n-1 个盘子 mid -> to             2^(n-1) - 1

    /**
     * 时间复杂度: O(n)
     * 空间复杂度: O(n)
     */
    public int process(int[] arr, int index, int from, int mid, int to) {
        if (index == -1) return 0;
        if (arr[index] == mid) {
            // 说明最大的圆盘位于 中柱上，不是最优移动轨迹中的状态
            return -1;
        }
        if (arr[index] == from) {
            // 可能的情况：
            // 步骤一未完成，说明该状态是 步骤一中的状态 process(arr,index-1,from,to,mid)
            // 步骤一完成，步骤二未完成 , 也属于步骤一中的状态（结束状态）
            return process(arr, index - 1, from, to, mid);
        } else {
            // arr[index] = to
            // 最大的圆盘位于右柱上，说明步骤一、二已经完成，当前状态是步骤三中的状态
            int rest = process(arr, index - 1, mid, from, to);
            if (rest == -1) return -1; // 得不到当前状态

            return rest + (1 << index);
        }
    }

    /**
     * 将上面的递归过程改成迭代
     * 时间复杂度: O(n)
     * 空间复杂度: O(1)
     *
     * @param arr
     * @return
     */
    public int step2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int from = 1, mid = 2, to = 3;
        int index = arr.length - 1;
        int result = 0;
        int temp = 0;
        while (index >= 0) {
            if (arr[index] == mid) {
                return -1;
            } else if (arr[index] == from) {
                index--;
                temp = mid;
                mid = to;
                to = temp;
            } else {
                result += (1 << index);
                temp = mid;
                mid = from;
                from = temp;
            }
            index--;
        }
        return result;
    }

    @Test
    public void test() {
        int count = hanoi("A", "B", "C", 4);
        System.out.println(count);
    }

}
