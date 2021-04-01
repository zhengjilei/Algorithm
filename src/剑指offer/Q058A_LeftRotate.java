package 剑指offer;

import org.junit.Test;

/**
 * 字符串左旋转
 * created by Ethan-Walker on 2018/12/14
 */
public class Q058A_LeftRotate {


    /**
     * 将字符串左起的k位 翻转到最右边
     * 思路：abcdefg
     * 若 k<=length/2 例 k = 3
     * 将前 k 位和倒数 k 位交换 ，得到 efgdabc,倒数k位确定，循环对序列 efgd 左翻转 k 位
     * 若 k>length 例 efgd k=3
     * 用辅助数组，记录除前k位的剩余 length-k 位
     * 然后将前 k 位往后移 length - k 位
     * 最后复制辅助数组到 数组的前 length-k 位
     * 时间复杂度: O(n)
     * 空间复杂度: O(1)
     *
     * @param k
     * @return
     */
    public void leftRotate(char[] chs, int k, int end) {


        if (chs == null || chs.length == 0 || k <= 0 || k >= (end + 1)) return;

        int border = (end + 1) / 2;

        if (k > border) {
            int stepInto = end + 1 - k;
            char[] help = new char[stepInto];

            for (int i = k, j = 0; i <= end; i++, j++) {
                help[j] = chs[i];
            }
            for (int i = k - 1; i >= 0; i--) {
                chs[i + stepInto] = chs[i];
            }
            for (int i = 0; i < stepInto; i++) {
                chs[i] = help[i];
            }
            return;
        } else {
            for (int i = k - 1, j = end; i >= 0; i--, j--) {
                swap(chs, i, j);
            }
            leftRotate(chs, k, end - k);
        }
    }

    public void swap(char[] chs, int i, int j) {
        char c = chs[i];
        chs[i] = chs[j];
        chs[j] = c;
    }


    /**
     * 将字符串左起的k位 翻转到最右边
     * 思路： abcdefg  k=3
     * 先将[0,k-1] [k,length-1] 各自反转    得到cbagfed
     * 然后将整个字符串反转得到 defgabc
     * <p>
     * 时间复杂度: O(n)
     * 空间复杂度: O(1)
     *
     * @param s
     * @param k
     * @return
     */
    public String leftRotate(String s, int k) {
        if (s == null || s.length() == 0 || k <= 0 || k >= s.length()) return s;
        char[] chs = s.toCharArray();
        reversePart(chs, 0, k - 1);
        reversePart(chs, k, chs.length - 1);
        reversePart(chs, 0, chs.length - 1);

        return new String(chs);
    }

    public void reversePart(char[] chs, int start, int end) {
        if (start >= end) return;
        int mid = (end - start + 1) / 2 + start; // length/2 + start
        for (int i = start, j = 0; i < mid; i++, j++) {
            swap(chs, i, end - j);
        }
    }

    @Test
    public void test() {

        String s = "abcdefg";
        char[] chs = s.toCharArray();
        int k = 6;
        leftRotate(chs, k, chs.length - 1);
        System.out.println(new String(chs));


        System.out.println(leftRotate(s, k));
    }

}
