package 全排列;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * 输入一个字符串S和一个整数N，其中S中的各个字符均不相等，并且N小于等于S的长度，请输出由S中字符组成的长度为N的所有的排列。
 * 如：字符串是"abc", N=2, 则输出：ab, ac, bc, ba, ca, cb
 */
public class PermutationApply {
    private static ArrayList<String> list = new ArrayList<>();

    /**
     * 从数组a 中的 [0,n)前 n个元素中选出 m 个元素
     *
     * @param m
     * @param n
     * @param a
     */
    public static void Cnm(int m, int n, char[] a, StringBuilder sb) {
        if (m < 0 || n <= 0) return;
        if (m == 0) {
            // 说明已经选好了
            list.add(sb.toString());
            return;
        }
        if (m == n) {
            // 前n个元素恰好全部选
            for (int i = 0; i < n; i++) {
                sb.append(a[i]);
            }
            list.add(sb.toString());
            return;
        } else if (m < n) {
            // 第 n-1 个元素不选
            Cnm(m, n - 1, a, new StringBuilder(sb));
            // 第 n-1 个元素选
            Cnm(m - 1, n - 1, a, new StringBuilder(sb).append(a[n - 1]));

        } else {
            return;
        }
    }


    /**
     * 对 list<String> 中的每一个元素进行全排列，字典序输出
     */
    public static void permutation(String str) {
        char[] chs = str.toCharArray();
        int n = chs.length;
        Arrays.sort(chs);
        System.out.println(chs);
        while (true) {
            // 从右往左找到第一个a[i]<a[i+1]
            int index = -1;
            for (int i = n - 2; i >= 0; i--) {
                if (chs[i] < chs[i + 1]) {
                    index = i;
                    break;
                }
            }
            if (index != -1) {
                //找到index 后的比 a[index] 大但最小的元素
                int min = chs[index + 1];
                int minIndex = index + 1;
                for (int i = minIndex + 1; i < n; i++) {
                    if (chs[i] > chs[index] && chs[i] < min) {
                        minIndex = i;
                        min = chs[i];
                    }
                }
                swap(chs, minIndex, index);
                Arrays.sort(chs, index + 1, n);
                System.out.println(chs);
            } else {
                break;
            }
        }

    }

    public static void swap(char[] chs, int a, int b) {
        char t = chs[a];
        chs[a] = chs[b];
        chs[b] = t;
    }

    public static void main(String[] args) {

        Cnm(3, 4, new char[]{'a', 'b', 'c', 'd'}, new StringBuilder());
//        System.out.println(list);
        for (String str : list) {
            permutation(str);
            System.out.println("---------");
        }
    }
}
