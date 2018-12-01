package 剑指offer;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 长度为n的数组所有数字范围都在 0~n-1 的范围内
 * 1. 测试数组超出范围
 * 2. 数组为空
 * 3. 数组中不包含重复数字
 * created by Ethan-Walker on 2018/12/1
 */
public class Q003_DuplicateNum {

    /**
     * hash
     * count[i] = j 表示数组中值为 i 的数有 j 个
     * <p>
     * 时间复杂度: O(n)
     * 空间复杂度: O(n)
     * <p>
     * 找出所有的重复数字
     *
     * @param a
     * @return
     */
    public ArrayList<Integer> getDuplicate(int[] a) {
        int n = a.length;
        int[] count = new int[n];

        ArrayList<Integer> dup = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (a[i] >= n || a[i] < 0) {
                System.out.println("数值" + a[i] + "超出范围");
                return dup;
            }
            count[a[i]]++;
        }
        for (int i = 0; i < n; i++) {
            if (count[i] > 1) dup.add(i);
        }
        return dup;
    }

    /**
     * 先排序
     * 时间复杂度： O(nlogn)
     * 空间复杂度:  O(1)
     * 找出所有的重复数字
     *
     * @param a
     * @return
     */
    public ArrayList<Integer> getDuplicate2(int[] a) {
        int n = a.length;
        int[] b = Arrays.copyOf(a, n);   // 复制数组是为了不影响后面的测试，跟空间复杂度没关系
        Arrays.sort(b);
        ArrayList<Integer> dup = new ArrayList<>();

        if (b[n - 1] >= n) {
            System.out.println("数值" + b[n - 1] + "超出范围");
            return dup;
        }
        for (int i = 0, j = 1; j < n; j++) {
            if (b[i] == b[j]) {
                dup.add(b[i]);
                while (j < n && b[i] == b[j]) {
                    j++;
                }
            }
            i = j;
        }
        return dup;
    }

    /**
     * 获得随机一个重复数字，若没有重复数，返回-1
     * 时间复杂度: O(n)
     * 空间复杂度: O(1)
     * <p>
     * 若没有重复数字，可以通过交换得到对任意i都有  a[i] = i
     *
     * @param a
     * @return
     */
    public int getDuplicate3(int[] a) {
        int n = a.length;
        int[] b = Arrays.copyOf(a, n);// 复制数组是为了不影响后面的测试，跟空间复杂度没关系

        for (int i = 0; i < n; i++) {
            if (b[i] < 0 || b[i] >= n) {
                System.out.println("数值" + b[i] + "超出范围");
                return -1;
            }
        }
        int temp;
        for (int i = 0; i < n; i++) {
            if (b[i] != i) {
                do {
                    if (b[i] == b[b[i]]) {
                        return b[i];
                    } else {
                        temp = b[i];
                        b[i] = b[temp];
                        b[temp] = temp;
                    }
                } while (b[i] != i); // 交换直到b[i] == i
            }
        }
        return -1;
    }

    /**
     * 长度为 n+1 的数组中，所有数字的范围为 1~n（故一定有重复数字）
     * 返回其中一个重复数
     * 时间复杂度 O(nlogn)
     * 空间复杂度 O(1)
     *
     * @return
     */
    public int getDup(int[] a, int length) {
        int start = 1;
        int end = length - 1;
        int middle, count;
        while (start <= end) {
            middle = (end - start) / 2 + start;
            count = getCount(a, length, start, middle);

            if (end == start) {
                // 只有一个元素了
                if (count > 1) return start;
                else break;
            }

            if (count > (middle - start + 1)) {// 数组 a 在 [start,middle] 之间存在重复数
                end = middle;
            } else {             // 数组 a 在 [start,middle] 之间不存在重复数，可能在 [middle+1,end] 之间存在
                start = middle + 1;
            }

        }
        return -1;
    }

    /**
     * 数组中范围在 [start,end] 之间的数目
     *
     * @param a
     * @param length
     * @param start
     * @param end
     * @return
     */
    public int getCount(int[] a, int length, int start, int end) {
        int count = 0;
        for (int i = 0; i < length; i++) {
            if (a[i] >= start && a[i] <= end) {
                count++;
            }
        }
        return count;
    }

    @Test
    public void testGetDup() {
        int[] a = new int[]{2, 3, 8, 9, 9, 4, 1, 5, 6, 7};
        int[] b = new int[]{1, 1};
        int[] c = new int[]{1, 5, 6, 3, 2, 4, 1};
        int[] d = new int[]{1, 5, 2, 3, 4};
        System.out.println(getDup(a, 10));
        System.out.println(getDup(b, 2));
        System.out.println(getDup(c, 7));
        System.out.println(getDup(d, 5));
        System.out.println(getDup(new int[]{}, 0));
        System.out.println(getDup(new int[]{0}, 1));
    }

    @Test
    public void test() {
        int[] a = new int[]{2, 3, 8, 9, 1, 4, 1, 5, 2, 2};
        System.out.println(getDuplicate(a));
        System.out.println(getDuplicate2(a));
        System.out.println(getDuplicate3(a));
        System.out.println("----------");
        int[] b = new int[]{0, 0};
        System.out.println(getDuplicate(b));
        System.out.println(getDuplicate2(b));
        System.out.println(getDuplicate3(b));

        System.out.println("----------");

        int[] c = new int[]{1, 5, 6, 3, 2, 4, 0};
        System.out.println(getDuplicate(c));
        System.out.println(getDuplicate2(c));
        System.out.println(getDuplicate3(c));

        System.out.println("----------");

        int[] d = new int[]{1, 5, 6, 3, 4, 3};
        System.out.println(getDuplicate2(d));
        System.out.println(getDuplicate2(d));
        System.out.println(getDuplicate3(d));

    }
}
