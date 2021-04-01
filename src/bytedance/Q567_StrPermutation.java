package bytedance;

import org.junit.Test;

/**
 * created by Ethan-Walker on 2019/2/1
 */
public class Q567_StrPermutation {

    // 思路1：求s1 的全排列，对每一个s1，通过kmp 在 s2 中查找是否包含 s1
    // 时间复杂度: 最坏 O(n!)


    /**
     * 思路2: 滑动窗口
     * 滑动窗口长度为 s1 的长度，在 s2 中滑动
     * 如果滑动窗口中 各字符的类型和计数和 s1 完全相同，则说明该滑动窗口是 s1 的一个全排列
     */
    public boolean checkInclusion(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() > s2.length()) return false;
        int[] count1 = new int[26];
        int[] count2 = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            count1[s1.charAt(i) - 'a']++;
            count2[s2.charAt(i) - 'a']++;
        }
        // 以s2[0] 开始的滑动窗口
        if (isSame(count1, count2)) return true;

        // 最多向右滑动 s2.length -  s1.length 步进行判断
        int maxStep = s2.length() - s1.length();
        for (int i = 0; i < maxStep; i++) {
            // 滑动窗口在 s2 中向右滑一步
            count2[s2.charAt(i) - 'a']--;
            count2[s2.charAt(i + s1.length()) - 'a']++;
            if (isSame(count1, count2)) return true;
        }
        return false;
    }

    boolean isSame(int[] count1, int[] count2) {
        for (int i = 0; i < count1.length; i++) {
            if (count1[i] != count2[i]) return false;
        }
        return true;
    }

    @Test
    public void test() {
        System.out.println(checkInclusion("ab", "a"));
    }
}
