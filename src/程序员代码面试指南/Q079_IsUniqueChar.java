package 程序员代码面试指南;

import org.junit.Test;

/**
 * created by Ethan-Walker on 2019/1/13
 */
public class Q079_IsUniqueChar {

    /**
     * 方法一：hash
     * 时间复杂度: O(n)
     * 空间复杂度: O(256)
     *
     * @param chs
     * @return
     */
    public boolean isUnique(char[] chs) {
        if (chs == null || chs.length <= 1) return true;
        boolean[] exists = new boolean[256];
        for (int i = 0; i < chs.length; i++) {
            if (exists[chs[i]]) return false;
            exists[chs[i]] = true;
        }
        return true;
    }

    /**
     * 要求空间复杂度为 O(1), 且时间复杂度尽可能小
     * <p>
     * 方法二: 排序 (空间复杂度为 O(1) 的排序最快的只有堆排序了)
     * 时间复杂度: O(nlogn)
     * 空间复杂度: O(1)
     *
     * @param chs
     * @return
     */
    public boolean isUnique2(char[] chs) {
        if (chs == null || chs.length <= 1) return true;

        heapSort(chs);
        for (int i = 1; i < chs.length; i++) {
            if (chs[i] == chs[i - 1]) return false;
        }
        return true;
    }

    /**
     * 堆排序，从小到大的顺序，用最大堆
     *
     * @param chs
     */
    public void heapSort(char[] chs) {

        if (chs.length <= 1) return;
        int end = chs.length - 1;
        buildMaxHeap(chs);
        do {
            swap(chs, end, 0);      // 堆顶最大值交换到最后
            siftDown(chs, 0, --end); // 堆顶 siftDown 调整
        } while (end >= 1);
    }

    public void buildMaxHeap(char[] chs) {
        int startIndex = (chs.length - 2) >> 1;
        while (startIndex >= 0) {
            siftDown(chs, startIndex, chs.length - 1);
            startIndex--;
        }
    }

    public void siftDown(char[] chs, int start, int end) {
        int i = start, j = (i << 1) + 1;
        while (j <= end) {
            if (j < end && chs[j + 1] > chs[j]) j++; // j 指向子节点中较大的位置
            if (chs[j] <= chs[i]) break;
            else {
                swap(chs, i, j);
                i = j;
                j = (j << 1) + 1;
            }
        }
    }

    public void swap(char[] chs, int i, int j) {
        char c = chs[i];
        chs[i] = chs[j];
        chs[j] = c;
    }

    @Test
    public void test() {
        String str1 = "12345678";
        String str2 = "123456781";
        String str3 = "123456278";
        String str4 = "123456738";

        System.out.println(isUnique(str1.toCharArray()));
        System.out.println(isUnique(str2.toCharArray()));
        System.out.println(isUnique(str3.toCharArray()));
        System.out.println(isUnique(str4.toCharArray()));
        System.out.println(isUnique(str1.toCharArray()));
        System.out.println(isUnique(str2.toCharArray()));
        System.out.println(isUnique(str3.toCharArray()));
        System.out.println(isUnique(str4.toCharArray()));

    }

}
