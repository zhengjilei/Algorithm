package 程序员代码面试指南;

import org.junit.Test;

/**
 * created by Ethan-Walker on 2019/1/13
 */
public class Q080_FindStr {


    public int getIndex(String[] strs, String dest) {
        if (dest == null || strs == null || strs.length == 0) return -1;

        int l = 0, r = strs.length - 1;
        int mid = 0;
        int firstEqualIndex = -1;
        int compare = 0;
        while (l <= r) {
            mid = (l + r) >> 1;
            if (strs[mid] == null) {
                int j = mid - 1;
                while (j >= l && strs[mid] == null) j--;
                if (j < l) {
                    // 左半部分全为null,去右半部分找
                    l = mid + 1;
                } else {
                    // j 指向从右往左遍历的第一个不为 null 的项
                    int t = strs[j].compareTo(dest);
                    if (t > 0) {
                        // 说明 dest 只可能在左半部分
                        r = j - 1;
                    } else if (t == 0) {
                        firstEqualIndex = j;
                        r = j - 1; // 继续往左找
                    } else {
                        // dest 只可能在右半部分
                        l = mid + 1;
                    }
                }

            } else {
                compare = strs[mid].compareTo(dest);
                if (compare == 0) {
                    firstEqualIndex = mid;
                    r = mid - 1;
                } else if (compare < 0) {
                    // dest 在右半部分
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return firstEqualIndex;
    }


    @Test
    public void test() {
        String[] strs = {null, null, null, null, "c", "d", "d"};
        System.out.println(getIndex(strs, "a"));
    }

}
